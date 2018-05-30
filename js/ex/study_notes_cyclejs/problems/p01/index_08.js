import xs from 'xstream';
import {run} from '@cycle/run';
import {div, label, input, hr, h1, makeDOMDriver} from '@cycle/dom';

function component(sources) {
  const state$ = xs.of({a: 1})
  const vdom$ = state$.map( (x) =>
    div(`div${x.a}`),
  )
	return {
		DOM: vdom$,
	};
}

function main(sources) {
	const c1 = component(sources)
  return {
    DOM: c1.DOM,
  };
}

run(main, {
  DOM: makeDOMDriver('#app')
});

