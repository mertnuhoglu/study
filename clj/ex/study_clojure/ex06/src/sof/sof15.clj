(ns sof.sof15)

; [How can I remove an item from a sequence in Clojure? - Stack Overflow](https://stackoverflow.com/questions/939278/how-can-i-remove-an-item-from-a-sequence-in-clojure)

(disj #{:foo :bar} :foo)       ; => #{:bar}
(dissoc {:foo 1 :bar 2} :foo)  ; => {:bar 2}
(pop [:bar :foo])              ; => [:bar]
(pop (list :foo :bar))         ; => (:bar)

;These also work (returning a seq):

(remove #{:foo} #{:foo :bar})      ; => (:bar)
(remove #{:foo} [:foo :bar])       ; => (:bar)
(remove #{:foo} (list :foo :bar))  ; => (:bar)

;This doesn't work for hash-maps because when you iterate over a map, you get key/value pairs. But this works:

(remove (fn [[k v]] (#{:foo} k)) {:foo 1 :bar 2})  ; => ([:bar 2])
