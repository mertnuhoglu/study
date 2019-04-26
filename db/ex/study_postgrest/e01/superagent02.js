// doesn't work
const superagent = require('superagent');
//let superdebug = require('superagent-debugger');
//require('request').debug = true
let req = superagent
	.post( 'http://localhost:8080/rest/company' )
	.set('Content-Type', 'text/csv')
	.send('@/Users/mertnuhoglu/projects/study/db/ex/study_postgrest/e01/company01.csv')
req
	//.use(superdebug(console.info))
  .then((result) => {
  })
  .catch((err) => {
		//console.log(req)
    throw err;
  });

