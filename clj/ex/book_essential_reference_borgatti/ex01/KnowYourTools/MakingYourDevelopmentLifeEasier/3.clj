(require '[clojure.java.io :as io])
(require '[clojure.xml :as xml])
(require '[clojure.string :refer [split capitalize join]])
(require '[clojure.pprint :refer [cl-format]])

(defn- to-double [k m]
  (update-in m [k] #(Double/valueOf %)))

(defn parse [xml] ; <1>
  (with-open [xml-in (io/input-stream (.getBytes xml))]
    (->> (xml/parse xml-in)
         :content
         (map #(hash-map (:tag %) (first (:content %))))
         (into {})
         (to-double :currentBalance))))

(defn separate-words [s]
  (->> (split s #"(?=[A-Z])") ; <2>
       (map capitalize)       ; <3>
       (join " ")))

(defn format-decimals [v]
  (if (float? v)
    (cl-format nil "~$" v) ; <4>
    v))

(defn print-balance [xml]
  (let [balance (parse xml)
        ks (map (comp separate-words name) (keys balance))
        vs (map format-decimals (vals balance))]
    (zipmap ks vs))) ; <5>

(print-balance balance)
;; {"Account Id" 3764882, "Last Access" "20120121", "Current Balance" "80.12"}

(comment
  (def xml balance)
  xml
  ;; "<balance>\n    <accountId>3764882</accountId>\n    <lastAccess>20120121</lastAccess>\n    <currentBalance>80.12389</currentBalance>\n  </balance>"
  (with-open [xml-in (io/input-stream (.getBytes xml))]
    (->> (xml/parse xml-in)
         :content
         (map #(hash-map (:tag %) (first (:content %))))
         (into {})
         (to-double :currentBalance)))
  ;; {:accountId "3764882", :lastAccess "20120121", :currentBalance 80.12389}
  (with-open [xml-in (io/input-stream (.getBytes xml))]
    (->> (xml/parse xml-in)))
  ;; {:tag :balance,
  ;;  :attrs nil,
  ;;  :content
  ;;  [{:tag :accountId, :attrs nil, :content ["3764882"]}
  ;;   {:tag :lastAccess, :attrs nil, :content ["20120121"]}
  ;;   {:tag :currentBalance, :attrs nil, :content ["80.12389"]}]}
  (with-open [xml-in (io/input-stream (.getBytes xml))]
    (->> (xml/parse xml-in)
         :content))
  ;; [{:tag :accountId, :attrs nil, :content ["3764882"]}
  ;;  {:tag :lastAccess, :attrs nil, :content ["20120121"]}
  ;;  {:tag :currentBalance, :attrs nil, :content ["80.12389"]}]
  (with-open [xml-in (io/input-stream (.getBytes xml))]
    (->> (xml/parse xml-in)
         :content
         (map #(hash-map (:tag %) (first (:content %))))))
  ;; ({:accountId "3764882"}
  ;;  {:lastAccess "20120121"}
  ;;  {:currentBalance "80.12389"})
  (def balance (parse xml))
  ;; {:accountId "3764882", :lastAccess "20120121", :currentBalance 80.12389}
  (def ks (map (comp separate-words name) (keys balance)))
  ;; ("Account Id" "Last Access" "Current Balance")
  (def vs (map format-decimals (vals balance)))
  ;; ("3764882" "20120121" "80.12")
  (zipmap ks vs)
  ;; {"Account Id" "3764882",
  ;;  "Last Access" "20120121",
  ;;  "Current Balance" "80.12")


  ,)
