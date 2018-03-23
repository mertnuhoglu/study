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

var listsOfItems = [
  {
    name: "list_01",
    items: [
      {
        "id": 1,
        "title": "a",
        "subitems": [
          { time: 150, url: "url01"},
          { time: 200, url: "url02"}
        ],
      },
      {
        "id": 2,
        "title": "b",
        "subitems": [
          { time: 200, url:"url03" },
          { time: 140, url:"url04" }

        ],
      }
    ]
  },
  {
    name: "list_02",
    items: [
      {
        "id": 3,
        "title": "c",
        "subitems": [
          { time: 130, url:"url05" },
          { time: 200, url:"url06" }
        ],
      },
      {
        "id": 4,
        "title": "d",
        "subitems": [
          { time: 200, url:"url07" },
          { time: 120, url:"url08" },
          { time: 300, url:"url09" }
        ],
      }
    ]
  }
];
var r = listsOfItems.concatMap((list) =>
  list.items.concatMap((e) =>
    e.subitems.reduce((acc,curr) => {
      if (acc.time < curr.time) {
        return acc;
      }
      else {
        return curr;
      }
    }).map((s) =>
      ({id: e.id, title: e.title, subitem: s.url})
    )
  )
);

console.log(r)
