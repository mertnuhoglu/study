(ns sof.sof28)

; [clojure - When to use `zipmap` and when `map vector`? - Stack Overflow](https://stackoverflow.com/questions/6135764/when-to-use-zipmap-and-when-map-vector)

#_(for [pair (map vector v (rest v))]
    ( ...)) ;do with (first pair) and (last pair)

#_(for [pair (zipmap v (rest v))]
    ( ...)) ;do with (first pair) and (last pair)

; a01: zipmap for hashmap, map vector to merge seqs

;Use (zipmap ...) when you want to directly construct a hashmap from separate sequences of keys and values. The output is a hashmap:

(zipmap [:k1 :k2 :k3] [10 20 40])
;=> {:k3 40, :k2 20, :k1 10}

; şuna denk:
(into {} (map vector  [:k1 :k2 :k3] [10 20 40]))
; is quite a convoluted way to do zipmap

;Use (map vector ...) when you are trying to merge multiple sequences. The output is a lazy sequence of vectors:

(map vector [1 2 3] [4 5 6] [7 8 9])
;=> ([1 4 7] [2 5 8] [3 6 9])

; a02:

;Another use of zipmap which I use all the time is to create a map out of a vector of maps.. Consider
;where data is a vector of maps, each of which contains a unique :id key/value pair. –
(def data
  [{:id 101 :b 2}
   {:id 102 :b 20}])
(zipmap (map :id data) data)
;=> {101 {:id 101, :b 2}, 102 {:id 102, :b 20}}

; fulcro tipi veritabanı yapılarında kullanılabilir

; a03:

; zipmap creates a map
; (map vector ...) creates a LazySeq of n-tuples (vectors of size n)
; zipmap içinde duplike keyler olmaz

(zipmap [:k1 :k2 :k3 :k1] [1 2 3 4])
;{:k3 3, :k2 2, :k1 4}

(map vector [:k1 :k2 :k3 :k1] [1 2 3 4])
;([:k1 1] [:k2 2] [:k3 3] [:k1 4])

