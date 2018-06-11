const xs = require('xstream').default

var s1$ = xs.periodic(1000)
  .endWhen(xs.periodic(5000).take(1))

s1$.addListener({
  next: i => console.log(i),
  error: err => console.error(err),
  complete: () => console.log('completed'),
})

