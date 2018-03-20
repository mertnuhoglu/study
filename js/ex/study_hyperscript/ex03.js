var h = require('react-hyperscript')
const React = require('react')
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
// {{ '$$typeof': Symbol(react.element), ...}
