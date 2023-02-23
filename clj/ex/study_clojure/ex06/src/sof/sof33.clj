(ns sof.sof33)

; rfr: video/20230223-mert-clj-egzersiz-51.mp4

; [group-by by multiple keys in Clojure - Stack Overflow](https://stackoverflow.com/questions/9089200/group-by-by-multiple-keys-in-clojure)

(def m1 [{:a 1 :b 2 :c 3}
         {:a 1 :b 2 :c 4}
         {:a 1 :b 4 :c 3}
         {:a 1 :b 4 :c 3}])

; a01: group-by select-keys
(group-by #(select-keys % [:a :b]) m1)
;{{:b 2, :a 1} [{:a 1, :c 3, :b 2} {:a 1, :c 4, :b 2}],
; {:b 4, :a 1} [{:a 1, :c 3, :b 4} {:a 1, :c 3, :b 4}]}

(comment
  (select-keys {:a 1 :b 2 :c 3} [:a :b])
  ;=> {:a 1, :b 2}
  (map #(select-keys % [:a :b]) m1)
  ;=> ({:a 1, :b 2} {:a 1, :b 2} {:a 1, :b 4} {:a 1, :b 4})
  (set (map #(select-keys % [:a :b]) m1))
  ;=> #{{:a 1, :b 2} {:a 1, :b 4}}
  ,)

; a02: vec apply concat group-by select-keys
(vec (apply concat (group-by #(select-keys % [:a :b]) m1)))
;=> [{:a 1, :b 2} [{:a 1, :b 2, :c 3} {:a 1, :b 2, :c 4}] {:a 1, :b 4} [{:a 1, :b 4, :c 3} {:a 1, :b 4, :c 3}]]

(comment
  ; apply concat'i biz içiçe bir veri yapısının bir seviye düzleştirmek için kullanırız
  (group-by #(select-keys % [:a :b]) m1)
  ;{{:b 2, :a 1} [{:a 1, :c 3, :b 2} {:a 1, :c 4, :b 2}],
  ; {:b 4, :a 1} [{:a 1, :c 3, :b 4} {:a 1, :c 3, :b 4}]}
  (apply concat (group-by #(select-keys % [:a :b]) m1))
  ;=> ({:a 1, :b 2} [{:a 1, :b 2, :c 3} {:a 1, :b 2, :c 4}] {:a 1, :b 4} [{:a 1, :b 4, :c 3} {:a 1, :b 4, :c 3}])
  ; en dıştaki map'i kaldırdı
  ; 4 öğeden bir liste oluştu
  ,)

; a02b: ->> pipeline
(->> (group-by #(select-keys % [:a :b]) m1)
  (apply concat)
  vec)
;=> [{:a 1, :b 2} [{:a 1, :b 2, :c 3} {:a 1, :b 2, :c 4}] {:a 1, :b 4} [{:a 1, :b 4, :c 3} {:a 1, :b 4, :c 3}]]

; a03:

(group-by (juxt :a :b) m1)
;=> {[1 2] [{:a 1, :b 2, :c 3} {:a 1, :b 2, :c 4}],
;    [1 4] [{:a 1, :b 4, :c 3} {:a 1, :b 4, :c 3}]}
; biraz fulcro formatına benzemiş oldu
