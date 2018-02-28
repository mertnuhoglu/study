import {run} from '@cycle/run';
import {div, label, input, hr, h1, makeDOMDriver} from '@cycle/dom';

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
        h1(`merhaba ${name}`)
      ])
    );
  return {
    DOM: vdom$,
  };
}

run(main, {
  DOM: makeDOMDriver('#root')
});

//import {run} from '@cycle/run'
//import {makeDOMDriver} from '@cycle/dom'
//import {App} from './app'

//const main = App

//const drivers = {
  //DOM: makeDOMDriver('#root')
//}

//run(main, drivers)
