(ns fn.update-in)

; TODO: daha önce videoda bunları yapmıştık bulup ekleyelim.
; rfr: assoc_update.clj
; rfr: video/20230216-mert-clj-egzersiz-39.mp4
; rfr: fn/update.clj
; rfr: fn/assoc-in.clj

; (update-in m ks f & args)
; 'Updates' a value in a nested associative structure, where ks is a
; sequence of keys and f is a function that will take the old value
; and any supplied args and return the new value, and returns a new
; nested structure.  If any levels do not exist, hash-maps will be
; created.

;; ## update-in

;; [update-in - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/update-in)

(def users [{:name "James" :age 26}  {:name "John" :age 43}])
;;=> #'user/users

;; similar to assoc-in but does not simply replace the item.
;; the specified function is performed on the matching item.
;; here the age of the second (index 1) user is incremented.
(update-in users [1 :age] inc)
;;=> [{:name "James", :age 26} {:name "John", :age 44}]

; yukarıda vektörün içinde map vardı
; mapin içinde map de olabilir
;;You can use update-in in a nested map too, in order to update more than
;;one value:

(def m {:1 {:value 0, :active false}, :2 {:value 0, :active false}})

; a01: update-in ve assoc ile alt mapi (submap) değiştirmek
(update-in m [:1] assoc :value 1 :active true)
;;=>{:1 {:value 1, :active true}, :2 {:value 0, :active false}}

; a02: assoc-in ile alt mapi değiştirmek
(assoc-in m [:1 :value] 1)
;;=> {:1 {:value 1, :active false}, :2 {:value 0, :active false}}

; ancak a02'de sadece :value propertysini değiştirebildik. yukarıdaysa hem :value hem de :active propertylerini değiştirmiştik


