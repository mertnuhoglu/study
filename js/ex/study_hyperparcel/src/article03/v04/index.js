import { h, app } from "hyperapp"
import { div, h1, button } from "@hyperapp/html"

const state = {
  count: 0
}

const actions = {
  down: value => state => ({ count: state.count - value }),
  up: value => state => ({ count: state.count + value })
}

const view = (state, actions) => (
	div([
		h1(state.count),
		button({onclick: () => actions.down(1)}, "-"),
		button({onclick: () => actions.up(1)}, "+"),
	])
)

app(state, actions, view, document.body)


