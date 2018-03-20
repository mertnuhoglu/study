var R = require('ramda');
const data = {
  'group1-perm1': true,
  'group1-perm2': false,
  'group2-perm1': false,
  'group2-perm2': true,
  'perm3': true,
  'perm4': false
};

const getGroup = R.compose(R.defaultTo('general'), R.head, R.match(/group[0-9]/g));
const getLabel = R.o(R.head, R.match(/perm[0-9]/g));
const groupName = R.o(getGroup, R.prop('value'));
const formatData = R.pipe(
  R.toPairs,
  R.map(([value, checked]) => ({value, checked, label: getLabel(value)})),
  R.groupBy(groupName),
);
var out = formatData(data);
console.log(out);
