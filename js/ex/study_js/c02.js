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
    concatAll();
};
var xs = [
  {x_id: 11, ys: [{y_id: 51}, {y_id: 52}]},
  {x_id: 12, ys: [{y_id: 61}, {y_id: 62}]},
]
var r01 = xs.map(x => x.ys.map(y => y.y_id))
console.log(r01)
// [ [ 51, 52 ], [ 61, 62 ] ]
var r02 = xs.map(x => x.ys.map(y => y.y_id)).concatAll()
console.log(r02)
// [ 51, 52, 61, 62 ]
var r03 = xs.concatMap(x => x.ys.map(y => y.y_id))
console.log(r03)
// [ 51, 52, 61, 62 ]
