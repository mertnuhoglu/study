(ns sof.sof31)

; rfr: video/20230223-mert-clj-egzersiz-51.mp4

; [How can I update an item in a vector in Clojure? - Stack Overflow](https://stackoverflow.com/questions/6168415/how-can-i-update-an-item-in-a-vector-in-clojure)

;If I have a Vector: [1 2 3 4 5 6 7 8 9]

; and I want to replace the 5 with a 0 to give:
;[1 2 3 4 0 6 7 8 9]

(assoc [1 2 3 4 5 6 7 8 9] 4 0)
;=> [1 2 3 4 0 6 7 8 9]

(assoc [1 2 3] 1 :a)
;=> [1 :a 3]
