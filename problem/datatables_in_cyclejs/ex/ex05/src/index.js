import { run } from '@cycle/run';
import { makeDOMDriver } from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';
import xs from 'xstream'
import {label, input, hr, div, h1} from '@cycle/dom';
import {a, li, ul} from '@cycle/dom';
import {h} from '@cycle/dom';
import "./import_jquery";

function main(sources) {
  const vdom$ = xs.of(
    div([ 
      'Hello Datatables',
    ])
  )
  const data = [
    {
      "name":       "Ali Osman",
      "position":   "System Architect",
      "salary":     "$3,120",
      "start_date": "2011/04/25",
      "office":     "Edinburgh",
      "extn":       "5421"
    },
    {
      "name":       "Garrett Winters",
      "position":   "Director",
      "salary":     "$5,300",
      "start_date": "2011/07/25",
      "office":     "Edinburgh",
      "extn":       "8422"
    }
  ]
  vdom$.subscribe({
    next: (value) => {
      jQuery('#table_id').DataTable( {        
        data: data,
        columns: [
          { data: 'name' },
          { data: 'position' },
          { data: 'salary' },
          { data: 'office' }
        ]
      })
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

