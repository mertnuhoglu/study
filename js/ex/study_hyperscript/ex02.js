var h = require('hyperscript')
const { div, span, h1, ul, li } = require('hyperscript-helpers')(h); 
const items = [
  {'id': 1, 'title': "a"},
  {'id': 2, 'title': "b"},
];
var out = ul('#bestest-menu', items.map( e =>
  li('#item-'+e.id, e.title))
);
console.log(out.outerHTML);
// <ul id="bestest-menu"><li id="item-1">a</li><li id="item-2">b</li></ul>
console.log(out);
// Node(...)
