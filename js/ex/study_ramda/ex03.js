const R = require('ramda');
const data = [
  {'sub': {'id': 1, 'title': "a"}},
  {'sub': {'id': 2, 'title': "b"}},
];
const getId = R.map(e => e.sub.id);
console.log(getId(data));
