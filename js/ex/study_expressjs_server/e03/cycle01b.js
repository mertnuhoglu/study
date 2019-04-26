const {makeHTTPDriver} = require('@cycle/http')
const xs = require('xstream').default

const HTTP = makeHTTPDriver()
const requests$ = xs.from(
  [
    {
      url: 'http://localhost:3000/login',
      method: 'POST',
			send: {user:"bob", password: "ali"},
			category: 'express'
    },
  ]
)
const httpSource = HTTP(requests$)

const plans$ = httpSource.select('express')
  .flatten()
  .map(res => 
    res.body
  )
plans$.addListener({
  next: i => console.log(`result: ${JSON.stringify(i)}`),
  error: err => console.error(err),
  complete: () => console.log('s1 completed'),
})

