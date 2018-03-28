import Rx from 'rxjs/Rx'
import {button, h1, h4, a, div, table, thead, tbody, tr, td, th} from '@cycle/dom';

export function App (sources) {
  const vtree$ = Rx.Observable.of(
    div('My Awesome Cycle.js app')
  )
  const sinks = {
    DOM: vtree$
  }
  return sinks
}
