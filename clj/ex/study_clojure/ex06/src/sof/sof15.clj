(ns sof.sof15)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; [How can I remove an item from a sequence in Clojure? - Stack Overflow](https://stackoverflow.com/questions/939278/how-can-i-remove-an-item-from-a-sequence-in-clojure)

(disj #{:foo :bar} :foo)       ; => #{:bar}
(dissoc {:foo 1 :bar 2} :foo)  ; => {:bar 2}
(pop [:bar :foo])              ; => [:bar]
(pop (list :foo :bar))         ; => (:bar)

;These also work (returning a seq):

(remove #{:foo} #{:foo :bar})      ; => (:bar)
(remove #{:foo} [:foo :bar])       ; => (:bar)
(remove #{:foo} (list :foo :bar))  ; => (:bar)
(remove #{:foo} '(:foo :bar))  ; => (:bar)

; remove bir pred fonksiyonu bir de coll alır
; dolayısıyla #{:foo} bir pred fonksiyonu
; neden bu bir pred fonksiyonu işlevi görebiliyor?
(#{:foo} :foo)
;=> :foo
(#{:foo} :bar)
;=> nil
; çünkü set objesi kendisi de bir get fonksiyonu olarak işlev görür
; eğer nil/false dönerse -> falsy
; başka bir şey dönerse -> truthy

;This doesn't work for hash-maps because when you iterate over a map, you get key/value pairs. But this works:
; önce destructure etmemiz lazım k ve v parçalarına

(remove (fn [[k v]] (#{:foo} k)) {:foo 1 :bar 2})  ; => ([:bar 2])

; q: yukarıda (list :foo) ve '(:foo) arasında bir fark var mı?
; yok. ikisi de aynı şey.