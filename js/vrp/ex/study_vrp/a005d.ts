import * as R from "ramda";
//const {Maybe} = import("ramda-fantasy")
let f = R.pipe(
	R.map(a => R.prop('order_line', a).map(R.prop('purchase_order'))),
	R.unnest
)
let as = [{"order_line":[{"purchase_order":{"purchase_order_id":1}}]}]
let bs = [{"order_line":[{"purchase_order":{}}]}]
let cs = [{"order_line":[]}]
let ds = [{"order_line":[{"purchase_order":{}}]}]
console.log(f(as))
console.log(f(bs))
console.log(f(cs))
console.log(f(ds))
// [ { purchase_order_id: 1 } ]
// [ {} ]
// []
// [ {} ]

