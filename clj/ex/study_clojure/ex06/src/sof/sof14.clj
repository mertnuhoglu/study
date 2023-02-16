(ns sof.sof14)

; [Clojure apply vs map - Stack Overflow](https://stackoverflow.com/questions/2311528/clojure-apply-vs-map)

; e01:
;Let's imagine an abstract function F and a vector. So,

(def F identity)

(apply F [1 2 3 4 5])

;translates to

(F 1 2 3 4 5)

;which means that F has to be at best case variadic.

;While

(map F [1 2 3 4 5])

;translates to

[(F 1) (F 2) (F 3) (F 4) (F 5)]

; #terim: apply = splatting = unpacking = explode a vector in a function call
; There is name for what apply does in some other dynamic programming languages, it's called splatting or unpacking

; e02: map is lazy by default. make it eager:

;Try wrapping the map expression in a dorun:

(def foundApps
  {:some-key :some-val})
(dorun (map println foundApps))

;Also, since you're doing it just for the side effects, it might be cleaner to use doseq instead:

(doseq [fa foundApps]
  (println fa))


