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

const initial_data = [
  ['', 'Tesla', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
  ['2017', 10, 11, 12, 13, 15, 16],
]

const drivers = {
  DOM: makeDOMDriver('#app'),
  HTTP: makeHTTPDriver(),
  storage: storageDriver,
  //Hot: makeHotDriver("#example", [[]]),
};

function makeHotDriver(mount_id, initial_data) {
  var hot;
  var container = document.querySelector(mount_id);
  hot = new Handsontable(container, {
    data: initial_data,
    minRows: 1,
    minCols: 7,
    minSpareRows: 1,
    contextMenu: true
  });
  return function HotDriver(data$) {
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
}

run(wrappedMain, drivers);
