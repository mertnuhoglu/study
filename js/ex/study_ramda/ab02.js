var R = require('ramda');
const data = { 'group1-perm1': true, 'group1-perm2': false, 'group2-perm1': false, 'group2-perm2': true, 'perm3': true, 'perm4': false };

R.map(([k, v]) => global[k] = v, R.toPairs(R));

//const addLabel = chain(head, append);
const addLabel = ([value, checked]) => ({value, checked, label: value});
const fn = compose(map(addLabel), toPairs);
console.log(fn(data));
// [ { value: 'group1-perm1', checked: true, label: 'group1-perm1' },
//   { value: 'group1-perm2', checked: false, label: 'group1-perm2' },
//   { value: 'group2-perm1', checked: false, label: 'group2-perm1' },
//   { value: 'group2-perm2', checked: true, label: 'group2-perm2' },
//   { value: 'perm3', checked: true, label: 'perm3' },
//   { value: 'perm4', checked: false, label: 'perm4' } ]
