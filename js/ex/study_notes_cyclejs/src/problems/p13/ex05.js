const {makeHTTPDriver} = require('@cycle/http')
const xs = require('xstream').default

const HTTP = makeHTTPDriver()
const requests$ = xs.from(
  [
    {
      url: 'http://localhost:8080/rest/company',
      method: 'POST',
      headers: {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
      },
			attach: [
				{
					name: 'upload',
					path: '/Users/mertnuhoglu/projects/study/r/ex/study_plumber_restful_apis/e01.R',
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



