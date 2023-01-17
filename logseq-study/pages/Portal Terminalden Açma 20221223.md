tags:: clj/portal

# Portal: Terminalden Açma #clj/portal id=g13648

- rfr: [CLI Usage — djblue/portal 0.35.0](https://cljdoc.org/d/djblue/portal/0.35.0/doc/cli-usage)

-

Çalışıyor:
  
```bash
bb -cp `clojure -Spath -Sdeps '{:deps {djblue/portal {:mvn/version "LATEST"}}}'` \
  -e '(require (quote [portal.api])) (portal.api/open {:port 53755}) @(promise)'
```

Bu komut çalışıyor:

```bash
cat deps.edn | bb -cp `clojure -Spath -Sdeps '{:deps {djblue/portal {:mvn/version "0.9.0"}}}'` -m portal.main edn
```

Bu ise çalışmıyor:

```bash
cat deps.edn | bb -cp `clojure -Spath -M:portal/cli` -m portal.main edn
  #> Message:  Could not find namespace: portal.main.
```

Muhtemelen `path` içinde bulunmuyor, portal. Doğrudan `deps` içine koyalım:

```bash
cat deps.edn | bb -cp `clojure -Spath -Sdeps '{:deps {djblue/portal {:mvn/version "0.35.0"}}}'` -m portal.main edn
```

Bu şekilde çalıştı. Versiyon `LATEST` olsun

```bash
cat deps.edn | bb -cp `clojure -Spath -Sdeps '{:deps {djblue/portal {:mvn/version "LATEST"}}}'` -m portal.main edn
```

Böyle de çalışıyor. Ancak `command substitution` içinde `clojure` prosesini çalıştırmak, bayağı bir vakit kaybettiriyor. Bunu kaldırmanın bir yolu var mı?

Gerek yok, sadece `clojure` komutunu çalıştırınca anında çalışıyor proses.

```bash
clojure -Spath -Sdeps '{:deps {djblue/portal {:mvn/version "LATEST"}}}'
```

Fakat alias olarak yukarıdaki komutu çalıştırınca yine hata alıyorum:

```bash
alias edn='bb -cp `clojure -Spath -Sdeps "{:deps {djblue/portal {:mvn/version "LATEST"}}}"` -m portal.main edn'
cat deps.edn | edn
  #> Message:  File does not exist: portal.main
```

a01: Muhtemelen içiçe tırnaklardan kaynaklanıyor sorun.

```bash
alias edn="bb -cp `clojure -Spath -Sdeps '{:deps {djblue/portal {:mvn/version \"LATEST\"}}}'` -m portal.main edn"
```

Evet, bu şekilde çalıştı. Sorun içiçe quotelarla ilgiliydi.

rfr: `clojure portal <url:file:///~/.zshrc#r=g13649>`

## Result

Bunlar çalışıyor:

```bash
alias portal="bb -cp `clojure -Spath -Sdeps '{:deps {djblue/portal {:mvn/version \"LATEST\"}}}'` -m portal.main"
alias edn="portal edn"
```

```bash
cat deps.edn | edn
```


