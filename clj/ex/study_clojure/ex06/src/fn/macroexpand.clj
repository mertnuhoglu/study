(ns fn.macroexpand)

; (macroexpand form)
; Repeatedly calls macroexpand-1 on form until it no longer
; represents a macro form, then returns it.  Note neither
; macroexpand-1 nor macroexpand expand macros in subforms.
;

(macroexpand '(-> Ms
                (get 0)
                (:k1)))
;=> (:k1 (get Ms 0))

(macroexpand '(defn f [a b]
                (+ a b)))
;=> (def f (clojure.core/fn ([a b] (+ a b))))

