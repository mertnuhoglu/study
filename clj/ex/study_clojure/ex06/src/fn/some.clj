(ns fn.some)

; some: #nclk/çok-önemli
;[pred coll]
;Returns the first logical true value of (pred x) for any x in coll, else nil.
;One common idiom is to use a set as pred, for example this will return :fred if :fred is in the sequence, otherwise nil: (some #{:fred} coll)

; [some - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/some)

;; 2 is even, so `some` stops there, 3 and 4 are never tested
(some even? '(1 2 3 4))
;;=> true

; verdiğin collectionda herhangi bir öğe even? fonksiyonunu karşılıyor mu, yani çift mi?

;; they are all odd, so not true, i.e. nil
(some even? '(1 3 5 7))
;;=> nil

(some even? [2])
;=> true

#_(some even? [[1 2] [3 4]])
;Argument must be an integer: [1 2]
; neden hata verdi?
; çünkü even? fonksiyonuna numerik bir rakam değil, bir vektör verdik
(comment
  (even? [1 2])
  ; Argument must be an integer: [1 2]
  (even? 1)
  ;=> false
  (even? 2)
  ;=> true
  ,)

; some ve every birbirine benzer
; some: bir tane öğe bile even? ise true döner
; every: tüm öğeler even? ise true döner
(some even? '(1 2 3 4))
;;=> true
(every? even? '(1 2 3 4))
;=> false

; e02:

(some {2 "two" 3 "three"} [nil 3 2])
;;=> "three

(comment
  ;; a hash acts as a function returning nil when the
  ;; key is not present and the key value otherwise.
  ({:a 1} :a)
  ;=> 1
  (:a {:a 1})
  ;=> 1

  ; some'ın ikinci argümanı predicate fonksiyonu olması gerekiyor
  ; burada ise bir map verilmiş
  ; demek ki, bu map bir predicate fonksiyonu olarak çalışıyor

  ; q: predicate fonksiyon mantıksal (logical, boolean) değer dönen fonksiyonlardı.
  ; bu durumda map nasıl bir predicate fonksiyonu  oluyor? mantıksal değer dönmediği halde.
  ; clojure'da tüm değerler truthy'dir
  ; başka bir deyişle, herhangi bir değer dönüyorsa, bu mantıksal true değerine denktir
  ; nil ise false değerine denktir
  ; dolayısıyla herhangi bir değer dönen her fonksiyon, predicate olarak kullanılabilir

  ; {2 "two" 3 "three"} map bir predicate fonksiyon olarak kullanılıyor
  (def pred {2 "two" 3 "three"})
  ; şimdi [nil 3 2] vektörünün her bir öğesini pred'e gönderiyorum
  (pred nil)
  ;=> nil
  (pred 3)
  ;=> "three"
  (pred 2)
  ;=> "two"

  (some {2 "two" 3 "three"} [nil 3 2])
  ;=> "three"
  )