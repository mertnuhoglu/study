import xs from 'xstream';
import {run} from '@cycle/run';
import {div, makeDOMDriver} from '@cycle/dom';

function main(sources) {
  const vdom$ = xs.of(
    div("planet earth")
  )
  let x = "x"
  let y = "y"
  window.x = x
  global.y = y
  return {
    DOM: vdom$,
  };
}

run(main, {
  DOM: makeDOMDriver('#app')
});
