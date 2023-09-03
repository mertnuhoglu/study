(ns ex.e08b)

; aynı veritabanını fulcro formatında yapmayı deneyelim
(def db-sample {
                :person                  {1 {:person/id 1 :name "dan" :surname "stone" :joindate "01.06.2022" :experience :experience/starter :loyalitylevel :loyality-level/zero-one-years :worktime :worktime/internship :managers {:manager/id 1}}
                                          2 {:person/id 2 :name "dave" :surname "jhons" :joindate "04.04.2021" :experience :experience/medior :loyalitylevel :loyality-level/one-two-years :worktime :worktime/full-time :managers {:manager/id 1}}
                                          3 {:person/id 3 :name "patrick" :surname "square pant" :joindate "09.01.2022" :experience :experience/senior :loyalitylevel :loyality-level/zero-one-years :worktime :worktime/part-time :managers {:manager/id 1}}
                                          9 {:person/id 9 :name "rich" :surname "hickey" :joindate "04.04.2016" :experience :experience/lead :loyalitylevel :loyality-level/more-than-seven-years :worktime :worktime/part-time}}


                :employees/developerteam {:frontend [[:person/id 1] [:person/id 2] [:person/id 3]]}

                :employees/managers      {:manager/id 1 :manager/person [:person/id 9]}

                :relations/experience    {:experience/starter "0-1"
                                          :experience/medior  "1-3"
                                          :experience/senior  "3-6"
                                          :experience/lead    "6+"}


                :relations/loyalitylevel {:loyality-level/zero-one-years        "0-1 year(s)"
                                          :loyality-level/one-two-years         "1-2 year(s)"
                                          :loyality-level/three-five-years      "3-5 year(s)"
                                          :loyality-level/five-seven-years      "5-7 year(s)"
                                          :loyality-level/more-than-seven-years "7+ year(s)"}

                :relations/worktime      {:worktime/full-time  "full time"
                                          :worktime/part-time  "part time"
                                          :worktime/internship "internship"}})



(defn get-person-by-id-2
  "get person by :person/id value"
  [id]
  (get-in db-sample [:person id :managers :manager/id]))
(get-person-by-id-2 1)
; burada get-in fonksiyonunun daha pratik bir kullanım imkanı verdiğini görüyoruz

