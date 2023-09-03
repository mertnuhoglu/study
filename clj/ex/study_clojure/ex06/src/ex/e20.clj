(ns ex.e20)

(def m {:a 1 :b 2 :c nil})

(comment
  ;öncelikle into [to form] formunda kullanılmış buna göre debuggingi mizi yapalım.
  (into {}
    (remove
      (comp nil? second)
      m))
  ;=> {:a 1, :b 2}

  ((comp nil? second) {:a 1 :b 2 :c nil})
  ;=> false

  (remove
    (comp nil? second)
    m)
  ;=> ([:a 1] [:b 2])

  (def pred (comp nil? second))

  ; iterasyonlar
  ; 1. iterasyon
  (pred [:a 1])
  ;=> false
  ; 2. iterasyon
  (pred [:b 2])
  ;=> false
  ; 3. iterasyon
  (pred [:c nil])
  ;=> true

  ; true olanlar ayıklanır diğerleri muhafaza edilir

  (into {}
    (remove
      (comp nil? second)
      m))
  ; => şuna denktir
  (into {} '([:a 1] [:b 2]))
  ;=> {:a 1, :b 2}

  ((comp nil? second) {:a 1 :b 2 :c nil})
  ; yukarıdaki hata şu:
  ; tüm m map objesini vermiş arg olarak
  ; her bir ikiliyi ayrı ayrı vermen gerekiyor
  (pred [:a 1])

  ; end
  ,)

