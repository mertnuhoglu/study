(ns mertnuhoglu.day-of-datomic.cloud.graph)

(require '[datomic.client.api :as d])

;; inspired by http://docs.neo4j.org/chunked/stable/cypher-cookbook-hyperedges.html

(def client (d/client {:server-type :dev-local
                       :system "datomic-samples"}))
(def conn (d/connect client {:db-name "graph"}))

(def db (d/db conn))

;; find roles for user and particular groups
(d/q '[:find (pull ?role [:role/name])
       :in $ ?e ?group
       :where
       [?e :hasRoleInGroups ?roleInGroup]
       [?roleInGroup :hasGroups ?group]
       [?roleInGroup :hasRoles ?role]]
  db [:user/name "User1"] [:group/name "Group2"])
;=> [[#:role{:name "Role2"}] [#:role{:name "Role3"}]]

;; find all groups and roles for a user
(d/q '[:find (pull ?group [:group/name]) (pull ?role [:role/name])
       :in $ ?e
       :where
       [?e :hasRoleInGroups ?roleInGroup]
       [?roleInGroup :hasGroups ?group]
       [?roleInGroup :hasRoles ?role]]
  db [:user/name "User2"])
;=>
;[[#:group{:name "Group1"} #:role{:name "Role5"}]
; [#:group{:name "Group1"} #:role{:name "Role2"}]
; [#:group{:name "Group2"} #:role{:name "Role4"}]
; [#:group{:name "Group2"} #:role{:name "Role3"}]
; [#:group{:name "Group3"} #:role{:name "Role6"}]
; [#:group{:name "Group3"} #:role{:name "Role5"}]]

(def rules '[[(user-roles-in-groups ?user ?role ?group)
              [?user :hasRoleInGroups ?roleInGroup]
              [?roleInGroup :hasGroups ?group]
              [?roleInGroup :hasRoles ?role]]])

;; find all groups and roles for a user, using a datalog rulea
(d/q '[:find (pull ?group [:group/name]) (pull ?role [:role/name])
       :in $ % ?user
       :where (user-roles-in-groups ?user ?role ?group)]
  db rules [:user/name "User1"])

;; find common groups based on shared roles, counting shared roles
(d/q '[:find (pull ?group [:group/name]) (count ?role)
       :in $ % ?user1 ?user2
       :where (user-roles-in-groups ?user1 ?role ?group)
       (user-roles-in-groups ?user2 ?role ?group)]
  db rules [:user/name "User1"] [:user/name "User2"])

