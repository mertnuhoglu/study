(ns app.app00h
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.algorithms.merge :as merge]))

; video: Part 2
; Normalize the tree 03
; 18:40

(defonce app (app/fulcro-app))

(defsc Car [this {:car/keys [id model] :as props}]
  {:query [:car/id :car/model]
   :ident :car/id}
  (dom/div
    (js-debugger)
    "Model " model))

(def ui-car (comp/factory Car {:keyfn :car/id}))

(defsc Person [this {:person/keys [id name cars] :as props}]
  {:query [:person/id :person/name {:person/cars (comp/get-query Car)}]
   :ident :person/id}
  (dom/div
    (dom/div "Name: " name)
    (dom/h3 "Cars")
    (dom/ul
      (map ui-car cars))))

(def ui-person (comp/factory Person {:keyfn :person/id}))

(defsc Sample [this {:root/keys [person]}]
  {:query [{:root/person (comp/get-query Person)}]}
  (dom/div
    (ui-person person)))

(comment
  (in-ns 'app.app00h)

  (app/mount! app Sample "app")
  (reset! (::app/state-atom app) {})

  (merge/merge-component! app
    Person {:person/id 1
            :person/name "Joe"
            :person/cars [{:car/id 22
                           :car/model "Escort"}]})

  (app/current-state app)
  ;=> {:car/id {22 #:car{:id 22, :model "Escort"}},
  ;    :person/id {1 #:person{:id 1, :name "Joe", :cars [[:car/id 22]]}}}

  ; :person/cars nested map olarak verildiği halde, merge-component! bunu normalize etti
  ; Nasıl yaptı bunu?
  ; 1. Person'ın query'sini çekti
  ; 2. Bu query'de get-query Car alt query'sini buldu
  ; 3. Bu alt query'nin meta datasından onun ait olduğu componentin Car olduğunu buldu
  ; 4. Car componentinin ident'ini çekti
  ; 5. Alt mapi bu ident'in tablosuna yerleştirdi

  ; cars altında vector of cars yerine tek bir car olsa ne olur?
  (reset! (::app/state-atom app) {})

  (merge/merge-component! app
    Person {:person/id   1
            :person/name "Joe"
            :person/cars {:car/id    22
                          :car/model "Escort"}})

  (app/current-state app)
  ;=> {:car/id {22 #:car{:id 22, :model "Escort"}},
  ;    :person/id {1 #:person{:id 1, :name "Joe", :cars [:car/id 22]}}}

  ; bu durumda tek bir edge ile normalleştirilir

  (comp/get-ident Car {})
  ; [:car/id nil]

  (comp/get-ident Car {:car/id 1})
  ; [:car/id 1]

  (comp/get-ident Car {:car/id 1
                       :car/model "honda"})
  ; [:car/id 1]

  ; Şimdi bu Person datasını nasıl app'in state veritabanına ekleriz?
  ; Bunun için köke (root) bir kenar (edge) ilişkisi eklemeliyiz.
  (reset! (::app/state-atom app) {})
  (merge/merge-component! app
    Person {:person/id   1
            :person/name "Joe2"
            :person/cars {:car/id    22
                          :car/model "Escort"}}
    :replace [:root/person])
  (app/current-state app)
  ;{:car/id {22 #:car{:id 22, :model "Escort"}},
  ; :person/id {1 #:person{:id 1, :name "Joe", :cars [:car/id 22]}},
  ; :root/person [:person/id 1]}

  (app/schedule-render! app)
  (+ 1),)
