(ns fn.juxt)

; rfr: video/20230223-mert-clj-egzersiz-51.mp4

; (juxt f)
; (juxt f g)
; (juxt f g h)
; (juxt f g h & fs) #nclk/çok-önemli
; Takes a set of functions and returns a fn that is the juxtaposition
; of those fns.  The returned fn takes a variable number of args, and
; returns a vector containing the result of applying each fn to the
; args
; (left-to-right).
; ((juxt a b c) x) => [ (a x) (b x) (c x)]

;; Extract values from a map, treating keywords as functions.
((juxt :a :b) {:a 1 :b 2 :c 3 :d 4})
;;=> [1 2]

(comment
  ; önce (:a) fonksiyonunu uyguluyor
  ; sonra (:b) fonksiyonunu uyguluyor
  ; bunların sonuçlarını yan yana diziyor
  (def coll {:a 1 :b 2 :c 3 :d 4})
  [(:a coll) (:b coll)]
  ;=> [1 2]
  ,)

;; "Explode" a value.
((juxt identity name) :keyword)
;;=> [:keyword "keyword"]

(comment
  [(identity :keyword) (name :keyword)]
  ;=> [:keyword "keyword"]
  ,)

(juxt identity name)
;...is the same as:
(fn [x] [(identity x) (name x)])

;; eg. to create a map:
(into {} (map (juxt identity name) [:a :b :c :d]))
;;=> {:a "a" :b "b" :c "c" :d "d"}

(comment
  (def f (juxt identity name))
  (map f [:a :b :c :d])
  ; ≣
  [(f :a) (f :b) (f :c) (f :d)]
  ;=> [[:a "a"] [:b "b"] [:c "c"] [:d "d"]]

  ; apply olsaydı?
  #_(apply f [:a :b :c :d])
  ; ≣
  #_(f :a :b :c :d)
  ;Wrong number of args (4) passed to: clojure.core/identity
  ; neden apply uygulanamıyor?

  ,)

;; Get the first character and length of string
((juxt first count) "Clojure Rocks")
;;=> [\C 13]

;; sort list of maps by multiple values
(sort-by (juxt :a :b) [{:a 1 :b 3} {:a 1 :b 2} {:a 2 :b 1}])
;;=> [{:a 1 :b 2} {:a 1 :b 3} {:a 2 :b 1}]
