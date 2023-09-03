(ns ex.e29)

; Title: Clojure Power Tools Part 1 | Kari Marttila Blog id=g14497
; Date: 20230614

; [Clojure Power Tools Part 1 | Kari Marttila Blog](https://www.karimarttila.fi/clojure/2020/10/26/clojure-power-tools-part-1.html)

; p01: let içinde def kullanarak bir objeyi debug etmek:

(if-let [x {:id {:a 1}}]
  "then"
  "else")
;=> "then"

; ->

(if-let [x {:id {:a 1}}]
  (let [_ (def debug-x x)]
    "then")
  "else")
;=> "then"
(:id debug-x)
;=> {:a 1}

; p02: Use hashp for printing values

; Put `#p` before the function you are interested in

(defn f01 [x]
  (+ x 1))

(comment
  (require '[hashp.core])
  (let [r #p (f01 10)]
    (identity r))
  ;#p[ex.e29/eval11723:34] (f01 10) => 11

  ,)

; p03: Use Portal Data Browser to Visualize Data

