import xs from 'xstream';

export default function model(sources) {
  const plan$ = sources.HTTP.select('plan')
    .flatten()
    .map(res => 
      res.body
    )
    .debug(x => {
      console.log("plan$")
      console.log(x)
    })
  const purchase_order$ = sources.HTTP.select('purchase_order')
    .flatten()
    .map(res => res.body)
    .debug(x => {
      console.log("purchase_order$")
      console.log(x)
    })
  return {plan$, purchase_order$}
}


