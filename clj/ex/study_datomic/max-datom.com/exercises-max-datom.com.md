
```{r}
(d/q '[:find ?v
	:where [_ :book/publication-date ?v]] db)
[[#inst "2031-03-19T00:00:00.000-00:00"]
 [#inst "2032-02-15T00:00:00.000-00:00"]
 [#inst "2032-09-09T00:00:00.000-00:00"]
 [#inst "2032-12-24T00:00:00.000-00:00"]
 [#inst "2031-05-21T00:00:00.000-00:00"]
 [#inst "2031-10-31T00:00:00.000-00:00"]
 [#inst "2032-07-04T00:00:00.000-00:00"]
 [#inst "2031-01-13T00:00:00.000-00:00"]
 [#inst "2033-04-07T00:00:00.000-00:00"]
 [#inst "2032-06-17T00:00:00.000-00:00"]]
```


```{r}
(d/q '[:find ?v
       :where [_ :book/author ?v]] db)
[[87960930222164]
 [87960930222165]
 [87960930222166]
 [87960930222167]
 [87960930222168]
 [87960930222169] 
 [87960930222170] 
 [87960930222171] 
 [87960930222172]]
```

```{r}
(d/q '[:find ?n
       :where 
       [_ :author/first+last-name ?n]
       ] db)
[[["Miguel" "Dvd Rom"]]
 [["J. R." "Token"]]
 [["E. L." "Mainframe"]]
 [["Charles" "Diskens"]]
 [["Perry" "Farrell"]]
 [["Miles" "Dyson"]]
 [["Napoleon" "Desktop"]] 
 [["Segfault" "Larsson"]] 
 [["Kim" "K"]]]
```

```{r}
(d/q '[:find ?e ?v
       :where [?e :author/first+last-name ?v]] db)
[[87960930222167 ["Segfault" "Larsson"]]
 [87960930222172 ["Perry" "Farrell"]]
 [87960930222171 ["Miguel" "Dvd Rom"]]
 [87960930222169 ["J. R." "Token"]]
 [87960930222164 ["Kim" "K"]]
 [87960930222170 ["Charles" "Diskens"]]
 [87960930222166 ["E. L." "Mainframe"]]
 [87960930222165 ["Miles" "Dyson"]]
 [87960930222168 ["Napoleon" "Desktop"]]]
```

```{r}
(d/q '[:find ?v
       :where 
       [?b :book/publication-date ?v]
       [?b :book/name "Process and Grow RAM"]] db)
[[#inst "2032-07-04T00:00:00.000-00:00"]]
```

```{r}
(d/q '[:find (pull ?e [*])
       :where [?e :book/author]] db)
[[{:db/id 87960930222173,
   :book/id #uuid "2fd9f72e-5cab-4a63-91e9-2cc3fbfc5f91",
   :book/name "Fifty Shades Of Transistors",
   :book/author {:db/id 87960930222166},
   :book/publication-date #inst "2032-02-15T00:00:00.000-00:00",
   :book/bestseller true,
   :book/id+name
   [#uuid "2fd9f72e-5cab-4a63-91e9-2cc3fbfc5f91"
    "Fifty Shades Of Transistors"]}]
 [{:db/id 87960930222174,
   :book/id #uuid "03db52ef-4ca3-4b1f-9fd0-7e8e91e9c4fc",
   :book/name "The Processor with the Intel Tattoo",
   :book/author {:db/id 87960930222167},
   :book/publication-date #inst "2031-03-19T00:00:00.000-00:00",
   :book/id+name
   [#uuid "03db52ef-4ca3-4b1f-9fd0-7e8e91e9c4fc"
    "The Processor with the Intel Tattoo"]}]
```

```{r}
(d/q '[:find (pull ?e [:book/name 
                       {:book/author 
                        [:author/first-name
                         :author/last-name]}])
       :where [?e :book/author]] db)
[[{:book/name "Fifty Shades Of Transistors",
   :book/author
   {:author/first-name "E. L.", :author/last-name "Mainframe"}}]
 [{:book/name "The Processor with the Intel Tattoo",
   :book/author
   {:author/first-name "Segfault", :author/last-name "Larsson"}}]
```

```{r}
(d/q '[:find (pull ?e [:book/_author :author/first-name :author/last-name])
       :where [?e :author/id #uuid "14E86ACF-000B-463E-90CB-CEA0927A97DA"]] db)
[[{:author/first-name "Napoleon",
   :author/last-name "Desktop",
   :book/_author [{:db/id 87960930222175} {:db/id 87960930222176}]}]]
```

```{r}
(d/q '[:find (pull ?e 
                   [:author/first-name 
                    :author/last-name 
                    {:book/_author 
										 [:book/name :book/publication-date]} ])
       :where [?e :author/id #uuid "14E86ACF-000B-463E-90CB-CEA0927A97DA"]] db)
[[{:author/first-name "Napoleon",
   :author/last-name "Desktop",
   :book/_author [{:db/id 87960930222175} {:db/id 87960930222176}]}]]
;; =>
[[{:author/first-name "Napoleon",
   :author/last-name "Desktop",
   :book/_author
   [{:book/name "Process and Grow RAM",
     :book/publication-date #inst "2032-07-04T00:00:00.000-00:00"}
    {:book/name "Outwitting the Garbage Collector",
     :book/publication-date #inst "2033-04-07T00:00:00.000-00:00"}]}]]
```

```{r}
(def author-id #uuid "35636B79-EE46-4447-8AA7-3F0FB351C45C")

(d/q '[:find (pull ?e [:author/first-name :author/last-name])
       :where [?e :author/id _]] db author-id)
[[{:author/first-name "Kim", :author/last-name "K"}]
 [{:author/first-name "Miles", :author/last-name "Dyson"}]
 [{:author/first-name "E. L.", :author/last-name "Mainframe"}]
 [{:author/first-name "Segfault", :author/last-name "Larsson"}]
 [{:author/first-name "Napoleon", :author/last-name "Desktop"}]
 [{:author/first-name "J. R.", :author/last-name "Token"}]
 [{:author/first-name "Charles", :author/last-name "Diskens"}]
 [{:author/first-name "Miguel", :author/last-name "Dvd Rom"}]
 [{:author/first-name "Perry", :author/last-name "Farrell"}]]
```

```{r}
(def author-id #uuid "35636B79-EE46-4447-8AA7-3F0FB351C45C")

(d/q '[:find (pull ?e [:author/first-name :author/last-name])
       :in $ ?a
       :where [?e :author/id ?a]] db author-id)
[[{:author/first-name "Charles", :author/last-name "Diskens"}]]
```

```{r}
(def author-ids [#uuid "0955EDF7-FF8F-4EC2-AFB2-380E7E5D48D7"
                 #uuid "B7761785-79F9-49FA-97AF-13B4F5C2BCC2"])

(d/q '[:find (pull ?e [:author/first-name :author/last-name])
       :in $ [?a ...]
       :where [?e :author/id ?a]] db author-ids)
[[{:author/first-name "Segfault", :author/last-name "Larsson"}]
 [{:author/first-name "Miguel", :author/last-name "Dvd Rom"}]]
```

```{r}
(d/q '[:find  ?user-name
       :where [?user :user/id #uuid "1B341635-BE22-4ACC-AE5B-D81D8B1B7678"]
              [?user :user/first+last-name ?user-name]] db)
```

Aggregate function:

```{r}
(d/q '[:find  ?user-name (count ?post)
       :where [?user :user/id #uuid "1B341635-BE22-4ACC-AE5B-D81D8B1B7678"]
              [?user :user/first+last-name ?user-name]
              [?post :post/author ?user]
] db)
[[["E. L." "Mainframe"] 3]]
```

Update the query to return the count of user posts and the post author first+last-name only if a post does not have any :post/dislikes

```{r}
(d/q '[:find (count ?post) ?fln
       :where [?user :user/first+last-name ?fln]
			 [?post :post/author ?user]
			 (not [?post :post/dislikes])]
			 db)
[[1 ["E. L." "Mainframe"]]]
```

```{r}
(d/q '[:find  (pull ?farm [:farm/name [:farm/expired-battery-count :xform ns/int->str]])
       :where [?farm :farm/name]]
     db)
```

Using :xform update the query to return the :user/first+last-name of the post author and a string in the form of Comment Count: <count> for the :post/comments

```{r}
(defn comment-count-str [x]
  (str "Comment Count: " (count x)))

(d/q '[:find  (pull ?posts [{:post/author [:user/first+last-name]}])
       :where [?posts :post/author _]] db)
[[{:post/author {:user/first+last-name ["E. L." "Mainframe"]}}]
 [{:post/author {:user/first+last-name ["E. L." "Mainframe"]}}]
 [{:post/author {:user/first+last-name ["E. L." "Mainframe"]}}]
 [{:post/author {:user/first+last-name ["Segfault" "Larsson"]}}]]
```

```{r}
(d/q '[:find  (pull ?posts 
               [{:post/author [:user/first+last-name]} 
      			    {:post/comments [:post/message-url]}])
       :where [?posts :post/author _]] db)
[[{:post/author {:user/first+last-name ["E. L." "Mainframe"]}}]
 [{:post/author {:user/first+last-name ["E. L." "Mainframe"]}}]
 [{:post/author {:user/first+last-name ["E. L." "Mainframe"]}}]
 [{:post/author {:user/first+last-name ["Segfault" "Larsson"]}}]]
```

```{r}
(d/q '[:find  (pull ?posts 
               [{:post/author [:user/first+last-name]} 
			    {:post/comments [:post/message-url]}])
       :where [?posts :post/author _]] db)
[[{:post/author {:user/first+last-name ["E. L." "Mainframe"]},
   :post/comments "Comment Count: 3"}]
 [{:post/author {:user/first+last-name ["E. L." "Mainframe"]},
   :post/comments "Comment Count: 2"}]
 [{:post/author {:user/first+last-name ["E. L." "Mainframe"]},
   :post/comments "Comment Count: 0"}]
 [{:post/author {:user/first+last-name ["Segfault" "Larsson"]},
   :post/comments "Comment Count: 2"}]]
```

