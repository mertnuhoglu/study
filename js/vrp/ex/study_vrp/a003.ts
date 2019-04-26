import * as R from "ramda";
let as = [{"pln_orl":[{"order_line":{"purchase_order":{"purchase_order_id":1}}}]}, 
 {"pln_orl":[{"order_line":{"purchase_order":{"purchase_order_id":1}}}]}, 
 {"pln_orl":[{"order_line":{"purchase_order":{"purchase_order_id":3}}}]}, 
 {"pln_orl":[{"order_line":{"purchase_order":{"purchase_order_id":4}}}, 
 {"order_line":{"purchase_order":{"purchase_order_id":2}}}]}]
let f = R.pipe(
	R.map(a => a.pln_orl.map(b => b.order_line.purchase_order)),
	R.unnest
)
let ys = f(as)
console.log(ys)
// [ { purchase_order_id: 1 },
//   { purchase_order_id: 1 },
//   { purchase_order_id: 3 },
//   { purchase_order_id: 4 },
//   { purchase_order_id: 2 } ]

