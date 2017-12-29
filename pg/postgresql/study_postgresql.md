    <url:file:///~/projects/study/pg/postgresql/study_postgresql.md>

# Study PostgreSQL 20171003 

    ref
      <url:file:///~/projects/study/pg/postgrest02/README.md>
      <url:file:///~/Dropbox (Personal)/mynotes/content/articles/articles_db.md>
    Run 
      docker start postgreststarterkit_db_1
        opt
          sudo docker run --name tutorial -p 5432:5432 ... -d postgres
      DataGrip > postgrest_starter_kit superuser@docker
        DB_NAME=app
        DB_SCHEMA=api
        SUPER_USER=superuser
        SUPER_USER_PASSWORD=superuserpass
      franchise - notebook
        npx franchise-client@0.2.7
        https://franchise.cloud/app/
      psql -d app -h localhost -p 5432 -U superuser
        psql -d app -U superuser -h localhost -w
        set search_path = data, public;
      env variables
        export PGHOST=localhost
        export PGPORT=5432
        export PGUSER=superuser
        export PGDATABASE=app
        psql
      jupyter
        %load_ext sql
        %%sql postgresql://superuser:superuserpass@localhost/app
        select * from api.todos;
        %sql select 1;
      pgcli
        pgcli postgresql://superuser:superuserpass@localhost/app
        pgcli app
        set search_path = data, public;
      db create
        psql -c 'CREATE DATABASE library OWNER = superuser'
    Database Administration
      Roles
        CREATE ROLE leo LOGIN PASSWORD 'king' CREATEDB VALID UNTIL 'infinity'
        CREATE ROLE royalty INHERIT
        GRANT group_role TO leo  
        SET ROLE royalty
        CREATE DATABASE mydb
      Information Schema
        select table_name FROM information_schema.tables;
          all tables in pgs
          ~/projects/study/pg/postgresql/select_table_name_FROM_information_schem.tsv
            pg_statistic
            pg_type
            ...
        SELECT * FROM information_schema.columns WHERE table_name = 'pg_database';
          | table_catalog | table_schema | table_name  | column_name | ... |
          | app           | pg_catalog   | pg_database | datname     | ... |
        select * from information_schema.information_schema_catalog_name;
          catalog_name
          ---
          app
        select * from information_schema.schemata;
          catalog_name  schema_name schema_owner  ...
          app pg_toast  postgres        
          app pg_temp_1 postgres        
        select * from information_schema.sequences;
          sequence_catalog  sequence_schema sequence_name ...
          ---
          app data  user_id_seq bigint  64  2 0 1 1 9223372036854775807 1 NO
          app data  todo_id_seq bigint  64  2 0 1 1 9223372036854775807 1 NO
      Extensions
        CREATE SCHEMA my_extensions
        ALTER DATABASE mydb SET search_path = '"$user", public, my_extensions'
        SELECT name FROM pg_available_extensions WHERE installed_version IS NOT NULL ORDER BY name;
          name
          --
          pgcrypto
          plpgsql
        CREATE EXTENSION fuzzystrmatch SCHEMA my_extensions
      GRANT
        GRANT some_privilege TO some_role
        GRANT ALL ON ALL TABLES IN SCHEMA public TO mydb_admin WITH GRANT OPTION
        GRANT SELECT, REFERENCES, TRIGGER ON ALL TABLES IN SCHEMA my_schema TO PUBLIC
        GRANT SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA my_schema TO PUBLIC
        GRANT USAGE ON SCHEMA my_schema TO PUBLIC
        ALTER DEFAULT PRIVILEGES IN SCHEMA my_schema GRANT SELECT, REFERENCES ON TABLES TO PUBLIC
        ALTER DEFAULT PRIVILEGES IN SCHEMA my_schema GRANT ALL ON TABLES TO MYDB_ADMIN WITH GRANT OPTION
        GRANT USAGE ON SCHEMA
      Backup and Restore
        pg_dump
        pg_dumpall
        ~/.pgpass
        pg_dump -h localhost -p 5432 -U someuser -F c -b -v -f mydb.backup mydb
        ... -F c -b -v -t *.pay* -f pay.backup mydb
        ... -F c -b -v -N public -f all_schema_except_public.backup mydb
        ... -F p --column-inserts -f select_tables.backup mydb
        ... -F d -f /path/dir mydb
        pg_dumpall ... -f myglobals.sql --globals-only
        psql -U postgres -f myglobals.sql
        psql -U postgres --set ON_ERROR_STOP=on -f myglobals.sql
        psql -U postgres -d mydb -f myglobals.sql
        CREATE DATABASE mydb
          pg_restore --dbname=mydb --jobs=4 --verbose mydb.backup
          --section=pre-data
      Tablespaces
        pg_default
        pg_global
        CREATE TABLESPACE secondary LOCATION 'path'
        ALTER DATABASE mydb SET TABLESPACE secondary
      postgresql.conf
        SELECT *  FROM pg_settings ORDER BY context, name;
          SELECT____FROM_pg_settings_ORDER_BY_cont.csv
          name  setting unit  category  short_desc  extra_desc  context vartype source  min_val max_val enumvals  boot_val  reset_val sourcefile  sourceline  pending_restart
          ---
          ignore_system_indexes off   Developer Options Disables reading from system indexes. It does not prevent updating the indexes, so it is safe to use.  The worst consequence is slowness. backend bool  default       off off     false
          post_auth_delay 0 s Developer Options Waits N seconds on connection startup after authentication. This allows attaching a debugger to the process.  backend integer default 0 2147    0 0     false
    other
      CREATE OR REPLACE
      SELECT 'CREATE TABLE
        staging.count_to_50(array_to_string(array_agg('x' || i::text ' varchar(10)));' As create_sql
        FROM generate_series(1,9) As i;
    psql
      psql -d database -U  user -W
      \c <dbname> <username>
      list tables: \dt
        more: \dt+
      describe a table: \d <table_name>
      list schemas: \dn
      list functions: \df
      list views: \dv
      list users and roles: \du
      save command history: \s <filename>
      exec commands from file: \i <filename>
      help on statement: \h <ALTER TABLE>
      edit command in editor: \e
      \l                 # list databases
      \set AUTOCOMMIT off
      \timing
      \set eav 'EXPLAIN ANALYZE VERBOSE'
        :eav SELECT COUNT(*) FROM pg_tables
      \set HISTSIZE 10
      \! ls
      \copy
      \connect postgresql_book
        \cd /postgresql_book/ch03
        \copy staging.factfinder_import FROM DEC_..csv CSV
        NULL AS ''
        DELIMITER '|'
        FROM somefile.txt
      \connect postgresql_book
        \copy (SELECT * FROM staging.factfinder_import WHERE s01 ~ E'^[0-9]+') TO '/test.tab'
        WITH DELIMITER E'\t' CSV HEADER
    Data Types
      Serials
        CREATE SEQUENCE
        nextval(sequence_name)
      String
        concat: ||
        extracting: substring
        split_part("text.sub", ".", 1) 
        SELECT unnest(string_to_array("split.this", ".")) As x
      regex
        regexp_replace('text', 'regex', E'replace')
        backreferences: \\1
        unnest(regexp_matches('text', 'match', 'flags'))
      Temporals
        SELECT '2012-03-11 3:10 AM America/Los_Angeles'::timestamptz
        SELECT '2012-03-11 3:10 AM'::timestamp - '2012-03-11 1:10 AM'::timestampt
        SELECT '2012-03-11 3:10 AM America/Los_Angeles'::timestamptz AT TIME ZONE 'Europe/Paris'
        SELECT '2012-03-11 3:10 AM'::timestamp + '1 hour'::interval
        SELECT '23 hours'::interval + '1 hour'::interval
        SELECT ('2012-03-11 3:10 AM'::timestamp ,'2012-03-11 5:10 AM'::timestamp) OVERLAPS (<timestamp>) 
        generate_series(<timestamp>, <end_timestamp>, <interval>)
        date_part('hour', <timestamp>)
        to_char(<timestamp>, 'HH12:MI AM')
      Arrays
        integer[]
        character[]
        SELECT ARRAY[2001, 2002] As years
        SELECT array(SELECT ...)
        '{Alex,Sonia}'::text[] As name
        string_to_array('ca.ma.tx', '.')
        SELECT array_agg(log_ts) FROM logs WHERE log_ts BETWEEN ...
        my_array[1] # first
        my_array[array_upper(my_array,1)] # last
        my_array[2:4]
        arr[1:2] || arr[5:6]
        unnest('{1,2,3}'::smallint[])
      Range
        (-2,2]
        INSERT INTO employment (employee, period) VALUES ('Alex', '[2012-04-24, infinity)'::daterange)
        overlap (&&)
        contains (@>)
        SELECT employee FROM employment WHERE period @> CURRENT_DATE GROUP BY employee
      JSON
        https://www.postgresql.org/docs/current/static/functions-json.html
        INSERT INTO families_j (profile) VALUES (
          '{"name":"Gomez", "members": [{"member":{"relation":"padre",...}}]}')
        json_extract_path(profile, 'name') As family,
        json_extract_path_text( json_array_elements( json_extract_path(profile, 'members') ), 'member', 'name') As member
        json_array_elements( (profile->'members')) #>> '{member,name}'::text[] As member
        json_array_length(profile->'members') As numero,
        profile->'members'->0#>>'{member,name}'::text[] As primero
        profile->'members'
        profile->0
        SELECT row_to_json(f) As x
          FROM (SELECT id, profile->>'name' As name FROM families_j) As f
        SELECT profile->>'name' As family
          FROM families_b
          WHERE profile @> '{"members":[{"member":{"name":"Alex"} }]}'
    Tables
      CREATE TABLE logs_2011 (PRIMARY KEY(log_id)) INHERITS (logs)
      ALTER TABLE logs_2011 ADD CONSTRAINT chk_y2011
        CHECK (log_ts >= '2011-1-1'::timestamptz 
          AND log_ts < <timestamp>)
      CREATE UNLOGGED TABLE ...
      ALTER TABLE table_name ADD COLUMN new_col TYPE;
      ALTER TABLE table_name DROP COLUMN col1;
      ALTER TABLE table_name RENAME col1 TO col2;
      ALTER TABLE table_name ADD PRIMARY KEY (column,...)
      ALTER TABLE table_name DROP CONSTRAINT pk_constraint_name
      ALTER TABLE table_name RENAME TO new_table
    Constraints
      ALTER TABLE facts ADD CONSTRAINT fk_facts_f 
        FOREIGN KEY (fact_type_id) REFERENCES lu_fact_types (fact_type_id)
        ON UPDATE CASCADE ON DELETRESTRICT;
        CREATE INDEX fki_facts_1 ON facts (fact_type_id);
      ALTER TABLE logs_2011 ADD CONSTRAINT uq 
        UNIQUE (user_name, log_ts);
      ALTER TABLE logs ADD CONSTRAINT chk 
        CHECK (user_name = lower(user_name));
      ALTER TABLE schedules ADD CONSTRAINT ex_schedules
        EXCLUDE USING gist (room WITH =, time_slot WITH &&)
    Views
      CREATE view_name AS query
      CREATE RECURSIVE VIEW view_name(columns) AS query
      CREATE VIEW census.vw_facts_2011 AS
        SELECT fact_type_id, ... FROM <table> WHERE yr = 2011;
      WITH CHECK OPTION
        keep data in view always
      UPDATE <view> SET val = 1 WHERE val = 0;
      DELETE FROM <view> WHERE val = 0
      Using Triggers to Update Views
        CREATE FUNCTION census.trig_vw_facts() RETURNS trigger AS $$
          BEGIN
            IF (TG_OP = 'INSERT') THEN
              INSERT INTO <table(col1, col2)>
              SELECT NEW.col1, NEW.col2;
              RETURN NEW;
            END IF;
          END;
          $$
          LANGUAGE plpgsql VOLATILE;
        CREATE TRIGGER census.trig_vw_facts
          INSTEAD OF INSERT OR UPDATE OR DELETE ON <view>
          FOR EACH ROW EXECUTE PROCEDURE census.trig_vw_facts();
    Handy Constructions
      DISTINCT ON
      LIMIT and OFFSET
      CAST('2011-1-11' AS date)
        '2011-1-1'::date
      INSERT INTO <table> (<cols>)
        VALUES
          (<row1>),
          (<row2>);
      SELECT .. WHERE <col1> ILIKE '%duke%'
      DELETE FROM census.facts
        USING census.lu_fact_types As ft
        WHERE facts.fact_type_id = ft.fact_type_id AND ft_short_name = 's01'
      UPDATE census.lu_fact_types AS f
        SET short_name = ..
        WHERE ..
        RETURNING fact_type_id, short_name
      SELECT x FROM census.lu_fact_types As x LIMIT 2;
      SELECT array_to_json(array_agg(f)) As cat FROM (
        SELECT MAX(fact_type_id) As max_type, category FROM census.lu_fact_types
        GROUP BY category
        ) As f
      DO language plpgsql
        $$
        DECLARE var_sql text
        BEGIN
          var_sql := string_agg(
            'INSERT INTO ...
            ' || lpad(i::text, 2, '0') || '..
            '
          )
          FROM generate_series(1,51) As i;
          EXECUTE var_sql;
        END
        $$;
      SELECT student,
          AVG(score) FILTER (WHERE subject ='algebra') As algebra, 
          AVG(score) FILTER (WHERE subject ='physics') As physics
        FROM test_scores 
        GROUP BY student;
    Window Functions
      SELECT tract_id, val, AVG(val) OVER () as val_avg
        FROM census.facts
        WHERE fact_type_id = 86;
      SELECT tract_id, val, AVG(val) OVER (PARTITION BY left(tract_id,5)) as val_avg_county
        FROM census.facts
        WHERE fact_type_id = 86 ORDER BY tract_id;
      SELECT ROW_NUMBER() OVER (ORDER BY tract_name) As rnum, tract_name
      SELECT 
        ROW_NUMBER() OVER (wt) As rnum, 
        substring(tract_id,1,5) As county_code,
        tract_id,
        LAG(tract_id,2) OVER wt As tract_2_before,
        LEAD(tract_id) OVER wt As tract_after,
        FROM census.lu_tracts
        WINDOW wt AS (PARTITION BY substring(tract_id,1,5) ORDER BY tract_id)
    Common Table Expressions (CTE)
      WITH 
        cte1 AS (
          SELECT * FROM <table1>
        ), 
        cte2 AS (
          SELECT * FROM <table2>
        )
        SELECT *
        FROM cte1
        WHERE ..
      ex: writable cte
        CREATE TABLE logs_2011_01 (
            PRIMARY KEY (..),
            CONSTRAINT chk
              CHECK (log_ts >= '2011-01-01')
          )
          INHERITS (logs_2011);
        WITH t AS (
            DELETE FROM ONLY logs_2011 WHERE log_ts < '2011-03-0' RETURNING *
          )
          INSERT INTO logs_2011_01_02 SELECT * FROM t;
      WITH RECURSIVE subordinates AS (
        SELECT employee_id, manager_id, full_name
          FROM employees
          WHERE employee_id = 2
        UNION
        SELECT e.employee_id, e.manager_id, e.full_name
          FROM employees e
          INNER JOIN subordinates s ON s.employee_id = e.manager_id
        ) 
        SELECT * FROM subordinates;
    Functions
      CREATE OR REPLACE FUNCTION func_name(arg1 arg1_datatype DEFAULT arg1_default)
        RETURNS some type | set of some type | TABLE (..) AS
        $$ 
        BODY of function
        $$ 
        LANGUAGE languafe_of_function;
      SELECT lanname FROM pg_language;
      CREATE AGGREGATE my_agg (input data type) ( ..)
      CREATE OR REPLACE FUNCTION write_to_log(param_user_name varchar, param_description text)
        RETURNS integer AS
        $$
        INSERT INTO logs(user_name, description) VALUES($1, $2)
        RETURNING log_id;
        $$
        LANGUAGE 'sql' VOLATILE;
      SELECT write_to_log('alejandro', 'Woke up') As new_id;
      CREATE OR REPLACE FUNCTION select_logs_rt(param_user_name varchar)
        RETURNS TABLE (log_id int, user_name varchar(50), description text, log_ts timestamptz) AS
        ...
      CREATE AGGREGATE geom_mean (numeric) (
          SFUNC=geom_mean_state,
          STYPE=numeric[],
          FINALFUNC=geom_mean_final,
          INITCOND='{0,0}'
        );
      CREATE EXTENSION plpython3u;
    Builtin Functions
      between is
        a BETWEEN x and y
        a <= x AND a <= y
        a NOT BETWEEN x AND y
        <expression> IS [NOT] NULL
        IS
          TRUE
          FALSE
          UNKNOWN
          NOT
      string substring to_char
        char_length(string)
        substring(string, int, int)
        'thomas' ~ '.*thomas.*'
        to_char(current_timestamp, 'HH12:MI:SS')
        to_date('05 Dec 2000', 'DD Mon YYYY')
      date temporal extract
        current_date()
        current_time()
        date_part('hour', timestamp '2011-02-16 20:38:40')
        SELECT EXTRACT(DAY FROM TIMESTAMP '2001-02-16 20:38:40');
        SELECT CURRENT_TIMESTAMP;
        SELECT now();
      case coalesce
        SELECT a,
          CASE WHEN a=1 THEN 'one'
               WHEN a=2 THEN 'two'
               ELSE 'other'
          END
          FROM test;
        SELECT COALESCE(description, short_description, '(none)') ...
      array append ndims length unnest agg
        array_append(ARRAY[1,2], 3) 
        array_cat(ARRAY[1,2,3], ARRAY[4,5]) 
        array_ndims(ARRAY[[1,2,3], [4,5,6]])  
        array_length(array[1,2,3], 1)  # 3
        unnest(ARRAY[1,2])  
          1
          2
          (2 rows)
        array_agg(expression) any non-array type    
      aggregate avg bool_and count
        avg(expr)
        bool_and(expr)
        bool_or(expr)
        count(*)
        count(expr)
        max
        min
        sum(expr)
      agg json_agg string_agg
        json_agg(expression)  
        json_object_agg(name, value)  
        string_agg(expr, delimiter)
        STRING_AGG(a.activity, ';' ORDER BY a.activity) As activities
      window row_number rank cume_dist ntile
        row_number()
        rank()
        dense_rank()
        percent_rank()
        cume_dist()
        ntile
        lag
        lead
        first_value
        last_value
        nth_value
      IN generate_series 
        SELECT col1
          FROM tab1
          WHERE EXISTS (SELECT 1 FROM tab2 WHERE col2 = tab1.col2);
        expression IN (subquery)
        generate_series(start, stop, step interval)
        SELECT generate_subscripts('{NULL,1,NULL,2}'::int[], 1) AS s;
      current db user schema setting set_config
        current_catalog
        current_database()
        current_role
        current_schema
        current_user
        has_any_column_privilege(user, table, privilege)
        has_column_privilege(user, table, column, privilege)
        SELECT current_setting('datestyle');
        SELECT set_config('log_statement_stats', 'off', false);
        pg_cancel_backend(pid int)  
        select pg_start_backup('label_goes_here');
    Roles
      CREATE ROLE name;
      GRANT group_role TO role1, ...;
      REVOKE group_role TO role1, ...;
      CREATE ROLE joe LOGIN INHERIT;
      CREATE ROLE admin NOINHERIT;
      SET ROLE wheel;
      ALTER ROLE john LOGIN
    JOIN
      FROM people AS p 
        LEFT JOIN people_activities As a ON (p.p_name = a.p_name)
        GROUP BY p.p_name
        ORDER BY p.p_name; 
      FROM employment As e1 INNER JOIN employment As e2
        ON e1.period && e2.period
        WHERE e1.employee <> e2.employee GROUP BY e1.employee;
      CREATE OR ... AS
        SELECT ..
        FROM <table> INNER JOIN <table>
        ON <fk1> = <fk2>
    Row Level Security (RLS)
      CREATE TABLE accounts (manager text, company text);
      ALTER TABLE accounts ENABLE ROW LEVEL SECURITY;
      CREATE POLICY user_policy ON users
        USING (true)
        WITH CHECK (user_name = current_user);
      GRANT SELECT
        (user_name, uid, gid, real_name, home_phone, extra_info, home_dir, shell)
        ON passwd TO public;
      GRANT UPDATE
        (pwhash, real_name, home_phone, extra_info, shell)
        ON passwd TO public;
    postgrest
      steps
        sudo docker run --name tutorial -p 5432:5432 ... -d postgres
        CREATE SCHEMA api;
          CREATE TABLE api.todos (..)
          INSERT INTO api.todos (task) VALUES ..
        CREATE ROLE web_anon NOLOGIN;
          GRANT web_anon TO postgres;
          GRANT usage ON SCHEMA api TO web_anon;
          GRANT select ON api.todos TO web_anon;
        tutorial.conf
          db-uri = "postgres://postgres:mysecretpassword@localhost/postgres"
          db-schema = "api"
          db-anon-role = "web_anon"
        postgrest tutorial.conf
        curl http://localhost:3000/todos
        curl http://localhost:3000/todos -X POST ..  -d '{"task": "do bad thing"}'
          # error
        CREATE ROLE todo_user NOLOGIN;
          GRANT ALL ON api.todos TO todo_user;
          GRANT USAGE, SELECT ON SEQUENCE api.todos_id_seq TO todo_user;
        tutorial.conf
          jwt-secret = "<secret>"
        jwt.io:
          { "role": "todo_user" }
        export TOKEN="..."
        curl http://localhost:3000/todos -X POST ..  
      admin
        nginx.conf
          ...
          upstream postgrest {
            server localhost:3000;
          }
          server {
            location /api/ {
              proxy_pass http://postgrest/;
              add_header Content-Location /api/$upstream_http_content_location;
              ...
        DELETE /logs
          sudo -E pgxn install safeupdate
          postgresql.conf:
            shared_preload_libraries='safeupdate';
        sudo ngrep -d lo0 port 3000
        postgresql.conf
          log_destination = "stderr"
          log_directory = "pg_log"
          log_statement = "all"
      API
        GET /entity?param1=op.value&...
          GET /people HTTP/1.1
          verbs: OPTIONS, GET, POST, PATCH, DELETE
          GET /people?age=gte.18&student=is.true
          GET /people?and=(grade.gte.90,student.is.true,or(age.gte.14,age.is.null))
          ?a=in."hi there","yes"
          ?tags=cs.{example, new}
        GET /view
          CREATE VIEW fresh_stories AS
            SELECT *
              FROM stories
             WHERE pinned = true
                OR published > now() - interval '1 day'
            ORDER BY pinned DESC, published DESC;
            -->
            GET /fresh_stories HTTP/1.1
        GET /entity?function /entity?order
          GET /people?select=*,full_name
          CREATE FUNCTION full_name(people) RETURNS text AS $$
            SELECT $1.fname || ' ' || $1.lname;
          CREATE INDEX people_full_name_idx ON people
            USING GIN (to_tsvector('english', full_name(people)));
            -->
            GET /people?full_name=fts.Beckett
          GET /people?order=age.desc,height.asc
        Range pagination
          GET /people HTTP/1.1
            Range-Unit: items
            Range: 0-19
          GET /people?limit=15&offset=30
        single item ?id=eq.1 Accept: vnd.pgrst
          /items?id=eq.1
            [ {"id":1} ]
          /items?id=eq.1
            Accept: application/vnd.pgrst.object+json
            {"id":1}
        joins items?select=id,subitems(id,field)
          curl http://localhost:8080rest/items?id=gt.1&select=id,name,subitems(id,name)
          /films?select=title,directors(id,last_name)
          GET /films?select=*,actors(*)&actors.order=last_name,first_name
        POST /rpc/function {args}
          POST /rpc/function_name
          CREATE FUNCTION add_them(a integer, b integer)
            -->
            POST /rpc/add_them
              {"a":1, "b":2}
        request.header.XYZ
          SELECT current_setting('request.header.origin', true);
        insert = POST, update = PATCH, delete = DELETE
          POST /table_name 
            {"col1": "value", "col2": "value"}
          PATCH /people?age=lt.13
            {"category": "child"}
          POST /people
            Content-Type: text/csv
            name,age
            J Doe,62 
            Jonas,10
          POST /people
            Content-Type: application/json
            [
              {"name":"J Doe", "age":62},
              ..
            ]
      Authentication
        GRANT user123 TO authenticator;
      postgrest_starter_kit (psk)
        db/src/data/tables.sql
          create table client (...)
          create index client_user_id_index on client(user_id);
        db/src/data/schema.sql
          \ir tables.sql
        db/src/api/views_and_procedures.sql
          create view clients as
            select id, ... from data.client;
        db/src/api/schema.sql
          \ir views_and_procedures.sql
        db/src/sample_data/data.sql
          \echo # client
          COPY client (id,name,...) FROM STDIN (FREEZE ON); 
          1  Apple  address_1_   1   ...
          ALTER SEQUENCE client_id_seq RESTART WITH 4;
        db/src/sample_data/reset.sql
          \ir data.sql
        curl -H ".." http:.../clients?select=id,name
          # {"permission error"}
        db/src/authorization/privileges.sql
          grant select, insert, update, delete on api.clients, api.projects, ...  to webuser;
          grant usage on sequence data.client_id_seq to webuser;
        curl -H ".." http:.../clients?select=id,name
          # [{"id":1,...}] 
          # all rows
        db/src/api/views_and_procedures.sql
          alter view clients owner to api;
        db/src/authorization/privileges.sql
          create policy access_own_rows on client to api
          using ( request.user_role() = 'webuser' and request.user_id() = user_id )
          with check ( request.user_role() = 'webuser' and request.user_id() = user_id);
        curl -H "Authorization: Bearer $JWT_TOKEN" http://localhost:8080/rest/clients?select=id,name
          # [{"id":1,"name":"Apple"},{"id":2,"name":"Microsoft"}]
          # my own rows
        db/src/data/tables.sql
          user_id      int not null references "user"(id) default request.user_id(),
        create table client (
          check (length(name)>2 and length(name)<100),
          check (updated_on is null or updated_on > created_on)
        test:
          curl ... -d '{"name":"A"}'  
          # {..."message":"new row for relation \"client\" violates check constraint \"client_name_check\""}
        db/src/libs/util/mutation_comments_trigger.sql
          create function mutation_comments_trigger() returns trigger as $$
              elsif (tg_op = 'UPDATE') then
                  if (new.parent_type = 'task') then
                      update data.task_comment 
                      set 
                          body = coalesce(new.body, old.body)
                      where id = old.id
                      returning * into c;
                      return (c.id, c.body);
        db/src/libs/util/schema.sql
          \ir mutation_comments_trigger.sql;
        db/src/init.sql
          \ir libs/util/schema.sql
        db/src/api/views_and_procedures.sql
          create trigger comments_mutation
            instead of insert or update or delete on comments
            for each row execute procedure util.mutation_comments_trigger();
        curl \
          -H "Content-Type: application/json" \
          -H "Accept: application/vnd.pgrst.object+json" \
          -d '{"email":"alice@email.com","password":"pass"}' \
          http://localhost:8080/rest/rpc/login
          # response:
            {
              "me":{"id":1,"name":"alice","email":"alice@email.com","role":"webuser"},
              "token":"xxxxxxxxxxxxx"
            }
        curl ... | python -mjson.tool
        curl ...  /projects
          --data-urlencode select="id,name,tasks(id,name)" \
          --du client_id="eq.1" \
          --du tasks.completed="eq.false"
        cloc --include-lang=SQL db/src/api/views_and_procedures.sql db/src/data/tables.sql db/src/authorization/privileges.sql db/src/libs/util/
    psk: source code review
      file structure
        .env
        docker-compose.yml
        openresty: reverse proxy and lua code
          lualib/user_code: application lua code
          nginx/
            conf
            html
          tests
            rest: rest interface tests
            common.js: helper functions
          Dockerfile
          entrypointsh: custom entrypoint
        postgrest
          tests: bash based integration tests
            all.sh assert.sh
        db: 
          src: schema definition
            data: definition of source tables
              schema.sql tables.sql
            api: api entities
              schema.sql views_and_procedures.sql
            libs:
              auth pgjwt rabbitmq request settings util
            authorization: roles and privileges
              privileges.sql roles.sql
            sample_data 
              data.sql reset.sql
            init.sql: entry point
          tests: pgTap tests
            rls.sql structure.sql
      db
        src
          init.sh
            ~/codes/pg/khumbuicefall/db/src/init.sh
          init.sql
            ~/codes/pg/khumbuicefall/db/src/init.sql
          api
      function call hierarchy
        \ir libs/auth/schema.sql
          \ir ../pgjwt/schema.sql
            pgjwt--0.0.1.sql
              FUNCTION url_encode
              FUNCTION url_decode
              FUNCTION algorithm_sign
              FUNCTION sign
              FUNCTION verify
          encrypt_pass
          sign_jwt
          get_jwt_payload
          set_auth_endpoints_privileges
        \ir libs/request/schema.sql
          create or replace function request.env_var(v text) returns text as $$
          create or replace function request.jwt_claim(c text) returns text as $$
          create or replace function request.cookie(c text) returns text as $$
          create or replace function request.header(h text) returns text as $$
          create or replace function request.user_id() returns int as $$
          create or replace function request.user_role() returns text as $$
        \ir libs/rabbitmq/schema.sql
          create or replace function rabbitmq.send_message(
          create or replace function rabbitmq.on_row_change() returns trigger as $$
        \ir libs/util/schema.sql
          \ir mutation_comments_trigger.sql;
            create or replace function mutation_comments_trigger() returns trigger as $$
        \ir data/schema.sql
          \ir ../libs/auth/data/user_role_type.sql
            create type user_role as enum ('webuser');
          \ir ../libs/auth/data/user.sql
            create table "user" (
            create trigger user_encrypt_pass_trigger
          \ir todo.sql
            create table todo (
            create trigger send_change_event
          \ir tables.sql
            create table client (
            create table project (
            create table task (
            create table project_comment (
            create table task_comment (
        \ir api/schema.sql
          \ir ../libs/auth/api/user_type.sql
            create type "user" as (id int, name text, email text, role text);
          \ir ../libs/auth/api/all.sql
            \ir session_type.sql
              create type session as (me json, token text);
            \ir login.sql
              create or replace function login(email text, password text) returns session as $$
            \ir refresh_token.sql
              create or replace function refresh_token() returns text as $$
            \ir signup.sql
              create or replace function signup(name text, email text, password text) returns session as $$
            \ir me.sql
              create or replace function me() returns "user" as $$
          \ir todos.sql
            create or replace view todos as
          \ir views_and_procedures.sql
            create or replace view clients as
            create or replace view projects as
            create or replace view tasks as
            create or replace view comments as
            create trigger comments_mutation
        \ir authorization/roles.sql
          create role :"authenticator" with login password :'authenticator_pass';
          create role :"anonymous";
          create or replace function _temp_create_application_roles("authenticator" text, "roles" text[]) returns void as $$
        \ir authorization/privileges.sql
          create policy todo_access_policy on data.todo to api 
          create policy access_own_rows on client to api
          create policy access_own_rows on project to api
          create policy access_own_rows on task to api
          create policy access_own_rows on project_comment to api
          create policy access_own_rows on task_comment to api
        \ir sample_data/data.sql
          COPY data.user (id,name,email,"password") FROM STDIN (FREEZE ON);
          COPY data.todo (id,todo,private,owner_id) FROM STDIN (FREEZE ON);
          COPY client (id,name,address,user_id,created_on,updated_on) FROM STDIN (FREEZE ON);
          COPY project (id,name,client_id,user_id,created_on,updated_on) FROM STDIN (FREEZE ON);
          COPY task (id,name,completed,project_id,user_id,created_on,updated_on) FROM STDIN (FREEZE ON);
          COPY project_comment (id,body,project_id,user_id,created_on,updated_on) FROM STDIN (FREEZE ON);
          COPY task_comment (id,body,task_id,user_id,created_on,updated_on) FROM STDIN (FREEZE ON);
      function signatures
        type session as (me json, token text)
        login(email, password): session
        refresh_token(): text token
        signup(name text, email text, password text): session
        me(): user
    ddl
      analysis
        pk
          id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
          product_id serial PRIMARY KEY  -- implicit primary key constraint
          multiple keys
            bill_id    int REFERENCES bill (bill_id) ON UPDATE CASCADE ON DELETE CASCADE
            , product_id int REFERENCES product (product_id) ON UPDATE CASCADE
            , CONSTRAINT bill_product_pkey PRIMARY KEY (bill_id, product_id)  -- explicit pk
          bid INTEGER NOT NULL REFERENCES Book, 
            PRIMARY KEY(bid) -- a book is borrowed once at a time!
          id INT NOT NULL PRIMARY KEY,
          employee_id   NUMBER         NOT NULL,
            CONSTRAINT employees_pk PRIMARY KEY (employee_id)
        text varchar
          payload text
          , product    text NOT NULL
          employee varchar(20)
          first_name    VARCHAR2(1000) NOT NULL,
        numeric int
          , price      numeric NOT NULL DEFAULT 0
          , product_id int REFERENCES product (product_id) ON UPDATE CASCADE
          room smallint
          bid INTEGER NOT NULL REFERENCES Book, 
          a1 int[]
          a_id int
          running_total INTEGER
          gdp_per_capita DECIMAL(10, 2) NOT NULL
          employee_id   NUMBER         NOT NULL,
        fk
          bill_id    int REFERENCES bill (bill_id) ON UPDATE CASCADE ON DELETE CASCADE
          , product_id int REFERENCES product (product_id) ON UPDATE CASCADE
          bid INTEGER NOT NULL REFERENCES Book, 
          user_id INTEGER,
        date
          aggr_date DATE,
          , billdate date NOT NULL DEFAULT CURRENT_DATE
          period daterange
          time_slot tstzrange
          due TIMESTAMPTZ
          added TIMESTAMP WITHOUT TIME ZONE NOT NULL
          borrowed TIMESTAMP NOT NULL, 
          created_on   timestamptz not null default now(),
        other
          gender BOOLEAN NOT NULL, 
          profile json
          profile jsonb
      examples
        identity column
          CREATE TABLE test_new (
              id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
              payload text
          );
        How to implement a many-to-many relationship in PostgreSQL? <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10176>
          CREATE TABLE product (
            product_id serial PRIMARY KEY  -- implicit primary key constraint
            , product    text NOT NULL
            , price      numeric NOT NULL DEFAULT 0
          );
          CREATE TABLE bill (
            bill_id  serial PRIMARY KEY
          , bill     text NOT NULL
          , billdate date NOT NULL DEFAULT CURRENT_DATE
          );
          CREATE TABLE bill_product (
            bill_id    int REFERENCES bill (bill_id) ON UPDATE CASCADE ON DELETE CASCADE
          , product_id int REFERENCES product (product_id) ON UPDATE CASCADE
          , amount     numeric NOT NULL DEFAULT 1
          , CONSTRAINT bill_product_pkey PRIMARY KEY (bill_id, product_id)  -- explicit pk
          );
        CREATE TABLE accounts (manager text, company text);
        CREATE TABLE dir_list (filename text) 
        CREATE TABLE employment (id serial PRIMARY KEY, employee varchar(20), period daterange)
        CREATE TABLE families_j (id serial PRIMARY KEY, profile json)
        CREATE TABLE families_b (id serial PRIMARY KEY, profile jsonb)
        CREATE TABLE logs_2011 (PRIMARY KEY(log_id)) INHERITS (logs)
        CREATE TABLE schedules(id .., room smallint, time_slot tstzrange);
        writable CTE
          CREATE TABLE logs_2011_01 (
            PRIMARY KEY (..),
            CONSTRAINT chk
              CHECK (log_ts >= '2011-01-01')
          )
          INHERITS (logs_2011);
        CREATE TABLE api.todos (
          id SERIAL PRIMARY KEY,
          done BOOLEAN NOT NULL DEFAULT FALSE,
          task TEXT NOT NULL,
          due TIMESTAMPTZ
        );
        CREATE TABLE people (
          fname text,
          lname text
        );
        CREATE TABLE sample_data01 (
          product_id INTEGER NOT NULL, 
          title VARCHAR(11) NOT NULL, 
          added TIMESTAMP WITHOUT TIME ZONE NOT NULL
        );
        CREATE TABLE generated_table (
                name VARCHAR(6) NOT NULL,
                kg INTEGER NOT NULL,
                species VARCHAR(8) NOT NULL
        )
        ;
        CREATE TABLE Book( 
          bid SERIAL PRIMARY KEY,
          title TEXT NOT NULL, 
          isbn ISBN13 NOT NULL 
        );
        CREATE TABLE Reader( 
          rid SERIAL PRIMARY KEY,
          firstname TEXT NOT NULL, 
          lastname TEXT NOT NULL, 
          born DATE NOT NULL, 
          gender BOOLEAN NOT NULL, 
          phone TEXT 
        );
        CREATE TABLE Borrow( 
          borrowed TIMESTAMP NOT NULL, 
          rid INTEGER NOT NULL REFERENCES Reader,
          bid INTEGER NOT NULL REFERENCES Book, 
          PRIMARY KEY(bid) -- a book is borrowed once at a time!
        );
        CREATE TABLE channel(
          id INT NOT NULL PRIMARY KEY,
          fixture INT NOT NULL
          );
        CREATE TABLE user_msg_log (
            aggr_date DATE,
            user_id INTEGER,
            running_total INTEGER
        );
        CREATE TABLE countries (
          code CHAR(2) NOT NULL,
          year INT NOT NULL,
          gdp_per_capita DECIMAL(10, 2) NOT NULL
        );
        CREATE TABLE states
          ("name" int, "admin" int)
        ;
        CREATE TABLE employees (
           employee_id   NUMBER         NOT NULL,
           first_name    VARCHAR2(1000) NOT NULL,
           last_name     VARCHAR2(1000) NOT NULL,
           date_of_birth DATE           NOT NULL,
           phone_number  VARCHAR2(1000) NOT NULL,
           CONSTRAINT employees_pk PRIMARY KEY (employee_id)
        )
        CREATE TABLE tracks (id SERIAL, artists JSON);
        CREATE TABLE tbl
          (zipcode text NOT NULL, city text NOT NULL, state text NOT NULL);
        CREATE TABLE monkey(name text NOT NULL)
        CREATE TABLE tbl (a1 int[], a2 int[]);
        CREATE TABLE foo (id serial, data text);
        CREATE TABLE staff (
          staff_id serial PRIMARY KEY,
          staff    text NOT NULL
        );
        CREATE TABLE tbl_a (a_id int, col1 int, col2 int);
        create table client (
          id           serial primary key,
          name         text not null,
          address      text,
          user_id      int not null references "user"(id),
          created_on   timestamptz not null default now(),
          updated_on   timestamptz
        );
