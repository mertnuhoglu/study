
## rxjs ex 01

··  &lt;body&gt; <br>
··  &lt;div id="{{c1::app}}"&gt;&lt;/div&gt; <br>
··  &lt;{{c2::script}} type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/rxjs/5.5.6/Rx.min.js"&gt;&lt;/{{c2::script}}&gt; <br>
··  &lt;{{c3::script}}&gt; <br>
····  Rx.Observable.{{c4::timer}}(0, 1000) <br>
······  .{{c5::map}}(i =&gt; \`Seconds ${i}\`) <br>
······  .{{c6::subscribe}}(text =&gt; { <br>
········  const container = document.{{c7::querySelector}}('#app'); <br>
········  container.textContent = text; <br>
······  }) <br>
··  &lt;/script&gt; <br>
··  &lt;/body&gt; <br>

%

%

clozeq

---

## two parts of rxjs app

This code consists of two parts: 

1. {{c1::Logic}}

······  Rx.Observable.timer(0, 1000) <br>
········  .map(i =&gt; \`Seconds ${i}\`) <br>

2. {{c2::Effects}}

········  .subscribe(text =&gt; { <br>
··········  const container = document.querySelector('#app'); <br>
··········  container.textContent = text; <br>
········  }) <br>


%

%

clozeq

---

## effects vs logic

Effects part is {{c1::imperative}}. 

Logic part is {{c2::functional}}. 

We need to separate these two parts. 

Effects part needs to be handled by the {{c3::framework}}. 

Application should consist only of the logic part. 

%

%

clozeq

---

## rxjs ex02: basic separation of logic and effects

··  function {{c1::main}}() { <br>
····  return Rx.Observable.timer(0, 1000) <br>
······  .map(i =&gt; \`Seconds ${i}\`); <br>
··  } <br>

··  function {{c2::DOMEffect}}(text$) { <br>
····  text$.subscribe(text =&gt; { <br>
······  const container = document.querySelector('#app'); <br>
······  container.textContent = text; <br>
····  }); <br>
··  } <br>

··  {{c3::DOMEffect}}({{c4::main}}()); <br>

%

%

clozeq

---

## rxjs ex03: multiple effects

··  function DOMEffect(text$) { <br>
····  text$.subscribe(text =&gt; { <br>
······  const container = document.querySelector('#app'); <br>
······  container.textContent = text; <br>
····  }); <br>
··  } <br>

··  function {{c1::consoleLogEffect}}(msg$) { <br>
····  msg$.subscribe(msg =&gt; console.log(msg)); <br>
··  } <br>

··  const {{c2::sink}} = main(); <br>
··  consoleLogEffect({{c3::sink}}); <br>
··  DOMEffect(sink); <br>

%

%

clozeq

---

## rxjs ex04: multiple effects. different streams for each effect.

··  const {{c2::sink}} = main(); <br>
··  consoleLogEffect({{c3::sink}}); <br>
··  DOMEffect(sink); <br>

··  ---&gt;&gt;&gt; <br>

··  const sink = main(); <br>
··  consoleLogEffect({{c1::sink.Log}}); <br>
··  DOMEffect({{c2::sink.DOM}}); <br>

··  function main() { <br>
····  return { <br>
······  {{c3::DOM}}: Rx.Observable.timer(0, 1000) <br>
········  .map(i =&gt; \`Seconds ${i}\`), <br>
······  Log: Rx.Observable.timer(0, 2000) <br>
········  .map(i =&gt; 2*i), <br>
····  }; <br>
··  } <br>

%

%

clozeq

---

## rxjs ex05: multiple effects but they are hardcoded

··  const {{c2::sink}} = main(); <br>
··  consoleLogEffect({{c3::sink}}); <br>
··  DOMEffect(sink); <br>

··  ---&gt;&gt;&gt; <br>

··  function run(main, effects) { <br>
····  const sinks = main(); <br>
····  Object.keys({{c1::effects}}).forEach(key =&gt; { <br>
······  effects[key&#093;({{c2::sinks[key]}}) <br>
····  }) <br>
··  } <br>
··  const effects = { <br>
····  DOM: DOMEffect, <br>
····  Log: consoleLogEffect, <br>
··  } <br>
··  run({{c3::main, effects}}); <br>

%

%

clozeq

---

## rxjs ex06: rename effects 

··  const effects = { <br>
····  DOM: DOMEffect, <br>
····  Log: consoleLogEffect, <br>
··  } <br>

··  ---&gt;&gt;&gt; <br>

··  const {{c1::drivers}} = { <br>
····  DOM: {{c2::DOMDriver}}, <br>
····  Log: consoleLogDriver, <br>
··  } <br>

%

%

clozeq

---

## rxjs ex07: input effect: using click events from DOM

··  function main(DOMSource) { <br>
····  const click$ = {{c1::DOMSource}}; <br>
····  return { <br>
······  DOM: {{c2::click$}} <br>
········  .{{c3::startWith}}(null) <br>
········  .{{c4::switchMap}}({{c5::()}} =&gt; <br>
··········  Rx.Observable.timer(0, 1000) <br>
············  .map(i =&gt; \`Seconds ${i}\`) <br>
········  ), <br>

%

%

clozeq

---

## rxjs ex07: input effect: getting click events in DOM

··  function DOMDriver(text$) { <br>
····  text$.subscribe(text =&gt; { <br>
······  const container = document.querySelector('#app'); <br>
······  container.textContent = text; <br>
····  }); <br>
····  const DOMSource = Rx.Observable.{{c1::fromEvent}}({{c2::document}}, 'click'); <br>
····  return {{c3::DOMSource}}; <br>
··  } <br>

%

%

clozeq

---

## rxjs ex07: input effect: configuring sinks-sources cyclically

But there is a cyclic dependency problem here:

··  const sinks = main({{c1::DOMSource}}); <br>
··  const DOMSource = drivers.DOM({{c2::sinks.DOM}}); <br>

---&gt;&gt;&gt;

··  const {{c3::proxyDOMSource}} = new Rx.Subject(); <br>
··  const sinks = main(proxyDOMSource); <br>
··  const DOMSource = drivers.DOM(sinks.DOM); <br>
··  DOMSource.{{c4::subscribe}}(click =&gt; proxyDOMSource.{{c5::next}}(click)); <br>

%

%

clozeq

---

## rxjs ex08: parameterize input source arguments to main() function


··  function main({{c1::DOMSource}}) { <br>

··  ---&gt;&gt;&gt; <br>

··  function main({{c2::sources}}) { <br>
····  const click$ = {{c3::sources.DOM}}; <br>

%

%

clozeq

---

## rxjs ex08: parameterize input source arguments to main() function. run is generic

move run into cyclejs framework

··  function run(main, drivers) { <br>
····  const proxySources = {} <br>
····  Object.keys(drivers).forEach(key =&gt; { <br>
······  proxySources[key] = new Rx.Subject(); <br>
····  }) <br>
····  const sinks = main(proxySources); <br>
····  Object.keys(drivers).forEach(key =&gt; { <br>
······  const source = drivers[key&#093;(sinks[key]); <br>
······  source.subscribe(x =&gt; proxySources[key].next(x)); <br>
····  }) <br>
··  } <br>

---&gt;&gt;&gt;

··  &lt;script src="https://rawgit.com/cyclejs/{{c1::cycle-core}}/v6.0.0/dist/cycle.js"&gt;&lt;/script&gt; <br>
··  {{c2::Cycle.run}}(main, drivers); <br>

%

%

clozeq

---

## rxjs ex09: return HTML element stream instead of text stream in DOM driver


····  .flatMapLatest(() =&gt; <br>
······  Rx.Observable.timer(0, 1000) <br>
········  .map(i =&gt; \`Seconds ${i}\`) <br>
····  ), <br>

····  ---&gt;&gt;&gt; <br>

····  .flatMapLatest(() =&gt; <br>
······  Rx.Observable.timer(0, 1000) <br>
········  .map(i =&gt; { <br>
············  return { <br>
··············  {{c1::tagName}}: 'H1', <br>
··············  {{c2::children}}: [ <br>
················  \`Seconds ${i}\` <br>
··············  ] <br>
············  } <br>
··········  } <br>
········  ) <br>
····  ), <br>

%

%

clozeq

---

## rxjs ex09: return HTML element stream instead of text stream in DOM driver. createElement()

··  text$.subscribe(text =&gt; { <br>
····  const container = document.querySelector('#app'); <br>
····  container.textContent = text; <br>
··  }); <br>

··  ---&gt;&gt;&gt; <br>

··  function createElement(obj) { <br>
····  const element = document.{{c1::createElement}}(obj.tagName); <br>
····  element.innerHTML = obj.{{c2::children}}[0]; <br>
····  return element; <br>
··  } <br>
··  obj$.subscribe(obj =&gt; { <br>
····  const container = document.querySelector('#app'); <br>
····  container.{{c3::innerHTML}} = ''; <br>
····  const element = {{c4::createElement}}(obj); <br>
····  container.{{c5::appendChild}}(element); <br>
··  }); <br>

%

%

clozeq

---

## rxjs ex10: return HTML stream. return H1 and span

········  .map(i =&gt; { <br>
············  return { <br>
··············  tagName: 'H1', <br>
··············  children: [ <br>
················  \`Seconds ${i}\` <br>
··············  ] <br>
············  } <br>
··········  } <br>

········  ---&gt;&gt;&gt; <br>

········  .map(i =&gt; { <br>
············  return { <br>
··············  tagName: 'H1', <br>
··············  {{c1::children}}: [ <br>
················  { <br>
··················  {{c2::tagName}}: 'SPAN', <br>
··················  {{c3::children}}: [ <br>
····················  \`Seconds ${i}\` <br>
··················  ] <br>
················  } <br>
··············  ] <br>
············  } <br>
··········  } <br>

%

%

clozeq

---

## rxjs ex10: return HTML stream. return H1 and span. implement createElement

··  function createElement(obj) { <br>
····  const element = document.createElement(obj.tagName); <br>
····  element.innerHTML = obj.children[0]; <br>
····  return element; <br>
··  } <br>

··  ---&gt;&gt;&gt; <br>

··  function createElement(obj) { <br>
····  const element = document.createElement(obj.tagName); <br>
····  obj.children <br>
······  .{{c1::filter}}(c =&gt; typeof c === '{{c2::object}}') <br>
······  .map(createElement) <br>
······  .forEach(c =&gt; element.{{c3::appendChild}}(c)); <br>
····  obj.children <br>
······  .filter(c =&gt; typeof c === '{{c4::string}}') <br>
······  .forEach(c =&gt; element.{{c5::innerHTML}} += c); <br>
····  return element; <br>
··  } <br>

%

%

clozeq

---

## rxjs ex10: capture mouseover events in span

const DOMSource = Rx.Observable.fromEvent(document, 'click'); <br>

---&gt;&gt;&gt; <br>

const DOMSource = { <br>
··  {{c1::selectEvents}}: function(tagName, eventType) { <br>
····  return Rx.Observable.fromEvent(document, eventType) <br>
······  .filter(ev =&gt; ev.target.{{c2::tagName}} === tagName.toUpperCase()); <br>
··  } <br>
} <br>

--- <br>

const click$ = sources.DOM; <br>

---&gt;&gt;&gt; <br>

const mouseover$ = sources.DOM.selectEvents('{{c3::span}}', 'mouseover'); <br>

%

%

clozeq

---

## rxjs ex10: use h() helper functions for html elements

············  return { <br>
··············  tagName: 'H1', <br>
··············  children: [ <br>
················  { <br>
··················  tagName: 'SPAN', <br>
··················  children: [ <br>
····················  \`Seconds ${i}\` <br>
··················  ] <br>
················  } <br>
··············  ] <br>
············  } <br>

---&gt;&gt;&gt; 

··  function {{c1::h}}(tagName, children) { <br>
····  return { <br>
······  tagName: tagName, <br>
······  children: children, <br>
····  } <br>
··  } <br>

············  return { <br>
··············  {{c2::h}}('{{c3::H1}}', [ <br>
················  h('SPAN', [ <br>
··················  \`Seconds ${i}\` <br>
················  ]) <br>
··············  ]) <br>

%

%

clozeq

---

## rxjs ex10: h1() span() helper functions

··  function {{c1::h1}}(children) { <br>
····  return { <br>
······  tagName: 'H1', <br>
······  children: children, <br>
····  } <br>
··  } <br>

··  function span(children) { <br>
····  return { <br>
······  tagName: 'SPAN', <br>
······  children: children, <br>
····  } <br>
··  } <br>

··············  ... <br>
··············  {{c2::h1}}([ <br>
················  {{c3::span}}([ <br>
··················  \`Seconds ${i}\` <br>
················  ]) <br>
··············  ]) <br>

%

%

clozeq

---

## rxjs ex14: use DOMDriver from cyclejs

··  &lt;script src="https://rawgit.com/cyclejs/{{c1::cycle-dom}}/v9.0.1/dist/cycle-dom.js"&gt;&lt;/script&gt; <br>

··  const {h, h1, span, makeDOMDriver} = {{c3::CycleDOM}}; <br>

··  const drivers = { <br>
····  DOM: DOMDriver, <br>

··  ---&gt;&gt;&gt; <br>

··  const drivers = { <br>
····  DOM: {{c2::makeDOMDriver}}('#app'), <br>

%

%

clozeq

---

## rxjs ex14: complete code with cyclejs

··  const {h, h1, span, makeDOMDriver} = {{c1::CycleDOM}}; <br>

··  function main(sources) { <br>
····  const mouseover$ = sources.DOM.{{c2::select}}('span').{{c3::events}}('mouseover'); <br>
····  const sinks = { <br>
······  DOM: {{c4::mouseover$}} <br>
········  .{{c5::startWith}}(null) <br>
········  .{{c6::flatMapLatest}}(() =&gt; <br>
··········  Rx.Observable.timer(0, 1000) <br>
············  .map(i =&gt;  <br>
··············  {{c7::h1}}( {style: {background: 'yellow'}}, [ <br>
················  span([ <br>
··················  \`Seconds ${i}\` <br>
················  ]) <br>
··············  ]) <br>
············  ) <br>
········  ), <br>
······  Log: Rx.Observable.timer(0, 2000).map(i =&gt; 2*i), <br>
····  }; <br>
····  return sinks; <br>
··  } <br>

··  function consoleLogDriver(msg$) { <br>
····  msg$.subscribe(msg =&gt; console.log(msg)); <br>
··  } <br>

··  const drivers = { <br>
····  DOM: {{c8::makeDOMDriver}}('#app'), <br>
····  Log: consoleLogDriver, <br>
··  } <br>

··  {{c9::Cycle.run}}(main, drivers); <br>

%

%

clozeq

---

## rxjs ex15: hello world app

&lt;div id="app"&gt;&lt;div&gt;&lt;label&gt;Name:&lt;/label&gt;&lt;input type="text" class="field"&gt;&lt;hr&gt;&lt;h1&gt;Hello ali!&lt;/h1&gt;&lt;/div&gt;&lt;/div&gt;

··  const {label, input, hr, div, h1, makeDOMDriver} = CycleDOM; <br>

··  function main(sources) { <br>
····  const inputEv$ = sources.DOM.select('{{c1::.field}}').events('{{c2::input}}'); <br>
····  const name$ = {{c3::inputEv$}}.map(ev =&gt; {{c4::ev.target.value}}).{{c5::startWith}}(''); <br>
····  return { <br>
······  DOM: {{c6::name$}}.map(name =&gt; <br>
········  div([ <br>
··········  label('Name:'), <br>
··········  {{c7::input}}('.field', {type: 'text'}), <br>
··········  hr(), <br>
··········  h1(\`Hello ${name}!\`) <br>
········  ]) <br>
······  ) <br>
····  }; <br>
··  } <br>

··  const drivers = { <br>
····  DOM: makeDOMDriver('#app'), <br>
··  } <br>

··  Cycle.run(main, drivers); <br>


%

%

clozeq

---

## rxjs ex17: effects classification

··  // {{c1::DOM read}} effect: button clicked <br>
··  // {{c2::HTTP write}} effect: request sent <br>
··  // {{c3::HTTP read}} effect: response received <br>
··  // {{c4::DOM write}} effect: data displayed <br>

%

%

clozeq

---

## rxjs ex17: http request/response

··  function main(sources) { <br>
····  const clickEvent$ = {{c1::sources.DOM}} <br>
······  .{{c2::select}}('.get-first').events('click'); <br>
····   <br>
····  const request$ = clickEvent$.map(() =&gt; { <br>
······  return { <br>
········  {{c3::url: 'http://jsonplaceholder.typicode.com/users/1'}}, <br>
········  method: 'GET', <br>
······  }; <br>
····  }); <br>
····   <br>
····  const response$$ = {{c4::sources.HTTP}} <br>
······  .{{c5::filter}}(response$ =&gt; response$.request.url === <br>
············   'http://jsonplaceholder.typicode.com/users/1'); <br>
····   <br>
····  const response$ = response$$.{{c6::switch}}(); <br>
····  const firstUser$ = response$.map(response =&gt; {{c7::response.body}}) <br>
······  .{{c8::startWith}}(null); <br>
····   <br>
····  return { <br>
······  DOM: firstUser$.map(firstUser =&gt; <br>
········  div([ <br>
··········  button('.get-first', 'Get first user'), <br>
··········  firstUser === null ? null : div('.user-details', [ <br>
············  h1('.user-name', {{c9::firstUser.name}}), <br>
············  h4('.user-email', firstUser.email), <br>
············  a('.user-website', {href: firstUser.website}, firstUser.website) <br>
··········  ]) <br>
········  ]) <br>
······  ), <br>
······  HTTP: request$, <br>
····  }; <br>
··  } <br>


%

%

clozeq

---

## beau: popup options

··  {{c1::alert}}("message"): void <br>
··  {{c2::confirm}}("yes or no"): boolean <br>
··  var person = {{c3::prompt}}("enter name:", "default value") <br>

%

%

clozeq

---

## beau: window object implied

··  document <br>
··  --&gt; implies <br>
··  {{c1::window}}.document <br>

··  alert <br>
··  --&gt; implies <br>
··  window.alert <br>

%

%

clozeq

---

## beau: window: move, open

··  window.{{c1::addEventListener}}('resize', update) <br>
··  var x = window.document.{{c2::getElement}}ById("demo") <br>
··  function update() { <br>
····  x.{{c3::innerHTML}} = "inner window width: " + window.innerWidth; <br>
··  } <br>
··  var newWindow = window.{{c4::open}}("http://freecodecamp.com", "newWindow", "menubar=true,top=200,left=200") <br>

··  &lt;input type="button" value="Close" onclick="{{c5::newWindow}}.close()" /&gt; <br>
··  &lt;a href=".." target="newWindow"&gt;My &lt;/a&gt; <br>

%

%

clozeq

---

## beau: node tree

top node: {{c1::Document}}

root node: {{c2::&lt;html&gt;}}

%

%

clozeq

---

## beau: node tree 02


··  var para = document.{{c1::createElement}}("p"); <br>
··  var node = document.{{c2::createTextNode}}("hello"); <br>
··  para.{{c3::appendChild}}(node); <br>
··  var parent = document.getElementById("div1"); <br>
··  parent.appendChild(para); <br>
··  para.{{c4::innerHTML}} = "new text" <br>
··  var child = document.getElementById("p1") <br>
··  parent.{{c5::insertBefore}}(para, child) <br>

%

%

clozeq

---

## beau: event listener

··  var myp = document.getElementById("myp"); <br>
··  myp.addEventListener("click", function() { myDiv.{{c1::style}}.background = "blue" }); <br>

same with onclick: but more constrained

··  myp.{{c2::onclick}} = function() { myDiv.style.background = "blue" }; <br>

%

%

clozeq

---

## beau: onclick

··  &lt;h1 onclick="this.{{c1::innerHTML}}='Cereal!'"&gt;My fav&lt;/h1&gt; <br>
··  &lt;h1 onclick="changeColor(this)"&gt;Change&lt;/h1&gt; <br>

··  function {{c2::changeColor}}(obj) { <br>
····  obj.style.color = "blue"; <br>
··  } <br>

%

%

clozeq

---

## beau: css styles in js

··  mydiv.style.background = 'red'; <br>
··  mydiv.style.{{c1::cssText}} = 'background: red;'; <br>
··  mydiv.{{c2::setAttribute}}("{{c3::style}}", "background: red;"); <br>

show every style element:

··  console.log({{c4::mydiv.style}}); <br>
··  // inline styles  <br>
··  console.log(window.{{c5::getComputedStyle}}(mydiv)); <br>
··  // inline + css styles <br>

%

%

clozeq

---

## beau: dom manipulation

··  var unicyle = document.{{c1::getElementsByClassName}}('unicycle'); <br>
··  var unicyle = {{c2::div1}}.getElementsByClassName('unicycle'); <br>
··  var paragraphs = document.{{c3::getElementsByTagName}}('p'); <br>
··  var query = document.{{c4::querySelector}}('.unicycle'); <br>
··  var query = document.{{c5::querySelectorAll}}('.unicycle'); <br>
··  // returns array of elements <br>
··  var query = document.querySelectorAll('.unicycle{{c6::, #div2}}'); <br>

··  var text = "&lt;h1&gt;Hello&lt;/h1&gt;" <br>
··  div1.{{c7::innerHTML}} = text; <br>
··  // replaces all div1 content <br>

note: innerHTML opens up {{c8::XSS}} attack

··  instead use textContent <br>
··  div1.{{c9::textContent}} = text; <br>
··  // puts raw content <br>

%

%

clozeq

---

## beau: check: property is in an object?

··  var my = {name: 'js'}; <br>
··  if ({{c1::my.name}}) { ... } <br>
··  console.log(my.{{c2::hasOwnProperty}}('name')) <br>
··  // if name is contained directly (not from prototype) <br>
··  console.log('name' {{c3::in my}}) <br>

%

%

clozeq

---

## beau: IIFE Immediately Invoked Function Expression 


{{c1::IIFE}}: runs as soon as it is defined

··  (function () { <br>
····  console.log("my"); <br>
··  })(); <br>
··  (fav = function () { <br>
····  console.log("my"); <br>
··  })(); <br>
··  fav(); <br>

usecase: creating {{c2::closures}} so that variable names don't conflict with imported libraries

··  (function() { <br>
····  var a = 3; <br>
··  }) <br>
··  console.log(a); // {{c3::error}} <br>

%

%

clozeq

---

## beau: Get current URL 

··  window.{{c1::location}}.protocol <br>
··  window.location.{{c2::host}} <br>
··  window.location.{{c3::pathname}} <br>
··  window.location.{{c4::href}} <br>

jquery

··  $(location).{{c5::attr}}('&lt;property&gt;') <br>

%

%

clozeq

---

## beau: Destructuring in ES6 

··  var obj = {x: 3, y: 5}; <br>

old way:

··  var x = obj.x; <br>
··  var y = obj.y; <br>

new:

··  const {{c1:: {x,y} }} = obj; <br>
··  console.log(x); <br>

or rename

··  const {  {{c2:: x : a, y: b}}  } = obj; <br>
··  console.log(a); <br>

assign from nested objects

··  const a = { <br>
····  start: {x: 5, y: 6} <br>
··  } <br>
··  const { {{c3::start}}: {x: startX}} = a; <br>

assign from arrays

··  const [a{{c4::,,,b}}] = [1,2,3,4,5]; <br>
··  console.log(a); <br>

rest operator

··  const [a, {{c5::...b}}] = [1,2,3,4,5]; <br>
··  console.log(b) <br>

%

%

clozeq

---

## beau: arrow functions

when returning objects, always put into {{c1::paranthesis}}:

··  var func = () =&gt; ({foo: 1}); <br>

%

%

clozeq

---

## beau: strings [bracket notation]

··  var name = "ali" <br>
··  console.log(name{{c1::[1]}}) <br>
··  console.log(name[name.length - 1]) <br>
··  name{{c2::[0]}} = "q"; // error: string is immutable <br>

%

%

clozeq

---

## beau: copying arrays

··  var o = [true, false] <br>

slice

··  var copy1 = o.{{c1::slice(0)}}; <br>

spread

··  var copy2 = {{c2::[...o]}}; <br>

deep

··  var deep = [['free']] <br>
··  var shallow = deep.slice(0) <br>
··  shallow[0].{{c3::push}}['great'] <br>
··  var copy = JSON.parse({{c4::JSON.stringify}}(deep)); <br>
··  copy[0].push['awesome'] <br>
··  console.log(shallow, deep) <br>

%

%

clozeq

---

## beau: for in vs for of

loop through property names

··  for ({{c1::var in obj}}) <br>

loop through array values

··  for ({{c2::var of obj}}) <br>

··  let obj = {name: "ali"} <br>
··  for (key in obj) { <br>
····  obj{{c3::[key]}} <br>
··  } <br>

%

%

clozeq

---

## crockford: const vs object freeze

const applies to {{c1::bindings}} ("variables").  i.e. you cannot assign a new value to the binding.

Object.freeze works on object values. i.e. you cannot change its {{c2::properties}}.

··  var ob1 = { <br>
····   foo : 1, <br>
······  bar : { <br>
··········  value : 2··  <br>
······  } <br>
··  }; <br>
··  Object.{{c3::freeze}}( ob1 ); <br>
··  const ob2 = { <br>
····   foo : 1, <br>
······  bar : { <br>
··········  value : 2··  <br>
······  } <br>
··  } <br>
··  ob1.foo = 4;  // (frozen) ob1.foo {{c4::not modified}} <br>
··  ob2.foo = 4;  // (const) ob2.foo modified <br>


%

%

clozeq

---

## crockford: http controversy: post put patch

it creates {{c1::clutter}}

programmers love clutter intuitively

getting rid of clutter in programming is hard

we enjoy clutter


%

%

clozeq

---

## crockford: es6: clutters

let vs. var: get rid of {{c1::var}}

let vs. const: use {{c2::const}}

null vs undefined: use {{c3::undefined}}

problem with null:

you have to ask {{c4::is—null}}() before accessing

correct implementation: an immutable empty object

··  {{c5::const}} null = Object.{{c6::freeze}}(Object.{{c7::create}}()) <br>


%

%

clozeq

---

## crockford: js: remove impurities

··  Date <br>
··  Math.random <br>
··  delete <br>
··  Object.{{c1::assign}} <br>
··  Array.splice <br>
··  Array.{{c2::sort}} <br>
····  inplace editing <br>
··  RegExp.exec <br>
····  modifies regex object <br>
··  assignment <br>
··  var <br>
··  {{c3::let}} <br>
····  const is ok <br>
··  for and loops  <br>
····  recursion functions better <br>


%

%

clozeq

---

## crockford: es6 features generators

it is a mistake confusing behavior

··  function element(array) { <br>
····  {{c1::let i = 0}} <br>
····  return function {{c2::generator}}() { <br>
······  if (i &lt; array.length) { <br>
········  let value = {{c3::array[i]}} <br>
········  i += 1 <br>
········  return value <br>
······  } <br>
····  } <br>


%

%

clozeq

---

## crockford: better parts 01

Saint Exupery: perfection is attained not when there is nothing more to add, but when there is nothing more to {{c1::subtract}}

i made every mistake i can

first: i didn't bother {{c2::learning}} the language before writing


%

%

clozeq

---

## crockford: es2015

proper {{c1::tail}} call

{{c2::ellipsis}} operator: much better than argument arrays

{{c3::modules}}: import and export values. no need for require(..) stuff

let and const: solves block scope

destructuring
<br>
{{c4::WeakMap}}: any value can be key

Megastring literals

fat {{c5::arrow}}

bad parts: {{c6::class}}

%

%

clozeq

---

## crockford: stopped using

Object.{{c1::create}} and new()

··  problem: this <br>
····  this in a method, bound to object <br>
····  this in a function, bound to {{c2::global}} object <br>
······  violates security <br>

{{c3::null}}

··  {{c4::typeof}}(null) returns object <br>

{{c5::falsiness}}: using null, 0 instead of false

{{c6::for}} -&gt; forEach, map from es5

forEach -&gt; Object.{{c7::keys}} gives a nice array of keys

loops: use only recursive functions

··  function repeat(func) { <br>
····  while (func() !== undefined) { <br>
····  } <br>
··  } <br>
··  ---&gt;&gt;&gt; <br>
··  function repeat(func) { <br>
····  if (func() !== undefined) { <br>
······  return {{c8::repeat}}(func); <br>
····  } <br>
··  } <br>

%

%

clozeq

---

## JavaScript Patterns for 2017 01

export

··  function work(..) {..} <br>
··  class Person { <br>
····  constructor(name) {..} <br>
··  {{c1::export}} {work, Person} <br>
··  export default Person <br>

import

··  {{c2::import}} {Person, Animal} {{c3::from}} "./lib" <br>
····  from lib.js or lib.ts <br>
··  import HumanPerson from "./lib/humans" <br>
····  exported by default <br>
··  {{c4::import *}} as lib from "./lib" <br>
····  lib.Person <br>


%

%

clozeq

---

## JavaScript Patterns for 2017 02

public apis with index.js

··  in a {{c1::directory}} <br>
····  put index.js <br>
····  export public functions from here <br>
····  like index.py <br>
··  this will separate public and package scope <br>
··  ex: index.js <br>
····  export * from {{c2::"./humans"}} <br>


%

%

clozeq

---

## JS Patterns 2017: Arrow syntax 03

return an object literal

··  # use ({..}) when returning objects <br>
··  const result = [1,2,3].map(n =&gt; {value: n}) <br>
··  // doesn't return objects, just {{c1::undefined}} <br>
··  const result = [1,2,3].map(n =&gt; {{c2::({value: n})}} ) <br>
··  // value:1, value:2 .. <br>


%

%

clozeq

---

## JS Patterns 2017: Arrow syntax 04

lexical this reference

··  arrow doesn't bind {{c1::`this`}} <br>

··  const adder = { <br>
····  add: () =&gt; { this.x } <br>
··  } <br>

cannot rebind with {{c2::bind}}, apply


%

%

clozeq

---

## JS Patterns 2017: immutable data structure 01

··  {{c1::const}} nums = [1,2,3] <br>
··  Object.{{c2::freeze}}(nums) <br>


%

%

clozeq

---

## js wes bos: string templating

··  const markup = \`${renderKeywords({{c1::beer.keywords}})}\` <br>
··  funcion {{c2::renderKeywords}}(kw) { <br>
····  return \`&lt;ul&gt; <br>
······  ${kw.{{c3::map}}(key =&gt; &lt;{{c4::li}}&gt;${key}\` <br>


%

%

clozeq

---

## js wes bos: enhanced object literals

··  const dog = { <br>
····  first: first, <br>
····  last: last <br>
··  ---&gt;&gt;&gt; <br>
····  {{c1::first, last,}} <br>

method definition 

··  var modal = { <br>
····  create: function(sel) {.. <br>
··  ---&gt;&gt;&gt; <br>
····  {{c2::create(sel)}} { ... <br>


%

%

clozeq

---

## difference bw nodejs and browser console

no {{c1::window}}, no document object

global process object

··  var a = 1; <br>
··  ---&gt;&gt;&gt; <br>
··  {{c2::global}}.a // node <br>
··  window.a // browser <br>


%

%

clozeq

---

## frisby: point-free programming

··  return ajaxCall(function(json) { <br>
····  return callback(json); <br>
··  }); <br>
··  ---&gt;&gt;&gt; <br>
··  return ajaxCall({{c1::callback}}); <br>


%

%

clozeq

---

## frisby: why first class functions?

multiple names for the same concept is source of confusion

··  // specific to our current blog <br>
··  var validArticles = function(articles) { <br>
····  return articles.filter(function(article) { <br>
······  return article !== null && article !== undefined; <br>
····  }); <br>
··  }; <br>
··  ---&gt;&gt;&gt; more general <br>
··  var compact = function({{c1::xs}}) { <br>
····  return xs.filter(function({{c2::x}}) { <br>
······  return x !== null && x !== undefined; <br>
····  }); <br>
··  }; <br>


%

%

clozeq

---

## frisby: pure function

given same input

always return {{c1::same output}}

and has no observable side effect


slice is pure vs. {{c2::splice}} has effect

modifies input array

%

%

clozeq

---

## frisby: pure function 02

··  // {{c1::impure}} <br>
··  var minimum = 21; <br>
··  var checkAge = function(age) { <br>
····  return age &gt;= minimum; <br>
··  }; <br>
··  // {{c2::pure}} <br>
··  var checkAge = function(age) { <br>
····  var {{c3::minimum}} = 21; <br>
····  return age &gt;= minimum; <br>
··  }; <br>


%

%

clozeq

---

## frisby: pure function 03

what is side effect

··  anything that {{c1::changes}} during computation other than the result <br>
··  a change of the system state <br>


%

%

clozeq

---

## frisby: pure function 04

so what is side effects' use?

··  they are not forbidden <br>
··  we want to {{c1::contain}} them <br>


%

%

clozeq

---

## frisby: pure function 05

benefits of purity?

··  Cacheable <br>
··  Portable/{{c1::self-documenting}} <br>
··  Testable <br>
··  Reasonable <br>
··  Parallel code <br>

Erlang creator, Joe Armstrong: 

"The problem with object-oriented languages is they’ve got all this {{c2::implicit}} environment that they carry around with them. You wanted a banana but what you got was a gorilla holding the banana... and the entire jungle".


%

%

clozeq

---

## frisby: currying 01

··  var add = function(x) { <br>
····  return function({{c1::y}}) { <br>
······  return x + y; <br>
····  }; <br>
··  }; <br>
··  var increment = {{c2::add(1)}}; <br>
··  increment(2); <br>


%

%

clozeq

---

## frisby: currying 02

data as {{c1::last}} argument

··  match(/\s+/g, 'hello world'); <br>
··  // [ ' ' ] <br>
··  match(/\s+/g){{c1::('hello world')}}; <br>
··  // [ ' ' ] <br>
··  var {{c2::hasSpaces}} = match(/\s+/g); <br>


%

%

clozeq

---

## how to import js library in web pages?

··  &lt;script src="https://cdnjs.cloudflare.com/ajax/libs/{{c1::require.js}}/2.2.0/{{c1::require}}.min.js"&gt;&lt;/script&gt; <br>

··  const {{c2::CDN}} = s =&gt; `https://cdnjs.cloudflare.com/ajax/libs/${s}`; <br>
··  const {{c3::ramda}} = CDN('ramda/0.21.0/ramda.min'); <br>
··  {{c4::requirejs}}.config({ paths: { ramda } }); <br>
··  {{c5::require}}(['ramda'], ({ compose, curry, map, prop }) =&gt; { <br>
····  // app <br>
··  }); <br>

%

%

clozeq

---

## how to import js library in web pages? 02

··  &lt;script src="https://cdnjs.cloudflare.com/ajax/libs/{{c1::require.js}}/2.2.0/{{c1::require}}.min.js"&gt;&lt;/script&gt; <br>
··  const R = {{c2::require}}('ramda'); <br>
··  const prop = {{c3::R.prop}}; <br>

%

%

clozeq

---



## node: node-gyp python error

node-gyp: gyp ERR! stack You can pass the --python switch to point to Python &gt;= v2.5.0 & &lt; 3.0.0.

··  npm install {{c1::--python}}=/usr/bin/python -g underscore-cli <br>

%

%

clozeq

---

## ramda practical: FP Challenges:

1. how to handle if-else: {{c1::Either}} monad

2. how to handle null exceptions: {{c2::Maybe}} monad

3. How to ensure reusable functions: {{c3::pure functions}}, {{c4::referential transparency}}

4. How to ensure unchanged data: pure functions, {{c5::immutability}}

5. Functions with multiple args. How to chain with a single value: {{c6::currying}}, {{c7::higher order functions}}

%

%

clozeq

---

## ramda practical: Fantasyland Specs

Dependencies of specs:

··  {{c1::Functor}} <br>
····  Alt <br>
······  Plus <br>
········  Alternative <br>
····  Apply <br>
······  Applicative <br>
········  Alternative <br>
········  {{c2::Monad}} <br>
······  Chain <br>
········  Monad <br>
········  ChainRec <br>
····  Bifunctor <br>
····  Extend <br>
······  Comonad <br>
····  Profunctor <br>
····  Traversable <br>
··  {{c3::Foldable}} <br>
····  Traversable <br>
··  Semigroup <br>
····  {{c4::Monoid}} <br>
··  Setoid <br>

%

%

clozeq

---

## eloquent10: modules: CommonJS: definition of require()

··  require.{{c1::cache}} = Object.create(null) <br>
··  function require(name) { <br>
····  if (!(name in require.cache)) { <br>
······  let code = {{c2::readFile}}(name) <br>
······  let {{c3::module}} = {exports: {}} <br>
······  require.cache[name] = module <br>
······  let wrapper = {{c4::Function}}("require, exports, module", code) <br>
······  wrapper(require, {{c5::module.exports}}, module) <br>
····  } <br>
····  return require.cache[name].{{c6::exports}} <br>
··  } <br>

%

%

clozeq

---

## eloquent10: modules: CommonJS: require()

Example of using require()

format-date.js:

··  const ordinal = {{c1::require}}("ordinal") <br>
··  const {days, months} = require("date-names") <br>
··  {{c2::exports}}.formatDate = function(date, format) { <br>
····  return format.replace(..) <br>
··  } <br>

using format-date.js:

··  const {{{c3::formatDate}}} = require("./format-date") <br>

%

%

clozeq

---

## eloquent10: modules: Function constructor

··  let plusOne = {{c1::Function}}("n", "return n + 1;") <br>
··  console.log({{c2::plusOne}}(4)) <br>
··  //&gt; 5 <br>

takes two args: 

a string: a comma separated list of {{c2::arg names}}

a string: function {{c3::body}}

%

%

clozeq

---

## eloquent10: modules: Module definition

a module specifies

which other pieces it relies on ({{c1::dependencies}})
which functions it provides for use ({{c2::interface}})

%

%

clozeq

---

## eloquent10: modules: quote

Write code that is easy to {{c1::delete}}, not easy to extend.

Tef, Programming is Terrible

%

%

clozeq

---

## eloquent10: modules: name argument

when it starts with "./" or "../" it is {{c1::relative file path}}

else, node will look for an {{c2::installed package}} by that name


%

%

clozeq

---

## eloquent10: ES Modules

··  const ordinal = require("ordinal") <br>
··  const {days, months} = require("date-names") <br>
··  exports.formatDate = function(date, format) {..} <br>
··  ---&gt;&gt;&gt; <br>
··  {{c1::import ordinal}} from "ordinal" <br>
··  import {{c2::{days, months}}} from "date-names" <br>
··  {{c3::export function}} formatDate(date, format) {...} <br>


%

%

clozeq

---

## eloquent10: modules: quote 2

{{c1::don't assume}} that a painful mess is just the way it is.

you can {{c2::improve the structure}} of almost everything by putting more thought into it

%

%

clozeq

---

## js: same origin policy definition

js cannot access resources from {{c1::other}} websites. 

it can access resources from that same site

problem with file:// urls:

origin becomes {{c2::null}}

thus you cannot import other js modules

%

%

clozeq

---

## js: es modules 01

index.html

··  &lt;script type="{{c1::module}}" src="app.js"&gt;&lt;/script&gt; <br>

app.js

··  import lib from './lib.js' <br>
··  lib.doStuff() <br>

must: static {{c2::web server}} (due to CORS error from file:// urls)

if authentication enabled, &lt;script {{c3::crossorigin}}="use-credentials"&gt;

%

%

clozeq

---

## js: es modules 02

how to use external libraries that don't use es modules?

opt1: {{c1::Webpack}}

opt2: libraries can be used directly from {{c2::CDN}} or local node—modules


%

%

clozeq

---

## js: es modules 03: problems with AMD

··  define(['file1', 'file2'], function(Class1, Class2)) { <br>
····  let obj = new Class1(), <br>
······  obj2 = new Class2(); <br>
····  return obj.foo(obj2); <br>
··  } <br>

{{c1::boilerplate}}: need to wrap code in an outer function

AMD dependencies: 
1. array of strings
2. parameters to callback function

Node Ecosystem: some modules work only with {{c2::CommonJS}}

%

%

clozeq

---

## js: es modules 04: before modules

Revealing Module Pattern

··  var revealingModule = (function () { <br>
····  var privateVar = "Ben Thomas"; <br>
····  function setNameFn( strName ) { <br>
······  privateVar = strName; <br>
····  } <br>
····  return { <br>
········  {{c1::setName}}: setNameFn, <br>
······  }; <br>
····  })(); <br>
··  revealingModule.setName( "Paul Adams" ); <br>


%

%

clozeq

---

## js: es modules 05: CommonJS example

··  var obj = {{c1::require}}('module—name') <br>
··  {{c2::exports}} = function() { <br>
····  return ...; <br>
··  } <br>


%

%

clozeq

---

## js: es modules 06: Node example

··  var obj = {{c1::require}}('module—name') <br>
··  {{c2::modules.exports}} = function() { <br>
····  return ...; <br>
··  } <br>

con: {{c3::one file}} per module

con: browsers cannot use them without {{c4::transpiling}}

Browserify, Webpack

%

%

clozeq

---

## js: es modules 07: es modules ex

main.js

··  {{c1::import}} {square, diag} from 'lib'; <br>

lib.js

··  {{c2::export}} function square(x) { return x * x; } <br>

import statement is {{c3::not dynamic}}

makes static analyzers build tree of dependencies

%

%

clozeq

---

## js: es modules 08

{{c1::implicit}} dependency:

··  &lt;script src="https://unpkg.com/lodash@4.16.6"&gt;&lt;/script&gt; <br>

%

%

clozeq

---

## js: webpack 01: basic setup

cli

··  npm init -y <br>
··  npm install --save-dev {{c1::webpack}} <br>

src/index.js

··  element.innerHTML = {{c1::—}}.join(['Hello','webpack'], ' '); <br>

index.html

··  &lt;script src="https://unpkg.com/lodash@4.16.6"&gt;&lt;/script&gt; <br>

{{c2::implicit}} dependency:

··  &lt;script src="https://unpkg.com/lodash@4.16.6"&gt;&lt;/script&gt; <br>


%

%

clozeq

---

## js: webpack 02: basic setup 02

cli

··  npm {{c1::install}} --save lodash <br>

src/index.js

··  {{c2::import}} — from 'lodash'; <br>

index.html

··  &lt;script src="{{c3::bundle.js}}"&gt;&lt;/script&gt; <br>

cli

··  {{c4::webpack}} src/index.js --output dist/bundle.js <br>

%

%

clozeq

---

## js: webpack 03: how it works

Webpack {{c1::transpiles}} `import` and `export` statements

other code is not touched


%

%

clozeq

---

## js: webpack 04: Using a Configuration

if complex setup is needed, use configuration file

{{c1::webpack.config}}.js

··  const path = {{c2::require}}('path'); <br>
··  module.{{c3::exports}} = { <br>
····  {{c4::entry}}: './src/index.js', <br>
····  {{c5::output}}: { <br>
······  filename: 'bundle.js', <br>
······  path: path.{{c6::resolve}}(——dirname, 'dist') <br>
····  } <br>
··  } <br>

cli

··  npx webpack {{c7::--config}} webpack.config.js <br>


%

%

clozeq

---

## js: webpack 05: basic setup: npm shortcut

package.json

··  "scripts": { <br>
····  "{{c1::build}}": "webpack" <br>
··  } <br>

cli

··  npm run {{c2::build}} <br>


%

%

clozeq

---

## js: webpack 06: Loading CSS

webpack.config.js

··  module: { <br>
····  {{c1::rules}}: [ <br>
······  { <br>
········  {{c2::test}}: /\.css$/, <br>
········  use: [ <br>
··········  'style-loader', <br>
··········  'css-loader' <br>
········  ] <br>
······  } <br>
····  ] <br>
··  } <br>

src.index.js

··  import — from 'lodash'; <br>
··  {{c3::import}} './style.css'; <br>
··  function component() { <br>
····  var element = document.createElement('div'); <br>
····  element.innerHTML = —.join(['Hello','webpack'], ' '); <br>
····  element.{{c4::classList}}.add('hello'); <br>
····  return element; <br>
··  } <br>
··  document.body.appendChild(component()); <br>

cli

··  npm run build <br>


%

%

clozeq

---

## js: webpack 07: Loading Images

··  npm install --save-dev file-loader <br>

webpack.config.js

··  module: { <br>
····  rules: [ <br>
······  { <br>
········  test: /\.({{c1::png|svg|jpg|gif}})$/, <br>
········  use: [ <br>
··········  'file-loader' <br>
········  ] <br>
······  } <br>
····  ] <br>
··  } <br>

src/index.js <br>

··  import {{c2::Icon}} from './icon.png'; <br>
··  var myIcon = new Image(); <br>
··  myIcon.{{c3::src}} = Icon; <br>
··  element.appendChild(myIcon); <br>

src/style.css <br>

··  .hello { <br>
····  background: url('{{c4::./icon.png}}'); <br>
··  } <br>

%

%

clozeq

---

## js: webpack 08: Loading Data

··  npm install --save-dev {{c1::csv-loader}} xml-loader <br>

··  module: { <br>
····  rules: [ <br>
······  { <br>
········  test: /\.(csv|tsv)$/, <br>
········  use: [ <br>
··········  '{{c2::csv-loader}}' <br>
········  ] <br>
······  }, <br>
······  { <br>
········  test: /\.(xml)$/, <br>
········  use: [ <br>
··········  'xml-loader' <br>
········  ] <br>
······  } <br>
····  ] <br>
··  } <br>

src/index.js

··  import {{c3::Data}} from './data.xml'; <br>


%

%

clozeq

---

## js: webpack 09 file structuring

··  /components <br>
····  /{{c1::my-component}} <br>
······  index.jsx <br>
······  index.css <br>
······  icon.svg <br>

%

%

clozeq

---

## js: static web page hosting in rawgit

··  https://{{c1::rawgit}}.com/mertnuhoglu/{{c3::study}}/master/js/ex/ecmascript—modules—ex07/dist/index.html <br>

··  https://rawgit.com/user/repo/{{c2::branch}}/file <br>

%

%

clozeq

---

## js: es6 features 01

destructuring with concise parameters

··  let { {{c1::a,c}} } = {a:1, b:2, c:3}; <br>

assign to a new variable name

··  let {x: {{c2::vehicle}} } = {x: 'car'}; <br>
··  let {x: { {{c3::name: driver}} } } = {x: {name: 'john'}}; <br>

assigning a value to multiple variables

··  let {x: {{c4::first}}, x: second} = {x:4}; <br>


%

%

clozeq

---

## js: es6 features 02

omit key if it is the same as variable name

··  let a = 4, b = 7; <br>
··  let concise = { {{c1::a,b}} }; <br>
··  console.log(concise) // {a: 4, b: 7} <br>


%

%

clozeq

---

## js: es6 features 03

dynamic property names

··  let city = 'london—'; <br>
··  let a = { {{c1::[city+'population']}}: 350 }; <br>
··  a[ city + 'county' ] <br>

%

%

clozeq

---

## js: hyperscript helpers 01

··  h('section#main', mainContents) <br>
··  ---&gt;&gt;&gt; <br>
··  {{c1::section}}('{{c2::#main}}', mainContents) <br>


%

%

clozeq

---

## js: hyperscript helpers 02

output

··  &lt;ul id="best-menu"&gt; <br>
····  &lt;li id="item-10" draggable="true" data-id="10"&gt;my title&lt;/li&gt; <br>
····  &lt;li id="item-11" draggable="true" data-id="11"&gt;my title&lt;/li&gt; <br>
··  &lt;g/ul&gt; <br>

code:

··  function attrs(id) { <br>
····  return { {{c1::draggable: "true", "data-id": id}} }; <br>
··  } <br>
··  ul("{{c2::#best-menu}}', items.{{c3::map}}( item =&gt; <br>
····  li('#item-'+item.id, attrs((item.id), {{c4::item.title}})) <br>
··  )") <br>

%

%

clozeq

---

## js: hyperscript helpers 03 How to setup

··  npm install {{c1::hyperscript-helpers}} <br>

··  const h = require('hyperscript'); <br>
··  const { div, span, h1 } = require('hyperscript-helpers'){{c2::(h)}}; <br>


%

%

clozeq

---

## js: hyperscript helpers 04 API

··  tagName(selector) <br>
··  tagName(attrs) <br>
··  tagName({{c1::children}}) <br>
··  tagName({{c2::attrs}}, children) <br>
··  tagName(selector, children) <br>
··  tagName(selector, {{c3::attrs}}, children) <br>

%

%

clozeq

---

## js: hyperscript helpers 05 Multiple classes:

··  button({className: "btn btn-default"});  <br>
··  button({{c1::".btn.btn-default"}});············  <br>


%

%

clozeq

---

## js: hyperscript helpers 06

··  const ProfileLink = user =&gt; <br>
····  &lt;a href={`/users/${u.id}`}&gt;{u.name}&lt;/a&gt; <br>
··  ---&gt;&gt;&gt; <br>
··  const ProfileLink = user =&gt; <br>
··  {{c1:: a({href: `/users/${user.id}`}, user.name) }} <br>

%

%

clozeq

---

## js: lonsdorf composable 01

··  const ProfileLink = user =&gt; <br>
····  &lt;a href={`/users/${u.id}`}&gt;{u.name}&lt;/a&gt; <br>
··  ---&gt;&gt;&gt; <br>
··  const ProfileLink = user =&gt; <br>
····  a({{{c1::href}}: `/users/${user.id}`}, user.name) <br>


%

%

clozeq

---

## js: lonsdorf composable 02 Component

··  const Comp = g =&gt; <br>
··  ({ <br>
····  fold: g, <br>
····  contramap: f =&gt; <br>
······  Comp(x =&gt; {{c1::g(f(x))}} ) <br>
··  }) <br>

··  const Heading = str =&gt; h1(`Now Viewing ${str}`); <br>
··  const Title = Comp(Heading).{{c2::contramap}}(s =&gt; s.pageName); <br>

··  Title.fold({  <br>
····  pageName: 'Home', <br>
····  currentUser: { id: 2, name: 'Chris' } <br>
··  }); <br>


%

%

clozeq

---

## js: lonsdorf composable 03

`contramap` is the {{c1::opposite}} of `map`

`contramap` {{c2::prepends}} its input function `f` to the existing function `g` of `Comp`.

%

%

clozeq

---

## js: lonsdorf composable 04 map vs contramap

How to do it with usual `map`?

··  const Heading = str =&gt; h1(`Now Viewing ${str}`); <br>
··  const s = { pageName: 'Home', currentUser: { id: 2, name: 'Chris' } } <br>
··  const f = s =&gt; s.pageName <br>
··  const g = Heading <br>

··  Comp(f) <br>
····  .contramap(g) <br>
····  .fold(s) <br>

··  Box(s) <br>
····  .{{c1::map}}(g) <br>
····  .fold({{c2::f}}) <br>


%

%

clozeq

---

## js: lonsdorf composable 05 concat

`concat` function concatenates two components:

··  const Comp = g =&gt; <br>
··  ({ <br>
····  fold: g, <br>
····  contramap: f =&gt; Comp(x =&gt; g(f(x))), <br>
····  concat: other =&gt;  <br>
······  Comp(x =&gt; {{c1::div( g(x) + other.fold(x) )}}) <br>
··  }) <br>

··  const Link = Comp(ProfileLink).contramap(s =&gt; s.currentUser) <br>
··  const App = Heading.{{c2::concat}}(Link) <br>
··  App.fold(s) <br>
··  // &lt;div&gt; <br>
··  //   &lt;h1&gt;Now viewing Home&lt;/h1&gt; <br>
··  //   &lt;a href="/users/22"&gt;Chris&lt;/a&gt; <br>
··  // &lt;/div&gt; <br>

%

%

clozeq

---

## js: webpack error 01

··  npm start <br>

··  Error: Cannot find module '../lib/polyfills' <br>

cause: I copied the project from somewhere else.  some of the references broken

solution: 

··  {{c1::npm install}} --save-dev webpack-dev-server <br>


%

%

clozeq

---

## js: comparison AMD, RequireJS, CommonJS, ES Modules

ex: AMD

··  {{c1::define}}(['file1', 'file2'], function(Class1, Class2)) { <br>
····  let obj = new Class1(), <br>
······  obj2 = new Class2(); <br>
····  return obj.foo(obj2); <br>
··  } <br>

ex: CommonJS

··  let Class1 = {{c2::require}}('file1'), <br>
····  Class2 = require('file2') <br>
····  obj = new Class1(), <br>
····  obj2 = new Class2(); <br>
··  module.exports = obj.foo(obj2); <br>

ex: es6

··  {{c3::import}} Class1 from 'file1' <br>
··  import Class2 from 'file2' <br>
··  let obj = new Class1(), <br>
····  obj2 = new Class2(); <br>
··  export default obj.foo(obj2); <br>

%

%

clozeq

---

## js: webpack when to use 01

Webpack is 

- a build tool: bundles all files in a {{c1::dependency graph}}
- lets you use {{c2::require()}} to point to local files
- replaces the file references with {{c3::URLs}} in final bundle

%

%

clozeq

---

## js: webpack when to use 02 Earlier:

2. Build script to concatenate and minify scripts

··  ```  <br>
··  // build-script.js <br>
··  var scripts = [   <br>
······  'jquery.min.js', <br>
······  'jquery.some.plugin.js', <br>
······  'main.js' <br>
··  ].concat().uglify().writeTo('bundle.js'); <br>

··  // Everything our app needs! <br>
··  &lt;script src="bundle.js"&gt;&lt;/script&gt;   <br>
··  ```  <br>

··  Con: relied on the {{c1::order}} of concatenated files <br>
··  Con: still has {{c2::global}} variables <br>

%

%

clozeq

---

## js: webpack when to use 03 The Good

Static assets in a dependency graph has benefits:

- {{c1::Dead asset}} elimination. Great especially for CSS rules.

- Easier code {{c2::splitting}}. Each js file has a specific CSS file that reduces
file sizes a lot.

- You control how assets are {{c3::processed}}. Ex: You can base64 encode small files
directly into js.
 
- {{c4::Stable}} production deploys. No image missing.

- {{c5::Hot}} page reloading. True CSS management. CDN cache busting.

%

%

clozeq

---

## js: update npm and node

update npm

    sudo npm install -g {{c1::npm}}

update node

    sudo npm cache clean -f
    sudo npm install -g {{c2::n}}
    nvm {{c3::ls}}
    # check local node versions
    nvm ls-remote
    # check available node versions
    nvm {{c4::install}} 9.8.0
    # install node version


%

%

clozeq

---

## js: document.querySelector instead of jquery

    const {{c1::$}} = (e) => document.querySelector(e)
    const {{c1::$$}} = (e) => document.querySelectorAll(e)

    $('#root').innerHTML = `<h1>Parcel for VanillaJS</h1><br><time>${moment().format('LTS')}</time>`


%

%

clozeq

---

## js: study_parcel_jquery.Rmd 01 ES + CommonJS 

    const jquery = {{c1::require}}("jquery")
    window.$ = window.jQuery = jquery;
    require("jquery-ui-dist/jquery-ui.css")
    require("jquery-ui-dist/jquery-ui.js")
    {{c2::import}} {addText} from './app.js'


%

%

clozeq

---

## js: study_parcel_jquery.Rmd 02 parceljs steps

    mkdir my_project && cd $_ && npm init -y 
    npm i parcel-bundler jquery jquery-ui-dist

index.html

    <script src="./src/index.js"></script>

{{c1::index.js}}

    window.$ = require('jquery')
    {{c2::import}} {addText} from './app.js'

package.json

    "start": "{{c3::parcel}} index.html",
    "build": "parcel build index.html --public-url ./",

    npm start


%

%

clozeq

---

## js: study_parcel_jquery.Rmd 03 import jquery

import jquery and make it global variable

Differences in `index.js` 

    window.$ = require('jquery')
    --->>>
    import "./{{c1::import-jquery}}";

a separate file `import-jquery.js` to import `jquery`:

    import jquery from "jquery";
    {{c2::export}} default ({{c3::window.$}} = window.jQuery = jquery);


%

%

clozeq

---

## js: use unpkg
 
    <script src="https://unpkg.com/{{c1::jquery}}@3.1.1/dist/{{c2::jquery.js}}"></script>


%

%

clozeq

---

