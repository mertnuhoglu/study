const xs = require('xstream').default

var s1$ = xs.periodic(1000)
  .map(i => i * 2)
  .endWhen(xs.periodic(5000).take(1))
var s2$ = xs.periodic(1000)
  .map(i => i * 3)
  .endWhen(xs.periodic(5000).take(1))
var s3$ = xs.merge(
  s1$, 
  s2$,
)

s3$.addListener({
  next: i => console.log(i),
  error: err => console.error(err),
  complete: () => console.log('completed'),
})

