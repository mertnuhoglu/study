---
title: "Studying Reagent"
date: 2020-02-05T21:00:41+03:00 
draft: false
description: ""
tags:
categories: clojurescript
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Projects

## Project: simple

``` bash
cd ~/codes/clojure/reagent/examples/simple
lein figwheel
``` 

Check `~/codes/clojure/reagent/examples/simple/src/simpleexample/core.cljs`

Open `example.html`

### Project: ex03 copied from simple

``` bash
cp -R ~/codes/clojure/reagent/examples/simple ex03
``` 

Check `~/projects/study/clj/ex/study_reagent/ex03/project.clj`

Burada `hello-component` koyalım:

Edit `~/projects/study/clj/ex/study_reagent/ex03/src/simpleexample/core.cljs`

``` bash
(defn hello-component [name]
  [:p "Hello, " name "!"])

(defn simple-component []
  [:div
   [hello-component "mert"]
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

(defn simple-example []
  [:div
	 ...
   [simple-component]])
``` 

Şimdi bracket çalışıyor.

# Article: Up and running with ClojureScript in 2018 - By Rory Gibson

``` bash
mkdir -p ex02/src/ex02
cd ex02
mkdir -p resources/public
``` 

Create `~/projects/study/clj/ex/study_reagent/ex02/deps.edn`

Create `~/projects/study/clj/ex/study_reagent/ex02/dev.cljs.edn`

Create `~/projects/study/clj/ex/study_reagent/ex02/resources/public/index.html`

Create `~/projects/study/clj/ex/study_reagent/ex02/resources/public/styles.css`

Create `~/projects/study/clj/ex/study_reagent/ex02/src/ex02/core.cljs`

``` bash
clojure -A:fig:build
``` 

This starts the web server. Opens http://localhost:9500/. And prompts a REPL.

Hot-reload is enabled.

Update `~/projects/study/clj/ex/study_reagent/ex02/resources/public/styles.css`

## Studying code:

### core.cljs

Check `~/projects/study/clj/ex/study_reagent/ex02/src/ex02/core.cljs`

#### `^:figwheel-hooks`

[Clojure - Reading Clojure Characters](https://clojure.org/guides/weird_characters)

[^ (and #^) - Metadata](https://clojure.org/guides/weird_characters#_and_metadata)

`^` is the metadata marker. 

``` bash
user=> (def ^:debug five 5)
  #'user/five
user=> (meta #'five)
{:ns #<Namespace user>, :name five, :column 1, :debug true, :line 1, :file "NO_SOURCE_PATH"}
``` 

# Discussion: Routing with re-frame/Reagent : Clojure

https://www.reddit.com/r/Clojure/comments/a6lokt/routing_with_reframereagent/

reitit: start here. It works on both backend and frontend. 

# Article: Reagent: Minimalistic React for ClojureScript

http://reagent-project.github.io/

``` bash
``` 

# Article: reagent-project/reagent: A minimalistic ClojureScript interface to React.js

https://github.com/reagent-project/reagent

http://reagent-project.github.io/

## Usage

``` bash
lein new reagent ex01
``` 

Check `~/projects/study/clj/ex/study_reagent/ex01/project.clj`

``` bash
lein run
``` 

### Logs

#### Error

``` bash
$ lein new reagent myproject

java.lang.NullPointerException: null
``` 

https://github.com/Day8/re-frame-template/issues/63

opt01: Does lein work in general?

``` bash
lein new compojure tmp
``` 

opt02: Edit `~/.lein/profiles.clj`

Remove this line:

``` bash
{:user {:plugins [[luminus/lein-template "3.56"]]}}
``` 

This fixed the issue.

#### Error: lein run: clojure-future-spec 

``` bash
$ lein run

Retrieving clojure-future-spec/clojure-future-spec/1.9.0/clojure-future-spec-1.9.0.jar from clojars
java.lang.NullPointerException: null
 at clojure.core$name.invokeStatic (core.clj:1595)
``` 

https://github.com/technomancy/leiningen/issues/2383

opt01:

``` bash
lein repl
``` 

Same error

``` bash
lein version
``` 

Same error

opt02: leinı tekrar yükle

opt03: profile dosyasını temizle

``` bash
mv ~/.lein ~/.lein0
``` 

This fixed the issue.

## Simple Component

``` bash
(defn simple-component []
  [:div
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])
``` 

``` bash
(def mount-target
  [:div#app
	 ..
   (simple-component)
	 ...
``` 

Open file:///Users/mertnuhoglu/projects/study/clj/ex/study_reagent/ex03/example.html

## hello-component

``` bash
(defn hello-component [name]
  [:p "Hello, " name "!"])

(defn simple-component []
  [:div
   (hello-component "mert")
``` 

Error: Bracket ile çalışmıyor, hata veriyor. Parantez yapmam gerekiyor. 

Fix: Project: ex03 copied from simple <url:/Users/mertnuhoglu/projects/study/clj/study_reagent.md#tn=Project: ex03 copied from simple>

## list component

``` bash
(defn lister [items]
  [:ul
   (for [item items]
     ^{:key item} [:li "Item " item])])

(defn lister-user []
  [:div
   "Here is a list:"
   [lister (range 3)]])
``` 

Check `~/projects/study/clj/ex/study_reagent/ex03/src/simpleexample/core.cljs`

### ^{:key item}

https://stackoverflow.com/questions/40236460/purpose-of-key-item

> React needs to identify the individual list items, so that it can tell which ones have changed

> In React (JSX):

``` bash
<li key={item.whatever}>
  {item.bar}
</li>
``` 

> In reagent, you add the key as metadata to the Hiccup form. Reagent takes care of setting the key for React then.

``` bash
^{:key (.-whatever item)} [:li (.-bar item)]
``` 

## Managing state in Reagent

``` bash
cp -R ex03 ex04
lein figwheel
``` 

Check `~/projects/study/clj/ex/study_reagent/ex04/src/simpleexample/core.cljs`

``` bash
(def click-count (r/atom 0))

(defn counting-component []
  [:div
   "The atom " [:code "click-count"] " has value: "
   @click-count ". "
   [:input {:type "button" :value "Click me!"
            :on-click #(swap! click-count inc)}]])
``` 

Keep state inside component:

``` bash
(defn timer-component []
  (let [seconds-elapsed (r/atom 0)]
    (fn []
      (js/setTimeout #(swap! seconds-elapsed inc) 1000)
      [:div
       "Seconds Elapsed: " @seconds-elapsed])))
``` 

Note: This component returns a function that does the actual rendering. It is called with the same arguments as the first one.

This allows you to perform some setup of newly created components without resorting to React’s lifecycle events.

By simply passing an atom around you can share state management between components, like this:

👉👉👉👉👉👉👉 DİKKAT: bir atom paslayarak, state management yapıyorsun. React'in kendi lifecycle eventlerini kullanmıyorsun. 👈👈👈👈👈👈👈

Share state between components:

``` bash
(defn atom-input [value]
  [:input {:type "text"
           :value @value
           :on-change #(reset! value (-> % .-target .-value))}])

(defn shared-state []
  (let [val (r/atom "foo")]
    (fn []
      [:div
       [:p "The value is now: " @val]
       [:p "Change it here: " [atom-input val]]])))
``` 

## BMI calculator

``` bash
cp -R ex04 ex05
lein figwheel
``` 

Check `~/projects/study/clj/ex/study_reagent/ex05/src/simpleexample/core.cljs`

### Logs

#### Question: let kullanımı

``` bash
  (let [{:keys [weight height bmi]} (calc-bmi)
``` 

Bu kullanım ne anlama geliyor?

Bir mape bir değer mi atanıyor? Yoksa destructuring mi bu?

``` bash
(def bmi-data (r/atom {:height 180 :weight 80}))

(defn calc-bmi []
  (let [{:keys [height weight bmi] :as data} @bmi-data
``` 

#### html componentlerinde destructuring nasıl çalışıyor?

Paralel bir `lein figwheel` başlatalım.

``` bash
lein figwheel
  ##> Port 3449 is already being used.
``` 

Başka bir porttan nasıl başlatılır?

https://github.com/bhauman/lein-figwheel/wiki/Configuration-Options

``` bash
  :figwheel {:repl false 
             :server-port 3450}
``` 

##### calc-bmi function

``` bash
(defn calc-bmi []
  (let [{:keys [height weight bmi] :as data} @bmi-data
        h (/ height 100)]
    (if (nil? bmi)
      (assoc data :bmi (/ weight (* h h)))
      (assoc data :weight (* bmi h h)))))
``` 

`@bmi-data` içeriği ne?

``` bash
(def bmi-data (atom {:height 180 :weight 80}))
(let [{:keys [height weight bmi] :as data} @bmi-data ]
  (p data))
  ##> {:height 180, :weight 80}
(p @bmi-data)
  ##> {:height 180, :weight 80}
``` 

`:keys` ne yapıyor?

Ref: :keys Syntactic sugar for extracting keys from maps: <url:/Users/mertnuhoglu/projects/study/clj/study_clojure.md#tn=:keys Syntactic sugar for extracting keys from maps:>

`:as` ne işe yarıyor?

Ref: :as Another useful destructuring option allows us to extract some keys while preserving the original map: <url:/Users/mertnuhoglu/projects/study/clj/study_clojure.md#tn=:as Another useful destructuring option allows us to extract some keys while preserving the original map:>

##### bmi-component function

``` bash
(defn bmi-component []
  (let [{:keys [weight height bmi]} (calc-bmi)
        [color diagnose] (cond
                          (< bmi 18.5) ["orange" "underweight"]
                          (< bmi 25) ["inherit" "normal"]
                          (< bmi 30) ["orange" "overweight"]
                          :else ["red" "obese"])]
    [:div
     [:h3 "BMI calculator"]
     [:div
      "Height: " (int height) "cm"
      [slider :height height 100 220]]
     [:div
      "Weight: " (int weight) "kg"
      [slider :weight weight 30 150]]
     [:div
      "BMI: " (int bmi) " "
      [:span {:style {:color color}} diagnose]
      [slider :bmi bmi 10 50]]]))
``` 

Note: `(calc-bmi)` returns the atom value `@bmi-data`. 

Ben ilk başta, `bmi-component`ın argümanlarının html'den geldiğini düşünmüştüm. Ama oradan gelmiyor. Zaten biz içeriden `@bmi-data` atomunu veriyoruz.

Peki bu durumda html'de kullanıcının yaptığı eventlerin sonucu nasıl burayı besliyor?

``` bash
(defn slider [param value min max]
  [:input {:type "range" :value value :min min :max max
           :style {:width "100%"}
           :on-change (fn [e]
                        (swap! bmi-data assoc param (.. e -target -value))
                        (when (not= param :bmi)
                          (swap! bmi-data assoc :bmi nil)))}])
``` 

`assoc` nasıl çalışıyor?

``` bash
song
  ##> {:name "Agnus Dei", :artist "Krzysztof Penderecki", :album "Polish Requiem", :genre "Classical"}
(assoc song :kind "MPEG")
``` 

Peki `param`ın değeri ne?

``` bash
     [:div
      "Weight: " (int weight) "kg"
      [slider :weight weight 30 150]]
``` 

`on-change` kısmı nasıl işliyor?

``` bash
(swap! bmi-data assoc param (.. e -target -value))
->
assoc bmi-data param (.. e -target -value)
param: :weight
->
assoc bmi-data :weight (.. e -target -value)
->
assoc bmi-data :weight e.-target.-value
``` 

`..` fonksiyonunun doku nerede?

Ref: dot dot syntax <url:/Users/mertnuhoglu/projects/study/clj/study_clojure.md#tn=dot dot syntax>

## Todo MVC

Check `~/codes/clojure/reagent/examples/todomvc/project.clj`

### Logs

``` bash
                (add-todo "Rename Cloact to Reagent")
                (add-todo "Add undo demo")
todomvc.core=> @todos
{1 {:id 1, :title "Rename Cloact to Reagent", :done false}, 2 {:id 2, :title "Add undo demo", :done false}}
todomvc.core=> (toggle 1)
{1 {:id 1, :title "Rename Cloact to Reagent", :done true}, 2 {:id 2, :title "Add undo demo", :done false}}
todomvc.core=> (toggle 1)
{1 {:id 1, :title "Rename Cloact to Reagent", :done false}, 2 {:id 2, :title "Add undo demo", :done false}}
``` 

``` bash
(swap! todos update-in [1 :done] not)
(complete-all true)
  ##> {1 {:id 1, :title "Rename Cloact to Reagent", :done true}, 2 {:id 2, :title "Add undo demo", :done true}}
``` 

#### complete-all

Şunun çalışması karmaşık:

``` bash
(defn mmap [m f a] (->> m (f a) (into (empty m))))
(defn complete-all [v] (swap! todos mmap map #(assoc-in % [1 :done] v)))
``` 

##### Bu içerideki `assoc-in` hangi öğeye erişiyor? Nasıl görürüm bunu?

``` bash
(get-in todos [1 :done])
  ##> nil
``` 

Tek tek mapin öğelerini dolaşmalıyım.

opt01: list comprehension olur mu?

``` bash
(for [x @todos] x)
  ##> ([1 {:id 1, :title "Rename Cloact to Reagent", :done false}] [2 {:id 2, :title "Add undo demo", :done false}])
``` 

###### structure içini nasıl görürüm?

R'daki `str` fonksiyonu gibi bir şey olsa.

Introspection

edn formatında olsa mesela.

opt01: pprint

https://clojure.org/guides/repl/data_visualization_at_the_repl

``` bash
user=> (require '[clojure.pprint :as pp])
nil
user=> (pp/pprint (mapv number-summary [5 6 7 12 28 42]))
[{:n 5,
  :proper-divisors #{1},
  :even? false,
  :prime? true,
  :perfect-number? false}
 
``` 

``` bash
(pp/pprint @todos)
  ##> {1 {:id 1, :title "Rename Cloact to Reagent", :done false},
  ##>  2 {:id 2, :title "Add undo demo", :done false}}
``` 

---

Peki list comprehension sonucunu nasıl bir isme bağlayacağım?

``` bash
(def a (for [x @todos] x))
(pp/pprint a)
``` 

`pprint` yerine bir alias nasıl kullanırım?

``` bash
(require '[clojure.pprint :as pp])
(def p pp/pprint)
(p @todos)
(def pp pp/pprint)
(pp @todos)
``` 

En son burada kalmıştım, geri dönelim:

``` bash
(get-in @todos [1 :done])
  ##> false
``` 

Ancak `map` içinde buradan farklı bir şey var.

``` bash
(def t2 (for [x @todos] x))
(pp t2)
  ##> ([1 {:id 1, :title "Rename Cloact to Reagent", :done false}]
  ##>  [2 {:id 2, :title "Add undo demo", :done false}])
(get-in t2 [0])
  ##> nil
(get t2 0)
  ##> nil
``` 

`t2` bir list veya seq olduğundan, index ile erişemiyor olmalıyım.

``` bash
todomvc.core=> (type t2)
clojure.lang.LazySeq
``` 

Seq'in öğelerini tek tek görebilir miyim?

``` bash
(first t2)
  ##> [1 {:id 1, :title "Rename Cloact to Reagent", :done false}]
(next t2)
  ##> ([2 {:id 2, :title "Add undo demo", :done false}])
``` 

##### complete-all mantığı nasıl kolay anlaşılır

``` bash
(defn mmap [m f a] (->> m (f a) (into (empty m))))
(defn complete-all [v] (swap! todos mmap map #(assoc-in % [1 :done] v)))
``` 

Burada bir sürü dolaylandırma (indirection) var. 

Fonksiyonlar arasında bir şekil değiştirme oyunu var. Argümanların sıraları değişip duruyor, bu arada nihai istediğimiz şekle ulaşıyoruz.

``` bash
(swap! todos mmap map #(assoc-in % [1 :done] v))
->
(swap! m mmap f a)
m: todos
f: map
a: assoc-in
->
(mmap m f a)
->
(defn mmap [m f a] (->> m (f a) (into (empty m))))
->
(->> m (f a) (into (empty m)))
->
(f a m)
->
(a x)
x: an element of m
->
([1 :done] x)
``` 

Bu dolaylandırma aslında FP'nin değiştirim (substitution) modelinden kaynaklanıyor. 

#### clear-done

``` bash
(defn mmap [m f a] (->> m (f a) (into (empty m))))
(defn clear-done [] (swap! todos mmap remove #(get-in % [1 :done])))
``` 

``` bash
(defn clear-done [] (swap! todos mmap remove #(get-in % [1 :done])))
->
swap! todos mmap remove #(get-in ..)
->
mmap todos remove #(get-in ..)
mmap [m f a]
->
(->> m (f a))
->
(f a m)
->
(remove #(get-in ..) todos)
->
(get-in x [1 :done])
x: key-value pair in todos
``` 

Note: `remove` works like `map`. It iterates over each element of the input collection `todos`.

#### todo-input function

``` bash
(defn todo-input [{:keys [title on-save on-stop]}]
  (let [val (r/atom title)
        stop #(do (reset! val "")
                  (if on-stop (on-stop)))
        save #(let [v (-> @val str clojure.string/trim)]
                (if-not (empty? v) (on-save v))
                (stop))]
    (fn [{:keys [id class placeholder]}]
      [:input {:type "text" :value @val
               :id id :class class :placeholder placeholder
               :on-blur save
               :on-change #(reset! val (-> % .-target .-value))
               :on-key-down #(case (.-which %)
                               13 (save)
                               27 (stop)
                               nil)}])))
``` 

Kullanımı:

``` bash
           [todo-input {:id "new-todo"
                        :placeholder "What needs to be done?"
                        :on-save add-todo}]]
``` 

Argümanlar:

``` bash
  (let [val (r/atom title)
        stop #(do (reset! val "")
                  (if on-stop (on-stop)))
        save #(let [v (-> @val str clojure.string/trim)]
                (if-not (empty? v) (on-save v))
                (stop))]
``` 

`save` ne yapıyor?

``` bash
        save #(let [v (-> @val str clojure.string/trim)]
                (if-not (empty? v) (on-save v))
                (stop))]
``` 

`todo_input` kendisi bir map argüman alıyor. Fakat döndürdüğü fonksiyon da 3 argüman alıyor. Nasıl oluyor bu?

``` bash
(defn todo-input [{:keys [title on-save on-stop]}]
	...
    (fn [{:keys [id class placeholder]}]
``` 

Ref: Note: This component returns a function that does the actual rendering. It is called with the same arguments as the first one. <url:/Users/mertnuhoglu/projects/study/clj/study_reagent.md#tn=Note: This component returns a function that does the actual rendering. It is called with the same arguments as the first one.>

Muhtemelen, her ikisi de çalışıyor. Yani hangi argümanlar paslanmışsa, yukarıdaki tanımların ikisini de kullanıp içine alıyor.


# Article: React's lifecycle methods

https://reactjs.org/docs/react-component.html#lifecycle-methods

[Lifecycle diagram](http://projects.wojtekmaj.pl/react-lifecycle-methods-diagram/)

Lifecycle methods:

- Mounting
 * constructor()
 * render()
 * componentDidMount()
- Updating
 * render()
 * componentDidUpdate()
- Unmounting
 * componentWillUnmount()

# Article: Creating Reagent Components

https://github.com/reagent-project/reagent/blob/master/doc/CreatingReagentComponents.md

## The Core of a Component

`render` function is the ultimate value of a `component`

`render` takes some data and returns HTML.

It is a pure function with no side effect.

## The Three Ways

Creating a component:

1. `render` function

2. A function returned by `render`

3. A map of functions. One is render, the rest are lifecycle methods.

## Form-1: A Simple Funciton

``` bash
(defn greet
   [name]                    ;; data coming in is a string
   [:div "Hello " name])     ;; returns Hiccup (HTML)
``` 

Actually, renderers return clojure data structures. They are in Hiccup format.

``` bash
[:div {:style {:background "blue"}} "hello " "there"]
``` 

This is processed by hiccup. It produces HTML:

``` bash
<div style="background:blue;">hello there</div>
``` 

### Rookie mistake

Sibling HTML elements:

``` bash
(defn wrong-component
   [name]              
   [[:div "Hello"] [:div name]])     ;; a vec of 2 [:div] 
``` 

This isn't valid hiccup. 

opt01: Wrap them in a parent:

``` bash
(defn right-component
   [name]              
   [:div 
     [:div "Hello"] 
     [:div name]])     ;; [:div] containing two nested [:divs]
``` 

opt02: Return React Fragment using `:<>`

``` bash
(defn right-component
   [name]
   [:<>
     [:div "Hello"]
     [:div name]])
``` 

## Form-2: A Function Returning A Function

Sometimes a component requires:

- some setup
- some local state
- a renderer

`outer` function returns an `inner` renderer:

``` bash
(defn timer-component []
  (let [seconds-elapsed (reagent/atom 0)]     ;; setup, and local state
    (fn []        ;; inner, render function is returned
      (js/setTimeout #(swap! seconds-elapsed inc) 1000)
      [:div "Seconds Elapsed: " @seconds-elapsed])))
``` 

Local state: `seconds-elapsed`

Renderer closes over some state that is initialised by the outer.

### Rookie mistake

Inner parameters should repeat outer parameters.

``` bash
(defn outer 
  [a b c]            ;; <--- parameters
  ;;  ....
  (fn [a b c]        ;; <--- forgetting to repeat them, is a rookie mistake
    [:div
      (str a b c)]))
``` 

Outer is called once per component instance. Renderer will be called many times by reagent, potentially with alternative parameter values.

> unless you repeat the parameters on the renderer it will close over those initial values in outer. As a result, the component renderer will stubbornly only ever render the original parameter values, not the updated ones, which can be baffling for a beginner.

## Form-3: A Class With Life Cycle Methods

Very seldomly used. Ex: using a js library like d3.

Sometimes we need to perform actions at specific moments in a component's lifetime.

[React's lifecycle methods](https://reactjs.org/docs/react-component.html#lifecycle-methods)

Ref: Article: React's lifecycle methods <url:/Users/mertnuhoglu/projects/study/clj/study_reagent.md#tn=Article: React's lifecycle methods>

# Article: Hiccup

## Syntax

https://github.com/weavejester/hiccup/wiki/Syntax

``` bash
[:a {:href "http://github.com"} "GitHub"]
Data Structure -> HTML String
<a href="http://github.com">GitHub</a>
``` 

Clojure data structure:

``` bash
[tag & body]
[tag attributes & body]
``` 

Nested tag vectors are nested HTML elements:

``` bash
[:p "Hello " [:em "World!"]]
``` 

CSS-style sugar

``` bash
[:div {:id "email" :class "selected starred"} "..."]
;; Short form:
[:div#email.selected.starred "..."]
``` 

Expanding seqs:

``` bash
[:div (list "Hello" "World")]
  ->
[:div "Hello" "World"]
``` 

Useful for macros like `for`

``` bash
[:ul (for [x coll] [:li x])]
``` 

## Forms

https://github.com/weavejester/hiccup/wiki/Forms

``` bash
(form-to [:post ""]
         [:fieldset.mds-border {:style "padding-top: 20px"}])

``` 

# Article: clojure-cookbook/7-13_templating-with-hiccup.asciidoc at master · clojure-cookbook/clojure-cookbook

https://github.com/clojure-cookbook/clojure-cookbook/blob/master/07_webapps/7-13_templating-with-hiccup.asciidoc

To escape characters, use `h`

``` bash
(require '[hiccup.core :refer [h]])
(html [:a {:href (h "/post/my<crazy>url")}])
;; -> "<a href=\"/post/my&amp;lt;crazy&amp;gt;url\"></a>"
``` 

# Article: DOM Elements – React

https://reactjs.org/docs/dom-elements.html

All DOM properties, attributes (also event handlers) should be camelCased.

Ex: `tabindex` is `tabIndex` in React.

Exception: `aria-*` and `data-*` attributes. Ex: `aria-label`

## Differences

- `checked`

- `className`

- `htmlFor` instead of `for`

- `onChange`

- `selected`

- `style`

# Article: Programming: Reagent deep dive part 1: Hiccup and ratoms

http://timothypratley.blogspot.com/2017/01/reagent-deep-dive-part-1.html

Ex: Clickable button

``` bash
[:button
 {:on-click
  (fn [e]
    (js/alert "You pressed the button!"))}
 "Do not press"]
``` 

Ex: style attributes as a map:

``` bash
<p style="color: red; background: lightblue;">Such style!</p>
``` 

``` bash
[:p
 {:style {:color "white"
          :background "darkblue"}}
 "Such style!"]
``` 

Ex: shorthand for class and id attributes:

``` bash
[:div#my-id.my-class1.my-class2]
;; ->
[:div
 {:id "my-id"
  :className "my-class1 my-class2"}]
``` 

Note: Some attributes are mapped according to React specs of attributes. Check [React reference](https://reactjs.org/docs/dom-elements.html)

Ex: Form

``` bash
[:div
 [:h3 "Greetings human"]
 [:form
  {:on-submit
   (fn [e]
     (.preventDefault e)
     (js/alert
      (str "You said: " (.. e -target -elements -message -value))))}
  [:label
   "Say something:"
   [:input
    {:name "message"
     :type "text"
     :default-value "Hello"}]]
  [:input {:type "submit"}]]]
``` 

## State and reaction to state change

> Reagent atoms behave very much like a regular Clojure atom. You change them with swap! or reset! and you get their value by deref @my-atom. The special thing about a reagent/atom is that all components that deref it will be re-rendered whenever the value held by the reagent/atom changes

Ex: conditional logic to show some html

``` bash
(let [show? (reagent/atom false)]
  (fn waldo []
    [:div
     (if @show?
       [:div
        [:h3 "You found me!"]
        [:img
         {:src "https://goo.gl/EzvMNp"
          :style {:height "320px"}}]]
       [:div
        [:h3 "Where are you now?"]
        [:img
         {:src "https://i.ytimg.com/vi/HKMlPDwmTYM/maxresdefault.jpg"
          :style {:height "320px"}}]])
     [:button
      {:on-click
       (fn [e]
         (swap! show? not))}
      (if @show? "reset" "search")]]))
``` 

Ex: inc/decrement

``` bash
(def c
  (reagent/atom 1))

(defn counter []
  [:div
   [:div "Current counter value: " @c]
   [:button
    {:disabled (>= @c 4)
     :on-click
     (fn clicked [e]
       (swap! c inc))}
    "inc"]
   [:button
    {:disabled (<= @c 1)
     :on-click
     (fn clicked [e]
       (swap! c dec))}
    "dec"]
   (into [:div] (repeat @c [concentric-circles]))])
``` 

## Reactions

`atom`un bir ileri mertebesi. `atom` bir expression. 

Reaction'ın bağımlı olduğu atom değişince, otomatik fonksiyon çalışır.

Ex: Sorting as a reaction

``` bash
(def rolls (reagent/atom [1 2 3 4]))
(def sorted-rolls (reagent.ratom/reaction (sort @rolls)))

(defn sorted-d20 []
  [:div
   [:button {:on-click (fn [e] (swap! rolls conj (rand-int 20)))} "Roll!"]
   [:p (pr-str @sorted-rolls)]
   [:p (pr-str (reverse @sorted-rolls))]])
``` 

---

http://timothypratley.blogspot.com/2017/01/reagent-deep-dive-part-2-lifecycle-of.html

## Component Forms

`reagent/atom` solves a lot of change problems. But not all of them:

- Self-contained component with its own state. Keep a local atom then.

- Component that calls js functions after they are rendered.

3 forms of components:

1. Function that returns a hiccup vector
2. Function that returns a function
3. Function that returns a react class.

Ex: Form-2

``` bash
(defn a-better-mouse-trap [mouse]
  (let [mice (reagent/atom 1)]
    (fn render-mouse-trap [mouse]
      (into
       [:div
        [:button
         {:on-click
          (fn [e]
            (swap! mice (fn [m] (inc (mod m 4)))))}
         "Catch!"]]
       (repeat @mice mouse)))))

[:div
 [a-better-mouse-trap
  [:img
   {:src "https://www.domyownpestcontrol.com/images/content/mouse.jpg"
    :style {:width "150px" :border "1px solid"}}]]
 [a-better-mouse-trap
  [:img
   {:src "https://avatars1.githubusercontent.com/u/9254615?v=3&s=150"
    :style {:border "1px solid"}}]]]
``` 

`with-let`: takes care of inner function

# Article: Reagent Mysteries. Part 1: Vectors and Sequences

https://presumably.de/reagent-mysteries-part-1-vectors-and-sequences.html

# Article: ClojureScript + Reagent Tutorial with Code Examples

https://purelyfunctional.tv/guide/reagent/

## React DOM Events

``` bash
[:button.btn.btn-primary
  {:on-click (fn [e] (js/console.log "Hello!!"))}
  "Click me!"]
``` 

Note: Event propagation model of DOM is always active. 

Event fires on a node -> Handler on that node called -> Handler on parent node called -> ...

Stop this by calling `(.stopPropagation e)`

Many elements have default behaviors. Ex: A submit button submits the form when clicked. 

Prevent it b y`(.preventDefault e)`

## Sub-components

Embed Reagent components directly into Hiccup:

``` bash
;; hiccup to render deeply nested div
[:div#login-form
  [:form {:method :post :action "/login"}
    [username-field] ;; embed Reagent component (defined elsewhere)
    [password-field a b c] ;; note the arguments
    [:button {:type :submit} "Log in"]]]
``` 

## Embedding JS React components 

Use `:>` function:

``` bash
[:> js/nativeJSComponent {:title "Hello" :otherProp "World"}]
``` 

## String of HTML

If you use this way, all HTML tags will be escaped:

``` bash
(defn blog-post 
  [:div.blog-post
    html])

``` 

Fix:

``` bash
(defn blog-post 
  [:div.blog-post {:dangerouslySetInnerHTML {:__html html}}])
``` 

## Node lists and keys

Add unique keys to array elements in React.

``` bash
(defn student-list [students]
  [:ul
    ;; for returns a seq
    (for [student students]
      [:li {:key (:id student)} ;; stable and unique key
        [:a {:href (:url student)} (:name student)]])])
``` 

## Lazy seqs

`for` lazy seq döndürür. 

``` bash
(defonce student-urls (reagent/atom {}))

(defn student-list [students]
  [:ul
    ;; for returns a lazy seq
    (for [student students]
      [:li {:key (:id student)}
        ;; deref the atom
        [:a {:href (get @student-urls (:id student))}
          (:name student)]])])
``` 

Sorun şu ki, `student-list` fonksiyonu return ettiğinde `student-urls` atomu deref edilmiş olur. Halbuki, `for` loop daha sonra run edilebilir. Bu durumda atom doğru render edilmez. 

Çözüm: `doall` içine paketle.

``` bash
(defonce student-urls (reagent/atom {}))

(defn student-list [students]
  [:ul
    ;; for returns a lazy seq
    (doall
      (for [student students]
        [:li {:key (:id student)}
          ;; deref the atom
          [:a {:href (get @student-urls (:id student))}
            (:name student)]]))])

``` 

## Getting at the DOM node itself

React çoğu zaman DOM'dan seni soyutlar. Fakat bazen DOM nodelarına doğrudan erişmen gerekir. Bunun için React `ref` kullanmalısın. 

``` bash
(defn form-canvas []
  (let [s (reagent/atom {})]
    (fn [] ;; Form-2
      [:div
        [:form {:ref #(swap! s assoc :form %)} ;; save the FORM node
          [:input {:type :text :name "first-name"}]
          [:input {:type :hidden :name "token" :value "Hello!"}]]
        [:canvas {:ref #(swap! s assoc :canvas %)}]]))) ;; save the CANVAS node

``` 

SPA'larda formlar diğer uygulamalar gibi servera post edilmez:

1. Form submit edilince bunu kendin işlemek istersin.

2. Formun etkileşimli olmasını istersin, yanlış yazım gibi durumlarda.

Bu yüzden, komponentin durumunu (state) kendi içinde yönetmek istersin. 

Form-2 component. Arguments: form fields

``` bash
(defn contact-form [first-name last-name email-address message]
``` 

Atom: stores local state. Arguments: initial values

``` bash
(defn contact-form [first-name last-name email-address message]
  (let [s (reagent/atom {:first-name first-name
                         :last-name  last-name
                         :email email-address
                         :message message})]
``` 

Şimdi inner (render) fonksiyonu döneriz. 

Note: Argümanları tekrarlamıyoruz, çünkü tekrarlasaydık formu değiştirmiş olurduk.

``` bash
(defn contact-form [first-name last-name email-address message]
  (let [s (reagent/atom {:first-name first-name
                         :last-name  last-name
                         :email email-address
                         :message message})]
    (fn []
``` 

Default form submission işlemini önlemek için: `preventDefault`

``` bash
    (fn []
      [:form {:on-submit (fn [e]
                           (.preventDefault e)
                           ... ;; do something with the state @s
                           )}
``` 

Input field. İki yönlü data binding yaparız, atom ile.

``` bash
        [:input {:type :text :name :first-name
                 :value (:first-name @s)
                 :on-change (fn [e]
                              (swap! s :first-name (-> e .-target .-value)))}]
``` 

## Reagent Atoms

Clojure atomları gibi. Fazladan bir özelliği var: Atomları deref eden reagent componentlerini takip ederler. Atomun değeri değişince, komponent yeniden render edilir. 

`reset!` atomun mevcut değerini dikkate almadan onu günceller

``` bash
(reset! state 10)
``` 

`swap!` atomun mevcut değerini dikkate alarak onu günceller

``` bash
(swat! state + 10)
``` 

## When will components be re-rendered?

When arguments of a component change, the component will be re-rendered.

``` bash
[:div
  [my-component a]]
``` 

# Article: Reagent Components Reference Sheet

https://purelyfunctional.tv/pftv-download/5677/PDF/23006

/Users/mertnuhoglu/Downloads/Re-frame Components Reference Sheet.pdf

## Form-1 Components

Just a function that returns Hiccup

re-renders when args change or when Reagent Atoms it derefs change

``` bash
(defn green-button [txt]
  [:button.green txt])
``` 

## Form-2 Components

use Reagent Atom to store local state

argument lists should match or it won't re-render when they change

``` bash
(defn counting-button [txt]
  (let [state (reagent/atom 0)]
    (fn [txt]
      [:button.green
        {:on-click #(swap! state inc)}
        (str txt " " @state)])))
``` 

## Hiccup

id and class shorthand: `:div#login-form.row`

embed components directly in [] `[username-field]`

attributes in a map

style attribute is broken out into a map instead of a string

``` bash
[:form {:method :post :action "/login"
				:style {:margin-left "10px"}}
``` 

## Reagent Atoms

usually defined using defonce to allow for reloading

``` bash
(defonce state (reagent/atom [1 2]))
``` 

## Seqs & Lazy seqs

`for` is lazy

we deref inside the `for`

List components should add `:key` attribute that is unique

``` bash
[:ul 
	(doall
    (for [student students]
      [:li {:key (:id student)}
				[:a {:href (get @student-urls (:id student))}
					(:name student)]]))])
``` 

# Article: The Future of JavaScript MVC Frameworks

https://swannodette.github.io/2013/12/17/the-future-of-javascript-mvcs

JS is very fast now. Collection performance within 2.5 x of JVM.

# Article: re-frame/SubscriptionFlow.md at master · day8/re-frame

https://github.com/Day8/re-frame/blob/master/docs/SubscriptionFlow.md

# Article: re-frame interactive demo

https://blog.klipse.tech/clojure/2019/02/17/reframe-tutorial.html

# Article: re-frame README.md

https://github.com/day8/re-frame

## It Leverages Data

Lisps are homoiconic.

It means: you program in a Lisp by making Lisp data structures. So, you aree programming in data. Programs are data.

re-frame has data oriented design. Events, effects, DOM is data. Functions that transform data are registered via data. Interceptors (data) are preferred over middleware (higher order functions)

## It is a 6-domina cascade

Six dominoes:

1. Event dispatch
2. Event handling
3. Effect handling
4. Query
5. View
6. DOM

1st Domino: Event dispatch

An event is sent.

2nd Domino: Event Handling

Event handlers compute declarative description of side effects, represented as a data structure.

3rd Domino: Effect Handling

This changes the world. Side effects are performed.

It also changes the application state. This triggers the next dominoes.

`v = f(s)`

v: view

s: app state

As `s` changes, `f` reruns to compute new `v` over time.

4th Domino: Query

``` bash
[dispatch] event -> [event handler] effects -> [effect handler] state -> [app state] state -> [query] materialised view -> [view] hiccup -> [reagent]

[effect handler] -> email, database (side effects)

[view]: view functions (reagent components)
``` 

## Managing mutation

### Ex: Code for Domino 1

``` bash
(defn delete-button 
  [item-id]
  [:div.garbage-bin 
     :on-click #(re-frame.core/dispatch [:delete-item item-id])])
``` 

`on-click` is an event emitter.

An `event` is a vector such as: `[:delete-item 2486]`

The first element `:delete-item` is the kind of the event.

Events express intent in a domain specific way.

### Ex: Code for Domino 2

An event handler `h` is called to compute the effect of the event `[:delete-item 2486]`

``` bash
(re-frame.core/reg-event-fx   ;; a part of the re-frame API
  :delete-item                ;; the kind of event
  h)                          ;; the handler function for this kind of event
``` 

On app startup, `h` is registered as handler for `:delete-item` events.

`h` takes two arguments:

1. a `coeffects` map that contains the current state of the app (including app state)

2. the `event`

`h` returns a map of `effects`

``` bash
(defn h                               ;; maybe choose a better name like `delete-item`
 [coeffects event]                    ;; `coeffects` holds the current state of the world.  
 (let [item-id (second event)         ;; extract id from event vector
       db      (:db coeffects)]       ;; extract the current application state
   {:db  (dissoc-in db [:items item-id])})) ;; effect is "change app state to ..."
``` 

More idiomatic:

``` bash
(defn h 
  [{:keys [db]} [_ item-id]]    ;; <--- new: obtain db and id directly
  {:db  (dissoc-in db [:items item-id])}) ;; same as before
``` 

### Ex: Code for Domino 3

Effect handler performs the effects returned by `h`.

`h` returns:

``` bash
{:db  (dissoc-in db [:items 2486])}   ;; db is a map of some structure
``` 

This means updating the app state.

### Ex: Code for Domino 4

A query over app state is called. The query extracts some data from app state and computes a materialised view.

``` bash
(defn query-fn
  [db v]         ;; db is current app state, v the query vector
  (:items db))   ;; not much of a materialised view
``` 

`query-fn` must be registered on program startup:

``` bash
(re-frame.core/reg-sub  ;; part of the re-frame API
   :query-items         ;; query id  
   query-fn)            ;; query fn
``` 

### Ex: Code for Domino 5

``` bash
(defn items-view
  []
  (let [items  (subscribe [:query-items])]  ;; source items from app state
    [:div (map item-render @items)]))   ;; assume item-render already written
``` 

### Ex: Code for Domino 6

This is done by Reagent.

# Article: Initial Code Walk-through

https://github.com/day8/re-frame/blob/master/docs/CodeWalkthrough.md

Follow `~/codes/clojure/re-frame/examples/simple/README.md`

Check `~/codes/clojure/re-frame/examples/simple/src/simple/core.cljs`

# Article: Todomvc reframe

https://github.com/day8/re-frame/tree/master/examples/todomvc

``` bash
cd /Users/mertnuhoglu/codes/clojure/re-frame/examples/todomvc
lein do clean, shadow watch client
open http://localhost:8280/example.html
``` 

Check `~/codes/clojure/re-frame/examples/todomvc/project.clj`

Compare with classical reagent todomvc: `~/codes/clojure/reagent/examples/todomvc/src/todomvc/core.cljs`

# Article: Coming to Re-Frame from Redux · Clicking into Clojure

https://www.joshuahorwitz.net/posts/reduxtoreframe/#domino-5-view

Re-frame is very similar to Redux.

Domino 1 - Event Dispatch: Exactly the same

Domino 2 - Event Handling:

Action in redux. It contains a `type` and a `payload`

Event handler in reframe: 

``` bash
[:update-name "Josh"]
``` 

Domino 3 - Effect Handling

Reducers in redux.

They receive all actions and switch on the actions types. 

In both: changes are handled and app state is updated.

Domino 4 - Query

Redux: Mapping state to props `mapStateToProps`

Both: view is a function of the state

Reframe: `subscriptions` listen to state and update the view when the data it is subscribed to is updated.

Domino 5 - View

JSX in Redux.

Hiccup in Reframe.

# Article: Re-frame Tutorial with Code Examples

https://purelyfunctional.tv/guide/re-frame-building-blocks/

## Event queue

Reagent provides two things:

1. Components with functions

2. Store state with reagent atoms

Reframe provides: Event queue

Adding an event to the queue: `re-frame.core/dispatch`

``` bash
(rf/dispatch [:buy 32323])
``` 

Events are vectors. First element is the name of the event. 

How does naming events clears up our components?

Ex: Reagent component for a buy button:

``` bash
(defn buy-button [item-id]
  [:button
   {:on-click (fn [e]
                (.preventDefault e)
                (ajax/post (str "http://url.com/product/" item-id "/purchase")
                  {:on-success #(swap! app-state assoc :shopping-cart %)
                   :on-error #(swap! app-state update :errors conj)}))}
   "Buy"])
``` 

It does:

1. Builds a product's purchase URL
2. Stores the item in the app state
3. Handles request error

Reframe:

``` bash
(defn buy-button [item-id]
  [:button
    {:on-click (fn [e]
                 (.preventDefault e)
                 (rf/dispatch [:buy item-id]))}
    "Buy"])
``` 

## Handling events

Think of it like as interpreting the intent of the user. An event might be expressed as a click, a tap, a swipe. But we interpret the intent of the user as "buying an item".

Define event handler: `re-frame.core/reg-event-fx`

``` bash
(rf/reg-event-fx ;; register an event handler
  :buy           ;; for events with this name
  (fn [cofx [_ item-id]] ;; get the co-effects and destructure the event
    {:http-xhrio {:uri (str "http://url.com/product/" item-id "/purchase")
                  :method :post
                  :timeout 10000
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success [:added-cart]
                  :on-failure [:notified-error]}
     :db (update-in (:db cofx) [:cart :items] conj {:item item-id})}))
``` 

The function returns a map with two keys: `:http-xhrio` and `:db`. These are effects. Each effect is a key/value pair in the map the event handler returns.

## Effects

Example effects: ajax requests, storing data, outputting to console.

Defining effect handler: `re-frame.core/reg-fx`

``` bash
(rf/reg-fx       ;; register an event handler
  :http-xhrio    ;; the name is the key in the effects map
  (fn [request]  ;; we get the value (a map) we stored at that key
    (case (:method request :get)
      :post (ajax/post (:url request))
      ...)))
``` 

## Database events

Since it is very common, there's a shortcut:

``` bash
(rf/reg-event-db ;; notice it's a db event
  :save-name
  (fn [db [_ first-name last-name]]
    (update db :current-user assoc :first-name first-name :last-name last-name)))
``` 

## Keeping our Event Handlers pure

Every event handler gets a coeffects map. We've extracted `db` out of that. We can put more stuff in there. Ex: time, local cache. 

Use three argument version of `reg-event-fx`

``` bash
(rf/reg-event-fx
  :buy
  [(rf/inject-cofx :now)] ;; add the co-effects we need here.
  (fn [cofx [_ item-id]]
    {:http-xhrio {:uri (str "http://url.com/product/" item-id "/purchase")
                  :params {:time (:now cofx)} ;; use the time
                  :method :post
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success [:added-cart]
                  :on-failure [:notified-error]}
     :db (update-in (:db cofx) [:cart :items] conj {:item item-id})}))
``` 

## Handling Coeffects

Defining a coeffect handler for `:now`

``` bash
(rf/reg-cofx
  :now
  (fn [cofx _data] ;; _data unused
    (assoc cofx :now (js/Date.))))
``` 

Ex:

Add temporary id to an item added to the cart:

``` bash
(rf/reg-event-fx
  :buy
  [(rf/inject-cofx :temp-id)] ;; we'll define this later
  (fn [cofx [_ item-id]] ;; get the co-effects and destructure the event
    {:http-xhrio {:uri (str "http://url.com/product/" item-id "/purchase")
                  :method :post
                  :timeout 10000
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success [:added-cart (:temp-id cofx)]       ;; start using temp-id
                  :on-failure [:notified-error (:temp-id cofx)]}  ;; here, too
     :db (update-in (:db cofx) [:cart :items] conj {:item item-id
                                                    :temp-id (:temp-id cofx)})}))
``` 

Define this coeffect handler:

``` bash
(defonce last-temp-id (atom 0))

(rf/reg-cofx
  :temp-id ;; same name
  (fn [cofx _]
    (assoc cofx :temp-id (swap! last-temp-id inc))))
``` 

## Summary: reg functions

``` bash
(defn buy-button [item-id]
  [:button
    {:on-click (fn [e]
                 (.preventDefault e)
                 (rf/dispatch [:buy item-id]))}

(rf/reg-event-fx ;; register an event handler
  :buy           ;; for events with this name
  (fn [cofx [_ item-id]] ;; get the co-effects and destructure the event
    {:http-xhrio {:uri (str "http://url.com/product/" item-id "/purchase") ...
     :db (update-in (:db cofx) [:cart :items] conj {:item item-id})}))

(rf/reg-fx       ;; register an event handler
  :http-xhrio    ;; the name is the key in the effects map
  (fn [request]  ;; we get the value (a map) we stored at that key
    (case (:method request :get)
      :post (ajax/post (:url request))
      ...)))

(rf/reg-event-db ;; notice it's a db event
  :save-name
  (fn [db [_ first-name last-name]]
    (update db :current-user assoc :first-name first-name :last-name last-name)))

(rf/reg-event-fx
  :buy
  [(rf/inject-cofx :now)] ;; add the co-effects we need here.
  (fn [cofx [_ item-id]]
    {:http-xhrio {:uri (str "http://url.com/product/" item-id "/purchase")
                  :params {:time (:now cofx)} ;; use the time ...
     :db (update-in (:db cofx) [:cart :items] conj {:item item-id})}))

(rf/reg-cofx
  :now
  (fn [cofx _data] ;; _data unused
    (assoc cofx :now (js/Date.))))
``` 


## Cross-cutting concerns

Event handlers work actually with Interceptors underneath. 

`reg-event-fx` is defined in terms of Interceptors.

Interceptors: a way of creating a data pipeline.

![Interceptors](/Users/mertnuhoglu/Pictures/screenshots/20200303092642.png)

Each function takes a context and returns a context.

A context is just data.

Each  interceptor is a pair of one before and one after function.

Event handler function is converted into a before Interceptor and a no-op after function.

Adding a co-effect: it is a before interceptor that adds to the context.

Format of context map:

``` bash
{;; all of the values from the co-effects
 :coeffects {:event [:my-event "hello"]
             :db    {...}}

 ;; return value from your handler
 :effects   {:db        {...}
             :dispatch  [:event 1 2 3]}

 ;; the remaining interceptor functions to run
 :queue     (...)
 ;; the interceptor functions already run
 :stack     (...)
}
``` 

## Atoms

### The structure of database

Database is a map. 

### Callback inferno

First problem: the structure of the database is defined all over the place, a little bit in each component.

There are two kinds of db access: writes and reads. Event handlers for the writes. Subscriptions for the reads. 

### Getting data from the database reactively

Reagent components re-render when ratoms they deref are updated. This can slow down the application. We don't want that. We want to specify exactly what data the component needs only re-render if that data changes.

Subscriptions are parts of the data from the database.

Ex: A component that lists shopping cart items.

Subscription to get the items:

``` bash
(rf/reg-sub
  :cart-items
  (fn [db _]
    (:items db)))
``` 

Now, subscribe to `:cart-items` and deref it.

``` bash
(defn cart []
  (let [items (rf/subscribe [:cart-items])]
    (fn []
      [:ul
        (doall
          (for [item @items]
            [:li {:key (:name item)} (:name item)]))])))
``` 

Note: The component doesn't know the details of the database structure anymore.

### Reactive de-duplication

Ex: showing the count of items in the shopping cart

No subscription:

``` bash
(defn cart-icon []
  (let [items (rf/subscribe [:cart-items])]
    (fn []
      [:span [:img {:src "/cart.png"}] " " (count @items)])))
``` 

With subscription:

``` bash
(rf/reg-sub
  :cart-count
  (fn [_]
    (rf/subscribe [:cart-items]))
  (fn [items]
    (count items)))

(defn cart-icon []
  (let [count (rf/subscribe [:cart-count])]
    (fn []
      [:span [:img {:src "/cart.png"}] " " @count])))
``` 

Note, the result of the first function will be the input to the second function here:

``` bash
  (fn [_]
    (rf/subscribe [:cart-items]))
  (fn [items]
    (count items)))
``` 

The argument to the first function is the current value of the subscription. 

Ex: Recombine two reactive values (like subscription or ratoms)

``` bash
(rf/reg-sub
  :cart-filter-settings
  (fn [db _]
    (:filter-settings (:cart db))))

(rf/reg-sub
  :cart-items-filtered
  (fn [_]
    ;; return a vector of subscriptions
    [(rf/subscribe [:cart-items])
     (rf/subscribe [:cart-filter-settings])])
  (fn [[items settings]] ;; now this will be a vector of the current values
    (apply-filters settings items))) ;; actually do the filtering
``` 

## Conclusions

Reframe: captures UI actions (events) and converts them into changes in application state and browser UI (effects)

# Article: Where to Store State in Re-frame?

https://purelyfunctional.tv/guide/state-in-re-frame/

## Introduction

4 places to store state:

1. Server API

2. Reframe database

3. Component local

4. Reagent atoms

All frontend state should be in one of those four places.

## The constraints of your state

### Locality (Where is it read and written?)

Keep state as local as possible.

### Update frequency

If changes are too fast (such as window resizing), keep it out of reframe database and use reagent atoms.

### Transience (How long to keep it?)

Store state as transiently as possible. 

## Caching data

Subscriptions and ratoms are ok. But how to react to data stored on the server?

Cache the state on the server in the database or ratom. 

Big question: When to update the cache?

Example: The user clicks delete to remove an item from the cart.

### Optimistic update

If I delete the item immediately when the user clicks the delete button, this is optimistic update.

I assume that server will successfully handle the delete. 

### Pessimistic update

You can send the event to the server and wait for the response.

Meanwhile, disable the delete button. 

## Tools for storing state

### Server or Third Party API

Use HTTP Effects handler. 

### Component-local state

Use a ratom inside a Form 2 or 3 component.

Reading: `deref`

Writing: `swap!` and `reset!`

### Reframe Database

Writing: return a `:db` effect from an event (or by using `DB Event`)

Reading: use `:db` coeffect in an event. It is implicitly added to all event handlers.

### Reagent Atoms

Reading:

opt01: deref in components or in reactive subscriptions.

opt02: read in events by creating a coeffect

For highly volatile state: use ratoms directly in components. Avoid reframe events and effects.

Ex: highly volatile window resizing:

``` bash
(defonce window-size
  (let [a (r/atom {:width  (.-innerWidth  js/window)
                   :height (.-innerHeight js/window)})]
    (.addEventListener js/window “resize”
      (fn [] (reset! a {:width  (.-innerWidth  js/window)
                        :height (.-innerHeight js/window)})))
    a))
``` 

You can access width and height in subscriptions and components by derefing `window-size`

### Browser LocalStorage

It is good for caching.

# Article: How to use React Lifecycle Methods in Re-frame

https://purelyfunctional.tv/guide/re-frame-lifecycle/

# Article: Re-frame Database Best Practices + Code Examples

https://purelyfunctional.tv/guide/database-structure-in-re-frame/

## Introduction

Reframe database: global store of app state.

It is a single ratom. But you can't access it directly.

Writes: through event handlers

Reads: through subscription

Why no direct access?

Database structure can evolve over time. Indirect access separates app code from the structure of the database. 

Indirection layer: event handlers (writing) and subscriptions (reading)

## Naming recommendations

### Events

Ex: clickin "Add to cart" button.

What is user's intent? 

opt: 

`:click-add-to-cart`: tied to the UI clicking

`:store-item-in-cart-db`: tied to the effect

`:add-to-cart`: user's intent only

Ex: send AJAX post when the item is added to the cart

This is an effect returned from the event handler. Reframe handles it by firing a new event for a success and one for a failure. How to name these events?

opt:

`:cache-cart-from-server`

`:cart-ajax-response-success`

`:confirm-add-to-cart`: intent from the server

Summary: Events to be named in terms of domain concepts, not technical implementations.

### Subscriptions

Name the value they return.

### Other considerations

Good source: Command-Query Responsibility Separation (CQRS)

### Naming summary

Events: Capture the intent

Subscriptions: Describe the data

## Structure Recommendations

Use a map as database at the top level.

First event of the app should be to initialize the database. Ex: `:page-load`

Nested keys for subdivision. Ex: by `:user` or `:products`

## Database Access

### Use `assoc-in` and `update-in`

``` bash
(assoc-in db [:user :address :zip] "72773")
;;           ^ key path            ^ value

(update-in db [:user :score] inc)
;;            ^ key path     ^ function
``` 

### Handle empty case

Use `fnil`

``` bash
(update-in db [:user :score] (fnil inc  0))
;;            ^ key path           ^ fn ^ default
``` 

``` bash
(update-in {} [:user :game :score] (fnil inc 0))

  ;; => {:user {:game {:score 1}}}
``` 

### Get deeply nested stuff

Use `get-in` in subscriptions.

``` bash
(rf/reg-sub
  :user-score
  (fn [db]
    (get-in db [:user :game :score])))
``` 

``` bash
(get-in db [:user :game :score] 0)
;;                              ^ default value
``` 

### Keep events and subscriptions together

Reframe template contradicts with this recommendation. It has separate namespaces for events and subscriptions.

### Calculate stuff in subscriptions

Ex: Count of items in the cart. Where should we store the count?

Calculate it in a subscription. 

``` bash
(rf/reg-sub
  :shopping-cart-items
  (fn [db]
    (get-in db [:cart :items])))
``` 

Now, we can chain subscriptions:

``` bash
(rf/reg-sub
  :shopping-cart-count
  (fn []
    (rf/subscribe [:shopping-cart-items]))
  (fn [cart-items]
    (count cart-items)))
``` 

This subscription has three arguments. First: name. Second: subscription that returns the items. Third: calculating the count  of these items.

### Recombining Subscriptions

``` bash
(rf/reg-sub
  :discounted-cart-items
  (fn []
    [(rf/subscribe :shopping-cart-items)
     (rf/subscribe :coupon)])
  (fn [[items coupon]]
    (mapv #(apply-discount % (:discount coupon)) items)))
``` 

Note: we don't care whether input subscriptions get data from database or not.

## Indexed Entities Pattern

### Indexed entities

Ex: database structure of product listings and my cart:

``` bash
{:products [{:id 123
             :name "Bag of holding"
             :price 40
             :description "..."}
            ...}]
 :cart [{:quantity 2
         :item {:id 123
                :name "Bag of holding"
                :price 40
                :description "..."}
       ...]
 ...}
``` 

Assume that an update in the server changes the description of the item. 

Problems:

1. We need to update it in two places.

2. Products are in vectors. We need to iterate all elements to find `:id` matches.

Solution:

Normalize the data using Indexed Entity pattern.

Goal:

``` bash
{:products {123 {:id 123
                 :name "Bag of holding"
                 :price 40
                 :description "..."}
           ...}
 :cart [{:quantity 2 :item 123} ...]
 ...}
``` 

Update product event:

``` bash
(rf/reg-event-db
  :update-product
  (fn [db [_ product-info]]
    (assoc-in db [:products (:id product-info)] product-info)))
``` 

Read product:

``` bash
(rf/reg-sub
  :product-info
  (fn [db [_ product-id]]
    (get-in db [:products product-id])))
``` 

### Dealing with order

New problem: How to sort products always in a consistent way?

Use a vector whose purpose is ordering: 

``` bash
{:products {123 {:id 123
                 :name "Bag of holding"
                 :price 40
                 :description "..."}
           ...}
 :product-list [123 ...]
 :cart [{:quantity 2 :item 123} ...]
 ...}
``` 

Subscription: to return all products:

``` bash
(rf/reg-sub
  :product-list
  (fn [db]
    (mapv (fn [id] (get-in db [:products id])) (:product-list db))))
``` 

This is like a join in relational databases.

### Nesting the refactoring

Another problem: How to update quantity of an item in the cart? Same problem as above. They are inside a vector. We need to iterate by matching id. 

Same solution. Make an index and add an order vector for sorting:

``` bash
{:products {123 {:id 123
                 :name "Bag of holding"
                 :price 40
                 :description "..."}
           ...}
 :product-list [123 ...]
 :cart {:quantities {123 2 ...}
        :order [123 ...]}
 ...}
``` 

Adding an item:

``` bash
(rf/reg-event-db
 :add-to-cart
 (fn [db [_ product-id quantity]]
   ;; check if it's already in the cart
   (if (nil? (get-in db [:cart :quantities product-id]))
     ;; not in the cart, add to quantities and order
     (-> db
       (assoc-in [:cart :quantities product-id] quantity)
       (update-in [:cart :order] (fnil conj []) product-id))
     ;; in the cart, add to existing quantity
     (update-in db [:cart :quantities product-id] + quantity))))
``` 

Read: cart items (do another join)

``` bash
(rf/reg-sub
  :cart-items
  (fn [db]
    (mapv (fn [id]
            {:quantity (get-in db [:cart :quantities id])
             :item (get-in db [:products id])})
      (get-in db [:cart :order]))))
``` 

# Article: Re-frame visually explained

https://purelyfunctional.tv/guide/re-frame-a-visual-explanation/

![](/Users/mertnuhoglu/Pictures/screenshots/20200303134042.png)

## Structure

All arrows leaving the Event Handler are Effects.

All arrows entering the Event Handler are Events.

Arrows leaving DOM: Events

Arrows entering DOM: Subscriptions

Arrows leaving DB: Subscriptions

Arrows entering DB: DB Effects

# Article: Optimistic Update in Re-frame - PurelyFunctional.tv

https://purelyfunctional.tv/guide/optimistic-update-in-re-frame/

``` bash
(rf/reg-event-fx :like
  (fn [cofx [_ post-id]]
    {:http-xhrio {:url (str "http://server.com/" post-id "/like")
                  :method :post
                  :on-success [:confirm-like post-id]
                  :on-failure [:show-error "Could not like."]}}))

(rf/reg-event-db :confirm-like
  (fn [db [_ post-id]]
    (assoc-in db [:likes post-id] true)))

(rf/reg-event-db :show-error
  (fn [db [_ msg]]
    (assoc db :last-error msg)))
``` 

Problems:

Message to server and back is too slow. The user wants instant feedback.

## Pessimistic update

``` bash
{:status :stable
 :value  true}

 {:status    :loading
 :value      true
 :next-value false}
``` 

When status is `:loading`, we show a loading spinner.

Three operations on this map: 

`begin-load`: it puts `:next-value`

`succeed-load`: it sets the status to stable and updates the value

`fail-load`: it sets the status to stable and leaves the value

``` bash
(defn begin-load [state next-value]
  (cond
    (nil? state) ;; consider nil a stable value
    {:status :loading
     :value nil
     :next-value next-value}

    (= :stable (:status state))
    {:status :loading
     :value (:value state)
     :next-value next-value}

    (= :loading (:status state))
    (assoc state :next-value next-value)))

(defn succeed-load [state]
  (cond
    (nil? state)
    state

    (= :stable (:status state))
    state

    (= :loading (:status state))
    {:status :stable
     :value (:next-value state)}))

(defn fail-load [state]
  (cond
    (nil? state)
    state

    (= :stable (:status state))
    state

    (= :loading (:status state))
    {:status :stable
     :value (:value state)}))

(rf/reg-event-db :succeed-load
  (fn [db [_ path]]
    (update-in db path succeed-load)))

(rf/reg-event-db :fail-load
  (fn [db [_ path msg]]
    (-> db
      (update-in path fail-load)
      (assoc :last-error msg))))
``` 

Now, we define the event handlers:

``` bash
(rf/reg-event-fx :like
  (fn [cofx [_ post-id]]
    ;; let's start the ajax request
    {:http-xhrio {:url (str "http://server.com/" post-id "/like")
                  :method :post
                  ;; we can call our new Events
                  :on-success [:succeed-load [:likes post-id]]
                  :on-failure [:fail-load    [:likes post-id] "Failed to like post."]}
     ;; and let's begin the load with next value true
     :db (update-in (:db cofx) [:likes post-id] begin-load true)}))
``` 










