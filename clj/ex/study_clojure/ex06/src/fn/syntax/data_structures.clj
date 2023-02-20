(ns fn.syntax.data-structures)

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