// acaba sources: {DOM, HTTP} olmasından dolayı mı oldu?
import { DOMSource, VNode } from '@cycle/dom';
import { HTTPSource } from '@cycle/http';
import xs from 'xstream'
import Stream from 'xstream';
import { StateSource } from './cycle-onionify/StateSource';
import concat from 'xstream/extra/concat';
import microtask from 'quicktask';

const schedule = microtask();

// @essence
export type Sources = { 
    onion: StateSource<State>
    DOM: DOMSource
    HTTP: HTTPSource
}
type Sinks = { onion: xs<(s: any) => any | undefined> }
interface State_a { field_a: String }
interface State_b { field_b: String }
type State = State_a | State_b
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
function comp_b(sources: Sources): Sinks {
    const reducer$ = model_b()
    const sinks: Sinks = {
        onion: reducer$
    }
    return sinks
}
function comp_a(sources: Sources): Sinks {
    const parentReducer$ = model_a()
    const cmp_b = comp_b(sources)
    const reducer$ = xs.merge(
        parentReducer$,
        cmp_b.onion,
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
type MainFn<So, Si> = (sources: So) => Si;
type Reducer<T> = (state: T | undefined) => T | undefined;
type OSo<T> = { onion: StateSource<T> };
type OSi<T> = { onion: Stream<Reducer<T>> };
type Omit<T, K extends keyof T> = Pick<T, Exclude<keyof T, K>> // @essence

type MainOnionified<T, So extends OSo<T>, Si extends OSi<T>> =
    MainFn<Omit<So, 'onion'>, Si>;
export function onionify<T, So extends OSo<T>, Si extends OSi<T>>(
    main: MainFn<So, Si>,
    name: string = 'onion'): MainOnionified<T, So, Si> {
    return function mainOnionified(sources: So): Si {
        const reducerMimic$ = xs.create<Reducer<T>>();
        const state$ = reducerMimic$
            .fold((state, reducer) => reducer(state), void 0 as (T | undefined))
            .drop(1);
        sources[name] = new StateSource<any>(state$, name);
        const sinks = main(sources as So);
        if (sinks[name]) {
            const stream$ = concat(
                xs.fromObservable<Reducer<T>>(sinks[name]),
                xs.never(),
            );
            stream$.subscribe({
                next: i => schedule(() => reducerMimic$._n(i)),
                error: err => schedule(() => reducerMimic$._e(err)),
                complete: () => schedule(() => reducerMimic$._c()),
            })
        }
        reducerMimic$.imitate(sinks.onion);
        return sinks;
    };
}
function main(): Sinks {
    const sources = {} as Omit<Sources, 'onion'>
    const mainOnionified = onionify(comp_a)
    const sinks = mainOnionified(sources)
    return sinks
}

console.log("ex08.ts")
main().onion.addListener({ next: console.log })