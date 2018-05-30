const xs = require('xstream').default
const {div} = require('@cycle/dom');

const vdom$ = xs.of(
  div("div01"),
)
vdom$
  .debug(x => console.log(Reflect.ownKeys(x)))
  .addListener({
    next: console.log
  })

