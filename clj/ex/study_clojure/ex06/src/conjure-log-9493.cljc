; --------------------------------------------------------------------------------
; eval (current-form): (reduce + [1 2 3])
6
; --------------------------------------------------------------------------------
; eval (file): ...ects/study/clj/ex/study_clojure/ex06/src/clojure_ref_transducers.clj
#object[clojure_ref_transducers$eval16562$reify__16563 0x3ec8cc6d "clojure_ref_transducers$eval16562$reify__16563@3ec8cc6d"]
; --------------------------------------------------------------------------------
; eval (file): ...ects/study/clj/ex/study_clojure/ex06/src/clojure_ref_transducers.clj
#object[clojure_ref_transducers$eval16588$reify__16589 0x408e5dea "clojure_ref_transducers$eval16588$reify__16589@408e5dea"]
; --------------------------------------------------------------------------------
; eval (current-form): (reduce + 0 iter)
6
; --------------------------------------------------------------------------------
; eval (current-form): (def p {:name "James" :age 26})
#'core01/p
; --------------------------------------------------------------------------------
; eval (current-form): (update p :age inc)
{:name "James", :age 27}
; --------------------------------------------------------------------------------
; eval (current-form): (update p :age + 10)
{:name "James", :age 36}
; --------------------------------------------------------------------------------
; eval (current-form): (update p :age - 10)
{:name "James", :age 16}
; --------------------------------------------------------------------------------
; eval (current-form): [(type []) (class [])]
[clojure.lang.PersistentVector clojure.lang.PersistentVector]
; --------------------------------------------------------------------------------
; eval (file): /Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/core01.clj
; (out) ali
; (out) ali"ali"
; (out) "ali"[1 2 3]
; (out) [1 2 3]
; (out) [a :b "\n" \space "c"][a :b 
; (out)    c]Twice 15 = 30
; (out) Six times 15 = 90
nil
; --------------------------------------------------------------------------------
; eval (current-form): (with-redefs [type (constantly java.lang.String) class (constantly 10)] [(type []...
[java.lang.String 10]
; --------------------------------------------------------------------------------
; eval (current-form): (concat '(1 2) '(3 4))
(1 2 3 4)
; --------------------------------------------------------------------------------
; eval (current-form): (mapcat reverse [[1 2] [3 4]])
(2 1 4 3)
; --------------------------------------------------------------------------------
; eval (current-form): (assoc {} :a :v1 :b :v2)
{:a :v1, :b :v2}
; --------------------------------------------------------------------------------
; eval (current-form): (assoc [1 2 3] 0 10)
[10 2 3]
; --------------------------------------------------------------------------------
; eval (current-form): {:a :b}
{:a :b}
; --------------------------------------------------------------------------------
; eval (current-form): {:b}
; (err) Syntax error reading source at (REPL:359:18).
; (err) Map literal must contain an even number of forms
; --------------------------------------------------------------------------------
; eval (current-form): (some #{:a} #{:a :b})
:a
; --------------------------------------------------------------------------------
; eval (current-form): (some #{:a} #{:b})
nil
; --------------------------------------------------------------------------------
; eval (current-form): ((juxt :a :b) {:a 1 :b 2 :c 3 :d 4})
[1 2]
; --------------------------------------------------------------------------------
; eval (current-form): ((juxt identity name) :keyword)
[:keyword "keyword"]
; --------------------------------------------------------------------------------
; eval (current-form): (into {} (map (juxt identity name) [:a :b :c :d]))
{:a "a", :b "b", :c "c", :d "d"}
; --------------------------------------------------------------------------------
; eval (current-form): (def foo (map println [1 2 3]))
#'core01/foo
; --------------------------------------------------------------------------------
; eval (current-form): (def foo (doall (map println [1 2 3])))
; (out) 1
; (out) 2
; (out) 3
#'core01/foo
; --------------------------------------------------------------------------------
; eval (current-form): (doall (map println [1 2 3]))
; (out) 1
; (out) 2
; (out) 3
(nil nil nil)
