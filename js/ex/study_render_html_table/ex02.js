var {table, th, tr, td} = require('hyperaxe');
const data = [
  {'id': 1, 'title': "a"},
  {'id': 2, 'title': "b"},
];
var out = table(
  tr(
    th("Id"),
    th("Title")
  ),
  data.map( e =>
    tr([
      td(e.id), 
      td(e.title)
    ])
  )
);
console.log(out.outerHTML);
