import { h, app } from 'hyperapp'

//const html = hyperx(hyperapp.h)
const model = 0
const click = model => {
  console.log("clicked", model)
  return model + 1
}
const actions = {click}
const view = (model, actions) => 
	<div>
		<h1>Clicked {model}</h1>
		<button onClick={console.log("hello")}>click</button>
	</div>

//<button onClick={actions.click}>click</button>
app(
  model,
  actions,
  view,
	document.body
)

