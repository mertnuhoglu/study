// @description: conditional type'larla MainFn ile App arasındaki ilişkiyi test edebilirim
type TypeName<T> =
    T extends string ? "string" :
    T extends number ? "number" :
    T extends boolean ? "boolean" :
    T extends undefined ? "undefined" :
    T extends Function ? "function" :
    "object";

type T0 = TypeName<string>;  // "string"
type T1 = TypeName<"a">;  // "string"
type T2 = TypeName<true>;  // "boolean"
type T3 = TypeName<() => void>;  // "function"
type T4 = TypeName<string[]>;  // "object"

const v0: T0 = "string"

type T10 = TypeName<string | (() => void)>;  // "string" | "function"
type T12 = TypeName<string | string[] | undefined>;  // "string" | "object" | "undefined"
type T11 = TypeName<string[] | number[]>;  // "object"

const v10a: T10 = "string"
const v10b: T10 = "function"

type T13 = string extends number ? never : string

function fun01(a: string): string { return a}
type T14 = InstanceType<typeof fun01> // Error: Type '{ <T>(a: T): T; (a: string): string; }' does not satisfy the constraint 'new (...args: any[]) => any'.
type T16 = ReturnType<typeof fun01> // string
type T15 = InstanceType<"ali"> // Error: Type '"ali"' does not satisfy the constraint 'new (...args: any[]) => any'
const v17a: string = "ali"
type T17 = InstanceType<v17a> // Error: Cannot find name 'v17a'

class C1 { x = 0 }
interface I1 { x: string }
type T18 = InstanceType<typeof C1>
type T19 = ReturnType<(<T>() => T)>;  // {}
type T20 = ReturnType<(<T extends U, U extends number[]>() => T)>;  // number[]
type T21 = ReturnType<typeof fun01>;  // string

// @essence
import { comp_a, MainFn, Sources, Sinks, onionify, State, model_a } from './ex02'
type T22 = typeof comp_a extends MainFn<Sources, Sinks> ? never : string // never
type T23 = typeof comp_a extends MainFn<Pick<Sources, never>, Sinks> ? never : string // never
const main01 = onionify(comp_a) // MainFn<Pick<Sources, never>, Sinks>

import xs from 'xstream'
import Stream from 'xstream';
import { StateSource } from '../cycle-onionify/StateSource';
type Sources02 = { onion: StateSource<State>, DOM: {} }
type Sinks02 = { onion: xs<(s: any) => any | undefined>, DOM: {} }
function comp_b(sources: Sources02): Sinks02 { 
    return { 
        onion: model_a(),
        DOM: {},
    }
}
const main02 = onionify(comp_b) // MainFn<Pick<Sources02, "DOM">, Sinks02>

const v24 = {onion: 1} as Pick<Sources, never>
const v25 = v24.onion // Error: Property 'onion' does not exist on type 'Pick<Sources, never>'.
const v26 = {a: 1} as Pick<Sources, never>
const v27 = v26.a // Error: Property 'a' does not exist on type 'Pick<Sources, never>'.
