(ns sof.sof27)

; [Idiomatic sequence slice in Clojure - Stack Overflow](https://stackoverflow.com/questions/12069855/idiomatic-sequence-slice-in-clojure)

;In Python, there is a convenient way of taking parts of a list called "slicing":

;a = [1,2,3,4,5,6,7,8,9,10] # ≡ a = range(1,10)
;a[:3] # get first 3 elements
;a[3:] # get all elements except the first 3
;a[:-3] # get all elements except the last 3
;a[-3:] # get last 3 elements
;a[3:7] # get 4 elements starting from 3rd (≡ from 3rd to 7th exclusive)
;a[3:-3] # get all elements except the first 3 and the last 3

;Playing with clojure.repl/doc in Clojure, I found equivalents for all of them but I'm not sure they are idiomatic.

(def a (take 10 (iterate inc 1)))
(take 3 a)
(drop 3 a)
(take (- (count a) 3) a)
(drop (- (count a) 3) a)
(drop 3 (take 7 a))
(drop 3 (take (- (count a) 3) a))

; a01: count yerine `take-last` veya `drop-last` kullan

(def a (take 10 (iterate inc 1)))
(take 3 a) ; get first 3 elements
(drop 3 a) ; get all elements except the first 3
(drop-last 3 a) ; get all elements except the last 3
(take-last 3 a) ; get last 3 elements
(drop 3 (take 7 a)) ; get 4 elements starting from 3
(drop 3 (drop-last 3 a)) ; get all elements except the first and the last 3

; içiçe işlemler için thread macro:
(->> a (take 7) (drop 3)) ; get 4 elements starting from 3
(->> a (drop-last 3) (drop 3)) ; get all elements except the first and the last 3

; a02: vektörler için subvec kullan

(def a (vec (range 1 (inc 10))))

(subvec a 0 3)
; [1 2 3]

(subvec a 3)
; [4 5 6 7 8 9 10]

(subvec a 0 (- (count a) 3))
; [1 2 3 4 5 6 7]

(subvec a (- (count a) 3))
; [8 9 10]

(subvec a 3 (+ 3 4))
; [4 5 6 7]

(subvec a 3 (- (count a) 3))
; [4 5 6 7]

; a03: code smell

; Slicing a sequence is a bit of a "code smell" - a sequence in general is designed for sequential access of items.
;
; If you are going to do a lot of slicing / concatenation,
; there are much better data structures available,
; in particular checkout the RRB-Tree vector implementation: