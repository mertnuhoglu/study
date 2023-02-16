(ns sof.sof17)

; [currying - Higher-order functions in Clojure - Stack Overflow](https://stackoverflow.com/questions/5329079/higher-order-functions-in-clojure)

;I'm wondering what the idiomatic way of creating and managing higher-order functions in a Haskell-like way is. In Clojure I can do the following:

(defn sum [a b] (+ a b))
;But (sum 1) doesn't return a function: it causes an error. Of course, you can do something like this:

(defn sum
  ([a] (partial + a))
  ([a b] (+ a b)))
;In this case:

(sum 1)
;#<core$partial$fn__3678 clojure.core$partial$fn__3678@1acaf0ed>
((sum 1) 2)
;3

;But it doesn't seem like the right way to proceed. Any ideas?

; a01:
; The reason this doesn't happen by default in Clojure is that we prefer variadic functions to auto-curried functions, I suppose.

; a02:

(defn curry
  ([f len] (curry f len []))
  ([f len applied]
   (fn [& more]
     (let [args (concat applied (if (= 0 (count more)) [nil] more))]
       (if (< (count args) len)
         (curry f len args)
         (apply f args))))))

;Here's how to use it:

(def add (curry + 2)) ; read: curry plus to 2 positions
((add 10) 1) ; => 11