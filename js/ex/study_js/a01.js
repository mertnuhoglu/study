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
var result = listsOfItems.map( list =>
    list.items.map((e) => (e.id))
  ).
  concatAll();
console.log(result);
// [ 1, 2, 5, 7 ]
var result02 = listsOfItems.map( list =>
    list.items.map(e => {
      return e.id;
    })
  ).
  concatAll();
console.log(result02);
// [ 1, 2, 5, 7 ]
var result03 = listsOfItems.map( function(list) {
    return list.items.map(function(e) {return e.id;});
  }).
  concatAll();
console.log(result03);
// [ 1, 2, 5, 7 ]
var result04 = listsOfItems.map( list =>
    list.items.map(function(e) {return e.id;})
  ).
  concatAll();
console.log(result04);
// [ 1, 2, 5, 7 ]




