tags:: logseq/query

- #ex 01:
	- #+BEGIN_QUERY
	  {:title "list block/name"
	   :query [:find (pull ?b [*])
	                :where              
	                [_ :block/name ?b]
	                ]
	  }
	  #+END_QUERY
- #ex 02:
	- query-sort-by:: page
	  query-sort-desc:: true
	  #+BEGIN_QUERY
	  {:title "Lists all pages"
	   :query [:find (pull ?p [*])
	                :where              
	                [?p :block/name ?b]
	                ]
	  }
	  #+END_QUERY
- #ex a03:
	- #+BEGIN_QUERY
	  {:title "All page tags"
	  :query [:find ?tag-name
	        :where
	        [?tag :page/name ?tag-name]]
	  :view (fn [tags]
	        [:div
	         (for [tag (flatten tags)]
	           [:a.tag.mr-1 {:href (str "/page/" tag)}
	            (str "#" tag)])])}
	  #+END_QUERY