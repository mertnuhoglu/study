(ns sof.sof08)

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
; [hashmap - clojure convert lazy-seq to hash map - Stack Overflow](https://stackoverflow.com/questions/7034685/clojure-convert-lazy-seq-to-hash-map)

; How do I create a map from a lazySeq?

(def fields [:name :age :color])
(def values ["joe" 32 "red"])
(def record (interleave fields values))
(identity record) ; (:name "joe" :age 32 :color "red")
;; (get mymap :age)
;; 32

; a01:

(apply hash-map record) ; {:age 32, :color "red", :name "joe"}

; a02: Since you actually already have separate keys and values, I would suggest skipping the interleave step and instead writing

(zipmap fields values) ; {:name "joe", :age 32, :color "red"}

; Or if you have your heart set on into, you could

(into {} (map vector fields values)) ; {:name "joe", :age 32, :color "red"}

; a03: This isn't sensible at all, but since the original question wanted to use into with record:

(into {} (map vec (partition 2 record))) ; {:name "joe", :age 32, :color "red"}


; [Return a map from a list of values in Clojure - Stack Overflow](https://stackoverflow.com/questions/26256753/return-a-map-from-a-list-of-values-in-clojure?noredirect=1&lq=1)

; tek sayıda öğe de olsa listeyi map yapmak istiyor

(defn seq->map
  [s]
  (let [s+ (concat s [nil])]
    (zipmap (take-nth 2 s+)
      (take-nth 2 (rest s+)))))

(seq->map '(1 2 3 4))   ; {3 4, 1 2}
(seq->map '(1 2 3 4 5)) ; {5 nil, 3 4, 1 2}
(seq->map [1 2 3 4])    ; {3 4, 1 2}
(seq->map [1 2 3 4 5])  ; {5 nil, 3 4, 1 2}

(comment
  (def s [1 2 3 4])
  (def s+ (concat s [nil]))
  (identity s+)
  ; (1 2 3 4 nil)
  (take-nth 2 s+)
  ; (1 3 nil)
  (take-nth 2 (rest s+))
  ; (2 4)
  ,)

; a02:

(defn to-map [v]
  (apply hash-map
    (if (odd? (count v))
      (conj v nil)
      v)))
(to-map [1 2 3])
;=> {1 2, 3 nil}
; [Convert an array of tuples into a hash-map in Clojure - Stack Overflow](https://stackoverflow.com/questions/4328709/convert-an-array-of-tuples-into-a-hash-map-in-clojure)

; a01: into {}
(into {} [[:a 1] [:b 2]])
;{:a 1, :b 2}

; list için:
(into {} (map vec '((:a 1) (:b 1))))
;{:a 1, :b 2}

; listelerde önce vektöre çevirmeden çalışmıyor
#_(into {} '((:a 1) (:b 1)))
;class clojure.lang.Keyword cannot be cast to class java.util.Map$Entry (clojure.lang.Keyword is in unnamed module of loader 'app'); java.util.Map$Entry is in module java.base of loader 'bootstrap')

; a02: reduce

(reduce conj {} [[:a 1] [:b 2]])
;{:a 1, :b 2}

(comment

  (reduce conj {} [[:a 1] [:b 2]])
  ; reduce ile bunun çalışması nasıl oluyor? #nclk/çok-önemli
  ; rfr: e03: reduce fonksiyonunun çalışma mantığını anlama: <url:file:///~/prj/study/clj/ex/study_clojure/ex06/src/fn/reduce.clj#r=g13703>

  ; reduce, conj fonksiyonunu coll'daki öğelere sırayla uygular
  ; 1. tur: ilk çağrıyı {} ve [:a 1] öğelerine yapar.
  ; 2. tur: ilk turun sonucunu ve bir sonraki coll öğesini `[:b 2]` conj fonksiyonuna paslar

  (conj {} [:a 1])
  ;=> {:a 1}
  ; reduce bu ara sonucu alır. conj fonksiyonuna bunu ve bir sonraki öğeyi uygular.
  (conj {:a 1} [:b 2])
  ;=> {:a 1, :b 2}
  ,)

; a03: flatten. (kötü çözüm)

(def a [[:a 4] [:b 6]])
(apply hash-map (flatten a))
;{:a 4, :b 6}

; bu çözüm burada çalıştı, ancak flatten özyinelemeli bir şekilde her şeyi düzler
; o yüzden beklenmedik sonuçlar alabilirsin.