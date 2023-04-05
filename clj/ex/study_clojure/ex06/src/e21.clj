(ns e21)

(comment
  (defn seq->map [s]
    (let [s+ (concat s [nil])]
      (zipmap
        (take-nth 2 s+)
        (take-nth 2 (rest s+)))))

  (seq->map '(1 2 3 4 5 6 7 8 9 10 11 12 13 14 15))

  ; q: yukarıdaki seq->map fonksiyonunda verilen s argümanına nil ekliyoruz
  ; ancak ne zaman nil eklenmesi gerektiğini fonksiyon nereden biliyor?

  ; adım adım debug edelim
  ; ilk başta s objesini tanımlayalım
  ; mümkün olduğunca küçük bir şekilde örnek objelerimizi tanımlayalım
  (def s '(1 2 3))

  ; 2. adım: let'in binding'lerini tanımlayalım
  (def s+ (concat s [nil]))
  (identity s+)
  ;=> (1 2 3 nil)

  ; 3. adım: en içerideki formları debug edelim
  (take-nth 2 s+)
  ;=> (1 3)

  (rest s+)
  ;=> (2 3 nil)
  (take-nth 2 (rest s+))
  ;=> (2 nil)

  (zipmap '(1 3) '(2 nil))
  ;=> {1 2, 3 nil}

  (seq->map s)
  ;=> {1 2, 3 nil}

  ; bu ilk örnekte, s listesinin tek sayıda öğesi vardı
  ; şimdi ikinci s listesinin çift sayıda öğesi olsun

  (def s '(1 2 3 4))
  (def s+ (concat s [nil]))
  (identity s+)
  ;=> (1 2 3 4 nil)

  (take-nth 2 s+)
  ;=> (1 3 nil)
  (take-nth 2 (rest s+))
  ;=> (2 4)

  (zipmap '(1 3 nil) '(2 4))
  ;=> {1 2, 3 4}

  (cat)
  ; end
  ,)


