(ns fn.assoc-in)

; TODO: daha önce videoda bunları yapmıştık bulup ekleyelim.
; rfr: assoc_update.clj
; rfr: video/20230216-mert-clj-egzersiz-39.mp4
; rfr: fn/assoc.clj
; rfr: fn/update-in.clj

; (assoc-in m [k & ks] v)
; Associates a value in a nested associative structure, where ks is a
; sequence of keys and v is the new value and returns a new nested structure.
; If any levels do not exist, hash-maps will be created.
;

;; ## assoc-in

(def users [{:name "James" :age 26}
            {:name "John" :age 43}])

(assoc-in users [1 :age] 44)
;;=> [{:name "James", :age 26} {:name "John", :age 44}]

;; insert the password of the second (index 1) user
(assoc-in users [1 :password] "nhoJ")
;;=> [{:name "James", :age 26} {:password "nhoJ", :name "John", :age 43}]

; olmayan (yeni) bir keyword verdiğimiz için eklemeyi yapıyor

;; Also (assoc m 2 {...}) or (conj m {...})
(assoc-in users [2] {:name "Jack" :age 19})
;;=> [{:name "James", :age 26} {:name "John", :age 43} {:name "Jack", :age 19}]

;; From http://clojure-examples.appspot.com/clojure.core/assoc-in

(assoc-in {} [:cookie :monster :vocals] "Finntroll")
; => {:cookie {:monster {:vocals "Finntroll"}}}

(assoc-in {} [:a] "ali")
;=> {:a "ali"}

(assoc-in {} [:a :b] "ali")
;=> {:a {:b "ali"}}

(assoc-in {} [:a :b] [1 2 3])
;=> {:a {:b [1 2 3]}}

(get-in {:a {:b 1}} [:a :b])
;;=> 1

(get-in {:cookie {:monster {:vocals "Finntroll"}}} [:cookie :monster])
; => {:vocals "Finntroll"}

(assoc-in {} [1 :connections 4] 2)
; => {1 {:connections {4 2}}}
; buradaki rakamlar vektörün indeksine değil, mapin indeksine (key) denk geliyor.
; çünkü vektör yok ortalıkta, yeni map oluşturuyor

(assoc-in {} [1 :connections 4] [2])
;=> {1 {:connections {4 [2]}}}
