(ns fn.next)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; rfr: fn/rest.clj

; (next coll)
; Returns a seq of the items after the first. Calls seq on its
; argument.  If there are no more items, returns nil.
;

(def coll [10 20 30])
(next coll)
;=> (20 30)

; rest ile benzer sonuç:
(rest coll)
;=> (20 30)

;; Difference between next and rest:
; tek öğeli listeye next verdiğimde nil döner
(next [:a])
;; => nil
; rest ise () empty seq döner
(rest [:a])
;; => ()

(next [])
;; => nil
(rest [])
;; => ()

(next nil)
;; => nil
(rest nil)
;; => ()

(next '(:alpha :bravo :charlie))
;(:bravo :charlie)

(next (next '(:one :two :three)))
;(:three)

(next (next (next '(:one :two :three))))
;nil
