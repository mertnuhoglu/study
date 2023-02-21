(ns fn.or)

; rfr: video/20230221-mert-clj-egzersiz-46.mp4

; (or)    #nclk/çok-önemli
; (or x)
; (or x & next)
; Evaluates exprs one at a time, from left to right. If a form
; returns a logical true value, or returns that value and doesn't
; evaluate any of the other expressions, otherwise it returns the
; value of the last expression.
; (or) returns nil.
;

; rfr: fn/and.clj

; and ve or birbirine benzer mantıkla çalışır, ama birbirinin tamamlayıcısıdır

; and'de nasılki ilk false'dan sonraki formlar çalışmaz
; or'da da ilk true'dan sonraki formlar çalışmaz

(or
  (do (println "1") "ali")       ; ilk form true dönüyor
  (do (println "2") "ayşe"))     ; dolayısıyla 2. form çalışmasına gerek yok
;1
;=> "ali"

(or
  (do (println "1") false)
  (do (println "2") "ayşe"))
;1
;2
;=> "ayşe"

; İkinci bir trick imkanı:
; or ifadesini "if then else" gibi kullanabiliriz
; if'te test koşulu doğruysa then cümleciğini, yanlışsa else cümleciğini çalıştırıyorduk
; or'da test koşulu ile then cümleciğini aynı olarak düşün
; eğer test koşulu doğruysa, then cümleciğini çalıştırır; else cümleciğini çalıştırmaz
; eğer test koşulu yanlışsa, else cümleciğini çalıştırır
; not: eğer test koşulu yanlışsa, tabi ki, then cümleciği de çalışır, ama yan etkisi olmayan bir ifadeyse bizim için bir zararı yok.
;
