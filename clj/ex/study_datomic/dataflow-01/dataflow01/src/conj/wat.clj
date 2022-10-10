(ns conj.wat
  (:require
    [clj-3df.core :as df :use [exec!]]))

(def schema
  {:system/time {:db/valueType :Number}
   
   :read-access? {:db/valueType :Eid}
   
   :branch/subsidy {:db/valueType :Eid}
   :branch/account {:db/valueType :Eid}
   
   :loan/amount {:db/valueType :Number}
   :loan/from {:db/valueType :Eid}
   :loan/to {:db/valueType :Eid}
   :loan/payday {:db/valueType :Number}
   
   :person/name {:db/valueType :String}
   :person/opt-in? {:db/valueType :Bool}})

;; entities
(do
  (def system 0)
  (def mabel 1)
  (def dipper 2)
  (def soos 3)
  (def loan-1 4)
  (def loan-2 5)
  (def loan-3 6)
  (def loan-4 7)
  (def branch-parent 8)
  (def branch-child 9)
  (def user 10))
  
(def initial-data
  ;; start at day 1
  [{:db/id system :system/time 1}
   {:db/id branch-parent :branch/subsidy branch-child}
   {:db/id mabel 
    :person/name "Mabel"
    :person/opt-in? true}
   {:db/id dipper
    :person/name "Dipper"
    :person/opt-in? true}
   ;; soor has not opted-in yet
   {:db/id soos
    :person/name "Soos"}
   
   ;; all person accounts are part of the same subsidy
   [:db/add branch-child :branch/account mabel]
   [:db/add branch-child :branch/account dipper]
   [:db/add branch-child :branch/account soos]
   
   ;; initially we have a read permission for the child branch
   [:db/add user :read-access? branch-child]
   
   ;; Mabel has issued to loans to Soos and Dipper
   ;; both are due yesterday
   {:db/id loan-1
    :loan/from mabel
    :loan/to soos
    :loan/amount 50
    :loan/payday 0}
   {:db/id loan-2
    :loan/from mabel
    :loan/to dipper
    :loan/amount 75
    :loan/payday 0}])
   
;; when is a loan considered due?
(def rules
  '[[(due? ?loan)
     [?loan :loan/payday ?payday]
     [?system :system/time ?time]
     [(<= ?payday ?time)]]])
   
(comment
  ;; connect to 3DF cluster
  (do
    (def conn (df/debug-conn "ws://127.0.0.1:6262")))
  (do
    (println "\n--- initial data ---")
    (exec! conn (df/transact db initial-data)))
  
  ;;->
  ;; [conjbank/opt-ins [[[1 Mabel] 1 0] [[2 Dipper] 1 0]]]

  ;; queries are named and made available globally
  (exec! conn
    (def/register-query
      db "conjbank/opt-ins"
      '[:find ?person ?name
        :where
        [?person :person/opt-in? true]
        [?branc :branch/account ?person]
        [?person :person/name ?name]
        (read? ?branch)]
      
      '[[(read? ?branch)
         [?e :read-access? ?branch]]
        
        [(read? ?branch)
         [?super :branch/subsidy ?branch]
         (read? ?super)]]))
  
  ;; simulate: someone retracts access rights of a child branch
  (do
    (println "\n--- we lose access on the child...")
    (exec! conn
           (df/transact
             db
             [[:db/retract user :read-access? branch-child]]))))
  
  ;; bunu yapınca otomatik olarak `opt-ins` koleksiyonunu tekrar alırız
  ;; burada aynı entity'ler dönüyor ama -1 ile. 
  ;; yani state tam tersi olmuş bu viewa ait olan
  ;; en son 1 ise timestamp anlamına geliyor
  ;; ->
  ;; [conjbank/opt-ins [[[1 Mabel] -1 1] [[2 Dipper] -1 1]]]
   
  
