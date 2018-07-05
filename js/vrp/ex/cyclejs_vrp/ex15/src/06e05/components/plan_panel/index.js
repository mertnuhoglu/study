//import xs from 'xstream';
import xs from '../../xstream';
import { a, article, button, div, form, h3, input, label, li, nav, option, select, span, table, tbody, td, th, thead, tr, ul } from '@cycle/dom';
import { style } from 'typestyle';
            var toHTML = require('snabbdom-to-html')
            global.toHTML = toHTML

const styles = style({
});

export default function PlanPanel(sources) {
  const state$ = sources.onion.state$;
  const panel_vdom$ = state$
    .debug( x => {
      console.log("PlanPanel state: " )
      console.log(x)
    })
    .map( ({body}) =>
    div(
      body.map(e => 
        div([
          div(e.plan_id),
        ])
      ),
    ),
  )
  global.state$ = state$
  global.sources = sources
  const sinks = {
    DOM: panel_vdom$,
  }
  return sinks
}

