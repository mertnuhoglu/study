
## What is onionify 01

A fractal state management tool:

State stream as {{c1::source}}, reducer stream as sink:

`main` can access state as:

··  `` const state$ = sources.{{c2::onion.state$;}} `` <br>

`main` returns a stream of reducer functions as:

··  `` return { `` <br>
····  `` onion: {{c3::reducer$}}, `` <br>

%

%

clozeq

---

## What is onionify 02

One large state tree for the {{c1::entire}} application:

All the state lives in the state stream managed by onionify.

%

%

clozeq

---

## Why use onionify 01

Simpler: State management with `props$` and `state$` is hard to understand. With onion, there is {{c1::no distinction}} between props and state. 

Eliminates {{c2::circular}} dependencies of stream

%

%

clozeq

---

## Article: Using cycle-onionify within a non-onionified Cycle.js app 01

index.ts 

··  `` import Counter from "./Counter"; `` <br>
··  `` function main(sources: Sources): Sinks { `` <br>
····  `` ... `` <br>
····  `` const counterSinks = {{c1::onionify}}({{c2::Counter}})(sources); `` <br>
····  `` const combinedVdom$ = xs.combine(vdom$, {{c3::counterSinks.DOM}}) `` <br>
······  `` .map({{c4::div}}); `` <br>
····  `` return { `` <br>
········  `` DOM: combinedVdom$, `` <br>

%

%

clozeq

---

## Article: Using cycle-onionify within a non-onionified Cycle.js app 02

`Counter.ts`:

··  `` export default function Counter(sources) { `` <br>
····  `` const action$ = xs.merge( sources.DOM.select... `` <br>
····  `` const {{c2::updateReducer$}} = {{c1::action$}}.map(num => function updateReducer(prevState) { `` <br>
······  `` return { count: prevState.count + num }; `` <br>
····  `` }); `` <br>
····  `` ... `` <br>
····  `` const reducer$ = xs.{{c3::merge}}(initReducer$, updateReducer$); `` <br>
····  `` ... `` <br>
····  `` const state$ = sources.{{c4::onion.state$}}; `` <br>
····  `` ... `` <br>
····  `` return { `` <br>
······  `` DOM: vdom$, `` <br>
······  `` onion: reducer$, `` <br>
····  `` }; `` <br>

%

%

clozeq

---

## Me: Data Flow of State in Onion 01

··  `` Component :: (DOMSource, onion.state$) -> ({{c1::reducer$}}, vdom$) `` <br>
··  `` Onion :: reducer$ -> onion.{{c2::state$}} `` <br>

%

%

clozeq

---

## Me: Data Flow of State in Onion 02

index.ts:

··  `` {{c1::onionify}}(Counter)(sources) `` <br>

Counter.ts:

··  `` {{c2::act1$}} = sources.DOM.select(..) `` <br>
··  `` {{c3::red1$}} = act1$.map(num => {{c4::state}} => {count: state.count + num}) `` <br>
··  `` red$ = xs.merge(red1$, red2$, ...) `` <br>
··  `` state$ = onion.sources.state `` <br>
··  `` vdom$ = {{c5::state$}}.map(..) `` <br>
··  `` return {onion: red$, DOM: vdom$} `` <br>

%

%

clozeq

---

## What is onionify 03

What is onionify?

It {{c1::wraps}} `main` function and provides it with an `onion`

··  `` import Counter from "./Counter"; `` <br>
··  `` function main(sources: Sources): Sinks { `` <br>
····  `` ... `` <br>
····  `` const counterSinks = onionify({{c2::Counter}})(sources); `` <br>

%

%

clozeq

---

## Article: Reducer Pattern in Cyclejs

https://staltz.com/reducer-pattern-in-cyclejs.html

Each {{c1::action stream}} is mapped to a reducer function

··  `` const setHighlightReducer$ = actions.setHighlight$ `` <br>
····  `` .map(highlighted => function setHighlightReducer(state) { `` <br>
······  `` return state.set('highlighted', highlighted) `` <br>
····  `` }) `` <br>

This produces a stream of reducer functions. We merge all of them to get {{c2::one stream}} of reducer functions.

··  `` xs.merge( `` <br>
······  `` moveHighlightReducer$, `` <br>
······  `` setHighlightReducer$, `` <br>

The stream of reducer functions is reduced to accumulate state over time.

··  `` reducer$.fold((acc, reducer) => reducer(acc), state) `` <br>

%

%

clozeq

---

## Code Reading: Cycle Onionify Examples Basic 01

··  `` const {{c1::action$}} = xs.merge( `` <br>
····  `` sources.DOM.select('.decrement').events('click').map(ev => -1), `` <br>
····  `` sources.DOM.select('.increment').events('click').map(ev => +1) `` <br>
··  `` ); `` <br>
··  `` const {{c6::initReducer$}} = xs.of(function initReducer() { `` <br>
····  `` return {count: 0}; `` <br>
··  `` }); `` <br>
··  `` const {{c2::updateReducer$}} = action$.map(num => function updateReducer({{c3::prevState}}) { `` <br>
····  `` return {count: prevState.count + num}; `` <br>
··  `` }); `` <br>
··  `` const {{c4::reducer$}} = xs.merge(initReducer$, updateReducer$); `` <br>
··  `` return { `` <br>
····  `` DOM: vdom$, `` <br>
····  `` {{c5::onion}}: reducer$, `` <br>
··  `` } `` <br>

%

%

clozeq

---

## Code Reading: Cycle Onionify Examples Basic 02

data flow: 

··  `` sources.DOM -> action$ -> updateReducer$ -> reducer$ -> sinks.{{c1::onion}} `` <br>

%

%

clozeq

---

## Code Reading: Cycle Onionify Examples Basic 03

data flow:

··  `` sources.onion.{{c1::state$}} -> vdom$ -> sinks.DOM `` <br>


%

%

clozeq

---

## Code Reading: Cycle Onionify Examples Basic 04

wrap `main`

··  `` const wrappedMain = {{c1::onionify}}(main); `` <br>
··  `` run(wrappedMain, { `` <br>
····  `` DOM: makeDOMDriver('#main-container') `` <br>
··  `` }); `` <br>

%

%

clozeq

---

## Code Read: onion architecture 01

sliderInput/ index.js intent.js model.js view.js styles.js 

··  `` export function {{c1::SliderInput}}(sources: Sources): Sinks { `` <br>
··  `` export function {{c2::intent}}(domSource): SliderInputActions { `` <br>
··  `` export function {{c3::model}}(actions: SliderInputActions): xs<Reducer> { `` <br>
··  `` export function {{c4::view}}(state$: xs<State>): xs<VNode> { `` <br>
··  `` export const {{c5::styles}} = stylesheet({ `` <br>

%

%

clozeq

---

## Code Read: onion architecture 02

index.js

··  `` ex: controls/index.js `` <br>
····  `` export interface {{c1::State}} { `` <br>
····  `` export const {{c2::lens}} = { `` <br>
····  `` export function {{c3::Controls}}(sources: Sources): Sinks { `` <br>
······  `` # {{c4::model view intent}} `` <br>

%

%

clozeq

---

## Code Read: onion architecture 03

export interface State

··  `` export interface {{c1::State}} { `` <br>
····  `` description: string; `` <br>
····  `` unit: string; `` <br>
····  `` min: number; `` <br>

%

%

clozeq

---

## Code Read: onion architecture 04

lenses: ex: controls/index.js

··  `` export const {{c1::lens}} = { `` <br>
····  `` {{c2::get}}: (state: {{c3::AppState}}): {{c4::State}} => ({ `` <br>
······  `` currency: {{c5::state}}.currency, `` <br>
······  `` personAmount: state.personAmount, `` <br>
······  `` avgPrice: state.avgPrice `` <br>
····  `` }), `` <br>
····  `` set: (state: AppState, childState: State) => ({ `` <br>
······  `` ...state, `` <br>
······  `` currency: childState.currency, `` <br>
······  `` personAmount: childState.personAmount, `` <br>
······  `` avgPrice: childState.avgPrice `` <br>
····  `` }) `` <br>
··  `` }; `` <br>

%

%

clozeq

---

## Code Read: onion architecture 05

model view intent: ex: app/index.js

··  `` const parentReducer$ = {{c1::model}}(); `` <br>
··  `` const tickerSinks = isolate(Ticker, { onion: {{c2::tickerLens}} })(sources); `` <br>
··  `` const reducer$ = xs.merge( {{c3::parentReducer$}}, tickerSinks.onion,.. `` <br>
··  `` const headerVDom$: xs<VNode> = {{c4::Header}}(); `` <br>
··  `` const vdom$ = xs.combine( headerVDom$, {{c5::tickerSinks.DOM}}, .. `` <br>


%

%

clozeq

---

## Code Read: onion architecture 06

model view intent: ex: controls/index.ts function Controls:

··  `` const actions: Actions = {{c1::intent}}(sources.DOM); `` <br>
··  `` const parentReducer$: xs<Reducer> = model({{c2::actions}}); `` <br>
··  `` const personAmountReducer$: xs<Reducer> = {{c3::personAmountSlider}}.onion; `` <br>
··  `` const reducer$: xs<Reducer> = xs.merge( parentReducer$, {{c4::personAmountReducer$}}, .. `` <br>
··  `` const vdom$ = view(state$, personAmountSlider.DOM, avgPriceSlider.DOM); `` <br>


%

%

clozeq

---

## Code Read: onion architecture 07

data flow: {{c1::DOM}} -&gt; state

··  `` sources.DOM -> action$ -> {{c2::reducer_i$}} -> reducer$ -> sinks.onion `` <br>
····  `` act1$ = sources.DOM.select(..) `` <br>
····  `` red1$ = act1$.map(num => state => {count: state.count + num}) `` <br>
····  `` red$ = xs.merge(red1$, red2$, ...) `` <br>
····  `` return {onion: red$, DOM: vdom$} `` <br>


%

%

clozeq

---

## Code Read: onion architecture 08

data flow: {{c1::state}} -&gt; DOM

··  `` sources.{{c2::onion.state}} -> state$ -> vdom$ -> sinks.DOM `` <br>
····  `` state$ = onion.sources.state `` <br>
····  `` vdom$ = state$.map(..) `` <br>
····  `` return {onion: red$, DOM: vdom$} `` <br>

%

%

clozeq

---

## Code Read: onion architecture 09

use subcomponents: ex: app/index.js

··  `` const tickerSinks = {{c1::isolate}}(Ticker, { onion: tickerLens })(sources); `` <br>
··  `` const controlsSinks = isolate({{c2::Controls}}, { {{c3::onion:}} controlsLens })(sources); `` <br>


%

%

clozeq

---

## Code Read: onion architecture 10

use subcomponents: ex: controls/index.js

··  `` const personAmountSlider: Sinks = isolate(SliderInput, { onion: personAmountLens })({{c1::sources}}); `` <br>
··  `` const avgPriceSlider: Sinks = isolate(SliderInput, { onion: avgPriceLens })(sources); `` <br>

%

%

clozeq

---

## Code Read: onion architecture 11

intent(): ex: controls/intent.js

··  `` export function intent(domSource): Actions { `` <br>
····  `` const currencyChangeAction$: xs<string> = {{c1::domSource}} `` <br>
······  `` .select(`.${styles.currencySelect}`) `` <br>
······  `` .events('change') `` <br>
······  `` .map(inputEv => (inputEv.target as HTMLInputElement).{{c2::value}}); `` <br>

%

%

clozeq

---

## Code Read: onion architecture 12

model(): ex: app/model.js

··  `` export default function model(): xs<Reducer> { `` <br>
····  `` const {{c1::initReducer$}}: xs<Reducer> = xs.of( `` <br>
······  `` ({{c2::prev?}}: State): State => `` <br>
········  `` prev !== undefined `` <br>
··········  `` ? prev `` <br>
··········  `` : { `` <br>
··············  `` startTime: moment(), `` <br>
··············  `` duration: {{c3::0}}, `` <br>


%

%

clozeq

---

## Code Read: onion architecture 13

model(): ex: ticker/model.js

··  `` export default function model(timeSource: TimeSource): xs<Reducer> { `` <br>
····  `` const initReducer$: xs<Reducer> = xs.of( `` <br>
······  `` (prev?: State): State => `` <br>
········  `` prev !== undefined `` <br>
··········  `` ? prev `` <br>
··········  `` : { `` <br>
··············  `` startTime: moment(), `` <br>
··············  `` duration: 0, `` <br>
····  `` const tickReducer$: xs<Reducer> = timeSource `` <br>
······  `` .periodic(1000) `` <br>
······  `` .map(i => (prevState: State): State => ({ `` <br>
····  `` return xs.merge({{c1::initReducer$, tickReducer$}}); `` <br>


%

%

clozeq

---

## Code Read: onion architecture 14

initReducer

··  `` const initReducer$: xs<Reducer> = xs.of( `` <br>
····  `` (prev?: State): State => `` <br>
······  `` prev !== {{c1::undefined}} `` <br>
········  `` ? prev `` <br>
········  `` : { `` <br>
············  `` startTime: moment(), `` <br>
············  `` duration: 0, `` <br>

%

%

clozeq

---

## Code Read: onion architecture 15

view(): ex: sliderInput/view.js

··  `` export default function view(state$: xs<State>): xs<VNode> { `` <br>
····  `` return {{c1::state$}}.map(({ description, unit, min, max, step, value }) => `` <br>
······  `` {{c2::div}}(`.${{{c3::styles}}.sliderInput}`, [ `` <br>

%

%

clozeq

---

## Code Read: onion architecture 16

styles: ex: app/styles.js

··  `` export const styles = {{c1::stylesheet}}({ `` <br>
····  `` flexContainer: { `` <br>
······  `` {{c2::height: '100%'}}, `` <br>

%

%

clozeq

---

## Code Read: onion architecture 17

App State and App Sources & Sinks

··  `` import { {{c1::Sources, Sinks}} } from '../../{{c2::interfaces}}'; `` <br>
··  `` import { State as {{c3::AppState}} } from '../app'; `` <br>

%

%

clozeq

---

## Code Read: onion architecture 18

interfaces.js

··  `` export type Sources = { `` <br>
····  `` DOM: DOMSource; `` <br>
····  `` {{c1::onion}}: StateSource<State>; `` <br>
··  `` export type Sinks = { `` <br>
····  `` DOM: xs<{{c2::VNode}}>; `` <br>

%

%

clozeq

---

## Code Read: onion architecture 19

header.js footer.js

··  `` export function {{c1::Header}}(): xs<VNode> { `` <br>
····  `` return xs.of( `` <br>
······  `` header(`.${headerStyles}`, [h1('Header-title', 'Meeting price calculator')]) `` <br>

%

%

clozeq

---

## Code Read: onion architecture 20

App: index.js: vdom$

··  `` const headerVDom$: xs<VNode> = Header(); `` <br>
··  `` const vdom$ = xs `` <br>
····  `` .combine( `` <br>
······  `` {{c1::headerVDom$}}, `` <br>
······  `` tickerSinks.DOM, `` <br>
····  `` .map(([{{c2::header, ticker}}, controls, duration, footer]) => `` <br>
····  `` div(`.${styles.flexContainer}`, [ `` <br>
······  `` {{c3::header}}, `` <br>

%

%

clozeq

---

## Me: Data Flow of State in Onion 03

DOM -&gt; state vs state -&gt; DOM

··  `` DOM -> state `` <br>
····  `` sources.DOM -> [intent] -> [{{c1::model}}] -> [onion] -> state$ `` <br>
····  `` intent(DOM$): action$ -> model(action$): {{c2::reducer$}} -> onion(reducer$): state$ `` <br>
··  `` state -> DOM `` <br>
····  `` sources.onion.state -> [{{c3::view}}] -> sinks.DOM `` <br>

%

%

clozeq

---

## Me: Data Flow of State in Onion 04

onion as a Driver

··  `` {DOM$, {{c1::state$}}} -> [{{c2::Component}}] -> {vdom$, reducer$} `` <br>
··  `` {vdom$} -> [DOMDriver] -> {DOM$} -> [Component] `` <br>
··  `` {{c3::{reducer$} }} -> [onion] -> {{c4::{state$} }} -> [Component] `` <br>

%

%

clozeq

---

## Code Read: onion architecture 21

common templates of the architecture:

··  `` {{c1::index.js}} intent.js model.js view.js styles.js  `` <br>
··  `` {{c2::index.js}} `` <br>
··  `` export interface {{c3::State}} `` <br>
··  `` {{c4::lenses}} `` <br>
··  `` {{c5::intent model view}} `` <br>
··  `` {{c6::data flow}} `` <br>
··  `` {{c7::use subcomponents}}: `` <br>
··  `` {{c8::intent()}} `` <br>
··  `` {{c9::model()}} `` <br>
··  `` {{c17::setter lens}}: [model] -> reducer$ -> [onion] `` <br>
··  `` {{c10::initReducer}} `` <br>
··  `` {{c11::view() }} `` <br>
··  `` {{c12::styles}} `` <br>
··  `` {{c13::App State}} and App Sources & Sinks `` <br>
··  `` {{c14::interfaces.js }} `` <br>
··  `` {{c15::header.js}} footer.js `` <br>
··  `` {{c16::App}} `` <br>

%

%

clozeq

---

## Me: Data Flow of State in Onion 05

complete data cycle

··  `` {DOMSource} -> [App.{{c1::intent}}] -> {action$} -> [App.model] ->  `` <br>
··  `` {reducer$} -> [{{c2::onion}}] -> {state$} -> [App.view] ->  `` <br>
··  `` {vdom$} -> [{{c3::DomDriver}}] -> {DOMSource} `` <br>

%

%

clozeq

---

## Code Read: onion architecture 22

ex: ticker/model.js

··  `` function model(timeSource){ `` <br>
····  `` const initReducer$: xs = xs.of( ... `` <br>
····  `` const tickReducer$: xs = timeSource `` <br>
······  `` .periodic(1000) `` <br>
······  `` .map(i => (prevState: State): State => ({ `` <br>
········  `` {{c1::...prevState}}, `` <br>
········  `` duration: moment().diff({{c2::prevState.startTime}}, 'seconds') `` <br>
······  `` return xs.{{c3::merge}}(initReducer$, tickReducer$); `` <br>

%

%

clozeq

---

## Code Read: onion architecture 23

ex: sliderInput/model.js

··  `` function model(actions) { `` <br>
····  `` const defaultReducer$: xs = xs.of( ... `` <br>
····  `` const valueChangeReducer$: xs = actions.ValueChangeAction$.map( `` <br>
······  `` value => (prevState: State): State => ({ `` <br>
········  `` {{c1::...prevState}}, `` <br>
········  `` {{c2::value}} `` <br>
····  `` return xs.merge(defaultReducer$, valueChangeReducer$); `` <br>

%

%

clozeq

---

## Code Read: onion architecture 24

ex: ticker/view.js

··  `` function view(state$) { `` <br>
····  `` return state$.map({{c1::({ currency, totalPrice })}} => `` <br>
······  `` div(`.${styles.actualPrice}`, [ `` <br>

%

%

clozeq

---

## Code Read: onion architecture 25

setter lens: [model] -&gt; reducer$ -&gt; [onion]

ex: sliderInput/

··  `` model.js `` <br>
····  `` const valueChangeReducer$ = actions.ValueChangeAction$.map( `` <br>
······  `` value => (prevState) => ({ `` <br>
········  `` ...prevState, `` <br>
········  `` {{c1::value}} `` <br>
··  `` index.js `` <br>
····  `` const personAmountLens = { `` <br>
······  `` get: (state) => ({ ... `` <br>
······  `` set: (state: AppState, childState: State) => ({ `` <br>
········  `` ...state, `` <br>
········  `` personAmount: {{c2::childState}}.{{c3::value}} `` <br>
····  `` export const avgPriceLens = { `` <br>
······  `` get: (state) => ({ ... `` <br>
······  `` set: (state: AppState, childState: State) => ({ `` <br>
········  `` ...state, `` <br>
········  `` avgPrice: childState.value `` <br>

%

%

clozeq

---

## Code Read: onion architecture 26

setter lens: [model] -&gt; reducer$ -&gt; [onion]

ex: ticker/

··  `` model.js `` <br>
····  `` const tickReducer$ = timeSource `` <br>
······  `` .periodic(1000) `` <br>
······  `` .map(i => (prevState) => ({ `` <br>
········  `` {{c1::...prevState}}, `` <br>
········  `` {{c2::duration}}: moment().diff({{c3::prevState}}.startTime, 'seconds') `` <br>
··  `` index.js `` <br>
····  `` export const lens = { `` <br>
······  `` get: (state) => ... `` <br>
······  `` set: (state: AppState, childState: State) => ({ `` <br>
········  `` ...state, `` <br>
········  `` duration: childState.{{c4::duration}} `` <br>

%

%

clozeq

---

