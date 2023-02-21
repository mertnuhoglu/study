(ns fn.postwalk)

; rfr: video/20230217-mert-clj-egzersiz-41.mp4

; (postwalk f form)
; Performs a depth-first, post-order traversal of form.  Calls f on
; each sub-form, uses f's return value in place of the original.
; Recognizes all Clojure data structures. Consumes seqs as with doall.
;

; verilen bir veri yapısının tüm öğelerini dolaşıp f fonksiyonunu bu öğeye uygular
; sonuçları biriktirir ve aynı şekildeki veri yapısında geri döner

(require '[clojure.walk :refer [postwalk]])

; [postwalk - clojure.walk | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.walk/postwalk)

;;example of removing namespaces from all keys in a nested data structure
(def m {:page/tags [{:tag/category "foo"}]})

(postwalk
  #(if
     (keyword? %)
     (keyword (name %))
     %)
  m)
;=> {:tags [{:category "foo"}]}
; keywordlerin namespace'leri silinmiş bir şekilde aynı map objesini bize döndürdü

(comment
  ; #terim: MapEntry = key-value pair = anahtar-değer ikilisi
  ,)

