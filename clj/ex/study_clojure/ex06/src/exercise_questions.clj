(ns exercise-questions)(ns exercise-questions)

(comment

  (def input ["the" "quick" "brown" "fox"])
  ;;=> ("<p>the</p>" "<p>quick</p>" "<p>brown</p>" "<p>fox</p>")

  (identity (for [word input])
    (format "<p>%s</p>" word))

  (def input [{:grade 83} {:grade 77} {:grade 90}])
  ;;=> ({:grade 90} {:grade 83} {:grade 77})

  (identity 
    (sort-by :grade > input))

  (def input [{:grade 83} {:grade 77} {:grade 90}])

  (identity 
    (sort-by :grade > input))

  (def input [42 1 7])
  ;;=> (42 7 1)

  (identity 
    (sort > [42 1 7]))

  (def input [42 1 7])
  ;;=> (1 42 7)

  (identity
    (sort-by #(.toString %) [42 1 7]))

  (def input (range 1 11))
  ;;=> 55

  (identity
    (reduce + (range 1 11)))

  ,)

(comment

  ;; opt01: input - output

  ;; ["the" "quick" "brown" "fox"]
  ;;=> ("<p>the</p>" "<p>quick</p>" "<p>brown</p>" "<p>fox</p>")
  (for [word ["the" "quick" "brown" "fox"]]
    (format "<p>%s</p>" word))

  ;; [{:grade 83} {:grade 77} {:grade 90}]
  ;;=> ({:grade 90} {:grade 83} {:grade 77})
  (sort-by :grade > [{:grade 83} {:grade 77} {:grade 90}])

  ;; opt02: input given as a var + solution in identity for folding with cljfold in vim

  (def input [{:grade 83} {:grade 77} {:grade 90}])

  (identity 
    (sort-by :grade > input))

  ,)

