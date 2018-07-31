import { style } from 'typestyle/lib';
import { Plan, PurchaseOrder, Sinks, Sources, SoHTTP, SiHTTP } from '../../interfaces';
import { State as AppState } from '../app';
import model from './model'
import {view} from './view'
import {intent} from './intent'
import { addListenerStream } from '../../interfaces';

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
  const state$ = sources.onion.state$
    .debug(x => { console.log("PlanPanel state$"); console.log(x); })
  addListenerStream(state$, "PlanPanel.state$")
  const {requests$} = intent(sources.DOM)
	requests$
    .debug(x => { console.log("PlanPanel requests$"); console.log(x); })
  const reducer$ = model(sources)
  const sinks = {
    DOM: view(state$),
    HTTP: requests$,
    onion: reducer$,
  }
  return sinks
}
