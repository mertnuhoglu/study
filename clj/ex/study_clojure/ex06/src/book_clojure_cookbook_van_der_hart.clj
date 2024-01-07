(ns book_clojure_cookbook_van_der_hart
  (:require [clojure.string :as str]))

; most codes are taken from https://github.com/clojure-cookbook/clojure-cookbook

; capitalization of a string id=g11372
;   id:: d8f78af7-eaf7-4d6e-bbcd-d7fd00318710

(str/capitalize "a b. c d.")
;; => "A b. c d."
(str/upper-case "ab c") 
;; => "AB C"
(str/lower-case "A B") 
;; => "a b"

; Clean Whitespace in a String  id=g11373
;   id:: c4611fe2-d403-414f-a062-02e2a4a33733

(str/trim " \ta b\n") 
;; => "a b"

(str/replace "a\t\nb c\fd" #"\s+" " ") 
;; => "a b c d"

; Combine/Join a String id=g11374
;   id:: d5637b75-6329-4d4d-b718-6bd4417edca4

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

; String to Character id=g11375
;   id:: 65b13c56-1c29-4dc6-8e8a-3d5ca5be8352

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

; Character to/from Integer id=g11376
;   id:: 79499e1f-7531-4b49-b747-a06a79bb7b8a

(int \a)
;; -> 97
(map int "a b")
;; => (97 32 98)
(char 97)
;; -> \a

; Formatting Strings id=g11377
;   id:: 573f52b9-2fbc-4059-a45c-ae6a42fa5ef2

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

; Regex Match id=g11378
;   id:: 49cd9d3b-c5f4-4967-94f9-ec3e18e1a757

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

; Regex Replace id=g11379
;   id:: b325aa9c-8140-448b-a29a-ef88109066f0

(str/replace "a b" "a" "c")
;; => "c b"

; Regex Split


(str/split "A,B" #",")
;; => ["A" "B"]

; Pluralizing Strings id=g11380
;   id:: 8bb1a430-e696-4de6-ac8b-0bcdb7d5e520


(require '[inflections.core :as inf])

(inf/pluralize 1 "monkey")
;; -> "1 monkey"

(inf/pluralize 12 "monkey")
;; -> "12 monkeys"

; Converting Between Strings, Symbols, and Keywords id=g11381
;   id:: 6ff036e7-480e-479c-876b-1dedba7635b1


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

; Precision Numbers id=g11382
;   id:: ce594888-8628-4791-9062-88e54bee3120


2.1e2
;; -> 2.1E2
1e-10
;; -> 1.0E-10

(* 9999 9999 9999 9999 9999)
;; ArithmeticException integer overflow  clojure.lang.Numbers.throwIntOverflow

(*' 9999 9999 9999 9999 9999)
;; => 99950009999000049999N

; Rational Numbers id=g11383
;   id:: a6cce897-adca-4e97-bcb7-17afb3aeb661


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

; Parsing Numbers id=g11384
;   id:: 1cb62e86-7451-435b-b0fd-956f9128ceb9

(Integer/parseInt "-42")
;; -> -42

(Double/parseDouble "3.14")
;; -> 3.14

; Rounding and Truncating id=g11385
;   id:: bcc72f4e-b972-4f2d-8d96-835188e9936a

(int 2.0001)
;; -> 2
(int 2.999999999)
;; -> 2

(Math/round 2.0001)
;; -> 2
(Math/round 2.999)
;; -> 3

; Fuzzy Comparison id=g11386
;   id:: 4aaf599b-4f9c-4c25-bd45-29015f081086


(defn fuzzy= [tolerance x y]
  (let [diff (Math/abs (- x y))]
    (< diff tolerance)))

(fuzzy= 0.01 10 10.001)
;; => true

; Trigonometry id=g11388
;   id:: 416fb9b1-fb9e-497e-8473-f9a0e85ab3f4

(Math/sin 0.1)
;; => 0.09983341664682815

; Different Bases id=g11387
;   id:: af24e979-de07-44e2-8cb2-31ea63547501

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

; Random Numbers id=g11389
;   id:: 92f532a4-43bf-47a8-8492-442202208a89

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

; imprecise numbers id=g11391
;   id:: ae8db229-390c-4753-af50-f1d8fa28e3e3


; IEEE 754 standard carry a certain imprecision by design

(- 0.23 0.24)
;; -> -0.009999999999999981

; uuid global identifiers id=g11390
;   id:: ae224cea-ca28-4191-adcf-eb386a11400d


(java.util.UUID/randomUUID)
;; -> #uuid "5358e6e3-7f81-40f0-84e5-750e29e6ee05"

; squuid: sortable and unique uuid

(def u1 (squuid))
u1
;; -> #uuid "527bf210-dfae-4c73-8b7a-302d3b511f41"

; Date and Time id=g11392
;   id:: 9bb38b6c-8efe-4919-9713-4ea4d7ffdd8d


(defn now []
  (java.util.Date.))

(now)
;; => #inst "2020-06-16T18:44:08.981-00:00"

; unix timestamp
(System/currentTimeMillis)
;; => 1592333064027

; Dates as Literals id=g11393
;   id:: acacb380-6f4d-42f6-9ab1-dd64c37d3b7f

(def my-birthday #inst "1987-02-18T18:00:00.000-00:00")

(println my-birthday)
;; #inst "1987-02-18T18:00:00.000-00:00"

; Parsing Dates id=g11394
;   id:: 7d6cd1a6-c8ed-408f-9ae8-5bc8b98c775e


; $ lein try clj-time

(require '[clj-time.format :as tf])

(tf/parse (tf/formatter "MM/dd/yy") "02/18/87")
;; -> #<DateTime 1987-02-18T00:00:00.000Z>

(def wonky-format (tf/formatter "HH:mm:ss:SS' on 'yyyy-MM-dd"))
;; -> #'user/wonky-format

(tf/parse wonky-format "16:13:49:06 on 2013-04-06")
;; -> #<DateTime 2013-04-06T16:13:49.060Z>

; Formatting Dates id=g11395
;   id:: b671f0ff-8610-43e8-a896-c3bcbe828313


(require '[clj-time.format :as tf])
(require '[clj-time.core :as t])

(tf/unparse (tf/formatters :date) (t/now))
;; -> "2013-04-06"

(def my-format (tf/formatter "MMM d, yyyy 'at' hh:mm"))
(tf/unparse my-format (t/now))
;; -> "Apr 6, 2013 at 04:54"

; Convert joda from/to java date instances id=g11396
;   id:: 387b0fed-a948-4f51-a892-8b0c05b151db

(require '[clj-time.coerce :as tc])

(tc/from-date (java.util.Date.))
;; -> #<DateTime 2013-04-06T17:03:16.872Z>

(tc/to-date (t/now))
;; -> #inst "2013-04-06T17:03:57.239-00:00"

(tc/to-long (t/now))
;; -> 1365267761585

; Comparing Dates id=g11397
;   id:: d3b0d8ae-ab32-46c7-90bf-0b576d5c3a6a


(defn now [] (java.util.Date.))
(def one-second-ago (now))
(compare (now) one-second-ago)
;; -> 1

(def occurrences
  [#inst "2013-04-06T17:40:57.688-00:00"
   #inst "2002-12-25T00:40:57.688-00:00"])

(sort occurrences)
;; => (#inst "2002-12-25T00:40:57.688-00:00" #inst "2013-04-06T17:40:57.688-00:00")

; Time Interval Between id=g11398
;   id:: aa11d368-3e00-406d-9b6b-01dd407254e4

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


