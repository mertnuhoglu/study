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

var listsOfItems = [
  {
    name: "list_01",
    items: [
      {
        "id": 1,
        "title": "a",
        "sub_a": [
          { time: 150, url: "url01"},
          { time: 200, url: "url02"}
        ],
        "sub_b": [
          { type: "m", time: 20 },
          { type: "s", time: 30 },
        ]
      },
      {
        "id": 2,
        "title": "b",
        "sub_a": [
          { time: 200, url:"url03" },
          { time: 140, url:"url04" }
        ],
        "sub_b": [
          { type: "m", time: 12 },
          { type: "s", time: 20 },
        ]
      }
    ]
  },
  {
    name: "list_02",
    items: [
      {
        "id": 3,
        "title": "c",
        "sub_a": [
          { time: 130, url:"url05" },
          { time: 200, url:"url06" }
        ],
        "sub_b": [
          { type: "m", time: 23 },
          { type: "s", time: 11 },
        ]
      },
      {
        "id": 4,
        "title": "d",
        "sub_a": [
          { time: 200, url:"url07" },
          { time: 120, url:"url08" },
          { time: 300, url:"url09" }
        ],
        "sub_b": [
          { type: "m", time: 15 },
          { type: "s", time: 8 },
        ]
      }
    ]
  }
];
var r = listsOfItems.concatMap((list) =>
  list.items.concatMap((e) =>
		Array.zip(
			e.sub_a.reduce((acc,curr) => {
				if (acc.time < curr.time) {
					return acc;
				}
				else {
					return curr;
				}
			}),
			e.sub_b.filter((sub_b) => sub_b.type === "m"),
      (sub_a, sub_b) => ({id: e.id, title: e.title, time: sub_b.time, url: sub_a.url})
		)
	)
);
console.log(r)
// [ { id: 1, title: 'a', time: 20, url: 'url01' },
//   { id: 2, title: 'b', time: 12, url: 'url04' },
//   { id: 3, title: 'c', time: 23, url: 'url05' },
//   { id: 4, title: 'd', time: 15, url: 'url08' } ]
