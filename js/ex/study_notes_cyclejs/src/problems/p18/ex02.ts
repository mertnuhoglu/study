import xs from 'xstream'

export type Sinks = { onion: xs<(s: any) => any | undefined> }
interface State_a { field_a: String }
interface State_b { field_b: String }
type Reducer_a = (prev?: State_a) => State_a | undefined
type Reducer_b = (prev?: State_b) => State_b | undefined
function model_a(): xs<Reducer_a> {
    return xs.of(
        function initReducer(prevState: State_a) {
            return { field_a: "a" }
        }
    )
}
function model_b(): xs<Reducer_b> {
    return xs.of(
        function initReducer(prevState: State_b) {
            return { field_b: "b" }
        }
    )
}
function comp_b(): Sinks {
    const reducer$ = model_b()
    const sinks: Sinks = {
        onion: reducer$
    }
    return sinks
}
function comp_a(): Sinks {
    const parentReducer$ = model_a()
    const cmp_b = comp_b()
    const reducer$ = xs.merge(
        parentReducer$,
        cmp_b.onion,
    )
    const sinks: Sinks = {
        onion: reducer$
    }
    return sinks
}
function main(): Sinks {
    const sinks = comp_a()
    return sinks
}

main().onion.addListener({ next: console.log })
