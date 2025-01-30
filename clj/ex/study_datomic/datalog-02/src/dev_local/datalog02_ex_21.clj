(ns dev-local.datalog02-ex-21)

; Tarih: 20230609
; Title: API

(require '[datomic.client.api :as d])

;; Memory storage
(def client (d/client {:server-type :dev-local
                       :storage-dir :mem
                       :system "ci"}))

(d/delete-database client {:db-name "db21"})
(d/create-database client {:db-name "db21"})
(def conn (d/connect client {:db-name "db21"}))

(def schema
  [{:db/ident       :user/name
    :db/doc         "The unique username of a user."
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}])
(d/transact conn {:tx-data schema})
(def data01
  [{:user/name    "jillosaurus"}
   {:user/name    "jonnyboy"}])
(def tx-result
  (d/transact conn {:tx-data data01}))

(class tx-result) 
; clojure.lang.PersistentArrayMap

(keys tx-result)  
; (:db-before :db-after :tx-data :tempids)

(:db-before tx-result)   
; #datomic.core.db.Db{:id "594ebd88-4ec6-4c7b-8d23-ef2954af23e8", :basisT 6, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil}

(d/q '[:find ?e :where [?e :user/name "jillosaurus"]] (:db-after tx-result)) 
; [[101155069755466]]

(d/q '[:find ?e :where [?e :user/name "jillosaurus"]] (:db-before tx-result))  
; []

(d/q '[:find ?e :where [?e :user/name "jillosaurus"]] (d/db conn))   
; [[101155069755466]]

; #grsm/tst Yukarıda `:db-after` `:db-before` ve `d/db` ifadeleri arasındaki farkları açıklayın. 
;   id:: a6bc892b-c6e3-4a55-a7be-8cb939f31740

(:tx-data tx-result) 
; [#datom[13194139533319 50 #inst "2023-06-09T08:53:09.874-00:00" 13194139533319 true] 
;  #datom[101155069755466 73 "jillosaurus" 13194139533319 true] 
;  #datom[101155069755467 73 "jonnyboy" 13194139533319 true]]

; #grsm/tst Yukarıda 3 tane datom objesinin kaydını görüyoruz. Hepsinde ortak olan `13194139533319` değeri ne anlama geliyor? 
;   id:: 374c0461-8798-452c-b683-4a3789f4e33b

; (d/ident (d/db conn) 50)                       ;;=> :db/txInstant
(d/q '[:find ?i :where [50 ?a] [?a :db/ident ?i]] (d/db conn)) 
; [[:db/doc] [:db/valueType] [:db/ident] [:db/cardinality]]

; #grsm/tst Yukarıdaki sorguda `50` nedir? `?a` nedir? `?i` nedir? id=g14483
