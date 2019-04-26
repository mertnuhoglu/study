// doesn't work
const superagent = require('superagent');
superagent
	.post( 'http://localhost:8080/rest/company' )
	.set('Content-Type', 'text/csv')
	.send('@company01.csv')
  .then((result) => {
    // process the result here
  })
  .catch((err) => {
    throw err;
  });
