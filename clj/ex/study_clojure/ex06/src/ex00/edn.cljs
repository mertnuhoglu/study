(ns edn
  (:require [com.wsscode.edn-json :refer [edn->json json->edn]]))

; [wilkerlucio/edn-json: Tools to convert back and forth between EDN and JSON, optimised for storage and JSON tooling friendliness.](https://github.com/wilkerlucio/edn-json)

; simple scalars
(= (edn->json 42) 42)
(= (edn->json true) true)
(= (edn->json nil) null)
(= (edn->json "string") "string")

; edn scalars
(= (edn->json :keyword) "__edn-value|:keyword")
(= (edn->json :ns/keyword) "__edn-value|:ns/keyword")
(= (edn->json 'symb) "__edn-value|symb")
(= (edn->json 'foo/symb) "__edn-value|foo/symb")

; injection prevention
(= (edn->json "__edn-value|:foo") "__edn-value|\"__edn-value|:foo\"")

; edn default extensions
(= (edn->json #uuid"ca37585a-73cb-48c3-a8a4-7868ebc31801") "__edn-value|#uuid\"ca37585a-73cb-48c3-a8a4-7868ebc31801\"")
(= (edn->json #inst"2020-01-08T03:20:26.984-00:00") "__edn-value|#inst \"2020-01-08T03:20:26.984-00:00\"")

; sequences
(= (edn->json []) #js [])
(= (edn->json [42]) #js [42])
(= (edn->json #{true}) #js ["__edn-list-type|set" true])
(= (edn->json '(nil :kw)) #js ["__edn-list-type|list" null "__edn-value|:kw"])

; maps
(= (edn->json {}) #js {})
(= (edn->json {2 42}) #js {"2" 42})
(= (edn->json {nil 42}) #js {"__edn-key:nil" 42})
(= (edn->json {true 42}) #js {"__edn-key:true" 42})
(= (edn->json {"foo" 42}) #js {"foo" 42})
(= (edn->json {:foo 42}) #js {":foo" 42})
(= (edn->json {:foo/bar 42}) #js {":foo/bar" 42})
(= (edn->json {:foo {:bar 42}}) #js {":foo" #js {":bar" 42}})

; maps with complex keys
(= (edn->json {'sym 42}) #js {"__edn-key:sym" 42})
(= (edn->json {[3 5] 42}) #js {"__edn-key:[3 5]" 42})
(= (edn->json {#{:a :c} 42}) #js {"__edn-key:#{:a :c}" 42})
