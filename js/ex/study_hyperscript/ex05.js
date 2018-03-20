const render = require('preact-render-to-string');
const { h } = require('preact');
var vdom = h('div', {}, "content")
var out = render(vdom)
console.log(out);
// <div>content</div>

