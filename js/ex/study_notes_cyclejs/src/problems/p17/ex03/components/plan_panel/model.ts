import xs from 'xstream';
import { Reducer, State } from '.';

export default function model(sources): xs<Reducer> {
  const initReducer$: xs<Reducer> = xs.of(
    function initReducer(prevState: State) {
      const initialState = {
        purchase_orders: [],
      }
      return initialState
    }
  )
  const purchaseOrderReducer$: xs<Reducer> = sources.HTTP.select('purchase_order')
    .flatten()
    .map(res => function purchaseOrderReducer(prevState: State) {
      return {
        ...prevState,
        purchase_orders: res.body
      }
    })
  return xs.merge(initReducer$, purchaseOrderReducer$)
    .debug(x => { console.log("PlanPanel model return$"); console.log(x); })
}


