(ns fn.map-indexed)

; rfr: video/20230222-mert-clj-egzersiz-49.mp4

; rfr: fn/keep_indexed.clj

; (map-indexed f)
; (map-indexed f coll)
; Returns a lazy sequence consisting of the result of applying f to 0
; and the first item of coll, followed by applying f to 1 and the second
; item in coll, etc, until coll is exhausted. Thus function f should
; accept 2 arguments, index and item. Returns a stateful transducer when
; no collection is provided.
;

(map-indexed (fn [idx itm] [idx itm]) [10 20 30])
;=> ([0 10] [1 20] [2 30])

; iki argüman almış
; 1. arg: f
; 2. arg: coll
; coll'un tüm öğelerini indexleriyle beraber f fonksiyonuna paslamış
; f fonksiyonu ikililer (tuple) dönmüş
; map-indexed de bu ikilileri seq içine toplayıp dönmüş

(comment
  (fn [idx itm] [idx itm])
  ; bu ne yapıyor?
  ; ≣
  (fn [idx itm]
    '(idx itm))
  ; anonim fonksiyonların tanımı:
  ; (fn [arg-list] body)
  ; ilk [idx itm] formu => arg-list
  ; ikinci [idx itm] formu => return ettiğimiz body expression

  ; idx ve itm değerlerini biraz değiştirerek dönelim
  (map-indexed
    (fn [idx itm]
      [(+ 1 idx) (* 2 itm)])
    [10 20 30])
  ;=> ([1 20] [2 40] [3 60])
  ,)

; keep-indexed ve map-indexed birbirine oldukça benziyor
(keep-indexed #(if (odd? %1) %2) [:a :b :c])
;(:b :d)
(map-indexed (fn [idx itm] [idx itm]) [10 20 30])
;=> ([0 10] [1 20] [2 30])

; ikisi de iki arg alıyor:
; 1. arg: f
; 2. arg: coll
; ikisi de f fonksiyonuna coll'un indexlerini ve öğelerini paslıyor
; farkı ne?
; map-indexed ise f'in döndüğü tüm değerleri toplayıp dönüyor
; keep-indexed f'in döndüğü sonuçlardan non-nil olanlara denk gelen coll'un öğelerini dönüyor (filter gibi)
; keep-indexed'in f'i aslında filter'ın f'i gibi çalışıyor
; map-indexed map gibi çalışıyor
; ancak map'ten farkı ne?
; map de f ve coll diye iki arg alıyor
; map'in f'i tek arg (item) alıyor
; map-indexed'in f'i isi iki arg (index, item) alıyor

; #trm: item = öğe = element = bir kümenin üyeleri = elements of a collection
