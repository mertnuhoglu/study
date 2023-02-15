(ns sof.sof08)

; [Convert an array of tuples into a hash-map in Clojure - Stack Overflow](https://stackoverflow.com/questions/4328709/convert-an-array-of-tuples-into-a-hash-map-in-clojure)

; a01: into {}
(into {} [[:a 1] [:b 2]])
;{:a 1, :b 2}

; list için:
(into {} (map vec '((:a 1) (:b 1))))
;{:a 1, :b 2}

; listelerde önce vektöre çevirmeden çalışmıyor
#_(into {} '((:a 1) (:b 1)))
;class clojure.lang.Keyword cannot be cast to class java.util.Map$Entry (clojure.lang.Keyword is in unnamed module of loader 'app'); java.util.Map$Entry is in module java.base of loader 'bootstrap')

; a02: reduce

(reduce conj {} [[:a 1] [:b 2]])
;{:a 1, :b 2}

(comment

  (reduce conj {} [[:a 1] [:b 2]])
  ; reduce ile bunun çalışması nasıl oluyor? #nclk/çok-önemli
  ; rfr: e03: reduce fonksiyonunun çalışma mantığını anlama: <url:file:///~/prj/study/clj/ex/study_clojure/ex06/src/fn/reduce.clj#r=g13703>

  ; reduce, conj fonksiyonunu coll'daki öğelere sırayla uygular
  ; 1. tur: ilk çağrıyı {} ve [:a 1] öğelerine yapar.
  ; 2. tur: ilk turun sonucunu ve bir sonraki coll öğesini `[:b 2]` conj fonksiyonuna paslar

  (conj {} [:a 1])
  ;=> {:a 1}
  ; reduce bu ara sonucu alır. conj fonksiyonuna bunu ve bir sonraki öğeyi uygular.
  (conj {:a 1} [:b 2])
  ;=> {:a 1, :b 2}
  ,)

; a03: flatten. (kötü çözüm)

(def a [[:a 4] [:b 6]])
(apply hash-map (flatten a))
;{:a 4, :b 6}

; bu çözüm burada çalıştı, ancak flatten özyinelemeli bir şekilde her şeyi düzler
; o yüzden beklenmedik sonuçlar alabilirsin.