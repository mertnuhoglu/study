import xs from 'xstream';
import {run} from '@cycle/run';
import {div, makeDOMDriver} from '@cycle/dom';

function main(sources) {
  const vdom$ = xs.of(
    div("planet earth")
  )
  return {
    DOM: vdom$,
  };
}

run(main, {
  DOM: makeDOMDriver('#app')
});

