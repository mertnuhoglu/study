tags:: opal, ndx, clj

- index clj
	- Seviyeye göre
		- ((63a86641-d2dc-4107-8aaa-caaa4fbfbc68))
- Başlangıç seviyesi
  id:: 63a86641-d2dc-4107-8aaa-caaa4fbfbc68
	- Calva temelli
		- [[rtc- Get Started with Clojure - Calva User Guide]]
-
- tag-clj
	- query-sort-by:: page
	  query-sort-desc:: false
	  #+BEGIN_QUERY
	  {:query [:find (pull ?p [*])
	  	:where
	  	[?b :block/ref-pages ?p]
	  	[?p :block/name ?tag]
	  	[(clojure.string/starts-with? ?tag "clj")]] 
	      ;;   👆
	  }
	  #+END_QUERY