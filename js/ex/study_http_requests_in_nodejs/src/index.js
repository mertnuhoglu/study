const request = require('request');
request('http://jsonplaceholder.typicode.com/users/1', function (error, response, body) {
  console.log('error:', error);
  console.log('statusCode:', response && response.statusCode);
  console.log('body:', body);
});
