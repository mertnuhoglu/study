(ns ex00.rtc_clojure_in_nutshell_james_trunk)

;; [Clojure in a nutshell by James Trunk - YouTube](https://www.youtube.com/watch?v=C-kF25fWTO8)

;; Codes from Clojure in a nutshell by James Trunk - YouTube id=g13272

(def book (slurp "https://www.gutenberg.org/files/2701/2701-0.txt"))

(def words (re-seq #"[\w|']+" book))

(count words) ; 222719

(take 5 words) ; ("The" "Project" "Gutenberg" "eBook" "of")

; 5 most frequently used words

(def common-words #{"the" "of" "to" "and" "a" "in" "is" "it" "you" "that" "he" "was" "for" "on" "are" "with" "as" "i" "his" "they" "be" "at" "one" "have" "this" "from" "or" "had" "by" "not" "word" "but" "what" "some" "we" "can" "out" "other" "were" "all" "there" "when" "up" "use" "your" "how" "said" "an" "each" "she" "which" "do" "their" "time" "if" "will" "way" "about" "many" "then" "them" "write" "would" "like" "so" "these" "her"})

(->> words
     (map clojure.string/lower-case)
     (remove common-words)
     (frequencies)
     (sort-by val)
     (take-last 5))   
; (["me" 629] ["now" 786] ["him" 1065] ["whale" 1229] ["s" 1817])

; (remove common-words) nasıl çalıştı?

; set'ler aynı zamanda birer fonksiyondur
(#{1 2 3} 1) ; 1
(#{1 2 3} 4)  ; nil

; eğer non-nil dönerse, truty döner. bu da remove tarafından sonuç veriden silinir.

; 10 longest words

(->> words
     (distinct)
     (sort-by count)
     (take-last 10))
; ("cannibalistically"
;  "circumnavigations"
;  "superstitiousness"
;  "comprehensiveness"
;  "preternaturalness"
;  "indispensableness"
;  "uncompromisedness"
;  "subterraneousness"
;  "characteristically"
;  "uninterpenetratingly")

(->> words
     (distinct)
     (sort-by count)
     (take-last 10)
     (group-by count))
; {17
;  ["cannibalistically"
;   "circumnavigations"
;   "superstitiousness"
;   "comprehensiveness"
;   "preternaturalness"
;   "indispensableness"
;   "uncompromisedness"
;   "subterraneousness"],
;  18 ["characteristically"],
;  20 ["uninterpenetratingly"]}

; longest palindrome

(defn palindrome? [coll] 
  (= (seq coll) (reverse coll)))

(palindrome? [1 2 3 2]) ; false
(palindrome? [1 2 3 2 1]) ; true
(palindrome? "racecar")  ; true

(->> words
     (distinct)
     (filter palindrome?)
     (sort-by count)
     (last)) ; "deified"
