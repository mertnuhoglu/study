import xs from 'xstream'
import { run } from '@cycle/run';
import { makeDOMDriver } from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';
import onionify from 'cycle-onionify';
import storageify from 'cycle-storageify';
import storageDriver from '@cycle/storage';
import makeHotDriver from './drivers/hot_driver';

import App from './components/app';
import 'handsontable/dist/handsontable.full.min.css'

const main = App;
//const wrappedMain = onionify(storageify(main, { key: 'mpc-state' }));
const wrappedMain = onionify(main);

const drivers = {
  DOM: makeDOMDriver('#app'),
  HTTP: makeHTTPDriver(),
  storage: storageDriver,
  Hot: makeHotDriver("#example", [[]]),
};

run(wrappedMain, drivers);
