// @description: mevcut p18 örneğini sadeleştirmeye çalış
import xs from 'xstream'
import Stream from 'xstream';
import { StateSource } from '../cycle-onionify/StateSource';
import concat from 'xstream/extra/concat';

export type Sources = { onion: StateSource<State> }
export type Sinks = { onion: xs<(s: any) => any | undefined> }
interface State_a { field_a: String }
interface State_b { field_b: String }
export type State = State_a | State_b
type Reducer_a = (prev?: State_a) => State_a | undefined
type Reducer_b = (prev?: State_b) => State_b | undefined
export function model_a(): xs<Reducer_a> {
    return xs.of(
        function initReducer(prevState: State_a) {
            return { field_a: "a" }
        }
    )
}
export function comp_a(sources: Sources): Sinks {
    const parentReducer$ = model_a()
    const reducer$ = xs.merge(
        parentReducer$,
    )
    const sinks: Sinks = {
        onion: reducer$
    }
    const state$ = sources.onion.state$
    state$.addListener({
        next: i => console.log(`state:: ${JSON.stringify(i)}`),
        error: err => console.error(err),
        complete: () => console.log('s1 completed'),
    })
    return sinks
}
export type MainFn<So, Si> = (sources: So) => Si;
type Reducer<T> = (state: T | undefined) => T | undefined;
export type OSo<T> = { onion: StateSource<T> };
export type OSi<T> = { onion: Stream<Reducer<T>> };
export type Omit<T, K extends keyof T> = Pick<T, Exclude<keyof T, K>> // @essence

type MainOnionified<T, So extends OSo<T>, Si extends OSi<T>> = MainFn<Omit<So, 'onion'>, Si>;
export function onionify<T, So extends OSo<T>, Si extends OSi<T>>( main: MainFn<So, Si>): MainOnionified<T, So, Si> {
    return function mainOnionified(sources: So): Si {
        const state$ = xs.create<Reducer<T>>();
        sources['onion'] = new StateSource<any>(state$, 'onion');
        const sinks = main(sources as So);
        return sinks;
    };
}
function main(): Sinks {
    const sources = {} as Omit<Sources, 'onion'>
    const mainOnionified = onionify(comp_a)
    const sinks = mainOnionified(sources)
    return sinks
}

console.log("ex02.ts")
main().onion.addListener({ next: console.log })
