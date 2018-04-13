const request = require('superagent');

request
	.get('http://jsonplaceholder.typicode.com/users/1')
  .end((err, res) => {
    console.log(`err ${err}`)
    console.log(`res ${res}`)
  })

request
  .get('http://english.i-terative.com')
  .end((err, res) => {
    console.log(`err ${err}`)
    console.log(`res ${res}`)
  })

request
  .get('https://dentas.i-terative.com')
  .end((err, res) => {
    console.log(`err ${err}`)
    console.log(`res ${res}`)
  })
request
  .get('http://13.59.28.250:8110/')
  .end((err, res) => {
    console.log(`err ${err}`)
    console.log(`res ${res}`)
  })

var e = undefined
var r = undefined
request.
  get('https://dentas.i-terative.com').
  end((err, res) => {
    e = err
    r = res
  })
console.log(e.status)
console.log(r.status)
request.
  get('http://english.i-terative.com').
  end((err, res) => {
    e = err
    r = res
  })
request.
  get('http://13.59.28.250:8110/').
  end((err, res) => {
    e = err
    r = res
  })
