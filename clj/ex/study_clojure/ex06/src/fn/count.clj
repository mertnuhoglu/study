(ns fn.count)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; (count coll)    #nclk/çok-önemli
; Returns the number of items in the collection.
; (count nil) returns
; 0.  Also works on strings, arrays, and Java Collections and Maps

; Array.length()'in muadili

(count nil)
;;=> 0

(count [])
;;=> 0

(count [1 2 3])
;;=> 3

; Dikkat: map'lerde MapEntry sayısını verir
(count {:one 1 :two 2})
;;=> 2

(count [1 \a "string" [1 2] {:foo :bar}])
;;=> 5

; Not: stringlerde harf sayısını veriyor
(count "string")
;;=> 6

