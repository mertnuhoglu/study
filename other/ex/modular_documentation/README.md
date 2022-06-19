
# Modüler Dokümantasyon Nasıl Yapılır?

`test/01` klasörü altında modüler dokümantasyonun nasıl yapılacağına dair basit bir örnek görebilirsiniz.

Buradaki girdi dosyalar:

- Book.txt
- doc_srs.md
- introduction-srs.md
- rmarkdown-01.rmd
- rdb-description.md

Öncelikle `test01/Book.txt` dosyası scriptler için giriş dosyasıdır. Burada, hangi markdown dosyalarını sırayla birleştireceğimiz yazar. Tek bir dosya olması bile yeterli. Örneğin, mevcut dosyada sadece tek bir dosyaya referans verilmiş: 

`test01/Book.txt`

```
doc_srs.md
```

Bu demek oluyor ki, `doc_src.md` dosyasını aç ve içindeki tüm diğer gömülmüş markdown dosyalarını birleştirerek çıktıyı oluştur. `test01/doc_srs.md` dosyası alt dosyalar içeriyor:

`test01/doc_srs.md`

```
# Hello People

{{introduction-srs.md}}

{{rdb-description.md}}
```

Bu demek oluyor ki, önce "Hello People" başlığını koy. Sonra "introduction-srs.md" dosyasını koy. Sonra da "rdb-description.md" dosyasını koy.

Bu alt dosyalar da yine başka dosyaları içerebilirler. 

## Rmarkdown desteği

Normal markdown formatı, statik içerikler için uygun. Ancak bazı yerlerde statik içeriklerin ötesinde ihtiyaçlarımız bulunur. Örneğin:

- Excel'de tutulan tabloların doküman içine konulması
- Veri analizi yapılması gereken bilgiler (Örneğin, kaç gereksinim maddesi var, kaçı bitmiş gibi?)

Bu gibi dinamik dokümantasyon ihtiyaçlarını Rmarkdown kullanarak kolayca karşılayabiliriz. 

Bilgi akışı şu şekilde olacak:

1. Rmarkdown dosyalarından dinamik olarak markdown dosyaları üretiriz
2. Bu markdown dosyaları modüler bir şekilde birleşir (birbirine referanslar içerir)
3. mdmerge ve pandoc kullanarak tüm markdown dosyaları birleştirilir ve Word/pdf/html gibi çıktılar üretilir.

Örnek olarak hazırladığımız `test01` klasöründe, bir Rmarkdown dosyasının diğer markdown dosyalarıyla entegre kullanım örneğini bulabilirsiniz.

- `test01/rmarkdown-01.rmd`

Bu Rmarkdown dosyasını render ederek (dosyadaki tüm komutları çalıştırıp çıktı üretmek), bir markdown dosyası oluştururuz, aynı isimde fakat uzantısı `.md` olan.

Bu `.md` uzantılı dosyayı diğer markdown dosyalarının içine gömebiliriz. Örneğin, `introduction-srs.md` içinde bu dosyayı gömmüşüz:

`test01/introduction-srs.md`

```
## Introduction to SRS

{{rmarkdown-01.md}}
```

Özet olarak, önce `Rmd` dosyalarından `md` dosyalarını oluşturuyoruz. Sonra bu md dosyalarını mdmerge kullanarak birleştiriyoruz. Sonra birleşmiş olan dosyayı (`output.md`) pandoc ile nihai çıktımız olan Word/pdf/html formatlarına çeviriyoruz.

## Kurulum

Modüler dokümantasyon sisteminin çalışması için öncelikle aşağıdaki kurulumları yapmanız gerekir:

- brew
- python
- pip
- pandoc
- mdmerge

### brew Kurulumu

`brew` bir package manager (paket yöneticisi) uygulamasıdır. Paket yöneticileri, MacOs, Python ve benzeri platformlarda çeşitli yazılımların otomatik olarak kurulmasını sağlayan araçlardır. Önce `brew` kuracağız. Sonra `brew` kullanarak `pip`, `python` gibi yazılımları kuracağız.

`brew` kurmak için [web sitesine](https://brew.sh/) girin.

Şu komutu terminalden çalıştırın:

```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

### Pip Kurulumu

Pip, Python'un paket yöneticisidir. Dolayısıyla Python modüllerini yüklemek için, pip kullanabilirsiniz.

Googledan `install pip` diye aratınca çıkan [sayfada](https://phoenixnap.com/kb/install-pip-windows) kurulum talimatları var:

```
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py
python get-pip.py
```

Eğer bilgisayarınızda `curl` yoksa, önce onu kurmak için `brew install curl` komutu verin.

Kurulumun ardından doğrulamak için şu komutu çalıştırın:

```
pip --version
```

### Pandoc Kurulumu

Pandoc, farklı dosya formatları arasında dönüştürme yapmayı sağlayan bir yazılımdır. 

Kurmak için brew kullanabilirsiniz. Şu komutu terminalden çalıştırın: `brew install pandoc`

Genel olarak, bir yazılımı MacOs'te nasıl kurulur bulmak için, googleda ilgili anahtar kelimelerler arama yapın, örneğin: "install brew pandoc"

### MarkdownTools2

[MarkdownTools2](https://pypi.org/project/MarkdownTools2/) mdmerge programını içeren bir Python paketidir. Bununla farklı markdown dosyalarını modüler bir şekilde parça parça birleştirmek mümkündür.

Kurulum için:

```bash
pip install MarkdownTools2
```

### R Kurulumu

Şunları googledan aratıp kurun:

- R
- R Studio

Şimdi R Studio'yu açın. Console üzerinden şu komutu verin:

```{r}
install.packages('rmarkdown')
```

## Çalıştırma

Tüm ön bağımlılıkları yükledikten sonra, ilgili scripti çalıştırarak, farklı markdown dosyalarını birleştirip tek bir çıktı üretebilirsiniz.

Örneğin, `test01/main_doc` scriptini çalıştırmak için, terminali açıp, bu scriptin bulunduğu klasöre gidin. Kendi bilgisayarım üzerinden gösteriyorum:

```
cd ~/gdrive/btg/layermark_projects/opal/doc/test01
```

Bu şekilde ilgili klasöre gittik. Bir klasörün dizin yolunu (directory path) bulamadıysanız, Finder'da ilgili klasörü seçip, terminalin üzerine sürükleyip bırakın. MacOs otomatik olarak o klasörün dizin yolunu terminale yazar.

Şimdi scripti çalıştırın:

```bash
./main_doc
```

`test01/main_doc` scriptinin içeriği:


```bash
R -e 'rmarkdown::render("rmarkdown-01.rmd")'

mdmerge --leanpub Book.txt -o output.md

pandoc output.md -o srs.docx
pandoc output.md -o srs.pdf
```

Bunu çalıştırınca, önce R yazılımı `rmarkdown-01.rmd` dosyasını `rmarkdown-01.md` (`.md` uzantısına dikkat) dosyasına çevirir. Daha sonra, yukarıda anlattığımız gibi, `Book.txt` içindeki tüm markdown dosyalarını birleştirip, `output.md` oluşturur. Bunu da pandoc alır ve `srs.docx` nihai çıktı dosyasını oluşturur.


