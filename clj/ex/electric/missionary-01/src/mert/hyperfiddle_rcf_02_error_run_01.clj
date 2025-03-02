(ns mert.hyperfiddle-rcf-02-error-run-01
  (:require 
    [hyperfiddle.electric :as e]
    [hyperfiddle.rcf :as rcf :refer [tests tap % with]]))
;; spcs: Error: `hyperfiddle.electric/run` `hyperfiddle.rcf` || ((475098b8-97d4-4f91-9e17-5ca1e2d9c4d8))             
;; src: [o3](https://chatgpt.com/c/67ad9525-7ce8-8012-be89-5bf2090df3d3)

(def !x (atom 0))

(defn -main []
  (e/run
    (e/server  ;; or e/server, depending on your context
      (let [x (e/watch !x)]
        x))))

;; Uncomment the following line if you want to run -main automatically
(-main)

