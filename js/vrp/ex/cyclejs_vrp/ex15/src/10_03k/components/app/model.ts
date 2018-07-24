import xs from 'xstream';
import { State } from '.';
import { Reducer, Sources } from '../../interfaces';

export default function model(sources: Sources): xs<Reducer> {
  const initReducer$: xs<Reducer> = xs.of(
    function initReducer(prevState: State) {
      const initialState: State = {
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
      const result: State = {
        ...prevState,
        plans: res.body
      }
      return result
    })
  return xs.merge(initReducer$, 
    planReducer$
  )
    .debug(x => { console.log("app model return$"); console.log(x); })
}


