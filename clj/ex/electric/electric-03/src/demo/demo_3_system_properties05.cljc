(ns demo.demo-3-system-properties05
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

(e/defn App []
  (e/client
    (dom/h1 (dom/text "JVM System Properties search"))
    ; @CHANGE:
    ; başlangıç değeri verdik atoma
    (let [!search (atom "java")
          search (e/watch !search)]
      (e/server
        (let [system-props (e/offload #(sort-by key (system-properties search)))
              matched-count (count system-props)]
          (e/client
            (dom/div (dom/props {:style {:color "gray"}}) (dom/text matched-count " matches"))
            (ui/input search (e/fn [v] (reset! !search v))
              (dom/props {:type "search" :placeholder "java.home"}))
            ; ilk sayfa açıldığında:
            ;   search -> input kutusunun içeriği olarak atanıyor
            ;
            ; kullanıcı input kutusuna yazı yazdığında:
            ;   input -> v -> !search -> search -> input
            (dom/table
              (e/server
                (e/for-by first [[k v] system-props]
                  (e/client
                    (dom/tr
                      (dom/td (dom/text k))
                      (dom/td (dom/props {:style {:white-space :nowrap}}) (dom/text v)))))))))))))

(comment
  ; @CHANGE: filter fonksiyonunu nasıl daha sade bir şekilde ifade edebiliriz?
  ; daha sade bir formunu bulamadım
  (def ?s "java.vendor")
  (take 3 (system-properties ""))
  (->> (System/getProperties)
    (filter (fn [[k v]] (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s)))))
    (into {}))

  ; end
  ,)