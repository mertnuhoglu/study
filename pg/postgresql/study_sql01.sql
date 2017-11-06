select * from pg_catalog.pg_tables pt where pt.schemaname = 'pg_catalog';

select * from pg_catalog.pg_tables pt where pt.schemaname = 'api';

select * from pg_catalog.pg_views pv where pv.schemaname = 'api';

select user;

-- select "$user";

select '$user';

select '{Alex,Sonia}'::text[] As name;

select unnest('{Alex,Sonia}'::text[]) As name;

SELECT unnest(string_to_array('split.this', '.')) As x;

SELECT unnest(regexp_matches('100 hats 200', '\d+', 'g'));

SELECT '2012-03-11 3:10 AM America/Los_Angeles'::timestamptz AS america
  , '2012-03-11 3:10 AM'::timestamptz as istanbul1
  , '2012-03-11 3:10 AM GMT-3'::timestamptz as istanbul2
  , '2012-03-11 3:10'::timestamptz::date as date1
;

SELECT
  a
  , extract('year' from b)
  FROM
    ( VALUES
      ( '2012-03-11 3:10 AM'::timestamptz
      , '1012-03-11 3:10'::timestamptz::date)
    ) dates(a,b)
;

SELECT
  *
  FROM
    ( VALUES
      ( '2012-03-11 3:10 AM'::timestamptz
      , '1012-03-11 3:10'::timestamptz::date)
    ) dates
;

SELECT
  column1
  FROM
    ( VALUES
      ( '2012-03-11 3:10 AM'::timestamptz
      , '1012-03-11 3:10'::timestamptz::date)
    ) dates
;

SELECT
  *
  FROM
    ( VALUES
      ( '2012-03-11 3:10 AM'::timestamptz
      , '1012-03-11 3:10'::timestamptz::date)
    ) AS dates
;

select * from api.todos;