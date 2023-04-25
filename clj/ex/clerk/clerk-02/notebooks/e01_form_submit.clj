(ns e01_form_submit)

(require '[nextjournal.clerk :as clerk])

;; rfr: [[20230414-Clj-Clerk-ile-Uygulama-Simulasyonu]]
;; Video: 20230425-mert-clj-58-clerk-uygulama-simulasyonu.mp4

;; ## p01: Form kayıt id=g14240
;;
(def form
  {:name nil
   :department nil})

;; - e01: map of seqs olması gerekiyor. map of nil hata veriyor:
;;
#_(clerk/table
   {:name nil
    :department nil})

;; - e02: map of seqs. seq: içi boş.
;;
(clerk/table
 {:name []
  :department []})

;; - e03: map of seqs. seq: placeholder: `...`
;;
(clerk/table
 {:name ["..."]
  :department ["..."]})

;; - e04: map of seqs. seq: placeholder: `<input>`
;;
(clerk/table
 {:name ["<input>"]
  :department ["<input>"]})

;; - e05: tabloya bir başlık koyalım
;;
;; [Student]
(clerk/table
 {:name ["<input>"]
  :department ["<input>"]})

;; - e06: meta bilgileri backquote ile formatlayalım
;;
;; `[Student]`
(clerk/table
 {:name ["`<input>`"]
  :department ["<input>"]})

;; - e07: kodu gizleyelim
;;
;; `[Student]`
^{::clerk/visibility {:code :hide}}
(clerk/table
 {:name ["<input>"]
  :department ["<input>"]})


