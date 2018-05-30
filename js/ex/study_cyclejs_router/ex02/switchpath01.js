const {switchPath} = require('switch-path');
//import switchPath from 'switch-path';

const {path, value} = switchPath('/home/foo', {
  '/bar': 123,
  '/home/foo': 456,
});

