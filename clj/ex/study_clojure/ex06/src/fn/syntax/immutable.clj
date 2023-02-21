(ns fn.syntax.immutable)

; rfr: video/20230220-mert-clj-egzersiz-45.mp4

(def a [1 2 3])
(identity a)
;=> [1 2 3]

(def a [2 3 4])
(identity a)
;=> [2 3 4]

; q: madem a objesi sabitti (immutable) nasıl oldu  da değişti?
; cevap: obje değişmedi aslında
; biz bir objeye bir isim bağlıyoruz
; o ismi istersek başka bir objeye de bağlayabiliriz
; bu durumda objeler değişmiş olmuyor

; bellekte iki tane kutu (place) düşünün:
; place01: [1 2 3] memory_address: 0x09403948
; place02: [2 3 4] memory_address: 0x32939090
; benim başka bir yerde namespace'im var:
; a diye ismim var
; a -> 0x09403948
; bu şekilde ben onu bağlamışım
; def dediğim satırda bu isim bu bellekteki konuma bağlanıyor
; ben tekrar (def a ..) yaptığımda, o a isminin bağlandığı yeri değiştiriyorum
; a -> 0x32939090
; ama hiçbir durumda o objelerin içeriğinde değişiklik meydana gelmiyor

; q: Peki benim bu objelerin sabit olduğunu doğrulayabileceğim bir örnek verebilir misiniz?
; Bir fonksiyona bunları argüman göndererek yapabiliriz
(def b [1 2 3])
(defn f1 [v]
  (map inc v))
(f1 b)
;=> (2 3 4)
(identity b)
;=> [1 2 3]

; Java ve js'de yapıyor olsaydık bu işlem şöyle bir şey olurdu, pseudocode:
; static List f2(List v) {
;   for(int i = 0; i < v.length(); i++) {
;     v[i] = v[i] + 1; burada v objesinin öğelerini değiştiriyoruz
;   };
;   return v;
; }
; f2(b)
; bu durumda, b'nin kendisi de değişir
; println(b) ; => 2 3 4
;