
# Error: lein repl çalışmıyor veya datomic-pro.jar dosyası indirilemiyor id=g13696

Hata mesajı: 

	Could not find artifact com.datomic:datomic-pro:jar:1.0.6610 in central (https://repo1.maven.org/maven2/)
	Could not find artifact com.datomic:datomic-pro:jar:1.0.6610 in clojars (https://repo.clojars.org/)

Problemin nedeni şu:

- Bizim deps.edn dosyasında biz kullanacağımız tüm kütüphanelerin tanımını veriyoruz
- Mesela {org.clojure/clojure {:mvn/version "1.11.1"}}
- Burada aslında diyoruz ki, git maven reposundan org.clojure/clojure isim repoyu bul.
- Bunun 1.11.1 versiyonunu bul ve indir.
- Fakat bunu yapabilmemiz için, ilgili repoda bu kütüphanenin (jar) bulunması lazım.
- Eğer kullandığımız library jar dosyası public değilse, o zaman bulamayız.
- Private jar dosyaları için ekstra bir konfigürasyon yapmamız gerekiyor, onlara erişebilmek için.
- datomic-pro.jar  dosyası da private olduğundan bu konfigürasyon yapmamız gerekiyor.

Çözüm:

rfr: datomic-pro maven setup <url:file:///~/prj/study/clj/datomic-pro-maven-setup.md#r=g13697>

