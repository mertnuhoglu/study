import xs from 'xstream'
import "./import_jquery";
import {div, makeDOMDriver} from '@cycle/dom';
import isolate from '@cycle/isolate';
import model from './model';
import intent from './intent';
import Header from '../header';
import PlanPanel from '../plan_panel';
import DetailPanel from '../detail_panel';

export default function main(sources) {
  const headerSinks = isolate(Header, {storage: null, onion: null, Hot: null})(sources)
  const planPanelSinks = isolate(PlanPanel, {storage: null, onion: null, Hot: null})(sources)
  const detailPanelSinks = isolate(DetailPanel, {storage: null, onion: null, Hot: null})(sources)
  const parentReducer$ = model(sources.HTTP);
  const reducer$ = xs.merge(
    parentReducer$
  );
  const vdom$ = xs.combine(
    headerSinks.DOM,
    planPanelSinks.DOM,
    detailPanelSinks.DOM,
  ).map( ([header, plan_panel, detail_panel]) =>
    div('.vrp_container',
      [
        header,
        plan_panel,
        detail_panel, 
      ]
    )
  )

  var data1 = [
    ['', 'Tesla', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
    ['2017', 10, 11, 12, 13, 15, 16],
  ];
  console.log("sources.onion")
  sources.onion.state$
    .debug(x => {
      console.log("state$ in App")
      console.log(x)
    })

  return {
    DOM: vdom$,
    HTTP: intent(sources.DOM),
    onion: reducer$,
    Hot: xs.periodic(2000)
    .take(1)
    .map(i => data1),
  };
}

