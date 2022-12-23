(ns mertnuhoglu.datomic.datomic-01c)

;; 04. Sample Data <url:file:///~/prj/study/clj/articles-datomic.md#r=g13507>

(require '[datomic.client.api :as d])

(def client (d/client {:server-type :dev-local
                       :system "datomic-samples"}))
(d/list-databases client {})
;=>
;["solar-system"
; "graph"
; "mbrainz-subset"
; "social-news"
; "streets"
; "dilithium-crystals"
; "movies"
; "friends"
; "decomposing-a-query"]
