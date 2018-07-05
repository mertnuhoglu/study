import xs from 'xstream'
import { run } from '@cycle/run';
import { makeDOMDriver } from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';
import makeHotDriver from './drivers/hot_driver';

import App from './components/app';
import 'handsontable/dist/handsontable.full.min.css'

//const main = App;
const initialState = {
  plans: [], 
  purchase_orders: [],
}
function onion(reducer$) {
  const state$ = xs.merge(reducer$)
    .fold((prevState, reducer) => reducer(prevState), initialState);
  return state$
}
function onionify(main) {
  return function mainOnionified(sources) {
    const reducerMimic$ = xs.create()
    const state$ = onion(reducerMimic$)
    sources.onion = {state$}
    const sinks = main(sources)
    reducerMimic$.imitate(sinks.onion)
    return sinks
  }
}
const main = onionify(App);

const drivers = {
  DOM: makeDOMDriver('#app'),
  HTTP: makeHTTPDriver(),
  Hot: makeHotDriver("#example", [[]]),
};

run(main, drivers);
