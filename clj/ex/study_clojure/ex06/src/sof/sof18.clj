(ns sof.sof18)

; rfr: video/20230221-mert-clj-egzersiz-47.mp4

; [clojure - How to remove multiple keys from a map? - Stack Overflow](https://stackoverflow.com/questions/9717606/how-to-remove-multiple-keys-from-a-map)

(defn remove-key [key map]
  (into {}
    (remove
      (fn [[k v]] (#{key} k))
      map)))

(remove-key :foo {:foo 1 :bar 2 :baz 3})

;How do i apply this function using multiple keys?

#_(remove-key [:foo :bar] {:foo 1 :bar 2 :baz 3})

; a01: dissoc
(apply dissoc {:foo 1, :bar 2, :baz 3} [:foo :bar])
;=> {:baz 3}

(dissoc {:foo 1 :bar 2 :baz 3} :foo :bar)
;=> {:baz 3}

; q: neden apply dissoc kullanmak bazen gerekebilir, düz dissoc kullanmak daha kısayken?
; cevap: eğer sana verilen silinecek anahtarlar verisi bir collection olarak verilmişse,
; apply kullanmak daha pratik olur

; a02: reduce

(reduce (fn [m k] (remove-key k m)) {:foo 1 :bar 2 :baz 3} [:foo :bar])
;=> {:baz 3}

(comment
  ; 1. tur
  ((fn [m k] (remove-key k m))
   {:foo 1 :bar 2 :baz 3}
   :foo)
  ;=> {:bar 2, :baz 3}
  ; 2. tur
  ((fn [m k] (remove-key k m))
   {:bar 2, :baz 3}
   :bar)
  ;=> {:baz 3}
  ,)
