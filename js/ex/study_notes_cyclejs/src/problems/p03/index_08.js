import xs from 'xstream';
import {run} from '@cycle/run';
import {div, button, h1, h4, a, makeDOMDriver, DOMSource} from '@cycle/dom';
import {makeHTTPDriver, Response, HTTPSource} from '@cycle/http';

function main(sources) {
  const requests$ = intent(sources)
  const {user$, todo$} = model(sources)
  const userVdom$ = user$.map( user => 
    user
  )
  const todoVdom$ = todo$.map( todo =>
    todo
  )
  userVdom$.addListener({
    next: x => console.log(x),
  })
  todoVdom$.addListener({
    next: x => console.log(x),
  })
  return {
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
        url: 'https://jsonplaceholder.typicode.com/users/1',
        category: 'users',
        method: 'GET',
      },
      {
        url: 'https://jsonplaceholder.typicode.com/todos/1',
        category: 'todos',
        method: 'GET',
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

