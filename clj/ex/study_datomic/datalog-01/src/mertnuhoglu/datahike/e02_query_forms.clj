(ns mertnuhoglu.datahike.e02-query-forms
  (:use [mertnuhoglu.datahike.e02])
  (:require [datahike.api :as dh]))

;Query as parameter with additional args:
(dh/q
  '[:find ?value
    :where [_ :likes ?value]]
  #{[1 :likes "fries"]
    [2 :likes "candy"]
    [3 :likes "pie"]
    [4 :likes "pizza"]})
;=> #{["fries"] ["pie"] ["pizza"] ["candy"]}

;Or query passed in arg-map:
(dh/q {:query '[:find ?value
                :where [_ :likes ?value]]
       :offset 2
       :limit 1
       :args [#{[1 :likes "fries"]
                [2 :likes "candy"]
                [3 :likes "pie"]
                [4 :likes "pizza"]}]})
; => #{["fries"] ["candy"] ["pie"] ["pizza"]}

;Or query passed as map of vectors:
(dh/q
  '{:find [?value] :where [[_ :likes ?value]]}
  #{[1 :likes "fries"]
    [2 :likes "candy"]
    [3 :likes "pie"]
    [4 :likes "pizza"]})
; => #{["fries"] ["candy"] ["pie"] ["pizza"]}

;Or query passed as string
(dh/q
  {:query "[:find ?value :where [_ :likes ?value]]"
   :args [#{[1 :likes "fries"]
            [2 :likes "candy"]
            [3 :likes "pie"]
            [4 :likes "pizza"]}]})
; => #{["fries"] ["candy"] ["pie"] ["pizza"]}
