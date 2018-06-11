function isolate(component, scope) {
  // lens = scope
  lens = scope.lens
  return function wrappedComponent(outerSources) {
    // outerSources = sources from App
    const innerSources = isolateAllSources(outerSources, lens)
    //const innerSources = isolateAllSources({onion: {state$ : xs.create()}}, lens)
    // innerSinks = sinks of component
    // innerSources.onion.state$ = sources.onion.state$.map(lens.get)
    const innerSinks = component(innerSources)
    // outerSinks = sinks of component returned to App
    const outerSinks = isolateAllSinks(outerSources, innerSinks, lens)
    return outerSinks
  }
}
function isolateAllSources(outerSources, lens) {
  const innerSources = {}
  for (const channel in outerSources) {
    innerSources[channel] = outerSources[channel]
  }
  innerSources.onion = {}
  innerSources.onion.state$ = outerSources.onion.state$.map(lens.get)
  return innerSources
}
function isolateAllSinks(outerSources, innerSinks, lens) {
  // innerReducer$ = reducer$ of component
  const innerReducer$ = innerSinks.onion
  // outerReducer$ = reducer$ of component returned to App
  const outerReducer$ = innerReducer$.map( innerReducer =>
    function outerReducer(outer) {
      // outer = prevState of App
      // prevInner = prevState of component
      const prevInner = lens.get(outer)
      // nextInner = nextState of component
      const nextInner = innerReducer(prevInner)
      // nextState to return to parent component
      return lens.set(outer, nextInner)
    }
  )
  const outerSinks = innerSinks
  outerSinks.onion = outerReducer$
  return outerSinks
}

exports.isolate = isolate
