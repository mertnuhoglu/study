(ns mertnuhoglu.mbrainz.e01)

(use '[datomic.api :only [q db] :as d])
(use '[mertnuhoglu.mbrainz.setup :only [conn] :as setup])

;; qry01: tüm attribute'ları listele
(q '[:find ?n
     :where
     [:db.part/db :db.install/attribute ?a]
     [?a :db/ident ?n]]
  (db conn)) 
; #{[:medium/trackCount] [:db/code] [:db.sys/reId] [:medium/tracks] [:release/packaging] [:track/name] [:release/month] [:db/fn] [:release/year] [:release/artistCredit] [:artist/name] [:db/txInstant] [:release/labels] [:abstractRelease/name] [:db.alter/attribute] [:db/noHistory] [:db/isComponent] [:release/language] [:release/gid] [:abstractRelease/artists] [:artist/endDay] [:fressian/tag] [:db/index] [:db/lang] [:abstractRelease/artistCredit] [:release/script] [:language/name] [:db.excise/beforeT] [:db.sys/partiallyIndexed] [:db.install/valueType] [:artist/country] [:abstractRelease/type] [:script/name] [:artist/endMonth] [:artist/startMonth] [:db.install/attribute] [:track/position] [:artist/sortName] [:label/country] [:release/status] [:db/system-tx] [:label/startMonth] [:label/gid] [:db/doc] [:label/endYear] [:track/artists] [:artist/startDay] [:db.install/function] [:release/name] [:db/cardinality] [:db/excise] [:country/name] [:db.excise/attrs] [:artist/type] [:release/artists] [:artist/gid] [:release/day] [:artist/gender] [:abstractRelease/gid] [:label/sortName] [:db/fulltext] [:medium/position] [:track/artistCredit] [:release/barcode] [:db.excise/before] [:label/startYear] [:label/type] [:release/country] [:release/abstractRelease] [:db.install/partition] [:release/media] [:label/name] [:db/valueType] [:label/endMonth] [:artist/startYear] [:db/unique] [:medium/name] [:db/ident] [:label/endDay] [:track/duration] [:label/startDay] [:medium/format] [:artist/endYear]}

;; qry02: tüm ident'leri listele
(q '[:find ?id
     :where
     [?e :db/ident ?id]]
  (db conn)) 
; #{[:language/ukw] [:language/bow] [:media/part_48] [:artist/part_15] [:language/tij] [:language/cre] [:language/ciy] [:language/dlg] [:script/Tagb] [:script/Zyyy] [:language/nsl] [:language/nka] [:language/lwm] [:language/mht] [:language/yuk] [:language/gbh] [:language/jrr] [:language/ylo] [:language/ncg] [:language/dts] [:language/hmb] [:language/omo] [:language/nsm] [:language/jal] [:language/pha] [:language/kcx] [:language/amj] [:language/bwr] [:db/retract] [:language/aug] ...

;; qry03 mbrainz içindeki tüm ref tipindeki atributlar id=g12903
;;   id:: ad0b4b3d-64c0-43f1-a635-2d46e8e6752d
(q '[:find ?ae ?v
     :where 
     [:db.part/db :db.install/attribute ?ae]
     [?ae :db/valueType :db.type/ref]
     [?ae :db/ident ?v]]
   (db conn))   
; #{[107 :release/artists] [9 :db.sys/reId] [97 :release/script] [19 :db.alter/attribute] [111 :medium/format] [12 :db.install/valueType] [102 :release/packaging] [86 :label/type] [95 :release/country] [101 :release/media] [96 :release/labels] [110 :medium/tracks] [15 :db/excise] [87 :label/country] [115 :track/artists] [80 :abstractRelease/type] [69 :artist/type] [98 :release/language] [42 :db/unique] [41 :db/cardinality] [16 :db.excise/attrs] [81 :abstractRelease/artists] [13 :db.install/attribute] [70 :artist/gender] [14 :db.install/function] [46 :db/lang] [40 :db/valueType] [11 :db.install/partition] [71 :artist/country] [108 :release/abstractRelease]}

;; ## qry04a: ref verilen varlıkları bulma id=g12906

;; qry04a: tüm artistlerin, tüm atributları ve değerlerini çıkart.
(q '[:find ?e ?id ?v
     :where 
     [?e :release/artists ?rar]
     [?rar ?a ?v]
     [?a :db/ident ?id]]
   (db conn)) 
; #{[17592186072866 :artist/type 17592186045423] [17592186071616 :artist/country 17592186045691] [17592186070723 :artist/sortName "Kinks, The"] [17592186076115 :artist/sortName "CTI All-Stars"] [17592186072392 :artist/endYear 1978] [17592186077666 :artist/gid #uuid "05b5488d-7141-4b21-819b-d4713abf2a98"] ...

;; qry04b: bir refin karşı tarafındaki varlıkları bulma. değerleri ident ile göster.
(q '[:find ?e ?id ?idv
     :where 
     [?e :release/artists ?f]
     [?f ?a ?v]
     [?a :db/ident ?id]
     [?v :db/ident ?idv]]
   (db conn))
; (err) Cannot resolve key: 76c9a186-75bd-436a-85c0-823e3efddb7f

;; qry04c: bir refin karşı tarafındaki varlıkları bulma. pull api ile çek.

;; qry04c1: basit bir pull kullanım örneği
(q '[:find (pull ?e [:release/script])
     :where 
     [?e :release/artists ?rar]]
   (db conn))
; [[#:release{:script #:db{:id 17592186053542}}]
;  [#:release{:script #:db{:id 17592186053542}}]
;  [#:release{:script #:db{:id 17592186053542}}]
;  ...]

;; qry04c2: başka bir pull kullanım
(q '[:find (pull ?e [:track/name])
     :where 
     [?e :track/name _]]
   (db conn))
; [[#:track{:name "Intuition"}]
;  [#:track{:name "Mind Games"}]
;  [#:track{:name "One Day (at a Time)"}]
;  ...]

;; qry04c3: nested bir pull kullanım
(q '[:find (pull ?e [:track/name {:track/artists [:artist/name]}])
     :where 
     [?e :track/name _]]
   (db conn))
; [[#:track{:name "Intuition", :artists [#:artist{:name "John Lennon"}]}]
;  [#:track{:name "Mind Games",
;           :artists [#:artist{:name "John Lennon"}]}]
;  ...]

;; qry04c4: query map kullanım
(q {:query '[:find ?e :where [?e :track/name _]]
    :args [(db conn)]})
;; error

;; ## qry04b: ref verilen varlıkları bulma. 
   
;; qry04b1: artist'in tüm atributları neler?
(q '[:find ?id 
     :where 
     [?e :release/artists ?f]
     [?f ?a ?v]
     [?a :db/ident ?id]]
   (db conn))  
; #{[:artist/endDay] [:artist/sortName] [:artist/country] [:artist/startYear] [:artist/type] [:artist/startMonth] [:artist/endMonth] [:artist/gid] [:artist/startDay] [:artist/gender] [:artist/name] [:artist/endYear]}

;; qry04b2: artist'in name değerlerini listeleme
(q '[:find ?n
     :where 
     [?e :release/artists ?f]
     [?f :artist/name ?n]]
   (db conn))   
; #{["Porter Wagoner"] ["Sigmund Snopek III"] ["Flower Travellin' Band"] ["Randy Brecker"] ["Cowboy"] ...}

;; qry04b3: artist'in tüm atributlarının değerlerini pull ile çıkart
(q '[:find (pull ?f [*])
     :where 
     [?e :release/artists ?f]]
   (db conn))
; [[{:db/id 527765581341755,
;    :artist/gid #uuid "21dbcaa3-47ad-4c12-977c-66ddc891c938",
;    :artist/name "Juan y Junior",
;    :artist/sortName "Juan y Junior",
;    :artist/type #:db{:id 17592186045424}}]
;  ...]

;; qry04b4: bir artist'e ref veren tüm varlık tiplerinin atribut isimlerini bul
(q '[:find ?e
     :where 
     [?e :release/artists ?f]
     [?f :artist/name "Juan y Junior"]]
   (db conn))   

;; Datomic API
;; [datomic.api - Datomic Clojure API documentation](https://docs.datomic.com/on-prem/clojure/index.html)

(datomic.api/q 
  '[:find ?e
     :where 
     [?e :release/artists ?f]
     [?f :artist/name "Juan y Junior"]]
  (db conn))   

; Returns information about the attribute with the given id or ident.
(datomic.api/attribute db0 :db/ident) 
; #AttrInfo{:id 10 :ident :db/ident :value-type :db.type/keyword :cardinality :db.cardinality/one :indexed false :has-avet true :unique :db.unique/identity :is-component false :no-history false :fulltext false}

;; Returns the as-of point, or nil if none
(datomic.api/as-of-t db0) ; nil

;; Returns the t of the most recent transaction reachable via this db value.
(datomic.api/basis-t db0) ; 148253


