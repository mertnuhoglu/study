import xs from 'xstream'
import "./import_jquery";
import {div, makeDOMDriver} from '@cycle/dom';
import model from './model';
import intent from './intent';
import Header from '../header';
import PlanPanel from '../plan_panel';

export default function main(sources) {
  const parentReducer$ = model(sources.HTTP);
  const reducer$ = xs.merge(
    parentReducer$
  );
  const state$ = sources.onion.state$;
  const vdom$ = xs.combine(
    Header(),
    PlanPanel(state$),
  ).map( ([header, plan_panel]) =>
    div(
      [
        header,
        plan_panel,
      ]
    )
  )
  return {
    DOM: vdom$,
    HTTP: intent(sources.DOM),
    onion: reducer$,
  };
}
