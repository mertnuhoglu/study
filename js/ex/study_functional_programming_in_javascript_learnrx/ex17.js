var videos = [
  {
    "id": 1,
    "title": "a"
  },
  {
    "id": 2,
    "title": "b"
  },
  {
    "id": 3,
    "title": "c"
  },
  {
    "id": 4,
    "title": "d"
  }
];
var bookmarks = [
    {id: 101, time: 20},
    {id: 102, time: 40},
    {id: 103, time: 30},
    {id: 104, time: 50}
  ],
  i,
  pairs = [];
for(i = 0; i < Math.min(videos.length, bookmarks.length); i++) {
  pairs.push({video_id: videos[i].id, bookmark_id: bookmarks[i].id});
}
console.log(pairs)
// [ { video_id: 1, bookmark_id: 101 },
//   { video_id: 2, bookmark_id: 102 },
//   { video_id: 3, bookmark_id: 103 },
//   { video_id: 4, bookmark_id: 104 } ]
