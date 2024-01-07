
- # Datomic Kod Okumalarından Notlar  id=g13532
  id:: fb4641e7-4412-4eec-95d6-f397216b36a6

- [[f/ndx]]
	- rfr: /Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01b.clj
	- rfr: refcard || ((87b63076-64b3-46ab-8500-992c4a72f85a))
  - transact || ((91da1a06-1a44-4b25-8548-31ade1b98609))
  - transact || ((91da1a06-1a44-4b25-8548-31ade1b98609))
  - schema || ((7aa798c0-c5ae-4c72-a92b-1b977c84e9ef))
  - pull || ((579aa9e5-0027-4b65-a384-3b834c60b3fe))
  - Nested entity map || ((3fbce4c4-5096-4151-bc0c-a09a9289fd9f))
  - Parameterized Query || ((3bf21976-f0a5-4c9d-a610-84c35cce5244))
  - Rules || ((2d960e66-110b-403f-b28e-36b9e362a5e7))
  - Lookup ref || ((45e68a5c-19ca-4bc3-94a7-19b7717f1e27))
  - Connection || ((c8ae1746-e9df-4ca5-bd05-e51648a2c3b7))
  - as-of || ((db3aaea0-4884-407e-b09c-46dbb769c97d))
  - history || ((fc2f4749-ab3f-4ef0-a426-34d4a0e0ec69))
  - since || ((f9959d43-d2ec-4bfe-ac93-da502bd75bf2))
  - queries || ((17d06a30-6681-4de7-b05d-34d22fc5b3ee))
  - metadata queries || ((7fff3c68-ed85-47b0-855b-06939309b07d))
  - find clause || ((8dc2f28f-9f4d-42b2-a729-32808fa89b20))
  - entity api || ((2891cc75-146a-4602-b8ff-421a3c0dbfcb))
  - datom api || ((edee69a5-37a2-4921-909c-3639cc32549b))
  - tuples || ((c8cc396e-4aeb-45e3-8a30-4dc44d9763ed))
  - temporary ids || ((498065d9-d2cf-4883-b6d2-3a049bd9c33d))
  - transaction functions || ((542bbcd7-7a63-4fc0-9abd-0461627ae779))
  - pattern inputs || ((e787d2d9-1bb6-4af4-9bb7-b3138ccbf9ec))
  - bindings || ((84e970c4-fa0c-4358-9a03-b8e1d80f1abb))
  - find specifications || ((2be4a43b-936a-4dae-909c-af418983bc28))
  - return maps || ((d7ba52a5-6b97-48b3-bcad-ccf418d371f2))
  - not clause || ((ecc81a84-e850-49fc-8a5c-4fe86a7e87c2))
  - or clause || ((7e114f0a-bf91-4377-8103-4065ad63fe1a))
  - predicate expressions || ((e1779fa1-9e0e-4c81-8949-1a32dbdf528d))
  - function expressions || ((423cca22-9177-45e8-9c48-244ca671c688))
  - Built-in expressions || ((182e3b23-486d-4a18-b2d6-80d803db5ba8))
  - rules || ((2a9d3472-9df4-444d-9b61-ef538dc277cd))
  - aggregates || ((4320184c-e3f7-4da9-a429-58b6cbb3c46a))
  - aggregates returning a single value || ((f66ad660-253b-4e5f-b6e6-c019ef38fc48))
  - aggregates returning collections || ((86bd8c5c-663a-4e66-bad9-7c1153ddd57d))
  - pull 02 || ((1bc12071-4435-4dd7-a2e3-a4cec43ed4f2))
  - entity identifier in V position || ((80cd9b06-ecde-4db9-a33c-3aa33a04a7b0))
  - index || ((a69f952d-4896-41a4-a793-8cd48ac927fd))
  - pull 03 || ((4cec4105-9f46-4905-900b-b69e0cc30a64))
  - pull reverse lookup || ((2dd5b84b-a1fc-41d4-8d09-e2afcb51a5e0))
  - pull map specifications || ((8a7e38c6-8b2d-4ac7-aacd-5513ecdef695))
  - pull attribute specifications || ((f7cc8a4d-4563-4837-a4d0-5e3cd0d7d0c2))
  - pull wildcards || ((d700152e-b06d-4300-abcb-0f7306edb77a))
  - recursion limits || ((1c34e4ff-3722-406a-83c0-6fd4584109da))
  - datoms api || ((bdbe2f5f-a936-4a4f-aea5-04bfc768a00b))
  - datomic clojure api || ((e44b9c8b-5b49-47e6-b8b1-716a3f58537d))
  - setup and require || ((e6d7f089-2db7-45d6-8f96-209745036d7f))
  - best practices || ((412a7a34-986a-4b45-b9ce-bf6e588357aa))

# f/pnt

- ## transact
  id:: 91da1a06-1a44-4b25-8548-31ade1b98609

```
	(d/transact)

	(d/transact conn {:tx-data 👈 [ 👉[:db/add eid :inv/count 100]]})

	[:db/add "datomic.tx" 👈 :db/doc "correct data entry"]  ;; fact about transaction

	[:db/add [:inv/sku "SKU-21"] 👈 <attribute> <value>] ;; lookup ref

	[:db/retract 👈 [:inv/sku "SKU-22"] :inv/count 7]
```

rfr: Transaction structure || ((6d62456b-92a9-4d45-8858-83a3e4a7cc0e))

```
	[:db/add entity-id attribute value]

	[:db/retract entity-id attribute value]
	[:db/retract entity-id attribute]
```

- ## schema
  id:: 7aa798c0-c5ae-4c72-a92b-1b977c84e9ef

```
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
```

- ## pull
  id:: 579aa9e5-0027-4b65-a384-3b834c60b3fe

```
	(d/pull
		(d/db conn)
		[{:inv/color [:db/ident]}
		 {:inv/size [:db/ident]}
		 {:inv/type [:db/ident]}]
		[:inv/sku "SKU-42"] 👈)
	;;=> #:inv{:color #:db{:ident :blue}, :size #:db{:ident :large}, :type #:db{:ident :dress}}

	(pull-many db '[*] [led-zeppelin jimi-hendrix janis-joplin])
```

- ## Nested entity map
  id:: 3fbce4c4-5096-4151-bc0c-a09a9289fd9f

```
	(def add-order
		{:order/items
		[👉{:item/id [:inv/sku "SKU-25"]
		    :item/count 10}
		   {:item/id [:inv/sku "SKU-26"]
		    :item/count 20}]})
```

rfr: `fulcro tipi veritabanı <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/edn03.edn#r=g13092>`

- ## Parameterized Query
  id:: 3bf21976-f0a5-4c9d-a610-84c35cce5244

```
	(d/q
		'[:find ?sku
			:in $ ?inv 👈
			:where [?item :item/id ?inv]
		db 
		[:inv/sku "SKU-25"] 👈)
```

- ## Rules
  id:: 2d960e66-110b-403f-b28e-36b9e362a5e7

Kurallarla, çok sayıda join cümleciğini kapsülleyebiliriz.

Bu kuralları sorguda kullanmak için `:in` cümleciğinde `%` sembolünü kullanmalıyız:

```
	(def rules
		'[[(ordered-together ?inv ?other-inv)
			 [?item :item/id ?inv]
			 [?order :order/items ?item]
			 [?order :order/items ?other-item]
			 [?other-item :item/id ?other-inv]]])
```

```
	(d/q
		'[:find ?sku
	 👉	:in $ % ?inv
			:where
	 👉	(ordered-together ?inv ?other-inv)
			[?other-inv :inv/sku ?sku]]
	 👉db rules [:inv/sku "SKU-25"])
	;;=> [["SKU-25"] ["SKU-26"]]
```

- ## Lookup ref
  id:: 45e68a5c-19ca-4bc3-94a7-19b7717f1e27

```
	[:inv/sku "SKU-21"]
```

```
	[:db/add [:inv/sku "SKU-21"] <attribute> <value>]
```

```
  [{:db/ident :inv/sku
    :db/valueType :db.type/string
  👉:db/unique :db.unique/identity 
    :db/cardinality :db.cardinality/one}
```

```
	(def stu [:user/email "stuarthalloway@datomic.com"] 👈)
	(def tx-result (d/transact conn
									{:tx-data
										[{:story/title "ElastiCache in 6 minutes"
											:story/url "http://blog.datomic.com/2012/09/elasticache-in-5-minutes.html"}
										 {:db/id "datomic.tx"
									    :source/user stu 👈}]}))
```

Mevcut varlıklara ref vermek için:

rfr: `Lookup Refs <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13538>`

```
	{👉 :db/id [:person/email "joe@example.com"]
	 :person/loves :pizza}
```

- ## Connection
  id:: c8ae1746-e9df-4ca5-bd05-e51648a2c3b7

rfr: `Connect to Sample Database <url:file:///~/prj/study/clj/articles-datomic.md#r=g13507>`

```
	(def client (d/client {:server-type :dev-local
								   		 👉:storage-dir :mem
												 :system "ci"}))
	(def conn (d/connect client 
	          👉{:db-name "tutorial"}))
```

```
	(def client (d/client {:server-type :dev-local
											 👉:system "datomic-samples"}))
```

Bu durumda  `~/.datomic/dev-local.edn` içindeki `{:storage-dir "/Users/mertnuhoglu/db/"}` içinde veritabanları tutulur.

```
	(d/create-database 👈 client {:db-name "provenance"} 👈)
	(def conn (d/connect client {:db-name "provenance"}))
```

- ## as-of
  id:: db3aaea0-4884-407e-b09c-46dbb769c97d

```
	(def tx-result (d/transact conn {:tx-data ...}))
	(def db-t 👉 (:t (:db-after tx-result))) ;=> 12
	;; what was the title as of earlier point in time?
	(d/pull (d/as-of db db-t 👈) '[:story/title] story)
```

```
	(def as-of-eoy-2013 (d/as-of db #inst "2014-01-01"))
	(d/entity as-of-eoy-2013 [:item/id "DLC-042"])
```

- ## history
  id:: fc2f4749-ab3f-4ef0-a426-34d4a0e0ec69

query the history of the data

```
	(dh/q '[:find ?a
					:where
					[?e :name "Alice"]
					[?e :age ?a]]
		(dh/history @conn))
	;; => #{[20] [25]}
```

- ## since
  id:: f9959d43-d2ec-4bfe-ac93-da502bd75bf2

since <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13566>

```
	(d/q ('[:find ?count 
					:in $ $since ?id 
					:where [$ ?e :item/id ?id] 
								 [$since ?e :item/count ?count]]) 
		db since-2014 "DLC-042")
	;;=>
	{:item/count 100, 
			:db/id 17592186045418}
```

Note: $, $since, since-2014

- ## queries
  id:: 17d06a30-6681-4de7-b05d-34d22fc5b3ee

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/day_of_datomic/cloud/provenance.clj`

what is the entire history of entity e?

```
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
```

who changed the title, and when?

```
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
```

- ## metadata queries
  id:: 7fff3c68-ed85-47b0-855b-06939309b07d

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/mbrainz/e01.clj`

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/tutorial_ground_up/e01_metadata.clj`

List all attributes

```
	(q '[:find ?n 
	     :where 
	  👉 [:db.part/db :db.install/attribute ?a] 
			 [?a :db/ident 👈 ?n]] 
			(db conn))
	;=>
	#_#{[:db/code]
			[:db.sys/reId]
```

Atributların kendi atributları:

```
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
```

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datahike/datahike01a.clj`

list all eav tuples

```
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
```


- ## find clause
  id:: 8dc2f28f-9f4d-42b2-a729-32808fa89b20

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/tutorial_ground_up/e01_querying.clj`

```
	; return list of scalars
	(q '[:find ?n 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> #{["Lucy"] ["Jerry"]}
```

```
	; return single scalar value
	(q '[:find ?n . 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> "Lucy"
```

```
	; return list of tuples
	(q '[:find ?n ?a 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> #{["Jerry" 44] ["Lucy" 32]}
```

```
	; return list of scalars
	(q '[:find [?n ...] 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> ["Lucy" "Jerry"]
```

- ## entity api
  id:: 2891cc75-146a-4602-b8ff-421a3c0dbfcb

```
	(-> (db conn) (d/entity 36 👈) (:db/ident 👈))
	#_:db.cardinality/many
```

- ## datom api
  id:: edee69a5-37a2-4921-909c-3639cc32549b

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datahike/e02_datoms.clj`

- ## tuples
  id:: c8cc396e-4aeb-45e3-8a30-4dc44d9763ed

rfr: `Tuples <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13537>`

Composite Tuples:

```
	{:db/ident :reg/semester+course+student
	 :db/valueType :db.type/tuple 👈
	 :db/tupleAttrs [:reg/course 👈:reg/semester 👈:reg/studen 👈]
```

```
	[{👉:reg/course [:course/id "BIO-101"]
		👉:reg/semester [:semester/year+season [2018 :fall]]
		👉:reg/student [:student/email "johndoe@university.edu"]}]
```

Heterogeneous Tuples:

```
	:db/tupleTypes [:db.type/long :db.type/long] 👈
```

```
	(def data [{:player/handle "Argent Adept"
							:player/location [100 0] 👈}])
```

- ## temporary ids
  id:: 498065d9-d2cf-4883-b6d2-3a049bd9c33d

rfr: `Temporary ids <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13540>`

```
	[[:db/add "jdoe" :person/first "Jan"]
	 [:db/add "jdoe" :person/last "Doe"]]
```

- ## transaction functions
  id:: 542bbcd7-7a63-4fc0-9abd-0461627ae779

rfr: `Transaction Functions <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13541>`

```
	[:db/retractEntity [:person/email "jdoe@example.com"]]]
```

cas = compare-and-swap. 4 argüman alır: eid, atribut, eski değer, yeni değer.

```
	[[:db/cas 42 :account/balance 100 110]]
```

- ## pattern inputs
  id:: e787d2d9-1bb6-4af4-9bb7-b3138ccbf9ec

```
	;; query
	'[:find (pull ?e pattern 👈)
		:in $ ?name 👉 pattern
		:where [?e :artist/name ?name]]
```

```
	;; args
	[db "The Beatles" 👉 [:artist/startYear :artist/endYear]]
```

- ## bindings
  id:: 84e970c4-fa0c-4358-9a03-b8e1d80f1abb

rfr: `Bindings <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13542>`

| clause       | type       |
|--------------|------------|
| ?a           | scalar     |
| [?a ?b]      | tuple      |
| [?a …]       | collection |
| [ [?a ?b ] ] | relation   |

Tuple:

```
	:in $ [?artist-name ?release-name]
  ...
	db, ["John Lennon" "Mind Games"]
```

Collection:

```
	:in $ [?artist-name ...]
  ...
	db, ["Paul McCartney" "George Harrison"]
```

Relation:

```
	:in $ [[?artist-name ?release-name]]
  ...
	db,  [["John Lennon" "Mind Games"] 
				["Paul McCartney" "Ram"]]
```

- ## find specifications
  id:: 2be4a43b-936a-4dae-909c-af418983bc28

rfr: `Find Specifications <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13543>`

| clause          | type          |
|-----------------|---------------|
| :find ?a . 👈   | single scalar |
| :find [?a ?b]   | single tuple  |
| :find [?a …👈 ] | collection    |
| :find ?a ?b     | relation      |

```
	[:find ?year .
	=>
	db "John Lennon"
```

```
	[:find [?year ?month ?day]
	=>
	[1940 10 9]
```

```
	[:find [?release-name ...]
	=>
	["Power to the People" 
	"Unfinished Music No. 2: Life With the Lions" 
```

```
	[:find ?artist-name ?release-name
	=>
	#{["George Jones" "With Love"] 
		["Shocking Blue" "Hello Darkness / Pickin' Tomatoes"] 
```

- ## return maps
  id:: d7ba52a5-6b97-48b3-bcad-ccf418d371f2

rfr: `Return Maps <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13544>`

| clause | type         |
|--------|--------------|
| :keys  | keyword keys |
| :strs  | string keys  |
| :syms  | symbol keys  |

```
	:find ?artist-name ?release-name
	:keys artist 👈 release
	=>
	#{{:artist 👈 "George Jones" :release "With Love"}
```

- ## not clause
  id:: ecc81a84-e850-49fc-8a5c-4fe86a7e87c2

Not Clauses <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13546>

```
	:where [?eid :artist/name]
				 (not 👈 [?eid :artist/country :country/CA])]
```

```
       (not-join 👈 [?artist] 👈 
         [?release :release/artists ?artist]
         [?release :release/year 1970])]
```

- ## or clause
  id:: 7e114f0a-bf91-4377-8103-4065ad63fe1a

rfr: Or Clauses <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13545>

```
	:where (or 👈 [?artist :artist/type :artist.type/group]
							(and [?artist :artist/type :artist.type/person]
									[?artist :artist/gender :artist.gender/female]))]
```

```
      (or-join 👈 [?release] 👈
        (and [?release :release/artists ?artist]
             [?artist :artist/country :country/CA])
        [?release :release/year 1970])]
```

- ## predicate expressions
  id:: e1779fa1-9e0e-4c81-8949-1a32dbdf528d

Predicate Expressions <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13547>

```
 :where 
        [?artist :artist/startYear ?year]
        [(< 👈 ?year 1600)]]
```

- ## function expressions
  id:: 423cca22-9177-45e8-9c48-244ca671c688

Function Expressions <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13548>

```
  :find ?track-name ?minutes 👈
	:where 
					[?track :track/duration ?millis]
					[(quot 👈 ?millis 60000) ?minutes 👈]
```

- ## Built-in expressions
  id:: 182e3b23-486d-4a18-b2d6-80d803db5ba8

Built-in Expression Functions and Predicates <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13549>

```
	[(get-else 👈 $ ?artist :artist/startYear "N/A") ?year]]
```

```
	[(get-some 👈 $ ?e :country/name :artist/name) [?attr ?name]]]
```

```
  (missing? 👈 $ ?artist :artist/startYear)
```

```
	(fulltext 👈 $ :artist/name ?search)
```

```
	[:find ?tup
	 :in ?a ?b
	 :where [(tuple 👈 ?a ?b) ?tup]]
	;; inputs
	1 2
	;; result
	#{[[1 2]]}
```

```
	[:find [?tx ...]
	 :in ?log
	 :where [(tx-ids 👈 ?log 1000 1050) [?tx ...]]]
	;; inputs
	log
	;;=>
	[13194139534340 13194139534312 13194139534313 13194139534314]
```

```
	(d/q '[:find (count ?tx)
				:in $ ?log ?t1 ?t2
				:where [(tx-ids 👈 ?log ?t1 ?t2) [?tx ...]]]
			(d/db conn) (d/log conn) t1 t2)
```

- ## rules
  id:: 2a9d3472-9df4-444d-9b61-ef538dc277cd

Rules <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13550>

```
	[(twitter? ?c) ; 1 arg
	 [?c :community/type :community.type/twitter]]
```

```
	[(community-type 👉 ?c ?t 👈) ;; 2 arg
	 [?c :community/type ?t]]
```

```
	[(community-type [?c] 👈 ?t) ;; [] içi arg girdi, diğeri çıktı
	 [?c :community/type ?t]]
```

```
	:in $ % 👈 ?track-name  
	:where
		(track-name 👈 ?e ?track-name)
```

- ## aggregates
  id:: 4320184c-e3f7-4da9-a429-58b6cbb3c46a

Aggregates <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13551>

```
	:find ?a (min ?b) 
```

`?a` için gruplar, `?b` için kümeler.

`:with` ile küme yerine bag oluşturarak gruplama:

```
	[:find (sum ?heads) .
	:with ?monster
	:in [[?monster ?heads]]]
```

```
	;; inputs
	[["Cerberus" 3]
	["Medusa" 1]
	;;=>
	4
```

- ## aggregates returning a single value
  id:: f66ad660-253b-4e5f-b6e6-c019ef38fc48

Aggregates Returning a Single Value <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13552>

```
	(min ?xs)
	(max ?xs)
	(count ?xs)
	(count-distinct ?xs)
	(sum ?xs)
	(avg ?xs)
	(median ?xs)
	(variance ?xs)
	(stddev ?xs)
```

- ## aggregates returning collections
  id:: 86bd8c5c-663a-4e66-bad9-7c1153ddd57d

Aggregates Returning Collections <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13553>

```
	(distinct ?xs)
	(min n ?xs)
	(max n ?xs)
	(rand n ?xs)
	(sample n ?xs)
```

- ## pull 02
  id:: 1bc12071-4435-4dd7-a2e3-a4cec43ed4f2

Pull Expressions <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13555>

```
	:find (pull ?e [:release/name])
	=>
	[[{:release/name 👈 "Immigrant Song"}]
```

Pattern `:in` cümleciğinde tanımlanabilir:

```
	:find (pull ?e 👉 pattern)
	:in $ ?artist pattern 👈
	:where ...
	db, led-zeppelin, 👉 [:release/name]
```

Birden çok pull olabilir:

```
	:find (👉 pull ?e [:release/name]) (👉 pull ?a [*])
```

- ## entity identifier in V position
  id:: 80cd9b06-ecde-4db9-a33c-3aa33a04a7b0

Resolving Entity Identifiers in V (value) Position <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13556>

```
	db, [:country/name "Belgium"] ;; lookup ref
	db, :country/BE               ;; ident
	db, 17592186045516            ;; entity id
```

- ## index
  id:: a69f952d-4896-41a4-a793-8cd48ac927fd

Indexes <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13557>

```
	(d/index-pull db {:index    :avet 👈
										:selector '[:artist/name :artist/startYear :artist/endYear]
										:start    [:artist/startYear]}))
	;;=>
	(#:artist{:name "Choir of King's College, Cambridge", :startYear 1441}
	#:artist{:name "Heinrich Schütz", :startYear 1585, :endYear 1672}
```

- ## pull 03
  id:: 4cec4105-9f46-4905-900b-b69e0cc30a64

Pull API <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13558>

```
	(pull db '[*] led-zeppelin)
	(pull-many db '[*] [led-zeppelin jimi-hendrix janis-joplin])
```

- ## pull reverse lookup
  id:: 2dd5b84b-a1fc-41d4-8d09-e2afcb51a5e0

Reverse Lookup <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13559>

Örnek: `:country/GB` ülkesine ait artistleri bulmak:

```
	[:artist/_country]
	;;=>
	{:artist/_country [{:db/id 17592186045751} {:db/id 17592186045755} ...]}
```

- ## pull map specifications
  id:: 8a7e38c6-8b2d-4ac7-aacd-5513ecdef695

Map Specifications <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13560>

```
	[:track/name 👉 {:track/artists 👉 [:db/id :artist/name]}]
	;;=>
	{👉 :track/artists 👉 [{:db/id 17592186048186, :artist/name "Bob Dylan"}
									       {:db/id 17592186049854, :artist/name "George Harrison"}],
	:track/name "Ghost Riders in the Sky"}
```

- ## pull attribute specifications
  id:: f7cc8a4d-4563-4837-a4d0-5e3cd0d7d0c2

Attribute Specifications <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13561>

```
	[:artist/name :as 👈 "Band Name"]
	;;=>
	{👉 "Band Name" "Led Zeppelin"}
```

```
	[:artist/name (:track/_artists :limit 10)]
```

```
	[{(:track/_artists :limit 2 👈) [:track/name]}]
	;;=>
	{:track/_artists  ;; bu alt mapin 2 üyesi bulunabilir
	 [{:track/name "Whole Lotta Love"}
	 	{:track/name "What Is and What Should Never Be"}]
```

```
	[:artist/name (:artist/endYear 👉:default 0)] 
	;;=>
	{:artist/endYear 0 👈, :artist/name "Paul McCartney"}
```

```
	[[:artist/endYear 👉 :xform 👉 str]] ;; str fonksiyonuyla çıktıyı transforme eder
	;;=>
	{:artist/endYear "1980"}
```

- ## pull wildcards
  id:: d700152e-b06d-4300-abcb-0f7306edb77a

Wildcards <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13563>

```
	[*]   
	;;=>  özyinelemeli şekilde alt üyeleri çeker
	:release/media
	[{:db/id 17592186072004,
		:medium/tracks
		[{:db/id 17592186072005,
			:track/duration 376000,
```

```
	["*" {:track/artists [:artist/name]}]
	;;=>  ref öğelerin belirli atributlarını çeker
	:track/artists
	[{:artist/name "Bob Dylan"} {:artist/name "George Harrison"}]}
```

- ## recursion limits
  id:: 1c34e4ff-3722-406a-83c0-6fd4584109da

```
	[:person/lastName {:person/friends 6 👈}] ;; 6 seviye iner
	[:person/lastName {:person/friends ... 👈}] ;; sınırsız seviye iner
```

- ## datoms api
  id:: bdbe2f5f-a936-4a4f-aea5-04bfc768a00b

Datoms API <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13564>

```
	db.datoms(AVET, ":account/balance");
```

- ## datomic clojure api
  id:: e44b9c8b-5b49-47e6-b8b1-716a3f58537d

Datomic Clojure API <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13565>

```
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
```

```
	(d/entity since-2014 (d/entid db [:item/id "DLC-042"]))
	;;=>
	{:item/count 100, 
			:db/id 17592186045418}
```

- ## setup and require
  id:: e6d7f089-2db7-45d6-8f96-209745036d7f

```
	(ns project-ns
		(:require [datomic.api :as d :refer [db q]]))
```

```
	(def uri "datomic:mem://test")
	(def conn (d/connect uri))
	(def results (q '[:find ?e :where [?e :db/doc]] (db conn)))
```

- ## best practices
  id:: 412a7a34-986a-4b45-b9ce-bf6e588357aa

Datomic Best Practices <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13567>

Use aliases:

```
	[:db/add :user/id 👉 :db/ident :user/primary-email 👈]
```

Annotate schema:

```
	{:db/ident :user
	:schema/see-instead 👈 :user2
	:db/doc "prefer the user2 namespace for new development"}
```

Add Facts About the Transaction Entity

```
	[:db/add "datomic.tx" 👈
	 :data/src "https://example.com/catalog-2_29_2012.xml"]
```

Pass multiple points-in-time to a single query

```
	[:find ?count .
	 :in $ $since ?id
	 :where [$ ?e :item/id ?id]
					[$since ?e :item/count ?count]]
```


