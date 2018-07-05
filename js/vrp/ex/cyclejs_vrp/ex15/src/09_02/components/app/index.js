import xs from 'xstream'
import "./import_jquery";
import {div, makeDOMDriver} from '@cycle/dom';
import isolate from '@cycle/isolate';
import model from './model';
import intent from './intent';
import Header from '../header';
import PlanPanel from '../plan_panel';
import DetailPanel from '../detail_panel';

export default function main(sources) {
  //const state$ = model(sources)
  const initialState = {
    plans: [], 
    purchase_orders: [],
  }
  const reducer$ = model(sources)
  const state$ = reducer$
    .fold((prevState, reducer) => reducer(prevState), initialState);
  const headerSinks = isolate(Header, {storage: null, onion: null, Hot: null})(sources)
  const planPanelSinks = isolate(PlanPanel, {storage: null, onion: null, Hot: null})(sources, state$)
  const detailPanelSinks = isolate(DetailPanel, {storage: null, onion: null, Hot: null})(sources, state$)
  const vdom$ = xs.combine(
    headerSinks.DOM,
    planPanelSinks.DOM,
    detailPanelSinks.DOM,
  ).map( ([header, plan_panel, detail_panel]) =>
    div('.vrp_container',
      [
        header,
        plan_panel,
        detail_panel, 
      ]
    )
  )

  const hot$ = detailPanelSinks.Hot
  return {
    DOM: vdom$,
    HTTP: intent(sources),
    Hot: hot$,
  };
}

