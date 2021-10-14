(ns main.pathom04
  (:require
    [cheshire.core :as json]
    [com.wsscode.pathom3.connect.indexes :as pci]
    [com.wsscode.pathom3.connect.operation :as pco]
    [com.wsscode.pathom3.interface.smart-map :as psm]))

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

(pco/defresolver woeid->temperature
  [{:keys [woeid]}]
  {:temperature
   (-> (slurp (str "https://www.metaweather.com/api/location/" woeid))
     (json/parse-string keyword)
     :consolidated_weather
     first
     :the_temp)})

(comment
  (woeid->temperature {:woeid 2379574})
  ; => {:temperature 4.529999999999999}
  (-> {:ip "198.29.213.3"}
    ip->lat-long
    latlong->woeid
    woeid->temperature)
  ; => {:temperature 4.529999999999999}
,)

(def env
  (pci/register [ip->lat-long
                 latlong->woeid
                 woeid->temperature]))

(comment
  ; Using the indexes we generated at the name of env,
  ; we can do the same processing without mentioning any resolver name, using a Smart Map:
  (-> (psm/smart-map env {:ip "198.29.213.3"})
    :temperature)
  ; 5.24
,)

(defn main [args]
  ; start smart maps with call args
  (let [sm (psm/smart-map env args)]
    (println (str "It's currently " (:temperature sm) "C at " (pr-str args)))))