const xs = require('xstream').default

// s1:  -0-1-2-3|
// s2:  ---0---1|
// res: ---1---3|
const s1 = xs.periodic(250).take(4)
const s2 = xs.periodic(510).take(2)

s1.addListener({
  next: i => console.log(`s1: ${i}`),
  error: err => console.error(err),
  complete: () => console.log('s1 completed'),
})
s2.addListener({
  next: i => console.log(`s2: ${i}`),
  error: err => console.error(err),
  complete: () => console.log('s2 completed'),
})
// s1: 0
// s1: 1
// s2: 0
// s1: 2
// s1: 3
// s1 completed
// s2: 1
// s2 completed

