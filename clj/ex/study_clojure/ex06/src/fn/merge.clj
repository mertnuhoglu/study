(ns fn.merge)

; [merge - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/merge)

(merge {:a 1 :b 2 :c 3} {:b 9 :d 4})
;;=> {:d 4, :a 1, :b 9, :c 3}

(merge {:a 1} nil)   ;=> {:a 1}
(merge nil {:a 1})   ;=> {:a 1}
(merge nil nil)      ;=> nil

;; `merge` can be used to support the setting of default values
(merge {:foo "foo-default" :bar "bar-default"}
  {:foo "custom-value"})
;;=> {:foo "custom-value" :bar "bar-default"}

;; This is useful when a function has a number of options
;; with default values.
(defn baz [& options]
  (let [options (merge {:opt1 "default-1" :opt2 "default-2"}
                  (first options))]
    options))

(baz {:opt1 "custom-1" :opt3 "custom-3"})
;;=> {:opt3 "custom-3" :opt1 "custom-1 :opt2 "default-2"}

(comment
  (def options {:opt1 "custom-1" :opt3 "custom-3"})
  (first options)
  ;=> [:opt1 "custom-1"]
  ,)