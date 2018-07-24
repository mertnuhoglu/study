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

function main(): Sinks {
    const parentReducer$ = model_a()
    const compReducer$ = model_b()
    const reducer$ = xs.merge(
        parentReducer$,
        compReducer$
    )
    const sinks: Sinks = {
        onion: reducer$
    }
    return sinks
}

main().onion.addListener({ next: console.log })