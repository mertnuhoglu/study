(ns fn.conj)

; conj: #nclk/çok-önemli #tpc/data-structure
; [coll x]
; [coll x & xs]
; conj[oin].
; Returns a new collection with the xs 'added'. (conj nil item) returns (item).  The 'addition' may happen at different 'places' depending on the concrete type.

; iki argüman alıyor
; ilk arg: x
; kalan argümanlar: xs
; &: rest operatörü #nclk/çok-önemli
; xs'i x'e ekler (conjoin) ~ birleştirir gibi

; not: Kaizen (continous improvement): sürekli iyileştirme anlamına gelir
; herhangi bir süreçteki eksikleri en iyi onu işlerken görürsünüz
; bir eksiklik gördüğünüzde onunla ilgili iyileştirme çalışmasına hemen başlamalısınız
; ilk adım o eksikliği kaydetmek, çünkü onu daha sonra planlayacaksınız

; biz burada birden çok iş yapmış oluyoruz aslında
; hem eğitim yapıyoruz, hem de dokümantasyon sistemi ve gelecekteki bilgi sistemimizi iyileştiriyoruz
; bu da aslında holistik (wholistics) (bütüncül) bir yaklaşım uygulamamızdan kaynaklanıyor.
; bu bütüncül (sistem) yaklaşımı, çok önemli
; yazılım sistemlerinin uzun vadede yönetilemeyişi, temelde karmaşıklığın aşırı büyümesinden kaynaklanır.
; dolayısıyla karmaşıklığı her aşamada sadeleştirmemiz (refactoring, temizlik) gerekiyor.
; refactoring özellikle yazılımcılar tarafından bilinen bir kavramdır
; fakat biz bu kavramı genelleştirerek kullanıyoruz
; sadece yazılım geliştirme çalışmaları değil, tüm organizasyonel sistem çalışmalarında karmaşıklık problemini her gün çözmemiz lazım.

; listelerde conj başa ekler
(conj '(1 2) 3)
;; => (3 1 2)

; vektörlerde conj sona ekler
(conj [4 1] 7)
;; => [4 1 7]

; q: neden listelerde başa, vektörlerde sona ekler? bu çalışma yapısıyla mı alakalı.
; evet, listenin ve vektörün tutulma şekliyle alakalı bu.
; liste yapırsan: linked list bir data structuredır.
; listedeki her bir öğeyi <x1 ..> <x2 ..> notasyonuyla gösterelim.
; implementasyonu şu şekildedir
; <x1 next> -> <x2 next> <x3 next> <x4 nil>
; dolayısıyla ekleme yaparken, en kolay nasıl eklersin?
; en baştan eklersin.
; sona ekleme yapacak olsaydın, o zaman tek tek tüm öğeleri dolaşman gerekirdi
; sen ne kadar çok dolaşırsan işlemcin o kadar yorulur
; eğer senin listen 100 milyon öğeden oluşuyorsan, 100 milyon tane referansı dolaşman gerekir
; bu da sistemi ciddi anlamda yavaşlatır

; bu konular Algorithms ve Data Structures dersinde işlenir
; orada Big-O notation ile tüm algoritma ve veri yapılarının performansı incelenir
; [(150) Veri Yapıları - YouTube](https://www.youtube.com/playlist?list=PLh9ECzBB8tJN9bckI6FbWB03HkmogKrFT)

