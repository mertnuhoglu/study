// @description: Reducer type safe olsun

import Stream from 'xstream';
import xs from 'xstream';

export type A = {a: string}
type B = {b: string}
type State = A | B
type A_is_State = A extends State? true : false  // true
// @essence
type Reducer<S extends State> = (prev?: S) => S 
const redA$: Stream<Reducer<A>> = xs.of(
    function initReducer(prev: A) {
        const initialState: A = {
            a: "a"
        }
        return initialState
    }
)
const redB$: Stream<Reducer<B>> = xs.of(
    function initReducer(prev: B) {
        const initialState: B = {
            b: "b"
        }
        return initialState
    }
)