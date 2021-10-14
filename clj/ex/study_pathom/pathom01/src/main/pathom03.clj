(ns main.pathom03
  (:require
    [cheshire.core :as json]
    [com.wsscode.pathom3.connect.operation :as pco]))

(pco/defresolver ip->lat-long
  [{:keys [ip]}]
  {::pco/output [:latitude :longitude]}
  (-> (slurp (str "https://get.geojs.io/v1/ip/geo/" ip ".json"))
    (json/parse-string keyword)
    (select-keys [:latitude :longitude])))

(pco/defresolver latlong->woeid
  [{:keys [latitude longitude]}]
  {:woeid
   (-> (slurp
         (str "https://www.metaweather.com/api/location/search/?lattlong="
           latitude "," longitude))
     (json/parse-string keyword)
     first
     :woeid)})

(defn main [{:keys [ip]}]
  (println "Request temperature for the IP" ip))

(comment
  (latlong->woeid {:longitude "-88.0569", :latitude "41.5119"})
  ; => {:woeid 2379574}
  ,)