
## rxjs ex 01

··  &lt;body&gt; <br>
··  &lt;div id="{{c1::app}}"&gt;&lt;/div&gt; <br>
··  &lt;{{c2::script}} type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/rxjs/5.5.6/Rx.min.js"&gt;&lt;/{{c2::script}}&gt; <br>
··  &lt;{{c3::script}}&gt; <br>
····  Rx.Observable.{{c4::timer}}(0, 1000) <br>
······  .{{c5::map}}(i =&gt; `Seconds ${i}`) <br>
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
········  .map(i =&gt; `Seconds ${i}`) <br>

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
······  .map(i =&gt; `Seconds ${i}`); <br>
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
········  .map(i =&gt; `Seconds ${i}`), <br>
······  Log: Rx.Observable.timer(0, 2000) <br>
········  .map(i =&gt; 2*i), <br>
····  }; <br>
··  } <br>

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
············  .map(i =&gt; `Seconds ${i}`) <br>
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
········  .map(i =&gt; `Seconds ${i}`) <br>
····  ), <br>

····  ---&gt;&gt;&gt; <br>

····  .flatMapLatest(() =&gt; <br>
······  Rx.Observable.timer(0, 1000) <br>
········  .map(i =&gt; { <br>
············  return { <br>
··············  {{c1::tagName}}: 'H1', <br>
··············  {{c2::children}}: [ <br>
················  `Seconds ${i}` <br>
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
················  `Seconds ${i}` <br>
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
····················  `Seconds ${i}` <br>
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
····················  `Seconds ${i}` <br>
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
··················  `Seconds ${i}` <br>
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
··················  `Seconds ${i}` <br>
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
··················  `Seconds ${i}` <br>
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
··········  h1(`Hello ${name}!`) <br>
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

