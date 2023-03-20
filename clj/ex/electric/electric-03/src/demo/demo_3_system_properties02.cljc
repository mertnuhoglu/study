(ns demo.demo-3-system-properties02
  (:require
    [clojure.string :as str]
    [hyperfiddle.electric :as e]
    [hyperfiddle.electric-dom2 :as dom]
    [hyperfiddle.electric-ui4 :as ui]))

#?(:clj
   (defn system-properties [?s]
     (->> (System/getProperties)
       (filter (fn [[k v]] (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s)))))
       (into {}))))

; @CHANGE
; let yerine def kullanalım
; bu durumda uygulama ilk başta yükleniyor, ama filtreleme fonksiyonu çalışmıyor
(e/def !search (atom ""))
(e/def search (e/watch !search))

(e/defn App []
  (e/client
    (dom/h1 (dom/text "JVM System Properties search"))
    (let []
      (e/server
        (let [system-props (e/offload #(sort-by key (system-properties search)))
              matched-count (count system-props)]
          (e/client
            (dom/div (dom/props {:style {:color "gray"}}) (dom/text matched-count " matches"))
            (ui/input search (e/fn [v] (reset! !search v))
              (dom/props {:type "search" :placeholder "java.home"}))
            (dom/table
              (e/server
                (e/for-by first [[k v] system-props]
                  (e/client
                    (dom/tr
                      (dom/td (dom/text k))
                      (dom/td (dom/props {:style {:white-space :nowrap}}) (dom/text v)))))))))))))

(comment
  (take 3 (system-properties ""))
  ; end
  ,)