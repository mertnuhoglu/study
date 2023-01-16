tags:: opal

- Kısaltma Template
  template:: kısaltma
  template-including-parent:: false
	- Kısaltma Title
	  kslt:: kısaltma
	  type:: opal
	  alias:: Kısaltma, Abbreviation, Abbrv
	  description:: ...
- GTD Görev Template
  template:: gtd
  template-including-parent:: false
	- GTD Görev Şablonu
	  status:: next
	  description:: ...
	  created-date:: <%today%>
	  done-at:: ...
- Query Template: Find tag
  template:: Query Find Tag
  template-including-parent:: false
	- #+BEGIN_QUERY
	  {:title "Find: blocks with tag"
	   :query [:find (pull ?b [*])
	                :where
	                [?p :block/name "kslt"]
	                [?b :block/ref-pages ?p]]
	  }
	  #+END_QUERY
- Query Template: List tags under namespace
  template:: Query List Tags in Namespace
  template-including-parent:: false
	- #+BEGIN_QUERY
	  {:query [:find (pull ?p [*])
	  	:where
	  	[?b :block/ref-pages ?p]
	  	[?p :block/name ?tag]
	  	[(clojure.string/starts-with? ?tag "clj")]] 
	      ;;   👆
	  }
	  #+END_QUERY