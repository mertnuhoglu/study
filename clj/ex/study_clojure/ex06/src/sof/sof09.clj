(ns sof.sof09)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230216
; rfr: video/20230216-mert-clj-egzersiz-40.mp4
; rfr: video/20230217-mert-clj-egzersiz-41.mp4
; rfr: video/20230217-mert-clj-egzersiz-42.mp4
; rfr: video/20230217-mert-clj-egzersiz-43.mp4

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
; rfr: video/20230217-mert-clj-egzersiz-41.mp4

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

(remove-nils m)
; {:a 1, :b 2}
(remove-nils {:a 1 :b {:c 2 :d nil}})
; {:a 1, :b {:c 2}}
(remove-nils {:a 1 :b {:c 2 :d {:e 3 :f nil}}})
;=> {:a 1, :b {:c 2, :d {:e 3}}}

; a05
(into {} (remove (fn [[k v]] (nil? v)) m)) ; {:a 1, :b 2}
; not: yukarıdaki into remove comp nil? second kullanımıyla aynı, fakat orada second kullanmıştık, burada destructuring kullandık

; into remove second ile birbirine çok benziyor
; second ile ikililerin değer kısmına erişmek yerine [k v] destructuring ile erişiyoruz
(into {} (remove (fn [[k v]] (nil? v)) m)) ; {:a 1, :b 2}
(into {} (remove (comp nil? second) m)) ; {:a 1, :b 2}

; rfr: video/20230217-mert-clj-egzersiz-42.mp4

; shorter
(into {} (remove #(nil? (val %)) m)) ;=> {:a 1, :b 2}

(into {} (filter #(not (nil? (val %))) m)) ;=> {:a 1, :b 2}

; a06: select-keys

(select-keys m (for [[k v] m :when (not (nil? v))] k)) ; {:a 1, :b 2}

; şuna denktir
(select-keys m '(:a :b))

; TODO: cursive editörünün eğitiminde `!e e` ve `!e d` farkını anlatalım
; #terim: kısayol = shortcut = key binding
; benim kısayollarımın kökeni, emacs'in spacemacs eklentisi
; cursive (intellij), vim, emacs, vscode: bunların hepsinde aynı standart kısayolları tanımlıyorum
; mnemonics (çağrışımlara) bağlı bu kısayollar

; rfr: video/20230217-mert-clj-egzersiz-43.mp4

; a07: reduce-kv

(identity m)
;=> {:a 1, :b 2, :c nil}
(def f9
  (fn [m key value]
    (if (nil? value)
      (dissoc m key)
      m)))
(reduce-kv
  f9             ; f
  m              ; init
  m)
; {:a 1, :b 2}

(comment
  ; (reduce-kv f                 init coll)
  ; (...       anonim-function   m    m   )
  ; ilk tur:
  (f9 m :a 1)
  ;=> {:a 1, :b 2, :c nil}
  ; 2. tur:
  (f9 {:a 1, :b 2, :c nil} :b 2)
  ;=> {:a 1, :b 2, :c nil}
  ; 3. tur:
  (f9 {:a 1, :b 2, :c nil} :c nil)
  ;=> {:a 1, :b 2}

  ; q: anonim fonksiyona verdiğimiz [m key value] argümanları destructuring mı?
  ; [m key value]
  ; %1
  (def f10
    #(if (nil? %2)
       (dissoc %1 %3)
       %1))
  (reduce-kv f10 m m)
  ;=> {:a 1, :b 2, :c nil}
  (def f11
    #(if (nil? %3)
       (dissoc %1 %2)
       %1)
    ,)
  (reduce-kv f11 m m)
  ;=> {:a 1, :b 2}
  ; end
  ,)

; reduce-kv vektör üzerinde de çalışabilir
(def v [10 20 nil])
#_(reduce-kv f11 v v)
;class clojure.lang.PersistentVector cannot be cast to class clojure.lang.IPersistentMap (clojure.lang.PersistentVector and clojure.lang.IPersistentMap are in unnamed module of loader 'app')
; dissoc'un ilk argümanı mutlaka map olmalı

; a08: hem map hem vector üzerinde çalışır:

(defn compact
  [coll]
  (cond
    (vector? coll) (into [] (filter (complement nil?) coll))
    (map? coll) (into {} (filter (comp not nil? second) coll))))

(identity m)
;=> {:a 1, :b 2, :c nil}
(compact m)
; {:a 1, :b 2}

(compact v)
;=> [10 20]

(comment
  ; yukarıda complement yerine not kullanırsak hata verir
  ; çünkü filter fonksiyonu arg olarak bir fonksiyon alır
  ; fakat not fonksiyon değil, değer döner
  (defn compact2
    [coll]
    (cond
      (vector? coll) (into [] (filter (not nil?) coll))
      (map? coll) (into {} (filter (comp not nil? second) coll))))
  ;class java.lang.Boolean cannot be cast to class clojure.lang.IFn (java.lang.Boolean is in module java.base of loader 'bootstrap'); clojure.lang.IFn is in unnamed module of loader 'app')
  ; end
  ,)

; a09: reduce ile

(identity m)
;=> {:a 1, :b 2, :c nil}
(reduce
  (fn [m [k v]]                                    ; f (reducer)
    (if
      (nil? v)          ; test
      m                 ; then clause
      (assoc m k v)))   ; else clause
  {}                                               ; val (init)
  m)                                               ; coll

(comment
  ; reducer fonksiyonları 2 argüman alır her zaman (reduce için)
  ; ilk arg: ya başlangıç değeri yani init (ilk tur için), ya da bir önceki turun sonuç değeri
  ; ikinci arg: coll'daki sıradaki öğe
  ; ilk arg: m
  ; 2. arg: [k v]
  ; 2. argümanda destructuring yapyıor

  ; 1. tur:
  ; reducer fonksiyonunun 1. arg: {}
  ;                       2. arg: [:a 1]
  ;                               [k  v]  (destructuring)
  ; k <- :a
  ; v <- 1
  ; (assoc m k v)
  ; (assoc {} :a 1)
  ;
  ; 2. tur:
  ; (assoc {:a 1} :b 2)
  ; 3. tur:
  ; (assoc {:a 1 :b 2} :c nil)

  ; q: anonim fonksiyonun içindeki m ile dışarıdaki m aynı değiller mi?
  ; değiller
  ; scope meselesinden dolayı
  ; inner scope, outer scope'u ezer (override eder).
  ; rfr: fn/syntax/scope.clj

  ;end
  ,)
; {:a 1, :b 2}

; sırayı korumak için dissoc
(reduce (fn [m [k v]] (if (nil? v) (dissoc m k) m)) m m) ; {:a 1, :b 2}
; init argümanları yukarıdakinden farklı:
; yukarıda en başta boş bir map ile başlıyorduk
; burada ise en başta tam dolu map ile başlıyoruz.