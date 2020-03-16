--- 
title: "Book Notes: Clojure Cookbook - Van der Hart"
date: 2020-03-15T20:58:14+03:00 
draft: false
description: ""
tags:
categories: clojure
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Book: Clojure Cookbook - Van der Hart

Ref

		https://github.com/clojure-cookbook/clojure-cookbook
		~/codes/clojure/clojure-cookbook/README.md

## Chapter 01: Primitive Data

### Capitalization of a String

``` clojure
(clojure.string/capitalize "this is a proper sentence.")
;; -> "This is a proper sentence."
(clojure.string/upper-case "loud noises!") 
;; -> "LOUD NOISES!"
(clojure.string/lower-case "COLUMN_HEADER_ONE") 
;; -> "column_header_one"
``` 

### Clean Whitespace in a String

``` clojure
(clojure.string/trim " \tBacon ipsum dolor sit.\n") 
;; -> "Bacon ipsum dolor sit."

;; Collapse whitespace into a single space
(clojure.string/replace "Who\t\nput all this\fwhitespace here?" #"\s+" " ") 
;; -> "Who put all this whitespace here?"
    

;; Replace Windows-style line endings with Unix-style newlines
(clojure.string/replace "Line 1\r\nLine 2" "\r\n" "\n") 
;; -> "Line 1\nLine 2"
``` 

shorter:

``` clojure
(require '[clojure.string :as str])
``` 

### Combine/Join a String

``` clojure
(str "John" " " "Doe")
;; -> "John Doe"

(def lines ["#! /bin/bash\n", "du -a ./ | sort -n -r\n"]) 
(apply str lines)
;; -> "#! /bin/bash\ndu -a ./ | sort -n -r\n"
``` 

Better: `join`

``` clojure
(def food-items ["milk" "butter" "flour" "eggs"]) (clojure.string/join ", " food-items)
;; -> "milk, butter, flour, eggs"
(clojure.string/join [1 2 3 4]) ;; -> "1234"
``` 

Worse alternative: `str` and `interpose`

``` clojure
;; Constructing a CSV from a header string and vector of rows
(def header "first_name,last_name,employee_number\n") (def rows ["luke,vanderhart,1","ryan,neufeld,2"])
(apply str header (interpose "\n" rows))
;; -> "first_name,last_name,employee_number\nluke,vanderhart,1\nryan,neufeld,2"

``` 

### String to Character

``` clojure
(seq "ali")
  ##> (\a \l \i)
``` 

Any function taking a seq converts a string into characters.

``` clojure
(frequencies (clojure.string/lower-case "ali veli"))
  ##> {\a 1, \l 2, \i 2, \space 1, \v 1, \e 1}
``` 

``` clojure
;; Is every letter in a string capitalized?
(defn yelling? [s]
(every? #(or (not (Character/isLetter %))
                   (Character/isUpperCase %))
            s))
(yelling? "LOUD NOISES!") ;; -> true
(yelling? "Take a DEEP breath.") ;; -> false
``` 

### Character to/from Integer

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-05_integer-to-character-conversions.asciidoc`

``` clojure
(int \a)
;; -> 97
(map int "Hello, world!")
;; -> (72 101 108 108 111 44 32 119 111 114 108 100 33)
``` 

``` clojure
(char 97)
;; -> \a
``` 

### Formatting Strings

`str`

``` clojure
(def me {:first-name "Ryan", :favorite-language "Clojure"})
(str "My name is " (:first-name me)
     ", and I really like to program in " (:favorite-language me))
;; -> "My name is Ryan, and I really like to program in Clojure"

(apply str (interpose " " [1 2.000 (/ 3 1) (/ 4 9)]))
;; -> "1 2.0 3 4/9"
``` 

`format`

`%03d`: pad a digit with three zeros

``` clojure
(defn filename [name i]
  (format "%03d-%s" i name)) ; <1>

(filename "my-awesome-file.txt" 42)
;; -> "042-my-awesome-file.txt"
``` 

`%-20s`: left justify string. total width of 20 chars.

``` clojure
;; Create a table using justification
(defn tableify [row]
  (apply format "%-20s | %-20s | %-20s" row)) ; <2>

(def header ["First Name", "Last Name", "Employee ID"])
(def employees [["Ryan", "Neufeld", 2]
                ["Luke", "Vanderhart", 1]])

(->> (concat [header] employees)
     (map tableify)
     (mapv println))
;; *out*
;; First Name           | Last Name            | Employee ID
;; Ryan                 | Neufeld              | 2
;; Luke                 | Vanderhart           | 1
``` 

### Regex Match

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-07_regexp-matching.asciidoc`

`re-find`

``` clojure
(re-find #"\d+" "I've just finished reading Fahrenheit 451")
;; -> "451"
``` 

`re-matches` matches complete string

``` clojure
(re-matches #"\w+" "my-param")
;; -> nil
``` 

### Regex Match: successive matches

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-08_matching-strings.asciidoc`

`re-seq`

``` clojure
(re-seq #"\w+" "My Favorite Things")
;; -> ("My" "Favorite" "Things")
``` 

Matching groups (parantheses): return a vector for each match

``` clojure
(defn mentions [tweet]
  (re-seq #"(@|#)(\w+)" tweet))

(mentions "So long, @earth, and thanks for all the #fish. #goodbyes")
;; -> (["@earth" "@" "earth"] ["#fish" "#" "fish"] ["#goodbyes" "#" "goodbyes"])
``` 

### Regex Replace

``` clojure
(def about-me "My favorite color is green!")
(clojure.string/replace about-me "green" "red")
;; -> "My favorite color is red!"
``` 

### Regex Split

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-10_tokenizing-strings.asciidoc`

``` clojure
(clojure.string/split "HEADER1,HEADER2,HEADER3" #",")
;; -> ["HEADER1" "HEADER2" "HEADER3"]
``` 

### Pluralizing Strings

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-11_inflecting-strings.asciidoc`

``` clojure
(require '[inflections.core :as inf])

(inf/pluralize 1 "monkey")
;; -> "1 monkey"

(inf/pluralize 12 "monkey")
;; -> "12 monkeys"
``` 

### Converting Between Strings, Symbols, and Keywords

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-12_converting-stringlike-data.asciidoc`

string to symbol

``` clojure
(symbol "valid?")
;; -> valid?
``` 

symbol to string

``` clojure
(str 'valid?)
;; -> "valid?"
``` 

keyword to string

``` clojure
(name :triumph)
;; -> "triumph"

;; Or, to include the leading colon:
(str :triumph)
;; -> ":triumph"
``` 

symbol/string to keyword

``` clojure
(keyword "fantastic")
;; -> :fantastic

(keyword 'fantastic)
;; -> :fantastic
``` 

keyword to symbol

``` clojure
(symbol (name :wonderful))
;; -> wonderful
``` 

primary conversion functions

		str
		keyword
		symbol

namespace included:

``` clojure
;; If you only want the name part of a keyword
(name :user/valid?)
;; -> "valid?"

;; If you only want the namespace
(namespace :user/valid?)
;; -> "user"
``` 

### Precision Numbers

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-13_absolute-precision.asciidoc`

exponents

``` clojure
;; Avogadro's number
6.0221413e23
;; -> 6.0221413E23

;; 1 Angstrom in meters
1e-10
;; -> 1.0E-10
``` 

quote promotes numbers to Big types

``` clojure
(* 9999 9999 9999 9999 9999)
;; ArithmeticException integer overflow  clojure.lang.Numbers.throwIntOverflow

(*' 9999 9999 9999 9999 9999)
;; -> 99950009999000049999N
``` 

### Rational Numbers

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-14_working-with-rational-numbers.asciidoc`

``` clojure
(/ 1 3)
;; -> 1/3

(type (/ 1 3))
;; -> clojure.lang.Ratio

(* 3 (/ 1 3))
;; -> 1N
``` 

`rationalize`: converts decimals to rationals

``` clojure
(+ (/ 1 3) 0.3)
;; -> 0.6333333333333333

(rationalize 0.3)
;; -> 3/10

(+ (/ 1 3) (rationalize 0.3))
;; -> 19/30
``` 

### Parsing Numbers

``` clojure
(Integer/parseInt "-42")
;; -> -42

(Double/parseDouble "3.14")
;; -> 3.14
``` 

### Rounding and Truncating

Truncate using `int`

``` clojure
(int 2.0001)
;; -> 2

(int 2.999999999)
;; -> 2
``` 

Round

``` clojure
(Math/round 2.0001)
;; -> 2

(Math/round 2.999)
;; -> 3

;; This is equivalent to:
(int (+ 2.99 0.5))
;; -> 3

``` 

### Fuzzy Comparison

``` clojure
(defn fuzzy= [tolerance x y]
  (let [diff (Math/abs (- x y))]
    (< diff tolerance)))

(fuzzy= 0.01 10 10.000000000001)
;; -> true

(fuzzy= 0.01 10 10.1)
;; -> false
``` 

### Trigonometry

``` clojure
;; Calculating sin(a + b). The formula for this is
;; sin(a + b) = sin a * cos b + sin b cos a
(defn sin-plus [a b]
  (+ (* (Math/sin a) (Math/cos b))
     (* (Math/sin b) (Math/cos a))))

(sin-plus 0.1 0.3)
;; -> 0.38941834230865047
``` 

### Different Bases

`2r101`: `101` in `2` radix

``` clojure
2r101010
;; -> 42

3r1120
;; -> 42

16r2A
;; -> 42

36rABUNCH
;; -> 624567473
``` 

Output:

``` clojure
(Integer/toString 13 2)
;; -> "1101"

(Integer/toString 42 16)
;; -> "2a"

(Integer/toString 35 36)
;; -> "z"
``` 

Partially applying: Change order of args

``` clojure
(defn to-base [radix n]
  (Integer/toString n radix))

(def base-two (partial to-base 2))

(base-two 9001)
;; -> "10001100101001"
``` 


### Simple Statistics

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-20_simple-statistics.asciidoc`

``` clojure
(defn mean [coll]
  (let [sum (apply + coll)
        count (count coll)]
    (if (pos? count)
      (/ sum count)
      0)))

(mean [1 2 3 4])
;; -> 5/2

(mean [1 1.6 7.4 10])
;; -> 5.0

(mean [])
;; -> 0
``` 

``` clojure
(defn median [coll]
  (let [sorted (sort coll)
        cnt (count sorted)
        halfway (quot cnt 2)]
    (if (odd? cnt)
      (nth sorted halfway) ; <1>
      (let [bottom (dec halfway)
            bottom-val (nth sorted bottom)
            top-val (nth sorted halfway)]
        (mean [bottom-val top-val]))))) ; <2>

(median [5 2 4 1 3])
;; -> 3

(median [7 0 2 3])
;; -> 5/2  ; The average of 2 and 3.
``` 

### Random Numbers

``` clojure
(rand)
;; -> 0.0249306187447903

(rand)
;; -> 0.9242089829055088
``` 

`rand-int` integers

``` clojure
;; Emulating a six-sided die
(defn roll-d6 []
  (inc (rand-int 6)))

(roll-d6)
;; -> 1

(roll-d6)
;; -> 3
``` 

`rand-nth` take nth element

``` clojure
(rand-nth [1 2 3])
;; -> 1

(rand-nth '(:a :b :c))
;; -> :c
``` 

For nonsequential collections:

``` clojure
(rand-nth (seq #{:heads :tails}))
;; -> :heads
``` 

`shuffle`: randomly sort

``` clojure
(shuffle [1 2 3 4 5 6])
;; -> [3 1 4 5 2 6]
``` 

### Currency

``` clojure
$ lein try clojurewerkz/money
``` 

``` clojure
(require '[clojurewerkz.money.amounts    :as ma])
(require '[clojurewerkz.money.currencies :as mc])

;; $2.00 in USD
(def two (ma/amount-of mc/USD 2))
two
;; -> #<Money USD 2.00>


(ma/plus two two)
;; -> #<Money USD 4.00>

(ma/minus two two)
;; -> #<Money USD 0.00>

(ma/< two (ma/amount-of mc/USD 2.01))
;; -> true

(ma/total [two two two two])
;; -> #<Money USD 8.00>
``` 

IEEE 754 standard carry a certain imprecision by design

``` clojure
(- 0.23 0.24)
;; -> -0.009999999999999981
``` 

### UUID Unique IDs

``` clojure
(java.util.UUID/randomUUID)
;; -> #uuid "5358e6e3-7f81-40f0-84e5-750e29e6ee05"

(java.util.UUID/randomUUID)
;; -> #uuid "a6f92a6f-f736-468f-9e26-f392852825f4"
``` 

`squuid`: sortable and unique uuid

``` clojure
(def u1 (squuid))
u1
;; -> #uuid "527bf210-dfae-4c73-8b7a-302d3b511f41"

(def u2 (squuid))
u2
;; -> #uuid "527bf219-65f0-4241-a165-c5c541cb98ea"
``` 

### Date and Time

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-25_current-date.asciidoc`

``` clojure
(defn now []
  (java.util.Date.))

(now)
;; -> #inst "2013-04-06T14:33:45.740-00:00"

;; A few seconds later...
(now)
;; -> #inst "2013-04-06T14:33:51.234-00:00"
``` 

unix timestamp

``` clojure
(System/currentTimeMillis)
;; -> 1365260110635
``` 

### Dates as Literals

`#inst`

``` clojure
(def ryans-birthday #inst "1987-02-18T18:00:00.000-00:00")

(println ryans-birthday)
;; *out*
;; #inst "1987-02-18T18:00:00.000-00:00"
``` 

### Parsing Dates

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-28_formatting-dates.asciidoc`

wrapper over joda

``` clojure
$ lein try clj-time
``` 

``` clojure
(require '[clj-time.format :as tf])

;; To parse dates like "02/18/87"
(def government-forms-date (tf/formatter "MM/dd/yy"))

(tf/parse government-forms-date "02/18/87")
;; -> #<DateTime 1987-02-18T00:00:00.000Z>

(def wonky-format (tf/formatter "HH:mm:ss:SS' on 'yyyy-MM-dd"))
;; -> #'user/wonky-format

(tf/parse wonky-format "16:13:49:06 on 2013-04-06")
;; -> #<DateTime 2013-04-06T16:13:49.060Z>
``` 

`DateTime` is joda class

### Formatting Dates

``` clojure
(require '[clj-time.format :as tf])
(require '[clj-time.core :as t])

(tf/unparse (tf/formatters :date) (t/now))
;; -> "2013-04-06"

(def my-format (tf/formatter "MMM d, yyyy 'at' hh:mm"))
(tf/unparse my-format (t/now))
;; -> "Apr 6, 2013 at 04:54"
``` 

#### Convert joda from/to java date instances

java to joda

``` clojure
(require '[clj-time.coerce :as tc])

(tc/from-date (java.util.Date.))
;; -> #<DateTime 2013-04-06T17:03:16.872Z>
``` 

joda to java

``` clojure
(tc/to-date (t/now))
;; -> #inst "2013-04-06T17:03:57.239-00:00"

(tc/to-long (t/now))
;; -> 1365267761585
``` 

### Comparing Dates

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-29_comparing-dates.asciidoc`

``` clojure
(defn now [] (java.util.Date.))
(def one-second-ago (now))
(Thread/sleep 1000)

;; Now is greater than (1) one second ago.
(compare (now) one-second-ago)
;; -> 1

;; One second ago is less than (-1) now.
(compare one-second-ago (now))
;; -> -1

;; "Equal" manifests as 0.
(compare one-second-ago one-second-ago)
;; -> 0
``` 

Why not use built-in comparison operators? `<=` `<`

They work with numbers.

`sort` uses `compare`

``` clojure
(def occurrences
  [#inst "2013-04-06T17:40:57.688-00:00"
   #inst "2002-12-25T00:40:57.688-00:00"
   #inst "2025-12-25T11:23:31.123-00:00"])

(sort occurrences)
;; -> (#inst "2002-12-25T00:40:57.688-00:00"
;;     #inst "2013-04-06T17:40:57.688-00:00"
;;     #inst "2025-12-25T11:23:31.123-00:00")
``` 

### Time Interval Between

`interval` from `clj-time`

``` clojure
(require '[clj-time.core :as t])

;; The first step is to capture two dates as an interval
(def since-april-first
  (t/interval (t/date-time 2013 04 01) (t/now)))

;; dt is the interval between April Fools Day, 2013 and today
since-april-first
;; -> #<Interval 2013-04-01T00:00:00.000Z/2013-04-06T20:06:30.507Z>

(t/in-days since-april-first)
;; -> 5

;; Years since the Moon landing
(t/in-years (t/interval (t/date-time 1969 07 20) (t/now)))
;; -> 43

;; Days from Feb. 28 to March 1 in 2012 (a leap year)
(t/in-days (t/interval (t/date-time 2012 02 28)
                       (t/date-time 2012 03 01)))
;; -> 2

;; And in a non-leap year
(t/in-days (t/interval (t/date-time 2013 02 28)
                       (t/date-time 2013 03 01)))
;; -> 1
``` 

### Generate Date and Time Ranges

## Chapter 02

### Create List

``` clojure
'(1 :2 "3")
(list 1 :2 "3")
``` 

Using `list` is better than quoted lists. Because quote prevents evaluation everything inside the list. 


