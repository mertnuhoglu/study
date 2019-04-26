Array.prototype.concatAll = function() {
  var results = [];
  this.forEach(function(subArray) {
    results.push.apply(results, subArray);
  });

  return results;
};
Array.prototype.concatMap = function(projection) {
  return this.
    map(function(item) {
      return projection(item);
    }).
    // apply the concatAll function to flatten the two-dimensional array
    concatAll();
};
let as = [{"pln_orl":[{"order_line":{"purchase_order":{"purchase_order_id":1}}}]}, 
 {"pln_orl":[{"order_line":{"purchase_order":{"purchase_order_id":1}}}]}, 
 {"pln_orl":[{"order_line":{"purchase_order":{"purchase_order_id":3}}}]}, 
 {"pln_orl":[{"order_line":{"purchase_order":{"purchase_order_id":4}}}, 
 {"order_line":{"purchase_order":{"purchase_order_id":2}}}]}]
let ys = as.concatMap(a => a.pln_orl.map(b => b.order_line.purchase_order))
console.log(ys)
// [ { purchase_order_id: 1 },
//   { purchase_order_id: 1 },
//   { purchase_order_id: 3 },
//   { purchase_order_id: 4 },
//   { purchase_order_id: 2 } ]

