import * as R from "ramda";
let f = R.pipe(
	R.map(a => R.prop('order_line', a).map(R.prop('purchase_order'))),
	R.unnest
)
let as = [{"order_line":[{"purchase_order":{"purchase_order_id":1}}]}, 
 {"order_line":[{"purchase_order":{"purchase_order_id":1}}]}, 
 {"order_line":[]}, 
 {"order_line":[{"purchase_order":{"purchase_order_id":3}}]}, 
 {"order_line":[{"purchase_order":{"purchase_order_id":4}}, 
 {"purchase_order":{"purchase_order_id":2}}]}]
console.log(f(as))
// [ { purchase_order_id: 1 },
//   { purchase_order_id: 1 },
//   { purchase_order_id: 3 },
//   { purchase_order_id: 4 },
//   { purchase_order_id: 2 } ]
