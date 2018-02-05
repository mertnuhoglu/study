drop schema if exists ddl_to_data cascade;
create schema ddl_to_data;
set search_path = ddl_to_data, public;

\ir schema.sql
\ir data.sql

