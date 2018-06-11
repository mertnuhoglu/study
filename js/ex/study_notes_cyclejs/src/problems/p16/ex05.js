const {makeHTTPDriver} = require('@cycle/http')
const xs = require('xstream').default
const onionify = require('cycle-onionify').default
const {main: sub, subLens} = require('./ex05_sub')
const {isolate} = require('./ex03_isolate')
const {run} = require('@cycle/run')

function intent() {
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
  const component = isolate(sub, {onion: subLens})
  const requests$ = intent()
  //const HTTP = makeHTTPDriver()
  //const httpSource = HTTP(requests$)
  const {onion: subReducer$} = component(sources)
  const parentReducer$ = model(sources.HTTP)
  const reducer$ = xs.merge(parentReducer$, subReducer$)
  const state$ = sources.onion.state$
  state$.addListener({
    next: i => console.log(`result: ${JSON.stringify(i)}`),
    error: err => console.error(err),
    complete: () => console.log('s1 completed'),
  })
  return {
    onion: reducer$,
    state$: state$,
    HTTP: requests$,
  }
}

function run2(main, drivers) {
  const proxySources = {}
  Object.keys(drivers).forEach(key => {
    proxySources[key] = new xs.create();
  })
  const sinks = main(proxySources);
  Object.keys(drivers).forEach(key => {
    const source = drivers[key](sinks[key]);
    source.subscribe(x => proxySources[key].next(x));
  })
}
const mainOnionified = onionify(main);
run(mainOnionified, {
  HTTP: makeHTTPDriver(),
})

// firstPlan$: []
// result: {"plans":[],"purchase_orders":[]}
// firstPlan$: [{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4}]
// result: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2},{"plan_id":3,"usr":"usr_2_2_2_2_","depot_id":5},{"plan_id":4,"usr":"usr_1_1_1_1_1_1","depot_id":3},{"plan_id":5,"usr":"usr_1_1_1_1_1_1","depot_id":3}],"purchase_orders":[]}

