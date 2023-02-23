(ns fn.loop)

; rfr: video/20230223-mert-clj-egzersiz-52.mp4

; (loop [bindings*] exprs*)
; Evaluates the exprs in a lexical context in which the symbols in
; the binding-forms are bound to their respective init-exprs or parts
; therein. Acts as a recur target.
;

(loop [x 10]
  (when (> x 1)
    (println x)
    (recur (- x 2))))
; 1. tur:
; önce x ismini 10 değerine bağlıyor
; x eğer 1'den büyükse
; x'i print et
; x'den iki çıkartıp tekrar en başa dön diyor
;
; 2. tur:
; önce x ismini 8 değerine bağlıyor
; x eğer 1'den büyükse
; x'i print et
; x'den iki çıkartıp tekrar en başa dön diyor
;
; 2. tur:
; önce x ismini 6 değerine bağlıyor
; x eğer 1'den büyükse
; x'i print et
; x'den iki çıkartıp tekrar en başa dön diyor
;

; imperatif dillerdeki while loopa denk geliyor bu
; fakat biz bunu recursive bir formatta yazıyoruz clojureda.
; iki tane kavram var:
; loop [<bindings>]
; (recur args)
; recur çağrısındaki argümanlar, loop'un en başındaki bindinglere aktarılıyor
; loop [x 10]
; loop diye sanki bir fonksiyon var
; bu fonksiyon x argümanını alıyor
; bu fonksiyon while loop gibi ilk çağrıldığında x <- 10 ataması yapılıyor
; sonraki çağrılarda
; (recur (- x 2)) ile gelen değere bağlanıyor
