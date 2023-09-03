(ns ex.e15)

(comment
  (def m
    {:person/id {1 {:person/id 1, :person/name "ali", :person/surname "ahmet"},
                 2 {:person/id 2, :person/name "ahmet", :person/surname "ayşe"}}})
  (for [{:keys [username email]} m]
    (str "table-id" m))
  ;("table-id#:person{:id {1 #:person{:id 1, :name \"ali\", :surname \"ahmet\"}, 2 #:person{:id 2, :name \"ahmet\", :surname \"ayşe\"}}}")

  (:person/name (get-in m [:person/id 1]))
  ;=> "ali"

  (:person/id m)
  ;=> {1 #:person{:id 1, :name "ali", :surname "ahmet"}, 2 #:person{:id 2, :name "ahmet", :surname "ayşe"}}
  (vals (:person/id m))
  ;=> (#:person{:id 1, :name "ali", :surname "ahmet"} #:person{:id 2, :name "ahmet", :surname "ayşe"})

  (first (vals (:person/id m)))
  ;=> #:person{:id 1, :name "ali", :surname "ahmet"}

  (for [{:keys [person/name person/surname]} (vals (:person/id m))]
    (str "name: " name " surname: " surname))
  ;=> ("name: ali surname: ahmet" "name: ahmet surname: ayşe")

  ; end
  ,)

