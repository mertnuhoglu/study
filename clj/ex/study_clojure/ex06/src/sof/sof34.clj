(ns sof.sof34)

; [sequence - Idiomatic way to iterate through all pairs of a collection in Clojure - Stack Overflow](https://stackoverflow.com/questions/4053845/idiomatic-way-to-iterate-through-all-pairs-of-a-collection-in-clojure)

;Given a collection I want to iterate through all pairs in a collection. Example

;(all-pairs seq)
;
;(all-pairs '(a b c d)) => ([a b] [a c] [a d] [b c] [b d] [c d])
(def input '(a b c d))

;Here is my idea

; a01
(require '[clojure.math.combinatorics :as combo])
(vec (map vec (combo/combinations '(a b c d) 2)))
;=> [[a b] [a c] [a d] [b c] [b d] [c d]]

; a02: when lazy-cat for
(defn all-pairs [coll]
  (when-let [s (next coll)]
    (lazy-cat
      (for [e s] [(first coll) e])
      (all-pairs s))))
(all-pairs input)
;=> ([a b] [a c] [a d] [b c] [b d] [c d])

(comment
  (def coll input)
  (def s (next coll))
  (identity s)
  ;=> (b c d)
  (for [e s] [(first coll) e])
  ;=> ([a b] [a c] [a d])
  ; end
  ,)

; a02b:
(defn all-pairs2 [coll]
  (let [x (first coll)
        xs (next coll)]
    (when xs
      (lazy-cat
        (map (fn [e] [x e]) xs)
        (all-pairs2 xs)))))
(all-pairs2 input)
;=> ([a b] [a c] [a d] [b c] [b d] [c d])

; a03:
(defn all-pairs3 [coll]
  (loop [[x & xs] coll
         result []]
    (if (nil? xs)
      result
      (recur xs (concat result (map #(vector x %) xs))))))

(comment
  ; [x & xs] coll
  ; burada destructuring yapılmış
  ; coll'un ilk öğesi -> x
  ; coll'un rest öğeleri -> xs
  (def x (first coll))
  (def xs (rest coll))
  (identity xs)
  ;=> (b c d)

  ; kombinasyon burada yapılıyor:
  (map #(vector x %) xs)
  ;=> ([a b] [a c] [a d])

  ; not:
  ; burada birikimli argüman: (concat ...)
  ; her turda büyüyen bir veri
  ; bitme koşulunun argümanı: xs
  ; her turda küçülen bir veri
  ,)

; a04

(defn all-pairs4 [s]
  (filter
    #(< (first %) (second %))
    (for [i s j s] [i j])))
(all-pairs4 [10 20 30])
;=> ([10 20] [10 30] [20 30])

#_(all-pairs4 input)
class clojure.lang.Symbol cannot be cast to class java.lang.Number (clojure.lang.Symbol is in unnamed module of loader 'app'); java.lang.Number is in module java.base of loader 'bootstrap')

