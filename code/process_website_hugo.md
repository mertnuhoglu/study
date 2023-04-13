
# Process Website with Hugo id=g14193

Ref:

		~/projects/study/logbook/hugo_kisisel_web_sitemi_duzeltme_20190827.md

## Steps

Existing website: `/Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com`

``` bash
mkdir mertnuhoglu.com
cd $_
git init
hub create
src=/Users/mertnuhoglu/projects/jekyll/testnetlify12
dest=/Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com
cp -R $src/static $dest/static
cp -R $src/resources $dest/resources
cp -R $src/layouts $dest/layouts
cp -R $src/data $dest/data
cp -R $src/content $dest/content
cp -R $src/archetypes $dest/archetypes
cp -R $src/themes $dest/themes
cp $src/config.toml $dest/config.toml
git add .
git commit -m ".."
git push
``` 

``` bash
netlify init
  ##> Admin URL: https://app.netlify.com/sites/optimistic-volhard-1f3e5a
  ##> URL:       https://optimistic-volhard-1f3e5a.netlify.com
  ##> Site ID:   4b4e57ab-a8f0-4aca-a6cc-c79777086a08
  ##> ? Your build command (hugo build/yarn run build/etc): hugo --gc --minify
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

### DNS Setup

opt01: ALIAS or ANAME redirection

https://app.netlify.com/sites/mertnuhoglu/settings/domain#custom-domains

opt02: Netlify DNS

netlify > Domain management > 

DNS Settings:

opt01:

```
nuhoglu.org.tr ALIAS nuhogluvakfi.netlify.app.
www CNAME nuhogluvakfi.netlify.app.
```

opt02:

```
nuhoglu.org.tr A 104.198.14.52
```

DNS Name Servers:

```bash
dns1.p04.nsone.net
dns2.p04.nsone.net
dns3.p04.nsone.net
dns4.p04.nsone.net
```



### SSL Setup

netlify > Domain management > SSL

## Publishing a new page

``` bash
hugo new blog/new_page.md
``` 

Summary:

``` bash
<!--more-->

<!-- toc -->
``` 

Test it:

```bash
hugo server -D
```

Deploy on netlify:

```bash
git add ...
git commit
git push
```

## nuhogluvakfi

```bash
rename 's/'
```

```bash
mogrify -quality 90 -density 300x300 -resize 800x480\> ./*.jpeg
mogrify -quality 90 -density 300x300 -resize 800x480\> ./*.jpg
mogrify -quality 90 -density 300x300 -resize 800x480\> ./*.png
```



