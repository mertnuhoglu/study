import xs from 'xstream'
import { VNode } from '@cycle/dom';

type Sinks1 = {
    onion: xs<number>
}

function main1(): Sinks1 {
    const reducer1$ = xs.from([1,2,3])
    const sinks1: Sinks1 = {
        onion: reducer1$
    }
    return sinks1
}

type Sinks2 = {
    onion: xs<(s: number) => number>
}

function main2(): Sinks2 {
    const reducer2$ = xs.of(
        function initReducer(prevState: number) {
            return prevState + 1
        }
    )
    const sinks2: Sinks2 = {
        onion: reducer2$
    }
    return sinks2
}

type Sinks3 = {
    onion: xs<(s: number) => number>
}

function main3(): Sinks3 {
    const parentReducer3$ = xs.of(
        function initReducer(prevState: number) {
            return prevState + 1
        }
    )
    const compReducer3$ = xs.of(
        function initReducer(prevState: number) {
            return prevState + 1
        }
    )
    const reducer3$ = xs.merge(
        parentReducer3$,
        compReducer3$
    )
    const sinks3: Sinks3 = {
        onion: reducer3$
    }
    return sinks3
}

type Sinks4 = {
    onion: xs<(s: number) => number | undefined>
}

function main4(): Sinks4 {
    const parentReducer4$ = xs.of(
        function initReducer(prevState: number) {
            return prevState + 1
        }
    )
    const compReducer4$ = xs.of(
        function initReducer(prevState: number) {
            return prevState + 1
        }
    )
    const reducer4$ = xs.merge(
        parentReducer4$,
        compReducer4$
    )
    const sinks4: Sinks4 = {
        onion: reducer4$
    }
    return sinks4
}

type Sinks5 = {
    onion: xs<(s: any) => any | undefined>
}

function main5(): Sinks5 {
    const parentReducer5$ = xs.of(
        function initReducer(prevState: number) {
            return prevState + 1
        }
    )
    const compReducer5$ = xs.of(
        function initReducer(prevState: number) {
            return prevState + 1
        }
    )
    const reducer5$ = xs.merge(
        parentReducer5$,
        compReducer5$
    )
    const sinks5: Sinks5 = {
        onion: reducer5$
    }
    return sinks5
}

type Sinks6 = {
    onion: xs<(s: any) => any | undefined>
}

interface State6a {
    field_a: String
}

interface State6b {
    field_b: String
}

function main6(): Sinks6 {
    const parentReducer6$ = xs.of(
        function initReducer(prevState: State6a) {
            return {field_a: ""}
        }
    )
    const compReducer6$ = xs.of(
        function initReducer(prevState: State6b) {
            return {field_b: ""}
        }
    )
    const reducer6$ = xs.merge(
        parentReducer6$,
        compReducer6$
    )
    const sinks6: Sinks6 = {
        onion: reducer6$
    }
    return sinks6
}

type Sinks7 = { onion: xs<(s: any) => any | undefined> }
interface State7a { field_a: String }
interface State7b { field_b: String }
type Reducer7a = (prev: State7a) => State7a
type Reducer7b = (prev: State7b) => State7b

function main7(): Sinks7 {
    const parentReducer7$: xs<Reducer7a> = xs.of(
        function initReducer(prevState: State7a) {
            return {field_a: ""}
        }
    )
    const compReducer7$: xs<Reducer7b>  = xs.of(
        function initReducer(prevState: State7b) {
            return {field_b: ""}
        }
    )
    const reducer7$ = xs.merge(
        parentReducer7$,
        compReducer7$
    )
    const sinks7: Sinks7 = {
        onion: reducer7$
    }
    return sinks7
}

type Sinks8 = { onion: xs<(s: any) => any | undefined> }
interface State8a { field_a: String }
interface State8b { field_b: String }
type Reducer8a = (prev?: State8a) => State8a | undefined
type Reducer8b = (prev?: State8b) => State8b | undefined

function main8(): Sinks8 {
    const parentReducer8$: xs<Reducer8a> = xs.of(
        function initReducer(prevState: State8a) {
            return {field_a: ""}
        }
    )
    const compReducer8$: xs<Reducer8b>  = xs.of(
        function initReducer(prevState: State8b) {
            return {field_b: ""}
        }
    )
    const reducer8$ = xs.merge(
        parentReducer8$,
        compReducer8$
    )
    const sinks8: Sinks8 = {
        onion: reducer8$
    }
    return sinks8
}

type Sinks9 = { onion: xs<(s: any) => any | undefined> }
interface State9a { field_a: String }
interface State9b { field_b: String }
type Reducer9a = (prev?: State9a) => State9a | undefined
type Reducer9b = (prev?: State9b) => State9b | undefined
function model9a(): xs<Reducer9a> {
    return xs.of(
        function initReducer(prevState: State9a) {
            return { field_a: "" }
        }
    )
}
function model9b(): xs<Reducer9b> {
    return xs.of(
        function initReducer(prevState: State9b) {
            return { field_b: "" }
        }
    )
}

function main9(): Sinks9 {
    const parentReducer9$ = model9a()
    const compReducer9$ = model9b()
    const reducer9$ = xs.merge(
        parentReducer9$,
        compReducer9$
    )
    const sinks9: Sinks9 = {
        onion: reducer9$
    }
    return sinks9
}
