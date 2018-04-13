import { run } from '@cycle/run';
import { makeDOMDriver } from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';
import onionify from 'cycle-onionify';
import storageify from 'cycle-storageify';
import storageDriver from '@cycle/storage';

import App from './components/app';

const testVar01 = 10;
const testVar02 = 10;
const testVar03 = 10;
const testVar04 = 10;
const testVar05 = 10;
const testVar06 = 10;
const testVar07 = 10;

const main = App;
const wrappedMain = onionify(storageify(main, { key: 'mpc-state' }));

const drivers = {
  DOM: makeDOMDriver('#app'),
  HTTP: makeHTTPDriver(),
  storage: storageDriver,
};

run(wrappedMain, drivers);
