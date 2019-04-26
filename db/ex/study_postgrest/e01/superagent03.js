const superagent = require('superagent');
superagent
	.post( 'http://localhost:8080/rest/company' )
	.set('Content-Type', 'text/csv')
	.set('Authorization', 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk')
	.send('company_id,company_extid,company_name\n201,,company_201')
  .then((result) => {
    // process the result here
  })
  .catch((err) => {
    throw err;
  });

