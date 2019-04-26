const {makeHTTPDriver} = require('@cycle/http')
const xs = require('xstream').default

const HTTP = makeHTTPDriver()
const requests$ = xs.from(
  [
    {
      url: 'http://localhost:6973/add2',
      method: 'POST',
			send: '{"num":"3"}'
    },
  ]
)
const httpSource = HTTP(requests$)

const plans$ = httpSource.select('plan')
  .flatten()
  .map(res => 
    res.body
  )
plans$.addListener({
  next: i => console.log(`result: ${JSON.stringify(i)}`),
  error: err => console.error(err),
  complete: () => console.log('s1 completed'),
})
