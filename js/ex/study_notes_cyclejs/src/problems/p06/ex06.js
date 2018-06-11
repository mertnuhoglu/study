const xs = require('xstream').default
const delay = require('xstream/extra/delay').default

const input = xs.of(1, 2, 3, 4)
const out = input.compose(delay(500))

out.addListener({
  next: i => console.log(i),
  error: err => console.error(err),
  complete: () => console.log('completed'),
})
//> 1
//> 2
//> 3
//> 4
//> completed
