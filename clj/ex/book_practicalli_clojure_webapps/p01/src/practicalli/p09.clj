(ns practicalli.p09
  (:require [next.jdbc :as jdbc]))

(comment
  (def db {:dbtype "h2" :dbname "example"})
  (def ds (jdbc/get-datasource db))
  (jdbc/execute! ds ["
  create table address (
    id int auto_increment primary key,
    name varchar(32),
    email varchar(255)
  )
  "])
  (jdbc/execute! ds ["
  insert into address(name,email)
    values('Sean', 'sean@x.com')
  "])
  (jdbc/execute! ds ["select * from address"])
  ;=> [#:ADDRESS{:ID 1, :NAME "Sean", :EMAIL "sean@x.com"}]

  (def db {:dbtype "sqlite" :dbname "db_p10.db"})
  (def ds (jdbc/get-datasource db))
  (jdbc/execute! ds ["select * from contacts"])
  ;=>
  ;[#:contacts{:contact_id 101, :first_name "ali", :last_name "veli", :email "ali@x.com", :phone "+90(555)..."}
  ; #:contacts{:contact_id 103, :first_name "ali", :last_name "veli", :email "ali2@x.com", :phone "+90(555)2..."}]

  (jdbc/execute! ds ["select 3*5 as result"])
  ;=> [{:result 15}]
,)