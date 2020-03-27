
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

## Examples - devcards (Quick Trial)

``` bash
git clone https://github.com/bhauman/devcards.git
``` 

``` bash
cd ~/codes/clojure/devcards
lein figwheel
``` 

Open http://localhost:3449/devcards/index.html

Check `/Users/mertnuhoglu/codes/clojure/devcards/project.clj`

### Testing examples

Ex: `devdemos.core`

Check: `~/codes/clojure/devcards/example_src/devdemos/core.cljs`

#### API Documentation

Check `~/codes/clojure/devcards/example_src/devdemos/defcard_api.cljs`

React element:

``` bash
(defcard react-example (sab/html [:h3 "Example: Rendering a ReactElement"]))
``` 

Atoms:

``` bash
(defonce observed-atom
  (let [a (atom {:time 0})]
    (js/setInterval (fn [] (swap! observed-atom update-in [:time] inc)) 1000)
    a))

(defcard atom-observing-card observed-atom {} {:history false})
``` 

React element with state:

``` bash
(defonce first-example-state (atom {:count 55}))

(defcard example-counter
  (fn [data-atom owner]
    (sab/html [:h3 "Example Counter w/Shared Initial Atom: " (:count @data-atom)]))
  first-example-state)

(defcard example-incrementer
  (fn [data-atom owner]
    (sab/html [:button {:onClick (fn [] (swap! data-atom update-in [:count] inc)) } "increment"]))
  first-example-state)

``` 

##### Devcard Options

Inspect data:

``` bash
    :inspect-data false ;; whether to display the data in the card atom
``` 

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


