(ns holyjak.fulcro-exercises.impl
  (:require
    [holyjak.fulcro-exercises.mock-server :refer [mock-remote]]
    [clojure.string :as str]
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]))

(declare hints)
(defonce hints-shown (atom nil))

(defn hint [exercise-nr]
  {:pre [(int? exercise-nr)
         (not (neg? exercise-nr))]}
  (let [exercise-hints   (get hints exercise-nr)
        shown-previously (get @hints-shown exercise-nr 0)
        next?            (< shown-previously (count exercise-hints))
        more?            (not= (inc shown-previously) (count exercise-hints))
        printit!         #(println (str/join "\n\n" %))]
    (cond
      (nil? exercise-hints) "Sorry, no hints available for this exercise"

      next?
      (do (swap! hints-shown update exercise-nr (fnil inc 0))
        (cond-> (subvec exercise-hints shown-previously (inc shown-previously))
          more? (conj "Repeat for more...")
          true printit!))

      :else
      (printit! exercise-hints))))

(defonce current-app (atom nil))

(defn show-client-db
  "Print the current content of Fulcro's Client DB, if any."
  []
  (some-> @current-app
    ::app/state-atom
    deref
    cljs.pprint/pprint))

(defn config-and-render!
  "Renders the given root component, also creating a new Fulcro app for it.

  Args:
  - `RootComponent` - the class of the component to render
  - `opts` - options, including:
    - `:resolvers` - a sequence of Pathom resolvers; if provided, a Pathom 'backend'
       (though running in the browser) with these resolvers will be set up
    - `:initial-db` - the initial value of the client DB (normalized)

  Return the new Fulcro app."
  ([RootComponent] (config-and-render! RootComponent nil))
  ([RootComponent {:keys [initial-db resolvers]}]
   (let [current-root? (= (some-> @current-app app/root-class .-name)
                         (.-name RootComponent))
         remotes       (some-> resolvers seq mock-remote)
         app           (if current-root? @current-app (app/fulcro-app
                                                        {:initial-db initial-db
                                                         :remotes    remotes}))]

     (when remotes
       (println "LOG: Configured the remote" (-> remotes keys first) "for the Fulcro App"))
     (reset! current-app app)
     (println "LOG: Rendering" RootComponent "...")
     (app/mount! app RootComponent "app" {:initialize-state? (some? initial-db)})
     app)))

(defn refresh []
  (when-let [app @current-app]
    (app/mount! app
      (app/root-class app)
      "app")
    ;; As of Fulcro 3.3.0, this addition will help with stale queries when using dynamic routing:
    (comp/refresh-dynamic-queries! app)))


(comment
  (hint 4)
  (comp/component-name (app/root-class @current-app))

  ; app06
  (->  (deref current-app) (::app/state-atom) deref)
  ;=> #:fulcro.inspect.core{:app-id "app.app06/Root", :app-uuid #uuid"de1ab45b-bd4a-47ae-9acb-239b618d967b"}
  ,)



