
## rxjs: egghead 01 what is it?

programming with {{c1::event streams}}

event stream: sequence of events happening over time (async)

when an event happens, we {{c2::react}} to it

%

%

clozeq

---

## rxjs: manual 01 Core Concepts

- Observable: {{c1::invokable}} collection of {{c2::future}} values (events)
- Observer: collection of {{c3::callbacks}} that listen to values coming from Observable
- Subscription: {{c4::execution}} of Observable
- Operators: functions like `map, filter, concat`
- Subject: similar to EventEmitter. It multicasts a value to {{c5::multiple}} Observers
- Schedulers: control concurrency


%

%

clozeq

---

## rxjs: manual 02 Before RxJs 

we had event listeners:

··  `` var button = document.querySelector('button'); `` <br>
··  `` button.{{c1::addEventListener}}('click', () => console.log('Clicked!')); `` <br>

Now we use Observables:

··  `` var button = document.querySelector('button'); `` <br>
··  `` Rx.Observable.{{c2::fromEvent}}(button, 'click') `` <br>
····  `` .subscribe(() => console.log('Clicked!')); `` <br>

%

%

clozeq

---

## rxjs: manual 03 Purity

Before:

··  `` var count = 0; `` <br>
··  `` var button = document.querySelector('button'); `` <br>
··  `` button.addEventListener('click', () => console.log(`Clicked ${++count} times`)); `` <br>

With RxJs, you isolate the state (`count`):

··  `` var button = document.querySelector('button'); `` <br>
··  `` Rx.Observable.fromEvent(button, 'click') `` <br>
····  `` .scan({{c1::count => count + 1}}, 0) `` <br>
····  `` .subscribe(count => console.log(`Clicked ${count} times`)); `` <br>

%

%

clozeq

---

## rxjs: manual 04 Flow

Operators control how the events flow through observables: `throttleTime, filter, delay, debounceTime, take, takeUntil, distinct, distinctUntilChanged` etc.

Ex: Limiting one click per second:

··  `` var button = document.querySelector('button'); `` <br>
··  `` Rx.Observable.fromEvent(button, 'click') `` <br>
····  `` .{{c1::throttleTime}}(1000) `` <br>
····  `` .scan(count => count + 1, 0) `` <br>
····  `` .subscribe(count => console.log(`Clicked ${count} times`)); `` <br>


%

%

clozeq

---

## rxjs: manual 04 Transform Values

Ex: Add current mouse x position for every click:

··  `` var button = document.querySelector('button'); `` <br>
··  `` Rx.Observable.fromEvent(button, 'click') `` <br>
····  `` .throttleTime(1000) `` <br>
····  `` .map(event => event.clientX) `` <br>
····  `` .{{c1::scan}}({{c2::(count, clientX)}} => count + clientX, 0) `` <br>
····  `` .subscribe(count => console.log(count)); `` <br>

Value producing operators: `map, pluck, pairwise, sample` etc.

%

%

clozeq

---

## rxjs: manual 05 Observable

Observables are lazy push collections of multiple values

··  `` | .··  | Single   | Multiple   | `` <br>
··  `` | Pull | {{c1::Function}} | Iterator   | `` <br>
··  `` | Push | {{c2::Promise}}  | {{c3::Observable}} | `` <br>

%

%

clozeq

---

## rxjs: manual 06 

Ex: Push values 1,2 immediately (synchronously) when subscribed, and 3 after one second:

··  `` var observable = Rx.Observable.create(function (observer) { `` <br>
····  `` observer.next(1) `` <br>
····  `` observer.next(2) `` <br>
····  `` {{c1::setTimeout}}(() => { `` <br>
······  `` observer.{{c2::next(3)}} `` <br>
······  `` observer.complete() `` <br>
····  `` }, 1000) `` <br>
··  `` }) `` <br>


%

%

clozeq

---

## rxjs: manual 07 What is Pull?

The Consumer determines when it {{c1::receives}} data.

Every {{c1::function}} is a Pull system. The function is a Producer. The client code pulls the single return value.


%

%

clozeq

---

## rxjs: manual 08


··  `` | .··  | Producer | Consumer | `` <br>
··  `` | Pull | {{c1::Passive}}  | Active   | `` <br>
··  `` | Push | Active   | {{c2::Passive}}  | `` <br>


%

%

clozeq

---

## rxjs: manual 09 Observables as generalizations of functions

Observables are like functions with zero arguments.

··  `` function foo() { `` <br>
····  `` return 42; `` <br>
··  `` } `` <br>
··  `` var x = foo.{{c1::call}}();  `` <br>
··  `` console.log(x); `` <br>

··  `` var foo = Rx.Observable.create(function (observer) { `` <br>
····  `` observer.next(42); `` <br>
··  `` }); `` <br>
··  `` foo.{{c2::subscribe}}(function (x) { `` <br>
····  `` console.log(x); `` <br>
··  `` }); `` <br>


%

%

clozeq

---

## rxjs: manual 10 Difference between an Observable and a function:

Observables can return {{c1::multiple}} values over time. Functions cannot.

You can't do this:

··  `` function foo() { `` <br>
····  `` console.log('Hello'); `` <br>
····  `` {{c2::return}} 42; `` <br>
····  `` return 100; `` <br>
··  `` } `` <br>


%

%

clozeq

---

## rxjs: manual 11 Core Observable functionalities:

- {{c1::Creating}} Observables: ex: `create()`
- {{c2::Subscribing}} to Observables: `subscribe()`
- {{c3::Executing}} the Observable: ex: {{c4::`next()`}}
- Disposing Observables: {{c5::`unsubscribe()`}}

%

%

clozeq

---

## rxjs: manual 12 Creating Observables

··  `` var observable = Rx.Observable.{{c1::create}}(function subscribe(observer) { `` <br>
····  `` var id = setInterval(() => { `` <br>
······  `` observer.next('hi') `` <br>
····  `` }, 1000); `` <br>
··  `` }); `` <br>

Creation operators: `of, from, interval` etc.


%

%

clozeq

---

## rxjs: manual 13 Executing Observables

Observable execution = the code inside `Observable.{{c1::create}}(function subscribe(observer) {...})`

It is a {{c2::lazy}} computation. 

It happens only when each Observer {{c3::subscribes}}.

The execution produces {{c4::multiple}} values over time.

%

%

clozeq

---

## rxjs: manual 14 executing observables

There are three types of values:

- {{c1::`next`}} notification
- `error` notification
- `complete` notification

Observable Grammar (Contract):

··  `` {{c2::next*}}(error|complete)? `` <br>


%

%

clozeq

---

## rxjs: manual 15 Observer

What is an Observer?

A {{c1::consumer}} of values pushed by an Observable.

An Observer is a set of {{c2::callbacks}} for `next`, `error` and `complete`.

··  `` var {{c3::observer}} = { `` <br>
····  `` next: x => console.log(`next ${x}`), `` <br>
····  `` error: err => console.log(`error ${err}`), `` <br>
····  `` complete: () => console.log('completed') `` <br>
··  `` } `` <br>
··  `` observable.subscribe({{c4::observer}}) `` <br>

Observers can be {{c5::partial}} (without `error` or `complete` callback).


%

%

clozeq

---

## rxjs: manual 16 Subscription

What is a Subscription?

An object that represents a disposable resource, the {{c1::execution}} of an Observable.

%

%

clozeq

---

## rxjs: manual 17 Subject

What is a Subject?

A special type of Observable to {{c1::multicast}} to many Observers.

Plain Observables are unicast ie. each Observer owns an {{c2::independent}} execution of the Observable.

A Subject is an Observable, but can multicast to many Observers. 

They store a {{c3::list}} of many observers.

`subscribe` does not {{c4::invoke}} a new execution. It simply registers the observer.

%

%

clozeq

---

## rxjs: manual 18 Subject 02

Every Subject is {{c1::both}} an Observable and an Observer. 

It has methods {{c2::`next(v)`}}, `error(e)`, and `complete()`

Push a new value to the Subject with `next(v)`. It will be {{c3::multicasted}} to the Observers registered to listen to the Subject.

%

%

clozeq

---

## rxjs: manual 19 Subject 03

··  `` var subject = new Rx.{{c1::Subject}}(); `` <br>
··  `` subject.{{c2::subscribe}}({ `` <br>
····  `` {{c3::next}}: (v) => console.log('observerA: ' + v) `` <br>
··  `` }); `` <br>
··  `` subject.subscribe({ `` <br>
····  `` next: (v) => console.log('observerB: ' + v) `` <br>
··  `` }); `` <br>
··  `` subject.next(1); `` <br>
··  `` subject.next(2); `` <br>
··  `` // observerA: 1 `` <br>
··  `` // observerB: 1 `` <br>
··  `` // observerA: 2 `` <br>
··  `` // observerB: 2 `` <br>


%

%

clozeq

---

## rxjs: manual 20 Subject 04

A Subject can {{c1::`subscribe`}} to an Observable too, because it is an Observer too.

··  `` var subject = new Rx.Subject(); `` <br>
··  `` subject.subscribe({ `` <br>
····  `` next: (v) => console.log('observerA: ' + v) `` <br>
··  `` }); `` <br>
··  `` subject.subscribe({ `` <br>
····  `` next: (v) => console.log('observerB: ' + v) `` <br>
··  `` }); `` <br>
··  `` var observable = Rx.Observable.from([1, 2]); `` <br>
··  `` observable.subscribe({{c2::subject}});  `` <br>
··  `` // observerA: 1 `` <br>
··  `` // observerB: 1 `` <br>
··  `` // observerA: 2 `` <br>
··  `` // observerB: 2 `` <br>


%

%

clozeq

---

## rxjs: manual 21 Multicasted Observables

Observers subscribe to a {{c1::Subject}}. And the Subject subscribes to the {{c2::source Observable}}.

%

%

clozeq

---

## rxjs: manual 22 Operators

What are operators?

They are methods of Observable such as `map, filter, merge`

They {{c1::don't change}} the existing Observable. Instead, they return a new Observable.

An Operator is essentially {{c2::a pure function}}. It takes one Observable as input and returns another Observable as output.

%

%

clozeq

---

## rxjs: manual 23 Operators 02

The following function maps a value of the input Observable to its multiplication by 10.

··  `` function multiplyByTen(input) { `` <br>
····  `` var output = Rx.Observable.{{c1::create}}(function subscribe(observer) { `` <br>
······  `` {{c2::input}}.subscribe({ `` <br>
········  `` next: (v) => observer.next(10 * v), `` <br>
······  `` }); `` <br>
····  `` }); `` <br>
····  `` return output; `` <br>
··  `` } `` <br>
··  `` var input = Rx.Observable.from([1, 2, 3, 4]); `` <br>
··  `` var output = multiplyByTen({{c3::input}}); `` <br>
··  `` output.subscribe(x => console.log(x)); `` <br>

This corresponds to the following `map` call:

··  `` var input = Rx.Observable.from([1, 2, 3, 4]); `` <br>
··  `` var output = input.map(x => {{c4::10 * x}}); `` <br>
··  `` output.subscribe(x => console.log(x)); `` <br>

%

%

clozeq

---

## rxjs: manual 24 static operators

Examples of static operators: 

1. {{c1::Creation}} operators: `from, interval` etc.

······  `` var observable = Rx.Observable.interval(1000); `` <br>

2. {{c2::Combination}} operators that take multiple Observables as input: `merge, combineLatest, concat` etc.

······  `` var observable1 = Rx.Observable.interval(1000); `` <br>
······  `` var observable2 = Rx.Observable.interval(400); `` <br>
······  `` var merged = Rx.Observable.{{c3::merge}}(observable1, observable2); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 01 combineLatest

When any observable emits a value, emit the {{c1::latest}} value 

··  `` combineLatest(observables: {{c2::...Observable}}, project: function): Observable `` <br>

··  `` const t1 = hot(  'a----b-----c---|'); `` <br>
··  `` const t2 = hot(  '-----e------f--------|'); `` <br>
··  `` const t3 = hot(  '-------------h-----i---------|') `` <br>
··  `` const expected = '-------------y-----z---------|'; `` <br>
··  `` var combined = Observable.combineLatest(t1, t2, t3, (a,b,c) => a + b + c); `` <br>


%

%

clozeq

---

## rxjs: learnrxjs 02 concat

··  `` concat(observables: ...*): Observable `` <br>

Subscribe to observables in order. When one previous {{c1::completes}}, emit values of it.

Tests:

··  `` const sourceOne = Observable.of(1,2,3); `` <br>
··  `` const sourceTwo = Observable.of(4,5,6); `` <br>
··  `` const example = {{c1::sourceOne}}.concat(sourceTwo); `` <br>
··  `` const values = {a: 1, b: 2, c: 3, d: 4, e: 5, f: 6}; `` <br>
··  `` const expected = '(abcdef|)'; `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 03 merge

··  `` merge(input: Observable): Observable `` <br>

Turn multiple observables into a {{c1::single}} observable.

··  `` const first = {{c2::interval}}(2500); `` <br>
··  `` const second = interval(2000); `` <br>
··  `` const example = merge( `` <br>
····  `` first.pipe({{c3::mapTo}}('FIRST!')), `` <br>
····  `` second.{{c4::pipe}}(mapTo('SECOND!')), `` <br>
··  `` ); `` <br>
··  `` //output: "SECOND!", "FIRST!",  `` <br>
··  `` const subscribe = example.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 04 startWith

··  `` startWith(an: Values): Observable `` <br>

Emit given value first

Tests:

··  `` const source = Observable.of(1,2,3); `` <br>
··  `` const example =  source.{{c1::startWith}}(0); `` <br>
··  `` const values = {a: 0, b: 1, c: 2, d: 3}; `` <br>
··  `` const expected = '(abcd|)'; `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 05 withLatestFrom

··  `` withLatestFrom(other: Observable, project: Function): Observable `` <br>

When event is received, also provide the last value from another observable

··  `` const source = interval(5000); `` <br>
··  `` const secondSource = interval(1000); `` <br>
··  `` const example = source.pipe( `` <br>
····  `` {{c1::withLatestFrom}}({{c1::secondSource}}), `` <br>
····  `` {{c1::map}}(([first, second]) => { `` <br>
······  `` return `First Source (5s): ${first} Second Source (1s): ${second}`; `` <br>
····  `` }) `` <br>
··  `` ); `` <br>
··  `` /* `` <br>
····  `` "First Source (5s): 0 Second Source (1s): 4" `` <br>
····  `` "First Source (5s): 1 Second Source (1s): 9" `` <br>
····  `` "First Source (5s): 2 Second Source (1s): 14" `` <br>
····  `` ... `` <br>
··  `` */ `` <br>
··  `` const subscribe = example.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 06 from

··  `` from(ish: ObservableInput, mapFn, function, thisArg: any, scheduler: Scheduler): Observable `` <br>

Turn an {{c1::array}}, {{c2::promise}}, or iterable into an observable

%

%

clozeq

---

## rxjs: learnrxjs 07 from 02

Ex: Observable from array 

··  `` import { from } from 'rxjs/observable/from'; `` <br>
··  `` const arraySource = from({{c1::[1, 2, 3, 4, 5]}}); `` <br>
··  `` //output: 1,2,3,4,5 `` <br>
··  `` const subscribe = arraySource.subscribe(val => console.log(val)); `` <br>


%

%

clozeq

---

## rxjs: learnrxjs 08 from 03

Ex: Observable from Promise

··  `` import { from } from 'rxjs/observable/from'; `` <br>

··  `` const promiseSource = from({{c1::new Promise}}({{c2::resolve}} => resolve('Hello World!'))); `` <br>
··  `` //output: 'Hello World' `` <br>
··  `` const subscribe = promiseSource.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 09 from 04

Ex: Observable from collection

··  `` import { from } from 'rxjs/observable/from'; `` <br>
··  `` const map = {{c1::new Map()}}; `` <br>
··  `` map.set(1, 'Hi'); `` <br>
··  `` map.set(2, 'Bye'); `` <br>
··  `` const mapSource = from({{c2::map}}); `` <br>
··  `` //output: [1, 'Hi'], [2, 'Bye'] `` <br>
··  `` const subscribe = mapSource.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 10 of 01

··  `` of({{c1::...values}}, scheduler: Scheduler): Observable `` <br>

Emit variable amount of values in a sequence


%

%

clozeq

---

## rxjs: learnrxjs 11 of 02

Ex: Emit a sequence of numbers

··  `` const source = Rx.Observable.of({{c1::1, 2, 3, 4, 5}}); `` <br>
··  `` //output: 1,2,3,4,5 `` <br>
··  `` const subscribe = source.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 12 of 03

Ex: Emit an object, array, and function

··  `` import { of } from 'rxjs/observable/of'; `` <br>
··  `` const source = of({ name: 'Brian' }, [1, 2, 3], {{c1::function}} hello() { `` <br>
····  `` return 'Hello'; `` <br>
··  `` }); `` <br>
··  `` //output: {name: 'Brian}, [1,2,3], function hello() { return 'Hello' } `` <br>
··  `` const subscribe = source.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 13 interval

··  `` interval(period: number, scheduler: Scheduler): Observable `` <br>

Emit {{c1::numbers in sequence}} based on provided timeframe

%

%

clozeq

---

## rxjs: learnrxjs 14 interval

Ex: Emit sequence of values at 1 second interval

··  `` import { interval } from 'rxjs/observable/interval'; `` <br>
··  `` const source = {{c1::interval(1000)}}; `` <br>
··  `` //output: 0,1,2,3,4,5.... `` <br>
··  `` const subscribe = source.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 15

··  `` create(subscribe: function) `` <br>

Create an observable with given {{c1::subscription function}}

%

%

clozeq

---

## rxjs: learnrxjs 16 

Ex: Observable that emits multiple values

··  `` import { Observable } from 'rxjs/Observable'; `` <br>
··  `` const hello = Observable.{{c1::create}}({{c2::function}}(observer) { `` <br>
····  `` observer.next('Hello'); `` <br>
····  `` observer.next('World'); `` <br>
··  `` }); `` <br>
··  `` //output: 'Hello'...'World' `` <br>
··  `` const subscribe = hello.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 17

Ex: Emit even numbers on timer

··  `` import { Observable } from 'rxjs/Observable'; `` <br>
··  `` const evenNumbers = Observable.create(function(observer) { `` <br>
····  `` let value = 0; `` <br>
····  `` const interval = {{c1::setInterval}}(() => { `` <br>
······  `` if (value % 2 === 0) { `` <br>
········  `` {{c2::observer.next(value)}}; `` <br>
······  `` } `` <br>
······  `` value++; `` <br>
····  `` }, 1000); `` <br>
····  `` return () => clearInterval(interval); `` <br>
··  `` }); `` <br>
··  `` //output: 0...2...4...6...8 `` <br>
··  `` const subscribe = evenNumbers.subscribe(val => console.log(val)); `` <br>
··  `` //unsubscribe after 10 seconds `` <br>
··  `` {{c3::setTimeout}}(() => { `` <br>
····  `` subscribe.unsubscribe(); `` <br>
··  `` }, 10000); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 18

··  `` empty(scheduler: Scheduler): Observable `` <br>

Observable that {{c1::immediately completes}}

%

%

clozeq

---

## rxjs: learnrxjs 19

··  `` //Create observable that immediately completes `` <br>
··  `` const example = {{c1::empty()}}; `` <br>
··  `` //output: 'Complete!' `` <br>
··  `` const subscribe = example.subscribe({ `` <br>
····  `` next: () => console.log('Next'), `` <br>
····  `` complete: () => console.log('Complete!') `` <br>
··  `` }); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 20

··  `` range(start: number, count: number, scheduler: Scheduler): Observable `` <br>

Emit numbers in provided range in sequence

··  `` import { range } from 'rxjs/observable/range'; `` <br>
··  `` const source = {{c1::range(1, 10)}}; `` <br>
··  `` //output: 1,2,3,4,5,6,7,8,9,10 `` <br>
··  `` const example = source.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 21

··  `` {{c1::fromEvent}}(target: EventTargetLike, eventName: string, selector: function): Observable `` <br>

Turn event into observable sequence

%

%

clozeq

---

## rxjs: learnrxjs 22

Ex: Observable from mouse clicks

··  `` import { fromEvent } from 'rxjs/observable/fromEvent'; `` <br>
··  `` import { map } from 'rxjs/operators'; `` <br>
··  `` const source = {{c1::fromEvent}}(document, 'click'); `` <br>
··  `` const example = source.{{c2::pipe}}(map(event => `Event time: ${event.timeStamp}`)); `` <br>
··  `` //output (example): 'Event time: 7276.390000000001' `` <br>
··  `` const subscribe = example.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 23

··  `` {{c1::timer}}(initialDelay: number | Date, {{c2::period}}: number, scheduler: Scheduler): Observable `` <br>

After given duration, emit numbers in sequence every specified duration

%

%

clozeq

---

## rxjs: learnrxjs 24

Ex: Emit first after 1 second, then every 2 seconds

··  `` import { timer } from 'rxjs/observable/timer'; `` <br>
··  `` const source = {{c1::timer(1000, 2000); }} `` <br>
··  `` //output: 0,1,2,3,4,5...... `` <br>
··  `` const subscribe = source.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 25

··  `` {{c1::catchError}}(project: function): Observable `` <br>

Gracefully handle errors in an observable sequence

%

%

clozeq

---

## rxjs: learnrxjs 26 catch error

··  `` import { {{c1::_throw}} } from 'rxjs/observable/throw'; `` <br>
··  `` import { {{c2::catchError}} } from 'rxjs/operators'; `` <br>
··  `` const source = _throw('This is an error!'); `` <br>
··  `` const example = {{c3::source.pipe}}(catchError(val => of(`I caught: ${val}`))); `` <br>
··  `` //output: 'I caught: This is an error' `` <br>
··  `` const subscribe = example.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 27

··  `` {{c1::publish}}(): ConnectableObservable `` <br>

{{c2::Share source}} and make hot by calling connect

%

%

clozeq

---

## rxjs: learnrxjs 28 publish

··  `` import { interval } from 'rxjs/observable/of'; `` <br>
··  `` import { publish, tap } 'rxjs/operators'; `` <br>
··  `` const source = interval(1000); `` <br>
··  `` const example = source.pipe( `` <br>
····  `` tap(_ => console.log('Do Something!')), `` <br>
····  `` {{c1::publish()}} `` <br>
··  `` ); `` <br>
··  `` const subscribe = example.subscribe(val => `` <br>
····  `` console.log(`Subscriber One: ${val}`) `` <br>
··  `` ); `` <br>
··  `` const subscribeTwo = example.subscribe(val => `` <br>
····  `` console.log(`Subscriber Two: ${val}`) `` <br>
··  `` ); `` <br>
··  `` //call connect after 5 seconds, causing source to begin emitting items `` <br>
··  `` setTimeout(() => { `` <br>
····  `` {{c2::example.connect(); }} `` <br>
··  `` }, 5000); `` <br>
··  `` // "Do Something!" `` <br>
··  `` // "Subscriber One: 0" `` <br>
··  `` // "Subscriber Two: 0" `` <br>
··  `` // "Do Something!" `` <br>
··  `` // "Subscriber One: 1" `` <br>
··  `` // "Subscriber Two: 1" `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 29

··  `` {{c1::debounceTime}}(dueTime: number, scheduler: Scheduler): Observable `` <br>

Discard emitted values that take less than the specified time between outputs

%

%

clozeq

---

## rxjs: learnrxjs 30

··  `` import { fromEvent } from 'rxjs/observable/fromEvent'; `` <br>
··  `` import { timer } from 'rxjs/observable/timer'; `` <br>
··  `` import { debounceTime, map } from 'rxjs/operators'; `` <br>
··  `` const input = document.getElementById('example'); `` <br>
··  `` const example = fromEvent(input, 'keyup').pipe(map(i => i.currentTarget.value)); `` <br>
··  `` //wait .5s between keyups to emit current value `` <br>
··  `` //throw away all other values `` <br>
··  `` const debouncedInput = example.pipe({{c1::debounceTime}}(500)); `` <br>
··  `` const subscribe = debouncedInput.subscribe(val => { `` <br>
····  `` console.log(`Debounced Input: ${val}`); `` <br>
··  `` }); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 31

··  `` {{c1::distinctUntilChanged}}(compare: function): Observable `` <br>

Only emit when current value different than the last

%

%

clozeq

---

## rxjs: learnrxjs 32

··  `` import { from } from 'rxjs/observable/from'; `` <br>
··  `` import { distinctUntilChanged } from 'rxjs/operators'; `` <br>
··  `` const myArrayWithDuplicatesInARow = from([1, 1, 2, 2, 3, 1, 2, 3]); `` <br>
··  `` const distinctSub = myArrayWithDuplicatesInARow `` <br>
····  `` .pipe({{c1::distinctUntilChanged()}}) `` <br>
····  `` //output: 1,2,3,1,2,3 `` <br>
····  `` .subscribe(val => console.log('DISTINCT SUB:', val)); `` <br>
··  `` const nonDistinctSub = myArrayWithDuplicatesInARow `` <br>
····  `` //output: 1,1,2,2,3,1,2,3 `` <br>
····  `` .subscribe(val => console.log('NON DISTINCT SUB:', val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 33

··  `` import { from } from 'rxjs/observable/from'; `` <br>
··  `` import { filter } from 'rxjs/operators'; `` <br>
··  `` //emit (1,2,3,4,5) `` <br>
··  `` const source = from([1, 2, 3, 4, 5]); `` <br>
··  `` const example = source.pipe({{c1::filter}}(num => num % 2 === 0)); `` <br>
··  `` //output: "Even number: 2", "Even number: 4" `` <br>
··  `` const subscribe = example.subscribe(val => console.log(`Even number: ${val}`)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 34

··  `` {{c1::take}}(count: number): Observable `` <br>

Emit provided number of values before completing.

%

%

clozeq

---

## rxjs: learnrxjs 35

··  `` const source = of(1, 2, 3, 4, 5); `` <br>
··  `` //take the first emitted value then complete `` <br>
··  `` const example = source.pipe({{c1::take(1)}}); `` <br>
··  `` //output: 1 `` <br>
··  `` const subscribe = example.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 36

··  `` {{c1::takeUntil}}(notifier: Observable): Observable `` <br>

Emit values until provided observable emits

%

%

clozeq

---

## rxjs: learnrxjs 37

··  `` const source = interval(1000); `` <br>
··  `` const timer = timer(5000); `` <br>
··  `` //when timer emits after 5s, complete source `` <br>
··  `` const example = source.pipe({{c1::takeUntil(timer)}}); `` <br>
··  `` //output: 0,1,2,3 `` <br>
··  `` const subscribe = example.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 38

··  `` {{c1::bufferTime}}(bufferTimeSpan: number, bufferCreationInterval: number, scheduler: Scheduler): Observable `` <br>

Collect emitted values until provided time has passed, emit as array

%

%

clozeq

---

## rxjs: learnrxjs 39

··  `` const source = Rx.Observable.interval(500); `` <br>
··  `` //After 2 seconds have passed, emit buffered values as an array `` <br>
··  `` const example = source.{{c1::bufferTime}}(2000); `` <br>
··  `` //ex. output [0,1,2]...[3,4,5,6] `` <br>
··  `` const subscribe = example.subscribe(val => `` <br>
····  `` console.log('Buffered with Time:', val) `` <br>
··  `` ); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 40

··  `` {{c1::concatMap}}(project: function, resultSelector: function): Observable `` <br>

Map values to inner observable, subscribe and emit in order.

%

%

clozeq

---

## rxjs: learnrxjs 41

··  `` const source = of(2000, 1000); `` <br>
··  `` // map value from source into inner observable, when complete emit result and move to next `` <br>
··  `` const example = source.pipe( `` <br>
····  `` {{c1::concatMap}}(val => {{c2::of}}(`Delayed by: ${val}ms`).{{c3::pipe}}(delay(val))) `` <br>
··  `` ); `` <br>
··  `` //output: With concatMap: Delayed by: 2000ms, With concatMap: Delayed by: 1000ms `` <br>
··  `` const subscribe = example.subscribe(val => `` <br>
····  `` console.log(`With concatMap: ${val}`) `` <br>
··  `` ); `` <br>
··  `` // showing the difference between concatMap and mergeMap `` <br>
··  `` const mergeMapExample = source `` <br>
····  `` .pipe( `` <br>
······  `` // just so we can log this after the first example has run `` <br>
······  `` delay(5000), `` <br>
······  `` mergeMap(val => of(`Delayed by: ${val}ms`).pipe(delay(val))) `` <br>
····  `` ) `` <br>
····  `` .subscribe(val => console.log(`With mergeMap: ${val}`)); `` <br>
····  `` //output: With mergeMap: Delayed by: 1000ms, With mergeMap: Delayed by: 2000ms `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 42

··  `` const source = from([1, 2, 3, 4, 5]); `` <br>
··  `` //add 10 to each value `` <br>
··  `` const example = source.pipe({{c1::map}}(val => val + 10)); `` <br>
··  `` //output: 11,12,13,14,15 `` <br>
··  `` const subscribe = example.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 43

··  `` {{c1::mergeMap}}(project: function: Observable, resultSelector: function: any, concurrent: number): Observable `` <br>

Map to observable, emit values

%

%

clozeq

---

## rxjs: learnrxjs 44

··  `` const source = of('Hello'); `` <br>
··  `` //map to inner observable and flatten `` <br>
··  `` const example = source.{{c2::pipe}}({{c1::mergeMap}}(val => {{c3::of}}(`${val} World!`))); `` <br>
··  `` //output: 'Hello World!' `` <br>
··  `` const subscribe = example.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 45

··  `` {{c1::scan}}(accumulator: function, seed: any): Observable `` <br>

Like Array.reduce 

%

%

clozeq

---

## rxjs: learnrxjs 46

··  `` const source = of(1, 2, 3); `` <br>
··  `` const example = source.pipe(scan((acc, curr) => {{c1::acc + curr}}, 0)); `` <br>
··  `` // output: 1,3,6 `` <br>
··  `` const subscribe = example.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 47

··  `` {{c1::switchMap}}(project: function: Observable, resultSelector: function(outerValue, innerValue, outerIndex, innerIndex): any): Observable `` <br>

Map to observable, complete previous inner observable, emit values

%

%

clozeq

---

## rxjs: learnrxjs 48

··  `` const source = timer(0, 5000); `` <br>
··  `` //switch to new inner observable when source emits, emit items that are emitted `` <br>
··  `` const example = source.pipe({{c1::switchMap}}(() => interval(500))); `` <br>
··  `` //output: 0,1,2,3,4,5,6,7,8,9...0,1,2,3,4,5,6,7,8 `` <br>
··  `` const subscribe = example.subscribe(val => console.log(val)); `` <br>

%

%

clozeq

---

## rxjs: learnrxjs 49

Combination Operators:

- combineAll
- {{c1::combineLatest}}
- {{c2::concat}}
- concatAll
- forkJoin
- {{c3::merge}}
- mergeAll
- pairwise
- race
- {{c4::startWith}}
- {{c5::withLatestFrom}}
- zip

%

%

clozeq

---

## rxjs: learnrxjs 50

Conditional Operators:

- {{c1::defaultIfEmpty}}
- {{c2::every}}

%

%

clozeq

---

## rxjs: learnrxjs 51

Creation Operators:

- {{c1::create}}
- {{c2::empty}} 
- {{c3::from}}
- {{c4::fromEvent}}
- fromPromise
- {{c5::interval}}
- {{c6::of}}
- {{c7::range}}
- {{c8::throw}}
- {{c9::timer}}

%

%

clozeq

---

## rxjs: learnrxjs 52

Error Handling:

- {{c1::catchError}}
- retry
- retryWhen

%

%

clozeq

---

## rxjs: learnrxjs 53

Multicasting:

- {{c1::publish}}
- multicast
- {{c2::share}}
- shareReplay

%

%

clozeq

---

## rxjs: learnrxjs 54

Filtering:

- {{c1::debounce}}
- debounceTime
- {{c2::distinctUntilChanged}}
- filter 
- {{c3::first}}
- ignoreElements
- last
- sample
- single
- skip
- skipUntil
- {{c4::take}}
- {{c5::takeUntil}}
- takeWhile
- {{c6::throttle}}
- throttleTime

%

%

clozeq

---

## rxjs: learnrxjs 55

Transformation:

- {{c1::buffer}}
- bufferCount
- bufferTime
- bufferToggle
- bufferWhen
- {{c2::concatMap}}
- concatMapTo
- exhaustMap
- expand
- {{c3::groupBy}}
- map
- {{c4::mapTo}}
- {{c5::mergeMap}}
- partition
- {{c6::pluck}} 
- reduce
- {{c7::scan}}
- {{c8::switchMap}}
- window
- windowCount
- windowTime
- windowToggle
- windowWhen


%

%

clozeq

---

## rxjs: marble 01

`create({{c1::producer}})`


%

%

clozeq

---

## rxjs: marble 02

`empty()`

··  `` {{c1::-|}} `` <br>


%

%

clozeq

---

## rxjs: marble 03

`from(input)`

··  `` input: {{c1::Array|PromiseLike|Observable}} `` <br>


%

%

clozeq

---

## rxjs: marble 04

`of(a, b)`

··  `` of({{c1::1,2,3}}) `` <br>
··  `` 123| `` <br>

%

%

clozeq

---

## rxjs: marble 05

`fromArray(array)`

··  `` fromArray({{c1::[1,2,3]}}) `` <br>
··  `` 123| `` <br>

%

%

clozeq

---

## rxjs: marble 06

`periodic(period)` = `interval()`

··  `` periodic({{c1::1000}}) `` <br>
··  `` ---0---1---2---3.... `` <br>

%

%

clozeq

---

## rxjs: marble 07

`merge(stream1, stream2)`

··  `` --1----2----4 `` <br>
··  `` ----a----b--- `` <br>
··  `` merge `` <br>
··  `` {{c1::--1-a--2-b--4}} `` <br>

%

%

clozeq

---

## rxjs: marble 08

`combine(stream1, stream2)`

combines latest events from each input stream

··  `` --1----2----4 `` <br>
··  `` ----a----b--- `` <br>
··  `` combine `` <br>
··  `` {{c1::----1a---2b-4b}} `` <br>

%

%

clozeq

---

## rxjs: marble 09

`mapTo(projectedValue)`

··  `` --1----2----4 `` <br>
··  `` mapTo(10) `` <br>
··  `` {{c1::--10---10---10}} `` <br>

%

%

clozeq

---

## rxjs: marble 10

`take(amount)`

··  `` --1----2----4 `` <br>
··  `` take(2) `` <br>
··  `` {{c1::--1----2| }} `` <br>


%

%

clozeq

---

## rxjs: marble 11

`drop(amount)`

reverse of `take`

··  `` --1----2----4 `` <br>
··  `` drop(2) `` <br>
··  `` {{c1::------------4 }} `` <br>

%

%

clozeq

---

## rxjs: marble 12

`last()`

··  `` --1----2----4 `` <br>
··  `` last() `` <br>
··  `` {{c1::------------4 }} `` <br>

%

%

clozeq

---

## rxjs: marble 13

`startWith(initial)`

··  `` --1----2----4 `` <br>
··  `` startWith(0) `` <br>
··  `` {{c1::0-1----2----4 }} `` <br>

%

%

clozeq

---

## rxjs: marble 14

`endWhen(other)` = `takeUntil`

··  `` --1----2----4 `` <br>
··  `` endWhen(  -a--b--| ) `` <br>
··  `` {{c1::--1----2---| }} `` <br>

%

%

clozeq

---

## rxjs: marble 15

`fold(accumulate, seed)` = `scan`

··  `` --1----2----4 `` <br>
··  `` fold((acc, x) => acc + x, 3) `` <br>
··  `` {{c1::3-4----6----10 }} `` <br>

%

%

clozeq

---

## rxjs: marble 16

`flatten()`

··  `` --+--------+--------------- `` <br>
····  `` \······  \ `` <br>
····   `` \····   ----1----2---3-- `` <br>
····   `` --a--b----c----d-------- `` <br>
············  `` flatten `` <br>
··  `` {{c1::-----a--b------1----2---3-- }} `` <br>

%

%

clozeq

---

## rxjs: marble 17

`compose(operator)`

{{c2::chained}} style: instead of `out = f(in)` write `out = {{c1::in.compose(f)}}`

%

%

clozeq

---

## rxjs: marble 18

`debug(labelOrSpy)`

··  `` --1----2----4 `` <br>
··  `` debug `` <br>
··  `` {{c1::--1----2----4 }} `` <br>


%

%

clozeq

---

## rxjs: egghead rx intro 01

Nested Observables:

··  `` var req$ = Rx.Observable.just('https://api.github.com/users') `` <br>
··  `` req$.subscribe(url => { `` <br>
····  `` var res$ = Rx.Observable.fromPromise(jQuery.getJSON(url)); `` <br>
····  `` res$.{{c1::subscribe}}(response => { console.log(response) }); `` <br>
··  `` }) `` <br>
··  `` --->>> `` <br>
··  `` var res$ = req$. `` <br>
··  `` {{c2::flatMap}}(url => Rx.Observable.fromPromise(jQuery.getJSON(url))); `` <br>
··  `` res$.subscribe(response => { console.log(response) }); `` <br>

%

%

clozeq

---

## rxjs: egghead rx intro 02

We have two streams of request: startupRequest$ and refreshClick$.

··  `` var refreshButton = document.querySelector('.refresh') `` <br>
··  `` var refreshClick$ = Rx.Observable.fromEvent(refreshButton, 'click') `` <br>
··  `` var startupRequest$ = Rx.Observable.just('https://api.github.com/users') `` <br>
··  `` var req$ = refreshClick$ `` <br>
····  `` .map(ev => { `` <br>
······  `` var randomOffset = Math.floor(Math.random()*500); `` <br>
······  `` return 'https://api.github.com/users?since='; `` <br>
····  `` }) `` <br>
··  `` var res$ = req$. `` <br>
····  `` flatMap(url => Rx.Observable.fromPromise(jQuery.getJSON(url))); `` <br>

Merging stream of clicks with start stream:

··  `` var res$ = req$. `` <br>
····  `` {{c1::merge}}(startupRequest$). `` <br>
····  `` flatMap(url => Rx.Observable.fromPromise(jQuery.getJSON(url))); `` <br>

%

%

clozeq

---

## rxjs: egghead rx intro 03

Clear HTML elements on startup and each refresh. And this should be done at the time of declaration:

··  `` function createSuggestion$(res$) { `` <br>
····  `` return res$.map(listUser => `` <br>
······  `` listUser[Math.floor(Math.random().listUser.length)] `` <br>
····  `` ) `` <br>
····  `` .{{c1::startWith(null) }} `` <br>
····  `` .merge(refreshClick$.map(ev => null)) `` <br>

%

%

clozeq

---

## rxjs: egghead rx intro 04

We make a new request for each subscribe() call:

··  `` var suggestion1$ = createSuggestion$(res$) `` <br>
··  `` var suggestion2$ = createSuggestion$(res$) `` <br>
··  `` var suggestion3$ = createSuggestion$(res$) `` <br>
··  `` --->>> `` <br>
··  `` var res$ = req$ `` <br>
····  `` .merge(startupReq$) `` <br>
····  `` .flatMap(url => Rx.Observable.fromPromise(jQuery.getJSON(url))) `` <br>
····  `` .{{c1::shareReplay}}(1) `` <br>

%

%

clozeq

---

## rxjs: egghead cyclejs fundamentals 01

··  `` const decrementClick$ = sources.DOM.select('.decrement').events('click') `` <br>
··  `` const incrementClick$ = sources.DOM.select('.increment').events('click') `` <br>
··  `` const decrementAction$ = decrementClick$.map(ev => -1) `` <br>
··  `` const incrementAction$ = incrementClick$.map(ev => +1) `` <br>
··  `` const number$ = Rx.Observable.of(10) `` <br>
····  `` .{{c1::merge}}(decrementAction$) `` <br>
····  `` .merge(incrementAction$) `` <br>
····  `` .{{c2::scan}}( (prev, curr) => prev + curr) `` <br>


%

%

clozeq

---

## rxjs: egghead cyclejs fundamentals 02

··  `` const clickEvent$ = sources.DOM.select('.get-first').events('click') `` <br>
··  `` const request$ = clickEvent$.map(() => { `` <br>
····  `` return { `` <br>
······  `` url: 'http://jsonplaceholder.typicode.com/users/1', `` <br>
······  `` method: 'GET', `` <br>
····  `` } `` <br>
··  `` }) `` <br>
··  `` const response$ = sources.HTTP `` <br>
····  `` .filter(response$ => response$.request.url === 'http://jsonplaceholder.typicode.com/users/1') `` <br>
····  `` .{{c1::switch()}} `` <br>
··  `` const firstUser$ = response$.map(response => {{c2::response.body}}) `` <br>
····  `` .startWith(null) `` <br>

%

%

clozeq

---

## rxjs: egghead cyclejs fundamentals 03

··  `` const state$ = Rx.Observable.{{c1::combineLatest}}( `` <br>
····  `` changeWeight$.{{c2::startWith}}(70), `` <br>
····  `` changeHeight$.startWith(170), `` <br>
····  `` {{c3::(weight, height)}} => { `` <br>
······  `` const heightMeters = height * 0.01; `` <br>
······  `` const bmi = Math.round(weight / heightMeters * heightMeters)); `` <br>
······  `` return {bmi, weight, height}; `` <br>
····  `` } `` <br>

%

%

clozeq

---

## rxjs: egghead cyclejs fundamentals 04

··  `` function main(sources) { `` <br>
····  `` const {changeWeight$, changeHeight$} = {{c1::intent}}(sources.DOM)  `` <br>
····  `` const state$ = {{c2::model}}(changeWeight$, changeHeight$) `` <br>
····  `` const vtree$ = {{c3::view}}(state$) `` <br>
····  `` return { `` <br>
······  `` DOM: {{c4::vtree$}} `` <br>
····  `` } `` <br>
··  `` } `` <br>

%

%

clozeq

---

## rxjs: egghead cyclejs fundamentals 05

··  `` function {{c1::intent}}({{c2::DOMSource}}) { `` <br>
····  `` const changeWeight$ = DOMSource.select('.weight').events('input') `` <br>
······  `` .map(ev => ev.target.value) `` <br>
····  `` const changeHeight$ = DOMSource.select('.height').events('input') `` <br>
······  `` .map(ev => ev.target.value) `` <br>
····  `` return {changeWeight$, changeHeight$} `` <br>
··  `` } `` <br>

%

%

clozeq

---

## rxjs: egghead cyclejs fundamentals 06

Component

··  `` function {{c1::LabeledSlider}}(sources) { `` <br>
····  `` const change$ = intent(sources.DOM) `` <br>
····  `` const state$ = model(change$, sources.props) `` <br>
····  `` const vtree$ = view(state$) `` <br>
····  `` return { `` <br>
······  `` DOM: vtree$, `` <br>
······  `` {{c2::value}}: state$.map(state => state.value), `` <br>
····  `` } `` <br>
··  `` } `` <br>

%

%

clozeq

---

## rxjs: egghead cyclejs fundamentals 07

Component

··  `` function model(change$, {{c1::props$}}) { `` <br>
····  `` const initialValue$ = props$.map(props => props.init).first() `` <br>
····  `` const value$ = initialValue$.concat(change$) `` <br>
····  `` return Rx.Observable.{{c2::combineLatest}}(value$, props$, (value, props) => { `` <br>
······  `` return { `` <br>
········  `` label: props.label, `` <br>
········  `` unit: props.unit, `` <br>
········  `` min: props.min, `` <br>
········  `` max: props.max, `` <br>
········  `` value: value, `` <br>
······  `` } `` <br>
····  `` } `` <br>
··  `` } `` <br>

%

%

clozeq

---

## rxjs: egghead cyclejs fundamentals 08

··  `` function main(sources) { `` <br>
····  `` const props$ = Rx.Observable.{{c1::of}}({ `` <br>
······  `` label: 'Height', `` <br>
······  `` unit: 'cm', `` <br>
······  `` min: 140, `` <br>
······  `` max: 220, `` <br>
······  `` init: 170 `` <br>
····  `` }) `` <br>
····  `` return LabeledSlider({DOM: sources.DOM, props: {{c2::props$}}}) `` <br>
··  `` } `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 01

··  `` function subscribe(observer) { `` <br>
····  `` observer.next(42) `` <br>
····  `` observer.next(100) `` <br>
····  `` observer.next(200) `` <br>
····  `` observer.complete() `` <br>
··  `` } `` <br>
··  `` var bar = Rx.Observable({{c1::subscribe}}) `` <br>
··  `` var {{c2::observer}} = { `` <br>
····  `` next: function (x) { console.log('next + x) },  `` <br>
····  `` error: function (err) { console.log('error ' + err) }, `` <br>
····  `` complete: function () { console.log('done') }, `` <br>
··  `` } `` <br>
··  `` bar.subscribe(observer) `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 02

What does subscribe return?

··  `` function subscribe(observer) { `` <br>
····  `` var id = setInterval(function () { `` <br>
······  `` observer.next('hi') `` <br>
····  `` }, 1000) `` <br>
····  `` {{c1::return}} function unsubscribe() { `` <br>
······  `` {{c2::clearInterval}}(id) `` <br>
····  `` } `` <br>
··  `` } `` <br>
··  `` var bar = Rx.Observable(subscribe) `` <br>
··  `` var observer = { `` <br>
····  `` next: function (x) { console.log('next + x) },  `` <br>
····  `` error: function (err) { console.log('error ' + err) }, `` <br>
····  `` complete: function () { console.log('done') }, `` <br>
··  `` } `` <br>
··  `` var subscription = bar.subscribe(observer) `` <br>
··  `` setTimeout(function() { `` <br>
····  `` subscription.{{c3::unsubscribe}}() `` <br>
··  `` }, 4500) `` <br>


%

%

clozeq

---

## rxjs: egghead rx operators 03

··  `` function operator({{c1::source}}) { `` <br>
····  `` // result = ... `` <br>
····  `` return result; `` <br>
··  `` } `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 04

··  `` var foo = Rx.Observable.of(1,2,3,4,5) `` <br>
··  `` function {{c1::multiplyByTen}}() { `` <br>
····  `` var {{c2::source}} = this; `` <br>
····  `` var result = Rx.Observable.create(function subscribe(observer) { `` <br>
······  `` source.subscribe( `` <br>
········  `` function (x) { observer.next(x*10)}, `` <br>
········  `` function (err) { observer.error(err) }, `` <br>
········  `` function () { observer.complete() } `` <br>
······  `` ) `` <br>
····  `` }) `` <br>
····  `` return result `` <br>
··  `` } `` <br>
··  `` Rx.Observable.{{c3::prototype}}.multiplyByTen = multiplyByTen `` <br>
··  `` var bar = foo.multiplyByTen() `` <br>
··  `` bar.subscribe( `` <br>
····  `` function (x) { console.log('next + x) },  `` <br>
····  `` function (err) { console.log('error ' + err) }, `` <br>
····  `` function () { console.log('done') }, `` <br>
··  `` ) `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 05

Generalize it by making multiplier a parameter:

··  `` var foo = Rx.Observable.of(1,2,3,4,5) `` <br>
··  `` function multiplyBy(multiplier) { `` <br>
····  `` var source = this; `` <br>
····  `` var result = Rx.Observable.create(function subscribe(observer) { `` <br>
······  `` {{c1::source.subscribe}}( `` <br>
········  `` function (x) { observer.next(x*multiplier)}, `` <br>
········  `` function (err) { observer.error(err) }, `` <br>
········  `` function () { observer.complete() } `` <br>
······  `` ) `` <br>
····  `` }) `` <br>
····  `` return result `` <br>
··  `` } `` <br>
··  `` Rx.Observable.prototype.multiplyBy = multiplyBy `` <br>
··  `` var bar = foo.multiplyBy(10) `` <br>
··  `` bar.subscribe( `` <br>
····  `` function (x) { console.log('next + x) },  `` <br>
····  `` function (err) { console.log('error ' + err) }, `` <br>
····  `` function () { console.log('done') }, `` <br>
··  `` ) `` <br>

We create a subscription chain.

··  `` bar.subscribe -> source.subscribe -> ... `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 06

`do` is a special case of `map` where it returns its {{c1::input untouched}}.

%

%

clozeq

---

## rxjs: egghead rx operators 07

··  `` --0--1--2--3--4--5--6--7- `` <br>
····  `` take(3) `` <br>
··  `` --0--1--2|··············  (foo) `` <br>
··········   `` (345|)········   (more) `` <br>
····  `` concat `` <br>
··  `` {{c1::--0--1--2(345|)}} `` <br>

··  `` var foo = Rx.Observable.interval(100).take(3) `` <br>
··  `` var more = Rx.Observable.of(3,4,5) `` <br>
··  `` var bar = foo.concat(more) `` <br>


%

%

clozeq

---

## rxjs: egghead rx operators 08

··  `` --0--1--2--3--4--5--6--7- `` <br>
····  `` take(3) `` <br>
··  `` --0--1--2|··············  (foo) `` <br>
····  `` startWith('a') `` <br>
··  `` {{c1::a-0--1--2|··············   }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 09

merge = OR operator

··  `` ----0----1---2--  (foo) `` <br>
··  `` --0----1-----3--  (bar) `` <br>
····   `` merge `` <br>
··  `` {{c1::--0-0--1-1---(23)--   }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 10

combineLatest = AND operator

··  `` ----0----1---2--  (foo) `` <br>
··  `` --0----1-----3--  (bar) `` <br>
····  `` foo.combineLatest(bar, (x,y) => x + y) `` <br>
··  `` {{c1::----0--1-2---7--   }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 11

uppercase if latest value from bar is 1

··  `` ----H--e---l---l---o|  (foo) `` <br>
··  `` --0--1---0---1---0|··  (bar) `` <br>
····  `` foo.withLatestFrom(bar, (x,y) => y === 1 ? x.toUpperCase() : x.toLowerCase()) `` <br>
··  `` {{c1::----h--E---l---L---o|  }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 12

AND style combinations:

- combineLatest
- withLatestFrom
- zip

··  `` ----0----1---2--  (foo) `` <br>
··  `` --0----1-----3--  (bar) `` <br>
····  `` zip((x,y) => x + y) `` <br>
··  `` {{c1::----0----2---5-- }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 13

ex: spread a sync value over time

··  `` (hello|)········  (foo) `` <br>
··  `` --0--1--2--3--4|  (bar) `` <br>
····  `` zip((x,y) => x) `` <br>
··  `` {{c1::--h--e--l--l--o| }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 14

combining values over time of one observable: horizontal combinator

··  `` ---h--e--l--l--o `` <br>
····  `` scan((acc,x) => acc + x, '') `` <br>
··  `` {{c1::---h--(he)(hel)(hell)(hello) }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 15

combine last values:

- buffer
- bufferCount
- bufferTime
- bufferToggle
- bufferWhen

··  `` ---h--e--l--l--o| `` <br>
····  `` bufferCount(2) `` <br>
··  `` {{c1::------([h,e])---ll--o| }} `` <br>


%

%

clozeq

---

## rxjs: egghead rx operators 16

··  `` --0--1--2--3--4| `` <br>
····  `` delay(1000) `` <br>
··  `` {{c1::----0--1--2--3--4| }} `` <br>

··  `` --0--1--2--3--4| `` <br>
····  `` delayWhen(x => ----0| ) `` <br>
··  `` {{c2::----0--1--2--3--4| }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 17

rate limiting operators:

- debounce
- throttle
- audit

waits for some silence time

··  `` --0--1--2------- `` <br>
····  `` debounceTime(1000) `` <br>
··  `` {{c1::------------2--- }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 18

Do it only when user stands for 500 ms:

··  `` var inputText = Rx.Observable.fromEvent(fieldElem, 'input') `` <br>
····  `` .map(ev => ev.target.value) `` <br>
····  `` .{{c1::debounceTime(500) }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 19

First emits, then causes silence

··  `` --0--1--2-----4| `` <br>
····  `` throttleTime(1000) `` <br>
··  `` {{c1::--0-----2-----4| }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 20

··  `` --a--b--a--c--b| `` <br>
····  `` distinct() `` <br>
··  `` {{c1::--a--b-----c---| }} `` <br>

··  `` --a--b--a--a--b| `` <br>
····  `` distinctUntilChanged() `` <br>
··  `` {{c2::--a--b--a-----b| }} `` <br>

%

%

clozeq

---

## rxjs: egghead rx operators 21

Error handling operators:

- catch
- retry

··  `` var result = bar.catch(error => Rx.Observable.of('Z')) `` <br>

··  `` --a--b--c--d--2|  (foo) `` <br>
····  `` map(toUpperCase) `` <br>
··  `` --a--b--c--d--#  (bar) `` <br>
····  `` catch(# => Z|) `` <br>
··  `` {{c1::--a--b--c--d--Z| }} `` <br>

%

%

clozeq

---

