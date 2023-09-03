(ns ex.e04)

; Tarih: 20230118
; Barış'la clojure çalışması
; rfr: video/20230118-mert-clj-egzersiz-04.mp4

(def dbSample {
               :employees [:developerteam [:frontend [{:name "lodos" :surname "eskioğlu" :joindate "01.06.2022" :experience 1 :loyalitylevel 1 :worktime 3}
                                                      {:name "alp" :surname "boriçi" :joindate "04.04.2021" :experience 2 :loyalitylevel 2 :worktime 1}
                                                      {:name "emir" :surname "sürmeli" :joindate "09.01.2022" :experience 3 :loyalitylevel 1 :worktime 2}
                                                      {:name "deniz" :surname "zengin" :joindate "07.08.2020" :experience 2 :loyalitylevel 2 :worktime 1}]
                                           :backend [
                                                     {:name "alphan" :surname "kaplan" :joindate "01.03.2022" :experience 1 :loyalitylevel 1 :worktime 3}
                                                     {:name "barış" :surname "can" :joindate "04.04.2020" :experience 2 :loyalitylevel 2 :worktime 1}]

                                           :fullstack [
                                                       {:name "ramazan" :surname "nedim" :joindate "04.04.2020" :experience 3 :loyalitylevel 2 :worktime 1}]

                                           :devops [
                                                    {:name "tarık" :surname "derkan" :joindate "04.04.2018" :experience 4 :loyalitylevel 3 :worktime 1}]

                                           :cybersecurity [
                                                           {:name "tayfun" :surname "tatlı" :joindate "02.10.2022" :experience 1 :loyalitylevel 1 :worktime 1}]

                                           :testautomation [
                                                            {}
                                                            {}]]



                           :analysisteam [
                                          :businessanalysisteam []

                                          :marketingteam []

                                          :hrteam []]]





               :managers  [:developerteam/managers []

                           :analysisteam/managers []]


               :relations [
                           :experience [{:id 1 :experiencetimeperiod "0-1" :experiencetype "junior"}
                                        {:id 2 :experiencetimeperiod "1-3" :experiencetype "medior"}
                                        {:id 3 :experiencetimeperiod "3-6" :experiencetype "senior"}
                                        {:id 4 :experiencetimeperiod "6+" :experiencetype "lead senior"}
                                        {}]

                           :loyalitylevel [{:id 1 :loyalitylevelvalue "0-1 year(s)"}
                                           {:id 2 :loyalitylevelvalue "1-2 year(s)"}
                                           {:id 3 :loyalitylevelvalue "3-5 year(s)"}
                                           {:id 4 :loyalitylevelvalue "5-7 year(s)"}
                                           {:id 5 :loyalitylevelvalue "7+ year(s)"}]

                           :worktime [{:id 1 :worktimetype "full time"}
                                      {:id 2 :worktimetype "part time"}
                                      {:id 3 :worktimetype "internship"}]]})


; ->

(def dbSample {
               :person [{:id 1 :name "lodos" :surname "eskioğlu" :joindate "01.06.2022" :experience 1 :loyalitylevel 1 :worktime 3}
                        {:id 2 :name "alp" :surname "boriçi" :joindate "04.04.2021" :experience 2 :loyalitylevel 2 :worktime 1}
                        {:id 3 :name "emir" :surname "sürmeli" :joindate "09.01.2022" :experience 3 :loyalitylevel 1 :worktime 2}
                        {:id 4 :name "deniz" :surname "zengin" :joindate "07.08.2020" :experience 2 :loyalitylevel 2 :worktime 1}
                        {:id 5 :name "alphan" :surname "kaplan" :joindate "01.03.2022" :experience 1 :loyalitylevel 1 :worktime 3}
                        {:id 6 :name "barış" :surname "can" :joindate "04.04.2020" :experience 2 :loyalitylevel 2 :worktime 1}]

               :employees [:developerteam [:frontend [{:person 1}
                                                      {:person 2}
                                                      {:person 3}
                                                      {:person 4}]
                                           :backend [{:person 5}
                                                     {:person 6}]]]})

