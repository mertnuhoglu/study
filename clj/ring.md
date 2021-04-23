---
title: "Study: Ring Library"
date: 2021-03-29T20:48:20+03:00 
draft: false
description: ""
tags:
categories: clojure
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css

---

# Index

`Ring suyu <url:file:///~/projects/study/clj/clojure.otl#r=g_12082>`

# Official Documentation

## Article: README

[ring-clojure/ring: Clojure HTTP server abstraction](https://github.com/ring-clojure/ring)

## Article: Getting Started

[Getting Started · ring-clojure/ring Wiki](https://github.com/ring-clojure/ring/wiki/Getting-Started)

```bash
cd ~/projects/study/clj/ex/ring/e01
lein new hello-world
cd hello-world
lein deps
```

Check `~/projects/study/clj/ex/ring/e01/hello-world/project.clj`

Edit `~/projects/study/clj/ex/ring/e01/hello-world/src/hello_world/core.clj`

```clj
(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})
```

```bash
lein repl
```

At REPL:

```clj
(use 'ring.adapter.jetty)
(use 'hello-world.core)
(run-jetty handler {:port  3000
                       :join? false})
```

Open `http://localhost:3000/`

## Concepts

[Concepts · ring-clojure/ring Wiki](https://github.com/ring-clojure/ring/wiki/Concepts)

4 components:

- Handler
- Request
- Response
- Midleware

### Handlers

Handler: functions that define a web application

	| synchronous handlers  | 1 | request                               |
	| asynchronous handlers | 3 | request, response callback, exception |

ex: synchronous

```clj
(defn what-is-my-ip [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (:remote-addr request)})
```

ex: asynchronous

```clj
(defn what-is-my-ip [request respond raise]
  (respond {:status 200
            :headers {"Content-Type" "text/plain"}
            :body (:remote-addr request)}))
```

We can combine them into one handler:

```clj
(defn what-is-my-ip
  ([request]
    {:status 200
     :headers {"Content-Type" "text/plain"}
     :body (:remote-addr request)})
  ([request respond raise]
    (respond (what-is-my-ip request))))
```

### Requests

1. standard keys: `server-port server-name remote-addr uri query-string scheme request-method headers body`
2. custom keys (added by middleware)

### Responses

Keys: `status headers body`

body types: `String ISeq File InputStream`

### Middleware

First arg: handler

Return value: handler function that calls the original handler

```clj
(defn wrap-content-type [handler content-type]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers "Content-Type"] content-type))))
```

Bu mw, tüm response objelerine "Content-Type" headerı ekliyor. Önce handlerın responseunu alıyor. Sonra buna header ekliyor. 

Dikkat edersen, mw kendisi bir fonksiyon dönüyor. Bu fonksiyon da bir handler fonksiyonu. Bu yeni handler fonksiyonu, orjinal handlerın responseunu değiştiriyor. 

Bu mwyi bir handlera nasıl uygulayacağız?

```clj
(def app
  (wrap-content-type handler "text/html"))
```

`content-type` argümanını bu sırada paslıyoruz. Bu çağrının sonunda bir handler fonksiyonu dönüyor. 

Bu tarz mwlerin her birini arka arkaya threading macro ile diziyoruz:

```clj
(def app
  (-> handler
      (wrap-content-type "text/html")
      (wrap-keyword-params)
      (wrap-params)))
```

handlerla başlıyoruz, sonunda yine bir handler fonksiyonu dönüyoruz. Aralarda mwlerin kendi ihtiyaç duydukları argümanları da paslıyoruz.

### Creating Responses

`response` fonksiyonu bir response objesi dönüyor:

```clj
(response "Hello World")

=> {:status 200
    :headers {}
    :body "Hello World"}
```

redirect yapmak için özel fonksiyon: `redirect`

```clj
(redirect "http://example.com")

=> {:status 302
    :headers {"Location" "http://example.com"}
    :body ""}
```

statik dosya return etmek için özel fonksiyon: `file-response`

```clj
(file-response "readme.html" {:root "public"})

=> {:status 200
    :headers {}
    :body (io/file "public/readme.html")}
```

Dosyayı, stream olarak döndürmek için: `resource-response`

### Static Resources

İki mw fonksiyonuyla statik kaynaklara erişim sunar Ring:

- `wrap-file`: dosya sisteminden
- `wrap-resource`: classpath üzerinden

```clj
(use 'ring.middleware.file)
(def app
  (wrap-file your-handler "/var/www/public"))
;; veya
  (wrap-resource your-handler "public"))
```

Bunlar başka mw'lerin içinde kullanılır:

```clj
  (-> your-handler
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-not-modified))
```

- `wrap-content-type`: örneğin: txt dosyalarına otomatikman `text/plain` content-type ekler
- `wrap-not-modified`: `Last-Modified` headerını kullanarak, hali hazırda yüklenmiş dosyaları tekrar göndermez.

Ancak bu ekstra mwlerin `wrap-resource`tan sonra gelmeleri lazım. Çünkü önce dosya olmalı ki, bunun content-typeını değiştirebilsin Ring.

### Content Types

Özel mime-type ekleyebilirsin dosya uzantılarına bağlı olarak:

```clj
  (wrap-content-type
   your-handler
   {:mime-types {"foo" "text/x-foo"}}))
```

### Parameters

`wrap-params`: url-encoded parameters in query string or request body

`wrap-multipart-params`: file uploads

3 yeni key ekler request mapine:

- `:query-params`: query string parametre mapi
- `:form-params`: form data parametre mapi
- `:params`: merged map

Mesela request şuysa:

```clj
{:request-method :get
 :uri "/search"
 :query-string "q=clojure"}
```

mw bu 3 keyi ekler:

```clj
 :query-params {"q" "clojure"}
 :form-params {}
 :params {"q" "clojure"}
```

Sadece `:params` yeterli. Diğerleri verinin kaynağını bulman için.

Eğer aynı parametre ismine bağlı birden çok değer varsa, parametre değeri vektör olur.

```clj
http://example.com/demo?x=hello&x=world
; {"x" ["hello", "world"]}
```

### Cookies

`wrap-cookies`: yeni `:cookies` keyini requeste ekler. Örn:

```clj
 :cookies {"session_id" {:value "session-id-hash"}}
```

Sen de bunu response mape eklersin.

### Sessions

`wrap-session`: requeste `:session` key ekler.

Oturumu verisini değiştirmek için, responsea da requestten aldığın `:session` keyini ekleyebilirsin:

```clj
(defn handler [{session :session}]
  (let [count   (:count session 0)
        session (assoc session :count (inc count))]
    (-> (response (str "You accessed this page " count " times."))
        (assoc :session session))))
```

### File Uploads

`wrap-multipart-params`

Yüklenen dosyalar response içinde `:multipart-params` keyindedir.

```bash
curl -XPOST  "http://localhost:3000" -F file=@words.txt
; :params
  {"file" {:filename     "words.txt"
           :content-type "text/plain"
           :tempfile     #object[java.io.File ...]
           :size         51}}
```

Bu geçici bir yere kaydeder.

Kalıcı kayıt için: `bean` kullanırsın sunucu tarafında:

```clj
(def demo-ring-req
  {:params {"file"
            {:filename     "words.txt"}
            :content-type "text/plain"
            :tempfile     #object[java.io.File ...]
            :size         51}})
 
(def save-file [req]
  (let [tmpfilepath (:path (bean (get-in req [:params "file" :tempfile])))
        custom-path "/your/custom/path/file.txt"]
    (do
      (io/copy (io/file tmpfilepath) (io/file custom-path))
      {:status 200
       :headers  {"Content-Type" "text/html"}
       :body (str "File now available for download at: http://localhost:3000/" custom-path)})))

(save-file demo-ring-req)
```

Not: `bean` bir java objesinin propertylerini map keylerine çevirir.

### Middleware Patterns

#### Adding keys to the request map

```clj
(assoc request :user (-> request :session :user-id))
```

#### Conditionally executing the handler

```clj
(if (authorized? request)
	(handler request)
	(-> (response "Access denied")
	    (status 403)))
```

## Article: Ring Examples

[Examples · ring-clojure/ring Wiki](https://github.com/ring-clojure/ring/wiki/Examples)

### Hello World

```bash
clojure -X:project/new :name mert/e02
cd e02
clojure -M:project/find-deps ring-core
```

opt01: run as script

Edit `~/projects/study/clj/ex/ring/e02/e02.clj`

```bash
clojure src/mert/e02.clj
```

Open: `http://localhost:8080/`

opt02: run as main function:

Edit `~/projects/study/clj/ex/ring/e02/e02b.clj`

```bash
clj -M -m mert/e02b.clj
```

Repl:

Cursive: `switch repl to ns`

```clj
(-main)
```


