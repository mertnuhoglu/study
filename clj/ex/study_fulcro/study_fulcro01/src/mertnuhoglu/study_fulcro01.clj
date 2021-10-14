(ns mertnuhoglu.study-fulcro01
  (:require
    [com.fulcrologic.fulcro.dom :as dom :refer [div table td tr th tbody]]
    [com.fulcrologic.fulcro.routing.legacy-ui-routers :as r :refer [defsc-router]]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [taoensso.timbre :as log]))

;; study: /Users/mertnuhoglu/codes/clj/content/fulcro-developer-guide/src/book/book/queries/union_example_1.cljs

(comment
  (r/router-instruction :detail-router [:param/kind :param/id])
  ;=> {:target-router :detail-router, :target-screen [:param/kind :param/id]}
  (r/make-route :detail [(r/router-instruction :detail-router [:param/kind :param/id])])
  ;=> {:name :detail, :instructions [{:target-router :detail-router, :target-screen [:param/kind :param/id]}]}
  (r/routing-tree
    (r/make-route :detail [(r/router-instruction :detail-router [:param/kind :param/id])]))
  ;#:com.fulcrologic.fulcro.routing.legacy-ui-routers{:routing-tree {:detail [{:target-router :detail-router,
  ;                                                                            :target-screen [:param/kind :param/id]}]}}
  (merge
    (r/routing-tree
      (r/make-route :detail [(r/router-instruction :detail-router [:param/kind :param/id])]))/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20211006_213510.jpg
    {:item-list   ""
     :item-detail ""})
  ;=>
  ;{:com.fulcrologic.fulcro.routing.legacy-ui-routers/routing-tree {:detail [{:target-router :detail-router,
  ;                                                                           :target-screen [:param/kind :param/id]}]},
  ; :item-list "",
  ; :item-detail ""}

  (r/route-to {:handler :detail :route-params {:kind "kind" :id "id"}})
  ;(com.fulcrologic.fulcro.routing.legacy-ui-routers/route-to {:handler :detail, :route-params {:kind "kind", :id "id"}})

,)