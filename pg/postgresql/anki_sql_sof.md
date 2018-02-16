
---

## General Structure of a Function

### Q: Parts of a Function's Definition

Fill in spaces 7

··  {{c1::CREATE OR REPLACE}} FUNCTION foo() <br>
····  RETURNS {{c2::TABLE (a int, b int, c int)}} AS <br>
··  $func$ <br>
··  {{c3::BEGIN}} <br>
····   RETURN {{c4::QUERY VALUES}} <br>
······   {{c5::(1,2,3) <br>
··········  , (3,4,5) <br>
············   , (3,4,5)}}; <br>
··  END <br>
··  $func$  {{c6::LANGUAGE}} plpgsql IMMUTABLE ROWS 3; <br>

%

% 

clozeq

---

## Function Declaration: RETURNS Types

### Q: RETURNS ...

··  CREATE OR REPLACE FUNCTION foo() <br>
··  RETURNS ... AS <br>

Use RETURNS {{c1::TABLE}} to define an ad-hoc row type to return.
Or RETURNS {{c2::SETOF mytbl}} to use a pre-defined row type.

%

% 

clozeq

---

## Quoting Double or Single

### Q: Illegal Names (mixed-case, reserved words)

Which quote to use then? Ex: 

MyTable <br>
date <br>

%

### A: 

Double quotes

"MyTable" <br>
"date" <br>

---

## Calling a Function

### Q: How to call a function?

Example function:

CREATE OR REPLACE FUNCTION foo(open-id numeric) <br>

How to call foo?

%

### A:

SELECT * FROM foo(1); <br>

---

## Group By: Select first row in each GROUP BY group?

### Q: How to select first row in each GROUP?
 

SELECT customer, ... <br>
FROM  purchases <br>
GROUP BY customer <br>
ORDER BY total DESC; <br>

Something like:

SELECT customer, FIRST(id) <br>

%

### A: opt1: DISTINCT ON

SELECT DISTINCT ON (customer) <br>
customer, id <br>
FROM   purchases <br>
ORDER  BY customer, id; <br>

---

## How to ORDER when column can be NULL


ORDER  BY total DESC ...; <br>

%

### A:

ORDER  BY total DESC NULLS LAST; <br>

---

## INDEX with multiple ORDER columns

### Q: What is a good index with multiple ORDER keys?

Ex:

··  SELECT DISTINCT ON (customer) <br>
··  customer, id <br>
··  FROM   purchases <br>
··  ORDER  BY customer, id; <br>

··  CREATE INDEX some-idx ON purchases ({{c1::customer, id}}); <br>

%

%

clozeq

---

## Select first row in each GROUP BY group

input:

··  id | customer | total <br>
··  ---+----------+------ <br>
··  1 | Joe····  | 5 <br>
··  2 | Joe····  | 3 <br>

output:

··  FIRST(id) | customer | FIRST(total) <br>
··  ----------+----------+------------- <br>
··  1 | Joe····  | 5 <br>

··  SELECT {{c1::DISTINCT ON}} {{c2::(customer)}} <br>
··  id, customer, total <br>
··  FROM   purchases <br>
··  {{c3::ORDER  BY}} {{c4::customer, total DESC, id}}; <br>

%

% 

clozeq

---

## how to escape single quotes

opt1: double quotes

'{{c1::user''s}} log'

opt2: dollar quoted

{{c1::$$}}escape ' with '' {{c1::$$}}

%

% 

clozeq

---

## PostgreSQL Crosstab Query: Which module and function to use?

Section··  Status··  Count <br>
A········  Active··  1 <br>
A········  Inactive  2 <br>
B········  Active··  4 <br>
B········  Inactive  5 <br>

--&gt;

Section··  Active··  Inactive <br>
A········  1······   2 <br>
B········  4······   5 <br>

%

install 'tablefunc'

use crosstab()

%

---

## PostgreSQL Crosstab Query: SQL Query

··  Section··  Status··  Count <br>
··  A········  Active··  1 <br>
··  A········  Inactive  2 <br>
··  B········  Active··  4 <br>
··  B········  Inactive  5 <br>
--&gt; <br>
··  Section··  Active··  Inactive <br>
··  A········  1······   2 <br>
··  B········  4······   5 <br>

··  SELECT * <br>
··  FROM   {{c1::crosstab}}( <br>
··  'SELECT {{c2::section, status, ct}} <br>
··  FROM   t <br>
··  ORDER  BY {{c3::1,2}}'   <br>
··  ) AS ct ({{c4::"Section" text}}, "Active" int, "Inactive" int); <br>

%

%

clozeq

---

## Select rows which are not present in other table: with NOT EXISTS

two postgresql tables:

··  table name··   column names <br>
··  -----------··  ------------------------ <br>
··  login-log····  ip | etc. <br>
··  ip-location··  ip | location | hostname | etc. <br>

query like

··  SELECT login-log.ip  <br>
··  FROM login-log  <br>
··  WHERE NOT EXIST (SELECT ip-location.ip <br>
··  FROM ip-location <br>
··  WHERE login-log.ip = ip-location.ip) <br>

··  SELECT ip  <br>
··  FROM   login-log l  <br>
··  WHERE  {{c3::NOT EXISTS}} ( <br>
··  SELECT {{c1::1}}············  -- it's mostly irrelevant what you put here <br>
··  FROM   ip-location i <br>
··  WHERE  {{c2::l.ip = i.ip}} <br>
··  ); <br>

%

%

clozeq

---

## Select rows which are not present in other table: with LEFT JOIN

two postgresql tables:

··  table name··   column names <br>
··  -----------··  ------------------------ <br>
··  login-log····  ip | etc. <br>
··  ip-location··  ip | location | hostname | etc. <br>

query like

··  SELECT login-log.ip  <br>
··  FROM login-log  <br>
··  WHERE NOT EXIST (SELECT ip-location.ip <br>
··  FROM ip-location <br>
··  WHERE login-log.ip = ip-location.ip) <br>

··  SELECT l.ip  <br>
··  FROM   login-log l  <br>
··  LEFT   JOIN ip-location i {{c2::USING}} ({{c2::ip}})  -- short for: ON {{c1::i.ip}} = l.ip <br>
··  WHERE  i.ip IS {{c3::NULL}}; <br>

%

%

clozeq

---

## Select rows which are not present in other table: with EXCEPT

two postgresql tables:

··  table name··   column names <br>
··  -----------··  ------------------------ <br>
··  login-log····  ip | etc. <br>
··  ip-location··  ip | location | hostname | etc. <br>

query like

··  SELECT login-log.ip  <br>
··  FROM login-log  <br>
··  WHERE NOT EXIST (SELECT ip-location.ip <br>
··  FROM ip-location <br>
··  WHERE login-log.ip = ip-location.ip) <br>

··  SELECT ip  <br>
··  FROM   login-log <br>
··  {{c1::EXCEPT ALL}}··············  <br>
··  SELECT {{c2::ip}} <br>
··  FROM   {{c3::ip-location}}; <br>

%

%

clozeq

---

## What is easier to read in EXISTS subqueries? [closed]

··  SELECT foo FROM bar WHERE EXISTS (SELECT {{c1::*}} FROM baz WHERE baz.id = bar.id); <br>

%

%

clozeq

---

## What is semi-join in SQL


··  SELECT *  <br>
··  FROM Customers C  <br>
··  WHERE {{c1::EXISTS}} (  <br>
··  SELECT *  <br>
··  FROM Sales S  <br>
··  WHERE {{c2::S.Cust-Id = C.Cust-Id}}  <br>
··  )  <br>

%

%

clozeq

---

## Join with NULL values?

what to use if there are NULL in user-id?

··  WHERE {{c1::NOT EXISTS}} ( <br>
··  SELECT 1 <br>
··  FROM   votes v <br>
··  WHERE  v.some-id = base-table.some-id <br>
··  AND··  v.user-id = ? <br>
······   )

what to use if there are no NULL in user-id?

··  WHERE {{c1::NOT IN}} ( <br>
··  SELECT 1 <br>
··  FROM   votes v <br>
··  WHERE  v.some-id = base-table.some-id <br>
··  AND··  v.user-id = ? <br>
······   )

%

%

clozeq

---

## How to check if a table exists in a given schema

opt1: {{c1::information-schema.tables}}

··  SELECT EXISTS ( <br>
··  SELECT 1 <br>
··  FROM   {{c1::information-schema.tables}}  <br>
··  WHERE  table-schema = 'schema-name' <br>
··  AND··  table-name = 'table-name' <br>
··  ); <br>

opt2: {{c2::pg-tables}}

··  SELECT EXISTS ( <br>
··  SELECT 1  <br>
··  FROM   {{c2::pg-tables}} <br>
··  WHERE  schemaname = 'schema-name' <br>
··  AND··  tablename = 'table-name' <br>
··  ); <br>

%

%

clozeq

---

## How to implement a many-to-many relationship in PostgreSQL? id=g-10176

··  CREATE TABLE product ( <br>
····  product-id serial PRIMARY KEY  -- implicit primary key constraint <br>
····  , product··  text NOT NULL <br>
····  , price····  numeric NOT NULL DEFAULT 0 <br>
··  ); <br>
··  CREATE TABLE bill ( <br>
····  bill-id  serial PRIMARY KEY <br>
····  , bill··   text NOT NULL <br>
····  , billdate date NOT NULL DEFAULT CURRENT-DATE <br>
··  ); <br>
··  CREATE TABLE bill-product ( <br>
····  bill-id··  int REFERENCES {{c1::bill (bill-id)}} ON {{c2::UPDATE CASCADE ON DELETE CASCADE}} <br>
····  , product-id int REFERENCES {{c1::product (product-id)}} ON {{c2::UPDATE CASCADE}} <br>
····  , amount··   numeric NOT NULL {{c3::DEFAULT 1}} <br>
····  , CONSTRAINT bill-product-pkey PRIMARY KEY {{c4::(bill-id, product-id)}}  -- explicit pk <br>
··  ); <br>

%

%

clozeq

---

## PostgreSQL 10 identity columns explained

before:

··  CREATE TABLE test-old ( <br>
····  id serial PRIMARY KEY, <br>
····  payload text <br>
··  ); <br>
··  INSERT INTO test-old (payload) VALUES ('a'), ('b'), ('c') RETURNING *; <br>
··  ALTER SEQUENCE test-old-id-seq RESTART WITH 1000; <br>

now: 

··  CREATE TABLE test-new ( <br>
····  id int {{c1::GENERATED BY DEFAULT}} AS {{c2::IDENTITY PRIMARY KEY}}, <br>
····  payload text <br>
··  ); <br>
··  INSERT INTO test-new (payload) VALUES ('a'), ('b'), ('c') RETURNING *; <br>
··  ALTER TABLE test-new ALTER COLUMN {{c3::id}} RESTART WITH 1000; <br>

%

%

clozeq

---

## How do I query using fields inside the new PostgreSQL JSON datatype?

··  SELECT * <br>
··  FROM   {{c3::json-array-elements}}( <br>
····  '[{"name": "Toby", "occupation": "Software Engineer"}, <br>
····  {"name": "Zaphod", "occupation": "Galactic President"} ]' <br>
····  ) AS elem <br>
··  WHERE elem{{c1::-&gt;&gt;}}{{c2::'name'}} = 'Toby'; <br>

%

%

clozeq

---

## Index for finding an element in a JSON array

q

··  CREATE TABLE tracks (id SERIAL, artists JSON); <br>
··  INSERT INTO tracks (id, artists)  <br>
····  VALUES (1, '[{"name": "blink-182"}]'); <br>
··  INSERT INTO tracks (id, artists)  <br>
····  VALUES (2, '[{"name": "The Dirty Heads"}, {"name": "Louis Richards"}]'); <br>
··  SELECT * FROM tracks <br>
····  WHERE 'The Dirty Heads' IN  <br>
····  (SELECT value-&gt;&gt;'name' FROM json-array-elements(artists)) <br>

this does a full table scan <br>
 
better:

··  SELECT * FROM tracks WHERE artists {{c1::@&gt;}} '[{{{c2::"name"}}: "The Dirty Heads"}]'; <br>

%

%

clozeq

---

## Check if NULL exists in Postgres array

opt 

··  {{c1::array-replace}}(ar, NULL, 0) &lt;&gt; ar <br>
··  -- substitute
··  {{c2::array-remove}}(ar, NULL) &lt;&gt; ar <br>
··  -- delete
··  {{c3::array-position}}(ar, NULL) IS NOT NULL <br>
··  -- index

%

%

clozeq

---

## Check if NULL exists in Postgres array: function wrapper

opt 

··  {{c1::array-position}}(ar, NULL) IS NOT NULL <br>

··  CREATE OR REPLACE FUNCTION f-array-has-null ({{c2::anyarray}}) <br>
····  RETURNS {{c3::bool}} LANGUAGE {{c4::sql}} IMMUTABLE AS <br>
····  'SELECT {{c5::array-position}}($1, NULL) IS NOT NULL'; <br>

%

%

clozeq

---

## Polymorphic Types: what are they?

Five pseudo-types of special interest are {{c1::anyelement}}, {{c2::anyarray}}, {{c3::anynonarray}}, {{c4::anyenum}}, and {{c5::anyrange}}, which are collectively called polymorphic types. 

%

%

clozeq

---

## Best way to select random rows PostgreSQL

select * from table where {{c1::random()}} &lt; 0.01; <br>

%

%

clozeq

---

## Fast way to discover the row count of a table in PostgreSQL

SELECT reltuples::bigint AS {{c1::estimate}} <br>
FROM   pg-class <br>
WHERE  oid = {{c2::to-regclass}}('myschema.mytable') <br>

%

%

clozeq

---

## Double colon (::) notation in SQL

cast to a data type

b.date-completed &gt;  a.dc::{{c1::date}} + INTERVAL '1 DAY 7:20:00' <br>

%

%

clozeq

---

## How to concatenate columns in a Postgres SELECT?

SELECT {{c1::a::text}} {{c1::||}} ', ' || b::text AS ab FROM foo; <br>

%

%

clozeq

---

## How to concatenate columns in a Postgres SELECT? if NULL exists

SELECT {{c1::concat-ws}}(', ', {{c2::a::text}}, b::text) AS ab FROM foo; <br>

Or just concat() if you don't need separators:

SELECT concat({{c3::a::text, b::text}}) AS ab FROM foo; <br>

%

%

clozeq

---

## Combine two columns and add into one new column

before:

··  +---------+--------+-------+ <br>
··  | zipcode |  city  | state | <br>
··  +---------+--------+-------+ <br>
··  | 10954   | Nanuet | NY··  | <br>
··  +---------+--------+-------+ <br>

after:

··  +---------+--------+-------+--------------------+ <br>
··  | zipcode |  city  | state |····  combined····  | <br>
··  +---------+--------+-------+--------------------+ <br>
··  | 10954   | Nanuet | NY··  | 10954 - Nanuet, NY | <br>
··  +---------+--------+-------+--------------------+ <br>


··  CREATE FUNCTION {{c1::combined}}({{c2::rec}} tbl) <br>
··  RETURNS text <br>
··  LANGUAGE {{c3::SQL}} <br>
··  AS $$ <br>
··  SELECT {{c4::$1.zipcode}} || ' - ' || $1.city || ', ' || $1.state; <br>
··  $$; <br>
··  SELECT *, tbl.{{c5::combined}} FROM tbl; <br>

if NULL exists:
  
SELECT {{c1::concat}}(col-a, col-b); <br>

%

%

clozeq

---

## How to enforce maximum length to TEXT field?

ALTER TABLE tbl ADD CONSTRAINT tbl-col-len {{c1::CHECK}} ({{c2::length}}(col) &lt; 100); <br>

%

%

clozeq

---

## Does PostgreSQL support “accent insensitive” collations?

CREATE EXTENSION {{c1::unaccent}}; <br>

ex

··  SELECT * <br>
··  FROM   users <br>
··  WHERE  {{c2::unaccent}}(name) = {{c3::unaccent}}('João'); <br>

%

%

clozeq

---

## Are PostgreSQL column names case-sensitive?

{{c1::yes}}: All identifiers that are not double-quoted are folded to {{c2::lower}} case in PostgreSQL

%

%

clozeq

---

## Single quote vs double quote

··  SELECT * FROM persons WHERE {{c1::"col"}} = {{c1::'xyz'}}; <br>
%

%

clozeq

---

## Query a parameter (postgresql.conf setting) like “max-connections”

opt

{{c1::SHOW}} max-connections; <br>

SHOW {{c1::ALL}}; <br>

··  SELECT * <br>
··  FROM   {{c1::pg-settings}} <br>
··  WHERE  name = 'max-connections'; <br>

%

%

clozeq

---

## How to do join using IN or EXISTS

Use IN instead of JOIN:

··  SELECT s.stud-id, s.name <br>
··  FROM   student s <br>
··  JOIN   student-club x ON s.stud-id = x.stud-id <br>
··  JOIN   student-club y ON s.stud-id = y.stud-id <br>
··  WHERE  x.club-id = 30 <br>
··  AND··  y.club-id = 50; <br>

-&gt; IN

··  SELECT s.stud-id,  s.name <br>
··  FROM   student s <br>
··  WHERE  s.stud-id {{c1::IN}} (SELECT stud-id FROM {{c1::student-club}} WHERE club-id = 30) <br>
··  AND··  s.stud-id {{c1::IN (SELECT stud-id FROM student-club WHERE club-id = 50)}}; <br>

-&gt; EXISTS

··  SELECT s.stud-id,  s.name <br>
··  FROM   student s <br>
··  WHERE  {{c1::EXISTS}} ({{c1::SELECT * FROM student-club}} <br>
··  WHERE  stud-id = s.stud-id AND club-id = 30) <br>
··  {{c1::AND··  EXISTS (SELECT * FROM student-club <br>
··  WHERE  stud-id = s.stud-id AND club-id = 50)}}; <br>

%

%

clozeq

---

## Export specific rows from a PostgreSQL table as INSERT SQL script

··  COPY {{c1::(SELECT * FROM nyummy.cimory WHERE city = 'tokio')}} TO '{{c1::/path/to/file.csv}}'; <br>

%

%

clozeq

---

## Import from csv file

··  COPY {{c2::other-tbl}} FROM '{{c1::/path/to/file.csv}}'; <br>

%

%

clozeq

---

## COPY vs pg-dump/psql

%

COPY: runs on server

pg-dump, psql: runs on client

%

---

## PostgreSQL sort by datetime asc, null first?

%

ORDER BY last-updated NULLS FIRST <br>

%

---

## PostgreSQL unnest() with element number

··  id | elements <br>
··  ---+------------ <br>
··  1  |ab,cd,efg,hi <br>
··  2  |jk,lm,no,pq <br>
··  3  |rstuv,wxyz <br>

-&gt;

··  id | elem | nr <br>
··  ---+------+--- <br>
··  1  | ab   | 1 <br>
··  1  | cd   | 2 <br>
··  1  | efg  | 3 <br>

··  SELECT t.id, a.elem, a.nr <br>
··  FROM   tbl t, unnest({{c1::string-to-array}}({{c2::t.elements}}, ',')) {{c4::WITH ORDINALITY}} a({{c3::elem, nr}}); <br>

%

%

clozeq

---

## Concatenate multiple result rows of one column into one, group by another column 

q

··  Movie   Actor··  <br>
··  A····   1 <br>
··  A····   2 <br>
··  A····   3 <br>
··  B····   4 <br>

--&gt;

Movie   ActorList <br>
A····   1, 2, 3 <br>

ans

··  SELECT movie, {{c1::string-agg}}(actor, ', ') AS actor-list <br>
··  FROM   tbl <br>
··  GROUP  BY {{c2::1}}; <br>

%

%

clozeq

---

## How to update selected rows with values from a CSV file in Postgres?

··  CREATE {{c1::TEMP}} TABLE tmp-x (id int, apple text, banana text);  <br>
··  {{c2::COPY}} tmp-x FROM '/absolute/path/to/file' (FORMAT csv); <br>

now update original tbl

··  {{c3::UPDATE}} tbl <br>
··  SET··  banana = tmp-x.banana <br>
··  FROM   tmp-x <br>
··  WHERE  {{c4::tbl.id = tmp-x.id}}; <br>
··  DROP TABLE tmp-x; -- else it is dropped at end of session automatically <br>

%

%

clozeq

---

## Duplicate a table's fields only

··  CREATE TEMP TABLE tmp-x AS {{c1::SELECT}} * FROM tbl {{c2::LIMIT 0}}; <br>

%

%

clozeq

---

## How to select id with max date group by category in PostgreSQL

··  SELECT {{c1::DISTINCT ON}} (category) <br>
····  id <br>
··  FROM   tbl <br>
··  {{c2::ORDER  BY}} category, {{c3::"date" DESC}}; <br>

%

%

clozeq

---

## Postgresql manual: format() id=adb-009

s: simple string

I: SQL identifiier

L: SQL literal

··  SELECT format({{c1::'Hello %s'}}, 'World'); <br>
··  Result: Hello World <br>
··  SELECT format('Testing %s, %s, %s, {{c2::%%}}', 'one', 'two', 'three'); <br>
··  Result: Testing one, two, three, % <br>
··  SELECT format('INSERT INTO {{c3::%I}} VALUES ({{c4::%L}})', 'Foo bar', E'O\'Reilly'); <br>
··  Result: INSERT INTO "Foo bar" VALUES('O''Reilly') <br>

%

%

clozeq

---

## Creating temporary tables in SQL


··  {{c1::CREATE TEMP}} TABLE temp1 {{c2::AS}} <br>
··  {{c3::SELECT}} dataid <br>
····  , register-type <br>
····  , timestamp-localtime <br>
····  , read-value-avg <br>
··  FROM   rawdata.egauge <br>
··  WHERE  register-type LIKE '%gen%' <br>
··  ORDER  BY dataid, timestamp-localtime <br>

%

%

clozeq

---

## Grant all on a specific schema in the db to a group role in PostgreSQL

··  GRANT {{c1::ALL PRIVILEGES}} ON {{c2::ALL TABLES IN SCHEMA}} foo TO {{c3::staff}}; <br>

what about new objects:

··  ALTER {{c4::DEFAULT}} PRIVILEGES IN SCHEMA foo GRANT ALL PRIVILEGES ON TABLES TO staff; <br>

%

%

clozeq

---

## How to return result of a SELECT inside a function in PostgreSQL

function

··  CREATE OR REPLACE FUNCTION word-frequency(-max-tokens int) <br>
····  {{c1::RETURNS TABLE}} ( <br>
····  txt   text   -- visible as OUT parameter inside and outside function <br>
····  , cnt   bigint <br>
····  , ratio bigint) AS <br>
··  $func$ <br>
··  BEGIN <br>
····  {{c1::RETURN QUERY}} <br>
····  SELECT t.txt <br>
······  , count(*) AS cnt  -- column alias only visible inside <br>
······  , (count(*) * 100) / -max-tokens  -- I added brackets <br>
····  FROM  ( <br>
······  SELECT t.txt <br>
······  FROM   token t <br>
······  WHERE  t.chartype = 'ALPHABETIC' <br>
······  LIMIT  -max-tokens <br>
····  ) t <br>
····  GROUP  BY t.txt <br>
····  ORDER  BY cnt DESC;  -- note the potential ambiguity  <br>
··  END <br>
··  $func$  LANGUAGE plpgsql; <br>

Call:

··  SELECT * FROM {{c2::word-frequency}}(123); <br>

%

%

clozeq

---

## What names to use instead of "text" and "count"?

Don't to use "text" and "count" as column names

%

txt, cnt better <br>

%

---

## PostgreSQL: aggregate count of minutes in a column by minute

··  SELECT {{c1::DISTINCT}} <br>
····  date-trunc('minute', "when") AS minute <br>
····  , {{c2::count}}(*) {{c3::OVER}} (ORDER BY date-trunc({{c4::'minute'}}, "when")) AS running-ct <br>
··  FROM   mytable <br>
··  ORDER  BY 1; <br>

%

%

clozeq

---

## PostgreSQL - GROUP BY clause or be used in an aggregate function

q

··  cars <br>
··  |id|name|created-at|update-at| <br>
··  users <br>
··  |user-name|car-id|used-at| <br>
····  --&gt; <br>
··  |car-id|name|..|count-used| <br>

ans

··  SELECT id, name, created-at, updated-at, u.ct <br>
····  FROM   cars c <br>
····  {{c1::LEFT   JOIN}} ( <br>
··  SELECT car-id, count(*) AS ct <br>
····  FROM   users <br>
····  {{c2::GROUP  BY 1}} <br>
····  ) u {{c3::ON}} {{c4::u.car-id  = c.id}} <br>
····  ORDER  BY u.ct DESC; <br>

%

%

clozeq

---

## How do you find results that occurred in the past week?

··  SELECT * FROM books WHERE returned-date &gt; {{c1::now()::date}} - 7 <br>

%

%

clozeq

---

## Votes for posts

schema

··  posts <br>
··  |post-id| <br>
··  votes <br>
··  |vote-id|post-id| <br>
····  --&gt; <br>
··  |votes-for-post|post-id| <br>

opt1: EXISTS

··  SELECT count(*) AS post-ct <br>
··  FROM   posts p <br>
··  WHERE  EXISTS ({{c1::SELECT 1 FROM votes v WHERE v.post-id = p.id}}); <br>

ex2: DISTINCT

··  SELECT {{c2::count(DISTINCT p.id)}} AS post-ct <br>
··  FROM   posts p <br>
··  JOIN   votes v ON v.post-id = p.id; <br>

%

%

clozeq

---

## How to convert “string” to “timestamp without time zone”

opt1: ISO 8601 format

{{c1::'2013-08-20 14:52:49'}}::{{c2::timestamp}} <br>

opt2: in general

{{c3::to-timestamp}}('20/8/2013 14:52:49',{{c4:: 'DD/MM/YYYY hh24:mi:ss'}}) <br>
%

%

clozeq

---

## Get month name from number in PostgreSQL

··  SELECT {{c1::to-char}}({{c2::to-timestamp}} (4::text,{{c3:: 'MM'}}), 'TMmon') <br>

%

%

clozeq

---



