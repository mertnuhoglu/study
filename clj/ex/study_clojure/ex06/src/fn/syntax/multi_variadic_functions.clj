(ns fn.syntax.multi-variadic-functions)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; [Learn clojure in Y Minutes](https://learnxinyminutes.com/docs/clojure/)

; normal bir fonksiyonda genellikle belirli bir sayıda argüman veririz
(defn hello1 [name]
  (str "Hello " name))
(hello1 "ali")
;=> "Hello ali"
; bu fonksiyonda tek argüman alıyoruz

; bir fonksiyonu hem tek argümanlı, hem de sıfır argümanlı çalışabilir.
; bu durumda bu fonksiyona multi-variadic diyoruz
; yani argüman sayısı farklı olabilir anlamında

; You can have multi-variadic functions, too
(defn hello3
  ([] "Hello World")
  ([name] (str "Hello " name)))

(hello3 "Jake")
; => "Hello Jake"
(hello3)
; => "Hello World"

(defn hello4 [& a]
  (print a))
(hello4 "ali" "ayşe")
;(ali ayşe)=> nil
; demek ki "ali" ve "ayşe" argümanlarını bir liste içine almış

(defn hello5 [& a]
  (apply str a))
(hello5 "ali" "ayşe")
;=> "aliayşe"
(hello5 "ali" "ayşe" "nazım")
;=> "aliayşenazım"

(defn hello6 [a b]
  (str a b))
(hello6 "ali" "ayşe")

; değişken sayıda argüman alabilen (variadic) fonksiyonlara argümanlarını seq olarak gönderirsen,
; bu durumda o seqi apply ile uygulaman gerekiyor fonksiyona
(def es '("ali" "ayşe" "nazım"))
(hello5 es)
;=> "(\"ali\" \"ayşe\" \"nazım\")"
; yanlış çalıştı

(apply hello5 es)
;=> "aliayşenazım"

