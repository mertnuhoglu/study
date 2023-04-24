## p03: Master-detay varlıkları modelleme id=g14242

rfr: [[20230414-Clj-Clerk-ile-Uygulama-Simulasyonu]]

rfr: opal_sandbox_grsm > 20230420-Master-detay-modelleme

```clojure
(require '[nextjournal.clerk :as clerk])
```

- e01: Master altında detay varlıkları listeleme


Üst varlık tablosu:	[Department]	

| id  | title     |
|-----|-----------|
| 101 | Matematik |
| 102 | Fizik     |
| 103 | Sosyoloji |
| ... | ...       |

Kullanıcı bu departmanlardan birinin detayına girer.		

Üst varlık formu:		[Department]		

	id:	102	
	title:	Fizik	
	students:	[Student]	
	| id  | name         |
	|-----|--------------|
	| 201 | Can Toprak   |
	| 202 | Ali Deniz    |
	| 203 | Zeynep Irmak |
		
Kullanıcı bu öğrencilerden birinin detayına girer.		
Alt varlık formu:		[Student]		

	id:	202	
	name:	Ali Deniz	
	department:	Fizik    ▾	
