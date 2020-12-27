--- 
title: "Study Meander"
date: 2020-12-23T12:00:08+03:00 
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

# Video: "Meander: Declarative Explorations at the Limits of FP" by Jimmy Miller  id=g_11838

["Meander: Declarative Explorations at the Limits of FP" by Jimmy Miller - YouTube](https://www.youtube.com/watch?v=9fhnJpCgtUw)

![Input Data](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201223_120252.jpg)

Goal: 

- All valid player\weapon combinations
- name, weapon, class, attack power, and all upgrades
- no third party weapons

![Target Output](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201223_120215.jpg)

opt01: map, filter ile:

![map filter](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201223_120404.jpg)

opt02: meander ile:

![meander solution](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201223_120430.jpg)

@mine: Aslında Meander da, datomic de, SQL da, dplyr da birer sorgulama dili. Ayrıca çoğu for loop, map de öyle. Çünkü hepsi bir şekilde data transformasyonu yapma araçları.

@mine: Bunun dplyr veya ilişkisel cebire göre bir üstünlüğü şu: Tüm girdi modeli tek seferde görebiliyorsun. SQL'da tabloların schemasını query ifadesinde direk göremiyorsun. 

@mine: Join işlemi yalnız SQL kadar görünür değil. SQL'de özellikle "join by key" diyorsun. Burada açıkça join ifadesini kullanmıyorsun. Örneğin `#{?class}` ile join yapıyor, ama bunu görmek için tüm sorgu ifadesini okumak lazım.

@mine: Yalnız ilişkisel cebire göre bir üstünlüğü, çıktının illa ki flat olma zorunluluğu yok. İlişkisel cebirde sadece `SELECT` ile projeksiyon yapıyoruz. Ve doğal olarak çıktımız yassı oluyor. Burada ise içiçe olabilir. 

![AST Querying](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201223_195008.jpg)
