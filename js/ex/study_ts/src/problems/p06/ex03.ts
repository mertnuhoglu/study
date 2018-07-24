// @description: In which conditions do we have `isa` relationship when using unions and intersections?

export type A = {a: number}
type B = {b: number}
type AandB = A & B
type AorB = A | B
type AandB_is_A = AandB extends A ? true : false // true
type A_isnt_AandB = A extends AandB ? true : false // false
type AorB_isnt_A = AorB extends A ? true : false // false
type A_is_AorB = A extends AorB ? true : false // true