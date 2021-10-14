=> (def state (com.fulcrologic.fulcro.application/current-state app.application/app))
#'cljs.user/state
=> (def query (com.fulcrologic.fulcro.components/get-query app.ui/Root))
#'cljs.user/query
=> (com.fulcrologic.fulcro.algorithms.denormalize/db->tree query state state)
{:friends {:list/id :friends,
           :list/label "Friends",
           :list/people [{:person/id 1, :person/name "Sally", :person/age 32}
                         {:person/id 2, :person/name "Joe", :person/age 22}]},
 :enemies {:list/id :enemies,
           :list/label "Enemies",
           :list/people [{:person/id 3, :person/name "Fred", :person/age 11}
                         {:person/id 4, :person/name "Bobby", :person/age 55}]}}

