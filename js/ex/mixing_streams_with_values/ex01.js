const {of} = require('rxjs/observable/of')
const {map} = require('rxjs/operators')

const s1 = of([1,2,3])
s1.subscribe(x => console.log(x))
const s2 = of(1,2,3)
s2.subscribe(x => console.log(x))
const s3 = of(1,s2,3)
s3.subscribe(x => console.log(x))
