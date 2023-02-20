(ns fn.disj)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; (disj set)
; (disj set key)
; (disj set key & ks)
; disj[oin]. Returns a new set of the same
; (hashed/sorted) type, that
; does not contain key
; (s).
;

; conj'un tersi
; yalnız set alıyor argüman olarak

(disj #{:foo :bar} :foo)
; => #{:bar}

(disj '(:a :b) :b)
;class clojure.lang.PersistentList cannot be cast to class clojure.lang.IPersistentSet (clojure.lang.PersistentList and clojure.lang.IPersistentSet are in unnamed module of loader 'app')
