(ns fn.defmacro)

; (defmacro name doc-string? attr-map? [params*] body)
; (defmacro name doc-string? attr-map?
; ([params*] body) + attr-map?)
; Like defn, but the resulting function name is declared as a
; macro and will be used as a macro by the compiler when it is
; called.

; [Learn clojure macros in Y Minutes](https://learnxinyminutes.com/docs/clojure-macros/)

;; Define a macro using defmacro. Your macro should output a list that can
;; be evaluated as clojure code.
;;
;; This macro is the same as if you wrote (reverse "Hello World")
(defmacro macro1 []
  (list reverse "ali"))

(macroexpand '(macro1))
;=> (#object[clojure.core$reverse 0x465ada9d "clojure.core$reverse@465ada9d"] "ali")

(eval (macroexpand '(macro1)))
;=> (\i \l \a)

(macro1)
;=> (\i \l \a)

; list yerine quote ' kullan
(defmacro macro2 []
  '(reverse "ali"))

(macro2)
;=> (\i \l \a)

;; Macros can take arguments.
(defmacro inc2 [arg]
  (list + 2 arg))

(inc2 2)
;=> 4

; argümanları quote içinde kullanıyorsan `~` ile çağırmalısın
(defmacro inc3 [arg]
  `(+ 2 ~arg))

(inc3 2)
;=> 4

; rest argümanları ~@ ile expand etmelisin
;; You can use the usual destructuring args. Expand list variables using ~@
(defmacro unless3 [arg & body]
  `(if (not ~arg)
     (do ~@body)))

(macroexpand '(unless3 true (reverse "Hello World")))
;=> (if (clojure.core/not true) (do (reverse "Hello World")))

; [defmacro - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/defmacro)

(defmacro unless1 [pred a b]
  `(if (not ~pred) ~a ~b))

;; usage:

(unless1 false (println "Will print") (println "Will not print"))
;Will print
;=> nil

(defmacro unless [cond then]
  (list
    'if
    (list 'not cond)
    then))
;; => #'user/unless
(unless false 1)
;; => 1

(macroexpand '(unless false 1))
;; => (if (not false) 1)

; [Learn clojure macros in Y Minutes](https://learnxinyminutes.com/docs/clojure-macros/)
;
; #terim: macros = code generation procedures
; #code-smell: to write a macro when a function will do
; #terim: code smell = kötü kodlama alışkanlığı