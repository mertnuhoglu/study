Array.prototype.filter = function(predicateFunction) {
  var results = [];
  this.forEach(function(itemInArray) {
    if (predicateFunction(itemInArray)) {
    results.push(itemInArray);
    }
  });

  return results;
};
var items = [
    {
      "id": 1,
      "title": "a",
      "boxart": "boxart01",
      "uri": "uri01",
      "rating": 4.0,
      "bookmark": []
    },
    {
      "id": 2,
      "title": "b",
      "boxart": "boxart02",
      "uri": "uri02",
      "rating": 5.0,
      "bookmark": [{ id: 101, time: 60 }]
    },
  ];
var result = items.filter( e => e.rating === 5.0);
console.log(result);
