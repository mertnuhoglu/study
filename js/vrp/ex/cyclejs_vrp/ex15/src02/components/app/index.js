import xs from 'xstream'
import "./import_jquery";
import {div, makeDOMDriver} from '@cycle/dom';
import model from './model';
import intent from './intent';
import Header from '../header';
import PlanPanel from '../plan_panel';
import DetailPanel from '../detail_panel';


export default function main(sources) {
  var data1 = [
    ['', 'Tesla', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
    ['2017', 10, 11, 12, 13, 15, 16],
  ];
  const parentReducer$ = model(sources.HTTP);
  const reducer$ = xs.merge(
    parentReducer$
  );
  const state$ = sources.onion.state$;
  const vdom$ = xs.combine(
    Header(),
    PlanPanel(state$),
    DetailPanel(state$),
  ).map( ([header, plan_panel, detail_panel]) =>
    div('.vrp_container',
      [
        header,
        plan_panel,
        detail_panel, 
      ]
    )
  )

  return {
    DOM: vdom$,
    HTTP: intent(sources.DOM),
    onion: reducer$,
    Hot: xs.periodic(2000)
      .map(i => {
        console.log(data1)
        data1[0][i % 7] = i; 
        return data1
      })
  };
}

