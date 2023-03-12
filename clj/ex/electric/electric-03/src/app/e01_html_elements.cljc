(ns app.e01-html-elements
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

; First demonstration of client/server transfer:
; a full stack function with both frontend and backend parts,
; all defined in the same expression

(e/defn HtmlElements []
  (e/client
    (dom/h1 (dom/text "Html Elements in Electric"))
    (dom/div
      (dom/text "Examples from: ")
      (dom/a
        (dom/props {:href "https://www.codecademy.com/learn/learn-html/modules/learn-html-elements/cheatsheet"})
        (dom/text "Codecademy"))
      (dom/a
        (dom/props {:href "https://www.geeksforgeeks.org/html-cheat-sheet-a-basic-guide-to-html/"})
        (dom/text "geeksforgeeks")))
    (dom/h2 (dom/text "Headings"))
      
    (dom/h1 (dom/text "h1 Heading"))
    (dom/h2 (dom/text "h2 Heading"))
    (dom/h3 (dom/text "h3 Heading"))
    (dom/h4 (dom/text "h4 Heading"))
    (dom/h5 (dom/text "h5 Heading"))
    (dom/h6 (dom/text "h6 Heading"))

    ; multiline string id=g13917
    (dom/code (dom/text "
      (dom/h1 (dom/text \"h1 Heading\"))\n
      (dom/h2 (dom/text \"h2 Heading\"))\r
      (dom/h3 (dom/text \"h3 Heading\"))\r\n
      (dom/h4 (dom/text \"h4 Heading\"))
      (dom/h5 (dom/text \"h5 Heading\"))
      (dom/h6 (dom/text \"h6 Heading\"))
                        "
                        ,))

    (dom/div (dom/text "Hello from client, where JS number type is: ")
      (dom/code (dom/text (e/client (pr-str (type 1))))))))
