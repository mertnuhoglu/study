
# Logs 20201011

# New Website Using Hugo tranquilpeak theme 

## @deprecated: opt01: nuhogluvakfi instructions

ref: `universal theme: sıfırdan oluşturma <url:file:///~/projects/study/logbook/hugo_universal_theme_customization_nuhogluvakfi_20190913.md#r=g11635>`

``` bash
mkdir test03
cd $_
src=~/projects/jekyll/hugo-universal-theme
dest=~/projects/jekyll/test03
cp $src/exampleSite/config.toml $dest
cp -R $src/exampleSite/static $dest/static
cp -R $src/exampleSite/resources $dest/resources
cp -R $src/exampleSite/data $dest/data
cp -R $src/exampleSite/content $dest/content
mkdir -p $dest/themes
cp -R $src $dest/themes
``` 

Edit `~/projects/jekyll/test03/config.toml`

Delete: 

		themesDir = "../.."

## opt02: mertnuhoglu.com instructions id=g11641

ref: `Final: mertnuhoglu.com ile yap <url:file:///~/projects/study/logbook/hugo_kisisel_web_sitemi_duzeltme_20190827.md#r=g11639>`

``` bash
mkdir -p burakpehlivan_org/burakpehlivan_org
cd $_
src=~/projects/jekyll/mertnuhoglu.com
dest=~/projects/jekyll/burakpehlivan_org/burakpehlivan_org
cp -R $src/static $dest/static
cp -R $src/resources $dest/resources
cp -R $src/layouts $dest/layouts
cp -R $src/data $dest/data
cp -R $src/content $dest/content
cp -R $src/archetypes $dest/archetypes
cp -R $src/themes $dest/themes
cp $src/config.toml $dest/config.toml
``` 

```bash
cd $dest
hugo server -D
```

```bash
rm $dest/content/blog/*
cp ~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/out/*.md $dest/content/blog/
```

```bash
git init
hub create
git add .
git commit -m ".."
git push
```

``` bash
netlify init
  ##> Admin URL: https://app.netlify.com/sites/optimistic-volhard-1f3e5a
  ##> URL:       https://optimistic-volhard-1f3e5a.netlify.com
  ##> Site ID:   4b4e57ab-a8f0-4aca-a6cc-c79777086a08
  ##> ? Your build command (hugo build/yarn run build/etc): hugo --gc --mini
  ##> fy
  ##> ? Directory to deploy (blank for current dir): public
  ##> ? No netlify.toml detected. Would you like to create one with these build settings? Yes
  ##>   git push       Push to your git repository to trigger new site builds
  ##>   netlify open   Open the Netlify admin URL of your site
``` 

Fix: `~/projects/jekyll/mertnuhoglu.com/config.toml`

``` bash
baseURL = "https://mertnuhoglu.netlify.com"
``` 

## Mirror web site

```bash
wget --mirror --convert-links --adjust-extension --page-requisites --no-parent --no-check-certificate http://burakpehlivan.org
```

## @deprecated: Error: tags içinde tırnak kullanımı yanlış id=g11643

```
'Erdogan's Visit to Ukraine yanukovich and erdogan meeting'
```

Fixed:

```bash
sed -i -e "/tags:/ {s/\(\w\)'\(\w\)/\1@@@\2/g ; s/'/\"/g ; s/@@@/'/g}" *.md
sed -i -e "/^title:/ { s/\(\w\)'\(\w\)/\1''\2/g ; }" *.md
```

# Migrate from Wordpress to Hugo 

opt01:

[Switching From WordPress To Hugo — Smashing Magazine](https://www.smashingmagazine.com/2019/05/switch-wordpress-hugo/)

wp admin panel > tools > Export 

inside `wp2hugo/` 

```bash
mkdir -p ~/projects/jekyll/burakpehlivan_org/wp2hugo
git clone https://github.com/palaniraja/blog2md
cd blog2md
npm install
cp burakpehlivan.wordpress.2020-10-10.xml ~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md

```

Convert all `^M` characters into `NewLine` characters.

```bash
sed -i -e 's//NewLine/' ~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/burakpehlivan.wordpress.2020-10-10.xml
```

Convert `xml` into `md` files

```bash
node index.js w burakpehlivan.wordpress.2020-10-10.xml out m
```

Fix mistakes in md files

```bash
cp ~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/out/*.md $dest/content/blog/
cd $dest/content/blog/
```

01: `'` içinde `'` kullanılmış:

```
tags: ['Kiev'de Aqua Park']
->
tags: ["Kiev'de Aqua Park"]
```

```bash
sed -i -e "/tags:/ {s/\(\w\)'\(\w\)/\1@@@\2/g ; s/'/\"/g ; s/@@@/'/g}" *.md
sed -i -e "/^title:/ { s/\(\w\)'\(\w\)/\1''\2/g ; }" *.md
```

02: tags satırından sonra type satırı ekle:

```bash
tags: ['Kiev'de Aqua Park']
->
tags: ['Kiev'de Aqua Park']
type: post
```

```bash
sed -i -e '/tags:/ a\
type: post
' *.md
```

Convert `NewLine` to `\n`

```bash
sed -i -e 's/NewLine */\n/g' *.md
```

Resimlerdeki http url'lerini https yapalım

```bash
sed -i -e 's/http:..burakpehlivan.org/https:\/\/burakpehlivan.org/g' content/blog/*.md
```

Copy images:

```bash
cp -R ~/projects/jekyll/burakpehlivan_org/backup/burakpehlivan.org/wp-content/uploads/ ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/
```

Remove redundant image files:

```bash
find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -regextype posix-extended -regex '.*-[0-9][0-9]+x[0-9][0-9]+.jpg' -delete
find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -regextype posix-extended -regex '.*-[0-9][0-9]+x[0-9][0-9]+.jpeg' -delete
find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -regextype posix-extended -regex '.*-[0-9][0-9]+x[0-9][0-9]+.png' -delete
```

Resize image files:

```bash
for d in $(find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -maxdepth 5 -type d)
do
  echo $d
	mogrify -quality 90 -density 300x300 -resize 800x480\> ${d}/*.jpeg
	mogrify -quality 90 -density 300x300 -resize 800x480\> ${d}/*.jpg
	mogrify -quality 90 -density 300x300 -resize 800x480\> ${d}/*.png
done 
```

## @deprecated: opt02: id=g11646

[lonekorean/wordpress-export-to-markdown: Converts a WordPress export XML file into Markdown files.](https://github.com/lonekorean/wordpress-export-to-markdown)

```bash
npx wordpress-export-to-markdown
```

## compress all images files

[bash - Traverse all subdirectories in and do something in Unix shell script - Unix & Linux Stack Exchange](https://unix.stackexchange.com/questions/187167/traverse-all-subdirectories-in-and-do-something-in-unix-shell-script)

```bash
for d in $(find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -maxdepth 5 -type d)
do
  echo $d
	mogrify -quality 100 -density 300x300 -resize 800x480\! ${d}/*.jpg
	mogrify -quality 100 -density 300x300 -resize 800x480\! ${d}/*.png
done 

for d in ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/*; do
  if [ -d "$d" ]; then
		echo "$d"
  ##> 		mogrify -quality 100 -density 300x300 -resize 800x480\! *.jpg
  ##> 		mogrify -quality 100 -density 300x300 -resize 800x480\! *.png
  fi
done
```

### Shring images never enlarge

[Resizing or Scaling -- IM v6 Examples](https://legacy.imagemagick.org/Usage/resize/#shrink)

Use `>` flag:

```bash
mogrify -quality 100 -density 300x300 -resize 800x480\> ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/2016/12/*.jpg
```

### Fazlalık resimleri sil

Şu tarz dosyaları silmek istiyorum:

```
tarim-300x227.jpg
tarim-150x150.jpg
tarim-65x65.jpg
```

Nasıl bunları bulup silerim?

#### find: use regex in find

[bash - How to use regex in file find - Stack Overflow](https://stackoverflow.com/questions/5249779/how-to-use-regex-in-file-find)

```bash
find /home/test -regextype posix-extended -regex '^.*test\.log\.[0-9]{4}-[0-9]{2}-[0-9]{2}\.zip' -mtime +3
```

```bash
find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -regextype posix-extended -regex '.*-[0-9][0-9]+x[0-9][0-9]+.jpg' 
find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -regextype posix-extended -regex '.*-[0-9][0-9]+x[0-9][0-9]+.png' 
```

#### find: rm files that find returns

```bash
find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -regextype posix-extended -regex '.*-[0-9][0-9]+x[0-9][0-9]+.jpg' -delete
find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -regextype posix-extended -regex '.*-[0-9][0-9]+x[0-9][0-9]+.png' -delete
```

#### resimlerdeki x kısımlarını da temizle

```bash
sed -i -e "/burakpehlivan.org.wp-content/ {s/-[0-9][0-9]+x[0-9][0-9]+.jpg/.jpg/g ; }" *.md
sed -i -e "/burakpehlivan.org.wp-content/ {s/-[0-9][0-9]\+x[0-9][0-9]\+.jpg/.jpg/g ; }" tuid-yeni-yonetim-kurulu-t-c-kiev-buyukelcisi-yonet-can-tezeli-ziyaret-etti.md
sed -i -e "/burakpehlivan.org.wp-content/ {s/-[0-9][0-9]\+x[0-9][0-9]\+.jpg/.jpg/g ; }" *.md
find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -regextype posix-extended -regex '.*-[0-9][0-9]+x[0-9][0-9]+.jpg' -delete
```


# sidebar ve diğer sayfaları düzelt 

# url redirection 

## Aliases

https://gohugo.io/content-management/urls/

## opt01: Convert wordpress export xml to csv

Use: `https://www.freeformatter.com/xml-to-json-converter.html`

Use: `http://www.utilities-online.info/xmltojson/`

```bash
cp ~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/burakpehlivan.wordpress.2020-10-10.xml ~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/burak02.xml
```

Edit `~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/burak02.xml`

Şu satırların dışındakileri sil:

```bash
	<item>
		<link>http://burakpehlivan.org/259/ukrayna-italya-iliskileri-hakkinda/148_86853/</link>
		<wp:post_id>270</wp:post_id>
		<wp:post_type><![CDATA[post]]></wp:post_type>
```

Delete all lines except:

```bash
cd ~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/
sed -i -e '/\(<.\?item\)\|\(<.\?link\)\|\(wp:post_id\)\|\(wp:post_type\)\|\(<.\?title\)/! d' -e '/wp:meta_value/d' burak02.xml
```

Remove `CDATA`:

```
<wp:post_type><![CDATA[post]]></wp:post_type>
->
<wp:post_type>post</wp:post_type>
```

```bash
sed -i -E 's/<!\[CDATA\[(\w+)]]>/\1/' burak02.xml
sed -i -e 's/wp://g' burak02.xml
```

Convert xml to json: `http://www.utilities-online.info/xmltojson/`

Output: `~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/burak02.json`

```r
library(dplyr)
ba0 = jsonlite::fromJSON("burak02.json", simplifyDataFrame = T) 
ba1 = ba0[[1]] %>%
	as_tibble()
```

### Eksik sayfaların sebebi nedir?

Hangi `post_type` değerleri var?

```r
pt = ba1$post_type %>% unique
  ##> [1] "attachment"    "nav_menu_item" "page"          "post"          NA             
```

```bash
ba2 = ba1 %>%
	dplyr::filter(post_type %in% c("page", "post"))
```

`ba2` içinde 262 satır var. 

Dosya listesiyle bunları kıyasla:

```bash
fl = list.files("~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/content/blog/") %>%
	stringr::str_replace("\\.md","")
writeLines(fl, "out/file_list.txt")
```

Toplam bende 432 md dosyası var. Aradaki fark xml'den alamadığım sayfalar. Bunların nedeni bulalım.

```bash
missing_pages = setdiff(fl, page_names)
writeLines(missing_pages, "out/missing_pages.txt")
```

Eksik olan toplam 175 dosya var:

`~/projects/study/logbook/ex/hugo_burakpehlivan_org_20201011/c_wordpress_export_xml_to_csv/out/missing_pages.txt`

Bunları `xml` dosyasında arayalım: 

Örneğin:

```bash
  <item>
		<title>Ukrayna’yı ve Ukrayna Türkiye ilişkilerini Burak Pehlivan BRTV’ye bir röportajla değerlendirdi </title>
		<link>http://burakpehlivan.org/5105/ukraynayi-ve-ukrayna-turkiye-iliskilerini-burak-pehlivan-brtvye-bir-roportajla-degerlendirdi/</link>
		<wp:post_id>5105</wp:post_id>
		<wp:post_type>post</wp:post_type>
	</item>
```

`tsv` dosyasında bu kayıt var mı?

Check `~/projects/study/logbook/ex/hugo_burakpehlivan_org_20201011/c_wordpress_export_xml_to_csv/out/ba1.tsv`

Yok. O zaman xml dosyasını json'a aktarmada sorun olmuş olabilir.

Evet, xml dosyasında 4183 `item` var, fakat tsv dosyasında 4025 satır var.

### Debug: neden xml dosyası düzgün çevrilmedi:

opt01: `wp:` prefixlerini kaldır

opt02:

```bash
<strong>Original link</strong>: <a href="http://www.kyivpost.com/content/business/turkey-remains-good-friend-in-ukraines-neighborhood-400455.html">http://www.kyivpost.com/content/business/turkey-remains-good-friend-in-ukraines-neighborhood-400455.html</a>]]></content:encoded>
```

## Alias sayfaları oluştur

https://gohugo.io/content-management/urls/#how-hugo-aliases-work

```html
<!DOCTYPE html>
<html>
  <head>
    <title>https://example.com/posts/my-intended-url</title>
    <link rel="canonical" href="https://example.com/posts/my-intended-url"/>
    <meta name="robots" content="noindex">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="refresh" content="0; url=https://example.com/posts/my-intended-url"/>
  </head>
</html>
```

Bu yönlendirme sayfalarını otomatik üretebilirim `ba2.tsv` verilerini kullanarak:

```bash
rp = gen_redirection_pages(ba2)
```

## Tarih başlığını düzelt sayfalardaki

opt03: bozarak ilerle (sistematik eliminasyon)

Check `~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/themes/tranquilpeak/layouts/partials/post/meta.html`

```html
{{ if not (eq .Params.showMeta false) }}
  <div class="postShorten-meta post-meta">
    {{ if not (eq .Params.showDate false)  }}
      <time itemprop="datePublished" datetime="{{ .Date.Format "2006-01-02T15:04:05Z07:00" }}">
        {{ partial "internal/date.html" . }}
      </time>
    {{ end }}
    {{ partial "post/category.html" . }}
  </div>
{{ end }}
```

## netlify deploy

### Error: filename includes `#` or `?` character

Bu karakterlerin olduğu dosyalar neler?

```bash
	dplyr::filter(!stringr::str_detect(link, "[?#]"))
```

### error: stil bozuk

Şurada 404 hatası vermiş:

```bash
Request URL: https://burakpehlivan.org/css/style-twzjdbqhmnnacqs0pwwdzcdbt8yhv8giawvjqjmyfoqnvazl0dalmnhdkvp7.min.css
```

Sebebi: `config.toml` 

```bash
baseURL = "https://burakpehlivan.org"
```

### DNS Update

```bash
ns114.inhostedns.com2016-10-31 
ns214.inhostedns.net2016-10-31 
ns314.inhostedns.net2016-10-31 
```

```bash
dns1.p04.nsone.net
dns2.p04.nsone.net
dns3.p04.nsone.net
dns4.p04.nsone.net
```

