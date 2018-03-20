var request = require('request-promise-native');
request('http://jsonplaceholder.typicode.com/users/1').
  then( json => console.log(JSON.parse(json).company)).
  catch( err => console.log('error:', err) );
