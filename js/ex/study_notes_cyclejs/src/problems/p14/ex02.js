const {makeHTTPDriver} = require('@cycle/http')
const xs = require('xstream').default

const HTTP = makeHTTPDriver()
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
      {
        url: 'http://localhost:8080/rest/purchase_order?select=purchase_order_id,company_id,order_extid,company_extid',
        method: 'GET',
        headers: {
          "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
        },
        category: 'purchase_order',
      },
    ]
  )
  return requests$
}

const requests$ = intent()
const httpSource = HTTP(requests$)

const initialState = {
  plans: [], 
  purchase_orders: [],
}
const planReducer$ = httpSource.select('plan')
  .flatten()
  .map(res => function planReducer(prevState) {
    return {
      ...prevState,
      plans: res.body
    }
  })
const purchaseOrderReducer$ = httpSource.select('purchase_order')
  .flatten()
  .map(res => function purchaseOrderReducer(prevState) {
    return {
      ...prevState,
      purchase_orders: res.body
    }
  })
const reducer$ = xs.merge(planReducer$, purchaseOrderReducer$)
//const rs$ = xs.combine(planReducer$, purchaseOrderReducer$)
  //.map( ([planReducer, purchaseOrderReducer]) => ({
    //plans: plans,
    //purchase_orders: purchase_orders,
  //}))

const state$ = reducer$
  .fold((prevState, reducer) => reducer(prevState), initialState);

state$.addListener({
  next: i => console.log(`result: ${JSON.stringify(i)}`),
  error: err => console.error(err),
  complete: () => console.log('s1 completed'),
})
// result: {"plans":[],"purchase_orders":[]}
// result: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2},{"plan_id":3,"usr":"usr_2_2_2_2_","depot_id":5},{"plan_id":4,"usr":"usr_1_1_1_1_1_1","depot_id":3},{"plan_id":5,"usr":"usr_1_1_1_1_1_1","depot_id":3}],"purchase_orders":[]}
// result: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2},{"plan_id":3,"usr":"usr_2_2_2_2_","depot_id":5},{"plan_id":4,"usr":"usr_1_1_1_1_1_1","depot_id":3},{"plan_id":5,"usr":"usr_1_1_1_1_1_1","depot_id":3}],"purchase_orders":[{"purchase_order_id":1,"company_id":2,"order_extid":"order_extid_2","company_extid":"company_e"},{"purchase_order_id":2,"company_id":5,"order_extid":"order_extid_2","company_extid":"company_e"},{"purchase_order_id":3,"company_id":3,"order_extid":"order_extid_2","company_extid":"company_e"},{"purchase_order_id":4,"company_id":1,"order_extid":"order_extid_2","company_extid":"company_e"},{"purchase_order_id":5,"company_id":1,"order_extid":"order_extid_2","company_extid":"company_e"}]}
// s1 completed
