const R = require('ramda');
const data = [
  {'sub': {'id': 1, 'title': "a"}},
  {'sub': {'id': 2, 'title': "b"}},
];
const getId = R.map(R.compose(R.prop('id'), R.prop('sub')));
console.log(getId(data));
