(ns e05_database)

;; rfr: [[20230414-Clj-Clerk-ile-Uygulama-Simulasyonu]]
;;
;; # p05: Sistem formu veritabanına kaydeder

; a01: Veritabanı bellekteki bir hashmap olabilir

; boş veritabanı:
(def db {})

; veritabanında iki tablo olsun
(def db2
  {:department {}
   :student {}})

; bu tabloların içlerini dolduralım
(def db2
  {:department {101 {:id 101 :title "Matematik"}
                102 {:id 102 :title "Fizik"}
                103 {:id 103 :title "Sosyoloji"}}
   :student {201 {:id 201 :name "Can Toprak"}
             202 {:id 202 :name "Ali Deniz"}
             203 {:id 203 :name "Zeynep Irmak"}}})

; [Student] n-1 [Department]
; Bu ilişkiyi de veritabanına koyalım
; a01.1: İlişkiyi Student tarafında tutalım
(def db3
  {:department {101 {:id 101 :title "Matematik"}
                102 {:id 102 :title "Fizik"}
                103 {:id 103 :title "Sosyoloji"}}
   :student {201 {:id 201 :name "Can Toprak" :department/id 101}
             202 {:id 202 :name "Ali Deniz" :department/id 102}
             203 {:id 203 :name "Zeynep Irmak" :department/id 102}}})

; a01.2: İlişkiyi Student tarafında tutalım. Fakat vektör olarak tutalım.
(def db3
  {:department {101 {:id 101 :title "Matematik"}
                102 {:id 102 :title "Fizik"}
                103 {:id 103 :title "Sosyoloji"}}
   :student {201 {:id 201 :name "Can Toprak" :department [[:department/id 101]]}
             202 {:id 202 :name "Ali Deniz" :department [[:department/id 102]]}
             203 {:id 203 :name "Zeynep Irmak" :department [[:department/id 102]]}}})

; a01.3: İlişkiyi Department tarafında tutalım.
(def db3
  {:department {101 {:id 101 :title "Matematik" :students [[:student/id 201]]}
                102 {:id 102 :title "Fizik" :students [[:student/id 202] [:student/id 203]]}
                103 {:id 103 :title "Sosyoloji" :students []}}
   :student {201 {:id 201 :name "Can Toprak"}
             202 {:id 202 :name "Ali Deniz"}
             203 {:id 203 :name "Zeynep Irmak"}}})

; a02: Veritabanı bellekteki bir atom olabilir

(def db
  (atom {:department {101 {:id 101 :title "Matematik" :students [[:student/id 201]]}
                      102 {:id 102 :title "Fizik" :students [[:student/id 202] [:student/id 203]]}
                      103 {:id 103 :title "Sosyoloji" :students []}}
         :student {201 {:id 201 :name "Can Toprak"}
                   202 {:id 202 :name "Ali Deniz"}
                   203 {:id 203 :name "Zeynep Irmak"}}}))

; a03: Veritabanı datomic olabilir
