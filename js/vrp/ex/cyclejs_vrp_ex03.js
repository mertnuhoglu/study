const {button, h1, h4, a, div, makeDOMDriver} = CycleDOM;
const {makeHTTPDriver} = CycleHTTPDriver;

// DOM read effect: button clicked
// HTTP write effect: request sent
// HTTP read effect: response received
// DOM write effect: data displayed

// frontend app -> backend/server (request) = output = write effect = sink
// frontend app <- backend (response) = input = read effect = source

// external systems for frontend app (js):
// backend server
// browser (DOM)

// frontend app <- DOM event = input = read effect = source
// frontend app -> DOM object = output = write effect = sink

// ---x------x-------x-----
//    \
//     \__q____r__j

function main(sources) {
  const clickEvent$ = sources.DOM
    .select('.get-first').events('click');
  
  const request$ = clickEvent$.map(() => {
    return {
      url: 'http://localhost:8080/rest/plan?select=plan_id',
      method: 'GET',
      headers: {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
      }
    };
  });
  
  const response$$ = sources.HTTP
    .filter(response$ => response$.request.url ===
           'http://localhost:8080/rest/plan?select=plan_id');
  
  const response$ = response$$.switch();
  const json$ = response$.map(response => response.body)
    .startWith(null);
  
  return {
    DOM: json$.map(json =>
      div([
        button('.get-first', 'Get first comment'),
        json === null ? null : div('.comment-details', [
          h4('.comment-id', `${json[0].plan_id}`)
        ])
      ])
    ),
    HTTP: request$,
  };
}

const drivers = {
  DOM: makeDOMDriver('#app'),
  HTTP: makeHTTPDriver(),
}

Cycle.run(main, drivers);
