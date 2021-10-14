;; Given an app with this client DB:
(def app
  (app/fulcro-app
    {:initial-db
     {:list/id   {:friends    {:list/people [[:person/id :me]]}
                  :partygoers {:list/people [[:person/id :me]]}}
      :person/id {:me         {:person/id :me :person/fname "Me"
                               :person/bff [[:person/id :me]]}}}}))

;; and this call (reusing the person-tree defined earlier):
(merge/merge-component!
  app
  Person ; = Jo, id 1
  person-tree
  :append  [:list/id :friends :list/people]
  :prepend [:list/id :partygoers :list/people]
  :replace [:person/id :me :person/bff 0]
  :replace [:best-person])

;; we get this Client DB:
{:list/id
 {:friends    {:list/people [[:person/id :me] [:person/id 1]]} ;
  :partygoers {:list/people [[:person/id 1] [:person/id :me]]}};
 :person/id
 {:me #:person{:id :me, :fname "Me", :bff [[:person/id 1]]}, ;
  1   #:person{:id 1,   :fname "Jo", :address [:address/id 11]}},
 :address/id {11 #:address{:id 11, :street "Elm Street 7"}},
 :best-person [:person/id 1]}                                ;
