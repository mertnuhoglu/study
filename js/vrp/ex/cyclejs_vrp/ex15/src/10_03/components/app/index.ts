import { div } from '@cycle/dom';
import isolate from '@cycle/isolate';
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
  const headerSinks = isolate(Header, {storage: null, onion: null, Hot: null})(sources)
  const planPanelSinks = isolate(PlanPanel, {storage: null, onion: planPanelLens, Hot: null})(sources)
  const detailPanelSinks = isolate(DetailPanel, {storage: null, onion: null, Hot: null})(sources)

  const parentReducer$ = model(sources)
  const reducer$ = xs.merge(parentReducer$, planPanelSinks.onion)

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
  const sinks: any = {
    DOM: vdom$,
    HTTP: requests$,
    Hot: hot$,
    onion: reducer$,
  }
  return sinks;
}

