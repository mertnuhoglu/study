--- 
title: "Study Paredit"
date: 2020-12-26T13:03:10+03:00 
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

# @mine

paredit'in mevcut kısayolları hep noktalama işaretleri içeriyor. Bu yüzden kullanımı oldukça zor. Tim Pope'un vim için tanımladığı standart kısayollar daha kullanışlı. Burada anlatılıyor:

[Writing Clojure in Vim](https://thoughtbot.com/blog/writing-clojure-in-vim)

# The Animated Guide to Paredit id=g11865

[The Animated Guide to Paredit](http://danmidwood.com/content/2014/11/21/animated-paredit.html)

## Balanced pairs:

01: Opening: `()` `[]` `{}`

02: Closing and indenting:  `()` `[]` `{}`

03: Quoting: `""`

04: Wrapping an S-expression

	| M-( | paredit-wrap-round|

05: Deleting

They refuse deleting if it breaks balance of sexp.

`paredit-kill`, `paredit-forward-delete`

06: Slurping and barfing

	| C-) | paredit-forward-slurp-sexp | 
	| C-} | paredit-forward-barf-sexp | 

### 07: Structural navigation

Move forward/backward amongst siblings

Raise up enclosing sexp / descend back down into children

	| C-M-f | paredit-forward      |

opt01: 

```clj
(a
	(b| c d))
->
(a
	(b c| d))
```

opt02:

```clj
(a
	(b c d)|
	(e f))
->
(a
	(b c d)
	(e f)|)
```

	| C-M-d | paredit-forward-down |

### 08: Splicing

Splicing = removing current sexp and joining the contents with the enclosing sexp

	| M-<up> | paredit-splice-sexp-killing-backward |

```clj
(a
	(b)
	(c)|
	(d))
->
(d)
```

```otl
a
	b
	c|
	d
->
d
```

	| M-<down> | paredit-splice-sexp-killing-forward |

```clj
((b| c) d)
->
(b d)
```


```otl
.
	.
		b|
		c
	d
->
.
	b
	d
```


