(ns sof.sof36)

; [clojure - Update the values of multiple keys - Stack Overflow](https://stackoverflow.com/questions/9638271/update-the-values-of-multiple-keys)

(def m [{:a 2 :b 3} {:a 2 :b 5}])
(map #(update-in % [:a] inc) m)
;({:a 3, :b 3} {:a 3, :b 5})

;Rather than mapping update-in for each key, I'd ideally like some function that operates like this:

;(map #(update-vals % [:a :b] inc) m)
;({:a 3, :b 4} {:a 3, :b 6})

; a01: reduce update-in
;Whenever you need to iteratively apply a fn to some data, reduce is your friend:

(defn update-vals [m ks f]
  (reduce #(update-in % [%2] f) m ks))

(def m1 {:a 2 :b 3})
(update-vals m1 [:a :b] inc)
;{:a 3, :b 4}
(def m [{:a 2 :b 3} {:a 2 :b 5}])
(map #(update-vals % [:a :b] inc) m)
;({:a 3, :b 4} {:a 3, :b 6})

; Not: [:a :b] -> ks -> [%2]. Neden arg [] ile paketlenmiş?
; Çünkü reduce ks, döngü sırasında önce ks'ın paketini açar.

; a02: reduce apply update-in
(defn update-vals2 [m ks & args]
  (reduce #(apply update-in % [%2] args) m ks))
(update-vals2 m1 [:a :b] inc)
