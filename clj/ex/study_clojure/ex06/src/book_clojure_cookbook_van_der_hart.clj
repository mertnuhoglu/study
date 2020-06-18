(ns book_clojure_cookbook_van_der_hart
  (:require [clojure.string :as str]))

; most codes are taken from https://github.com/clojure-cookbook/clojure-cookbook

; capitalization of a string id=g_11372

(str/capitalize "a b. c d.")
;; => "A b. c d."
(str/upper-case "ab c") 
;; => "AB C"
(str/lower-case "A B") 
;; => "a b"

; Clean Whitespace in a String  id=g_11373

(str/trim " \ta b\n") 
;; => "a b"

(str/replace "a\t\nb c\fd" #"\s+" " ") 
;; => "a b c d"

; Combine/Join a String id=g_11374

(str "a" " " "b")
;; => "a b"

(def lines ["#! /bin/bash\n", "du -a ./ | sort -n -r\n"]) 
(apply str lines)
;; -> "#! /bin/bash\ndu -a ./ | sort -n -r\n"

(def f ["a" "b"]) (str/join ", " f)
(str/join [1 2 3 4]) ;; -> "1234"
;; => "1234"

;; Constructing a CSV from a header string and vector of rows
(def header "a,b\n")
(def rows ["10,20","11,21"])
(apply str header (interpose "\n" rows))
;; => "a,b\n10,20\n11,21"

; String to Character id=g_11375

(seq "ali")
;; => (\a \l \i)

(frequencies (str/lower-case "aa b"))
;; => {\a 2, \space 1, \b 1}

(defn all_upper? [s]
  (every? #(or (not (Character/isLetter %))
               (Character/isUpperCase %))
          s))
(all_upper? "A B")
;; => true
(all_upper? "A b")
;; => false

; Character to/from Integer id=g_11376

(int \a)
;; -> 97
(map int "a b")
;; => (97 32 98)
(char 97)
;; -> \a

; Formatting Strings id=g_11377

;; str

(def me {:k "v"})
(str "key: " (:k me))
;; => "key: v"

(apply str (interpose " " [1 2.000 (/ 3 1) (/ 4 9)]))
;; -> "1 2.0 3 4/9"

;; format

(defn filename [name i]
  (format "%03d-%s" i name))
(filename "file.txt" 12)
;; => "012-file.txt"

;; Create a table using justification
(defn tableify [row]
  (apply format "%-20s | %-20s | %-20s" row))

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

; Regex Match id=g_11378

(re-find #"\d+" "ab 12")
;; => "12"

(re-matches #"\w+" "ab c")
;; => nil

; Regex Match: successive matches

(re-seq #"\w+" "ab c")
;; => ("ab" "c")

(defn mentions [tweet]
  (re-seq #"(@|#)(\w+)" tweet))

(mentions "ab @c de. #fg")
;; => (["@c" "@" "c"] ["#fg" "#" "fg"])

; Regex Replace id=g_11379

(str/replace "a b" "a" "c")
;; => "c b"

; Regex Split


(str/split "A,B" #",")
;; => ["A" "B"]

; Pluralizing Strings id=g_11380


(require '[inflections.core :as inf])

(inf/pluralize 1 "monkey")
;; -> "1 monkey"

(inf/pluralize 12 "monkey")
;; -> "12 monkeys"

; Converting Between Strings, Symbols, and Keywords id=g_11381


(symbol "a?")
;; => a?
(str 'a?)
;; => "a?"

(name :a)
;; => "a"
(str :a)
;; => ":a"

(keyword "a")
;; => :a
(keyword 'a)
;; => :a

(symbol (name :a))
;; => a

(name :user/a?)
;; => "a?"
(namespace :user/a?)
;; => "user"

; Precision Numbers id=g_11382


2.1e2
;; -> 2.1E2
1e-10
;; -> 1.0E-10

(* 9999 9999 9999 9999 9999)
;; ArithmeticException integer overflow  clojure.lang.Numbers.throwIntOverflow

(*' 9999 9999 9999 9999 9999)
;; => 99950009999000049999N

; Rational Numbers id=g_11383


(/ 1 3)
;; -> 1/3

(type (/ 1 3))
;; -> clojure.lang.Ratio

(* 3 (/ 1 3))
;; -> 1N

;; `rationalize`: converts decimals to rationals

(rationalize 0.3)
;; -> 3/10

(+ (/ 1 3) (rationalize 0.3))
;; -> 19/30

; Parsing Numbers id=g_11384

(Integer/parseInt "-42")
;; -> -42

(Double/parseDouble "3.14")
;; -> 3.14

; Rounding and Truncating id=g_11385

(int 2.0001)
;; -> 2
(int 2.999999999)
;; -> 2

(Math/round 2.0001)
;; -> 2
(Math/round 2.999)
;; -> 3

; Fuzzy Comparison id=g_11386


(defn fuzzy= [tolerance x y]
  (let [diff (Math/abs (- x y))]
    (< diff tolerance)))

(fuzzy= 0.01 10 10.001)
;; => true

; Trigonometry id=g_11388

(Math/sin 0.1)
;; => 0.09983341664682815

; Different Bases id=g_11387

(int 2r101)
;; => 5

(int 16r2A)
;; => 42

; Output:

(Integer/toString 5 2)
;; => "101"

; Partially applying: Change order of args

(defn to-base [radix n]
  (Integer/toString n radix))
(def base-two (partial to-base 2))
(base-two 5)
;; => "101"


; Simple Statistics


(defn mean [coll]
  (let [sum (apply + coll)
        count (count coll)]
    (if (pos? count)
      (/ sum count)
      0)))

(mean [1 2 3])
;; => 2
(mean [])
;; => 0

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

(median [5 2 4])
;; => 4

; Random Numbers id=g_11389

(rand)
;; -> 0.0249306187447903

(inc (rand-int 6))
;; => 1

(rand-nth '(:a :b :c))
;; -> :c

; For nonsequential collections:
(rand-nth (seq #{:heads :tails}))
;; -> :heads

(shuffle [1 2 3 4 5 6])
;; -> [3 1 4 5 2 6]

; Currency

; $ lein try clojurewerkz/money

(require '[clojurewerkz.money.amounts    :as ma])
(require '[clojurewerkz.money.currencies :as mc])

(def two (ma/amount-of mc/USD 2))
two
;; -> #<Money USD 2.00>

(ma/plus two two)
;; -> #<Money USD 4.00>

; imprecise numbers id=g_11391


; IEEE 754 standard carry a certain imprecision by design

(- 0.23 0.24)
;; -> -0.009999999999999981

; uuid global identifiers id=g_11390


(java.util.UUID/randomUUID)
;; -> #uuid "5358e6e3-7f81-40f0-84e5-750e29e6ee05"

; squuid: sortable and unique uuid

(def u1 (squuid))
u1
;; -> #uuid "527bf210-dfae-4c73-8b7a-302d3b511f41"

; Date and Time id=g_11392


(defn now []
  (java.util.Date.))

(now)
;; => #inst "2020-06-16T18:44:08.981-00:00"

; unix timestamp
(System/currentTimeMillis)
;; => 1592333064027

; Dates as Literals id=g_11393

(def my-birthday #inst "1987-02-18T18:00:00.000-00:00")

(println my-birthday)
;; #inst "1987-02-18T18:00:00.000-00:00"

; Parsing Dates id=g_11394


; $ lein try clj-time

(require '[clj-time.format :as tf])

(tf/parse (tf/formatter "MM/dd/yy") "02/18/87")
;; -> #<DateTime 1987-02-18T00:00:00.000Z>

(def wonky-format (tf/formatter "HH:mm:ss:SS' on 'yyyy-MM-dd"))
;; -> #'user/wonky-format

(tf/parse wonky-format "16:13:49:06 on 2013-04-06")
;; -> #<DateTime 2013-04-06T16:13:49.060Z>

; Formatting Dates id=g_11395


(require '[clj-time.format :as tf])
(require '[clj-time.core :as t])

(tf/unparse (tf/formatters :date) (t/now))
;; -> "2013-04-06"

(def my-format (tf/formatter "MMM d, yyyy 'at' hh:mm"))
(tf/unparse my-format (t/now))
;; -> "Apr 6, 2013 at 04:54"

; Convert joda from/to java date instances id=g_11396

(require '[clj-time.coerce :as tc])

(tc/from-date (java.util.Date.))
;; -> #<DateTime 2013-04-06T17:03:16.872Z>

(tc/to-date (t/now))
;; -> #inst "2013-04-06T17:03:57.239-00:00"

(tc/to-long (t/now))
;; -> 1365267761585

; Comparing Dates id=g_11397


(defn now [] (java.util.Date.))
(def one-second-ago (now))
(compare (now) one-second-ago)
;; -> 1

(def occurrences
  [#inst "2013-04-06T17:40:57.688-00:00"
   #inst "2002-12-25T00:40:57.688-00:00"])

(sort occurrences)
;; => (#inst "2002-12-25T00:40:57.688-00:00" #inst "2013-04-06T17:40:57.688-00:00")

; Time Interval Between id=g_11398

(require '[clj-time.core :as t])

(def since-april-first
  (t/interval (t/date-time 2013 04 01) (t/now)))
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

; Generate Date and Time Ranges

;; ## Chapter 02

; Create List

'(1 :2 "3")
;; => (1 :2 "3")
(list 1 :2 "3")
;; => (1 :2 "3")


