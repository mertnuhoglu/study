import xs from 'xstream';
import {run} from '@cycle/run';
import {div, button, h1, h4, a, makeDOMDriver, DOMSource} from '@cycle/dom';
import {makeHTTPDriver, Response, HTTPSource} from '@cycle/http';

function main(sources) {
  const user$ = sources.HTTP.select('users')
    .flatten()
    .map(res => res.body)
    .startWith(null);

  const getRandomUser$ = sources.DOM.select('.get-random').events('click')
    .map(() => {
      return {
        url: 'https://jsonplaceholder.typicode.com/users/1',
        category: 'users',
        method: 'GET',
      };
    });

  const vdom$ = user$.map(user =>
    div('.users', [
      button('.get-random', 'Get random user'),
      user === null ? null : div('.user-details', [
        h1('.user-name', user.name),
        a('.user-website', {attrs: {href: user.website}}, user.website),
      ]),
    ]),
  );
  return {
    DOM: vdom$,
    HTTP: getRandomUser$,
  };
}


run(main, {
  DOM: makeDOMDriver('#app'),
  HTTP: makeHTTPDriver(),
});

