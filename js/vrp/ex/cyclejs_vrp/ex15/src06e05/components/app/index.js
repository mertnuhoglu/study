import xs from '../../xstream';
import "./import_jquery";
import {div, makeDOMDriver} from '@cycle/dom';
import isolate from './isolate';
import model from './model';
import intent from './intent';
import PlanPanel from '../plan_panel';

export default function main(sources) {
  const state$ = sources.onion.state$
    .debug( x => console.log("state: "))
    .debug( x => console.log("state: " + x))
  console.log("isolate called")
  //const planPanelSinks = PlanPanel(sources)
  const planPanelSinks = isolate(PlanPanel, {storage: null, onion: null})(sources)
  console.log("isolate called for PlanPanel")
  const parentReducer$ = model(sources.HTTP);
  const reducer$ = xs.merge(
    parentReducer$
  )
    .debug( x => console.log("reducer: " + x))
  const vdom$ = planPanelSinks.DOM
    .map( (plan_panel) =>
      div('.vrp_container',
        [
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

