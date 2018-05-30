import { run } from '@cycle/run';
import { makeDOMDriver } from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';
import xs from 'xstream'
import {label, input, hr, div, h1} from '@cycle/dom';
import {a, li, ul} from '@cycle/dom';
import {table, thead, tbody, th, td, tr} from '@cycle/dom';
import {h} from '@cycle/dom';
const Handsontable = require('handsontable/dist/handsontable.full.min.js')
import 'handsontable/dist/handsontable.full.min.css'

var data = [
  ["", "Ford", "Tesla", "Toyota", "Honda"],
  ["2017", 10, 11, 12, 13],
  ["2018", 20, 11, 14, 13],
  ["2019", 30, 15, 12, 13]
];

function main(sources) {
  const vdom$ = xs.of(
    div([ 
      'Hello Handsontable',
      table("#vdom_table.display", [
        thead(
          tr([
            th('Plan Id'),
            th('Kullanıcı'),
            th('Depot Id')
          ])
        ),
        tbody(
          tr([
            td("a"),
            td("b"),
            td("c"),
          ])
        )
      ])
    ])
  )
  vdom$.subscribe({
    next: (value) => {
      var container = document.getElementById('vdom_table');
      var hot = new Handsontable(container, {
        data: data,
        rowHeaders: true,
        colHeaders: true,
        filters: true,
        dropdownMenu: true
      });
    }
  })
  return {
    DOM: vdom$
  }
}

const drivers = {
  DOM: makeDOMDriver('#app'),
}

run(main, drivers);

