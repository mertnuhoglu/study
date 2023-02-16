(ns fn.update)

; TODO: daha önce videoda bunları yapmıştık bulup ekleyelim.
; rfr: assoc_update.clj
; rfr: video/20230216-mert-clj-egzersiz-39.mp4
; rfr: fn/assoc.clj
; rfr: fn/update-in.clj

; (update m k f)
; (update m k f x)
; (update m k f x y)
; (update m k f x y z)
; (update m k f x y z & more)
; 'Updates' a value in an associative structure, where k is a
; key and f is a function that will take the old value
; and any supplied args and return the new value, and returns a new
; structure.  If the key does not exist, nil is passed as the old value.

;; [update - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/update)

(def m {:name "James" :age 26})
;;=> #'user/p

(update m :age inc)
;;=> {:name "James", :age 27}

;; remember, the value of m hasn't changed because it is immutable!
(identity m)
;; {:name "James", :age 26}

(update m :age + 10)
;;=> {:name "James", :age 36}

;; bir nevi şöyle bir şey yapıyoruz:
; (+ 26 10)

;; Here we see that the keyed object is ; the first argument in the function call.
;; i.e. :age (- 26 10) => 16
(update m :age - 10)
;;=> {:name "James", :age 16}

;; the map in update can be nil, and f will still be applied to nil and ; return a value

(def empty-map nil)
#'user/empty-map

(update empty-map :isim #(str "ali" %))
;=> {:isim "ali"}

;; can also use in vectors []
(update [1 2 3] 0 inc)
;;=> [2 2 3]

; vektör de olsa map de olsa 2. argüman k argümanı indeks görevi görüyor

(update [] 0 #(str "foo" %))
;;=> ["foo"]

;; For a simple change of value we can use constantly
(def hm {:banana 1, :apple 2, :orange 3})

(update hm :banana (constantly 6))
;;=> {:banana 6 :apple 2 :orange 3}

;; Which is equivalent to using "assoc"
(assoc hm :banana 6)
;;=> {:banana 6 :apple 2 :orange 3}

