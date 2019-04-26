let as = [
	{ p1: 'a01', bs: [ {p2: 'b11'}, {p2: 'b12'} ] },
	{ p1: 'a02', bs: [ {p2: 'b21'} ] },
]
let ys = as.map(a => ({bs2: a.bs.map(b => b.p2)}))
console.log(ys)
// [ { bs2: [ 'b11', 'b12' ] }, { bs2: [ 'b21' ] } ]

