(ns mertnuhoglu.metamodel.e01)

(use '[datomic.api :only [q db] :as d])
(use '[mertnuhoglu.tutorial-ground-up.e01 :only [conn] :as e01])

;; qry01: Bütün atributları listeleyelim
(q '[:find ?n 
     :where 
     [:db.part/db :db.install/attribute ?a] 
     [?a :db/ident ?n]] 
   (db conn)) 
;; =>
; #{[:db/code] [:db.sys/reId] [:db/doc] [:db/fn] [:db.install/function] [:db/excise] [:db/cardinality] [:db/txInstant] [:db.excise/attrs] [:db.alter/attribute] [:db/noHistory] [:db/isComponent] [:db/fulltext] [:fressian/tag] [:db/index] [:db/lang] [:db.excise/before] [:db.excise/beforeT] [:db.sys/partiallyIndexed] [:person/address] [:db.install/valueType] [:db.install/partition] [:db/valueType] [:db/unique] [:db/ident] [:person/age] [:db.install/attribute] [:person/name]}

;; qry02: dbd (:db.part/db) varlığının (entity) atributları nelerdir?
(q '[:find ?a ?id
     :where 
     [:db.part/db ?a _]
     [?a :db/ident ?id]]
   (db conn))  
;; =>
; #{[13 :db.install/attribute] [10 :db/ident] [62 :db/doc] [14 :db.install/function] [11 :db.install/partition] [12 :db.install/valueType]}

;; qry03: Bütün dia (:db.install/attribute) değerlerini ve entitylerini listeleyelim
(q '[:find ?a ?n 
     :where 
     [:db.part/db :db.install/attribute ?a] 
     [?a :db/ident ?n]] 
   (db conn)) 
;; =>
; #{[50 :db/txInstant] [67 :db/tupleAttrs] [39 :fressian/tag] [7 :db/system-tx] [9 :db.sys/reId] [66 :db/tupleTypes] [19 :db.alter/attribute] [12 :db.install/valueType] [45 :db/noHistory] [10 :db/ident] [52 :db/fn] [105 :person/address] [15 :db/excise] [8 :db.sys/partiallyIndexed] [101 :person/name] [17 :db.excise/beforeT] [42 :db/unique] [68 :db/ensure] [69 :db.entity/attrs] [41 :db/cardinality] [16 :db.excise/attrs] [71 :db.attr/preds] [62 :db/doc] [43 :db/isComponent] [51 :db/fulltext] [13 :db.install/attribute] [44 :db/index] [102 :person/age] [18 :db.excise/before] [14 :db.install/function] [46 :db/lang] [70 :db.entity/preds] [40 :db/valueType] [65 :db/tupleType] [11 :db.install/partition] [47 :db/code]}

;; qry04a: Yukarıdaki dia entity'lerinden bir tanesinin tüm atributları ve değerlerini görelim id=g12909
(q '[:find ?a ?id ?v
     :where 
     [102 ?a ?v]
     [?a :db/ident ?id]]
   (db conn))  
;; =>
; #{[10 :db/ident :person/age] [41 :db/cardinality 35] [40 :db/valueType 22]}

;; qry04b: Yukarıdaki dia entity'lerinden bir tanesinin tüm atributları ve değerlerini görelim
(q '[:find ?a ?id ?v
     :where 
     [13 ?a ?v]
     [?a :db/ident ?id]]
   (db conn))   
;; =>
; #{[40 :db/valueType 20] [41 :db/cardinality 36] [10 :db/ident :db.install/attribute] [62 :db/doc "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will install v as an attribute."]}

;; qry04b1: `[40 :db/valueType 20]` içinde ref edilen 20 idli e nasıl bir varlık?
(q '[:find ?a ?id ?v
     :where 
     [20 ?a ?v]
     [?a :db/ident ?id]]
   (db conn)) 
; #{[62 :db/doc "Value type for references. All references from one entity to another are through attributes with this value type."] [39 :fressian/tag :ref] [10 :db/ident :db.type/ref]}

;; qry05: tüm valueType = ref atributları bulalım
(q '[:find ?n ?r
     :where 
     [:db.part/db :db.install/attribute ?ae]
     [?ae :db/ident ?n]
     [?ae :db.type/ref ?r]]
   (db conn))
; (err) datomic.db.ValueType cannot be cast to datomic.db.Attribute

(q '[:find ?n
     :where 
     [:db.part/db :db.install/attribute ?ae]
     [?ae :db/ident ?n]]
   (db conn)) 
; #{[:db/code] [:db.sys/reId] [:db/system-tx] [:db/doc] [:db.entity/preds] [:db/tupleAttrs] [:db/fn] [:db.install/function] [:db/excise] [:db/cardinality] [:db/txInstant] [:db.excise/attrs] [:db.alter/attribute] [:db/noHistory] [:db/isComponent] [:db/ensure] [:db/fulltext] [:fressian/tag] [:db/index] [:db.attr/preds] [:db/lang] [:db.entity/attrs] [:db.excise/before] [:db/tupleTypes] [:db.excise/beforeT] [:db.sys/partiallyIndexed] [:person/address] [:db.install/valueType] [:db.install/partition] [:db/valueType] [:db/unique] [:db/ident] [:person/age] [:db.install/attribute] [:person/name] [:db/tupleType]}

;; qry05a: Tüm valueType değerlerini bulalım
(q '[:find ?n ?v
     :where 
     [:db.part/db :db.install/attribute ?ae]
     [?ae :db/valueType ?n]
     [?n :db/ident ?v]]
   (db conn)) 
; #{[63 :db.type/tuple] [22 :db.type/long] [26 :db.type/fn] [24 :db.type/boolean] [23 :db.type/string] [25 :db.type/instant] [20 :db.type/ref] [21 :db.type/keyword] [64 :db.type/symbol]}

;; qry05b: valueType = ref olan tüm atributları çıkartalım id=g12902
(q '[:find ?ae ?v
     :where 
     [:db.part/db :db.install/attribute ?ae]
     [?ae :db/valueType :db.type/ref]
     [?ae :db/ident ?v]]
   (db conn))  
; #{[42 :db/unique] [68 :db/ensure] [41 :db/cardinality] [9 :db.sys/reId] [16 :db.excise/attrs] [19 :db.alter/attribute] [12 :db.install/valueType] [13 :db.install/attribute] [15 :db/excise] [14 :db.install/function] [46 :db/lang] [40 :db/valueType] [11 :db.install/partition]}

