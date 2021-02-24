(ns conjure-log-74280)
; --------------------------------------------------------------------------------
; eval (current-form): (pp/print-table [{:a 1 :b 2 :c 3}{:a 4 :b 5 :c 6}])
; (out) 
; (out) | :a | :b | :c |
; (out) |----+----+----|
; (out) |  1 |  2 |  3 |
; (out) |  4 |  5 |  6 |
nil
; --------------------------------------------------------------------------------
; eval (current-form): (ns-publics 'clojure.repl)
{source-fn #'clojure.repl/source-fn,
 doc #'clojure.repl/doc,
 stack-element-str #'clojure.repl/stack-element-str,
 find-doc #'clojure.repl/find-doc,
 dir #'clojure.repl/dir,
 pst #'clojure.repl/pst,
 dir-fn #'clojure.repl/dir-fn,
 source #'clojure.repl/source,
 set-break-handler! #'clojure.repl/set-break-handler!,
 root-cause #'clojure.repl/root-cause,
 demunge #'clojure.repl/demunge,
 thread-stopper #'clojure.repl/thread-stopper,
 apropos #'clojure.repl/apropos}
; --------------------------------------------------------------------------------
; eval (current-form): (clojure.repl/source map)
; (out) (defn map
; (out)   "Returns a lazy sequence consisting of the result of applying f to
; (out)   the set of first items of each coll, followed by applying f to the
; (out)   set of second items in each coll, until any one of the colls is
; (out)   exhausted.  Any remaining items in other colls are ignored. Function
; (out)   f should accept number-of-colls arguments. Returns a transducer when
; (out)   no collection is provided."
; (out)   {:added "1.0"
; (out)    :static true}
; (out)   ([f]
; (out)     (fn [rf]
; (out)       (fn
; (out)         ([] (rf))
; (out)         ([result] (rf result))
; (out)         ([result input]
; (out)            (rf result (f input)))
; (out)         ([result input & inputs]
; (out)            (rf result (apply f input inputs))))))
; (out)   ([f coll]
; (out)    (lazy-seq
; (out)     (when-let [s (seq coll)]
; (out)       (if (chunked-seq? s)
; (out)         (let [c (chunk-first s)
; (out)               size (int (count c))
; (out)               b (chunk-buffer size)]
; (out)           (dotimes [i size]
; (out)               (chunk-append b (f (.nth c i))))
; (out)           (chunk-cons (chunk b) (map f (chunk-rest s))))
; (out)         (cons (f (first s)) (map f (rest s)))))))
; (out)   ([f c1 c2]
; (out)    (lazy-seq
; (out)     (let [s1 (seq c1) s2 (seq c2)]
; (out)       (when (and s1 s2)
; (out)         (cons (f (first s1) (first s2))
; (out)               (map f (rest s1) (rest s2)))))))
; (out)   ([f c1 c2 c3]
; (out)    (lazy-seq
; (out)     (let [s1 (seq c1) s2 (seq c2) s3 (seq c3)]
; (out)       (when (and  s1 s2 s3)
; (out)         (cons (f (first s1) (first s2) (first s3))
; (out)               (map f (rest s1) (rest s2) (rest s3)))))))
; (out)   ([f c1 c2 c3 & colls]
; (out)    (let [step (fn step [cs]
; (out)                  (lazy-seq
; (out)                   (let [ss (map seq cs)]
; (out)                     (when (every? identity ss)
; (out)                       (cons (map first ss) (step (map rest ss)))))))]
; (out)      (map #(apply f %) (step (conj colls c3 c2 c1))))))
nil
; --------------------------------------------------------------------------------
; eval (current-form): [clojure.repl]
; (err) Syntax error (ClassNotFoundException) compiling at (/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/help_functions.clj:0:0).
; (err) clojure.repl
; --------------------------------------------------------------------------------
; eval (current-form): (clojure.repl/dir clojure.string)
; (out) blank?
; (out) capitalize
; (out) ends-with?
; (out) escape
; (out) includes?
; (out) index-of
; (out) join
; (out) last-index-of
; (out) lower-case
; (out) re-quote-replacement
; (out) replace
; (out) replace-first
; (out) reverse
; (out) split
; (out) split-lines
; (out) starts-with?
; (out) trim
; (out) trim-newline
; (out) triml
; (out) trimr
; (out) upper-case
nil
; --------------------------------------------------------------------------------
; eval (current-form): (clojure.repl/dir user)
nil
; --------------------------------------------------------------------------------
; eval (current-form): (clojure.repl/dir 'user)
; (err) Execution error (ClassCastException) at help-functions/eval8190 (REPL:113).
; (err) clojure.lang.PersistentList cannot be cast to clojure.lang.Symbol
; --------------------------------------------------------------------------------
; eval (current-form): (clojure.repl/dir help_functions)
; (out) ns1
; (out) number-summary
nil
; --------------------------------------------------------------------------------
; eval (buf): /Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/help_functions.clj
nil
#object[javax.swing.JFrame 0x11703df1 "javax.swing.JFrame[frame1,0,25,400x600,invalid,layout=java.awt.BorderLayout,title=Clojure Inspector,resizable,normal,defaultCloseOperation=HIDE_ON_CLOSE,rootPane=javax.swing.JRootPane[,0,28,400x572,invalid,layout=javax.swing.JRootPane$RootLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=16777673,maximumSize=,minimumSize=,preferredSize=],rootPaneCheckingEnabled=true]"]
#'help_functions/number-summary
nil
[{:n 5,
  :proper-divisors #{1},
  :even? false,
  :prime? true,
  :perfect-number? false}
 {:n 6,
  :proper-divisors #{1 2 3},
  :even? true,
  :prime? false,
  :perfect-number? true}
 {:n 7,
  :proper-divisors #{1},
  :even? false,
  :prime? true,
  :perfect-number? false}
 {:n 12,
  :proper-divisors #{1 2 3 4 6},
  :even? true,
  :prime? false,
  :perfect-number? false}
 {:n 28,
  :proper-divisors #{1 2 4 7 14},
  :even? true,
  :prime? false,
  :perfect-number? true}
 {:n 42,
  :proper-divisors #{1 2 3 6 7 14 21},
  :even? true,
  :prime? false,
  :perfect-number? false}]
; (out) 
; (out) | :n | :proper-divisors | :even? | :prime? | :perfect-number? |
; (out) |----+------------------+--------+---------+------------------|
; (out) |  6 |         #{1 2 3} |   true |   false |             true |
; (out) | 12 |     #{1 2 3 4 6} |   true |   false |            false |
; (out) | 28 |    #{1 2 4 7 14} |   true |   false |             true |
nil
; (out) 
; (out) | :a | :b | :c |
; (out) |----+----+----|
; (out) |  1 |  2 |  3 |
; (out) |  4 |  5 |  6 |
nil
(#object[clojure.lang.Namespace 0x7ea3fa4b "nrepl.middleware.interruptible-eval"]
 #object[clojure.lang.Namespace 0x54f7c32d "cljfx.fx.parent"]
 #object[clojure.lang.Namespace 0x9ba61be "vlaaad.reveal.popup"]
 #object[clojure.lang.Namespace 0x633e250f "cljfx.fx.popup"]
 #object[clojure.lang.Namespace 0x20a3f61f "clojure.stacktrace"]
 #object[clojure.lang.Namespace 0x74cfa6e0 "nrepl.cmdline"]
 #object[clojure.lang.Namespace 0x2ea28e56 "vlaaad.reveal.layout"]
 #object[clojure.lang.Namespace 0x3c26d0d0 "clojure.inspector"]
 #object[clojure.lang.Namespace 0x30aca45 "clojure.test"]
 #object[clojure.lang.Namespace 0xafb9462 "vlaaad.reveal.ui"]
 #object[clojure.lang.Namespace 0x30917b55 "clojure.core.server"]
 #object[clojure.lang.Namespace 0x3a3b9dc7 "clojure.core.specs.alpha"]
 #object[clojure.lang.Namespace 0x6f363027 "cljfx.fx.stage"]
 #object[clojure.lang.Namespace 0x39b8f127 "nrepl.server"]
 #object[clojure.lang.Namespace 0x2c86e96 "vlaaad.reveal.search"]
 #object[clojure.lang.Namespace 0x647b1ead "vlaaad.reveal.canvas"]
 #object[clojure.lang.Namespace 0x3a92ad0d "cljfx.jdk.fx.window"]
 #object[clojure.lang.Namespace 0x445d52b1 "nrepl.middleware.session"]
 #object[clojure.lang.Namespace 0x5a02872 "conjure.internal"]
 #object[clojure.lang.Namespace 0x3992c01d "nrepl.middleware.caught"]
 #object[clojure.lang.Namespace 0x16c7f8e7 "cljfx.jdk.platform"]
 #object[clojure.lang.Namespace 0x3821f854 "clojure.spec.alpha"]
 #object[clojure.lang.Namespace 0x3c46b49a "nrepl.util.completion"]
 #object[clojure.lang.Namespace 0x137b0927 "clojure.set"]
 #object[clojure.lang.Namespace 0x3ad2db6f "cljfx.fx.control"]
 #object[clojure.lang.Namespace 0xd866739 "cljfx.fx.node"]
 #object[clojure.lang.Namespace 0x46df077 "nrepl.ack"]
 #object[clojure.lang.Namespace 0x4f14e3ec "clojure.string"]
 #object[clojure.lang.Namespace 0x63b150c2 "clojure.data.priority-map"]
 #object[clojure.lang.Namespace 0x5db04c22 "cljfx.fx.row-constraints"]
 #object[clojure.lang.Namespace 0x36b5f1ab "vlaaad.reveal.stream"]
 #object[clojure.lang.Namespace 0x5ed67f12 "clojure.repl"]
 #object[clojure.lang.Namespace 0x3708034a "cljfx.fx.scroll-pane"]
 #object[clojure.lang.Namespace 0x688698d5 "vlaaad.reveal.output-panel"]
 #object[clojure.lang.Namespace 0x712714c7 "clojure.template"]
 #object[clojure.lang.Namespace 0x1ce4db1e "cljfx.event-handler"]
 #object[clojure.lang.Namespace 0x6b01899b "nrepl.misc"]
 #object[clojure.lang.Namespace 0x379df41e "cljfx.css"]
 #object[clojure.lang.Namespace 0x7e554f06 "cljfx.jdk.fx.node"]
 #object[clojure.lang.Namespace 0x44d347f9 "clojure.core"]
 #object[clojure.lang.Namespace 0x23f953a9 "vlaaad.reveal.fx"]
 #object[clojure.lang.Namespace 0x7b9c6ecc "cljfx.defaults"]
 #object[clojure.lang.Namespace 0x2fe533c6 "cljfx.fx.column-constraints"]
 #object[clojure.lang.Namespace 0x71d7554c "clojure.walk"]
 #object[clojure.lang.Namespace 0x185a34c4 "vlaaad.reveal.view"]
 #object[clojure.lang.Namespace 0x28d30d "nrepl.middleware"]
 #object[clojure.lang.Namespace 0x655bbcd "clojure.spec.gen.alpha"]
 #object[clojure.lang.Namespace 0x4d63e37e "cljfx.fx.grid-pane"]
 #object[clojure.lang.Namespace 0x4561202 "cljfx.fx.stack-pane"]
 #object[clojure.lang.Namespace 0x4220193e "vlaaad.reveal.event"]
 #object[clojure.lang.Namespace 0x69805964 "vlaaad.reveal.font"]
 #object[clojure.lang.Namespace 0x3b2a2f39 "cljfx.fx.canvas"]
 #object[clojure.lang.Namespace 0xf58ec5c "vlaaad.reveal.action"]
 #object[clojure.lang.Namespace 0x4d73fd60 "cljfx.renderer"]
 #object[clojure.lang.Namespace 0x26d3c6d3 "clojure.uuid"]
 #object[clojure.lang.Namespace 0x4cea66d7 "cljfx.lifecycle"]
 #object[clojure.lang.Namespace 0x498491a "clojure.main"]
 #object[clojure.lang.Namespace 0x56d23c14 "user"]
 #object[clojure.lang.Namespace 0x6e0f8d43 "vlaaad.reveal.nrepl"]
 #object[clojure.lang.Namespace 0x714c828b "cljfx.fx.scene"]
 #object[clojure.lang.Namespace 0x37dad521 "nrepl.middleware.dynamic-loader"]
 #object[clojure.lang.Namespace 0x702f6d52 "vlaaad.reveal.doc"]
 #object[clojure.lang.Namespace 0x62589381 "clojure.edn"]
 #object[clojure.lang.Namespace 0x77951d9b "clojure.java.io"]
 #object[clojure.lang.Namespace 0x1687ad95 "cljfx.prop"]
 #object[clojure.lang.Namespace 0x1dc907ea "cljfx.api"]
 #object[clojure.lang.Namespace 0x3bab4b49 "cljfx.context"]
 #object[clojure.lang.Namespace 0x648df062 "clojure.core.protocols"]
 #object[clojure.lang.Namespace 0x4fcd1263 "cljfx.fx"]
 #object[clojure.lang.Namespace 0x1aeeaa27 "clojure.pprint"]
 #object[clojure.lang.Namespace 0x7b7e5b30 "nrepl.util.lookup"]
 #object[clojure.lang.Namespace 0x707bbadd "nrepl.bencode"]
 #object[clojure.lang.Namespace 0x5782e0bb "nrepl.middleware.load-file"]
 #object[clojure.lang.Namespace 0x3852d7fe "nrepl.version"]
 #object[clojure.lang.Namespace 0x317cbc1f "clojure.instant"]
 #object[clojure.lang.Namespace 0x1c7c45bf "vlaaad.reveal.lines"]
 #object[clojure.lang.Namespace 0x5c3be9b3 "cljfx.composite"]
 #object[clojure.lang.Namespace 0x72f828ed "nrepl.middleware.lookup"]
 #object[clojure.lang.Namespace 0x26b0fee9 "nrepl.transport"]
 #object[clojure.lang.Namespace 0x589f063d "nrepl.middleware.sideloader"]
 #object[clojure.lang.Namespace 0x5b6a1aec "help_functions"]
 #object[clojure.lang.Namespace 0x6866ee2c "vlaaad.reveal.nav"]
 #object[clojure.lang.Namespace 0x4900027f "cljfx.mutator"]
 #object[clojure.lang.Namespace 0x698e5b8c "clojure.datafy"]
 #object[clojure.lang.Namespace 0x6238812b "vlaaad.reveal.prefs"]
 #object[clojure.lang.Namespace 0x3df004ce "vlaaad.reveal.cursor"]
 #object[clojure.lang.Namespace 0x742aeaf5 "cljfx.platform"]
 #object[clojure.lang.Namespace 0x6fe39dc7 "cljfx.fx.popup-window"]
 #object[clojure.lang.Namespace 0x49f478c9 "cljfx.fx.window"]
 #object[clojure.lang.Namespace 0x749fd34d "cljfx.coerce"]
 #object[clojure.lang.Namespace 0x5741f269 "cljfx.fx.pane"]
 #object[clojure.lang.Namespace 0x243f50e3 "nrepl.core"]
 #object[clojure.lang.Namespace 0x4ee7569d "nrepl.middleware.completion"]
 #object[clojure.lang.Namespace 0x44e8f1b7 "vlaaad.reveal.style"]
 #object[clojure.lang.Namespace 0x1c6281f9 "nrepl.config"]
 #object[clojure.lang.Namespace 0x420462de "clojure.core.cache"]
 #object[clojure.lang.Namespace 0x36a387bd "nrepl.middleware.print"]
 #object[clojure.lang.Namespace 0x3545bd97 "cljfx.component"]
 #object[clojure.lang.Namespace 0x3da09f8f "cljfx.fx.region"])
nil
{default-streams-impl #'clojure.java.io/default-streams-impl,
 make-output-stream #'clojure.java.io/make-output-stream,
 make-parents #'clojure.java.io/make-parents,
 delete-file #'clojure.java.io/delete-file,
 input-stream #'clojure.java.io/input-stream,
 make-writer #'clojure.java.io/make-writer,
 as-relative-path #'clojure.java.io/as-relative-path,
 copy #'clojure.java.io/copy,
 as-file #'clojure.java.io/as-file,
 output-stream #'clojure.java.io/output-stream,
 make-reader #'clojure.java.io/make-reader,
 Coercions #'clojure.java.io/Coercions,
 file #'clojure.java.io/file,
 make-input-stream #'clojure.java.io/make-input-stream,
 IOFactory #'clojure.java.io/IOFactory,
 resource #'clojure.java.io/resource,
 writer #'clojure.java.io/writer,
 as-url #'clojure.java.io/as-url,
 reader #'clojure.java.io/reader}
nil
{source-fn #'clojure.repl/source-fn,
 doc #'clojure.repl/doc,
 stack-element-str #'clojure.repl/stack-element-str,
 find-doc #'clojure.repl/find-doc,
 dir #'clojure.repl/dir,
 pst #'clojure.repl/pst,
 dir-fn #'clojure.repl/dir-fn,
 source #'clojure.repl/source,
 set-break-handler! #'clojure.repl/set-break-handler!,
 root-cause #'clojure.repl/root-cause,
 demunge #'clojure.repl/demunge,
 thread-stopper #'clojure.repl/thread-stopper,
 apropos #'clojure.repl/apropos}
; (out) (defn map
; (out)   "Returns a lazy sequence consisting of the result of applying f to
; (out)   the set of first items of each coll, followed by applying f to the
; (out)   set of second items in each coll, until any one of the colls is
; (out)   exhausted.  Any remaining items in other colls are ignored. Function
; (out)   f should accept number-of-colls arguments. Returns a transducer when
; (out)   no collection is provided."
; (out)   {:added "1.0"
; (out)    :static true}
; (out)   ([f]
; (out)     (fn [rf]
; (out)       (fn
; (out)         ([] (rf))
; (out)         ([result] (rf result))
; (out)         ([result input]
; (out)            (rf result (f input)))
; (out)         ([result input & inputs]
; (out)            (rf result (apply f input inputs))))))
; (out)   ([f coll]
; (out)    (lazy-seq
; (out)     (when-let [s (seq coll)]
; (out)       (if (chunked-seq? s)
; (out)         (let [c (chunk-first s)
; (out)               size (int (count c))
; (out)               b (chunk-buffer size)]
; (out)           (dotimes [i size]
; (out)               (chunk-append b (f (.nth c i))))
; (out)           (chunk-cons (chunk b) (map f (chunk-rest s))))
; (out)         (cons (f (first s)) (map f (rest s)))))))
; (out)   ([f c1 c2]
; (out)    (lazy-seq
; (out)     (let [s1 (seq c1) s2 (seq c2)]
; (out)       (when (and s1 s2)
; (out)         (cons (f (first s1) (first s2))
; (out)               (map f (rest s1) (rest s2)))))))
; (out)   ([f c1 c2 c3]
; (out)    (lazy-seq
; (out)     (let [s1 (seq c1) s2 (seq c2) s3 (seq c3)]
; (out)       (when (and  s1 s2 s3)
; (out)         (cons (f (first s1) (first s2) (first s3))
; (out)               (map f (rest s1) (rest s2) (rest s3)))))))
; (out)   ([f c1 c2 c3 & colls]
; (out)    (let [step (fn step [cs]
; (out)                  (lazy-seq
; (out)                   (let [ss (map seq cs)]
; (out)                     (when (every? identity ss)
; (out)                       (cons (map first ss) (step (map rest ss)))))))]
; (out)      (map #(apply f %) (step (conj colls c3 c2 c1))))))
nil
; (out) -------------------------
; (out) clojure.core/map
; (out) ([f] [f coll] [f c1 c2] [f c1 c2 c3] [f c1 c2 c3 & colls])
; (out)   Returns a lazy sequence consisting of the result of applying f to
; (out)   the set of first items of each coll, followed by applying f to the
; (out)   set of second items in each coll, until any one of the colls is
; (out)   exhausted.  Any remaining items in other colls are ignored. Function
; (out)   f should accept number-of-colls arguments. Returns a transducer when
; (out)   no collection is provided.
nil
; (out) -------------------------
; (out) cljfx.api/create-app
; (out) ([*context & {:keys [event-handler desc-fn co-effects effects async-agent-options renderer-middleware renderer-error-handler], :or {co-effects {}, effects {}, async-agent-options {}, renderer-middleware identity, renderer-error-handler renderer/default-error-handler}}])
; (out)   Convenient starting point for apps with pure views, subscriptions and events
; (out) 
; (out)   Creates renderer that is mounted on `*context` containing context created by
; (out)   [[create-context]]
; (out) 
; (out)   Returns map with `:renderer` and `:handler` keys containing actual renderer and event
; (out)   handler
; (out) 
; (out)   Accepted options:
; (out)   - `:event-handler` (required) - map event handler that should be a pure function.
; (out)     received current context at `:fx/context` key, should return effects description,
; (out)     default available effects are `:context` to set a context to a new value and
; (out)     `:dispatch` to dispatch new event. Events are handled asynchronously.
; (out)   - `:desc-fn` (required) - function receiving context and returning view description
; (out)   - `:co-effects` (optional, default `{}`) - additional co-effects map as described in
; (out)     [[wrap-co-effects]]
; (out)   - `:effects` (optional, default `{}`) - additional effects map as described in
; (out)     [[wrap-effects]]
; (out)   - `:async-agent-options` (optional, default `{}`) - agent options as described in
; (out)     [[wrap-async]]
; (out)   - `:renderer-middleware` (optional, default `identity`) - additional renderer
; (out)     middleware, such as [[wrap-many]]
; (out)   - `:renderer-error-handler` (optional, prints Exception stack traces and re-throws
; (out)     Errors by default) - 1-argument function that will receive Throwables thrown during
; (out)     advancing
; (out) 
; (out)   Note that since events are handled using agents, you'll need to call
; (out)   [[clojure.core/shutdown-agents]] to gracefully stop JVM
; (out) -------------------------
; (out) cljfx.api/create-context
; (out) ([m] [m cache-factory])
; (out)   Create a memoizing context for a value
; (out) 
; (out)   Context should be treated as a black box with [[sub-val]]/[[sub-ctx]] as an interface
; (out)   to access context's content.
; (out) 
; (out)   [[sub-val]] subscribes to a function that receives current value in the context,
; (out)   should be fast like [[get]].
; (out)   [[sub-ctx]] subscribes to a function that receives context to subscribe to other
; (out)   functions, can be slow like [[sort]]
; (out) 
; (out)   Values returned by `sub-*` will be memoized in this context, resulting in cache lookups
; (out)   for subsequent `sub-*` calls on corresponding functions with same arguments.
; (out) 
; (out)   Cache will be reused on contexts derived by `swap-context` and `reset-context`
; (out)   to minimize recalculations. To make it efficient, all calls to `sub-*` by subscription
; (out)   functions are tracked, thus calling `sub-*` from subscription function is not allowed
; (out)   after that function returns. For example, all lazy sequences that may
; (out)   call `sub-*` during computing of their elements have to be realized.
; (out) -------------------------
; (out) cljfx.api
; (out)   Main API namespace for cljfx
; (out) 
; (out)   Requiring this namespace starts JavaFX runtime if it wasn't previously started
; (out) 
; (out)   Sections:
; (out)   - JavaFX-specific helpers and info:
; (out)     - [[initialized]] - keyword indicating whether JavaFX was initialized by cljfx or not
; (out)     - [[on-fx-thread]] - run code on fx thread
; (out)     - [[run-later]] - run code asynchronously on fx thread
; (out)   - low-level component interaction:
; (out)     - [[create-component]] - creates component from description
; (out)     - [[advance-component]] - changes previously created component to new description
; (out)     - [[delete-component]] - deletes previously created/advanced component
; (out)     - [[instance]] - get JavaFX object from component
; (out)   - extension lifecycles:
; (out)     - [[ext-instance-factory]] - manually create component instance
; (out)     - [[ext-on-instance-lifecycle]] - observe created/advanced/deleted instances
; (out)     - [[ext-let-refs]] - manage component lifecycles decoupled from component tree
; (out)     - [[ext-get-ref]] - use offscreen component instance introduced by [[ext-let-refs]],
; (out)       possibly in multiple places
; (out)     - [[ext-set-env]] - put values into environment
; (out)     - [[ext-get-env]] - get values from environment
; (out)     - [[ext-many]] - manage a vector of components
; (out)     - [[make-ext-with-props]] - create lifecycle that uses user-defined props
; (out)   - automatic component lifecycle:
; (out)     - [[keyword->lifecycle]] - component/renderer `:fx.opt/type->lifecycle` opt function
; (out)       that returns lifecycle of keyword fx-types
; (out)     - [[fn->lifecycle]] - component/renderer `:fx.opt/type->lifecycle` opt function that
; (out)       returns lifecycle of function fx-types
; (out)     - [[fn->lifecycle-with-context]] - renderer `:fx.opt/type->lifecycle` opt function
; (out)       that returns lifecycle of function fx-types that also receive context as argument.
; (out)       Used in conjunction with [[wrap-context-desc]]
; (out)     - [[wrap-map-desc]] - renderer middleware that changes received description
; (out)     - [[wrap-many]] - renderer middleware that allows multiple root descriptions instead
; (out)       of one
; (out)     - [[wrap-context-desc]] - renderer middleware that expects `context` as description
; (out)       and passes down the description tree, used with [[fn->lifecycle-with-context]]
; (out)     - [[create-renderer]] - create function that automatically handles component lifecycle
; (out)     - [[mount-renderer]] - watch `*ref` for updates and re-render component
; (out)     - [[unmount-renderer]] - stop watching `*ref` for updates and tear down component
; (out)   - context:
; (out)     - [[create-context]] - wrap map in a black box that memoizes function subscriptions to
; (out)       it
; (out)     - [[sub]] - extract value from a context and memoize it using key or function
; (out)     - [[swap-context]] - create new context using function that reuses existing cache
; (out)     - [[reset-context]] - derive new context that reuses existing cache
; (out)     - [[unbind-context]] - debug utility that releases context from dependency tracking
; (out)   - event handling:
; (out)     - [[wrap-co-effects]] - wrap event handler to replace input side effects (such as
; (out)       derefing app state) with pure functions
; (out)     - [[make-deref-co-effect]] - helper function that creates deref co-effect
; (out)     - [[wrap-effects]] - wrap event handler to replace output side effects (such as
; (out)       updating app state, performing http requests etc.) with pure functions
; (out)     - [[make-reset-effect]] - helper function that creates effect that resets atom
; (out)     - [[dispatch-effect]] - effect that allows dispatching another events
; (out)     - [[wrap-async]] - wrap event handler to perform event processing in background
; (out)   - combining it all together:
; (out)     - [[create-app]] - convenient function that combines all cljfx concepts to create
; (out)       applications with pure views, subscriptions and event handlers
nil
(cljfx.api/unmount-renderer)
(cljfx.api/wrap-map-desc
 cljfx.coerce/map->image-pattern
 cljfx.composite/observable-map
 cljfx.lifecycle/advance-prop-map
 cljfx.lifecycle/detached-prop-map
 cljfx.lifecycle/map-of
 cljfx.lifecycle/wrap-map-desc
 cljfx.mutator/observable-map
 clojure.core/*print-namespace-maps*
 clojure.core/Throwable->map
 clojure.core/amap
 clojure.core/array-map
 clojure.core/hash-map
 clojure.core/map
 clojure.core/map-entry?
 clojure.core/map-indexed
 clojure.core/map?
 clojure.core/mapcat
 clojure.core/mapv
 clojure.core/ns-map
 clojure.core/ns-unmap
 clojure.core/pmap
 clojure.core/proxy-mappings
 clojure.core/sorted-map
 clojure.core/sorted-map-by
 clojure.core/struct-map
 clojure.core/zipmap
 clojure.data.priority-map/priority-map
 clojure.data.priority-map/priority-map-by
 clojure.data.priority-map/priority-map-keyfn
 clojure.data.priority-map/priority-map-keyfn-by
 clojure.set/map-invert
 clojure.spec.alpha/map-of
 clojure.spec.alpha/map-spec-impl
 clojure.spec.gen.alpha/fmap
 clojure.spec.gen.alpha/hash-map
 clojure.spec.gen.alpha/map
 nrepl.server/map->Server
 vlaaad.reveal.event/map->MapEventHandler
 vlaaad.reveal.stream/map->AnnotatedValue)
#'help_functions/ns1
{:source clojure.repl/source,
 :doc clojure.repl/doc,
 :find-doc clojure.repl/find-doc,
 :apropos clojure.repl/apropos,
 :dir clojure.repl/dir}
#object[clojure.lang.Namespace 0x5b6a1aec "help_functions"]
{}
; (out) ns1
; (out) number-summary
nil
; (out) blank?
; (out) capitalize
; (out) ends-with?
; (out) escape
; (out) includes?
; (out) index-of
; (out) join
; (out) last-index-of
; (out) lower-case
; (out) re-quote-replacement
; (out) replace
; (out) replace-first
; (out) reverse
; (out) split
; (out) split-lines
; (out) starts-with?
; (out) trim
; (out) trim-newline
; (out) triml
; (out) trimr
; (out) upper-case
nil
; --------------------------------------------------------------------------------
; eval (current-form): (clojure.repl/dir help_functions)
; (out) ns1
; (out) number-summary
nil
; --------------------------------------------------------------------------------
; eval (current-form): (require '[clojure.repl])
nil
; --------------------------------------------------------------------------------
; eval (current-form): (clojure.repl/source map)
; (out) (defn map
; (out)   "Returns a lazy sequence consisting of the result of applying f to
; (out)   the set of first items of each coll, followed by applying f to the
; (out)   set of second items in each coll, until any one of the colls is
; (out)   exhausted.  Any remaining items in other colls are ignored. Function
; (out)   f should accept number-of-colls arguments. Returns a transducer when
; (out)   no collection is provided."
; (out)   {:added "1.0"
; (out)    :static true}
; (out)   ([f]
; (out)     (fn [rf]
; (out)       (fn
; (out)         ([] (rf))
; (out)         ([result] (rf result))
; (out)         ([result input]
; (out)            (rf result (f input)))
; (out)         ([result input & inputs]
; (out)            (rf result (apply f input inputs))))))
; (out)   ([f coll]
; (out)    (lazy-seq
; (out)     (when-let [s (seq coll)]
; (out)       (if (chunked-seq? s)
; (out)         (let [c (chunk-first s)
; (out)               size (int (count c))
; (out)               b (chunk-buffer size)]
; (out)           (dotimes [i size]
; (out)               (chunk-append b (f (.nth c i))))
; (out)           (chunk-cons (chunk b) (map f (chunk-rest s))))
; (out)         (cons (f (first s)) (map f (rest s)))))))
; (out)   ([f c1 c2]
; (out)    (lazy-seq
; (out)     (let [s1 (seq c1) s2 (seq c2)]
; (out)       (when (and s1 s2)
; (out)         (cons (f (first s1) (first s2))
; (out)               (map f (rest s1) (rest s2)))))))
; (out)   ([f c1 c2 c3]
; (out)    (lazy-seq
; (out)     (let [s1 (seq c1) s2 (seq c2) s3 (seq c3)]
; (out)       (when (and  s1 s2 s3)
; (out)         (cons (f (first s1) (first s2) (first s3))
; (out)               (map f (rest s1) (rest s2) (rest s3)))))))
; (out)   ([f c1 c2 c3 & colls]
; (out)    (let [step (fn step [cs]
; (out)                  (lazy-seq
; (out)                   (let [ss (map seq cs)]
; (out)                     (when (every? identity ss)
; (out)                       (cons (map first ss) (step (map rest ss)))))))]
; (out)      (map #(apply f %) (step (conj colls c3 c2 c1))))))
nil
; --------------------------------------------------------------------------------
; doc (word): ns-aliases
; -------------------------
; clojure.core/ns-aliases
; ([ns])
;   Returns a map of the aliases for the namespace.
; --------------------------------------------------------------------------------
; doc (word): all-ns
; -------------------------
; clojure.core/all-ns
; ([])
;   Returns a sequence of all namespaces.
; --------------------------------------------------------------------------------
; eval (current-form): (use 'clojure.contrib.trace)
; (err) Execution error (FileNotFoundException) at help-functions/eval8297 (REPL:120).
; (err) Could not locate clojure/contrib/trace__init.class, clojure/contrib/trace.clj or clojure/contrib/trace.cljc on classpath.
; --------------------------------------------------------------------------------
; eval (current-form): (use '[clojure.string :only (split)])
nil
; --------------------------------------------------------------------------------
; eval (current-form): (use 'clojure.contrib.trace)
; (err) Execution error (FileNotFoundException) at help-functions/eval8301 (REPL:120).
; (err) Could not locate clojure/contrib/trace__init.class, clojure/contrib/trace.clj or clojure/contrib/trace.cljc on classpath.
; --------------------------------------------------------------------------------
; eval (buf): ...rs/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/require_functions.clj
nil
nil
; --------------------------------------------------------------------------------
; localhost:52321 (disconnected)
; --------------------------------------------------------------------------------
; localhost:52784 (connected)
; --------------------------------------------------------------------------------
; Assumed session: Greyhound (Clojure)
; --------------------------------------------------------------------------------
; eval (buf): ...ojects/study/clj/ex/study_clojure/ex06/src/require_functions.clj
nil
nil
; --------------------------------------------------------------------------------
; eval (buf): ...projects/study/clj/ex/study_clojure/ex06/src/debug_functions.clj
nil
nil
; --------------------------------------------------------------------------------
; eval (current-form): (trace (* 2 3))
; (out) TRACE: 6
6
; --------------------------------------------------------------------------------
; doc (word): trace
; -------------------------
; clojure.tools.trace/trace
; ([value] [name value])
;   Sends name (optional) and value to the tracer function, then
; returns value. May be wrapped around any expression without
; affecting the result.
; --------------------------------------------------------------------------------
; eval (current-form): (defn fib[n] (if (< n 2) n (+ (fib (- n 1)) (fib (- n 2)))))
#'debug-functions/fib
; --------------------------------------------------------------------------------
; eval (current-form): (trace [fib] (fib 3))
; (out) TRACE [#object[debug_functions$fib 0x3a97a21c "debug_functions$fib@3a97a21c"]]: 2
2
; --------------------------------------------------------------------------------
; eval (current-form): (dotrace [fib] (fib 3))
; (err) Execution error (IllegalStateException) at debug-functions/eval8361 (REPL:9).
; (err) Can't dynamically bind non-dynamic var: debug-functions/fib
; --------------------------------------------------------------------------------
; eval (current-form): (defn ^:dynamic fib[n] (if (< n 2) n (+ (fib (- n 1)) (fib (- n 2)))))
#'debug-functions/fib
; --------------------------------------------------------------------------------
; eval (current-form): (fib 3)
2
; --------------------------------------------------------------------------------
; eval (current-form): (trace [fib] (fib 3))
; (out) TRACE [#object[debug_functions$fib 0x94d0e71 "debug_functions$fib@94d0e71"]]: 2
2
; --------------------------------------------------------------------------------
; eval (current-form): (ns-publics 'clojure.tools.trace)
{ThrowableRecompose #'clojure.tools.trace/ThrowableRecompose,
 trace-forms #'clojure.tools.trace/trace-forms,
 trace-compose-throwable #'clojure.tools.trace/trace-compose-throwable,
 deftrace #'clojure.tools.trace/deftrace,
 traced? #'clojure.tools.trace/traced?,
 trace-fn-call #'clojure.tools.trace/trace-fn-call,
 untrace-ns* #'clojure.tools.trace/untrace-ns*,
 untrace-var* #'clojure.tools.trace/untrace-var*,
 trace-ns #'clojure.tools.trace/trace-ns,
 trace-ns* #'clojure.tools.trace/trace-ns*,
 trace #'clojure.tools.trace/trace,
 dotrace #'clojure.tools.trace/dotrace,
 trace-form #'clojure.tools.trace/trace-form,
 traceable? #'clojure.tools.trace/traceable?,
 trace-special-form #'clojure.tools.trace/trace-special-form,
 trace-var* #'clojure.tools.trace/trace-var*,
 untrace-ns #'clojure.tools.trace/untrace-ns,
 trace-vars #'clojure.tools.trace/trace-vars,
 untrace-vars #'clojure.tools.trace/untrace-vars,
 clone-throwable #'clojure.tools.trace/clone-throwable}
; --------------------------------------------------------------------------------
; eval (current-form): (dotrace [fib] (fib 3))
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
2




















; --------------------------------------------------------------------------------
; localhost:52784 (disconnected)
; --------------------------------------------------------------------------------
; localhost:53922 (connected)
; --------------------------------------------------------------------------------
; Assumed session: Bull Terrier (Clojure)
; --------------------------------------------------------------------------------
; eval (buf): ...sers/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/debug_functions.clj
nil
nil
; (out) TRACE: 6
6
#'debug-functions/fib
; (out) TRACE [#object[debug_functions$fib 0x788e3fd5 "debug_functions$fib@788e3fd5"]]: 2
2
; (out) TRACE t8369: (fib 3)
; (out) TRACE t8370: | (fib 2)
; (out) TRACE t8371: | | (fib 1)
; (out) TRACE t8371: | | => 1
; (out) TRACE t8372: | | (fib 0)
; (out) TRACE t8372: | | => 0
; (out) TRACE t8370: | => 1
; (out) TRACE t8373: | (fib 1)
; (out) TRACE t8373: | => 1
; (out) TRACE t8369: => 2
2
; --------------------------------------------------------------------------------
; eval (current-form): (require 'spyscope.core)
; (err) WARNING: boolean? already refers to: #'clojure.core/boolean? in namespace: fipp.visit, being replaced by: #'fipp.visit/boolean?
nil
; --------------------------------------------------------------------------------
; eval (current-form): (take 20 (repeat #spy/p (+ 1 2 3)))
; (out) 6
(6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6)
; --------------------------------------------------------------------------------
; eval (current-form): (take 20 (repeat (+ 1 2 3)))
(6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6)
; --------------------------------------------------------------------------------
; eval (current-form): (repeat #spy/d (+ 1 2 3))
; (err) Execution error (ClassCastException) at puget.printer/cprint (printer.clj:645).
; (err) java.io.StringWriter cannot be cast to clojure.lang.Associative
; --------------------------------------------------------------------------------
; eval (current-form): (take 20 (repeat #spy/d (+ 1 2 3)))
; (err) Execution error (ClassCastException) at puget.printer/cprint (printer.clj:645).
; (err) java.io.StringWriter cannot be cast to clojure.lang.Associative
; --------------------------------------------------------------------------------
; eval (current-form): (take 20 (repeat #spy/d (+ 1 2 3)))
; (err) Execution error (ClassCastException) at puget.printer/cprint (printer.clj:645).
; (err) java.io.StringWriter cannot be cast to clojure.lang.Associative
; --------------------------------------------------------------------------------
; localhost:53922 (disconnected)
; --------------------------------------------------------------------------------
; localhost:54003 (connected)
; --------------------------------------------------------------------------------
; Assumed session: Lakeland Terrier (Clojure)
; --------------------------------------------------------------------------------
; eval (buf): ...glu/projects/study/clj/ex/study_clojure/ex06/src/debug_functions.clj
nil
nil
; (err) Syntax error reading source at (REPL:32:36).
; (err) Attempting to call unbound fn: #'spyscope.core/print-log
; --------------------------------------------------------------------------------
; eval (current-form): (ns debug-functions)
nil
; --------------------------------------------------------------------------------
; eval (current-form): (require '[clojure.tools.trace :refer [trace, dotrace]])
nil
; --------------------------------------------------------------------------------
; eval (current-form): (trace (* 2 3))
; (out) TRACE: 6
6
; --------------------------------------------------------------------------------
; eval (current-form): (defn ^:dynamic fib[n] (if (< n 2) n (+ (fib (- n 1)) (fib (- n 2)))...
#'debug-functions/fib
; --------------------------------------------------------------------------------
; eval (current-form): (trace [fib] (fib 3))
; (out) TRACE [#object[debug_functions$fib 0x5163a268 "debug_functions$fib@5163a268"]]: 2
2
; --------------------------------------------------------------------------------
; eval (current-form): (dotrace [fib] (fib 3))
; (out) TRACE t8362: (fib 3)
; (out) TRACE t8363: | (fib 2)
; (out) TRACE t8364: | | (fib 1)
; (out) TRACE t8364: | | => 1
; (out) TRACE t8365: | | (fib 0)
; (out) TRACE t8365: | | => 0
; (out) TRACE t8363: | => 1
; (out) TRACE t8366: | (fib 1)
; (out) TRACE t8366: | => 1
; (out) TRACE t8362: => 2
2
; --------------------------------------------------------------------------------
; eval (current-form): (require 'hashp.core)
nil
; --------------------------------------------------------------------------------
; eval (current-form): (take 3 (repeat #p (+ 1 2)))
; (out) #p[debug-functions/eval15048:44] (+ 1 2) => 3
(3 3 3)
; --------------------------------------------------------------------------------
; eval (current-form): (inc #p (* 2 #p (+ 3 #p (* 4 5))))
; (out) #p[debug-functions/eval15053:48] (* 4 5) => 20
; (out) #p[debug-functions/eval15053:48] (+ 3 (* 4 5)) => 23
; (out) #p[debug-functions/eval15053:48] (* 2 (+ 3 (* 4 5))) => 46
47
; --------------------------------------------------------------------------------
; eval (current-form): (macroexpand ; <1> '(defn hello [person] (str "hello " person)))
(def hello (clojure.core/fn ([person] (str "hello " person))))
; --------------------------------------------------------------------------------
; localhost:54003 (disconnected)
; --------------------------------------------------------------------------------
; localhost:60343 (connected)
; --------------------------------------------------------------------------------
; Assumed session: Khao Manee (Clojure)
; --------------------------------------------------------------------------------
; eval (current-form): (ns profilable)
nil
; --------------------------------------------------------------------------------
; eval (current-form): (defn ^:bench profile-me [ms] ; <1> (println "Crunching bits...
#'profilable/profile-me
; --------------------------------------------------------------------------------
; eval (buf): ...dmanipulatingfunctions/Functiondefinition/defnanddefn-/6.clj
nil
#'profilable/profile-me
#'profilable/dont-profile-me
nil
#'user/wrap
#'user/make-profilable
#'user/tagged-by
#'user/prepare-bench
; (out) Crunching bits for 500 ms
nil
nil
; (out) Crunching bits for 500 ms
; (out) "Elapsed time: 503.674784 msecs"
nil
; (out) not expecting profiling
nil
; --------------------------------------------------------------------------------
; eval (current-form): (require '[clojure.tools.trace :refer [trace, dotrace]])
; (err) Execution error (FileNotFoundException) at debug-functions/eval8176 (REPL:5).
; (err) Could not locate clojure/tools/trace__init.class, clojure/tools/trace.clj or clojure/tools/trace.cljc on classpath.
; --------------------------------------------------------------------------------
; eval (current-form): (:doc (meta #'hello))
; (err) Syntax error compiling var at (/Users/mertnuhoglu/codes/clj/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/defnanddefn-/7.clj:16:7).
; (err) Unable to resolve var: hello in this context
; --------------------------------------------------------------------------------
; eval (current-form): (defn hello "A function to say hello" ; <1> [person] (str "Hello " person))
#'profilable/hello
; --------------------------------------------------------------------------------
; eval (current-form): (clojure.repl/doc hello)
; (out) -------------------------
; (out) profilable/hello
; (out) ([person])
; (out)   A function to say hello
nil
; --------------------------------------------------------------------------------
; eval (current-form): (:doc (meta #'hello))
"A function to say hello"
; --------------------------------------------------------------------------------
; eval (current-form): (require '[clojure.test :refer [are]])
nil
; --------------------------------------------------------------------------------
; eval (current-form): (defn save! [item] {:pre [(are [x] x ; <1> (map? item) ; <2> (integer? (:mult ite...
#'profilable/save!
; --------------------------------------------------------------------------------
; eval (current-form): (save! {:mult "4" :width :single})
; (out) 
; (out) FAIL in () (NO_SOURCE_FILE:4)
; (out) expected: (integer? (:mult item))
; (out)   actual: (not (integer? "4"))
; (out) 
; (out) FAIL in () (NO_SOURCE_FILE:4)
; (out) expected: (#{:double :triple} (:width item))
; (out)   actual: nil
; (err) Execution error (AssertionError) at profilable/save! (REPL:3).
; (err) Assert failed: (are [x] x (map? item) (integer? (:mult item)) (#{:double :triple} (:width item)))
; --------------------------------------------------------------------------------
; doc (word): are
; -------------------------
; clojure.test/are
; ([argv expr & args])
; Macro
;   Checks multiple assertions with a template expression.
;   See clojure.template/do-template for an explanation of
;   templates.
; 
;   Example: (are [x y] (= x y)  
;                 2 (+ 1 1)
;                 4 (* 2 2))
;   Expands to: 
;            (do (is (= 2 (+ 1 1)))
;                (is (= 4 (* 2 2))))
; 
;   Note: This breaks some reporting features, such as line numbers.
