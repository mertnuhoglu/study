(ns mert.datalog-04-07)
(require '[datomic.client.api :as d])
(def cfg {:server-type :datomic-local
          :system "datomic-samples"})
(def client (d/client cfg))
(d/create-database client {:db-name "tutorial"})
(def conn (d/connect client {:db-name "tutorial"}))
;; [[datalog_04_07.clj]]

(def assertions 
  [{:db/ident :red}
   {:db/ident :green}
   {:db/ident :blue}
   {:db/ident :yellow}])

(d/transact
  conn
  {:tx-data assertions})
; {:db-before
;  #datomic.core.db.Db{:id "tutorial", :basisT 5, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :db-after
;  #datomic.core.db.Db{:id "tutorial", :basisT 6, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :tx-data
;  [#datom[13194139533318 50 #inst "2025-01-29T11:38:23.976-00:00" 13194139533318 true] #datom[74766790688841 10 :red 13194139533318 true] #datom[74766790688842 10 :green 13194139533318 true] #datom[74766790688843 10 :blue 13194139533318 true] #datom[74766790688844 10 :yellow 13194139533318 true]],
;  :tempids {}}

(defn make-idents
  [x]
  (mapv #(hash-map :db/ident %) x))
(def sizes [:small :medium :large :xlarge])
(make-idents sizes)
; [#:db{:ident :small}
;  #:db{:ident :medium}
;  #:db{:ident :large}
;  #:db{:ident :xlarge}]
(def types [:shirt :pants :dress :hat])
(def colors [:red :green :blue :yellow])
(d/transact conn {:tx-data (make-idents sizes)})
(d/transact conn {:tx-data (make-idents colors)})
(d/transact conn {:tx-data (make-idents types)})

(def schema-1
  [{:db/ident :inv/sku
    :db/valueType :db.type/string
    :db/unique :db.unique/identity
    :db/cardinality :db.cardinality/one}
   {:db/ident :inv/color
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   {:db/ident :inv/size
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   {:db/ident :inv/type
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}])
(d/transact conn {:tx-data schema-1})

(defn create-sample-data
  "Create a vector of maps of all permutations of args"
  [colors sizes types]
  (->> (for [color colors size sizes type types]
         {:inv/color color
          :inv/size size
          :inv/type type})
       (map-indexed
        (fn [idx map]
          (assoc map :inv/sku (str "SKU-" idx))))
       vec)) ;; 64 (4 x 4 x 4) maps

@(def sample-data (create-sample-data colors sizes types))

@(def sample-data-transaction (d/transact conn {:tx-data sample-data}))
