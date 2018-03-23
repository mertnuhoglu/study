var { div, span, h1, ul, li } = require('hyperaxe')
const items = [
  {'id': 1, 'title': "a"},
  {'id': 2, 'title': "b"},
];
function renderItems(items) {
  return (
    `<ul id="bestest-menu">
      ${items.map(e => `<li id="item-${e.id}">${e.title}</li>`).join('')}
    </ul>`);
}
var out = renderItems(items);
console.log(out);
// Node(...)
