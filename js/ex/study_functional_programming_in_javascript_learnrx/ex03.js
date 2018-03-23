Array.prototype.map = function(projectionFunction) {
  var results = [];
  this.forEach(function(itemInArray) {
    results.push(projectionFunction(itemInArray));

  });

  return results;
};
var items = [
    {
      "id": 1,
      "title": "a",
      "boxart": "boxart01",
      "uri": "uri01",
      "rating": [4.0],
      "bookmark": []
    },
    {
      "id": 2,
      "title": "b",
      "boxart": "boxart02",
      "uri": "uri02",
      "rating": [5.0],
      "bookmark": [{ id: 101, time: 60 }]
    },
  ];
result = items.map( e => ({ id: e.id, title: e.title }))
console.log(result);
