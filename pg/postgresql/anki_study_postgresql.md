
## Run posgtersql database server from docker

  docker start {{c1::postgreststarterkit_db_1}}

%

%

clozeq

---

## Run pgcli with existing env variables

  pgcli {{c1::app}}
  set {{c1::search_path}} = {{c1::data}}, public;

%

%

clozeq

---

## Env variables for postgresql database clients

  export {{c1::PGHOST}}=localhost
  export {{c1::PGPORT}}=5432
  export {{c1::PGUSER}}=superuser
  export {{c1::PGDATABASE}}=app

%

%

clozeq

---


