const { Observable, Subject, ReplaySubject, from, of, range, interval } = require('rxjs');
const { tap, withLatestFrom, map, take } = require('rxjs/operators')


// s1:  -0-1-2-3|
// s2:  ---0---1|
// res: ---1---3|
const s1 = interval(250).pipe(
  take(4), 
  tap(console.log)
)

const s2 = interval(510).pipe(
  take(2),
  tap(console.log)
)

const res = s1.pipe(
  withLatestFrom(s2),
  map( ([v1, v2]) => v1 )
)
res.subscribe({
  next: i => console.log(`res: ${i}`),
  error: err => console.error(err),
  complete: () => console.log('res completed'),
})
// 0
// 1
// 0
// 2
// res: 2
// 3
// res: 3
// res completed
