const {of} = require('rxjs/observable/of')
const {forkJoin} = require('rxjs/observable/forkJoin')
const {map, concatMap} = require('rxjs/operators')

const s1 = of(2)
s1.subscribe(x => console.log(x))
const s2 = forkJoin(
  s1
)
s2.subscribe(x => console.log(x))
const s3 = forkJoin(
  of(1),
  s1,
  of(3)
)
s3.subscribe(x => console.log(x))
