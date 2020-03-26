
# Video: Literate interactive coding - Devcards' by Bruce Hauman-G7Z_g2fnEDg

Two problems:

1. continous browser reloading

2. developing inside the main application (as main avenue of feedback)

Problem:

- code, reload, manipulate, verify cycle

- UI coding = endless tweaking

Solution:

- hot code reloading

figwheel sohlves this

## Devcards: it is like rmarkdown notebook

visual namespaces: contain code examples to navigate

`defcard` macro

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture//20200305101536.png)

React components rendered in notebook.

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture//20200305101628.png)

Print data:

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture//20200305101835.png)

Test UI component and undo/play:

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture//20200305102020.png)

Benefits:

- encourages independent components

- Flexible dev environment

- validation document: check behavior of individual components

- reference document: how to use code

# Article: Official README

https://github.com/bhauman/devcards

## Quick Start

``` bash
cd ~/projects/study/clj/ex/study_devcards/e01
lein new devcards hello-world
cd hello-world
lein figwheel
``` 

Open http://localhost:3449/cards.html

Edit `~/projects/study/clj/ex/study_devcards/e01/hello-world/src/hello_world/core.cljs`

## Examples - devcards

``` bash
git clone https://github.com/bhauman/devcards.git
cd devcards
lein figwheel
``` 

Open http://localhost:3449/devcards/index.html

Check `/Users/mertnuhoglu/codes/clojure/devcards/project.clj`

## Usage

Step 1: Add `:dependencies` into `project.clj`

``` bash
[org.clojure/clojurescript "1.10.238"]
[devcards "0.2.5"]
``` 

Step 2: Make an HTML file to host devcards

Ex: `resources/public/cards.html` such as `~/projects/study/clj/ex/study_devcards/e01/hello-world/resources/public/cards.html`

``` bash
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset="UTF-8">
    <link href="/css/example.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <script src="/js/compiled/example.js" type="text/javascript"></script>
  </body>
</html>
``` 


