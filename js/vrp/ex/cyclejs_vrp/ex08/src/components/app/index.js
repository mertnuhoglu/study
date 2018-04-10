import xs from 'xstream'
import {makeDOMDriver} from '@cycle/dom';
import model from './model';
import view from './view';
import intent from './intent';

export default function main(sources) {
  const parentReducer$ = model(sources.HTTP);
  const reducer$ = xs.merge(
    parentReducer$
  );
  const state$ = sources.onion.state$;
  const vdom$ = view(state$);
  return {
    DOM: vdom$,
    HTTP: intent(sources.DOM),
    onion: reducer$,
  };
}

