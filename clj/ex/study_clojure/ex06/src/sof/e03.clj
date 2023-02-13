(ns sof.e03)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230212
; rfr: video/20230212-mert-clj-egzersiz-32.mp4
; rfr: sndgrsm > 20230212-Barış'la-Clojure-Çalışması

; [Can someone explain Clojure Transducers to me in Simple terms? - Stack Overflow](https://stackoverflow.com/questions/26317325/can-someone-explain-clojure-transducers-to-me-in-simple-terms)

; Prior to version 1.7 of Clojure you had three ways to write dataflow (veri işleme) queries:

; a01: nested calls

(reduce + (filter odd? (map #(+ 2 %) (range 0 10)))) ; 35

; Bu ifadeyi deşifre etmek ilk etapta zor olabilir
; Bu durumda şöyle bir şey yaparak, kolayca deşifre edebilirsiniz
; rich comment bloku açın
(comment
; şimdi yukarıdaki ifadeleri işlem sırasıyla deneyelim
  (range 0 10)
  ; => (0 1 2 3 4 5 6 7 8 9)
  #(+ 2 %)
  ; bu anonim fonksiyon olduğundan tek başına çalışmaz
  (#(+ 2 %) 3)
  ; => 5
  (map #(+ 2 %) (range 0 10))
  ; => (2 3 4 5 6 7 8 9 10 11)
  (filter odd? (map #(+ 2 %) (range 0 10)))
  ; => (3 5 7 9 11)
  (reduce + (filter odd? (map #(+ 2 %) (range 0 10))))
  ;=> 35

  ; şimdi burada şöyle bir şey yapalım:
  ; filter ifadesinin yerine o filter ifadesinin çıktısını koyalım
  (reduce + '(3 5 7 9 11))
  ;=> 35
  ; bir nevi aslında clojureun gerçek çalışma şeklini böylece canlandırmış olur
  ; cebirsel bir özellik
  ; cebirin mantığı bir şeyin yerine başka bir şeyi koyarak bir bilinmeyeni bulmaktır
  ; bunu neden yapabiliyoruz burada?
  ; side-effect free fonksiyonlar olduğundan yapabiliyoruz
  ; bunun için de değişkenlerin immutable olması lazım
  ; buna programlamada biz referential transparency diyoruz
  ; 'Referential transparency' is a fancy way of saying that you can replace the function with its result
  ; Referential transparency: referanssal şeffaflık
  ; bu referans şeffaflığını sağlayabilirseniz, programlarınız inanılmaz derecede okunabilir ve kolay yönetilebilir olur
  ; Complexity (karmaşıklık)
  ; Yazılımdaki bir numaralı problem: karmaşıklığı yönebilmektir
  ; Karmaşıklığı yönetmenin temel yolu composition (parçaları birleştirebilmekten) geçer
  ; Parçaları birleştirebilmeniz için de referanssal şeffaflığınız olmalı kodunuzda.

  ; reduce fonksiyonunu anlatımı:
  ; rfr: fn/reduce.clj

  ; bu tip fonksiyonların mantığını öğrenmenin yolu iteratif (döngüsel) öğrenmektir
  ; yani biraz örnek yapın
  ; sonra ilgili fonksiyonunun dokümantasyonunu okuyun
  ; sonra biraz daha örnek yapın
  ; sonra dokümantasyonu tekrar okuyun
  ; bu şekilde

  ; genel olarak buradaki bazı fonksiyonlar çok karmaşık gelebilir
  ; yabancı dil öğreniminde şöyle bir usul tavsiye ediyorlar:
  ; yabancı dil öğrenirken karşınıza her yeni kelimeyi veya kuralı tam olarak öğrenmeye çalışmayın
  ; tam olarak öğrenmek çok zordur
  ; kaba hatlarıyla öğrenin
  ; kendiniz o kelimeyi veya kuralı kullanamayabilirsiniz, ama sezgisel olarak biraz anlayabiliyorsunuzdur
  ; bu yeterli.
  ; daha sonra yeni örnekler yaptıkça, öğrenmeniz ilerledikçe tekrar o kelime ve kurala geri dönersiniz
  ; o zaman daha iyi anlarsınız
  ; döngüsellikten kasıt bu.

  (reduce + (filter odd? (map #(+ 2 %) (range 0 10)))) ; 35
  ; 0'dan 10'a kadar olan sayıları al
  ; bunlara 2 ekle
  ; bunlardan tek sayı olanları ayıkla
  ; bunların toplamını al

  ,)

; bu yaptığımız işlemi başka şekillerde de yapabiliriz

; a02: functional composition

(def xform
  (comp
    (partial filter odd?)
    (partial map #(+ 2 %))))
(reduce + (xform (range 0 10))) ; 35

; a03: threading macro

; isimlendirme standardı: xs = list of x
(defn xform [xs]
  (->> xs
    (map #(+ 2 %))
    (filter odd?)))
(reduce + (xform (range 0 10))) ; 35

; With transducers you will write it like:

(def xform
  (comp
    (map #(+ 2 %))
    (filter odd?)))
(transduce xform + (range 0 10)) ; 35

; The order of combinators is like you write it with threading macro (natural order)

; Compared to composing calls to the old map, filter, reduce etc. you get better performance because you don't need to build intermediate collections between each step, and repeatedly walk those collections.

; buradaki comp, partial, transduce gibi fonksiyonlar oldukça karmaşık fonksiyonlar
; bunları şimdi öğrenmenize gerek yok
; bu tip kodları gördüğünüzde her şeyiyle öğrenme şartı koymayın kendinize
; yeterince anlayabiliyorsanız yeterli
; biraz varsayımlarda (tahminlerde) bulunun
; tahminlerinizin doğru olup olmaması çok önemli değil
; öğrenmeyi öğrenmek çok önemli bir beceri
; double loop learning
; tahmin etme becerilerinizi iyileştirmeniz lazım
; denemekten geçer
; imposter syndrome: yetersizlik sendromu
; 60 yaşına geldiğinizde de bunu hissedecekseniz
; ben şu kitabı okursam, üniversitesine gidersem, artık tam olurum
; tam tersine, ne kadar çok okuyup öğrenirseniz, yetersizlik sendromunuz o kadar artar
; çünkü bilmediğiniz ve anlamadığınız daha çok şeyle karşılaşırsınız

; map, filter, reduce, comp, partial
; bu gibi kavramlar FP'nin temel araçlarıdır
; FP öğrenmek bu tip kavramları öğrenmeyi gerektirir
; ancak şöyle bir umut vaat edebilirim
; bu kavramları bir kere öğrenirseniz, yarın bir gün javascript'te, java'da vs. bunları yine uygulayabilirsiniz
; orda da bunlara denk fonksiyonlar var
; fp daha olgun, üst bir yöntemdir
; bu yüzden imperatif dillerde programlama yapan fp'cılar, oradaki kavramları burada da uygulamaya sağlayacak kütüphaneleri geliştirirler

