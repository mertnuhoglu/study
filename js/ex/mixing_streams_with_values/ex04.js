const xs = require('xstream').default

var snabbdom = require('snabbdom');
var patch = snabbdom.init([ 
  require('snabbdom/modules/class').default, 
  require('snabbdom/modules/props').default, 
  require('snabbdom/modules/style').default, 
  require('snabbdom/modules/eventlisteners').default, 
]);
var h = require('snabbdom/h').default; 
var toHTML = require('snabbdom-to-html')

const s1 = xs.of(
  h('div', 'Subpart of div')
)
s1.addListener({next: x => console.log(toHTML(x))})
const s2 = xs.merge(
  xs.of(h('div', 'Part 1')),
  s1,
  xs.of(h('div', 'Part 2')),
)
s2.addListener({next: x => console.log(toHTML(x))})
