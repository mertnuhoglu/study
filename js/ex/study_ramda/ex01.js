const R = require('ramda');
const data = [
  {'id': 1, 'title': "a"},
  {'id': 2, 'title': "b"},
];
const getId = R.map(R.prop('id'));
console.log(getId(data));
