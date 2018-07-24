import { div } from '@cycle/dom';
// import isolate from '@cycle/isolate';
import { isolate } from '../../cycle-isolate'
import xs from 'xstream';
import { Plan, PurchaseOrder, Sinks, Sources } from '../../interfaces';
import DetailPanel from '../detail_panel';
import Header from '../header';
import { PlanPanel, lens as planPanelLens } from '../plan_panel';
import "./import_jquery";
import intent from './intent';
import model from './model';

export interface State {
  plans: Array<Plan>
  purchase_orders: Array<PurchaseOrder>
  firstPlan: Plan | {}
}

export type Reducer = (prev?: State) => State | undefined

export default function main(sources: Sources): Sinks {
  const state$ = sources.onion.state$
    .debug(x => { console.log("app/index main state$"); console.log(x); })
  const headerSinks = Header(sources)
  const planPanelSinks = isolate(PlanPanel, {storage: null, onion: planPanelLens, Hot: null})(sources)
  const detailPanelSinks = isolate(DetailPanel, {storage: null, onion: null, Hot: null})(sources)

  const parentReducer$ = model(sources)
    .debug(x => { console.log("app/index main parentReducer$"); console.log(x); })
  const reducer$ = xs.merge(
    parentReducer$, 
    planPanelSinks.onion
  )
    .debug(x => { console.log("app/index main reducer$"); console.log(x); })

  const vdom$ = xs.combine(
    headerSinks.DOM,
    planPanelSinks.DOM,
    detailPanelSinks.DOM,
  ).map( ([
    header, 
    plan_panel, 
    detail_panel
  ]) =>
    div('.vrp_container',
      [
        header,
        plan_panel,
        detail_panel, 
      ]
    )
  )
    .debug(x => { console.log("app/index main vdom$"); console.log(x); })
  const {actions, requests$: parentRequests$} = intent(sources)
  const requests$ = xs.merge(
    parentRequests$,
    planPanelSinks.HTTP,
  )
    .debug(x => { console.log("app/index main requests$"); console.log(x); })

  const hot$ = detailPanelSinks.Hot
    .debug(x => { console.log("app/index main hot$"); console.log(x); })
  const sinks: Sinks = {
    DOM: vdom$,
    HTTP: requests$,
    Hot: hot$,
    onion: reducer$,
  }
  return sinks;
}

