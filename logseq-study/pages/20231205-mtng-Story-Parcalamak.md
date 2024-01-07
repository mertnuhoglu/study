tags:: study
date:: 20231205

# 20231205-mtng-Story-Parcalamak

- [[f/ndx]]

# f/pnt

- Sunucu: Ece Bakır, Softtech
	- ![Zoom](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20231205_195730.jpg)

- Katılımcılar: 
	- Emre Uçar
	- Sare
	- İlker Murat Paşabeyoğlu
	- Egemen
	- Merve Ergenç

- Story parçalamanın faydaları neler?
	- INVEST kriterlerinden başlama
	- Geri bildirim süresini azaltma
		- Erken ve hızlı geri bildirim almak
	- Müşteriler de ne istediğini bilemeyebilir
	- Belirsizlik hayatın bir parçası

- Çevik prensipler
	- ![12 Çevik Prensip](./assets/scs20231205_194448.png)

- Kullanıcı hikayesi nedir?
	- As a <user role> 
		- I want <goal> 
		- so that <benefit>
	- Given = Durum
		- When = Eylem
		- Then = Çıktı
	- When = Durum
		- I want to = motivasyon
		- So I can = beklenen çıktı
	- Farklı kalıplarla hikayeleri yazabilirsiniz.

- Ne işe yarar (5C)
	- Jira'daki hikayeler iletişim dili haline gelebiliyor.
		- Onları bir kalıpta tutmak maksat değil.
		- Onlar konuşma hatırlatıcılar.
		- Bazı kabul kriterlerinde mutabık kalıyoruz diyelim.
		- Sonra o konuşmayı hatırlayabilmek için, not alıyoruz aslında.
		- Onları unutmayalım. 
		- Onları kontrol etmek için bir teyitleşme yapıyoruz.
		- Yazıya dökme: Konuşma hatırlatıcı olması
		- Conversation -> confirmation -> construction -> consequences -> card -> conversation
		- Konuşmaların yerine, Jira'daki soğuk kartların almasına izin vermeyin.
	- Sare: Fotoğraflara bakınca, sana bir şey ifade etmez. Ama onlar benim için bir hikayedir. Tatil anılarımdır.

- INVEST prensipleri
	- Independent:
		- İki hikaye birbirinden bağımsız
		- Kendi başına bir değer, fonksiyon parçasını ifade etmesi
		- Bir kullanıcı fonksiyonunu
	- Negotitiable
		- 2 boyutu var.
		- 1. storyler, task boyutunda kırılmış.
		- Ben artık bunun neresini tartışayım.
		- Bir tabloya bir kayıt atmak gibi.
		- Bu çok küçük bir şey.
		- 2. Bunun sf'si kaç olmalı diye soruyorum.
		- İstersen 8 istersen 100 yaz diyor.
		- İş o kadar büyük ve belirsizlik var ki, hepsi gider.
		- Mini bir proje o aslında.
		- Ne çok minik olmalı. Ne de tasklarını konuşamayacağım kadar büyük olmalı.
	- Valuable
		- Hangi kullanıcı ihtiyaçlarını karşıladığı
		- Hangi maliyetlerden tasarruf sağlayacağı
		- Ürün için vizyonu gerçekleştirmeye nasıl yardımcı olacağı
		- Birisi için bir fayda, değer yaratmasını bekliyoruz
	- Estimatable
		- Bir sprintte bitirilebilecek seviyede olmalı.
		- Hikayelerin doğasında 3 unsur var:
			- Ne, neden, nasıl?
			- Ne ve neden soruları: PO'nun sorumluluğu
			- PO bunları takım kriterlerinde sağlamalı.
			- Bunlar netse, nasıl kısmını tanımlamak daha kolay olur.
		- Eğer takım, nasıl yapılacağını kestiremiyorsa, hikaye çok büyüktür.
	- Small
		- Küçük olması tek başına amaç değil.
		- Hikayeleri yönetmenin de bir maliyeti var.
		- Optimum boyut olmalı.
		- Release geçiş maliyetleri, evrak işleri oluyor.
	- Testable
		- Test ekibi çok önemli
		- Çünkü istisnaları ve caseleri onlar çıkartır.
- Hikayeleri, yatay veya dikey bölme
	- ![Pasta bölme](./assets/scs20231205_200901.png)
	- Yatay bölme: Teknik ihtiyaçlara göre bölme
	- Yatay bölme: Kullanıcı fonksiyonlarına göre bölme
	- Ekipler, parametrik yapıya ulaşmak için, zamanın büyük kısmını, teknik detaylara ayırırdı.
	- Müşteri ise gecikmede şöyle derdi: Bunlar ön yüzü böyle yaptıysa, kim bilir altyapıyı ne kadar bozmuşlardır.
	- Teknik esneklikten dolayı, müşteri deneyimini iyileştirmeye zamanın kalmadı.
	- Müşteri deneyim eksikliğinden dolayı, ürüne güvenini kaybederdi.
	- Pastanın en altında, kuru tabanı olur.
	- En üstünde ise en lezzetli kısım olur.
- q: User story vs Product backlog item?
	- İkisi aynı
- SPIDR
	- Apply the splitting patterns
	- Story parçalama checklisti var.
	- POS cihazına taksitli işlem geldiğinde, doğrulama yapılması, böylece x hatanın önlenmesi sağlanacaktır.
	- 1. INVEST kriterlerini karşılıyor mu?
		- Üretim hızınızın %10'undan daha büyükse, hikayeyi parçalayın.
	- 2. Apply the splitting patterns
		- Workflow:Does the story describe a workflow?
			- Hikayeyi öyle parçala ki, iş akışının başını ve sonunu yap.
		- Operations: Bir şeyi yönetmek veya konfigüre etmek
		- Business rule variations: Farklı kurallar var mı
		- Simple vs complex: Efor
		- Variations in data:


