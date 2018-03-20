const render = require('preact-render-to-string');
const { h } = require('preact');
var { div, span, h1, ul, li } = require('preact-hyperscript')
const items = [
  {'id': 1, 'title': "a"},
  {'id': 2, 'title': "b"},
];
var vdom = ul('#bestest-menu', items.map( e =>
  li('#item-'+e.id, e.title))
);
var out = render(vdom)
console.log(out);
// <ul id="bestest-menu"><li id="item-1">a</li><li id="item-2">b</li></ul>
console.log(vdom);
// VNode(...)
