tags:: study, clj/datomic

# 20230602-rtc-Datomic-Quickstart,-part-1---Lambda-Island id=g14462

Source: [Datomic Quickstart, part 1 - Lambda Island](https://lambdaisland.com/episodes/datomic-quickstart-part-1)

Difference from traditional database server:

- Pulls transactor out of server into its own process
- Write operations goes through it
- Query engine runs in client app
- Query engine reads directly from storage and caches in memory.
- Peers are notified by transactions. So changes are updated.

Downsides:

- Peers can only run on JVM
- Spend a lot of memory

Solution:

- Peer server with HTTP API

Facts:

- EAV makes a proposition. 
- EAV + Transaction (time) makes a fact.
- EAVT + `added?` makes a datom.

Entity map: `{:user/name "..."}`

```clj
(def tx-result 
  (d/transact [{:user/name "..."}
	             {:user/name "..."}]))
(class tx-result)
(keys @tx-result)
(:db-before @tx-result)
(:db-after @tx-result)
(d/q '[:find ?e :where [?e :user/name "..."]] (:db-after @tx-result))
```

[Part 2 - Lambda Island](https://lambdaisland.com/episodes/datomic-quickstart-part-2)

```clj

```



