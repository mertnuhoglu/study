(ns ex.20250125_exmp_04)
; [[20250125_exmp_01-04.clj]]

;; keep vs filter vs map vs for #prg/clj
;;   id:: 2c2a19c0-d3e5-4de0-aa8f-3251e26b0e5e
;;     filter pred coll
;;     keep #(if pred ..) coll
;;     map #(if pred ..) coll
;;     for [.. coll :when pred] ..

(keep #(if (odd? %) %) (range 10))
;;=> (1 3 5 7 9)

(map #(if (odd? %) %) (range 10))
;;=> (nil 1 nil 3 nil 5 nil 7 nil 9)

(for [ x (range 10) :when (odd? x)] x)
;;=> (1 3 5 7 9)

(filter odd? (range 10))
;;=> (1 3 5 7 9)

(keep-indexed #(if (odd? %1) [%1 %2]) (range 10))  
; ([1 1] [3 3] [5 5] [7 7] [9 9])

(keep-indexed #(if (odd? %1) %2) [:a :b :c :d :[[20250125-exmp-]]]) 
; (:b :d)
