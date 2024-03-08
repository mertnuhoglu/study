(ns syntax.vars-bindings)

; rfr: fn/binding.clj

; [Clojure - Vars and the Global Environment](https://clojure.org/reference/vars)

; Biz hep bütün objeler değişmez diyorduk ya, aslında bunun istisnası var.
; 4 tane istisnası var:
; 1. Vars
; 2. Refs
; 3. Agents
; 4. Atoms

; Vars bildiğimiz def ile tanımladığımız objeler olur:
(def x 10)

; #trm: mapping = bağıntı. binding = bağlama.

; q: Var'lar değişebilirse, o zaman def ile tanımladığımız yukarıdaki 10 objesi değişebilir mi?
; Hayır. 10 objesi değişmez, sabittir.
; Fakat x'in referans verdiği obje değişebilir.
; Bir ismin yönlendirdiği (refer ettiği) adresi değiştirebiliriz.

; Normalde bir Varı def tanımladığımızda ona bir değer bağlarsak, bu değer köke (root) bağlanır.
; Eğer değer bağlamazsak, bu Vara bağlanmamış (unbound) denir.
; Eğer bu Varı farklı threadlerde farklı değerlere bağlamak (per-thread binding) istiyorsak,
; bu durumda Varı `dynamic` olarak işaretlemeliyiz (mark etmek).
(def static-root-binding 10)
(def unbound-binding)
(def ^:dynamic per-thread-binding 10)

; dynamic Varlar (threade bağlanmış Varlar), `binding` ile farklı değerlere bağlanabilir.
(def ^:dynamic x 1)
(def ^:dynamic y 1)
(+ x y)
;2

; burada dinamik olarak thread içinde başka değerlere bağlıyoruz
(binding [x 2 y 3]
  (+ x y))
;5

; fakat `binding`in dışına çıktığımızda, root seviyesindeki değerler kullanılır
(+ x y)
;2

; Demek ki, `binding` fonksiyonu bizim thread içi bağlama yapmamıza izin veriyor.
