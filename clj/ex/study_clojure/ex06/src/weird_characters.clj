(ns weird-characters)

; set literal #{}

(:a #{:a :b})
;=> :a

(#{:a :b} :a)
;=> :a

; Function literal syntax #()

((fn [x] (* (Math/random) x))   ; <1>
 (System/currentTimeMillis))   ; <2>

(#(* (Math/random) %)           ; <3>
  (System/currentTimeMillis))

