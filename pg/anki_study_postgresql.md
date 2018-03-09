
## Run posgtersql database server from docker

docker start {{c1::postgreststarterkit—db—1}} <br>

%

%

clozeq

---

## Run pgcli with existing env variables

··  pgcli {{c1::app}} <br>
··  set {{c2::search—path}} = {{c3::data}}, public; <br>

%

%

clozeq

---

## Env variables for postgresql database clients

··  export {{c1::PGHOST}}=localhost <br>
··  export {{c2::PGPORT}}=5432 <br>
··  export {{c3::PGUSER}}=superuser <br>
··  export {{c4::PGDATABASE}}=app <br>

%

%

clozeq

---

## where is sql documentation notes?

~/Dropbox/mynotes/content/articles/{{c1::articles—db}}.md

%

%

clozeq

---

## what are the repl tools for sql?

··  {{c1::franchise}} notebook <br>
··  {{c2::psql}} <br>
··  {{c3::jupyter}}: %load—ext sql <br>
··  {{c4::pgcli}} <br>
··  {{c5::DataGrip}} <br>
··  {{c6::rmarkdown}}: sql engine <br>

%

%

clozeq

---

## postgresql connection string example?

··  {{c1::postgresql}}://{{c2::superuser}}:{{c3::superuserpass}}@localhost/{{c4::app}} <br>

%

%

clozeq

---

## how to create new database from terminal

··  psql -c '{{c1::CREATE DATABASE}} library OWNER = {{c2::superuser}}' <br>

%

%

clozeq

---

## how to connect to pgs db from R?

··  con &lt;- DBI::{{c1::dbConnect}}({{c2::RPostgreSQL}}::PostgreSQL() <br>
····  , user = Sys.{{c3::getenv}}("SUPER—USER") <br>
····  , password = Sys.getenv("SUPER—USER—PASSWORD") <br>
····  , {{c4::dbname}} = "app" <br>
····  , host = "localhost" <br>
····  , port = "5432" <br>
··  ) <br>

%

%

clozeq

---

## study—postgresql.md TOC 01

··  {{c1::Run}}: how to run db <br>
··  {{c2::Database Administration}}: roles, information schema, GRANT, backup ... <br>
····  {{c11::Roles}}: GRANT group1 TO role1, REVOKE, CREATE ROLE, LOGIN INHERIT <br>
····  {{c13::Row Level Security (RLS)}}: ALTER TABLE accounts ENABLE ROW LEVEL SECURITY, WITH CHECK <br>
····  {{c16::ddl}}: db schema  <br>
······  {{c6::Views}}: view, recursive view, update &lt;view&gt;, using triggers to update &lt;view&gt; <br>
······  {{c5::Constraints}}: ALTER TABLE ... ADD CONSTRAINT ... <br>
······  {{c4::Data Types}}: String, regex, Arrays <br>
··  {{c3::psql}}: terminal client commands \d \dt <br>
··  {{c7::Window Functions}}: AVG(val) OVER (), PARTITION BY, ROW—NUMBER(), ORDER BY <br>
··  {{c8::Common Table Expressions (CTE)}}: WITH &lt;cte1&gt; AS (...), WITH RECURSIVE <br>
··  {{c9::Functions}}: CREATE FUNCTION ...(arg1 numeric) <br>
··  {{c10::Builtin Functions}}: BETWEEN, CASE, substring, to—date <br>
··  {{c12::JOIN}}: LEFT JOIN .. ON, USING, EXISTS,  <br>
··  {{c14::postgrest}}: REST services from db schema <br>
··  {{c15::postgrest—starter—kit (psk)}}: template for postgrest apps <br>

%

%

clozeq

---

## sql grant

··  GRANT {{c1::some—privilege}} TO {{c2::some—role}} <br>
··  CREATE ROLE todo—user NOLOGIN; <br>
··  GRANT {{c3::todo—user}} TO {{c3::group1}}; <br>

%

%

clozeq

---

## sql privilege list

··  ALL {{c1::ON ALL}} <br>
··  ON {{c2::ALL TABLES}}/SEQUENCES <br>
····  {{c3::SELECT}} <br>
····  {{c4::REFERENCES}} <br>
····  {{c5::TRIGGER}} <br>
····  {{c6::UPDATE}} <br>
····  {{c7::DELETE}} <br>
··  {{c8::USAGE}} ON SCHEMA <br>
··  ON ALL TABLES IN {{c9::SCHEMA}} &lt;my—schema&gt; <br>
··  ON {{c10::&lt;schema&gt;}}.&lt;table&gt; <br>

%

%

clozeq

---

## sql privilege: grantee can grant onwards

··  WITH {{c1::GRANT OPTION}} <br>
··  GRANT ALL ON ALL TABLES IN SCHEMA public TO mydb—admin WITH {{c1::GRANT OPTION}} <br>

%

%

clozeq

---

## pgs backup and restore

··  {{c1::pg—dump}}: selective backup <br>
··  {{c2::pg—dumpall}}: all <br>
··  {{c3::CREATE DATABASE}} mydb <br>
····  {{c4::pg—restore}} --dbname=mydb --jobs=4 --verbose mydb.backup <br>
····  --section=pre-data <br>

%

%

clozeq

---

## pgs password stored in

··  ~/{{c1::.pgpass}} <br>

%

%

clozeq

---

## the path of disk space to store db in pgs?

··  {{c1::TABLESPACE}} <br>
··  CREATE {{c1::TABLESPACE}} secondary LOCATION 'path' <br>
··  pg—default <br>
··  pg—global <br>

%

%

clozeq

---

## psql arguments

··  psql {{c1::-d}} app {{c2::-h}} localhost {{c3::-p}} 5432 {{c4::-U}} superuser <br>

%

%

clozeq

---

## psql commands 01

··  {{c1::\c}} &lt;dbname&gt; &lt;username&gt; <br>
··  list tables: {{c2::\dt}} <br>
··  more: {{c3::\dt+}} <br>
··  describe a table: {{c4::\d}} &lt;table—name&gt; <br>
··  list schemas: {{c5::\dn}} <br>
··  list functions: {{c6::\df}} <br>
··  list views: {{c7::\dv}} <br>
··  list users and roles: {{c8::\du}} <br>

%

%

clozeq

---

## psql commands 02

··  save command history: {{c1::\s}} &lt;filename&gt; <br>
··  exec commands from file: {{c2::\i}} &lt;filename&gt; <br>
··  help on statement: {{c3::\h}} &lt;ALTER TABLE&gt; <br>
··  edit command in editor: {{c4::\e}} <br>
··  {{c5::\l}}··············   # list databases <br>
··  \set {{c6::AUTOCOMMIT}} off <br>
··  \timing <br>
··  {{c7::\set}} eav 'EXPLAIN ANALYZE VERBOSE' <br>
····  :eav SELECT COUNT(*) FROM pg—tables <br>
··  \set {{c8::HISTSIZE}} 10 <br>
··  {{c9::\!}} ls <br>
··  export/import data: {{c10::\copy}}  <br>
··  {{c11::\connect}} some—db <br>
····  \cd /postgresql—book/ch03 <br>
····  {{c12::\copy}} staging.factfinder—import FROM DEC—..csv CSV <br>
····  {{c13::NULL}} AS '' <br>
····  {{c14::DELIMITER}} '|' <br>
····  {{c15::FROM}} somefile.txt <br>

%

%

clozeq

---

## pgs: what are main data types?

··  {{c1::numeric}}: integer bigint decimal numeric real double precision <br>
··  serials <br>
··  {{c2::character}}: varchar(n), char(n), text <br>
··  {{c3::temporals}}: timestamptz timestamp <br>
··  enum <br>
··  {{c4::Arrays}}: integer[] character[] <br>
··  {{c5::Range}}: (-2,2] '[2012-04-24, infinity)'::daterange <br>
··  {{c6::json}}: json jsonb <br>

%

%

clozeq

---

## pgs: add uniqueness constraint

··  {{c1::ALTER TABLE}} logs—2011 ADD {{c2::CONSTRAINT}} uq  <br>
····  {{c3::UNIQUE}} (user—name, log—ts); <br>

%

%

clozeq

---

## pgs: constraint all chars as lowercase

··  ALTER TABLE logs ADD {{c1::CONSTRAINT}} chk  <br>
····  {{c2::CHECK}} (user—name = {{c3::lower}}(user—name)); <br>

%

%

clozeq

---

## pgs: fk constraint

··  ALTER TABLE facts ADD CONSTRAINT fk—facts—f  <br>
····  {{c2::FOREIGN KEY}} (fact—type—id) {{c1::REFERENCES}} lu—fact—types (fact—type—id) <br>

%

%

clozeq

---

## pgs: view examples

··  CREATE {{c1::view—name}} {{c1::AS}} query <br>
··  CREATE {{c2::RECURSIVE VIEW}} view—name({{c3::columns}}) AS query <br>
··  CREATE VIEW census.vw—facts—2011 AS <br>
····  SELECT fact—type—id, ... FROM &lt;table&gt; WHERE yr = 2011; <br>
··  WITH {{c4::CHECK OPTION}} <br>
····  keep data in view always <br>
··  UPDATE {{c5::&lt;view&gt;}} SET val = 1 WHERE val = 0; <br>
··  DELETE FROM &lt;view&gt; WHERE val = 0 <br>

%

%

clozeq

---

## pgs: handy 01

··  {{c1::DISTINCT}} ON <br>
··  {{c2::LIMIT}} and OFFSET <br>
··  {{c3::CAST}}('2011-1-11' AS date) <br>
····  '2011-1-1'::{{c4::date}} <br>
··  INSERT INTO &lt;table&gt; (&lt;cols&gt;) <br>
····  {{c5::VALUES}} <br>
······  (&lt;row1&gt;), <br>
······  (&lt;row2&gt;); <br>

%

%

clozeq

---

## pgs: handy02

··  SELECT .. WHERE &lt;col1&gt; {{c1::ILIKE}} '%duke%' <br>
··  DELETE FROM census.facts <br>
····  {{c2::USING}} census.lu—fact—types As ft <br>
····  {{c3::WHERE}} facts.fact—type—id = ft.fact—type—id AND ft—short—name = 's01' <br>
··  UPDATE census.lu—fact—types AS f <br>
····  {{c4::SET}} short—name = .. <br>
····  {{c5::WHERE}} .. <br>
····  {{c6::RETURNING}} fact—type—id, short—name <br>

%

%

clozeq

---

## pgs: handy 03

··  SELECT x FROM census.lu—fact—types As x {{c1::LIMIT}} 2; <br>
··  SELECT {{c2::array—to—json}}({{c3::array—agg}}(f)) As cat FROM ( <br>
····  SELECT MAX(fact—type—id) As max—type, category FROM census.lu—fact—types <br>
····  GROUP BY category <br>
····  ) As f <br>
··  SELECT student, <br>
······  AVG(score) {{c4::FILTER}} ({{c5::WHERE}} subject ='algebra') As algebra,  <br>
······  AVG(score) FILTER (WHERE subject ='physics') As physics <br>
····  FROM test—scores  <br>
····  GROUP BY student; <br>

%

%

clozeq

---

## pgs: window ex 01

··  SELECT tract—id, val, {{c1::AVG}}(val) {{c2::OVER}} () as val—avg <br>
····  FROM census.facts <br>
····  WHERE fact—type—id = 86; <br>
··  SELECT tract—id, val, AVG(val) OVER ({{c3::PARTITION BY}} {{c4::left}}(tract—id,5)) as val—avg—county <br>
····  FROM census.facts <br>
····  WHERE fact—type—id = 86 ORDER BY tract—id; <br>
··  SELECT {{c5::ROW—NUMBER}}() OVER ({{c6::ORDER BY}} tract—name) As rnum, tract—name <br>

%

%

clozeq

---

## pgs: window ex 02

··  SELECT  <br>
····  ROW—NUMBER() OVER ({{c1::wt}}) As rnum,  <br>
····  substring(tract—id,1,5) As county—code, <br>
····  tract—id, <br>
····  {{c2::LAG}}(tract—id,2) OVER wt As tract—2—before, <br>
····  {{c3::LEAD}}(tract—id) OVER wt As tract—after, <br>
··  FROM census.lu—tracts <br>
··  {{c4::WINDOW}} wt AS ({{c5::PARTITION BY}} substring(tract—id,1,5) ORDER BY tract—id) <br>

%

%

clozeq

---

## pgs: cte ex 01

··  {{c1::WITH}}  <br>
····  {{c2::cte1}} AS ( <br>
······  SELECT * FROM &lt;table1&gt; <br>
····  ),  <br>
····  cte2 {{c3::AS}} ( <br>
······  SELECT * FROM &lt;table2&gt; <br>
····  ) <br>
····  SELECT * <br>
····  FROM {{c4::cte1}} <br>
····  WHERE .. <br>

%

%

clozeq

---

## pgs: recursive cte ex 01

··  WITH {{c1::RECURSIVE}} subordinates AS ( <br>
····  SELECT employee—id, manager—id, full—name <br>
······  FROM employees <br>
······  WHERE employee—id = 2 <br>
····  {{c2::UNION}} <br>
····  SELECT e.employee—id, e.manager—id, e.full—name <br>
······  FROM employees e <br>
······  {{c3::INNER JOIN}} {{c4::subordinates}} s ON s.employee—id = e.manager—id <br>
····  )  <br>
····  SELECT * FROM {{c5::subordinates}}; <br>

%

%

clozeq

---

## pgs: define function 01

··  CREATE OR REPLACE FUNCTION func—name(arg1 {{c1::arg1—datatype}} {{c2::DEFAULT}} arg1—default) <br>
····  {{c3::RETURNS}} &lt;some type&gt; | set of &lt;some type&gt; | {{c4::TABLE}} (..) AS <br>

%

%

clozeq

---

## pgs: define window function 01

··  CREATE {{c1::AGGREGATE}} my—agg (input data type) ( ..) <br>

%

%

clozeq

---

## pgs: define function 02

··  CREATE OR REPLACE FUNCTION write—to—log(param—user—name varchar, param—description text) <br>
····  RETURNS integer AS <br>
····  $$ <br>
····  INSERT INTO logs(user—name, description) VALUES({{c1::$1, $2}}) <br>
····  {{c2::RETURNING}} log—id; <br>
····  $$ <br>
····  LANGUAGE '{{c3::sql}}' VOLATILE; <br>
··  SELECT {{c4::write—to—log}}('alejandro', 'Woke up') As new—id; <br>

%

%

clozeq

---

## pgs: builtin functions 01: between

··  a {{c1::BETWEEN}} x AND y <br>
··  a &lt;= x AND a &lt;= y <br>
··  a {{c2::NOT BETWEEN}} x AND y <br>

%

%

clozeq

---

## pgs: builtin functions 02: IS check null

··  &lt;expression&gt; IS [NOT] {{c1::NULL}} <br>

%

%

clozeq

---

## pgs: builtin functions 03: character funs

··  {{c1::char—length}}(string) <br>
··  -- length <br>
··  {{c2::substring}}(string, int, int) <br>
··  -- part of string <br>
··  'thomas' {{c3::~}} '.*thomas.*' <br>
··  -- regex match <br>
··  {{c4::to—char}}(current—timestamp, 'HH12:MI:SS') <br>
··  -- to string <br>
··  {{c5::to—date}}('05 Dec 2000', 'DD Mon YYYY') <br>
··  -- convert string to date <br>

%

%

clozeq

---

## pgs: builtin functions 04: temporals

current date funs:

··  {{c1::current—date}}() <br>
··  {{c2::current—time}}() <br>
··  SELECT {{c3::now}}(); <br>
··  SELECT {{c4::CURRENT—TIMESTAMP}}; <br>

extract:

··  {{c5::date—part}}('{{c6::hour}}', timestamp '2011-02-16 20:38:40') <br>
··  SELECT {{c7::EXTRACT}}(DAY FROM TIMESTAMP '2001-02-16 20:38:40'); <br>

%

%

clozeq

---

## pgs: builtin functions 05: case

··  SELECT a, <br>
····  {{c1::CASE}} WHEN a=1 THEN 'one' <br>
······  {{c2::WHEN}} a=2 {{c2::THEN}} 'two' <br>
······  {{c3::ELSE}} 'other' <br>
····  END <br>
····  FROM test; <br>
··  -- return first not null argument: <br>
··  SELECT {{c4::COALESCE}}(description, short—description, '(none)') ... <br>

%

%

clozeq

---

## pgs: builtin functions 06: arrays

··  {{c1::array—append}}(ARRAY[1,2], 3)  <br>
··  {{c2::array—cat}}(ARRAY[1,2,3], ARRAY[4,5])  <br>
··  array—ndims(ARRAY[[1,2,3], [4,5,6]])   <br>
··  {{c3::array—length}}(array[1,2,3], 1)  -- 3 <br>
··  {{c4::unnest}}(ARRAY[1,2])   <br>
····  1 <br>
····  2 <br>
····  (2 rows) <br>
··  {{c5::array—agg}}(expression)  <br>
··  -- convert any set to array <br>

%

%

clozeq

---

## pgs: builtin functions 07: aggregate

··  avg(expr) <br>
··  {{c1::bool—and}}(expr) <br>
··  -- and <br>
··  {{c2::bool—or}}(expr) <br>
··  -- or <br>
··  {{c3::count}}(*) <br>
··  -- number of elements <br>
··  count(expr) <br>
··  max <br>
··  min <br>
··  sum(expr) <br>

%

%

clozeq

---

## pgs: builtin functions 08: window

··  {{c1::row—number}}() <br>
··  -- row no <br>
··  {{c2::rank}}() <br>
··  -- row no as ranks <br>
··  dense—rank() <br>
··  percent—rank() <br>
··  {{c3::cume—dist}}() <br>
··  -- dist <br>
··  ntile <br>
··  {{c4::lag}} <br>
··  lead <br>
··  {{c5::first—value}} <br>
··  last—value <br>
··  nth—value <br>
····  <br>
%

%

clozeq

---

## pgs: current db setting

··  {{c1::current—catalog}} <br>
··  {{c2::current—database}}() <br>
··  {{c3::current—schema}} <br>
··  {{c4::current—role}} <br>
··  {{c5::current—user}} <br>
··  SELECT {{c1::current—setting}}('datestyle'); <br>

%

%

clozeq

---

## postgrest (pgr): getting started app steps

··  sudo docker run --name tutorial -p 5432:5432 ... -d {{c1::postgres}} <br>
··  CREATE {{c2::SCHEMA}} api; <br>
····  CREATE {{c3::TABLE}} api.todos (..) <br>
····  {{c4::INSERT}} INTO api.todos (task) VALUES .. <br>
··  CREATE {{c5::ROLE}} web—anon NOLOGIN; <br>
····  GRANT {{c6::usage}} ON {{c6::SCHEMA}} api TO web—anon; <br>
····  GRANT {{c7::select}} ON api.todos TO web—anon; <br>
··  {{c8::tutorial.conf}} <br>
····  db-uri = "postgres://postgres:mysecretpassword@localhost/postgres" <br>
····  db-schema = "api" <br>
····  db-anon-role = "web—anon" <br>
··  {{c9::postgrest}} tutorial.conf <br>
··  {{c10::curl}} http://localhost:3000/todos <br>
··  CREATE {{c12::ROLE}} todo—user NOLOGIN; <br>
····  GRANT {{c13::ALL}} ON api.todos TO todo—user; <br>
····  GRANT {{c14::USAGE, SELECT}} ON SEQUENCE api.todos—id—seq TO todo—user; <br>
··  tutorial.conf <br>
····  {{c15::jwt-secret}} = "&lt;secret&gt;" <br>
··  {{c16::jwt.io}}: <br>
····  { "{{c17::role}}": "todo—user" } <br>
··  export {{c18::TOKEN}}="..." <br>
··  curl http://localhost:3000/todos -X POST ..   <br>
····  -H "{{c19::Authorization}}: Bearer $TOKEN"   \ <br>

%

%

clozeq

---

## pgr: API ex 01

··  GET /{{c1::people}}?age=gte.18&student=is.true <br>
··  GET /people?{{c2::and=}}(grade.{{c2::gte}}.90,student.{{c3::is}}.true,{{c4::or}}(age.gte.14,age.{{c5::is}}.null)) <br>
··  ?a{{c6::=in}}."hi there","yes" <br>
··  ?tags{{c7::=cs}}.{example, new} <br>
··  verbs: OPTIONS, GET, {{c8::POST}}, PATCH, DELETE <br>

%

%

clozeq

---

## pgr: API ex 02

··  GET /view <br>
····  CREATE {{c1::VIEW}} fresh—stories AS <br>
······  SELECT * <br>
········  FROM stories <br>
······   WHERE pinned = true <br>
··········  OR published &gt; now() - interval '1 day' <br>
······  ORDER BY pinned DESC, published DESC; <br>
······  --&gt; <br>
······  GET /{{c2::fresh—stories}} HTTP/1.1 <br>

%

%

clozeq

---

## pgr: API ex 03

··  GET /entity?function /entity?order <br>
····  GET /people?{{c1::select=}}*,full—name <br>
····  CREATE FUNCTION full—name(people) RETURNS text AS $$ <br>
······  SELECT $1.fname || ' ' || $1.lname; <br>
····  GET /people?{{c2::full—name}}=fts.Beckett <br>
····  GET /people?{{c3::order=}}age.desc,height.asc <br>

%

%

clozeq

---

## pgr: API ex 04

··  Range pagination <br>
····  GET /people HTTP/1.1 <br>
······  Range-Unit: items <br>
······  {{c1::Range}}: 0-19 <br>
····  GET /people?{{c2::limit=}}15&{{c3::offset=}}30 <br>

%

%

clozeq

---

## pgr: API ex 05

··  single item ?id=eq.1 Accept: vnd.pgrst <br>
····  /items?{{c1::id}}={{c2::eq}}.1 <br>
······  [ {"id":1} ] <br>
····  /items?id=eq.1 <br>
······  Accept: application/{{c3::vnd.pgrst}}.object+json <br>
······  {{c4::{"id":1}}} <br>

%

%

clozeq

---

## pgr: API ex 06

··  joins items?select=id,{{c1::subitems}}(id,field) <br>
····  curl http://localhost:8080rest/items?id=gt.1&select=id,name,{{c2::subitems}}(id,name) <br>
····  /films?{{c3::select}}=title,directors(id,last—name) <br>
····  GET /films?select=*,actors(*)&actors.order=last—name,first—name <br>

%

%

clozeq

---

## pgr: API ex 07

··  {{c1::POST}} /{{c2::rpc}}/function {args} <br>
··  POST /rpc/function—name <br>
··  CREATE FUNCTION add—them(a integer, b integer) <br>
····  --&gt; <br>
····  POST /rpc/{{c3::add—them}} <br>
······  {"a":1, {{c4::"b"}}:2} <br>

%

%

clozeq

---

## pgr: API ex 08

··  request.header.XYZ <br>
····  SELECT {{c1::current—setting}}('{{c2::request.header.origin}}', true); <br>

%

%

clozeq

---

## pgr: API ex 09

··  insert = {{c1::POST}}, update = {{c2::PATCH}}, delete = DELETE <br>
····  POST /table—name  <br>
······  {{{c3::"col1": "value"}}, "col2": "value"} <br>
····  PATCH /people?age=lt.13 <br>
······  {{c4::{"category": "child"}}} <br>
····  POST /people <br>
······  Content-Type: {{c5::text/csv}} <br>
······  name,age <br>
······  J Doe,62  <br>
······  Jonas,10 <br>
····  POST /people <br>
······  Content-Type: application/{{c6::json}} <br>
······  [ <br>
········  {"name":"J Doe", "age":62}, <br>
········  .. <br>
······  ] <br>

%

%

clozeq

---

## pgr: authentication ex

··  Authentication <br>
····  GRANT user123 TO {{c1::authenticator}}; <br>

%

%

clozeq

---

## postgrest—starter—kit psk TOC 01

··  db/src/data/{{c1::tables}}.sql <br>
····  create table client (...) <br>
··  db/src/data/{{c2::schema}}.sql <br>
····  \ir tables.sql <br>
··  db/src/api/{{c3::views—and—procedures}}.sql <br>
····  create view clients as ... <br>
··  db/src/{{c4::api}}/schema.sql <br>
····  \ir views—and—procedures.sql <br>
··  db/src/{{c5::sample—data}}/data.sql <br>
····  COPY client (id,name,...) FROM STDIN (FREEZE ON);  <br>
····  1  Apple  address—1—   1   ... <br>
··  db/src/sample—data/{{c6::reset}}.sql <br>
····  \ir data.sql <br>

%

%

clozeq

---

## postgrest—starter—kit psk TOC 02

··  curl -H ".." http:.../clients?select=id,name <br>
··  db/src/{{c1::authorization}}/privileges.sql <br>
····  grant select, insert, update, delete on api.clients, api.projects, ...  to webuser; <br>
··  curl -H ".." http:.../clients?select=id,name <br>
··  db/src/api/{{c2::views—and—procedures}}.sql <br>
····  alter view clients owner to api; <br>
··  db/src/authorization/{{c3::privileges}}.sql <br>
····  create policy access—own—rows on client to api <br>
··  curl -H "Authorization: Bearer ${{c4::JWT—TOKEN}}" http://localhost:8080/rest/clients?select=id,name <br>

%

%

clozeq

---

## psk file structure 01

··  .env <br>
··  docker-compose.yml <br>
··  {{c1::openresty}}: reverse proxy and lua code <br>
··  {{c2::postgrest}} <br>
··  {{c3::db}}  <br>

%

%

clozeq

---

## psk file structure 02

··  openresty: reverse proxy and lua code <br>
····  {{c1::lualib}}/user—code: application lua code <br>
····  {{c2::nginx}}/ <br>
······  conf <br>
······  html <br>
····  {{c3::tests}} <br>
······  rest: rest interface tests <br>
······  common.js: helper functions <br>
····  Dockerfile <br>
····  entrypointsh: custom entrypoint <br>
··  postgrest <br>
····  {{c4::tests}}: bash based integration tests <br>
······  {{c5::all.sh}} assert.sh <br>

%

%

clozeq

---

## psk file structure 03

··  db/ <br>
····  {{c1::src}}: schema definition <br>
······  {{c2::data}}: definition of source tables <br>
········  schema.sql tables.sql <br>
······  {{c3::api}}: api entities <br>
········  schema.sql views—and—procedures.sql <br>
······  {{c4::libs}}: <br>
········  auth pgjwt rabbitmq request settings util <br>
······  {{c5::authorization}}: roles and privileges <br>
········  privileges.sql roles.sql <br>
······  {{c6::sample—data}}  <br>
········  data.sql reset.sql <br>
······  {{c7::init.sql}}: entry point <br>
····  {{c8::tests}}: pgTap tests <br>
······  rls.sql structure.sql <br>

%

%

clozeq

---

## Summary of Steps in PSK

1. basic REST request

··  $ curl http://localhost:8080/rest/todos?select=id <br>
··  [{"id":1},{"id":3},{"id":6}] <br>

2. {{c1::authorized}} REST request

··  $ export JWT—TOKEN=... <br>
··  $ curl -H "Authorization: Bearer $JWT—TOKEN" http://localhost:8080/rest/todos?select=id,todo <br>
··  [{"id":1,"todo":"item—1"},{"id":3,"todo":"item—3"},{"id":6,"todo":"item—6"}] <br>

3. Edit `db/src/sample—data/{{c2::data.sql}}`:

··  $ curl -H "Authorization: Bearer $JWT—TOKEN" http://localhost:8080/rest/todos?select=id,todo <br>
··  [{"id":1,"todo":"item—1—updated"},{"id":3,"todo":"item—3"},{"id":6,"todo":"item—6"}] <br>

4. Create new {{c7::table}}: data/table.sql imported from data/schema.sql

5. Create new {{c3::view as API}}: api/views—and—procedures.sql imported from api/schema.sql

6. Create {{c8::sample data}}: sample—data/data.sql imported from reset.sql

··  delete previous data in reset.sql: <br>

··  {{c4::truncate}} data.client restart identity cascade; <br>

7. Grant {{c5::privilege}} for the new view: authorization/privileges.sql imported from init.sql

8. Make request to new API:

··  $ curl -H "Authorization: Bearer $JWT—TOKEN" http://localhost:8080/rest/{{c6::new—view}}?select=id,name <br>

%

%

clozeq

---

## psk: install and setup

··  $ {{c1::git clone}} --single-branch https://github.com/subzerocloud/postgrest-starter-kit khumbuicefall <br>
··  $ cd khumbuicefall <br>

Edit `{{c2::.env}}`

··  COMPOSE—PROJECT—NAME=khumbuicefall <br>

Run the server embedded in docker container:

··  $ {{c3::docker-compose up -d}} # wait for 5-10s before running the next command <br>

Install and run [subzero-cli] to see what is going inside (optional):

··  $ docker pull subzerocloud/subzero-cli-tools <br>
··  $ npm install -g subzero-cli <br>
··  $ {{c4::subzero}} dashboard <br>

%

%

clozeq

---

## psk: basic REST calls

··  $ curl http://localhost:8080/rest/{{c1::todos?select=id}} <br>
··  [{"id":1},{"id":3},{"id":6}] <br>

Make an authorized request

··  $ export {{c2::JWT—TOKEN}}=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p—kMV1Ymk <br>
··  $ curl -H "Authorization: Bearer $JWT—TOKEN" http://localhost:8080/rest/todos?select=id,todo <br>
··  [{"id":1,"todo":"item—1"},{"id":3,"todo":"item—3"},{"id":6,"todo":"item—6"}] <br>

%

%

clozeq

---

## Check Generated SQL Using subzero

··  $ curl http://localhost:8080/rest/todos?select=id <br>

The following SQL code is generated automatically by `postgrest`:

··  {{c1::WITH}} pg—source AS <br>
····  (SELECT  "api"."todos"."{{c2::id}}" FROM  "api"."{{c3::todos}}") <br>
····  SELECT <br>
······  null AS total—result—set, pg—catalog.count(—postgrest—t) AS page—total, array[]::text[] AS header, coalesce(array—to—json(array—agg(row—to—json({{c4::—postgrest—t}}))), '[]')::character varying AS body <br>
······  FROM ( SELECT * FROM pg—source) —postgrest—t <br>

%

%

clozeq

---

## psk: actual data

··  {{c1::coalesce}}( <br>
····  {{c2::array—to—json}}( <br>
······  {{c3::array—agg}}( <br>
········  {{c4::row—to—json}}(—postgrest—t))) <br>

%

%

clozeq

---

## psk: editing db/src/ *.sql files

`./db/src/data/todo.sql` changed

··  --&gt; <br>

··  Starting code reload ------------------------ <br>
··  LOG:  statement: SELECT pg—terminate—backend(pid) FROM pg—stat—activity WHERE datname = 'app'; <br>
··  FATAL:  terminating connection due to administrator command <br>
··  LOG:  statement: DROP DATABASE if exists app; <br>

··  LOG:  statement: {{c1::CREATE DATABASE}} app; <br>

··  Ready --------------------------------------- <br>
··  LOG:  statement: {{c2::set client—min—messages}} to warning; <br>

%

%

clozeq

---

## psk: order of call for db/src/*.sql files

··  db/src/{{c1::init}}.sql <br>
····  db/src/data/{{c2::schema}}.sql <br>
······  db/src/data/{{c3::todo}}.sql <br>

%

%

clozeq

---

## File Structure of `.sql` Files

`init.sql` file contains several `include` i.e. `\ir` statements such as:

··  \ir {{c1::data}}/schema.sql <br>
··  \ir {{c2::api}}/schema.sql <br>
··  \ir {{c3::authorization}}/roles.sql <br>
··  \ir authorization/{{c4::privileges}}.sql <br>
··  \ir {{c5::sample—data}}/data.sql <br>

%

%

clozeq

---

## psk: reset.sql

Added `data.client` table sample data into sample—data/data.sql

Now edit `db/src/{{c1::sample—data}}/reset.sql` and add this line before `COMMIT`:

··  {{c1::truncate}} data.client {{c2::restart}} identity cascade; <br>

%

%

clozeq

---

## psk: added new view `api.clients`. GRANT?

Edit `db/src/authorization/privileges.sql` and add `GRANT` privilege statements:

··  {{c1::grant}} select, insert, update, delete <br>
··  on {{c2::api.clients}} <br>
··  to {{c3::webuser}}; <br>

%

%

clozeq

---

## psk: Summary of Steps 

1. basic REST request

2. authorized REST request

3. Edit `db/src/{{c1::sample—data/data}}.sql`:

······  [{"id":1,"todo":"item—1—updated"},] <br>

4. Create new table: data/{{c2::table}}.sql imported from data/schema.sql

5. Create new view as API: api/{{c3::views—and—procedures}}.sql imported from api/schema.sql

6. Create sample data: {{c4::sample—data}}/data.sql imported from reset.sql

······  {{c5::truncate}} data.client restart identity cascade; <br>

7. Grant privilege for the new view: authorization/{{c6::privileges}}.sql imported from init.sql

8. Make request to new API:

