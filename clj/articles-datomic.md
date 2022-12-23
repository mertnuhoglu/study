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

## Article: The Datomic Information Model by Rich Hickey id=g13516

https://www.infoq.com/articles/Datomic-Information-Model/

### Motivations

Datomic perspektifinden bir veritabanı bir enformasyon sistemidir. Enformasyon bir cümleler kümesidir (set of facts). Cümlelerse gerçekleşmiş şeylerdir. 

Geçmişi değiştirmek mümkün olmadığından, veritabanı ancak yeni cümleler biriktirebilir. Bu yüzden geçmiş değişmezdir.

Geleneksel veritabanları şu ana odaklanır. Yani şu an doğru olan cümleleri kaydederler. Bunu yaparken geçmiş veriyi yitirirler. Halbuki insanlar açısından geçmiş veri birçok yönden değer sunar. Sadece geçmiş yedekleri tutmak açısından değil, mevcut karar alımı açısından da geçmiş veri değer katar.

Bir veritabanını veritabanı yapan şey, verinin üzerine eklediği fonksiyonalitelerdir. Sadece veriyi saklamak işlevinden ibaret olsaydı, o zaman sadece bir depolama sistemi olurdu. Bu katma değerli işlevler, veriyi düzenlemeyle (indeksler yoluyla), bu düzenden yararlanan sorgu sistemleriyle sağlanır. 

> Goals of datomic
> 
> - No update-in-place
> - Scalable storage
> - ACID transactions
> - Declarative data programming
> 
> Datomic considers a database to be an information system. Information is a set of facts. Facts are things that have happened.
> 
> We cannot change the past. Thus database should accumulate facts. There cannot be any updates. 
> 
> Immutability leads to important benefits.
> 
> Existing database focus on "now". "Now" is the set of facts that are currently true. 
> 
> They lose historical information. Historical information can support decision making.
> 
> A database provides leverage over the data. Otherwise, it is just a storage system.
> 
> The leverage comes from organizing the data (via indexes) and query systems.

### Structure and Representation

Tüm veritabanlarının enformasyon modelinde temel bir yapıtaşı bulunur: ilişki, satır veya doküman gibi. Datomic için bu temel yapıtaşı, atomik bir cümledir (fact). Buna datom denir.

Datom beş parçadan oluşur:

- Entity (varlık)
- Attribute (atribut)
- Value (değer)
- Transaction (database time) (tx)
- Add/retract (assertion, doğrulama)

Bu temsil şekli RDF cümlelerindeki Subject/Predicate/Object veri modeline benzer. Ancak RDF cümlelerinde zaman boyutu ve doğrulama boyutu bulunmaz. Bu açıdan geçmiş veriyi temsil etmede yetersizdir. Datomic kapalı dünya varsayımıyla tasarlanmıştır. Bu yüzden semantik webdeki gibi evrensel bir isim uzayı ve ortak semantik kısıtları bulunmaz. 

Datomlar, yeniliğin temsili (transaction) için yeterlidir. Buna karşılık daha büyük yapıtaşlarında (doküman veya satır gibi), verinin değişimini modellemek çok zordur.

Datomlar, standart, düz (flat), evrensel bir ilişki oluşturur. Datomic içinde başka herhangi bir yapısal bileşen bulunmaz. Bu çok önemli, çünkü ne kadar çok yapısal bileşen olursa, aslında uygulamanın veri modelinin esnekliği o kadar azalmış olur. 

Örneğin, ilişkisel bir veritabanında, her bir ilişkinin isimlendirilmesi gerekir. Veriyi bulabilmek için, bu ilişki isimlerini biliyor olman lazım. Ayrıca çokaçok ilişkiler için keyfi bağlantı tabloları gerekir ve bunların da isimlerini bilmen gerekir. Fiziksel yapısal kararları, mantıksal tasarımdan izole etmek için özel çaba sarfetmek gerekir.

Doküman veritabanları yapısal olarak daha da kısıtlayıcıdır. Çünkü doküman içi hiyerarşi, uygulamaların içinde tekrarlanmak ve aynı şekilde hard kod edilmesi gerekir.

> The fundamental unit is atomic fact, or datom.
> 
> It has the following parts:
> 
> This model is similar to RDF Subject/Predicate/Object data model. But this model has also temporal notion and retraction notion.
> 
> Datomic adopts closed-world assumption.
> 
> Atomic unit ensures that novelty representations are only as big as the new facts themselves. Contrast this with document updates or delta schemes.
> 
> Datoms constitute a single, flat, universal relation. There is no other structural component in Datomic.
> 
> Contrast this to relational databases. They have relations, fields, tables, join tables. 

### Schemas

Tüm veritabanlarında schema bulunur. Ancak bazıları bunu açık bir şekilde tanımlar, bazıları gizli. 

Datomic'te tüm atributların önceden tanımlanması gerekir. 

Atributların kendileri de birer varlıktır. Bu varlıkların da kendi atributları bulunur:

- name
- data type of values
- cardinality
- uniqueness
- indexing properties
- component nature (whole part)
- documentation

Atributların hangi varlıklara uygulanabileceğine dair herhangi bir kısıtlama yoktur. Yani varlıklar açık ve sparse özelliktedir. Farklı varlıklarda atributlar paylaşılabilir. 

Örnek atribut tanımı:

```clj
{:db/ident       :person/name,
 :db/valueType   :db.type/string,
 :db/cardinality :db.cardinality/one,
 :db/doc         "A person's name"}
``` 

Schema, veri olarak edn formatında tanımlanır. 

Bu yapıyla satır benzeri demetler (tuples), hiyerarşik dokümanlar, kolon yapıları, çizgeler oluşturulabilir.

### Transactions

Transaksiyonlar, doğrulama datomlarından oluşan listelerdir. 

```clj
[[:db/add entity-id attribute value]
 [:db/add entity-id attribute value]...]
``` 

Datomicle tüm etkileşim veri ile yapıldığından, txler de bir liste listesi olarak işlenir.

Each vector representing a datom in:

```clj
[op entity attribute value]
``` 

Bir varlıkla ilgili birden çok cümle göndereceksen, bir map kullanabilirsin:

```clj
[{:db/id entity-id,
  attribute value,
	attribute value}
	...]
``` 

Datomların tx kısmıyla ilgili herhangi bir şey belirtmen gerekmez. Onları transactor modülü sağlar. 

Txlerin kendileri de birer varlıktır, aynı atributlar gibi. Bu yüzden tx hakkında cümleler de txin bir parçası olabilir. 

Txlerin içinde veritabanı fonksiyonları da bulunabilir:

```clj
[[:db/add entity-id attribute value]
 [:my/giveRaise sally-id 100]
 ...]
```

Bir txin parçası olan veritabanı fonksiyonlarına tx fonksiyonu da denir. Bunlara ilk argüman olarak veritabanı paslanır. Bu sayede fonksiyon kendisi sorgu yapabilir. Tx fonksiyonu tx verisi döndürmek zorundadır. 

Örneğin, yukarıdaki tx içinde `giveRaise` fonksiyonu Sally'nin mevcut maaşını bulur. Bunun 45000 olduğunu bulur. Bunun için bir doğrulama cümlesi döndürür:

```clj
[[:db/add entity-id attribute value]
 [:db/add sally-id :employee/salary 45100]
 ...]
```

### Connections and Database Values

Yazma yönünden, klasik veritabanları gibi çalışır. Bir uri kullanarak veritabanına bağlanırsın. transact fonksiyonunu kullanarak bu veritabanına veriyi kaydedersin.

Okuma yönünden, klasik veritabanlarından çok farklı çalışır. Onlarda okuma ve sorgulama, bağlantının bir fonksiyonudur. Bağlantı üzerinden bir sorgu gönderirsin. Sunucuya giden sorgu ifadesi, mevcut veritabanı durumu bağlamında çalıştırılır. Bu sırada kaynak ve senkronizasyon için diğer proseslerle ki bunlar yazma proseslerini de içerir, yarışır.

Datomicte tek bir okuma fonksiyonu bulunur: `db()`. Ve bu fonksiyon da network üzerinden erişim sağlamaz. Bağlantı bir kere kurulduktan sonra, otomatik olarak veritabanının değerini istemci tarafına aktarır. Bu sayede tüm veri sorgulamaları, lokal olarak çalışır. 

### Query

Sorgulama, ne bağlantının bir fonksiyonudur, ne de veritabanının. Sorgulama, kendi başına çalışan ve bu arada birkaç veri kaynağını argüman olarak alan bir fonksiyondur. Bu veri kaynakları, veritabanı değerleri veya veri koleksiyonları olabilir. 

Datomic peer kütüphanesi datalog tabanlı bir sorgu motoru içerir. Temel sorgu formu:

```clj
{:find [variables...] :where [clauses...]} ;; map form
[:find variables... :where clauses...] ;; list form
``` 

Sorgular da yine txler ve schemalar gibi veri yapılarıdır.

Örnek veritabanı:

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

Her datom bir demet veri yapısıdır: `[e a v tx]`

Sağ taraftaki kısımları istediğin gibi eleyebilirsin. 

`?variable` are variables. 

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

`with` fonksiyonuyla simülasyon amaçlı bazı veriler ekleyip onunla sorguyu çalıştırabiliriz:

> Ex: We can add a new collection and rerun a query:

```clj
Peer.q(query, db.with(everyoneFromBrookly))
``` 

> So, you can do speculative, what-if queries.

Veritabanı olmadan da sadece veriyi aynı formatta sağlayarak sorgu yapabiliriz:

```clj
Peer.q(query, aCollection)
``` 

Ex: Query historical data

```clj
// who has ever liked pizza?
Peer.q(query, db.history())
``` 

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

[(1137) Day of Datomic Cloud - Session 1 - YouTube](https://www.youtube.com/watch?v=yWdfhQ4_Yfw&list=PLjyLzdfdsKwqF9I1XSX_Y4TXAo8pYXbOv) id=g13502

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
	one database, many indexes id=g13503
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
	slide: time aware id=g13504
		| db view | semantics | supports |
		| (default) | current state | what is current situation? |
		| as-of | state at point in past | how were things in the past? |
		| since | state since point in past | how have things changed? |
		| tx report | before/after/change view of tx | automated event response |
		| with | state with proposed additions | what would happen if we did X? |
		| history | timeless view of all history | anything |
	slide: entities id=g13505
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
		impedance yok. datomları nested maplere dönüştüreceğimiz vakit, tek bir yol var:
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
			örneğin: twitter hesabı için şu 10 facte sahip olmalısın
			bizse varlıklar için hangi atributlara sahip olacağına dair bir ön taahhütte bulunmuyoruz
			sadece atributların neler olduğuyla ilgili bir dizayn yapıyoruz
			atributlar indekslerle veri sisteminde dolaşmana izin verir
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
		db/ident: db/ident'i olan her şeyin bir ismi var demektir
			programatik bir isimdir
		db/valueType: atributun veri tipi
		db/cardinality: bir tane mi olabilir, birden çok mu?
			eğer "liking" için kardinalite tekilse ne anlama gelir?
			schema güç kazandırır
			bu durumda "liking"i kimin yapacağını bilmeyiz, ama sadece bir şeyi sevebileceğini biliriz
			bu durumda mesela "jane brokoliyi sever" cümlesinden sonra "jane pizzayı sever" cümlesi gelirse, bir önceki cümlenin geçersiz olduğunu çıkartabiliriz
		db/unique: sadece bir varlık bu atributun bir değerine sahip olabilir
		db/isComponent: order has line item. person has arms.
	slide: social news example
		https://github.com/Datomic/day-of-datomic/blob/master/tutorial/social_news.clj
		örnek demo sitesi: insanlar link yayınlar ve bunlara yorum yaparlar
			bir story yayınlarlar. buna title verirler: story/title
		bu gerçek veriye ne kadar yakındır?
			çok yakın
			datomic kaynaklı yabancı bir şey yoktur
			sql tarafında mantıksal ve fiziksel modelleme yaparsın
			fiziksel modelleme, peperformans amaçlı olur.
		stories
		| attribute | type | cardinality |
		| story/title | string | 1 |
		| story/url | string | 1 |
		| story/slug | string | 1|
		| news/comments | ref | many |
	slide: schema is plain old data
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
		comments have comments
			ne tür şeyleri commenti olabilir sistemde?
			storylerin ve commentlerin
			fakat her ikisi için de atribut ismi aynı.
			burası önemli. bu comment, başka bir comment için veya bir story için olabilir.
			ama her ikisi için de tek bir atribut yeterli.
			sql ile çok daha karmaşık olurdu.
		note: both stories and comments might have comments attribute. and it is the same attribute.
	slide: types do not dictate attrs
		people assume entities as a group of related things
		we show relatedness with namespace as convention
	slide: example: mbrainz
		designed by rich hickey
		modelled by drawing pictures
			modeller grafiklerle yapılmalı
	slide: client api
		asenkron bir şekilde tasarlanmıştır
			ama istersen client/server şeklinde çağırabilirsin
			bunu genellikle sonuçları pagination halinde almak için yaparsın
			pagination yaparsan channel (queue) olarak alırsın verileri
			transducible
			async olunca, hataları veri olarak alabilirsin
	slide: sync api
		(client/pull db arg-map)
		arg-map: '{:eid [:name "jane" :selector [*]]}
		pull api sayesinde client tarafı nasıl veri istediğini belirtebilir, önceden tanımlaman gerekmez
	slide: async api
	slide: anomalies
		hatalar async api'de birer map verisi olarak döner
		keywordler:
			unavailable
			interrupted
			incorrect
			forbidden
			unsupported
			not-found
			conflict
			fault
			busy
		mevcut teknolojiyi kullanmak için elimizden geleni yapıyoruz
			jvm üzerinde çalışma
		http status codeları bu hatalara yönelik bir girişim
			sorunu: kimseyle konuşmadığın durumları ele alamaz
			anlamlı olmayan hata kodları var
		java exception kodları da problemli
			buradaki amaç sınırlı bir hata kümesi olsun
			bunu insanlar fork edebilsinler
			java exceptionlarında bu sınırlı küme yok
	slide: lab: data modeling
		assertion tutorial ve read tutorial örnekleriin uygula
		bir schema tasarla
			küçük bir alan olsun
			er diyagramı çiz
			buna özel bir edn scheması yaz
			veritabanına transaction yap

## Lab: Assertion Tutorial id=g13506

[Assertion | Datomic](https://docs.datomic.com/cloud/tutorial/assertion.html)

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01b.clj`

### Setup

[Local Dev and CI with dev-local | Datomic](https://docs.datomic.com/cloud/dev-local.html)

```bash
cd /Users/mertnuhoglu/.m2/repository/cognitect-dev-tools-0.9.72/
./install
```

Şuraya kurar dosyaları:

```
/Users/mertnuhoglu/.m2/repository/com/datomic/dev-local/
/Users/mertnuhoglu/.m2/repository/com/cognitect/rebl/
```

Lokal depo için klasörü burada tanımlayabilirsin: `~/.datomic/dev-local.edn`

```clj
{:storage-dir "/full/path/to/where/you/want/data"}
```

Cognitect'ten gelen emailde maven ayarları var:

01. maven:

> Both tools.deps and maven configurations require thew following step:

> Add a new server entry under servers in your ~/.m2/settings.xml as shown below.

	<!-- in ~/.m2/settings.xml -->
	<settings>
		...
		<servers>
		...
			<server>
				<id>cognitect-dev-tools</id>
				<username>mert.nuhoglu@gmail.com</username>
				<password>...</password>
			</server>
		</servers>
		...
	</settings>

02. tools.deps:

> Add an entry under the :mvn/repos key in your ~/.clojure/deps.edn file. You only need to do this once, nothing else needs to be done per-project to specify maven information.

	;; in ~/.clojure/deps.edn
	{...
	:mvn/repos {"cognitect-dev-tools"
							{:url "https://dev-tools.cognitect.com/maven/releases/"}}}

03. deps.edn

Edit `~/prj/study/clj/ex/study_datomic/datalog-01/deps.edn`

```clj
{com.datomic/dev-local {:mvn/version "1.0.243"}}
```

04. Memory storage

```clj
(def client (d/client {:server-type :dev-local
	:storage-dir :mem
	:system "ci"}))
```

04. Connect to Sample Database id=g13507

[Sample Data](https://docs.datomic.com/cloud/dev-local.html#samples)

rfr: 

	/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01b.clj
	/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01c.clj

Download https://datomic-samples.s3.amazonaws.com/datomic-samples-2020-07-07.zip

Bu dosyanın içindekileri `dev-local.edn` tarafından tanımlanmış storage-dir içine koy.

Dikkat: Hiç sunucu yazılımı çalıştırmana gerek yok, burada. Aşağıdaki client yazılımı otomatik olarak storage-dir altındaki dosyaları veritabanı olarak kullanabilir.

```clj
(require '[datomic.client.api :as d])
(def client (d/client {:server-type :dev-local
                       :system "datomic-samples"}))
(d/list-databases client {})
=> ["mbrainz-subset" "solar-system" "social-news" "movies" ...]
```

Bu durumda  `~/.datomic/dev-local.edn` içindeki `{:storage-dir "/Users/mertnuhoglu/db/"}` içinde veritabanları tutulur.

Bu örnekte local-dev kütüphanesi kullanılarak diskte storage-dir altında veriler tutulur.

> dev-local stores data to your local filesystem, in directories under the :system you specify when creating a dev-local client.

> Each database will store transactions in a directory named <storage-dir>/<system-name>/<database-name>. You can "backup" or "restore" a dev-local database simply by copying the database directory.

Next step: rfr: Example: Day of Datomic Cloud Sample Data <url:file:///~/prj/study/clj/articles-datomic.md#r=g13521>

### Code - Assertion and Read Tutorial id=g13509

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01b.clj`

Bu örnekte local-dev kütüphanesi kullanılarak bellekte veriler tutulur.

## Session 3

[Day of Datomic Cloud - Session 3 - YouTube](https://www.youtube.com/watch?v=0ozQ5aSPB04&list=PLjyLzdfdsKwqF9I1XSX_Y4TXAo8pYXbOv&index=3) id=g13510

### otl notes

	slide: ACID
		atomic: 
			transaction is a set of datoms 
			transaction entirely in single write
		consistent
			all processes see same global ordering of transactions
			tx'ler arasındaki sıralama aynı olacak
			bu eventual consistency'den farklı bir şey
			ec'de, mesela şimdi tx1 tx2 sıralaması görürsün. fakat daha sonra bu sıralamanın yanlış olduğunu görebilirsin.
			ec'de nasıl varsayımlarda bulunacağında dikkatli olmalısın
			geleneksel veritabanlar, consistency bir ayarlanabilir bir şeydir. azaltılıp artırılabilir.
			mongo gibi yaparsan, ec'yi azaltırsın. performansı yükseltirsin.
			datomicde bu tip bir ayarlama yok.
			her zaman tutarlılık sağlanır.
		isolated
			datomic izoledir, çünkü single writer system (nobody to be isolated from) vardır.
			sadece tek bir proses var, transactor adında yazma işlemi yapan.
			diğer tüm prosesler peerdir. hepsi veriye erişebilir, ama sadece bir tanesi yazabilir.
		durable
			always flush through to durable storage before reporting transaction complete
			tx bittiğinde, tx kalıcı depoya yazılmış demektir
	slide: assertion and retraction
		list of assertions
		[:db/add entity-id attribute value]
		[:db/retract entity-id attribute value]
		:db ile başlayan her şey datomice aittir
		tekil atributlarda yeni bir değer eklediğinde, eski değer otomatik geri çekilir
		bu şekilde verileri eklemek çok pratik değil
		bu şekilde eklemek yerine entity map ile de verileri ekleyebilirsin
	slide: entity maps
		list assertions = entity maps birbirine denkler
		can nest arbitrarily
		multiple assertions about same entity
		verim üzerine düşünürken, genellikle entity'ler üzerinden düşünürüm
	slide: lists vs. entity maps
		veri üzerinde düşünürken, tek tek cümleler (facts) üzerinden değil, varlıklar (entity) üzerinden düşünürüm.
		bir varlık üzerine çok sayıda doğrulama (assertion) yapabilmeliyim
		assertion list:
			[:db/add 42 :likes "pizza"]
			[:db/add 42 :firstName "John"]
			[:db/add 42 :lastName "Doe"]
		entity map
			{:db/id 42
			 :likes "pizza"
			 :firstName "John"
			 :lastName "Doe"}
		tek seferde çok sayıda doğrulama yapıyorum burada
			bu map'ler gibi veya objectler gibi bir format
		entity map can be nested
			örneğin, friend olabilir. sınırsız nesting olabilir.
		dolayısıyla veritabanına veri eklemek işi, aslında bir veri yapısı inşa etmek işi olur.
			bunun unit test açısından sonucu nedir?
			unit test için veritabanına ihtiyacın yoktur.
			çünkü tx yapmak, aslında veri yapısı inşa etmek demek.
	slide: cross reference
		datomic geçici idlere (temp id) izin verir
		[{:person/name "Bob"
		  :db/id "B"}
		 {:db/id "A"
		  :person/name "Alice"
			:person/friends "B"}]
		henüz daha veritabanına kaydedilmeden böylece entity'ler arasında ref kullanabiliriz
			henüz daha gerçek idleri bilmiyoruz, ama yine de varlıklar arasında ilişkiler kurabiliriz
		stringler tempid'lerdir
		integerlar permanent id'dir datomic tarafından atanmış olan
	slide: nesting
		everything nests
		örnek: order has line items
		[{:order/lineItems 
		  [{:lineItem/product chocolate
			  :lineItem/quantity 1}
			 {:lineItem/product whisky
			  :lineItem/quantity 2}]}]
		veriyi datomice map ile koyarsın genellikle
			bu mapler temel olarak açıktır
			gerçek veriyi temsil eder.
			slot değillerdir. önceden tanımlanmış bir format zorlamazlar.
			nest oldukları için karmaşık veri yapılarına izin verirler
	slide: identity
		uuid konusu
		01. database relative unique id
			sadece bu veritabanında tekil olması kesin
			opaque long. yani bu sayının ne olduğunu bilmene gerek yok.
			tx sonrasında datomic sana verir
		02. external id
			email adresi gibi. tekildir.
			atributun meta değeri unique olmalı
		03. global id
			bazen external id olmadığı halde global bir idye ihtiyaç duyarsın
			bu durumda uuid oluşturursun 
		04. programmatic name
			programcıların programda kullandıkları değişkenler gibi
			:db/ident
			tüm db ile başlayan keywordler aslında birer :db/ident sahibidir
			bunlar aşırı hızlıdır
			cached in memory
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
		assert ve retract yeterli değildir
		bankadaki hesabıma 10 dolar ekleyecek bir tx istiyorum
		bu durumda, bankadaki balansımı -5'ten +5'e set etmek istiyorum
		bu aradaki zamanda ne olabilir?
		başkası araya girip değiştirebilir
		veritabanının mevcut değerine bakıp, eğer beklediğim değerse değişikliği yapmalıyım
		:db/cas tx fonksiyonu bu işe yarar
		bunu kullanmadan önce, buna ihtiyacın var mı, önce buna karar ver
		:db/cas
			compare and swap
			do this only if this holds
			produces datoms only if condition holds
		:db/retractEntity
			takes entity id and
			bu varlıkla ilgili tüm değerleri geri alır
	slide: reified transactions
		tx fonksiyoları, tx'lerin en ilginç kısmı değildir
		en ilginç kısım, reified tx özelliğidir
		tx'ler objedir, git'teki commitler gibidir
		her tx ile ilgili bağlanan bir atribut vardır: :db/txInstant
		bu ne işe yarar?
			tx'lere cümle (fact) eklemek ne işe yarar?
			bu tx web'den oluşturulmuştur.
			veya bu tx batch import ile oluşturulmuştur.
			tx'lerle ilgili kaydetmek istediğin her türlü veriyi tanımlayabilrisin.
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
		sql veritabanında: createdat ve updatedat atributları
			bunlar tüm satırla ilgilidir, tam olarak hangi değerin değiştiğini tutmaz
		sql veritabanlarında mevcut slotlar yetersizse, gölge bir tablo oluşturursun çoğu zaman
		bir update yapacağın vakit 3-4 farklı tabloya güncelleme yaparsın, o zaman hepsinde updatedat kaydetmen gerekir
		ayrıca zamanla ilgili de sorun var. aynı ms içinde gerçekleşmeyebilir işlemler.
		Reified_Transactions diye bir tablo oluşturabilirsin, ama bunu tüm tablolara bağlaman gerekir
			aslında Datomicin hikayesi de bu şekilde başlamıştı
			evet bunu yapabilirsin, ama kullanışsız ve düşük performanslı olur

## Lab: Accumulate, Read, Retract, History  id=g13511

rfr: `Accumulate | Datomic <url:file:///~/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01b.clj#r=g13513>`

## Video: Day of Datomic Cloud - Day 4 id=g13514

[(1143) Day of Datomic Cloud - Session 4 - YouTube](https://www.youtube.com/watch?v=qplsC2Q2xBA&list=PLjyLzdfdsKwqF9I1XSX_Y4TXAo8pYXbOv&index=4)

### Day Of Datomic Cloud otl id=g12849

	slide: query model
		datalog
		pull
		raw indexes (advanced)
		filters (advanced)
	slide: why datalog?
		logic language for databases
			developed around same time as SQL
		neden sql popülerleşti, datalog yerine?
			equivalent to relational model + recursion
			garantili termination
		style of datalog is pattern matching
	slide: example database
		örnek veritabanımız 4 datom içerecek:
		| entity | attribute | value
		| 42 | :email | joe@.. |
		| 43 | :email | jane@ .. |
		| 42 | :orders | 107.|
		| 42 | :orders | 108.|
	slide: data pattern
		sorgu yaparken, data pattern (veri örüntüsü) adında bir veri oluşturursun
		veri örüntüsü: Constrains the results returned and binds variables
		always in the same order
			[<entity> <attribute> <value>]
		eğer bu örüntüdeki bir şey sabitse, o kısıtlayıcıdır
		soru işareti olan her şey bir değişkendir
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
		sabitler her yerde olabilir. örnek:
		[42 :email ?email]
			this data pattern matches only one row
			| 42 | :email | joe@.. |
	slide: variables anywhere
		variables can appear anywhere
		[42 ?attribute]
			what attributes does customer 42 have?
			dikkat: değerler üzerine değil bu sorgu, atributlar üzerine
		any stuff you don't put, we don't care
			here we don't care about value
		matching rows:
			| 42 | :email | joe@.. |
			| 42 | :orders | 107.|
			| 42 | :orders | 108.|
		3 tane satır eşleşir
		ama sonuç kümesinde 2 satır döner
		çünkü bu küme tabanlıdır (set based)
		but result will contain `:orders` once
	slide: clauses
		[42 ?attribute ?value]
			42 eid üzerine bildiğim her şeyi bana ver
	slide: find clause
		[:find ?customer ;; variable to return
		 :where [?customer :email]]
	slide: where clause
		where clause
			data pattern
			[?customer :email]
		find clause
			variable to return
			:find ?customer
	slide: implicit join
		aynı değişken birden çok cümlecikte yer alırsa, bir join gerçekleşir
			buna birleştirme (unification) de denir
		query: Find all the customers who have placed orders
		[:find ?customer
		:where [?customer :email]
		       [?customer :orders]]
		anytime you have same variable appearing in more than one clause
			these clauses must match variable
			so that is a join
	slide: in clause
		sql'de parametrik sorgular vardır, bu in clause ile yapılır
		names inputs so you can refer to them elsewhere in the query	
		these things are bound on input
		:in $database ?email
		here we bound $database and ?email on input
	slide: parameterized query
		örnek: find a customer by email
			[:find ?customer
			 :in $db ?email
			 :where [$db ?customer :email ?email]]
			db
			"jdoe@example.com"
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
		bir değişken bir kere bağlandıktan sonra, yüklemlerle daha da kısıtlayabilirsin
		functional constraints that can appear in a :where clause
		[(< 50 ?price)]
		note: price must be already bound
			predicate further constrains the variable
	slide: adding a predicate
		find the expensive items
		[:find ?item
		 :where [?item :item/price ?price]
		        [(< 50 ?price)]]
		önce ?price değişkenini bağlıyorsun
		sonra bu değerlerden 50'den küçük olanları filtreliyorsun
	slide: pull api
		sorguda önce factleri bulursun
		daha sonra bu cümlelerden belli verileri çekersin
		bu çekme işlemini mantık diliyle yapabilirsin, ama çok pratik değildir
		bunun yerine varlıkları aldıktan sonra, bunları yapısal bir şekilde speklemek daha iyi olur
	slide: pull api
		{:selector [{:likes [:name]}
		 :eid [:name "jane"]]}
		/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20221112_114931.jpg
		selector hangi kısımları pull out edeceğimizi söyler
		ilk parça "likes" içine girer (drills into)
		ikinci parça "name" içine girer
		/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20221112_115100.jpg
	slide: pull api
		ters yönde hareket etmek için `_` kullanılır
			böylece modelleme yaparken iki yönlü ilişki kurmana gerek kalmaz
		eid: broccoli isimli varlıkları bul
			/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20221112_115312.jpg
		selector: bulduğun bu varlıkları seven varlıkları bul
			/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20221112_115328.jpg
			bu varlıkların name değerini al
			/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20221112_115339.jpg
		myn: broccoli isimli varlıklara x diyelim
			sadece :likes olsaydı, x'nin sevdiği varlıkları bulurduk
			:_likes olunca e'yi seven varlıkları buluyoruz
			dolayısıyla ilk durumda v kolonunda arama yaparken,
			ikinci durumda e kolonunda arama yapıyoruz
			[x :likes ?] => {:likes}
				| e | a | v |
				| x | :likes | ? |
			[? :likes x] => {:_likes}
				| e | a | v |
				| x | :likes | ? |
	örnek: recent-deploys
		q
			:find (pull ?e [*
											{:deploy/application [:application/name]}
											{:deploy/group [:group/name]}])
			:in $ ?since
			:where
			[?e :deploy/time ?time]
			[(<= ?since ?time)]
	slide: programming model
		datomic cloud
		dev: fns dynamically
		push: artifact revisions (install)
		deploy: artifact revisions
		configure and bond: ion entry points
			
# Article: Datomic from the ground up - subhash id=g12895

	ref
		[Datomic from the ground up · subhash/clj-stack Wiki](https://github.com/subhash/clj-stack/wiki/Datomic-from-the-ground-up)
		~/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/tutorial_ground_up/e01.clj
	Pitfalls of SQL Models
		Schema katıdır (rigid)
			Person (name string, age integer)
			Sonuçları:
				Bazı varlıkların ek atributları olabilir => sparsity problem
				Gereksinimlere göre varlıkların tanımları değişebilir.
					Bunu sağlamak için, tüm mevcut veriyi yeni tanıma göre taşımak gerekir
				Yeni ilişkiler keşfedilemez
					Bütün ilişkilerin önceden tanımlanmış olmasını gerektirir.
					Örneğin: Person ve Employee tablolarına iki satır ekleyip, bunların aynı varlık olduğunu belirtemem, eğer daha önceden bu ikisi arasında bir FK koymamışsam
		Relational view of data
			Codd's relation: n-tuple over n sets of values
			Ex:
				set1: {0, 1, 2, ...}
				set2: {"ali", "fred"}
				relation: {0, "ali"} 
				bu relation bir varlığı temsil edebilir
				relation: {1, "fred", "namık cad."}
				yeni bir relation farklı atributları içerebilir
			Datomic atribut tanımları Codd'un relation'larına çok yakındır:
				{:db/id 101 :db/ident :person/name :db/valueType :db.type/string
				:db/cardinality :db.cardinality/one :db.install/_attribute :db.part/db}
			Points:
				p01: keywords are namespaced
					:db | internal identifiers
					:person | kendi namespaceimiz
	ex01: Adding schema and data
		ref: ~/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/tutorial_ground_up/e01.clj
		p01: tekil id'leri otomatik tanımlamak
			{:db/id #db/id[db.part/db]}
	ex02: Querying
		ref: ~/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/tutorial_ground_up/e01_querying.clj
	ex03: Reverse navigation with `_`
		ref: Reverse ref <url:file:///~/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/tutorial_ground_up/e01_ref.clj#r=g12897>
		:person/_job 103
	`:db.install/_attribute :db.part/db` nedir? ters referans
		dolayısıyla `:db.part/db` den yeni tanımlanmış atributa referans demek
		dolayısıyla `:db.part/db` varlığının (entity) atributlarından bir tanesi `:db.install/attribute`
		bu atributun değerleri, ilgili varlığın tüm atributlarını içerir
	Turtles all the way down: Meta data modeli
		ref
			~/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/tutorial_ground_up/e01_metadata.clj
		Atributların listesi de bir atributun değeri olarak tutulur
		ex: sistemdeki tüm atributları listeleyelim:
			ref: Bir varlığın tüm atributlarını listele <url:file:///~/projects/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/tutorial_ground_up/e01_metadata.clj#r=g12898>

# Article: Hodur: Declarative Domain Modeling for Datomic Ion/Cloud - Tiago Luchini - YouTube id=g12893

	ref
		[(28) Declarative Domain Modeling for Datomic Ion/Cloud - Tiago Luchini - YouTube](https://www.youtube.com/watch?v=EDojA_fahvM)
	Temel amaç:
		system = fn(logic, state, model)
		where:
		logic = fn_1 ... fn_n => clojure
		state = fn(state_{init}, txs) => datomic
		model = fn(model_{domain}, model_{mech}) => hodur
	Hodur principles
		p01: Model is data
			ex: Entities
				[Employee
				 Project
				 Invoice]
			ex: Entities with attributes
				[Employee
				 [first-name
					last-name]
				 Project
					[name]
				 Invoice
				 [date
					amount]]
			ex: Use metadata for types:
				[Employee
				 [^String first-name
					^String ast-name]
				 Project
				 [^String ame]
				 Invoice
				 [^Date date
					^Float amount]]
			ex: references
				[Employee
				 [^String first-name
					^String ast-name
					supervisor]
				 Project
				 [^String ame
					employees]
				 Invoice
				 [^Date date
					^Float amount]]
			ex: references
				[Employee
				 [^String first-name
					^String ast-name
					^Employee supervisor]
				 Project
				 [^String ame
					^{:type Employee
						:cardinality [0 n]} employees]
				 Invoice
				 [^Date date
					^Float amount]]
			ex: Querying the model with datomic:
				(require '[datascript.core :as d])
				(d/q '[:find [?e ...]
							 :where
							 [?e :type/node :field]
							 [?e :field/cardinality [0 n]]]
						 @meta-db) ;; => [13]
			ex: Using pull
				(d/q '[:find [(pull ?e [:field/name])...]
							 ...
						 @meta-db) ;; => [{:field/name "employees"}]
			ex: Accessing parent
				(d/q '[:find [(pull ?e [:field/name
																{:field/parent [:type/name]}])...]
							 ...) 
				;; => 
				[{:field/name "employees"
					:field/parent {:type/name "Project"}}]
		p02: One Model Many Views
			ex: visual
				(def meta-db
					(engine/init-schema
					 '[Employee
						 [^String first-name]]))
				-> meta-db
					 visualizer/schema
					 visualizer/apply-diagram!
			ex: datomic view
				(hodur-datomic/schema meta-db)
			ex: spec view
				(hodur-spec/defspecs meta-db)
		p04: Let Mahcnies do machine stuff
			ex:
				1. build model
				2. extend with queries & pagination
				3. deploy it:
					- datomic
					- graphql
					- datomic cloud ions
			datomic ions:
				power of datomic + run your lambda functions in cloud
				1. write function
					x.clj
						(defn hello-from-cloud ..)
					ion-config.edn
						:lambdas {:hello-from-cloud ...}
				2. push
					clojure -A:dev -m datomic.ion.dev '{:op :push}'
				3. deploy
					clojure -A:dev -m datomic.ion.dev \
					'{:op :deploy
						:rev ...
						:group "qa"}'
					=> output: lambda
					; call it:
					aws lambda invoke --function-name \
						qa-hello-from-cloud --payload .. 
				4. plumbing
					biri şu işlemi yaptığında, bu lambda çağrılsın

# Article: Day of Datomic tutorial id=g12865

	ref
		~/codes/clj/content/day-of-datomic/README.md
		https://github.com/Datomic/day-of-datomic
	setup
		cd ~/codes/clj/content/day-of-datomic
		lein repl

# Article: mbrainz tutorial id=g12871

	ref
		~/projects/study/clj/ex/study_datomic/mbrainz-sample/examples/clj/datomic/samples/mbrainz.clj
		[Datomic/mbrainz-sample: Example queries and rules for working with the Datomic mbrainz example database](https://github.com/Datomic/mbrainz-sample)
	prerequisite
		ref: run datomic pro and console <url:file:///~/projects/study/clj/datomic.otl#r=g12858>
	setup
		; import mbrainz data id=g12872
		bin/datomic restore-db file:///Users/mertnuhoglu/codes/clj/ex/mbrainz-1968-1973 datomic:dev://localhost:4334/mbrainz-1968-1973
	run
		cd /Users/mertnuhoglu/projects/study/clj/ex/study_datomic/mbrainz-sample
		ref: run repl + Rebl + portal <url:file:///~/projects/study/clj/datomic.otl#r=g12891>
	repl code
		ref: repl code connection <url:file:///~/projects/study/clj/datomic.otl#r=g12892>
		database-name: mbrainz-1968-1973
		(d/q '[:find ?id ?type ?gender
					 :in $ ?name
					 :where
					 [?e :artist/name ?name]
					 [?e :artist/gid ?id]
					 [?e :artist/type ?teid]
					 [?teid :db/ident ?type]
					 [?e :artist/gender ?geid]
					 [?geid :db/ident ?gender]]
				 db
				 "Janis Joplin")
	console
		ref: run datomic console <url:file:///~/projects/study/clj/datomic.otl#r=g12858>
		open http://localhost:9000/browse
		DB: mbrainz
	query
		ref: [Queries · Datomic/mbrainz-sample Wiki](https://github.com/Datomic/mbrainz-sample/wiki/Queries)
		(d/q '[:find ?title
					 :in $ ?artist-name
					 :where
					 [?a :artist/name ?artist-name]
					 [?t :track/artists ?a]
					 [?t :track/name ?title]]
				 db, "John Lennon")
		; şimdi rebel ile datayı dolaşabilirsin

# Article: rebl ile datomic kullanımı id=g12873

	prerequisite:
		import mbrainz data <url:file:///~/projects/study/clj/datomic.otl#r=g12872>
		run datomic console <url:file:///~/projects/study/clj/datomic.otl#r=g12858>
	run rebel+repl:
		cd ~/codes/clj/ex/day-of-datomic-cloud
		ref: run repl + Rebl + portal <url:file:///~/projects/study/clj/datomic.otl#r=g12891>
	repl code
		ref: repl code connection <url:file:///~/projects/study/clj/datomic.otl#r=g12892>

# Article: Datomic: Look at all the things I'm not doing! (Cont.) (August Lilleaas' blog) id=g12883

	ref
		[Datomic: Look at all the things I'm not doing! (Cont.) (August Lilleaas' blog)](https://augustl.com/blog/2018/datomic_look_at_all_the_things_i_am_not_doing_cont/)
	p01: Query engine is embedded into the client
		Geleneksel yaklaşım: sorgu motoru veritabanı sunucusunda
		Datomic: sorgu motoru istemci içinde
		Bunun sonucu:
			Eş zamanlı sorgular yapabilirsin, sunucuyla senkronize olmadan
			Tek darboğaz: Veriyi sunucudan çekmek
			Bunu da hafifletmek için, sunucu ve istemci arasına memcached koyabilirsin
		Başka sonuç: N+1 sorgulardan kaçınmak gerekmez
			Geleneksel veritabanında N+1 sorgudan kaçınılır
			Örneğin: Projeleri listele ve sonra her birindeki todo itemları listele.
			Bunu tek tek yapmazsın. Tek seferde yaparsın.
			Böylece her sefer gerçekleşecek network round-trip maliyetinden kurtarırsın.
			Fakat datomicde sorgu motoru zaten senin uygulamanın içinde.
			Bu yüzden çok daha basit sorgular yapabilirsin, tüm sorguları tek seferde yapman gerekmez.
		Başka sonuç: Application level caching gerekmez
			Sorgu sonuçlarını uygulama seviyesinde keşlemen gerekmez, datomic ile.
			Sorgu motoru ve kullandığın veri (working set) zaten uygulamanın içindedir.
	p02: Audit logging
		Her bir varlığın (entity) tüm geçmiş kayıtlarına kolayca erişebilirsin
		Ayrı bir audit tablo tutmak ve buna json dump etmen gerekmez
	p03: Change detection
		Datomic ile tüm değişimleri tembelce (lazily) bir kuyruktan (queue) çekebilirsin
	p04: Database mocking yapmaya gerek yok
		with API ile doğrudan veritabanı üzerinde test sorguları çalıştırabilirsin
	p05: Schema tanımlama önden yapmaya gerek yok
		Yazarken değil, okuma yaparken yapı oluşturursun.

# Article: What Datomic brings to businesses | by Val Waeselynck | Medium id=g12884

	ref
		[What Datomic brings to businesses | by Val Waeselynck | Medium](https://medium.com/@val.vvalval/what-datomic-brings-to-businesses-e2238a568e1c)
	Expressive querying
		High query power
		veriyle ilgili bir soruyu koda çevirmek çok daha basit
		sebepleri:
			çoklu paradigma:
				mantıksal/ilişkisel (datalog)
				navigasyonel/graph-like (entity api)
				graphql tarzı (pull api)
			Lokal veri
				Diğer veritabanlarında her bir sorgu, bir geziye (expedition) çıkmak gibidir.
				Datomic'te veri çoğu zaman lokaldir.
				Peer lokal uygulama içinde sorguyu çalıştırır
			Programlanabilir
				Tüm requestler data olduğundan, esnek bir şekilde programlanabilir
	No data loss
		asOf() ile eski bir ana gidilebilir
	Flexible data modeling
		Universal schema
		Bir atribut birden çok entity type tarafından paylaşılabilir
		Sparsity SQL veritabanlarına göre çok daha kolay yönetilir
			Schemada bozucu değişikliklere neden olmaz
			Bir atributa ihtiyaç yoksa, onu silmezsin. Sadece kullanmayı bırakırsın.
	Testing is cheap
		with() lokal olarak write işlemlerini yapmış bir view üretir
		bir nevi veritabanı değerlerinde (database value) fork yapmış gibi
	Reproducibility
		Geçmişteki her ana ulaşabilirsin
		Production veritabanını lokalde klonlayabilirsin
	Integrating other data systems
		Eğer doğruluk kaynağı datomic olursa, diğer sistemlere senkronizasyon verilerini göndermek çok kolay olur

# Article: Val on Programming: Using Datomic in your app: a practical guide id=g12886

	return: What Datomic brings to businesses | by Val Waeselynck | Medium <url:file:///~/projects/study/clj/datomic.otl#r=g12884>
	ref
		[Val on Programming: Using Datomic in your app: a practical guide](https://vvvvalvalval.github.io/posts/2016-07-24-datomic-web-app-a-practical-guide.html)
	Datomic'in çözdüğü sorunlar:
		Schema rigidity
		N+1 problem
		impedance mismatch
		remote querying
		consistency
	Datomic özet:
		datom: 5-tuple
			entity_id attribute value transaction_id operation
			[42 :user/email "hello@gmail.com" 201 true]
		database value: immutable, shared data structure that is logically a set of datoms
			corresponds to a commit in Git
		database grows by accumulating new datoms
		Datomic connection is a remote reference to current database value
		Datomic system is a succession of database values
		Succession of values is controlled by the Transactor process.
		Write = transaction request
		Reading is local. 
			It happens on the application process, called Peer.
			This is possible because database values are immutable
			Thus easy to cache and location-transparent
		Low-level reading interface via indexes
			On top of it: 
				Datalog Query Language
				Entities
	Represent business entities with Entities
		Normal veritabanı uygulamalarında entity'nin tüm verilerini çekip çekmemek performans problemidir.
		Datomicte her şeyi çekebilirsin, hiç sorun olmaz.
		ex:
			(defn comments-of-user-about-post
			"Given a user Entity and a post Entity, returns the user's comments about that post as a seq of Entities."
			[user post]
			(let [db (d/entity-db user)]
				(->> (d/q '[:find [?comment ...] :in $ ?user ?post 
				            :where
										[?comment :comment/post ?post]
										[?comment :comment/user ?user]]
							db (:db/id user) (:db/id post))
				(map #(d/entity db %))
				)))
		Bir entity'nin ilişkilerini serbestçe dolaşabilirsin. N+1 problemi olmaz.
		ex:
			(defn cl-comment
				"clientizes a comment."
				[cmt]
				{:id (:comment/id cmt)
				:content (:comment/content cmt)
				:author {:id (-> cmt :comment/author :user/id)}
				:post {:id (-> cmt :comment/post :post/id)}})
		Veriyi lokalmiş gibi düşünebilirsin.
	Querying: Datalog vs. Entities
		Entities: navigating around database
		Pull API: combines both
	Fixture Data:
		example based testing

# Article: Separation of Concerns in Datomic Query: Datalog Query and Pull Expressions id=g12885

	ref
		[Separation of Concerns in Datomic Query: Datalog Query and Pull Expressions](https://cognitect.com/blog/2017/4/21/separation-of-concerns-in-datomic-query-datalog-query-and-pull-expressions)
	Homoiconicity: Code is data. Data is code.
		Bu kavram çok büyük güç katar.
	Örnek: Datalog sorguları
		Data olarak ifade edilir, string olarak değil.
		Dolayısıyla bunları compose edebiliriz/validate edebiliriz.
	ex01: Datalog sorgusu
		(d/q '[:find [?lname ?fname]
			:in $ ?ssn
			:where
			[?e :person/ssn ?ssn]
			[?e :person/first-name ?fname]
			[?e :person/last-name ?lname]]
		(d/db conn)
		"123-45-6789")
		;; => ["Murray" "William"]
	ex02: Dönen veriyi bir map içine koy:
		(->> (d/q '[:find [?lname ?fname]
						:in $ ?ssn
						:where
						[?e :person/ssn ?ssn]
						[?e :person/first-name ?fname]
						[?e :person/last-name ?lname]]
					(d/db conn)
					"123-45-6789")
		 (zipmap [:last-name :first-name]))
		;; => {:last-name "Murray" :first-name "William"}
	ex03: Pull API ile
		(d/pull (d/db conn)
		;; pull pattern - attributes to retrieve
		[:person/first-name
			:person/last-name
			{:person/address [:address/zipcode]}]
		;; lookup-ref - entity to find
		[:person/ssn "123-45-6789"])
		;; =>
		{:person/first-name "William"
		 :person/last-name "Murray"
		 :person/address {:address/zipcode "02134"}}
		Burada entity id veya lookup reference verdik, pull'a.
	ex04: Pull API inside query
		(d/q '[:find [
			;; pull expression
			(pull ?e
			;; pull pattern
			[:person/first-name
				:person/last-name
				{:person/address [:address/zipcode]}])]
			:in $ ?zip
			:where
			[?a :address/zipcode ?zip]
			[?e :person/address ?a]]
		(d/db conn)
		"02134")
		Pull pattern datadır. Bu yüzden, bunu argüman olarak paslayabiliriz:
	ex05: Pull pattern as argument
		(defn find-by-zip [db zip pattern]
			(d/q '[:find (pull ?e pattern)
						:in $ ?zip pattern
						:where
						[?a :address/zipcode ?zip]
						[?e :person/address ?a]]
					db
					zip
					pattern))
		(find-by-zip (d/db conn)
								"02134"
								[:person/first-name
									:person/middle-name
									:person/last-name
									{:person/address [:address/zipcode]}])
	ex06: Compose etme
		(def address-pattern [:address/street
													:address/city
													:address/state
													:address/zipcode])
		(find-by-zip (d/db conn)
								"02134"
								[:person/first-name
									:person/middle-name
									:person/last-name
									{:person/address address-pattern}])

# Article: Datomic Cheat Sheet Series - Queries (Part 4) - Pull - Curious... id=g12887

	ref
		[Datomic Cheat Sheet Series - Queries (Part 4) - Pull - Curious...](https://curiousprogrammer.dev/blog/datomic-cheat-sheet-series-queries-part-4-pull/)

# Article: Max-Datom.com Exercises id=g12888

	ref
		~/projects/study/clj/ex/study_datomic/max-datom.com/exercises-max-datom.com.md
	t0

# Example: Day of Datomic Cloud Sample Data id=g13521

[cognitect-labs/day-of-datomic-cloud](https://github.com/cognitect-labs/day-of-datomic-cloud)

rfr: `/Users/mertnuhoglu/codes/clj/ex/day-of-datomic-cloud/deps.edn`

Previous step: rfr: 04. Sample Data <url:file:///~/prj/study/clj/articles-datomic.md#r=g13507>

Step01: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/day_of_datomic/cloud/hello_world.clj`

# Example: Get Current Datomic Schema id=g13527

[How can I get the current Datomic schema? - Stack Overflow](https://stackoverflow.com/questions/26575320/how-can-i-get-the-current-datomic-schema)

```clj
(def schema 
	'[:find ?attr ?type ?card
		:where
		[_ :db.install/attribute ?a]
		[?a :db/valueType ?t]
		[?a :db/cardinality ?c]
		[?a :db/ident ?attr]
		[?t :db/ident ?type]
		[?c :db/ident ?card]]
)
(d/q schema db)
```

