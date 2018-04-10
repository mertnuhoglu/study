var snabbdom = require('snabbdom');
var h = require('snabbdom/h').default; 
var toHTML = require('snabbdom-to-html')
//var { div, span, h1, ul, li } = require("snabbdom-helpers");

const items = [
  {'id': 1, 'title': "a"},
  {'id': 2, 'title': "b"},
];
var vdom = h("ul#bestest-menu", items.map( e =>
  h(`li#item-${e.id}`, e.title))
);
var out = toHTML(vdom)
console.log(out)
console.log(vdom)

