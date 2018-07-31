
## ts: advanced types 01: Intersection Types

··  `` A {{c1::&}} B `` <br>
··  `` has {{c1::all}} members of A and B `` <br>


%

%

clozeq

---

## ts: advanced types 02: terms

··  `` {{c1::A & B}} -> intersection types `` <br>
··  `` {{c2::A | B}} -> union types `` <br>
··  `` :{{c3::arg is A}} -> type predicate `` <br>
··  `` {{c4::<A>x}}  -> type cast `` <br>
··  `` {{c5::x as A}} -> type assertion `` <br>
··  `` {{c6::isTypeA(x)}} -> type guard as function `` <br>
··  `` typeof x === 'number' -> type guard as typeof `` <br>

%

%

clozeq

---

## ts: advanced types 03: terms 02

··  `` {{c1::x instanceof A}} -> type guard as instanceof `` <br>
··  `` let x: {{c2::A | null}} -> nullable type `` <br>
··  `` f({{c3::x?:}} A)  -> optional parameter `` <br>
··  `` class A { {{c4::x?:}} B } -> optional property `` <br>
··  `` {{c5::type A = B}} -> type alias `` <br>
··  `` type A = {{c6::"a1" | "a2"}} -> string literal type `` <br>
··  `` type B = 1 | 2 -> numeric literal type `` <br>

%

%

clozeq

---

## ts: advanced types 04: terms 03

··  `` interface A {kind: ""; ...} ... B -> discriminated types `` <br>
··  `` fun assertNever(x: never): {{c1::never}} -> exhaustiveness checking `` <br>
··  `` class A { f(): {{c2::this}} } -> F-bounded polymorphism `` <br>
··  `` {{c3::keyof T}} -> index type query operator `` <br>
··  `` {{c4::T[K]}} -> indexed access operator `` <br>
··  `` let a: keyof A -> index type `` <br>
··  `` type Partial<T> = { {{c5::[P in keyof T]}}: T[P]} -> mapped type `` <br>
··  `` same properties in mapped type -> homomorphic  `` <br>
··  `` {{c6::a["key"]}} -> index signature `` <br>

%

%

clozeq

---

## ts: advanced types 02: terms 04

··  `` A & B -> {{c1::intersection}} types `` <br>
··  `` A | B -> {{c2::union}} types `` <br>
··  `` :arg is A -> type {{c3::predicate}} `` <br>
··  `` <A>x  -> type {{c4::cast}} `` <br>
··  `` x as A -> type {{c5::assertion}} `` <br>
··  `` isTypeA(x) -> type {{c6::guard}} as function `` <br>
··  `` typeof x === 'number' -> type guard as {{c7::typeof}} `` <br>

%

%

clozeq

---

## ts: advanced types 03: terms 05

··  `` x instanceof A -> type guard as instanceof `` <br>
··  `` let x: A | null -> {{c1::nullable}} type `` <br>
··  `` f(x?: A)  -> {{c2::optional}} parameter `` <br>
··  `` class A { x?: B } -> optional property `` <br>
··  `` type A = B -> type {{c3::alias}} `` <br>
··  `` type A = "a1" | "a2" -> string {{c4::literal}} type `` <br>
··  `` type B = 1 | 2 -> numeric literal type `` <br>

%

%

clozeq

---

## ts: advanced types 04: terms 06

··  `` interface A {kind: ""; ...} ... B -> {{c1::discriminated}} types `` <br>
··  `` fun assertNever(x: never): never -> {{c2::exhaustiveness}} checking `` <br>
··  `` class A { f(): this } -> {{c3::F-bounded}} polymorphism `` <br>
··  `` keyof T -> {{c4::index type}} query operator `` <br>
··  `` T[K] -> {{c5::indexed access}} operator `` <br>
··  `` let a: keyof A -> index type `` <br>
··  `` type Partial<T> = {[P in keyof T]: T[P]} -> {{c6::mapped}} type `` <br>
··  `` same properties in mapped type -> {{c7::homomorphic}}  `` <br>
··  `` a["key"] -> index {{c8::signature}} `` <br>

%

%

clozeq

---

## ts: five tips 01: strict configuration

··  `` "forceConsistentCasingInFileNames": true, `` <br>
··  `` "noImplicitReturns": true, `` <br>
··  `` {{c1::"strict"}}: true, `` <br>
··  `` "noUnusedLocals": true, `` <br>


%

%

clozeq

---

## ts: five tips 02: strict configuration

··  `` {{c1::noImplicitAny}} `` <br>

··  `` const fn = (foo) => foo.bar; `` <br>

foo is of type any implicitly

%

%

clozeq

---

## ts: five tips 03: strict configuration

never try to access a property of null

··  `` strictNullChecks `` <br>

··  `` interface Foo { `` <br>
··    `` bar: string; `` <br>
··  `` } `` <br>
··  `` const fn = ({{c1::foo?}}: Foo) => foo.bar; `` <br>

foo is optional. Thus it can be undefined. Thus ts will not compile.

%

%

clozeq

---

## ts: five tips 04: strict configuration

··  `` tslint `` <br>
··  `` {{c1::"no-any"}}: true `` <br>

won't allow explicit "any"

%

%

clozeq

---

## ts: five tips 05: type inference

avoid being {{c1::explicit}} 

check type by hovering with cursor

type guards leverage type inference

··  `` const isFish = (animal: Animal): {{c2::animal is Fish}} => (<Fish>animal).fin !== undefined `` <br>
··  `` if (isFish(myAnimal)) { `` <br>
··    `` console.log(myAnimal.fin); `` <br>
··  `` } else { `` <br>
··    `` console.log(myAnimal.legs.number); `` <br>
··  `` }; `` <br>

%

%

clozeq

---

## ts: five tips 06: undefined null

use {{c1::undefined}} instead of null

··  `` tslint `` <br>
··    `` {"no-null-keyword": true} `` <br>

%

%

clozeq

---

## ts: basarat's book: arrow functions

wrong object return

··  `` var foo = () => { `` <br>
··      `` bar: 123 `` <br>
··  `` }; `` <br>
··  `` -->> `` <br>
··  `` var foo = {{c1::() => ({  }} `` <br>
··      `` bar: 123 `` <br>
··  `` }); `` <br>

%

%

clozeq

---

## ts: basarat's book 02: Rest Parameters

··    `` function iTakeItAll(first, second, ...allOthers) { `` <br>
··        `` console.log(allOthers); `` <br>
··    `` } `` <br>
··    `` iTakeItAll('foo', 'bar'); // allOthers = [] `` <br>
··    `` iTakeItAll('foo', 'bar', 'bas', 'qux'); // allOthers = {{c1::['bas','qux']}} `` <br>

%

%

clozeq

---

## ts: basarat's book 03: let

··    `` `var` is function scope `` <br>
··    `` let/const are {{c1::block}} scope `` <br>

%

%

clozeq

---

## ts: basarat's book 04: tuple  type

··  `` var a: {{c1::[string, number]}} `` <br>
··  `` a = ['ali', 10] `` <br>


%

%

clozeq

---

## ts: basarat's book 05: inline type annotation

··  `` {{c1::var name:}} { `` <br>
··      `` first: string; `` <br>
··      `` second: string; `` <br>
··  `` }; `` <br>

%

%

clozeq

---

## ts: basarat's book 06: tuple type

··  `` var a: {{c1::[string, number]}} `` <br>
··  `` a = ['ali', 10] `` <br>

%

%

clozeq

---

## ts: basarat's book 07: callable 01

··  `` interface ReturnString { `` <br>
··    `` {{c1:: (): string}} `` <br>
··  `` } `` <br>


%

%

clozeq

---

## ts: basarat's book 08: structural typing

objects are structurally typed: ie: names don't matter

··  `` interface Point { `` <br>
··      `` x: number, `` <br>
··      `` y: number `` <br>
··  `` } `` <br>
··  `` class Point2D { `` <br>
··      `` constructor(public x:number, public y:number){} `` <br>
··  `` } `` <br>
··  `` let p: Point; `` <br>
··  `` // OK, because of structural typing `` <br>
··  `` p = new {{c1::Point2D}}(1,2); `` <br>


%

%

clozeq

---

## ts: marius schulz 01: non-nullable types 01

··  `` "compilerOptions": { `` <br>
··      `` "strictNullChecks": true `` <br>

··  `` let name: string; `` <br>
··  `` name = "Marius";   // OK `` <br>
··  `` name = {{c1::null}};       // Error `` <br>
··  `` name = undefined;  // Error `` <br>

%

%

clozeq

---

## ts: marius schulz 02: non-nullable types 02

··  `` function getLength(s: string | null) { `` <br>
··    `` return {{c1::s ? s.length : 0}}; `` <br>
··  `` } `` <br>

%

%

clozeq

---

## ts: marius schulz 03: type declaration files

··  `` npm install --save lodash `` <br>
··  `` --> `` <br>
··  `` npm install --save {{c1::@types/}}lodash `` <br>

%

%

clozeq

---

## ts: marius schulz 04: read only properties

··  `` type Point = { `` <br>
··      `` {{c1::readonly x}}: number; `` <br>
··      `` readonly y: number; `` <br>
··  `` }; `` <br>

%

%

clozeq

---

## ts: marius schulz 05: literal types

··  `` let zeroOrOne: {{c1::0 | 1}}; `` <br>
··  `` zeroOrOne = 0; `` <br>

%

%

clozeq

---

## ts: marius schulz 06: keyof and lookup types

··  `` function prop<T, K {{c1::extends keyof T}}>(obj: T, key: K) { `` <br>
··      `` return obj[key]; `` <br>
··  `` } `` <br>
··  `` const id = prop(todo, "id");      // number `` <br>
··  `` const text = prop(todo, "text");  // string `` <br>

%

%

clozeq

---

## ts: marius schulz 07: mapped types: readonly

··  `` type Readonly<T> = { `` <br>
··    `` readonly [{{c1::P in keyof T}}]: T[P]; `` <br>
··  `` }; `` <br>

%

%

clozeq

---

## ts: marius schulz 08: mapped types: pick

··  `` type Pick<T, K extends keyof T> = { `` <br>
··    `` {{c1::[P in K]}}: T[P]; `` <br>
··  `` }; `` <br>

%

%

clozeq

---

## ts: marius schulz 09: mapped types: record

··  `` type Record<{{c1::K extends string}}, T> = { `` <br>
··    `` {{c2::[P in K]}}: T; `` <br>
··  `` }; `` <br>

%

%

clozeq

---

## ts: marius schulz 10: mapped types: partial

··  `` type Partial<T> = { `` <br>
··    `` {{c1::[P in keyof T]?}}: T[P]; `` <br>
··  `` }; `` <br>



%

%

clozeq

---

## ts: marius schulz 11: mapped types: nullable

··  `` type Nullable<T> = { `` <br>
··    `` [P in keyof T]: {{c1::T[P] | null;}} `` <br>
··  `` }; `` <br>

%

%

clozeq

---

## ts: marius schulz 12: mapped types: stringify

··  `` type Stringify<T> = { `` <br>
··    `` [P in keyof T]: {{c1::string}}; `` <br>
··  `` }; `` <br>


%

%

clozeq

---

## ts: marius schulz 13: generic parameter defaults

··  `` class Component<Props {{c1::= any}}, State = any> { `` <br>
··      `` props: Props; `` <br>
··      `` state: State; `` <br>
··  `` } `` <br>


%

%

clozeq

---

## ts: marius schulz 14: string enums

··  `` enum MediaTypes { `` <br>
··      `` JSON = {{c1::"application/json"}}, `` <br>
··      `` XML = ... `` <br>
··  `` } `` <br>

%

%

clozeq

---

## ts: marius schulz 15: weak type detection

A type is weak if all of its properties are {{c1::optional}}.

··  `` interface PrettierConfig { `` <br>
··      `` printWidth?: number; `` <br>
··      `` tabWidth?: number; `` <br>
··      `` semi?: boolean; `` <br>
··  `` } `` <br>


%

%

clozeq

---

## ts: marius schulz 16: explicit type annotation

How to add a new property to a weakly typed object:

Workaround: to add an {{c1::index signature}}:

··  `` interface PrettierConfig { `` <br>
··      `` {{c2::[prop: string]}}: any; `` <br>
··      `` printWidth?: number; `` <br>
··      `` tabWidth?: number; `` <br>
··      `` semi?: boolean; `` <br>
··  `` } `` <br>

%

%

clozeq

---

## ts: generics 01: generic constraints

constraint: types that have `length` property

··  `` interface Lengthwise { `` <br>
··      `` length: number; `` <br>
··  `` } `` <br>

··  `` function loggingIdentity<{{c1::T extends Lengthwise}}>(arg: T): T { `` <br>
··      `` console.log(arg.length) `` <br>
··      `` return arg; `` <br>
··  `` } `` <br>


%

%

clozeq

---

## ts: record 01

how to solve this error:

··  `` type PrefName = "PET_NAME" | "SEATING_PREFERENCE" | "AGE"; `` <br>

··  `` type UserPref = { `` <br>
··    `` [pref: PrefName]: any; `` <br>
··    `` // Wrong! ^ key must string or number `` <br>
··  `` } `` <br>

--&gt;&gt;

··  `` type UserPref = {{c1::Partial<Record}}<PrefName, any>> `` <br>
··  `` let prefs: UserPref = { `` <br>
··      `` "PET_NAME": "fido" `` <br>
··  `` } `` <br>

%

%

clozeq

---

## ts: index type (index signature) 01

··  `` interface Dict { `` <br>
··    `` {{c1::[index: string]}}: string `` <br>
··  `` } `` <br>

··  `` let foo:{[index: string] : {message: string}} = {} `` <br>
··  `` foo['a'] = {message: 'some'} `` <br>

note: name of index key eg. `index` in "{[index: string] : {message: string}}" has {{c2::no significance}}

%

%

clozeq

---

## ts: index type (index signature) 02

all explicit members must conform to it

··  `` /** Okay */ `` <br>
··  `` interface Foo { `` <br>
··    `` [key:string]: number `` <br>
··    `` y: number; `` <br>
··  `` } `` <br>

··  `` /** Error */ `` <br>
··  `` interface Bar { `` <br>
··    `` [key:string]: number `` <br>
··    `` y: {{c1::string}}; // ERROR: Property `y` must be of type number `` <br>
··  `` } `` <br>

%

%

clozeq

---

## ts: index type (index signature) 03

index strings can be members of a union of literal strings by using Mapped Types

··  `` type Index = 'a' | 'b' | 'c' `` <br>
··  `` type FromIndex = { [{{c1::k in Index}}]?: number } `` <br>
··  `` const good: FromIndex = {b:1, c:2} `` <br>
··  `` // Error: `` <br>
··  `` const bad: FromIndex = {b:1, c:2, d:3}; `` <br>

%

%

clozeq

---

## ts: intersection type 01

··  `` function extend <A, B> (a: A, b: B): A & B { `` <br>
··    `` Object.keys(b).forEach(key => { `` <br>
··      `` {{c1::a[key]}} = b[key] `` <br>
··    `` }) `` <br>
··    `` return a {{c2::as A & B}} `` <br>
··  `` } `` <br>

%

%

clozeq

---

## ts: anki conditional types 01

··  `` T extends U ? {{c1::X : Y}} `` <br>

%

%

clozeq

---

## ts: anki conditional types 02

··  `` type TypeName<T> = `` <br>
··      `` T extends string ? "string" : `` <br>
··      `` T extends number ? "number" : `` <br>
··      `` T extends boolean ? "boolean" : `` <br>
··      `` T extends undefined ? "undefined" : `` <br>
··      `` T extends Function ? "function" : `` <br>
··      `` "object"; `` <br>

··  `` type T1 = TypeName<{{c1::"a"}}>;  // "string" `` <br>
··  `` type T3 = TypeName<{{c2::() => void}}>;  // "function" `` <br>

%

%

clozeq

---

## ts: anki conditional types 03 distributive

··  `` type T10 = TypeName<string | (() => void)>;  // {{c1::"string" | "function"}} `` <br>
··  `` type T12 = TypeName<string | string[] | undefined>;  // "string" | "object" | "undefined" `` <br>

%

%

clozeq

---

## ts: anki conditional types 04 predefined conditional types

··  `` {{c1::Exclude}}<T, U> `` <br>
··  `` {{c2::Extract}}<T, U> `` <br>
··  `` {{c3::NonNullable}}<T> `` <br>
··  `` {{c4::ReturnType}}<T> `` <br>
··  `` {{c5::InstanceType}}<T> `` <br>

%

%

clozeq

---

## ts: anki conditional types 05 predefined conditional types 02

··  `` type T00 = Exclude<"a" | "b" | "c" | "d", "a" | "c" | "f">;  // {{c1::"b" | "d"}} `` <br>
··  `` type T01 = Extract<"a" | "b" | "c" | "d", "a" | "c" | "f">;  // "a" | "c" `` <br>
··  `` type T03 = Extract<string | number | (() => void), Function>;  // {{c2::() => void}} `` <br>


%

%

clozeq

---

## ts: anki conditional types 06 predefined conditional types 03

··  `` type T04 = NonNullable<string | number | undefined>;  // string | number `` <br>
··  `` type T05 = NonNullable<(() => string) | string[] | null | undefined>;  // {{c1::(() => string) | string[]}} `` <br>


%

%

clozeq

---

## ts: anki conditional types 07 predefined conditional types 04

··  `` type T10 = ReturnType<() => string>;  // {{c1::string}} `` <br>
··  `` type T11 = ReturnType<(s: string) => void>;  // {{c2::void}} `` <br>
··  `` type T12 = ReturnType<(<T>() => T)>;  // {{c3::{}}} `` <br>

%

%

clozeq

---

## ts: anki conditional types 08 predefined conditional types 05

··  `` type T20 = InstanceType<typeof C>;  // {{c1::C}} `` <br>
··  `` type T21 = InstanceType<any>;  // any `` <br>
··  `` type T23 = InstanceType<string>;  // {{c2::Error}} `` <br>

%

%

clozeq

---

## ts: anki conditional types 09

Implementation of `Omit`

··  `` type Omit<T, {{c1::K extends keyof T}}> = {{c2::Pick}}<T, Exclude<keyof T, K>>  `` <br>

%

%

clozeq

---

## ts: anki conditional types 10 required

Implementation of Required

··  `` type Required<T> = { [P in keyof T]{{c1::-?}}: T[P] }; `` <br>

%

%

clozeq

---

## ts: anki conditional types 11 keyof with intersection types

··  `` type A = { a: string }; `` <br>
··  `` type B = { b: string }; `` <br>

··  `` type T1 = keyof (A & B);  // {{c1::"a" | "b"}} `` <br>
··  `` type T2<T> = keyof (T & B);  // {{c2::keyof T | "b"}} `` <br>
··  `` type T3<U> = keyof (A & U);  // "a" | keyof U `` <br>
··  `` type T4<T, U> = keyof (T & U);  // keyof T | keyof U `` <br>
··  `` type T5 = T2<A>;  // "a" | "b" `` <br>
··  `` type T6 = T3<B>;  // {{c3::"a" | "b"}} `` <br>
··  `` type T7 = T4<A, B>;  // "a" | "b" `` <br>


%

%

clozeq

---

## ts: distributive conditional types

Distributive conditional types are automatically distributed {{c1::over union types}} during instantiation. 

ex:

····`` `(A|B|C) extends U ? X : Y` `` <br>
····`` => `` <br>
····`` `(A extends U ? X : Y) | (B extends U ? X : Y) | (C extends U ? X : Y)` `` <br>

%

%

clozeq

---

## ts: index access inside index signature

··  `` type Nullable<T> = { `` <br>
··    `` [{{c1::P in keyof T}}]: T[P] | null; `` <br>
··  `` }; `` <br>

··  `` type Record<K extends string, T> = { `` <br>
··    `` {{c2::[P in K]}}: T; `` <br>
··  `` }; `` <br>

··  `` type Readonly<T> = { `` <br>
··    `` readonly [{{c3::P in keyof T}}]: T[P]; `` <br>
··  `` }; `` <br>

%

%

clozeq

---

