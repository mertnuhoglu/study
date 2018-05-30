import xs from 'xstream';
import {run} from '@cycle/run';
import {button, div, input, section, span, makeDOMDriver} from '@cycle/dom';

function intent(dom$) {
  const newItem$ = dom$.select('.new_item_submit').events('click')
    .mapTo({type: "NEW_ITEM"})
    .debug()
  const removeItem$ = dom$.select('.remove_item').events('click')
    .map((e) => ({type: "REMOVE_ITEM", payload: e.target.dataset.item_id}))
    .debug()
  return {newItem$, removeItem$ }
}
function model(actions) {
}
function view(state$) {
  return state$.map( (state) => {
    const items = state
    return div([
      section(".new_item", [
        input(".new_item_title"),
        button(".new_item_submit", "Add Item"),
      ]), 
      section(".item_list",        
        items.map( (item) =>
          div(".item",[
            span(".item_title", `${item.title}`),
            button(".remove_item", {dataset: {item_id: item.item_id}}, `Remove ${item.item_id}`),
          ]),
        ) 
      )
    ])
  }
  )
}

function main(sources) {
  const actions = intent(sources.DOM)
  actions.newItem$.addListener({
    next: data => {
      console.log("newItem$")
      console.log(data)
    }})
  actions.removeItem$.addListener({
    next: data => {
      console.log("removeItem$")
      console.log(data)
    }})
  //const state$ = model(actions)
  const state$ = xs.of([
    {"item_id": 1, "title": "item01"},
    {"item_id": 2, "title": "item02"},
  ])
  const vdom$ = view(state$)
  return {
    DOM: vdom$,
  };
}

run(main, {
  DOM: makeDOMDriver('#app')
});

