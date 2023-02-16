(ns sof.sof20)

; [arrays - How to implement a For loop in Clojure - Stack Overflow](https://stackoverflow.com/questions/9981943/how-to-implement-a-for-loop-in-clojure)

; a01: If you really want an imperative-style for loop in Clojure, you can create one with this macro:

(defmacro for-loop [[sym init check change :as params] & steps]
  `(loop [~sym ~init value# nil]
     (if ~check
       (let [new-value# (do ~@steps)]
         (recur ~change new-value#))
       value#)))

(for-loop [i 0 (< i 4) (inc i)]
  (println i))
;0
;1
;2
;3
;=> nil

; I love how in Clojure if your missing a language feature,
; instead of waiting for it to be added to a future version, you can just write your own. –

; a02: doseq

(doseq [i (for [i (range 4)] (inc i))]
  (println "i=" i))i= 1
;i= 2
;i= 3
;i= 4
;=> nil

;To loop through a seq, you can simply use:

(doseq [value '(1 2 3)]
  (println "Your expression here" value))
;Your expression here 1
;Your expression here 2
;Your expression here 3
;=> nil
