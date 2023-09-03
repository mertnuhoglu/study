(ns ex.e13)

; tarih: 20230313

; Matematiksel işlemler neredeyse hiç yapmayacağız.
; Yapacaksak da çok basit 4 işlemin ötesine geçmeyeceğiz.

; Enformasyon Yönetim Sistemi = Management Information System
; Temel özelliği: veritabanı ağırlıklı olması
; Burada neredeyse hiç algoritma yok.

; Esas olarak bizim yapacağımız iş, hep veri analizi (data analysis) olacak.
; Fakat bu istatistiksel veri analizi değil.
; ETL: Extract-transform-load işlemleri olacak.
; Extract: Veriyi çıkartmak
; Transform: Veriyi dönüştürmek
; Load: Veriyi yüklemek

; Veriyi bir formattan çıkartacağız, onu biraz evirip çevireceğiz, başka bir formata yükleyeceğiz.
; Örnek: JSON formatında alacağız veriyi. O veriyle csv formatında aldığımız başka bir veriyi birleştireceğiz (join)
; Sonra da hepsini Datomic'in formatına çevirip kaydedeceğiz.

; Hep bu tür işlemler olacak.

; Veriyi de genellikle data structurelar içinde işleyeceğiz.
; Data structurelar nelerdi?
; 1. Set
; 2. Vektör
; 3. List
; 4. Map

; Clojureda bunlara collection diyoruz.
; Dolayısıyla ilk etapta collection api'lerini iyi öğrenmemiz lazım.
; Brian Will Collections eğitim videoları
; Onu iyi çalış
; Onun gösterdiği o fonksiyonlarla nasıl farklı denemeler yapabilirsin, onu kurcalamaya çalış.

; ETL işlemlerine şu isimler de verilir:
; Data cleaning
; Data preparation
; Data scraping (veriyi kazımak)
; Data munging

(+ 10 20)


