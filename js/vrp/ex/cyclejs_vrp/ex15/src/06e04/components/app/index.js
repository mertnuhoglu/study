//import xs from 'xstream'
import xs from '../../xstream';
import "./import_jquery";
import {div, makeDOMDriver} from '@cycle/dom';
//import isolate from '@cycle/isolate';
import isolate from './isolate';
import model from './model';
import intent from './intent';
import Header from '../header';
import PlanPanel from '../plan_panel';
import DetailPanel from '../detail_panel';

var data1 = [
  ['', 'Tesla', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
  ['2017', 10, 11, 12, 13, 15, 16],
];

export default function main(sources) {
  const state$ = sources.onion.state$
    .debug( x => console.log("state: "))
    .debug( x => console.log("state: " + x))

  global.state0$ = state$
  global.sources0 = sources
  const headerSinks = isolate(Header, ".header1")(sources)
  console.log("isolate called")
  //const planPanelSinks = PlanPanel(sources)
  const planPanelSinks = isolate(PlanPanel, ".planpanel1")(sources)
  console.log("isolate called for PlanPanel")
  const parentReducer$ = model(sources.HTTP);
  const reducer$ = xs.merge(
    parentReducer$
  )
    .debug( x => console.log("reducer: " + x))
  const vdom$ = xs.combine(
    headerSinks.DOM,
    planPanelSinks.DOM,
  ).map( ([header, plan_panel]) =>
    div('.vrp_container',
      [
        header,
        plan_panel,
      ]
    )
  )

  return {
    DOM: vdom$,
    HTTP: intent(sources.DOM),
    onion: reducer$,
  };
}

