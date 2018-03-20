var R = require('ramda');
const data = { 'group1-perm1': true, 'group1-perm2': false, 'group2-perm1': false, 'group2-perm2': true, 'perm3': true, 'perm4': false };

R.map(([k, v]) => global[k] = v, R.toPairs(R));

const getLabel = R.compose(R.head, R.match(/perm[0-9]/g));
const addLabel = ([value, checked]) => ({value, checked, label: getLabel(value)});
const getGroup = R.compose(R.defaultTo('general'), R.head, R.match(/group[0-9]/g));
const groupName = R.compose(getGroup, R.prop('value'));
const fn = pipe(
  toPairs,
  map(addLabel), 
  groupBy(groupName), 
);
console.log(fn(data));
// { group1:
//    [ { value: 'group1-perm1', checked: true, label: 'perm1' },
//      { value: 'group1-perm2', checked: false, label: 'perm2' } ],
