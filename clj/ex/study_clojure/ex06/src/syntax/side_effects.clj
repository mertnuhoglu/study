(ns syntax.side-effects)

; rfr: video/20230221-mert-clj-egzersiz-46.mp4

; #tpc: Side Effects #nclk/çok-önemli

; rfr: fn/print.clj
; print fonksiyonu nil döner, herhangi bir değer dönmez
; fakat ekrana yazıyı basar
; ekrana yazı basma dediğimiz şey, bir side-effecttir
; fakat "ali" stringi "ali" stringini döner
; ekrana yazı basmaz. bu side-effect değildir.
(print "ali")
;ali=> nil
; not: bir formun döndüğü değer repl tarafından "=>" sembolünden sonra gösterilir
"ali"
;=> "ali"
(identity "ali")
;=> "ali"

; print'in nil döndüğünü başka bir deyişle "ali" stringini dönmediğini şurdan da anlayabiliriz:
; bir fonksiyona argüman olarak print'i gönderelim, hiçbir şey gelmeyecektir

(defn f [c]
  (str "merhaba " c))
(f "ali")
;=> "merhaba ali"

; bu fonksiyona "ali" stringi yerine (print "ali") gönderirsem ne olur?
(f (print "ali"))
;ali=> "merhaba "
; neden merhabadan sonra hiçbir şey yok?
; çünkü print nil döndüğü için aslında şunu yapmış olduk:
(f nil)
;=> "merhaba "

(defn g [c]
  (if (= c nil)
    "arg: nil"
    c))
(g "ali")
;=> "ali"
(g (print "ali"))
;ali=> "arg: nil"

; #trm: side-effect = yan etki
; Side-effect is any external effect a function has besides its return value
; [What are side-effects?](https://ericnormand.me/podcast/what-are-side-effects)
; A side effect is something that creates a change outside of the current code scope, or something external that affects the behaviour or result of executing the code in the current scope.
; https://practical.li

; Side-effect is any external effect a function has besides its return value
; Yani bir fonksiyonun döndüğü değer dışındaki yaptığı veya etkilediği herhangi bir şey varsa, buna yan etki diyoruz.
; Neden?
; Bir fonksiyonun tek bir görevi vardır.
; Aldığı argümanlara karşılık gelen değeri döndürmektir.
; Bir fonksiyonun özüne inersek, düz bir map gibi düşünebilirsin.
; {:a 1, :b 2}
; Bir map aslında bir fonksiyondur.
; Zaten cebirde, fonksiyondan önce mapping yani bağıntılar tanımlanır.
; Bağıntı = mapping ~> map
; Akraba kavramlar
; Birileri bize bu kavramları öğretiyor ve sanki hepsi birbirinden farklıymış gibi geliyor
; Ama bu kavramların çıkış kökenine gidersek, aslında birbirinden türetildiğini görürüz.
; Bir map objesi aslında ne yapar?
; Map'in içindeki her bir anahtarı (key) bir değere bağlar.
; Yani bir anahtar verirsen o map'e sana bir değer döner.
; Ve her zaman bir anahtar için aynı değeri döner.
; Bu zaten fonksiyonun tanımıdır
; Bir fonksiyon verilen bir argüman için her zaman aynı değeri dönmek demektir.
; Bir fonksiyonun bunun dışında hiçbir görevi (özelliği) yoktur.
; Dolayısıyla bir fonksiyon bir değer dönmenin dışında herhangi bir şey yaparsa, buna yan etki deriz.
; Neden? Çünkü normal etkisinin dışında bir etkide bulunmuştur.

; Peki print fonksiyonunun çıktı üretmesine yan etki diyoruz?
; Benim programım açısından, programımın dışındaki tüm uzay/dünya, dışsaldır.
; print fonksiyonu, benim ekranımda bir çıktı üretiyor.
; Fakat ekran (monitör), çalışan program için dışsal bir varlıktır.
; Dolayısıyla programın dışında meydana gelen her şey birer yan etkidir.

; FP'da şöyle bir prensip takip edilir:
; Bir fonksiyon eğer bir yan etkiye sahipse, onun nil dönmesi beklenir.
; Herhangi bir değer dönmesi beklenmez.
; Çünkü FP'da varsayılan durum, yan etkinin olmamasıdır.
; İmperatiflerde tam tersi. Her fonksiyon neredeyse yan etki içerir.

; Bir fonksiyon eğer kendi lokal argümanları dışında herhangi bir objeye erişirse, buna da yan etki deriz.
; O fonksiyon açısından global scope'daki tüm objeler haricidir. Fonksiyonun asli görevi değildir.
;
; Yazdığımız programlar için, dosya sistemi de haricidir, dolayısıyla veri tabanı sistemleri de haricidir.
; Dolayısıyla veri tabanında yaptığımız her türlü kayıt işlemi de yan etki sayılır.

; q: global scope nedir?
; def ile tanımladığın her şey global scope'tur.
(def x "ali")
(defn h [c]
  (str c x))  ; burada global scope'daki x objesine erişiyorum (yan etki)
(h "ayşe ")
;=> "ayşe ali"
; buradaki "ali" stringi global scopedan geliyor. dolayısıyla bu fonksiyon yan etki içerir.
; çünkü kendi dışındaki bir varlığa erişim yapıyor.

(def y "nazım")
(h y)
;=> "nazımali"
; burada y objesine de erişiyor, ama y objesi argüman olarak verildiğinden, burada y'ye erişmek yan etki sayılmaz.

; #trm: fonksiyon tanımı = definition = kaynak kodu
; h fonksiyonunun tanımına (definition) baktığımızda, fonksiyonun içinde tanımlanmamış bir obje varsa, buna yan etki deriz
; x içinde olmayan bir şey

; Yan etki konusu çok önemli
; Çünkü programlarımızı yazarken, hep yan etkisiz bir şekilde yazmamız lazım.
; Ancak sıfır yan etki diye bir şey söz konusu değildir.
; En azından IO içeren programlarda, imkansızdır çünkü illa ki harici bir varlıkla bir veri alışverişi olması gerekir
; #trm: IO = input output = harici sistemlerle veya öznelerle bilgi alışverişi yapmaya
; harici sistem: veritabanı, başka yazılımlar
; özne: insan, kurum vs.

; Yan etkileri minimize etmeliyiz
; Yan etkileri toplu ve kapsülleyerek yapmalıyız.

; Şu konferansları izlemenizi tavsiye ederim, bu yan etkinin nasıl sınırlandırılması gerektiğine değiniyor:
; [Keynote: Transparency through data by James Reeves - YouTube](https://www.youtube.com/watch?v=zznwKCifC1A)
; [Solving Problems the Clojure Way - Rafal Dittwald - YouTube](https://www.youtube.com/watch?v=vK1DazRK_a0)
