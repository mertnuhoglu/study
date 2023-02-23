(ns sof.sof38)

; rfr: video/20230223-mert-clj-egzersiz-52.mp4

; [Into or vec: converting sequence back to vector in Clojure - Stack Overflow](https://stackoverflow.com/questions/12044181/into-or-vec-converting-sequence-back-to-vector-in-clojure)

(def f inc)
(def args [10 20 30])

; a01: mapv
(mapv f args)
;=> [11 21 31]

; a02: into vec map
(into (vector) (map f args))
;=> [11 21 31]

(into [] (map f args))
;=> [11 21 31]

; Öğrenmeyi öğrenmekle alakalı bir tavsiye daha:
; Daniel Kahneman: Slow Thinking, Fast Thinking
; İnsanın zihni temelde iki farklı düşünme şeklinden birini kullanır:
; 1. Ya sezgisel olarak düşünür
; 2. Ya da analitik, hesap yaparak düşünür
; Sezgisel yöntem: hızlıdır, pratiktir
; Analitik yöntem: yavaştır, sağlamdır
; Yeni konuları öğrenirken, ilk başlarda yavaş yöntemden gitmek lazım
; Bazı şeyleri üzerine dura dura, çok hızlı adım atmadan gitmek
; Bunu defalarca yapa yapa, bir süre kas hafızası dedikleri sezgisel düşünme meydana geliyor.

; TODO: BURDA KALDIK: [Highest scored 'clojure' questions - Page 15 - Stack Overflow](https://stackoverflow.com/questions/tagged/clojure?tab=votes&page=15&pagesize=50)