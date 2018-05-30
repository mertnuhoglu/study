const xs = require('xstream').default
var snabbdom = require('snabbdom');
var h = require('snabbdom/h').default; 
var {div} = require("snabbdom-helpers");

const vdom$ = xs.of(
  div("div01"),
)
console.log(Reflect.ownKeys(vdom$))
console.log(Reflect.getPrototypeOf(vdom$))
console.log(vdom$.name)

