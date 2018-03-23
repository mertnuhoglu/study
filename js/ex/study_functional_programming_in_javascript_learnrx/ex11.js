var items = [
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
  ];
var maxRating = -1
var maxItem = null
items.forEach(function(e) {
  if (e.rating > maxRating) {
    maxItem = e;
    maxRating = e.rating;
  }
});
console.log(maxItem);
