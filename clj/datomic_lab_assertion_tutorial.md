---
title: "Study datomic: Lab Assertion Tutorial"
date: 2023-01-31T12:45:18+03:00 
draft: false
description: ""
tags:
categories: datomic, datalog
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:

---

- ## Lab: Assertion Tutorial id=g13506
  id:: b20d171e-081c-4110-a5dd-3650c81f2fe2

[Assertion | Datomic](https://docs.datomic.com/cloud/tutorial/assertion.html)

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01b.clj`

### Setup

[Local Dev and CI with dev-local | Datomic](https://docs.datomic.com/cloud/dev-local.html)

```bash
cd /Users/mertnuhoglu/.m2/repository/cognitect-dev-tools-0.9.72/
./install
```

Şuraya kurar dosyaları:

```
/Users/mertnuhoglu/.m2/repository/com/datomic/dev-local/
/Users/mertnuhoglu/.m2/repository/com/cognitect/rebl/
```

Lokal depo için klasörü burada tanımlayabilirsin: `~/.datomic/dev-local.edn`

```clj
{:storage-dir "/full/path/to/where/you/want/data"}
```

Cognitect'ten gelen emailde maven ayarları var:

01. maven:

> Both tools.deps and maven configurations require thew following step:

> Add a new server entry under servers in your ~/.m2/settings.xml as shown below.

	<!-- in ~/.m2/settings.xml -->
	<settings>
		...
		<servers>
		...
			<server>
				<id>cognitect-dev-tools</id>
				<username>mert.nuhoglu@gmail.com</username>
				<password>...</password>
			</server>
		</servers>
		...
	</settings>

02. tools.deps:

> Add an entry under the :mvn/repos key in your ~/.clojure/deps.edn file. You only need to do this once, nothing else needs to be done per-project to specify maven information.

	;; in ~/.clojure/deps.edn
	{...
	:mvn/repos {"cognitect-dev-tools"
							{:url "https://dev-tools.cognitect.com/maven/releases/"}}}

03. deps.edn

Edit `~/prj/study/clj/ex/study_datomic/datalog-01/deps.edn`

```clj
{com.datomic/dev-local {:mvn/version "1.0.243"}}
```

04. Memory storage

```clj
(def client (d/client {:server-type :dev-local
	:storage-dir :mem
	:system "ci"}))
```

- 04. Connect to Sample Database id=g13507
  id:: 0313e396-3a12-41fe-a82b-7997f09a42cb

[Sample Data](https://docs.datomic.com/cloud/dev-local.html#samples)

rfr: 

	/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01b.clj
	/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01c.clj

Download https://datomic-samples.s3.amazonaws.com/datomic-samples-2020-07-07.zip

Bu dosyanın içindekileri `dev-local.edn` tarafından tanımlanmış storage-dir içine koy.

Dikkat: Hiç sunucu yazılımı çalıştırmana gerek yok, burada. Aşağıdaki client yazılımı otomatik olarak storage-dir altındaki dosyaları veritabanı olarak kullanabilir.

```clj
(require '[datomic.client.api :as d])
(def client (d/client {:server-type :dev-local
                       :system "datomic-samples"}))
(d/list-databases client {})
=> ["mbrainz-subset" "solar-system" "social-news" "movies" ...]
```

Bu durumda  `~/.datomic/dev-local.edn` içindeki `{:storage-dir "/Users/mertnuhoglu/db/"}` içinde veritabanları tutulur.

Bu örnekte local-dev kütüphanesi kullanılarak diskte storage-dir altında veriler tutulur.

> dev-local stores data to your local filesystem, in directories under the :system you specify when creating a dev-local client.

> Each database will store transactions in a directory named <storage-dir>/<system-name>/<database-name>. You can "backup" or "restore" a dev-local database simply by copying the database directory.

Next step: rfr: Example: Day of Datomic Cloud Sample Data <url:file:///~/prj/study/clj/articles-datomic.md#r=g13521>

- ### Code - Assertion and Read Tutorial id=g13509
  id:: d8c6d317-b6ab-4861-b39f-d7cd36d11083

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01b.clj`

Bu örnekte local-dev kütüphanesi kullanılarak bellekte veriler tutulur.

## Session 3

- [Day of Datomic Cloud - Session 3 - YouTube](https://www.youtube.com/watch?v=0ozQ5aSPB04&list=PLjyLzdfdsKwqF9I1XSX_Y4TXAo8pYXbOv&index=3) id=g13510
  id:: e6996a34-a911-42e7-bf66-432ada13f72a


