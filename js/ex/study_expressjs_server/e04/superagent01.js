// works
const superagent = require('superagent');
superagent
  .post( 'http://localhost:3030/profile' )
	.set('Content-Type', 'multipart/form-data')
  .attach('avatar', '/Users/mertnuhoglu/projects/study/js/ex/study_expressjs_server/img/galileo.jpg')
  .then((result) => {
    // process the result here
  })
  .catch((err) => {
    throw err;
  });



