(ns sof.sof37)

; [clojure - Sliding window over seq - Stack Overflow](https://stackoverflow.com/questions/1427894/sliding-window-over-seq)

(def v [3 1 4 1 5 9])

; a01: partition 1
(partition 3 1 v)
;((3 1 4) (1 4 1) (4 1 5) (1 5 9))

; a02: map apply
; window'larda işlem yapmak için:

(map (partial apply +) (partition 3 1 v))
;(8 6 10 15)

; a02b: map next nnext
(map + v (next v) (nnext v))
;(8 6 10 15)

; a03: from scratch
(defn sliding-window [s length]
  (loop [result ()
         remaining s]
    (let [chunk (take length remaining)]
      (if (< (count chunk) length)
        (reverse result)
        (recur (cons chunk result) (rest remaining))))))
