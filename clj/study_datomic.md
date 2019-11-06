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
path: ~/projects/study/clj/study_datomic.md
state: wip

---

# Setup Datomic

https://docs.datomic.com/on-prem/get-datomic.html

``` bash
cd ~/codes/clojure/datomic-pro-0.9.5951/
``` 

## Connect to a Database

Launch Peer Server

``` bash
cd ~/codes/clojure/datomic-pro-0.9.5951/
bin/run -m datomic.peer-server -h localhost -p 8998 -a myaccesskey,mysecret -d hello,datomic:mem://hello
``` 

Edit `~/projects/study/clj/ex/study_datomic/ex01/deps.edn`

``` bash
 {com.datomic/client-pro {:mvn/version "0.8.28"}}
``` 

``` bash
clj
``` 

``` bash
(require '[datomic.client.api :as d])
``` 

``` bash
(def cfg {:server-type :peer-server
                 :access-key "myaccesskey"
                 :secret "mysecret"
                 :endpoint "localhost:8998"})
  ##> #'user/cfg
(def client (d/client cfg))
  ##> #'user/client
(def conn (d/connect client {:db-name "hello"}))
  ##> #'user/conn
``` 

``` bash
conn
  ##> {:db-name "hello", :database-id "5dbc1958-2af3-4743-91d6-3e2c82b85267", :t 66, :next-t 1000, :type :datomic.client/conn}
``` 

## Transact Schema

In Datomic everything including the schema is data.

Attribute definition. This is a map of data.

``` bash
{:db/ident :movie/title
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "The title of the movie"}
{:db/ident :movie/genre
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "The genre of the movie"}
{:db/ident :movie/release-year
 :db/valueType :db.type/long
 :db/cardinality :db.cardinality/one
 :db/doc "The year the movie was released in theaters"}
``` 

We can send these attribute definitions in one batch as a single transaction:

Combine three maps into a single vector of maps. Bind this single vector to a var called `movie-schema` which defines our schema:

``` bash
(def movie-schema [{:db/ident :movie/title
                           :db/valueType :db.type/string
                           :db/cardinality :db.cardinality/one
                           :db/doc "The title of the movie"}

                          {:db/ident :movie/genre
                           :db/valueType :db.type/string
                           :db/cardinality :db.cardinality/one
                           :db/doc "The genre of the movie"}

                          {:db/ident :movie/release-year
                           :db/valueType :db.type/long
                           :db/cardinality :db.cardinality/one
                           :db/doc "The year the movie was released in theaters"}])
``` 

Now transact the schema:

``` bash
(d/transact conn {:tx-data movie-schema})
``` 

## Transact Data

https://docs.datomic.com/on-prem/getting-started/transact-data.html

Define the data as a vector of maps:

``` bash
(def first-movies [{:movie/title "The Goonies"
                           :movie/genre "action/adventure"
                           :movie/release-year 1985}
                          {:movie/title "Commando"
                           :movie/genre "action/adventure"
                           :movie/release-year 1985}
                          {:movie/title "Repo Man"
                           :movie/genre "punk dystopia"
                           :movie/release-year 1984}])
``` 

``` bash
(d/transact conn {:tx-data first-movies})
  ##> {:db-before {:database-id "5dbc3927-e737-4646-9372-a22597e9606c", :db-name "hello", :t 1000, :next-t 1001, :type :datomic.client/db}, 
  ##> :db-after {:database-id "5dbc3927-e737-4646-9372-a22597e9606c", :db-name "hello", :t 1001, :next-t 1005, :type :datomic.client/db}, 
  ##> :tx-data [#datom[13194139534313 50 #inst "2019-11-01T14:02:44.626-00:00" 13194139534313 true] #datom[17592186045418 72 "The Goonies" 13194139534313 true] #datom[17592186045418 73 "action/adventure" 13194139534313 true] #datom[17592186045418 74 1985 13194139534313 true] #datom[17592186045419 72 "Commando" 13194139534313 true] #datom[17592186045419 73 "action/adventure" 13194139534313 true] #datom[17592186045419 74 1985 13194139534313 true] #datom[17592186045420 72 "Repo Man" 13194139534313 true] #datom[17592186045420 73 "punk dystopia" 13194139534313 true] #datom[17592186045420 74 1984 13194139534313 true]], :tempids {-9223301668109598131 17592186045418, -9223301668109598130 17592186045419, -9223301668109598129 17592186045420}}
``` 

## Query the Data

https://docs.datomic.com/on-prem/getting-started/query-the-data.html

Two ways to get data:

1. query: uses datomic language
2. pull

Get current database value:

``` bash
(def db (d/db conn))
db
  ##> {:t 1001, :next-t 1005, :db-name "hello", :database-id "5dbc3927-e737-4646-9372-a22597e9606c", :type :datomic.client/db}
``` 

This is the value of the database to use.

### Ex01: Find ids

Defining a query:

``` bash
(def all-movies-q '[:find ?e 
                           :where [?e :movie/title]])
``` 

This means: find ids of all entities which have an attribute called `:movie/title`

Running the query:

``` bash
(d/q all-movies-q db)
  ##> [[17592186045418] [17592186045419] [17592186045420]]
``` 

### Ex02: Get all the titles

Get all the titles:

``` bash
(def all-titles-q '[:find ?movie-title 
                           :where [_ :movie/title ?movie-title]])
``` 

Notice `underscore`: It means: we are not  interested in the entity id but value of `:movie/title`

The query reads as "find all movie titles that has an attribute `:movie/title` and assign the title to a logic variable called `?movie-title`"

Run the query:

``` bash
(d/q all-titles-q db)
  ##> [["Commando"] ["The Goonies"] ["Repo Man"]]
``` 

### Ex03: Filter by release date

Get titles of movies released in 1985:

``` bash
(def titles-from-1985 '[:find ?title 
                               :where [?e :movie/title ?title] 
                                      [?e :movie/release-year 1985]])
(d/q titles-from-1985 db)
  ##> [["Commando"] ["The Goonies"]]
``` 

Now, `:where` has two clauses, one to bind `:movie/title` attribute and one to filter by `:movie/release-year`. These two clauses are joined.

Note that we use `?e` instead of `_` because it is the join point for the two clauses.

### Ex04: Get all attribute data

``` bash
(def all-data-from-1985 '[:find ?title ?year ?genre 
                                 :where [?e :movie/title ?title] 
                                        [?e :movie/release-year ?year] 
                                        [?e :movie/genre ?genre] 
                                        [?e :movie/release-year 1985]])
(d/q all-data-from-1985 db)
  ##> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]]
``` 

## See Historic Data

``` bash
(d/q '[:find ?e 
              :where [?e :movie/title "Commando"]] 
            db)
  ##> [[17592186045419]]
``` 

``` bash
(def commando-id 
         (ffirst (d/q '[:find ?e 
                        :where [?e :movie/title "Commando"]] 
                       db)))
  ##> #'user/commando-id
``` 

Update value of `:movie/genre`

``` bash
(d/transact conn {:tx-data [{:db/id commando-id :movie/genre "future governor"}]})
  ##> {:db-before {:database-id "5dbc3927-e737-4646-9372-a22597e9606c", :db-name "hello", :t 1001, :next-t 1005, :type :datomic.client/db}, :db-after {:database-id "5dbc3927-e737-4646-9372-a22597e9606c", :db-name "hello", :t 1005, :next-t 1006, :type :datomic.client/db}, :tx-data [#datom[13194139534317 50 #inst "2019-11-01T18:26:18.910-00:00" 13194139534317 true] #datom[17592186045419 73 "future governor" 13194139534317 true] #datom[17592186045419 73 "action/adventure" 13194139534317 false]], :tempids {}}
``` 

Get record to verify it is updated:

``` bash
(d/q all-data-from-1985 db)
  ##> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]]
``` 

It looks like it has still the same value. 

Remember:

> A database value is the state of the database at a given point in time. You can issue as many queries against that database value as you want, they will always return the same results.

The connection `db` corresponds to a `database value`

So we need to get the current value of the database first:

``` bash
(def db (d/db conn))
(d/q all-data-from-1985 db)
  ##> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "future governor"]]
``` 

``` bash
db
  ##> {:t 1005, :next-t 1006, :db-name "hello", :database-id "5dbc3927-e737-4646-9372-a22597e9606c", :type :datomic.client/db}
``` 

Get the database at `:t 1004`

``` bash
(def old-db (d/as-of db 1004))
(d/q all-data-from-1985 old-db)
  ##> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]]
``` 

> You can also use "since" instead of "as-of", which returns a database value with only changes added after a point in time.

Use `history` to see all the values a given attribute has held:

``` bash
(def hdb (d/history db))
(d/q '[:find ?genre 
              :where [?e :movie/title "Commando"] 
                     [?e :movie/genre ?genre]] 
             hdb)
  ##> [["action/adventure"] ["future governor"]]
``` 

## Tutorial

### Introduction

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

#### List and Map Forms

``` bash
[:db/add "foo" :db/ident :green]
``` 

- assertion: `:db/add`
- temporary entity id: `"foo"`
- attribute for identifiers: `:db/ident`
- datom's value: `:green`

This is equivalent to:

``` bash
{:db/ident :green}
``` 

Maps imply and are equivalent to a set of assertions, all about the same entity.

``` bash
(d/transact
	conn 
	{:tx-data [{:db/ident :red}
						 {:db/ident :green}
						 {:db/ident :blue}
						 {:db/ident :yellow}]})
=> ;; returns a big map
``` 

This transaction adds four colors to the database.

#### Programming with Data

``` bash
(defn make-idents
	[x]
	(mapv #(hash-map :db/ident %) x))
  ##> #'user/make-idents
(def sizes [:small :medium :large :xlarge])
  ##> #'user/sizes
(make-idents sizes)
  ##> [#:db{:ident :small} #:db{:ident :medium} #:db{:ident :large} #:db{:ident :xlarge}]
``` 

`make-idents` is a pure function with no database.

Let's insert the data into database:

``` bash
(def types [:shirt :pants :dress :hat])
(def colors [:red :green :blue :yellow])
(d/transact conn {:tx-data (make-idents sizes)})
(d/transact conn {:tx-data (make-idents types)})
``` 

#### Schema

``` bash
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
``` 

#### Sample Data

``` bash
(def sample-data
  (->> (for [color colors size sizes type types]
         {:inv/color color
          :inv/size size
          :inv/type type})
       (map-indexed
        (fn [idx map]
          (assoc map :inv/sku (str "SKU-" idx))))
        vec))
sample-data
=> ;; 64 (4 x 4 x 4) maps

(d/transact conn {:tx-data sample-data})
``` 

### Read

#### Database Values

`db` API returns the latest database value from a connection:

``` bash
(def db (d/db conn))
``` 

Analogy: a connection references the entire history, similar to source code repository. A database value from db is similar to checkout.

#### Pull

Lookup ref: a two element list of unique attribute + value

``` bash
[:inv/sku "SKU-42"]
``` 

``` bash
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

``` bash
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

#### More Schema

We have a new requirement. Database needs to track orders too.

``` bash
(def order-schema
  [{:db/ident :order/items
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/many
    :db/isComponent true}
   {:db/ident :item/id
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   {:db/ident :item/count
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one}])
(d/transact conn {:tx-data order-schema})
=> ;; transaction result map
``` 

#### More Data

``` bash
(def add-order
  {:order/items
   [{:item/id [:inv/sku "SKU-25"]
     :item/count 10}
    {:item/id [:inv/sku "SKU-26"]
     :item/count 20}]})

(d/transact conn {:tx-data [add-order]})
``` 

### Read Revisited: More Query

First, get the latest value of the database:

``` bash
(def db (d/db conn))
``` 

#### Parameterized Query

``` bash
(d/q '[:find ?sku
       :in $ ?inv
       :where [?item :item/id ?inv]
              [?order :order/items ?item]
              [?order :order/items ?other-item]
              [?other-item :item/id ?other-inv]
              [?other-inv :inv/sku ?sku]]
     db [:inv/sku "SKU-25"])
=> [["SKU-25"] ["SKU-26"]]
``` 




