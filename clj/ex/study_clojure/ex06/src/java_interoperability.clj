(ns java-interoperability)

; From: [Clojure - Wikipedia](https://en.wikipedia.org/wiki/Clojure)) > Java Interoperability

;; call an instance method
(.toUpperCase "apple")
;; => "APPLE"

;; call a static method
(System/getProperty "java.vm.version")
;; => "12+33"

;; create an instance of `java.util.HashMap` and
;; add some entries
(doto (java.util.HashMap.)
  (.put "apple" 1)
  (.put "banana" 2))
;; => {"banana" 2, "apple" 1}

;; create an instance of `java.util.ArrayList` and
;; increment its elements with `clojure.core/map`
(def al (doto (java.util.ArrayList.)
          (.add 1)
          (.add 2)
          (.add 3)))

(map inc al)
;; => (2 3 4)

;; show a message dialog using Java Swing
(javax.swing.JOptionPane/showMessageDialog
  nil
  "Hello, World!")
;; => nil

; From: Programming Clojure - Alex Miller

(.. "hello" getClass getProtectionDomain)
; = "hello".getClass().getProtectionDomain()

(instance? java.util.Collection [1 2 3])
;; true

(new java.util.Random)
;; #object[java.util.Random 0x166cad2b "java.util.Random@166cad2b"]

(java.util.Random.)
;; #object[java.util.Random 0x4c273b70 "java.util.Random@4c273b70"]

(def rnd (new java.util.Random))

(. rnd nextInt)
;; 586317606

(. rnd nextInt 10)
;; 0

; Instance field
(def p (java.awt.Point. 10 20))
(. p x)
;; 10

; Static method
(. System lineSeparator)
;; "\n"

; Static field
(. Math PI)
;; 3.141592653589793

; More concise syntax for instance and static access:

(.nextInt rnd 10)
;; 7

(.x p)
;; 10

(System/lineSeparator)
;; "\n"

Math/PI
;; 3.141592653589793

(import '(java.util Random Locale)
        '(java.text MessageFormat))

Random
;; java.util.Random

(require '[clojure.repl :refer :all])
(javadoc java.net.URL)

