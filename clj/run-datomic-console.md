
# run datomic console id=g12858
  id:: da5248f4-ba34-4aab-98d9-ada1bb3ad075

rfr: 

	run datomic console <url:file:///~/prj/study/clj/datomic.otl#r=g12858>
	~/codes/clj/content/day-of-datomic/README.md
	~/codes/clj/content/day-of-datomic/tutorial/hello_world.clj
	[Run a Transactor | Datomic](https://docs.datomic.com/on-prem/getting-started/transactor.html#run-dev-transactor)

tüm işlemler datomic-pro klasörü altında yapılacak:

	cd /Users/mertnuhoglu/codes/clj/lib/datomic-pro-1.0.6397

step01:

	bin/transactor -Ddatomic.printConnectionInfo=true config/dev-transactor-template.properties

rfr: datomic-pro maven setup <url:file:///~/prj/study/clj/datomic-pro-maven-setup.md#r=g13697>

rfr: hata: lein repl çalışmıyor veya datomic-pro.jar dosyası indirilemiyor <url:file:///~/prj/study/clj/datomic.otl#r=g13696>

step02 (optional):

	lein repl
	bin/repl
	:ConjureConnect

step03:

	bin/console -p 8080 dev datomic:dev://localhost:4334/
	error: hata verirse, farklı bir port dene
	http://localhost:8080/browse
