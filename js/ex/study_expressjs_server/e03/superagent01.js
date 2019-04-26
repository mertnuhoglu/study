// doesn't work anymore
const request = require('superagent');

request
	.post('http://localhost:3000/login')
	.send({ user: 'Manny', password: 'cat' })
	.set('Accept', 'application/json')
	.then(res => {
		alert('yay got ' + JSON.stringify(res.body));
	})
  .end((err, res) => {
    console.log(`err ${err}`)
    console.log(`res ${res}`)
  })

