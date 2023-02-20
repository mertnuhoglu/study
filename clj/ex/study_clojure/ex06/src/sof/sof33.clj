(ns sof.sof33)

; [group-by by multiple keys in Clojure - Stack Overflow](https://stackoverflow.com/questions/9089200/group-by-by-multiple-keys-in-clojure)

(def m1 [{:a 1 :b 2 :c 3}
         {:a 1 :b 2 :c 4}
         {:a 1 :b 4 :c 3}
         {:a 1 :b 4 :c 3}])

; a01: group-by select-keys
(group-by #(select-keys % [:a :b]) m1)
;{{:b 2, :a 1} [{:a 1, :c 3, :b 2} {:a 1, :c 4, :b 2}],
; {:b 4, :a 1} [{:a 1, :c 3, :b 4} {:a 1, :c 3, :b 4}]}

; a02: vec apply concat group-by select-keys
(vec (apply concat (group-by #(select-keys % [:a :b]) m1)))
;=> [{:a 1, :b 2} [{:a 1, :b 2, :c 3} {:a 1, :b 2, :c 4}] {:a 1, :b 4} [{:a 1, :b 4, :c 3} {:a 1, :b 4, :c 3}]]

; a02b: ->> pipeline
(->> (group-by #(select-keys % [:a :b]) m1)
  (apply concat)
  vec)
;=> [{:a 1, :b 2} [{:a 1, :b 2, :c 3} {:a 1, :b 2, :c 4}] {:a 1, :b 4} [{:a 1, :b 4, :c 3} {:a 1, :b 4, :c 3}]]

; a03:

(group-by (juxt :a :b) coll)