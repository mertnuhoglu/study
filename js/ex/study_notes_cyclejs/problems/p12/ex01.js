const xs = require('xstream').default

const plans$ = xs.of( [
  { "plan_id": 1, "usr": "usr_4_4_4_4_4_4", "depot_id": 4 },
  { "plan_id": 2, "usr": "usr_4_4_4_4_4_4", "depot_id": 2 }
])
const purchase_orders$ = xs.of([
  { "purchase_order_id": 1, "company_id": 2, "order_extid": "order_extid_2", "company_extid": "company_e" },
  { "purchase_order_id": 2, "company_id": 5, "order_extid": "order_extid_2", "company_extid": "company_e" }
])

const rs$ = xs.combine(plans$, purchase_orders$)
  .map( ([plans, purchase_orders]) => ({
    plans: plans,
    purchase_orders: purchase_orders,
  }))

rs$.addListener({
  next: i => console.log(`result: ${JSON.stringify(i)}`),
  error: err => console.error(err),
  complete: () => console.log('s1 completed'),
})
// result: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2}],"purchase_orders":[{"purchase_order_id":1,"company_id":2,"order_extid":"order_extid_2","company_extid":"company_e"},{"purchase_order_id":2,"company_id":5,"order_extid":"order_extid_2","company_extid":"company_e"}]}
// s1 completed

