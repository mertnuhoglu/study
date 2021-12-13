(ns map)

; map using reduce

(defn my-map [func inp]
      (reduce (fn [vect number
                            (concat vect [(func number)])
                        ()
                        inp])))

(my-map inc [1 2 3])
      ; => (2 3 4)

