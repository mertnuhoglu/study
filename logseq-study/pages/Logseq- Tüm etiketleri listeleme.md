tags:: logseq, opal/tag, standard, logseq/query

- [How to query all tags and display them - Questions & Help - Logseq](https://discuss.logseq.com/t/how-to-query-all-tags-and-display-them/2273/5)
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