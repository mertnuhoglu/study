(ns e14)

; tarih: 20230314

(defn f [a]
  (def b 2)
  (+ a b))

(f 3)

(defn isPrime
  [a]
  (def largestDivisor (atom 1))
  (loop [x 1]
    (when (< x a)
      (if (= 0 (mod a x))
        (reset! largestDivisor x)
        ())
      (recur (inc x))))
  (= @largestDivisor 1))

(isPrime 5)
;=> true
(isPrime 8)
;=> false

(println "hello")

(comment
  (+ 2 3)
  (println "merhaba")

  (def largestDivisor (atom 1))
  (def x 1)
  (def a 5)
  (< x a)
  ;=> true
  (= 0 (mod a x))
  ;=> true
  (reset! largestDivisor x)
  (deref largestDivisor)
  ;=> 1
  @largestDivisor
  ;=> 1

  ,)

;