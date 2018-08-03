
## pgr01: summary of steps 01

1. basic REST request

····   $ curl http://localhost:8080/rest/todos?select=id `` <br>

2. authorized REST request

····   $ export JWT_TOKEN=... `` <br>
····   $ curl -H "{{c1::Authorization}}: Bearer $JWT_TOKEN" http:... `` <br>

3. Edit `sample_data/my_data.sql`:

····   {{c2::COPY}} client (id,name,address) `` <br>
····   1 Apple address_1_·· `` <br>

4. Create `data/my_schema.sql`

····   {{c3::create table}} client ( ... `` <br>

5. Edit `data/schema.sql`: `{{c4::\ir}} `my_schema.sql`` 

%

%

clozeq

---

## pgr01: summary of steps 02

6. Create `api/my_views.sql` 

····   create or replace {{c1::view}} clients as... `` <br>

7. Edit `reset.sql`

····   {{c2::truncate}} data.client restart identity cascade; `` <br>

8. Edit `authorization/privileges.sql` 

····   {{c3::grant}} select, insert, update, delete ... `` <br>

9. Make request to new API:

····   $ curl -H "Authorization: Bearer $JWT_TOKEN" http... `` <br>

%

%

clozeq

---

## pgr02: import dependencies 03

····   {{c1::sample_data/reset.sql}}··`` <br>
······   {{c2::truncate}} plan ... `` <br>
······   {{c3::sample_data/data.sql}}··`` <br>
········   COPY plan (id,name,...) `` <br>
········   1,plan_1,... `` <br>
····   {{c4::init.sql}} `` <br>
······   {{c5::data}}/schema.sql `` <br>
········   ddl.sql `` <br>
··········   {{c6::CREATE TABLE}} enum_var (...) `` <br>
······   {{c7::api}}/schema.sql `` <br>
········   {{c8::views.sql}} `` <br>
··········   CREATE VIEW clients as ... `` <br>
······   {{c9::authorization}}/privileges.sql `` <br>
········   grant select ... on api.clients `` <br>
······   sample_data/data.sql `` <br>

%

%

clozeq

---

## pgr02: import dependencies 04

····   sample_data/{{c1::reset.sql}}··`` <br>
······   truncate plan ... `` <br>
······   sample_data/data.sql··`` <br>
····   init.sql `` <br>
······   data/{{c2::schema.sql}} `` <br>
········   {{c3::ddl.sql}} `` <br>
······   api/schema.sql `` <br>
········   {{c4::views.sql}} `` <br>
······   authorization/{{c5::privileges.sql}} `` <br>
······   sample_data/data.sql `` <br>


%

%

clozeq

---

## pgs01: psk: authorized request

Nologin request

··  `` $ curl http://localhost:8080/rest/{{c1::todos?select=id}}  `` <br>
··  `` [{"id":1},{"id":3},{"id":6}]  `` <br>

Make an authorized request

··  `` $ export {{c2::JWT_TOKEN}}=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk  `` <br>
··  `` $ curl _H "Authorization: Bearer $JWT_TOKEN" http://localhost:8080/rest/todos?select=id,todo  `` <br>
··  `` [{"id":1,"todo":"item_1"},{"id":3,"todo":"item_3"},{"id":6,"todo":"item_6"}]  `` <br>

%

%

active

---

## pgs01: psk sql file structure

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

---

## pgs01: psk: generated sql code 03

··  `` {{c1::coalesce}}(  `` <br>
····  `` {{c2::array_to_json}}(  `` <br>
······  `` {{c3::array_agg}}(  `` <br>
········  `` {{c4::row_to_json}}(_postgrest_t)))  `` <br>

%

%

active

---

## pgs01: psk non-sql file structure 02

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

---

## pgs01: pgr: API ex 01

··  `` GET /{{c1::people}}?age=gte.18&student=is.true  `` <br>
··  `` GET /people?{{c2::and=}}(grade.{{c2::gte}}.90,student.{{c3::is}}.true,{{c4::or}}(age.gte.14,age.{{c5::is}}.null))  `` <br>
··  `` ?a{{c6::=in}}."hi there","yes"  `` <br>
··  `` ?tags{{c7::=cs}}.{example, new}  `` <br>
··  `` verbs: OPTIONS, GET, {{c8::POST}}, PATCH, DELETE  `` <br>

%

%

active

---

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

---

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

---

## pgs01: pgr: API ex 04

··  `` Range pagination  `` <br>
····  `` GET /people HTTP/1.1  `` <br>
······  `` Range_Unit: items  `` <br>
······  `` {{c1::Range}}: 0_19  `` <br>
····  `` GET /people?{{c2::limit=}}15&{{c3::offset=}}30  `` <br>

%

%

active

---

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

---

## pgs01: pgr: API ex 06

··  `` joins items?select=id,{{c1::subitems}}(id,field)  `` <br>
····  `` curl http://localhost:8080rest/items?id=gt.1&select=id,name,{{c2::subitems}}(id,name)  `` <br>
····  `` /films?{{c3::select}}=title,directors(id,last_name)  `` <br>
····  `` GET /films?select=*,actors(*)&actors.order=last_name,first_name  `` <br>

%

%

active

---

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

---

## pgs01: pgr: API ex 08

··  `` request.header.XYZ  `` <br>
····  `` SELECT {{c1::current_setting}}('{{c2::request.header.origin}}', true);  `` <br>

%

%

active

---

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

---

## pgs01: Run pgcli with existing env variables

··  `` pgcli {{c1::app}}  `` <br>
··  `` set {{c2::search_path}} = {{c3::data}}, public;  `` <br>

%

%

active

---

## pgs01: Env variables for postgresql database clients

··  `` export {{c1::PGHOST}}=localhost  `` <br>
··  `` export {{c2::PGPORT}}=5432  `` <br>
··  `` export {{c3::PGUSER}}=superuser  `` <br>
··  `` export {{c4::PGDATABASE}}=app  `` <br>

%

%

active

---

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

---

## pgs01: postgresql connection string example?

··  `` {{c1::postgresql}}://{{c2::superuser}}:{{c3::superuserpass}}@localhost/{{c4::app}}  `` <br>

%

%

active

---

## pgs01: how to create new database from terminal

··  `` psql -c '{{c1::CREATE DATABASE}} library OWNER = {{c2::superuser}}'  `` <br>

%

%

active

---

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

---

## pgs01: sql grant

··  `` GRANT {{c1::some_privilege}} TO {{c2::some_role}}  `` <br>
··  `` CREATE ROLE todo_user NOLOGIN;  `` <br>
··  `` GRANT {{c3::todo_user}} TO {{c3::group1}};  `` <br>

%

%

active

---

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

---

## pgs01: backup and restore

··  `` {{c1::pg_dump}}: selective backup  `` <br>
··  `` {{c2::pg_dumpall}}: all  `` <br>
··  `` {{c3::CREATE DATABASE}} mydb  `` <br>
····  `` {{c4::pg_restore}} __dbname=mydb __jobs=4 __verbose mydb.backup  `` <br>
····  `` __section=pre_data  `` <br>

%

%

active

---

## pgs01: password stored in

··  `` ~/{{c1::.pgpass}}  `` <br>

%

%

active

---

## pgs01: the path of disk space to store db in pgs?

··  `` {{c1::TABLESPACE}}  `` <br>
··  `` CREATE {{c1::TABLESPACE}} secondary LOCATION 'path'  `` <br>
··  `` pg_default  `` <br>
··  `` pg_global  `` <br>

%

%

active

---

## pgs01: psql arguments

··  `` psql {{c1::-d}} app {{c2::-h}} localhost {{c3::-p}} 5432 {{c4::-U}} superuser  `` <br>

%

%

active

---

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

---

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

---

## pgs01: pgs: add uniqueness constraint

··  `` {{c1::ALTER TABLE}} logs_2011 ADD {{c2::CONSTRAINT}} uq   `` <br>
····  `` {{c3::UNIQUE}} (user_name, log_ts);  `` <br>

%

%

active

---

## pgs01: pgs: constraint all chars as lowercase

··  `` ALTER TABLE logs ADD {{c1::CONSTRAINT}} chk   `` <br>
····  `` {{c2::CHECK}} (user_name = {{c3::lower}}(user_name));  `` <br>

%

%

active

---

## pgs01: pgs: fk constraint

··  `` ALTER TABLE facts ADD CONSTRAINT fk_facts_f   `` <br>
····  `` {{c2::FOREIGN KEY}} (fact_type_id) {{c1::REFERENCES}} lu_fact_types (fact_type_id)  `` <br>

%

%

active

---

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

---

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

---

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

---

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

---

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

---

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

---

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

---

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

---

## pgs01: pgs: define function 01

··  `` CREATE OR REPLACE FUNCTION func_name(arg1 {{c1::arg1_datatype}} {{c2::DEFAULT}} arg1_default)  `` <br>
····  `` {{c3::RETURNS}} <some type> | set of <some type> | {{c4::TABLE}} (..) AS  `` <br>

%

%

active

---

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

---

## pgs01: pgs: builtin functions 01: between

··  `` a {{c1::BETWEEN}} x AND y  `` <br>
··  `` a <= x AND a <= y  `` <br>
··  `` a {{c2::NOT BETWEEN}} x AND y  `` <br>

%

%

active

---

## pgs01: pgs: builtin functions 02: IS check null

··  `` <expression> IS [NOT] {{c1::NULL}}  `` <br>

%

%

active

---

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

---

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

---

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

---

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

---

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

---

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

---

## pgs01: aggregate functions 01

    string_agg(expr, delimiter)
      description: input values concatenated into an {{c1::string}}
    array_agg(expression) 
      description: input values concatenated into an {{c2::array}}
    json_agg(expression)  
      description: aggregates values as {{c3::json array}}
    json_object_agg(name, value)  
      description: aggregates name/value pairs as json object

%

%

clozeq

---

## pgs01: aggregate functions 02

		select movie, STRING_AGG(actor, ', ') as actor_list from tbl group  by 1;
			--> ali, veli, hasan
		select ARRAY_AGG('{a,b,c}'::text[],'{d,e,f}'::text[]); 
			--> {{a,d},{b,e},{c,f}}
		select company_id, ARRAY_AGG(employee order by company_id desc)::text from tbl group  by 1;
			--> 1 | {ali, veli} 
