(require 'app.parser)

(app.parser/api-parser [{[:list/id :friends] [:list/id]}])
=> {[:list/id :friends] {:list/id :friends}}

(app.parser/api-parser [{[:person/id 1] [:person/name]}])
=> {[:person/id 1] {:person/name "Sally"}}

(app.parser/api-parser [{[:list/id :friends] [:list/id {:list/people [:person/name]}]}])
=> {[:list/id :friends] {:list/id :friends, :list/people [{:person/name "Sally"} {:person/name "Joe"}]}}
