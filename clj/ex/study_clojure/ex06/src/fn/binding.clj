(ns fn.binding)

; rfr: fn/syntax/vars_bindings.clj

; (binding bindings & body)
; binding => var-symbol init-expr
;  Creates new bindings for the
; (already-existing) vars, with the
; supplied initial values, executes the exprs in an implicit do, then
; re-establishes the bindings that existed before.  The new bindings
; are made in parallel
; (unlike let); all init-exprs are evaluated
; before the vars are bound to their new values.
;

; Daha önce biz diyorduk ki, clojure'daki tüm objeler değişmezdir
; Fakat biraz bu bir detay var.
; Galiba bu binding bu değişebilir objeler yapmak için kullanılıyor

; [clojure and ^:dynamic - Stack Overflow](https://stackoverflow.com/questions/11730828/clojure-and-dynamic)


;let creates a lexically scoped immutable alias for some value. binding creates a dynamically scoped binding for some Var.
;
;Dynamic binding means that the code inside your binding form and any code which that code calls (even if not in the local lexical scope) will see the new binding.
;
;Given:

(def ^:dynamic x 0)

;binding actually creates a dynamic binding for a Var but let only shadows the var with a local alias:

(binding [x 1] (var-get #'x))
;; 1
(let [x 1] (var-get #'x))
;; 0

(binding [x 1] (identity x))
;; 1
(let [x 1] (identity x))
;; 1

;Lexical vs. dynamic binding:

(defn foo [] (println x))
;#'user/foo
(binding [x 1] (foo))
;1
;nil
(let [x 1] (foo))
;0
;nil

(def y 0)
(defn bar [] (println y))
(let [y 1] (bar))
;0
#_(binding [y 1] (bar))
; Can't dynamically bind non-dynamic var: fn.binding/y

















