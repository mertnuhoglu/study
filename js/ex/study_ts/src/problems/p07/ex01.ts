// @description: Reducer type safe olsun
import Stream from 'xstream';
import xs from 'xstream';

export type A = {a: string}
type B = {b: string}
type State = A | B
type Reducer = (prev?: State) => State
const redA$: Stream<Reducer> = xs.of(
    function initReducer(prev: State) {
        const initialState: State = {
            a: "a"
        }
        return initialState
    }
)
const redB$: Stream<Reducer> = xs.of(
    function initReducer(prev: State) {
        const initialState: State = {
            b: "b"
        }
        return initialState
    }
)