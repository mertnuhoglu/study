var {table, th, tr, td} = require('hyperaxe');
var R = require('ramda');
const data = [
  {'id': 1, 'title': "a"},
  {'id': 2, 'title': "b"},
];
var renderItem = R.map( e =>
  tr([
    td(e.id), 
    td(e.title)
  ])
)
var out = table(
  tr(
    th("Id"),
    th("Title")
  ),
  renderItem(data)
);
console.log(out.outerHTML);
