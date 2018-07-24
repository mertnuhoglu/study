// @description: ## p03: Type Inference Issues

type T1 = { p1: string }
export function f1(): T1 {
    const p1: "ali" = "ali"
    return {
        p1
    }
}
const v1 = f1() // type: string
const v2: "ali" = "ali" // type: "ali"

// js runtime doesn't keep type information
console.log(typeof(v1.p1) === "string" ? "string" : "not string") // "string"
console.log(typeof(v2) === "string" ? "string" : "not string") // "string"