(ns sequence)

; Code from: Programming Clojure, Alex Miller

;; Vectors are seqs

(def aseq [1 2 3])

(first aseq)
;;=> 1

(rest aseq)
;;=> (2 3)

(cons 4 aseq)
;;=> (2 3)

;; next = seq rest
(next aseq)
;;=> (2 3)

(seq (rest aseq))
;;=> (2 3)

(seq? (rest aseq))
;;=> true

;; Maps are seqs

(def m {:fname "Ali" :lname "Veli"})

(first m)
;;=> [:fname "Ali"]

(rest m)
;;=> ([:lname "Veli"])

(cons [:mname "Can"] m)
;;=> ([:mname "Can"] [:fname "Ali"] [:lname "Veli"])

;; Sets are seqs

(def s #{:ali :veli})

(first s)
;;=> :ali

(rest s)
;;=> (:veli)

(cons :can s)
;;=> (:can :ali :veli)

(sorted-set :can :ali)
;;=> #{:ali :can}

(conj s :a)
;;=> #{:ali :veli :a}

(into [:a] s)
;;=> [:a :ali :veli]

;; rest doesn't return a list but a seq
(list? (rest [1 2 3]))
;;=> false

(seq? (rest [1 2 3]))
;;=> true

;; 05 Creating Sequences

; 05.01 (range start? end? step?)

(range 10)
;;=> (0 1 2 3 4 5 6 7 8 9)

(range 10 20)
;;=> (10 11 12 13 14 15 16 17 18 19)

(range 1 10 2)
;;=> (1 3 5 7 9)

(range 0 -1 -0.25)
;;=> (0 -0.25 -0.5 -0.75)

(range 1/2 4 1)
;;=> (1/2 3/2 5/2 7/2)

; 05.02 (repeat n x)

(repeat 5 1)
;;=> (1 1 1 1 1)

(repeat 3 "a")
;;=> ("a" "a" "a")

; 05.03 (iterate f x)

(take 5 (iterate inc 1))
;;=> (1 2 3 4 5)

; 05.04 (take n sequence)

(def whole-numbers (iterate inc 1))

; 05.04 (repeat x)

(take 5 (repeat 1))
;;=> (1 1 1 1 1)

; 05.05 (cycle coll)

(take 10 (cycle (range 3)))
;;=> (0 1 2 0 1 2 0 1 2 0)

; 05.06 (interleave & colls)

(interleave whole-numbers ["a" "b" "c" "d"])
;;=> (1 "a" 2 "b" 3 "c" 4 "d")

; 05.07 (interpose separator coll)

(interpose "," ["apples" "bananas"  "grapes"])
;;=> ("apples" "," "bananas" "," "grapes")

(apply str (interpose "," ["apples" "bananas"  "grapes"]))
;;=> "apples,bananas,grapes"

; 05.08 (join separator sequence)

(require '[clojure.string :refer [join]])
(join \, ["apples" "bananas"  "grapes"])
;;=> "apples,bananas,grapes"

; 05.09 list vector hash-set hash-map

(set [1 2 3])
;;=> #{1 3 2}

(hash-set 1 2 3)
;;=> #{1 3 2}

(vec (range 3))
;;=> [0 1 2]

(vector 1 2 3)
;;=> [1 2 3]

; 06 Filtering Sequences

; 06.01 (filter pred coll)

(take 5 (filter even? whole-numbers))
;;=> (2 4 6 8 10)

(take 5 (filter odd? whole-numbers))
;;=> (1 3 5 7 9)

; 06.02 (take-while pred coll)

(def vowel? #{\a \e \i \o \u})
(def consonant? (complement vowel?))

(take-while consonant? "the-quick")
;;=> (\t \h)

; 06.03 (drop-while pred coll)

(drop-while consonant? "the-quick-brown-fox")
;;=> (\e \- \q \u \i \c \k \- \b \r \o \w \n \- \f \o \x)

; 06.04 (split-at index coll)

(split-at 5 (range 10))
;;=> [(0 1 2 3 4) (5 6 7 8 9)]

; 06.05 (split-with pred coll)

(split-with #(<= % 10) (range 0 20 2))
;;=> [(0 2 4 6 8 10) (12 14 16 18)]

; 07 Sequence Predicates

; 07.01 (every? pred coll)

(every? odd? [1 3 5])
;;=> true

(every? odd? [1 2])
;;=> false

; 07.02 (some pred coll)

(some even? [1 2 3])
;;=> true

(some even? [1 3])
;;=> nil

; Note: some is not predicate

(some identity [nil false 1 2])
;;=> 1

(some #{3} (range 10))
;;=> 3

; 07.03 (not-every? pred coll)

(not-every? even? whole-numbers)
;;=> true

; 07.04 (not-any? pred coll)

(not-any? even? whole-numbers)
;;=> false

; 08 Transforming Sequences

; 08.01 (map f coll)

(map #(format "<p>%s</p>" %) ["the" "quick" "brown" "fox"])
;;=> ("<p>the</p>" "<p>quick</p>" "<p>brown</p>" "<p>fox</p>")

(map #(format "<%s>%s</%s>" % %1 %2 %1) ["h1" "h2" "h3" "h4"] ["the" "quick" "brown" "fox"])
;;=> ("<h1>h1</the>" "<h2>h2</quick>" "<h3>h3</brown>" "<h4>h4</fox>")

; 08.02 (reduce f coll)

(reduce + (range 1 11))
;;=> 55

(reduce * (range 1 11))
;;=> 3628800

; 08.03 (sort comp? coll)

(sort [42 1 7])
;;=> (1 7 42)

; 08.04 (sort-by f comp? coll)

(sort-by #(.toString %) [42 1 7])
;;=> (1 42 7)

(sort > [42 1 7])
;;=> (42 7 1)

(sort-by :grade > [{:grade 83} {:grade 77} {:grade 90}])
;;=> ({:grade 90} {:grade 83} {:grade 77})

; 08.05 (for [binding-form coll-expr filter-expr? ...] expr)
; list comprehension: mother of all filters and transformations

(for [word ["the" "quick" "brown" "fox"]]
  (format "<p>%s</p>" word))
;;=> ("<p>the</p>" "<p>quick</p>" "<p>brown</p>" "<p>fox</p>")

(take 5 (for [n whole-numbers :when (even? n)] n))
;;=> (2 4 6 8 10)

(for [n whole-numbers :while (odd? n)] n)
;;=> (1)

; more than one binding:
(for [file "abc"
      rank (range 1 3)]
  (format "%c%d" file rank))
;;=> ("a1" "a2" "b1" "b2" "c1" "c2")

(for [rank (range 1 3)
      file "abc"]
  (format "%c%d" file rank))
;;=> ("a1" "b1" "c1" "a2" "b2" "c2")

; 09 Lazy and Infinite Sequences

; 09.01 (doall coll)
; walk sequences with side-effects

(def x
  (for [i (range 1 3)]
    (do (println i) i)))
(doall x)
;;=> (1 2)

; 09.02 (dorun coll)

(dorun x)
;;=> nil

; 10 Seq-ing Java Collections

(first (.getBytes "hello"))
;;=> 104

(rest (.getBytes "hello"))
;;=> (101 108 108 111)

(cons (int \h) (.getBytes "ello"))
;;=> (104 101 108 108 111)

; Hashtables and Maps are also seqable

(first (System/getProperties))
;;=> #object[java.util.Hashtable$Entry 0x194f24db "clojure.basis=.cpcache/1745053518.basis"]
;;=> #object[java.util.Hashtable$Entry 0x194f24db "clojure.basis=.cpcache/1745053518.basis"]

(first (rest (System/getProperties)))
;;=> #object[java.util.Hashtable$Entry 0x12c022f5 "javafx.version=8.0.91"]

(reverse "hello")
;;=> (\o \l \l \e \h)

(apply str (reverse "hello"))
;;=> "olleh"

; 11 Seqing Regular Expressions

; 11.01 (re-matcher regexp string)
; don't use this

; 11.02 (re-seq regexp string)

(re-seq #"\w+" "the quick brown fox")
;;=> ("the" "quick" "brown" "fox")

(sort (re-seq #"\w+" "the quick brown fox"))
;;=> ("brown" "fox" "quick" "the")

(drop 2 (re-seq #"\w+" "the quick brown fox"))
;;=> ("brown" "fox")

(map clojure.string/upper-case (re-seq #"\w+" "the quick brown fox"))
;;=> ("THE" "QUICK" "BROWN" "FOX")

; 12 Seqing the File System

(import 'java.io.File)
(first (.listFiles (File. ".")))
;;=> #object[java.io.File 0x58128fdb "./ex06.iml"]

(first (seq (.listFiles (File. "."))))
;;=> #object[java.io.File 0x73036637 "./ex06.iml"]

(map #(.getName %) (seq (.listFiles (File. "."))))
;;=> ("ex06.iml"...

; depth-first walk
(count (file-seq (File. ".")))
;;=> 111

(defn minutes-to-millis [mins] (* mins 1000 60))

(defn recently-modified? [file]
  (> (.lastModified file)
     (- (System/currentTimeMillis) (minutes-to-millis 30))))

(filter recently-modified? (file-seq (File. ".")))
;;=> (#object[java.io.File 0x6c3e4710 "./src/sequence.clj"])

; 13 Seqing a Stream

; ex13.01: read a file

(require '[clojure.java.io :refer [reader]])
(take 2
      (line-seq
        (reader "deps.edn")))
;;=> ("{:aliases"
;;=>  "   {:deploy {:extra-deps {slipset/deps-deploy {:mvn/version \"0.1.1\"}},")

; close reader
(with-open [rdr (reader "deps.edn")]
  (count (line-seq rdr)))
;;=> 26

; ex13.02: count all lines in all clojure source code

; non-blank lines
(with-open [rdr (reader "deps.edn")]
  (count (filter #(re-find #"\S" %) (line-seq rdr))))
;;=> 25

; detect non-blank lines
(defn non-blank? [line] (if (re-find #"\S" line) true false))

; detect files that are not svn metadata
(defn non-svn? [file] (not (.contains (.toString file) ".svn")))

; detect clojure source files
(defn clojure-source? [file] (.endsWith (.toString file) ".clj"))

(defn clojure-loc [base-file]
  (reduce
   +
   (for [file (file-seq base-file)
         :when (and (clojure-source? file) (non-svn? file))]
     (with-open [rdr (reader file)]
       (count (filter non-blank? (line-seq rdr)))))))

(count (file-seq (File.  "/Users/mertnuhoglu/codes/clj/clojure/src/clj/clojure")))
;;=> 49

(clojure-loc (java.io.File.  "/Users/mertnuhoglu/codes/clj/clojure/src/clj/clojure"))
;;=> 18582

(clojure-loc (java.io.File.  "/Users/mertnuhoglu/codes/clj/clojure"))
;;=> 30543

; 14 Functions on Lists

; 14.01 (peek coll)

; same as first
(peek '(1 2 3))
;;=> 1

; 14.02 (pop coll)

; similar to rest but not the same
(pop '(1 2 3))
;;=> (2 3)

(rest '())
;;=> ()

;; (pop ())
; (err) Can't pop empty list

; 15 Functions on Vectors

; 15.01 (peek v)

(peek [1 2 3])
;;=> 3

; 15.02 (pop v)
(pop [1 2 3])
;;=> [1 2]

; 15.03 (get v idx)

(get [:a :b :c] 1)
;;=> :b

(get [:a :b :c] 5)
;;=> nil

; 15.04 vectors as functions

([:a :b :c] 1)
;;=> :b

;; ([:a :b :c] 5)
; (err) Execution error (IndexOutOfBoundsException) at sequence/eval8965 (REPL:455).

; 15.05 assoc

(assoc [0 1 2] 3 :four)
;;=> [0 1 2 :four]

; 15.06 (subvec avec start end?)

(subvec [1 2 3 4 5] 3)
;;=> [4 5]

(subvec [1 2 3 4 5] 1 3)
;;=> [2 3]

; equivalent but subvec is faster:
(take 2 (drop 1 [1 2 3 4 5]))
;;=> (2 3)

; 16 Functions on Maps

; 16.01 (keys map)

(keys {:a 1 :b 2})
;;=> (:a :b)

; 16.02 (vals map)

(vals {:a 1 :b 2})
;;=> (1 2)

; 16.03 (get map key value-if-not-found?)

(get {:a 1 :b 2} :a)
;;=> 1

(get {:a 1 :b 2} :c)
;;=> nil

; 16.04 Maps as functions

({:a 1 :b 2} :a)
;;=> 1

({:a 1 :b 2} :c)
;;=> nil

; 16.05 Keywords as functions

(:a {:a 1 :b 2})
;;=> 1

(:c {:a 1 :b 2})
;;=> nil

; 16.06 (contains? map key)

(def score {:stu nil :joey 100})

(:stu score)
;;=> nil

(contains? score :stu)
;;=> true

; equivalent
(get score :stu :score-not-found)
;;=> nil

(get score :aaron :score-not-found)
;;=> :score-not-found

; if `nil` is a legal value in a map use `contains?` or `get` to test the presence of a key

; 16.07 (assoc map key val)

(def song {:name "Agnus Dei"
           :artist "Krzysztof Penderecki"
           :album "Polish Requiem"
           :genre "Classical"})

(assoc song :kind "MPEG Audio File")
;;=> {:name "Agnus Dei", :artist "Krzysztof Penderecki", :album "Polish Requiem", :genre "Classical", :kind "MPEG Audio File")

; 16.08 (dissoc map key)

(dissoc song :genre)
;;=> {:name "Agnus Dei", :artist "Krzysztof Penderecki", :album "Polish Requiem")

; 16.09 (select-keys map coll)

(select-keys song [:name :artist])
;;=> {:name "Agnus Dei", :artist "Krzysztof Penderecki"}

; [select-keys - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/select-keys)

(select-keys {:a 1 :b 2} [:a])
;;=> {:a 1}
(select-keys {:a 1 :b 2} [:a :c])
;;=> {:a 1}
(select-keys {:a 1 :b 2 :c 3} [:a :c])
;;=> {:c 3, :a 1}

; 16.10 (merge map1 map2)

(merge song {:size 8118166 :time 507245})
;;=> {:name "Agnus Dei", :artist "Krzysztof Penderecki", :album "Polish Requiem", :genre "Classical", :size 8118166, :time 507245)

; 16.11 (merge-with merge-fn & maps)

; like merge
; if two maps have the same key, you specify how to combine the values
; use merge-with with concat to build a seq of values under each key

(merge-with
  concat
  {:a ["ali"], :b ["bob"]}
  {:a ["ayse"], :b ["bill"]}
  {:a ["a3"], :b ["b3"]})
;;=> {:a ("ali" "ayse" "a3"), :b ("bob" "bill" "b3")}

; 17 Functions on sets

(def languages #{"java" "c" "d" "clojure"})
(def beverages #{"java" "chai" "pop"})

(require '[clojure.set :refer [union difference intersection select]])

; 17.01 (union set1 set2)

(union languages beverages)
;;=> #{"d" "clojure" "pop" "java" "chai" "c"}

; 17.02 (difference set1 set2)

(difference languages beverages)
;;=> #{"d" "clojure" "c"}

; 17.03 (intersection set1 set2)

(intersection languages beverages)
;;=> #{"java"}

; 17.04 (select f set)

(select #(= 1 (count %)) languages)
;;=> #{"d" "c"}

; Ex: relational algebra

(def compositions
  #{{:name "The Art of the Fugue" :composer "J. S. Bach"}
    {:name "Musical Offering" :composer "J. S. Bach"}
    {:name "Requiem" :composer "Giuseppe Verdi"}
    {:name "Requiem" :composer "W. A. Mozart"}})
(def composers
  #{{:composer "J. S. Bach" :country "Germany"}
    {:composer "W. A. Mozart" :country "Austria"}
    {:composer "Giuseppe Verdi" :country "Italy"}})
(def nations
  #{{:nation "Germany" :language "German"}
    {:nation "Austria" :language "German"}
    {:nation "Italy" :language "Italian"}})

(require '[clojure.set :refer [rename project]])

; 17.05 (rename relation rename-map)

(rename compositions {:name :title})
;;=> #{{:composer "Giuseppe Verdi", :title "Requiem"}
  ;; {:composer "W. A. Mozart", :title "Requiem"}
  ;; {:composer "J. S. Bach", :title "The Art of the Fugue"}
  ;; {:composer "J. S. Bach", :title "Musical Offering"})

; 17.06 (select pred relation)

(select #(= (:name %) "Requiem") compositions)
;;=> #{{:name "Requiem", :composer "Giuseppe Verdi"}
  ;; {:name "Requiem", :composer "W. A. Mozart"})

; 17.07 (project relation keys)

(project compositions [:name])
;;=> #{{:name "The Art of the Fugue"} {:name "Musical Offering"} {:name "Requiem"})

; 17.08 list comprehension as cross product

(for [m compositions c composers] (concat m c))
;;=> (([:name "Musical Offering"]
  ;; [:composer "J. S. Bach"]
  ;; [:composer "Giuseppe Verdi"]
  ;; [:country "Italy"]
  ;; ...

; 17.09 (join relation-1 relation-2 keymap?)

(clojure.set/join compositions composers)
;;=> #{{:composer "W. A. Mozart", :country "Austria", :name "Requiem"}
  ;; {:composer "J. S. Bach", :country "Germany", :name "Musical Offering"}
  ;; {:composer "Giuseppe Verdi", :country "Italy", :name "Requiem"}
  ;; {:composer "J. S. Bach", :country "Germany", :name "The Art of the Fugue"})

(clojure.set/join composers nations {:country :nation})
;;=> #{{:composer "W. A. Mozart", :country "Austria", :nation "Austria", :language "German"
  ;; {:composer "J. S. Bach", :country "Germany", :nation "Germany", :language "German"}
  ;; {:composer "Giuseppe Verdi", :country "Italy", :nation "Italy", :language "Italian"})

(project
  (clojure.set/join
    (select #(= (:name %) "Requiem") compositions)
    composers)
  [:country])
;;=> #{{:country "Italy"} {:country "Austria"}}

(as-> composers x
     clojure.set/join (select #(= (:name %) "Requiem") compositions) x
     project x [:country])
;;=> [:country]

(as-> composers x
  identity x)
;;=> #object[clojure.core$identity 0x18be638c "clojure.core$identity@18be638c"]

(as-> composers x
  (identity x))
;;=> #{{:composer "Giuseppe Verdi", :country "Italy"} {:composer "J. S. Bach", :country "Germany"} {:composer "W. A. Mozart", :country "Austria"})

(as-> composers x
     (clojure.set/join (select #(= (:name %) "Requiem") compositions) x)
     (project x [:country]))
;;=> #{{:country "Italy"} {:country "Austria"}}
