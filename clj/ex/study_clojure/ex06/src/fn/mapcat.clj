(ns fn.mapcat)

; rfr: video/20230215-mert-clj-egzersiz-38.mp4

; (mapcat f)
; (mapcat f & colls)
; Returns the result of applying concat to the result of applying map
; to f and colls.  Thus function f should return a collection. Returns
; a transducer when no collections are provided

(mapcat reverse [[1 2] [3 4]])
=> (2 1 4 3)

(map reverse [[1 2] [3 4]])
;=> ((2 1) (4 3))
(apply concat (map reverse [[1 2] [3 4]]))
;=> (2 1 4 3)

; mapcat: aslına bakarsan FP kitaplarında reduce, map, foldmap (mapcat) diye konular anlatılır
; bizim clj tarafında nispeten daha az kullanılıyor
; haskell, javascript, purescript, R

; önce map ettim
; sonra concat ettim
; bu yüzden ismi mapcat

; rfr: [Professor Frisby Introduces Composable Functional JavaScript | egghead.io](https://egghead.io/courses/professor-frisby-introduces-composable-functional-javascript)
; [MostlyAdequate/mostly-adequate-guide: Mostly adequate guide to FP (in javascript)](https://github.com/MostlyAdequate/mostly-adequate-guide)
; [(166) Classroom Coding with Prof. Frisby - YouTube](https://www.youtube.com/playlist?list=PLK_hdtAJ4KqX0JOs_KMAmUNTNMRYhWEaC)
; Brian Lonsdorf, bu foldMap fonksiyonunu çok sade ve anlaşılır bir anlatır
; bunun kullanım maksadını güzel anlatıyor
; FP'de array'ler (collectionlar) bir nevi bir kutuya (box) benzetiyor
; map fonksiyonu o kutuyu açıyor, o kutunun içindeki her şeyi bir işlemden geçiriyor, ve başka bir kutuya koyuyor
; (apply concat) fonksiyonu ise bu kutuyu açıyor ve içindeki öğeleri çıkartıyor.
; mapcat: kutuyu açıyor, işlemden geçiriyor ve açık bir şekilde içindeki parçaları sana dönüyor