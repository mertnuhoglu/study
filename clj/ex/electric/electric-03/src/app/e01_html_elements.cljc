(ns app.e01-html-elements
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

; rfr: [[20230312-Electric--electric-03--e01--HTML-Elementleri]] <url:file:///~/gdrive/grsm/opal/docs-grsm/pages/20230312-Electric--electric-03--e01--HTML-Elementleri.md#r=g13930>

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
    (dom/code 
      (dom/text "
        (dom/h1 (dom/text \"h1 Heading\"))
        (dom/h2 (dom/text \"h2 Heading\"))
        (dom/h3 (dom/text \"h3 Heading\"))
        (dom/h4 (dom/text \"h4 Heading\"))
        (dom/h5 (dom/text \"h5 Heading\"))
        (dom/h6 (dom/text \"h6 Heading\"))"
        ,))

    ; Containers
    (dom/h2 (dom/text "Containers"))

    ; <p> <span> <pre>
    (dom/p (dom/text "GeeksforGeeks is a") 
      (dom/span 
        (dom/props {:style 
                    {:color :red
                     :font-weight :bolder}})
        (dom/text "computer science"))
      (dom/text " portal for") 
      (dom/span 
        (dom/props {:style {:background-color :lightgreen}})
        (dom/text " geeks"))
      (dom/pre (dom/text "        This\n        is    a pre tag.")))

    (dom/pre
      (dom/code 
        (dom/text "
    (dom/p (dom/text \"GeeksforGeeks is a\") 
      (dom/span 
        (dom/props {:style 
                    {:color :red
                    :font-weight :bolder}})
        (dom/text \"computer science\"))
      (dom/text \" portal for\") 
      (dom/span 
        (dom/props {:style {:background-color :lightgreen}})
        (dom/text \" geeks\"))
      (dom/pre (dom/text \"        This\n        is    a pre tag.\")))
                  " ,)))

    ; <pre> <code>
    (dom/pre 
      (dom/text "code tag: Displays code snippets.")
      (dom/code 
          (dom/text "
    #include<stdio.h>
    int main() {
        printf(\"Hello Geeks\");
    }
        ")))

    ; <a>
    (dom/p (dom/text "Click on the following link ") 
      (dom/a (dom/props {:href "https://www.geeksforgeeks.org"})
             (dom/text "GeeksforGeeks")))

    (dom/pre
      (dom/code 
        (dom/text "
    (dom/pre 
      (dom/text \"code tag: Displays code snippets.\")
      (dom/code 
          (dom/text \"
              #include<stdio.h>
              int main() {
                  printf(\"Hello Geeks\");
              }
        \")))
    (dom/p (dom/text \"Click on the following link \") 
      (dom/a (dom/props {:href \"https://www.geeksforgeeks.org\"})
              (dom/text \"GeeksforGeeks\")))
        " ,)))

    ; Sectioning Content
    (dom/h2 (dom/text "Sectioning Content"))

    ; <section>
    (dom/h3 (dom/text "<section>"))

    (dom/section
        (dom/p
          (dom/text "Content of section")))

    ; <nav>
    (dom/h3 (dom/text "<nav>"))

    (dom/nav
      (dom/a (dom/props {:href "https://www.geeksforgeeks.org"})
             (dom/text "GeeksforGeeks"))
      (dom/a (dom/props {:href "#"})
             (dom/text "Home")))

    ; Text formatting
    (dom/h2 (dom/text "Text formatting"))

    ; <b>
    (dom/h3 (dom/text "<b> bold"))

    (dom/p (dom/b (dom/text "This is bold text")))

    ; <i>
    (dom/h3 (dom/text "<i> italic"))

    (dom/p (dom/i (dom/text "This is italic text")))

    ; <em>
    (dom/h3 (dom/text "<em> emphasis"))

    (dom/div (dom/em (dom/text "This is 1st level emphasis text")
                     (dom/div (dom/em (dom/text "This is 2nd level emphasis text")))))

    ; Lists
    (dom/h2 (dom/text "Lists"))

    ; <ul>
    (dom/h3 (dom/text "<ul> unordered list"))

    (dom/ul 
      (dom/li (dom/text "item 1 unordered"))
      (dom/li (dom/text "item 2 unordered")))

    ; <ol>
    (dom/h3 (dom/text "<ol> unordered list"))

    (dom/ol 
      (dom/li (dom/text "item 1 ordered"))
      (dom/li (dom/text "item 2 ordered")))

    ; Table
    (dom/h2 (dom/text "Table"))

    ; <table>
    (dom/h3 (dom/text "<table> table <tr> table row <th> table header <td> table data"))

    (dom/table 
      (dom/tr 
        (dom/th (dom/text "th 1"))
        (dom/th (dom/text "th 2")))
      (dom/tr
        (dom/td (dom/text "td 1.1"))
        (dom/td (dom/text "td 1.2")))
      (dom/tr
        (dom/td (dom/text "td 2.1"))
        (dom/td (dom/text "td 2.2"))))

    ; Form
    (dom/h2 (dom/text "Form"))

    ; <form> <fieldset> <legend> <label> <select>
    (dom/form 
      (dom/fieldset 
        (dom/legend 
          (dom/text "form > fieldset > Legend"))

        (dom/p
          (dom/text "form > fieldset > label > select"))
        (dom/label
          (dom/text "Dropdown:")
          (dom/select (dom/props {:name "select>name"})
                      (dom/option
                        (dom/text "---option1---"))
                      (dom/option
                        (dom/text "---option2---"))
                      (dom/option
                        (dom/text "---option3---"))))

        (dom/p
          (dom/text "form > fieldset > label > input"))
        (dom/label
          (dom/text "TextField:")
          (dom/input (dom/props {:name "input > props > name"
                                 :placeholder "input > props > placeholder"})))

        (dom/p
          (dom/text "form > fieldset > label > input > :type = radio"))
        (dom/label
          (dom/input (dom/props {:type "radio"
                                 :name "gender"
                                 :value "male"}))
          (dom/text " Male"))
        (dom/label
          (dom/input (dom/props {:type "radio"
                                 :name "gender"
                                 :value "female"}))
          (dom/text " Female"))

        (dom/p
          (dom/text "form > fieldset > label > input > :list"))
        (dom/label
          (dom/text "Label4:"))
        (dom/input (dom/props {:list "lang"
                               :placeholder "datalist"}))

        (dom/datalist (dom/props {:id "lang"})
          (dom/option (dom/props {:value "java"}))
          (dom/option (dom/props {:value "react"})))

        (dom/p
          (dom/text "input > :type email"))
        (dom/label
          (dom/text "Email:")
          (dom/input (dom/props {:type "email"
                                 :name "email"})))

        (dom/p
          (dom/text "input > :type date"))
        (dom/label
          (dom/text "Birth date:")
          (dom/input (dom/props {:type "date"
                                 :name "birthDate"})))

        (dom/p
          (dom/text "textarea"))
        (dom/label
          (dom/text "Address")
          (dom/textarea (dom/props {:placeholder "<textarea>"
                                    :name "address"})))

        (dom/p
          (dom/text "Button type submit"))
        (dom/button (dom/props {:type "submit"})
          (dom/text "Submit"))

        (dom/p
          (dom/text "<progress>"))
        (dom/progress (dom/props {:value "57"
                                  :max "100"
                                  :placeholder "<progress>"})))


    ;end form
      ,)

    ; Multimedia: <img> <audio> <video>
    (dom/h2 (dom/text "Multimedia"))

    (dom/p (dom/text "<img> image"))
    (dom/img (dom/props {:src "https://media.istockphoto.com/id/1195743934/vector/cute-panda-character-vector-design.jpg?s=612x612&w=0&k=20&c=J3ht-bKADmsXvF6gFIleRtfJ6NGhXnfIsrwlsUF8w80="
                         :width "420"
                         :height "420"}))

    (dom/p (dom/text "<audio> sound"))
    (dom/audio (dom/props {:controls nil}))

    ; Footer
    (dom/h2 (dom/text "Footer"))

    (dom/footer
        (dom/article
          (dom/p
            (dom/text "Content of footer"))
          
          (dom/address
            (dom/text "Organization: Joker"))))

    ; end
    ,))
