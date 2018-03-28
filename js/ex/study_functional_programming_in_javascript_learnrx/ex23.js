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
Array.prototype.reduce = function(combiner, initialValue) {
  var counter,
    accumulatedValue;

  // If the array is empty, do nothing
  if (this.length === 0) {
    return this;
  }
  else {
    // If the user didn't pass an initial value, use the first item.
    if (arguments.length === 1) {
      counter = 1;
      accumulatedValue = this[0];
    }
    else if (arguments.length >= 2) {
      counter = 0;
      accumulatedValue = initialValue;
    }
    else {
      throw "Invalid arguments.";
    }

    // Loop through the array, feeding the current value and the result of
    // the previous computation back into the combiner function until
    // we've exhausted the entire array and are left with only one function.
    while(counter < this.length) {
      accumulatedValue = combiner(accumulatedValue, this[counter])
      counter++;
    }

    return [accumulatedValue];
  }
};
Array.zip = function(left, right, combinerFunction) {
	var counter,
		results = [];

	for(counter = 0; counter < Math.min(left.length, right.length); counter++) {
		results.push(combinerFunction(left[counter],right[counter]));
	}

	return results;
};

var prices = [
  // ... from the NASDAQ's opening day
  {name: "ANGI", price: 31.22, timeStamp: new Date(2018,3,15) },
  {name: "MSFT", price: 32.32, timeStamp: new Date(2018,3,15) },
  {name: "GOOG", price: 150.43, timeStamp: new Date(2018,3,15)},
  {name: "MSFT", price: 28.44, timeStamp: new Date(2018,3,16)},
  {name: "GOOG", price: 199.33, timeStamp: new Date(2018,3,16)},
  // ...and up to the present.
];
var now = new Date();
var r = prices.
  filter((e) => 
    (e.name === 'MSFT' && 
      e.timeStamp > new Date( now.getFullYear(), now.getMonth(), now.getDate() - 10))
  )
console.log(r)
// [ { name: 'MSFT',
//     price: 32.32,
//     timeStamp: 2018-04-14T21:00:00.000Z },
//   { name: 'MSFT',
//     price: 28.44,
//     timeStamp: 2018-04-15T21:00:00.000Z } ]
