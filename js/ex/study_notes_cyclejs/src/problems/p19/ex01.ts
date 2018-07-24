// ## p19: Test: addListener doesn't consume streaming data

import xs from 'xstream';

export var stream = xs.periodic(1000)
  .map(i => i)
  .endWhen(xs.periodic(5000).take(1))

stream.addListener({
  next: i => console.log(i),
  error: err => console.error(err),
  complete: () => console.log('completed'),
})

// 0
// 1
// 2
// 3
// completed