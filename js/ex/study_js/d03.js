const xs = require('xstream').default
var snabbdom = require('snabbdom');
var h = require('snabbdom/h').default; 
var {div} = require("snabbdom-helpers");

const vdom$ = xs.of(
  div("div01"),
)
vdom$
  .debug(x => console.log(Reflect.ownKeys(x)))

