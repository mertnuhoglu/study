Array.zip = function(left, right, combinerFunction) {
	var counter,
		results = [];

	for(counter = 0; counter < Math.min(left.length, right.length); counter++) {
		results.push(combinerFunction(left[counter],right[counter]));
	}

	return results;
};
var r = Array.zip(
	[1,2,3],
	[4,5,6], 
	(left, right) => left + right 
)
console.log(r)
