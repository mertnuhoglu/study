import {makeHTTPDriver} from '@cycle/http'
import xs from 'xstream'
import onionify from 'cycle-onionify'
import {main as sub, subLens} from './ex07_sub'
import isolate from '@cycle/isolate'
import {run} from '@cycle/run'

export function intent() {
  const requests$ = xs.from(
    [
      {
        url: 'http://localhost:8080/rest/plan?select=plan_id,usr,depot_id',
        method: 'GET',
        headers: {
          "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
        },
        category: 'plan',
      },
    ]
  )
  return requests$
}
function model(httpSource) {
  const initReducer$ = xs.of(
    function initReducer(prevState) {
      const initialState = {
        plans: [], 
        purchase_orders: [],
        firstPlan: {},
      }
      return initialState
    }
  )
  const planReducer$ = httpSource.select('plan')
    .flatten()
    .map(res => function planReducer(prevState) {
      return {
        ...prevState, 
        plans: res.body
      }
    })
  return xs.merge(initReducer$, planReducer$)
}
function main(sources) {
  const component = isolate(sub, {onion: subLens})(sources)

  const parentRequests$ = intent()
  const requests$ = xs.merge(parentRequests$, component.HTTP)

  const parentReducer$ = model(sources.HTTP)
  const reducer$ = xs.merge(parentReducer$, component.onion)

  const state$ = xs.merge(sources.onion.state$, component.state$)
  state$.addListener({
    next: i => console.log(`state:: ${JSON.stringify(i)}`),
    error: err => console.error(err),
    complete: () => console.log('s1 completed'),
  })
  return {
    onion: reducer$,
    HTTP: requests$,
  }
}

const mainOnionified = onionify(main);
run(mainOnionified, {
  HTTP: makeHTTPDriver(),
})

// component state: {"plans":[],"purchase_orders":[],"firstPlan":[]}
// app state:: {"plans":[],"purchase_orders":[],"firstPlan":{}}
// component state: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2},{"plan_id":3,"usr":"usr_2_2_2_2_","depot_id":5},{"plan_id":4,"usr":"usr_1_1_1_1_1_1","depot_id":3},{"plan_id":5,"usr":"usr_1_1_1_1_1_1","depot_id":3}],"purchase_orders":[],"firstPlan":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4}]}
// app state:: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2},{"plan_id":3,"usr":"usr_2_2_2_2_","depot_id":5},{"plan_id":4,"usr":"usr_1_1_1_1_1_1","depot_id":3},{"plan_id":5,"usr":"usr_1_1_1_1_1_1","depot_id":3}],"purchase_orders":[],"firstPlan":{}}
// component state: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2},{"plan_id":3,"usr":"usr_2_2_2_2_","depot_id":5},{"plan_id":4,"usr":"usr_1_1_1_1_1_1","depot_id":3},{"plan_id":5,"usr":"usr_1_1_1_1_1_1","depot_id":3}],"purchase_orders":[],"firstPlan":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4}]}
// app state:: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2},{"plan_id":3,"usr":"usr_2_2_2_2_","depot_id":5},{"plan_id":4,"usr":"usr_1_1_1_1_1_1","depot_id":3},{"plan_id":5,"usr":"usr_1_1_1_1_1_1","depot_id":3}],"purchase_orders":[],"firstPlan":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4}]}
