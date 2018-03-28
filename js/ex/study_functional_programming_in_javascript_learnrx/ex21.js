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

var lists = [
    {
      "id": 901,
      "name": "group_01"
    },
    {
      "id": 902,
      "name": "group_02"
    }
  ],
  videos = [
    {
      "list_id": 901,
      "id": 1,
      "title": "a"
    },
    {
      "list_id": 901,
      "id": 2,
      "title": "b"
    },
    {
      "list_id": 902,
      "id": 3,
      "title": "c"
    },
    {
      "list_id": 902,
      "id": 4,
      "title": "d"
    }
  ];

var r = lists.map((list) => {
  return {
    name: list.name,
    videos: videos.
      filter((video) => video.list_id === list.id).
      map((video) => ({id: video.id, title: video.title}))
  };
});
console.log(JSON.stringify(r))
// [
//   {
//     "name": "group_01",
//     "videos": [
//       {
//         "id": 1,
//         "title": "a"
//       },
//       {
//         "id": 2,
//         "title": "b"
//       }
//     ]
//   },
//   {
//     "name": "group_02",
//     "videos": [
//       {
//         "id": 3,
//         "title": "c"
//       },
//       {
//         "id": 4,
//         "title": "d"
//       }
//     ]
//   }
// ]

