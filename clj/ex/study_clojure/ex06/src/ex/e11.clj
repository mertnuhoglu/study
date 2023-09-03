(ns ex.e11
  (:require [portal.api :as p]
          [clojure.java.io :refer [reader] :as io]
          [clojure.edn :as edn]
          [clojure.data :refer [diff]]
          [clojure.pprint :refer [pprint]]))


; Barış'la Clojure Egzersizleri
; Tarih: 20230131
; rfr: video/20230131-mert-clj-egzersiz-12.mp4

; Portal kullanımı
; Herhangi bir clojure programını çalıştırdığında, ilk başta user namespace run edilir
; Bunun için doğrudan src/ klasörü altında `user.clj` dosyasını oluşturmak yeterli
; (ns user)

; buraya koyduğun tüm kodlar ilk başta run edilir
; dolayısıyla bizim data browser (navigator) araçlar user ns'ye konulur ki, program başlar başlamaz açılsın diye
; [(87) Thinking with Portal (by Chris Badahdah) - YouTube](https://www.youtube.com/watch?v=Tj-iyDo3bq0&t=337s)

; bu data browserlar ne işe yarıyor?
; bizim clj'da çok kompleks data yapılarıyla çalıştığımız için, bu elimizdeki objelerin doğru dataya sahip olup olmadığını kontrol etmemiz gerekiyor
; chrome'da js yazan insanlar, chrome > debugging tools > console > herhangi bir objeyi tıkladıklarında o objenin iç yapısını inceleyebiliyorlar
; örnek: googleda: chrome debugging tools inspect object data structure

; kullanım örneği:
; ex06/src/user.clj

(def portal (p/open))

;; Add portal as a tap> target
(p/tap)
(tap> :hello)
(tap> {:a 1 :b 2})

(def db-sample {
                :person                  [{:person/id 1 :name "dan" :surname "stone" :joindate "01.06.2022" :experience :experience/starter :loyalitylevel :loyality-level/zero-one-years :worktime :worktime/internship :managers {:manager/id 1}}
                                          {:person/id 2 :name "dave" :surname "jhons" :joindate "04.04.2021" :experience :experience/medior :loyalitylevel :loyality-level/one-two-years :worktime :worktime/full-time :managers {:manager/id 1}}
                                          {:person/id 3 :name "patrick" :surname "square pant" :joindate "09.01.2022" :experience :experience/senior :loyalitylevel :loyality-level/zero-one-years :worktime :worktime/part-time :managers {:manager/id 1}}
                                          {:person/id 9 :name "rich" :surname "hickey" :joindate "04.04.2016" :experience :experience/lead :loyalitylevel :loyality-level/more-than-seven-years :worktime :worktime/part-time}]


                :employees/developerteam [{:frontend [[:person/id 1] [:person/id 2] [:person/id 3]]}]


                :employees/managers      [{
                                           :manager/id 1 :manager/person [:person/id 9]}]



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

(tap> db-sample)
(tap> :db-sample)
