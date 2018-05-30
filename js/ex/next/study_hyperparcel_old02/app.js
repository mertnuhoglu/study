const html = hyperx(hyperapp.h)
const model = 0
const click = model => model + 1
const actions = {click}
const view = (model, actions) => {
  return html`
    <div>
      <h1>Clicked ${model}</h1>
      <button onClick=${actions.click}>click</button>
    </div>
  `
}
hyperapp.app(model, actions, view, document.body)
//hyperapp.app({
  //model,
  //actions,
  //view
//})
