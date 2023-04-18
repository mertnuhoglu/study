;; # Hello, Clerk 👋

;; rfr: [[20230412-Clerk-Kurulum]]
;; Video: 20230414-mert-clj-56-clerk-kurulum-kurcalama.mp4

;; Clerk enables a _rich_, local-first notebook experience using standard Clojure namespaces.
(ns e01
  (:require [nextjournal.clerk :as clerk]))

;; Here's a visualization of unemployment in the US.
(clerk/vl
 {:width 700
  :height 400
  :data {:url "https://vega.github.io/vega-datasets/data/us-10m.json"
         :format {:type "topojson" :feature "counties"}}
  :transform [{:lookup "id"
               :from {:data {:url "https://vega.github.io/vega-datasets/data/unemployment.tsv"}
                      :key "id" 
                      :fields ["rate"]}}]
  :projection {:type "albersUsa"} 
  :mark "geoshape" 
  :encoding {:color {:field "rate" :type "quantitative"}}})

;; Koment satırlarına yazdığımız metinler düz metin olarak görünür.
;;
;; Real time güncelleme özelliği var.
;;
;; Koment kısmına yazdığınız her şey anında web sayfasında güncellenir.

;; Markdown'daki tüm formatlama notasyonlarını kullanabiliriz.

;; ## Heading veya Bir Başlık Oluşturalım

;; Bir alt seviye başlık oluşturalım

;; ### 3. Seviye Başlık

;; [Google Sayfası](https://google.com)

;; Kod formlarını da buraya gömebiliriz.

(+ 1 2)
;=> 3

(defn f [a b]
  (str "merhaba " a " ve " b))

(f "hüseyin" "mert")
;=> "merhaba hüseyin ve mert"
