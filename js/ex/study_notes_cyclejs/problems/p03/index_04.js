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
  const getRandomUser$ = sources.DOM.select('.get-random').events('click')
    .map(() => {
      return {
        url: 'https://jsonplaceholder.typicode.com/users/1',
        category: 'users',
        method: 'GET',
      };
    });
  const getTodo$ = sources.DOM.select('.get-todo').events('click')
    .map(() => {
      return {
        url: 'https://jsonplaceholder.typicode.com/todos/1',
        category: 'todos',
        method: 'GET',
      };
    });
  return xs.merge(getRandomUser$, getTodo$)
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
  const userVdom$ = user$.map( user =>
    div('.users', [
      button('.get-random', 'Get user'),
      user === null ? null : div('.user-details', [
        h1('.user-name', user.name),
        a('.user-website', {attrs: {href: user.website}}, user.website),
      ]),
    ])
  )
  const todoVdom$ = todo$.map( todo =>
    div('.todos', [
      button('.get-todo', 'Get todo'),
      todo === null ? null : div('.todo-details', [
        h1('.todo-title', todo.title),
      ]),
    ])
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
