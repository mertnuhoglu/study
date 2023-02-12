(ns mert.e05)

; Barış'la Datomic Çalışmaları
; Tarih: 20230209
; rfr: video/20230209-mert-clj-egzersiz-30.mp4

; rfr: https://github.com/Bariscanates404/study e01.clj

(require '[datomic.api :as d])
; (def uri "datomic:mem://movies")
(def uri "datomic:dev://localhost:4334/mbrainz-1968-1973")
(def conn (d/connect uri))
(def db (d/db conn))

(take 5 (d/q '[:find ?title
               :in $ ?artist-name
               :where
               [?a :artist/name ?artist-name]
               [?t :track/artists ?a]
               [?t :track/name ?title]]
          db "John Kander"))
;=> (["Finale"] ["Seeing Things"] ["The Elephant Song"] ["Believe"] ["You and I, Love"])

; q: ?a neden [?t :track/artists ?a] cümleciğinde en sonda yer alıyor? Bu bir entity id olduğuna göre ?t'nin olduğu yerde olması gerekmez mi?
; rfr: sndgrsm > 20230209-Referans-verilen-entity-id
; Elimizde şu şekilde bir örnek veri var:
;| E                                | A                | V                   |
;|--------------------------------  |----------------  |-------------------  |
;| 101                              | :artist/name     | John Kander         |
;| 201                              | :track/name      | Finale              |
;| 202                              | :track/name      | Seeing Things       |
;| 203                              | :track/name      | The Elephant Song   |
;| 201                              | :track/artists   | 101                 |
;| 202                              | :track/artists   | 101                 |
;| 203                              | :track/artists   | 101                 |

; İlk başta parametrik sorgudan dolayı "John Kander" değerini `?artist-name` değişkenine bağlıyorum
; ?artist-name <- "John Kander"

; Sonra ilk data patternı alıyorum:
; [?a :artist/name ?artist-name]
; Burada ?artist-name yerine "John Kander" bağlı durumda.
; Dolayısıyla bu cümle aslında şuna denk geliyor:
; [?a :artist/name "John Kander"]

; Yukarıdaki tabloda :artist/name satırlarını buluyorum ve ilgili değere karşılık gelen satırı alıyorum
;| 101                              | :artist/name     | John Kander         |

; buradaki entity id değerini de ?a değişkenine bağlıyorum:
; ?a <- 101

; Sonra ikinci data patternı alıyorum:
; [?t :track/artists ?a]
; Burada ?a bağlanmış durumda. Denk geldiği ifade şu:
; [?t :track/artists 101]

; Yukarıdaki tablodan :track/artists 101 ile uyuşan satırları buluyorum
;| 201                              | :track/artists   | 101                 |
;| 202                              | :track/artists   | 101                 |
;| 203                              | :track/artists   | 101                 |

; Şimdi bunları sırayla ele alacağım
; Önce ilk satırla başlıyorum
;| 201                              | :track/artists   | 101                 |
; [?t :track/artists ?a]
; Burada 201 değerini ?t'nin yerine bağlıyoruz
; ?t <- 201

; Üçüncü data patternı alıyoruz
; [?t :track/name ?title]
; ?t yerine 201 bağlamıştık. Dolayısıyla şuna denk geliyor:
; [201 :track/name ?title]
;| 201                              | :track/name      | Finale              |
; ?title <- "Finale"

; Sırayla hepsi için aynısını yapıyoruz.

;| [?a :artist/name ?artist-name]   |                  |                     |
;| 101                              | :artist/name     | John Kander         |
;| ?a                               | :artist/name     | ?artist-name        |
;|                                  |                  |                     |
;| [?t :track/artists ?a]           |                  |                     |
;| 201                              | :track/artists   | 101                 |
;| ?t                               | :track/artists   | ?a                  |
;|                                  |                  |                     |
;| [?t :track/name ?title]          |                  |                     |
;| 201                              | :track/name      | Finale              |
;| ?t                               | :track/name      | ?title              |

; John Lennon adlı sanatçının tüm releaselerini bulalım. Sonra da bu releaselerin yayınlandığı mediumları bulalım.
(take 5 (d/q '[:find ?artist-name ?release-name ?format-name
               :in $ ?artist-name
               :where
               [?artist :artist/name ?artist-name]
               [?release :release/artists ?artist]
               [?release :release/media ?medium]
               [?release :release/name ?release-name]
               [?medium :medium/format ?format]
               [?format :db/ident ?format-name]]
          db "John Lennon"))
;=>
;(["John Lennon" "Mind Games" :medium.format/vinyl12]
; ["John Lennon" "Woman Is the Nigger of the World" :medium.format/vinyl]
; ["John Lennon" "John Lennon/Plastic Ono Band" :medium.format/vinyl]
; ["John Lennon" "Power to the People" :medium.format/vinyl7]
; ["John Lennon" "Unfinished Music No. 2: Life With the Lions" :medium.format/vinyl12])

; Tarih: 20230212
; Video: ...
;

;Ödev:
;Şu ana kadar AV üzerinden E'lere ulaştık
;Benzer şekilde EA üzerinden V'lere ulaştık
;Peki EV üzerinden A'lara ulaşabilir miyiz?
;Evet, bunu bir egzersiz olarak yapalım.

; "John Lennon" değerli bir atributa sahip olan entity'nin, atributu ve onun ismi nedir?
(d/q '[:find ?a
       :in $ ?name
       :where
       [527765581346058 ?a ?name]]
  db "John Lennon")
;=> #{[67]}
; Bu 67 bulduğumuz atributun ?a'nın entity idsi.
; Datomicte atributların kendileri de bir entity olarak tanımlanıyor.
; Meta modelde entity olarak tanımlanıyor.
; Şimdi benim bu entity'nin ismini bulmam gerekiyor.

; Datomic'te tüm keywordler (enumlar) bir entity olarak saklanır.
; Bu entitylerin hepsinin :db/ident diye bir atributu vardır.
; Bu atributun içinde de o keyword entitysinin ismi saklanır.

(d/q '[:find ?a ?attr-name
       :in $ ?name
       :where
       [527765581346058 ?a ?name]
       [?a :db/ident ?attr-name]]
  db "John Lennon")
;=> #{[67 :artist/name]}
