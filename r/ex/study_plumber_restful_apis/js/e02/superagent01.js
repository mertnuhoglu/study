// works
const superagent = require('superagent');
superagent.post( 'http://localhost:4500/echo2' )
	.set('Content-Type', 'multipart/form-data')
	.attach('upload', '/Users/mertnuhoglu/projects/study/r/ex/study_plumber_restful_apis/e01.R')
  .then((result) => {
		console.log(result.body)
  })
  .catch((err) => {
    throw err;
  });
//> { formContents:
//>    { upload:
//>       { filename: [Array],
//>         tempfile: [Array],
//>         content_type: [Array],
//>         head: [Array] } } }
