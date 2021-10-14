(ns main.smart-maps01
  (:require [com.wsscode.pathom3.connect.indexes :as pci]
            [com.wsscode.pathom3.connect.operation :as pco]
            [com.wsscode.pathom3.interface.smart-map :as psm]))

;; 01: Using smart maps

(pco/defresolver full-name [{::keys [first-name last-name]}]
  {::full-name (str first-name " " last-name)})

(def indexes (pci/register full-name))

(def person-data {::first-name "Anne" ::last-name "Frank"})

(def smart-map (psm/smart-map indexes person-data))

; if you lookup for a key in the initial data, it works the same way as a regular map
(::first-name smart-map) ; => "Anne"

; but when you read something that's not there, it will trigger the Pathom engine to
; fulfill the attribute
(::full-name smart-map) ; => "Anne Frank"

;; 02: Nested maps

(pco/defresolver full-name [{::keys [first-name last-name]}]
  {::full-name (str first-name " " last-name)})

(pco/defresolver anne []
  {::anne {::first-name "Anne" ::last-name "Frank"}})

(def indexes (pci/register [full-name anne]))

(def smart-map (psm/smart-map indexes))

(::anne smart-map) ; => {::first-name "Anne" ::last-name "Frank"}

; nested access
(-> smart-map ::anne ::full-name) ; => "Anne Frank"

;; 03: Nested sequences

(pco/defresolver full-name [{::keys [first-name last-name]}]
  {::full-name (str first-name " " last-name)})

(pco/defresolver stars []
  {::star-wars-characters
   [{::first-name "Luke" ::last-name "Skywalker"}
    {::first-name "Darth" ::last-name "Vader"}
    {::first-name "Han" ::last-name "Solo"}]})

(def indexes (pci/register [full-name stars]))

(def smart-map (psm/smart-map indexes))

; nested access on sequences
(mapv ::full-name (::star-wars-characters smart-map))
; => ["Luke Skywalker"
;     "Darth Vader"
;     "Han Solo"]