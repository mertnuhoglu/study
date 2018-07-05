import xs from 'xstream'
import { run } from '@cycle/run';
import { makeDOMDriver } from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';
import onionify from 'cycle-onionify';
import storageify from 'cycle-storageify';
import storageDriver from '@cycle/storage';

import App from './components/app';

const Handsontable = require('handsontable/dist/handsontable.full.min.js')
import 'handsontable/dist/handsontable.full.min.css'

const main = App;
const wrappedMain = onionify(storageify(main, { key: 'mpc-state' }));

var data1 = [
  ['', 'Tesla', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
  ['2017', 10, 11, 12, 13, 15, 16],
];
var container = document.getElementById('example');
var hot = new Handsontable(container, {
  data: data1,
});

const drivers = {
  DOM: makeDOMDriver('#app'),
  HTTP: makeHTTPDriver(),
  storage: storageDriver,
  Hot: HotDriver,
};

function HotDriver(data$) {
  data$.addListener({
    next: data => {
      hot.loadData(data)
    }}
  )
  var producer = {
    start: function(observer) {
      hot.addHook('afterChange', function () {
        console.log("changed")
        observer.next(hot.getSourceData())
      })
    },
    stop: function () {
      console.log("stopped")
    }
  }
  const HotSource = xs.create(producer)
  return HotSource
}

run(wrappedMain, drivers);
