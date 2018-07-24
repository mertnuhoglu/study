import xs from 'xstream'

type Sinks11 = { onion: xs<(s: any) => any | undefined> }
interface State11a { field_a: String }
interface State11b { field_b: String }
type Reducer11a = (prev?: State11a) => State11a | undefined
type Reducer11b = (prev?: State11b) => State11b | undefined
function model11a(): xs<Reducer11a> {
    return xs.of(
        function initReducer(prevState: State11a) {
            return { field_a: "" }
        }
    )
}
function model11b(): xs<Reducer11b> {
    return xs.of(
        function initReducer(prevState: State11b) {
            return { field_b: "" }
        }
    )
}

function main11(): Sinks11 {
    const parentReducer11$ = model11a()
    const compReducer11$ = model11b()
    const reducer11$ = xs.merge(
        parentReducer11$,
        compReducer11$
    )
    const sinks11: Sinks11 = {
        onion: reducer11$
    }
    return sinks11
}