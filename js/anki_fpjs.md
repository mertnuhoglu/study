
## Article: Lenses and Virtual DOM Support Open Closed 01

Sandi Metz, “duplication is far cheaper than using the wrong abstraction.”

%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 02

getter:

··  `` foo.bar; `` <br>
··  `` --->>> `` <br>
··  `` {{c1::view}}(bar, foo); `` <br>

setter:

··  `` foo.bar = 3; `` <br>
··  `` --->>> `` <br>
··  `` {{c2::set}}(bar, 3, foo); `` <br>


%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 03

setter:

··  `` foo.bar = 3; `` <br>
··  `` --->>> `` <br>
··  `` set({{c1::bar}}, {{c2::3}}, {{c3::foo}}); `` <br>


%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 04

They compose:

··  `` foo.bar.baz.quux; `` <br>
··  `` --->>> `` <br>
··  `` view({{c1::compose}}({{c2::bar, baz, quux}}), foo); `` <br>

··  `` foo.bar.baz.quux = 3; `` <br>
··  `` --->>> `` <br>
··  `` set(compose(bar, baz, quux), 3, foo); `` <br>


%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 05

··  `` import {h} from 'virtual-dom'; `` <br>

··  `` function radio(name, value, description, actualValue) { `` <br>
····  `` return h('input', { `` <br>
······  `` checked: value === actualValue, `` <br>
······  `` type: 'radio', `` <br>
······  `` value `` <br>
····  `` }, description); `` <br>
··  `` } `` <br>

··  `` > const bar = radio('foo', 'bar', 'Bar', 'bar'); `` <br>
··  `` VirtualNode { `` <br>
····  `` tagName: 'input', `` <br>
····  `` {{c1::properties}}: `` <br>
····   `` { checked: true, `` <br>
······   `` type: 'radio', `` <br>
······   `` value: SoftSetHook { value: 'bar' } }, `` <br>
····  `` children: [ VirtualText { text: 'Bar' } ], `` <br>


%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 06

··  `` const properties = lensProp('properties'); `` <br>
··  `` const disabled = lensProp('disabled'); `` <br>
··  `` const propsDisabled = compose({{c1::properties, disabled}}); `` <br>
··  `` const disable = set(propsDisabled, true); `` <br>

··  `` > disable(bar) `` <br>
··  `` { tagName: 'input', `` <br>
····  `` properties: `` <br>
····   `` { checked: true, `` <br>
······   `` type: 'radio', `` <br>
······   `` value: SoftSetHook { value: 'bar' }, `` <br>
······   `` {{c2::disabled}}: true }, `` <br>
····  `` children: [ VirtualText { text: 'Bar' } ], `` <br>

%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 07

Let's work on a nested radio object:

··  `` > bar.children[0].children[1] `` <br>
··  `` VirtualNode { `` <br>
····  `` tagName: 'input', `` <br>
····  `` properties: `` <br>
····   `` { checked: true, `` <br>
······   `` type: 'radio', `` <br>
······   `` value: SoftSetHook { value: 'bar' } }, `` <br>
····  `` children: [ VirtualText { text: 'Bar' } ], `` <br>

··  `` const children = lensProp({{c1::'children'}}); `` <br>
··  `` const _0 = {{c2::lensIndex}}(0); `` <br>
··  `` const _1 = lensIndex(1); `` <br>
··  `` const complexDisabled = compose({{c3::children, _0}}, children, _1, propsDisabled); `` <br>
··  `` const complexDisable = set(complexDisabled, true); `` <br>

%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 08

Alternative way by reusing `disable` function:

··  `` const properties = lensProp('properties'); `` <br>
··  `` const disabled = lensProp('disabled'); `` <br>
··  `` const propsDisabled = compose({{c1::properties, disabled}}); `` <br>
··  `` const disable = set(propsDisabled, true); `` <br>

··  `` const children = lensProp('children'); `` <br>
··  `` const _0 = lensIndex(0); `` <br>
··  `` const _1 = lensIndex(1); `` <br>
··  `` const complexDisabled = compose(children, _0, children, _1); `` <br>
··  `` const complexDisable = {{c2::over}}(complexDisabled, disable); `` <br>

%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 09

Here we use `over` instead of `set`. `over` applies a function to the focus of the lens:

··  `` set(lensProp('foo'), 13, {foo: 3}); //=> {foo: 13} `` <br>
··  `` over(lensProp('foo'), {{c1::add(10)}}, {foo: 3}); //=> {foo: 13} `` <br>

%

%

clozeq

---

## Article - Lenses with Immutable.js - Brian Lonsdorf 01

··  `` import {{c1::Task}} from 'data.task' `` <br>
··  `` import {{c2::Maybe}} from 'data.maybe' `` <br>
··  `` import {lensProp, lensIndex,compose, map, toUpper, reverse, replace} from {{c3::'ramda'}} `` <br>
··  `` import {mapped, over, view, set, lens, iso, from} from {{c4::'ramda-lens'}} `` <br>
··  `` import {Map, List} from 'immutable' `` <br>

%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 02

··  `` const addrs = [{street: '99 Walnut Dr.', zip: '04821'}, {street: '2321 Crane Way', zip: '08082'}] `` <br>
··  `` const user = {id: 3, name: 'Charles Bronson', addresses: addrs} `` <br>
··  `` const name = lensProp('name') `` <br>
··  `` view({{c1::name, user}}) `` <br>
··  `` // Charles Bronson `` <br>
··  `` set({{c2::name, 'Richard Branson', user}}) `` <br>
··  `` // { id: 3, name: 'Richard Branson', addresses: [..] } `` <br>
··  `` over(name, {{c3::toUpper}}, user) `` <br>
··  `` // { id: 3, name: 'CHARLES BRONSON', addresses: [ { street: '99 Walnut Dr.', zip: '04821' }, { street: '2321 Crane Way', zip: '08082' } ] } `` <br>

%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 03

Lenses compose in left-to-right order:

··  `` const addrs = [{street: '99 Walnut Dr.', zip: '04821'}, {street: '2321 Crane Way', zip: '08082'}] `` <br>
··  `` const user = {id: 3, name: 'Charles Bronson', addresses: addrs} `` <br>
··  `` const addresses = lensProp('addresses') `` <br>
··  `` const street = lensProp('street') `` <br>
··  `` const first = lensIndex(0) `` <br>
··  `` const firstStreet = compose({{c1::addresses, first, street}}) `` <br>
··  `` view(firstStreet, user) `` <br>
··  `` // 99 Walnut Dr. `` <br>

%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 04

To access an object inside a function use `mapped` which is also a lens.

··  `` over(compose({{c1::mapped, mapped, mapped, name}}), toUpper, Task.of(Maybe.of([user]))) `` <br>
··  `` // Task(Maybe([{ id: 3, name: 'CHARLES BRONSON', addresses: [Object] }])) `` <br>

This is like `map` over all values of some array.

%

%

clozeq

---

## Article: Lenses and Virtual DOM Support Open Closed 05

··  `` const addresses = lensProp('addresses') `` <br>
··  `` const street = lensProp('street') `` <br>
··  `` const allStreets = compose(addresses, mapped, street) `` <br>
··  `` //  :: Int -> Task Error User `` <br>
··  `` const getUser = id => new Task((rej, res) => setTimeout(() => res(user), 400)) `` <br>
··  `` // profilePage :: User -> Html `` <br>
··  `` const profilePage = compose(map(x => `<span>${x.street}<span>`), view(addresses)) `` <br>
··  `` // updateUser :: User -> User `` <br>
··  `` const updateUser = over(allStreets, replace(/\d+/, '****')) `` <br>
··  `` // renderProfile :: User -> Html `` <br>
··  `` const renderProfile = compose(map(compose(profilePage, updateUser)), getUser) `` <br>
··  `` {{c1::renderProfile}}(1).fork(console.log, console.log) `` <br>
··  `` // [ '<span>**** Walnut Dr.<span>', '<span>**** Crane Way<span>' ] `` <br>

%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 01

Lenses are references to {{c1::subparts}} of complex data structures expressed in a functional form.

You can focus in some particular path of a complex object without losing the {{c2::context}} of overall data.

%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 02

Critical idea: `lensOver` applies a function at a particular part of a complex data object, yet it still returns back the {{c1::entire}} object.

This allows us to {{c2::chain}} together a lot of pure, declarative operations.

%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 03

··  `` var bigBird = { `` <br>
····  `` name: "Big Bird", `` <br>
····  `` age:6, `` <br>
····  `` comments:[ `` <br>
······  `` {body:'sing, sing a song',title:'Line 1'}, `` <br>
······  `` {body:'make it simple',title:'Line 2'}, `` <br>
······  `` {body:'sing out strong',title:'Line 3'}] `` <br>
··  `` }; `` <br>
··  `` {{c1::lensOver}}({{c2::'comments'}}, {{c3::map}}( lensOver('body', capitalizeFirst) ))(bigBird); `` <br>
··  `` /* `` <br>
··  `` { `` <br>
··   `` "name": "Big Bird", `` <br>
··   `` "age": 6, `` <br>
··   `` "comments": [ `` <br>
····  `` {"body": "Sing, sing a song", "title": "Line 1"}, `` <br>
····  `` {"body": "Make it simple", "title": "Line 2"}, `` <br>
····  `` {"body": "Sing out strong", "title": "Line 3"} `` <br>
··   `` ] `` <br>
··  `` } `` <br>
··  `` */ `` <br>

%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 04

To build a real lens implementation we need: composing, currying, mapping, array and object cloning and splicing, k combinator, constant and identity functors.

··  `` const pipe = (fn,...fns) => (...args) => fns.{{c1::reduce}}( (acc, f) => f(acc), {{c2::fn(...args)}}); `` <br>
··  `` const compose = (...fns) => pipe(...fns.{{c3::reverse}}()); `` <br>
··  `` const curry = (f, ...args) => (f.length <= args.length) ?  `` <br>
····  `` f(...args) :  `` <br>
····  `` (...more) => curry(f, ...args, ...more); `` <br>
··  `` const mapWith = curry((f, xs) => {{c4::xs.map}}(f)); `` <br>

%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 05

··  `` update = require('ramda.update');//for Arrays `` <br>
··  `` assoc = require('ramda.assoc');//for Objects `` <br>
··  `` //curried arguments to both are in this order: {{c1::index|key}} => {{c2::replacement value}} -> Array|Object `` <br>

%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 06

··  `` const arrayLens = curry( `` <br>
····  `` (index, f, xs) => mapWith(replacement => {{c1::update}}(index, replacement, xs), f(xs[index]) ); `` <br>
··  `` ); `` <br>

··  `` const objectLens = curry( `` <br>
····  `` (key, f, xs) => mapWith(replacement => {{c2::assoc}}(key, replacement, xs), f(xs[key]) ); `` <br>
··  `` ); `` <br>

%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 07

··  `` //K Combinator! `` <br>
··  `` const _K = {{c1::x => y => x}}; `` <br>
··  `` const Const = x => ({value: x, map(){ {{c2::return this}}; }}); `` <br>
··  `` const Identity = x => ({value: x, map: mapf => {{c3::Identity}}(mapf(x)) }); `` <br>

%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 08

What is "mapping"? 

"mapping" over something means taking a container, {{c1::opening it up}}, applying a function to its contents, and then returning same type of container.

%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 09

··  `` const view = curry( (lens, targetData) => lens(Const)(targetData).value ); `` <br>
··  `` const over = curry( (lens, apf, targetData) => lens(y => Identity({{c1::apf}}(y)) )(targetData).value ); `` <br>
··  `` const set = curry( (lens, val, targetData) => over(lens, {{c2::_K}}(val), targetData) ); `` <br>

%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 10

··  `` compose(objectLens('comments'), arrayLens(0)) `` <br>

This is equivalent to: {{c1::`['comments'][0]`}}


%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 11

`makeLenses` returns pre-configured lenses:

··  `` const makeLenses = (...paths) => paths.reduce( (acc, key) => { `` <br>
······  `` const oL = objectLens(key) `` <br>
······  `` return set(oL, oL, acc) `` <br>
····  `` }, {num: arrayLens} `` <br>
··  `` ) `` <br>
··  `` let L = makeLenses('comments', 'body') `` <br>
··  `` const firstCommentBody = compose({{c1::L.comments, L.num(0), L.body}}) `` <br>

Ramda has similar function: {{c1::`lensPath`}}

%

%

clozeq

---

## Article: Functional Lenses, How Do They Work - Drew Tipson 12

··  `` const mapped = curry( `` <br>
····  `` (f, x) => Identity( {{c1::mapWith}}( compose( x=>x.value, f), x) ) `` <br>
··  `` ); `` <br>

%

%

clozeq

---

### Article: Lenses Quick n' Dirty-104807358.mp4 - Brian Lonsdorf 01

Functor Review

··  `` map({{c1::add(1)}}, [1,2,3]) `` <br>
··  `` //=> [2,3,4] `` <br>
··  `` map(add(1), {a: 1, b: 2}) `` <br>
··  `` //=> {a: 2, b: 3} `` <br>

%

%

clozeq

---

### Article: Lenses Quick n' Dirty-104807358.mp4 - Brian Lonsdorf 02

··  `` {{c1::map}}(map(add(1)), Future.of(Maybe(2))) `` <br>
··  `` //=> Future(Maybe(3)) `` <br>

%

%

clozeq

---

### Article: Lenses Quick n' Dirty-104807358.mp4 - Brian Lonsdorf 03

··  `` map(f, Identity(x)) // {{c1::Identity(f(x))}} `` <br>
··  `` map(f, Const(x)) // {{c2::Const(x)}} !noop `` <br>

%

%

clozeq

---

### Article: Lenses Quick n' Dirty-104807358.mp4 - Brian Lonsdorf 04

··  `` map(add(1), Identity(2)) `` <br>
··  `` //=> {{c1::Identity(3)}} `` <br>
··  `` map(add(1), Const(2)) `` <br>
··  `` //=> {{c2::Const(2)}} `` <br>

%

%

clozeq

---

### Article: Lenses Quick n' Dirty-104807358.mp4 - Brian Lonsdorf 05

··  `` compose(fold, {{c1::map(add(1))}}, Identity)(2) `` <br>
··  `` //=> 3 `` <br>
··  `` compose(fold, map(add(1)), Const)(2) `` <br>
··  `` //=> 2 `` <br>

%

%

clozeq

---

### Article: Lenses Quick n' Dirty-104807358.mp4 - Brian Lonsdorf 06

··  `` var getter = function(f, x) { `` <br>
····  `` return compose(fold, map(f), {{c1::Const}})(x) `` <br>
··  `` } `` <br>
··  `` var setter = function(f, x) { `` <br>
····  `` return compose(fold, {{c3::map(f)}}, Identity)(x) `` <br>
··  `` } `` <br>
··  `` getter(reverse, [1,2,3]) `` <br>
··  `` //=> {{c2::[1,2,3]}} `` <br>
··  `` setter(reverse, [1,2,3]) `` <br>
··  `` //=> [3,2,1] `` <br>

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 01

Ever worked with "Collections"? Ever {{c1::chained}} collection-y operations together? Stop it.

Replace .chain().op().op().value() syntax with something more point-free and functional.

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 02

··  `` const reduce = (accFn, start, xs) => {{c1::xs}}.reduce(accFn, start); `` <br>
··  `` reduce( {{c2::(acc, item)}} => {{c3::acc+item}}, 0, [1,2,3] );//-> 6 `` <br>

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 03

`accFn` is the most important part. It gets {{c1::two things}} and combine them into one result.

`(a,b)` -&gt; `a` or in curried notation: `a` -&gt; `b` -&gt; `a`

The first argument and the output are of the {{c2::same type}} because reduce is built {{c3::to pipe}} that output right into the first argument of theh same function again on the next loop.

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 04

`reduce` is similar to `compose` as if a {{c1::single callback}} function can be repeated over and over as there are more items remaining.

··  `` const adding = (acc, item) => acc+item;//reducing function: a -> b -> a `` <br>
··  `` [1,2,3].reduce(adding, 0);//-> 6 `` <br>
··  `` //same as adding, but arguments are reversed and curried `` <br>
··  `` const addingRC = item => acc => acc+item;// b -> a -> a `` <br>
··  `` compose( addingRC(1), addingRC(2), addingRC(3) )(0);//-> 6 `` <br>

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 05

Any iterative operation like mapping and filtering can be expressed in terms of reducing alone:

··  `` const mapper = transformerFn => {{c2::(acc, item)}} => acc.{{c1::concat}}( transformerFn(item) ); `` <br>
··  `` const incrementer = mapper(x => x+1); `` <br>
··  `` reduce( incrementer, [], [1,2,3] );//-> [2,3,4] `` <br>

··  `` const filterer = {{c3::testFn}} => (acc, item) => testFn(item) ? acc.concat( {{c4::item}} ) : acc; `` <br>
··  `` const falsyRemover = filterer(x=>!!x); `` <br>
··  `` reduce( falsyRemover, [], [1,null,2,3])//-> [1,2,3] `` <br>

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 06

We can go even further. For example, we can combine filtering and mapping:

··  `` const mapperfilterer = (transformerFn, testFn => (acc, item) => { `` <br>
····  `` let transformedItem = {{c2::transformerFn}}(item, acc); `` <br>
····  `` return testFn(transformedItem, acc) ? acc.concat( transformedItem ) : acc; `` <br>
··  `` }; `` <br>
··  `` const incrementThenFilterLessThenThree = mapperfilterer({{c1::x=>x+1, x=>x<3}}); `` <br>
··  `` [1,1,2,3].reduce(incrementThenFilterLessThenThree, []);//-> [2,2] `` <br>

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 07

But what if we want to filter the original values, not the transformed ones?

··  `` const mapping = transformFn => {{c1::resultifierFn}} =>  `` <br>
····  `` (acc, item) => resultifierFn(acc, transformFn(item, acc)); `` <br>
··  `` const filtering = testFn => resultifierFn => (acc, item) =>  `` <br>
····  `` testFn(item, acc) ? resultifierFn(acc, item) : acc; `` <br>
··  `` const concat = (array, value) => array.concat([value]);//build up a final result `` <br>
··  `` const sum = (x, y) => x+y; `` <br>
··  `` reduce( filtering(x=>x>1)({{c2::concat}}), [], [1,2,3]);//-> [2,3] `` <br>
··  `` reduce( mapping(x=>x+1)(sum), 0, [1,2,3]);//-> 2+3+4 = 9 `` <br>

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 08

Note, resultifiers `sum` and `concat` take {{c1::two arguments}} and then return a single result. This is exactly what we expect from {{c2::reducing}} functions. 

··  `` reduce( concat, [], [1,2,3]);//-> [1,2,3] `` <br>
··  `` reduce( sum, 0, [1,2,3]);//-> 1+2+3 = 6 `` <br>

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 09

Interestingly, `mapping` and `filtering` also return reducing functions ultimately:

··  `` {{c1::(acc, item)}} => resultifierFn(acc, transformFn(item, acc)); `` <br>

··  `` const mapping = transformFn => {{c1::resultifierFn}} =>  `` <br>
····  `` (acc, item) => resultifierFn(acc, transformFn(item, acc)); `` <br>

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 10

In summary, they first choose a {{c1::mapping/filtering}} logic (transformFn), then they accep a {{c2::reducing}} function (sum, concat), then they wrap it somehow and return a new {{c3::reducing}} function. It is like this:

··  `` (result->a->result:a) -> (result->b->result:ab) `` <br>

In a way, we {{c4::transform}} one sort of reducing function (sum, concat) into another one. We transform {{c5::reduces}}. That is we transduce.

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 11

If the inputs and outputs of a transducer are the same sort of thing ({{c1::reducing function}}), we can take the output of any one and use it as input to another one. 

That is, transducers {{c2::compose}}. Thus, we can construct map and filter as we please, in any order we wish.


%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 12

··  `` //types of transformations `` <br>
··  `` const mapping = {{c1::transformFn}} => reducingFn =>  `` <br>
····  `` (acc, item) => reducingFn(acc, transformFn(item, acc)); `` <br>
····  ``  `` <br>
··  `` const filtering = testFn => reducingFn => (acc, item) =>  `` <br>
····  `` testFn(item, acc) ? reducingFn(acc, item) : acc; `` <br>

··  `` //defining particular mapping/filtering operations `` <br>
··  `` const divideByThree = {{c2::mapping}}(x=>x/3);// returns a transducer `` <br>
··  `` const keepOnlyIntegers = filtering({{c3::x => x % 1 === 0}});//returns a transducer `` <br>

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 13

··  `` const divBy3andOnlyIntergers = compose(divideByThree, keepOnlyIntegers); `` <br>
··  `` [3,4,9,13,14,12].{{c1::reduce}}( divBy3andOnlyIntergers({{c2::concat}}), []); `` <br>
··  `` //-> [ 1, 3, 4 ] `` <br>

··  `` //a transducer (composed or not) + a reducing function = a reducing function `` <br>
··  `` const divBy3andOnlyIntergersAndSum = compose(divideByThree, keepOnlyIntegers)({{c3::sum}}); `` <br>

··  `` reduce(divBy3andOnlyIntergersAndSum, 0, [3,4,9,13,14,12]); `` <br>
··  `` //-> (1+3+4) = 8 `` <br>

%

%

clozeq

---

### Article: Everything Reduced: Transducers in Javascript - Drew Tipson 14

Order of Composition: transducers compose left-to-right like lenses. 

Why? What do we pass into composed chain of transducers? A reducing function like `concat`

The rightmost function receives this `concat`. But it then returns {{c1::a function}}, not a value. So, next link in our chain, gets that reducing function. 

When the final reduce operation runs and is handed actual values (accumulator and item), the {{c2::outermost wrapper}} is the last reducing function. So that operation runs first. 

%

%

clozeq

---

## Article: Composing lenses in Ramda 01

··  `` import { lensPath, view } from 'ramda'; `` <br>

··  `` const complexObject = { level1: { level2: { prop1: 1, prop2: 2 } } }; `` <br>
··  `` const prop1Lens = lensPath({{c1::['level1', 'level2', 'prop1']}}); `` <br>

··  `` console.assert(view(prop1Lens, complexObject) === 1); `` <br>

%

%

clozeq

---

### Article: Easier lenses in JavaScript with reshape-ramda 01

··  `` /* Creates lens `` <br>
··  `` from anything implementing: `` <br>
····  `` { a: any, b: { c: any } } `` <br>
··  `` to: `` <br>
····  `` { x: any, y: any } `` <br>
··  `` */ `` <br>
··  `` lensFromPattern({ `` <br>
····  `` {{c1::a: 'x'}}, `` <br>
····  `` b: { `` <br>
······  `` {{c2::c: 'y'}}, `` <br>
····  `` }, `` <br>
··  `` }) `` <br>

%

%

clozeq

---

### Article: Easier lenses in JavaScript with reshape-ramda 02

Ways to make lenses:

- {{c1::`lens`}}
- `lensIndex` and `lensPath`
- `lensPath`: 
- `compose`

%

%

clozeq

---

### Article: Using functional lenses to modify objects by example - Gleb Bahmutov 01

··  `` var tomato = { `` <br>
····  `` firstName: '  Tomato ', `` <br>
····  `` data: {elapsed: 100, remaining: 1400}, `` <br>
····  `` id:123 `` <br>
··  `` } `` <br>
··  `` var transformations = { `` <br>
····  `` firstName: {{c1::R.trim}}, `` <br>
····  `` lastName: R.trim, // Will not get invoked. `` <br>
····  `` data: {elapsed: {{c2::R.add(1)}}, remaining: R.add(-1)} `` <br>
··  `` } `` <br>
··  `` R.{{c3::pick}}(['firstName'], R.evolve(transformations, tomato)) `` <br>
··  `` //=> {firstName: 'Tomato'} `` <br>

%

%

clozeq

---

