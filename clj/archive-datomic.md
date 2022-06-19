---
title: "Study datomic"
date: 2019-11-01T14:35:49+03:00 
draft: false
description: ""
tags:
categories: datomic, datalog
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:
path: ~/projects/study/clj/datomic.md
state: wip

---

# nxt

ref: `nxt - datomic <url:file:///~/projects/myrepo/work/work.otl#r=g12787>`

# Datomic Tutorial: Getting Started id=g12785

[Connect to a Database | Datomic](https://docs.datomic.com/on-prem/getting-started/connect-to-a-database.html)

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic_01.clj`

# Articles

## Setup Datomic

https://docs.datomic.com/on-prem/get-datomic.html

```clj
VERSION=1.0.6362
cd /Users/mertnuhoglu/codes/clj/lib/datomic-pro-${VERSION}
``` 

## Connect to a Database

Veritabanı iki türde çalışıyor:

1. Peer Server: Geçici
2. Kalıcı

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic_01.clj`

Launch Peer Server

```clj
VERSION=1.0.6362
cd /Users/mertnuhoglu/codes/clj/lib/datomic-pro-${VERSION}
bin/run -m datomic.peer-server -h localhost -p 8998 -a myaccesskey,mysecret -d hello,datomic:mem://hello
``` 

Veritabanı sunucusunu çalıştırdıktan sonra, client kütüphanesini projeye eklemek gerekiyor:

Edit `/Users/mertnuhoglu/projects/study/clj/ex/study_datomic/datalog-01/deps.edn`

```clj
{com.datomic/client-pro {:mvn/version "1.0.72"}}
``` 

## Tutorial

### Introduction

#### Information, not CRUD id=g12786

Information is not forgotten as a side effect of acquiring new information.

This is in stark contrast with CRUD paradigm

		| CRUD   | Datomic    |
		| Create | Assert     |
		| Read   | Read       |
		| Update | Accumulate |
		| Delete | Retract    |

Assert/Read/Accumulate/Retract: ARAR

- Assertions: statements of fact
- Read: performed against a database value at a particular time
- Accumulate: new transactions accumulate new data
- Retraction: states that an assertion no longer holds at some later time

### Iterative Development

No need for a parallel way of version-controlled migration files.

Any change is tracked within the database itself. 

### Assertion

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic_02_assertion.clj`

#### List and Map Forms

```clj
[:db/add "foo" :db/ident :green]
``` 

- assertion: `:db/add`
- temporary entity id: `"foo"`
- attribute for identifiers: `:db/ident`
- datom's value: `:green`

This is equivalent to:

```clj
{:db/ident :green}
``` 

Maps imply and are equivalent to a set of assertions, all about the same entity.

```clj
(d/transact
	conn 
	{:tx-data [{:db/ident :red}
						 {:db/ident :green}
						 {:db/ident :blue}
						 {:db/ident :yellow}]})
=> ;; returns a big map
``` 

This transaction adds four colors to the database.

### Read

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic_03_read.clj`

#### Database Values

`db` API returns the latest database value from a connection:

```clj
(def db (d/db conn))
``` 

Analogy: a connection references the entire history, similar to source code repository. A database value from db is similar to checkout.

#### Pull

Lookup ref: a two element list of unique attribute + value

```clj
[:inv/sku "SKU-42"]
``` 

```clj
(d/pull db
        [{:inv/color [:db/ident]}
         {:inv/size [:db/ident]}
         {:inv/type [:db/ident]}]
        [:inv/sku "SKU-42"])
=> #:inv{:color #:db{:ident :blue}, 
         :size #:db{:ident :large}, 
         :type #:db{:ident :dress}}
``` 

#### Query

Find skus of all products that share a color with SKU-42:

```clj
(d/q '[:find ?sku
       :where [?e :inv/sku "SKU-42"]
              [?e :inv/color ?color]
              [?e2 :inv/color ?color]
              [?e2 :inv/sku ?sku]]
     db)
=> [["SKU-42"] 
    ["SKU-32"] 
    ...]
``` 

`?..` are datalog variables. When the symbol is repeated, it causes a join.

In this example:

- `?e` joins `SKU-42` to its color
- `?e2` joins all entities sharing the color
- `?sku` joins all `?e2` entities to their skus

### Accumulate

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic_04_accumulate.clj`

## Article: The Architecture of Datomic, Rich Hickey

https://www.infoq.com/articles/Architecture-Datomic/

### Motivations

A database system has a point of view about the data model.

RDBMS support relational model and world-update semantic.

NoSQL knows nothing of the information it contains. 

Datomic considers a database to be an information system. 

Information is a set of facts. 

Facts are things that have happened.

Facts cannot be updated in place.

Amazon Dynamo: A highly available, redundant, scalable storage system. Datomic seeks to support these systems directly.

RDBMS provides powerful language for manipulating data. But its power is trapped in servers. Once the data reaches the application, we need to use a lot of for loops and imperative manipulation. 

Datomic provides fundamentally a distributed index. It allows declarative query to work in the application server tier.

### Overarching Concerns

- Data as interface

The system is programmable. Therefore, the interfaces are data-driven, not syntax based. 

Schema, transactions, queries, and query results are all defined terms of ordinary data structures such as lists and maps.

### Logical Model 

Services:

- Storage
- Transaction
- Indexing
- Application data model and query
- Caching

### Physical Model

- Storage services

Supported storages are: 

- in-process memory
- SQL
- Key/value such as DynamoDB
- Memory grid

Storage is taken as a service. This brings a lot of flexibility.

- The Transactor

- The Peer Library

It runs in the datomic client application.

Programming at the peer level is different from client-server database programming. The database feels like an object in memory. 

Queries run within the context of a database. Databases become arguments to queries.

Datalog is the query engine. 

- memcache

### The REST API and clients

## Article: The Datomic Information Model by Rich Hickey

https://www.infoq.com/articles/Datomic-Information-Model/

### Motivations

Goals of datomic

- No update-in-place
- Scalable storage
- ACID transactions
- Declarative data programming

Datomic considers a database to be an information system. Information is a set of facts. Facts are things that have happened.

We cannot change the past. Thus database should accumulate facts. There cannot be any updates. 

Immutability leads to important benefits.

Existing database focus on "now". "Now" is the set of facts that are currently true. 

They lose historical information. Historical information can support decision making.

A database provides leverage over the data. Otherwise, it is just a storage system.

The leverage comes from organizing the data (via indexes) and query systems.

### Structure and Representation

The fundamental unit is atomic fact, or datom.

It has the following parts:

- Entity
- Attribute
- Value
- Transaction (database time)
- Add/retract

This model is similar to RDF Subject/Predicate/Object data model. But this model has also temporal notion and retraction notion.

Datomic adopts closed-world assumption.

Atomic unit ensures that novelty representations are only as big as the new facts themselves. Contrast this with document updates or delta schemes.

Datoms constitute a single, flat, universal relation. There is no other structural component in Datomic.

Contrast this to relational databases. They have relations, fields, tables, join tables. 

### Schemas

Attributes are entities themselves with the following attributes:

- name
- data type of values
- cardinality
- uniqueness
- indexing properties
- component nature (whole part)
- documentation

```clj
{:db/ident       :person/name,
 :db/valueType   :db.type/string,
 :db/cardinality :db.cardinality/one,
 :db/doc         "A person's name"}
``` 

Schema is represented by data. This is a map in edn format

### Transactions

A transaction is a list of datoms:

```clj
[[:db/add entity-id attribute value]
 [:db/add entity-id attribute value]...]
``` 

All data manipulation is represented by data. 

Each vector representing a datom in:

```clj
[op entity attribute value]
``` 

To submit several facts about the same entity, you can use a map instead:

```clj
[{:db/id entity-id,
  attribute value,
	attribute value}
	...]
``` 

Transactions are ordinary data structures (java.util.List, java.util.Map, arrays etc). 

The primary interface to datomic is data, not strings.

Note that, you don't specify transaction part of the datoms. It will be filled in by the transactor.

A transaction can assert facts about the transaction itself, such as metada. 

#### database functions

Example:

```clj
[[:db/add entity-id attribute value]
 [:my/giveRaise sally-id 100]
 ...]
``` 

Database functions are installed into the database. Once installed, it can be part of a transaction. 

Onca it is part of a transaction, it is called a transaction function. It gets passed a first argument which is the in-transaction value of the database itself. 

Transaction function returns simple facts. This fact replaces the transaction function then. Thus transaction becomes like:

```clj
[[:db/add entity-id attribute value]
 [:db/add sally-id :employee/salary 45100]
 ...]
``` 

### Connections and Database Values

Write side:

You have a connection. Connection connects you to the current transactor.

Calling transact function on the connection issues transactions.

Read side:

In a traditional database, reading is also a function of the connection. 

In Datomic, there is only one read operation of connection: `db()`

The connection is fed information. It can deliver the value of the database as an immutable object.

Querying happens locally.

### Query

Query is a stand-alone function that takes database as argument. 

Thus datomic frees query from running within the context of a database.

Peer library comes with a query engine based upon datalog.

Datalog is a declarative query language with pattern-matching.

The basic form of query is:

```clj
{:find [variables...] :where [clauses...]}
[:find variables... :where clauses...]
``` 

Ex:

```clj
[[sally :age 21]
 [fred :age 42]
 [ethel :age 42]
 [fred :likes pizza]
 [sally :likes opera]
 [ethel :likes sushi]]
``` 

```clj
[:find ?e :where [?e :age 42]]
  ##> [[fred], [ethel]]
``` 

`?variable` are avariables. 

Joins are implicit and occur whenever you use a variable more than once:

```clj
[:find ?e ?x
 :where [?e :age 42]
        [?e :likes ?x]
  ##> [[fred pizza], [ethel sushi]]
``` 

API for query is a function `q`

```clj
Peer.q(query, inputs...)
``` 

`inputs` can be database, collection etc.

```clj
//connect
Connection conn = Peer.connect("a-db-URI");
//grab the current value of the database
Database db = conn.db();
//a string for now, because Java doesn't have collection literals
String query = "[:find ?e :where [?e :likes pizza]]";
//who likes pizza?
Collection result = Peer.q(query, db);
``` 

### Same query, different basis

`db` has all the historical information:

Ex:

```clj
//who liked pizza last week?
Peer.q(query, db.asOf(lastTuesday));
``` 

Note that, we didn't use the connection and we didn't change the `query`. 

Ex: 

We can add a new collection and rerun a query:

```clj
Peer.q(query, db.with(everyoneFromBrookly))
``` 

So, you can do speculative, what-if queries.

Ex:

We can test a query without any database too:

```clj
Peer.q(query, aCollection)
``` 

Ex: Query historical data

```clj
// who has ever liked pizza?
Peer.q(query, db.history())
``` 

### Different queries

# Article: JUXT Blog - Datalog for trees in Clojure id=g12784

[JUXT Blog - Datalog for trees in Clojure](https://www.juxt.pro/blog/datascript-dom)

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datalog_01.clj`

# Video: Day of Datomic Cloud - Session 1 id=g12788

[(1137) Day of Datomic Cloud - Session 1 - YouTube](https://www.youtube.com/watch?v=yWdfhQ4_Yfw&list=PLjyLzdfdsKwqF9I1XSX_Y4TXAo8pYXbOv)

## Day 2 - Information Model

- notation
- datoms
- databases
- entities

### otl notes

[(1137) Day of Datomic Cloud - Session 1 - YouTube](https://www.youtube.com/watch?v=yWdfhQ4_Yfw&list=PLjyLzdfdsKwqF9I1XSX_Y4TXAo8pYXbOv)

	slide: agenda
		notation
		datoms
		databases
		entities
			things in the world
			how to get from datoms to entities (aggregates)
		schema
			how to model in datomic
	slide: notation: edn
		similar to json
		differences
		symbolic types:
			2 types: symbol, keyword
		4 types of aggregates
			list: sequentially
			vector: both sequential and random access
			map: associative
				everything composes or nests
				use maps to represent entities in world
			set: membership
	slide: generic extensibility
		#name edn-form
		interpret form in the context of this name
		built-in tags
			#inst
			#uuid
	slide: datoms
		granular atomic fact
		granular: minimal
		immutable
		datoms never go away
		5-tuple
			entity attribute value transaction op
		entity: identifier
		attribute: name
		value
		transaction: reference to transaction that adds this information
			reified transactions
			git has reified transactions: commits
			it has data about it:
				reference to other commits, 
				commit message
				tags
		op: adding or removing
	example datoms
		jane likes broccoli 1008 true
	entity ids
		you don't store jane as a name
		we don't store attribute names neither
			we store them in one place
			they are stored as opaque identifier
		1001 63 jane 1008 true
		1002 63 broccoli 1008 true
	database
		this is a universal relation
		by contrast: in sql you use sqecific relation
		first you need to create table (specific relation)
		you have slots in table
		some information doesn't match this slot
		naive triple store implementation: rdf
			we have to have universal names
		many sort orders
		accumulate only
	powerful
		universal relation supports many access styles
			row
			column, key-value, document, and graph
		universal relation lets you model all these styles directly
	one database, many indexes
		| structure | attribute |
		| k/v | AVET |
		| row | EAVT |
		| column | AEVT |
		| document | EAVT, components|
		| graph | VAET |
		document: 
			subtle
			Entity, calling
			component: 
				order line items
				line item is part of order
				it doesn't exist outside
				it is a component
		graph:
			values
			work backwards to who has the value
			start with a value
			i have some friend, is that a friend of stu?
		granular approach:
			hangi yaklaşıma ihtiyacın varsa, ona göre veritabanına erişmene izin verir
	slide: time aware
		| db view | semantics | supports |
		| (default) | current state | what is current situation? |
		| as-of | state at point in past | how were things in the past? |
		| since | state since point in past | how have things changed? |
		| tx report | before/after/change view of tx | automated event response |
		| with | state with proposed additions | what would happen if we did X? |
		| history | timeless view of all history | anything |
	slide: entities
		do we really model everything in real world?
			never.
		when do we decide what do we care about in relational database?
			when we make tables.
		in datomic
			we care about when we care about
		tables
			pro: this row must be completely filled
			pro: impose structural rules
			we are not into that
			in datomic you can do validation 
				if this entity has 7 fields filled, that is valid
			con: brittleness of database
			con: joining because information is in another table
				complexity
		how to get from datoms to entities?
	slide: tx entity map
		ex:
			{:name "jane"
				:likes [[:name "broccoli"]
								[:name ":name pizza"]]}
		eav view
			| e | a | v | opt |
			| 1001 | 64 | 1002 | true |
			| 1001 | 64 | 1003 | true |
		if values are themselves entities, drill down 
		transformation: there is only one way to do it
		datoms:
			all attributes become keys
			values are values, if they are entities recurse one more level
	slide: schema
		schema adds power
		schema is plain data
		schema installed via transactions
		sth is schemaless
			means: schema is implicitly in your program
		but: schema means slots for most people
			we don't want this
		in-between:
			schema for only attributes
		ex: there is this attribute: "color"
		pro
			allows indexes
			navigate around systems
			without committing to slots/rectangles
	slide: common schema attributes
		| attribute | type | use |
		| db/ident | keyword | programmatic name |
		| db/valueType | ref | attribute type |
		| db/cardinality | ref | one- or many- valued? |
		| db/unique | ref | unique, "upsert" |
		| db/isComponent | ref | ownership |
		component: the thing is owned
			order has line item
	slide: social news example
		stories
		| attribute | type | cardinality |
		| story/title | string | 1 |
		| story/url | string | 1 |
		| story/slug | string | 1|
		| news/comments | ref | many |
		schema is plain old data
		users
		| attribute | type | cardinality |
		| user/firstName |
		| user/lastName |
		| user/email |
		comments 
		| attribute | type | cardinality |
		| comment/body |
		| comment/author |
		| news/comments | ref | many |
		note: comments have comments
		note: both stories and comments might have comments attribute. and it is the same attribute.
	slide: types do not dictate attrs
		people assume entities as a group of related things
		we show relatedness with namespace as convention
	slide: example: mbrainz
		designed by rich hickey
		modelled by drawing pictures
	slide: sync api
		(client/pull db arg-map)
	slide: async api

## Session 3

[(1143) Day of Datomic Cloud - Session 3 - YouTube](https://www.youtube.com/watch?v=0ozQ5aSPB04&list=PLjyLzdfdsKwqF9I1XSX_Y4TXAo8pYXbOv&index=3)

### otl notes

	slide: ACID
		atomic: 
			transaction is a set of datoms 
			transaction entirely in single write
		consistent
			all processes see same global ordering of transactions
		isolated
			single writer system (nobody to be isolated from)
		durable
			always flush through to durable storage before reporting transaction complete
	slide: assertion and retraction
		list of assertions
		[:db/add entity-id attribute value]
		[:db/retract entity-id attribute value]
		fakat bu şekilde eklemek yerine entity map ile de verileri ekleyebilirsin
	slide: entity maps
		list assertions = entity maps birbirine denkler
		can nest arbitrarily
		multiple assertions about same entity
	slide: lists vs. entity maps
		assertion list:
			[:db/add 42 :likes "pizza"]
			[:db/add 42 :firstName "John"]
			[:db/add 42 :lastName "Doe"]
		entity map
			{:db/id 42
			 :likes "pizza"
			 :firstName "John"
			 :lastName "Doe"}
		entity map can be nested
		job of putting data into your system =
			job of building a data structure
	slide: cross reference
		allows temp id
		[{:person/name "Bob"
		  :db/id "B"}
		 {:db/id "A"
		  :person/name "Alice"
			:person/friends "B"}]
		henüz daha veritabanına kaydedilmeden böylece entity'ler arasında ref kullanabiliriz
		integerlar permanent id'dir datomic tarafından atanmış olan
		stringler temp id'dir
	slide: nesting
		everything nests
		order has line items
		[{:order/lineItems 
		  [{:lineItem/product chocolate
			  :lineItem/quantity 1}
			 {:lineItem/product whisky
			  :lineItem/quantity 2}]}]
		maps are fundamentally open
	slide: identity
		database related unique id
			bu entity id
			number
			tx sonrasında datomic sana verir
		| requirement | model with | value types |
		| db-relative id | entity id | opaque long |
		| external id | unique identity | string, uuid, uri |
		| global id | unique identity | uuid |
		| programmatic name | :db/ident | keyword |
		programmatic name: programcıların kullandığı isimler
			değişkenler, atribut isimleri
			:db/ident ile isimlendirilir
			atributlar da böyle isimlendirilir
			datomicin tüm parçaları db/ident ile isimlendirilmiştir
			db/add ilk ident'tir
		çok hızlıdır db/ident
			cached in memory
			tüm entity'leri db/ident ile isimlendirmemelisin
	slide: transaction functions
		add and retract are not enough
		i want to look at current db value
		using transaction functions
		:db/cas
			compare and swap
			do this only if this holds
			produces datoms only if condition holds
		:db/retractEntity
			takes entity id and
			expands to retract all datoms about entity
	slide: reified transactions
		transactions are like commits in git
		they are objects (entities) by themselves
		there are facts about them
		there is one fact associated with every transaction
		they are assigned entity id when they happen
		what is the use for?
			facts about transactions
		ex: 
			this tx was created by ordinary web form
			this tx came from batch operations
	slide: transaction attributes
		ex01:
			[{:story/title "codeq"
				:story/url "http:.."}
			{:db/id "datomic.tx"               ; identifies transaction entity
				:publish/at (java.util.Date.)}]   ; add your own attributes
		ex02:
			[{:story/title "codeq"
				:story/url "http:.."}
			{:db/id "datomic.tx"               
				:db/txInstant #inst "2013-02"}]  ; override txInstant (for imports)

## Video: Day 4

[(1143) Day of Datomic Cloud - Session 4 - YouTube](https://www.youtube.com/watch?v=qplsC2Q2xBA&list=PLjyLzdfdsKwqF9I1XSX_Y4TXAo8pYXbOv&index=4)

### Day Of Datomic Cloud otl id=g12849

	slide: query model
		datalog
		pull
	slide: why datalog?
		logic language for databases
		developed same time as SQL
		equivalent to relational model + recursion
		style of datalog is pattern matching
	slide: example database
		| entity | attribute | value
		| 42 | :email | joe@.. |
		| 43 | :email | jane@ .. |
		| 42 | :orders | 107.|
		| 42 | :orders | 108.|
	slide: data pattern
		Constrains the results returned and binds variables
		always in the same order
		[<entity> <attribute> <value>]
		[?customer :email ?email]
		:email -> constant
			if the thing in the pattern is a constant, that is constraining the results
			all the results have to have :email in this position
		variables:
			?customer -> variable
			?email -> variable
		matching rows:
			| 42 | :email | joe@.. |
			| 43 | :email | jane@ .. |
		[42 :email ?email]
			this data pattern matches only one row
			| 42 | :email | joe@.. |
	slide: variables anywhere
		variables can appear anywhere
		[42 ?attribute]
			what attributes does customer 42 have?
		any stuff you don't put, we don't care
			here we don't care about value
		matching rows:
			| 42 | :email | joe@.. |
			| 42 | :orders | 107.|
			| 42 | :orders | 108.|
		but result will contain `:orders` once
	slide: clauses
		[:find ?customer
		 :where [?customer :email]]
		where clause
			data pattern
			[?customer :email]
		find clause
			variable to return
			:find ?customer
	slide: implicit join
		query: Find all the customers who have placed orders
		[:find ?customer
		:where [?customer :email]
		       [?customer :orders]]
		anytime you have same variable appearing in more than one clause
			these clauses must match variable
			so that is a join
	slide: in clause
		names inputs so you can refer to them elsewhere in the query	
		these things are bound on input
		:in $database ?email
		here we bound $database and ?email on input
	slide: parameterized query
	slide: first input
		find a customer by email
		( d/q [:find ?customer
		       :in $database ?email
					 :where [$database ?customer :email ?email]]
					db
					"joe@example.com")
		bindings:
			db -> $database
			"joe.." -> ?email
	slide: predicates
		functional constraints that can appear in a :where clause
		[(< 50 ?price)]
		note: price must be already bound
			predicate further constrains the variable
	slide: adding a predicate
		find the expensive items
		[:find ?item
		 :where [?item :item/price ?price]
		        [(< 50 ?price)]]
		additional stuff:
	slide: pull api
		



