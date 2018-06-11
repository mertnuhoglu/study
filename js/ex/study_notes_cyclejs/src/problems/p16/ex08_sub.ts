import xs from 'xstream'
import {Reducer, State as AppState} from './ex08'

export interface State {
  plans: Array<any>
  purchase_orders: Array<any>
  firstPlan: any
}

function intent() {
  const requests$ = xs.from(
    [
      {
        url: 'http://localhost:8080/rest/purchase_order?select=purchase_order_id,company_id,order_extid,company_extid',
        method: 'GET',
        headers: {
          "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
        },
        category: 'purchase_order',
      }
    ]
  )
  return requests$
}
function model(httpSource): xs<Reducer> {
  const initialReducer$: xs<Reducer> = xs.of( (prevState: State): State => ({
    plans: [],
    purchase_orders: [],
    firstPlan: {},
  }))
  const firstPlanReducer$: xs<Reducer> = httpSource.select('purchase_order')
    .flatten()
    .map( value => function firstPlanReducer(prevState) {
      return {
        ...prevState,
      }
    })
  return xs.merge(firstPlanReducer$)
}
export const subLens = {
  get: (state: AppState): State => ({
    ...state,
    firstPlan: state.plans.filter(plan => plan.plan_id === 1),
  }),
  set: (state: AppState, childState: State) => ({
    ...state,
    firstPlan: childState.firstPlan,
  })
}
export function main(sources) {
  return {
    state$: sources.onion.state$,
    onion: model(sources.HTTP),
    HTTP: intent(),
  }
}


