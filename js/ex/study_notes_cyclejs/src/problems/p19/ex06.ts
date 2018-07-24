// ## p19: Test: addListener doesn't consume streaming data

import xs from 'xstream';

export var stream = xs.periodic(1000)
  .map(i => i)
  .endWhen(xs.periodic(5000).take(1))

const s2 = stream
  .map(i => i + 10)

const s3 = stream
  .map(i => i + 20)
  .debug()

stream.addListener({
  next: i => console.log(i),
  error: err => console.error(err),
  complete: () => console.log('completed'),
})
s2.addListener({
  next: i => console.log(i),
  error: err => console.error(err),
  complete: () => console.log('completed'),
})

// 0
// 10
// 1
// 11
// 2
// 12
// 3
// 13
// completed