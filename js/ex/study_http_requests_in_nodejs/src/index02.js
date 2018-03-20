var request = require('request-promise-native');
request('http://jsonplaceholder.typicode.com/users/1').
  then( html => console.log('body:', html) ).
  catch( err => console.log('error:', err) );
