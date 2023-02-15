(ns sof.sof06)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230215
; rfr: video/20230215-mert-clj-egzersiz-35.mp4

; [What is the idiomatic way to prepend to a vector in Clojure? - Stack Overflow](https://stackoverflow.com/questions/4095714/what-is-the-idiomatic-way-to-prepend-to-a-vector-in-clojure)

; Terim: idiomatic
; idiom: deyim
; idiomatic: Her dilin kendi tavsiye edilen yazım stili var.
; Bir problemi çözmenin birden çok yolu vardır.
; Ancak bunlardan hepsi değil, belki bir veya iki tanesi, o dilin yazım stiline daha uygundur.
; Buna idiomatic deniyor.

; Terim: prepend append
; prepend: önden ekleme
; append: sona ekleme
; mesela bir listeniz var. o listenin başına ekleme: prepend. sonuna: append.

; Prepending to a list is easy:

(conj '(:bar :baz) :foo)
; (:foo :bar :baz)

; Appending to a vector is easy:

(conj [:bar :baz] :foo)
; [:bar :baz :foo]

; How do I (idiomatically) prepend to a vector, while getting back a vector? This does not work as it returns a seq, not a vector:

(cons :foo [:bar :baz])
; (:foo :bar :baz)

; This is ugly (IMVHO):

(apply vector (cons :foo [:bar :baz]))
; [:foo :bar :baz]

; Vectors are not designed for prepending. You have only O(n) prepend:

; Big-O notation: performans (zaman) ölçümünü tarif eder.
; O(n): senin veri yapındaki öğe sayısıyla orantılı bir şekilde zaman harcar
; Yani: 100 öğeli bir vektörün varsa. 1 sn'de işlem yaptın.
; 1000 öğeli bir vektörün olursa, 10 sn'de işlem biter.
; O(n): öğe sayısıyla düz orantılı.
; O(n2), O(log n), O(x^n), O(1)
; conj fonksiyonu O(1) bir fonksiyondur. Anında çalışır, verinin büyüklüğünden bağımsız olarak.
; ama bir vektöre sondan ekleme yapmak cons ve apply ile O(n)'dir. Yani ciddi zaman harcar.

; Prensip: Optimizasyon en son yapılacak iştir.
; [design - Is premature optimization really the root of all evil? - Software Engineering Stack Exchange](https://softwareengineering.stackexchange.com/questions/80084/is-premature-optimization-really-the-root-of-all-evil)
; Performans optimizasyonunu çok fazla dikkate almadan programı yaz.
; İleride sorun yaşarsak yavaşlık açısından, o zaman o problemi çözeriz.
; Dijkstra: Premature optimization is the root of all evil.
; [Premature Optimization](https://wiki.c2.com/?PrematureOptimization)
; Gerekçesi: Kodu performans için optimize edersin, çok sayıda trick kullanman gerekir.
; Bunlar da kodunu anlaşılmaz hale getirir.
; State bağımlılığın giderek artar (immutable yerine mutable yapılar kullanırsın).

; Ama bu demek değil ki, tamamen de ihmal edelim.
; En azından conj fonksiyonun O(1) bileceksin. Vektöre sondan ekleme yapman gerektiğini bileceksin.
; Çok temel bilgiler bunlar.
; İleri seviye optimizasyon yapmana gerek yok.

(into [:foo] [:bar :baz])
; [:foo :bar :baz]

; [data structures - Representing A Tree in Clojure - Stack Overflow](https://stackoverflow.com/questions/1787708/representing-a-tree-in-clojure)

; What would be an idiomatic way to represent a tree in Clojure? E.g.:

;      A
;     / \
;    B   C
;   /\    \
;  D  E    F

'(A (B (D) (E)) (C (F)))

; Demek ki, biz vektör/liste türü yapılarla aslında hiyerarşik verileri de tutabiliriz.
; Nested (içiçe) bir liste/vektörümüz olur.

; [Clojure: Semi-Flattening a nested Sequence - Stack Overflow](https://stackoverflow.com/questions/5232350/clojure-semi-flattening-a-nested-sequence)

; flatten: rfr: fn/flatten.clj #nclk/çok-önemli

'(([1 2]) ([3 4] [5 6]) ([7 8]))

(flatten '(([1 2]) ([3 4] [5 6]) ([7 8])))
;=> (1 2 3 4 5 6 7 8)
; Adam bunu istemiyor, sadece bir seviyelik düzleştirme istiyor.

; Which I know is not ideal to work with. I'd like to flatten this to ([1 2] [3 4] [5 6] [7 8]).

; flatten doesn't work: it gives me (1 2 3 4 5 6 7 8).

; apply concat, sadece tek seviyelik düzleştirme yapar. özyinelemeli yapmaz.
; apply: rfr: fn/apply.clj #nclk/çok-önemli
; concat: rfr: fn/concat.clj #nclk/çok-önemli
(apply concat '(([1 2]) ([3 4] [5 6]) ([7 8])))
; => ([1 2] [3 4] [5 6] [7 8])

; bu aslında şuna  denk geliyor:
(concat '([1 2]) '([3 4] [5 6]) '([7 8]))
; => ([1 2] [3 4] [5 6] [7 8])

; apply demezsek, verdiğimiz argümanın aynısını bize döner
(concat '(([1 2]) ([3 4] [5 6]) ([7 8])))
;=> (([1 2]) ([3 4] [5 6]) ([7 8]))

; [How to repeat string n times in idiomatic clojure way? - Stack Overflow](https://stackoverflow.com/questions/5433691/how-to-repeat-string-n-times-in-idiomatic-clojure-way)

; a01: idiomatic
(apply str (repeat 3 "foo"))
; "foofoofoo"

; a02: idiomatic
(repeat 3 "foo")
; ("foo" "foo" "foo")

; a03: uzun yol
(map (fn [n] "foo") (range 3))
; ("foo" "foo" "foo")

; a04: protocol ile örnek #nclk/sonra

; protocol: java'da interfacelere denk gelir
; fakat biz clojureda klasik OOP çok yapmadığımızdan çok kullanılmaz.
; biz clojurde OOP'yi olması gerektiği gibi kullanıyoruz.
; OOP'nin özündeki kavramları kullanıyoruz: polimorfizm
; polimorfizm için protocolleri kullanırız.

; And one more fun alternative using protocols:
(defprotocol Multiply (* [this n]))

; Next the String class is extended:
(extend String Multiply {:* (fn [this n] (apply str (repeat n this)))})

; So you can now 'conveniently' use:
(* "foo" 3) ; "foofoofoo"

; a05:

(clojure.string/join (repeat 3 "str"))
; "strstrstr"

(format "%1$s%1$s%1$s" "str")
; "strstrstr"

(reduce str (take 3 (cycle ["str"])))
; "strstrstr"

(reduce str (repeat 3 "str"))
; "strstrstr"

(reduce #(.concat %1 %2) (repeat 3 "str"))
; "strstrstr"



; [functional programming - What's the one-level sequence flattening function in Clojure? - Stack Overflow](https://stackoverflow.com/questions/10723451/whats-the-one-level-sequence-flattening-function-in-clojure)

(def bs [[[1 2]] [[2 3]] [[4 5]]])

(apply concat bs) ; ([1 2] [2 3] [4 5])

(for [subcoll bs, item subcoll] item) ; ([1 2] [2 3] [4 5])

(mapcat identity bs) ; ([1 2] [2 3] [4 5])

(mapcat seq bs) ; ([1 2] [2 3] [4 5])

; a0n: özel durum: eğer ilk öğe primitifse apply concat hata verir
#_(apply concat [1 [2 3] [4 [5]]])

(defn flatten-one-level [coll]
  (mapcat  #(if (sequential? %) % [%]) coll))

(flatten-one-level [1 [2 3] [4 [5]]]) ; (1 2 3 4 [5])

; [idioms - What is the idiomatic way to assoc several keys/values in a nested map in Clojure? - Stack Overflow](https://stackoverflow.com/questions/4495345/what-is-the-idiomatic-way-to-assoc-several-keys-values-in-a-nested-map-in-clojur)

(def person {
             :name {
                    :first-name "John"
                    :middle-name "Michael"
                    :last-name "Smith"}})

(update-in person [:name] assoc :first-name "Bob" :last-name "Doe")
; {:name {:middle-name "Michael", :last-name "Doe", :first-name "Bob"}}

(update-in person [:name] merge {:first-name "Bob" :last-name "Doe"})
; {:name {:middle-name "Michael", :last-name "Doe", :first-name "Bob"}}

(update-in person [:name] into {:first-name "Bob" :last-name "Doe"})
; {:name {:middle-name "Michael", :last-name "Doe", :first-name "Bob"}}

(-> person
  (assoc-in [:name :first-name] "Bob")
  (assoc-in [:name :last-name]  "Doe"))
; {:name {:middle-name "Michael", :last-name "Doe", :first-name "Bob"}}

; update-in does recursive assocs on your map. In this case it's roughly equivalent to:
(assoc person :name
  (assoc (:name person)
    :first-name "Bob"
    :last-name "Doe"))
; {:name {:first-name "Bob", :middle-name "Michael", :last-name "Doe"}}

(def foo {:bar {:baz {:quux 123}}})

(assoc foo :bar
  (assoc (:bar foo) :baz
    (assoc (:baz (:bar foo)) :quux
      (inc (:quux (:baz (:bar foo)))))))
; {:bar {:baz {:quux 124}}}

(update-in foo [:bar :baz :quux] inc)
; {:bar {:baz {:quux 124}}}

; [clojure - Remove nil values from a map? - Stack Overflow](https://stackoverflow.com/questions/3937661/remove-nil-values-from-a-map)

(def record {:a 1 :b 2 :c nil})
(merge (for [[k v] record :when (not (nil? v))] {k v}))
; ({:a 1} {:b 2})

; I would like to have:
{:a 1, :b 2}

; a01
(apply merge (for [[k v] record :when (not (nil? v))] {k v})) ; {:a 1, :b 2}

; a02: kısa
(into {} (filter second record)) ; {:a 1, :b 2}

; Dont remove false values:
(into {} (remove (comp nil? second) record)) ; {:a 1, :b 2}

; a03: dissoc
; Using dissoc to allow persistent data sharing instead of creating a whole new map:
(apply dissoc
  record
  (for [[k v] record :when (nil? v)] k)) ; {:a 1, :b 2}

; a04: nested maps
; Here is one that works on nested maps:

(require '[clojure.walk :refer [postwalk]])
(defn remove-nils
  [m]
  (let [f (fn [[k v]] (when v [k v]))]
    (postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))
(remove-nils record) ; {:a 1, :b 2}
(remove-nils {:a 1 :b {:c 2 :d nil}}) ; {:a 1, :b {:c 2}}

; a05
(into {} (remove (fn [[k v]] (nil? v)) {:a 1 :b 2 :c nil})) ; {:a 1, :b 2}

; shorter
(into {} (remove #(nil? (val %)) {:a true :b false :c nil})) ; {:a true, :b false}

(into {} (filter #(not (nil? (val %))) {:a true :b false :c nil})) ; {:a true, :b false}

; a06: select-keys

(select-keys record (for [[k v] record :when (not (nil? v))] k)) ; {:a 1, :b 2}

; a07: reduce-kv

(reduce-kv 
  (fn [m key value]
     (if (nil? value)
       (dissoc m key)
       m))
  record
  record) ; {:a 1, :b 2}

; a08: hem map hem vector üzerinde çalışır:

(defn compact
  [coll]
  (cond
    (vector? coll) (into [] (filter (complement nil?) coll))
    (map? coll) (into {} (filter (comp not nil? second) coll))))

(compact record) ; {:a 1, :b 2}

; a09: reduce ile

(reduce (fn [m [k v]] (if (nil? v) m (assoc m k v))) {} record) ; {:a 1, :b 2}

; sırayı korumak için dissoc
(reduce (fn [m [k v]] (if (nil? v) (dissoc m k) m)) record record) ; {:a 1, :b 2}
