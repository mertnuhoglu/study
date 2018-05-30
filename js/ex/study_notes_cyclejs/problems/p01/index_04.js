import xs from 'xstream';
import {run} from '@cycle/run';
import {div, label, input, hr, h1, makeDOMDriver} from '@cycle/dom';

function main(sources) {
  const vdom$ = xs.of(
    div("div01"),
  )
  vdom$.addListener({
    next: x => {
      console.log(Reflect.ownKeys(x))
    },
  })
  return {
    DOM: vdom$,
  };
}

run(main, {
  DOM: makeDOMDriver('#app')
});
