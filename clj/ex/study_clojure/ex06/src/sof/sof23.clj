(ns sof.sof23)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230221
; rfr: video/20230222-mert-clj-egzersiz-48.mp4

; [Create a list from a string in Clojure - Stack Overflow](https://stackoverflow.com/questions/4836768/create-a-list-from-a-string-in-clojure)

; a01
(seq "aaa")
;(\a \a \a)

(map #(Character/getNumericValue %) "123")
;(1 2 3)

(comment
  (Character/getNumericValue "1")
  ;No matching method getNumericValue found taking 1 args

  (map (fn [a] (Character/getNumericValue a)) "123")
  ;(1 2 3)

  (map (fn [a] (identity a)) "123")
  ;=> (\1 \2 \3)
  ; \1 ile "1" farklı objeler java açısından
  ; \1 Character typeında bir obje
  ; "1"  String typeında bir obje
  (class \a)
  ;=> java.lang.Character
  (class 'a')
  ;=> clojure.lang.Symbol

  (Character/getNumericValue \1)
  ;=> 1
  ,)

; a02: you wanted a list of different characters
(frequencies "lazybrownfox")
;{\a 1, \b 1, \f 1, \l 1, \n 1, \o 2, \r 1, \w 1, \x 1, \y 1, \z 1}
(apply str (keys (frequencies "lazybrownfox")))
;"abflnorwxyz"

; a03:
(apply str (set "lazybrownfox"))
;"abflnorwxyz"
