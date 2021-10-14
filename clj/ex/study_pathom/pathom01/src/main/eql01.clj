(ns main.eql01
  (:require [com.wsscode.pathom3.connect.built-in.resolvers :as pbir]
            [com.wsscode.pathom3.connect.indexes :as pci]
            [com.wsscode.pathom3.interface.eql :as p.eql]))

(def indexes
  (pci/register
    [(pbir/constantly-resolver ::pi 3.1415)
     (pbir/single-attr-resolver ::pi ::tau #(* % 2))]))

(p.eql/process indexes [::pi ::tau])
; => {::pi 3.1415 ::tau 6.283}
