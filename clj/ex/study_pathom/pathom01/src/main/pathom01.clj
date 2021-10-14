(ns main.pathom01
  (:require [com.wsscode.pathom3.cache :as p.cache]
            [com.wsscode.pathom3.connect.built-in.resolvers :as pbir]
            [com.wsscode.pathom3.connect.built-in.plugins :as pbip]
            [com.wsscode.pathom3.connect.foreign :as pcf]
            [com.wsscode.pathom3.connect.indexes :as pci]
            [com.wsscode.pathom3.connect.operation :as pco]
            [com.wsscode.pathom3.connect.operation.transit :as pcot]
            [com.wsscode.pathom3.connect.planner :as pcp]
            [com.wsscode.pathom3.connect.runner :as pcr]
            [com.wsscode.pathom3.error :as p.error]
            [com.wsscode.pathom3.format.eql :as pf.eql]
            [com.wsscode.pathom3.interface.async.eql :as p.a.eql]
            [com.wsscode.pathom3.interface.eql :as p.eql]
            [com.wsscode.pathom3.interface.smart-map :as psm]
            [com.wsscode.pathom3.path :as p.path]
            [com.wsscode.pathom3.plugin :as p.plugin]))

; our mock temperatures database
(def temperatures
  {"Recife" 23})

(pco/defresolver temperature-from-city [{:keys [city]}]
  {:temperature (get temperatures city)})

(def indexes
  (pci/register [temperature-from-city]))

; this creates a smart map, using our indexes, and with some initial data
(def smart-map (psm/smart-map indexes {:city "Recife"}))

; smart maps work as regular maps when looking for the initial data
(:city smart-map); => "Recife"

; but the difference comes when we ask for keys not present in the map, but reachable
; via resolvers
(:temperature smart-map); => 23

; ex02:

; Now consider a slightly different question: Is it cold in Recife now?
(pco/defresolver cold? [{:keys [temperature]}]
  {:cold? (< temperature 20)})

(def indexes
  (pci/register [temperature-from-city cold?]))

; this time we will use the EQL interface to trigger the Pathom engine
(p.eql/process
  indexes
  {:city "Recife"}
  [:cold?])
; => {:cold? false}