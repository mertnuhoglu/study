const {button, h1, h4, a, div, makeDOMDriver} = CycleDOM;
const {makeHTTPDriver} = CycleHTTPDriver;

// DOM read effect: button clicked
// HTTP write effect: request sent
// HTTP read effect: response received
// DOM write effect: data displayed

function main(sources) {
  const clickEvent$ = sources.DOM
    .select('.get-first').events('click');
  
  const request$ = clickEvent$.map(() => {
    return {
      url: 'http://localhost:3000/comments/1',
      method: 'GET',
    };
  });
  
  const response$$ = sources.HTTP
    .filter(response$ => response$.request.url ===
           'http://localhost:3000/comments/1');
  
  const response$ = response$$.switch();
  const firstComment$ = response$.map(response => response.body)
    .startWith(null);
  
  return {
    DOM: firstComment$.map(firstComment =>
      div([
        button('.get-first', 'Get first comment'),
        firstComment === null ? null : div('.comment-details', [
          h4('.comment-body', firstComment.body),
          h4('.comment-id', `${firstComment.id}`)
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
