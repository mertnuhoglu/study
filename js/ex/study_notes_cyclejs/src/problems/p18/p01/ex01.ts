// @description: nasıl generikleşmeyi test edebilirim?

function fun01<T>(a: T): T {
    return a
}
const g: "help" = fun01("help")

type MainFn<T> = (arg: T) => T
function fun02<T>(arg01: T): MainFn<T> {
    return (arg: T) => arg
}
const g02: MainFn<string> = fun02("help")
const g03: string = g02("ali")

function fun03<T>(fun: MainFn<T>): MainFn<T> {
    return function mainFun(arg: T): T {
        return arg
    }
}
const g04: MainFn<string> = fun03(g02)
const g05: string = g04("b")