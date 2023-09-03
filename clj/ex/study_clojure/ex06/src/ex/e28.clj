(ns ex.e28)

; Tarih: 20230525

(def departments
  '({:db/id 92358976733259, :department/id 100, :department/title "Matematik"}
    {:db/id 92358976733260, :department/id 200, :department/title "Fizik"}
    {:db/id 92358976733261, :department/id 300, :department/title "Sosyoloji"}))


(def dt {:id [] :title []})

(map #(assoc-in dt [:id] (% :department/id)) departments)
;=> ({:id 100, :title []} {:id 200, :title []} {:id 300, :title []})

; id değerlerini vektör içine almak istiyoruz
(def a [])
(def bs [10 20])
; replace etmek için
(into a bs)
;=> [10 20]
; append etmek için: merge
(conj a bs)
;=> [[10 20]]
(cons a bs)
;=> ([] 10 20)

(apply conj a bs)
;=> [10 20]
(conj a 10 20)
;=> [10 20]
