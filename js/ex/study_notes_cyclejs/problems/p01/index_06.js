import xs from 'xstream';
import {run} from '@cycle/run';
import {div, label, input, hr, h1, makeDOMDriver} from '@cycle/dom';

function main(sources) {
  const state$ = xs.of({a: 1})
  const vdom$ = state$.map( (x) =>
    div(`div ${x.a}`),
  )
  return {
    DOM: vdom$,
  };
}

run(main, {
  DOM: makeDOMDriver('#app')
});

