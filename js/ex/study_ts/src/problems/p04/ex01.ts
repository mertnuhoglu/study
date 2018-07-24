// @description: ## p04: Optional Property Specifically Required For Some Specific Variable
// opt: HTTP'yi optional hale getir
// export type Sinks = { DOM: Stream<VNode>; HTTP: Stream<RequestInfo>; }
// bu durumda HTTP eksik olursa, hata vermeyecek. onu nasıl çözeceğim?

export type T1 = {
    p1: string
    p2?: string
}
const v1: T1 = {p1: "ali"}
const v2: T1 = {p1: "ali", p2: "cin"}
const v3: T1 & {p2: string} = {p1: "adu", p2: "p2"}
const v4: T1 & {p2: string} = {p1: "adu"} // Error: Type '{ p1: string; }' is not assignable to type 'T1 & { p2: string; }'.\n  Type '{ p1: string; }' is not assignable to type '{ p2: string; }'.\n    Property 'p2' is missing in type '{ p1: string; }'.

type T2 = {
    p1: string
}
type T3 = T2 & {p2: string}

function f2a(a: T2): void {}
function f3a(a: T3): void {}
const v5: T2 = {p1: "p1"}
const v6: T3 = {p1: "p1", p2: "p2"}
f2a(v5)
f3a(v6)
f3a(v5) // Error: Property 'p2' is missing in type 'T2'.
f2a({p1: "p1", p2: "p2"}) // Error: 'p2' does not exist in type 'T2'.
f3a({p1: "p1", p2: "p2"})