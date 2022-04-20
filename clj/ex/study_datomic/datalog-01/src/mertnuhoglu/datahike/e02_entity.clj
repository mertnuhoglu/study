(ns mertnuhoglu.datahike.e02-entity
  (:use [mertnuhoglu.datahike.e02])
  (:require [datahike.api :as dh]))

; Cardinality many attributes are returned sequences:
(:likes (dh/entity @db 4))
;=> #{"fries" "pizza"}

;Reference attributes are returned as another entities:
(:friends (dh/entity @db 4)) ; => {:db/id 5}

;References can be walked backwards by prepending _ to name part of an attribute:
(:_friends (dh/entity @db 5)) ; => [{:db/id 4}]
;(:ns/_ref (entity db 2)) ; => [{:db/id 1}]
