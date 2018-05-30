const xs = require('xstream').default
var snabbdom = require('snabbdom');
var h = require('snabbdom/h').default; 
var {div} = require("snabbdom-helpers");
var toHTML = require('snabbdom-to-html')

const vdom$ = xs.of(
  h('div',"div01"),
)
vdom$
  .debug(x => console.log(Reflect.ownKeys(x)))
  .addListener({
    next: x => console.log(toHTML(x))
  })

