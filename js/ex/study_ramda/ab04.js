var R = require('ramda');
const data = { 'group1-perm1': true, 'group1-perm2': false, 'group2-perm1': false, 'group2-perm2': true, 'perm3': true, 'perm4': false };
// { group1: [ { value: 'group1-perm1', checked: true, 'label': 'perm1' } ]}

R.map(([k, v]) => global[k] = v, R.toPairs(R));

const getLabel = R.compose(R.head, R.match(/perm[0-9]/g));
const addLabel = ([value, checked]) => ({value, checked, label: getLabel(value)});
const groupName = R.prop('value');
const fn = compose(R.groupBy(groupName), map(addLabel), toPairs);
console.log(fn(data));
// { 'group1-perm1': [ { value: 'group1-perm1', checked: true, label: 'perm1' } ], ...
