
## kişisel web sitemi düzeltme 20190827 

### mevcut eski notlarım

    Publishing Web Site and Blog <url:file:///~/gdrive/mynotes/general/processes.md#r=g_10033>
		hugo <url:file:///~/gdrive/mynotes/content/code/ccode.md#r=g_10167>
		/Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/deploy.sh

### basit bir hugo sitesi oluştur 

``` bash
cd ~/codes/hugo
hugo new site quickstart
cd quickstart
git init
git submodule add https://github.com/budparr/gohugo-theme-ananke.git themes/ananke
echo 'theme = "ananke"' >> config.toml
hugo new posts/my-first-post.md
``` 

Edit `~/codes/hugo/quickstart/content/posts/my-first-post.md`

``` bash
cd ~/codes/hugo/quickstart
hugo server -D
``` 

http://localhost:1313/posts/my-first-post/


### price comparison

1. DigitalOcean Spaces

https://medium.com/dailyjs/digital-ocean-spaces-pros-cons-and-how-to-use-it-with-javascript-1802559ce2bd

> 5 USD per month for 250 GB of storage and 1TB of outbound transfer each month. Making some calculations, that means 0,020 USD per GB/month, without counting the included outbound transfer. To compare, in AWS you would pay 0,023 USD per GB/month in storage, and 0,09 USD per GB transferred out each month. Meaning that you would have to pay around 90 USD extra just for the 1TB of outbound transfer that it’s included in the DigitalOcean spaces plan each month!

2. Backblaze B2

https://itnext.io/backblaze-b2-pros-cons-and-how-to-use-it-with-javascript-8c2d2a9a69d9


### cloudflare s3 domain name mapping

https://medium.com/pixelpoint/99-9-uptime-static-site-deployment-with-cloudflare-and-aws-s3-388e82b4b9b6

site name: example.com

bucket name: example.com

### backblaze storage + cloudflare cdn

https://help.backblaze.com/hc/en-us/articles/217666928-Using-Backblaze-B2-with-the-Cloudflare-CDN

### wasabi storage + cloudflare cdn

https://wasabi-support.zendesk.com/hc/en-us/articles/360018526192-How-do-I-use-Cloudflare-with-Wasabi-

### cloudflare vs netlify

https://www.netlify.com/blog/2017/03/28/why-you-dont-need-cloudflare-with-netlify/

### gitlab pages + cloudflare

https://tkainrad.dev/posts/using-hugo-gitlab-pages-and-cloudflare-to-create-and-run-this-website/

### netlify on hn

https://news.ycombinator.com/item?id=18176539

### netlify setup

Edit `/Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/netlify.toml`

``` bash

``` 

### netlify cli setup

https://www.netlify.com/blog/2019/05/28/deploy-in-seconds-with-netlify-cli/

``` bash
npm install netlify-cli -g
netlify login
``` 

#### Link an existing site

``` bash
netlify link
``` 

#### master branch üzerinden ci yapmayı dene

		master branch üzerinden ci yapmayı dene <url:/Users/mertnuhoglu/gdrive/mynotes/stuff.otl#tn=master branch üzerinden ci yapmayı dene>

### yeni bir site oluştur

``` bash
hugo new site testnetlify01
``` 

#### mevcut siteyi ayrı bir yerde yapsam tekrar

``` bash
netlify init
  ##> Admin URL: https://app.netlify.com/sites/compassionate-lamport-aeb916
  ##> URL:       https://compassionate-lamport-aeb916.netlify.com
  ##> Site ID:   f9e9aa92-c1ac-457e-a0a1-28295666453b
``` 

### yeniden sıfırdan başlat projeyi

#### önce çalışan sıfır bir hugo projesi oluştur

``` bash
hugo new site testnetlify04
cd $_
git init
git submodule add https://github.com/budparr/gohugo-theme-ananke.git themes/ananke
echo 'theme = "ananke"' >> config.toml
hugo new posts/test-post.md
``` 

``` bash
netlify init
  ##> Admin URL: https://app.netlify.com/sites/serene-williams-91452b
  ##> URL:       https://serene-williams-91452b.netlify.com
  ##> Site ID:   55708760-0daa-4e2b-8dcc-0fe92e90b727
``` 

### dosyaları tek tek aktar

#### Result

Plan Steps:

		tüm çıktıları kopyala yapıştır
		{bash} -> bash
			s/{\<bash\>}/bash/
		resimleri düzelt

``` bash
hugo new tech/yuml_to_uml.md
``` 

http://localhost:1313/tech/yuml_to_uml/

#### Logs

aktar: ~/projects/jekyll/mertnuhoglu.com/content/tech/yuml_to_uml.Rmd

``` bash
netlify sites:list
  ##> serene-williams-91452b - 55708760-0daa-4e2b-8dcc-0fe92e90b727
  ##>   url:  https://serene-williams-91452b.netlify.com
  ##>   repo: https://github.com/mertnuhoglu/testnetlify04
  ##>   account: Mert Nuhoglu's team
  ##> ─────────────────────────────────────────────────
  ##> compassionate-lamport-aeb916 - f9e9aa92-c1ac-457e-a0a1-28295666453b
  ##>   url:  https://compassionate-lamport-aeb916.netlify.com
  ##>   repo: https://github.com/mertnuhoglu/mertnuhoglu.com
  ##>   account: Mert Nuhoglu's team
  ##> ─────────────────────────────────────────────────
  ##> admiring-leavitt-963ae8 - e59bd208-fef1-444a-858c-f0e4dc09c793
  ##>   url:  https://admiring-leavitt-963ae8.netlify.com
  ##>   repo: https://github.com/mertnuhoglu/testnetlify01
  ##>   account: Mert Nuhoglu's team
  ##> ─────────────────────────────────────────────────
  ##> vibrant-varahamihira-0026da - fc71bc95-1916-4736-9373-2a64828d30d2
  ##>   url:  https://mertnuhoglu.com
  ##>   repo: https://github.com/mertnuhoglu/mertnuhoglu.com
  ##>   account: Mert Nuhoglu's team
  ##> ─────────────────────────────────────────────────
``` 

Plan Steps:

		tüm çıktıları kopyala yapıştır
		{bash} -> bash
			s/{\<bash\>}/bash/
		resimleri düzelt

``` bash
hugo new tech/yuml_to_uml.md
``` 

http://localhost:1313/tech/yuml_to_uml/

Fakat ana sayfada listelenmiyor bu şekilde.

#### Error: dosya ana sayfaya çıkmıyor

``` bash
hugo new content/tech/yuml_to_uml.md
``` 

Ayrıca url'si de yok

Eski sitedeki linki:

http://mertnuhoglu.com/tech/yuml_to_uml/

opt01: düz posts içine koy

``` bash
hugo new posts/yuml_to_uml.md
``` 

http://localhost:1313/posts/yuml_to_uml/

opt02: content koyma +

``` bash
hugo new tech/yuml_to_uml.md
``` 

Tamam url çalışıyor: http://localhost:1313/tech/yuml_to_uml/

Fakat ana sayfada listelenmiyor. 

#### mevcut siteyi ayağa kaldır

		ERROR 2019/09/03 14:00:24 Error while rendering "home": template: index.html:1:3: executing "index.html" at <partial "common/head...>: error calling partial: template: partials/common/head.html:10:7: executing "partials/common/head.html" at <partial "common/head...>: error calling partial: template: partials/common/head-meta.html:3:68: executing "partials/common/head-meta.html" at <markdownify>: wrong number of args for markdownify: want 1 got 0

`partials/common/head-meta.html` isminde bir dosya yok.

#### farklı sectionları deneyerek listele

``` bash
hugo new short-stories/martin-ramirezin-yoksul-hayati.md
``` 

##### mevcut temanın kodlarını değiştir

###### ananke örnek sitesini incele

####### try 01:

``` bash
hugo new site testnetlify05
cd $_
git init
git submodule add https://github.com/budparr/gohugo-theme-ananke.git themes/ananke
echo 'theme = "ananke"' >> config.toml
``` 

``` bash
cp -R /Users/mertnuhoglu/projects/jekyll/gohugo-theme-ananke/exampleSite/* /Users/mertnuhoglu/projects/jekyll/testnetlify05
``` 

Edit `~/projects/jekyll/testnetlify05/config.toml`

		theme = "ananke"

``` bash
hugo server -D
``` 

####### Error: theme not found

Fix: Bu satırı sil:

		themesDir = "../.."

opt02:

``` bash
hugo new site testnetlify06
cd $_
git init
git submodule add https://github.com/budparr/gohugo-theme-ananke.git themes/ananke
echo 'theme = "ananke"' >> config.toml
``` 

``` bash
cp -R /Users/mertnuhoglu/projects/jekyll/gohugo-theme-ananke/exampleSite/content /Users/mertnuhoglu/projects/jekyll/testnetlify06/
cp -R /Users/mertnuhoglu/projects/jekyll/gohugo-theme-ananke/exampleSite/static /Users/mertnuhoglu/projects/jekyll/testnetlify06/
``` 

---

Sayfaları listelemeyi sağlayan kod nerede?

Edit `~/projects/jekyll/testnetlify05/themes/ananke/layouts/index.html`

        {{ range (first $n_posts $section) }}

####### kategorik listelemeleri nasıl yapmışlar?

Section nedir?

https://www.youtube.com/watch?v=jrMClsB3VsY

######## sıfır bir tema kullan

``` bash
hugo new site testnetlify07
cd $_
git init
``` 

``` bash
hugo new a.md
``` 

http://localhost:61112/a/

Error: 404

######## mevcut layoutun üstüne yaz

######## Result

Edit 

`~/projects/jekyll/testnetlify05/layouts/index.html`

`~/projects/jekyll/testnetlify05/layouts/_default/baseof.html`

They override: `themes/ananke/layouts/index.html` and `themes/ananke/layouts/_default/baseof.html`

list pages:

http://localhost:1313/post/

Check `~/projects/jekyll/testnetlify05/themes/ananke/layouts/post/list.html`

Kural galiba şöyle:

Örneğin, bir list page render ediyoruz. Bu durumda template `list.html` isimli olmalı. Bu ya theme içindedir ya da `layouts`. Eğer `layouts` içinde yoksa, `theme` içinden bunu alır. Bu durumda, `list.html` sayfasının içinde bulunduğu `block` da `theme` içindeki `baseof.html` içinden alınır.

Fakat `list.html` de `post/_index.md` tarafından render ediliyor:

Check `~/projects/jekyll/testnetlify05/content/post/_index.md`

######### error: neden baseof override etmiyor?

Edit `~/projects/jekyll/testnetlify05/layouts/_default/baseof.html`

Error: no effect

opt01: doğrudan theme içindeki dosyaları değiştir bakalım etkili mi?

theme içindeki dosyaları değiştirince, çıktı değişiyor. ama doğrudan `layouts/` içindekileri değiştirmenin etkisi olmuyor.

Check `~/projects/jekyll/testnetlify05/themes/ananke/layouts/_default/baseof.html`

Ediyor:

`~/projects/jekyll/testnetlify05/layouts/index.html`

`~/projects/jekyll/testnetlify05/layouts/_default/baseof.html`

list pages:

http://localhost:1313/post/

Check `~/projects/jekyll/testnetlify05/themes/ananke/layouts/post/list.html`

##### örnek bir section yap

``` bash
hugo new section01/a.md
``` 

Edit `~/projects/jekyll/testnetlify05/content/section01/a.md`

Edit `~/projects/jekyll/testnetlify05/content/section01/_index.md`

This is layout for list page of `section01`: `~/projects/jekyll/testnetlify05/layouts/section01/list.html`

Open: http://localhost:1313/section01/

#### farklı sectionları deneyerek listele

``` bash
hugo new tech/yuml_to_uml.md
``` 

Check `~/projects/jekyll/testnetlify05/content/tech/yuml_to_uml.md`

http://localhost:1313/tech/yuml_to_uml/

##### Logs

###### Debug alıştırmaları

``` bash
<hr>
debug03: All variables .  <br>
{{ printf "%#v" . }}

<hr>
debug04: .Site <br>
{{ printf "%#v" $.Site }}

<hr>
debug02: $section <br>
{{ range $section }}
		<br>
    {{ printf "%#v" .Title }}
    {{ printf "%#v" .URL }}
{{ end }}

debug01: Pages
<br>
{{ range .Pages }}
		<br>
    {{ printf "%#v" .Title }}
    {{ printf "%#v" .URL }}
{{ end }}

``` 

Type reflection çalışmıyor:

``` bash
debug06: reflect
<!--{{ fmt.Println(reflect.TypeOf($.Site)) }}-->
<!--{{ printf "%T" $.Site }}-->
``` 

####### where nasıl çalışıyor

		{{ $section := where .Site.RegularPages "Section" "in" $mainSections }}

Burada "Section" Pages objesinin bir atributu mu?

##### debug: Page variables: https://gohugo.io/variables/page/ .Site.RegularPages

``` bash
debug07: where Section
{{ $page := index .Site.RegularPages 0 }} <br>
{{ printf "%#v" $page.Title  }} <br>
{{ printf "%#v" $page  }} <br>
{{ printf "%#v" $page.Kind  }} <br>
{{ printf "%#v" $page.Section  }} <br>
  ##> debug07: where Section 
  ##> "A" 
  ##> &hugolib.Page{pageInit:(*hugolib.pageInit)(0xc420028e40),
  ##> Kind:"page",
  ##> Pages:hugolib.Pages(nil),
  ##> translations:hugolib.Pages{},
  ##> translationKey:"",
  ##> Params:map[string]interface {}{
  ##> 	"publishdate":time.Time{wall:0x0, ext:63703131601, loc:(*time.Location)(0x2acbde0)},
  ##> 	"expirydate":time.Time{wall:0x0, ext:0, loc:(*time.Location)(nil)},
  ##> 	"iscjklanguage":false,
  ##> 	"title":"A",
  ##> 	"date":time.Time{wall:0x0, ext:63703131601, loc:(*time.Location)(0x2acbde0)},
  ##> 	"draft":false,
  ##> 	"lastmod":time.Time{wall:0x0, ext:63703131601, loc:(*time.Location)(0x2acbde0)}},
  ##> Content:"<p>Page A</p>\n",
  ##> Summary:"Page A",
  ##> TableOfContents:"",
  ##> Aliases:[]string(nil),
  ##> Images:[]hugolib.Image(nil),
  ##> Videos:[]hugolib.Video(nil),
  ##> Truncated:false,
  ##> Draft:false,
  ##> Status:"",
  ##> PublishDate:time.Time{wall:0x0, ext:63703131601, loc:(*time.Location)(0x2acbde0)},
  ##> ExpiryDate:time.Time{wall:0x0, ext:0, loc:(*time.Location)(nil)},
  ##> PageMeta:hugolib.PageMeta{wordCount:2, fuzzyWordCount:100, readingTime:1, Weight:0},
  ##> Markup:"markdown",
  ##> extension:"",
  ##> contentType:"",
  ##> renderable:true,
  ##> Layout:"",
  ##> selfLayout:"",
  ##> linkTitle:"",
  ##> frontmatter:[]uint8{0x2d, 0x2d, 0x2d, 0xa, 0x74, 0x69, 0x74, 0x6c, 0x65, 0x3a, 0x20, 0x22, 0x41, 0x22, 0xa, 0x64, 0x61, 0x74, 0x65, 0x3a, 0x20, 0x32, 0x30, 0x31, 0x39, 0x2d, 0x30, 0x39, 0x2d, 0x30, 0x33, 0x54, 0x32, 0x31, 0x3a, 0x32, 0x30, 0x3a, 0x30, 0x31, 0x2b, 0x30, 0x33, 0x3a, 0x30, 0x30, 0xa, 0x64, 0x72, 0x61, 0x66, 0x74, 0x3a, 0x20, 0x66, 0x61, 0x6c, 0x73, 0x65, 0xa, 0x2d, 0x2d, 0x2d, 0xa}, rawContent:[]uint8{0x50, 0x61, 0x67, 0x65, 0x20, 0x41, 0xa},
  ##> workContent:[]uint8{0x3c, 0x6e, 0x61, 0x76, 0x3e, 0xa, 0x3c, 0x2f, 0x6e, 0x61, 0x76, 0x3e, 0xa, 0xa, 0x3c, 0x70, 0x3e, 0x50, 0x61, 0x67, 0x65, 0x20, 0x41, 0x3c, 0x2f, 0x70, 0x3e, 0xa},
  ##> rendered:true,
  ##> isCJKLanguage:false,
  ##> shortcodeState:(*hugolib.shortcodeHandler)(0xc420716fc0),
  ##> plain:"Page A\n",
  ##> plainWords:[]string(nil),
  ##> renderingConfig:(*helpers.Blackfriday)(0xc420717f80),
  ##> pageMenus:hugolib.PageMenus{},
  ##> Source:hugolib.Source{Frontmatter:[]uint8(nil),
  ##> 	Content:[]uint8(nil),
  ##> 	File:source.File{
  ##> 		relpath:"section01/a.md",
  ##> 		logicalName:"a.md",
  ##> 		baseName:"a",
  ##> 		Contents:io.Reader(nil),
  ##> 		section:"section01",
  ##> 		dir:"section01/",
  ##> 		ext:"md",
  ##> 		uniqueID:"e65b415118786aad3add7d1b8a356811",
  ##> 		translationBaseName:"a",
  ##> 		lang:"en"}
  ##> 	},
  ##> Position:hugolib.Position{
  ##> 	Prev:(*hugolib.Page)(nil),
  ##> 	Next:(*hugolib.Page)(0xc420316100),
  ##> 	PrevInSection:(*hugolib.Page)(nil),
  ##> 	NextInSection:(*hugolib.Page)(nil)
  ##> 	},
  ##> GitInfo:(*gitmap.GitInfo)(nil),
  ##> sections:[]string{"section01"},
  ##> parent:(*hugolib.Page)(0xc4203deb00),
  ##> origOnCopy:(*hugolib.Page)(nil),
  ##> subSections:hugolib.Pages{},
  ##> s:(*hugolib.Site)(0xc420110280),
  ##> Site:(*hugolib.SiteInfo)(0xc4201102d8),
  ##> Title:"A",
  ##> Description:"",
  ##> Keywords:[]string{},
  ##> Data:map[string]interface {}{"Pages":hugolib.Pages(nil)},
  ##> Date:time.Time{wall:0x0, ext:63703131601, loc:(*time.Location)(0x2acbde0)},
  ##> Lastmod:time.Time{wall:0x0, ext:63703131601, loc:(*time.Location)(0x2acbde0)},
  ##> Sitemap:hugolib.Sitemap{ChangeFreq:"monthly", Priority:0.5, Filename:"sitemap.xml"},
  ##> URLPath:hugolib.URLPath{URL:"", Permalink:"", Slug:"", Section:""},
  ##> permalink:"http://localhost:1313/section01/a/",
  ##> relPermalink:"/section01/a/",
  ##> layoutDescriptor:output.LayoutDescriptor{Type:"section01", Section:"", Kind:"page", Lang:"en", Layout:""},
  ##> scratch:(*hugolib.Scratch)(0xc42102f520),
  ##> language:(*helpers.Language)(0xc4200727e0),
  ##> lang:"en",
  ##> outputFormats:output.Formats{output.Format{Name:"HTML",
  ##> 	MediaType:media.Type{MainType:"text", SubType:"html", Suffix:"html", Delimiter:"."},
  ##> 	Path:"",
  ##> 	BaseName:"index",
  ##> 	Rel:"canonical",
  ##> 	Protocol:"",
  ##> 	IsPlainText:false,
  ##> 	IsHTML:true,
  ##> 	NoUgly:false,
  ##> 	NotAlternative:false}
  ##> 	},
  ##> mainPageOutput:(*hugolib.PageOutput)(0xc4208e0000),
  ##> targetPathDescriptorPrototype:(*hugolib.targetPathDescriptor)(0xc4200e9540)} 
	##>
  ##> "page" 
  ##> "section01" 

``` 

##### debug: Site Parameters: .Site.Params

They come from `~/projects/jekyll/testnetlify05/config.toml`

``` bash
debug08: .Site.Params <br>
{{ printf "%#v" .Site.Params.mainSections }} <br>
{{ printf "%#v" .Site.Params }} <br>
  ##> debug08: .Site.Params 
  ##> []string{"post"} 
  ##> map[string]interface {}{
  ##> 	"favicon":"",
  ##> 	"background_color_class":"bg-black",
  ##> 	"gitlab":"",
  ##> 	"facebook":"",
  ##> 	"youtube":"",
  ##> 	"mastodon":"",
  ##> 	"twitter":"https://twitter.com/GoHugoIO",
  ##> 	"description":"The last theme you'll ever need. Maybe.",
  ##> 	"linkedin":"",
  ##> 	"mainSections":[]string{"post"},
  ##> 	"instagram":"",
  ##> 	"github":"",
  ##> 	"featured_image":"/images/gohugo-default-sample-hero-image.jpg",
  ##> 	"recent_posts_number":2} 
``` 

##### debug: Site Variables: https://gohugo.io/variables/site/ .Site

``` bash
debug09: .Site <br>
{{ printf "%#v" .Site }} <br>
  ##> debug09: .Site 
  ##> &hugolib.SiteInfo{_:[4]uint8{0x0, 0x0, 0x0, 0x0},
  ##> 	paginationPageCount:0x0,
  ##> 	Taxonomies:hugolib.TaxonomyList{
  ##> 		"tags":hugolib.Taxonomy{"scene":hugolib.WeightedPages{hugolib.WeightedPage{Weight:0, Page:(*hugolib.Page)(0xc4203de580)}, hugolib.WeightedPage{Weight:0, Page:(*hugolib.Page)(0xc420315080)}}},
  ##> 		"categories":hugolib.Taxonomy{"yuml-uml":hugolib.WeightedPages{hugolib.WeightedPage{Weight:0, Page:(*hugolib.Page)(0xc420316100)}}}
  ##> 		},
  ##> 	Authors:hugolib.AuthorList(nil),
  ##> 	Social:hugolib.SiteSocial{},
  ##> 	PageCollections:(*hugolib.PageCollections)(0xc4204a0000),
  ##> 	Files:(*[]*source.File)(0xc420110290),
  ##> 	Menus:(*hugolib.Menus)(0xc420110420),
  ##> 	Hugo:(*hugolib.HugoInfo)(nil),
  ##> 	Title:"Notre-Dame de Paris",
  ##> 	RSSLink:"http://localhost:1313/index.xml",
  ##> 	Author:map[string]interface {}{},
  ##> 	LanguageCode:"en-us",
  ##> 	DisqusShortname:"",
  ##> 	GoogleAnalytics:"",
  ##> 	Copyright:"",
  ##> 	LastChange:time.Time{wall:0x0, ext:63703131601, loc:(*time.Location)(0x2acbde0)},
  ##> 	Permalinks:hugolib.PermalinkOverrides{},
  ##> 	Params:map[string]interface {}{"favicon":"",
  ##> 		"background_color_class":"bg-black",
  ##> 		"gitlab":"",
  ##> 		"facebook":"",
  ##> 		"youtube":"",
  ##> 		"mastodon":"",
  ##> 		"twitter":"https://twitter.com/GoHugoIO",
  ##> 		"description":"The last theme you'll ever need. Maybe.",
  ##> 		"linkedin":"",
  ##> 		"instagram":"",
  ##> 		"github":"",
  ##> 		"featured_image":"/images/gohugo-default-sample-hero-image.jpg",
  ##> 		"recent_posts_number":2,
  ##> 		"mainSections":[]string{"post"},
  ##> 		"mainsections":[]string{"post"}
  ##> 		},
  ##> 	BuildDrafts:true,
  ##> 	canonifyURLs:false,
  ##> 	relativeURLs:false,
  ##> 	uglyURLs:false,
  ##> 	preserveTaxonomyNames:false,
  ##> 	Data:(*map[string]interface {})(0xc420110450),
  ##> 	owner:(*hugolib.HugoSites)(0xc42006e200),
  ##> 	s:(*hugolib.Site)(0xc420110280),
  ##> 	multilingual:(*hugolib.Multilingual)(0xc42006e1c0),
  ##> 	Language:(*helpers.Language)(0xc4200727e0),
  ##> 	LanguagePrefix:"",
  ##> 	Languages:helpers.Languages{(*helpers.Language)(0xc4200727e0)},
  ##> 	defaultContentLanguageInSubdir:false,
  ##> 	sectionPagesMenu:"main"} 

``` 

##### debug: $mainSection debug

``` bash
debug10: $mainSections <br>
{{ printf "%#v" $mainSections }} <br>
  ##> debug10: $mainSections 
  ##> []interface {}{"tech", "post"} 
``` 

##### debug: $section debug

``` bash
debug02: $section <br>
{{ printf "%#v" $section }} <br>
{{ range $section }}
		<br>
    {{ printf "%#v" .Title }}
    {{ printf "%#v" .URL }}
{{ end }}
  ##> debug02: $section 
  ##> hugolib.Pages{(*hugolib.Page)(0xc421c2e100), (*hugolib.Page)(0xc421c2d600), (*hugolib.Page)(0xc421c95080), (*hugolib.Page)(0xc421c95600), (*hugolib.Page)(0xc421c2c580), (*hugolib.Page)(0xc421548c00), (*hugolib.Page)(0xc421548680)} 
  ##> 
  ##> "UML Diagrams from Text: yuml to uml" "/tech/yuml_to_uml/" 
  ##> "Chapter VI: Esmeralda" "/post/chapter-6/" 
  ##> "Chapter V: Quasimodo" "/post/chapter-5/" 
  ##> "Chapter IV: Master Jacques Coppenole" "/post/chapter-4/" 
  ##> "Chapter III: Monsieur the Cardinal" "/post/chapter-3/" 
  ##> "Chapter II: Pierre Gringoire" "/post/chapter-2/" 
  ##> "Chapter I: The Grand Hall" "/post/chapter-1/"
``` 

##### ana sayfada tüm sectionları listeleyelim

Edit `~/projects/jekyll/testnetlify05/config.toml`

``` bash
	mainSections = ["tech", "post"]
``` 

##### tech listeleme sayfası oluştur

``` bash
mkdir -p layouts/tech
cp ~/projects/jekyll/testnetlify05/themes/ananke/layouts/post/list.html ~/projects/jekyll/testnetlify05/layouts/tech/list.html
``` 

Edit `~/projects/jekyll/testnetlify05/layouts/tech/list.html`

``` bash
touch ~/projects/jekyll/testnetlify05/content/tech/_index.md
``` 

##### All Articles altında listeleme

Check `~/projects/jekyll/testnetlify05/layouts/index.html`

        {{ with .Site.GetPage "section" $section_name }}

##### Ana sayfa "Recent" Başlığını değiştir

Check `~/projects/jekyll/testnetlify05/layouts/index.html`

            {{ $.Param "recent_copy" | default (i18n "recentTitle" .) }}

Edit `~/projects/jekyll/testnetlify05/content/_index.md`

``` bash
recent_copy = "Recent Posts"
``` 

##### farklı sectionlar için bloklar koyma

		{{ $mainSections := .Site.Params.mainSections | default (slice "tech" "post") }} 
		{{ $section := where .Site.RegularPages "Section" "in" $mainSections }}

->

		{{ $techSections := default (slice "tech") }} 
		{{ $techPages := where .Site.RegularPages "Section" "in" $techSections }}

##### listeleme blokunu bir partials yap

###### Result

``` bash
mkdir -p layouts/partials
touch layouts/partials/listing_posts.html
``` 

Edit `~/projects/jekyll/testnetlify05/layouts/index.html`
 
``` bash
			{{ $mainSections := slice "tech" }} 
			{{ $section := where .Site.RegularPages "Section" "in" $mainSections }}
			{{ partial "listing_posts03" (dict "Site" .Site "section" $section "Page" .) }}
``` 

Edit `~/projects/jekyll/testnetlify05/layouts/partials/listing_posts.html`

###### Logs

####### daha basit bir örnek yaparak başla

``` bash
touch layouts/partials/ex01.html
``` 

Edit `~/projects/jekyll/testnetlify05/layouts/partials/ex01.html`

``` bash
<div>This is partial ex01.html</div>
``` 

Edit `~/projects/jekyll/testnetlify05/layouts/index.html`

``` bash
	layouts_index09
	{{ partial "ex01" . }}
``` 

####### adım adım ilerle

Tamam çalışıyormuş. Aynısı olduğu için fark etmemişim

####### partial sayfasına $section parametresini gönder

``` bash
			{{ $mainSections := slice "tech" }} 
			{{ $section := where .Site.RegularPages "Section" "in" $mainSections }}
			{{ partial "listing_posts" $section }}
``` 

######## Error: .Site.GetPage

Sebebi: partial sayfasına $section gönderiyorum. Bu ise bir Pages arrayi. Bu yüzden, .Site diye bir üyesi yok.

			{{ partial "listing_posts" (dict "Site" .Site "section" $section "Param" $.Param "Page" .) }}

Error: executing "main" at <$.Param>: wrong number of args for Param: want 1 got 0

Sebebi: .Param bir fonksiyon. Parametre alıyor. 

			{{ partial "listing_posts03" (dict "Site" .Site "section" $section "Page" .) }}

.Param fonksiyonunu partial içinde şu şekilde kullan:

      {{ $n_posts := .Page.Param "recent_posts_number" | default 3 }}

##### duplikasyonları temizle partial ile

###### yeni site üret

``` bash
hugo new site testnetlify08
cd $_
git init
git submodule add https://github.com/budparr/gohugo-theme-ananke.git themes/ananke
echo 'theme = "ananke"' >> config.toml
``` 

``` bash
cp -R /Users/mertnuhoglu/projects/jekyll/testnetlify05/layouts /Users/mertnuhoglu/projects/jekyll/testnetlify08
cp -R /Users/mertnuhoglu/projects/jekyll/testnetlify05/content /Users/mertnuhoglu/projects/jekyll/testnetlify08
``` 

Check `~/projects/jekyll/testnetlify08/layouts/index.html`

###### error calling after: can't iterate over *hugolib.PageOutput

		error: error calling partial: template: partials/listing_posts.html:48:27: executing "partials/listing_posts.html" at <after $n_posts .Page>: 

Hatayı verdiği yer:

``` bash
			{{ $mainSections := slice "post" }}  
			{{ $section := where .Site.RegularPages "Section" "in" $mainSections }}
			{{ partial "listing_posts" (dict "Site" .Site "section" $section "Page" .) }}
``` 

Hata bu dosyayı ekleyince oluyor: `/Users/mertnuhoglu/projects/jekyll/testnetlify08/content/post/chapter-5.md`

Hayır, hata 3'ten fazla dosya bulununca meydana geliyor.

Hatanın meydana geldiği satır:

``` bash
        {{ range (first 4 (after $n_posts .section))  }}
          <h2 class="f5 fw4 mb4 dib mr3">
            <a href="{{ .URL }}" class="link black dim">
              {{ .Title }}
            </a>
          </h2>
        {{ end }}
``` 

####### resimleri düzelt

``` bash
cp -R /Users/mertnuhoglu/projects/jekyll/testnetlify08/themes/ananke/exampleSite/static/images /Users/mertnuhoglu/projects/jekyll/testnetlify08/static
``` 

####### tepe menüleri kur

Edit `config.toml`

		SectionPagesMenu = "main"

######## sıfırdan bir site kopyası daha yap

``` bash
hugo new site ananke01
cd $_
git init
git submodule add https://github.com/budparr/gohugo-theme-ananke.git themes/ananke
``` 

``` bash
cp -R /Users/mertnuhoglu/projects/jekyll/gohugo-theme-ananke/exampleSite/content /Users/mertnuhoglu/projects/jekyll/ananke01/
cp -R /Users/mertnuhoglu/projects/jekyll/gohugo-theme-ananke/exampleSite/static /Users/mertnuhoglu/projects/jekyll/ananke01/
cp /Users/mertnuhoglu/projects/jekyll/gohugo-theme-ananke/exampleSite/config.toml /Users/mertnuhoglu/projects/jekyll/ananke01/
echo 'theme = "ananke"' >> config.toml
``` 

Bu satırı sil:

		themesDir = "../.."

``` bash
hugo new tech/a.md
``` 

######## debug

ananke01:

		.Site.Menus.main &hugolib.Menu{(*hugolib.MenuEntry)(0xc4210a6090), (*hugolib.MenuEntry)(0xc4210a6000), (*hugolib.MenuEntry)(0xc4201203f0), (*hugolib.MenuEntry)(0xc4210a6120)}

themenetlify08:

		debug: .Site.Menus.main &hugolib.Menu{(*hugolib.MenuEntry)(0xc42011e000), (*hugolib.MenuEntry)(0xc42011e240)} site-navigation03

######## yuml yazısının resimleri

``` bash
cp /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/content/tech/data/img/*.png /Users/mertnuhoglu/projects/jekyll/testnetlify08/static/images/
``` 

### process 01

``` bash
hugo new site testnetlify08
cd $_
git init
git submodule add https://github.com/budparr/gohugo-theme-ananke.git themes/ananke
``` 

``` bash
cp -R /Users/mertnuhoglu/projects/jekyll/gohugo-theme-ananke/exampleSite/content /Users/mertnuhoglu/projects/jekyll/testnetlify08/
cp -R /Users/mertnuhoglu/projects/jekyll/gohugo-theme-ananke/exampleSite/static /Users/mertnuhoglu/projects/jekyll/testnetlify08/
cp /Users/mertnuhoglu/projects/jekyll/gohugo-theme-ananke/exampleSite/config.toml /Users/mertnuhoglu/projects/jekyll/testnetlify08/
echo 'theme = "ananke"' >> config.toml
``` 

``` bash
netlify init
  ##> Admin URL: https://app.netlify.com/sites/serene-williams-91452b
  ##> URL:       https://serene-williams-91452b.netlify.com
  ##> Site ID:   55708760-0daa-4e2b-8dcc-0fe92e90b727
``` 

Edit `config.toml`

Bu satırı sil:

		themesDir = "../.."

Plan Steps:

		tüm çıktıları kopyala yapıştır
		{bash} -> bash
			s/{\<bash\>}/bash/
		resimleri düzelt

``` bash
hugo new tech/yuml_to_uml.md
``` 

http://localhost:1313/tech/yuml_to_uml/

### bir yazıyı aktarma 20190904 

2. copy md content

``` bash
cp /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/content/tech/study_ramda.Rmd /Users/mertnuhoglu/projects/jekyll/testnetlify08/content/tech/study_ramda.md
``` 

3. copy executed code blocks from https://mertnuhoglu.com/tech/study_ramda/

4. code block headers

``` bash
%s/{\<bash\>}/bash/
%s/{\<js\>}/js/
g/^tags:/ s/\(^tags: \)\@<=\(.*\)/[\2]/
``` 

5. resimler

6. dosyalar

7. delete <style> tag

``` bash
g/^<style>/ .,/^<\/style>$/ d
``` 

