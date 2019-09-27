

## nuhoğlu vakfı web sitesi hugo 20190910 

Tema: https://github.com/devcows/hugo-universal-theme

https://nuhogluvakfi.netlify.com

``` bash
git clone https://github.com/devcows/hugo-universal-theme
cd hugo-universal-theme
cd exampleSite
hugo serve --themesDir ../..
``` 

``` bash
mkdir nuhogluvakfi01
cd $_
git init
hub create
src=/Users/mertnuhoglu/projects/jekyll/hugo-universal-theme
dest=/Users/mertnuhoglu/projects/jekyll/nuhogluvakfi01
cp $src/exampleSite/config.toml $dest
cp -R $src/exampleSite/static $dest/static
cp -R $src/exampleSite/resources $dest/resources
cp -R $src/exampleSite/data $dest/data
cp -R $src/exampleSite/content $dest/content
mkdir -p $dest/themes
cp -R $src $dest/themes
``` 

Edit `~/projects/jekyll/nuhogluvakfi01/config.toml`

Delete: 

		themesDir = "../.."

``` bash
baseurl = "https://nuhogluvakfi.netlify.com"
``` 

``` bash
git add .
git commit -m ".."
git push
``` 

### other: surfingkeys: copy image url

``` bash
git clone http://github.com/b0o/surfingkeys-conf
``` 

``` bash
cp build/surfingkeys.js ~/.config
``` 

### background resmini değiştirme

``` bash
cd ~/projects/jekyll/nuhogluvakfi01/themes/hugo-universal-theme/static/img
cp photogrid.jpg photogrid0.jpg
convert texture-bw.png texture-bw.jpg
mv texture-bw.jpg photogrid.jpg
``` 

### nav menu paddings

``` bash
mkdir -p static/css
cp ~/projects/jekyll/nuhogluvakfi01/themes/hugo-universal-theme/static/css/style.default.css static/css
``` 

### deploy

``` bash
netlify init
  ##> Admin URL: https://app.netlify.com/sites/nuhogluvakfi
  ##> URL:       https://nuhogluvakfi.netlify.com
  ##> Site ID:   a5e6e136-2496-4891-944a-fe43f9d703ba
``` 

#### Error checking out submodules: fatal: No url found for submodule path

``` bash
git rm --cached themes/hugo-universal-theme
``` 

## Download all images  

opt01:

``` bash
for i in `lynx -image_links -dump http://www.google.com | grep 'jpg\|gif' | grep http | awk '{print $2}'`; do wget $i; done
``` 

opt02: 

``` bash
wget -i links
``` 

#### opt01: R rvest

``` r
#Loading the rvest package
library('rvest')

#Specifying the url for desired website to be scraped
url <- 'https://nuhoglu.org.tr/tr/nuhoglu-vakfi/danisma-meclisi'

#Reading the HTML code from the website
webpage <- read_html(url)

``` 

## web sitesi formu

### google forms inside hugo site

https://blog.webjeda.com/google-form-customize/

Ex: `~/projects/jekyll/nuhogluvakfi01/content/form/dilek_ve_istek_formu.md`

``` bash
``` 

Redirection:

``` html
     <script type="text/javascript">var submitted=false;</script>
     <iframe name="hidden_iframe" id="hidden_iframe" style="display:none;" onload="if(submitted)  {window.location='THE REDIRECT LINK HERE';}"></iframe>

    <form action="FORMACTION CODE HERE" method="post" target="hidden_iframe" onsubmit="submitted=true;">
          <label>Name</label>
          <input name="ENTRY HERE" type="text" placeholder="Name" />
          <br>
          <label>Email</label>
          <input name="ENTRY HERE" type="email" placeholder="Email"/>
          <br>
          <input type="submit" value="Send" />

    </form>

``` 

### css stillendirmesi

Follow https://colorlib.com/wp/template/colorlib-contact-form/

``` bash
mkdir -p layouts/_default
cp ~/projects/jekyll/nuhogluvakfi01/themes/hugo-universal-theme/layouts/_default/single.html layouts/_default
cp ~/projects/jekyll/nuhogluvakfi01/themes/hugo-universal-theme/layouts/_default/single.html layouts/form
cp ~/projects/jekyll/nuhogluvakfi01/themes/hugo-universal-theme/layouts/partials/head.html layouts/partials/head.html
``` 

Edit `~/projects/jekyll/nuhogluvakfi01/layouts/form/single.html`

Edit `/Users/mertnuhoglu/projects/jekyll/nuhogluvakfi01/static/css/forms.css`

Copy paste from https://codepen.io/colorlib/pen/KVoZyv

Edit `~/projects/jekyll/nuhogluvakfi01/layouts/partials/head.html`

``` bash
  <link href="{{ "css/forms.css" | relURL}}" rel="stylesheet">
``` 

### sidebar iç sayfalarda olmasın

Edit `~/projects/jekyll/nuhogluvakfi01/layouts/form/single.html` and `~/projects/jekyll/nuhogluvakfi01/layouts/_default/single.html`

                        <!-- {{ partial "sidebar.html" . }} -->

### arka fon renksiz olsun

Edit `~/projects/jekyll/nuhogluvakfi01/static/css/style.default.css`

``` bash
.jumbotron .dark-mask {
  background: #d8e1e291;
.home-carousel .dark-mask {
  background: #d8e1e291;
``` 

### türkçe

Edit `~/projects/jekyll/nuhogluvakfi01/config.toml`

``` bash
defaultContentLanguage = "tr"
``` 

``` bash
mkdir -p i18n
cp ~/projects/jekyll/nuhogluvakfi01/themes/hugo-universal-theme/i18n/en.yaml i18n/tr.yaml
``` 

Edit `~/projects/jekyll/nuhogluvakfi01/i18n/tr.yaml`

### postları sil

``` bash
rm -f content/blog/*.md
``` 

### style: color

Edit `~/projects/jekyll/nuhogluvakfi01/config.toml`

    style = "red"
