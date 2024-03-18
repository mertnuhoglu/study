(ns syntax.special-forms)

; Source: [Clojure - Special Forms](https://clojure.org/reference/special_forms)

;; ------------------------------------------------------------------
;; # def
;; ------------------------------------------------------------------
;;
; (def symbol doc-string? init?)
;
; Default metadata keys:
; :private
; :doc
; :test
; :tag
; :file
; :line
; :name
; :ns
; :macro 
; :arglists

; Use namespace-qualified keys for your own metadata:
;
(defn
 ^{:doc "mymax [xs+] gets the maximum value in xs using > "
   :test (fn []
             (assert (= 42  (mymax 2 42 5 4))))
   :user/comment "this is the best fn ever!"}
  mymax
  ([x] x)
  ([x y] (if (> x y) x y))
  ([x y & more]
   (reduce mymax (mymax x y) more)))

(meta #'mymax)
; {:user/comment "this is the best fn ever!",
;  :ns #object[clojure.lang.Namespace 0x263f038e "syntax.special-forms"],
;  :name mymax,
;  :file
;  "/Users/mertnuhoglu/prj/study/clj/ex/study_clojure/ex06/src/syntax/special_forms.clj",
;  :column 1,
;  :line 22,
;  :arglists ([x] [x y] [x y & more]),
;  :doc "mymax [xs+] gets the maximum value in xs using > ",
;  :test
;  #object[syntax.special_forms$fn__4279 0x1bcafdb1 "syntax.special_forms$fn__4279@1bcafdb1"]}

;; ------------------------------------------------------------------
;; ## if
;; ------------------------------------------------------------------
;;
; (if test then else?)

;; ------------------------------------------------------------------
;; ## do
;; ------------------------------------------------------------------
;;
; (do expr*)

;; ------------------------------------------------------------------
;; ## let
;; ------------------------------------------------------------------
;;
; (let [binding*] expr*)

; Simplest binding-form is a symbol:
(let [x 1
      y x]
  y)

;; ------------------------------------------------------------------
;; ## quote
;; ------------------------------------------------------------------
;;
; (quote form)

(identity '(a b c)) 
; (a b c)

;; ------------------------------------------------------------------
;; ## var
;; ------------------------------------------------------------------
;;
; (var symbol)

;; ------------------------------------------------------------------
;; ## fn
;; ------------------------------------------------------------------
;;
; (fn name? [params*] expr*)
; (fn name? ([params*] expr*)+)

; params => positional-param* or positional-param* & rest-param
; positional-param => binding-form
; rest-param => binding-form
; name => symbol




