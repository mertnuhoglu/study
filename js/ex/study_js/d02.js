const xs = require('xstream').default
var snabbdom = require('snabbdom');
var h = require('snabbdom/h').default; 
var {div} = require("snabbdom-helpers");

const vdom$ = xs.of(
  div("div01"),
)
vdom$.addListener({
  next: x => {
    console.log(Reflect.ownKeys(x))
    console.log(Reflect.getPrototypeOf(x))
    var d1 = div("1")
    console.log(Reflect.getPrototypeOf(d1))
    console.log(d1.name)
    //console.log(d1.prototype.constructor.name)
    // error: d1.prototype is undefined
    let f1 = function fn(a) {
      return a + 1
    }
    console.log(f1.name)
    console.log(f1.prototype.constructor.name)
  },
})

