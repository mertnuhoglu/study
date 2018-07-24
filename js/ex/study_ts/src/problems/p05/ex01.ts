// @description: ## p05: Meaning of `extends`: subset or superset?

export type A = {a: string} 
export type B = {a: string, b: string}

type AB = A extends B ? never : string // string
type BA = B extends A ? never : string // never
// B extends A
// A doesn't extend B
