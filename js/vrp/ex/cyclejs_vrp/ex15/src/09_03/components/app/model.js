import xs from 'xstream';

export default function model(sources) {
  const planReducer$ = sources.HTTP.select('plan')
    .flatten()
    .map(res => function planReducer(prevState) {
      return {
        ...prevState,
        plans: res.body
      }
    })
  const purchaseOrderReducer$ = sources.HTTP.select('purchase_order')
    .flatten()
    .map(res => function purchaseOrderReducer(prevState) {
      return {
        ...prevState,
        purchase_orders: res.body
      }
    })

  const reducer$ = xs.merge(planReducer$, purchaseOrderReducer$)
  return reducer$
}


