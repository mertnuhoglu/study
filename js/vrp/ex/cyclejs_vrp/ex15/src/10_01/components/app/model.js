import xs from 'xstream';

export default function model(sources) {
  const initReducer$ = xs.of(
    function initReducer(prevState) {
      const initialState = {
        plans: [], 
        purchase_orders: [],
      }
      return initialState
    }
  )
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

  return xs.merge(initReducer$, planReducer$, purchaseOrderReducer$)
}


