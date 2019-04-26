// works
const superagent = require('superagent');
superagent
  .post( 'http://localhost:3000/login' )
	.set('Content-Type', 'application/json')
	.send('{"user":"bob", "password": "ali"}')
  .then((result) => {
    // process the result here
  })
  .catch((err) => {
    throw err;
  });


