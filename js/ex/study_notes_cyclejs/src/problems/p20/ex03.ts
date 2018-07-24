// @description: What happens when `cmp_b` accepts an argument of type `Sources & SoHTTP`
import xs from 'xstream'
import { StateSource } from './cycle-onionify/StateSource';
import isolate, {Component} from './cycle-isolate'
import { Omit, onionify } from './cycle-onionify/onionify';
import { HTTPSource } from '../../../node_modules/@cycle/http';
import Stream from 'xstream';

export type Sources = { onion: StateSource<State> }
type SoHTTP = { HTTP: Stream<HTTPSource> }
type Sinks = { onion: xs<(s: any) => any | undefined> }
interface State_a { field_a: String }
interface State_b { field_b: String }
type State = State_a | State_b
type Reducer_a = (prev?: State_a) => State_a | undefined
type Reducer_b = (prev?: State_b) => State_b | undefined
function model_a(): xs<Reducer_a> {
    return xs.of(
        function initReducer() {
            return { field_a: "a" }
        }
    )
}
function model_b(): xs<Reducer_b> {
    return xs.of(
        function initReducer() {
            return { field_b: "b" }
        }
    )
}
function comp_b(sources: Sources & SoHTTP): Sinks {
    sources.HTTP.debug() // @essence
    const reducer$ = model_b()
    const sinks: Sinks = {
        onion: reducer$
    }
    return sinks
}
function comp_a(sources: Sources): Sinks {
    const parentReducer$ = model_a()
    const cmp_b = isolate(comp_b) as Component<Sources, Sinks>
    const sinks_b = cmp_b(sources) // @essence
    const reducer$ = xs.merge(
        parentReducer$,
        sinks_b.onion,
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

function main(): Sinks {
    const sources = {} as Omit<Sources, 'onion'>
    const mainOnionified = onionify(comp_a)
    const sinks = mainOnionified(sources)
    return sinks
}

console.log("ex03.ts")
main().onion.addListener({ next: console.log })