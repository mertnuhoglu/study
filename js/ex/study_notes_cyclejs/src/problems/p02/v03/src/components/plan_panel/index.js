import xs from 'xstream';
import { a, article, button, div, form, h3, input, label, li, nav, option, select, span, table, tbody, td, th, thead, tr, ul } from '@cycle/dom';
import { style } from 'typestyle';

const styles = style({
});

export default function PlanPanel(state$) {
	const panel_vdom$ = state$
		.debug( x => console.log(x))
    .debug( ({body}) => console.log(body))
    .map( ({body}) =>
      div([
        body.map(e => 
          div([
            td(e.plan_id),
          ])
        ),
      ])
    )
  return panel_vdom$
}

