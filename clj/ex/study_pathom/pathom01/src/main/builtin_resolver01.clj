(ns main.builtin-resolver01
  (:require
    [com.wsscode.pathom3.connect.operation :as pco]
    [com.wsscode.pathom3.connect.indexes :as pci]
    [com.wsscode.pathom3.interface.smart-map :as psm]
    [com.wsscode.pathom3.connect.built-in.resolvers :as pbir]))

;; 01: Constants

; constant literal
(pbir/constantly-resolver :math/PI 3.1415)

; constant function value
(pbir/constantly-fn-resolver ::throw-error (fn [_] (throw (ex-info "Error" {}))))

;; 02: Aliasing

(pbir/alias-resolver :specific.impl.product/id :generic.ui.product/id)

; is equivalent to:

(pco/defresolver spec->generic-prod-id [{:specific.impl.product/keys [id]}]
  {:generic.ui.product/id id})

;; 02: Equivalence

(pbir/equivalence-resolver :acme.product/upc :affiliate.product/upc)
; iki yönlü alias gibi düşün bunu

;; 03: Simple one-to-one transformation

; in this example we create one different transformation for each direction
(def registry
  [(pbir/single-attr-resolver :acme.track/duration-ms :affiliate.track/duration-seconds #(/ % 1000))
   (pbir/single-attr-resolver :affiliate.track/duration-seconds :acme.track/duration-ms #(* % 1000))])

(def indexes (pci/register registry))

(-> (psm/smart-map indexes {:acme.track/duration-ms 324000})
  :affiliate.track/duration-seconds)
; => 324

(-> (psm/smart-map indexes {:affiliate.track/duration-seconds 324})
  :acme.track/duration-ms)
; => 324000

;; 04: Static Tables

(def registry
  [(pbir/static-table-resolver :song/id
     {1 {:song/name "Marchinha Psicotica de Dr. Soup"}
      2 {:song/name "There's Enough"}})

   ; you can provide a name for the resolver, prefer fully qualified symbols
   (pbir/static-table-resolver `song-analysis :song/id
     {1 {:song/duration 280 :song/tempo 98}
      2 {:song/duration 150 :song/tempo 130}})])

(let [sm (psm/smart-map (pci/register registry)
           {:song/id 1})]
  (select-keys sm [:song/id :song/name :song/duration]))
; => #:song{:id 1, :name "Marchinha Psicotica de Dr. Soup", :duration 280}

;; 05: Static Attribute Map

; Tek atributtan oluşan tablolar

(def registry
  [; simple attribute table
   (pbir/static-attribute-map-resolver :song/id :song/name
     {1 "Marchinha Psicotica de Dr. Soup"
      2 "There's Enough"})

   (pbir/static-table-resolver `song-analysis :song/id
     {1 {:song/duration 280 :song/tempo 98}
      2 {:song/duration 150 :song/tempo 130}})])

(let [sm (psm/smart-map (pci/register registry)
           {:song/id 1})]
  (select-keys sm [:song/id :song/name :song/duration]))
; => #:song{:id 1, :name "Marchinha Psicotica de Dr. Soup", :duration 280}

;; 06: EDN File Resolver

; app
(pco/defresolver full-url [{:keys [my.system.server/port my.system.resource/path]}]
  {::server-url (str "http://localhost:" port "/" path)})

(def registry
  [(pbir/edn-file-resolver "resources/file-resolver.edn")
   full-url])

(-> (psm/smart-map (pci/register registry))
  ::server-url)
; => "http://localhost:1234/resources/public

;; 07: Static tables on EDN Files

; app
(def registry (pbir/edn-file-resolver "resources/file-resolver-with-table.edn"))

(let [sm (psm/smart-map (pci/register registry) {:my.system/user-id 4})]
  (select-keys sm [:my.system/port :my.system.user/name]))
; => {:my.system/port 1234, :my.system.user/name "Anne"}

;; 08: Global Data

; Global data is the same operation that EDN Files do, but using the data directly:

; app
(pco/defresolver full-url [{:keys [my.system.server/port my.system.resource/path]}]
  {::server-url (str "http://localhost:" port "/" path)})

(def registry
  [(pbir/global-data-resolver {:my.system.server/port
                          1234

                          :my.system.resource/path
                          "resources/public"})
   full-url])

(-> (psm/smart-map (pci/register registry))
  ::server-url)
; => "http://localhost:1234/resources/public