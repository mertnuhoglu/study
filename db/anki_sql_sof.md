
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

## How to implement a `many_to_many` relationship in PostgreSQL? `id=g_10176`

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


## pgs01: Run posgtersql database server in docker

docker start `{{c1::postgreststarterkit_db_1}}` 

%

%

suspended

___

## pgs01: Run pgcli with existing env variables

··  `` pgcli {{c1::app}}  `` <br>
··  `` set {{c2::search_path}} = {{c3::data}}, public;  `` <br>

%

%

active

___

## pgs01: Env variables for postgresql database clients

··  `` export {{c1::PGHOST}}=localhost  `` <br>
··  `` export {{c2::PGPORT}}=5432  `` <br>
··  `` export {{c3::PGUSER}}=superuser  `` <br>
··  `` export {{c4::PGDATABASE}}=app  `` <br>

%

%

active

___

## pgs01: where is sql documentation notes?

~/Dropbox/mynotes/content/articles/{{c1::articles_db}}.md

%

%

suspended

___

## pgs01: what are the repl tools for sql?

··  `` {{c1::franchise}} notebook  `` <br>
··  `` {{c2::psql}}  `` <br>
··  `` {{c3::jupyter}}: %load_ext sql  `` <br>
··  `` {{c4::pgcli}}  `` <br>
··  `` {{c5::DataGrip}}  `` <br>
··  `` {{c6::rmarkdown}}: sql engine  `` <br>

%

%

active

___

## pgs01: postgresql connection string example?

··  `` {{c1::postgresql}}://{{c2::superuser}}:{{c3::superuserpass}}@localhost/{{c4::app}}  `` <br>

%

%

active

___

## pgs01: how to create new database from terminal

··  `` psql _c '{{c1::CREATE DATABASE}} library OWNER = {{c2::superuser}}'  `` <br>

%

%

active

___

## pgs01: how to connect to pgs db from R?

··  `` con <_ DBI::{{c1::dbConnect}}({{c2::RPostgreSQL}}::PostgreSQL()  `` <br>
····  `` , user = Sys.{{c3::getenv}}("SUPER_USER")  `` <br>
····  `` , password = Sys.getenv("SUPER_USER_PASSWORD")  `` <br>
····  `` , {{c4::dbname}} = "app"  `` <br>
····  `` , host = "localhost"  `` <br>
····  `` , port = "5432"  `` <br>
··  `` )  `` <br>

%

%

active

___

## pgs01: `study_postgresql.md` TOC 01

··  `` {{c1::Run}}: how to run db  `` <br>
··  `` {{c2::Database Administration}}: roles, information schema, GRANT, backup ...  `` <br>
····  `` {{c11::Roles}}: GRANT group1 TO role1, REVOKE, CREATE ROLE, LOGIN INHERIT  `` <br>
····  `` {{c13::Row Level Security (RLS)}}: ALTER TABLE accounts ENABLE ROW LEVEL SECURITY, WITH CHECK  `` <br>
····  `` {{c16::ddl}}: db schema   `` <br>
······  `` {{c6::Views}}: view, recursive view, update <view>, using triggers to update <view>  `` <br>
······  `` {{c5::Constraints}}: ALTER TABLE ... ADD CONSTRAINT ...  `` <br>
······  `` {{c4::Data Types}}: String, regex, Arrays  `` <br>
··  `` {{c3::psql}}: terminal client commands \d \dt  `` <br>
··  `` {{c7::Window Functions}}: AVG(val) OVER (), PARTITION BY, ROW_NUMBER(), ORDER BY  `` <br>
··  `` {{c8::Common Table Expressions (CTE)}}: WITH <cte1> AS (...), WITH RECURSIVE  `` <br>
··  `` {{c9::Functions}}: CREATE FUNCTION ...(arg1 numeric)  `` <br>
··  `` {{c10::Builtin Functions}}: BETWEEN, CASE, substring, to_date  `` <br>
··  `` {{c12::JOIN}}: LEFT JOIN .. ON, USING, EXISTS,   `` <br>
··  `` {{c14::postgrest}}: REST services from db schema  `` <br>
··  `` {{c15::postgrest_starter_kit (psk)}}: template for postgrest apps  `` <br>

%

%

suspended

___

## pgs01: sql grant

··  `` GRANT {{c1::some_privilege}} TO {{c2::some_role}}  `` <br>
··  `` CREATE ROLE todo_user NOLOGIN;  `` <br>
··  `` GRANT {{c3::todo_user}} TO {{c3::group1}};  `` <br>

%

%

active

___

## pgs01: sql privilege list

··  `` ALL {{c1::ON ALL}}  `` <br>
··  `` ON {{c2::ALL TABLES}}/SEQUENCES  `` <br>
····  `` {{c3::SELECT}}  `` <br>
····  `` {{c4::REFERENCES}}  `` <br>
····  `` {{c5::TRIGGER}}  `` <br>
····  `` {{c6::UPDATE}}  `` <br>
····  `` {{c7::DELETE}}  `` <br>
··  `` {{c8::USAGE}} ON SCHEMA  `` <br>
··  `` ON ALL TABLES IN {{c9::SCHEMA}} <my_schema>  `` <br>
··  `` ON {{c10::<schema>}}.<table>  `` <br>

%

%

active

___

## pgs01: sql privilege: grantee can grant onwards

··  `` WITH {{c1::GRANT OPTION}}  `` <br>
··  `` GRANT ALL ON ALL TABLES IN SCHEMA public TO mydb_admin WITH {{c1::GRANT OPTION}}  `` <br>

%

%

suspended

___

## pgs01: backup and restore

··  `` {{c1::pg_dump}}: selective backup  `` <br>
··  `` {{c2::pg_dumpall}}: all  `` <br>
··  `` {{c3::CREATE DATABASE}} mydb  `` <br>
····  `` {{c4::pg_restore}} __dbname=mydb __jobs=4 __verbose mydb.backup  `` <br>
····  `` __section=pre_data  `` <br>

%

%

active

___

## pgs01: password stored in

··  `` ~/{{c1::.pgpass}}  `` <br>

%

%

active

___

## pgs01: the path of disk space to store db in pgs?

··  `` {{c1::TABLESPACE}}  `` <br>
··  `` CREATE {{c1::TABLESPACE}} secondary LOCATION 'path'  `` <br>
··  `` pg_default  `` <br>
··  `` pg_global  `` <br>

%

%

active

___

## pgs01: psql arguments

··  `` psql {{c1::_d}} app {{c2::_h}} localhost {{c3::_p}} 5432 {{c4::_U}} superuser  `` <br>

%

%

active

___

## pgs01: psql commands 01

··  `` {{c1::\c}} <dbname> <username>  `` <br>
··  `` list tables: {{c2::\dt}}  `` <br>
··  `` more: {{c3::\dt+}}  `` <br>
··  `` describe a table: {{c4::\d}} <table_name>  `` <br>
··  `` list schemas: {{c5::\dn}}  `` <br>
··  `` list functions: {{c6::\df}}  `` <br>
··  `` list views: {{c7::\dv}}  `` <br>
··  `` list users and roles: {{c8::\du}}  `` <br>

%

%

active

___

## pgs01: psql commands 02

··  `` save command history: {{c1::\s}} <filename>  `` <br>
··  `` exec commands from file: {{c2::\i}} <filename>  `` <br>
··  `` help on statement: {{c3::\h}} <ALTER TABLE>  `` <br>
··  `` edit command in editor: {{c4::\e}}  `` <br>
··  `` {{c5::\l}}··············   # list databases  `` <br>
··  `` \set {{c6::AUTOCOMMIT}} off  `` <br>
··  `` \timing  `` <br>
··  `` {{c7::\set}} eav 'EXPLAIN ANALYZE VERBOSE'  `` <br>
····  `` :eav SELECT COUNT(*) FROM pg_tables  `` <br>
··  `` \set {{c8::HISTSIZE}} 10  `` <br>
··  `` {{c9::\!}} ls  `` <br>
··  `` export/import data: {{c10::\copy}}   `` <br>
··  `` {{c11::\connect}} some_db  `` <br>
····  `` \cd /postgresql_book/ch03  `` <br>
····  `` {{c12::\copy}} staging.factfinder_import FROM DEC_..csv CSV  `` <br>
····  `` {{c13::NULL}} AS ''  `` <br>
····  `` {{c14::DELIMITER}} '|'  `` <br>
····  `` {{c15::FROM}} somefile.txt  `` <br>

%

%

suspended

___

## pgs01: pgs: what are main data types?

··  `` {{c1::numeric}}: integer bigint decimal numeric real double precision  `` <br>
··  `` serials  `` <br>
··  `` {{c2::character}}: varchar(n), char(n), text  `` <br>
··  `` {{c3::temporals}}: timestamptz timestamp  `` <br>
··  `` enum  `` <br>
··  `` {{c4::Arrays}}: integer[] character[]  `` <br>
··  `` {{c5::Range}}: (_2,2] '[2012_04_24, infinity)'::daterange  `` <br>
··  `` {{c6::json}}: json jsonb  `` <br>

%

%

active

___

## pgs01: pgs: add uniqueness constraint

··  `` {{c1::ALTER TABLE}} logs_2011 ADD {{c2::CONSTRAINT}} uq   `` <br>
····  `` {{c3::UNIQUE}} (user_name, log_ts);  `` <br>

%

%

active

___

## pgs01: pgs: constraint all chars as lowercase

··  `` ALTER TABLE logs ADD {{c1::CONSTRAINT}} chk   `` <br>
····  `` {{c2::CHECK}} (user_name = {{c3::lower}}(user_name));  `` <br>

%

%

active

___

## pgs01: pgs: fk constraint

··  `` ALTER TABLE facts ADD CONSTRAINT fk_facts_f   `` <br>
····  `` {{c2::FOREIGN KEY}} (fact_type_id) {{c1::REFERENCES}} lu_fact_types (fact_type_id)  `` <br>

%

%

active

___

## pgs01: pgs: view examples

··  `` CREATE {{c1::view_name}} {{c1::AS}} query  `` <br>
··  `` CREATE {{c2::RECURSIVE VIEW}} view_name({{c3::columns}}) AS query  `` <br>
··  `` CREATE VIEW census.vw_facts_2011 AS  `` <br>
····  `` SELECT fact_type_id, ... FROM <table> WHERE yr = 2011;  `` <br>
··  `` WITH {{c4::CHECK OPTION}}  `` <br>
····  `` keep data in view always  `` <br>
··  `` UPDATE {{c5::<view>}} SET val = 1 WHERE val = 0;  `` <br>
··  `` DELETE FROM <view> WHERE val = 0  `` <br>

%

%

active

___

## pgs01: pgs: handy 01

··  `` {{c1::DISTINCT}} ON  `` <br>
··  `` {{c2::LIMIT}} and OFFSET  `` <br>
··  `` {{c3::CAST}}('2011_1_11' AS date)  `` <br>
····  `` '2011_1_1'::{{c4::date}}  `` <br>
··  `` INSERT INTO <table> (<cols>)  `` <br>
····  `` {{c5::VALUES}}  `` <br>
······  `` (<row1>),  `` <br>
······  `` (<row2>);  `` <br>

%

%

active

___

## pgs01: pgs: handy02

··  `` SELECT .. WHERE <col1> {{c1::ILIKE}} '%duke%'  `` <br>
··  `` DELETE FROM census.facts  `` <br>
····  `` {{c2::USING}} census.lu_fact_types As ft  `` <br>
····  `` {{c3::WHERE}} facts.fact_type_id = ft.fact_type_id AND ft_short_name = 's01'  `` <br>
··  `` UPDATE census.lu_fact_types AS f  `` <br>
····  `` {{c4::SET}} short_name = ..  `` <br>
····  `` {{c5::WHERE}} ..  `` <br>
····  `` {{c6::RETURNING}} fact_type_id, short_name  `` <br>

%

%

active

___

## pgs01: pgs: handy 03

··  `` SELECT x FROM census.lu_fact_types As x {{c1::LIMIT}} 2;  `` <br>
··  `` SELECT {{c2::array_to_json}}({{c3::array_agg}}(f)) As cat FROM (  `` <br>
····  `` SELECT MAX(fact_type_id) As max_type, category FROM census.lu_fact_types  `` <br>
····  `` GROUP BY category  `` <br>
····  `` ) As f  `` <br>
··  `` SELECT student,  `` <br>
······  `` AVG(score) {{c4::FILTER}} ({{c5::WHERE}} subject ='algebra') As algebra,   `` <br>
······  `` AVG(score) FILTER (WHERE subject ='physics') As physics  `` <br>
····  `` FROM test_scores   `` <br>
····  `` GROUP BY student;  `` <br>

%

%

active

___

## pgs01: pgs: window ex 01

··  `` SELECT tract_id, val, {{c1::AVG}}(val) {{c2::OVER}} () as val_avg  `` <br>
····  `` FROM census.facts  `` <br>
····  `` WHERE fact_type_id = 86;  `` <br>
··  `` SELECT tract_id, val, AVG(val) OVER ({{c3::PARTITION BY}} {{c4::left}}(tract_id,5)) as val_avg_county  `` <br>
····  `` FROM census.facts  `` <br>
····  `` WHERE fact_type_id = 86 ORDER BY tract_id;  `` <br>
··  `` SELECT {{c5::ROW_NUMBER}}() OVER ({{c6::ORDER BY}} tract_name) As rnum, tract_name  `` <br>

%

%

active

___

## pgs01: pgs: window ex 02

··  `` SELECT   `` <br>
····  `` ROW_NUMBER() OVER ({{c1::wt}}) As rnum,   `` <br>
····  `` substring(tract_id,1,5) As county_code,  `` <br>
····  `` tract_id,  `` <br>
····  `` {{c2::LAG}}(tract_id,2) OVER wt As tract_2_before,  `` <br>
····  `` {{c3::LEAD}}(tract_id) OVER wt As tract_after,  `` <br>
··  `` FROM census.lu_tracts  `` <br>
··  `` {{c4::WINDOW}} wt AS ({{c5::PARTITION BY}} substring(tract_id,1,5) ORDER BY tract_id)  `` <br>

%

%

active

___

## pgs01: pgs: cte ex 01

··  `` {{c1::WITH}}   `` <br>
····  `` {{c2::cte1}} AS (  `` <br>
······  `` SELECT * FROM <table1>  `` <br>
····  `` ),   `` <br>
····  `` cte2 {{c3::AS}} (  `` <br>
······  `` SELECT * FROM <table2>  `` <br>
····  `` )  `` <br>
····  `` SELECT *  `` <br>
····  `` FROM {{c4::cte1}}  `` <br>
····  `` WHERE ..  `` <br>

%

%

active

___

## pgs01: pgs: recursive cte ex 01

··  `` WITH {{c1::RECURSIVE}} subordinates AS (  `` <br>
····  `` SELECT employee_id, manager_id, full_name  `` <br>
······  `` FROM employees  `` <br>
······  `` WHERE employee_id = 2  `` <br>
····  `` {{c2::UNION}}  `` <br>
····  `` SELECT e.employee_id, e.manager_id, e.full_name  `` <br>
······  `` FROM employees e  `` <br>
······  `` {{c3::INNER JOIN}} {{c4::subordinates}} s ON s.employee_id = e.manager_id  `` <br>
····  `` )   `` <br>
····  `` SELECT * FROM {{c5::subordinates}};  `` <br>

%

%

active

___

## pgs01: pgs: define function 01

··  `` CREATE OR REPLACE FUNCTION func_name(arg1 {{c1::arg1_datatype}} {{c2::DEFAULT}} arg1_default)  `` <br>
····  `` {{c3::RETURNS}} <some type> | set of <some type> | {{c4::TABLE}} (..) AS  `` <br>

%

%

active

___

## pgs01: pgs: define window function 01

··  `` CREATE {{c1::AGGREGATE}} my_agg (input data type) ( ..)  `` <br>

%

%

active

___

## pgs01: pgs: define function 02

··  `` CREATE OR REPLACE FUNCTION write_to_log(param_user_name varchar, param_description text)  `` <br>
····  `` RETURNS integer AS  `` <br>
····  `` $$  `` <br>
····  `` INSERT INTO logs(user_name, description) VALUES({{c1::$1, $2}})  `` <br>
····  `` {{c2::RETURNING}} log_id;  `` <br>
····  `` $$  `` <br>
····  `` LANGUAGE '{{c3::sql}}' VOLATILE;  `` <br>
··  `` SELECT {{c4::write_to_log}}('alejandro', 'Woke up') As new_id;  `` <br>

%

%

active

___

## pgs01: pgs: builtin functions 01: between

··  `` a {{c1::BETWEEN}} x AND y  `` <br>
··  `` a <= x AND a <= y  `` <br>
··  `` a {{c2::NOT BETWEEN}} x AND y  `` <br>

%

%

active

___

## pgs01: pgs: builtin functions 02: IS check null

··  `` <expression> IS [NOT] {{c1::NULL}}  `` <br>

%

%

active

___

## pgs01: pgs: builtin functions 03: character funs

··  `` {{c1::char_length}}(string)  `` <br>
··  `` __ length  `` <br>
··  `` {{c2::substring}}(string, int, int)  `` <br>
··  `` __ part of string  `` <br>
··  `` 'thomas' {{c3::~}} '.*thomas.*'  `` <br>
··  `` __ regex match  `` <br>
··  `` {{c4::to_char}}(current_timestamp, 'HH12:MI:SS')  `` <br>
··  `` __ to string  `` <br>
··  `` {{c5::to_date}}('05 Dec 2000', 'DD Mon YYYY')  `` <br>
··  `` __ convert string to date  `` <br>

%

%

active

___

## pgs01: pgs: builtin functions 04: temporals

current date funs:

··  `` {{c1::current_date}}()  `` <br>
··  `` {{c2::current_time}}()  `` <br>
··  `` SELECT {{c3::now}}();  `` <br>
··  `` SELECT {{c4::CURRENT_TIMESTAMP}};  `` <br>

extract:

··  `` {{c5::date_part}}('{{c6::hour}}', timestamp '2011_02_16 20:38:40')  `` <br>
··  `` SELECT {{c7::EXTRACT}}(DAY FROM TIMESTAMP '2001_02_16 20:38:40');  `` <br>

%

%

active

___

## pgs01: pgs: builtin functions 05: case

··  `` SELECT a,  `` <br>
····  `` {{c1::CASE}} WHEN a=1 THEN 'one'  `` <br>
······  `` {{c2::WHEN}} a=2 {{c2::THEN}} 'two'  `` <br>
······  `` {{c3::ELSE}} 'other'  `` <br>
····  `` END  `` <br>
····  `` FROM test;  `` <br>
··  `` __ return first not null argument:  `` <br>
··  `` SELECT {{c4::COALESCE}}(description, short_description, '(none)') ...  `` <br>

%

%

active

___

## pgs01: pgs: builtin functions 06: arrays

··  `` {{c1::array_append}}(ARRAY[1,2], 3)   `` <br>
··  `` {{c2::array_cat}}(ARRAY[1,2,3], ARRAY[4,5])   `` <br>
··  `` array_ndims(ARRAY[[1,2,3], [4,5,6]])··  `` <br>
··  `` {{c3::array_length}}(array[1,2,3], 1)  __ 3  `` <br>
··  `` {{c4::unnest}}(ARRAY[1,2])··  `` <br>
····  `` 1  `` <br>
····  `` 2  `` <br>
····  `` (2 rows)  `` <br>
··  `` {{c5::array_agg}}(expression)   `` <br>
··  `` __ convert any set to array  `` <br>

%

%

active

___

## pgs01: pgs: builtin functions 07: aggregate

··  `` avg(expr)  `` <br>
··  `` {{c1::bool_and}}(expr)  `` <br>
··  `` __ and  `` <br>
··  `` {{c2::bool_or}}(expr)  `` <br>
··  `` __ or  `` <br>
··  `` {{c3::count}}(*)  `` <br>
··  `` __ number of elements  `` <br>
··  `` count(expr)  `` <br>
··  `` max  `` <br>
··  `` min  `` <br>
··  `` sum(expr)  `` <br>

%

%

active

___

## pgs01: pgs: builtin functions 08: window

··  `` {{c1::row_number}}()  `` <br>
··  `` __ row no  `` <br>
··  `` {{c2::rank}}()  `` <br>
··  `` __ row no as ranks  `` <br>
··  `` dense_rank()  `` <br>
··  `` percent_rank()  `` <br>
··  `` {{c3::cume_dist}}()  `` <br>
··  `` __ dist  `` <br>
··  `` ntile  `` <br>
··  `` {{c4::lag}}  `` <br>
··  `` lead  `` <br>
··  `` {{c5::first_value}}  `` <br>
··  `` last_value  `` <br>
··  `` nth_value  `` <br>
····  ``  `` <br>
%

%

active

___

## pgs01: pgs: current db setting

··  `` {{c1::current_catalog}}  `` <br>
··  `` {{c2::current_database}}()  `` <br>
··  `` {{c3::current_schema}}  `` <br>
··  `` {{c4::current_role}}  `` <br>
··  `` {{c5::current_user}}  `` <br>
··  `` SELECT {{c1::current_setting}}('datestyle');  `` <br>

%

%

active

___

## pgs01: postgrest (pgr): getting started app steps

··  `` sudo docker run __name tutorial _p 5432:5432 ... _d {{c1::postgres}}  `` <br>
··  `` CREATE {{c2::SCHEMA}} api;  `` <br>
····  `` CREATE {{c3::TABLE}} api.todos (..)  `` <br>
····  `` {{c4::INSERT}} INTO api.todos (task) VALUES ..  `` <br>
··  `` CREATE {{c5::ROLE}} web_anon NOLOGIN;  `` <br>
····  `` GRANT {{c6::usage}} ON {{c6::SCHEMA}} api TO web_anon;  `` <br>
····  `` GRANT {{c7::select}} ON api.todos TO web_anon;  `` <br>
··  `` {{c8::tutorial.conf}}  `` <br>
····  `` db_uri = "postgres://postgres:mysecretpassword@localhost/postgres"  `` <br>
····  `` db_schema = "api"  `` <br>
····  `` db_anon_role = "web_anon"  `` <br>
··  `` {{c9::postgrest}} tutorial.conf  `` <br>
··  `` {{c10::curl}} http://localhost:3000/todos  `` <br>
··  `` CREATE {{c12::ROLE}} todo_user NOLOGIN;  `` <br>
····  `` GRANT {{c13::ALL}} ON api.todos TO todo_user;  `` <br>
····  `` GRANT {{c14::USAGE, SELECT}} ON SEQUENCE api.todos_id_seq TO todo_user;  `` <br>
··  `` tutorial.conf  `` <br>
····  `` {{c15::jwt_secret}} = "<secret>"  `` <br>
··  `` {{c16::jwt.io}}:  `` <br>
····  `` { "{{c17::role}}": "todo_user" }  `` <br>
··  `` export {{c18::TOKEN}}="..."  `` <br>
··  `` curl http://localhost:3000/todos _X POST ..··  `` <br>
····  `` _H "{{c19::Authorization}}: Bearer $TOKEN"   \  `` <br>

%

%

active

___

## pgs01: pgr: API ex 01

··  `` GET /{{c1::people}}?age=gte.18&student=is.true  `` <br>
··  `` GET /people?{{c2::and=}}(grade.{{c2::gte}}.90,student.{{c3::is}}.true,{{c4::or}}(age.gte.14,age.{{c5::is}}.null))  `` <br>
··  `` ?a{{c6::=in}}."hi there","yes"  `` <br>
··  `` ?tags{{c7::=cs}}.{example, new}  `` <br>
··  `` verbs: OPTIONS, GET, {{c8::POST}}, PATCH, DELETE  `` <br>

%

%

active

___

## pgs01: pgr: API ex 02

··  `` GET /view  `` <br>
····  `` CREATE {{c1::VIEW}} fresh_stories AS  `` <br>
······  `` SELECT *  `` <br>
········  `` FROM stories  `` <br>
······   `` WHERE pinned = true  `` <br>
··········  `` OR published > now() _ interval '1 day'  `` <br>
······  `` ORDER BY pinned DESC, published DESC;  `` <br>
······  `` -->  `` <br>
······  `` GET /{{c2::fresh_stories}} HTTP/1.1  `` <br>

%

%

active

___

## pgs01: pgr: API ex 03

··  `` GET /entity?function /entity?order  `` <br>
····  `` GET /people?{{c1::select=}}*,full_name  `` <br>
····  `` CREATE FUNCTION full_name(people) RETURNS text AS $$  `` <br>
······  `` SELECT $1.fname || ' ' || $1.lname;  `` <br>
····  `` GET /people?{{c2::full_name}}=fts.Beckett  `` <br>
····  `` GET /people?{{c3::order=}}age.desc,height.asc  `` <br>

%

%

active

___

## pgs01: pgr: API ex 04

··  `` Range pagination  `` <br>
····  `` GET /people HTTP/1.1  `` <br>
······  `` Range_Unit: items  `` <br>
······  `` {{c1::Range}}: 0_19  `` <br>
····  `` GET /people?{{c2::limit=}}15&{{c3::offset=}}30  `` <br>

%

%

active

___

## pgs01: pgr: API ex 05

··  `` single item ?id=eq.1 Accept: vnd.pgrst  `` <br>
····  `` /items?{{c1::id}}={{c2::eq}}.1  `` <br>
······  `` [ {"id":1} ]  `` <br>
····  `` /items?id=eq.1  `` <br>
······  `` Accept: application/{{c3::vnd.pgrst}}.object+json  `` <br>
······  `` {{c4::{"id":1}}}  `` <br>

%

%

active

___

## pgs01: pgr: API ex 06

··  `` joins items?select=id,{{c1::subitems}}(id,field)  `` <br>
····  `` curl http://localhost:8080rest/items?id=gt.1&select=id,name,{{c2::subitems}}(id,name)  `` <br>
····  `` /films?{{c3::select}}=title,directors(id,last_name)  `` <br>
····  `` GET /films?select=*,actors(*)&actors.order=last_name,first_name  `` <br>

%

%

active

___

## pgs01: pgr: API ex 07

··  `` {{c1::POST}} /{{c2::rpc}}/function {args}  `` <br>
··  `` POST /rpc/function_name  `` <br>
··  `` CREATE FUNCTION add_them(a integer, b integer)  `` <br>
····  `` -->  `` <br>
····  `` POST /rpc/{{c3::add_them}}  `` <br>
······  `` {"a":1, {{c4::"b"}}:2}  `` <br>

%

%

active

___

## pgs01: pgr: API ex 08

··  `` request.header.XYZ  `` <br>
····  `` SELECT {{c1::current_setting}}('{{c2::request.header.origin}}', true);  `` <br>

%

%

active

___

## pgs01: pgr: API ex 09

··  `` insert = {{c1::POST}}, update = {{c2::PATCH}}, delete = DELETE  `` <br>
····  `` POST /table_name   `` <br>
······  `` {{{c3::"col1": "value"}}, "col2": "value"}  `` <br>
····  `` PATCH /people?age=lt.13  `` <br>
······  `` {{c4::{"category": "child"}}}  `` <br>
····  `` POST /people  `` <br>
······  `` Content_Type: {{c5::text/csv}}  `` <br>
······  `` name,age  `` <br>
······  `` J Doe,62   `` <br>
······  `` Jonas,10  `` <br>
····  `` POST /people  `` <br>
······  `` Content_Type: application/{{c6::json}}  `` <br>
······  `` [  `` <br>
········  `` {"name":"J Doe", "age":62},  `` <br>
········  `` ..  `` <br>
······  `` ]  `` <br>

%

%

active

___

## pgs01: pgr: authentication ex

··  `` Authentication  `` <br>
····  `` GRANT user123 TO {{c1::authenticator}};  `` <br>

%

%

active

___

## pgs01: `postgrest_starter_kit` psk TOC 01

··  `` db/src/data/{{c1::tables}}.sql  `` <br>
····  `` create table client (...)  `` <br>
··  `` db/src/data/{{c2::schema}}.sql  `` <br>
····  `` \ir tables.sql  `` <br>
··  `` db/src/api/{{c3::views_and_procedures}}.sql  `` <br>
····  `` create view clients as ...  `` <br>
··  `` db/src/{{c4::api}}/schema.sql  `` <br>
····  `` \ir views_and_procedures.sql  `` <br>
··  `` db/src/{{c5::sample_data}}/data.sql  `` <br>
····  `` COPY client (id,name,...) FROM STDIN (FREEZE ON);   `` <br>
····  `` 1  Apple  address_1_   1   ...  `` <br>
··  `` db/src/sample_data/{{c6::reset}}.sql  `` <br>
····  `` \ir data.sql  `` <br>

%

%

active

___

## pgs01: `postgrest_starter_kit` psk TOC 02

··  `` curl _H ".." http:.../clients?select=id,name  `` <br>
··  `` db/src/{{c1::authorization}}/privileges.sql  `` <br>
····  `` grant select, insert, update, delete on api.clients, api.projects, ...  to webuser;  `` <br>
··  `` curl _H ".." http:.../clients?select=id,name  `` <br>
··  `` db/src/api/{{c2::views_and_procedures}}.sql  `` <br>
····  `` alter view clients owner to api;  `` <br>
··  `` db/src/authorization/{{c3::privileges}}.sql  `` <br>
····  `` create policy access_own_rows on client to api  `` <br>
··  `` curl _H "Authorization: Bearer ${{c4::JWT_TOKEN}}" http://localhost:8080/rest/clients?select=id,name  `` <br>

%

%

active

___

## pgs01: psk file structure 01

··  `` .env  `` <br>
··  `` docker_compose.yml  `` <br>
··  `` {{c1::openresty}}: reverse proxy and lua code  `` <br>
··  `` {{c2::postgrest}}  `` <br>
··  `` {{c3::db}}   `` <br>

%

%

active

___

## pgs01: psk file structure 02

··  `` openresty: reverse proxy and lua code  `` <br>
····  `` {{c1::lualib}}/user_code: application lua code  `` <br>
····  `` {{c2::nginx}}/  `` <br>
······  `` conf  `` <br>
······  `` html  `` <br>
····  `` {{c3::tests}}  `` <br>
······  `` rest: rest interface tests  `` <br>
······  `` common.js: helper functions  `` <br>
····  `` Dockerfile  `` <br>
····  `` entrypointsh: custom entrypoint  `` <br>
··  `` postgrest  `` <br>
····  `` {{c4::tests}}: bash based integration tests  `` <br>
······  `` {{c5::all.sh}} assert.sh  `` <br>

%

%

active

___

## pgs01: psk file structure 03

··  `` db/  `` <br>
····  `` {{c1::src}}: schema definition  `` <br>
······  `` {{c2::data}}: definition of source tables  `` <br>
········  `` schema.sql tables.sql  `` <br>
······  `` {{c3::api}}: api entities  `` <br>
········  `` schema.sql views_and_procedures.sql  `` <br>
······  `` {{c4::libs}}:  `` <br>
········  `` auth pgjwt rabbitmq request settings util  `` <br>
······  `` {{c5::authorization}}: roles and privileges  `` <br>
········  `` privileges.sql roles.sql  `` <br>
······  `` {{c6::sample_data}}   `` <br>
········  `` data.sql reset.sql  `` <br>
······  `` {{c7::init.sql}}: entry point  `` <br>
····  `` {{c8::tests}}: pgTap tests  `` <br>
······  `` rls.sql structure.sql  `` <br>

%

%

active

___

## pgs01: Summary of Steps in PSK

1. basic REST request

··  `` $ curl http://localhost:8080/rest/todos?select=id  `` <br>
··  `` [{"id":1},{"id":3},{"id":6}]  `` <br>

2. {{c1::authorized}} REST request

··  `` $ export JWT_TOKEN=...  `` <br>
··  `` $ curl _H "Authorization: Bearer $JWT_TOKEN" http://localhost:8080/rest/todos?select=id,todo  `` <br>
··  `` [{"id":1,"todo":"item_1"},{"id":3,"todo":"item_3"},{"id":6,"todo":"item_6"}]  `` <br>

3. Edit `db/src/sample_data/{{c2::data.sql}}`:

··  `` $ curl _H "Authorization: Bearer $JWT_TOKEN" http://localhost:8080/rest/todos?select=id,todo  `` <br>
··  `` [{"id":1,"todo":"item_1_updated"},{"id":3,"todo":"item_3"},{"id":6,"todo":"item_6"}]  `` <br>

4. Create new {{c7::table}}: data/table.sql imported from data/schema.sql

5. Create new {{c3::view as API}}: `api/views_and_procedures.sql` imported from api/schema.sql

6. Create {{c8::sample data}}: `sample_data/data.sql` imported from reset.sql

··  `` delete previous data in reset.sql:  `` <br>

··  `` {{c4::truncate}} data.client restart identity cascade;  `` <br>

7. Grant {{c5::privilege}} for the new view: authorization/privileges.sql imported from init.sql

8. Make request to new API:

··  `` $ curl _H "Authorization: Bearer $JWT_TOKEN" http://localhost:8080/rest/{{c6::new_view}}?select=id,name  `` <br>

%

%

suspended

___

## pgs01: psk: install and setup

··  `` $ {{c1::git clone}} __single_branch https://github.com/subzerocloud/postgrest_starter_kit khumbuicefall  `` <br>
··  `` $ cd khumbuicefall  `` <br>

Edit `{{c2::.env}}`

··  `` COMPOSE_PROJECT_NAME=khumbuicefall  `` <br>

Run the server embedded in docker container:

··  `` $ {{c3::docker_compose up _d}} # wait for 5_10s before running the next command  `` <br>

Install and run `[subzero_cli]` to see what is going inside (optional):

··  `` $ docker pull subzerocloud/subzero_cli_tools  `` <br>
··  `` $ npm install _g subzero_cli  `` <br>
··  `` $ {{c4::subzero}} dashboard  `` <br>

%

%

suspended

___

## pgs01: psk: basic REST calls

··  `` $ curl http://localhost:8080/rest/{{c1::todos?select=id}}  `` <br>
··  `` [{"id":1},{"id":3},{"id":6}]  `` <br>

Make an authorized request

··  `` $ export {{c2::JWT_TOKEN}}=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk  `` <br>
··  `` $ curl _H "Authorization: Bearer $JWT_TOKEN" http://localhost:8080/rest/todos?select=id,todo  `` <br>
··  `` [{"id":1,"todo":"item_1"},{"id":3,"todo":"item_3"},{"id":6,"todo":"item_6"}]  `` <br>

%

%

active

___

## pgs01: Check Generated SQL Using subzero

··  `` $ curl http://localhost:8080/rest/todos?select=id  `` <br>

The following SQL code is generated automatically by `postgrest`:

··  `` {{c1::WITH}} pg_source AS  `` <br>
····  `` (SELECT  "api"."todos"."{{c2::id}}" FROM  "api"."{{c3::todos}}")  `` <br>
····  `` SELECT  `` <br>
······  `` null AS total_result_set, pg_catalog.count(_postgrest_t) AS page_total, array[]::text[] AS header, coalesce(array_to_json(array_agg(row_to_json({{c4::_postgrest_t}}))), '[]')::character varying AS body  `` <br>
······  `` FROM ( SELECT * FROM pg_source) _postgrest_t  `` <br>

%

%

active

___

## pgs01: psk: actual data

··  `` {{c1::coalesce}}(  `` <br>
····  `` {{c2::array_to_json}}(  `` <br>
······  `` {{c3::array_agg}}(  `` <br>
········  `` {{c4::row_to_json}}(_postgrest_t)))  `` <br>

%

%

active

___

## pgs01: psk: editing db/src/ *.sql files

`./db/src/data/todo.sql` changed

··  `` -->  `` <br>

··  `` Starting code reload ________________________  `` <br>
··  `` LOG:  statement: SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'app';  `` <br>
··  `` FATAL:  terminating connection due to administrator command  `` <br>
··  `` LOG:  statement: DROP DATABASE if exists app;  `` <br>

··  `` LOG:  statement: {{c1::CREATE DATABASE}} app;  `` <br>

··  `` Ready _______________________________________  `` <br>
··  `` LOG:  statement: {{c2::set client_min_messages}} to warning;  `` <br>

%

%

active

___

## pgs01: psk: order of call for db/src/*.sql files

··  `` db/src/{{c1::init}}.sql  `` <br>
····  `` db/src/data/{{c2::schema}}.sql  `` <br>
······  `` db/src/data/{{c3::todo}}.sql  `` <br>

%

%

active

___

## pgs01: File Structure of `.sql` Files

`init.sql` file contains several `include` i.e. `\ir` statements such as:

··  `` \ir {{c1::data}}/schema.sql  `` <br>
··  `` \ir {{c2::api}}/schema.sql  `` <br>
··  `` \ir {{c3::authorization}}/roles.sql  `` <br>
··  `` \ir authorization/{{c4::privileges}}.sql  `` <br>
··  `` \ir {{c5::sample_data}}/data.sql  `` <br>

%

%

active

___

## pgs01: psk: reset.sql

Added `data.client` table sample data into `sample_data/data.sql`

Now edit `db/src/{{c1::sample_data}}/reset.sql` and add this line before `COMMIT`:

··  `` {{c1::truncate}} data.client {{c2::restart}} identity cascade;  `` <br>

%

%

active

___

## pgs01: psk: added new view `api.clients`. GRANT?

Edit `db/src/authorization/privileges.sql` and add `GRANT` privilege statements:

··  `` {{c1::grant}} select, insert, update, delete  `` <br>
··  `` on {{c2::api.clients}}  `` <br>
··  `` to {{c3::webuser}};  `` <br>

%

%

active

___

## pgs01: psk: Summary of Steps 

1. basic REST request

2. authorized REST request

3. Edit `db/src/{{c1::sample_data/data}}.sql`:

······  `` [{"id":1,"todo":"item_1_updated"},]  `` <br>

4. Create new table: data/{{c2::table}}.sql imported from data/schema.sql

5. Create new view as API: `api/{{c3::views_and_procedures}}.sql` imported from api/schema.sql

6. Create sample data: `{{c4::sample_data}}/data.sql` imported from reset.sql

······  `` {{c5::truncate}} data.client restart identity cascade;  `` <br>

7. Grant privilege for the new view: authorization/{{c6::privileges}}.sql imported from init.sql

8. Make request to new API:


%

%


---


