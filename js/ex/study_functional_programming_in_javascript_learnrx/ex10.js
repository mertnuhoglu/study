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
var listsOfItems = [
  {
    name: "list01",
    items: [
      {
        "id": 1,
        "title": "a",
        "subitems": [{ id: 301, time: 70 }]
      },
      {
        "id": 2,
        "title": "b",
        "subitems": [{ id: 401, time: 30 }]
      },
    ]
  },
  {
    name: "list02",
    items: [
      {
        "id": 5,
        "title": "e",
        "subitems": [{ id: 101, time: 50 }, { id: 102, time: 40 }]
      },
      {
        "id": 7,
        "title": "f",
        "subitems": [{ id: 201, time: 10 }]
      },
    ]
  }
];
var result = listsOfItems.concatMap( (list) =>
  list.items.concatMap( (e) =>
    e.subitems.filter( (s) =>
      s.time > 20
    ).map( (s) =>
      ({id: e.id, s: s.time})
    )
  )
)
console.log(result);
// [ { id: 1, s: 70 }, { id: 2, s: 30 }, { id: 5, s: 50 }, { id: 5, s: 40 } ]

