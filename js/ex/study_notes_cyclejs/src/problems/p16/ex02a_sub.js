const xs = require('xstream').default

const subLens = {
  get: (state) => ({
    ...state,
  }),
  set: (state, childState) => ({
    ...state,
  })
}
function main(sources) {
  console.log("inside sub")
  return {
    onion: xs.create()
  }
}

exports.main = main
exports.subLens = subLens
