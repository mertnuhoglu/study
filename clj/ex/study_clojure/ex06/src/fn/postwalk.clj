(ns fn.postwalk)

; rfr: video/20230217-mert-clj-egzersiz-41.mp4
; rfr: video/20230222-mert-clj-egzersiz-48.mp4

; (postwalk f form)
; Performs a depth-first, post-order traversal of form.  Calls f on
; each sub-form, uses f's return value in place of the original.
; Recognizes all Clojure data structures. Consumes seqs as with doall.
;

; depth-first: bir ağaç yapısı düşün:
;      A
;     / \
;    B   C
;   /\    \
;  D  E    F
; öncelikle bu ağaç yapısını her türlü coll'la oluşturabiliriz:
; vektörle yapsak nasıl olurdu:
(def D :d)
(def E :e)
(def F :f)
(def B [D E])
(def C [F])
(def A [B C])
; hepsini birleştirirsek:
(identity A)
;=> [[:d :e] [:f]]
; maple aynı ağaç yapısını nasıl yapabiliriz?
{:A {:B {:D "D"
         :E "E"}
     :C {:F "F"}}}
; depth-first traversal ne demek?
; #trm: traversal: dolaşmak
; genel olarak elimizde bir coll varsa (özellikle map'lerde) bunun öğelerini nasıl bir sıralamayla dolaşabiliriz?
; yukarıdaki map objesinden gidelim:

; Breadth-first (önce yatay) dolaşma
; a01: en başta :A'ya girerim.
;      sonra :B'ye girerim
;      sonra :C'ye girerim
;      :D'ye girerim
;      :E'ye girerim
;      :F'ye girerim
; https://miro.medium.com/max/502/1*eyqACQAziXkSuMNmMeTa6A.png

; Depth-first (önce dikey) dolaşma
; a02: ilk olarak :A'ya girerim
;      sonra :B'ye girerim
;      sonra :D'ye girerim
;      sonra :E'ye girerim
;      :B'nin bütün alt dallarının dolaşmam bitince, artık :B'nin yatay düğümlerine giderim
;      :C'ye girerim
;      :F'ye girerim
; https://i.imgur.com/bfitrJw.png

; Bu konu neden önemli?
; Sonuçta elimizde içiçe hiyerarşik bir ağaç yapısı var.
; Bu ağacın tüm düğümlerini (node) dolaşacağız.
; Bu dolaşma işini hangi sırayla yapalım ki, hiçbir düğümü atlamayalım ve her zaman birbirimizle tutarlı bir şekilde dolaşalım.

; q: postwalk bu dolaşma işlemini mi yapıyor?
; İki tür dolaşma kavramı var:
; Derinlemesine veya yataylamasına
; preorder veya postorder

; postwalk aslında bildiğimiz map() fonksiyonuna benziyor
; map ne yapıyordu?
; map kendisine verilen bir listedeki her bir öğeye bir f fonksiyonunu uyguluyordu
; ve tüm sonuçları aynı öğe sayısındaki bir listede biriktirip dönüyordu
; postwalk'ta aynısını hiyerarşik (ağaç) tipindeki collectionlarda yapıyor

;
; verilen bir veri yapısının tüm öğelerini dolaşıp f fonksiyonunu bu öğeye uygular
; sonuçları biriktirir ve aynı şekildeki veri yapısında geri döner

(require '[clojure.walk :refer [postwalk]])

; [postwalk - clojure.walk | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.walk/postwalk)

;;example of removing namespaces from all keys in a nested data structure
(def m {:page/tags [{:tag/category "foo"}]})

(postwalk
  #(if
     (keyword? %)
     (keyword (name %))
     %)
  m)
;=> {:tags [{:category "foo"}]}
; keywordlerin namespace'leri silinmiş bir şekilde aynı map objesini bize döndürdü

(comment
  ; #terim: MapEntry = key-value pair = anahtar-değer ikilisi
  ,)

