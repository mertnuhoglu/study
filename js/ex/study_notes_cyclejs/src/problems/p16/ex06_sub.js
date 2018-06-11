const xs = require('xstream').default

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
function model(httpSource) {
  const initialReducer$ = xs.of( prevState => ({
    firstPlan: {},
  }))
  const firstPlanReducer$ = httpSource.select('purchase_order')
    .flatten()
    .map( value => function firstPlanReducer(prevState) {
      return {
        ...prevState,
      }
    })
  return xs.merge(firstPlanReducer$)
}
const subLens = {
  get: (state) => ({
    ...state,
    firstPlan: state.plans.filter(plan => plan.plan_id === 1),
  }),
  set: (state, childState) => ({
    ...state,
    firstPlan: childState.firstPlan,
  })
}
function main(sources) {
  return {
    state$: sources.onion.state$,
    onion: model(sources.HTTP),
    HTTP: intent(),
  }
}

exports.main = main
exports.subLens = subLens
