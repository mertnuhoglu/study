(defsc Person [this {:person/keys [id name age] :as props} {:keys [onDelete]}]  (1)
    ...
    (dom/h5 (str name " (age: " age ")") (dom/button {:onClick #(onDelete id)} "X")))) ; (2)

...

(defsc PersonList [this {:list/keys [id label people] :as props}] (1)
  ...
  (let [delete-person (fn [person-id] (comp/transact! this [(api/delete-person {:list/id id :person/id person-id})]))] ; (2)

