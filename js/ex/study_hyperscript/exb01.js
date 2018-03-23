var { div, span, h1, ul, li } = require('hyperaxe')
const items = [
  {'id': 1, 'title': "a"},
  {'id': 2, 'title': "b"},
];
var out = ul('#bestest-menu', items.map( e =>
  li('#item-'+e.id, e.title))
);
      const markup = `
        <div>
          <h2>${beer.name}</h2>
          ${renderKeywords(beer.keywords)}
        </div>
      `
      funcion renderKeywords(kw) {
        return (
          `<ul>
            ${kw.map(key => `li>${key}</li>`).join('')}
          </ul>`);
      }
console.log(out.outerHTML);
// <ul id="bestest-menu"><li id="item-1">a</li><li id="item-2">b</li></ul>
console.log(out);
// Node(...)
