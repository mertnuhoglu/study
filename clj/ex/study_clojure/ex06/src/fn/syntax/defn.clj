(ns fn.syntax.defn)

; rfr: video/20230216-mert-clj-egzersiz-40.mp4

(defn f [a b]
  (+ a b))
(f 2 3)
;=> 5

(macroexpand '(f 2 3))
;=> (f 2 3)

(macroexpand '(defn f [a b]
                (+ a b)))
;=> (def f (clojure.core/fn ([a b] (+ a b))))

(clojure.core/fn ([a b] (+ a b)))
; bu aslında bildiğimiz anonim fonksiyon yazımı
(fn ([a b] (+ a b)))
; biz clojure.core ns'ini belirtmeyiz, çünkü bütün her yerde clojure.core içindeki isimler kullanılabilir

; dolayısıyla bizim defn dediğimiz aslında def
; fonksiyonları da aslında anonim fonksiyon olarak tanımıyoruz
; aslında anonim fonksiyonlara isim veriyoruz

; q: defn niye kullanıyoruz o zaman?
; biraz daha özlü (daha az verbose) yazmamızı sağlar
; ve maksadımızı daha net ortaya koyar

(defn f [a b]
  (+ a b))
(def f
  (fn ([a b] (+ a b))))

; dolayısıyla defn aslında clojure sentaksının bir parçası değil
; def bir parçası
; clojure'un özelliği çok minimalist bir sentaks kural kümesine sahip