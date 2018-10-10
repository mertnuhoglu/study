import { style } from 'typestyle/lib';
import { Plan, PurchaseOrder, Sinks, Sources, SoHTTP, SiHTTP } from '../../interfaces';
import { addListenerStream } from '../../interfaces';
import { State as AppState } from '../app';
import {intent} from './intent'
import model from './model'
import {view} from './view'

export interface State {
  plans: Array<Plan>
  purchase_orders: Array<PurchaseOrder>
  firstPlan: Plan | {}
}

export const lens = {
  get: (state: AppState): State => ({
    ...state,
    firstPlan: state.plans.filter(plan => plan.plan_id === 1),
  }),
  set: (state: AppState, childState: State) => ({
    ...state,
    purchase_orders: childState.purchase_orders,
    firstPlan: childState.firstPlan,
  })
}
const styles = style({
});


export function PlanPanel(sources: Sources & SoHTTP): Sinks & SiHTTP {
  const {requests$} = intent(sources.DOM)
	requests$.debug(x => { console.log("PlanPanel requests$"); console.log(x); })
  const reducer$ = model(sources)
  const state$ = sources.onion.state$
    .debug(x => { console.log("PlanPanel state$"); console.log(x); })
  addListenerStream(state$, "PlanPanel.state$")
  const sinks = {
    HTTP: requests$,
    onion: reducer$,
    DOM: view(state$),
  }
  return sinks
}
