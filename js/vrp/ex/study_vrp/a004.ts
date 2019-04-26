import * as R from "ramda";
let as = [{"order_line":[{"purchase_order":{"purchase_order_id":1}}]}]
let f = R.pipe(
	R.map(a => a.order_line.map(b => b.purchase_order)),
	R.unnest
)
let ys = f(as)
console.log(ys)
// [ { purchase_order_id: 1 } ]

