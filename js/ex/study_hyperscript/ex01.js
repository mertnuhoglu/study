var h = require('virtual-dom/h')
const { div, span, h1, ul, li } = require('hyperscript-helpers')(h); 
const items = [
  {'id': 1, 'title': "a"},
  {'id': 2, 'title': "b"},
];
var out = ul('#bestest-menu', items.map( e =>
  li('#item-'+e.id, e.title))
);
console.log(out.outerHTML);
// undefined
console.log(out);
// VirtualNode(...)
