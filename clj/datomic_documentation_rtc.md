
# Datomic Documentation 
  id:: 6104bcb1-0d38-48f3-a917-806e0dcf067c

[Introduction | Datomic](https://docs.datomic.com/on-prem/overview/introduction.html)

## Schema

[Schema | Datomic](https://docs.datomic.com/on-prem/schema/schema.html)

Required schema attributes:

- `:db/ident` - unique name of an attribute
- `:db/valueType` - `db.type/ref` `db.type/string` `db.type/tuple` …
- `:db/cardinality` - `db.cardinality/one`

### Tuples 
  id:: 9a6169d2-6e66-41ca-a48e-076b9dc18ec7

3 tür tuple var:

- Composite tuples
- Heterogeneous fixed length tuples
- Homogeneous variable length tuples

[Composite Tuples:](https://docs.datomic.com/on-prem/schema/schema.html#composite-tuples)

```clj
{:db/ident :reg/semester+course+student
 :db/valueType :db.type/tuple
 :db/tupleAttrs [:reg/course :reg/semester :reg/student]
 :db/cardinality :db.cardinality/one
 :db/unique :db.unique/identity}
```

```clj
[{:reg/course [:course/id "BIO-101"]
  :reg/semester [:semester/year+season [2018 :fall]]
  :reg/student [:student/email "johndoe@university.edu"]}]
```

[Heterogeneous Tuples:](https://docs.datomic.com/on-prem/schema/schema.html#heterogeneous-tuples)

```clj
{:db/ident :player/location
 :db/valueType :db.type/tuple
 :db/tupleTypes [:db.type/long :db.type/long]
 :db/cardinality :db.cardinality/one}
```

```clj
(def data [{:player/handle "Argent Adept"
            :player/location [100 0]}])
```

[Homogeneous Tuples:](https://docs.datomic.com/on-prem/schema/schema.html#homogeneous-tuples)

`:db/tupleTypes` kendisi homojen tuple olarak tanımlıdır:

```clj
;; built in to Datomic
[{:db/ident :db/tupleAttrs
  :db/valueType :db.type/tuple
  :db/tupleType :db.type/keyword
  :db/cardinality :db.cardinality/one}
 {:db/ident :db/tupleTypes
  :db/valueType :db.type/tuple
  :db/tupleType :db.type/keyword
  :db/cardinality :db.cardinality/one}]
```

### Optional schema attributes

[Optional schema attributes](https://docs.datomic.com/on-prem/schema/schema.html#operational-schema-attributes)

- `:db/doc`
- `:db/unique`
  - `:db.unique/value`
  - `:db.unique/identity` - upsert için
- `:db.attr/preds` - belli predicatelarla değer kontrolü yapmak için
- `:db/index` - boolean
- `:db/fulltext` - boolean
- `:db/isComponent` - boolean
- `:db/noHistory` - boolean

### External keys

[External keys](https://docs.datomic.com/on-prem/schema/schema.html#external-keys)

Entity id, dahili key. Buna ek olarak kendin de tekil bir değer de sağlayabilirsin. Bu external key oluyor. Bunun için o atributun `:db/unique` olması lazım.

### Attribute definition

Transaction map olarak tanımlamak idiyomatiktir:

```clj
{:db/ident :person/name
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "A person's name"}
```

### Attribute Predicates

```clj
(ns datomic.samples.attr-preds)

(defn user-name?
  [s]
  (<= 3 (count s) 15))

{:db/ident :user/name,
 :db/valueType :db.type/string,
 :db/cardinality :db.cardinality/one,
 :db.attr/preds 'datomic.samples.attr-preds/user-name?}
```

```clj
{:cognitect.anomalies/category
 :cognitect.anomalies/incorrect, 
 :cognitect.anomalies/message
 "Entity 42 attribute :user/name value This-name-is-too-long
failed pred datomic.samples.attr-preds/user-name?"
 :db.error/pred-return false}
```

### Entity Specs

Specs gibi spesifikasyonlar tanımlamak için:

- zorunlu alanlar
- belirli veri alanlarına dair kısıtlamalar

#### Required Attributes

`:user/name` ve `:user/email` alanlarını zorunlu yapmak:

```clj
{:db/ident :user/validate
 :db.entity/attrs [:user/name :user/email]}
```

Şimdi bir transactionda bu zorunluluğu kontrol etmek için:

```clj
{:user/name "John Doe"
 :db/ensure :user/validate}
```

### Entity Predicates

[Entity Predicates](https://docs.datomic.com/on-prem/schema/schema.html#entity-predicates)

Attribute predicate gibi, ancak tüm entity transactionı kaydedilirken test edilir. Eğer bir tane predicate bile yanlış dönerse, tüm transaction iptal olur.

### Partitions

	| Partition     | Purpose                                  |
	|---------------|------------------------------------------|
	| :db.part/db   | System partition, used for schema        |
	| :db.part/tx   | Transaction partition                    |
	| :db.part/user | User partition, for application entities |

`:db.part/tx` - Bunlar transaction entitylerine ait bilgileri içerir.

## Changing Schema 

[Changing Schema | Datomic](https://docs.datomic.com/on-prem/schema/schema-change.html)

### Schema Alteration

### Data Modeling

#### Use :db/ident for Enumerations

Kategorik kümeler (domain setler) için `:db/ident` kullan. Aynı bir atribut tanımlar gibi. Ama sadece ismini tanımla, ilgili şeyin.

```clj
[{:db/ident :artist/country
  :db/valueType :db.type/ref
  :db/cardinality :db.cardinality/one
  :db/doc "An artist's country of residence"}

 {:db/ident :country/CA}

 {:db/ident :country/JP}]
```

```clj
[{:artist/name "Leonard Cohen"
  :artist/country :country/CA}]
```

### Schema Limits

Maksimum db objesi sayısı (atribut, value types vs.) 2^20 olabilir.

`:db.*` namespaceleri dahili kullanıma sınırlıdır. `:db/doc` ve `:db/ident` dışındaki kelimeleri kullanmamalısın.

### Identity and Uniqueness

####Entity Identifiers

Tüm entitylerin veritabanı tekil entity id'si bulunur. Bu genellikle dokümantasyonda `e` sembolüyle gösterilir.

#### Idents

Programatik bir isim (keyword) sağlar. Böylece entity id yerine bunu kullanabilirsin.

```{r}
;; elided for brevity
{:db/ident :person/loves}

[42 :person/loves :pizza]
```

#### Unique Identities

```{r}
;; elided for brevity
{:db/ident :inv/sku
 :db/valueType :db.type/string
 :db/unique :db.unique/identity
 ...}
```

#### Unique Values

an attribute with `:db/unique` set to `:db.unique/value`.

#### Squuids

Global olarak tekil id oluşturmak için. 

`:db.type/uuid` tipinde atributlarda kullanılır.

#### Lookup Refs 
  id:: bb299957-c1b6-45fa-acf8-556a891bdb67

Bir listedir. Listenin ilk elemanı bir atribut ismi, ikincisi ise onun değeridir.

Bunlar external key olarak bir entity'yi bulmak için kullanılır.

```clj
[:person/email "joe@example.com"]
```

Transactionlarda mevcut varlıklara ref vermek için kullanabilirsin:

```clj
{:db/id [:person/email "joe@example.com"]
 :person/loves :pizza}
```

## Transactions 

[Transactions | Datomic](https://docs.datomic.com/on-prem/transactions/transactions.html)

Tüm yazma işlemleri ACID transaction olarak gerçekleşir. Yani ya hepsi birden yazılır, ya hiçbiri yazılmaz.

### Transaction structure 
  id:: 6d62456b-92a9-4d45-8858-83a3e4a7cc0e

A transaction is simply a list of lists and/or maps, each of which is a statement in the transaction.

List forms:

```clj
[:db/add entity-id attribute value]

[:db/retract entity-id attribute value]
[:db/retract entity-id attribute]
[data-fn args*]
```

Son form: invocation of a data function.

Map forms:

```clj
{:db/id entity-id
 attribute value
 attribute value
 ... }
```

Map formuyla, liste formu birbirine denktir. Dahili olarak, map formları list formlarına çevrilir.

Her bir attribute/value ikilisi bir `:db/add` listesine dönüştürülür.

Eğer entity-id vermediysen, datomic bir geçici entity-id oluşturur:

```clj
[:db/add entity-id attribute value]
[:db/add entity-id attribute value]
...
```

### Identifying entities

#### Temporary ids 
  id:: 872db05e-de9f-4517-a5ce-a0653469e64a

Yeni bir varlık eklerken, geçici id kullanırsın.

```clj
[[:db/add "jdoe" :person/first "Jan"]
 [:db/add "jdoe" :person/last "Doe"]]
```

Burada `jdoe` geçici id.

Eğer atributlardan birisi tekilse ve sen mevcut bir varlığın tekil değerini kullandıysan, bu durumda geçici id insert için değil update işlemi yapmada kullanılır.

#### Existing entity ids

Bir varlığın bir atributunun değerini değiştirmek için öncelikle entity id'yi bilmen lazım. Bunu bulmak için harici anahtarları kullanabilirsin:

```clj
[:find ?e :in $ ?email :where [?e :person/email ?email]]

;; transaction:
{:db/id 17592186046416
 :customer/status :active}
```

Alternatif olarak lookup ref kullanarak da transactionı çağırabilirsin:

```clj
{:db/id [:customer/email "joe@example.com"]
 :customer/status :active}
```

#### Entity identifiers

Bir varlığın eğer `:db/ident` atributu varsa, onun değeri entity id yerine kullanılabilir.

Bu sayede keywordlerle varlıklara bağ kurabilirsin.

`:db/ident` ayrıca enumlar için kullanılabilir.

```clj
[:db/add :person/name :db/doc "A person's full name"]
```

### Building transactions

#### Yeni bir varlığa veri eklemek:

```clj
[{:person/name "Bob"
  :person/email "bob@example.com"}]
```

#### Mevcut varlığa veri eklemek:

Önce mevcut entity id'yi bulmalısın.

```clj
[:find ?e :in $ ?email :where [?e :person/email ?email]]

;; transaction:
{:db/id 17592186046416
 :customer/status :active}
```

#### Varlık referansları eklemek:

Referans, geçici id de olabilir (eğer varlık mevcut transaction içinde oluşturulmaktaysa), gerçek entity id de olabilir.

Örnek: İki varlık. Birbirine ref ediyorlar. 

```clj
[{:db/id "bobid"
  :person/name "Bob"
  :person/spouse "aliceid"}
 {:db/id "aliceid"
  :person/name "Alice"
  :person/spouse "bobid"}]
```

#### Cardinality many transactions:

```clj
[{:db/id #db/id[:db.part/user]
  :person/name "Bob"
  :person/email "bob@example.com"
  :person/aliases ["Robert" "Bert" "Bobby" "Curly"]}]
```

e. Nested maps in transactions:

Datomic iç mapleri bağımsız varlıklara çevirir.

- Eğer iç map `:db/id` içermezse, datomic otomatik bir id verir.
- İç mape giden referans bir komponent atributu olmalı veya iç mapte unique bir atribut olmalı.

Örnek: Komponent atributu:

```clj
[{:db/id order-id
  :order/lineItems [{:lineItem/product chocolate
                     :lineItem/quantity 1}
                    {:lineItem/product whisky
                     :lineItem/quantity 2}]}]
```

Flat formatta bu varlıkları oluşturmak çok daha uzun olur:

```clj
[{:db/id order-id
  :order/lineItems [item-1-id, item-2-id]}
 {:db/id item-1-id
  :lineItem/product chocolate
  :lineItem/quantity 1}
 {:db/id item-2-id
  :lineItem/product whisky
  :lineItem/quantity 2}]
```

#### Retracting data:

İki şekilde veriyi geri alabilirsin:

1. Eav belirtirsin. İlgili atributun verili değerini geri alırsın.
2. Sadece E belirtirsin. O zaman varlığın tüm atributlarını geri alırsın.

```clj
[:find ?e :in $ ?email :where [?e :person/email ?email]]

;; transaction:
[:db/retract 17592186046416 :person/name "Robert"]
```

Lookup ref ile de yapabilirsin:

```clj
;; transaction:
[:db/retract [:person/email "bob@example.com"] :person/name "Robert"]
```

### Tempid Data Structure

```java
temp_id = Peer.tempid(":db.part/user");
```

Literal formatta temp id oluşturmak için:

```clj
#db/id[partition-name value*]
```

Partitionlar şunlardan biri olmalı:

- `:db.part/db`
- `:db.part/tx`
- `:db.part/user`

`value` opsiyonel bir negatif sayı olabilir. Eğer aynı sayıyla temp id oluşturursan, her seferinde aynı temp id'yi döner.

### Processing Transactions 

[Processing Transactions | Datomic](https://docs.datomic.com/on-prem/transactions/transaction-processing.html)

Transactor tüm transactionları bir kuyruğa koyar ve onları sırayla işler.

Sorguları ise transactor işlemez. Onları peerlar halleder. Onlar kilitleme gerektirmez. Bu yüzden hızlıca işlenebilir.

#### Submitting transactions

`transact` metoduyla transactionları gönderirsin. Bu bir `Future<Map>` döner. Bu dönen map, transactionın sonucunu içerir. `deref` metoduyla txin sonucunu görebilirsin.

#### Transaction Timeouts

`transact` metodu senkrondur. Yani tx bitinceye kadar bekler, ondan sonra dönüş yapar. `transactAsync` ise asenkrondur. Hemen döner. 

#### Monitoring transactions

Peerlar txlerin başarılı olup olmadığını bilmezler. Bunun için ayrı bir sorgu yapmalılar.

`txReportQueue` metoduyla tx bildirimlerine dair bir kuyruk (queue, stream) çekebilirler.

Bu stream, her gelen txin sonucunu bildirir, hangi peerden gelirse gelsin. 

#### Reified transactions

Bir tx işlendiği vakit, bir transaction entity oluşturulur. Bu varlığın tek bir atributu bulunur: `:db/txInstant`. Hangi anda txin işlendiğini kaydeder.

Tx içindeki tüm datomlar txe ref tutar: `:tx` alanıyla.

Txe yeni bilgiler ekleyebilirsin. 

#### Explicit :db/txInstant

`:db/txInstant` değerini değiştirebilirsin. Ama bunu yaparken, mevcut txlerden daha eski ve güncel saatten daha ileri olmamalı.

#### Redundancy Elimination

Eğer bir datomla aynı değerlere sahip fakat sadece tx idsi farklı bir datom varsa, bu gereksizdir. Bunlar temizlenir.

Ama txler hiçbir zaman gereksiz olmaz. Aynı şekilde schema atributları da.

### Transaction Functions 
  id:: c7c0f40b-c30a-4699-a9e5-2732e0ffaf1b

[Transaction Functions | Datomic](https://docs.datomic.com/on-prem/transactions/transaction-functions.html)

Dahili tx fonksiyonları: `:db/add` `:db/retractEntity` `:db/cas`

#### :db/retractEntity

Bir varlığın tüm atributlarını geri alır. Ayrıca o varlığa verilen referansları ve o varlığın komponentlerini de.

```clj
[[:db/retractEntity id-of-jane]
 [:db/retractEntity [:person/email "jdoe@example.com"]]]
```

#### :db/cas

cas = compare-and-swap. 4 argüman alır: eid, atribut, eski değer, yeni değer.

Eğer atributun değeri eski değerse, bunu yeni değerle değiştirir. Aksi taktirde hata fırlatır.

Eski değer yerine `nil` koyarsan, o zaman atributun mevcut değeri olmadığını doğrulamak ister.

```clj
[[:db/cas 42 :account/balance 100 110]]
```

### ACID

[ACID | Datomic](https://docs.datomic.com/on-prem/transactions/acid.html)

### Client Synchronization

[Client Synchronization | Datomic](https://docs.datomic.com/on-prem/transactions/client-synchronization.html)

## Query and Pull

### Executing Queries

[Executing Queries | Datomic](https://docs.datomic.com/on-prem/query/query-executing.html)

Sorgulama yapmak için, veritabanı değerini bir bağlantıdan çekmeliyiz:

```java
Database db = conn.db();
```

Bu veritabanı bir değerdir. Asla değişmez.

Veritabanı listesinin üyeleri 5 öğeli bir tupledır:

```clj
[entity attribute value transaction added?]
```

#### q

`datomic.client.api/q` ana fonksiyon.

- `:query` - query map, list veya string olabilir
  - `:find`
	- `:with` - opsiyonel.
	- `:in` - optional. Eğer yoksa `:in $` olur
	- `:where`
- `:args` - data sources

#### qseq

q'nun bir varyantı. pulls ve xforms yapar tembel bir şekilde.

#### Unification

```clj
;;which 42-year-olds like what?
[:find ?e ?x 
 :where [?e :age 42] [?e :likes ?x]]
```

Burada `?e` değişkeni birleştirilir (unification).

#### List form vs. Map form

İnsanlar genellikle list formunda yazmayı tercih eder:

```clj
[:find ?e
 :in $ ?fname ?lname
 :where [?e :user/firstName ?fname]
[?e :user/lastName ?lname]]
```

Programatik olaraksa, map formu daha kolaydır. List formları bu forma çevrilir otomatik:

```clj
{:find [?e]
 :in [$ ?fname ?lname]
 :where [[?e :user/firstName ?fname]
 [?e :user/lastName ?lname]]}
```

#### Work with Data Structures, Not Strings

Veri yapılarıyla sorgu yaparsan, sql injection saldırılarına karşı korunmuş olursun. Çünkü string interpolation, sanitation, escaping yapmak gerekmez.

#### Clause order

`:where` cümleciğinde en sınırlayıcı filtreyle ilk başta başlarsan, sorgu performansı daha iyi olur.

```clj
;; query
[:find [?name ...]
 :in $ ?artist
 :where [?release :release/name ?name]
        [?release :release/artists ?artist]]
;; inputs
db, mccartney
```

```clj
;; query
[:find [?name ...]
 :in $ ?artist
 :where [?release :release/artists ?artist]
        [?release :release/name ?name]]
;; inputs and result same as above
```

Bu ikinci sorgu, 50 kez daha hızlı çalışır.

#### Queries and Peer Memory

Sorgulamalar, Peer tarafından uygulama lokal bellek kullanılarak çalıştırılır. Ara sonuçlar diske yazılmaz. Bu yüzden eğer çok büyük bir veri kümesi dönüyorsa, problem çıkabilir. Bu durumda sorguyu küçük parçalara bölmelisin. Bunun için [datoms](https://docs.datomic.com/on-prem/query/indexes.html#datoms-api) ve [index-range](https://docs.datomic.com/on-prem/query/indexes.html#avet) API'lerini kullanabilirsin.

#### Query Caching

Sorgularda otomatik keşleme yapılır. Keşleme, q'nun ilk argümanına dayanarak yapılır. Birbirinin aynısı olan sorguların sonuçları keşlenir.

Bunun daha etkili olması için:

- Parametrik sorgulamalar yap (queries with multiple inputs), dinamik sorgulamalar yerine

### Query Reference

[Datomic Queries and Rules | Datomic](https://docs.datomic.com/on-prem/query/query.html)

#### Why Datalog

- Simple
- Declarative
- Logic based: Joinleri açıkça belirtmene gerek yok.
- Embedded: Sorgulama motoru ve veri, lokal olarak çalışır. SQL gibi client-server modelinde değil.

#### Query Grammar

#### Queries


```clj
[:find variables 
 :where clauses]
```

```clj
[[sally :age 21] 
 [fred :age 42] 
 [ethel :age 42]
 [fred :likes pizza] 
 [sally :likes opera] 
 [ethel :likes sushi]]
```

```clj
[:find ?e 
 :where [?e :age 42]]
```

- `[?e :age 42]`: data clause

A tuple satisfies a clause if its constants match. Variables in the data pattern are then bound to the coresponding part of the matching tuple.

#### Blanks

```clj
;; query
[:find ?x 
 :where [_ :likes ?x]]
```

#### Implicit Blanks

```clj
;; unnecessary trailing blanks
[_ :release/name ?release-name _ _]
```

#### Inputs

`$`: sorgulanan veritabanının yerine geçer.

Data patternlar, veritabanına `$` sembolüyle ref verir.

Girdilere açıkça isim vermek için `:in` cümleciği kullanılır.

```clj
;; query
[:find ?release-name
 :in $
 :where [$ _ :release/name ?release-name]]

;; inputs & result same as previous
```

#### Multiple Inputs

Queries can be parameterized at runtime with variable bindings.

Örnek: Bu sorgu, iki girdi alır. İlki, veritabanı. İkincisi: scalar variable binding to limit releases to those perfomed by John Lennon.

```clj
;; query
[:find ?release-name
 :in $ ?artist-name
 :where [?artist :artist/name ?artist-name]
        [?release :release/artists ?artist]
        [?release :release/name ?release-name]]

;; inputs
db, "John Lennon"
```

#### Pattern Inputs

Input bir pull patterni olabilir.

Örnek: `pattern` burada `pull` ifadesinde kullanılan atributları tanımlar.

```clj
;; query
'[:find (pull ?e pattern)
  :in $ ?name pattern
  :where [?e :artist/name ?name]]

;; args
[db "The Beatles" [:artist/startYear :artist/endYear]]

;; example in 1-arity form
(d/q {:query '[:find (pull ?e pattern)
               :in $ ?name pattern
               :where [?e :artist/name ?name]]
      :args [db "The Beatles" [:artist/startYear :artist/endYear]]})
```

#### Separation of Concerns

Varlıkları bulma işlemiyle, bu varlıklardan bilgi çekme işlemini ayırt eder, pull API.

```clj
(def songs-by-artist
  '[:find (pull ?t pattern)
    :in $ pattern ?artist-name
    :where
    [?a :artist/name ?artist-name]
    [?t :track/artists ?a]])

(def track-releases-and-artists
  [:track/name
   {:medium/_tracks
    [{:release/_media
      [{:release/artists [:artist/name]}
       :release/name]}]}])

;; Pull only the :track/name
(d/q songs-by-artist db [:track/name] "Bob Dylan")
;;=>
  ([#:track{:name "California"}]
   [#:track{:name "Grasshoppers in My Pillow"}]
   [#:track{:name "Baby Please Don't Go"}]
   [#:track{:name "Man of Constant Sorrow"}]
   [#:track{:name "Only a Hobo"}]
  ...)
```

```clj
;; Use a different pull pattern to get the track name, the release name, and the artists on the release.
(d/q songs-by-artist db track-releases-and-artists "Bob Dylan")
;;=>
([{:track/name "California",
   :medium/_tracks
   #:release{:_media #:release{:artists [#:artist{:name "Bob Dylan"}], :name "A Rare Batch of Little White Wonder"}}}]
 [{:track/name "Grasshoppers in My Pillow",
   :medium/_tracks
   #:release{:_media #:release{:artists [#:artist{:name "Bob Dylan"}], :name "A Rare Batch of Little White Wonder"}}}]
```

#### Bindings 
  id:: 3de017b1-1bf5-4db6-a972-a097b1e3d6ca

- rfr: 
	- web: [Bindings](https://docs.datomic.com/on-prem/query/query.html?search=Tuple%20Binding#bindings)
	- exmp: Binding Forms || ((f9aab781-594d-4dd7-85bc-a3e7a69832d6))
	- exmp: Binding of Transformation Function Results || ((150d2443-2abe-4a95-854d-4e08706d0a04))
	- exmp: Find Specifications || ((0c70d19a-64e4-4e10-aec0-70a87092fa6f))

| Binding Form | Binds      |
|--------------|------------|
| ?a           | scalar     |
| [?a ?b]      | tuple      |
| [?a …]       | collection |
| [ [?a ?b ] ] | relation   |

##### Tuple Binding

```clj
;; query
[:find ?release
 :in $ [?artist-name ?release-name]
 :where [?artist :artist/name ?artist-name]
        [?release :release/artists ?artist]
        [?release :release/name ?release-name]]

;; inputs
db, ["John Lennon" "Mind Games"]
```

##### Collection Binding

```clj
;; query
[:find ?release-name
 :in $ [?artist-name ...]
 :where [?artist :artist/name ?artist-name]
        [?release :release/artists ?artist]
        [?release :release/name ?release-name]]

;; inputs
db, ["Paul McCartney" "George Harrison"]
```

##### Relation Binding

```clj
;; query
[:find ?release
 :in $ [[?artist-name ?release-name]]
 :where [?artist :artist/name ?artist-name]
        [?release :release/artists ?artist]
        [?release :release/name ?release-name]]

;; inputs
db,  [["John Lennon" "Mind Games"] 
      ["Paul McCartney" "Ram"]]
```

#### Find Specifications 
  id:: 24874b63-7707-4220-857c-fb4dead840bf

rfr: [Datomic Queries and Rules | Datomic](https://docs.datomic.com/on-prem/query/query.html#find-specifications)

Bindings: girdileri tarif eder

Find specifications: sonuçları tarif eder

	| Find Spec     | Returns       | Java Type Returned  |
	|---------------|---------------|---------------------|
	| :find ?a ?b   | relation      | Collection of Lists |
	| :find [?a …]  | collection    | Collection          |
	| :find [?a ?b] | single tuple  | List                |
	| :find ?a .    | single scalar | Scalar Value        |

Relation find spec:

```clj
;; query
[:find ?artist-name ?release-name
 :where [?release :release/name ?release-name]
        [?release :release/artists ?artist]
        [?artist :artist/name ?artist-name]]

;; inputs
db
;;=>
#{["George Jones" "With Love"] 
  ["Shocking Blue" "Hello Darkness / Pickin' Tomatoes"] 
  ["Junipher Greene" "Friendship"]
  ...}
```

Collection find spec:

```clj
;; query
[:find [?release-name ...]
 :in $ ?artist-name
 :where [?artist :artist/name ?artist-name]
        [?release :release/artists ?artist]
        [?release :release/name ?release-name]]

;; inputs
db "John Lennon"
;;=>
["Power to the People" 
 "Unfinished Music No. 2: Life With the Lions" 
 "Live Peace in Toronto 1969" 
 "Live Jam"
 ...]
```

Single tuple find spec:

```clj
;; query
[:find [?year ?month ?day]
 :in $ ?name
 :where [?artist :artist/name ?name]
        [?artist :artist/startDay ?day]
        [?artist :artist/startMonth ?month]
        [?artist :artist/startYear ?year]] 

;; inputs
db "John Lennon"
;;=>
[1940 10 9]
```

Scalar find spec:

```clj
;; query
[:find ?year .
 :in $ ?name
 :where [?artist :artist/name ?name]
        [?artist :artist/startYear ?year]]

;; inputs
db "John Lennon"
;;=>
1940
```

#### Return Maps 
  id:: fcde5413-4a77-4785-9133-7cbc8314df81

Tuple yerine map döndürmeyi sağlar.

| keyword | symbols become |
|---------|----------------|
| :keys   | keyword keys   |
| :strs   | string keys    |
| :syms   | symbol keys    |

```clj
;; query
[:find ?artist-name ?release-name
 :keys artist release
 :where [?release :release/name ?release-name]
 [?release :release/artists ?artist]
 [?artist :artist/name ?artist-name]]

;; inputs
db
;;=>
#{{:artist "George Jones" :release "With Love"}
  {:artist "Shocking Blue" :release "Hello Darkness / Pickin' Tomatoes"} 
  {:artist "Junipher Greene" :release "Friendship"}
  ...}
```

#### Not Clauses 
  id:: 1faf7bac-f188-4e01-8da8-04763f076de9

a. not

```clj
(src-var? 'not' clause+)
```

`src-var?` veri kaynağıdır.

Örnek: Kanadalı olmayan artistleri bulmak:

```clj
;; query
[:find (count ?eid) .
 :where [?eid :artist/name]
        (not [?eid :artist/country :country/CA])]

;; inputs
db
```

b. not-join

```clj
(src-var? 'not-join' [var+] clause+)
```

Örnek: 1970'te albüm çıkartmayan artistlerin sayısı.

Burada `not-join` ile sadece `?artist` değişkeninin birleşeceğini belirtiyoruz. `?release` değişkeninin kuşatan sorguyla birleşmesi gerekmez.

```clj
;; query
[:find (count ?artist) .
       :where [?artist :artist/name]
       (not-join [?artist]
         [?release :release/artists ?artist]
         [?release :release/year 1970])]
;; inputs
db
```

Örnek: "Live at Carnegie Hall" isimli bir release çıkarmış olan artistlerden, adı "Bill Withers" olmayanların releaselerinin sayısı:

```clj
;; query
[:find (count ?r) .
 :where [?r :release/name "Live at Carnegie Hall"]
        (not-join [?r]
          [?r :release/artists ?a]
          [?a :artist/name "Bill Withers"])]

;; inputs
db
```

##### How Not Clauses Work

Not cümleciklerini alt birer sorgu olarak düşün. Buradaki değişkenler ve veri kaynakları, kuşatan sorguyla birleşir. Bu alt sorgunun sonuçları, kuşatan sorgunun sonuçlarından çıkartılırr. 

#### Or Clauses 
  id:: 43e9c64f-05b2-4e58-b7ec-f4966ce4bf68

```clj
(src-var? 'or' (clause | and-clause)+)
```

```clj
;; query
[:find (count ?medium) .
       :where (or [?medium :medium/format :medium.format/vinyl7]
                  [?medium :medium/format :medium.format/vinyl10]
                  [?medium :medium/format :medium.format/vinyl12]
                  [?medium :medium/format :medium.format/vinyl])]

;; inputs
db
```

Herhangi bir cümlecik and-clause olabilir.

```clj
;; query
[:find (count ?artist) .
 :where (or [?artist :artist/type :artist.type/group]
            (and [?artist :artist/type :artist.type/person]
                 [?artist :artist/gender :artist.gender/female]))]

;; inputs
db
```

Bir or cümleciğindeki tüm cümlecikler aynı değişkenleri kullanmalıdır. 

b. `or-join`

`or-join` ile hangi değişkenlerin birleştirileceğini belirtirsin:

```clj
(src-var? 'or-join' [rule-vars] (clause | and-clause)+)
```

```clj
;; query
[:find (count ?release) .
      :where [?release :release/name]
      (or-join [?release]
        (and [?release :release/artists ?artist]
             [?artist :artist/country :country/CA])
        [?release :release/year 1970])]
;; inputs
db
```

##### How Or Clauses Work

Or cümleciklerini anonim rule gibi düşünebilirsin. `src-vars` her ikisinde de sadece en üst seviyedeki cümlecikte tanımlanabilir.

#### Expression Clauses

Expression cümlecikleriyle, herhangi bir clojure fonksiyonu çağrılabilir.

Çağrılan fonksiyonlar saf olmalı, yani yan etkisi olmamalı. Yani her zaman aynı girdiye aynı çıktıyı üretmeli.

İki tür şekilde tanımlanabilir:

```clj
[(predicate ...)]
[(function ...) bindings]
```

##### Predicate Expressions 
  id:: b23094c0-50a2-4f30-a54e-59ca8ae091a3

Eğer binding yoksa, bir yüklem (predicate) olarak yorumlanır. Yani truth değeri döner. `null` ve `false` yanlıştır. Diğer her şey doğrudur.

```clj
;; query
[:find ?name ?year
 :where [?artist :artist/name ?name]
        [?artist :artist/startYear ?year]
        [(< ?year 1600)]]

;; inputs
db
```

##### Function Expressions 
  id:: 720fadaa-5bb4-4dcf-9bb3-c24f4f5bd2ff

Fonksiyon ifadelerinin çıktısı mantıksal bir değişkene bağlanır.

```clj
[:find ?track-name ?minutes
 :in $ ?artist-name
 :where [?artist :artist/name ?artist-name]
        [?track :track/artists ?artist]
        [?track :track/duration ?millis]
        [(quot ?millis 60000) ?minutes]
        [?track :track/name ?track-name]]

;; inputs 
db, "John Lennon"
```

İfade cümlecikleri içiçe yazılamaz:

```clj
;; this query will not work!!!
[:find ?celsius .
 :in ?fahrenheit
 :where [(/ (- ?fahrenheit 32) 1.8) ?celsius]]
```

Bunun yerine ardışık ifade cümlecikleri yazılır:

```clj
;; query
[:find ?celsius .
 :in ?fahrenheit
 :where [(- ?fahrenheit 32) ?f-32]
        [(/ ?f-32 1.8) ?celsius]]

;; inputs
212
```

#### Built-in Expression Functions and Predicates 
  id:: 0cc8801b-ddab-4ada-a4fe-49bf599382aa

Dahili fonksiyonlar ve yüklemler:

- `= != < <= > >=`
- `+ - * /`
- `clojure.core` fonksiyonları, `eval` hariç
- Aşağıdaki fonksiyonlar

01. get-else

```clj
[(get-else src-var ent attr default) ?val-or-default]
```

```clj
;; query
[:find ?artist-name ?year
 :in $ [?artist-name ...]
 :where [?artist :artist/name ?artist-name]
        [(get-else $ ?artist :artist/startYear "N/A") ?year]]

;; inputs
db, ["Crosby, Stills & Nash" "Crosby & Nash"]
;;=>
#{["Crosby, Stills & Nash" 1968] 
  ["Crosby & Nash" "N/A"]}
```

02. get-some

```clj
[(get-some src-var ent attr+) [?attr ?val]]
```

Örnek: Bulduğun entity'nin önce `:country/name` atributuna bak. Eğer bulamazsan `:artist/name` atributunu döndür.

```clj
;; query
[:find [?e ?attr ?name]
 :in $ ?e
 :where [(get-some $ ?e :country/name :artist/name) [?attr ?name]]]

;; inputs
db, :country/US
;;=>
[:country/US 84 "United States"]
```

03. ground

```clj
[(ground const) binding]
```

```clj
[(ground [:a :e :i :o :u]) [?vowel ...]]
```

04. fulltext

```clj
[(fulltext src-var attr search) [[?ent ?val ?tx ?score]]]
```

```clj
;; query
[:find ?entity ?name ?tx ?score
 :in $ ?search
 :where [(fulltext $ :artist/name ?search) [[?entity ?name ?tx ?score]]]]

;; inputs
db, "Jane"
;;=>
#{[17592186047274 "Jane Birkin" 2839 0.625] 
  [17592186046687 "Jane" 2267 1.0] 
  [17592186047500 "Mary Jane Hooper" 3073 0.5]}
```

05. missing?

Eğer bir varlığın ilgili atributunun değeri yoksa, true döner.

```clj
;; query
[:find ?name
 :where [?artist :artist/name ?name]
        [(missing? $ ?artist :artist/startYear)]]

;; inputs
db
```

06. tuple

```clj
[(tuple ?a ...) ?tup]
```

Verilen değerleri bir tuple olarak döner.

```clj
;; query
[:find ?tup
 :in ?a ?b
 :where [(tuple ?a ?b) ?tup]]

;; inputs
1 2

;; result
#{[[1 2]]}
```

07. tx-ids

İstenen zaman aralığı için txleri döner.

```clj
;; query
[:find [?tx ...]
 :in ?log
 :where [(tx-ids ?log 1000 1050) [?tx ...]]]

;; inputs
log
;;=>
[13194139534340 13194139534312 13194139534313 13194139534314]
```

08. tx-data

Given: database log + tx id
Returns: datoms added by the transaction

Örnek: Bir tx içinde ref edilen varlıklar:

```clj
;; query
[:find [?e ...]
 :in ?log ?tx
 :where [(tx-data ?log ?tx) [[?e]]]]

;; inputs
log, 13194139534312
;;=>
[13194139534312 63 0 64 65 66 67 68 69 70 71 ...]
```

09. untuple

```clj
;; query
[:find ?b
 :in ?tup
 :where [(untuple ?tup) [?a ?b]]]

;; inputs
[1 2]
;;=>
#{[2]}
```

### Calling Java Methods

- static methodlar:

```clj
;; query
[:find ?k ?v
 :where [(System/getProperties) [[?k ?v]]]]

;; no inputs
;;=>
#{["java.vendor.url.bug" "https://bugreport.sun.com/bugreport/"] ...}
```

- instance methodları:

```clj
;; query
[:find ?k ?v
 :where [(System/getProperties) [[?k ?v]]]
        [(.endsWith ?k "version")]]

;; no inputs
;;=>
#{["java.class.version" "52.0"] ...}
```

#### Type Hinting for Performance

```clj
[(.endsWith ^String ?k "path")]
```

`java.lang` dışındaki typelar tam olarak qualify edilmeli.

### Calling Clojure Functions

```clj
;; query
'[:find [?prefix ...]
  :in [?word ...]
  :where [(subs ?word 0 5) ?prefix]]

;; inputs
["hello" "antidisestablishmentarianism"]
;;=>
["hello" "antid"]
```

`clojure.core` dışındaki fonksiyonlar tam olarak qualify eilmeli. require etme işini datomic kendisi otomatik yapar.

### The implicit data source - $

Tek bir veri kaynağı varsa, buna `$` şeklinde erişebilirsin.

```clj
[:find ?e :in $ ?age :where [?e :age ?age]]
;;same as
[:find ?e :in $data ?age :where [$data ?e :age ?age]]
```

### Rules 
  id:: 299fb991-7a21-4e55-92f8-8615360d2495

Kurallar (rules), isimlendirilmiş bir cümlecik grubudur.

Bu cümlecikler, `:where` kısmına konulabilir.

Böylece belirli bir `:where` cümlecik kümesini farklı yerlerde yeniden kullanabilirsin.

01. Örnek: Verili bir varlığın tipinin twitter feed olduğunu test eder:

```clj
[(twitter? ?c)
 [?c :community/type :community.type/twitter]]
```

02. Bir kural, üyeleri liste olan bir listedir. İlk liste öğesi, kuralın başlığıdır (head). Bu başlık, kuralı isimlendirir ve parametrelerini tanımlar. Diğer listeler, kuralın gövdesini oluşturan cümleciklerdir.

03. Buradaki kuralın herhangi bir çıktı argümanı yok. Birden çok argümanı olan kurallar, çıktı argüman tanımlanabilir.

```clj
[(community-type ?c ?t)
 [?c :community/type ?t]]
```

Burada çağırma (invocation) zamanınnda, ya `?c` ya da `?t` değişkenini bağlayabiliriz. Bu durumda diğer değişken, kuralın çıktısına bağlanır.

04. Hangi değişkenin çağrı zamanında bağlanacağını şart koşuyorsak, onu bir vektör veya liste içine koyabiliriz:

```clj
[(community-type [?c] ?t)
 [?c :community/type ?t]]
```

05. Kuralların kullanımı:

a. Kuralı girdi kaynaklarına ekle
b. `:in` kısmında `%` sembolünü kullan
c. `:where` kısmında kuralı kullan

```clj
(def rules '[[(track-name ?e ?t)
             [?e :track/name ?t]]])

(d/q '[:find ?e ?artist-name ?track-name
       :in $ % ?track-name
       :where
       (track-name ?e ?track-name)
       [?e :track/artists]
       [?a :artist/name ?artist-name]] 
		 db 
		 rules 
		 "Can't Find a Reason")

; =>
; #{[17592186149695 "McCully Workshop" "Can't Find a Reason"]
;   [17592186149685 "Love" "Can't Find a Reason"]
;   [17592186149685 "Bakerloo" "Can't Find a Reason"]
;   ...}
```

06. Kuralları kullanırken parantez kullanmak idiyomatiktir, ama zorunlu değildir. Böylece diğer veri (data) cümleciklerinden ayırmak kolay olur.

07. Birden çok tanımı olan kural kümeleri, `or` şeklinde çalışır:

```clj
[[(social-media ?c)
  [?c :community/type :community.type/twitter]]
 [(social-media ?c)
  [?c :community/type :community.type/facebook-page]]]
```

08. Kural cümleciklerinde, diğer cümlecik türlerini kullanabilirsin: veri, ifade (expression), başka kurallar gibi.

### Aggregates 
  id:: d8da4036-3be5-420e-9563-01bb4bdd68c1

```clj
[:find ?a (min ?b) (max ?b) ?c (sample 12 ?d)
 :where ...]
```

`:find` cümleciğindeki liste ifadeleri, kümeleme (aggregate) ifadeleridir.

Yukarıdaki örnekte, sorgu `?a ?b ?c ?d` değişkenlerini bağlar. Bunları `?a` ve `?c` değişkenlerine göre gruplar. Daha sonra her grup için, kümeleme ifadelerinin sonucunu üretir.

#### Control Grouping via :with

Normal koşullarda, datomic kümeler döner. Bu yüzden birbirinin tekrarlanan öğeleri temizler. Bu her zaman istenen bir şey olmayabilir. Örneğin:

```clj
;; incorrect query
[:find (sum ?heads) .
 :in [[_ ?heads]]]

;; inputs
[["Cerberus" 3]
 ["Medusa" 1]
 ["Cyclops" 1]
 ["Chimera" 1]]
;;=>
4
```

Buradaki örnekte, küme mantığı `1` değerlerinin tümünü tek bir öğeye indirger. Bu sorunu çözmek için `:with` cümleciği kullanılır.

```clj
;; query
[:find (sum ?heads) .
 :with ?monster
 :in [[?monster ?heads]]]

;; inputs
[["Cerberus" 3]
 ["Medusa" 1]
 ["Cyclops" 1]
 ["Chimera" 1]]
;;=>
6
```

Burada `?monster` değişkenine göre sonucun taban kümesi oluşturulur. Daha sonra `?monster` değerleri silinir. Kalan değerler bir `bag` oluşturur, küme değil. Bunlarla kümeleme işlemi yapılır.

#### Aggregates Returning a Single Value 
  id:: 07bdcc9c-0b9a-4330-963a-2cf77e3d984a

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

Tek değer dönen kümeleme fonksiyonları bunlardır.

#### Aggregates Returning Collections 
  id:: 1791fdbc-24ce-40b9-8693-932d006d392d

```clj
(distinct ?xs)
(min n ?xs)
(max n ?xs)
(rand n ?xs)
(sample n ?xs)
```

01. distinct

```clj
;; query
[:find (distinct ?v) .
 :in [?v ...]] 

;; inputs
[1 1 2 2 2 3]
;;=>
#{1 3 2}

```

02. min n / max n: en büyük/küçük n değer

```clj
;; query
[:find [(min 5 ?millis) (max 5 ?millis)]
 :where [?track :track/duration ?millis]]

;; inputs 
db
;;=>
[[3000 4000 5000 6000 7000] 
 [3894000 3407000 2928000 2802000 2775000]]
```

03. rand n / sample n

```clj
;; query
[:find [(rand 2 ?name) (sample 2 ?name)]
 :where [_ :artist/name ?name]]

;; inputs
db
;;=>
[("Four Tops" "Ethel McCoy") 
 ["Gábor Szabó" "Zapata"]]
```

`rand n` örnekleminde tekrar olabilir. `sample n` örnekleminde ayrık öğeler seçilir. 

#### Custom Aggregates

Herhangi bir clojure fonksiyonunu kümeleme fonksiyonu olarak kullanabilir, şu şekilde:

- Tam nitelikli ismini kullan
- Namespace'i fonksiyondan önce yükle
- Kümeleme değişkeni en son argüman olsun
- Fonksiyonun diğer argümanları, sorgu içinde sabit olmalı

```clj
(defn mode
  [vals]
  (->> (frequencies vals)
       (sort-by (comp - second))
       ffirst))

;; query
[:find (user/mode ?track-count) .
 :with ?media
 :where [?media :medium/trackCount ?track-count]]

;; inputs
db
;;=>
2
```

### Pull Expressions 
  id:: 5aae8c1d-3259-478b-9ba9-7831fdfbdff2

[Pull Expressions](https://docs.datomic.com/on-prem/query/query.html#pull-expressions)

```clj
(pull ?entity-var pattern)
```

01. pattern pull ifadesinde tanımlanabilir:

```clj
;; query
[:find (pull ?e [:release/name])
 :in $ ?artist
 :where [?e :release/artists ?artist]]

;; args
db, led-zeppelin
;;=>
[[{:release/name "Immigrant Song / Hey Hey What Can I Do"}]
 [{:release/name "Heartbreaker / Bring It On Home"}] ...]
```

02. pattern `:in` parametresi olarak da tanımlanabilir:

```clj
;; query
[:find (pull ?e pattern)
 :in $ ?artist pattern
 :where [?e :release/artists ?artist]]

;; args
db, led-zeppelin, [:release/name]
```

03. Birden çok pull ifadesi kullanılabilir:

Her birinde tek bir `?entity-var` olmalı.

```clj
;; query with valid pull expression
[:find (pull ?e [:release/name])
 :in $ ?artist-name
 :where [?e :release/artists ?a]
        [?a :artist/name ?artist-name]]

;; query with valid pull expression
[:find (pull ?e [:release/name]) (pull ?a [*])
 :in $ ?artist-name
 :where [?e :release/artists ?a]
        [?a :artist/name ?artist-name]]

;; query with valid pull expression
[:find (pull ?e [:release/name :release/artists])
 :in $ ?artist-name
 :where [?e :release/artists ?a]
        [?a :artist/name ?artist-name]]

;; inputs used in each
db, "Led Zeppelin"
```

04. Bir `?entity-var` birden çok pull ifadesinde kullanılamaz:

```clj
;; invalid pull expression in query
[:find (pull ?e [:release/name]) (pull ?e [:release/artists])
 :in $ ?artist-name
 :where [?e :release/artists ?a]
        [?a :artist/name ?artist-name]]
```

### Timeout

```clj
(d/query {:query query :args args :timeout timeout-in-milliseconds})
```

### Limitations

#### Resolving Entity Identifiers in V (value) Position 
  id:: 7ad9cfca-fad4-4e33-a95b-c9a34610eb1c

01. Entity id, ident veya lookup ref birbiri yerine kullanılabilir:

```clj
 ;; query
[:find ?artist-name
 :in $ ?country
 :where [?artist :artist/name ?artist-name]
        [?artist :artist/country ?country]]

;; input option 1: lookup ref
db, [:country/name "Belgium"]

;; input option 2: ident
db, :country/BE

;; input option 3: entity id
db, 17592186045516
```

02. Eğer atributun kendisi de dinamikse, o zaman entity-id'leri kendin bulmalısın:

```clj
;; query
[:find [?artist-name ...]
 :in $ ?country [?reference ...]
 :where [(datomic.api/entid $ ?country) ?country-id]
        [?artist :artist/name ?artist-name]
        [?artist ?reference ?country-id]]

;; inputs
db, :country/BE, [:artist/country]
```

### Index-pull

[Index-Pull | Datomic](https://docs.datomic.com/on-prem/query/index-pull.html)

`index-pull` fonksiyonu bir index içindeki tüm üyelerin üzerinden geçer. Pattern kullanarak varlıkları pull eder. Sonunda tembel bir seq döner.

Fonksiyon iki girdi alır: `db` ve bir map. Map'in key'leri şunlardır:

- `:index` - `:avet` veya `:aevt`
- `:selector` - bir pull selector
- `:start`

#### Indexes 
  id:: 9d1c97a4-9a16-4854-9d07-d404dd067f94

##### AVET

```clj
(take 5
      (d/index-pull db {:index    :avet
                        :selector '[:artist/name :artist/startYear :artist/endYear]
                        :start    [:artist/startYear]}))
;;=>
(#:artist{:name "Choir of King's College, Cambridge", :startYear 1441}
 #:artist{:name "Heinrich Schütz", :startYear 1585, :endYear 1672}
 #:artist{:name "Antonio Vivaldi", :startYear 1678, :endYear 1741}
 #:artist{:name "Johann Sebastian Bach", :startYear 1685, :endYear 1750}
 #:artist{:name "Georg Friedrich Händel", :startYear 1685, :endYear 1759})
```

```clj
(take 5
      (d/index-pull db {:index :avet
                        :selector '[:artist/name :artist/startYear {:release/_artists [:release/year]}]
                        :start [:artist/startYear 1800]}))
;;=>
({:artist/name "Hector Berlioz",
  :artist/startYear 1803,
  :release/_artists [#:release{:year 1970} #:release{:year 1973} #:release{:year 1969}]}
 {:artist/name "Felix Mendelssohn", :artist/startYear 1809, :release/_artists [#:release{:year 1973}]}
 {:artist/name "Frédéric Chopin",
  :artist/startYear 1810,
  :release/_artists [#:release{:year 1968} #:release{:year 1970}]}
 {:artist/name "Franz Liszt", :artist/startYear 1811, :release/_artists [#:release{:year 1968}]}
 {:artist/name "Richard Wagner",
  :artist/startYear 1813,
  :release/_artists [#:release{:year 1968} #:release{:year 1972} #:release{:year 1971} #:release{:year 1970}]})
```

##### AEVT

```clj
(take 5 (d/index-pull db {:index :aevt
                          :selector [:artist/name]
                          :start [:release/artists]}))
;;=>
   (#:artist{:name "A1"}
    #:artist{:name "Antonín Dvořák"}
    #:artist{:name "Rob Lamothe"}
    #:artist{:name "Craig Erickson"}
    #:artist{:name "Frédéric Chopin"})
```

### Datomic Pull

[Datomic Pull | Datomic](https://docs.datomic.com/on-prem/query/pull.html)

#### Pull API 
  id:: 8726751a-8c25-40e1-8065-e31b281e190f

`pull` API 3 argüman alır:

- veritabanı
- pattern
- entity id

```clj
(pull db '[*] led-zeppelin)
```

`pull-many` tek id yerine bir collection alır:

```clj
(pull-many db '[*] [led-zeppelin jimi-hendrix janis-joplin])
```

#### Attribute Names

##### Attribute Name Example

```clj
[:artist/name :artist/gid]
;;=>
{:artist/gid #uuid "678d88b2-87b0-403b-b63d-5da7465aecc3", :artist/name "Led Zeppelin"}
```

##### Reverse Lookup 
  id:: 38a7eb11-1aed-4514-bc45-9eaa05fc660a

`_` underscore: ters yönde dolaşmayı sağlar

Örnek: `:country/GB` ülkesine ait artistleri bulmak:

```clj
[:artist/_country]
;;=>
{:artist/_country [{:db/id 17592186045751} {:db/id 17592186045755} ...]}
```

#### Map Specifications 
  id:: 88e2371a-3131-4901-b11b-8933f11a213a

```clj
[:track/name {:track/artists [:db/id :artist/name]}]
;;=>
{:track/artists [{:db/id 17592186048186, :artist/name "Bob Dylan"}
                 {:db/id 17592186049854, :artist/name "George Harrison"}],
 :track/name "Ghost Riders in the Sky"}
```

##### Map Specification Nesting

```clj
[{:release/media
    [{:medium/tracks
        [:track/name {:track/artists [:artist/name]}]}]}]
[{:medium/tracks
 [{:track/artists
     [{:artist/name "Ravi Shankar"} {:artist/name "George Harrison"}],
     :track/name "George Harrison / Ravi Shankar Introduction"}
 {:track/artists [{:artist/name "Ravi Shankar"}],
     :track/name "Bangla Dhun"}]}
  :medium/tracks
    [{:track/artists [{:artist/name "George Harrison"}],
        :track/name "Wah-Wah"}
    {:track/artists [{:artist/name "George Harrison"}],
        :track/name "My Sweet Lord"}
    {:track/artists [{:artist/name "George Harrison"}],
        :track/name "Awaiting on You All"}
    {:track/artists [{:artist/name "Billy Preston"}],
        :track/name "That's the Way God Planned It"}]
            ...}
```

#### Attribute Specifications 
  id:: 3fc3e595-79d6-42af-a2c1-c8eb84fb9514

##### :as Option

```clj
[:artist/name :as "Band Name"]
;;=>
{"Band Name" "Led Zeppelin"}
```

##### :limit Option

```clj
[:artist/name (:track/_artists :limit 10)]
;;=>
{:artist/name "Led Zeppelin",
 :track/_artists
 [{:db/id 17592186057344}
  {:db/id 17592186057345}
  {:db/id 17592186057346}
  {:db/id 17592186057347}
  {:db/id 17592186057348}
  {:db/id 17592186057349}
  {:db/id 17592186057350}
  {:db/id 17592186057351}
  {:db/id 17592186057352}
  {:db/id 17592186057355}]}
```

##### :limit Inside a Map Specification Example

```clj
[{(:track/_artists :limit 10) [:track/name]}]
;;=>
{:track/_artists
 [{:track/name "Whole Lotta Love"}
  {:track/name "What Is and What Should Never Be"}
  {:track/name "The Lemon Song"}
  {:track/name "Thank You"}
  {:track/name "Heartbreaker"}
  {:track/name "Living Loving Maid (She's Just a Woman)"}
  {:track/name "Ramble On"}
  {:track/name "Moby Dick"}
  {:track/name "Bring It on Home"}
  {:track/name "Whole Lotta Love"}]}
⇧

```

##### nil :limit Example

`:limit nil` olursa, limitsiz olarak kayıtlar döner:

```clj
[:artist/name (:track/_artists :limit nil)]
;;=>
{:artist/name "Led Zeppelin",
 :track/_artists
 [{:db/id 17592186057344}
  {:db/id 17592186057345}
  {:db/id 17592186057346}
	...]}
```

#### :default Option

```clj
[:artist/name (:artist/endYear :default 0)] 
;;=>
{:artist/endYear 0, :artist/name "Paul McCartney"}
```

#### :xform Option

```clj
[[:artist/endYear :xform str]]
;;=>
{:artist/endYear "1980"}
```

Kullanabileceğin transformasyon fonksiyonları: `str, keyword, symbol, name, namespace, clojure.edn/read-string`

#### Wildcards 
  id:: 67f58aa8-17a7-4c8f-9210-6ac43ef8bc1b

Bir varlığın tüm atributlarını çeker. Ayrıca komponentlerinin atributlarını da özyinelemeli bir şekilde çeker.

```clj
[*]
;;=>
{:release/name "The Concert for Bangla Desh",
 :release/artists [{:db/id 17592186049854}],
 :release/country {:db/id 17592186045504},
 :release/gid #uuid "f3bdff34-9a85-4adc-a014-922eef9cdaa5",
 :release/day 20,
 :release/status "Official",
 :release/month 12,
 :release/artistCredit "George Harrison",
 :db/id 17592186072003,
 :release/year 1971,
 :release/media
 [{:db/id 17592186072004,
   :medium/format {:db/id 17592186045741},
   :medium/position 1,
   :medium/trackCount 2,
   :medium/tracks
   [{:db/id 17592186072005,
     :track/duration 376000,
     :track/name "George Harrison / Ravi Shankar Introduction",
     :track/position 1,
     :track/artists [{:db/id 17592186048829} {:db/id 17592186049854}]}
    {:db/id 17592186072006,
     :track/duration 979000,
     :track/name "Bangla Dhun",
     :track/position 2,
     :track/artists [{:db/id 17592186048829}]}]}
  ...
  ]}
```

#### Combining Wildcards and Map Specifications

Normal koşullarda `*` ref atributların atributlarını çekmez, `db/id` hariç. Ref atributların diğer atributlarını çekmek için map spec kullanırız, `*` ile birlikte.

```clj
["*" {:track/artists [:artist/name]}]
;;=>
{:db/id 17592186063810,
 :track/duration 218506,
 :track/name "Ghost Riders in the Sky",
 :track/position 11,
 :track/artists
 [{:artist/name "Bob Dylan"} {:artist/name "George Harrison"}]}
```

#### Recursion Limits

Eğer map spec numerik bir değer içerirse, bu durumda N seviye özyineleme yapılabilir. 

`...` ellipsis sembolü varsa, sınırsız özyineleme yapılabilir.

Sonsuz döngülere girmez. Tekrarlanan varlıklar için sadece `:db/id` değeri çekilir.

Örnek: 6 seviye arkadaşa gider.

```clj
[:person/firstName :person/lastName {:person/friends 6}]
```

#### Empty Results

```clj
 ;; pattern
[:penguins]
;; entity
led-zeppelin
;;=>
nil
```

#### Pull Results

##### Component Defaults

Örnek: `:medium/track` bir komponent atributu. Bu yüzden tüm verileri otomatik çekilir:

```clj
[:release/media]
;;=>
{:release/media
 [{:db/id 17592186121277,
   :medium/format {:db/id 17592186045741},
   :medium/position 1,
   :medium/trackCount 10,
   :medium/tracks
   [{:db/id 17592186121278,
     :track/duration 68346,
     :track/name "Speak to Me",
     :track/position 1,
     :track/artists [{:db/id 17592186046909}]}
    {:db/id 17592186121279,
     :track/duration 168720,
     :track/name "Breathe",
     :track/position 2,
     :track/artists [{:db/id 17592186046909}]}
    {:db/id 17592186121280,
     :track/duration 230600,
     :track/name "On the Run",
     :track/position 3,
     :track/artists [{:db/id 17592186046909}]}
    ...]}]}

```

##### Non-component Defaults

Component olmayan ref atributları için, sadece `db/id` varsayılan olarak çekilir.

Örnek: Pulling `:artist/_country` of `:country/GB`

```clj
[:artist/_country]
;;=>
{:artist/_country [{:db/id 17592186045751} {:db/id 17592186045755} ...]}
```

#### Multiple Results

Örnek: Pulling `:artist/_country` of `:country/GB`

```clj
[:artist/_country]
;;=>
{:artist/_country [{:db/id 17592186045751} {:db/id 17592186045755} ...]}
```

#### Missing Attributes

Eksik atributlar hiç görünmez sonuç kümesinde:

```clj
[:artist/name :died-in-1966?]
;;=>
{:artist/name "Paul McCartney"}
```

#### Legacy Attribute Expressions

Eski attribute specleri.

#### Pull API vs. Entity API

Pull API'nin iki avantajı:

01. Declarative, veri temelli bir spec sağlar. Entity ise kodla sonucu inşa eder.

Veri temelli spekler daha kolay inşa edilir ve birleştirilebilir. 

02. Pull API, standart map interfaceleriyle eşleşir. Ek transformasyon gerektirmez.

### Indexes

[Indexes | Datomic](https://docs.datomic.com/on-prem/query/indexes.html)

#### Basics

Datomic indeksleri kapsayıcı indekslerdir (covering index). Bunun anlamı şu: Datomlara ref içermez. Datomları bizzat içerir. Bu sayede Datomic çok hızlı bir şekilde indeksler üzerinden datomlara erişebilir.

E, A, V her zaman artan sıradadır. T ise azalan sıradadır.

	| index | contains |
	| EAVT  | all datoms |
	| AEVT  | all datoms |
	| AVET  | :db/unique veya :db/index atributların datomları |
	| VAET  | :db.type/ref atributların datomları |

#### EAVT

Bir varlık hakkındaki her şeye erişim sağlar. SQL'daki satır erişimine benzer. Tek farkla: Varlıkların atributları değişken olabilir, önden tanımlı değildir.

![EAVT](https://docs.datomic.com/on-prem/images/eavt.png)

#### AEVT

Bir atributa ait farklı varlıklardaki tüm değerler arka arkaya dizilir.

![AEVT](https://docs.datomic.com/on-prem/images/aevt.png)

#### AVET

Atribut-value kombinasyonları birlikte dizilir.

![AVET](https://docs.datomic.com/on-prem/images/avet.png)

Diğer indekslere göre daha maliyetli bu. Bu yüzden ya `:db/index` ya da `:db/unique` tanımlı olmalı, ilgili atribut için bu indeks gerekiyorsa.

#### VAET

Bu indeks ters yönlü ref navigasyonunda işe yarar.

Örnek: Beatles eidsi 100 olsun. Bu durumda Beatles grubuna ref veren tüm `:release/artists` atributuna sahip varlıklar:

![VAET](https://docs.datomic.com/on-prem/images/vaet.png)

#### Datoms API 
  id:: fb19d7b6-ed2f-47ee-a218-9192431995c5

Örnek: Tüm `:account/balance` atributuna göre sıralanmış datomlar:

```java
db.datoms(AVET, ":account/balance");
```

Eğer herhangi bir komponent olmadan datoms fonksiyonu çağrılırsa, tüm datomlar döner:

```java
// lazily iterate the entire database!
db.datoms(EAVT);
```

#### Log

Log da datomları zamana göre indeksler. Diğer indekslerden farklıdır:

- Tx kümesinin sıraya dizilmiş halidir.
- Connection üzerinden alınır, Database üzerinden değil.
- Query içinde otomatik kullanılamaz. Erişmek için `tx-ids` veya `tx-data` fonksiyonları kullanılmalı sorgu içinde.

#### Implementation

##### Accumulate Only

##### Efficient Accumulation

## Configuration

### Configuring Logging 

[Configuring Logging | Datomic](https://docs.datomic.com/on-prem/configuration/configuring-logging.html)

#### Transactor Logging

Datomic transactor logback (log4j varyantı) kütüphanesiyle logları kaydeder.

Konfigürasyonu: `bin/logback.xml`

#### Peer Logging

Peer kütüphanesi, `slf4j` kullanarak loglama yapar.

### Configuring Embedded Storage

[Configuring Embedded Storage | Datomic](https://docs.datomic.com/on-prem/configuration/configuring-embedded-storage.html)

## API

[datomic (Datomic Java API Documentation)](https://docs.datomic.com/on-prem/javadoc/index.html)

[REST API | Datomic](https://docs.datomic.com/on-prem/api/rest.html)

REST API kalıntıdır (legacy). Yeni güncellemeler yapılmıyor. Client API kullanılmalı onun yerine.

### Datomic Clojure API 
  id:: ff814fd5-2083-44e9-b009-37eef251e137

[datomic.api - Datomic Clojure API documentation](https://docs.datomic.com/on-prem/clojure/index.html)

- `as-of`

`(as-of db t)`

Returns value of db as of some point t, inclusive.

t: tx num, tx id, or date

- `as-of-t`

`(as-of-t db)`

Returns the as-of point

- `attribute`

`(attribute db attrid)`

Atribut hakkında bilgi döner. Dönen veri bir maptir (ILookup interface).

- `basis-t`

`(basis-t db)`

En güncel tx'in t'sini döner.

- `cancel`

Mevcut operasyonu iptal eder (query or tx)

- `connect`

`(connect uri)`

Farklı depolayıcı veritabanlarına erişim için.

- `create-database`

`(create-database uri)`

Veritabanı oluşturur. 

- `datoms`

`(datoms db index & components)`

Indekse ham erişim. Index belirtilmeli.

- `db`

`(db connection)`

- `db-stats`

`(db-stats db)`

Veritabanın istatistiklerini sorgular.

- `delete-database`

- `entid`

`(enditd db ident)`

Entity id'yi döner, verilen keyword veya id için.

- `entid-at`

- `entity`

`(entity db eid)`

Bir varlığın atributlarından bir map döner.

- `entity-db`

`(entity-db entity)`

Bu varlığın tabanı olan db değerini döner.

- `filter`

`(filter db pred)`

Yüklemi karşılayan datomların veritabanını döner.

- `function`

`(function m)`

Verilen map ve zorunlu keyler için bir fonksiyon objesi oluşturur.

- `get-database-names`

`(get-database-names uri)`

Veritabanı isimlerinin listesini döner.

- `history`

`(history db)`

Tüm assert ve retract işlemlerini içeren özel bir veritabanı döner, belirli bir zaman dönemini içeren.

- `ident`

`(ident db eid)`

id ile bağlı keywordü döner.

- `index-pull`

`(index-pull db arg-map)`

Indeksi dolaşır. :e veya :v ile varlıkları çeker.

  :index - :avet or :aevt
	:selector - pull selector
	:start
	:reverse

- `index-range`

- `invoke`

`(invoke db eid-or-ident & args)`

Veritabanı fonksiyonunu bulur ve çağırır.

- `is-filtered`

- `log`

`(log connection)`

tx-range veya sorgu içinde kullanım için log değerini çeker.

- `next-t`

`(next-t db)`

Bu db değerinin bir sonraki t anını döndürür.

- `part`

`(part eid)`

eid ile bağlı partisyonu döner.

- `pull`

`(pull db pattern eid)`

eid ile atributları hiyerarşik bile çekip döner.

- `pull-many`

- `q`

`(q query & inputs)`

inputs için bir query çalıştırır.

inputs: data sources = Connection ile çekilebilen veritabanı değerleri veya list of lists veya rules.

Eğer tek bir veri kaynağı verilmişse, `:in` şart değil.

`query`: map, list veya string

- `qseq`

`q` gibi ama tembel seq döner.

- `query`

`(query query-map)`

`query-map` tarafından tarif edilen sorguyu çalıştırır.

`query-map` formu:

  {:query query
	 :args args
	 :timeout time-in-ms}

`query` q ile aynı formatta.

`args` q'nun inputs'uyla aynı formatta.

- `release`

`(release conn)`

Bağlantıyla ilişkili kaynakları serbest bırakır.

- `since`

- `since-t`

- `squuid`

`(squuid)`

- `sync`

`(sync connection)`

Diğer peerlerle koordinasyon için.

- `t->tx`

- `transact`

`(transact connection tx-data)`

- `with`

`(with db tx-data)`

### Datomic Client API

[Datomic Client](https://docs.datomic.com/client-api/index.html)

- `as-of`
- `client`
- `connect`
- `create-database`
- `datoms`
- `db`
- `db-stats`
- `history`
- `index-pull`
- `index-range`
- `list-databases`
- `pull`
- `q`
- `qseq`
- `since`
- `sync`
- `transact`
- `tx-range`
- `with`
- `with-db`

### Log API

[Log API | Datomic](https://docs.datomic.com/on-prem/api/log.html)

#### Log in Query: tx-ids and tx-data

Örnek: [t1, t2] zaman aralığındaki tüm tx sayısı:

```clj
(d/q '[:find (count ?tx)
       :in $ ?log ?t1 ?t2
       :where [(tx-ids ?log ?t1 ?t2) [?tx ...]]]
     (d/db conn) (d/log conn) t1 t2)
```

### io-stats

[Io-stats | Datomic](https://docs.datomic.com/on-prem/api/io-stats.html)

Bu API tüm tx'lerle ilgili verileri tutar.

## Reference

### Database Functions

[Database Functions | Datomic](https://docs.datomic.com/on-prem/reference/database-functions.html)

Datomic fonksiyonları, veritabanındaki birinci sınıf değerler olarak destekler.

Bunun faydaları:

- Transformasyon fonksiyonlarını tx içinde kullanabilmek
- Integrity checks
- Predicates ve generative functions

### Peer Language Support

[Peer Language Support | Datomic](https://docs.datomic.com/on-prem/reference/languages.html)

01. `datomic.api` as `d`

```clj
(ns project-ns
  (:require [datomic.api :as d :refer [db q]]))
```

02. Java to clojure

```java
String uri = "datomic:mem://test";
Connection conn = Peer.connect(uri);
Collection results = Peer.query("[:find ?e :where [?e :db/doc]]", conn.db);
```

```clj
;; clojure
(def uri "datomic:mem://test")
(def conn (d/connect uri))
(def results (q '[:find ?e :where [?e :db/doc]] (db conn)))
```

#### Non-JVM Languages

REST servisleriyle erişebilirler.

## Time in Datomic

[Database Filters | Datomic](https://docs.datomic.com/on-prem/time/filters.html)

### asOf

```clj
(def db (d/db conn))
(d/entity db [:item/id "DLC-042"])
;;=>
{:item/id "DLC-042", 
 :item/description "Dilitihium Crystals", 
 :item/count 100, 
 :db/id 17592186045418}
```

```clj
(def as-of-eoy-2013 (d/as-of db #inst "2014-01-01"))
(d/entity as-of-eoy-2013 [:item/id "DLC-042"])
;;=>
{:item/id "DLC-042", 
 :item/description "Dilitihium Crystals", 
 :item/count 250, 
 :db/id 17592186045418}
```

### since 
  id:: 080cc09c-1684-4b34-92ac-6bf4278069ee

since returns a value of the database that includes only datoms added by transactions after that point in time.

01. Lookup ref ile arama yapamazsın.

```clj
(def since-2014 (d/since db #inst "2014-01-01"))
;;=>
(d/entity since-2014 [:item/id "DLC-042"])
nil  ;; !!!
⇧
```

02. Doğrudan entity-id ile erişebilirsin:

```clj
(d/entity since-2014 (d/entid db [:item/id "DLC-042"]))
;;=>
{:item/count 100, 
    :db/id 17592186045418}
```

Dikkat: `entid` lookup refi şu anki veritabanına göre sorgular.

Bu işlemi bir sorgu ile yapacak olsak:

```clj
(d/q ('[:find ?count 
        :in $ $since ?id 
        :where [$ ?e :item/id ?id] 
               [$since ?e :item/count ?count]]) 
  db since-2014 "DLC-042")
```

### history

```clj
(def history (d/history db))

(->> (d/q '[:find ?aname ?v ?inst
            :in $ ?e
            :where [?e ?a ?v ?tx true]
                   [?tx :db/txInstant ?inst]
                   [?a :db/ident ?aname]]
          history [:item/id "DLC-042"])
     (sort-by #(nth % 2)))
```

## Analytics Support

[Analytics Support (Preview) | Datomic](https://docs.datomic.com/on-prem/analytics/analytics-concepts.html)

## Datomic Best Practices 
  id:: 9d1f507e-c6a8-41f0-9465-969a0d84e818

[Datomic Best Practices | Datomic](https://docs.datomic.com/on-prem/best-practices.html)

### Group Related Attributes in a Namespace

Namespaceleri tablo gibi düşün.

### Plan for Accretion

Varlıkların tüm atributlarını değil, seçili atributlarını kullanarak veriyi işle.

```clj
(do-side-effecty-thing-with-each
 (select-keys some-entity keys-i-understand))
;; not (do-side-effecty-thing-with-each some-entity)
```

### Model Relationships in One Direction Only

Bir varlıktan bir varlığa ref veriyorsan, tersi yönde ref tutmana gerek yok.

```clj
[?release :release/artists ?artist]
```

### Use Idents for Enumerated Types

```clj
[artist-id :artist/country :country/GB]
```

### Use Unique Identities for External Keys

Harici anahtarlar için, atributları tekil yap: `db/unique :db.unique/identity`

Not: Tüm varlıkların dahili tekil id'leri vardır. Entity id.

### Use NoHistory for High-churn Attributes

Çok sık değişen atributlar için, counter gibi, tarihçeyi saklama maliyetine değmez, çünkü indeksleme performansı her değişiklikte düşer. Bunlar için `:db/noHistory true` ayarı yap.

### Grow Schema and Never Break It

```clj
;; Given some initial schema
{:db/ident :user/id
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one}

;; Grow system by adding schema:
{:db/ident :user/name
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one}
```

### Never Remove or Reuse Names

Bir ismi farklı bir anlamda kullanmak, silmekten de kötüdür. Çünkü bu durumda anlam kaybı gizli olur.

### Use Aliases

İsim değiştirmek yerine, birden çok isim kullanmak için alias kullan.

Bunun için `:db/ident` birden çok tanımlayabilirsin, aynı entity-id'ye sahip olan.

```clj
[:db/add :user/id :db/ident :user/primary-email]
```

### Annotate Schema

```clj
{:db/ident :user
 :schema/see-instead :user2
 :db/doc "prefer the user2 namespace for new development"}
```

### Add Facts About the Transaction Entity

Çoğu varlık, alanla ilgili "ne?" sorusunu yanıtlar. "Ne zaman?", "kim?", "nerede?", "neden?" gibi soruların yanıtlarını transaction içinde modelleyebilirsin.

Transaction otomatik olarak `:db/txInstant` atributunda işlemin saatini kaydeder.

Örnek: Verinin kaynağı:

```clj
[:db/add 
 "datomic.tx"
 :data/src 
 "https://example.com/catalog-2_29_2012.xml"]
```

### Use Lookup Refs to Specify Existing Entities

01. Lookup ref kullanmadan işlem:

```clj
;;query
(def e (d/q [:find ?e .
             :where [?e :user/email "jdoe@example.com"]]))

;;then use
(d/transact conn [[:db/add 
                   e
                   :db/doc
                   "doc about John"]])
```

02. Lookup ref kullanarak işlem:

```clj
(d/transact conn [[:db/add
                   [:user/email "jdoe@example.com"]
                   :db/doc
                   "doc about John"]])
```

### Use CAS for Optimistic Concurrency

Eğer işlem gerçekleşmezse, tekrar dener:

```clj
(defn make-deposit!
  [conn account-id deposit]
  (let [db (d/db conn)
        acc-lookup [:account/number account-id]
        balance (-> (d/pull db '[:account/balance] acc-lookup)
                    :account/balance)]
             (try (deref (d/transact conn [[:db.fn/cas acc-lookup
                                            :account/balance balance
                                                             (+ balance deposit)]]))
                  (catch java.lang.IllegalStateException e
                    (make-deposit! conn account-id deposit)))))
```

### Use DbAfter to see Result of a Transaction

```clj
(let [db (d/db conn)
      deposit 850.00
      acc-lookup [:account/number "123-45-6789"]
      balance-before (-> (d/pull db '[:account/balance] acc-lookup)
                         :account/balance)
      tx-result (deref
                  (d/transact conn [[:db.fn/cas acc-lookup
                                     :account/balance balance-before
                                                      (+ balance-before deposit)]]))
      db-after (:db-after tx-result)
      balance-after (-> (d/pull db-after '[:account/balance] acc-lookup)
                         :account/balance)]
  [balance-before deposit balance-after])
```

Neden `(d/db conn)` ile yeni balance değerini çekmek yerine `:db-after` ile bunu çektik?

Çünkü ilk yolu deneseydik, arada geçen zamanda başka birisi `balance` değerini değiştirmiş olabilirdi.

### Set txInstant on Imports

Başka bir veri kaynağından veri import ederken, `:db/txInstant` değerini kendin yazabilirsin.

Bunun için tx'e bir map daha eklemelisin: 

```clj
{:db/id "datomic.tx"
 :db/txInstant #inst "2014-02-28"}
```

### Put the Most Selective Clause First in Query

```clj
:where [?e :only/matches ?few]
       [?e :will/match ?many]
```

### Join Along

Genel kural olarak: Her bir cümlecik, en azından bir tane değişkene bağlanmalı, daha önceki cümleciklerde tanımlanmış olan.

01. Kötü örnek. İkinci satırda cross product yapılarak veri kümesi büyür:

```clj
;; likely a bad ordering!
[?person :person/name ?name]
[?pet :pet/name ?pet-name]    ;; matches no previous variables!
[?person :person/pet ?pet]
```

02. Sıralaması değiştirilmiş

```clj
;; better ordering
[?person :person/name ?name]
[?person :person/pet ?pet]    ;; join along: ?person bound
[?pet :pet/name ?pet-name]    ;; join along: ?pet bound
```

### Prefer Query over Raw Index Access

Datoms API ile ham indeks erişimi bir anti-kalıp sayılır.

Datoms ancak şu durumlarda kullanılmalı:

1. Veritabanını sırayla dolaşma (paging veya düz export gibi)
2. Performans kazancı durumu

### Pass Collections As Inputs

```clj
(d/q '[:find (pull ?a [:artist/name])
       :in $ [?c ...]
       :where [?a :artist/country ?country]
              [?country :country/name ?c]]
     db ["Canada" "Japan"])
;;=>
[[{:artist/name "The Guess Who"}]
 [{:artist/name "Whiskey Howl"}] ...]
```

### Use Pull to Retrieve Attribute Values

Örnek: `:db/ident` varlığının dokümantasyonunu çekmek:

```clj
(d/pull db [:db/doc] :db/ident)
```

```clj
[:find [(pull ?e [:artist/name]) ...]
 :where [?e :artist/country :country/JP]]
;;=>
[{:artist/name "Flower Travellin' Band"}
 {:artist/name "The Tigers"}
 {:artist/name "Jacks"}
 ...]
```

02. Eksik atributları atlar:

```clj
[:find [(pull ?e [:artist/name :artist/gender]) ...]
 :where [?e :artist/country :country/JP]]
;;=>
[{:artist/gender {:db/id 17592186045591} :artist/name "Yoko Ono"}
 {:artist/name "Far Out"}
 {:artist/gender {:db/id 17592186045591} :artist/name "Asakawa Maki"} ...]
```

`pull` atributları birleştirmez, `:where` cümleciklerinden farklı olarak.

03. Entity yerine pull kullanırken dikkat et:

- pull ham veriyi hemen çeker.
- entity tembel bir map döner.
- pull `:db/ident` değerlerini map olarak döner. entity ise onları keyword olarak döner.

### Put Blanks in Data Patterns

### Use Query Inputs to Parameterize Queries and Leverage Caching

İki sorgunun query argümanları aynıysa, datomic sorgu sonuçlarını keşten yeniden kullanabilir. Bu yüzden parametrik sorgular yazmalısın.

Eğer veri yapılarını dinamik olarak oluşturuyorsan, hep birbirine denk sorgu cümleleri oluşturmaya dikkat etmelisin.

```clj
[:find [?name ...]
 :where [?a :artist/type :artist.type/group]
        [?a :artist/name ?name]
        [?a :artist/startYear 1970]
        [(.startsWith ^String ?name "B")]]
```

Şimdi benzer bir sorgu daha olsun:

```clj
[:find [?name ...]
 :where [?a :artist/type :artist.type/group]
        [?a :artist/name ?name]
        [?a :artist/startYear 1971]
        [(.startsWith ^String ?name "C")]]
```

Burada parametre kullanmadığımız için, keş kullanılamaz. Çözüm:

```clj
[:find [?name ...]
 :in $ ?year ?letter
 :where [?a :artist/type :artist.type/group]
        [?a :artist/name ?name]
        [?a :artist/startYear ?year]
        [(.startsWith ^String ?name ?letter)]]

;; inputs
mbrainz-db 1971 "C"
```

### Prefer qseq for large queries

`qseq` daha az bellek harcar. İlk sonucu hızlıca almana izin verir.

Tembel bir dizi (sequence) döner.

```clj
;; Find the names of all artists in the mbrainz database

;; q
(->> (d/q '[:find ?v
             :where [_ :artist/name ?v]]
          db)
     first
     time)

"Elapsed time: 49123.671445 msecs"
;; result
["Los Dudes"]
```

```clj
;; qseq
(->> (d/qseq '[:find ?v
               :where [_ :artist/name ?v]]
             db)
     first
     time)
"Elapsed time: 1729.227285 msecs"
;; result
["Los Dudes"]
```

### Use a Consistent Db Value for a Unit of Work

Bir db değerinde yapacağın tüm sorgular aynı sonucu döndürür. Bu yüzden aynı iş birimi (unit of work) için yapacağın sorgularda aynı db değerini kullanmalısın.

### Specify t Instead of txInstant for Precise asOf Locations

Datomic'in `t` zaman değeri, monoton ardan sırayla tx'leri sıralar.

Buna karşılık `txInstant` duvar saati aynı seviyede hassas değildir. Çünkü aynı ms içinde birden çok tx kaydedilebilir.

```clj
'[:find ?txInstant .
  :where [?a :artist/name "The Rolling Stones" ?tx]
         [?tx :db/txInstant ?txInstant]]
```

```clj
[:find ?tx .
 :where [?a :artist/name "The Rolling Stones" ?tx]]

(-> (d/tx-range log tx (inc tx))
    first
    :data)
```

### Use the History Filter for Audit Trail Queries

Örnek: Bir müşteriyle ilgili neler biliyoruz?

Hangi satın alma işlemlerini yapmış? Bunların hangilerini iptal etmiş?

```clj
(d/q '[:find ?p ?inst ?added
       :in $ ?userid
       :where [?u :user/purchase ?p ?tx ?added]
              [?u :user/id ?userid]
              [?tx :db/txInstant ?inst]]
     (d/history db) userid)
[["Bread" #inst "2014-11-18T03:41:59.301-00:00" false]
 ["Bread" #inst "2014-11-18T03:41:37.031-00:00" true]
 ["Soda" #inst "2014-11-18T03:41:39.617-00:00" true]
 ["French Fries" #inst "2014-11-18T03:41:56.115-00:00" true]]
```

### Pass Multiple Points-in-time to a Single Query

```clj
[:find ?count .
 :in $ $since ?id
 :where [$ ?e :item/id ?id]
        [$since ?e :item/count ?count]]
```

### Use the Log API if Time is Your Most Selective Criterion

Eğer belirli bir zamana göre değerleri çekmek istiyorsan, Log API kullanmak daha verimli olur.




