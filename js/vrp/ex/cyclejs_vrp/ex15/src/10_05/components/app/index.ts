import xs from 'xstream';
import { div } from '@cycle/dom';
import isolate, {Component} from '../../cycle-isolate'
import { Plan, PurchaseOrder, Sinks, Sources, SoHTTP, SiHTTP, SiHot } from '../../interfaces';
import { addListenerStream } from '../../interfaces';
import "./import_jquery";
import Header from '../header';
import DetailPanel from '../detail_panel';
import { PlanPanel, lens as planPanelLens } from '../plan_panel';
import intent from './intent';
import model from './model';

export interface State {
  plans: Array<Plan>
  purchase_orders: Array<PurchaseOrder>
  firstPlan: Plan | {}
}

export default function main(sources: Sources & SoHTTP): Sinks & SiHTTP & SiHot {
  const header = isolate(Header, {onion: null, Hot: null}) as Component<Sources, Sinks>
  const headerSinks = header(sources)
  const planPanel = isolate(PlanPanel, {onion: planPanelLens, Hot: null}) as Component<Sources, Sinks & SiHTTP>
  const planPanelSinks = planPanel(sources)
  const detailPanel = isolate(DetailPanel, {onion: null, Hot: null}) as Component<Sources, Sinks & SiHot>
  const detailPanelSinks = detailPanel(sources)

  const {requests$: parentRequests$} = intent(sources)
  const requests$ = xs.merge(
    parentRequests$,
    planPanelSinks.HTTP,
  )
    .debug(x => { console.log("app/index main requests$"); console.log(x); })

  const parentReducer$ = model(sources)
    .debug(x => { console.log("app/index main parentReducer$"); console.log(x); })
  const reducer$ = xs.merge(
    parentReducer$, 
    planPanelSinks.onion
  )
    .debug(x => { console.log("app/index main reducer$"); console.log(x); })

  const state$ = sources.onion.state$
    .debug(x => { console.log("app/index main state$"); console.log(x); })
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

  const hot$ = detailPanelSinks.Hot
    .debug(x => { console.log("app/index main hot$"); console.log(x); })

  const sinks: Sinks & SiHTTP & SiHot = {
    HTTP: requests$,
    onion: reducer$,
    DOM: vdom$,
    Hot: hot$,
  }
  return sinks;
}

