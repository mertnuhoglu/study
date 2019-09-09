
## yeni bir site aç önce

### Result

``` bash
hugo new site testnetlify09
cd $_
git init
``` 

``` bash
src=/Users/mertnuhoglu/projects/jekyll/peterychuang.github.io
dest=/Users/mertnuhoglu/projects/jekyll/testnetlify09
cp -R $src/static $dest
cp -R $src/layouts $dest
cp -R $src/data $dest
cp -R $src/archetypes $dest
cp -R $src/_less $dest
cp $src/config.yml $dest
rm $dest/config.toml
``` 

## yeni bir site daha aç ve 09 ile mertnuhoglu.com sitelerini birleştir burada

### Logs

``` bash
hugo new site testnetlify10
cd $_
git init
``` 

``` bash
src=/Users/mertnuhoglu/projects/jekyll/peterychuang.github.io
dest=/Users/mertnuhoglu/projects/jekyll/testnetlify10
cp -R $src/static $dest
cp -R $src/layouts $dest
cp -R $src/data $dest
cp -R $src/archetypes $dest
cp -R $src/_less $dest
cp $src/config.yml $dest
rm $dest/config.toml
``` 

#### İçeriği kopyala

``` bash
src=/Users/mertnuhoglu/projects/jekyll/testnetlify08
cp -R $src/content $dest
``` 

##### değişen dosyaları bul

https://stackoverflow.com/questions/16787916/difference-between-two-directories-in-linux/29299485

``` bash
dir1=/Users/mertnuhoglu/projects/jekyll/peterychuang.github.io
dir2=/Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com
dir2=/Users/mertnuhoglu/projects/jekyll/testnetlify10/backup
diff -qr $dir1l $dir2l | rg Files | awk '{print $4}' 
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/_default/bookshelf.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/_default/music.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/_default/page.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/_default/single.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/index.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/common/author.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/common/disqus.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/common/footer.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/common/head-social.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/common/head.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/common/header-social.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/common/header.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/common/js.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/common/nav-social.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/common/nav.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/css/css_book.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/css/css_bookshelf.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/css/css_error.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/css/css_home.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/css/css_index.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/css/css_music.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/css/css_pages.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/home/about.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/home/blog.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/home/bookshelf.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/home/contact.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/home/top.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/section/bookshelf.html
diff -qr $dir1l $dir2l | rg Only | awk '{print $3 $4}' | sed 's/:/\//'
  ##> /Users/mertnuhoglu/projects/jekyll/peterychuang.github.io/layouts/_default/list.html
  ##> /Users/mertnuhoglu/projects/jekyll/peterychuang.github.io/layouts/_default/novels.html
  ##> /Users/mertnuhoglu/projects/jekyll/peterychuang.github.io/layouts/partials/home/novels.html
  ##> /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/home/tech.html
  ##> /Users/mertnuhoglu/projects/jekyll/peterychuang.github.io/layouts/section/music.html
``` 

Kalan içerikleri düzelt

``` bash
\cp /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/layouts/partials/home/tech.html $dest/layouts/partials/home
\cp $dir2/layouts/index.html $dest/layouts/index.html
\cp $dir2/layouts/_default/bookshelf.html $dest/layouts/_default/bookshelf.html
\cp $dir2/layouts/_default/music.html $dest/layouts/_default/music.html
\cp $dir2/layouts/_default/page.html $dest/layouts/_default/page.html
\cp $dir2/layouts/_default/single.html $dest/layouts/_default/single.html
\cp $dir2/layouts/partials/common/author.html $dest/layouts/partials/common/author.html
\cp $dir2/layouts/partials/common/disqus.html $dest/layouts/partials/common/disqus.html
\cp $dir2/layouts/partials/common/footer.html $dest/layouts/partials/common/footer.html
\cp $dir2/layouts/partials/common/head-social.html $dest/layouts/partials/common/head-social.html
\cp $dir2/layouts/partials/common/head.html $dest/layouts/partials/common/head.html
\cp $dir2/layouts/partials/common/header-social.html $dest/layouts/partials/common/header-social.html
\cp $dir2/layouts/partials/common/header.html $dest/layouts/partials/common/header.html
\cp $dir2/layouts/partials/common/js.html $dest/layouts/partials/common/js.html
\cp $dir2/layouts/partials/common/nav-social.html $dest/layouts/partials/common/nav-social.html
\cp $dir2/layouts/partials/common/nav.html $dest/layouts/partials/common/nav.html
\cp $dir2/layouts/partials/css/css_book.html $dest/layouts/partials/css/css_book.html
\cp $dir2/layouts/partials/css/css_bookshelf.html $dest/layouts/partials/css/css_bookshelf.html
\cp $dir2/layouts/partials/css/css_error.html $dest/layouts/partials/css/css_error.html
\cp $dir2/layouts/partials/css/css_home.html $dest/layouts/partials/css/css_home.html
\cp $dir2/layouts/partials/css/css_index.html $dest/layouts/partials/css/css_index.html
\cp $dir2/layouts/partials/css/css_music.html $dest/layouts/partials/css/css_music.html
\cp $dir2/layouts/partials/css/css_pages.html $dest/layouts/partials/css/css_pages.html
\cp $dir2/layouts/partials/home/about.html $dest/layouts/partials/home/about.html
\cp $dir2/layouts/partials/home/blog.html $dest/layouts/partials/home/blog.html
\cp $dir2/layouts/partials/home/bookshelf.html $dest/layouts/partials/home/bookshelf.html
\cp $dir2/layouts/partials/home/contact.html $dest/layouts/partials/home/contact.html
\cp $dir2/layouts/partials/home/top.html $dest/layouts/partials/home/top.html
\cp $dir2/layouts/section/bookshelf.html $dest/layouts/section/bookshelf.html
``` 

Geri alma

``` bash
\cp $dir1/layouts/index.html $dest/layouts/index.html
\cp $dir1/layouts/_default/bookshelf.html $dest/layouts/_default/bookshelf.html
\cp $dir1/layouts/_default/music.html $dest/layouts/_default/music.html
\cp $dir1/layouts/_default/page.html $dest/layouts/_default/page.html
\cp $dir1/layouts/_default/single.html $dest/layouts/_default/single.html
\cp $dir1/layouts/partials/common/author.html $dest/layouts/partials/common/author.html
\cp $dir1/layouts/partials/common/disqus.html $dest/layouts/partials/common/disqus.html
\cp $dir1/layouts/partials/common/footer.html $dest/layouts/partials/common/footer.html
\cp $dir1/layouts/partials/common/head-social.html $dest/layouts/partials/common/head-social.html
\cp $dir1/layouts/partials/common/head.html $dest/layouts/partials/common/head.html
\cp $dir1/layouts/partials/common/header-social.html $dest/layouts/partials/common/header-social.html
\cp $dir1/layouts/partials/common/header.html $dest/layouts/partials/common/header.html
\cp $dir1/layouts/partials/common/js.html $dest/layouts/partials/common/js.html
\cp $dir1/layouts/partials/common/nav-social.html $dest/layouts/partials/common/nav-social.html
\cp $dir1/layouts/partials/common/nav.html $dest/layouts/partials/common/nav.html
\cp $dir1/layouts/partials/css/css_book.html $dest/layouts/partials/css/css_book.html
\cp $dir1/layouts/partials/css/css_bookshelf.html $dest/layouts/partials/css/css_bookshelf.html
\cp $dir1/layouts/partials/css/css_error.html $dest/layouts/partials/css/css_error.html
\cp $dir1/layouts/partials/css/css_home.html $dest/layouts/partials/css/css_home.html
\cp $dir1/layouts/partials/css/css_index.html $dest/layouts/partials/css/css_index.html
\cp $dir1/layouts/partials/css/css_music.html $dest/layouts/partials/css/css_music.html
\cp $dir1/layouts/partials/css/css_pages.html $dest/layouts/partials/css/css_pages.html
\cp $dir1/layouts/partials/home/about.html $dest/layouts/partials/home/about.html
\cp $dir1/layouts/partials/home/blog.html $dest/layouts/partials/home/blog.html
\cp $dir1/layouts/partials/home/bookshelf.html $dest/layouts/partials/home/bookshelf.html
\cp $dir1/layouts/partials/home/contact.html $dest/layouts/partials/home/contact.html
\cp $dir1/layouts/partials/home/top.html $dest/layouts/partials/home/top.html
\cp $dir1/layouts/section/bookshelf.html $dest/layouts/section/bookshelf.html
``` 

##### Hata bu dosyalardan kaynaklanıyor:

``` bash
\cp $dir1/layouts/partials/home/about.html $dest/layouts/partials/home/about.html
\cp $dir1/layouts/partials/home/blog.html $dest/layouts/partials/home/blog.html
\cp $dir1/layouts/partials/home/bookshelf.html $dest/layouts/partials/home/bookshelf.html
\cp $dir1/layouts/partials/home/contact.html $dest/layouts/partials/home/contact.html
\cp $dir1/layouts/partials/home/top.html $dest/layouts/partials/home/top.html
``` 

###### contact kısmı açılmıyor

``` bash
\cp $dir1/layouts/index.html $dest/layouts/index.html
``` 

###### contact linki çalışmıyor

hata burada: `layouts/index.html`

``` bash
\cp $dir2/layouts/index.html $dest/layouts/index.html
\cp $dir1/layouts/index.html $dest/layouts/index.html
``` 

Sıralamayı değiştirmek menüyü değiştirmiyor:

``` bash
{{ partial "home/contact.html" . }}
{{ partial "home/bookshelf.html" . }}
``` 

bookshelf satırını silince, sorun meydana geliyor. o zaman bu ikisi arasında bir bağımlılık olmalı.

``` bash
{{ partial "home/bookshelf.html" . }}
``` 

sıralamayı değiştirince?

O zaman Bookshelf linki Contact bölümüne gidiyor. Yani her zaman link 4. bölüme gidiyor. 

###### navbar hatası

tek tek satırları değiştirerek ilerle

``` bash
diff -qr $dir1l $dir2l | rg Files | rg nav.html
grep -v -F -x -f ~/projects/jekyll/mertnuhoglu.com/layouts/partials/common/nav.html ~/projects/jekyll/peterychuang.github.io/layouts/partials/common/nav.html
  ##>         <a class="navbar-brand" href="#page-top"><i class="fa fa-pencil">&nbsp;</i> Peter Y. Chuang - Novelist</a>
  ##>           <li data-menuanchor="about"><a href="#about"> About </a></li>
  ##>           <li data-menuanchor="novels" class="dropdown">
  ##>             <a href="/#novels" class="dropdown-toggle" role="button"> Novels <span class="caret"></span></a>
  ##>               <li><a href="/novels/2047/"> Twenty Forty-Seven </a></li>
  ##>               <li><a href="/novels/only-you-know-what-it-means/"> Only You Know What It Means </a></li>
  ##>               <li><a href="/short-stories/"> Short Stories </a></li>
  ##>               <li><a href="/essays/"> Essays </a></li>
  ##>               <li><a href="/poetry/"> Poetry </a></li>
  ##>               <li><a href="/tech/"> Tech </a></li>
  ##>               <li><a href="/misc/"> Misc. </a></li>
  ##>           <li data-menuanchor="bookshelf"><a href="#bookshelf"> Bookshelf </a></li>
  ##>           <li data-menuanchor="contact"><a href="#contact"> Contact </a></li>
  ##>   {{ if and (or ( eq .Section "bookshelf" ) ( eq .Section "music" )) ( .IsPage ) }}
  ##>           {{ if and (or ( eq .Section "bookshelf" ) ( eq .Section "music" )) ( .IsPage ) }}
  ##>         <a class="navbar-brand" href="/"><i class="fa fa-pencil">&nbsp;</i> Peter Y. Chuang - Novelist</a>
  ##>           <li class="dropdown">
  ##>             <a href="/#novels" class="dropdown-toggle" data-toggle="dropdown" role="button"> Novels <span class="caret"></span></a>
  ##>               <li><a href="/novels/2047/"> Twenty Forty-Seven </a></li>
  ##>               <li><a href="/novels/only-you-know-what-it-means/"> Only You Know What It Means </a></li>
  ##>               <li><a href="/short-stories/"> Short Stories </a></li>
  ##>               <li><a href="/essays/"> Essays </a></li>
  ##>               <li><a href="/poetry/"> Poetry </a></li>
  ##>               <li><a href="/tech/"> Tech </a></li>
  ##>               <li><a href="/misc/"> Misc. </a></li>
  ##>           <li><a href="/bookshelf/"> Bookshelf </a></li>
``` 


## düzelt: anasayfadaki linkler çalışsın

