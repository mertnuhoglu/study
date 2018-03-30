import xs from 'xstream';
import {run} from '@cycle/run'
import {button, h1, h4, a, div, table, thead, tbody, tr, td, th, makeDOMDriver} from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';
import onionify from 'cycle-onionify';

function main(sources) {
  const state$ = sources.onion.state$;
  const vdom$ = state$.map(state => div(`Hello App ${state}`));

  const initialReducer$ = xs.of(function initialReducer() { return 0; });
  const addOneReducer$ = xs.periodic(1000)
    .mapTo(function addOneReducer(prev) { return prev + 1; });
  const reducer$ = xs.merge(initialReducer$, addOneReducer$);

  return {
    DOM: vdom$,
    onion: reducer$,
  };
}

const wrappedMain = onionify(main);

run(wrappedMain, {
  DOM: makeDOMDriver('#app'),
});

