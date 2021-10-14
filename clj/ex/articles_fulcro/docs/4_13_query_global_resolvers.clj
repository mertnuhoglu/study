(app.parser/api-parser [{:friends [:list/id {:list/people [:person/name]}]}])
=> {:friends {:list/id :friends, :list/people [{:person/name "Sally"} {:person/name "Joe"}]}}

