^{::clerk/visibility {:code :hide}}
(ns e04_steps_form_submit)

^{::clerk/visibility {:code :hide}}
(require '[nextjournal.clerk :as clerk])
^{::clerk/visibility {:code :hide}}
(require '[clojure.walk :refer [postwalk]])

; s01: Öğrenci boş bir Student Registration formu açar.

^{::clerk/visibility {:code :hide}}
(clerk/table
  {:name ["<input>"]
   :department ["<input>"]})

; s02: Öğrenci formu doldurur ve gönderir.

(def form
  {:name "Can Ali"
   :department "Matematik"})

^{::clerk/visibility {:code :hide}}
(defn to-clerk [m]
  (postwalk
    #(if
       (or (string? %) (map? %))
       ;(string? %)
       (vector %)
       %)
    m))
^{::clerk/visibility {:code :hide}}
#_(to-clerk form)

^{::clerk/visibility {:code :hide}}
(clerk/table
  (to-clerk form))

^{::clerk/visibility {:code :hide}}
(comment
  ; map of string -> map of seq dönüşümü yapalım
  (def form
    {:name "Can Ali"
     :department "Matematik"})

  ; clerk map of seq objelerini tablo formatına çevirir
  ; burada map of string var. o yüzden hata verir
  ^{::clerk/visibility {:code :hide}}
  #_(clerk/table form)

  (update form :name vec)
  ;=> {:name [\C \a \n \space \A \l \i], :department "Matematik"}

  (update form :name vector)
  ;=> {:name ["Can Ali"], :department "Matematik"}

  ; bu işlemi tüm  property'ler için nasıl yaparız?
  (map #(update form % vector) (keys form))
  ;=> ({:name ["Can Ali"], :department "Matematik"} {:name "Can Ali", :department ["Matematik"]})

  (require '[clojure.walk :refer [postwalk]])

  (postwalk
    #(if
       (string? %)
       (vector %)
       %)
    form)
  ;=> {:name ["Can Ali"], :department ["Matematik"]}

  (defn to-clerk [m]
    (postwalk
      #(if
         (or (string? %) (map? %))
         (vector %)
         %)
      m))
  (to-clerk form)
  ;=> {:name ["Can Ali"], :department ["Matematik"]}

  ;end
  ,)

; a02: Departman ismi String değil, entity olarak kaydedilsin

; a01: id referansı verelim
(def form2
  {:name "Can Ali"
   :department 101})

; a02: hem id hem normal ismini belirtelim
(def form3
  {:name "Can Ali"
   :department [101 "Matematik"]})

; a03: hangi varlığa ref verdiğimizi de belirtelim
(def form4
  {:name "Can Ali"
   :department {:department/id 101
                :department/title "Matematik"}})

; bunu clerk table olarak nasıl gösterir?
(def form5
  {:name       ["Can Ali"]
   :department [{:department/id 101
                 :department/title "Matematik"}]})
^{::clerk/visibility {:code :hide}}
(clerk/table
  form5)

^{::clerk/visibility {:code :hide}}
(comment
  (defn to-clerk [m]
    (postwalk
      #(if
         (or (string? %) (map? %))
         ;(string? %)
         (vector %)
         %)
      m))
  (to-clerk form4)
  ;=> [{:name ["Can Ali"], :department [#:department{:id 101, :title ["Matematik"]}]}]

  ,)

; s03: Sistem bu formu [Student] tablosuna kaydeder.

