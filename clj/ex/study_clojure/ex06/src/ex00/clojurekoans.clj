(ns ex00.clojurekoans)

;; Source: [ClojureScript Koans](http://clojurescriptkoans.com/)
;; [lazerwalker/clojurescript-koans: A ClojureScript adaptation of the Clojure koans that runs purely in-browser](https://github.com/lazerwalker/clojurescript-koans)

(require '[clojure.string :as str])

(str "a" "b")

(interpose ", " [ "a" "b"])

(apply str (interpose ", " [ "a" "b"]))

(str "First comes love, " "then comes marriage, " "then comes Clojure with the baby carriage")

((fn [[a b c]]
   (str "First comes " a))
 ["love" "marriage" "Clojure"])

((fn [[a b c]]
   (apply str
     (interpose
       ", "
       (list
         (str "First comes " a)
         (str "then comes " b)
         (str "then comes " b)))))
 ["love" "marriage" "Clojure"])

(let [[first-name last-name & aliases] (list "Rich" "Hickey" "The Clojurer" "Go Time" "Macro Killah")]
  first-name)

(let [[first-name last-name & aliases] (list "Rich" "Hickey" "The Clojurer" "Go Time" "Macro Killah")]
  aliases)

(let
  [[first-name last-name & aliases]
   (list "Rich" "Hickey" "The Clojurer" "Go Time" "Macro Killah")]
  (apply
    str
    (interpose
      " aka "
      (concat
        [first-name last-name]
        aliases))))

(partition 3 [:a :b :c :d :e])

(partition 3 5
  (range 13))
