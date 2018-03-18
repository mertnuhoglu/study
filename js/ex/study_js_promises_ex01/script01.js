var d = new Promise((resolve, reject) => {
  if (true) {
    resolve('hello world');
  } else {
    reject('no bueno');
  }
});
d.then((data) => console.log('success : ', data));
d.catch((error) => console.log('error : ', error));
