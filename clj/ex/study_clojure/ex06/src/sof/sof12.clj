(ns sof.sof12)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; [Convert an array of tuples into a hash-map in Clojure - Stack Overflow](https://stackoverflow.com/questions/4328709/convert-an-array-of-tuples-into-a-hash-map-in-clojure)

; #nclk/çok-önemli: "array of tuples" -> map
; #nclk/çok-önemli: genel olarak bir veri yapısının başka bir veri yapısına dönüştürülmesine örneklere hakim olmalıyız

; a01: into {}
(into {} [[:a 1] [:b 2]])
;{:a 1, :b 2}

; into'ya verdiğimiz argüman vektör de olabilir liste de
; list için:
(into {} (map vec '((:a 1) (:b 1))))
;{:a 1, :b 2}

; listelerde önce vektöre çevirmeden çalışmıyor
#_(into {} '((:a 1) (:b 1)))
;class clojure.lang.Keyword cannot be cast to class java.util.Map$Entry (clojure.lang.Keyword is in unnamed module of loader 'app'); java.util.Map$Entry is in module java.base of loader 'bootstrap')

(comment
  (hash-map :a 1 :b 2)
  ;=> {:b 2, :a 1}
  ({:a 1} :a)
  ;=> 1
  ({:a 1} nil)
  ;=> nil
  ({} :a)
  ;=> nil
  ; Fakat bu şekilde map objelerini fonksiyon olarak kullandık mı, hash-map gibi constructor işlevi görmüyor
  ; get fonksiyonu görevi görüyor
  ,)

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
(comment
  (flatten a)
  ;=> (:a 4 :b 6)

  ; Örnek: nested tuplelarda sorun çıkacaktır:
  (def v2 [[:a [:ab 1]] [:b 2]])
  (reduce conj {} v2)
  ;=> {:a [:ab 1], :b 2}
  (apply hash-map (flatten v2))
  ;No value supplied for key: 2
  (flatten v2)
  ;=> (:a :ab 1 :b 2)
  ; burada tek sayılı bir seq döndüğünden hata verdi
  ; ama çift sayılı olsaydı da bu sefer yanlış kv ikililerini oluşturacaktı
  ,)