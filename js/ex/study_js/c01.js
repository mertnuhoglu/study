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
var xs = [1,2,3]
var r01 = xs.map(x => x * 2)
console.log(r01)
// [ 2, 4, 6 ]
var r02 = xs.map(x => [x * 2]).concatAll()
console.log(r02)
// [ 2, 4, 6 ]
var r03 = xs.concatMap(x => [x * 2])
console.log(r03)
// [ 2, 4, 6 ]
