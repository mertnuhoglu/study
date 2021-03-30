--- 
title: "Multimethods and Hierarchies"
date: 2021-03-24T10:34:51+03:00 
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

# Article: Clojure - Multimethods and Hierarchies

[Clojure - Multimethods and Hierarchies](https://clojure.org/reference/multimethods)

- Avoid creating new data types

Prefer functions on a small set of types.

- But polymorphism is useful

Multimethod system supports dispatching on types, values, attrributes, metadata, relation of arguments

- Multimethod API:

	| defmulti      | creates new multimethods                              |
	| defmethod     | creates and setups a new method with a dispatch-value |
	| remove-method | removes a method having a dispatch-value              |
	| prefer-method | orders methods when ambigous                          |

- multimethod = dispatching function + multiple methods

Hangi methodun çağrılacağı, dispaching value tarafından belirleniyor.

`:default` if no match

- Derivation:
- Similar to java inheritance
- is-a relations
- Clojure adhoc hierarchy system
	- derivation relation between names
	- derivation relation between classes and names
- `derive` function creates relation
- `isa?` function tests for their existence
- Note: `isa?` is not `isinstance?`

Örneğin, elimizde bir tane `::rect` keywordü olsun. Her bir dikdörtgen bir şekildir. Her bir kare ise bir dikdörtgendir. Dolayısıyla:

```clj
(derive ::rect ::shape)
(derive ::square ::rect)
```

Şimdi bu ilişkileri sorgulayalım:

  | parents     | bunun tek seviye üstü ne? |
  | ancestors   | bunun ataları ne?         |
  | descendants | bunun astları ne?         |

```clj
(parents ::rect)
-> #{:user/shape}

(ancestors ::square)
-> #{:user/rect :user/shape}

(descendants ::shape)
-> #{:user/rect :user/square}
```

Hiyerarşideki sınıf sorgulamaları için: `isa?`. Yani bu küme, öbürünün bir alt kümesi midir?

```clj
(isa? ::square ::shape)
-> true
```

Dikkat: `(isa? 42 42)` Bu da doğru. Fakat `42` bir küme midir? Tek elemanlı bir küme olarak düşünebiliriz. Aslında keywordler için de aynı durum var. Her bir küme aslında tek eleman. Elemanlarsa birer kavram. Örneğin `::rect` bir kavram. `::shape` de bir kavram. Bu kavramların arasındaki ilişkileri tarif ediyoruz.

Sadece keywordler değil, diğer şeyler arasında da hiyerarşik tasnif yapmak mümkün:

```clj
(derive java.util.Map ::collection)
; yani: java.util.Map bir ::collection'dır
(derive java.util.Collection ::collection)
; yani: java.util.Collection da bir ::collection'dır

(isa? java.util.HashMap ::collection)
-> true
```

Otomatik olarak javadaki tüm class inheritance ilişkileri `isa?` ile sorgulanabilir:

```clj
(isa? String Object)
-> true
```

## isa? based dispatch

opt01: class hiyerarşisi kullanarak dispatch:

```clj
(defmulti foo class)
(defmethod foo ::collection [c] :a-collection)
(defmethod foo String [s] :a-string)

(foo [])
; :a-collection

(foo "bar")
; :a-string
```

Note: normal bir fonksiyonda fonksiyon isminden sonra argüman listesi gelirdi. Burada ise dispatch value geliyor.

opt02: keyword hiyerarşisi kullanarak dispatch:

```clj
(defmulti area :Shape)
(defn rect [wd ht] {:Shape :Rect :wd wd :ht ht})
(defn circle [radius] {:Shape :Circle :radius radius})
(defmethod area :Rect [r]
    (* (:wd r) (:ht r)))
(defmethod area :Circle [c]
    (* (. Math PI) (* (:radius c) (:radius c))))
(defmethod area :default [x] :oops)
(def r (rect 4 13))
(def c (circle 12))
(area r)
-> 52
(area c)
-> 452.3893421169302
(area {})
-> :oops
```

Note: `defmulti` içindeki `:Shape` dispatch value var. Fakat `defmethod` içinde `:Rect` vs. Bunlar aslında `rect` mapinin `:Shape` keyine bağlı valuelar.


