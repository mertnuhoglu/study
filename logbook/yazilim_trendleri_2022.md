
# Yazılım Trendleri 2022

Sınırlı bilgime dayanarak gözlemlediğim yazılım trendlerine dair birkaç tahminim şunlar:

- Programlama Yöntemleri: Data Oriented Programming, R TidyVerse
- Araçlar: Fuzzy Finder Araçları, Neovim Telescope, Neovim ve Lua Tabanlı Eklenti Geliştirme, Language Server Protocol
- Stratejik Planlama Yöntemleri: Wardley Mapping

# Programlama Yöntemleri

## Data Oriented Programming

Önümüzdeki dönemin önemli trendlerinden birisinin veri odaklı programlama (data oriented programming) yaklaşımı olacağını düşünüyorum. Oyun programlamasında eskiden beri kullanılagelen bir yaklaşımmış bu. O tarafları doğrusu hiç bilmiyorum. Ben iki sene önce Clojure öğrenmeye başladığımda bu kavramı ilk kez duymuştum. Ve ilk duyduğumdan beridir de, çok hoşuma giden ve iyice öğrenmek istediğim bir yaklaşım bu. 

Bu yaklaşımı güzelce anlatan içerikler çokça mevcut internette. Örneğin: [Data-Oriented Programming is dope - DEV Community](https://dev.to/xba/data-oriented-programming-is-dope-ma2), [What is Data Oriented Programming? | Yehonathan Sharvit](https://blog.klipse.tech/databook/2020/09/25/data-book-chap0.html). Geçtiğimiz senenin [en çok satan](https://twitter.com/viebel/status/1477510420592271360) Manning kitabı [Data-Oriented Programming](https://www.manning.com/books/data-oriented-programming) olmuş. 

Gördüğüm kadarıyla, DOP zaman içinde OOP'yi mevcut yerleşik konumundan edebilecek bir yaklaşım. Mutlaka saf fonksiyonel programlama (FP) yöntemlerini kullanmadan da, mevcut Java, Python gibi OOP için tasarlanmış programlama dillerinin içinde kalarak, programlama yapmaya müsait. Önümüzdeki dönemin güçlenecek trendlerinden birisinin bu dizayn yöntemi olacağını düşünüyorum.

## TidyVerse

TidyVerse, R programlama dili içindeki bir programlama yaklaşımı ve bu yaklaşıma göre dizayn edilmiş bir library ekosistemidir. 2014 yılında Hadley Wickham tarafından yazılan dplyr adlı bir kütüphane etrafında şekillenen bir dünya bu. Hadley Wickham, R dünyasındaki pek çok önemli kitabın ve yaygın kullanılan kütüphanenin yazarıdır. Genel olarak veri bilimi ve veri analiz çalışmalarındaki en büyük maliyet kaleminin verinin hazırlanmasıyla ilgili çalışmalar olduğu söylenegelir. TidyVerse, bu problemlerin birçoğunu çözmeye yönelik yaklaşımlar ve araçlar sunar. Hadley Wickham, ["Tidy Data"](https://vita.had.co.nz/papers/tidy-data.pdf) makalesiyle veriyi formatlama ve modellemeye dair temel bazı prensipleri anlatır. Hem bu prensiplere, hem fonksiyonel programlama ve diğer iyi programlama yaklaşımlarına göre kendine kısıtlar koyarak veri manipülasyonuyla ilgili çeşitli kütüphaneler dizayn eder. Zaman içinde bu kütüphaneleri kullanan R programcıları, bu prensipleri ve yaklaşımları giderek daha çok benimsedi ve başka programcılar da aynı dizayn yaklaşımlarına göre TidyVerse adını verdikleri ekosistemi inşa ettiler. Her geçen sene bu ekosistem daha da gelişmektedir. 

Data Oriented Programming ile kesişen bazı yönleri de bulunuyor TidyVerse yaklaşımının. Bu da sanıyorum her ikisinin Fonksiyonel Programlama yaklaşımından ciddi ölçüde etkilenmesinden kaynaklanıyor olabilir. Mesela, her ikisinde de ham veri sadece temel data structurelar kullanılarak işleniyor. Nesne tabanlı modellemenin önerdiği data hiding veya encapsulation gibi yaklaşımların tam tersi uygulanıyor. Immutability ve side-effect free programlama prensipleri takip ediliyor. For-loop'lar yerine map/reduce tarzı fonksiyonlarla döngüsel iş mantığı kodlanıyor. 

TidyVerse'in belirleyici özelliklerinden birisi de Unix'in [pipe operatörünü](https://en.wikipedia.org/wiki/Pipeline_(Unix)) standart kompozisyon (composition) aracı olarak kullanması. Bu standart, kütüphane yazarlarının API dizayn kararlarını kısıtlamakla birlikte, tüm API'lerin birbiriyle uyumlu olmasını sağladığından çok büyük fayda sağlıyor. 

# Araçlar

## Fuzzy Finder Araçları

Fuzzy Finder araçlarından kastım, [Windows Search Bar](https://support.microsoft.com/en-us/windows/search-for-anything-anywhere-b14cc5bf-c92a-1e73-ea18-2845891e6cc8), [MacOs Spotlight](https://www.imore.com/how-use-spotlight-mac), [fzf (terminal fuzzy finder)](https://github.com/junegunn/fzf), [IntelliJ Search Everywhere](https://www.jetbrains.com/help/idea/searching-everywhere.html), [Vs Code Command Palette](https://code.visualstudio.com/docs/getstarted/userinterface#_command-palette), [Vivaldi Quick Commands](https://vivaldi.com/blog/quick-commands-guide/), [Neovim Telescope](https://github.com/nvim-telescope/telescope.nvim), [Figma Quick Actions](https://help.figma.com/hc/en-us/articles/360040328653-Use-shortcuts-and-quick-actions) gibi genel arama bulma fonksiyonalitesi sunan komut satırı (command bar) araçları.

Bu tür genel komut satırı araçları kullanıcıların uygulamaları kullanma verimliliklerini ciddi biçimde artırıyor. Sistem-kullanıcı etkileşiminin şeklini grafiksel etkileşimden daha klavye tabanlı etkileşime doğru çeviriyor. Uygulamaların kolay öğrenilebilirliği ve verimli kullanılabilirliği arasında uyumlu bir dengeyi yakalıyor. Önümüzdeki dönemde bu tarz etkileşimin daha da fazla yaygınlaşacağı kanısındayım.

Bu genel komut satırına dayalı arama bulma araçlarının tarihsel evriminde başlangıç noktasının Google olduğunu söyleyenler var. Google arama motoru ilk kurulduğundan beridir, sistem-kullanıcı etkileşimi için sadece tek bir görsel öğe sunageldi. Kullanıcı istediği her şeyi yazabildi bu girdi alanına. Google ise bu girdiyi bir şekilde anlamlandırarak ilişkili gördüğü her şeyi listeleyegeldi. Şimdi de benzer etkileşim şeklini bu komut satırı araçlarıyla, her türlü uygulamayla yapar hale gelmekteyiz.

## Neovim Telescope

Fuzzy finder araçları arasında [Neovim Telescope](https://github.com/nvim-telescope/telescope.nvim) eklentisinden bahsetmiştim. Buna özel bir bölüm açmak istiyorum, çünkü diğer fuzzy finder araçlarından çok farklı bir yönü var Neovim Telescope aracının. Neovim Telescope, kendisi bir Neovim eklentisi olmakla birlikte aynı zamanda kendisi için de eklenti geliştirilebiliyor. Yaklaşık bir veya bir buçuk yıllık bir eklenti olmakla birlikte ve kendisinden önce Unite veya Fzf.vim gibi yerleşik yaygın benzer eklentiler olduğu halde, çok hızlı bir şekilde bunların yerini almakta ve yepyeni bir eklenti ekosisteminin oluşmasına yol açmaktadır.

Telescope'un önceki benzer vim eklentilerinden önemli bir farkı bulunuyor. En baştan genişletilebilirlik düşünülerek tasarlanmış. Yeni kullanım ihtiyaçları için kolaylıkla [Telescope eklentileri](https://github.com/nvim-telescope/telescope.nvim/wiki/Extensions) hazırlanabiliyor. Böylece her türlü veri kaynağı için özelleşmiş bir fuzzy finder aracı hazırlanabiliyor. Dolayısıyla Telescope yeni, niş bir yazılım ekosistemi oluşturuyor kendi etrafında. Önümüzdeki dönemde bu ekosistemin oldukça zengin bir araç seti sunacağını zannediyorum.

## Neovim ve LSP (Language Server Protocol)

[Neovim](https://neovim.io/), klasik Vim uygulamasının sıfırdan yeniden yazılmış drop-in replacement versiyonu. Neovim ve Vim birbirine paralel olarak evrimlerine devam ediyorlar. Neovim çok daha geniş bir çekirdek geliştirici desteğine sahip. Performansı çok daha yüksek ve Lua tabanlı eklenti geliştirmeye dahili olarak destek veriyor. Bu da çok daha performanslı ve kolay eklenti geliştirme imkanı sağlıyor. 

Neovim'in geçtiğimiz 2021 senesinde yayınlanan 0.5 versiyonuyla dahili LSP (Language Server Protocol) desteği geldi. Bununla birlikte Neovim, sade bir metin editörü olmaktan çıkıp, Vs Code veya IntelliJ gibi tam teşekküllü bir IDE aracı haline geldi. Önümüzdeki dönemde de, mevcut LSP entegrasyonunu geliştirecek çok sayıda yeni özellik ve eklentiler çıkacağını düşünüyorum. Vim'in büyük IDE'lerin özellik seti olarak hep gerisinde kalışına neden olan en önemli kısıtı aşıldı. Bu sayede, Neovim'in gelecekte diğer IDE'lerin önüne geçmesini sağlayacak yeni özelliklere imkan tanıması muhtemel.

# Stratejik Planlama Yöntemleri

## Wardley Mapping

[Simon Wardley](https://www.linkedin.com/in/simonwardley/?originalSubdomain=uk), bulut bilişim kavramının ve teknolojisinin doğduğu 2000'li yıllarda bu alanda altyapı hizmetleri veren büyük bir teknoloji firmasının yöneticisi. Firmanın CEO'luğu görevine atandığında, bu firmaya nasıl bir yön çizmeliyiz, sorusu üzerine düşünürken, stratejik planlama literatürünün aslında son derece yetersiz olduğunu ve yöneticileri yanlış yönlendirdiğini fark etmiş. Bunun üzerine yaptığı okumalar ve çalışmalarla, herhangi bir alandaki evrilmenin nasıl olacağını daha iyi ifade etmeyi ve öngörmeyi sağlayacak bir yöntem geliştirmiş. Bu geliştirdiği yöntemi [açık kaynak bir kitap](https://medium.com/wardleymaps) haline getirip herkesle paylaşmış.

Doğrudan yazılım geliştirmeye yönelik bir yöntem olmamakla birlikte, teknolojinin evrimini anlamak için yararlı bir çerçeve sunduğunu düşünüyorum Wardley'in haritalama yönteminin. Çok hızlı olmamakla birlikte ağır fakat sağlam adımlarla yayılıyor bu yeni strateji planlama yaklaşımı. Bu yüzden, bu yaklaşımı da niş bir trend olarak eklemek istedim.
