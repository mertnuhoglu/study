(ns mertnuhoglu.datahike.e01-release-delete-database
  (:use [mertnuhoglu.datahike.e01])
  (:require [datahike.api :as dh]))

;; you might need to release the connection for specific stores
(dh/release conn)

;; clean up the database if it is not need any more
(dh/delete-database cfg)
