(ns sof.sof16)

; ["reduce" or "apply" using logical functions in Clojure - Stack Overflow](https://stackoverflow.com/questions/2891707/reduce-or-apply-using-logical-functions-in-clojure)

;Neither of the following works due to logical functions being macros:

#_(reduce and [... sequence of bools ...])
#_(apply or [... sequence of bools ...])

; a01: use every? and some instead.

; a02: whenever you want to use a macro as a function:
; Basically you just wrap the macro in an anonymous function.

;(reduce #(and %1 %2) [... sequence of bools ...])