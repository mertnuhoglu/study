drop schema if exists recommendation_engine cascade;
create schema recommendation_engine;
set search_path = recommendation_engine, public;

\ir schema.sql
\ir data.sql
