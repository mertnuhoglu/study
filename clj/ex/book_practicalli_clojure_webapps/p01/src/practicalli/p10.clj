(ns practicalli.p10
  (:require [clojure.pprint :as p]
            [clojure.java.jdbc :as j]
            ))

; from: [Clojure and SQLite – Yet Another Blog in Statistical Computing](https://statcompute.wordpress.com/2018/03/12/clojure-and-sqlite/)

(comment
  (def db
    {:classname   "org.sqlite.JDBC"
     :subprotocol "sqlite"
     :subname     "/Users/mertnuhoglu/projects/study/clj/ex/book_practicalli_clojure_webapps/p01/db_p10.db"})

  (p/print-table
    (j/query db
      (str "select tbl_name, type from sqlite_master where type = 'table' limit 3;")))

  (j/db-do-commands db
    (str "
       CREATE TABLE contacts (
         contact_id INTEGER PRIMARY KEY,
         first_name TEXT NOT NULL,
         last_name TEXT NOT NULL,
         email TEXT NOT NULL UNIQUE,
         phone TEXT NOT NULL UNIQUE
       );
    "))

  (j/db-do-commands db
    (str "
      insert into contacts(contact_id, first_name, last_name, email, phone)
      values (103, 'ali', 'veli', 'ali2@x.com', '+90(555)2...');
  "))

  (j/query db
    (str "select * from contacts;"))

  ,)