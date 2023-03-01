# cljs-03: Browser REPL'ına bağlanmak id=g13739

rfr: `~/codes/clj/clojurescript/samples/repl/README.md`

s01:

```bash
sh /Users/mertnuhoglu/codes/clj/clojurescript/script/repl
```

s02: eval in repl:

Bunlar cljs dosyalarını build eder:

```clj
(require '[cljs.closure :as cljsc])
(def opts {:output-to "main.js" :output-dir "out"})
(cljsc/build "src" opts)
```

Burada `src` klasörü altındaki tüm cljs dosyalarını build ettik. Yani onları Google Closure kullanarak js'e dönüştürdük ve bütün bu js scriptlerini de `main.js` içinde topladık.
    
Şu aşamada js'e transformasyonu yaptık, ama hala daha JVM REPL'ıyla Web tarayıcısının JS ortamı arasında bağlantı kurmadık.

s03: eval in repl:

Bunlar JVM REPL'ını browser ortamına bağlar ve dolayısıyla artık REPL ile cljs kodlarını çalıştırabilir hale geleceğiz.

```clj
(require '[cljs.repl :as repl])
(require '[cljs.repl.browser :as browser])
(def env (browser/repl-env))
(repl/repl env)
```

Bu son satır browser'da `http://localhost:9000/` sayfasını açar ve browser'a bağlanır.

Açıklama: Şimdi bizim cljs kodlarını js'e dönüştürme işleminde bir sorun yok. Bunları JVM üzerinde çalıştıramayız. Bunları bir şekilde Tarayıcıya gönderip tarayıcıdan bunların çalışmasının ardından sonuçlarını almamız gerekiyor. Dolayısıyla JVM REPL ile tarayıcı arasında bir bağlantı oluşturmamız gerekiyor. Bunu da dolambaçlı şekilde yapıyoruz. Burada iki tane sunucu oluyor:

1. JVM REPL-Tarayıcı iletişimi: Tarayıcı sunucu, JVM REPL istemci.

Fakat bu iletişimi konfigüre etmek için de, tarayıcı tarafında bir js programı çalıştırmalıyız.

Bu js programını tarayıcıya nasıl göndereceğiz?

Bunun için de JVM REPL tarafında bir sunucu oluşturmamız gerekiyor. Sadece o ilk konfigürasyonu yapacak js programı için.

2. Birinci adımdaki client-server iletişimini kurabilmek için, bir web sunucusuna ihtiyacımız var. Bu web sunucusu JVM REPL tarafında çalışıyor. Tarayıcı localhost:9000 adresine girince bu sunucudan bir HTML dosyası indiriyor. Bu HTML'in içinde bir js dosyası var. Bu js dosyasında da yukarıdaki JVM REPL-Tarayıcı iletişimini sağlayan bir sunucu programı var.

Bu js Tarayıcı tarafında çalıştığından, tarayıcı sunucu hale geliyor, JVM REPL istemci hale geliyor.

Artık js kodlarını çağırabilirsin:

```clj
(+ 1 1)
(js/alert "Hello World!")
```

Not: Dokümantasyonda index.html'i çalıştırın. Onun içinden bir js dosyasıyla `(repl/connect "http://localhost:9000/repl")` komutu çağrılacak. Bu komut tarayıcıyı repl sunucuya bağlayacak diyor. Ama bu index.html dosyasını açmadan da bağlanıyor zaten.

