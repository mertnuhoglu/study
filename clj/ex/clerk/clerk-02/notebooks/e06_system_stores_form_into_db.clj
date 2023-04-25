(ns e06_system_stores_form_into_db)

(require '[nextjournal.clerk :as clerk])
(require '[clojure.walk :refer [postwalk]])

;; rfr: [[20230414-Clj-Clerk-ile-Uygulama-Simulasyonu]]
;;

^{::clerk/visibility {:code :hide}}
(defn to-clerk [m]
  (postwalk
    #(if
       (string? %)
       (vector %)
       %)
    m))

;; # p06: Sistem formu veritabanına kaydeder

;; a01: Departman atributu stringdir.

(def db
  {:department {}
   :student {}})

(def form
  {:name "Can Ali"
   :department "Matematik"})
(to-clerk form)
;=> {:name ["Can Ali"], :department ["Matematik"]}

; a01.1: assoc-in
(assoc-in db [:student] form)
;=> {:department {}, :student {:name "Can Ali", :department "Matematik"}}

; a01.2: assoc-in id
(def id 201)
(def form2
  (merge {:id id} form))
(assoc-in db [:student id] form2)
;=> {:department {}, :student {201 {:id 201, :name "Can Ali", :department "Matematik"}}}

;; a02: Departman atributu entitydir.

(def form4
  {:name "Can Ali"
   :department {:department/id 101
                :department/title "Matematik"}})

(def form5
  (merge {:id id} form4))
(identity form5)
;=> {:id 201, :name "Can Ali", :department #:department{:id 101, :title "Matematik"}}
(assoc-in db [:student id] form5)
;=> {:department {}, :student {201 {:id 201, :name "Can Ali", :department #:department{:id 101, :title "Matematik"}}}}

;; a03: Departman entity olduğundan, önceden kaydedilmiş olmalı.

(def d101
  {:department/id 101
   :department/title "Matematik"})
(def db2
  (assoc-in db [:department (:department/id d101)] d101))
(identity db2)
;=> {:department {101 #:department{:id 101, :title "Matematik"}}, :student {}}

(assoc-in db2 [:student id] form5)
;=>
;{:department {101 #:department{:id 101, :title "Matematik"}},
; :student {201 {:id 201, :name "Can Ali", :department #:department{:id 101, :title "Matematik"}}}}
