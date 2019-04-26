let as = [
	[1,2],
	[3]
]
let rs = []
rs.push.apply(rs, as[0])
console.log(rs)
// [ 1, 2 ]
rs.push.apply(rs, as[1])
console.log(rs)
// [ 1, 2, 3 ]
console.log(as[0])
// [ 1, 2 ]

let ps = []
ps.push.apply(ps, [1,2])
console.log(ps)
// [ 1, 2 ]
ps.push.apply(ps, [3])
console.log(ps)
// [ 1, 2, 3 ]

let qs = []
qs.push(1,2)
console.log(qs)
// [ 1, 2 ]
qs.push(3)
console.log(qs)
// [ 1, 2, 3 ]

let ts = []
ts.push([1,2])
console.log(ts)
// [ [ 1, 2 ] ]
ts.push([3])
console.log(ts)
// [ [ 1, 2 ], [ 3 ] ]
