(ns sof.sof32)

; rfr: video/20230223-mert-clj-egzersiz-51.mp4

; [Clojure get nested map value - Stack Overflow](https://stackoverflow.com/questions/15639060/clojure-get-nested-map-value)

(def grid-settings
  {:width 50
   :height 50
   :ground {:variations 25}
   :water {:variations 25}})

;And I wondered if you know of a good way of retrieving a nested value? I tried writing

(:variations (:ground grid-settings))
;=> 25

; a01: get-in

(get-in grid-settings [:ground :variations])
;=> 25

; a02: thread-first macro
(-> grid-settings
  :ground
  :variations)
;=> 25

; a02b: default değer verme

(get-in grid-settings
  [:ground :variations]
  "not found!")
;=> 25

; varsayılan değer belirtmenin thread-macro'daki çözümü (or ..) ifadesi kullanmaktır
(-> grid-settings
  :ground
  :variations
  (or "not found"))
;=> 25

(-> grid-settings
  :ground
  :variations2
  (or "not found"))
;=> "not found"

(comment
  ; yukarıdaki ilk ifade
  (-> grid-settings
     :ground
     :variations
     (or "not found"))
  ;=> 25
  ; ≣
  (or 25 "not found")
  ;=> 25

  ; ikinci ifade ise:
  (-> grid-settings
    :ground
    :variations2
    (or "not found"))
  ;=> "not found"
  ; ≣
  (or nil "not found")
  ;=> "not found"
  ,)

; pro: You can also use -> when you need predicates that aren't keywords

; a03: destructuring

(let [{{gv :variations} :ground
       {wv :variations} :water} grid-settings]
  [gv wv])
;=> [25 25]

; pro: böylece birden çok değeri çekebiliyoruz
; tek seferde threading macro veya get-in ile birden çok propertynin değerini çekemeyiz

(comment
  (identity grid-settings)
  ;=> {:width 50, :height 50, :ground {:variations 25}, :water {:variations 25}}

  ; grid-settings isimli map objesinin :ground property'sinin :variations property değerini gv'ye bağlamışız
  ; grid-settings isimli map objesinin :water property'sinin :variations property değerini wv'ye bağlamışız

  ; biraz daha kısaltalım:
  ; grid-settings objesinin :water property'sinin :variations propertysini wv'ye bağlamışız
  ,)

; a04: nested map function

((grid-settings :ground) :variations)
;=> 25
