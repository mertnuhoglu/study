;; # Hiccup Form Examples

(ns hiccup02-form
  (:require [nextjournal.clerk :as clerk]))

;; rfr: ~/prj/study/clj/ex/electric/electric-03/src/app/e01_html_elements.cljc
;;
;; - ### Form

; <form> <fieldset> <legend> <label> <select>
;
(clerk/html [:form
             [:fieldset
              [:legend "form > fieldset > legend"]
              [:p "select:"]
              [:label "Dropdown: "
               [:select {:name "input01"}
                [:option "option1"]
                [:option "option2"]]]
              [:p "input:"]
              [:label "TextField:"
               [:input {:placeholder "input" :name "input02"}]]
              [:p "input:"]
              [:label "Radio"
               [:input {:type "radio"
                        :name "input03"
                        :value "male"}]
               [:input {:type "radio"
                        :name "input04"
                        :value "female"}]]
              [:p "input:"]
              [:label "Checkbox"
               [:input {:type "checkbox"
                        :name "input05"
                        :value "work"}]
               [:input {:type "checkbox"
                        :name "input06"
                        :value "home"}]]
              [:p "input:"]
              [:label "List"
               [:input {:list "lang"}]
               [:datalist.lang
                [:option {:value "java"}]
                [:option {:value "react"}]]]]])



