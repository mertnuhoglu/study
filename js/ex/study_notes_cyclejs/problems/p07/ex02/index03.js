import xs from 'xstream';
import run from '@cycle/run';
//import {div, button, p, makeDOMDriver} from '@cycle/dom';
import {div, button, p, makeDOMDriver} from './dom';
import onionify from 'cycle-onionify';
            var toHTML = require('snabbdom-to-html')
            global.toHTML = toHTML

function main(sources) {
  const action$ = xs.merge(
    sources.DOM.select('.decrement').events('click').map(ev => -1)
      .debug()
    ,
    sources.DOM.select('.increment').events('click').map(ev => +1)
      .debug()
  );

  //const state$ = sources.onion.state$
    //.debug( x => {
      //global.x = x
      //console.log(x)
    //} )
  const state$ = xs.of([
    {"item_id": 1, "title": "item01", count: 3},
    {"item_id": 2, "title": "item02", count: 4},
  ])
  const state_onion$ = sources.onion.state$
    .addListener({
      next: data => {
        console.log("state_onion$")
        console.log(data)
      }}
    )

  const vdom$ = state$.map(state =>
    div([
      button('.decrement', 'Decrement'),
      button('.increment', 'Increment'),
      p('Counter: ' + state.count)
    ])
  );

  const initReducer$ = xs.of(function initReducer() {
    return {count: 0};
  });

  const updateReducer$ = action$.map(num => function updateReducer(prevState) {
    return {count: prevState.count + num};
  })

  const reducer$ = xs.merge(initReducer$, updateReducer$)
    .debug(console.log)

  return {
    DOM: vdom$,
    onion: reducer$,
  };
}

const wrappedMain = onionify(main);

run(wrappedMain, {
  DOM: makeDOMDriver('#app')
});
