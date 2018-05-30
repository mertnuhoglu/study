import xs from 'xstream';
import {run} from '@cycle/run';
import {div, button, h1, h4, a, makeDOMDriver, DOMSource} from '@cycle/dom';
import {makeHTTPDriver, Response, HTTPSource} from '@cycle/http';

function main(sources) {
  const requests$ = intent(sources)
  const {user$, todo$} = model(sources)
  const vdom$ = view({user$, todo$})
  return {
    DOM: vdom$,
    HTTP: requests$,
  };
}

run(main, {
  DOM: makeDOMDriver('#app'),
  HTTP: makeHTTPDriver(),
});


function intent(sources) {
  const requests$ = xs.from(
    [
      {
        url: 'http://localhost:8080/rest/plan?select=plan_id,usr,depot_id',
        method: 'GET',
        headers: {
          "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
        },
        category: 'users',
      },
      {
        url: 'http://localhost:8080/rest/purchase_order?select=purchase_order_id,company_id,order_extid,company_extid',
        method: 'GET',
        headers: {
          "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
        },
        category: 'todos',
      },
    ]
  )
  return requests$
}

function model(sources) {
  const user$ = sources.HTTP.select('users')
    .flatten()
    .map(res => 
      res.body
    )
    .startWith(null)
  const todo$ = sources.HTTP.select('todos')
    .flatten()
    .map(res => res.body)
    .startWith(null)
  return {user$, todo$}
}

function view(state) {
  const {user$, todo$} = state
  const userVdom$ = user$
    .map( users =>
      div(
        users === null ? "" : users.map( user =>
          div(user.usr)
        )
      ),
    )

  const todoVdom$ = todo$
    .map( todos =>
      div(
        todos === null ? "" : todos.map( todo =>
          div(todo.purchase_order_id)
        )
      ),
    )
  const vdom$ = xs.combine(
    userVdom$, 
    todoVdom$,
  )
    .map( ([user, todo]) =>
      div([
        user,
        todo,
      ])
    )
  return vdom$
}

