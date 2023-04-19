;; # Hiccup Examples

(ns hiccup
  (:require [nextjournal.clerk :as clerk]))

;; rfr: ~/prj/study/clj/ex/electric/electric-03/src/app/e01_html_elements.cljc
;;
;; - ### Form

(clerk/html [:div "<div>...</div>"])

;; - ### `id` attribute

(clerk/html [:div#e01 "<div id='e01'>..."])

;; - ### `class` attribute

(clerk/html [:div.c01 "<div class='c01'>..."])

;; - ### `id` and `class` attribute

(clerk/html [:div#e02.c02 "<div id='e02' class='c02'>..."])

;; - ### Multiple `class` attributes

(clerk/html [:div.c03.c04 "<div class='c03 c04'>..."])

;; - ### Tag empty

(clerk/html [:div "<div>"])
(clerk/html [:h1 "<h1>"])
(clerk/html [:script])
(clerk/html [:text "<text>"])
(clerk/html [:a "<a>"])
(clerk/html [:iframe])
(clerk/html [:title "title"])
(clerk/html [:select "<select>"])

;; - ### Void tags

(clerk/html [:br])

;; - ### Contents are concatenated

(clerk/html [:div "foo " "bar"])
(clerk/html [:div [:p "<p>"] [:br]])

;; - ### seqs are expanded

(clerk/html [:div (list "foo " "bar")])

;; - ### tags can contain tags

(clerk/html [:div [:p "<div><p></p></div>"]])

;; - ### tag attributes

(clerk/html [:div {} "blank attributes"])
(clerk/html [:div {:a "1" :b "2"} "<div a='1' b='2'>"])

