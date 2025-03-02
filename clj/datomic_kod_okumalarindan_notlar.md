
- # Datomic Kod Okumalarından Notlar  id=g13532
  id:: fb4641e7-4412-4eec-95d6-f397216b36a6

- [[f/ndx]]
	- external:
		- prn: [[datomic.otl]]
		- exmp: 
			- [[datomic_01b.clj]]
			- [[datalog02_ex_01.clj]]
		- extended notes: [[datomic_documentation_rtc.md]]
		- rfr: refcard || ((87b63076-64b3-46ab-8500-992c4a72f85a))
			- classified-otl: reference exmp - datomic || ((a45257ee-94fb-4bda-8fb4-f8e73c06c379))
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
	- pull Expressions || ((1bc12071-4435-4dd7-a2e3-a4cec43ed4f2))
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

```clj
	(d/transact)

	(d/transact conn {:tx-data 👈 [ 👉[:db/add eid :inv/count 100]]})

	[:db/add "datomic.tx" 👈 :db/doc "correct data entry"]  ;; fact about transaction

	[:db/add [:inv/sku "SKU-21"] 👈 <attribute> <value>] ;; lookup ref

	[:db/retract 👈 [:inv/sku "SKU-22"] :inv/count 7]

	(def datoms [{:db/id -1 :name "Bob" :age 30}
							{:db/id -2 :name "Sally" :age 15}])
	(d/transact! conn {:tx-data datoms})
```

rfr: Transaction structure || ((6d62456b-92a9-4d45-8858-83a3e4a7cc0e))

```clj
	[:db/add entity-id attribute value]

	[:db/retract entity-id attribute value]
	[:db/retract entity-id attribute]
```

- ## schema
  id:: 7aa798c0-c5ae-4c72-a92b-1b977c84e9ef

```clj
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

```clj
	(d/pull
		(d/db conn)
		[{:inv/color [:db/ident]}
		 {:inv/size [:db/ident]}
		 {:inv/type [:db/ident]}]
		[:inv/sku "SKU-42"] 👈)
	;;=> #:inv{:color #:db{:ident :blue}, :size #:db{:ident :large}, :type #:db{:ident :dress}}

	(pull-many db '[*] [led-zeppelin jimi-hendrix janis-joplin])


	;; Note: pull clause is a little different:
	;; rfr: pull Expressions || ((1bc12071-4435-4dd7-a2e3-a4cec43ed4f2))
	(d/q
		'[:find (pull ?product [*])
			:where
			[?product :product/id]]
		db)
```

- ## Nested entity map
  id:: 3fbce4c4-5096-4151-bc0c-a09a9289fd9f

```clj
	(def add-order
		{:order/items
		[👉{:item/id [:inv/sku "SKU-25"]
		    :item/count 10}
		   {:item/id [:inv/sku "SKU-26"]
		    :item/count 20}]})
```

rfr: fulcro tipi veritabanı || ((f284aba4-df07-46ec-9ba3-6d1bf3c205f4))

- ## Parameterized Query
  id:: 3bf21976-f0a5-4c9d-a610-84c35cce5244

rfr: exmp: ; Konu: Parametrik Sorgular (Parameterised Queries) || ((5f11d317-e9c1-47bc-9d37-700415cbbb56))

```clj
	(d/q
		'[:find ?sku
			:in $ ?inv 👈
			:where [?item :item/id ?inv]
		db 
		[:inv/sku "SKU-25"] 👈)
```

rfr: q: Bu tuple: hangi değişkene bağlanıyor? `?inv` değişkenine. `[:inv/sku "SKU-25"]` || ((2803ff84-04f9-405d-a337-6945821002a5))

- ## Rules
  id:: 2d960e66-110b-403f-b28e-36b9e362a5e7

rfr: exmp: Rules || ((ead6f2ed-2ff0-4c60-a66f-bc68adb7839f))

Kurallarla, çok sayıda join cümleciğini kapsülleyebiliriz.

Bu kuralları sorguda kullanmak için `:in` cümleciğinde `%` sembolü gibi bir placeholder kullanabiliriz:

```clj
	(def rules
		'[[(ordered-together ?inv ?other-inv)
			 [?item :item/id ?inv]
			 [?order :order/items ?item]
			 [?order :order/items ?other-item]
			 [?other-item :item/id ?other-inv]]])
```

```clj
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

rfr: Konu: Identity erişimi || ((7325ce6b-0eaf-4cd9-a3ce-cc8b25110f73))
rfr: exmp: [[:db/add [:inv/sku "SKU-21"] :inv/count 7 || ((8af428a5-5fbe-4ca3-a34f-dbc7f2ee64c0))

```clj
	[:inv/sku "SKU-21"]
```

```clj
	[:db/add [:inv/sku "SKU-21"] <attribute> <value>]
```

```clj
  [{:db/ident :inv/sku
    :db/valueType :db.type/string
  👉:db/unique :db.unique/identity 
    :db/cardinality :db.cardinality/one}
```

```clj
	(def stu [:user/email "stuarthalloway@datomic.com"] 👈)
	(def tx-result (d/transact conn
									{:tx-data
										[{:story/title "ElastiCache in 6 minutes"
											:story/url "http://blog.datomic.com/2012/09/elasticache-in-5-minutes.html"}
										 {:db/id "datomic.tx"
									    :source/user stu 👈}]}))
```

Mevcut varlıklara ref vermek için:

rfr: Lookup Refs  || ((bb299957-c1b6-45fa-acf8-556a891bdb67))

```clj
	{👉 :db/id [:person/email "joe@example.com"]
	 :person/loves :pizza}
```

- ## Connection
  id:: c8ae1746-e9df-4ca5-bd05-e51648a2c3b7

rfr: 04. Connect to Sample Database  || ((756d565b-c26d-4be7-8445-c06adb70dc3c))

rfr: exmp: Konu: Client API Tutorial || ((f480298c-023d-458c-8f47-4538b6cf0895))

Client API ile connection: 

```clj
	(def client (d/client {:server-type :dev-local
								   		 👉:storage-dir :mem
												 :system "ci"}))
	(def conn (d/connect client 
	          👉{:db-name "tutorial"}))
```

```clj
	(def client (d/client {:server-type :dev-local
											 👉:system "datomic-samples"}))
```

Bu durumda  `~/.datomic/dev-local.edn` içindeki `{:storage-dir "/Users/mertnuhoglu/db/"}` içinde veritabanları tutulur.

```clj
	(d/create-database 👈 client {:db-name "provenance"} 👈)
	(def conn (d/connect client {:db-name "provenance"}))
```

- ## as-of
  id:: db3aaea0-4884-407e-b09c-46dbb769c97d

rfr: exmp: [[datalog02_ex_03.clj]]

```clj
	(def tx-result (d/transact conn {:tx-data ...}))
	(def db-t 👉 (:t (:db-after tx-result))) ;=> 12
	;; what was the title as of earlier point in time?
	(d/pull (d/as-of db db-t 👈) '[:story/title] story)
```

```clj
	(def as-of-eoy-2013 (d/as-of db #inst "2014-01-01"))
	(d/entity as-of-eoy-2013 [:item/id "DLC-042"])
```

- ## history
  id:: fc2f4749-ab3f-4ef0-a426-34d4a0e0ec69

rfr: exmp: [[datalog02_ex_03.clj]]

query the history of the data

```clj
	(dh/q '[:find ?a
					:where
					[?e :name "Alice"]
					[?e :age ?a]]
		(dh/history @conn))
	;; => #{[20] [25]}
```

- ## queries
  id:: 17d06a30-6681-4de7-b05d-34d22fc5b3ee

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/day_of_datomic/cloud/provenance.clj`

what is the entire history of entity e?

```clj
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

```clj
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

```clj
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

```clj
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

```clj
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

rfr: exmp: [[e01_querying.clj]]

```clj
	; return list of scalars
	(q '[:find ?n 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> #{["Lucy"] ["Jerry"]}
```

```clj
	; return single scalar value
	(q '[:find ?n . 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> "Lucy"
```

```clj
	; return list of tuples
	(q '[:find ?n ?a 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> #{["Jerry" 44] ["Lucy" 32]}
```

```clj
	; return list of scalars
	(q '[:find [?n ...] 👈 :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
	;=> ["Lucy" "Jerry"]
```


- ## datom api
  id:: edee69a5-37a2-4921-909c-3639cc32549b

rfr: exmp: [[e02_datoms.clj]]
rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datahike/e02_datoms.clj`

- ## tuples
  id:: c8cc396e-4aeb-45e3-8a30-4dc44d9763ed

rfr: Tuples  || ((9a6169d2-6e66-41ca-a48e-076b9dc18ec7))

Composite Tuples:

```clj
	{:db/ident :reg/semester+course+student
	 :db/valueType :db.type/tuple 👈
	 :db/tupleAttrs [:reg/course 👈:reg/semester 👈:reg/studen 👈]
```

```clj
	[{👉:reg/course [:course/id "BIO-101"]
		👉:reg/semester [:semester/year+season [2018 :fall]]
		👉:reg/student [:student/email "johndoe@university.edu"]}]
```

Heterogeneous Tuples:

```clj
	:db/tupleTypes [:db.type/long :db.type/long] 👈
```

```clj
	(def data [{:player/handle "Argent Adept"
							:player/location [100 0] 👈}])
```

- ## temporary ids
  id:: 498065d9-d2cf-4883-b6d2-3a049bd9c33d

rfr: Temporary ids  || ((872db05e-de9f-4517-a5ce-a0653469e64a))

```clj
	[[:db/add "jdoe" :person/first "Jan"]
	 [:db/add "jdoe" :person/last "Doe"]]
```

- ## transaction functions
  id:: 542bbcd7-7a63-4fc0-9abd-0461627ae779

rfr: Transaction Functions  || ((c7c0f40b-c30a-4699-a9e5-2732e0ffaf1b))

```clj
	[:db/retractEntity [:person/email "jdoe@example.com"]]]
```

cas = compare-and-swap. 4 argüman alır: eid, atribut, eski değer, yeni değer.

```clj
	[[:db/cas 42 :account/balance 100 110]]
```

- ## pattern inputs
  id:: e787d2d9-1bb6-4af4-9bb7-b3138ccbf9ec

```clj
	;; query
	'[:find (pull ?e pattern 👈)
		:in $ ?name 👉 pattern
		:where [?e :artist/name ?name]]
```

```clj
	;; args
	[db "The Beatles" 👉 [:artist/startYear :artist/endYear]]
```

- ## bindings
  id:: 84e970c4-fa0c-4358-9a03-b8e1d80f1abb

rfr: Bindings  || ((3de017b1-1bf5-4db6-a972-a097b1e3d6ca))
rfr: ; Binding Forms || ((f9aab781-594d-4dd7-85bc-a3e7a69832d6))

| clause       | type       |
|--------------|------------|
| ?a           | scalar     |
| [?a ?b]      | tuple      |
| [?a …]       | collection |
| [ [?a ?b ] ] | relation   |

Tuple:

```clj
	:in $ [?artist-name ?release-name]
  ...
	db, ["John Lennon" "Mind Games"]
```

Collection:

```clj
	:in $ [?artist-name ...]
  ...
	db, ["Paul McCartney" "George Harrison"]
```

Relation:

```clj
	:in $ [[?artist-name ?release-name]]
  ...
	db,  [["John Lennon" "Mind Games"] 
				["Paul McCartney" "Ram"]]
```

- ## find specifications
  id:: 2be4a43b-936a-4dae-909c-af418983bc28

rfr: Find Specifications  || ((24874b63-7707-4220-857c-fb4dead840bf))

| clause          | type          |
|-----------------|---------------|
| :find ?a . 👈   | single scalar |
| :find [?a ?b]   | single tuple  |
| :find [?a …👈 ] | collection    |
| :find ?a ?b     | relation      |

```clj
	[:find ?year .
	=>
	db "John Lennon"
```

```clj
	[:find [?year ?month ?day]
	=>
	[1940 10 9]
```

```clj
	[:find [?release-name ...]
	=>
	["Power to the People" 
	"Unfinished Music No. 2: Life With the Lions" 
```

```clj
	[:find ?artist-name ?release-name
	=>
	#{["George Jones" "With Love"] 
		["Shocking Blue" "Hello Darkness / Pickin' Tomatoes"] 
```

- ## return maps
  id:: d7ba52a5-6b97-48b3-bcad-ccf418d371f2

rfr: Return Maps  || ((fcde5413-4a77-4785-9133-7cbc8314df81))

| clause | type         |
|--------|--------------|
| :keys  | keyword keys |
| :strs  | string keys  |
| :syms  | symbol keys  |

```clj
	:find ?artist-name ?release-name
	:keys artist 👈 release
	=>
	#{{:artist 👈 "George Jones" :release "With Love"}
```

- ## not clause
  id:: ecc81a84-e850-49fc-8a5c-4fe86a7e87c2

rfr: Not Clauses  || ((1faf7bac-f188-4e01-8da8-04763f076de9))

```clj
	:where [?eid :artist/name]
				 (not 👈 [?eid :artist/country :country/CA])]
```

```clj
       (not-join 👈 [?artist] 👈 
         [?release :release/artists ?artist]
         [?release :release/year 1970])]
```

- ## or clause
  id:: 7e114f0a-bf91-4377-8103-4065ad63fe1a

rfr: Or Clauses  || ((43e9c64f-05b2-4e58-b7ec-f4966ce4bf68))

```clj
	:where (or 👈 [?artist :artist/type :artist.type/group]
							(and [?artist :artist/type :artist.type/person]
									[?artist :artist/gender :artist.gender/female]))]
```

```clj
      (or-join 👈 [?release] 👈
        (and [?release :release/artists ?artist]
             [?artist :artist/country :country/CA])
        [?release :release/year 1970])]
```

- ## predicate expressions
  id:: e1779fa1-9e0e-4c81-8949-1a32dbdf528d

Predicate Expressions  || ((b23094c0-50a2-4f30-a54e-59ca8ae091a3))

```clj
 :where 
        [?artist :artist/startYear ?year]
        [(< 👈 ?year 1600)]]
```

- ## function expressions
  id:: 423cca22-9177-45e8-9c48-244ca671c688

Function Expressions  || ((720fadaa-5bb4-4dcf-9bb3-c24f4f5bd2ff))

```clj
  :find ?track-name ?minutes 👈
	:where 
					[?track :track/duration ?millis]
					[(quot 👈 ?millis 60000) ?minutes 👈]
```

- ## Built-in expressions
  id:: 182e3b23-486d-4a18-b2d6-80d803db5ba8

Built-in Expression Functions and Predicates  || ((0cc8801b-ddab-4ada-a4fe-49bf599382aa))

```clj
	[(get-else 👈 $ ?artist :artist/startYear "N/A") ?year]]
```

```clj
	[(get-some 👈 $ ?e :country/name :artist/name) [?attr ?name]]]
```

```clj
  (missing? 👈 $ ?artist :artist/startYear)
```

```clj
	(fulltext 👈 $ :artist/name ?search)
```

```clj
	[:find ?tup
	 :in ?a ?b
	 :where [(tuple 👈 ?a ?b) ?tup]]
	;; inputs
	1 2
	;; result
	#{[[1 2]]}
```

```clj
	[:find [?tx ...]
	 :in ?log
	 :where [(tx-ids 👈 ?log 1000 1050) [?tx ...]]]
	;; inputs
	log
	;;=>
	[13194139534340 13194139534312 13194139534313 13194139534314]
```

```clj
	(d/q '[:find (count ?tx)
				:in $ ?log ?t1 ?t2
				:where [(tx-ids 👈 ?log ?t1 ?t2) [?tx ...]]]
			(d/db conn) (d/log conn) t1 t2)
```

- ## rules
  id:: 2a9d3472-9df4-444d-9b61-ef538dc277cd

rfr: Rules  || ((299fb991-7a21-4e55-92f8-8615360d2495))

```clj
	[(twitter? ?c) ; 1 arg
	 [?c :community/type :community.type/twitter]]
```

```clj
	[(community-type 👉 ?c ?t 👈) ;; 2 arg
	 [?c :community/type ?t]]
```

```clj
	[(community-type [?c] 👈 ?t) ;; [] içi arg girdi, diğeri çıktı
	 [?c :community/type ?t]]
```

```clj
	:in $ % 👈 ?track-name  
	:where
		(track-name 👈 ?e ?track-name)
```

- ## aggregates
  id:: 4320184c-e3f7-4da9-a429-58b6cbb3c46a

rfr: Aggregates  || ((d8da4036-3be5-420e-9563-01bb4bdd68c1))

```clj
	:find ?a (min ?b) 
```

`?a` için gruplar, `?b` için kümeler.

`:with` ile küme yerine bag oluşturarak gruplama:

```clj
	[:find (sum ?heads) .
	:with ?monster
	:in [[?monster ?heads]]]
```

```clj
	;; inputs
	[["Cerberus" 3]
	["Medusa" 1]
	;;=>
	4
```

- ## aggregates returning a single value
  id:: f66ad660-253b-4e5f-b6e6-c019ef38fc48

rfr: Aggregates Returning a Single Value  || ((07bdcc9c-0b9a-4330-963a-2cf77e3d984a))

```clj
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

rfr: Aggregates Returning Collections  || ((1791fdbc-24ce-40b9-8693-932d006d392d))

```clj
	(distinct ?xs)
	(min n ?xs)
	(max n ?xs)
	(rand n ?xs)
	(sample n ?xs)
```

- ## pull Expressions
  id:: 1bc12071-4435-4dd7-a2e3-a4cec43ed4f2

rfr: Pull Expressions  || ((5aae8c1d-3259-478b-9ba9-7831fdfbdff2))

```clj
	:find (pull ?e [:release/name])
	=>
	[[{:release/name 👈 "Immigrant Song"}]
```

Pattern `:in` cümleciğinde tanımlanabilir:

```clj
	:find (pull ?e 👉 pattern)
	:in $ ?artist pattern 👈
	:where ...
	db, led-zeppelin, 👉 [:release/name]
```

Birden çok pull olabilir:

```clj
	:find (👉 pull ?e [:release/name]) (👉 pull ?a [*])
```

- ## entity identifier in V position
  id:: 80cd9b06-ecde-4db9-a33c-3aa33a04a7b0

rfr: Resolving Entity Identifiers in V (value) Position  || ((7ad9cfca-fad4-4e33-a95b-c9a34610eb1c))

```clj
	db, [:country/name "Belgium"] ;; lookup ref
	db, :country/BE               ;; ident
	db, 17592186045516            ;; entity id
```

- ## index
  id:: a69f952d-4896-41a4-a793-8cd48ac927fd

rfr: Indexes  || ((9d1c97a4-9a16-4854-9d07-d404dd067f94))

```clj
	(d/index-pull db {:index    :avet 👈
										:selector '[:artist/name :artist/startYear :artist/endYear]
										:start    [:artist/startYear]}))
	;;=>
	(#:artist{:name "Choir of King's College, Cambridge", :startYear 1441}
	#:artist{:name "Heinrich Schütz", :startYear 1585, :endYear 1672}
```

- ## pull API
  id:: 4cec4105-9f46-4905-900b-b69e0cc30a64

rfr: Pull API  || ((8726751a-8c25-40e1-8065-e31b281e190f))

```clj
	(pull db '[*] led-zeppelin)
	(pull-many db '[*] [led-zeppelin jimi-hendrix janis-joplin])
```

- ## pull reverse lookup
  id:: 2dd5b84b-a1fc-41d4-8d09-e2afcb51a5e0

rfr: Reverse Lookup || ((38a7eb11-1aed-4514-bc45-9eaa05fc660a))

Örnek: `:country/GB` ülkesine ait artistleri bulmak:

```clj
	[:artist/_country]
	;;=>
	{:artist/_country [{:db/id 17592186045751} {:db/id 17592186045755} ...]}
```

- ## pull map specifications
  id:: 8a7e38c6-8b2d-4ac7-aacd-5513ecdef695

rfr: Map Specifications  || ((88e2371a-3131-4901-b11b-8933f11a213a))

```clj
	[:track/name 👉 {:track/artists 👉 [:db/id :artist/name]}]
	;;=>
	{👉 :track/artists 👉 [{:db/id 17592186048186, :artist/name "Bob Dylan"}
									       {:db/id 17592186049854, :artist/name "George Harrison"}],
	:track/name "Ghost Riders in the Sky"}
```

- ## pull attribute specifications
  id:: f7cc8a4d-4563-4837-a4d0-5e3cd0d7d0c2

rfr: Attribute Specifications  || ((3fc3e595-79d6-42af-a2c1-c8eb84fb9514))

```clj
	[:artist/name :as 👈 "Band Name"]
	;;=>
	{👉 "Band Name" "Led Zeppelin"}
```

```clj
	[:artist/name (:track/_artists :limit 10)]
```

```clj
	[{(:track/_artists :limit 2 👈) [:track/name]}]
	;;=>
	{:track/_artists  ;; bu alt mapin 2 üyesi bulunabilir
	 [{:track/name "Whole Lotta Love"}
	 	{:track/name "What Is and What Should Never Be"}]
```

```clj
	[:artist/name (:artist/endYear 👉:default 0)] 
	;;=>
	{:artist/endYear 0 👈, :artist/name "Paul McCartney"}
```

```clj
	[[:artist/endYear 👉 :xform 👉 str]] ;; str fonksiyonuyla çıktıyı transforme eder
	;;=>
	{:artist/endYear "1980"}
```

- ## pull wildcards
  id:: d700152e-b06d-4300-abcb-0f7306edb77a

rfr: Wildcards  || ((67f58aa8-17a7-4c8f-9210-6ac43ef8bc1b))

```clj
	[*]   
	;;=>  özyinelemeli şekilde alt üyeleri çeker
	:release/media
	[{:db/id 17592186072004,
		:medium/tracks
		[{:db/id 17592186072005,
			:track/duration 376000,
```

```clj
	["*" {:track/artists [:artist/name]}]
	;;=>  ref öğelerin belirli atributlarını çeker
	:track/artists
	[{:artist/name "Bob Dylan"} {:artist/name "George Harrison"}]}
```

- ## recursion limits
  id:: 1c34e4ff-3722-406a-83c0-6fd4584109da

```clj
	[:person/lastName {:person/friends 6 👈}] ;; 6 seviye iner
	[:person/lastName {:person/friends ... 👈}] ;; sınırsız seviye iner
```

- ## datoms api
  id:: bdbe2f5f-a936-4a4f-aea5-04bfc768a00b

rfr: Datoms API  || ((fb19d7b6-ed2f-47ee-a218-9192431995c5))

```clj
	db.datoms(AVET, ":account/balance");
```

- ## datomic clojure api
  id:: e44b9c8b-5b49-47e6-b8b1-716a3f58537d

rfr: Datomic Clojure API  || ((ff814fd5-2083-44e9-b009-37eef251e137))

```clj
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

```clj
	(d/entity since-2014 (d/entid db [:item/id "DLC-042"]))
	;;=>
	{:item/count 100, 
			:db/id 17592186045418}
```

- ## setup and require
  id:: e6d7f089-2db7-45d6-8f96-209745036d7f

```clj
	(ns project-ns
		(:require [datomic.api :as d :refer [db q]]))
```

```clj
	(def uri "datomic:mem://test")
	(def conn (d/connect uri))
	(def results (q '[:find ?e :where [?e :db/doc]] (db conn)))
```

- ## best practices
  id:: 412a7a34-986a-4b45-b9ce-bf6e588357aa

rfr: Datomic Best Practices  || ((9d1f507e-c6a8-41f0-9465-969a0d84e818))

Use aliases:

```clj
	[:db/add :user/id 👉 :db/ident :user/primary-email 👈]
```

Annotate schema:

```clj
	{:db/ident :user
	:schema/see-instead 👈 :user2
	:db/doc "prefer the user2 namespace for new development"}
```

Add Facts About the Transaction Entity

```clj
	[:db/add "datomic.tx" 👈
	 :data/src "https://example.com/catalog-2_29_2012.xml"]
```

Pass multiple points-in-time to a single query

```clj
	[:find ?count .
	 :in $ $since ?id
	 :where [$ ?e :item/id ?id]
					[$since ?e :item/count ?count]]
```

- ## Deprecated
	- ### since
		id:: f9959d43-d2ec-4bfe-ac93-da502bd75bf2

		rfr: since  || ((080cc09c-1684-4b34-92ac-6bf4278069ee))

		```clj
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

	- ### entity api
		id:: 2891cc75-146a-4602-b8ff-421a3c0dbfcb

		```clj
			(-> (db conn) (d/entity 36 👈) (:db/ident 👈))
			#_:db.cardinality/many
		```

