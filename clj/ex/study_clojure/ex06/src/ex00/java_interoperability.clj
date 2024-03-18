(ns ex00.java-interoperability)

; ## Java Interoparability
;   id:: b87bc0b9-628c-461a-b035-c280c3e1bbfd
; Source: [Clojure - Java Interop](https://clojure.org/reference/java_interop)

(.. System (getProperties) (get "os.name"))
;; expands to:
(. (. System (getProperties)) (get "os.name"))
(-> (System/getProperties) (.get "os.name"))

; java interoperability || ((fe99755d-5ac7-428c-a7e8-2a994c046486))
(new java.util.Date) ;; => #inst "2020-06-15T14:27:36.415-00:00"
(java.util.Date. "2016/2/19") ;; => #inst "2016-02-18T22:00:00.000-00:00"
(let [d (java.util.Date.)] (.toString d)) ;; => "Mon Jun 15 17:27:47 EEST 2020"
(let [d (java.util.Date.)] (. d toString)) ;; => "Mon Jun 15 17:27:51 EEST 2020"

(let [d1 (java.util.Date.)
      d2 (java.util.Date.)]
  (.equals d1 d2))
;; => true

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

