// doesn't work neither
const superagent = require('superagent');
superagent
  .post( 'http://localhost:3000/login' )
  .accept('application/json')
	.send('{"user":"bob", "password": "ali"}')

  .then((result) => {
    // process the result here
  })
  .catch((err) => {
    throw err;
  });


