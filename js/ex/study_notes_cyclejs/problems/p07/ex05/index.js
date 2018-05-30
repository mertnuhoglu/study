import xs from 'xstream';
import {button, div, input, section, span} from '@cycle/dom';
//import addListener, {addListenerStream} from 'jsutils_mn'

function intent(dom$) {
  const newItem$ = dom$.select('.new_item_submit').events('click')
    .mapTo({type: "NEW_ITEM"})
  const removeItem$ = dom$.select('.remove_item').events('click')
    .map((e) => ({type: "REMOVE_ITEM", payload: e.target.dataset.item_id}))
  return {newItem$, removeItem$ }
}
function model(actions) {
  const newItem$ = actions.newItem$.mapTo(function newItemReducer(prevState){
    //window.prevState = prevState
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
const initReducer$ = xs.of(function initReducer() {
  return seeds;
})
export default function MyList(sources) {
  const actions = intent(sources.DOM)
  const reducers = model(actions)
  const reducer$ = xs.merge(initReducer$, reducers.newItem$)
    //.startWith(initReducer$)
  //const reducer$ = xs.merge(initReducer$, ...reducers)
    //.startWith(initReducer$)
  //addListenerStream(reducer$, "reducer$")
  //const state$ = xs.merge(...reducers)
    //.fold((listItems, reducer) => reducer(listItems), seeds)
    //.startWith(seeds)
  const state$ = sources.onion.state$
  //addListenerStream(state$, "state$")
  const vdom$ = view(state$)
  return {
    DOM: vdom$,
    onion: reducer$,
  };
}

