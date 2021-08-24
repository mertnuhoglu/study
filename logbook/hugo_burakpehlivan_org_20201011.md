# Migrate burakpehlivan.org from wordrpress to hugo id=g11640

## New Website Using Hugo tranquilpeak theme 

ref: `Final: mertnuhoglu.com ile yap <url:file:///~/projects/study/logbook/hugo_kisisel_web_sitemi_duzeltme_20190827.md#r=g11639>`

ref for logs: `~/projects/study/logbook/logs_hugo_burakpehlivan_org_20201011.md`

opt01: Create hugo site from scratch

``` bash
cd ~/projects/jekyll/burakpehlivan_org
hugo new site burakpehlivan_org
git init
git submodule add https://github.com/kakawait/hugo-tranquilpeak-theme#quick-start themes/tranquilpeak
echo 'theme = "tranquilpeak"' >> config.toml
dest=~/projects/jekyll/burakpehlivan_org/burakpehlivan_org
``` 

opt02: Copy hugo site from a previous one:

``` bash
dest=~/projects/jekyll/burakpehlivan_org/burakpehlivan_org
mkdir -p $dest
src=~/projects/jekyll/mertnuhoglu.com
cp -R $src/static $dest/static
cp -R $src/resources $dest/resources
cp -R $src/layouts $dest/layouts
cp -R $src/data $dest/data
cp -R $src/content $dest/content
cp -R $src/archetypes $dest/archetypes
cp -R $src/themes $dest/themes
cp $src/config.toml $dest/config.toml
``` 

Run hugo:

```bash
cd $dest
hugo server -D
```

## Download Mirror of Existing Web Site

Download mirror of existing web site for two reasons:

1. To have a backup 
2. To copy the image files to the new web site

```bash
cd $dest/..
mkdir -p backup
cd $_
wget --mirror --convert-links --adjust-extension --page-requisites --no-parent --no-check-certificate http://burakpehlivan.org
```

## Migrate from Wordpress to Hugo id=g11638

[Switching From WordPress To Hugo — Smashing Magazine](https://www.smashingmagazine.com/2019/05/switch-wordpress-hugo/)

Step01: WP Admin Panel > Tools > Export xml

inside `wp2hugo/` 

```bash
mkdir -p ~/projects/jekyll/burakpehlivan_org/wp2hugo
cd $_
git clone https://github.com/palaniraja/blog2md
cd blog2md
npm install
cp burakpehlivan.wordpress.2020-10-10.xml ~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md

```

Convert all `^M` characters into `NewLine` characters. Otherwise, new lines are silently deleted in markdown files. (Type `Ctrl-V Ctrl-M` to enter `^M` in terminal)

```bash
sed -i -e 's//NewLine/' ~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/burakpehlivan.wordpress.2020-10-10.xml
```

Convert `xml` into `md` files using wp2hugo.

```bash
node index.js w burakpehlivan.wordpress.2020-10-10.xml out m
```

Step02: Fix mistakes in generated md files

```bash
cp ~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/out/*.md $dest/content/blog/
cd $dest/content/blog/
```

01: Fix nested quote characters inside `tags` and `title` lines: 

Example: 

```
tags: ['Kiev'de Aqua Park']
->
tags: ["Kiev'de Aqua Park"]
```

Run commands:

```bash
sed -i -e "/^tags:/ {s/\(\w\)'\(\w\)/\1@@@\2/g ; s/'/\"/g ; s/@@@/'/g}" *.md
sed -i -e "/^title:/ { s/\(\w\)'\(\w\)/\1''\2/g ; }" *.md
```

02: Append `type` line after `tags` line

Example:

```
tags: ['Kiev'de Aqua Park']
->
tags: ['Kiev'de Aqua Park']
type: post
```

Run commands:

```bash
sed -i -e '/tags:/ a\
type: post
' *.md
```

03: Convert `NewLine` to `\n` (wp2hugo silently removes all new lines inside the post contents)

```bash
sed -i -e 's/NewLine */\n/g' *.md
```

04: Convert `http` to `https` in all image urls of existing web site:

```bash
sed -i -e 's/http:..burakpehlivan.org/https:\/\/burakpehlivan.org/g' *.md
```

05: Copy all images from the mirror web site to new hugo site. Note that, I copied `wp-content` directory directly because I don't want to deal with changing existing image urls:

```bash
cp -R ~/projects/jekyll/burakpehlivan_org/backup/burakpehlivan.org/wp-content/uploads/ ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/
```

06: Remove redundant image files (Duplicate image files with names such as `IMG-800x600.jpg`):

```bash
find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -regextype posix-extended -regex '.*-[0-9][0-9]+x[0-9][0-9]+.jpg' -delete
find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -regextype posix-extended -regex '.*-[0-9][0-9]+x[0-9][0-9]+.jpeg' -delete
find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -regextype posix-extended -regex '.*-[0-9][0-9]+x[0-9][0-9]+.png' -delete
```

07: Resize image files to shrink their dimensional sizes such that they don't overflow in the web pages:

```bash
for d in $(find ~/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static/wp-content/uploads/ -maxdepth 5 -type d)
do
  echo $d
	mogrify -quality 90 -density 300x300 -resize 800x480\> ${d}/*.jpeg
	mogrify -quality 90 -density 300x300 -resize 800x480\> ${d}/*.jpg
	mogrify -quality 90 -density 300x300 -resize 800x480\> ${d}/*.png
done 
```

```bash
mogrify -quality 90 -density 300x300 -resize 800x480\> *.jpg
```

## Copy md files

Copy all md files that are generated from wordpress xml export file:

```bash
rm $dest/content/blog/*
cp ~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/out/*.md $dest/content/blog/
```

## Fix sidebar and about pages id=g11647

Edit `config.toml`

01: Edit `menu.main` to setup the main menu links.

Example:

```bash
[[menu.main]]
  weight = 6
  identifier = "about"
  name = "About"
  pre = "<i class=\"sidebar-button-icon fa fa-lg fa-question\"></i>"
  url = "/about"
```

02: Set other parameters such as `title`, `baseURL`

Example:

```bash
baseURL = "https://burakpehlivan.org"
```

Note that, when we set `baseURL` before setting up the domain settings then we will have some broken links after deploying to netlify. But they will be fixed after domain is set up.

## url redirection id=g11650

Wordpress has permalinks such as: 

`/7992/koronavirus-sonrasi-ukrayna-ekonomisi-ve-ortaya-cikabilecek-firsatlar`

But now the new link for this page is:

`/blog/koronavirus-sonrasi-ukrayna-ekonomisi-ve-ortaya-cikabilecek-firsatlar`

So we need to redirect old links to new links.

To do this we are going to create redirection pages as described in [hugo's documentation](https://gohugo.io/content-management/urls/#how-hugo-aliases-work)

Basically, we will create redirection pages for the old links:

For example, we will have `/static/7992/koronavirus-sonrasi-ukrayna-ekonomisi-ve-ortaya-cikabilecek-firsatlar.html`:

```html
<!DOCTYPE html>
<html>
<head>
<title>/blog/koronavirus-sonrasi-ukrayna-ekonomisi-ve-ortaya-cikabilecek-firsatlar</title>
<link rel="canonical" href="/blog/koronavirus-sonrasi-ukrayna-ekonomisi-ve-ortaya-cikabilecek-firsatlar"/>
<meta name="robots" content="noindex">
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<meta http-equiv="refresh" content="0; url=/blog/koronavirus-sonrasi-ukrayna-ekonomisi-ve-ortaya-cikabilecek-firsatlar"/>
</head>
</html>
```

We need to generate all such redirection pages. 

Here is the R script that generates these redirection pages:

`ex/hugo_burakpehlivan_org_20201011/c_wordpress_export_xml_to_csv/c_wordpress_export_xml_to_csv.R`

# Fix 20210110 

## 02: tuid.org.ua resimleri

```bash
rg "tuid.org.ua.images"
rg "tuid.org.ua.wp-content"
```

```bash
/http:..tuid.org.ua.images[^)]*
:MatchesOnly
/http:..tuid.org.ua.wp-content[^)]*
:MatchesOnly
%s#\/tuid.org.ua#\/arsiv.tuid.org.ua#
sort u
```

Save to file: `~/projects/jekyll/burakpehlivan_org/other/tuid_org_ua_images.txt`

```bash
acksed "tuid.org.ua.images" "arsiv.tuid.org.ua/images"
acksed "tuid.org.ua.wp-content" "arsiv.tuid.org.ua/wp-content"
```

Tüm resimleri indir

```bash
wget -i ~/projects/jekyll/burakpehlivan_org/other/tuid_org_ua_images.txt
```

Resimleri yeni bir klasöre aktar

```bash
mv /Users/mertnuhoglu/projects/jekyll/burakpehlivan_org/other/tuid_images /Users/mertnuhoglu/projects/jekyll/burakpehlivan_org/burakpehlivan_org/static
```

```bash
sed -n -e 's#http:..arsiv.tuid.org.ua.*\/\([^/]*.jpg\)#https://burakpehlivan.org/tuid_images/\1#p' *.md
sed -i -e 's#http:..arsiv.tuid.org.ua.*\/\([^/]*.jpg\)#https://burakpehlivan.org/tuid_images/\1#g' *.md
```


