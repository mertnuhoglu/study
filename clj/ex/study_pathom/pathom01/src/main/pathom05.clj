(ns main.pathom05
  (:require
    [com.wsscode.pathom3.connect.indexes :as pci]
    [com.wsscode.pathom3.connect.operation :as pco]
    [com.wsscode.pathom3.interface.eql :as p.eql]
    [com.wsscode.pathom3.interface.smart-map :as psm]
    [com.wsscode.pathom3.connect.built-in.resolvers :as pbir]
    [clojure.string :as str]))

; ex01:

(def user-birthdays
  {1 1969
   2 1954
   3 1986})

; defresolver is the main option to create new resolvers
(pco/defresolver user-birthday [{:keys [acme.user/id]}]
  {:acme.user/birth-year (get user-birthdays id)})

(comment
  (user-birthday user-birthdays)
  ;=> #:acme.user{:birth-year nil}
  (user-birthday {:acme.user/id 2})
  ;=> #:acme.user{:birth-year 1954}
,)

;; ex02:

; define a map for indexed access to user data
(def users-db
  {1 #:acme.user{:name     "Usuario 1"
                 :email    "user@provider.com"
                 :birthday "1989-10-25"}
   2 #:acme.user{:name     "Usuario 2"
                 :email    "anuser@provider.com"
                 :birthday "1975-09-11"}})

; pull stored user info from id
(pco/defresolver user-by-id [{:keys [acme.user/id]}]
  {::pco/output
   [:acme.user/name
    :acme.user/email
    :acme.user/birthday]}
  (get users-db id))

; extract birth year from birthday
(pco/defresolver birth-year [{:keys [acme.user/birthday]}]
  {:acme.user/birth-year (first (str/split birthday #"-"))})

(comment
  ; process needs an environment configuration
  (p.eql/process env
    ; we can provide some initial data
    {:acme.user/id 1}
    ; then we write an EQL query
    [:acme.user/birth-year])
  ; => {:acme.user/birth-year "1989"}
,)

; ex04: Invoking resolvers

;Like functions, you can call the resolvers directly:
(pco/defresolver extension [{::keys [path]}]
  {::path-ext (last (str/split path #"\."))})

(comment
  (extension {::path "foo.txt"})
  ; => {::path-ext "txt"}
,)

;Outside of the test context, you should have little reason to invoke a resolver directly. What sets resolvers apart from regular functions are that it contains data about its requirements and what it provides.

;This allows you to abstract away the operation names and focus only on the attributes.

;To illustrate the difference, let's compare the two approaches, first let's define the resolvers:

(def user-from-id
  (pbir/static-table-resolver `user-db :acme.user/id
    {1 #:acme.user{:name  "Trey Parker"
                   :email "trey@provider.com"}
     2 #:acme.user{:name  "Matt Stone"
                   :email "matt@provider.com"}}))

; avatar slug is a version of email, converting every non letter character into dashes
(pco/defresolver user-avatar-slug [{:acme.user/keys [email]}]
  {:acme.user/avatar-slug (str/replace email #"[^a-z0-9A-Z]" "-")})

(pco/defresolver user-avatar-url [{:acme.user/keys [avatar-slug]}]
  {:acme.user/avatar-url (str "http://avatar-images-host/for-id/" avatar-slug)})

(comment
  ;Consider the question: What is the avatar url of the user with id 1?

  ; opt01: traditional way
  (-> {:acme.user/id 1}
    (user-from-id)
    (user-avatar-slug)
    (user-avatar-url)
    :acme.user/avatar-url)
  ; => "http://avatar-images-host/for-id/trey-provider-com"

,)

; opt02: smart map
; first, we build an index for the relations expressed by the resolvers
(def indexes
  (pci/register [user-from-id
                 user-avatar-slug
                 user-avatar-url]))

(comment
  ; now instead of reference the functions, we let Pathom figure them out using the indexes
  (->> {:acme.user/id 2}
    (psm/smart-map indexes)
    :acme.user/avatar-url)
  ; => "http://avatar-images-host/for-id/matt-provider-com"

  ; to highlight the fact that we disregard the function, other ways where we can change
  ; the initial data and reach the same result:
  (->> {:acme.user/email "other@provider.com"}
    (psm/smart-map indexes)
    :acme.user/avatar-url)
  ; => "http://avatar-images-host/for-id/other-provider-com"

  (->> {:acme.user/avatar-slug "some-slogan"}
    (psm/smart-map indexes)
    :acme.user/avatar-url)
  ; => "http://avatar-images-host/for-id/some-slogan"
,)

;; ex05: Using the indexes

(comment
  ; index'leri farklı şekillerde oluşturabilirsin:

  ; create indexes for a single resolver
  (def single-registry (pci/register some-resolver))

  ; creating indexes with a few resolvers
  (def many-registry (pci/register [resolver-a resolver-b]))

  ; define a group of resolvers in a variable
  (def some-resolvers [res-a res-b])

  ; now use in combination with other mixes:
  (def more-registry
    (pci/register [some-resolvers
                   [other-group with-more
                    [deep-nested resolvers]]
                   and-more]))

  ; now using indexes as part of registry
  (def with-indexes (pci/register [some-resolvers many-registry]))

  ; all together now
  (def env
    (pci/register [[resolver-a resolver-b]
                   some-resolvers
                   many-registry]))
,)

; The indexes are part of the Pathom environment, for some operations just the indexes are enough.
; When you see indexes or env, consider they expect to have at least the indexes for the resolvers in the map.

;; ex06: Optional inputs

; use pco/?

(pco/defresolver user-display-name
  [{:user/keys [email name]}]
  {::pco/input [:user/email (pco/? :user/name)]}
  {:user/display-name (or name email)})

(def opt-env
  (pci/register
    [(pbir/static-table-resolver :user/id
       {1 {:user/email "user@example.com"}
        2 {:user/email "another@example.com"
           :user/name  "Sam"}})
     (pbir/constantly-resolver :all-users
       [{:user/id 1}
        {:user/id 2}])
     user-display-name]))

(p.eql/process opt-env
  [{:all-users [:user/display-name]}])
; => {:all-users
;     [{:user/display-name "user@example.com"} ; no name, use email
;      {:user/display-name "Sam"} ; has name (the optional), use it
;     ]}

;; ex07: Nested inputs

; This feature is handy when you need to make data aggregation based on indirect data.

(pco/defresolver top-players
  "Return maps with ids for the top players in a given game."
  []
  {:game/top-players
   [{:player/id 1}
    {:player/id 20}
    {:player/id 8}
    {:player/id 2}]})

(pco/defresolver player-by-id
  "Get player by id, in our case just generate some data based on the id."
  [{:keys [player/id]}]
  {:player/name (str "Player " id)
   :player/score (* id 50)})

; Now, for the task: how to make a resolver that computes the average score from the top players?

(pco/defresolver top-players-avg
  "Compute the average score for the top players using nested inputs."
  [{:keys [game/top-players]}]
  ; we have to make the nested input explicit
  {::pco/input [{:game/top-players [:player/score]}]}
  {:game/top-players-avg-score
   (let [score-sum (transduce (map :player/score) + 0 top-players)]
     (double (/ score-sum (count top-players))))})

(p.eql/process (pci/register [top-players player-by-id top-players-avg])
  [:game/top-players-avg-score])
; => #:game{:top-players-avg-score 387.5}

;; ex08: Parameters

(def mock-todos-db
  [{::todo-message "Write demo on params"
    ::todo-done?   true}
   {::todo-message "Pathom in Rust"
    ::todo-done?   false}])

; opt01: no parameters

; when resolvers have nested data, it's better always to specify it.
(pco/defresolver todos-resolver [env _]
  ; express nested shape
  {::pco/output
   [{::todos
     [::todo-message
      ::todo-done?]}]}
  {::todos mock-todos-db})

(def env (pci/register todos-resolver))

; list all todos
(p.eql/process env [::todos])

; opt02: with parameters

; add parameter:
; list undone todos:
(p.eql/process env ['(::todos {::todo-done? false})])

; nothing happens. we need to use the parameters to filter the data in the resolver

; opt03: with parameters in the resolver

(def mock-todos-db
  [{::todo-message "Write demo on params"
    ::todo-done?   true}
   {::todo-message "Pathom in Rust"
    ::todo-done?   false}])

(pco/defresolver todos-resolver [env _]
  {::pco/output
   [{::todos
     [::todo-message
      ::todo-done?]}]}

  {::todos
   ; pull params and filter
   (if-some [done? (get (pco/params env) ::todo-done?)]
     (->> mock-todos-db
       (filter #(= done? (::todo-done? %))))
     mock-todos-db)})

(def env (pci/register todos-resolver))

; list undone todos
(p.eql/process env
  '[(::todos {::todo-done? false})])
;=> #:main.pathom05{:todos (#:main.pathom05{:todo-message "Pathom in Rust", :todo-done? false})}

; opt04: use parameters with smart maps. call sm-preload!
(def sm (psm/smart-map env {}))

; preload using EQL params
;(psm/sm-preload! sm '[(::todos {::todo-done? false})])

;(::todos sm) ; => cached filtered li