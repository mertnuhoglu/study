Array.prototype.concatAll = function() {
  var results = [];
  this.forEach(function(subArray) {
    results.push.apply(results, subArray);
  });

  return results;
};
Array.prototype.concatMap = function(projectionFunctionThatReturnsArray) {
  return this.
    map(function(item) {
      return projectionFunctionThatReturnsArray(item);
    }).
    // apply the concatAll function to flatten the two-dimensional array
    concatAll();
};
var words = [ ["cero","rien"], ["uno","un"] ];
var allWords = [0,1].concatMap( (index) =>
  words[index]
);
console.log(allWords);
// [ 'cero', 'rien', 'uno', 'un' ]
