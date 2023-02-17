(ns fn.dissoc)

; rfr: video/20230217-mert-clj-egzersiz-41.mp4

; (dissoc map)
; (dissoc map key)
; (dissoc map key & ks)
; dissoc[iate]. Returns a new map of the same
; (hashed/sorted) type,
; that does not contain a mapping for key
; (s).

(dissoc {:a 1 :b 2 :c 3}) ; dissoc nothing
;;=> {:a 1, :b 2, :c 3}

(dissoc {:a 1 :b 2 :c 3} :b) ; dissoc key :b
;;=> {:a 1, :c 3}

(dissoc {:a 1 :b 2 :c 3} :d) ; dissoc not existing key
;;=> {:a 1, :b 2, :c 3}

(dissoc {:a 1 :b 2 :c 3} :c :b) ; several keys at once
;;=> {:a 1}
