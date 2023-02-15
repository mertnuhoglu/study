(ns fn.reduce)

; rfr: video/20230215-mert-clj-egzersiz-36.mp4

; (reduce f coll) #nclk/çok-önemli
; (reduce f val coll)
; f should be a function of 2 arguments. If val is not supplied,
; returns the result of applying f to the first 2 items in coll, then
; applying f to that result and the 3rd item, etc. If coll contains no
; items, f must accept no arguments as well, and reduce returns the
; result of calling f with no arguments.  If coll has only 1 item, it
; is returned and f is not called.  If val is supplied, returns the
; result of applying f to val and the first item in coll, then
; applying f to that result and the 2nd item, etc. If coll contains no
; items, returns val and f is not called.


; (reduce f coll) kullanımı:
(reduce + '(1 2 3))
;=> 6

; şuna denktir:
(+ (+ 1 2) 3)
;=> 6
; bütün matematiksel operatörler birer fonksiyondur
; 3 + 5 diye yazarız
; +(3, 5) şeklinde de yazılabiliriz matematiksel notasyonda da

; (reduce f val coll) kullanımı:
(reduce + 5 '(1 2 3))
;=> 11
; şuna denktir:
(+ (+ (+ 5 1) 2) 3)
;=> 11

; q: neden 2 argümanlı reduce fonksiyonu var, 3 argümanlıya ek olarak?
; cevap: matematikte identity element (etkisiz eleman) bir varlık vardır
; bütün cebirsel gruplarda bir etkisiz eleman bulunur.
; Tam sayılar kümesi ve + fonksiyonu bir cebirsel gruptur.
; bu cebirsel grubun etkisiz elemanı: 0 yani sıfırdır.
(+ 0 5)
;=> 5
; FP, kökeninde lambda cebiri bulunur. Bu da cebirin alt dallarından birisidir.
; dolayısıyla 2 argümanlı reduce fonksiyonunda yapılan şey, varsayılan ilk öğe olarak, etkisiz elemanı kullanmaktır.
; ama unutmayalım: etkisiz elemana fonksiyona bağlıdır.
; her fonksiyonda etkisiz eleman farklı olur.
; + fonksiyonu etkisiz elemanı 0
; * fonksiyonu etkisiz elemanı 1

(reduce + '(1 2 3 4))
;=> 10
(+ (+ (+ 1 2) 3) 4)

(reduce * '(1 2 3))
;=> 6
(reduce * '(1 2 3 4))
;=> 24

; map'e benziyor biraz
(map + '(1 2 3))
;=> (1 2 3)
(map #(+ % 2) '(1 2 3))
;=> (3 4 5)

; peki reduce * işlemini verdiğimiz öğelere nasıl uyguluyordur acaba?
; tahmin edelim:
(* (* 1 2) 3)
;=> 6
(* (* (* 1 2) 3) 4)
;=> 24
; demek ki reduce bizim ona verdiğimiz listedeki öğeleri ikişer ikişer bir fonksiyona uyguluyor
; sonra onun çıktısını listedeki bir sonraki öğeyle birleştirip tekrar aynı fonksiyona uyguluyor

; [reduce - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/reduce)

(reduce + [1 2 3 4 5])  ;;=> 15
(reduce + [])           ;;=> 0
(reduce + [1])          ;;=> 1
(reduce + [1 2])        ;;=> 3
(reduce + 1 [])         ;;=> 1
(reduce + 1 [2 3])      ;;=> 6

; e03: reduce fonksiyonunun çalışma mantığını anlama: id=g13703

(reduce conj {} [[:a 1] [:b 2]])
;{:a 1, :b 2}

(comment
  (reduce conj {} [[:a 1] [:b 2]])
  ; bunun çalışması nasıldır?
  ; reduce, conj fonksiyonunu coll'daki öğelere sırayla uygular
  ; ilk çağrıyı yapar. bunun sonucunu ve bir sonraki coll öğesini conj fonksiyonuna paslar

  (conj {} [:a 1])
  ;=> {:a 1}
  ; reduce bu ara sonucu alır. conj fonksiyonuna bunu ve bir sonraki öğeyi uygular.
  (conj {:a 1} [:b 2])
  ;=> {:a 1, :b 2}
  ,)

; yukarıda ilk öğe olarak {} yerine [] kullansak, çıktımız [] olurdu
(reduce conj [] [[:a 1] [:b 2]])
;=> [[:a 1] [:b 2]]

; e04: "foo" kelimesini 3 defa tekrarlayarak birleştirelim

(reduce str (take 3 (cycle ["foo"])))
;=> "foofoofoo"

; bu aslında şuna denk:
(reduce str '("foo" "foo" "foo"))
;=> "foofoofoo"

; bu ise şuna denk:
(str (str "foo" "foo") "foo")
;=> "foofoofoo"

(reduce str (repeat 3 "foo"))
;=> "foofoofoo"

; burada java'nın String.concat fonksiyonunu kullanıyor
(reduce #(.concat %1 %2) (repeat 3 "foo"))
;=> "foofoofoo"

; şuna denk geliyor:
(.concat (.concat "foo" "foo") "foo")
;=> "foofoofoo"
