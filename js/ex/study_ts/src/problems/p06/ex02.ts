// @description: p06: isa relationship wrt `and` `or` operators

export type A = {a: number}
type B = {b: number}
type C = A & B
type D = A | B
type C_is_A = C extends A ? true : false // true
type C_is_B = C extends B ? true : false // true
type D_is_not_A = D extends A ? true : false // false
type D_is_not_B = D extends B ? true : false // false
const c1: C = {a: 0, b: 1}
const a1: A = c1 as A
a1.a
a1.b // Error: Property 'b' does not exist on type 'A'.
const d1: D = {a: 0}
d1.b // Error: Property 'b' does not exist on type 'A'.
d1.a 
const d2: D = {b: 1}
d2.b
const d3: D = {a: 0, b: 1}
d3.a // Error: Property 'a' does not exist on type 'D'. Property 'a' does not exist on type 'B'.
d3.b // Error: Property 'b' does not exist on type 'D'. Property 'b' does not exist on type 'A'.
const d4: D = {c: 1} // Error: Type '{ c: number; }' is not assignable to type 'D'. Object literal may only specify known properties, and 'c' does not exist in type 'D'.
const d5 = d3 as A
d5.a
d5.b // Error: Property 'b' does not exist on type 'A'.
const d6 = d3 as A & B
d6.a
d6.b