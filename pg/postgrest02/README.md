
# README

Here I follow official tutorial of postgrest: https://postgrest.com/en/v4.3/tutorials/tut0.html

But instead of the official docker container, I use existing docker container of khumbuicefall

    postgrest
      setup db
        docker exec -it postgreststarterkit_db_1 psql -d postgres -h localhost -p 5432 -U postgres
        ALTER USER "postgres" WITH PASSWORD 'mysecretpassword';
        CREATE SCHEMA api;
        CREATE TABLE api.todos (
          id SERIAL PRIMARY KEY,
          done BOOLEAN NOT NULL DEFAULT FALSE,
          task TEXT NOT NULL,
          due TIMESTAMPTZ
        );
        INSERT INTO api.todos (task) VALUES
          ('finish tutorial 0'), ('pat self on back');
        CREATE ROLE web_anon NOLOGIN;
        GRANT web_anon TO postgres;
        GRANT usage ON SCHEMA api TO web_anon;
        GRANT select ON api.todos TO web_anon;
        \q
      run postgrest
        tutorial.conf
          db-uri = "postgres://postgres:mysecretpassword@localhost/postgres"
          db-schema = "api"
          db-anon-role = "web_anon"
        postgrest tutorial.conf
          > Listening on port 3000
        curl http://localhost:3000/todos
          [{"id":1,"done":false,"task":"finish tutorial 0","due":null},{"id":2,"done":false,"task":"pat self on back","due":null}]
        psql
          CREATE ROLE todo_user NOLOGIN;
          GRANT todo_user TO postgres;
          GRANT USAGE ON SCHEMA api TO todo_user;
          GRANT ALL ON api.todos TO todo_user;
          GRANT USAGE, SELECT ON SEQUENCE api.todos_id_seq TO todo_user;
        tutorial.conf
          jwt-secret = "T8k0JBUifFC6UevcbjpJi1Jc7mJfCVg3eEfoQi7IpuI"
        postgrest tutorial.conf
        jwt.io
          payload:
            { "role": "todo_user" }
          jwt:
            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoidG9kb191c2VyIn0.nZ1wYdWinlOGkzh_FE6CP0cJo4W8IWETc6LVtnEO2P0
        export TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoidG9kb191c2VyIn0.nZ1wYdWinlOGkzh_FE6CP0cJo4W8IWETc6LVtnEO2P0"
        curl http://localhost:3000/todos -X POST \
          -H "Authorization: Bearer $TOKEN"   \
          -H "Content-Type: application/json" \
          -d '{"task": "learn how to auth2"}'
    postgrest starter kit
      overview
        docker-compose up -d 
        curl http://localhost:8080/rest/todos?select=id
        subzero dashboard
        ~/codes/pg/khumbuicefall/db/src/sample_data/data.sql
          change: item_1 to updated
        export JWT_TOKEN=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk
        curl -H "Authorization: Bearer $JWT_TOKEN" http://localhost:8080/rest/todos?select=id,todo
      schemas: data schema, api schema, sample data, access rights
        db/src/data/tables.sql: 
          create table client (
            id           serial primary key,
            name         text not null,
            address      text,
            user_id      int not null references "user"(id),
            created_on   timestamptz not null default now(),
            updated_on   timestamptz
          );
          create index client_user_id_index on client(user_id);
          create table project (
            id           serial primary key,
            name         text not null,
            client_id    int not null references client(id),
            user_id      int not null references "user"(id),
            created_on   timestamptz not null default now(),
            updated_on   timestamptz
          );
          create index project_user_id_index on project(user_id);
          create index project_client_id_index on project(client_id);
          create table task (
            id           serial primary key,
            name         text not null,
            completed    bool not null default false,
            project_id   int not null references project(id),
            user_id      int not null references "user"(id),
            created_on   timestamptz not null default now(),
            updated_on   timestamptz
          );
          create index task_user_id_index on task(user_id);
          create index task_project_id_index on task(project_id);
          create table project_comment (
            id           serial primary key,
            body         text not null,
            project_id   int not null references project(id),
            user_id      int not null references "user"(id),
            created_on   timestamptz not null default now(),
            updated_on   timestamptz
          );
          create index project_comment_user_id_index on project_comment(user_id);
          create index project_comment_project_id_index on project_comment(project_id);
          create table task_comment (
            id           serial primary key,
            body         text not null,
            task_id      int not null references task(id),
            user_id      int not null references "user"(id),
            created_on   timestamptz not null default now(),
            updated_on   timestamptz
          );
          create index task_comment_user_id_index on task_comment(user_id);
          create index task_comment_task_id_index on task_comment(task_id);
        db/src/data/schema.sql:
          \ir tables.sql
        db/src/api/views_and_procedures.sql
          create or replace view clients as
          select id, name, address, created_on, updated_on from data.client;
          create or replace view projects as
          select id, name, client_id, created_on, updated_on from data.project;
          create or replace view tasks as
          select id, name, completed, project_id, created_on, updated_on from data.task;
          create or replace view comments as
          select 
            id, body, 'project'::text as parent_type, project_id as parent_id, 
            project_id, null as task_id, created_on, updated_on
          from data.project_comment
          union
          select id, body, 'task'::text as parent_type, task_id as parent_id,
            null as project_id, task_id, created_on, updated_on
          from data.task_comment;
        db/src/api/schema.sql
          \ir views_and_procedures.sql
        db/src/sample_data/data.sql
          set search_path = data, public;
          \echo # filling table client (3)
          COPY client (id,name,address,user_id,created_on,updated_on) FROM STDIN (FREEZE ON);
          1 Apple address_1_  1 2017-07-18 11:31:12 \N
          2 Microsoft address_1_  1 2017-07-18 11:31:12 \N
          3 Amazon  address_1_  2 2017-07-18 11:31:12 \N
          \.
          \echo # filling table project (4)
          COPY project (id,name,client_id,user_id,created_on,updated_on) FROM STDIN (FREEZE ON);
          1 MacOS 1 1 2017-07-18 11:31:12 \N
          2 Windows 2 1 2017-07-18 11:31:12 \N
          3 IOS 1 1 2017-07-18 11:31:12 \N
          4 Office  2 1 2017-07-18 11:31:12 \N
          \.
          \echo # filling table task (5)
          COPY task (id,name,completed,project_id,user_id,created_on,updated_on) FROM STDIN (FREEZE ON);
          1 Design a nice UI  TRUE  1 1 2017-07-18 11:31:12 \N
          2 Write some OS code  FALSE 1 1 2017-07-18 11:31:12 \N
          3 Start aggressive marketing  TRUE  2 1 2017-07-18 11:31:12 \N
          4 Get everybody to love it  TRUE  3 1 2017-07-18 11:31:12 \N
          5 Move everything to cloud  TRUE  4 1 2017-07-18 11:31:12 \N
          \.
          \echo # filling table project_comment (2)
          COPY project_comment (id,body,project_id,user_id,created_on,updated_on) FROM STDIN (FREEZE ON);
          1 This is going to be awesome 1 1 2017-07-18 11:31:12 \N
          2 We still have the marketshare, we should keep it that way   2 1 2017-07-18 11:31:12 \N
          \.
          \echo # filling table task_comment (2)
          COPY task_comment (id,body,task_id,user_id,created_on,updated_on) FROM STDIN (FREEZE ON);
          1 Arn't we awesome? 1 1 2017-07-18 11:31:12 \N
          2 People are going to love the free automated install when they see it in the morning 3 1 2017-07-18 11:31:12 \N
          \.
          -- 
          ALTER SEQUENCE client_id_seq RESTART WITH 4;
          ALTER SEQUENCE project_id_seq RESTART WITH 5;
          ALTER SEQUENCE task_id_seq RESTART WITH 6;
          ALTER SEQUENCE project_comment_id_seq RESTART WITH 3;
          ALTER SEQUENCE task_comment_id_seq RESTART WITH 3;
          -- 
          ANALYZE client;
          ANALYZE project;
          ANALYZE task;
          ANALYZE project_comment;
          ANALYZE task_comment;
        db/src/sample_data/reset.sql
          BEGIN;
          \set QUIET on
          \set ON_ERROR_STOP on
          set client_min_messages to warning;
          set search_path = data, public;
          truncate todo restart identity cascade;
          truncate user restart identity cascade;
          truncate client restart identity cascade;
          truncate project restart identity cascade;
          truncate task restart identity cascade;
          truncate project_comment restart identity cascade;
          truncate task_comment restart identity cascade;
          \ir data.sql
          COMMIT;
        db/src/authorization/privileges.sql
          grant select, insert, update, delete 
          on api.clients, api.projects, api.tasks, api.comments
          to webuser;
        curl -H "Authorization: Bearer $JWT_TOKEN" http://localhost:8080/rest/clients?select=id,name
          [{"id":1,"name":"Apple"},{"id":2,"name":"Microsoft"},{"id":3,"name":"Amazon"}]
      authentication
        curl \
          -H "Content-Type: application/json" \
          -H "Accept: application/vnd.pgrst.object+json" \
          -d '{"email":"alice@email.com","password":"pass"}' \
          http://localhost:8080/rest/rpc/login
          # {
            "me":{"id":1,"name":"alice","email":"alice@email.com","role":"webuser"},
            "token":"xxxxxxxxxxxxx"
          }
      row level security
        grant 
          create policy ...
          using ...
          with chec ...
      input validation
        check
      mutations on complex views
        function
          if INSERT
            ...
        create trigger ...

