import xs from 'xstream'
import { run } from '@cycle/run';
import { makeDOMDriver } from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';

import App from './components/app';
import 'handsontable/dist/handsontable.full.min.css'

const main = App;

const drivers = {
  DOM: makeDOMDriver('#app'),
  HTTP: makeHTTPDriver(),
};

run(main, drivers);
