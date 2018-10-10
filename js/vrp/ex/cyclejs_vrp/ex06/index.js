import Rx from 'rxjs/Rx'
import {run} from '@cycle/rxjs-run'
import {button, h1, h4, a, div, table, thead, tbody, tr, td, th, makeDOMDriver} from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';

function intent(dom$) {
  const clickEvent$ = dom$
    .select('.get-first').events('click')
    .startWith(null);

  const request$ = clickEvent$.map(() => {
    return {
      url: 'http://localhost:8080/rest/plan?select=plan_id,usr,depot_id',
      method: 'GET',
      headers: {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
      }
    };
  });
  return request$;
}

function model(http$) {
  const response$$ = http$.select();
  const response$ = response$$.switch();
  const state$ = response$.map(response => response.body);
  return state$;
}

function view(state$) {
  return state$.
    map(json =>
      table([
        thead(
          tr([
            th('Plan Id'),
            th('Kullanıcı'),
            th('Depot Id')
          ])
        ),
        tbody(
          json.map(e => 
            tr([
              td(e.plan_id),
              td(e.usr),
              td(e.depot_id)
            ])
          )
        )
      ])
    )
}

function main(sources) {
	const request$ = intent(sources.DOM)
  const state$ = model(sources.HTTP)
	const vdom$ = view(state$)
  return {
    HTTP: request$,
    DOM: vdom$,
  };
}

const drivers = {
  DOM: makeDOMDriver('#app'),
  HTTP: makeHTTPDriver(),
}

run(main, drivers);
