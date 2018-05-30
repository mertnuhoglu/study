import { run } from '@cycle/run';
import { makeDOMDriver } from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';
import xs from 'xstream'
import {label, input, hr, div, h1} from '@cycle/dom';
import {a, li, ul} from '@cycle/dom';
import {h} from '@cycle/dom';
import "./import_jquery";

function main(sources) {
  const vdom$ = xs.periodic(1000).
      map( x => {
        //jQuery('#table_id').DataTable()
        const r = div([ 
          'Hello Datatables',
        ])
        return r
      })
  vdom$.subscribe({
    next: (value) => {
      jQuery('#table_id').DataTable()
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

