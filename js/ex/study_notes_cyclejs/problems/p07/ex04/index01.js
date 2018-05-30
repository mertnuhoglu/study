import xs from 'xstream';
import {run} from '@cycle/run';
import {button, div, input, section, span, makeDOMDriver} from '@cycle/dom';
import addListener, {addListenerStream} from 'jsutils_mn'

function intent(dom$) {
  const newItem$ = dom$.select('.new_item_submit').events('click')
    .mapTo({type: "NEW_ITEM"})
  const removeItem$ = dom$.select('.remove_item').events('click')
    .map((e) => ({type: "REMOVE_ITEM", payload: e.target.dataset.item_id}))
  return {newItem$, removeItem$ }
}
function model(actions) {
  const newItem$ = actions.newItem$.mapTo(function newItemReducer(prevState){
    return [
      ...prevState,
      {"item_id": 3, "title": "item03"}
    ]
  })
  const removeItem$ = actions.removeItem$.mapTo(function removeItemReducer(prevState) {
    return {}
  })
  return {newItem$, removeItem$}
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

const seeds = [
  {"item_id": 1, "title": "item01"},
  {"item_id": 2, "title": "item02"},
]
function main(sources) {
  const actions = intent(sources.DOM)
  const reducers = model(actions)
  //addListener(reducers)
  const state$ = xs.merge(...reducers)
    .debug( (x) => {
      console.log("xs.merge")
      console.log(x)
    } )
    .fold((listItems, reducer) => reducer(listItems), seeds)
    .startWith(seeds)
  addListenerStream(state$, "state$")
  const vdom$ = view(state$)
  return {
    DOM: vdom$,
  };
    }

      run(main, {
        DOM: makeDOMDriver('#app')
      });

