tags:: study, mac

# 20230510-Mac-M1-Windows-XP-Kurulumu id=g14280

[(153) How to Install Windows XP Professional in UTM (M1 Mac) - YouTube](https://www.youtube.com/watch?v=CTT5eMKvNVc)

ISO dosyaları: ~/gdrive/yayinlar/windows/

Host ile VM arasında dosya alışverişi XP için mümkün değil. Bu yüzden, host bilgisayarda bir web server başlat.

Network erişim bilgilerini bulmak için: https://github.com/utmapp/UTM/discussions/3625#discussioncomment-3585871

UTM > VM > Settings > Network

			host
				10.0.2.2
			guest
				10.0.2.0/24

Şimdi host bilgisayarda bir web/http server başlat herhangi bir klasörde. Bu dosyalara VM içinden erişebilirsin.

