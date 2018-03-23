Array.prototype.concatAll = function() {
  var results = [];
  this.forEach(function(subArray) {
    results.push.apply(results, subArray);
  });

  return results;
};
var listsOfItems = [
  {
    name: "list01",
    items: [
      {
        "id": 1,
        "title": "a",
        "rating": 4.0,
      },
      {
        "id": 2,
        "title": "b",
        "rating": 5.0,
      },
    ]
  },
  {
    name: "list02",
    items: [
      {
        "id": 5,
        "title": "e",
        "rating": 3.0,
      },
      {
        "id": 7,
        "title": "f",
        "rating": 5.0,
      },
    ]
  }
];
var result01 = listsOfItems.
  map( list =>
    list.items.map(e => {e.id})
  ).
  concatAll();
console.log(result01);
var result02 = listsOfItems.
  map( list =>
    list.items.map(e => ({e}))
  ).
  concatAll();
console.log(result02);
