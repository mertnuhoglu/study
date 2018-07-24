import xs from 'xstream';
import { State } from '.';
import { Reducer, Sources } from '../../interfaces';
import { addListenerStream } from '../../interfaces';

export default function model(sources: Sources): xs<Reducer> {
  const initReducer$: xs<Reducer> = xs.of(
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
  const purchaseOrderReducer$: xs<Reducer> = sources.HTTP.select('purchase_order')
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


