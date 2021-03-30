--- 
title: "Article: Transparency through data by James Reeves"
date: 2020-12-20T14:53:28+03:00 
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

[(293) Keynote: Transparency through data by James Reeves - YouTube](https://www.youtube.com/watch?v=zznwKCifC1A)

## What is transparency?

- Easy to understand => to predict

Transparency is about understanding.

Understanding is about predictions.

### Ex: `1 2 4 8 16 x`. What is x?

32? No, 31. Moses circle problem.

If a prediction is wrong, then we don't understand.

If a prediction is right, we don't necessarily understand. Maybe we predicted right not for the right reasons.

### Predict => Understand = Transparent

Code that is easy to predict is easy to understand = transparent

What makes code predictable?

- constraints.

## Constraints

- static types
- immutability 
- pure function
- etc.

Constraints narrow down possibilities

More limits we have => we can make more broad predictions

## Broad vs narrow prediction

narrow: `(= (reverse [1 2 3]) [3 2 1])`

broad: `(= (count (reverse coll)) (count coll))`

Broad prediction: effects all output

- static types help: input and output must be a list => broad prediction

- immutability helps: functions won't change data structures passed as arguments

- pure functions help: no side effect

### Ex: search some text

opt01: use clojure: turing complete => no guarantee to halt (side-effects) 

opt02: regex: guarantee to halt

opt03: re2/j: guarantee to halt in linear time 

[most powerful = opaque] clojure -> regex -> re2/j [most predictable = transparent]

With great power comes great responsibility (unpredictability)

### Ex: macros

First rule of macro club?

Don't write macros

Why?

- They have few constraints.

They can screw around evaluation order.

## Rule of least power

Tim Berners Lee: "Pick the least powerful languages"

Solution: DSL

- Ruby: dsl ~ obfuscated API
- actual dsl = actual language with syntax & grammar

Develop DSL that is delibarately less powerful. DSL that is constrained.

### DSL Example: literal map

Literal map is a DSL for defining pure functions with a single argument and a small domain.

Ex: `NOT` function as a map:

```clj
(def NOT
	{true false
	 false true})
```

- input = domain
- output = codomain

Relational tables are maps => they are functions too

- In a fct: domain-codomain: not so clear
- In a map: domain-codomain: very clear

Why?

- We can't query a function domain programmatically
- Same for relational tables

These are predictions:

1. Not in this domain set => not a valid input
   Not in this codomain set => not a valid output
2. boolean => boolean
3. no side-effect = pure (same answer for same input)
   => referential transparency => composable

- It will always halt = not turing complete = no loops
- By using a map, we get all these predictable properties for free
- So a map is more transparent than an arbitrary function.

### DSL Example: destructuring

```clj
{:keys [id name enabled?]
 :or {enabled? true}
 [x y] :position}
```

- pure = same result for some input

- no loops => halt

### DSL Example: routing

compojure: uses macros => very opaque

ataraxy: uses data

We can ask questions about routes. Why?

Because we can predict the results of a routing rule.

### DSL Example: yada

```clj
{:methods
 {:get
  :produces "text/html"
	:response "<h1>hello</h1>"}}
```

### DSL Example: queries

datomic pull:

```clj
[{:release/media
  [{(limit :medium/track 10)
	  [:track/name
		 {:track/artists [:artist/name]}]}]}]
```

### DSL Example: integrant

Think code base as a tree. We branch specific functions to outer edges.

Libraries are reusable branches.

Frameworks are trunks of tree.

Integrant replaces trunk of tree.

Integrant drains code from trunk and puts it into multi methods.

Structure is defined using data.

```clj
{:database.sql/connection
 {:uri "jdbc:sqlite:"}

 :example/handler
 {:database #ref :database.sql/connection}

 :ring.adapter/jetty
 {:handler #ref :example/handler
  :port 8080}}
```

## How to build your own DSL

How do you make things less powerful?

It is pretty hard.

- Finding a good abstraction is hard
- Writing a good language is harder still

But good news:

- DSLs  accumulate over time

## Tips for building DSLs

Rule 01: Avoid loops and recursion

Rule 02: Look for matching and destructuring

Often DSLs do both like ataraxy.

Rule 03: Look for static structure

Değişmeyen sabit şeyler olmalı. Bu sabit şeyler veri olarak modellenebilir.

Rule 04: make use of :namespaced/keywords

Clojure en zengin keyword sentaksına sahip.

1. keywordler
2. namespace edilebiliyor
3. keyword inheritance

Mesela, şu tür sorular sorabiliyoruz integrantta: "Are there any keys that inherit from server"

Rule 05: Make use of different data types

Bu sayede grameri hem uyumlu yaparız, hem de kısayollar kullanabiliriz.

Örnek: ataraxy: 

| :kw | request methods       |
| sym | path parameters       |
| ""  | static path           |
| #{} | query parameters      |
| {}  | request destructuring |
| []  | combination rules     |

Rule 06: Use spec for complex grammars

Most people use specs for validation. It is a parser for data structures. ataraxy uses it to parse some expressions.

## Question

Eğer TC olmasına gerek varsa dilinin, API kullanırsın DSL yerine. Hem yazması daha kolay, hem de daha alışıldık.

Çok karmaşık olan fakat TC olmayan diller de var. regex böyle. 

- Broadness vs depth of languages

regex is a DSL. But it is complicated and broad.

Lisp is a TC lang. But it is simple and deep.

# Türkçe özet anahat

Article: Transparency through data by James Reeves
	şeffaflık nedir?
		kolay anlaşılabilirlik
		anlaşılabilirlik => öngörülebilirlik
		örnek: 1 2 4 8 16 x
			x'in yerine ne gelir?
			32 değil, 31. Moses circle probleme göre
		Tahmin yanlışsa => anlamamışız demek ki
		Tahmin doğruysa => yine anlamıyor olabiliriz, tesadüfen tutturmuş olabiliriz
		Kolay öngörülen kod = kolay anlaşılır =  şeffaf
	Kodu öngörülebilir kılan şeyler nelerdir?
		Kısıtlar
		Kısıt örnekleri:
			statik typelar
			immutability
			pure functions
		Kısıtlar ihtimalleri azaltır
		Kısıtlar arttıkça => daha geniş tahminler yapabiliriz
	Geniş tahmin vs dar tahmin
		dar tahmin: (= (reverse [1 2 3]) [3 2 1])
		geniş tahmin: (= (count (reverse coll)) (count coll))
		statik type, immutability, pure fonksiyonlar geniş tahmin yapmaya yardımcı
			örn: girdi ve çıktı list olmalı = geniş tahmin
	Örnekler:
		ex01: metin arama
			clojure: tc (turing complete)
			regex: ntc (non-tc)
			regex daha kısıtlı, ama daha öngörülebilir
		ex02: macros
			birinci kural: makro yazma
			neden?
				çok az kısıt var
				yanlışlıkla evaluation sıralamasını bozabilirsin
	Rule of least power
		Tim Berners Lee: "Pick the least powerful languages"
			En az güçlü programlama dilini seçin
		Çözüm: DSL
			Ruby'nin API'leri gibi değil yalnız
			Gerçek DSL: sentaks ve gramer kuralları olmalı
	DSL
		ex01: literal map
			Bir map bir fonksiyon için DSL'dir = modeldir
				tek bir argümanlı, küçük tanım kümeli (domain) fonksiyonlar
			ör: `not` fonksiyonu map olarak:
				(def NOT
					{true false
					 false true})
			girdi = tanım kümesi = domain
			çıktı = görüntü kümesi = codomain
			İlişkisel tablolar birer maptir => fonksiyondur
			Fonksiyonlarda tanım-görüntü kümesi çok net değil
			Maplerde tanım-görüntü kümesi çok net 
			Neden?
				Bir fonksiyonun tanım kümesini programatik olarak sorgulayamayız
			non-TC = loop (döngü) yok
			no side-effect = pure => referans şeffaflığı => composable
		ex02: destructuring
		ex03: routing
			ataraxy: verilerle routing kurallarını tanımlar
		ex04: datomic sorguları
		ex05: integrant
			verilerle komponentlerin arasındaki bağımlılıkları tanımlar
	Nasıl DSL geliştirirsin?
		doğru soyutlamaları yapmak çok zor
		zaman içinde evrilerek geliştir
			iyi soyutlamalar zaman içinde birikir
		kural01: döngü ve recursion kullanma
		kural02: matching ve destructuring var mı bak
		kural03: statik yapıları ara
			değişmeyen sabit şeyler olmalı
			bunları veri olarak modelle
		kural04: :namespaced/keyword kullan
			ve de keyword inheritance
		kural05: farklı veri tiplerini kullan: map, set, primitif vs.
			örn: ataraxy her veri tipi farklı bir kuralı tarif eder
				| :kw | request methods       |
				| sym | path parameters       |
		kural06: karmaşık gramer kuralları için spec'ten yararlan





			



	


