import * as R from "ramda";
//const {Maybe} = import("ramda-fantasy")
let f = R.pipe(
	R.map(R.prop('order_line').map(b => b.purchase_order)),
	R.unnest
)
let as = [{"order_line":[{"purchase_order":{"purchase_order_id":1}}]}]
let ys = f(as)
console.log(ys)
// error:
// R.map(R.prop('order_line').map(b => b.purchase_order)),

