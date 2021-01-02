(def foo +)
(def bar 1)
(def baz 2)
(def quux 3)
(def blab 3)
(def a 4)
(def b 5)
(def c 6)
(def d 7)
(def e 8)
(def f 9)
(def x 9)
(def y 9)
(def z 9)
(def car 1)

;; ## Joining splitting splicing

;; M-s sp-splice-sexp
(foo (,bar baz) quux) ;; M-s ->
(foo ,bar baz quux)

(foo (bar, baz) quux) ;; M-s ->
(foo bar, baz quux)

(foo ,(bar baz) quux) ;; M-s ->
foo ,(bar baz) quux

(foo (bar, baz) quux) ;; 2 M-s ->
foo (bar, baz) quux

;; M-J sp-join-sexp
(foo bar), (baz) ;; M-J ->
(foo bar baz)

(foo), (bar) (baz) ;; 2 M-s ->
(foo bar baz)

;; M-s sp-splice-sexp
(foo (bar, baz) quux) ;; M-s ->
(foo bar, baz quux)
;; splice ~ unindent

(foo ,(bar baz) quux) ;; M-s ->
foo ,(bar baz) quux

(foo (bar, baz) quux) ;; 2 M-s ->
foo (bar, baz) quux

;; M-S sp-split-sexp
(foo bar ,baz quux) ;; M-S ->
(foo bar) (,baz quux) ;; M-s ->
foo bar ,baz quux

"foo bar ,baz quux" ;; M-S ->
"foo bar " ",baz quux" ;; M-s ->
foo bar ,baz quux

([foo ,bar baz] quux) ;; M-S ->
([foo] [,bar baz] quux) ;; M-s ->
(foo ,bar baz quux)

(foo bar, baz quux) ;; SPC u M-S ->
(foo) (bar), (baz) (quux) ;; M-S ->
(foo bar)(, baz quux) ;; M-s ->
foo bar, baz quux

;; ## Wrapping

;; | M-(       | evil-cp-wrap-next-round     |
(a, b) ;; M-( ->
(a(, b)) ;; 2 M-( ->
(a(, b)) ;; -1 M-( ->
(a)(, b)

;; | M-)       | evil-cp-wrap-previous-round |
(a, b) ;; M-) ->
((a), b) ;; 2 M-) ->
((a), b) ;; -1 M-) ->
(a,) b()

;; ## Slurping/Barfing

;; | > | evil-cp-> |
;; | < | evil-cp-< |
(,foo bar baz)
(car)
;; `>` ->
(,foo bar baz
 (car))
;; `<` ->
(,foo bar) baz
(car)

a ,(foo bar ,baz) ;; `<` ->
,(a foo bar ,baz) ;; `>` ->
a foo ,(bar ,baz)

;; ## Motion

;; H evil-cp-backward-sexp
(foo bar baz), ;; H ->
,(foo bar baz)

(foo, bar baz) ;; H ->
(,foo bar baz)

(foo bar, baz) ;; 2 H ->
(,foo bar baz) 

((,foo bar) baz) ;; H ->
(,(foo bar) baz)

;; L evil-cp-forward-sexp
,(foo bar baz) ;; L ->
(foo bar baz),

(,foo bar baz) ;; L ->
(foo, bar baz)

(,foo bar baz) ;; 2 L ->
(foo bar, baz) 

(foo (bar baz,)) ;; L ->
(foo (bar baz),)

;; M-h evil-cp-beginning-of-defun,
(foo (,bar baz)) ;; M-h ->
,(foo (bar baz))

(foo (bar ,baz)) ;; M-h ->
,(foo (bar baz))

;; M-l evil-cp-end-of-defun,
(foo (,bar baz)) ;; M-l ->
(foo (bar baz),)

(foo (bar ,baz)) ;; M-l ->
(foo (bar baz),)

;; `(` evil-cp-backward-up-sexp
(foo (bar baz) quux, blab) ;; ( ->
,(foo (bar baz) quux blab)

(foo (bar ,baz) quux blab) ;; ( ->
(foo ,(bar baz) quux blab) ;; 2 ( ->
,(foo (bar baz) quux blab) ;; M-h ( ->
,(foo (bar baz) quux blab)

;; `[` evil-cp-previous-opening
(foo (bar baz) quux, blab) ;; [ ->
(foo ,(bar baz) quux blab) ;; ( ->
,(foo (bar baz) quux blab)

(foo (bar ,baz) quux blab) ;; [ ->
(foo ,(bar baz) quux blab) ;; ( ->
(foo ,(bar baz) quux blab)

;; `{` evil-cp-next-opening
(,foo (bar baz) quux blab) ;; { ->
(foo ,(bar baz) quux blab)

;; _ evil-cp-first-non-blank-non-opening
(foo (bar ,baz) quux blab) ;; _ ->
(,foo (bar baz) quux blab)

;; ^ evil-first-non-blank
(foo (bar ,baz) quux blab) ;; ^ ->
,(foo (bar baz) quux blab)

;; ## Yanking 

;; | M-w | evil-cp-evil-copy-paste-form |
(a (b, c) d) ;; M-w ->
(a (b, c)
   (b, c) d)
;; 2 M-w ->
(a (b, c)
   (b, c)
   (b, c) d)
;; -1 M-w ->
(a (b, c) d)

;; M-d evil-cp-delete-sexp
;; M-y M-c similar
(foo ,(bar baz) quux blab) ;; M-d ->
(foo  quux blab)

(,foo (bar baz) quux blab) ;; M-d ->
( (bar baz) quux blab)

(foo (,bar baz) quux blab) ;; M-d ->
(foo (, baz) quux blab) ;; SPC u M-d ->
(foo () quux blab)

;; dd evil-cp-delete-line
;; yy cc similar
(foo
 (bar baz)
 ,quux blab) ;; dd ->
(,foo
 (bar baz))

;; ## Insert

;; M-i evil-cp-insert-at-beginning-of-form
;; similar to I
(foo (bar ,baz) quux blab) ;; M-i ->
(foo (,bar baz) quux blab)

;; M-a evil-cp-insert-at-end-of-form
;; similar to A
(foo (bar ,baz) quux blab) ;; M-a ->
(foo (bar baz,) quux blab)

;; M-o evil-cp-open-below-form
;; similar to o
(foo (bar ,baz) quux blab) ;; M-o ->
(foo (bar baz)
     ,quux blab)

;; M-O evil-cp-open-above-form
;; similar to O
(foo (bar ,baz) quux blab)
(foo ,
 (bar baz) quux blab)

;; ## Moving / dragging

;; M-j evil-cp-drag-forward
(a ,b c) ;; M-j ->
(a c ,b)

(a ,b (c) d) ;; M-j ->
(a ,(c) b d)

(a, b c)
(d)
;; M-j ->
(d)
(a, b c)

;; M-k evil-cp-drag-backward
(a ,b c) ;; M-k ->
(,b a c)

;; M-t	sp-transpose-sexp
foo ,bar ;; M-t ->
,bar foo 

foo ,bar baz ;; M-t ->
,bar baz foo ;; 2

(foo) ,(bar baz) ;; M-t ->
,(bar baz) (foo)

(foo bar)
,(baz quux)   ;; keeps the formatting
;; M-t ->
,(baz quux)
(foo bar)

foo bar baz, ;; M-t ->
foo bar baz,

;; M-v	sp-convolute-sexp
(forward-char (sp-get env ,:op-l)) ;; M-v ->
(sp-get env (forward-char ,:op-l))

(foo bar,) ;; M-v ->
,

(foo ,bar) ;; M-v ->
bar

(foo (,bar)) ;; M-v ->
(,(foo bar))

(foo (bar ,baz)) ;; M-v ->
(bar ,(foo baz))

[foo [bar ,baz]] ;; M-v ->
[bar ,[foo baz]]

;; M-q sp-indent-defun
(foo
 ,(bar baz)
 quux)
;; M-q ->
(foo
 (bar baz)
 quux)

;; M-r	sp-raise-sexp
(a b ,(c d) e f) ;; M-r ->
,(c d)

(a b (,c d) e f) ;; M-r ->
(a b ,c e f) ;; 2 M-r ->
(a b ,c d e f)

(a b ,c d e f) ;; 1 M-r ->
,c ;; 2 M-r ->
,c d

(- (car x) ,a 3) ;; -1 M-r ->
(car x) ;; 0 M-r ->
- (car x) ,a 3 ;; 1 M-r ->
,a ;; 2 M-r ->
,a 3

(foo (bar ,baz) quux) ;; 1 M-r ->
(foo ,baz quux) ;; SPC u M-r ->
(foo ,baz quux) ;; SPC u SPC u M-r ->
(bar ,baz)

;; M-R evil-cp-raise-form
(foo (bar ,baz) quux) ;; M-R ->
(bar ,baz) ;; M-r ->
(foo ,baz quux)

(a (b ,c) d)
(a (b (,c)) d) ;; M-()
(a ((b ),c) d) ;; M-)
(a (b [,c]) d) ;; M-[]
(a d (b ,c)) ;; M-j
((b ,c) a d) ;; M-k
(a (,c b) d) ;; M-t
(b (a ,c d)) ;; M-v
(a ,c d) ;; M-r
(b ,c) ;; M-R
(a (,,b ,c) d) ;; M-i
(a (b ,c,,) d) ;; M-a
(a (b ,c)
   ,,d) ;; M-o
(a ,,
 (b ,c) d) ;; M-O
(,,a (b ,c) d) ;; _
,,(a (b ,c) d) ;; ^
(a (,,b ,c) d) ;; H
(a (b ,c,,) d) ;; L
,,(a (b ,c) d) ;; M-h
(a (b ,c) d),, ;; M-l
(a ,,(b ,c) d) ;; ()
(a ,,(b ,c) d) ;; []
(a (b ,c) d),, ;; {}
(a (b ,c)
   (b ,c) d) ;; M-w
(a (b ) d) ;; M-d
(a (b ) d) ;; D
(a b ,c d) ;; M-s
(a (b c) d) ;; M-J
(a (b) (,c) d) ;; M-S
