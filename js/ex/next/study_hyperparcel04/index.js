import { h, app } from 'hyperapp'
import debounce from 'debounce-promise'
import './styles/main.sass'

const state = {
  username: '',
  userData: null,
}

const getUserDataFn = username => {
  return fetch(`https://api.github.com/users/${username}`)
    .then(res => res.json())
}
const getUserData = debounce(getUserDataFn, 700)
const actions = {
  updateUsername: (username) => (state, actions) => {
    getUserData(username).then(actions.setUserData)
    return { username }
  },
  setUserData: userData => state => ({ userData })
}


const view = (state, actions) =>
  h("main", {}, 
    [
      h("div", {}, "Search github user"),
      h("input", {type: 'text', className: 'searchInput',
        value:`${state.username}`,
        oninput: `{e => actions.updateUsername(e.target.value)}`
      })
    ]
  )
app(state, actions, view, document.body)

