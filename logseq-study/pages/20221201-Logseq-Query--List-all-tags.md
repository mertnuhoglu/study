tags:: logseq/query

# 20221201-Logseq-Query--List-all-tags

- [How to query all tags and display them - Questions & Help - Logseq](https://discuss.logseq.com/t/how-to-query-all-tags-and-display-them/2273/5)
  id:: 22595e6a-8ff6-4c63-a120-e43bd3139e15
	- a01:
		- ```
		  Query example:  "All page tags"
		  #+BEGIN_QUERY
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
		  ```
		- #+BEGIN_QUERY
		  {:title "Page tags in namespace"
		  :query [:find ?tag-name
		        :where
		        [?tag :page/name ?tag-name]
		  	  [(clojure.string/starts-with? ?tag-name "clj")]] 
		  :view (fn [tags]
		        [:div
		         (for [tag (flatten tags)]
		           [:a.tag.mr-1 {:href (str "/page/" tag)}
		            (str "#" tag)])])}
		  #+END_QUERY
	- a02: [[logseq-plugin-tags]]
	-
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
