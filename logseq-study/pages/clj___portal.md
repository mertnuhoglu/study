-
- Query Template: Find tag
	- query-table:: true
	  #+BEGIN_QUERY
	  {:title "Find: clj/portal blokları"
	   :query [:find (pull ?b [*])
	                :where
	                [?p :block/name "clj/portal"]
	                [?b :block/ref-pages ?p]]
	  }
	  #+END_QUERY