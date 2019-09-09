---
title: "Study hugo"
date: 2019-09-02T14:44:34+03:00 
draft: true
description: ""
tags:
categories: bash, 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:
path: ~/projects/study/code/study_hugo
state: wip

---

# Documentation

## Quickstart

https://gohugo.io/getting-started/quick-start/

``` bash
brew install hugo
hugo version
``` 

``` bash
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

Site configuration: `config.toml`

Build static pages:

``` bash
hugo
``` 

### Create a git repo

First create a git repo for the local project

``` bash
hub create
git commit -m "initial commit"
git push --set-upstream origin master
``` 

### Deploy on Netlify

https://gohugo.io/hosting-and-deployment/hosting-on-netlify/

Edit `netlify.toml`

#### using netlify cli

https://cli.netlify.com/getting-started

``` bash
npm install netlify-cli -g
netlify login
``` 

New site:

``` bash
netlify init
  ##> build command: hugo --gc --minify
  ##> directory to deploy: public
  ##> https://app.netlify.com/sites/admiring-leavitt-963ae8
  ##> https://admiring-leavitt-963ae8.netlify.com
  ##> 	Site ID:   e59bd208-fef1-444a-858c-f0e4dc09c793
  ##>   git push       Push to your git repository to trigger new site builds
  ##>   netlify open   Open the Netlify admin URL of your site
``` 

Existing site:

``` bash
netlify link
  ##> Site already linked to "vibrant-varahamihira-0026da"
  ##> Admin url: https://app.netlify.com/sites/vibrant-varahamihira-0026da
``` 

#### Change site

``` bash
hugo new posts/second-post.md
  ##> git commit & push
  ##> draft: false
``` 

Create a post under a different directory:

``` bash
hugo new tech/yuml_to_uml.md
``` 

http://localhost:1313/tech/yuml_to_uml/

## Content Management

### Summary

https://gohugo.io/content-management/summaries/

opt01: automatic summary

`.Summary` page variable

`summaryLength` config parameter

opt02: specify summary manually

``` bash
<!--more-->

<!-- toc -->
``` 

## Templates 

### Templates Overview

Templates are HTML with addition of variables and functions. They are within `{{  }}`

Predefined variable:

- Existing in current scope: `.Title`
- A custom variable: `$address`

Parameters: `{{ add 1 2 }}`

Calling fields and methods: `{{ .Params.bar }}`

Each template is passed a `Page`

``` html
<title>{{ .Title }}</title>
``` 

`.Title` is an element of `Page` variable. `Page` is the default scope of a template.

Custom variable:

``` html
{{ $address := "123 Main St" }}
{{ $address }}
``` 

- if branch: `{{if}}` `{{end}}`

- Includes: 

Put a trailing dot at the end.

Templates must be in `layouts/` directory.

Partial: `{{ partial "header.html" . }}`

- Iteration: 

range:

``` html
{{ range $array }}
    {{ . }} <!-- The . represents an element in $array -->
{{ end }}
``` 

Give a name to each element:

``` html
{{ range $elem_val := $array }}
    {{ $elem_val }}
{{ end }}
``` 

- Pipes:

``` bash
{{ shuffle (seq 1 5) }}
{{ (seq 1 5) | shuffle }}
``` 

- Context ("dot")

`{{ . }}` refers to current context

Top level: data made available

Inside iteration: current item int the loop

- Content (`Page`) Parameters

Front matter (yaml)

``` yaml
date: 2013-11-18
notoc: true
``` 

`.Params.notoc`

https://www.youtube.com/watch?v=gnJbPO-GFIw

Two types of pages: single and list.

		content/
			dir1/
				a.md
				b.md

list page: example.com/dir1/

single page: example.com/dir1/a/

### Use $. to Access the Global Context

### Base Templates and Blocks

https://gohugo.io/templates/base/

Ex:

`layouts/_default/baseof.html`

Overarching template.

single, list, home page templates are separate. `baseof` is parent of all.

`layouts/_default/baseof.html`

``` bash
<body>
  {{ block "main" . }}
	...
	{{ end }}
``` 

`layouts/_default/single.html`

``` bash
{{ define "main" }}
...
{{ end }}
``` 

`layouts/_default/list.html`

``` bash
{{ define "main" }}
...
{{ end }}
``` 

`layouts/` folder overrides `themes/.../layouts/` folder.

### List Page Templates

https://gohugo.io/templates/lists/

https://www.youtube.com/watch?v=8b2YTSMdMps

`_index.md` is handled by list template page for some section.

List template: `layouts/_default/list.html`

`{{ .Content }}`: this puts content of `content/dir1/_index.md` into http://example.com/dir1/

### Single Page Templates

https://gohugo.io/templates/single-page-templates/

### Partial Templates

https://www.youtube.com/watch?v=pjS4pOLyB7c

Ex

`layouts/partials/header.html`

``` bash
<h1>{{.Title}}</h1>
``` 

`layouts/_default/single.html`

``` bash
{{ partial "header" . }}
``` 

### Functions

- index: access array element with index

### Section

https://gohugo.io/content-management/sections/

> If a user needs to define a section foo at a deeper level, they need to create a directory named foo with an _index.md file

ie. directory under `content` = section

but with an `_index.md` file

https://www.youtube.com/watch?v=jrMClsB3VsY

hugo has 3 templates: single, list, home page

normally inside `layouts/_default`: `list.html`, `single.html`

we have `content/dir1`

`dir1` is a section 

let's create: `layouts/dir1`

Inside it create: `single.html`, `list.html`

### Pagination

https://gohugo.io/templates/pagination/

Configuration in `config.toml`

- List Paginator Pages

`.Paginator`: available in list pages.

      {{ range .Paginator.Pages }}

What is the difference from `.Pages` only?

      {{ range .Paginator.Pages }}

`.Paginator` generates a navigation bar. `.Pages` loops full list of pages.

### Debugging Templates

https://gohugo.io/templates/template-debugging/

Print all scoped variables: `{{ printf "%#v" . }}`

Print all top level context variables under .Site `{{ printf "%#v" $.Site }}`

Print `.Pages`

``` html
{{ range .Pages }}
    {{ printf "%#v" .Title }}
    {{ printf "%#v" .URL }}
		<br>
{{ end }}
``` 

Ex: `~/projects/jekyll/testnetlify05/layouts/index.html`

## Functions

### .Param function

https://gohugo.io/functions/param/

Calls page or site variables into your template.

.Param KEY

site variables come from `config.toml`

page parameters come from front matter of a page.

### GetPage function

https://gohugo.io/functions/getpage/

Gets a Page of a given path.

.GetPage PATH

### printf function

https://gohugo.io/functions/printf/

``` go
printf FORMAT INPUT
``` 

Format

https://golang.org/pkg/fmt/

``` 
%v	the value in a default format
	when printing structs, the plus flag (%+v) adds field names
``` 

### default function

https://gohugo.io/functions/default/

Allows setting a default value that can be returned if a first value is not set.

``` go
default DEFAULT INPUT
``` 

Ex:

``` bash
title: Sane Defaults
seo_title:
date: 2017-02-18
font:
oldparam: The default function helps make your templating DRYer.
newparam:
``` 

``` bash
{{ index .Params "font" | default "Roboto" }}
{{ default "Roboto" (index .Params "font") }}
``` 

### render function

Takes a view to apply when rendering content.

.Render LAYOUT

### after function

after slices an array to only the items after the Nth item.

after INDEX COLLECTION

### slice function

Creates a slice (array) of all passed arguments.

slice ITEM...

``` bash
{{ delimit (slice "foo" "bar" "buzz") ", " }}
<!-- returns the string "foo, bar, buzz" -->
``` 

## Article01:

https://regisphilibert.com/blog/2019/01/from-wordpress-to-hugo-a-mindset-transition/

### The loop with `range`

Pages are served as `.Pages`

In list template for recipe section: `.Pages` returns collection in recipe.

On a single page, it is empty. You need there `.`

### Loop comparison:

``` html
//layouts/_default/list.html
{{ range .Pages }}
  <h2>
    <a href="{{ .Permalink }}">{{ .Title }}</a>
  </h2>
  <h6>{{ .Date.Format "January, 02 2006" }}</h6>
  <p>
    {{ .Summary }}
  </p>
  <hr>
{{ else }}
<!-- no posts found -->
{{ end }}
``` 

### Accessing other pages

`.Site.Pages` global collection. 

It includes regular pages, sections, taxonomies, etc.

`.Site.RegularPages` includes only pages.

Advanced wp query:

``` php
$recents = new WP_Query(
  [
    'post_type'=>'recipe',
    'posts_per_page'=>5,
    'orderby'   => 'meta_value_num',
    'meta_key'  => 'rating',
  ]
);
 if ( $recents->have_posts() ) : while ( $recents->have_posts() ) : $recents->the_post(); ?>
  <h2>
    <a href="<?php $recents->the_permalink(); ?>"><?php $recents->the_title() ?></a>
  </h2>
<!-- post -->
<?php endwhile; ?>
``` 

In Hugo:

``` html
{{ $recents := (where .Site.RegularPages "Type" "recipe").ByParam "rating" }}
{{ range first 5 $recents }}
  <h2>
    <a href="{{ .Permalink }}">{{ .Title }}</a>
  </h2>
{{ end }}
``` 

### Shortcodes

Wp: output returning functions

Hugo: under `layouts/shortcodes/`

# Theme Customizations

## ananke theme customizations

### ana sayfada tüm sectionları listeleyelim

Edit `~/projects/jekyll/testnetlify05/config.toml`

``` bash
	mainSections = ["tech", "post"]
``` 

### tech listeleme sayfası oluştur

``` bash
mkdir -p layouts/tech
cp ~/projects/jekyll/testnetlify05/themes/ananke/layouts/post/list.html ~/projects/jekyll/testnetlify05/layouts/tech/list.html
``` 

Edit `~/projects/jekyll/testnetlify05/layouts/tech/list.html`

``` bash
touch ~/projects/jekyll/testnetlify05/content/tech/_index.md
``` 



# Examples

## ex01: List page template

``` bash
hugo new section01/a.md
``` 

Edit `~/projects/jekyll/testnetlify05/content/section01/a.md`

``` html
Page A
``` 

Edit `~/projects/jekyll/testnetlify05/content/section01/_index.md`

``` html
This is section01 list page defined by `~/projects/jekyll/testnetlify05/content/section01/_index.md`
``` 

This is layout for list page of `section01`: `~/projects/jekyll/testnetlify05/layouts/section01/list.html`

``` html
<body>
	{{.Content}}
	{{ range .Pages }}
		<ul>
			<li><a href="{{.URL}}">{{.Title}}</a></li>
		</ul>
	{{ end }}
</body>
``` 

Open: http://localhost:1313/section01/

``` bash
<body class="vsc-initialized">
	<p>This is section01 list page defined by <code>~/projects/jekyll/testnetlify05/content/section01/_index.md</code></p>

		<ul>
			<li><a href="/section01/a/">A</a></li>
		</ul>
	
</body>
``` 

## ex02: Partial temel örnek

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
  ##> layouts_index09
  ##> This is partial ex01.html
``` 

## ex03: Context depends on the block

ex: `with`

``` bash
       {{ with .Site.GetPage "section" $section_name }}
            {{ .Param "recent_copy" | default (i18n "recentTitle" .) }}
``` 

Here the dot context is defined by `with`

ex: `range`

``` bash
        {{ range (first $n_posts .section) }}
            {{ partial "summary-with-image.html" . }}
``` 

Here the dot context is defined by `range`

