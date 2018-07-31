import { State } from '.';
import { Reducer, Sources, ActionPayload, SoHTTP, DictStream } from '../../interfaces';
import { addListenerStream } from '../../interfaces';
import Stream from 'xstream';
import xs from 'xstream';

export default function model(sources: Sources & SoHTTP): xs<Reducer<State>> {
  const initReducer$: Stream<Reducer<State>> = xs.of(
    function initReducer(prevState: State) {
      const initialState = {
        plans: [],
        purchase_orders: [],
        firstPlan: {},
      }
      return initialState
    }
  )
  const poraw = sources.HTTP.select("purchase_order").flatten()
  addListenerStream(poraw, "poraw$")
  const purchaseOrderReducer$: xs<Reducer<State>> = sources.HTTP.select('purchase_order')
    .flatten()
    .map(res => function purchaseOrderReducer(prevState: State) {
      return {
        ...prevState,
        purchase_orders: res.body
      }
    })
  // addListenerStream(purchaseOrderReducer$, "purchaseOrderReducer$")
  return xs.merge(initReducer$,
    purchaseOrderReducer$
  )
    .debug(x => { console.log("PlanPanel model return$"); console.log(x); })
}