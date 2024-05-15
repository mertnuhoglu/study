# 20130101-Book--About-Face---Alan-Cooper
  id:: 92a81adf-804a-4162-b162-95e002fce11a
	rfr:: moved-from: 3-337 About Face - Alan Cooper || ((a8254a11-dad5-4afd-bd20-ae156fbd7dd8))

### 3-337 About Face - Alan Cooper

About Face - Alan Cooper  file:///İkitaplar|About%20Face%20-%20Alan%20Cooper|0|0

Alan Cooper

#### 4-338 Amaç

1876´da yapılan her 4 köprüden biri başarısız oluyordu. 

Yazılım yeni bir inşaat alanıdır.

Dar bir köprü uyumsuzdur, teknolojisi ne kadar iyi olursa olsun

Benzer şekilde yazılımda da süper teknoloji yeterli değil. Kullanışlılık da gerekli.

1988´de otomobil üreticileri şunu fark etti: İyi mühendislik, iyi tasarımın daha gerisinde kalıyor.

Yanlış yere odaklanmak -» teknoloji

Kullanıcının amaçları ve ötesini düşünerek hazırlanmış yazılım -» en iyi yazılım
Kullanıcının amaçları:
	- aptal görünmemek
	- makul miktarda iş yapmak
Faturaları verimli bir şekilde kaydetmek -» patronun amacıdır.
Sadece iş amaçlarını ele almak, başarısız yazılımlara sebep olur.
Hem iş, hem de kişisel amaçlara dikkat edilmeli. 

Kötü yazılım 
	- aptal gösterir
	- büyük hatalar yaptırır
	- yavaşlatır -» iş yapamaz
	- canını sıkar

Niye böyleş Teknolojiye odaklanıyoruz, kullanıcıyı ihmal ediyoruz

Kaba yazılım 
	- Hata meydana gelir -» Ok
	- Emin misin?

Kapalı yazılım
	- IRQ´yu belirtiniz
	- Cevapların sonuçlarının ne olacağını bilmediği halde zor sorularla yüzleşmek 
		- Custom/Laptop/Full installation seçenekleri

Uygunsuz davranış
	- 5 dakika önce ne yaptığımızı unutur

"Kullanıcının aptal görünmesine sebep olma"

Not: Diyalog kutularında ünlem işaretleri koymak yerine, bir smiley koyup, (Herhangi bir tuşa basın) desek daha mı güzel olur.

Kullanıcı arayüzü -» implementasyona göre değil, kullanıcının zihinsel modeline uymalı
	- Excelde alt hücrelere scroll etmek
	- Photoshop´ta renkleri seçmek - numaralarına göre değil

WinFax - numara girmek ayrı, isim bulmak ayrı
	- yavaşlarken fren lambasını ayrı yakmak

Kullanıcılar Boolean mantığını anlamaz
	- Texasta ve Arizonada yaşayan insanları bul -» Booleanda "veya"

Araba -» atsız taşıt
Eski çağın kavramları
Telefon 40 yıl -» kişisel kullanılmadı
80 sütunluk kart işlemleri
Takvimler sayfa sayfa - sürekli değil

Mekanik çağı taklit çok daha kötü
	- Adres defteri - ön isme göre arama yok
			ve ayrıca diyalog kutuları engeli

Görsel arayüz - grafik değil
Bilinçdışı otomatik olarak kalıpları algılar

"Görsel olarak ne olduğunu göster, metin olarak hangisi olduğunu"

"Görsel arayüz kalıplar üzerine inşa edilir

İkonlar -» pattern

Simgelerin temsili olması gerekmez. 
Mnemonics, kazlar ve garsonlar hareket eder.

Her yerde birlikte olmalı: metin ve simgesi

Kullanıcının yapacağı girişleri sınırlandırmak
	- ilk guilerin üstünlüğünün sebebi


#### 4-339 2. Bölüm: Biçim



##### 5-340 5. Deyimler

Metafor Efsanesi:

Yazılım tasarımında sık sık "doğru metaforu" bulmaktan bahsedilir. Arayüzün bir metofar üzerine kurulması gerekitği fikri yaygındır. Mesela masaüstü uygulamalarının temelinde, bir ofis masası yatar. Dosyalar, klasörler, çöp kutusu...

Sihirli metaforu aramak, arayüz tasarımında yapılabilecek en büyük hatalardan biridir. Bu tıpkı, uçak motorunu tasarlarken, bir buharlı makineyi temel almak gibidir.

Metaforlar yararsız olmakla kalmaz, çoğu zaman zarar da verir. Metafor, ilk kullanıcıların öğrenmelerine küçük bir hız katkısı olur, ama büyük bir maliyet karşılığında. Eski teknolojilere dayanmalarından dolayı, ayağımızı yere çiviler. Ayrıca yeterli sayıda metafor yoktur. Metaforlar iyi ölçeklenemez ve kullanıcıların bunları algılama seviyeleri sorgulanabilir.

Üç Arayüz Paradigması:

Arayüz geliştirirken temel alınan üç paradigma vardır: Teknoloji paradigması, metafor paradigması ve idiomatic (deyimsel) paradigma).

Teknoloji Paradigması:

Temelinde, kullanılan şeyin nasıl çalıştığını anlamak yatar. Uygulaması basittir. Kullanıcının yazılımın nasıl çalıştığını anlaması gerekir. 

1960´lardaki Metabolist mimari akımı. Kablolar, havalandırma boruları ve diğer inşaat malzemeleri açıkta bırakılır.

"Kullanıcılar bilgi sahibi olmak değil, başarmak ister."

Mühendisler, eşyanın nasıl çalıştığını öğrenmek ister. Bu yüzden bu paradigma onlar için faydalıdır. Ama çoğu kullanıcı için değil.

Metafor Paradigması:

İlk GUI Xerox Parc laboratuvarlarında oluşturulmuştu. Bir GUI çok sayıda nesneden oluşur: fare, simgeler, pull down menuler, windowlar...

Bunların bir kısmı iyidir, bir kısmı pek iyi değildir. Ancak sonuçta hepsi birden bir üstünlük payesiyle ünvanlandırılmıştır.

Metaforların iyi olduğu fikri, çok yanıltıcıdır. Bu tıpkı, çoğu iyi yazılım 5.25"lik floppy disketlerden geldiği için, bu disketleri yüceltmek gibidir.

Macintoshun başarılı olmasının sebebi, metaforlar değildir. Kullanıcının etkileşime girebilmesi için sınırlandırılmış bir vocabulary (kanonik sözlük) tanımlamış olmasıdır. Metaforlar, iyi tasarlanmış bir evde güzel boyanmış duvarlar gibidir.

Metaforlar iyi ölçeklenemez. Mesela dosya simgeleri küçük hard diskleri çin uygundur. Ancak milyonlarca dosyanın saklandığı bir ortamda dosya simgeleri sadece kalabalık oluşturur.

Metaforları sezgisel olarak anlarız. Sezgisel anlayışımızın temelinde, daha önceden bildiğimiz bir şeyle bağlantı kurabilmemiz yatar. Mesela makas kesicidir. Makas simgesi, kesme işlemi yapar. 

Ancak bu yöntem insan zihninin muğlak ve sınırları iyi tanımlanmamış yeteneklerine bağlıdır. Bir simge her zaman aynı şeyi ifade etmez. Mesela bir uçak resmi, "hava postasıyla gönder" anlamına mı gelir "uçak rezervasyonu yap" anlamına mı gelir?

Metafor paradigmasındaki bir ilerleme, teknolojinin mekanizmasını anlamak ihtiyacının kaldırılmasıdır. 

Sezgisel ile kasıt çoğu zaman kolay kullanışlılıktır. Sezginin temelinde rasyonel bir düşünme sürecinin yatması gerekmez denir.

İçgüdüyle sezgi birbirinden farklıdır. Mesela bir çocuğun daha önce hiç görmemiş olmasa bile, saldırgan bir köpekten çekinmesi içgüdüseldir.

Sezgi içgüdüyle, bilinçli öğrenmenin arasındadır. Mesela daha önce kırmızı parıldayan bir şeyin bizi yakabileceğini öğrenmişizdir. Bundan sonra kırmızı görünen şeylere karşı ihtiyatlı yaklaşırız.

Bir çöp kutusunun nasıl kullanıldığını biliriz. Yıllar sonra bilgisayarımızdaki çöp kutusunun da ne işe yarayacağını bir çağrışımla hemen sezebiliriz. Gerçek çöp kutusunu kullanmayı sezmemişdir. Ancak onu dahi öğrenmemiz çok kolay olmuştur. Bu bizi üçüncü paradigmaya götürüyor. Bunun temelinde, insan zihninin çok güçlü bir öğrenme kapasitesine sahip olması yatar.

Deyimsel Paradigma:

Deyimsel yöntem önceki iki paradigmanın da sorunlarını çözer. Deyimsel denmesinin sebebi, deyimleri öğrenişimizi ve kullanışımızı temel almasıdır. Mesela "pireyi deve yapmak" veya "cool" demek gibi.

Bu deyimlerin anlamları kolayca anlaşılır. Ancak bunlar metaforlar gibi değildir. Ortada ne pire ne deve vardır. Ama bu deyimi kolayca anlarız ve kullanırız.

Burası insan zihninin mükemmel yaratılışından kaynaklanır. Deyimleri öğrenmek ve hatırlamak çok kolaydır. Bilinen bir şeylerle karşılaştırma yapmamız gerekmez. Nasıl çalıştıklarını anlamamız da gerekmez. 

GUI arayüzündeki çoğu şey bir deyimdir. Pencereler, status bar, toolbar, screen splitterlar, tablar, drop-downlar... Bunların hepsini deyimsel olarak kolaylıkla öğreniriz. Metaforlarla değil.

Öğrenmenin zor olduğunu kabul etmeye meyilliyiz. Teknoloji paradigması bize böyle göstermiş. Esik arayüzleri kullanmak zordu, çünkü nasıl çalıştıklarını anlamak gerekiyordu.

Bildiğimiz temel nesneler metaforik değildir. Mesela kimse, fareyi "fareyle" karşılaştırmaz. Veya pencereleri, "pencerelerin" özellikleriyle anlamaz.

Bir deyimi sezmek (yani bilinen bir şeyle karşılaştırıp anlamak) veya çıkarımla bulmak mümkün değildir. Dilimiz deyimlerle doludur, eğer bunlar bize öğretilmeseydi, bir anlam çıkaramazdır. "nalları dikmek" deyiminin, ölmek anlamına geldiğini biri bize söylemeseydi anlayamazdık. Nallarla benzetme kurmamız yetmez. Ancak bunu bağlamından anlayabiliriz, veya açıkça öğretilmekle öğrenebiliriz. 

"Bütün deyimler öğrenilmek zorundadır. İyi deyimlerin sadece bir defa öğrenilmesi gerekir."

Kötü deyimlerle iyi deyimleri ayıran kaç defa öğrenilmesi gerektiğidir. 

Marka Oluşturma (Branding)

Pazarlamacılar, basit bir fiili, sembolü alıp, onun içini anlamla doldurmayı ilyi bilir. Yeni deyimler tanımlamak, markası oluşturmanın özüdür. McDonalds´ın m harfi, Olimpiyatların beş halkası, Microsoftun pencereleri hepsi metafor olmayan deyimlerdir. 

Çoğu temel GUI elementi aslında metaforik olmayan deyimlerdir. tıkla ve sürüklenin, pencere kapama düğmelerinin, ayarlanabilir pencerelerin gerçek dünyada benzerleri yoktur.

file:///C:\Documents%20and%20Settings\mnuhoglu\My%20Documents\Notlarim\Resimler\144.gif 

Yukarıdaki simgenin nükleer radyasyonla hiçbir benzerliği yoktur. 

Eğlenceyi Sona Erdirenler:

Metaforlarla çalışıyor olsak ayrıca iki büyük problemimiz daha olurdu:

1. Yeni metafor bulmak çok zordur.
2. Metaforlar düşüncemizi sınırlar

Fiziksel nesneler için görsel metaforlar bulmak çok kolaydır: Mesela printer veya doküman gibi. Ancak süreçler, ilişkiler, servisler veya dönüştürmeler için metafor bulmak çok zor veya imkansızdır. Bilet satın almak, kanal değiştirmek, bir malzeme satın almak, referans bulmak, formatlamak, çözünürlüğü değiştirmek, hangi simgelerle ifade edilebilir?

Daha da kötüsü, metaforlar arayüzlerin mekanik çağın yapılarıyla sınırlanmasına sebep olur. 

Mesela bir yazılım, sokak metaforu üzerine inşa edilmişti. Yazılımın bütün servislerini kullanmak için sokakta doğru binaya gitmek gerekiyordu. Ancak bu kullanımı çok sınırlandırıyordu. Çünkü geriye gitmek için, kolay bir yol yoktu. Geldiğin yolu aynı şekilde geri gidecektin.

Eğer bir arkadaşınız size bir hediye veriyorsa, onu almalısınız. Almazsanız, aptallık olur, hem de kabalık da... Ancak bütün geçiminizi, hediye almak üzerine kurarsanız, bu çok daha büyük bir aptallık olur. Bunun gibi, metaforlar da eğer uygunsa kullanılır, ama yoksa bunun üzerine inşa edilmez. 

"Arayüzünü bir metafora uydurmak için sınırlama."

Bir kütüphane yönetim sistemi -
	Bazı düğmeler kalıcı bazıları bulunulan sayfaya bağlı
	Değişenleri bir not defterinin içine koyduk. Sayfa değiştirdikçe değişiyor.
	Diğerleri dışarıda.
	Kullanıcı ikisinin ayrımını ve düğmelerin kapsamlarını böylece anlayabildiler.

Yukarıda bahsettiğimiz sokak navigasyonu veya orjinal Macintosh küresel metaforlardır. Küresel metaforların gizli bir problemi, daha küçük metaforların bunlarla tutarlı olmasının anlaşılmayı kolaylaştıracağı varsayımıdır. Bu doğru değildir. Mesela bir telefon yazılımının bildiğimiz telefon gibi tuşları olması gerekmez. Niçin kullanıcıyı telefon rakamlarının zahmetinden soyutlamıyoruz. Doğrudan arayacağımız kişinin ismini seçmek yeterli olmalı.

Şimdi eski telefon cihazlarının sınırlamalarıyla kısıtlanmadığımız halde niçin, hala aynı telefon cihazını yapalım?

Algılanan Özellik (Affordance)

Donald Norman "The Psychology Of Everyday Things" kitabında, algılanan özelliklerin, eşyanın nasıl kullanılabileceğini belirttiğini söylüyor.

Eğer kapı yanında basılabilir bir düğme görürsek, bunun kapı zili olduğunu düşünürüz. Düğmeye bastığımızda, altımızdaki tuzak açılıp, deliğin içine düşersek ancak, bunun aslında bir kapı zili olmadığını anlarız. Ama bu yine de düğmenin algılanan özelliğini değiştirmez.

Peki onun kapı zili olduğunu nereden biliyoruz? Evvelki sosyalizasyon ve yetiştirilme sürecimizden.

Ancak burada önemli bir etki daha var. Eğer basma düğmesini uygun olmayan bir yerde, mesela arabanın tavanında görürsek, amacının ne olduğunu bilemeyiz. Ama bunun yine de parmakla basılabilir bir nesne olduğunu fark ederiz? Bunu nereden anlıyoruz. İçgüdüsel olarak olmadığı kesin. Ancak bakınca, parmak büyüklüğünde, parmak yüksekliğinde, basılabilir bir şey olduğunu görünce bu algı oluşuyor. Uzun ve yuvarlak bir şey gördüğümüzde, parmaklarımızla onu sarıyor ve tutaç gibi onu kavrıyoruz. Norman´ın affordance ile varmak istediği bu olsa gerek. Bu içgüdüsel anlayışı, el algısı (manual affordance) olarak adlandıralım.

Norman, el algısının yazılı tarfiten çok daha etkili olduğunu söylüyor. Mesela bir kapının ortasına konulmuş metal bir çubuk düşünelim. Metal çubuk, tam elle kavranabilecek şekil, yükseklik ve konumdadır. Kapının el algısı, "beni it" diye bağırır. Kapının üzerinde ne kadar "çekiniz" diye yazarsa yazsın, insanlar kapıyı iterler.

Windows 95´deki üç boyutlu görünümlü düğmeler de, el algısını yerleştirir: "Bana bas" derler.

Ne Anlama Geldiğini Anlamak

El algısı, eşyanın ne yaptığını söylemez. Bir düğme gibi göründüğünü anlarız, ama ne işe yaradığını anlamayız. Bunun için metin ve resimlere dayanırız. Ama en çok daha önceki öğrendiklerimize dayanırız. 

Anlaşmaya Uymak

Eğer basılabilir görüntülü bir düğmemiz varsa, kullanıcı buna basabilmeli. Kullanıcıya burada bir vaatte bulunuyoruz. Düğmelerde değil, ancak metin kutularında sık sık el algısı, işleve uymuyor.

##### 5-341 6 Ekrandaki Dikdörtgenlerin Saygısız Tarihi

Ekrandaki Dikdörtgenlerin Saygısız Tarihi

Windowların tarihçesini anlatmak istiyorum ki, okuyucu ona aşırı yüksek bir değer yüklemesin.

PARC´ın İlkeleri

Modelar

Eski programlarda modelarla davranış idare ediliyordu. Mesela kayıt girmek için belirli bir moda girmek, baskı almak için ayrı bir moda girmek gerekiyordu.

Bu yüzden Apple´daki bazı PARC üyeleri artık modeları terk etmeye çalışıyordu.

Ancak en başarılı masaüstü uygulamalarından olan MacPaint mod temelli bir arayüze sahipti. Bu bir çizim programıydı. Kullanıcı, araç panelindeki, araçlardan birini seçiyordu: Mesela bir sprey boyayı veya düz çizgiyi. Ve program artık bu moda uygun şekilde davranıyordu.

Mesele şuydu: Mode kullanıcının zihinsel modeline dayandığı sürece yararlıdır, ancak eğer programın kendi yapısına dayanıyorsa zararlıdır.

Üstüste Pencereler

İlk Maclerde pencereler üstüste geliyorlardı. Masaüstü metaforuyla uygundu bu. Kağıtlar üstüste dizilebilir. 

Ancak kullanıcılar için örtülü bir uygulamaya geri dönmek oldukça zahmetli oluyordu. Bu yüzden Windows 95 Taskbar ile bu işi kolaylaştırdı. Ayrıca eski yöntemde, ufak bir klikleme hatasından kullanıcılar ayrı bir uygulamaya geçebiliyorlardı.



##### 5-342 7 Küçük w ile Windows

Gereksiz Odalar

Genellikle programlar iki tip pencerelerden oluşur: Ana pencere ve alt pencereler (dokümanlar ve diyalog kutuları)

Ana pencereyi bir ev gibi düşünelim. Alt pencereleri de evin odaları olarak düşünelim. 

Yeni pencere eklemekten mümkün olduğunca kaçınmak lazım, pencerenin faydalı bir amacı olmadıkça. Odanın bir amacı olmalı, sadec belirli bir iş veya fonksiyon yeterli değil. Mesela, evin kapısının önünde biriyle el sıkışmanın bir anlamı vardır. O kişiyi karşılamak için yapılır. Başka bir odada el sıkışmak farklı bir anlama gelir.

Eğer ben elimi uzattığımda, sen bana desen ki "Bekle, el sıkışmak için diğer odaya gidelim", çok saçma olur. Hangi odada olduğumuzun bir manası yok. İkimiz de el sıkışmanın altında yatan gerekçeyi biliyoruz, ayrı bir odaya gitmenin hiçbir anlamı yok. Eğer diğer odada el sıkışmanın ardından yine eski odaya geri dönmek iyice saçmalık olur.

Diyalog kutularını oda olarak düşünürsek, El sıkışmak için oda değiştirmeyi gerektiren programlara birçok örnek bulabiliriz. Mesela yeni classların import edilmesi için JBuilder´da ClassInsight adında yeni bir diyalog kutusuna girmek gerekiyor. Halbuki aynı şeyi IntelliJ´de aynı pencerede yapmak mümkün. 

Birçok çizim programında, mesela gölgelerin derinliği yeni bir diyalog kutusunda tanımlanır. Daha sonra ana ekrana dönersin ve çizimin görünüşüne bakarsın. Halbuki bir insan, çizdiği resmin gölgesini değiştirmek için masa değiştirmez. Böyle ana fonksiyonların ana ekranda bir toolbarda bulunmasında fayda var. 

Programcının bakış açısından gölge düşürmek, ayrı bir fonksiyondur. Bu yüzden ayrı bir diyalog kutusunda ele alınması makuldur. Ancak kullanıcının bakış açısından bu dahili bir fonksiyondur ve ana pencere entegre olmalıdır.

"Fonksiyonların kontrollerini, kullanıldıkları pencereye koy."

Bu kullanıcı arayüzü tasarımında en çok ihlal edilen kurallardan biridir. Ayrıca GUI builder araçlarının çoğu statik diyalog kutularını kolayca yapmamıza izin verir. Ancak mevcut bir pencerede bağlama göre gui oluşturmak çoğu zaman geliştiricilerin çabasını gerektirir.

Gerekli Odalar

Denize gitmek için üstünü değiştirmek istiyorsan, benden özel bir giyim odası isteyebilirsin.

Benzer şekilde, programın normal fonksiyonalitesinin dışındaki davranışlar için ek pencereler sunmak faydalıdır. Mesela veritabanındaki kayıtların eklenmesi, değiştirilmesi normal aktivitedir. Ancak kayıtların silinmesi, sıradışı bir aktivitedir. Bunun için ayrı bir diyalog kutusu kullanmak doğrudur. 

Pencere Kirlenmesi

Bazı tasarımcılar, her bir diyalog kutusunun tek bir işlevi olması gerektiğini düşünüyor. Ancak bu pencere kirlenmesine sebep oluyor.

Pencereler mutlaka her zaman iyidir diye bir kural yok. Bisiklete biraz yağ koymak daha iyi pedal çevirmeyi sağlar. Ancak çok fazla koymak, pedallerin kendi kendine dönmesini sağlamaz. 

VB gibi RAD araçları form oluşturmayı çok kolaylaştırıyor. Bu yüzden de insanlar çok sayıda form ve pencere ile uygulamaları tasarlıyor. Bu da uygulamaların kullanımını zorlaştırıyor.

Mesela bir email aracında, email arama, bulma ve okuma ayrı pencerelerde yapılıyordu. Halbuki bunların hepsi aynı pencerede birleştirilebilir. Böylece kullanıcı, daha hızlı hareket edebilir. 

##### 5-343 8 dosyaların Kralı

Yaşlı bir insana bilgisayar kullanmayı öğrettiyseniz, zorluğun ne demek olduğunu bilirsiniz. Başlatırsınız, Word programını açarsınız. Bir şeyler yazarsınız. İzleyiciniz sizi takip eder. Ancak kapatırsınız ve bir diyalog kutusu çıkar: "Değişiklikleri kaydetmek istiyor musunuz?" İzleyiciniz birdenbire heyecanlanır. Ne diyor? Ne değiştiş Bir sorun mu varı

Dosya Sisteminin Trajedisi

Ana bellekle hard disk arasındaki farkı birçok kullanıcı bilmez. Ancak bilgisayar kullanabilmek için, bilgisayarın iç yapısına ait olan bu iki şeyi insanların bilmesi gerekir. 

İnsanların hemen hemen her zaman Evet diye cevap verdikleri bir diyalog kutusu gereksizdir. 

İnsanlar, niçin bu sorunun kendilerine sorulduğunu anlamayacaktır. Okuduğunuz bir kitabı kitaplığa geri koyarken, kenarlara yazdığınız notları silmezsiniz. Bir odayı terk ederken, oradayken yaptığımız işleri geri almayız.

Ancak biz tecrübeli kullanıcılar bu diyalog kutusunu maksadının dışında kullanmayı öğrenmişizdir: Büyük geri alma işlemleri için.

IDElerde Run etmeden önce Compile etme, Compile etmeden önce Save etme zorunluluğu. JBuilderda java dosyalarının dışındaki dosyalardaki değişiklikleri elle save etmek.

Tek Dosya Modeli

İki farklı dosya bulunmamalı: Bir hafızadaki bir de hard diskteki olmak üzere.

Save

Uygulama otomatik olarak yapılan her değişikliği (idealde) kaydetmeli. En azından kapatılırken, kendisi otomatik kaydetmeli. Ama kullanıcıya da istediği zaman kaydedebilme imkanı vermeli, sonuçta her an bir çökme meydana gelebilir..

Örnekler: Adres Defteri, IntelliJ, Outlook 

Kapatmak

Kapatma sırasında yeni açılmış bir dosyanın isminin ne olarak kaydedileceği (Save As) kutusu çıkar. Kullanıcı burada hem isim vermelidir, hem de dosyanın saklanacağı yeri belirlemelidir. Ancak çoğu insan varsayılan klasör neresiyse oraya dosyayı kaydetmek ister. 

Bill adında bir komşusu var Alan Cooperın. Her 6 ayda bir Lotus dosyalarını bulabilmek için Alan´ı çağırır. Ona ilk defa bu dosyaları nerede tuttuğunu sorduğunda, Bill masumca "Lotusta" diye cevap vermiş. Billin zihinsel modelin yazılımın implementasyon modelinden farklı. Ve tabi ki Billinki doğru. 

Save As diyaloğunun amacı net değildir. Eğer amacı, dosyaları isimlendirmek ve yerleştirmekse, bu amacı gerçekleştiremiyor. Çünkü bir kullanıcı bir kere dosyayı isimlendirdi ve yerleştirdi mi, bir daha bunu değiştiremiyor. Uygulamanın içindeki herhangi bir araçla da bunu yapamıyor.

Tecrübeli kullanıcılarsa, zor yolu uyguluyorlar. Dokümanı kapıyorlar. Explorera geçiyorlar. Dosyanın ismini değiştiriyor ve uygulamaya geri dönüyorlar. 

Kullanıcı Explorerdan ismi değiştirmek istese bir tuzak onu bekliyor. Dosyayı eğer henüz kapatmamışsa, sistem kaba bir hata verir: "İsim değiştirilemedi: Erişim engellendi" Hiçbir şey açıklamıyor. İşin garibi, ismi değiştirme yetkisine ve sorumluluğuna sahip olması gereken uygulama açıktır ve fakat işini yapmayı reddetmektedir.

Arşivleme

Dosyamızın bir yedeğini almak istiyoruz. Bunun için açık bir komut yoktur. Ancak Save As komutuyla dolaylı yoldan bunu yapabiliriz.

Alfa isimli dokümanımızın, ismini değiştirerek, "Alfanın Kopyası" diye kaydedebiliriz. Ancak bu durumda da iki farklı kullanıcı algısı gerçekleşir: Bazı kullanıcılar, kopyanın bir yerde saklandığını, bizim orjinal dosya üzerinde iş yaptığımızı düşünecektir. Bazı kullanıcılarsa, orjinal dosyanın bir yere konulduğunu, kopya üzerinde devam ettiğimizi düşünecektir.

Tecrübeli kullanıcılar ikinci yolda olduklarını düşünür. Gerçekten de böyledir. Save As yeni isimlendirilmiş dosya üzerinde uygulamayı devam ettirir. Bu kullanıcı açısından nahoş bir tuzaktır. 

İyi bir marangoz yanlış araçla çalışmanın ya aracı ya da işi bozacağını bilir. Save As arşivleme için kullanılacak araç değildir.

Doküman Yönetimi

Standart komutların yetersiz kaldığını gördük. Peki ne olmaliş

Dosyanın kopyasını oluşturmak

Bunun için açık bir fonksiyon olmalı. "Snapshot kopya oluştur" Snapshot, kopyanın orjinalin aynısı olduğunu ve orjinal dosyaya bağlı olmadığını gösterecektir. Bunun için ek bir diyalog kutusu çıkmamalı, çünkü kullanıcı bu komutu basit bir işlem olarak görmektedir.

İsimlendirme ve Yeniden Adlandırma

Dokümanın ismi, uygulama toolbarının sağ tarafında bulunmalı. Kullanıcı ismi değiştirmek istiyorsa, üzerine kliklemeli ve değiştirebilmeli. Bundan daha basit bir yol olabilir miş

Dosyayı yerleştirme ve yerini değiştirme

Otomatik olarak dosyalar sistem tarafından yerleştirilmeli. Ancak kullanıcı özel olarak yer tanımlamak istiyorsa buna izin vermeli. Bunun için mevcut dokümanın rengi vurgulanabilir. Kullanıcı istediği konumu seçer. 

Dosyanın formatını belirlemek

Çoğu Save As kutusunda dosya formatı da belirtilebilir. Bunun burada olmaması lazım. Kaydetme işlemi, insanlar açısından çok kolay olmalı. Bunun için ayrı bir komut olmalı. 

Dosya formatı kullanıcı açısından dokümanın bir özelliğidir, diskteki dosyanın değil. Bu yüzden format, daha ziyade bir "Doküman Özellikleri" kutusunda belirtilmeli. 

Ayrıca format tanımlama çok az kullanılan bir özelliktir. Kaydetme ise çok sık. Bu yüzden bu ikisi aynı ortamda birleştirilmemeli. 

Bazı değişiklikleri geri almak

Bunun için bir kilometre taşı fonksiyonu yürütülmeli. Dosya sistemini kullanıcı bu amaçla kullanmamalı.

Bütün değişiklikleri iptal et

Böyle basit bir iptal fonksiyonu olmalı. Kullanıcı buradaki veri kayıplarından haberdar edilmeli. Mümkünse, bir iki haftalık geri dönüş imkanını yine de bu komuttan sonra bile sunmalı. Bu çok taktir edilecektir.

Kilometre taşı oluşturmak

Bu snapshot oluşturmak gibidir. Ancak her bir kilometre taşı, sistem tarafından takip edilir. 

Dosya menüsü

Bütün bunların ardından Dosya menüsünün ismini de uygulamamızın ana dokümanlarına uygun bir isimle değiştirebiliriz. Mesela bir faturalama uygulamasında "Fatura", patent yönetim uygulamasında "Patent" ismini verebiliriz.

Son savunma

Son olarak iki argüman sunulabilir, dosya sisteminin lehine:

1. Mevcut yazılımlar buna göre tasarlanmıştır.

2. Kullanıcılar buna alışmıştır.

Yeni yazılımlar mevcut yazılımlarla birlikte yaşayabilir.

Kullanıcılar, daha kolay ve rahat bir modelle çalıştıklarında geçişi çok kolay yaparlar. Ancak şimdi insanlarla test yapsanız, büyük oranda kabul etmeyeceklerini söyleyeceklerdir. 



##### 5-344 9 Saklama ve Bulma Sistemleri

Bulma fonksiyonuyla ilgili yazılımların sunduğu mekanizmalar, mekanik çağdan kalan özelliklere bir şey eklemiyor.

Gerçek kitap ve kağıtlardan oluşan bir kütüphanede, en azından üç tane indeksimiz vardır: Yazar, konu ve başlık. Bilgisayarlarımız yüzlerce farklı indeksi kaldırabilecekleri halde, biz bu kapasiteden yararlanmıyoruz. Dosyalarımızı nereye koyduğumuzu hatırlamak ve böylece bulmak durumundayız. Bu ihmal, modern yazılım tasarımındaki en geri adımlardan biridir. 

Gerçek dünyada saklamak ve bulmak aynı şekilde gerçekleşir: Mekan özelliğiyle. Bir kaşığı bulmak istiyorsak, koyduğumuz yeri hatırlamalıyız. Kaşığın diğer özellikleriyle şekli, boyutu, arama yapmamız mümkün değil.

Bulma Yöntemleri

Bir dosyayı bulmanın temel olarak üç yolu var:

1. Nerede bıraktığını hatırlayarak: Konumsal erişim

2. İsmini hatırlayarak: Kimliksel erişim

3. Dosyanın bir iç niteliğine dayanarak arama yapmak yoluyla: Çağrışımsal erişim

Mesela kırmızı kaplı bir kitabı bulmak istiyorsam veya hafif raylı sistemlerden bahseden bir kitabı bulmak istiyorsam, bu çağrışımsal ereşimdir.

Çağrışımsal ereşim, saklama sisteminin bir fonksiyonu değildir. 

Dosya ve İçinde Yaşadığı Sistem

Gerçek dünyada bir kitabın mutlaka belirli bir saklama mekanizması içinde (mesela kitaplık) bulunması gerekmez. Ortalıkta dolaşabilir.

Ancak yazılımlarda böyle değil. Bir dosya mutlaka dosya sisteminin bir parçasıdır.

Indeksleme

Kütüphanelerde her bir kitaba tekil bir id verilir (Dewey veya LC). Bu idye göre saklanır. Ancak kitap bulmak için farklı indeksler sunulur: Mesela yazar, konu ve başlık gibi. Kullanıcılar bu indekslerden kitabın idsini bulur. Ve daha sonra fiziksel olarak temin eder. Dolayısıyla id mekanizması, saklama ve erişim mantığı arasında bir köprü olur.

Yazılım dünyasındaysa, çağrışımsal bir erişim mekanizmamız yok. Dosyaları sakladığımız yerde bulabiliyoruz ancak.



##### 5-345 10 Platform seçmek

Her ne kadar donanım daha pahalı görünse de aslında yazılım daha pahalıdır.

Yalnız geliştirme maliyeti noktasında yazılım ucuz.

Ancak kurulum, eğitim ve bakım maliyetleri + kullanıcıların kaybettikleri zamanın fırsat maliyeti düşünüldüğünde yazılım çok daha değerlidir.



#### 4-346 3. Bölüm: Davraniş

Yazılımların çoğu çok kötü bir arayüze sahip. Birçok yazılımın başarısız olduğunu gördük. Ancak yine de ne detaylarda ne de büyük meselelerde bir uzlaşmaya varamadık.

Arayüz tasarımı büyük oranda ya tahmin etmeye, ya da kopyalamaya dayanıyor.

Halbuki böyle olmak zorunda değil.

##### 5-347 11 Orkestrasyon ve akış

Yazılımları daha verimli kılmak için, kullanıcıları daha verimli kılmalıyız. Kullanıcıları daha verimli kılmak için, onlara uyumlu bir zihinsel çerçevede sunum yapmalıyız.

Akış

İnsanların çok yoğun ve verimli çalıştığı kısa zaman aralıklarına akış (flow) denir. Bu sırada insan zamanın geçişini hissetmez. İnsan aşırı verimli olur. Ancak bu sırada insanın yaptığı aktivietelrin çoğu: mühendislik, tasarım, geliştirme ve yazma gibi yazılımla etkileşim içinde yapılır. Dolayısıyla yazılım etkileşimi kullanıcının akışa girmesine yardımcı olmalı, engel olmamamıl. 

Örnek: Satır başına gelmek için Home tuşuna basmak ve sonra tek tek ilerlemek (JBuilder)

Akışı destekleyen teknikler

Akışı oluşturmak için, yazılımla etkileşimimizin şeffaf olması lazım. Yani kullanıcı bir yazılımla iş yaptığını hissetmemeli.

1. Zihinsel modelleri takip et
2. Yönet, tartışma
3. Araçları yakın bir yerde tut
4. Modesuz geri besleme sağla

Zihinsel modelleri takip et

Kullanıcılar sürecin nasıl işlediğine dair kendilerine ait bir zihinsel modelleri vardır. Zihin, sebe sonuç kalıbını hemen algılayabilecek basit bir kalıp arar. 

Mesela araba yarışlarında, arabada bir hata olup olmadığını gösteren basit bir ölçek vardır. Bu direksiyon hizasındadır, sürücünün çevresel görüşünün içindedir. Eğer iğne düz ise, bir hata yok demektir. Eğik olursa, sürücü dikkat eder.

Tartışma, Yönet

İdeal etkileşim iki yönlü bir diyalog değildir. Kullanıcı bir şey yapar, sistem bir tepkide bulunur. Kullanıcıların aradığı daha ziyade, araba kullanma şekillerindeki gibidir. Mesela, kullanıcı kapıyı açar. İçeri girer. Anahtarı çevirir ve gaza basarak ilerler.

Kullanıcılar bir aracı kullandıkları gibi, yazılımı kullanmak ister. Kullanıcı direkt geri besleme ister. Mesela araba kullanırken, yan pencerelerden görüş, çeşitli ölçekler, havanın ve tekerlerin sesi, merkez kaç kuvvetinin hissi, aynalardaki görüntü gibi.

Sürücü, arabanın kendisine bir diyalog kutusundaki gibi sorular sormasını istemez. "Motor daha hızlı gidemiyor. OK"

Yazılımın insanlarda yılgınlığa sebep olmasının en önemli sebeplerinden biri, araba veya çekiç gibi davranmamasıdır. Bizimle bir diyaloğa girer. Yapamadığım şeylerden bizi haberdar eder ve bizden cevap bekler. Kullanıcı açısından doller tersine olmalıdır. Kullanıcı talepkar olmalı ve yazılım cevap vermeli. 

Direkt manipülasyon daha iyidir. Mesela kullanıcı bir nesneyi alır ve bir yere sürükler. 

Araçları yakın bir yerde tut

Kullanıcı araçlara çabucak ulaşabilmeli. (Bir toolbar gibi)

Yazılım araçların kullanımıyla ilgili bilgiyi hemen ve akışı bozmadan sunabilmeli (status bar gibi)

Modesuz geri besleme

Eğer yazılımın kullanıcıya bilgi sunması gerekiyorsa, bunu yapmasının çeşitli yolları vardır. En yaygın yöntem, bir diyalog kutusu çıkarmak. Bu modlu bir tekniktir. Programı belli bir modun içine sokar ve kullanıcının bu sırada normal işini yapması mümkün değildir. Bunun yerine bilgilendirme modsuz olmalıdır.

Status bar ile sağlanan bilgi. Mesela kullanıcı için kelime sayısı önemliyse, bunun hemen status barda bulunması lazım.

Orkestrasyon

"Arayüzün ne kadar şık olursa olsun, ne kadar az olursa o kadar iyi"

"İyi arayüzler görünmezdir." Yani kullanıcı bir bilgisayarla etkileşim içinde olduğunu unutacak kadar işine odaklanabilir.

Mümkün ve Muhtemel

Bir şeyin mümkün olması, muhtemel olması anlamına gelmez. Eğer bir iş 9/10 aynı şekilde gerçekleşiyorsa, 1/10´u dikkate alarak işi yönlendirmemek lazım. 

Örnek: Print, Save, Delete (Excelde)

İnsanlar belki işlerini bu şekilde yapmaya alışmış olabilirler. Ama yeni yolu görünce bundan çok daha memnun kalacaklardır. Bir köpeğin bir kere ısırılmış olması, onu yeniden ısırmayı gerektirmez.

Nicel Bilgi

Windowsun alt status barında eskiden Diskte ne kadar boş yer olduğu yazardı: 231,859 KB boş, 1,039,204 KB toplam.

Bu okunabilir bir bilgi sunumu değildir. 

Görsel sunum uzmanı Edward Tufte´ye göre, sayısal sunumlar şu soruyu cevaplamalı: "Neyle karşılaştırıncağ" 231,848 KB boş yer olması, diskin %22´sinin boş olduğu bilgisinden daha yararsızdır.

Yeni Windowslarda artık bu bilgi bir pay grafiğiyle gösteriliyor. Ancak bunun için de diski seçip, özelliklerine girmek gerekiyor. Daha kolay görülebilirdi bu bilgi.

Grafiksel Giriş

Visio´da bir pay şemasını grafiksel olarak tanımlamak mümkün. Sizin çizdiğiniz grafiğe karşılık gelen sayısal verileri, Visio kendisi üretir. 

Programın Durumunu Yansıt

Program uyuyorsa, meşgulse veya başka bir haldeyse bu belli olmalı. Aynı bir insanın durumunun belli olduğu gibi. Mesela bilgisayar bir faks göndermekle meşgulse, bir faks gönderme animasyonu görebilmeliyiz.

İnsanlar birbirleriyle olan ilişkilerinde bu geri beslemeyi beklemekle kalmaz, aynı zamanda sosyal düzenin devamı için bu bilgiye bağımlıdırlar.

Yönlendir

Bir tablo oluşturma programını kullanıyordum. Program benden tabloyu oluşturmadan önce çok sayıda bilgi istiyordu. Bunlarla uğraşmak istemiyordum. Ben tabloyu görmek istiyorum. Ondan sonra özelliklerini ayarlarım.

Nezaket, insanlara önce hizmeti sunmayı, ondan sonra onu kendi isteklerine göre ayarlamayı gerektirir. Öbür türlü bir hizmet vermeden önce insanları iyice sıkıntıya sokmuş oluruz.

Kullanıcıya Rapor Verme

Programcılar ne olup bittiğini bilmek isterler. Programın doğru çalışıp çalışmadığından bu şekilde emin olurlar. Mesela debug amaçlı bilgileri konsola veya mesaj kutularına yazdırırlar. Mesela veritabanı güncellendi, 274 kayıt işlendi, kullanıcılar login oldu gibi.

Ancak bunlar kullanıcı açısından gereksiz verilerdir. Bunlar bir makinenin çok gürültü yapması gibi kullanıcıyı rahatsız eder.

"Normal durumları raporlamak için diyalog kutuları kullanma."

Aynı şekilde ciddi olmayan sorunlar için de kullanıcıyı rahatsız etme. Mesela program belirli bir sorunla uğraşıyorsa, bunu doğrudan kullanıcıya diyalog kutusuyla raporlama. Bunun yerine bir durum göstergesiyle sinyal ver. Eğer kullanıcı istiyorsa, bununla ilgilensin. 

Kullanıcı etkileşiminin orkestrasyonu, amaç odaklı bir yaklaşım yürütmeyi gerektirir. Belirli bir etkileşimin, kullanıcıyı daha hızlı ve doğrudan olarak amacına ulaştırıp ulaştırmadığını sormalısın. 

Sorgulama Modu

Programlar, bir iş yapmadan önce kullanıcıdan çeşitli cevaplar talep etmeye yönelik bir davranış modu içindedir. Bu davranış şeklinde sorgulama modu diyoruz.

Bu moda girilmesinin iki sebebi vardır: Bazen programcılar kullanıcıdan cevaplar talep etmenin bir hak olduğuna inanırlar. Bu açıdan tümüyle yanılırlar. Aksine program kullanıcıya seçenekler sunmalı. İkinci sebep programların çoğunun kendilerine ayar yapılmadan çalışamamasıdır. 

Programdan bir şey istersin. O da senden birçok detay ister. Programdan bir hizmet talep etmemiz, sorgulama moduna girmesi için bir mazeret olmamalı.

Mesela bir sayfanın basılmasını istediğimizde çoğu program bize büyük bir Print diyalog kutusu getirir. Sayfa yönlendirme, sayfa sayısı ... sayısız bilgiyi ayarlamamız gerekir. İstediğimiz sadece basit bir baskıdır. 

Daha iyi bir tasarım bir Print komutu bir Baskıyı Konfigüre Et komutu sunmaktır. Böylece basit işlemi yapmak isteyen kişi bunu hızlıca yapabilir. 

"Eğer çeşitli seçeneklerin varsa, bunların arasından tercihte bulunulmalı" görüşü, mümkün olanın üzerine odaklanan bir görüştür. Kullanıcı merkezli bir yaklaşım değildir. 

Mantıklı Etkileşim

Eski Windowslarda isim değiştirmek istediğinizde, bir diyalog kutusu çıkardı:

	From: xxx.doc
	To: ...

Burada birçok hata var:

From kısmı değiştirilebilir bir alan olmamalı. 
To kısmı, bize bir kolaylık sunmalı. Her şeyi en baştan yazmamız gerekmemeli.

.doc uzantısı bizi ilgilendirmez. Program bizim hata yapmamıza izin vermemeli. Borlanddan bir adam, Alan Cooper´a bu tarz etkileşimin şuna benzediğini söylemiş: "Bana bir balık olup olmadığımı sor." "Sen bir balık mısın?" "Hayır, niye sordun?"

Diyalog kutusu bizi bağlamdan ayırıyor.

Diyalog kutusuna hiç ihtiyaç bile yok.



##### 5-348 12 Tavır ve Durum

İnsanlar çalışırken belirli bir tavra sahiptir: Öğretmen güçlüdür. Kasa görevlisi sıkılmıştır. Aktör canlıdır. Programların da bir duruş tarzı vardır.

Tavır

Programın davranışı, nasıl kullanıldığını yansıtmalıdır. Mesela Excel gibi kullanılacak bir program yapıyorsan, Excele benzetmende fayda var. Aksi taktirde mesela Tayyip Erdoğanın şaklabanlık yapması gibi bir program ortaya çıkar.

Programın davranışsal duruşuna, tavır diyoruz.

Egemen Tavır

Bir program, çalışan tek program ve kullanıcının dikkatini uzun süre kendinde toplayan programsa, buna egemen tavırlı uygulama diyoruz.

Egemen uygulamalar, kraliyet üyeleri gibi davranırlar. Çevrelerinde birçok yan uygulama bulunur. Bütün ekranı kaplarlar. Kompleks uygulamalardır.

Word, Powerpoint ve sektörel otomasyon yazılımları böyle uygulamalardır.

Bu uygulamalar uzun süreliğine kullanılırlar. 

Dolayısıyla egemen tavırlı uygulamaların tasarımında tecrübeli kullanıcılar önceliklendirilmeli. Yeni kullanıcıların kolay öğrenebilmesi için uygulamaların gücünden fedakarlıkta bulunulması doğru olmaz. Çünkü yeniler hemen öğrendikten sonra, yeni bir şeyler ararlar.

Ancak bu demek değil ki, hem tecrübeli kullanıcılara hitap edip, hem de yeniler için kolaylaştırmak tercih edilmesin. Mesela WordPerfect böyle bir yazılımdı. Word çıktı. Hem güçlüydü, hem de yeni insanlar için öğrenmesi kolaydı.

Ekranı kaplar

Maksimum modda çalıştırılırlar.

Çok sayıda düğme, toolbarlarda bulunabilir.

Renkler sınırlı olmalı. Çok renkli bir Look and Feel oluşturmamalı. Çünkü böyle uygulamalar ilk etapta ilgi çeker, ancak sonradan fazla gösterişli ve dikkat dağıtıcı bulunurlar.

Kullanıcılar uzun süre aynı yere bakacaklar. Dolayısıyla küçük farklılıkları algılayabilecekler. Bu yüzden düğmeler küçük olabilir. 

Kullanıcı açısından zengin bir görsel geri besleme ortamı oluştururlar. Mesela status barlarda, scrollbarların sonunda, ve diğer köşelerde çeşitli bilgileri kullanıcılara iletebilir.

İlk kullanıcılar bu yapıların çoğu zaman farkına varmayacak şekilde olmalı. Böylece kalabalık karşısında şaşırmamalı. Ancak ilerledikçe bunların faydalarını keşfedebilmeli.

Mesela dershane uygulamamızda, Ödeme Vadesi dolan öğrenciler: 13, Devamsız öğrenci: 12 gibi veriler sunulabilir.

Veya programın şu an yaptığı kritik işlemler belirtilebilir: Kaydediyor...
Veya kullanıcıya ipuçları, kısayol bilgileri sunulabilir. Ctrl-F4...
Seçilebilir düğmelerin isimleri çıkabilir...

Benzer şekilde kullanıcı girişi de zengin olmalı. Program çok çeşitli yollardan kontrol edilebilmeli. Doğrudan manipülasyon, diyalog kutuları, düğmeler, klavye kısayolları, klavye hızlandırıcıları kullanılmalı.

Sık yapılan işlerde bekleme olmamalı: Mesela kaydetmek gibi. Bu işler saniyenin onda birinden kısa sürede gerçekleşmeli. Ama mesela bir rapor çıkarılması gibi aradabir yapılan işler belirli bir süreyi alabilir.

Geçici Duruş

Bir yazılım eğer sadece basit ve tek bir fonksiyonu yapmaya yönelik hazırlanmışsa, mesela bir grafiğin taranması gibi, bunlara geçici uygulamalar diyoruz.

Program gerektiğinde çağrılır, görünür, işini yapar ve sonra hızlıca çıkılır. 

Geçicilik özelliği olduğu için, kullanıcının uygulamayı detaylı olarak tanıması zordur. Bu yüzden kullanıcı arayüzü daha açık ve hatalara imkan bırakmayacak şekilde olmalı. Düğmeler daha büyük olmalı.

Görsel olarak daha renkli ve parlak olmalı. Amacını daha açık ifade etmeli. 

Kullanıcıların talimatları unutması ihtimali yüksektir. Bu yüzden bir düğme "Ayarla" yerine "Kullanıcı Tercihlerini Ayarla" şeklinde uzun olmalıdır. 

Kullanıcının dikkati farklı diyalog kutularına ve pencerelere dağılmamalı. Bütün her şeyi tek bir pencerede toplamaya çalış. 

Kenarlara çok fazla düğme konulmamalı. Büyük düğmeler, ana panelde üst tarafta bulunmalı. 

Klavye girişi de sınırlı olmalı. Enter, Escape ve Tab dışında oklar kullanılmalı. Bu kadarı yeter.

MDI arayüzleri bu tarz uygumalara çok uymaz. Pencere yönetimini zorlaştırır. Mesela Windows 3´deki Program Manager bir MDI uygulamasıdır. Halbuki kullanıcılar açısından bu sadece bir dosyayı açmak için kullanılan geçici bir uygulamadır. Bu yüzden birçok Windows Shell uygulamaları çıkıyor. Windows 95 bile bunu değiştirdi.

Programa bir hafıza vermekte fayda var. Program en son ne boyutta ve konumda olduğunu hatırlamalı. Yeniden aynı şekilde başlamalı. 

Eğer program çok basit ve tek şekilliyse, mesela Windowsun Calculatorı gibi, o zaman boyutu bile ayarlanamaz olmalı. 

Cin (Daemonic) Duruşu

Kullanıcıyla etkileşime girmezler. 

Parazit 

Geçici olup da egemen gibi görünen uygulamalar. Mesela Microsoft Office Manager

Niçin minimize?

Programın boyutunu niçin minimize ediyoruz. Başka uygulamalara geçmek için.

Bunun daha kolay bir yolu var: Alt-Tab. Ancak bunu insanlar bilmiyor. Hiçbir yerde yazılı değil.

Sonuçta Windows 95 yeni bir deyim getirdi: Start Bar. Buradan farklı pencereler arasında dolaşmak mümkün. Böylece bütün pencereler maksimum olabilir. 

##### 5-349 13 İdari İş ve Delilik

İki tür iş tanımlıyoruz:

Gelir işi (revenue task): Bunlar yaptığımız işe yardımcı olan işlerdir.

Muda (excise task) Bunlar doğrudan bir fayda sağlamayan, ancak işimizi bitirmemiz için yapmamız zorunlu işler.

Aradaki farkı anlamak çoğu zaman alışkanlıklarımızdan dolayı zordur. Mesela arabayı işimize sürmek fayda sağlayan iştir. Ancak garaj kapısını açmak, mudadır. Çünkü bunu doğrudan işimize gitmek için yapmıyoruz, araba için yapıyoruz. 

Mesela yazılımı kurmak da bu tarz bir iştir. Araba tekerleğini şişirmek gibi. Bu tip işler meşrudur. Yeri vardır. Ancak bunları ne kadar çok azaltabilirsek, özellikle kapı açmak gibi işleri, kullanıcımızı o kadar memnun ederiz.

"Mudayı yok etmek, kullanıcıları daha etkili kılar."

GUI mudası:

Komut satırına yatkın güçlü kullanıcılar, guilerin kendilerini yavaşlattıklarını söyler. Doğrudur da söyledikleri. Ancak gui daha kolaydır. Bu gibi durumlarda ne yapmaliş

Ancak aslında komut satırında da başka bir muda var. Bunu kullanmak isteyen kişi, öncelikle komutları ezberleme masrafına katlanmak zorundadır. Ayrıca ekranı kendine göre konfigüre edemez. 

Öte yandan guilerin görsel ifade gücü yeni kullanıcıların programı çok hızlı bir şekilde öğrenmesini sağlar. Adım adım iş yapma doğası (sihirbazlardaki) yine kullanıcılara rahatlık verir. Ayrıca çok sayıda programı kullanmak durumundaki insanlar için de bu yapı daha iyi. 

Dolayısıyla bir kullanıcının mudası, diğer kullanıcı için faydalı olabilir. Dolayısıyla mudayı yok ederken dikkatli olmak lazım. Sadece uzman kullanıcılara hitap etmemeliyiz. Ancak uzman kullanıcıların yeni kullanıcılarla aynı şeyleri yaşamak zorunda kalmasına da izin vermemeliyiz.

Saf muda:

Ancak bazı işler hem uzman hem de acemi kullanıcılar için saf mudadır. Mesela, bir program hangi IRQ veya COM portunu kullanması gerektiğini soruyorsa bu tamamıyla israftır. Çünkü hangisini kullandığının hiçbir önemi yok. 

Tasarımcıların yüklü miktarda muda eklemelerinin bir sebebi, ilk kullanıcılara destek olmaktır. Ancak bu özellikler kullanıcı programa alışınca israf haline gelir. Dolayısıyla bu tarz özelliklerin kolaylıkla kaldırılabilmesi mümkün olmalı. 

Görsel metafor mudası:

Telefona, faksa benzeyen yazılımlar daha kolay gelebilir insana. Ancak bu da geçmişin aletleriyle kullanıcıları sınırlandırmamıza sebep olur.

Ekranda geniş yer kaplayan simgeler de bir israf olabilir (Eğer çok kullanılan egemen bir uygulamaysa)

"Asla kullanıcının soru sormasını sordurtma."

Eski programlarda özellikle bir iş yapmak istediğinde, önce bir komut verirdin. Daha sonra o işi yapmanı sağlayacak ekran çıkardı ve burada komutu verirdin. Dolayısıyla ilk komut işe yaramaz bir komuttu. Bu tarz sorulara, meta-sorular diyor Cooper.

Mesela kurulum programlarında, "Bu programı şu dizine yükleyeceğim" der. Eğer seçeneği beğenmezsen, değiştir düğmesine basarsın. Yeni bir ekran çıkar. Burada nereyi istediğini belirtirsin.

"Çıktı nereye yapıyorsan, girişe orada izin ver."

Mesela WinRarda dizini doğrudan değişteribiliyorsun. Bu çalışma şekli kullanıcının zihinsel modeline daha uygun.

Mudalar özellikle egemen uygulamalarda çok rahatsız edicidir.

Hata ve onay mesajları büyük ölçüde israftır.

Diğer muda tuzakları:

Bu pencereye etkileyen bir iş için kullanıcı başka bir pencereye gitmemeli.

Örnek: IntelliJde main sınıfını seçmek için ayrı bir pencere açılıyor.

Kullanıcı dosyayı nereye koyduğunu hatırlamak zorunda olmamalı.

Kullanıcının pencerelerin boyutlarını ayarlaması gerekmesin.

Kişisel ayarlarını tekrar girmesi gerekmesin (font, boyut vs.)

Her veri alanına giriş yapması zorunlu olmasın.

Kullanıcının eylemleri hatayla sonuçlanmasın.
	Dinamik help ile, anlık yardım yapılabilir.

Örnek: IntelliJ. Compile etmeden hatayı kırmızıyla uyarıyor. Bu hatayı hemen çözmeye zorunlu değilsin. VBdeki gibi bir diyalog kutusuyla senin işini engellemiyor. Satıra geldiğinde de düzeltme seçenekleri sunuyor.

Rüya deneyim nasıl olurdu? diye sormalıyız kendimize. Ve felaket deneyimi nasıl olurdu.

Dershane: Yan panel kaybolmalı miş Opera gibi kenara tıklama imkanını sunmalıyız.

Aptallık

"İşin ilerlemesini, aptalca şeylerle durdurma"

Bazen uzun sürecek bir işin emrini veriyorsun, bilgisayar o işle uğraşırken ben de bir kahve alayım diyorsun. Geri döndüğünde bir bakıyorsun hiçbir şey olmamış. Aptal bir diyalog kutusu sana saçma bir soru soruyor. Program kurarken, print ederken çok yaşanan bir durum.

Program yüklerken sürekli bilgisayarın başında durma zorunluluğumuz var. Halbuki yazılımlar insanların yaptıkları işlerin otomasyona geçmesi için değil midir?

Adobe Illustratordan bir diyalog kutusu:
"The artwork contains a character in a text object that may dısplay or print incorrectly.
Ok"

Yukarıdaki gibi bir diyalog kutusunun hiçbir faydası yok. Hangi dosyada hata meydana gelmiş? Nasıl düzeltebilirim? Hatalı karakter varsa, niye verişi sırasında bizi uyarmadiş

"Artwork page format is different from the printer page format.
Ok Cancel"

Oryantasyon sorunu. Şekli değiştirince printerın oryantasyonu değişmiyor. Niçin program dosyanın oryantasyonunu unutuyor. Biz uzman kullanıcılarız. Peki ya çekingen kullanıcılar ne olacak>? Bilgisayar tarafından ezilecek. 

Eğer hatırlamayacaksa bile, niye diyalog kutusuna direnkt oryantasyon değiştirme imkanı koymuyor.

-» niyet algılamak (intention revealing) 
IntelliJ - sınıf ismi farklıysa iki seçenek sunar
	dosya ismini değiştir
	sınıf ismini değiştir

Şifre sorma - isteyen bunu kaldırabilmeli. Çoğu uygulama, çoğu kullanıcı için herhangi bir şifreyle korunacak kadar kritik değil. En azından kullanıcı bunu belirleyebilmeli.

Çok uzun süre işlem yapma	
	- harici aygıttan dolayı
	- sonsuz döngüye girilmesinden dolayı



##### 5-350 14 Gizli Silah

Kullanıcının bir sonraki tercihinin ve eyleminin ne olacağını bilmek işimize yaramaz mıydiş

Yazılım kullanıcının en son ne yaptığını hatırlamalı.

Bu en etkili tasarım yöntemidir.

İnsanlara soru sorarak da veri alabiliriz, ama insanlar soru sorulmayı sevmez. 

Soru sormak, insanlara tercih sunmak değildir.

Soru soran taraf üstündür.

Düğmeler (buttcon) tercih sunar. İstediğin zaman istediğine basarsın.

Soru soran yazılımlar kullanıcıda şöyle izlenim oluşturur:
	- unutkan, gafil, zayıf, inisiyatifsiz bir program
Bunlar insanlarda sevmediğimiz özellikleridr.
Merakından sormuyor, aptallığından soruyor.
Az soru soran yazılım kullanıcıya akıllı görünür.

İnsanların sorulardan daha çok nefret ettiği şey, sık sık ve gereksiz yere soru sorulmasıdır.
Dosya kaydetmek, basmak istiyor musunuz? Emin misiniz?

Tercihte kullanıcı yönlendirir.
Başlangıçta del ile konfigürasyon menüsünün çıkması.

İş uyumluluğu kavramı. 
Yazılım kullanma tarzımız. Her seferinde aynı tercihlerde bulunuyoruz.
Bu ikisinin arasındaki fark, inisiyatifi yüksek bir sekreterle sıradan bir sekreter gibi. 
Mesela MDI uygulamalarında pencerelerin konumları.
Hangi veriyi hatırlamaliş

Eğer sormaya değiyorsa, hatırlamaya değerdir.

Keynote: En son kaydedilen yer, en son açılan dosya

İdeal deneyim: Beyaz siyah yazı - Word -» otomatik olarak bir buttcon oluşmalı

Varsayılanları konfigüre etmek -» uzman
Aradabir kullanan kişi -» yaptıkları hatırlanmalı.
Font tercihi, paragraf stili.

Veri -» programcının işini kolaylaştırıyor, kullanıcınınki yerine

Örnek: form doldurma, şifre hatırlama, adres tamamlama, cookies

Hatalı veri girişine engel -» otomatik
Hatalık veri girişini uyarı -» eski verilerden
Mesela bir zip kodu daha önce tanıdık olan bir şehir yerine farklı bir yer için girilirse, sarı renkle boyanır. Ancak kullanıcı engellenmez.

İş uyumluluğu kavramının bir kez kabul edilmesi, tasarımcıların yaklaşımını özünden değiştiriyor.

Örnek: Yeniden uygula

Tek olmasa bile az sayıda seçenekten tercih yapmaya meyilliyiz.

Örnek: İşbank -» havale



#### 4-351 4. Bölüm: Etkileşim



##### 5-352 15 Filler, fareler

mouse -» işaret etme de güçlü, ancak kliklemede zayıf

Yakın motor kasları: mousu oynatırken, bileğini kaldırmamak
Uzak motor kasları: uzaktaki bir yere sürüklerken, bileğinden elini kaldırmak

Her ikisi de hızlı ve hassas. Ancak geçiş, konsantrasyonu bozar.

Ancak klikleme mutlaka, yakın motor kasları tarafından yürütülür. Bu yüzden hareket ederken kliklemek çok zahmetlidir.

Kliklenebilir alanlar birbirine yakın olmalı. Mesela scrollbar okları, hemen üstüste de olabilirdi.

Çoğu veri yoğun işte klavye daha üstündür.

Program hem klavyeyi hem mouseu desteklemeli.

Birçok insan mouse kullanamıyor.

Pliant: mouse duyarlı alanlar

"Görsel olarak pliantlığı göster."

Mouse üzerinden geçerken şekil değiştir.
Küçük ve köşelerdeki gizmolar için şart.

Pencere boyutlama -» imleç uyarısı

Beklemek -» imleç aktif olsun, ek bir video ile bunu göster iptal etme imkanı ver.

Meta-karakter imleç uyarıları -» ipucu vermek için



##### 5-353 16 Seçim

Yüklem-nesne yerine nesne-yüklem, çoğu zaman daha iyi.

Çünkü öbür türlü önce komutu veriyoruz, sonra nesneyi almak için diyalog kutusuyla beklemeye geçiliyor.

Önce seçim olmalı. Program bu nesneyi hatırlayabilmeli.

Kesik data -» farklı seçimlerin toplamı



##### 5-354 17 doğrudan manipülasyon

Zengin görsel etkileşim sunmak.

Gösterilerek öğretilmeli.

Apple:"Kullanıcılar işi yaptırdıklarını görmek ister."

Don Norman:"Kullanıcılar, iş yapmakta yetersizse, bunu istemez." -» çizim programları

İkisi de doğru
	idari işler -» direkt -» buton
	içerik işler -» dolaylı -» çizim

Yakalama: (capture) Tıklama ve sürükleme. Bundan çıkış yolu olmalı ve kullanıcı bu yolu bilmeli.

Çifte klikle iptal veya escape tuşu ile. İkisi birden mümkün. Windows APIsinde çifte klik tanımlanmamış. Ancak C ile bunu algılamak mümkün.

Pliant tepkisi -» işin ortasındayken vazgeçebileceğini gösterir.

Cooper bir uygulamada kocaman bir "Drag Canceled" etiketi göstermiş. İnsanlar bunu çok beğenmiş.

Tıklamak ve fareyi hareket ettirmek çok zor bir hareket -» Apple.

Basamaklı menülerin kullanımı da zordur.

Sürükleme işlemi: Seçme ve sürükleme iki ayrı eylem olarak yapılıyor. Halbuki gerçekte tek bir eylem olarak biz bir kitabı taşıyoruz. 
Meta karakterlerle bu sağlanabilir.



