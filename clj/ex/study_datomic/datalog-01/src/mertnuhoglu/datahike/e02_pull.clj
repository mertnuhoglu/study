(ns mertnuhoglu.datahike.e02-pull
  (:use [mertnuhoglu.datahike.e02])
  (:require [datahike.api :as dh]))

;Unlike entity, returns plain Clojure map (not lazy).
(dh/pull @db [:db/id, :name, :likes, {:friends [:db/id :name]}] 4)
;=>
#_{:db/id 4, :name "Ivan", :likes ["fries" "pizza"], :friends [{:db/id 5, :name "Oleg"}]}

;Same as pull, but accepts sequence of ids and returns sequence of maps.
(dh/pull-many @db [:db/id :name] [4 5])
;=>
#_[{:db/id 4, :name "Ivan"} {:db/id 5, :name "Oleg"}]
