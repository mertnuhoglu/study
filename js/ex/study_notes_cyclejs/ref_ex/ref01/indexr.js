const xs = require('xstream').default;
const {run} = require('@cycle/run');
const {div, label, input, hr, h1, makeDOMDriver} = require('@cycle/dom');

function main(sources) {
  const vdom$ = sources.DOM
    .select('.myinput').events('input')
    .map(ev => ev.target.value)
    .startWith('')
    .map(name =>
      div([
        label('Name:'),
        input('.myinput', {attrs: {type: 'text'}}),
        hr(),
        h1(`Hello ${name}`)
      ])
    );
  return {
    DOM: vdom$,
  };
}

run(main, {
  DOM: makeDOMDriver('#app')
});

