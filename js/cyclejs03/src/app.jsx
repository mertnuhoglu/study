import Rx from 'rxjs/Rx'

export function App (sources) {
  const vtree$ = Rx.Observable.of(
    <div>My Awesome Cycle.js app 3</div>
  )
  const sinks = {
    DOM: vtree$
  }
  return sinks
}
