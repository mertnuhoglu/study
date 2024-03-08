(ns syntax.def)

; rfr: video/20230216-mert-clj-egzersiz-40.mp4

; def normalde bizim imperatif programlardaki assingment statementına denktir
; int x = 10
(def x 10)
(identity x)
;=> 10

; fakat FP dillerinde fonksiyonlar birinci sınıf vatandaştır demiştik
; dolayısıyla fonksiyonları da bir obje gibi kullanabiliriz
; dolayısıyla 10 objesini kullandığımız yerde, f fonksiyonunu da kullanabilmeliyiz
(def f +)
; + fonksiyonuna f ismini bağlamış olurum
(f 5 2)
;=> 7
