import * as R from "ramda";

const data = [
  {'id': 1, 'title': "a"},
  {},
];
const getId = R.map(R.prop('id'));
console.log(getId(data));
// [ 1, undefined ]

