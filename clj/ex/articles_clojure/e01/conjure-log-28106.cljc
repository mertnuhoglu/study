; --------------------------------------------------------------------------------
; eval (current-form): (+ 2 1)
3
; --------------------------------------------------------------------------------
; eval (current-form): (count [1 2 3])
3
; --------------------------------------------------------------------------------
; eval (current-form): (prn x "Hello, World!")
; (err) Syntax error compiling at (src/fireplace/e01.clj:6:3).
; (err) Unable to resolve symbol: x in this context
; --------------------------------------------------------------------------------
; eval (current-form): (bar)
3
; --------------------------------------------------------------------------------
; eval (buf): ...ogic_programming/episode1/src/logic_tutorials/episode1.clj
nil
#'logic-tutorials.episode1/lvar
#'logic-tutorials.episode1/lvar?
#'logic-tutorials.episode1/walk
#'logic-tutorials.episode1/unify
; (err) WARNING: == already refers to: #'clojure.core/== in namespace: logic-tutorials.episode1, being replaced by: #'logic-tutorials.episode1/==
#'logic-tutorials.episode1/==
nil
; --------------------------------------------------------------------------------
; eval (current-form): (defn lvar ([] (lvar "")) ([nm] (gensym (str nm "_"))))
#'logic-tutorials.episode1/lvar
; --------------------------------------------------------------------------------
; eval (current-form): (defn lvar? [v] (symbol? v))
#'logic-tutorials.episode1/lvar?
; --------------------------------------------------------------------------------
; eval (current-form): (lvar "foo")
foo_5705
