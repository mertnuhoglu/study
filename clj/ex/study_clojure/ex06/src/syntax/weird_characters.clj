(ns syntax.weird-characters)

; ## Weird Characters
;   id:: f75edafd-c289-4f43-b376-46d57a23bae1
;   doc:: Weird Characters  || ((373ea7e0-e7c8-4d75-a1ec-c664e8d7b5b6))
;
; Source: [Clojure - Reading Clojure Characters](https://clojure.org/guides/weird_characters)
;

; [ASCII Pronunciation Rules for Programmers](https://blog.codinghorror.com/ascii-pronunciation-rules-for-programmers/)
; Karakter isimleri burada

; rfr: video/20230222-mert-clj-egzersiz-48.mp4

; [Clojure - Reading Clojure Characters](https://clojure.org/guides/weird_characters#_character_literal)
; Backslash \ karakteri, character tipindeki objeleri tanımlamak için kullanılır
(str \h \i)
;"hi"


; # - Dispatch character
; #{} #() #""

; ## set literal #{}
(:a #{:a :b})
;=> :a

(#{:a :b} :a)
;=> :a

; ## Discard #_
[1 2 3 #_4 5]

; ## Regex #"..."
(re-matches #"^test$" "test")

; ## Anonymous function #(...)
#(println %)

(macroexpand `#(println %))
; (fn* [arg] (clojure.core/println arg))

; ## Var quote #'
(read-string "#'foo")
; #'foo
; (var foo)

(def nine 9)
(var nine)
; #'weird-characters/nine
(identity #'nine)
; #'weird-characters/nine

; ## Function literal syntax #()

((fn [x] (* (Math/random) x))   ; <1>
 (System/currentTimeMillis))   ; <2>

(#(* (Math/random) %)           ; <3>
  (System/currentTimeMillis))


; ## Symbolic values ##
(/ 1.0 0.0)
; ##Inf
(/ -1.0 0.0)
; ##-Inf
(Math/sqrt -1.0)
; ##NaN

; ## Tagged literals #inst #uuid #js
(type #inst "2024-03-19T12:30:25.925-00:00") ; java.util.Date
(read-string "#inst \"2024-03-19T12:30:25.925-00:00\"")  ; #inst "2024-03-19T12:30:25.925-00:00"

; -------------------------------------------------
; ## Metadata `^` `#^`
; -------------------------------------------------

(def ^{:debug true} five 5) ; #'weird-characters/five
(meta #'five)
; {:debug true,
;  :line 65,
;  :column 1,
;  :file
;  "/Users/mertnuhoglu/prj/study/clj/ex/study_clojure/ex06/src/weird_characters.clj",
;  :name five,
;  :ns #object[clojure.lang.Namespace 0xb7bc195 "weird-characters"]}

(def ^:debug five 5)  ; #'weird-characters/five
; bc single value
(meta #'five)
; {:debug true,
;  :line 75,
;  :column 1,
;  :file
;  "/Users/mertnuhoglu/prj/study/clj/ex/study_clojure/ex06/src/weird_characters.clj",
;  :name five,
;  :ns #object[clojure.lang.Namespace 0xb7bc195 "weird-characters"]}

(def ^Integer ^:debug ^:private five 5)
; stack shorthand notations

; -------------------------------------------------
; ## Namespace Map Syntax `#:` and `#::`
; -------------------------------------------------
;
; ex: equivalent:
#:person{:first "Han"}
{:person/first "Han"}

; ex: equivalent:
(keys {:user/a 1, :user/b 2})
(keys #::{:a 1, :b 2})

; ex: equivalent:
; (ns rebel.core (:require [rebel.person :as p]))
; #::p{:first "Han"}
; {:rebel.person/first "Han"}

; -------------------------------------------------
; -------------------------------------------------
