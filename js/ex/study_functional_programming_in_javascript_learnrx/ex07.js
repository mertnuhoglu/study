Array.prototype.concatAll = function() {
  var results = [];
  this.forEach(function(subArray) {
    results.push.apply(results, subArray);
  });

  return results;
};
// JSON.stringify([ [1,2,3], [4,5,6], [7,8,9] ].concatAll()) === "[1,2,3,4,5,6,7,8,9]"
// [1,2,3].concatAll(); // throws an error because this is a one-dimensional array
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
var result = listsOfItems.
  map( list =>
    //return list.items.map(e => ({ e.id }));
    //return list.items.map(function(e) {return e.id;});
    //list.items.map(function(e) {return e.id;})
    //list.items.map(e => {e.id})
    //list.items.map(e => ({e}))
    //list.items.map((e) => ({e.id}))
    //list.items.map((elem) => ({elem.id}))
    //list.items.map(({e}) => ({e.id}))
    list.items.map((e) => (e.id))
    //list.items.map(e => {
      //return e.id;
    //})
  ).
  concatAll();
console.log(result);
// [ 1, 2, 5, 7 ]
