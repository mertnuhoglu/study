(ns e06)

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

; a01: assoc
(assoc-in db [:student] form)
;=> {:department {}, :student {:name "Can Ali", :department "Matematik"}}
