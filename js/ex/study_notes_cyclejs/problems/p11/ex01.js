const xs = require('xstream').default

// ticks:  -0-1-2-3-4-5-...100
const ticks = xs.periodic(100).take(100)

// pop(t) = 0.05 * dt * pop(t-1) + pop(t-1)
const population = ticks.fold((acc,cur) =>
  0.05 * 0.1 * acc + acc,
  100
)
population.addListener({
  next: i => console.log(`pop: ${i}`),
  error: err => console.error(err),
  complete: () => console.log('s1 completed'),
})
// pop: 100
// pop: 100.5
// pop: 101.0025
// pop: 101.5075125
// pop: 102.01505006250001
// ...
