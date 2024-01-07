---
title: "Study Smartparens"
date: 2020-12-26T12:54:32+03:00
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

- # Index - smartparens id=g11901
  id:: 1a2d151b-e246-4b46-8c17-18f5524d9321

`Cheatsheet cleverparens vim-sexp <url:file:///~/projects/study/emacs/smartparens.md#r=g11961>`

`Aktifleştirme: cleverparens <url:file:///~/projects/study/emacs/smartparens.md#r=g11857>`

`Mantık: Smartparens, paredit ve structural editing <url:file:///~/projects/study/emacs/smartparens.md#r=g11858>`

# Documentation - Smartparens

## Quick tour

[Quick tour · Fuco1/smartparens Wiki](https://github.com/Fuco1/smartparens/wiki/Quick-tour)

# @mine

- ## Mantık: Smartparens, paredit ve structural editing id=g11858
  id:: a5cac50a-2bac-4a6c-b464-a2fb0e6f1cf5

@mine: paredit'in çalışmasını sağlayan şey, kodun AST yapısında veri olması. Yani kod eğer AST ile aynı formda olmasaydı, o zaman onun üzerinde paredit'teki değişiklikleri yapmak bu kadar kesin tanımlanamazdı.

Dolayısıyla aslında paredit'in komutları her türlü hiyerarşik ağaç yapısındaki bilginin düzenlenmesinde kullanılabilir.

Vim düz metin düzenleme için tasarlanmış. Yapısal düzenleme araçlarıysa, hiyerarşik veri veya metinleri düzenlemek için tasarlanmış.

## Motivasyon: Bulmaca çözme ve dplyr ile benzerlik

Nasıl ki dplyr ve tidyverse kütüphaneleriyle veri manipülasyonu, bulmaca çözmek gibi zevkli; paredit veya benzeri yapısal düzenleme kütüphaneleriyle kod üzerindeki yapısal düzenlemeler de öyle.

Zaten aslında altta yatan mantık da birbirine benziyor. Birisinde ilişkisel veri üzerinde transformasyon yapıyorsun, diğerinde hiyerarşik AST verisi üzerinde.

Ayrıca vim'deki collapse komutları `zm zM` vs. de smartparens'ın motion komutlarına denk geliyor önemli oranda.

Bir başka faydası da, paredit sayesinde AST'nin yapısını görmeye ve bunun üzerinde düşünmeye zihnini odaklıyorsun. Normalde kodlar lineer yazılan ve birbiriyle ilişkisi çok açık net olmayan yapılardır. Fakat lisp dillerinde tüm kodlar bir hiyerarşi içinde yazılıyor. Ne var ki, bu hiyerarşiyi okumak oldukça zor. paredit bu hiyerarşiyi okumaya insanı zorluyor ve bu hiyerarşiyi anlamlandırmak için de insana bir kelime dağarcığı ve gramer sağlıyor. 

# Smartparens Cheatsheet

`SPC SPC sp-cheat-sheet` opens cheatsheet.

  | C-M-a | beginning-of-defun |
  | C-M-e | end-of-defun       |

# Article: Structural Editing · Clojure development with Spacemacs & Cider

[Structural Editing · Clojure development with Spacemacs & Cider](https://practicalli.github.io/spacemacs/structural-editing/)

`SPC k .` evil lisp-state

In lisp-state you can use single character commands to move

	| SPC k . | evil lisp-state                           |
	| s / S   | slurp forwards / backwards = pull in code |
	| b / B   | barf forw / back = push out code          |
	| d x     | delete expression                         |
	| r       | raise expression                          |

# Video: Emacs Rocks! Episode 14: Paredit - YouTube

[Emacs Rocks! Episode 14: Paredit - YouTube](https://www.youtube.com/watch?v=D6h5dFyyUX0)

It is about treating the code not as text, but as a tree to be manipulated.

Feature01: slurp/barf

  | M-(       | wrap-round         |
  | C-<right> | forward-slurp-sexp |
  | C-<left>  | forward-barf-sexp  |

- slurp-in or barf-out arguments = pull in or push out

Feature02: raise up:

  | M-r | raise-sexp |

```clj
(and (a) (|b))
;; ->
(b)
```

Raising replaces parent with a child.

Feature03: splice s-expression: killing backwards

  | M-<up> | splice-sexp-killing-backward |

```clj
(defn f []
	(a
		(|b)
		(c)))
;; ->
(defn f []
	(b)
	(c))
```

Feature03: convolute s-expression

  | M-? | convolute-sexp |

Feature04: kill to the end of line

  | C-k | paredit-kill       |
  | M-s | paredit-split-sexp |

```clj
(a "a"| "b" 2)
;; ->
(a "a")
("b" 2)
```"u y

Feature05: join-sexps

- # Article: Emacs and Pairs id=g11855
  id:: 0f2da62d-c9f0-439e-bc21-baa7facd5310

[Emacs and Pairs](https://ebzzry.io/en/emacs-pairs/)

# cleverparens:

- ## Aktifleştirme: cleverparens id=g11857
  id:: 7c5fc91d-eed7-4f21-9040-332cb01fa9f6

opt01:

[Evil Structural editing · Clojure development with Spacemacs & Cider](https://practicalli.github.io/spacemacs/install-spacemacs/evil-structural-editing.html)

Enable for all Clojure buffers by adding the following function in dotspacemacs/user-config

```clj
(spacemacs/toggle-evil-safe-lisp-structural-editing-on-register-hook-clojure-mode)
```

or major modes

```clj
(spacemacs/toggle-evil-safe-lisp-structural-editing-on-register-hooks)
```

opt02:

Açılır açılmaz aktif olmuyor bazen ve add-hook komutları çalışmadı.

Geçici çözüm: 

1. `SPC SPC evil-cleverparens-mode`

2. `SPC SPC evil-cp->`

Şimdi artık çalışır.

## Mantık: cleverparens

`cleverparens` `smartparens` keybindinglerini iptal edip onları değiştirmiş. Sebebi şu: `smartparens` `evil` modla tam uyumlu değil. `cp` bu uyumluluğu sağlıyor. `cp` ayrıca `paredit` ile de çalışabiliyor.

`cleverparens` komutları `evil-cp` diye başlıyor.

Emacs aşırı dinamik bir yazılım. Davranışını değiştiren komutlar ve modlar var. Dolayısıyla içinde bulunduğu state'e bağlı davranışı. Bu da anlaşılmasını çok zorlaştırıyor.

Örneğin: cleverparens kısayolları, emacs açıldığında yüklü değiller. Ancak `evil-cleverparens-mode` enable edersen çalışıyorlar. 

## Article: cleverparens README

[luxbock/evil-cleverparens: Evil normal-state minor-mode for editing lisp-like languages. Work in progress.](https://github.com/luxbock/evil-cleverparens)

To enable evil-cleverparens with your favorite lispy-mode use:

```clj
(add-hook '<your-lispy-mode> #'evil-cleverparens-mode)
```

@q: lispy-mode nedir? Nerede tanımlanmıştır?

- ## cleverparens: README id=g11864
  id:: 249586fe-26e5-4aee-b1e4-ddf9442f9750

[luxbock/evil-cleverparens: Evil normal-state minor-mode for editing lisp-like languages. Work in progress.](https://github.com/luxbock/evil-cleverparens)

Part of embracing Lisp and structural editing is learning to love the parentheses. Vim/evil is optimized for moving around by units of text, but for friends of Lisp the parentheses are more than just text

- ## Cheatsheet cleverparens vim-sexp id=g11961
  id:: 2a8e1fae-de5e-40f7-a4bd-44412466b4db
  id:: 61048688-4992-4a9c-b7a7-c46dc395e672

ref: `~/projects/study/emacs/ex/smartparens/e01/cleverparens_commands.clj`

ref: `sexp cheatsheet <url:file:///~/projects/study/clj/vim_sexp.md#r=g11971>`

vim-sexp komutlarının bir kısmını değiştirdim. Güncel ayarlar: 

ref: `vim-sexp <url:file:///~/projects/vim_repos/my-vim-custom/plugin/my-vim-custom.vim#r=g12870>`

  | keybinding | function                            | vim-sexp |
  | slurp barf |                                     |          |
  | >          | evil-cp->                           | >) >(    |
  | <          | evil-cp-<                           | <) <(    |
  | wrapping   |                                     |          |
  | M-(        | evil-cp-wrap-next-round             | ,w       |
  | M-)        | evil-cp-wrap-previous-round         |          |
  | M-[] M-{}  | similar...                          |          |
  | moving     |                                     |          |
  | M-j        | evil-cp-drag-forward                | >f M-j   |
  | M-k        | evil-cp-drag-backward               | <f M-k   |
  | M-t        | sp-transpose-sexp                   | <e       |
  | M-v        | sp-convolute-sexp                   |          |
  | M-q        | sp-indent-defun                     |          |
  | M-r        | sp-raise-sexp                       | ,O ,R    |
  | M-R        | evil-cp-raise-form                  | ,o ,r    |
  | inserting  |                                     |          |
  | M-i        | evil-cp-insert-at-beginning-of-form | <I ,h    |
  | M-a        | evil-cp-insert-at-end-of-form       | >I ,l    |
  | M-o        | evil-cp-open-below-form             |          |
  | M-O        | evil-cp-open-above-form             |          |
  | motion     |                                     |          |
  | _          | evil-cp-first-non-blank-non-opening |          |
  | ^          | evil-first-non-blank                | [[       |
  | H          | evil-cp-backward-sexp               | B        |
  | L          | evil-cp-forward-sexp                | W        |
  | M-h        | evil-cp-beginning-of-defun,         | (        |
  | M-l        | evil-cp-end-of-defun,               | )        |
  | `(`        | evil-cp-backward-up-sexp            |          |
  | `[`        | evil-cp-previous-opening            | (        |
  | `{`        | evil-cp-next-opening                |          |
  | yanking    |                                     |          |
  | M-w        | evil-cp-evil-copy-paste-form        |          |
  | M-d        | evil-cp-delete-sexp                 | daf      |
  | dd         | evil-cp-delete-line                 |          |
  | join split |                                     |          |
  | M-s        | sp-splice-sexp                      | ,@       |
  | M-J        | sp-join-sexp                        |          |
  | M-S        | sp-split-sexp                       |          |
  | navigating |                                     |          |
  | C-M-f      | paredit-forward                     |          |

### 01: slurp and barf:

	| > | evil-cp-> |
	| < | evil-cp-< |

`>`: behaviour depends on the position of cursor

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

### 02: wrap:

	| M-(       | evil-cp-wrap-next-round     |
	| M-)       | evil-cp-wrap-previous-round |
	| M-[] M-{} | similar...                  |

	(a, b) ;; M-( ->
	(a(, b)) 

	(a, b) ;; M-) ->
	((a), b) 

### 04: smartparens commands

  | M-q | sp-indent-defun   |
  | M-J | sp-join-sexp      |
  | M-s | sp-splice-sexp    |
  | M-S | sp-split-sexp     |
  | M-t | sp-transpose-sexp |
  | M-v | sp-convolute-sexp |
  | M-r | sp-raise-sexp     |

### 05: moving (dragging)

  | M-j | evil-cp-drag-forward  |
  | M-k | evil-cp-drag-backward |
  | M-t | sp-transpose-sexp     |
	| M-v | sp-convolute-sexp     |
	| M-q | sp-indent-defun       |
	| M-r | sp-raise-sexp         |
	| M-R | evil-cp-raise-form    |

	(a ,b c) ;; M-j ->
	(a c ,b)

	(a ,b c) ;; M-k ->
	(,b a c)

	foo ,bar ;; M-t ->
	,bar foo 

	(foo (bar ,baz)) ;; M-v ->
	(bar ,(foo baz))

	(foo
	,(bar baz)
	 quux)
	;; M-q ->
	(foo
	 (bar baz)
	 quux)

	(a b ,(c d) e f) ;; M-r ->
	,(c d)

	(a b (,c d) e f) ;; M-r ->
	(a b ,c e f) ;; 2 M-r ->
	(a b ,c d e f)

	(foo (bar ,baz) quux) ;; M-R ->
	(bar ,baz) ;; M-r ->
	(foo ,baz quux)

### 06: insert 

  | M-i | evil-cp-insert-at-beginning-of-form |
  | M-a | evil-cp-insert-at-end-of-form       |
  | M-o | evil-cp-open-below-form             |
  | M-O | evil-cp-open-above-form             |

	(foo (bar ,baz) quux blab) ;; M-i
	(foo (,bar baz) quux blab)

	(foo (bar ,baz) quux blab) ;; M-a
	(foo (bar baz,) quux blab)

	(foo (bar ,baz) quux blab) ;; M-o
	(foo (bar baz)
			 ,quux blab)

	(foo (bar ,baz) quux blab) ;; M-O
	(foo ,
	 (bar baz) quux blab)

### 07: motion

  | _   | evil-cp-first-non-blank-non-opening |
  | ^   | evil-first-non-blank                |
  | H   | evil-cp-backward-sexp               |
  | L   | evil-cp-forward-sexp                |
  | M-h | evil-cp-beginning-of-defun,         |
  | M-l | evil-cp-end-of-defun,               |
  | `(` | evil-cp-backward-up-sexp            |
  | `[` | evil-cp-previous-opening            |
  | `{` | evil-cp-next-opening                |

	(foo (bar ,baz) quux blab) ;; _ ->
	(,foo (bar baz) quux blab)

	(foo (bar ,baz) quux blab) ;; ^ ->
	,(foo (bar baz) quux blab)

	(foo bar baz), ;; H ->
	,(foo bar baz)

	(foo, bar baz) ;; H ->
	(,foo bar baz)

	(foo bar, baz) ;; 2 H ->
	(,foo bar baz) 

	((,foo bar) baz) ;; H ->
	(,(foo bar) baz)

	,(foo bar baz) ;; L ->
	(foo bar baz),

	(foo (,bar baz)) ;; M-h ->
	,(foo (bar baz))

	(foo (,bar baz)) ;; M-l ->
	(foo (bar baz),)

	(foo (bar baz) quux, blab) ;; ( ->
	,(foo (bar baz) quux blab)

	(foo (bar ,baz) quux blab) ;; ( ->
	(foo ,(bar baz) quux blab)

	(foo (bar baz) quux, blab) ;; [ ->
	(foo ,(bar baz) quux blab) ;; ( ->
	,(foo (bar baz) quux blab)

	(,foo (bar baz) quux blab) ;; { ->
	(foo ,(bar baz) quux blab)

### 08: yanking

	| M-w | evil-cp-evil-copy-paste-form |
	| M-d | evil-cp-delete-sexp          |
	| dd  | evil-cp-delete-line          |

	(a (b, c) d) ;; M-w ->
	(a (b, c)
		 (b, c) d)

	;; M-y M-c similar
	(foo ,(bar baz) quux blab) ;; M-d
	(foo  quux blab)

	(,foo (bar baz) quux blab) ;; M-d
	( (bar baz) quux blab)

	;; yy cc similar
	(foo
	 (bar baz)
	 ,quux blab) ;; dd
	(,foo
	 (bar baz))

### 09: joining/splitting/splicing

  | M-s | sp-splice-sexp |
  | M-J | sp-join-sexp   |
  | M-s | sp-splice-sexp |
  | M-S | sp-split-sexp  |

splice = unindent in tree hierarchy

	(foo (,bar baz) quux) ;; M-s ->
	(foo ,bar baz quux)

	(foo ,(bar baz) quux) ;; M-s ->
	foo ,(bar baz) quux

	(foo bar), (baz) ;; M-J ->
	(foo bar baz)

	(foo bar ,baz quux) ;; M-S ->
	(foo bar) (,baz quux) 

	"foo bar ,baz quux" ;; M-S ->
	"foo bar " ",baz quux"

	(foo bar, baz quux) ;; SPC u M-S ->
	(foo) (bar), (baz) (quux) 

# Exercises

## Ex01

```clj
(a (b ,c) d) ;; initial sexp
(a (b (,c)) d) ;; M-(
(a ((b ),c) d) ;; M-)
(a (b [,c]) d) ;; M-[
(a d (b ,c)) ;; M-j
((b ,c) a d) ;; M-k
(a (,c b) d) ;; M-t
(b (a ,c d)) ;; M-v
(a ,c d) ;; M-r
(b ,c) ;; M-R
(a (,b c) d) ;; M-i
(a (b c,) d) ;; M-a
(a (b c)
   ,d) ;; M-o
(a ,
 (b c) d) ;; M-O
(,a (b c) d) ;; _
,(a (b c) d) ;; ^
(a (,b c) d) ;; H
(a (b c,) d) ;; L
,(a (b c) d) ;; M-h
(a (b c) d), ;; M-l
(a ,(b c) d) ;; (
(a ,(b c) d) ;; [
(a (b c) d), ;; {
(a (b ,c)
   (b ,c) d) ;; M-w
(a (b ) d) ;; M-d
(a (b ) d) ;; D
(a b ,c d) ;; M-s
(a (b c) d) ;; M-J
(a (b) (,c) d) ;; M-S
```


