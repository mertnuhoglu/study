const {of} = require('rxjs/observable/of')
const {forkJoin} = require('rxjs/observable/forkJoin')
const {map, concatMap} = require('rxjs/operators')

var snabbdom = require('snabbdom');
var patch = snabbdom.init([ 
  require('snabbdom/modules/class').default, 
  require('snabbdom/modules/props').default, 
  require('snabbdom/modules/style').default, 
  require('snabbdom/modules/eventlisteners').default, 
]);
var h = require('snabbdom/h').default; 
var toHTML = require('snabbdom-to-html')

var output = toHTML(
  h('div', { style: { color: 'red' } }, 'The quick brown fox jumps')
)
console.log(output)

const s1 = of(
  h('div', 'Subpart of div')
)
s1.subscribe(x => console.log(toHTML(x)))
const s2 = forkJoin(
  of(h('div', 'Part 1')),
  s1,
  of(h('div', 'Part 2')),
)
s2.subscribe(x => console.log(x))
s2.subscribe(x => x.map(e => console.log(toHTML(e))))
