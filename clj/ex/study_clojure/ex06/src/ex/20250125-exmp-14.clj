(ns ex.20250125_exmp_14)
;; [[20250125-exmp-14.clj]]

(apply str ["a" "b"]) ; "ab"
(apply map vector [[:a :b] [1 2]])  ; ([:a 1] [:b 2])
(map vector [:a :b] [1 2])  ; ([:a 1] [:b 2])

