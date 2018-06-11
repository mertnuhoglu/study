import xs from 'xstream'

var stream = xs.periodic(1000)
  .filter(i => i % 2 === 0)
  .map(i => i * i)
  .endWhen(xs.periodic(5000).take(1))

// So far, the stream is idle.
// As soon as it gets its first listener, it starts executing.

stream.addListener({
  next: i => console.log(i),
  error: err => console.error(err),
  complete: () => console.log('completed'),
})
