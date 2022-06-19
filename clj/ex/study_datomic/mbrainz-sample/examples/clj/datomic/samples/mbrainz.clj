;;   Copyright (c) Metadata Partners, LLC. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file epl-v10.html at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.

(ns datomic.samples.mbrainz
  (:require [clojure.pprint :refer (pprint)]
            [datomic.api :as d]
            [datomic.samples.mbrainz.rules :refer (rules)]
            [fipp.edn :refer [pprint] :rename {pprint fipp}]))

;; this file is intended for evaluation, form-by-form, at the REPL

;;;;;;;;;;;;;;; get a connection ;;;;;;;;;;;;;;;;;;

;; Replace with your transactor's connection information
(def uri "datomic:dev://localhost:4334/mbrainz-1968-1973")

(def conn (d/connect uri))
(def db (d/db conn))

(comment
  ; başka bir veritabanına bağlanmayı deneyince, hata alıyorum:
  (def uri "datomic:dev://localhost:4334/ground-up")
  ; (err) Execution error at datomic.peer/get-connection$fn (peer.clj:681).
  ; (err) Could not find ground-up in catalog
  (def conn (d/connect uri))

  ; a03a: create-database yap önce
  (d/create-database uri)
  (def conn (d/connect uri)))
  
  
  
  
;;;;;;;;;;;;;;; REPL safety and convenience ;;;;;;;;;;;;;;;;;;

;; for when you accidentally ask for all tracks...
(set! *print-length* 250)

;;;;;;;;;;;;;;; data queries ;;;;;;;;;;;;;;;;;;

(d/q '[:find ?title
       :in $ ?artist-name
       :where
       [?a :artist/name ?artist-name]
       [?t :track/artists ?a]
       [?t :track/name ?title]]
     db
     "John Lennon")

(d/q '[:find ?title ?album ?year
       :in $ ?artist-name
       :where
       [?a :artist/name   ?artist-name]
       [?t :track/artists ?a]
       [?t :track/name    ?title]
       [?m :medium/tracks ?t]
       [?r :release/media ?m]
       [?r :release/name  ?album]
       [?r :release/year  ?year]]
     db
     "John Lennon")

(d/q '[:find ?title ?album ?year
       :in $ ?artist-name
       :where
       [?a :artist/name   ?artist-name]
       [?t :track/artists ?a]
       [?t :track/name    ?title]
       [?m :medium/tracks ?t]
       [?r :release/media ?m]
       [?r :release/name  ?album]
       [?r :release/year  ?year]
       [(< ?year 1970)]]
     db
     "John Lennon")

(d/q '[:find ?title ?album ?year
       :in $ % ?artist-name
       :where
       [?a :artist/name   ?artist-name]
       [?t :track/artists ?a]
       [?t :track/name    ?title]
       (track-release ?t ?r)
       [?r :release/name  ?album]
       [?r :release/year  ?year]]
     db
     rules
     "John Lennon")

(d/q '[:find ?entity ?name ?tx ?score
       :in $ ?search
       :where [(fulltext $ :artist/name ?search) [[?entity ?name ?tx ?score]]]] 
     db, "Jane")

(d/q '[:find ?title ?artist ?album ?year
       :in $ % ?search
       :where
       (track-search ?search ?track)
       (track-info ?track ?title ?artist ?album ?year)]
     db
     rules
     "always")

(d/q '[:find ?aname ?aname2
       :in $ % [?aname ...]
       :where (collab ?aname ?aname2)]
     db rules ["John Lennon" "Paul McCartney" "George Harrison" "Ringo Starr"])

(d/q '[:find ?aname ?aname2
       :in $ % [?aname ...]
       :where (collab-net-2 ?aname ?aname2)]
     db
     rules
     ["George Harrison"])

(def query '[:find ?aname2
             :in $ % [[?aname]]
             :where (collab ?aname ?aname2)])

(d/q query
     db
     rules
     (d/q query
          db
          rules
          [["Diana Ross"]]))


(d/q '[:find ?aname ?tname
       :in $ ?artist-name
       :where
       [?a :artist/name ?artist-name]
       [?t :track/artists ?a]
       [?t :track/name ?tname]
       [(!= "Outro" ?tname)]
       [(!= "[outro]" ?tname)]
       [(!= "Intro" ?tname)]
       [(!= "[intro]" ?tname)]
       [?t2 :track/name ?tname]
       [?t2 :track/artists ?a2]
       [(!= ?a2 ?a)]
       [?a2 :artist/name ?aname]]
     db
     "Bill Withers")

(comment
  (fipp rules)
; ([(track-release ?t ?r) [?m :medium/tracks ?t] [?r :release/media ?m]]
;  [(track-info ?t ?track-name ?artist-name ?album ?year)
;   [?t :track/name ?track-name]
;   [?t :track/artists ?a]
;   [?a :artist/name ?artist-name]
;   (track-release ?t ?r)
;   [?r :release/name ?album]
;   [?r :release/year ?year]]
  (d/datoms db :eavt :artist/name)

  ; 
  ,)

(comment
  ; Get schema id=g12894
  ; Find and pretty-print each attribute in schema 

  ; [How can I query the entire schema that is stored inside of a datomic database? - Stack Overflow](https://stackoverflow.com/questions/28685109/how-can-i-query-the-entire-schema-that-is-stored-inside-of-a-datomic-database)
  (map #(->> % first (d/entity db) d/touch)
        (d/q '[:find ?v
               :where [_ :db.install/attribute ?v]]
          db))
; (#:db{:id 97, :ident :release/script, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "The script used in the release"}
;  #:db{:id 98, :ident :release/language, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "The language used in the release"}
;  #:db{:id 99, :ident :release/barcode, :valueType :db.type/string, :cardinality :db.cardinality/one, :doc "The barcode on the release packaging"}
;  #:db{:id 100, :ident :release/name, :valueType :db.type/string, :cardinality :db.cardinality/one, :index true, :fulltext true, :doc "The name of the release"}
;  #:db{:id 101, :ident :release/media, :valueType :db.type/ref, :cardinality :db.cardinality/many, :isComponent true, :doc "The various media (CDs, vinyl records, cassette tapes, etc.) included in the release."}
;  #:db{:id 102, :ident :release/packaging, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "The type of packaging used in the release, an enum, one\n  of: :release.packaging/jewelCase, :release.packaging/slimJewelCase, :release.packaging/digipak, :release.packaging/other\n  , :release.packaging/keepCase, :release.packaging/none,\n  or :release.packaging/cardboardPaperSleeve"}
;  #:db{:id 103, :ident :release/year, :valueType :db.type/long, :cardinality :db.cardinality/one, :index true, :doc "The year of the release"}
;  #:db{:id 104, :ident :release/month, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The month of the release"}
;  #:db{:id 105, :ident :release/day, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The day of the release"}
;  #:db{:id 106, :ident :release/artistCredit, :valueType :db.type/string, :cardinality :db.cardinality/one, :fulltext true, :doc "The string represenation of the artist(s) to be credited on the release"}
;  #:db{:id 107, :ident :release/artists, :valueType :db.type/ref, :cardinality :db.cardinality/many, :doc "The set of artists contributing to the release"}
;  #:db{:id 108, :ident :release/abstractRelease, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "This release is the physical manifestation of the\n  associated abstract release, e.g. the the 1984 US vinyl release of\n  \"The Wall\" by Columbia, as opposed to the 2000 US CD release of\n  \"The Wall\" by Capitol Records."}
;  #:db{:id 109, :ident :release/status, :valueType :db.type/string, :cardinality :db.cardinality/one, :index true, :doc "The status of the release"}
;  #:db{:id 110, :ident :medium/tracks, :valueType :db.type/ref, :cardinality :db.cardinality/many, :isComponent true, :doc "The set of tracks found on this medium"}
;  #:db{:id 111, :ident :medium/format, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "The format of the medium. An enum with lots of possible values"}
;  #:db{:id 112, :ident :medium/position, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The position of this medium in the release relative to the other media, i.e. disc 1"}
;  #:db{:id 113, :ident :medium/name, :valueType :db.type/string, :cardinality :db.cardinality/one, :fulltext true, :doc "The name of the medium itself, distinct from the name of the release"}
;  #:db{:id 114, :ident :medium/trackCount, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The total number of tracks on the medium"}
;  #:db{:id 115, :ident :track/artists, :valueType :db.type/ref, :cardinality :db.cardinality/many, :doc "The artists who contributed to the track"}
;  #:db{:id 116, :ident :track/artistCredit, :valueType :db.type/string, :cardinality :db.cardinality/one, :fulltext true, :doc "The artists who contributed to the track"}
;  #:db{:id 117, :ident :track/position, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The position of the track relative to the other tracks on the medium"}
;  #:db{:id 118, :ident :track/name, :valueType :db.type/string, :cardinality :db.cardinality/one, :index true, :fulltext true, :doc "The track name"}
;  #:db{:id 119, :ident :track/duration, :valueType :db.type/long, :cardinality :db.cardinality/one, :index true, :doc "The duration of the track in msecs"}
;  #:db{:id 7, :ident :db/system-tx, :valueType :db.type/keyword, :cardinality :db.cardinality/many}
;  #:db{:id 8, :ident :db.sys/partiallyIndexed, :valueType :db.type/boolean, :cardinality :db.cardinality/one, :doc "System-assigned attribute set to true for transactions not fully incorporated into the index"}
;  #:db{:id 9, :ident :db.sys/reId, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "System-assigned attribute for an id e in the log that has been changed to id v in the index"}
;  #:db{:id 10, :ident :db/ident, :valueType :db.type/keyword, :cardinality :db.cardinality/one, :unique :db.unique/identity, :doc "Attribute used to uniquely name an entity."}
;  #:db{:id 11, :ident :db.install/partition, :valueType :db.type/ref, :cardinality :db.cardinality/many, :doc "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will install v as a partition."}
;  #:db{:id 12, :ident :db.install/valueType, :valueType :db.type/ref, :cardinality :db.cardinality/many, :doc "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will install v as a value type."}
;  #:db{:id 13, :ident :db.install/attribute, :valueType :db.type/ref, :cardinality :db.cardinality/many, :doc "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will install v as an attribute."}
;  #:db{:id 14, :ident :db.install/function, :valueType :db.type/ref, :cardinality :db.cardinality/many, :doc "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will install v as a data function."}
;  #:db{:id 15, :ident :db/excise, :valueType :db.type/ref, :cardinality :db.cardinality/one}
;  #:db{:id 16, :ident :db.excise/attrs, :valueType :db.type/ref, :cardinality :db.cardinality/many}
;  #:db{:id 17, :ident :db.excise/beforeT, :valueType :db.type/long, :cardinality :db.cardinality/one}
;  #:db{:id 18, :ident :db.excise/before, :valueType :db.type/instant, :cardinality :db.cardinality/one}
;  #:db{:id 19, :ident :db.alter/attribute, :valueType :db.type/ref, :cardinality :db.cardinality/many, :doc "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will alter the definition of existing attribute v."}
;  #:db{:id 39, :ident :fressian/tag, :valueType :db.type/keyword, :cardinality :db.cardinality/one, :index true, :doc "Keyword-valued attribute of a value type that specifies the underlying fressian type used for serialization."}
;  #:db{:id 40, :ident :db/valueType, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "Property of an attribute that specifies the attribute's value type. Built-in value types include, :db.type/keyword, :db.type/string, :db.type/ref, :db.type/instant, :db.type/long, :db.type/bigdec, :db.type/boolean, :db.type/float, :db.type/uuid, :db.type/double, :db.type/bigint,  :db.type/uri."}
;  #:db{:id 41, :ident :db/cardinality, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "Property of an attribute. Two possible values: :db.cardinality/one for single-valued attributes, and :db.cardinality/many for many-valued attributes. Defaults to :db.cardinality/one."}
;  #:db{:id 42, :ident :db/unique, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "Property of an attribute. If value is :db.unique/value, then attribute value is unique to each entity. Attempts to insert a duplicate value for a temporary entity id will fail. If value is :db.unique/identity, then attribute value is unique, and upsert is enabled. Attempting to insert a duplicate value for a temporary entity id will cause all attributes associated with that temporary id to be merged with the entity already in the database. Defaults to nil."}
;  #:db{:id 43, :ident :db/isComponent, :valueType :db.type/boolean, :cardinality :db.cardinality/one, :doc "Property of attribute whose vtype is :db.type/ref. If true, then the attribute is a component of the entity referencing it. When you query for an entire entity, components are fetched automatically. Defaults to nil."}
;  #:db{:id 44, :ident :db/index, :valueType :db.type/boolean, :cardinality :db.cardinality/one, :doc "Property of an attribute. If true, create an AVET index for the attribute. Defaults to false."}
;  #:db{:id 45, :ident :db/noHistory, :valueType :db.type/boolean, :cardinality :db.cardinality/one, :doc "Property of an attribute. If true, past values of the attribute are not retained after indexing. Defaults to false."}
;  #:db{:id 46, :ident :db/lang, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "Attribute of a data function. Value is a keyword naming the implementation language of the function. Legal values are :db.lang/java and :db.lang/clojure"}
;  #:db{:id 47, :ident :db/code, :valueType :db.type/string, :cardinality :db.cardinality/one, :fulltext true, :doc "String-valued attribute of a data function that contains the function's source code."}
;  #:db{:id 50, :ident :db/txInstant, :valueType :db.type/instant, :cardinality :db.cardinality/one, :index true, :doc "Attribute whose value is a :db.type/instant. A :db/txInstant is recorded automatically with every transaction."}
;  #:db{:id 51, :ident :db/fulltext, :valueType :db.type/boolean, :cardinality :db.cardinality/one, :doc "Property of an attribute. If true, create a fulltext search index for the attribute. Defaults to false."}
;  #:db{:id 52, :ident :db/fn, :valueType :db.type/fn, :cardinality :db.cardinality/one, :doc "A function-valued attribute for direct use by transactions and queries."}
;  #:db{:id 62, :ident :db/doc, :valueType :db.type/string, :cardinality :db.cardinality/one, :fulltext true, :doc "Documentation string for an entity."}
;  #:db{:id 63, :ident :country/name, :valueType :db.type/string, :cardinality :db.cardinality/one, :unique :db.unique/value, :doc "The name of the country"}
;  #:db{:id 64, :ident :language/name, :valueType :db.type/string, :cardinality :db.cardinality/one, :unique :db.unique/value, :doc "The name of the written and spoken language"}
;  #:db{:id 65, :ident :script/name, :valueType :db.type/string, :cardinality :db.cardinality/one, :unique :db.unique/value, :doc "Name of written character set, e.g. Hebrew, Latin, Cyrillic"}
;  #:db{:id 66, :ident :artist/gid, :valueType :db.type/uuid, :cardinality :db.cardinality/one, :unique :db.unique/identity, :doc "The globally unique MusicBrainz ID for an artist"}
;  #:db{:id 67, :ident :artist/name, :valueType :db.type/string, :cardinality :db.cardinality/one, :index true, :fulltext true, :doc "The artist's name"}
;  #:db{:id 68, :ident :artist/sortName, :valueType :db.type/string, :cardinality :db.cardinality/one, :index true, :doc "The artist's name for use in alphabetical sorting, e.g. Beatles, The"}
;  #:db{:id 69, :ident :artist/type, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "Enum, one of :artist.type/person, :artist.type/other, :artist.type/group."}
;  #:db{:id 70, :ident :artist/gender, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "Enum, one of :artist.gender/male, :artist.gender/female, or :artist.gender/other."}
;  #:db{:id 71, :ident :artist/country, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "The artist's country of origin"}
;  #:db{:id 72, :ident :artist/startYear, :valueType :db.type/long, :cardinality :db.cardinality/one, :index true, :doc "The year the artist started actively recording"}
;  #:db{:id 73, :ident :artist/startMonth, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The month the artist started actively recording"}
;  #:db{:id 74, :ident :artist/startDay, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The day the artist started actively recording"}
;  #:db{:id 75, :ident :artist/endYear, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The year the artist stopped actively recording"}
;  #:db{:id 76, :ident :artist/endMonth, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The month the artist stopped actively recording"}
;  #:db{:id 77, :ident :artist/endDay, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The day the artist stopped actively recording"}
;  #:db{:id 78, :ident :abstractRelease/gid, :valueType :db.type/uuid, :cardinality :db.cardinality/one, :unique :db.unique/identity, :doc "The globally unique MusicBrainz ID for the abstract release"}
;  #:db{:id 79, :ident :abstractRelease/name, :valueType :db.type/string, :cardinality :db.cardinality/one, :index true, :doc "The name of the abstract release"}
;  #:db{:id 80, :ident :abstractRelease/type, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "Enum, one\n  of: :release.type/album, :release.type/single, :release.type/ep, :release.type/audiobook,\n  or :release.type/other"}
;  #:db{:id 81, :ident :abstractRelease/artists, :valueType :db.type/ref, :cardinality :db.cardinality/many, :doc "The set of artists contributing to the abstract release"}
;  #:db{:id 82, :ident :abstractRelease/artistCredit, :valueType :db.type/string, :cardinality :db.cardinality/one, :fulltext true, :doc "The string represenation of the artist(s) to be credited on the abstract release"}
;  #:db{:id 83, :ident :label/gid, :valueType :db.type/uuid, :cardinality :db.cardinality/one, :unique :db.unique/identity, :doc "The globally unique MusicBrainz ID for the record label"}
;  #:db{:id 84, :ident :label/name, :valueType :db.type/string, :cardinality :db.cardinality/one, :index true, :fulltext true, :doc "The name of the record label"}
;  #:db{:id 85, :ident :label/sortName, :valueType :db.type/string, :cardinality :db.cardinality/one, :index true, :doc "The name of the record label for use in alphabetical sorting"}
;  #:db{:id 86, :ident :label/type, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "Enum, one of :label.type/distributor, :label.type/holding,\n  :label.type/production, :label.type/originalProduction,\n  :label.type/bootlegProduction, :label.type/reissueProduction, or\n  :label.type/publisher."}
;  #:db{:id 87, :ident :label/country, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "The country where the record label is located"}
;  #:db{:id 88, :ident :label/startYear, :valueType :db.type/long, :cardinality :db.cardinality/one, :index true, :doc "The year the label started business"}
;  #:db{:id 89, :ident :label/startMonth, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The month the label started business"}
;  #:db{:id 90, :ident :label/startDay, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The day the label started business"}
;  #:db{:id 91, :ident :label/endYear, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The year the label stopped business"}
;  #:db{:id 92, :ident :label/endMonth, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The month the label stopped business"}
;  #:db{:id 93, :ident :label/endDay, :valueType :db.type/long, :cardinality :db.cardinality/one, :doc "The day the label stopped business"}
;  #:db{:id 94, :ident :release/gid, :valueType :db.type/uuid, :cardinality :db.cardinality/one, :unique :db.unique/identity, :doc "The globally unique MusicBrainz ID for the release"}
;  #:db{:id 95, :ident :release/country, :valueType :db.type/ref, :cardinality :db.cardinality/one, :doc "The country where the recording was released"}
;  #:db{:id 96, :ident :release/labels, :valueType :db.type/ref, :cardinality :db.cardinality/many, :doc "The label on which the recording was released"})
  ,) ; nil
