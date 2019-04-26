import Stream from 'xstream';
import xs from 'xstream';
import { Reducer, Sources, ActionPayload, SoHTTP, DictStream } from '../../interfaces';
import { addListenerStream } from '../../interfaces';
import { State } from '.';
import * as R from "ramda";

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
		.map(response => response.body)
    .map(plans => function purchaseOrderReducer(prevState: State) {
			//console.log("por01: "); console.log(JSON.stringify(plans))
			let pos = R.pipe(
				R.map(a => R.prop('order_line', a).map(R.prop('purchase_order'))),
				//R.map(a => a.order_line.map(b => b.purchase_order)),
				R.unnest
			)(plans)
			return { 
				...prevState,
        purchase_orders: pos
      }
    })
  // addListenerStream(purchaseOrderReducer$, "purchaseOrderReducer$")
  return xs.merge(initReducer$,
    purchaseOrderReducer$
  )
    .debug(x => { console.log("PlanPanel model return$"); console.log(x); })
}
