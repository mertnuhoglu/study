Array.zip = function(left, right, combinerFunction) {
	var counter,
		results = [];

	for(counter = 0; counter < Math.min(left.length, right.length); counter++) {
		results.push(combinerFunction(left[counter],right[counter]));
	}

	return results;
};
var videos = [
  {
    "id": 1,
    "title": "a"
  },
  {
    "id": 2,
    "title": "b"
  },
];
var bookmarks = [
    {id: 101, time: 20},
    {id: 102, time: 40},
  ]
var r = Array.zip(
	videos,
	bookmarks,
	(video, bookmark) =>
		({videoId: video.id, bookmarkId: bookmark.id})
	)
console.log(r)
// [ 5, 7, 9  ]
