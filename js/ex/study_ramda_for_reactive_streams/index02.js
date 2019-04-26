import xs from 'xstream'
import {isEven, square} from './utils02'

const after5 = xs.periodic(5000).take(1)
var stream = xs.periodic(1000)
  .filter(isEven)
  .map(square)
  .endWhen(after5)

// So far, the stream is idle.
// As soon as it gets its first listener, it starts executing.

stream.addListener({
  next: i => console.log(i),
  error: err => console.error(err),
  complete: () => console.log('completed'),
})

