## pgs01: where is sql documentation notes?

`~/gdrive/mynotes/content/articles/{{c1::articles_db}}.md`

%

%

suspended

---

## pgs01: Run posgtersql database server in docker

docker start `{{c1::postgreststarterkit_db_1}}` 

%

%

suspended

---

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

---


## pgs01: sql privilege: grantee can grant onwards

··  `` WITH {{c1::GRANT OPTION}}  `` <br>
··  `` GRANT ALL ON ALL TABLES IN SCHEMA public TO mydb_admin WITH {{c1::GRANT OPTION}}  `` <br>

%

%

suspended

---

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

---

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

---

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

---

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

suspended

---

## pgs01: psk: order of call for db/src/*.sql files

··  `` db/src/{{c1::init}}.sql  `` <br>
····  `` db/src/data/{{c2::schema}}.sql  `` <br>
······  `` db/src/data/{{c3::todo}}.sql  `` <br>

%

%

active

---

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

---

## pgs01: psk: reset.sql

Added `data.client` table sample data into `sample_data/data.sql`

Now edit `db/src/{{c1::sample_data}}/reset.sql` and add this line before `COMMIT`:

··  `` {{c1::truncate}} data.client {{c2::restart}} identity cascade;  `` <br>

%

%

active

---

## pgs01: psk: added new view `api.clients`. GRANT?

Edit `db/src/authorization/privileges.sql` and add `GRANT` privilege statements:

··  `` {{c1::grant}} select, insert, update, delete  `` <br>
··  `` on {{c2::api.clients}}  `` <br>
··  `` to {{c3::webuser}};  `` <br>

%

%

active

---

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

---

## pgs01: psk file structure 01

··  `` .env  `` <br>
··  `` docker_compose.yml  `` <br>
··  `` {{c1::openresty}}: reverse proxy and lua code  `` <br>
··  `` {{c2::postgrest}}  `` <br>
··  `` {{c3::db}}   `` <br>

%

%

active

---

## pgs01: pgs: define window function 01

··  `` CREATE {{c1::AGGREGATE}} my_agg (input data type) ( ..)  `` <br>

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

