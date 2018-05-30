import xs from 'xstream';
import {run} from '@cycle/run';
import {div, makeDOMDriver} from '@cycle/dom';

function main(sources) {
  const vdom$ = xs.of(
    div("planet earth")
  )
  var a = [1,2,3]
  var b = [...a]
  console.log(a)

  return {
    DOM: vdom$,
  };
}

run(main, {
  DOM: makeDOMDriver('.todoapp')
});

