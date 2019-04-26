// doesn't work neither
const superagent = require('superagent');
superagent
  .post( 'http://localhost:3000/login' )
  .accept('application/json')
  .field('user', 'ali')
  .field('password', 'bob')

  .then((result) => {
    // process the result here
  })
  .catch((err) => {
    throw err;
  });

