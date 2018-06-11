import xs from 'xstream';
import { a, article, button, div, form, h3, input, label, li, nav, option, select, span, table, tbody, td, th, thead, tr, ul } from '@cycle/dom';
import { style } from 'typestyle';

const styles = style({
});

export default function PlanPanel(state$) {
	const panel_vdom$ = state$.map( ({body}) =>
    body.map(e => "ali"),
	)
	return panel_vdom$
}

