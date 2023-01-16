tags:: tag

- Query: Find tag: clj
	- query-table:: true
	  #+BEGIN_QUERY
	  {:title "Find: clj blokları"
	   :query [:find (pull ?b [*])
	                :where
	                [?p :block/name "clj"]
	                [?b :block/ref-pages ?p]]
	  }
	  #+END_QUERY