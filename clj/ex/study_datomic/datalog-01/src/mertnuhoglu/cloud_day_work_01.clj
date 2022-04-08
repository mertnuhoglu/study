(ns mertnuhoglu.cloud-day-work-01)

; [(1138) Day of Datomic Cloud - Session 2 - YouTube](https://www.youtube.com/watch?v=ZP-E2IgqKfA&list=PLjyLzdfdsKwqF9I1XSX_Y4TXAo8pYXbOv&index=2)
; Workshop

; Step01: Run datomic server first:

; cd /Users/mertnuhoglu/codes/clj/lib/datomic-pro-${VERSION}
; bin/run -m datomic.peer-server -h localhost -p 8998 -a myaccesskey,mysecret -d hello,datomic:mem://hello

(require '[datomic.client.api :as d])

(def cfg {:server-type :peer-server
          :access-key "myaccesskey"
          :secret "mysecret"
          :endpoint "localhost:8998"
          :validate-hostnames false})
;#'user/cfg

(def client (d/client cfg))
;#'user/client
(def conn (d/connect client {:db-name "hello"}))
;#'user/conn

; 1. Draw an ER diagram

;[Student|name; courses; ]
;[Course|name; students; teacher; ]
;[Teacher|name; courses; ]

(def student-schema
  [{:db/ident :student/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}

   {:db/ident :student/courses
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/many}])

(def course-schema
  [{:db/ident :course/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}

   {:db/ident :course/teacher
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}])

(def teacher-schema
  [{:db/ident :teacher/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}])

(d/transact conn {:tx-data student-schema})
(d/transact conn {:tx-data course-schema})
(d/transact conn {:tx-data teacher-schema})

