(ns sof.sof37)

; rfr: video/20230223-mert-clj-egzersiz-52.mp4

; [clojure - Sliding window over seq - Stack Overflow](https://stackoverflow.com/questions/1427894/sliding-window-over-seq)

(def v [3 1 4 1 5 9])

; a01: partition 1
(partition 3 1 v)
;((3 1 4) (1 4 1) (4 1 5) (1 5 9))
(partition 3 2 v)
;=> ((3 1 4) (4 1 5))

; a02: map apply
; window'larda işlem yapmak için:
; (3 1 4) gibi alt parçalara window diyoruz

(map (partial apply +) (partition 3 1 v))
;(8 6 10 15)

(comment
  (partition 3 1 v)
  ;((3 1 4) (1 4 1) (4 1 5) (1 5 9))

  ; 1. tur
  (apply + '(3 1 4))
  ;=> 8

  ; 2. tur
  (apply + '(1 4 1))
  ;=> 6

  (apply + (map (partial apply +) (partition 3 1 v)))
  ;=> 39
  ,)

; a02b: map next nnext
(map + v (next v) (nnext v))
;(8 6 10 15)

(comment
  (map +
    [3 1 4 1 5 9]
    [1 4 1 5 9]
    [4 1 5 9])
  ;=> (8 6 10 15)
  ,)

; a03: from scratch (en baştan, sıfırdan)
(defn sliding-window [s length]
  (loop [result ()
         remaining s]
    (let [chunk (take length remaining)]
      (if (< (count chunk) length)
        (reverse result)
        (recur (cons chunk result) (rest remaining))))))

; FP dillerinde for loop da yoktur, while loop da yoktur
; her ikisi de recursionla halledilir