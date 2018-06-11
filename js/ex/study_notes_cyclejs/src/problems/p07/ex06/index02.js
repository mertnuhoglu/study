import xs from 'xstream';
import sampleCombine from 'xstream/extra/sampleCombine'
import {button, div, input, section, span} from '@cycle/dom';
import addListener, {addListenerStream} from 'jsutils_mn'

function intent(dom$) {
  const updateInput$ = dom$.select('.new_item_title').events('input')
    .map(ev => ev.target.value.trim())
  const submit$ = dom$.select('.new_item_submit').events('click')
    .mapTo({type: "NEW_ITEM"})
  const newItem$ = updateInput$.compose(sampleCombine(submit$))
    .map( ([title, submit]) => title)
    .debug()
  const removeItem$ = dom$.select('.remove_item').events('click')
    .map((e) => ({type: "REMOVE_ITEM", payload: e.target.dataset.item_id}))
  return {newItem$, removeItem$ }
}
function model(actions) {
  const newItem$ = actions.newItem$.map( title =>
    function newItemReducer(prevState){
      //window.prevState = prevState
      return [
        ...prevState,
        {"item_id": 3, "title": title}
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
  const state$ = sources.onion.state$
  addListenerStream(state$, "state$")
  const vdom$ = view(state$)
  return {
    DOM: vdom$,
    onion: reducer$,
  };
}

