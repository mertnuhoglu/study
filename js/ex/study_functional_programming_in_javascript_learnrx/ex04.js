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
result = []
items.forEach( e => {
  if (e.rating === 5.0) {
    result.push(e);
  }
});
console.log(result);
