(ns main.pathom06
  (:require
    [com.wsscode.pathom3.connect.indexes :as pci]
    [com.wsscode.pathom3.connect.operation :as pco]
    [com.wsscode.pathom3.interface.eql :as p.eql]
    [com.wsscode.pathom3.interface.smart-map :as psm]
    [clojure.string :as str]))

;; Using defresolver

; this resolver computes a slug to use on URL from some article title
(pco/defresolver article-slug [env {:acme.article/keys [title]}]
  {::pco/op-name `article-slug
   ::pco/input   [:acme.article/title]
   ::pco/output  [:acme.article/slug]}
  {:acme.article/slug (str/replace title #"[^a-z0-9A-Z]" "-")})

; refactoring 01: We can remove the ::pco/op-name and let defresolver use the same symbol as the var:
(pco/defresolver article-slug [env {:acme.article/keys [title]}]
  {::pco/input  [:acme.article/title]
   ::pco/output [:acme.article/slug]}
  {:acme.article/slug (str/replace title #"[^a-z0-9A-Z]" "-")})

; refactoring 02: Now, given the input is the same as destructuring, we can let defresolver infer the input from it:
(pco/defresolver article-slug [env {:acme.article/keys [title]}]
  {::pco/output [:acme.article/slug]}
  {:acme.article/slug (str/replace title #"[^a-z0-9A-Z]" "-")})

; opt02: use destructuring via symbol binding
(pco/defresolver article-slug [env {title :acme.article/title}]
  {::pco/output [:acme.article/slug]}
  {:acme.article/slug (str/replace title #"[^a-z0-9A-Z]" "-")})

; refactoring 03: Since the last element of our expression is a map, defresolver can infer the output from it:
(pco/defresolver article-slug [env {:acme.article/keys [title]}]
  {:acme.article/slug (str/replace title #"[^a-z0-9A-Z]" "-")})

; note: if you wrap the final map with a let statement, pathom will not be able to figure it out:
(pco/defresolver foo []
  ; infer won't work, the last statement is a let
  (let [x 10]
    {:foo x}))

(pco/defresolver foo []
  ; works
  {:foo (let [x 10] x)})

; refactoring 04: Given we are not using env, we can also take it out, taking us to the final version:
(pco/defresolver article-slug [{:acme.article/keys [title]}]
  {:acme.article/slug (str/replace title #"[^a-z0-9A-Z]" "-")})

; In case you have a resolver that doesn't use an input, you can omit the first argument as well, for example:
(pco/defresolver constant-pi []
  {:acme.math/pi 3.1415})
