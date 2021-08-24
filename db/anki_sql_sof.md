
___

## General Structure of a Function

### Q: Parts of a Function's Definition

Fill in spaces 7

··  `` {{c1::CREATE OR REPLACE}} FUNCTION foo()  `` <br>
····  `` RETURNS {{c2::TABLE (a int, b int, c int)}} AS  `` <br>
··  `` $func$  `` <br>
··  `` {{c3::BEGIN}}  `` <br>
····   `` RETURN {{c4::QUERY VALUES}}  `` <br>
······   `` {{c5::(1,2,3)  `` <br>
··········  `` , (3,4,5)  `` <br>
············   `` , (3,4,5)}};  `` <br>
··  `` END  `` <br>
··  `` $func$  {{c6::LANGUAGE}} plpgsql IMMUTABLE ROWS 3;  `` <br>

%

% 

clozeq
active

___

## Function Declaration: RETURNS Types

### Q: RETURNS ...

··  `` CREATE OR REPLACE FUNCTION foo()  `` <br>
··  `` RETURNS ... AS  `` <br>

Use RETURNS {{c1::TABLE}} to define an `ad_hoc` row type to return.
Or RETURNS {{c2::SETOF mytbl}} to use a `pre_defined` row type.

%

% 

clozeq
active

___

## Quoting Double or Single

### Q: Illegal Names `(mixed_case,` reserved words)

Which quote to use then? Ex: 

MyTable 
date 

%

### A: 

Double quotes

"MyTable" 
"date" 

___

## Calling a Function

### Q: How to call a function?

Example function:

CREATE OR REPLACE FUNCTION `foo(open_id` numeric) 

How to call foo?

%

### A:

SELECT * FROM foo(1); 

___

## Group By: Select first row in each GROUP BY group?

### Q: How to select first row in each GROUP?
 

SELECT customer, ... 
FROM  purchases 
GROUP BY customer 
ORDER BY total DESC; 

Something like:

SELECT customer, FIRST(id) 

%

### A: opt1: DISTINCT ON

SELECT DISTINCT ON (customer) 
customer, id 
FROM   purchases 
ORDER  BY customer, id; 

___

## How to ORDER when column can be NULL


ORDER  BY total DESC ...; 

%

### A:

ORDER  BY total DESC NULLS LAST; 

___

## INDEX with multiple ORDER columns

### Q: What is a good index with multiple ORDER keys?

Ex:

··  `` SELECT DISTINCT ON (customer)  `` <br>
··  `` customer, id  `` <br>
··  `` FROM   purchases  `` <br>
··  `` ORDER  BY customer, id;  `` <br>

··  `` CREATE INDEX some_idx ON purchases ({{c1::customer, id}});  `` <br>

%

%

clozeq
active

___

## Select first row in each GROUP BY group

input:

··  `` id | customer | total  `` <br>
··  `` ___+__________+______  `` <br>
··  `` 1 | Joe····  | 5  `` <br>
··  `` 2 | Joe····  | 3  `` <br>

output:

··  `` FIRST(id) | customer | FIRST(total)  `` <br>
··  `` __________+__________+_____________  `` <br>
··  `` 1 | Joe····  | 5  `` <br>

··  `` SELECT {{c1::DISTINCT ON}} {{c2::(customer)}}  `` <br>
··  `` id, customer, total  `` <br>
··  `` FROM   purchases  `` <br>
··  `` {{c3::ORDER  BY}} {{c4::customer, total DESC, id}};  `` <br>

%

% 

clozeq
active

___

## how to escape single quotes

opt1: double quotes

'{{c1::user''s}} log'

opt2: dollar quoted

{{c1::$$}}escape ' with '' {{c1::$$}}

%

% 

clozeq
active

___

## PostgreSQL Crosstab Query: Which module and function to use?

Section··  Status··  Count 
A········  Active··  1 
A········  Inactive  2 
B········  Active··  4 
B········  Inactive  5 

__&gt;

Section··  Active··  Inactive 
A········  1······   2 
B········  4······   5 

%

install 'tablefunc'

use crosstab()

%

___

## PostgreSQL Crosstab Query: SQL Query

··  `` Section··  Status··  Count  `` <br>
··  `` A········  Active··  1  `` <br>
··  `` A········  Inactive  2  `` <br>
··  `` B········  Active··  4  `` <br>
··  `` B········  Inactive  5  `` <br>
__&gt; 
··  `` Section··  Active··  Inactive  `` <br>
··  `` A········  1······   2  `` <br>
··  `` B········  4······   5  `` <br>

··  `` SELECT *  `` <br>
··  `` FROM   {{c1::crosstab}}(  `` <br>
··  `` 'SELECT {{c2::section, status, ct}}  `` <br>
··  `` FROM   t  `` <br>
··  `` ORDER  BY {{c3::1,2}}'··  `` <br>
··  `` ) AS ct ({{c4::"Section" text}}, "Active" int, "Inactive" int);  `` <br>

%

%

clozeq
active

___

## Select rows which are not present in other table: with NOT EXISTS

two postgresql tables:

··  `` table name··   column names  `` <br>
··  `` ___________··  ________________________  `` <br>
··  `` login_log····  ip | etc.  `` <br>
··  `` ip_location··  ip | location | hostname | etc.  `` <br>

query like

··  `` SELECT login_log.ip   `` <br>
··  `` FROM login_log   `` <br>
··  `` WHERE NOT EXIST (SELECT ip_location.ip  `` <br>
··  `` FROM ip_location  `` <br>
··  `` WHERE login_log.ip = ip_location.ip)  `` <br>

··  `` SELECT ip   `` <br>
··  `` FROM   login_log l   `` <br>
··  `` WHERE  {{c3::NOT EXISTS}} (  `` <br>
··  `` SELECT {{c1::1}}············  -- it's mostly irrelevant what you put here  `` <br>
··  `` FROM   ip_location i  `` <br>
··  `` WHERE  {{c2::l.ip = i.ip}}  `` <br>
··  `` );  `` <br>

%

%

clozeq
suspended

___

## Select rows which are not present in other table: with LEFT JOIN

two postgresql tables:

··  `` table name··   column names  `` <br>
··  `` ___________··  ________________________  `` <br>
··  `` login_log····  ip | etc.  `` <br>
··  `` ip_location··  ip | location | hostname | etc.  `` <br>

query like

··  `` SELECT login_log.ip   `` <br>
··  `` FROM login_log   `` <br>
··  `` WHERE NOT EXIST (SELECT ip_location.ip  `` <br>
··  `` FROM ip_location  `` <br>
··  `` WHERE login_log.ip = ip_location.ip)  `` <br>

··  `` SELECT l.ip   `` <br>
··  `` FROM   login_log l   `` <br>
··  `` LEFT   JOIN ip_location i {{c2::USING}} ({{c2::ip}})  -- short for: ON {{c1::i.ip}} = l.ip  `` <br>
··  `` WHERE  i.ip IS {{c3::NULL}};  `` <br>

%

%

clozeq
suspended

___

## Select rows which are not present in other table: with EXCEPT

two postgresql tables:

··  `` table name··   column names  `` <br>
··  `` ___________··  ________________________  `` <br>
··  `` login_log····  ip | etc.  `` <br>
··  `` ip_location··  ip | location | hostname | etc.  `` <br>

query like

··  `` SELECT login_log.ip   `` <br>
··  `` FROM login_log   `` <br>
··  `` WHERE NOT EXIST (SELECT ip_location.ip  `` <br>
··  `` FROM ip_location  `` <br>
··  `` WHERE login_log.ip = ip_location.ip)  `` <br>

··  `` SELECT ip   `` <br>
··  `` FROM   login_log  `` <br>
··  `` {{c1::EXCEPT ALL}}··············   `` <br>
··  `` SELECT {{c2::ip}}  `` <br>
··  `` FROM   {{c3::ip_location}};  `` <br>

%

%

clozeq
suspended

___

## What is easier to read in EXISTS subqueries? [closed]

··  `` SELECT foo FROM bar WHERE EXISTS (SELECT {{c1::*}} FROM baz WHERE baz.id = bar.id);  `` <br>

%

%

clozeq
suspended

___

## What is `semi_join` in SQL


··  `` SELECT *   `` <br>
··  `` FROM Customers C   `` <br>
··  `` WHERE {{c1::EXISTS}} (   `` <br>
··  `` SELECT *   `` <br>
··  `` FROM Sales S   `` <br>
··  `` WHERE {{c2::S.Cust_Id = C.Cust_Id}}   `` <br>
··  `` )   `` <br>

%

%

clozeq
active

___

## Join with NULL values?

what to use if there are NULL in `user_id?`

··  `` WHERE {{c1::NOT EXISTS}} (  `` <br>
··  `` SELECT 1  `` <br>
··  `` FROM   votes v  `` <br>
··  `` WHERE  v.some_id = base_table.some_id  `` <br>
··  `` AND··  v.user_id = ?  `` <br>
······   `` ) `` <br>

what to use if there are no NULL in `user_id?`

··  `` WHERE {{c1::NOT IN}} (  `` <br>
··  `` SELECT 1  `` <br>
··  `` FROM   votes v  `` <br>
··  `` WHERE  v.some_id = base_table.some_id  `` <br>
··  `` AND··  v.user_id = ?  `` <br>
······   `` ) `` <br>

%

%

clozeq
active

___

## How to check if a table exists in a given schema

opt1: `{{c1::information_schema.tables}}`

··  `` SELECT EXISTS (  `` <br>
··  `` SELECT 1  `` <br>
··  `` FROM   {{c1::information_schema.tables}}   `` <br>
··  `` WHERE  table_schema = 'schema_name'  `` <br>
··  `` AND··  table_name = 'table_name'  `` <br>
··  `` );  `` <br>

opt2: `{{c2::pg_tables}}`

··  `` SELECT EXISTS (  `` <br>
··  `` SELECT 1   `` <br>
··  `` FROM   {{c2::pg_tables}}  `` <br>
··  `` WHERE  schemaname = 'schema_name'  `` <br>
··  `` AND··  tablename = 'table_name'  `` <br>
··  `` );  `` <br>

%

%

clozeq
active

___

## How to implement a `many_to_many` relationship in PostgreSQL? `id=g10176`

··  `` CREATE TABLE product (  `` <br>
····  `` product_id serial PRIMARY KEY  -- implicit primary key constraint  `` <br>
····  `` , product··  text NOT NULL  `` <br>
····  `` , price····  numeric NOT NULL DEFAULT 0  `` <br>
··  `` );  `` <br>
··  `` CREATE TABLE bill (  `` <br>
····  `` bill_id  serial PRIMARY KEY  `` <br>
····  `` , bill··   text NOT NULL  `` <br>
····  `` , billdate date NOT NULL DEFAULT CURRENT_DATE  `` <br>
··  `` );  `` <br>
··  `` CREATE TABLE bill_product (  `` <br>
····  `` bill_id··  int REFERENCES {{c1::bill (bill_id)}} ON {{c2::UPDATE CASCADE ON DELETE CASCADE}}  `` <br>
····  `` , product_id int REFERENCES {{c1::product (product_id)}} ON {{c2::UPDATE CASCADE}}  `` <br>
····  `` , amount··   numeric NOT NULL {{c3::DEFAULT 1}}  `` <br>
····  `` , CONSTRAINT bill_product_pkey PRIMARY KEY {{c4::(bill_id, product_id)}}  -- explicit pk  `` <br>
··  `` );  `` <br>

%

%

clozeq
active

___

## PostgreSQL 10 identity columns explained

before:

··  `` CREATE TABLE test_old (  `` <br>
····  `` id serial PRIMARY KEY,  `` <br>
····  `` payload text  `` <br>
··  `` );  `` <br>
··  `` INSERT INTO test_old (payload) VALUES ('a'), ('b'), ('c') RETURNING *;  `` <br>
··  `` ALTER SEQUENCE test_old_id_seq RESTART WITH 1000;  `` <br>

now: 

··  `` CREATE TABLE test_new (  `` <br>
····  `` id int {{c1::GENERATED BY DEFAULT}} AS {{c2::IDENTITY PRIMARY KEY}},  `` <br>
····  `` payload text  `` <br>
··  `` );  `` <br>
··  `` INSERT INTO test_new (payload) VALUES ('a'), ('b'), ('c') RETURNING *;  `` <br>
··  `` ALTER TABLE test_new ALTER COLUMN {{c3::id}} RESTART WITH 1000;  `` <br>

%

%

clozeq
suspended

___

## How do I query using fields inside the new PostgreSQL JSON datatype?

··  `` SELECT *  `` <br>
··  `` FROM   {{c3::json_array_elements}}(  `` <br>
····  `` '[{"name": "Toby", "occupation": "Software Engineer"},  `` <br>
····  `` {"name": "Zaphod", "occupation": "Galactic President"} ]'  `` <br>
····  `` ) AS elem  `` <br>
··  `` WHERE elem{{c1::_>>}}{{c2::'name'}} = 'Toby';  `` <br>

%

%

clozeq
suspended

___

## Index for finding an element in a JSON array

q

··  `` CREATE TABLE tracks (id SERIAL, artists JSON);  `` <br>
··  `` INSERT INTO tracks (id, artists)   `` <br>
····  `` VALUES (1, '[{"name": "blink_182"}]');  `` <br>
··  `` INSERT INTO tracks (id, artists)   `` <br>
····  `` VALUES (2, '[{"name": "The Dirty Heads"}, {"name": "Louis Richards"}]');  `` <br>
··  `` SELECT * FROM tracks  `` <br>
····  `` WHERE 'The Dirty Heads' IN   `` <br>
····  `` (SELECT value_>>'name' FROM json_array_elements(artists))  `` <br>

this does a full table scan 
 
better:

··  `` SELECT * FROM tracks WHERE artists {{c1::@>}} '[{{{c2::"name"}}: "The Dirty Heads"}]';  `` <br>

%

%

clozeq
suspended

___

## Check if NULL exists in Postgres array

opt 

··  `` {{c1::array_replace}}(ar, NULL, 0) <> ar  `` <br>
··  `` -- substitute `` <br>
··  `` {{c2::array_remove}}(ar, NULL) <> ar  `` <br>
··  `` -- delete `` <br>
··  `` {{c3::array_position}}(ar, NULL) IS NOT NULL  `` <br>
··  `` -- index `` <br>

%

%

clozeq
active

___

## Check if NULL exists in Postgres array: function wrapper

opt 

··  `` {{c1::array_position}}(ar, NULL) IS NOT NULL  `` <br>

··  `` CREATE OR REPLACE FUNCTION f_array_has_null ({{c2::anyarray}})  `` <br>
····  `` RETURNS {{c3::bool}} LANGUAGE {{c4::sql}} IMMUTABLE AS  `` <br>
····  `` 'SELECT {{c5::array_position}}($1, NULL) IS NOT NULL';  `` <br>

%

%

clozeq
active

___

## Polymorphic Types: what are they?

Five `pseudo_types` of special interest are {{c1::anyelement}}, {{c2::anyarray}}, {{c3::anynonarray}}, {{c4::anyenum}}, and {{c5::anyrange}}, which are collectively called polymorphic types. 

%

%

clozeq
suspended

___

## Best way to select random rows PostgreSQL

select * from table where {{c1::random()}} &lt; 0.01; 

%

%

clozeq
active

___

## Fast way to discover the row count of a table in PostgreSQL

SELECT reltuples::bigint AS {{c1::estimate}} 
FROM   `pg_class` 
WHERE  oid = `{{c2::to_regclass}}('myschema.mytable')` 

%

%

clozeq
active

___

## Double colon (::) notation in SQL

cast to a data type

b.date_completed >  a.dc::{{c1::date}} + INTERVAL '1 DAY 7:20:00' 

%

%

clozeq
active

___

## How to concatenate columns in a Postgres SELECT?

SELECT {{c1::a::text}} {{c1::||}} ', ' || b::text AS ab FROM foo; 

%

%

clozeq
active

___

## How to concatenate columns in a Postgres SELECT? if NULL exists

SELECT `{{c1::concat_ws}}(',` ', {{c2::a::text}}, b::text) AS ab FROM foo; 

Or just concat() if you don't need separators:

SELECT concat({{c3::a::text, b::text}}) AS ab FROM foo; 

%

%

clozeq
active

___

## Combine two columns and add into one new column

before:

··  `` +_________+________+_______+  `` <br>
··  `` | zipcode |  city  | state |  `` <br>
··  `` +_________+________+_______+  `` <br>
··  `` | 10954   | Nanuet | NY··  |  `` <br>
··  `` +_________+________+_______+  `` <br>

after:

··  `` +_________+________+_______+____________________+  `` <br>
··  `` | zipcode |  city  | state |····  combined····  |  `` <br>
··  `` +_________+________+_______+____________________+  `` <br>
··  `` | 10954   | Nanuet | NY··  | 10954 _ Nanuet, NY |  `` <br>
··  `` +_________+________+_______+____________________+  `` <br>


··  `` CREATE FUNCTION {{c1::combined}}({{c2::rec}} tbl)  `` <br>
··  `` RETURNS text  `` <br>
··  `` LANGUAGE {{c3::SQL}}  `` <br>
··  `` AS $$  `` <br>
··  `` SELECT {{c4::$1.zipcode}} || ' _ ' || $1.city || ', ' || $1.state;  `` <br>
··  `` $$;  `` <br>
··  `` SELECT *, tbl.{{c5::combined}} FROM tbl;  `` <br>

if NULL exists:
``  `` <br>
SELECT `{{c1::concat}}(col_a,` `col_b);` 

%

%

clozeq
suspended

___

## How to enforce maximum length to TEXT field?

ALTER TABLE tbl ADD CONSTRAINT `tbl_col_len` {{c1::CHECK}} ({{c2::length}}(col) &lt; 100); 

%

%

clozeq
active

___

## Does PostgreSQL support “accent insensitive” collations?

CREATE EXTENSION {{c1::unaccent}}; 

ex

··  `` SELECT *  `` <br>
··  `` FROM   users  `` <br>
··  `` WHERE  {{c2::unaccent}}(name) = {{c3::unaccent}}('João');  `` <br>

%

%

clozeq
suspended

___

## Are PostgreSQL column names `case_sensitive?`

{{c1::yes}}: All identifiers that are not `double_quoted` are folded to {{c2::lower}} case in PostgreSQL

%

%

clozeq
suspended

___

## Single quote vs double quote

··  `` SELECT * FROM persons WHERE {{c1::"col"}} = {{c1::'xyz'}};  `` <br>
%

%

clozeq
active

___

## Query a parameter (postgresql.conf setting) like `“max_connections”`

opt

{{c1::SHOW}} `max_connections;` 

SHOW {{c1::ALL}}; 

··  `` SELECT *  `` <br>
··  `` FROM   {{c1::pg_settings}}  `` <br>
··  `` WHERE  name = 'max_connections';  `` <br>

%

%

clozeq
active

___

## How to do join using IN or EXISTS

Use IN instead of JOIN:

··  `` SELECT s.stud_id, s.name  `` <br>
··  `` FROM   student s  `` <br>
··  `` JOIN   student_club x ON s.stud_id = x.stud_id  `` <br>
··  `` JOIN   student_club y ON s.stud_id = y.stud_id  `` <br>
··  `` WHERE  x.club_id = 30  `` <br>
··  `` AND··  y.club_id = 50;  `` <br>

_&gt; IN

··  `` SELECT s.stud_id,  s.name  `` <br>
··  `` FROM   student s  `` <br>
··  `` WHERE  s.stud_id {{c1::IN}} (SELECT stud_id FROM {{c1::student_club}} WHERE club_id = 30)  `` <br>
··  `` AND··  s.stud_id {{c1::IN (SELECT stud_id FROM student_club WHERE club_id = 50)}};  `` <br>

_&gt; EXISTS

··  `` SELECT s.stud_id,  s.name  `` <br>
··  `` FROM   student s  `` <br>
··  `` WHERE  {{c1::EXISTS}} ({{c1::SELECT * FROM student_club}}  `` <br>
··  `` WHERE  stud_id = s.stud_id AND club_id = 30)  `` <br>
··  `` {{c1::AND··  EXISTS (SELECT * FROM student_club  `` <br>
··  `` WHERE  stud_id = s.stud_id AND club_id = 50)}};  `` <br>

%

%

clozeq
suspended

___

## Export specific rows from a PostgreSQL table as INSERT SQL script

··  `` COPY {{c1::(SELECT * FROM nyummy.cimory WHERE city = 'tokio')}} TO '{{c1::/path/to/file.csv}}';  `` <br>

%

%

clozeq
active

___

## Import from csv file

··  `` COPY {{c2::other_tbl}} FROM '{{c1::/path/to/file.csv}}';  `` <br>

%

%

clozeq
active

___

## COPY vs `pg_dump/psql`

%

COPY: runs on server

pg_dump, psql: runs on client

%

___

## PostgreSQL sort by datetime asc, null first?

%

ORDER BY `last_updated` NULLS FIRST 

%

___

## PostgreSQL unnest() with element number

··  `` id | elements  `` <br>
··  `` ___+____________  `` <br>
··  `` 1  |ab,cd,efg,hi  `` <br>
··  `` 2  |jk,lm,no,pq  `` <br>
··  `` 3  |rstuv,wxyz  `` <br>

_&gt;

··  `` id | elem | nr  `` <br>
··  `` ___+______+___  `` <br>
··  `` 1  | ab   | 1  `` <br>
··  `` 1  | cd   | 2  `` <br>
··  `` 1  | efg  | 3  `` <br>

··  `` SELECT t.id, a.elem, a.nr  `` <br>
··  `` FROM   tbl t, unnest({{c1::string_to_array}}({{c2::t.elements}}, ',')) {{c4::WITH ORDINALITY}} a({{c3::elem, nr}});  `` <br>

%

%

clozeq
active

___

## Concatenate multiple result rows of one column into one, group by another column 

q

··  `` Movie   Actor··   `` <br>
··  `` A····   1  `` <br>
··  `` A····   2  `` <br>
··  `` A····   3  `` <br>
··  `` B····   4  `` <br>

__&gt;

Movie   ActorList 
A····   1, 2, 3 

ans

··  `` SELECT movie, {{c1::string_agg}}(actor, ', ') AS actor_list  `` <br>
··  `` FROM   tbl  `` <br>
··  `` GROUP  BY {{c2::1}};  `` <br>

%

%

clozeq
suspended

___

## How to update selected rows with values from a CSV file in Postgres?

··  `` CREATE {{c1::TEMP}} TABLE tmp_x (id int, apple text, banana text);   `` <br>
··  `` {{c2::COPY}} tmp_x FROM '/absolute/path/to/file' (FORMAT csv);  `` <br>

now update original tbl

··  `` {{c3::UPDATE}} tbl  `` <br>
··  `` SET··  banana = tmp_x.banana  `` <br>
··  `` FROM   tmp_x  `` <br>
··  `` WHERE  {{c4::tbl.id = tmp_x.id}};  `` <br>
··  `` DROP TABLE tmp_x; -- else it is dropped at end of session automatically  `` <br>

%

%

clozeq
suspended

___

## Duplicate a table's fields only

··  `` CREATE TEMP TABLE tmp_x AS {{c1::SELECT}} * FROM tbl {{c2::LIMIT 0}};  `` <br>

%

%

clozeq
active

___

## How to select id with max date group by category in PostgreSQL

··  `` SELECT {{c1::DISTINCT ON}} (category)  `` <br>
····  `` id  `` <br>
··  `` FROM   tbl  `` <br>
··  `` {{c2::ORDER  BY}} category, {{c3::"date" DESC}};  `` <br>

%

%

clozeq
active

___

## Postgresql manual: format() `id=adb_009`

s: simple string

I: SQL identifiier

L: SQL literal

··  `` SELECT format({{c1::'Hello %s'}}, 'World');  `` <br>
··  `` Result: Hello World  `` <br>
··  `` SELECT format('Testing %s, %s, %s, {{c2::%%}}', 'one', 'two', 'three');  `` <br>
··  `` Result: Testing one, two, three, %  `` <br>
··  `` SELECT format('INSERT INTO {{c3::%I}} VALUES ({{c4::%L}})', 'Foo bar', E'O\'Reilly');  `` <br>
··  `` Result: INSERT INTO "Foo bar" VALUES('O''Reilly')  `` <br>

%

%

clozeq
active

___

## Creating temporary tables in SQL


··  `` {{c1::CREATE TEMP}} TABLE temp1 {{c2::AS}}  `` <br>
··  `` {{c3::SELECT}} dataid  `` <br>
····  `` , register_type  `` <br>
····  `` , timestamp_localtime  `` <br>
····  `` , read_value_avg  `` <br>
··  `` FROM   rawdata.egauge  `` <br>
··  `` WHERE  register_type LIKE '%gen%'  `` <br>
··  `` ORDER  BY dataid, timestamp_localtime  `` <br>

%

%

clozeq
active

___

## Grant all on a specific schema in the db to a group role in PostgreSQL

··  `` GRANT {{c1::ALL PRIVILEGES}} ON {{c2::ALL TABLES IN SCHEMA}} foo TO {{c3::staff}};  `` <br>

what about new objects:

··  `` ALTER {{c4::DEFAULT}} PRIVILEGES IN SCHEMA foo GRANT ALL PRIVILEGES ON TABLES TO staff;  `` <br>

%

%

clozeq
active

___

## How to return result of a SELECT inside a function in PostgreSQL

function

··  `` CREATE OR REPLACE FUNCTION word_frequency(_max_tokens int)  `` <br>
····  `` {{c1::RETURNS TABLE}} (  `` <br>
····  `` txt   text   -- visible as OUT parameter inside and outside function  `` <br>
····  `` , cnt   bigint  `` <br>
····  `` , ratio bigint) AS  `` <br>
··  `` $func$  `` <br>
··  `` BEGIN  `` <br>
····  `` {{c1::RETURN QUERY}}  `` <br>
····  `` SELECT t.txt  `` <br>
······  `` , count(*) AS cnt  -- column alias only visible inside  `` <br>
······  `` , (count(*) * 100) / _max_tokens  -- I added brackets  `` <br>
····  `` FROM  (  `` <br>
······  `` SELECT t.txt  `` <br>
······  `` FROM   token t  `` <br>
······  `` WHERE  t.chartype = 'ALPHABETIC'  `` <br>
······  `` LIMIT  _max_tokens  `` <br>
····  `` ) t  `` <br>
····  `` GROUP  BY t.txt  `` <br>
····  `` ORDER  BY cnt DESC;  -- note the potential ambiguity   `` <br>
··  `` END  `` <br>
··  `` $func$  LANGUAGE plpgsql;  `` <br>

Call:

··  `` SELECT * FROM {{c2::word_frequency}}(123);  `` <br>

%

%

clozeq
suspended

___

## What names to use instead of "text" and "count"?

Don't to use "text" and "count" as column names

%

txt, cnt better 

%

___

## PostgreSQL: aggregate count of minutes in a column by minute

··  `` SELECT {{c1::DISTINCT}}  `` <br>
····  `` date_trunc('minute', "when") AS minute  `` <br>
····  `` , {{c2::count}}(*) {{c3::OVER}} (ORDER BY date_trunc({{c4::'minute'}}, "when")) AS running_ct  `` <br>
··  `` FROM   mytable  `` <br>
··  `` ORDER  BY 1;  `` <br>

%

%

clozeq
suspended

___

## PostgreSQL _ GROUP BY clause or be used in an aggregate function

q

··  `` cars  `` <br>
··  `` |id|name|created_at|update_at|  `` <br>
··  `` users  `` <br>
··  `` |user_name|car_id|used_at|  `` <br>
····  `` -->  `` <br>
··  `` |car_id|name|..|count_used|  `` <br>

ans

··  `` SELECT id, name, created_at, updated_at, u.ct  `` <br>
····  `` FROM   cars c  `` <br>
····  `` {{c1::LEFT   JOIN}} (  `` <br>
··  `` SELECT car_id, count(*) AS ct  `` <br>
····  `` FROM   users  `` <br>
····  `` {{c2::GROUP  BY 1}}  `` <br>
····  `` ) u {{c3::ON}} {{c4::u.car_id  = c.id}}  `` <br>
····  `` ORDER  BY u.ct DESC;  `` <br>

%

%

clozeq
suspended

___

## How do you find results that occurred in the past week?

··  `` SELECT * FROM books WHERE returned_date > {{c1::now()::date}} _ 7  `` <br>

%

%

clozeq
active

___

## Votes for posts

schema

··  `` posts  `` <br>
··  `` |post_id|  `` <br>
··  `` votes  `` <br>
··  `` |vote_id|post_id|  `` <br>
····  `` -->  `` <br>
··  `` |votes_for_post|post_id|  `` <br>

opt1: EXISTS

··  `` SELECT count(*) AS post_ct  `` <br>
··  `` FROM   posts p  `` <br>
··  `` WHERE  EXISTS ({{c1::SELECT 1 FROM votes v WHERE v.post_id = p.id}});  `` <br>

ex2: DISTINCT

··  `` SELECT {{c2::count(DISTINCT p.id)}} AS post_ct  `` <br>
··  `` FROM   posts p  `` <br>
··  `` JOIN   votes v ON v.post_id = p.id;  `` <br>

%

%

clozeq
suspended

___

## How to convert “string” to “timestamp without time zone”

opt1: ISO 8601 format

{{c1::'2013_08_20 14:52:49'}}::{{c2::timestamp}} 

opt2: in general

{{c3::to_timestamp}}('20/8/2013 14:52:49',{{c4:: 'DD/MM/YYYY hh24:mi:ss'}}) 
%

%

clozeq
active

___

## Get month name from number in PostgreSQL

··  `` SELECT {{c1::to_char}}({{c2::to_timestamp}} (4::text,{{c3:: 'MM'}}), 'TMmon')  `` <br>

%

%

clozeq
active

___


