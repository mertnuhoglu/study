const {makeHTTPDriver} = require('@cycle/http')
const xs = require('xstream').default

const HTTP = makeHTTPDriver()
const requests$ = xs.from(
  [
    {
      url: 'http://localhost:4500/echo2',
      method: 'POST',
			attach: [
				{
					name: 'upload',
					path: '~/projects/study/r/ex/study_plumber_restful_apis/e01.R',
					filename: 'e01.R'
				}
			],
			category: 'plumber'
    },
  ]
)
const httpSource = HTTP(requests$)

const plans$ = httpSource.select('plumber')
  .flatten()
  .map(res => 
    res.body
  )
plans$.addListener({
  next: i => console.log(`result: ${JSON.stringify(i)}`),
  error: err => console.error(err),
  complete: () => console.log('s1 completed'),
})

