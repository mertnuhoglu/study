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
  ],
  boxarts = [
    { video_id: 1, size: 130, url:"url_01" },
    { video_id: 1, size: 200, url:"url_02" },
    { video_id: 2, size: 200, url:"url_03" },
    { video_id: 2, size: 120, url:"url_04" },
    { video_id: 2, size: 300, url:"url_05" },
    { video_id: 3, size: 150, url:"url_06" },
    { video_id: 3, size: 200, url:"url_07" },
    { video_id: 4, size: 200, url:"url_08" },
    { video_id: 4, size: 140, url:"url_09" }
  ],
  bookmarks = [
    { video_id: 1, time: 20 },
    { video_id: 2, time: 30 },
    { video_id: 3, time: 40 },
    { video_id: 4, time: 45 }
  ];

var r = lists.map((list) => {
  return {
    name: list.name,
    videos:
      videos.
        filter((video) => video.list_id === list.id ).
        concatMap((video) => (
          Array.zip(
            bookmarks.
              filter((bookmark) => bookmark.video_id === video.id),
            boxarts.
              filter((boxart) => boxart.video_id === video.id).
              reduce( (acc,curr) => ( acc.size < curr.size ? acc : curr )),
            (bookmark, boxart) => ({ id: video.id, title: video.title, time: bookmark.time, boxart: boxart.url })
          )
        ))
  };
});

console.log(JSON.stringify(r))
// [
//   {
//     "name": "group_01",
//     "videos": [
//       {
//         "id": 1,
//         "title": "a",
//         "time": 20,
//         "boxart": "url_01"
//       },
//       {
//         "id": 2,
//         "title": "b",
//         "time": 30,
//         "boxart": "url_04"
//       }
//     ]
//   },
//   {
//     "name": "group_02",
//     "videos": [
//       {
//         "id": 3,
//         "title": "c",
//         "time": 40,
//         "boxart": "url_06"
//       },
//       {
//         "id": 4,
//         "title": "d",
//         "time": 45,
//         "boxart": "url_09"
//       }
//     ]
//   }
// ]
