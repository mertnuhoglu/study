tags:: tag

- Tüm datomic blokları
	- query-table:: true
	  #+BEGIN_QUERY
	  {:title "Find: Datomic blokları"
	   :query [:find (pull ?b [*])
	                :where
	                [?p :block/name "datomic"]
	                [?b :block/ref-pages ?p]]
	  }
	  #+END_QUERY
-