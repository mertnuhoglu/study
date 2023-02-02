
# Datomic Kod Okumalarından Notlar  id=g13532

rfr: /Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01b.clj

## transact

	(d/transact)

	(d/transact conn {:tx-data 👈 [ 👉[:db/add eid :inv/count 100]]})

	[:db/add "datomic.tx" 👈 :db/doc "correct data entry"]  ;; fact about transaction

	[:db/add [:inv/sku "SKU-21"] 👈 <attribute> <value>] ;; lookup ref

	[:db/retract 👈 [:inv/sku "SKU-22"] :inv/count 7]

rfr: `Transaction structure <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13539>`

	[:db/add entity-id attribute value]

	[:db/retract entity-id attribute value]
	[:db/retract entity-id attribute]

## schema

	(def attributes
		[{:db/ident :inv/count
			:db/valueType :db.type/long
			:db/cardinality :db.cardinality/one}])

	(d/transact conn {:tx-data [{:db/ident :red}]}
	;=>
	;{:db-before #datomic.core.db.Db{:id "19c724ee-3905-4f85-92d7-0d0623302e69",
	;                              👉:basisT 5,
	;                                :indexBasisT 0,
	;                                :index-root-id nil,
	;                              👉:asOfT nil,  👈
	;                                :sinceT nil,
	;                                :raw nil},
	; 👉:db-after #datomic.core.db.Db{:id "19c724ee-3905-4f85-92d7-0d0623302e69",
	;                                 :basisT 6,
	;                                 :indexBasisT 0,
	;                                 :index-root-id nil,
	;                                 :asOfT nil,
	;                                 :sinceT nil,
	;                                 :raw nil},
	; 👉:tx-data [#datom[13194139533318 50 #inst"2022-11-11T11:28:45.337-00:00" 13194139533318 true]
	;             #datom[101155069755465 10 :red 13194139533318 true]
	;             #datom[101155069755466 10 :green 13194139533318 true]
	;             #datom[101155069755467 10 :blue 13194139533318 true]
	;             #datom[101155069755468 10 :yellow 13194139533318 true]],
	; :tempids {}}

## pull

	(d/pull
		(d/db conn)
		[{:inv/color [:db/ident]}
		 {:inv/size [:db/ident]}
		 {:inv/type [:db/ident]}]
		[:inv/sku "SKU-42"] 👈)
	;;=> #:inv{:color #:db{:ident :blue}, :size #:db{:ident :large}, :type #:db{:ident :dress}}

	(pull-many db '[*] [led-zeppelin jimi-hendrix janis-joplin])

## Nested entity map

	(def add-order
		{:order/items
		[👉{:item/id [:inv/sku "SKU-25"]
		    :item/count 10}
		   {:item/id [:inv/sku "SKU-26"]
		    :item/count 20}]})

rfr: `fulcro tipi veritabanı <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/edn03.edn#r=g13092>`

## Parameterized Query

	(d/q
		'[:find ?sku
			:in $ ?inv 👈
			:where [?item :item/id ?inv]
		db 
		[:inv/sku "SKU-25"] 👈)

## Rules

Kurallarla, çok sayıda join cümleciğini kapsülleyebiliriz.

Bu kuralları sorguda kullanmak için `:in` cümleciğinde `%` sembolünü kullanmalıyız:

	(def rules
		'[[(ordered-together ?inv ?other-inv)
			 [?item :item/id ?inv]
			 [?order :order/items ?item]
			 [?order :order/items ?other-item]
			 [?other-item :item/id ?other-inv]]])

	(d/q
		'[:find ?sku
	 👉	:in $ % ?inv
			:where
	 👉	(ordered-together ?inv ?other-inv)
			[?other-inv :inv/sku ?sku]]
	 👉db rules [:inv/sku "SKU-25"])
	;;=> [["SKU-25"] ["SKU-26"]]

## Lookup ref

	[:inv/sku "SKU-21"]

	[:db/add [:inv/sku "SKU-21"] <attribute> <value>]

  [{:db/ident :inv/sku
    :db/valueType :db.type/string
  👉:db/unique :db.unique/identity 
    :db/cardinality :db.cardinality/one}

	(def stu [:user/email "stuarthalloway@datomic.com"] 👈)
	(def tx-result (d/transact conn
									{:tx-data
										[{:story/title "ElastiCache in 6 minutes"
											:story/url "http://blog.datomic.com/2012/09/elasticache-in-5-minutes.html"}
										 {:db/id "datomic.tx"
									    :source/user stu 👈}]}))

Mevcut varlıklara ref vermek için:

rfr: `Lookup Refs <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13538>`

	{👉 :db/id [:person/email "joe@example.com"]
	 :person/loves :pizza}

## Connection

rfr: `Connect to Sample Database <url:file:///~/prj/study/clj/articles-datomic.md#r=g13507>`

	(def client (d/client {:server-type :dev-local
								   		 👉:storage-dir :mem
												 :system "ci"}))

	(def conn (d/connect client 
	          👉{:db-name "tutorial"}))

	(def client (d/client {:server-type :dev-local
											 👉:system "datomic-samples"}))

Bu durumda  `~/.datomic/dev-local.edn` içindeki `{:storage-dir "/Users/mertnuhoglu/db/"}` içinde veritabanları tutulur.

	(d/create-database 👈 client {:db-name "provenance"} 👈)
	(def conn (d/connect client {:db-name "provenance"}))

## as-of

	(def tx-result (d/transact conn {:tx-data ...}))
	(def db-t 👉 (:t (:db-after tx-result))) ;=> 12
	;; what was the title as of earlier point in time?
	(d/pull (d/as-of db db-t 👈) '[:story/title] story)

	(def as-of-eoy-2013 (d/as-of db #inst "2014-01-01"))
	(d/entity as-of-eoy-2013 [:item/id "DLC-042"])

## history

query the history of the data

	(dh/q '[:find ?a
					:where
					[?e :name "Alice"]
					[?e :age ?a]]
		(dh/history @conn))
	;; => #{[20] [25]}

## since

since <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13566>

	(d/q ('[:find ?count 
					:in $ $since ?id 
					:where [$ ?e :item/id ?id] 
								[$since ?e :item/count ?count]]) 
		db since-2014 "DLC-042")
	;;=>
	{:item/count 100, 
			:db/id 17592186045418}

## queries

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/day_of_datomic/cloud/provenance.clj`

what is the entire history of entity e?

	(->> (d/q '[:find ?aname ?v ?tx ?inst ?added
							:in $ ?e
							:where
						👉[?e ?a ?v ?tx ?added]
							[?a 👈 :db/ident ?aname]
							[?tx :db/txInstant ?inst]]
				(d/history (d/db conn))
				story)
		seq
		(sort-by #(nth % 2)))

who changed the title, and when?

	(->> (d/q '[:find ?e ?v ?email ?inst ?added
							:in $ ?e
							:where
							[?e :story/title ?v ?tx ?added]
					 👉	[?tx :source/user ?user]
							[?tx :db/txInstant ?inst]
							[?user :user/email ?email]]
				(d/history (d/db conn))
				story)
		(sort-by #(nth % 3)))

## metadata queries

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/mbrainz/e01.clj`

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/tutorial_ground_up/e01_metadata.clj`

List all attributes

	(q '[:find ?n 
	     :where 
	  👉 [:db.part/db :db.install/attribute ?a] 
			 [?a :db/ident 👈 ?n]] 
			(db conn))
	;=>
	#_#{[:db/code]
			[:db.sys/reId]

Atributların kendi atributları:

	(q '[:find (pull ?a [*] 👈) 
	     :where 
			 [?a :db/ident :db.install/attribute]]  👈
			(db conn))
	;=>
	#_[[#:db{:id 13,
					:ident :db.install/attribute,
					:valueType #:db{:id 20},
					:cardinality #:db{:id 36},
					:doc "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will install v as an attribute."}]]

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datahike/datahike01a.clj`

list all eav tuples

	(dh/q '[:find ?e ?a ?v
					:where
					[?e ?a ?v]] 👈
		@db)
	; =>
	;#{[1 :name "Alice"]
	;  [3 :age 40]
	;  [1 :age 20]
	;  [2 :age 30]
	;  [536870913 :db/txInstant #inst"2022-04-17T11:47:26.322-00:00"] 👈


## find clause

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/tutorial_ground_up/e01_querying.clj`

	; return list of scalars
	(q '[:find ?n 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> #{["Lucy"] ["Jerry"]}

	; return single scalar value
	(q '[:find ?n . 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> "Lucy"

	; return list of tuples
	(q '[:find ?n ?a 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> #{["Jerry" 44] ["Lucy" 32]}

	; return list of scalars
	(q '[:find [?n ...] 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> ["Lucy" "Jerry"]

## entity api

	(-> (db conn) (d/entity 36 👈) (:db/ident 👈))
	#_:db.cardinality/many

## datom api

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datahike/e02_datoms.clj`

## tuples

rfr: `Tuples <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13537>`

Composite Tuples:

	{:db/ident :reg/semester+course+student
	 :db/valueType :db.type/tuple 👈
	 :db/tupleAttrs [:reg/course 👈:reg/semester 👈:reg/studen 👈]

	[{👉:reg/course [:course/id "BIO-101"]
		👉:reg/semester [:semester/year+season [2018 :fall]]
		👉:reg/student [:student/email "johndoe@university.edu"]}]

Heterogeneous Tuples:

	:db/tupleTypes [:db.type/long :db.type/long] 👈

	(def data [{:player/handle "Argent Adept"
							:player/location [100 0] 👈}])

## temporary ids

rfr: `Temporary ids <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13540>`

	[[:db/add "jdoe" :person/first "Jan"]
	[:db/add "jdoe" :person/last "Doe"]]

## transaction functions

rfr: `Transaction Functions <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13541>`

	[:db/retractEntity [:person/email "jdoe@example.com"]]]

cas = compare-and-swap. 4 argüman alır: eid, atribut, eski değer, yeni değer.

	[[:db/cas 42 :account/balance 100 110]]

## pattern inputs

	;; query
	'[:find (pull ?e pattern 👈)
		:in $ ?name 👉 pattern
		:where [?e :artist/name ?name]]

	;; args
	[db "The Beatles" 👉 [:artist/startYear :artist/endYear]]

## bindings

rfr: `Bindings <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13542>`

	| ?a           | scalar     |
	| [?a ?b]      | tuple      |
	| [?a …]       | collection |
	| [ [?a ?b ] ] | relation   |

Tuple:

	:in $ [?artist-name ?release-name]

	db, ["John Lennon" "Mind Games"]

Collection:

	:in $ [?artist-name ...]

	db, ["Paul McCartney" "George Harrison"]

Relation:

	:in $ [[?artist-name ?release-name]]

	db,  [["John Lennon" "Mind Games"] 
				["Paul McCartney" "Ram"]]

## find specifications

rfr: `Find Specifications <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13543>`

	| :find ?a . 👈    | single scalar | 
	| :find [?a ?b]    | single tuple  | 
	| :find [?a …👈 ]  | collection    | 
	| :find ?a ?b      | relation      | 

	[:find ?year .
	=>
	db "John Lennon"

	[:find [?year ?month ?day]
	=>
	[1940 10 9]

	[:find [?release-name ...]
	=>
	["Power to the People" 
	"Unfinished Music No. 2: Life With the Lions" 

	[:find ?artist-name ?release-name
	=>
	#{["George Jones" "With Love"] 
		["Shocking Blue" "Hello Darkness / Pickin' Tomatoes"] 

## return maps

rfr: `Return Maps <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13544>`

	| :keys   | keyword keys   |
	| :strs   | string keys    |
	| :syms   | symbol keys    |

	:find ?artist-name ?release-name
	:keys artist 👈 release
	=>
	#{{:artist 👈 "George Jones" :release "With Love"}

## not clause

Not Clauses <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13546>

	:where [?eid :artist/name]
				 (not 👈 [?eid :artist/country :country/CA])]

       (not-join 👈 [?artist] 👈 
         [?release :release/artists ?artist]
         [?release :release/year 1970])]

## or clause

rfr: Or Clauses <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13545>

	:where (or 👈 [?artist :artist/type :artist.type/group]
							(and [?artist :artist/type :artist.type/person]
									[?artist :artist/gender :artist.gender/female]))]

      (or-join 👈 [?release] 👈
        (and [?release :release/artists ?artist]
             [?artist :artist/country :country/CA])
        [?release :release/year 1970])]

## predicate expressions

Predicate Expressions <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13547>

 :where 
        [?artist :artist/startYear ?year]
        [(< 👈 ?year 1600)]]

## function expressions

Function Expressions <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13548>

  :find ?track-name ?minutes 👈
	:where 
					[?track :track/duration ?millis]
					[(quot 👈 ?millis 60000) ?minutes 👈]

## Built-in expressions

Built-in Expression Functions and Predicates <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13549>

	[(get-else 👈 $ ?artist :artist/startYear "N/A") ?year]]

	[(get-some 👈 $ ?e :country/name :artist/name) [?attr ?name]]]

  (missing? 👈 $ ?artist :artist/startYear)

	(fulltext 👈 $ :artist/name ?search)

	[:find ?tup
	 :in ?a ?b
	 :where [(tuple 👈 ?a ?b) ?tup]]
	;; inputs
	1 2
	;; result
	#{[[1 2]]}

	[:find [?tx ...]
	 :in ?log
	 :where [(tx-ids 👈 ?log 1000 1050) [?tx ...]]]
	;; inputs
	log
	;;=>
	[13194139534340 13194139534312 13194139534313 13194139534314]

	(d/q '[:find (count ?tx)
				:in $ ?log ?t1 ?t2
				:where [(tx-ids 👈 ?log ?t1 ?t2) [?tx ...]]]
			(d/db conn) (d/log conn) t1 t2)

## rules

Rules <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13550>

	[(twitter? ?c) ; 1 arg
	 [?c :community/type :community.type/twitter]]

	[(community-type 👉 ?c ?t 👈) ;; 2 arg
	 [?c :community/type ?t]]

	[(community-type [?c] 👈 ?t) ;; [] içi arg girdi, diğeri çıktı
	 [?c :community/type ?t]]

	:in $ % 👈 ?track-name  
	:where
		(track-name 👈 ?e ?track-name)

## aggregates

Aggregates <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13551>

	:find ?a (min ?b) 

`?a` için gruplar, `?b` için kümeler.

`:with` ile küme yerine bag oluşturarak gruplama:

	[:find (sum ?heads) .
	:with ?monster
	:in [[?monster ?heads]]]

	;; inputs
	[["Cerberus" 3]
	["Medusa" 1]
	;;=>
	4

## aggregates returning a single value

Aggregates Returning a Single Value <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13552>

	(min ?xs)
	(max ?xs)
	(count ?xs)
	(count-distinct ?xs)
	(sum ?xs)
	(avg ?xs)
	(median ?xs)
	(variance ?xs)
	(stddev ?xs)

## aggregates returning collections

Aggregates Returning Collections <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13553>

	(distinct ?xs)
	(min n ?xs)
	(max n ?xs)
	(rand n ?xs)
	(sample n ?xs)

## pull 02

Pull Expressions <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13555>

	:find (pull ?e [:release/name])
	=>
	[[{:release/name 👈 "Immigrant Song"}]

Pattern `:in` cümleciğinde tanımlanabilir:

	:find (pull ?e 👉 pattern)
	:in $ ?artist pattern 👈
	:where ...
	db, led-zeppelin, 👉 [:release/name]

Birden çok pull olabilir:

	:find (👉 pull ?e [:release/name]) (👉 pull ?a [*])

## entity identifier in V position

Resolving Entity Identifiers in V (value) Position <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13556>

	db, [:country/name "Belgium"] ;; lookup ref
	db, :country/BE               ;; ident
	db, 17592186045516            ;; entity id

## index

Indexes <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13557>

	(d/index-pull db {:index    :avet 👈
										:selector '[:artist/name :artist/startYear :artist/endYear]
										:start    [:artist/startYear]}))
	;;=>
	(#:artist{:name "Choir of King's College, Cambridge", :startYear 1441}
	#:artist{:name "Heinrich Schütz", :startYear 1585, :endYear 1672}

## pull 03

Pull API <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13558>

	(pull db '[*] led-zeppelin)
	(pull-many db '[*] [led-zeppelin jimi-hendrix janis-joplin])

## pull reverse lookup

Reverse Lookup <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13559>

Örnek: `:country/GB` ülkesine ait artistleri bulmak:

	[:artist/_country]
	;;=>
	{:artist/_country [{:db/id 17592186045751} {:db/id 17592186045755} ...]}

## pull map specifications

Map Specifications <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13560>

	[:track/name 👉 {:track/artists 👉 [:db/id :artist/name]}]
	;;=>
	{👉 :track/artists 👉 [{:db/id 17592186048186, :artist/name "Bob Dylan"}
									       {:db/id 17592186049854, :artist/name "George Harrison"}],
	:track/name "Ghost Riders in the Sky"}

## pull attribute specifications

Attribute Specifications <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13561>

	[:artist/name :as 👈 "Band Name"]
	;;=>
	{👉 "Band Name" "Led Zeppelin"}

	[:artist/name (:track/_artists :limit 10)]

	[{(:track/_artists :limit 2 👈) [:track/name]}]
	;;=>
	{:track/_artists  ;; bu alt mapin 2 üyesi bulunabilir
	 [{:track/name "Whole Lotta Love"}
	 	{:track/name "What Is and What Should Never Be"}]

	[:artist/name (:artist/endYear 👉:default 0)] 
	;;=>
	{:artist/endYear 0 👈, :artist/name "Paul McCartney"}

	[[:artist/endYear 👉 :xform 👉 str]] ;; str fonksiyonuyla çıktıyı transforme eder
	;;=>
	{:artist/endYear "1980"}

## pull wildcards

Wildcards <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13563>

	[*]   
	;;=>  özyinelemeli şekilde alt üyeleri çeker
	:release/media
	[{:db/id 17592186072004,
		:medium/tracks
		[{:db/id 17592186072005,
			:track/duration 376000,

	["*" {:track/artists [:artist/name]}]
	;;=>  ref öğelerin belirli atributlarını çeker
	:track/artists
	[{:artist/name "Bob Dylan"} {:artist/name "George Harrison"}]}

## recursion limits

	[:person/lastName {:person/friends 6 👈}] ;; 6 seviye iner
	[:person/lastName {:person/friends ... 👈}] ;; sınırsız seviye iner

## datoms api

Datoms API <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13564>

	db.datoms(AVET, ":account/balance");

## datomic clojure api

Datomic Clojure API <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13565>

	(as-of db t)
	(attribute db attrid)
	(basis-t db)
	(connect uri)
	(create-database uri)
	(datoms db index & components)
	(db connection)
	(db-stats db)
	(delete-database db)
	(endid db ident)
	(entity db eid)
	(entity-db entity)
	(filter db pred)
	(function m)
	(get-database-names uri)
	(history db)
	(ident db eid)
	(index-pull db arg-map)
	(invoke db eid-or-ident & args)
	(log connection)
	(next-t db)
	(part eid)
	(pull db pattern eid)
	(q query & inputs)
	(release conn)
	(squuid)
	(transact connection tx-data)
	(with db tx-data)

	(d/entity since-2014 (d/entid db [:item/id "DLC-042"]))
	;;=>
	{:item/count 100, 
			:db/id 17592186045418}

## setup and require

	(ns project-ns
		(:require [datomic.api :as d :refer [db q]]))

	(def uri "datomic:mem://test")
	(def conn (d/connect uri))
	(def results (q '[:find ?e :where [?e :db/doc]] (db conn)))

## best practices

Datomic Best Practices <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13567>

Use aliases:

	[:db/add :user/id 👉 :db/ident :user/primary-email 👈]

Annotate schema:

	{:db/ident :user
	:schema/see-instead 👈 :user2
	:db/doc "prefer the user2 namespace for new development"}

Add Facts About the Transaction Entity

	[:db/add "datomic.tx" 👈
	 :data/src "https://example.com/catalog-2_29_2012.xml"]

Pass multiple points-in-time to a single query

	[:find ?count .
	 :in $ $since ?id
	 :where [$ ?e :item/id ?id]
					[$since ?e :item/count ?count]]


