import xs from 'xstream';
import { Reducer, State } from '.';

export default function model(sources): xs<Reducer> {
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
  const planReducer$: xs<Reducer> = sources.HTTP.select('plan')
    .flatten()
    .map(res => function planReducer(prevState: State) {
      return {
        ...prevState,
        plans: res.body
      }
    })
  return xs.merge(initReducer$, 
    planReducer$
  )
    .debug(x => { console.log("app model return$"); console.log(x); })
}


