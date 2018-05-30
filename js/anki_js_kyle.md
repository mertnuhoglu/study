
## kyle types: 01

7 built-in types: 

`null {{c1::undefined}} boolean number string {{c2::object}} symbol`

%

%

clozeq

---

## kyle types: 02

··  `` typeof undefined === {{c1::"undefined"}} `` <br>
··  `` typeof {life: 42} === {{c2:: "object"}} `` <br>
··  `` typeof Symbol() === "symbol" `` <br>

%

%

clozeq

---

## kyle types: 03

`null` is special and buggy:

··  `` typeof null === "object" `` <br>

Testing `null` value:

··  `` var a = null `` <br>
··  `` ({{c1::!a}} && typeof a === "object"); // true `` <br>

%

%

clozeq

---

## kyle types: 04

··  `` typeof function a() { /* ... */ } === {{c1:: "function"}} `` <br>


%

%

clozeq

---

## kyle types: 05

··  `` function a(b,c) { `` <br>
····  `` // ... `` <br>
··  `` } `` <br>
··  `` a.length; // {{c1::2}} `` <br>

%

%

clozeq

---

## kyle types: 06

··  `` typeof [1,2,3] ==={{c1:: "object"}} `` <br>

%

%

clozeq

---

## kyle types: 07

Variables don't have types. {{c1::Values}} have types.

Variables can hold {{c2::any value}}.

%

%

clozeq

---

## kyle types: 08

JS doesn't have {{c1::"type enforcement"}} that is a variable doesn't always hold values of the same initial type.

··  `` var a = 42 `` <br>
··  `` typeof a; // "number" `` <br>
··  `` a = true `` <br>
··  `` typeof a; //{{c2:: "boolean"}} `` <br>

%

%

clozeq

---

## kyle types: 09

··  `` var a; `` <br>
··  `` a; // {{c1::undefined}} `` <br>
··  `` b; // {{c2::ReferenceError}}: b is not defined `` <br>

%

%

clozeq

---

## kyle types: 10

Note, "undefined" is different than "is not defined"

But `typeof` doesn't distinguish these two cases:

··  `` var a `` <br>
··  `` typeof a; // "undefined" `` <br>
··  `` typeof b; //{{c1:: "undefined"}} `` <br>

%

%

clozeq

---

## kyle types: 11

Check whether a variable has been declared and defined:

··  `` if (typeof DEBUG {{c1::!== "undefined"}}) { `` <br>
····  `` console.log("...") `` <br>
··  `` } `` <br>

%

%

clozeq

---

## kyle types: 12

··  `` var a = [ 1, "2", [3] ]; `` <br>

··  `` a.{{c1::length}};   // 3 `` <br>
··  `` a[0] === 1;   // true `` <br>
··  `` a[2][0] === 3;  // true `` <br>

%

%

clozeq

---

## kyle types: 13

You can add values as you like to an array:

··  `` var a = [ ]; `` <br>
··  `` a.length; // 0 `` <br>
··  `` a{{c1::[0]}} = 1; `` <br>
··  `` a.length; // 1 `` <br>

%

%

clozeq

---

## kyle types: 14

It is possible to skip some indexes:

··  `` var a = [ ]; `` <br>

··  `` a[0] = 1; `` <br>
··  `` // no `a[1]` slot set here `` <br>
··  `` a{{c1::[2]}} = [ 3 ]; `` <br>
··  `` a[1];   // {{c2::undefined}} `` <br>
··  `` a.length; // 3 `` <br>

%

%

clozeq

---

## kyle types: 15

Array vs String

Common properties: `length`, `indexOf(..)`, `concat(..)`

··  `` a.{{c1::length}};··········   // 3 `` <br>
··  `` b.length;··········   // 3 `` <br>
··  `` a.{{c2::indexOf}}( "o" );······   // 1 `` <br>
··  `` b.indexOf( "o" );······   // 1 `` <br>
··  `` var c = a.{{c3::concat}}( "bar" );····  // "foobar" `` <br>
··  `` var d = b.concat( ["b","a","r"] );  // ["f","o","o","b","a","r"] `` <br>


%

%

clozeq

---

## kyle types: 16

Note that, strings are immutable, while arrays are mutable.

Many methods that change array contents do modify {{c1::in-place}}.

··  `` c = a.toUpperCase(); `` <br>
··  `` a {{c2::===}} c;  // false `` <br>
··  `` a;····  // "foo" `` <br>
··  `` c;····  // "FOO" `` <br>
··  `` b.{{c3::push}}( "!" ); `` <br>
··  `` b;····  // ["f","O","o","!"] `` <br>


%

%

clozeq

---

## kyle types: 17

We can use non-mutation `array` methods for `string` objects as:

··  `` a.join;··   // undefined `` <br>
··  `` a.map;····  // undefined `` <br>
··  `` var c = Array.{{c1::prototype.join.call}}( a, "-" ); `` <br>
··  `` var d = Array.prototype.{{c2::map}}.call( a, function(v){ `` <br>
····  `` return v.toUpperCase() + "."; `` <br>
··  `` } ).join( "" ); `` <br>
··  `` c;······  // "f-o-o" `` <br>
··  `` d;······  // "F.O.O." `` <br>

%

%

clozeq

---

## kyle types: 18

JS has one numeric type: `number`. It includes integer values, {{c1::decimal}} numbers. 

`number` values can be boxed with `Number` object wrapper. `number` values can use methods in `{{c2::Number.prototype}}`

%

%

clozeq

---

## kyle types: 19

··  `` var a = 42.59; `` <br>
··  `` a.{{c1::toFixed}}( 0 ); // "43" `` <br>
··  `` a.toFixed( 1 ); // "42.6" `` <br>

··  `` var a = 42.59; `` <br>
··  `` a.{{c2::toPrecision}}( 1 ); // "4e+1" `` <br>
··  `` a.toPrecision( 2 ); // "43" `` <br>

%

%

clozeq

---

## kyle types: 20

You can use these methods on `number` literals but be careful with `.` operator

··  `` // invalid syntax: `` <br>
··  `` {{c1::42.}}toFixed( 3 );  // SyntaxError `` <br>

··  `` // these are all valid: `` <br>
··  `` {{c2::(42)}}.toFixed( 3 );  // "42.000" `` <br>
··  `` 0.42.toFixed( 3 );  // "0.420" `` <br>
··  `` 42..toFixed( 3 ); // "42.000" `` <br>
··  `` 42 .toFixed(3); // "42.000" `` <br>

%

%

clozeq

---

## kyle types: 31

Numbers can be specified in exponent form too:

··  `` var onethousand = {{c1::1E3}};··········  // means 1 * 10^3 `` <br>
··  `` var onemilliononehundredthousand = 1.1E6; // means 1.1 * 10^6 `` <br>

%

%

clozeq

---

## kyle types: 32

To compare with tolerance:

··  `` function numbersCloseEnoughToEqual(n1,n2) { `` <br>
····  `` return Math.abs( n1 - n2 ) < {{c1::Number.EPSILON}}; `` <br>
··  `` } `` <br>
··  `` var a = 0.1 + 0.2; `` <br>
··  `` var b = 0.3; `` <br>
··  `` numbersCloseEnoughToEqual( a, b );········  // true `` <br>
··  `` numbersCloseEnoughToEqual( 0.0000001, 0.0000002 );  // false `` <br>

%

%

clozeq

---

## kyle types: 33

Test if the value is an integer

··  `` Number.{{c1::isInteger}}( 42 );   // true `` <br>
··  `` Number.isInteger( 42.{{c2::000}} ); // true `` <br>
··  `` Number.isInteger( 42.3 ); // false `` <br>

%

%

clozeq

---

## kyle types: 34

`null` is a keyword and {{c1::not an identifier}}.

`undefined` is {{c2::an identifier}} (which is wrong actually)

%

%

clozeq

---

## kyle types: 35

Even, in strict mode you can create a variable of name `undefined`

··  `` function foo() { `` <br>
····  `` "use strict"; `` <br>
····  `` {{c1::var undefined}} = 2; `` <br>
····  `` console.log( undefined ); // 2 `` <br>
··  `` } `` <br>
··  `` foo(); `` <br>

%

%

clozeq

---

## kyle types: 36

`undefined` is a built-in identifier that holds the built-in `undefined` value.

Another way to get this value is the `void` operator

The expression `{{c1::void `__}}`` results in `undefined` always. 

%

%

clozeq

---

## kyle types: 37

··  `` function doSomething() { `` <br>
····  `` if (!APP.ready) { `` <br>
······  `` return {{c1::void}} setTimeout( doSomething, 100 ); `` <br>
····  `` } `` <br>
····  `` var result; `` <br>
····  `` return result; `` <br>
··  `` } `` <br>
··  `` if (doSomething()) { `` <br>
····  `` // handle next tasks right away `` <br>
··  `` } `` <br>

%

%

clozeq

---

## kyle types: 38

····  `` if (!APP.ready) { `` <br>
······  `` return {{c1::void}} setTimeout( doSomething, 100 ); `` <br>

Alternative way of doing this:

··  `` if (!APP.ready) { `` <br>
····  `` setTimeout( doSomething, 100 ); `` <br>
····  `` {{c2::return}}; `` <br>
··  `` } `` <br>

%

%

clozeq

---

## kyle types: 39

Any mathematical operation where at least one operand is {{c1::not a number}} results in `NaN` value which means "not a number". Better would be if it was "invalid number"

%

%

clozeq

---

## kyle types: 40

··  `` var a = 2 / "foo";··  // NaN `` <br>

··  `` typeof a === "number";  // {{c1::true}} `` <br>

%

%

clozeq

---

## kyle types: 41

How to test for NaN pre ES6

··  `` var a = 2 / "foo"; `` <br>
··  `` {{c1::isNaN}}( a ); // true `` <br>

%

%

clozeq

---

## kyle types: 42

But `isNaN(..)` has a fatal flaw.

··  `` var a = 2 / "foo"; `` <br>
··  `` var b = "foo"; `` <br>
··  `` a; // NaN `` <br>
··  `` b; // "foo" `` <br>
··  `` window.isNaN( a ); // true `` <br>
··  `` window.isNaN( b ); // {{c1::true}} -- ouch! `` <br>

%

%

clozeq

---

## kyle types: 43

ES6 provides a replacement utility for isNaN: `Number.isNaN(..)`

··  `` // polyfill for pre-ES6 `` <br>
··  `` if (!Number.isNaN) { `` <br>
····  `` Number.isNaN = function(n) { `` <br>
······  `` return ( `` <br>
········  `` typeof n === "number" && `` <br>
········  `` window.isNaN( n ) `` <br>
······  `` ); `` <br>
····  `` }; `` <br>
··  `` } `` <br>
··  `` var a = 2 / "foo"; `` <br>
··  `` var b = "foo"; `` <br>
··  `` {{c1::Number.isNaN}}( a ); // true `` <br>
··  `` Number.isNaN( b ); // false -- phew! `` <br>

%

%

clozeq

---

## kyle types: 44

Another peculiar feature of `NaN` is that it is not equal to itself. It is the only value that is {{c1::not equal}} to itself.

%

%

clozeq

---

## kyle types: 45

··  `` var a = 1 / 0;  // {{c1::Infinity}} `` <br>
··  `` var b = -1 / 0; // {{c2::-Infinity}} `` <br>

%

%

clozeq

---

## kyle types: 46

··  `` var a = Number.MAX_VALUE; // 1.7976931348623157e+308 `` <br>
··  `` a + a;··········  // Infinity `` <br>
··  `` a + Math.pow( 2, 970 );   // {{c1::Infinity}} `` <br>
··  `` a + Math.pow( 2, 969 );   // 1.7976931348623157e+308 `` <br>

%

%

clozeq

---

## kyle types: 47

Js has both a normal zero `0` and negative zero `-0`

··  `` var a = 0 / -3; // {{c1::-0}} `` <br>
··  `` var b = 0 * -3; // -0 `` <br>

%

%

clozeq

---

## kyle types: 48

··  `` var a = 0 / -3; `` <br>
··  `` // (some browser) consoles at least get it right `` <br>
··  `` a;············  // -0 `` <br>
··  `` // but the spec insists on lying to you! `` <br>
··  `` a.toString();····   // {{c1::"0"}} `` <br>
··  `` a + "";········   // "0" `` <br>
··  `` String( a );······  // "0" `` <br>
··  `` // strangely, even JSON gets in on the deception `` <br>
··  `` JSON.stringify( a );··  // "0" `` <br>

%

%

clozeq

---

## kyle types: 49

Interestingly using "string" instead of "number" works correctly with negative Zero:

··  `` +"-0";······  // -0 `` <br>
··  `` Number( "-0" );   // -0 `` <br>
··  `` JSON.parse( {{c1::"-0"}} ); // -0 `` <br>

%

%

clozeq

---

## kyle types: 50

Comparing negative and normal zeros are configured to deceive:

··  `` var a = 0; `` <br>
··  `` var b = 0 / -3; `` <br>
··  `` a == b;   // {{c1::true}} `` <br>

%

%

clozeq

---

## kyle types: 51

To test correctly if a value is negative zero use `{{c1::isNegZero}}()`

··  `` function isNegZero(n) { `` <br>
····  `` n = Number( n ); `` <br>
····  `` return (n === 0) && (1 / n === -Infinity); `` <br>
··  `` } `` <br>
··  `` isNegZero( -0 );··  // true `` <br>
··  `` isNegZero( 0 / -3 );  // true `` <br>
··  `` isNegZero( 0 );··   // false `` <br>

%

%

clozeq

---

