(ns ex00.debug-functions)

; Good methods:
; hashp for tracing <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/debug_functions.clj#r=g11988>
; cider debugging example <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/debug_functions.clj#r=g11970>

(comment 
  ; hashp for tracing id=g11988
  ;   id:: 07e840dd-b1c4-4e4e-8e09-f166315813ea
  ; [Clojure observability and debugging tools](http://www.futurile.net/2020/05/16/clojure-observability-and-debugging-tools/)

  (require 'hashp.core)

  (take 3 (repeat #p (+ 1 2)))
  ; (out) #p[debug-functions/eval15048:44] (+ 1 2) => 3
  ;;=> (3 3 3)

  (inc #p (* 2 #p (+ 3 #p (* 4 5))))
  ; eval (current-form): (inc #p (* 2 #p (+ 3 #p (* 4 5))))
  ; (out) #p[debug-functions/eval15053:48] (* 4 5) => 20
  ; (out) #p[debug-functions/eval15053:48] (+ 3 (* 4 5)) => 23
  ; (out) #p[debug-functions/eval15053:48] (* 2 (+ 3 (* 4 5))) => 46
  ;;=> 47

  ; ref_ex: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/fnil/6.clj`

  ,)

(comment 
  ; clj-inspector for tracing 

  (require 'inspector.core)
  ; (err) Execution error (FileNotFoundException) at debug-functions/eval8159 (REPL:31).

  (take 3 (repeat #p (+ 1 2)))
  ; (out) #p[debug-functions/eval15048:44] (+ 1 2) => 3
  ;;=> (3 3 3)

  (inc #p (* 2 #p (+ 3 #p (* 4 5))))

  ,)

(comment

  ; @mine: Use hashp instead of dotrace
  ; #p allows to mark what you want to trace precisely

  (require '[clojure.tools.trace :refer [trace, dotrace]])

  (trace (* 2 3))

  (defn ^:dynamic fib[n] (if (< n 2) n (+ (fib (- n 1)) (fib (- n 2)))))

  (trace [fib] (fib 3))

  #_(ns-publics 'clojure.tools.trace)

  (dotrace [fib] (fib 3)) 
  ; (out) TRACE t8378: (fib 3)
  ; (out) TRACE t8379: | (fib 2)
  ; (out) TRACE t8380: | | (fib 1)
  ; (out) TRACE t8380: | | => 1
  ; (out) TRACE t8381: | | (fib 0)
  ; (out) TRACE t8381: | | => 0
  ; (out) TRACE t8379: | => 1
  ; (out) TRACE t8382: | (fib 1)
  ; (out) TRACE t8382: | => 1
  ; (out) TRACE t8378: => 2

  ; ref_ex: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/fnil/6.clj`

  ,)
 

(comment
  (require 'spyscope.core)
  (take 20 (repeat #spy/p (+ 1 2 3)))
  ; (out) 6
  ;=> (6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6)
  (take 20 (repeat (+ 1 2 3)))
  ;=> (6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6)

  #_(take 20 (repeat #spy/d (+ 1 2 3)))

  ,)

(comment
  ; cider debugging example id=g11970
  ;   id:: 12cc3a35-1a81-42d8-af77-1927e2251174
  ; [Cider Debugger - YouTube](https://www.youtube.com/watch?v=jHCch3-Yuac)

  ; 01: put break point here: `,db`
  (defn pythagoras [x y]
    (let [x2 (* x 2)
          y2 (* y 2)]
      (Math/sqrt (+ x2 y2))))

  ; 02: run this function: `,ef`
  (pythagoras 3 4)

  ; example 2:
  (def sample-person
    {:person_id         1234567
     :person_name       "John Doe"
     :image             {:url "http://focus.on/me.jpg"
                         :preview "http://corporate.com/me.png"}
     :person_short_name "John"})

  (def cleanup                                      ; <1>
    {:person_id     [:id str]
     :person_name   [:name (memfn toLowerCase)]
     :image         [:avatar :url]})

  (defn transform [orig mapping]
    (apply merge
      (map (fn [[k [k' f]]] {k' (f (k orig))})      ; <2>
        mapping)))

  ; debugger: locals:
  ;k' = :id
  ;mapping = { :person_id [ :id clojure.core$str@63ebeb1f ], :person_name [ :name user$fn__7845@3cbba7c8 ], :image [ :avatar :url]}
  ;k = :person_id
  ;orig = { :person_id 1234567, :person_name "John Doe", :image { :url "http://focus.on/me.jpg", :preview "http://corporate.com/me.png" }, :person_short_name "John"}
  ;f = clojure.core$str@63ebeb1f

  (transform sample-person cleanup)

  ; ex03:
  ; `,db`
  (defn debugtest2 [a]
    (+ 1 a))
  (debugtest2 3)

  ; note: `#break`
  (defn debugtest3 [a]
    #break
    (+ 1 a))
  (debugtest3 3)

  ; note: `#dbg`
  (defn debugtest4 [a]
    #dbg
    (+ 1 a))
  (debugtest4 3)

  ; `M-x cider-browse-instrumented-defs` 
  ;=> debug-functions
  ;=> debugtest2
  ,)
