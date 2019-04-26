import xs from 'xstream'
import {isEven, square} from './utils02'
import {filter, map} from 'ramda'

const after5 = xs.periodic(5000).take(1)
const stream = map(square, filter(isEven, xs.periodic(1000)))
  .endWhen(after5)

stream.addListener({
  next: i => console.log(i),
  error: err => console.error(err),
  complete: () => console.log('completed'),
})

