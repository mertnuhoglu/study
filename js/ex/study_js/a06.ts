let ass = [
	[1,2],
	[3]
]
let rs = []

ass.forEach( as => rs.push.apply(rs, as) )
console.log(rs)
// [ 1, 2, 3 ]

let concatAll = function(ass) {
  let rs = [];
	ass.forEach( as => rs.push.apply(rs, as))
  return rs;
}

console.log(concatAll(ass))
// [ 1, 2, 3 ]
