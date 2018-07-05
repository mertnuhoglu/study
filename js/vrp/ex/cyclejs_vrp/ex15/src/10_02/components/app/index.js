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
  const reducer$ = model(sources)
  const state$ = sources.onion.state$
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
  const {actions, requests$: parentRequests$} = intent(sources)
  const requests$ = xs.merge(
    parentRequests$,
    planPanelSinks.HTTP,
  )

  const hot$ = detailPanelSinks.Hot
  return {
    DOM: vdom$,
    HTTP: requests$,
    Hot: hot$,
    onion: reducer$,
  };
}

