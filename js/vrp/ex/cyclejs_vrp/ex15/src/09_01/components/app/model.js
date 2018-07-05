import xs from 'xstream';

export default function model(sources) {
  const plans$ = sources.HTTP.select('plan')
    .flatten()
    .map(res => 
      res.body
    )
    .debug(x => {
      console.log("plans$")
      console.log(x)
      console.log(JSON.stringify(x))
    })
  const purchase_orders$ = sources.HTTP.select('purchase_order')
    .flatten()
    .map(res => res.body)
    .debug(x => {
      console.log("purchase_orders$")
      console.log(x)
      console.log(JSON.stringify(x))
    })
  const rs$ = xs.combine(plans$, purchase_orders$)
    .map( ([plans, purchase_orders]) => ({
      plans: plans,
      purchase_orders: purchase_orders,
    }))

  return rs$
}


