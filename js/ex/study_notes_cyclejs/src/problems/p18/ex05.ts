import xs from 'xstream'
import Stream from 'xstream';

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
type MainFn<Si> = () => Si
type Reducer<T> = (state: T) => T
type OSi<T> = {onion: Stream<Reducer<T>>};
type MainOnionified<T, Si extends OSi<T>> = MainFn<Sinks>;
function onionify<T, So, Si extends OSi<T>>(main: MainFn<Si>): MainOnionified<T, Si> {
    return function mainOnionified(): Sinks {
        let sources = {onion: undefined}
        const reducerMimic$ = xs.create<Reducer<T>>();
        const state$ = reducerMimic$
            .fold((state, reducer) => reducer(state), void 0 as (T | undefined))
            .drop(1);
        sources.onion = {state$}
        const sinks = main();
        reducerMimic$.imitate(sinks.onion);
        return sinks;
    }
}
function main(): Sinks {
    const mainOnionified = onionify(comp_a)
    const sinks = mainOnionified()
    return sinks
}

console.log("ex05.ts")
main().onion.addListener({ next: console.log })