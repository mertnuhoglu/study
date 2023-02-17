(ns fn.select-keys)

; rfr: video/20230217-mert-clj-egzersiz-42.mp4

; (select-keys map keyseq)
; Returns a map containing only those entries in map whose key is in keyseq

(select-keys {:a 1 :b 2} [:a])
;;=> {:a 1}

; olmayan bir anahtarı istersek, onu atlar
(select-keys {:a 1 :b 2} [:a :c])
;;=> {:a 1}

(select-keys {:a 1 :b 2 :c 3} [:a :c])
;;=> {:c 3, :a 1}

