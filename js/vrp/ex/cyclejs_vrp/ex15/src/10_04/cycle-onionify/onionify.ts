import xs, {Stream} from 'xstream';
import concat from 'xstream/extra/concat';
import {StateSource} from './StateSource';
import microtask from 'quicktask';
import { addListenerStream } from '../interfaces';

const schedule = microtask();
export type MainFn<So, Si> = (sources: So) => Si;
type Reducer<T> = (state: T | undefined) => T | undefined;
type OSo<T> = { onion: StateSource<T> };
type OSi<T> = { onion: Stream<Reducer<T>> };
type Omit<T, K extends keyof T> = Pick<T, Exclude<keyof T, K>> 

type MainOnionified<T, So extends OSo<T>, Si extends OSi<T>> =
    MainFn<Omit<So, 'onion'>, Omit<Si, 'onion'>>; 
export function onionify<T, So extends OSo<T>, Si extends OSi<T>>
    (main: MainFn<So, Si>, name: string = 'onion'): MainOnionified<T, So, Si> {
    return function mainOnionified(sources: So): Si {
        const reducerMimic$ = xs.create<Reducer<T>>();
        const state$ = reducerMimic$
            .debug((reducer) => {console.log("onion.state$ reducer"); console.log(reducer); })
            .fold((state, reducer) =>  {
                return reducer(state)
            }, void 0 as (T | undefined)
            )
            .debug((state) => { console.log("onion.state$ state"); console.log(state); })
            .drop(1);
        addListenerStream(state$, "onion.state$")
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
        return sinks;
    };
}