import { h, app } from "hyperapp"

const state = {
  count: 0
}

const actions = {
  down: value => state => ({ count: state.count - value }),
  up: value => state => ({ count: state.count + value })
}

const view = (state, actions) => (
	h("div", {}, [
		h("h1", {}, state.count),
		h("button", {onclick: () => actions.down(1)}, "-"),
		h("button", {onclick: () => actions.up(1)}, "+"),
	])
)

app(state, actions, view, document.body)

