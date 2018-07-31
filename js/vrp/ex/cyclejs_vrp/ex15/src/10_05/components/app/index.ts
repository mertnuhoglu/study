import { div } from '@cycle/dom';
// import isolate, { Component } from '@cycle/isolate';
import isolate, {Component} from '../../cycle-isolate'
import xs from 'xstream';
import { Plan, PurchaseOrder, Sinks, Sources, SoHTTP, SiHTTP, SiHot } from '../../interfaces';
import DetailPanel from '../detail_panel';
import Header from '../header';
import { PlanPanel, lens as planPanelLens } from '../plan_panel';
import "./import_jquery";
import intent from './intent';
import model from './model';
import { addListenerStream } from '../../interfaces';

export interface State {
  plans: Array<Plan>
  purchase_orders: Array<PurchaseOrder>
  firstPlan: Plan | {}
}

export default function main(sources: Sources & SoHTTP): Sinks & SiHTTP & SiHot {
  const state$ = sources.onion.state$
    .debug(x => { console.log("app/index main state$"); console.log(x); })
  const header = isolate(Header, {onion: null, Hot: null}) as Component<Sources, Sinks>
  const headerSinks = header(sources)
  const planPanel = isolate(PlanPanel, {onion: planPanelLens, Hot: null}) as Component<Sources, Sinks & SiHTTP>
  const planPanelSinks = planPanel(sources)
  const detailPanel = isolate(DetailPanel, {onion: null, Hot: null}) as Component<Sources, Sinks & SiHot>
  const detailPanelSinks = detailPanel(sources)

  const parentReducer$ = model(sources)
    .debug(x => { console.log("app/index main parentReducer$"); console.log(x); })
  planPanelSinks.onion
    .debug(x => { console.log("app/index planPanelSinks onion reducer$"); console.log(x); })
  addListenerStream(planPanelSinks.onion, "planPanelSinks.onion")
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
  const {requests$: parentRequests$} = intent(sources)
  const requests$ = xs.merge(
    parentRequests$,
    planPanelSinks.HTTP,
  )
    .debug(x => { console.log("app/index main requests$"); console.log(x); })

  const hot$ = detailPanelSinks.Hot
    .debug(x => { console.log("app/index main hot$"); console.log(x); })
  const sinks: Sinks & SiHTTP & SiHot = {
    DOM: vdom$,
    HTTP: requests$,
    Hot: hot$,
    onion: reducer$,
  }
  return sinks;
}
