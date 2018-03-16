import { h, app } from 'hyperapp'

const view = (state, actions) =>
  h("div", {}, "hello hyperapp")
app({}, {}, view, document.body)

