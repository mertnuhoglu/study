import xs, {Stream} from 'xstream';
import concat from 'xstream/extra/concat';
import {StateSource} from './StateSource';
import microtask from 'quicktask';

const schedule = microtask();
type MainFn<So, Si> = (sources: So) => Si;
type Reducer<T> = (state: T | undefined) => T | undefined;
type OSo<T> = { onion: StateSource<T> };
type OSi<T> = { onion: Stream<Reducer<T>> };
type Omit<T, K extends keyof T> = Pick<T, Exclude<keyof T, K>> 

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