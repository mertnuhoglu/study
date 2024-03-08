(ns syntax.data-structures)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; 4 tane temel veri yapımız var:
; 1. list (seq)   ()
; 2. vektör       []
; 3. map          {}
; 4. set         #{}

'(:a :b :a)
;=> (:a :b :a)

[:a :b :a]
;=> [:a :b :a]

{:a 1 :b 2}
;=> {:a 1, :b 2}

#_#{:a :b :a}
;Duplicate key: :a
; set'lerde tekrarlanan öğe olamaz

; set, liste ve vektörün muadili
; map'in değil
; çünkü map'lerde kv ikilileri var

; genel olarak set ve map'lerde sıralama önemli değildir
; liste ve vektördeyse sıralama özüne aittir

; rfr: video/20230220-mert-clj-egzersiz-45.mp4

; q: bunlardan hangileri sequential?
; sequential: sıralı, ardışık
; liste ve vektör sequential
; diğerleri değil.

; q: hangileri associative (ilişkili)?
; map ve vektör

; bunların hepsi birer collection veya data structure.

; q: set'in girdiği bir grup var mı?
; mutlaka bir kategoriye girer. ama bu ikisine girmez.

; tarih: 20230314

'(10 20 30)
;'(10 20 30)
; en başa neden tırnak işareti koyuyoruz?
; fonksiyon gibi yorumlanmasın diye
; aslında fonksiyonlar da birer list'tir clojure içinde.
; clojure reader, bir list formunu okuyunca, bunu hemen eval eder fonksiyon gibi. ilk öğesini fonksiyon ismi olarak kabul eder.
; ama eğer başında tırnak işareti varsa, eval etmez. onu literal list olarak kabul eder.
; bu yüzden, clojure'da "code is data"
; C tipi dillerdeki LinkedList'e denk gelir
; burada indeks yok
; 2. öğe nedir diye soramıyorsun?

[10 20 30]
;=> [10 20 30]
; vektör olunca, list'e göre tek fark şu:
; indeksle istediğin herhangi bir öğeye anında erişebilirsin.
; indeks olduğundan, doğrudan doğruya istediğin sıradaki öğeyi hemen çekebilirsin
(get [10 20 30] 0)
;=> 10
(get [10 20 30] 1)
;=> 20
; list'in yaptığı her şeyi (tüm fonksiyonları) vektör de destekler
; js'deki array'ler aslında vektördür.

#{10 20 30}
;=> #{20 30 10}
; set öncekilerden farklı olarak ek bir kısıtlama getirir
; herhangi bir öğenin tekrarlanmasına izin vermez
[10 20 30 10]
;=> [10 20 30 10]
#_#{10 20 30 10}
;Duplicate key: 10

;var myObj = {a: 200, b: 300};
; js'deki bu array'in karşılığı:
{:a 200, :b 300}
;=> {:a 200, :b 300}
; map'lere: key-value pair kümesi diyoruz
; anahtar-değer ikililerinden oluşan bir küme
(get {:a 200, :b 300} :a)
;=> 200

; get fonksiyonu en çok kullanılan işlem olduğundan bunu daha da basit ve kolay hale getirmiş clojure:
(get {:a 200, :b 300} :a)
; =
({:a 200 :b 300} :a)
;=> 200
; map'in kendisi aynı zamanda bir get fonksiyonu işlevi de görüyor
(:a {:a 200 :b 300})
;=> 200
; keyword de aynı şekilde bir get fonksiyonu işlevi görür

; bunun bir benzeri durum da set için geçerli
(#{10 20 30} 10)
;=> 10
(get #{10 20 30} 10)
;=> 10
(get #{10 20 30} 15)
;=> nil
(#{10 20 30} 15)
;=> nil

