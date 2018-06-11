const xs = require('xstream').default

const subLens = {
  get: (state) => ({
    ...state,
    firstPlan: state.plans.filter(plan => plan.plan_id === 1),
  }),
  set: (state, childState) => ({
    ...state,
  })
}
function main(sources) {
  const firstPlan$ = sources.onion.state$.map(state => state.firstPlan)
  firstPlan$.addListener({
    next: i => console.log(`firstPlan$: ${JSON.stringify(i)}`),
    error: err => console.error(err),
    complete: () => console.log('firstPlan$ completed'),
  })
  return {
    onion: xs.create(),
  }
}

exports.main = main
exports.subLens = subLens
