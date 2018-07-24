import { makeHTTPDriver } from '@cycle/http';
import { run } from '@cycle/run';
import onionify from 'cycle-onionify';
// import 'handsontable/dist/handsontable.full.min.css';
import App from './components/app';

const main: any = onionify(App);

const drivers = {
  // DOM: makeDOMDriver('#app'),
  HTTP: makeHTTPDriver(),
  // Hot: makeHotDriver("#example", [[]]),
};

run(main, drivers);
