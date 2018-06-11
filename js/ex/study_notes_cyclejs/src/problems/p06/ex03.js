const xs = require('xstream').default
const delay = require('xstream/extra/delay').default

var secondProxy$ = xs.create();
var first$ = secondProxy$.map(x => x * 10).take(3);
var second$ = first$.map(x => x + 1).startWith(1).compose(delay(100));

second$.addListener({
  next: i => console.log(i),
  error: err => console.error(err),
  complete: () => console.log('completed'),
})
//> 1
//> 11
//> 111
//> 1111
//> completed

