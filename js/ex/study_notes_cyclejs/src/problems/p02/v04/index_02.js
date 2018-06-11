import xs from 'xstream';
import {run} from '@cycle/run';
import {div, td, label, input, hr, h1, makeDOMDriver} from '@cycle/dom';
import isolate from '@cycle/isolate';

function component(sources) {
  const state$ = xs.of({
    body: [
      {plan_id: 1, usr: "usr1", depot_id: 4},
      {plan_id: 2, usr: "usr2", depot_id: 2}
    ]})
  const vdom$ = state$
    .debug( (x) => console.log(x))
    .map( ({body}) =>
      div(
        body.map(e => 
          div([
            div(e.plan_id),
          ])
        ),
      ),
    )
    .debug( console.log )
    .debug( x => console.log(x.children) )
    .debug( x => {
      global.x = x
      console.log(x.children)
    } )
  return {
    DOM: vdom$,
  };
}

function main(sources) {
  const c1 = isolate(component)(sources)
  return {
    DOM: c1.DOM,
  };
}

run(main, {
  DOM: makeDOMDriver('#app')
});

