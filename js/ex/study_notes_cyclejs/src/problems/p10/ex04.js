const xs = require('xstream').default
const sampleCombine = require('xstream/extra/sampleCombine').default

// s1:  -0-1-2-3|
// s2:  ---0---1|
// res: ---1---3|
const s1 = xs.periodic(250).take(4)
  .debug()
const s2 = xs.periodic(510).take(2)
  .debug()

const res = s2.compose(sampleCombine(s1))
  .map( ([v2, v1]) => v1 )
res.addListener({
  next: i => console.log(`res: ${i}`),
  error: err => console.error(err),
  complete: () => console.log('res completed'),
})
// 0
// 1
// 0
// res: 1
// 2
// 3
// 1
// res: 3
// res completed
