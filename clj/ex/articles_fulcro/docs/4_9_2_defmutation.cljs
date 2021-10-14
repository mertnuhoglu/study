(ns app.mutations
  (:require
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]
    [com.fulcrologic.fulcro.algorithms.merge :as merge]))

(defmutation delete-person

  [{list-id   :list/id
    person-id :person/id}]

  (action [{:keys [state]}]

    (swap! 
      state 
      merge/remove-ident*                 ; (1)
      [:person/id person-id]              ; (2)
      [:list/id list-id :list/people])))  ; (3)

; (1) removes an ident from a list of idents
; (2) ident to be removed
; (3) path to the list of idents that you want to remove ident from

...

(defsc Person 

  [this 
   {:person/keys [id name age] :as props}    ; 🦄 (3)
   {:keys [onDelete]   ; 3️⃣ 🐹
    ...}]

  (dom/h5 
    (str name " (age: " age ")")) 
  (dom/button 
    {:onClick #(onDelete id)} ; 🐱 1️⃣
    "X")) 

...

(defsc PersonList 

  [this 
   {:list/keys [id label people] :as props}]
  ...

  (let 
    [delete-person  ; 1️⃣ 🐹
     (fn [person-id]   ; 🐱 2️⃣
       (comp/transact! 
         this 
         [(api/delete-person 
            {:list/id id :person/id person-id})]))]

    (dom/div
      (dom/h4 label)
      (dom/ul
        (map 
          #(ui-person 
             (comp/computed 
               %     ; 🦄 (2)
               {:onDelete delete-person}))    ; 2️⃣ 🐹
          people)))))  ; 🦄 (1)


(comment

  (com.fulcrologic.fulcro.application/current-state app.application/app)
  {:friends [:list/id :friends]
   :enemies [:list/id :enemies]
   :person/id {1 {:person/id 1, :person/name "Sally", :person/age 32}
               2 {:person/id 2, :person/name "Joe", :person/age 22}
               3 {:person/id 3, :person/name "Fred", :person/age 11}
               4 {:person/id 4, :person/name "Bobby", :person/age 55}}
   :list/id {:friends {:list/id :friends, :list/label "Friends", :list/people [[:person/id 1] [:person/id 2]]}
             :enemies {:list/id :enemies, :list/label "Enemies", :list/people [[:person/id 3] [:person/id 4]]}}}

  (def state (com.fulcrologic.fulcro.application/current-state app.application/app))
  ;; #'cljs.user/state
  (def query (com.fulcrologic.fulcro.components/get-query app.ui/Root))
  ;; #'cljs.user/query

  (com.fulcrologic.fulcro.algorithms.denormalize/db->tree query state state)
  {:friends {:list/id :friends,
             :list/label "Friends",
             :list/people [{:person/id 1, :person/name "Sally", :person/age 32}
                           {:person/id 2, :person/name "Joe", :person/age 22}]},
   :enemies {:list/id :enemies,
             :list/label "Enemies",
             :list/people [{:person/id 3, :person/name "Fred", :person/age 11}
                           {:person/id 4, :person/name "Bobby", :person/age 55}]}}
  ,)

(def data-tree
  {:friends
   [{:list/id :friends
     :list/people [{:person/id 1
                    :person/id 2}]}]})
 
