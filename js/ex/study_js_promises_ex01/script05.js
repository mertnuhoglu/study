var d = new Promise((resolve, reject) => {
  setTimeout(() => {
    if (true) {
      resolve('hello world');
    } else {
      reject('no bueno');
    }
  }, 250);
});
d.then( (data) => console.log('success : ', data) )
  .catch( (error) => console.log('error : ', error) );
