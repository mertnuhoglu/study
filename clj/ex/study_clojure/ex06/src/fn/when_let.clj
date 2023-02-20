(ns fn.when-let)

;(when-let bindings & body)
;bindings => binding-form test
;When test is true, evaluates body with binding-form bound to the value of test

; [when-let - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/when-let)

(when-let [a 1]
  (+ a 2))

;; test is evaluated before values are bound to binding, so destructuring works
(when-let [a false] a)
;=> nil
(when-let [a false] (+ a 1))
;=> nil

(when-let [[a] nil] [a])
;=> nil
(when-let [[a] [:a]] [a])
;=> [:a]
(when-let [[a] []] [a])
;=> [nil]
