(ns sof.sof09)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230216
; rfr: video/20230216-mert-clj-egzersiz-40.mp4

; [clojure - Remove nil values from a map? - Stack Overflow](https://stackoverflow.com/questions/3937661/remove-nil-values-from-a-map)

(def m {:a 1 :b 2 :c nil})
(merge (for [[k v] m :when (not (nil? v))] {k v}))
; ({:a 1} {:b 2})
; liste içinde map objeleri

; I would like to have:
{:a 1, :b 2}
; listenin içinde değil, tek seviyeli map olsun

; a01: apply merge
(apply merge (for [[k v] m :when (not (nil? v))] {k v})) ; {:a 1, :b 2}

; neden yukarıdakinden farklı bir sonuç aldık?
; aslında for comprehension aynı ikisinde de
(for [[k v] m :when (not (nil? v))] {k v})
;=> ({:a 1} {:b 2})

(apply merge '({:a 1} {:b 2}))
;=> {:a 1, :b 2}
; şuna denktir:
(merge {:a 1} {:b 2})
;=> {:a 1, :b 2}

; a02: kısa
(into {} (filter second m)) ; {:a 1, :b 2}

(comment
  (filter second m)
  ;=> ([:a 1] [:b 2])

  ; normalde second kendisine verilen collectiondaki ikinci öğeyi döndürür
  (second [10 20 30])
  ;=> 20
  ; collection eğer bir MapEntry objesiyse (key-value pair) ise o zaman değer tarafını döndürür
  (second [:a 1])
  ; m map objesinin içindeki her bir kv ikilisi (MapEntry) bir ikili dizi (tuple) gibi düşün
  ,)

; a02b: sadece nil değerli ikilileri ayıkla, false değerli olanları değil
; Dont remove false values:
(into {} (remove (comp nil? second) m)) ; {:a 1, :b 2}

(comment
  ; bununla a02'in farkı ne?
  ; bir tane map yapalım. bunda hem nil hem de false değerli ikililer olsun
  (def m2 {:a 1 :b 2 :c nil :d false})
  (into {} (filter second m2))
  ;=> {:a 1, :b 2}
  (into {} (remove (comp nil? second) m2))
  ;=> {:a 1, :b 2, :d false}

  ((comp nil? second) [:a 1])
  ;=> false
  ((comp nil? second) [:a nil])
  ;=> true
  ; dolayısıyla bu compose çağrıları aslında şuna denk:
  (nil? (second [:a 1]))
  ;=> false
  (nil? (second [:a nil]))
  ;=> true
  ,)

; a03: dissoc
; Using dissoc to allow persistent data sharing instead of creating a whole new map:
(apply dissoc
  m
  (for [[k v] m :when (nil? v)] k)) ; {:a 1, :b 2}

; a04: nested maps
; Here is one that works on nested maps:

(require '[clojure.walk :refer [postwalk]])
(defn remove-nils
  [m]
  (let [f (fn [[k v]] (when v [k v]))]
    (postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))
(remove-nils m) ; {:a 1, :b 2}
(remove-nils {:a 1 :b {:c 2 :d nil}}) ; {:a 1, :b {:c 2}}

; a05
(into {} (remove (fn [[k v]] (nil? v)) {:a 1 :b 2 :c nil})) ; {:a 1, :b 2}

; shorter
(into {} (remove #(nil? (val %)) {:a true :b false :c nil})) ; {:a true, :b false}

(into {} (filter #(not (nil? (val %))) {:a true :b false :c nil})) ; {:a true, :b false}

; a06: select-keys

(select-keys m (for [[k v] m :when (not (nil? v))] k)) ; {:a 1, :b 2}

; a07: reduce-kv

(reduce-kv
  (fn [m key value]
    (if (nil? value)
      (dissoc m key)
      m))
  m
  m) ; {:a 1, :b 2}

; a08: hem map hem vector üzerinde çalışır:

(defn compact
  [coll]
  (cond
    (vector? coll) (into [] (filter (complement nil?) coll))
    (map? coll) (into {} (filter (comp not nil? second) coll))))

(compact m) ; {:a 1, :b 2}

; a09: reduce ile

(reduce (fn [m [k v]] (if (nil? v) m (assoc m k v))) {} m) ; {:a 1, :b 2}

; sırayı korumak için dissoc
(reduce (fn [m [k v]] (if (nil? v) (dissoc m k) m)) m m) ; {:a 1, :b 2}