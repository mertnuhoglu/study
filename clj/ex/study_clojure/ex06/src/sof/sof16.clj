(ns sof.sof16)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230221
; rfr: video/20230221-mert-clj-egzersiz-46.mp4

; ["reduce" or "apply" using logical functions in Clojure - Stack Overflow](https://stackoverflow.com/questions/2891707/reduce-or-apply-using-logical-functions-in-clojure)

;Neither of the following works due to logical functions being macros:

#_(reduce and [... sequence of bools ...])
#_(apply or [... sequence of bools ...])

; a01: use every? and some instead.

; reduce veya apply yerine every? veya some kullanacağız
(every? even? [1 2 3])
;=> false

; verdiğim argümanlardan hepsi truthy mi onu test etmek istiyorum:
(and 1 2 3)
;=> 3
; every? ile bunu nasıl yaparız?
(every? identity [1 2 3])
;=> true
(every? identity [1 2 nil 4])
;=> false

(comment
  (every? true? [1 2 3])
  ;=> false
  (true? 1)
  ;=> false
  (true? true)
  ;=> true

  (every? false? [1 2 3])
  ;=> false
  (false? 1)
  ;=> false

  (every? integer? [1 2 3])
  ;=> true
  (every? integer? [1 2 "a"])
  ;=> false

  (every? nil? [1 2 "a"])
  ;=> false
  (empty? 1)
  ;Don't know how to create ISeq from: java.lang.Long
  (every? empty? [1 2 "a"])
  ;Don't know how to create ISeq from: java.lang.Long
  ,)

; every? and'in karşılığı
; some da or'un karşılığı

(some identity [1 2 3 nil])
;=> 1
(some identity [false nil false])
;=> nil

; a02: whenever you want to use a macro as a function:
; Basically you just wrap the macro in an anonymous function.

;(reduce #(and %1 %2) [... sequence of bools ...])

(reduce #(and %1 %2) 0 [1 2 3])
;=> 3
(reduce #(or %1 %2) 0 [1 2 3])
;=> 0
(reduce #(and %1 %2) 0 [1 false 3])
;=> false

(comment
  ; makroyu bir anonim fonksiyona wrap ederken (sararken) dikkat edilmesi gereken bir şey var mı?
  ; yok
  (reduce #(and %1 %2) 0 [1 2 3])
  (reduce (fn [a b] and a b) 0 [1 2 3])
  ;Can't take value of a macro: #'clojure.core/and
  (reduce (fn [a b] (and a b)) 0 [1 2 3])
  ;=> 3

  ; #trm: canonical = kanuni. bir şeyi yapmanın normal veya herhangi bir soyutlama kullanılmadan yapma şekli
  ; literal anonim fonksiyonlar fn formuna çevrilir clojure tarafından
  ,)

(and 1 2 3)
;=> 3
(or 1 2 3)
;=> 1

; (reduce f init coll)
; reduce fonksiyonuna and fonksiyonunu argüman olarak veremiyoruz:
#_(reduce and [1 "ali" "ayşe" false nil])
;Can't take value of a macro: #'clojure.core/and
; Neden bunu yapamıyoruz?
; makro olduğundan (soru işareti kalsın)

; benzer şekilde apply fonksiyonuna da argüman olarak gönderemiyoruz
#_(apply and [1 2 3])
;Can't take value of a macro: #'clojure.core/and
