(ns main.pathom02
  (:require
    [cheshire.core :as json]
    [com.wsscode.pathom3.connect.operation :as pco]))

(pco/defresolver ip->lat-long
  [{:keys [ip]}]
  {::pco/output [:latitude :longitude]}
  (-> (slurp (str "https://get.geojs.io/v1/ip/geo/" ip ".json"))
    (json/parse-string keyword)
    (select-keys [:latitude :longitude])))

(defn main [{:keys [ip]}]
  (println "Request temperature for the IP" ip))

(comment
  (ip->lat-long {:ip "198.29.213.3"})
  ; => {:longitude "-88.0569", :latitude "41.5119"}
  (let [ip "198.29.213.3"]
    (slurp (str "https://get.geojs.io/v1/ip/geo/" ip ".json")))
  ;=> "{\"organization_name\":\"CONSTELLATION-ENERGY\",\"region\":\"Illinois\",\"accuracy\":1000,\"asn\":23503,\"organization\":\"AS23503 CONSTELLATION-ENERGY\",\"timezone\":\"America\\/Chicago\",\"longitude\":\"-88.0608\",\"country_code3\":\"USA\",\"area_code\":\"0\",\"ip\":\"198.29.213.3\",\"city\":\"Joliet\",\"country\":\"United States\",\"continent_code\":\"NA\",\"country_code\":\"US\",\"latitude\":\"41.5144\"} "
  )
