(ns fn.apply)

; (apply f args) #nclk/çok-önemli
; (apply f x args)
; (apply f x y args)
; (apply f x y z args)
; (apply f a b c d & args)
; Applies fn f to the argument list formed by prepending intervening arguments to args.

; e01:
(max [1 2 3])
;;=> [1 2 3]

; Normalde max fonksiyonunun verilen öğelerden en büyüğünü dönmesini beklerdim.
; Ama bana 3 vereceği halde tüm vektörü dönüyor.

(max 1 2 3)
;=> 3

; apply ile yukarıdaki vektörü bir nevi parçalayıp, tek tek argüman argüman max fonksiyonuna iletiyoruz:
(apply max [1 2 3])
;;=> 3
; bir nevi coll (vektör veya listeyi) box (kutu) olarak düşünelim
; apply sayesinde coll argümanını unbox (kutudan çıkartıp) sırayla argüman listesine ekliyoruz
; & rest argüman isteyen fonksiyonlarda kullanılıyor

; max'ın dokümantasyonuna baktığımızda imzasında rest operatörünü görürüz:
; [x y & more]
; rest operatörüyle bir fonksiyona arg verilmesi gerektiği halde,
; biz o fonksiyona bir coll iletiyorsak, o zaman apply kullanılması gerekir

; e02:
;; Note the equivalence of the following two forms
(apply str ["str1" "str2" "str3"])  ;;=> "str1str2str3"
(str "str1" "str2" "str3")          ;;=> "str1str2str3"

; apply:
; verilen bir fonksiyonu bir koleksiyonun içine girip oradaki bilgileri parametre olarak atamamıza yarar
; yani o fonksiyonu o verilerle çalıştırmamızı sağlar
; apply, bir fonksiyonu verdiğin argümanlarla çalıştırır
; ama bunu yaparken verilen argümanı unbox eder ve o fonksiyonun rest argümanları olarak gönderir
; apply aslında variadic bir fonksiyona verilen paketlenmiş (seq içindeki) argümanları unbox ederek gönderir.