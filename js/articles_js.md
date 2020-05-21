  <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md>

# Beau teaches JavaScript  id=g_10189

  Beau teaches JavaScript  <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10189>
  01-Heap Data Structure (max and min)- Beau teaches JavaScript-dM_JHpfFITs.mp4
  02-Pop ups tutorial - Beau teaches JavaScript-y6P8uvqRYgw.mp4
    alert("message"): void
    confirm("yes or no"): boolean
    parts of window object:
      same as:
        window.alert
        window.confirm
    var person = prompt("enter name:", "default value")
    if (person == null || person == "")
      console.log(..)
  03-Window Object - move, open, close, & size - Beau teaches JavaScript-ZJng8ls8uH0.mp4
    all browsers support it
    all js objects are part of window
      document
      --> implies
      window.document
    js
      window.addEventListener('resize', update)
      var x = window.document.getelementById("demo")
      function update() {
        x.innerHTML = "inner window width: " + window.innerWidth;
      }
      var newWindow = window.open("http://freecodecamp.com", "newWindow", "menubar=true,top=200,left=200")
    html
      <input type="button" value="Close" onclick="newWindow.close()" />
      <a href=".." target="newWindow">My </a>
  04-Trie Data Structure - Beau teaches JavaScript-7XmS8McW_1U.mp4
  05-requestAnimationFrame() - Beau teaches JavaScript-tS6oP1NveoI.mp4
  06-Animation in the DOM - Beau teaches JavaScript-YXZX_6RfHjk.mp4
  07-Mediator Design Pattern - Beau teaches JavaScript-KOVc5o5kURE.mp4
  08-DOM Nodes - Beau teaches JavaScript-BWVoPxob5DU.mp4
    node tree
    top node: Document
    root node: <html>
    js
      var para = document.createElement("p");
      var node = document.createTextNode("hello");
      para.appendChild(node);
      var parent = document.getElementById("div1");
      parent.appendChild(para);
      para.innerHTML = "new text"
      var child = document.getElementById("p1")
      parent.insertBefore(para, child)
  09-addEventListener() - Beau teaches JavaScript-F3odgpghXzY.mp4
    element.addEventListener(event, function)
    js
      var myp = document.getElementById("myp");
      myp.addEventListener("click", function() { myDiv.style.background = "blue" });
    same with onclick: but more constrained
      myp.onclick = function() { myDiv.style.background = "blue" };
  10-Linked List - Beau teaches JavaScript-9YddVVsdG5A.mp4
  11-DOM Events - Beau teaches JavaScript-0fy9TCcX8Uc.mp4
    html
      <h1 onclick="this.innerHTML='Cereal!'">My fav</h1>
      <h1 onclick="changeColor(this)">Change</h1>
    js
    function changeColor(obj) {
      obj.style.color = "blue";
    }
  12-CSS styles in JavaScript (setting and getting) - Beau teaches JavaScript-z_mSgK-6pOQ.mp4
    js
      mydiv.style.background = 'red';
      mydiv.style.cssText = 'background: red;';
      mydiv.setAttribute("style", "background: red;");
    show every style element:
      console.log(mydiv.style);
      // inline styles 
      console.log(window.getComputedStyle(mydiv));
      // inline + css styles
  13-Selecting & Changing Website Elements (DOM manipulation) - Beau teaches JavaScript-eaLKqoB9Fu0.mp4
    js
      var unicyle = document.getElementsByClassName('unicycle');
      var unicyle = div1.getElementsByClassName('unicycle');
      var paragraphs = document.getElementsByTagName('p');
      var query = document.querySelector('.unicycle');
      var query = document.querySelectorAll('.unicycle');
      // returns array of elements
      var query = document.querySelectorAll('.unicycle, #div2');
    js
      var text = "<h1>Hello</h1>"
      div1.innerHTML = text;
      // replaces all div1 content
    note: innerHTML opens up XSS attack
      instead use textContent
      div1.textContent = text;
      // puts raw content
  14-Hash Tables - Beau teaches JavaScript-F95z5Wxd9ks.mp4
  15-Check if a property is in an object - Beau teaches JavaScript-dpTFcPUe2oo.mp4
    var my = {name: 'js'};
    if (my.name) { ... }
    console.log(my.hasOwnProperty('name'))
    // if name is contained directly (not from prototype)
    console.log('name' in my)
  16-Strict Mode — 'use strict' - Beau teaches JavaScript-uqUYNqZx0qY.mp4
  17-Immediately Invoked Function Expression - Beau teaches JavaScript-3cbiZV4H22c.mp4
    IIFE
      runs as soon as it is defined
    js
      (function () {
        console.log("my");
      })();
      (fav = function () {
        console.log("my");
      })();
      fav();
    usecase:
      creating closures so that variable names don't conflict with imported libraries
    ex:
    (function() {
      var a = 3;
    })
    console.log(a); // error
  18-Map data structure & ES6 map object - Beau teaches JavaScript-_1BPrCHcjhs.mp4
  19-Get current URL with JavaScript (and jQuery) - Beau teaches JavaScript-w5whn4iJCLc.mp4
    js
      window.location.protocol
      window.location.host
      window.location.pathname
      window.location.href
    jquery
      $(location).attr('<property>')
  20-Destructuring in ES6 - Beau teaches JavaScript--vR3a11Wzt0.mp4
    var obj = {x: 3, y: 5};
    old way:
      var x = obj.x;
      var y = obj.y;
    new:
      const {x,y} = obj;
      console.log(x);
    or rename
      const {x: a, y: b} = obj;
      console.log(a);
    assign from nested objects
      const a = {
        start: {x: 5, y: 6}
      }
      const {start: {x: startX}} = a;
    assign from arrays
      const [a,,,b] = [1,2,3,4,5];
      console.log(a);
    rest operator
      const [a, ...b] = [1,2,3,4,5];
      console.log(b)
  21-Arrow Functions  - Beau teaches JavaScript-22fyYvxz-do.mp4
    when returning objects, always put into paranthesis:
      var func = () => ({foo: 1});
  22-Binary Search Tree - Traversal & Height - Beau teaches JavaScript-Aagf3RyK3Lw.mp4
  23-Clean Code - Formatting and Comments - Beau teaches JavaScript-HzWf-EeE3uI.mp4
  24-THIS keyword - Beau teaches JavaScript-eOI9GzMfd24.mp4
  25-Strings - [bracket notation] - Beau teaches JavaScript-sPmRfjJdg5Y.mp4
    js
      var name = "ali"
      console.log(name[1])
      console.log(name[name.length - 1])
      name[0] = "q"; // error: string is immutable
  26-Copying Arrays (deep and shallow) - Beau teaches JavaScript-EeZBKv34mm4.mp4
    var o = [true, false]
    slice
      var copy1 = o.slice(0);
    spread
      var copy2 = [...o];
    deep
      var deep = [['free']]
      var shallow = deep.slice(0)
      shallow[0].push['great']
      var copy = JSON.parse(JSON.stringify(deep));
      copy[0].push['awesome']
      console.log(shallow, deep)
  27-for in _ for of - Beau teaches JavaScript-a3KHBqH7njs.mp4
    in: loop through property names
      for (var in obj)
    of: loop through array values
      for (var of obj)
    let obj = {name: "ali"}
    for (key in obj) {
      obj[key]
    }
    for (val obj obj) {
    }
  28-Binary Search Tree - Beau teaches JavaScript-5cU1ILGy6dM.mp4
  29-Clean Code - Testing, Concurrency, & Error Handling - Beau teaches JavaScript-QnLBGxtteV8.mp4
  30-While _ Do While - Beau teaches JavaScript-v9zgD8wjtbw.mp4
  31-String Basics - Beau teaches JavaScript-Vd_Z1bYGrCM.mp4
  32-Random numbers & parseInt - Beau teaches JavaScript--xAJKmjKCUE.mp4
  33-spread operator and rest operator - Beau teaches JavaScript-iLx4ma8ZqvQ.mp4
  34-Objects, part 2 - Beau teaches JavaScript-r6SnMjsLrBk.mp4
  35-Logical operators && TRICKS with short-circuit evaluation - Beau teaches JavaScript-r7v6EIiHfVA.mp4
  36-Queues & Priority Queues - Beau teaches JavaScript-bK7I79hcm08.mp4
  37-Clean Code - SOLID - Beau teaches JavaScript-XzdhzyAukMM.mp4
  38-Arrays - Beau teaches JavaScript-QEZXbRiaY1I.mp4
  39-Classes - Beau teaches JavaScript-bq_jZY6Skto.mp4
  40-Ternary Operator - Beau teaches JavaScript-s4sB1hm73tw.mp4
  41-React Basics - Beau teaches JavaScript-QqLkkBKVDyM.mp4
  42-For Loops - Beau teaches JavaScript-24Wpg6njlYI.mp4
  43-Clean Code - Classes - Beau teaches JavaScript-KcfiBrL2Pq4.mp4
  44-Array Iteration - 8 Methods - Beau teaches JavaScript-Urwzk6ILvPQ.mp4
  45-Closures - Beau teaches JavaScript-1JsJx1x35c0.mp4
  46-Functions - Beau teaches JavaScript-R8SjM4DKK80.mp4
  47-Numbers - Beau teaches JavaScript-nBEBraDJkFg.mp4
  48-== vs === - Beau teaches JavaScript-kVOmc7NK1M0.mp4
  49-Sets (data structure) - Beau teaches JavaScript-wl8u02IdVxo.mp4
  50-Clean Code - Objects - Beau teaches JavaScript-NPtnp0w_mmA.mp4
  51-Symbols - Beau teaches JavaScript-gANDd4l-G5U.mp4
  52-Desktop Notifications - Beau teaches JavaScript-OMXtJ0USM8s.mp4
  53-Variables - Beau teaches JavaScript-le-URjBhevE.mp4
  54-JSON - Beau teaches JavaScript-B-k76DMOj2k.mp4
  55-Data Types - Beau teaches JavaScript-808eYu9B9Yw.mp4
  56-Singleton Design Pattern - Beau teaches JavaScript-bgU7FeiWKzc.mp4
  57-Clean Code - Functions (Part 2) - Beau teaches JavaScript-43YenciicXk.mp4
  58-Stacks (Data Structure) - Beau teaches JavaScript-Gj5qBheGOEo.mp4
  59-Template Literals (ES6) - Beau teaches JavaScript-kj8HU-_P2NU.mp4
  60-Promises - Beau teaches JavaScript-IGYxfTTpoFg.mp4
  61-Switch Statements - Beau teaches JavaScript-fM5qnyasUYI.mp4
  62-Objects - Beau teaches JavaScript-Gp5nnerXETg.mp4
  63-Observer Design Pattern - Beau teaches JavaScript-3PUVr8jFMGg.mp4
  64-Clean Code - Functions (Part 1) - Beau teaches JavaScript-RR_dQ4sBSBM.mp4
  65-Proxies (ES6) - Beau teaches JavaScript-vExLi5bTt3k.mp4
  66-Hoisting - Beau teaches JavaScript-C1PZh_ea-7I.mp4
  67-20 String Methods in 7 Minutes - Beau teaches JavaScript-VRz0nbax0uI.mp4
  68-Null vs Undefined - Beau teaches JavaScript-VwaqJy_clnc.mp4
  69-AJAX - Beau teaches JavaScript-tHRNuBf_8xg.mp4
  70-Module Design Pattern - Beau teaches JavaScript-3pXVHRT-amw.mp4
  71-Clean Code - Variables - Beau teaches JavaScript-b9c5GmmS7ks.mp4
  72-Comparison Operators & If Else - Beau teaches JavaScript-7WkfzokHGqo.mp4
  73-Var vs Const vs Let (ES6) - Beau teaches JavaScript-1mgLWu69ijU.mp4
  74-Common Array Methods - Beau teaches JavaScript-MeZVVxLn26E.mp4
  75-cookies vs localStorage vs sessionStorage - Beau teaches JavaScript-AwicscsvGLg.mp4
   

# JS Patterns and Idioms: Douglas Crockford, Design Patterns id=g_10138

  JS Patterns and Idioms: Douglas Crockford, Design Patterns <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10138>
  Js Design Patterns
    <url:file:///~/Dropbox/mynotes/content/books/js/js_design_patterns_addy_osmani.md>
  const vs object freeze
    http://stackoverflow.com/questions/33124058/object-freeze-vs-const
      const applies to bindings ("variables"). It creates an immutable binding, i.e. you cannot assign a new value to the binding.
      Object.freeze works on values, and more specifically, object values. It makes an object immutable, i.e. you cannot change its properties.
      code
        var ob1 = {
           foo : 1,
            bar : {
                value : 2   
            }
        };
        Object.freeze( ob1 );
        const ob2 = {
           foo : 1,
            bar : {
                value : 2   
            }
        }
        ob1.foo = 4;  // (frozen) ob1.foo not modified
        ob2.foo = 4;  // (const) ob2.foo modified
        ob1.bar.value = 4;  // (frozen) modified, because ob1.bar is nested
        ob2.bar.value = 4;  // (const) modified
        ob1.bar = 4;  // (frozen) not modified, bar is a key of obj1
        ob2.bar = 4;  // (const) modified
        ob1 = {};  // (frozen) ob1 redeclared
        ob2 = {}; // (const) ob2 not redeclared
  The Post JavaScript Apocalypse at Silicon Valley Code Camp 2016-6Fg3Aj9GzNw.mp4
    http controversy: post put patch
      it creates clutter
      KonMari: Marie Kondo 
        how to remove clutter from your home
        how to get rid of stuff
      cost of keeping stuff
        cost of storage
        cost of access
      take something
        does it spark joy?
        programmers love clutter intuitively
      getting rid of clutter in programming is hard
        we enjoy clutter
      ex: ascii
        invented for teletype machines
        tab vs. space
          source of arguments
          tab key: was put into ascii although not usable for teletype
          if i were ascii inventor, i wouldn't put tab
          tab is not worth of arguing
        upper vs lower case 
          argument: case sensitivity
        quoting chars: ' "
    es6: clutters
      let vs. var: get rid of var
      let vs. const: use const
        difference bw const and freeze
      null vs undefined: use undefined
        invented by Hoare
        problem with null
          if you have ref to sth
          and it can be null
          you have to ask is_null() before accessing
        correct implementation:
          an immutable empty object
          const null = Object.freeze(Object.create())
    js: remove impurities
      Date
      Math.random
      delete
      Object.assign
      Array.splice
      Array.sort
        inplace editing
      RegExp.exec
        modifies regex object
      assignment
      var
      let
        const is ok
      for and loops 
        recursion functions better
      user interaction
      network
    es6 features
      generators
        it is a mistake
          confusing behavior
          makes control flow more complicated
        but the idea is worthwile
          generator will yield a function
          it will return a new value every time you call it
        code
          function factory(..) {
            // state variables
            return function generator() {
              // new value
              return value;
            }
        ex   
          function element(array) {
            let i = 0
            return function generator() {
              if (i < array.length) {
                let value = array[i]
                i += 1
                return value
              }
            }
      callbacks
        does the continuation argument go first or last?
          function first(callback, a) { return callback(a)}
          function last(a, callback) { return callback(a)}
        better: continuation first
          because of ellipsis operator
          first(callback, ...a)
      promises
        invented to secured distributed protocol
        it hides us to understand the reality
        better: RQ
        douglascrockford/RQ
          takes requestor function
          composable
      try
        implied function
          code
            try {..}
            finally { // implied function }
          strong and subtle thus bad
        assigned goto
          code
            catch(M m) {..}
            catch(N m) {..}
      syntax
        too much clutter in syntax
          most syntax work against us
          lisp has no syntax at all
        if through ages
          fortran
            if (a.eq.0) a = b
          bcpl
            if a = 0 {a := b;}
          js  
            if (a === 0) a = b
            better
            if (a === 0) {a = b}
          algol68
            if a = 0 then
              a := b
            fi
          next?
            if a = 0
              a : b
            fi
        indexOf
          java
            "abc".indexOf("z") // -1
            how to indicate "not found"
        types
          int
            int32 + int32
            what is the type of result?
              java: int32
              correct: int33 
            idea of type system: 
              protect us from errors
              but here type system is source of error
            0.1 + 0.2 === 0.3
              false
              fault of ieee standart for floating point standard
              every language fails
              dec64
                number = coefficient * 10^exponent
                correct
                when we moved floating point into hardware, this was lost
            0 / 0
              math: undefined
  Douglas Crockford – “The Better Parts” _ .concat() 2015-_EF-FO63MXs.mp4
    ref
      Douglas Crockford - The Better Parts - Forward 2 Web Summit-rhV6hlL_wMc.mp4
    Saint Exupery:
      perfection is attained not when there is nothing more to add, but when there is nothing more to subtract
      this is principle of good parts
      learn as many languages
        js taught me the most
        i made every mistake i can
          first: i didn't bother learning the language before writing
        jslint: find errors in a program
        book: js the good parts
          it is still valid
          because good parts are still good
      js is implemented in 10 days
        mistake: ==
          first type coercion
          ecma said: lets keep it, and make ===
      brendon eich: features are foot guns
        guns to hit your foot
      js: js requires more discipline in order to write good stuff because of flimsiness
        fantasy of infallibility: foot gun
        futility of thoughtfulness: this never works, why bother
        both lead to danger driven development
      difficulty of scheduling
        two times:
          time a: time to write code
          time b: time to make code right
        there is no science to estimate time a
        time b can become infinite
        always take time to code well
      ecmascript 2015:
        proper tail call
          if the last thing:
            calling a function
            compiler instead of returning call return stack
            it can jump
            it can reduce memory consumption
            allows:
              continuation passing style
        ellipsis operator
          much better than argument arrays
        modules
          import and export values
          no need for require(..) stuff
        two new ways to defining variable: let and const
          solves block scope
            js doesn't have block scope
        destructuring
          let {that, other} = something
        WeakMap
          works the way objects should work
          js: keys have to be string
          weak maps allow any value to be key
        Megastring literals
          template strings
            before: quasi literals
          allow white space in regex
            regex expressions:
          use: regulex tool for regex
          ex:
            /Users/mertnuhoglu/Dropbox/public/img/ss-160.png
        fat arrow
        bad parts
          class
            they won't understand how js works
      my lessons: stopped using
        Object.create
          don't use new neither
          problem: this
            if you have this in a method, bound to object
            if you have this in a function, bound to global object
              violates security
          my solution
            let's make this illegal
        null
          stopped using null
          typeof(null) returns object
            wrong
        falsiness
          using null, 0 instead of false
        for
          forEach, map from es5
        forEach
          Object.keys (es5) gives a nice array of keys
        loops
          use only recursive functions
          ex
            function repeat(func) {
              while (func() !== undefined) {
              }
            }
            -->
            function repeat(func) {
              if (func() !== undefined) {
                return repeat(func);
              }
            }
      next language
        one generation to leave goto
          so much arguments
            then everything gone
        one generation to accept objects
          1967 simula
          alan kay: smalltalk 1980
            best designed language
        two generations to accept lambdas
          scheme 1970s
          js: first mainstream lang that supports it
        divide languages
          system languages
            low level stuff
            memory, device
          application languages
            everything else should be written here
            classical school
              you do classification first
                what are they composed of
              this is really hard
                at the beginning you don't know how the system works
                object graphs that don't fit
                  don't compose right
              extends: is come from statement
                dual of goto
                if you make mistake in lower levels
                  you are stuck with it
            prototypical school
              i was a strong advocate
                not anymore
              you don't do taxonomy and then refactoring
              only benefit:
                memory conservation, not worth
              costs
                own properties vs inherited properties
                retroactive heredity
                  you can change heredity
                  no good use for that
                performance inhibiting
            class free
              i am now advocate of class free
              block scope
                code: inner block can see outer
                  { 
                    let a
                    {
                      let b
                      .. a
                      .. b
                    }
                    .. a
                  }
              function scope (closure)
                we can do the same thing with nested functions
                code
                  function green() {
                    let a
                    function yellow() {
                      let b
                      .. a
                      ..b
                    }
                    ..a
                  }
                set diagram
                  /Users/mertnuhoglu/Dropbox/public/img/ss-159.png
                  inner function is an enclosure to the outer
                took long time for mainstream
                  because: inner may survive the outer
                    solution: stop using stack
              now i use objects like that:
                no new Object(), Object.create()
                code
                  function constructor(spec) {
                    let {member} = spec,
                        {other} = other_constructor(spec),
                        method = function() {
                          // member, other, method, spec
                        };
                    return Object.freeze({
                      method,
                      other
                    });
                  }
                spec is an initalization object
                  i don't take multiple params
                    i forget order
                  alternative to: Object.freeze
                    let that = other_constructor(..)
                    that.method = method
                    return that
                i can call x.method without this, bind
                  like a closure
                many constructors can be called inside
                  so we can copy some methods/data
                  multi inheritance
                Object.freeze
                  contains all public members
                  new syntax for object members
                    instead of:
                      Object.freez({
                        method: method,
                        other: other
                      })
                    now:
                      Object.freeze({
                        method, other
                      })
                  freezing solves security problem
                  object containing
                    only methods and frozen
                    only data and behind those objects
                  this is most effective
        Milner's promise:
          will static typing ever be worth the freight?
        functional programming
          async, distributed, concurrent
  'The Better Parts' - Douglas Crockford Tech Talk-vJKDh4UEXhw.mp4
  Which way is forward - Douglas Crockford-6eOhyEKUJko.mp4
    50 years
      1958 Lisp
      1973 ML
      1975 Scheme
      1990 Haskell
      1999 Javascript
    paradigm shift vs. bad idea
      we cannot decide the difference
      we adopt bad ideas
      we reject good ideas
  JavaScript Patterns for 2017  - Scott Allen-hO7mzO83N1Q.mp4
    ES Modules
      v1: IIFE
        (function() {
          "use strict";
        }());
        # two patterns
          IIFE: everything inside
          strict
      es6: iife is gone
        every js file is a module
        no "use strict", this is default
      es6
        export
          function work(..) {..}
          class Person {
            constructor(name) {..}
          export {work, Person}
          export default Person
            # import by default then
        import
          import {Person, Animal} from "./lib"
            from lib.js or lib.ts
          import HumanPerson from "./lib/humans"
            exported by default
          import * as lib from "./lib"
            import everything 
            lib.Person
            namespace
      import creates immutable bindings
        import Person
          this is not variable
          it is binding
        ex
          import {counter} ..
          counter += 1
            syntax error, it is immutable
        ex
          import {counter, increment} ..
          increment()
            this is ok, even counter is updated
      public apis with index.js
        in a directory
          put index.js
          export public functions from here
          like index.py
        this will separate public and package scope
        ex: index.js
          export * from "./humans"
      building modules
        bundle all imported modules into a single file
        webpack, browserify, rollup, closure
        analyzing dependencies and bundling together
      using webpack
        /Users/mertnuhoglu/Dropbox/public/img/ss-156.png
        extensions: what files to look up
        entry: what files to enter
        output
        loaders
        modulesDirectories: where to search for module directories: ex: ../../foo
          what is root?
      webpack config per purpose
        purposes: 
    Arrow syntax
      return an object literal
        # use ({..}) when returning objects
        const result = [1,2,3].map(n => {value: n})
        // doesn't return objects, just undefined
        const result = [1,2,3].map(n => ({value: n}))
        // value:1, value:2 ..
      lexical this reference
        arrow doesn't bind this
        /Users/mertnuhoglu/Dropbox/public/img/ss-157.png
        v1
          const adder = {
            add(x) { this.x }
          }
          this refers to adder object
        v2
          const adder = {
            add: () => { this.x }
          }
          this refers to enclosing scope of adder
      cannot rebind with bind, apply
        /Users/mertnuhoglu/Dropbox/public/img/ss-158.png
    const
      immutable data structure:
        const nums = [1,2,3]
        Object.freeze(nums)
    classes are mostly sugar
      class code
        class Employee {
          constructor(name) {
            this._name = name
          get name() {
            return this._name
          doWork() {
            ...
      prototype code
        let Employee = function(name) {
          this._name = name
        Employee.prototype = {
          doWork: function() {..}
          get name() { .. }
      instance
        new Employee(n)
      some prototype specific code is not usable with classes
        hoisting
          proto:
            const e = new Employee()
            function Employee() {
              this.name = "scott"
        reflective introspection
          proto
            const Human = function() {}
            Human.prototype.doWork = function() {}
            let names = []
            for (const p in new Human()) {
              names.push(p)
            // ["doWork"]
        no need to enforce new
          class Horse { doWork() {} }
          Horse()
          // TypeError
    object spread operator
      with arrays
        const nums = [1,2,3]
        const res = [...nums, 4,5]
      with objects
        const data = {x:1}
        const res = {
          name: ".."
          ...data
        }
    bang bang operator
      const value = 1
      const res = !!value
      // true
      # converts value into bool

# CSS

## BEM Methodology

  Introduction
    http://getbem.com/introduction/
    intro
    Methodologies
    Why BEM over the others
      less confusing than SMACSS
      good structure like OOCSS
    Blocks, Elements and Modifiers
      Block: meaningful on its own
        ex: header, container, menu, checkbox, input
      Element: no standalone meaning
        ex: menu item, list item, checkbox caption
      Modifier: flag on a block or element to change appearance or beahvior
        disabled, highlighted, checked, fixed
    Under the hood
    Benefits
      Modularity
        to dependence on other elements
      Reusability
      Structure
  Why BEM in a nutshell
    https://blog.decaf.de/2015/06/24/why-bem-in-a-nutshell/
    intro 
      inheritance and specificity
      what is so bad about it?
        css inheritance is infinite
          no function scope or closure
          styles will flow down and will never reach a bottom
          there is always context and you are about to touch it
        specificity
          you need to become more specific to win over existing one
          !important
    Modularity
      inheritance and specificity don't play nice with modularity
    Web components
      they provide some css scope
    BEM
      why?
        to get best modularity
          avoids inheritance by using unique css classes per element
            ex: .my-component__list-item
          reduces style conflicts by keeping css specificity to minimum
      how?
        unique css classes for all elements
    objectsions
      common way:
        by element type selectors (like p or li)
        by generic classes (like .title or .active)
        you apply styles not only to the element itself
        but to a whole cascading context
        even if context doesn't exist yet
      why not use element type selectors with child combinators?
        nested selectors raise css specificity
          you need to become more specific to win over
          modular contexts require low specificity
        css will be tightly coupled with the html
      nesting is brilliant feature
        with BEM any element can be selected by its unique class
        no need to selector nesting
  Naming
    http://getbem.com/naming/
    intro
      right styleguide can significantly increase 
        development speed
        debugging
      most css is unmaintainable
    Block
      naming
        .block
      html
        <div class="block">...</div>
      css
        .block {}
        no tag name or ids
        no dependency on other blocks/elements
    Element
      parts of a block and have no standalone meaning
      naming
        .block__elem
      html
        <span class="block__elem"><..>
      css
        .block__elem {}
    Modifier
      flags on blocks or elements
      naming
        .block--mod
        .block__elem--mod
        .block--color-black
      html
        an extra class name for a block/element node
        <div class="block block--mod">
        <div class="block block--size-big">
      css
        .block--hidden {}
        .block--mod .block__elem {}
    ex
      <form class="form form--theme-xmas form--simple">
        <input class="form__input" type="text" />
        <input
          class="form__submit form__submit--disabled"
          type="submit" />
      </form>

      
## HTML+CSS Öğreniyoruz by Adem İlter  id=g_10188

  HTML+CSS Öğreniyoruz by Adem İlter  <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10188>
  1-HTML+CSS Öğreniyoruz #1-1AhFCvWS-XE.mp4

## CSS Grid cssgrid

### Learn CSS Grid by scrimba Harald Borgen id=g_10187

  Learn CSS Grid by scrimba Harald Borgen <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10187>
  ref
    <url:file:///~/projects/study/html/cssgrid_01/cssgrid.Rmd>
    https://scrimba.com/p/pWqLHa/cg9PpTb
  01 Course Introduction
    /Users/mertnuhoglu/Dropbox/public/img/ss-292.png
    ex: examples.css
      .wrapper {
        display: grid;
        grid-template-columns: repeat(12, 1fr);
        grid-template-rows: 40px 100px 40px;
      }
      .header {
        grid-column: span 12;
      }
      .menu {
        grid-column: span 4;
      }
      .content {
        grid-column: span 8;
      }
      .footer {
        grid-column: span 12;
      }
    ex: examples.html
      <div class="wrapper">
          <div class="header">HEADER</div>
          <div class="menu">MENU</div>
          <div class="content">CONTENT</div>
          <div class="footer">FOOTER</div>
      </div>
    for mobile phones:
      /Users/mertnuhoglu/Dropbox/public/img/ss-293.png
      examples.css
        @media screen and (max-width: 480px) {
            .header {
                grid-column: span 6;
            }
            .menu {
                grid-row: 1;
                grid-column: span 6;
            }
            .content {
                grid-column: span 12;
            }
        }
  02 Your first grid
    ex: start
      /Users/mertnuhoglu/Dropbox/public/img/ss-294.png
      <html>
          <head>
              <link rel="stylesheet" href="basic.css">
              <style>
                  .container {
                      display: grid;
                  }
              </style>
          </head>
          <body>
              <div class="container">
                  <div>1</div>
                  <div>2</div>
                  <div>3</div>
                  <div>4</div>
                  <div>5</div>
                  <div>6</div>
              </div>
          </body>
      </html>
    ex:             
      .container {
          display: grid;
          grid-template-columns: 100px auto 100px;
          grid-template-rows: 50px 50px;
      }
      # three columns, two rows
      grid-gap: 3px;
  03 Fraction units and repeat
  16 Article Layout
    ex
      article {
          display: grid;
          grid-template-columns: 40px 1fr 40px;
          grid-column-gap: 10px;
      }
      article > * {
          grid-column: 2;
          min-width: 0;
      }
      article > figure {
          grid-column: 1 / -1;
          margin: 20px 0;
      }
      article > .aside {
          grid-column: 3;
          color: #666;
          font-size: 0.8em;
      }
      article > blockquote {
          grid-column: 1 / -1;
          margin: 10px 0;
          color: #666;
          border-left: 10px solid #eee;
          padding-left: 10px;
      }
  grid-template-columns and rows
    .container {
        display: grid;
        grid-template-columns: 100px auto 100px;
        grid-template-rows: 50px 50px;
    grid-template-columns: repeat(auto-fit, 100px);
  sizes:
    100px
    auto
    1fr
  display: grid;
  grid-gap: 3px;
  positioning with grid-column grid-row 
    .header {
      grid-column-start: 1;
      grid-column-end: 3;
    shorthand form:
      grid-column: 1 / 3; 
        # start-end lines
      grid-column: 1 / span 2; 
        # size
      grid-column: 1 / -1; 
        # from end
    grid-row: 1 / 3;
  naming lines
    grid-template-columns: [main-start] 1fr [content-start] 5fr [content-end main-end];
    grid-template-rows: [main-start] 40px [content-start] auto [content-end] 40px [main-end];
    grid-column: main-start / main-end;
    grid-column: main;
    grid-area: content;
  grid-template-areas
    grid-template-areas:
      "m . . h h h h h h h h h"
      "m c c c c c c c c c c c"
      "m f f f f f f f f f f f"
    .header {
      grid-area: h;
  auto-fit minmax auto-fill
    grid-template-columns: repeat(auto-fit, 100px);
    grid-template-columns: repeat(auto-fit, minmax(100px, 1fr));
  grid-auto-rows flow
    grid-auto-rows: 100px;
    grid-auto-flow: dense;
  justify-content align
    justify-content: end;
    start center end
    space-evenly space-between space-around
    align-content: end;

## Flexbox css

  display: flex;
  align-self margin-left
    to move a specific element:
    align-self: flex-start;
      puts it to top
    margin-left: auto;
      justifies it to right

# build and automation tools: bower, yarn, gulp, grunt, yo, package.json, npx, npm

  npx
    Introducing npx: an npm package runner
      https://medium.com/@maybekatz/introducing-npx-an-npm-package-runner-55f7d4bd282b
        npm: to manage dependencies hosted on registry
        npx: to manage CLI tools and executables hosted on registry
        it simplifies:
          using locally-installed tools without npm run-script
            before: mocha, grunt were installed globally
            now: they are installed as project-local "devDependencies" in package.json
            downside: how to invoke them globally?
            npx solution:
              npx mocha --> local mocha
              mocha --> global mocha
          executing one-off commands
            npx cowsay-hello
              runs once doesn't pollute global libraries
          run commands with different nodejs versions
            npx node@6 -v
            npx -p node@7 -v
          share gist scripts
            npx https://gist.../...
      https://www.futurehosting.com/blog/npx-makes-life-easier-for-node-developers-plus-node-vulnerability-news/
        problem with old way:
          npm installs either to local or global
          when global?  
            to use it everywhere
          but:
            pollutes global namespace
            how to use local one still?
        npx allows
          using local package, even if global exists
            npx mocha # uses local
            mocha # uses global
  npm package.json
    using npm installed cli programs globally
      add npm/bin path to $PATH
      ex:
        where npm
        export PATH =$PATH:/Users/mertnuhoglu/.nvm/versions/node/v7.7.4/bin/
    npm package.json doc
      https://docs.npmjs.com/files/package.json
      name
      version
        name+version = unique identifier 
      description
        used in `npm search`
      keywords
        for npm search
      homepage
        url
      bugs
        url to issue tracker or email
        used by `npm bugs`
      license
        use SPDX license identifier
      author contributors
        person
          name, email, url, email
      files
        aray of files
        .npmignore
        always included
          package.json 
          README
          CHANGES
          LICENSE
          NOTICE
          main field
      main
        module ID as primary entry point
      bin
        ex
          {"bin": { "myapp": "./cli.js" } }
          then cli.js is symlink to /usr/local/bin/myapp
        use shebang
          #!/usr/bin/env node
      man
        "man" : "./man/doc.1
      directories
      repository
        git repo
        npm docs uses
      scripts
        npm-scripts
      config
        configuration parameters
          { "name" : "foo"
          , "config" : { "port" : "8080" } }
          then start command:
            npm config set foo:port 8001
      dependencies
        semver: specifying verison ranges
        ex
          { "dependencies" :
            { "foo" : "1.0.0 - 2.9999.9999"
            , "bar" : ">=1.0.2 <2.1.2"
        urls as dependencies
        local paths
          "bar": "file:../foo/bar"
      devDependencies
        for transpiler, tests
      engines
        node versions supported
      os
        supported os
    npm-scripts
      https://docs.npmjs.com/misc/scripts
      for following scripts
        npm install
          prepublish publish .. 
          prepare
          preinstall, install...
          ...
        npm test
          pretest, test, ..
        npm stop
          prestop, stop, ...
        npm restart
          ..
      use cases
        prepublish
          compile coffeescript into js
          create minified
      default values
        "start": "node server.js"
        "install": "node-gyp rebuild"
    package script manager for npm
      https://corysimmons.com/writings/2016/node/introducing-package-script-manager
      use rollup instead of webpack
  bower
    52 - Egghead.io - Bower - intro_to_bower.mp4
      installing
        bower install angular
        bower install angular#1.2.0-rc.1
        bower init
        # builds bower.json
        bower install
      bower packages
        bower-angular
          has only min files
      register new bower package
        bower register something github_repo
      search
        bower search angular
      write into index.html script tags
        grunt bowerInstall
        # after bower install
  grunt
    documentation
      https://gruntjs.com
      task runner
        why
          minification, compilation, unit testing, linting
          configured through Gruntfile
    Getting started
      https://gruntjs.com/getting-started
      install
        npm install -g grunt-cli
      how cli works
        grunt looks for require()
      add grunt and plugins to package.json
        npm install <module> -save-dev
        npm install grunt -save-dev
      wrapper function
        module.exports = function(grunt) {
        };
      task configuration
        grunt.initConfig
        ex
          grunt.initConfig({
            pkg: grunt.file.readJSON('package.json'),
            uglify: {
              options: {
      loading grunt plugins
        grunt.loadNpmTasks('grunt-contrib-uglify');
      custom tasks
        ex
          grunt.registerTask('default', ['uglify']);
    Creating Tasks
      https://gruntjs.com/creating-tasks
      alias tasks
        grunt.registerTask('default', ['jshint', 'qunit', 'concat', 'uglify']);
          runs jshint, qunit in place of default
      Gulp vs Grunt
        http://stackoverflow.com/questions/35062852/npm-vs-bower-vs-browserify-vs-gulp-vs-grunt-vs-webpack
          npm is sufficient
          npm and bower
            package managers
            bower: 
          webpack, browserify
            bundle modules for browser
          webpack-dev-server
            a server that hot deploys your code and refreshes browser
        https://www.keithcirkel.co.uk/why-we-should-stop-using-grunt/
        http://www.hongkiat.com/blog/gulp-vs-grunt/
          grunt: configuration
          gulp: code
            code over configuration
    npm as a build tool
      https://www.keithcirkel.co.uk/how-to-use-npm-as-a-build-tool/
      ex
        package.json
          "scripts": {
            "lint": "jshint **.js",
            "test": "mocha test/"
          }
        npm run lint
          will spawn a shell
          run jshint **.js
        shell environment has node_modules/.bin added to PATH
      check env variables
        "env": "env"
        npm run env
      shortcut scripts 
        npm test, npm start npm stop
        npm run test, ...
      pre and post hooks
        pre- post-
      ex
        "scripts": {
          "lint": "jshint **.js",
          "build": "browserify index.js > myproject.min.js",
          "test": "mocha test/",
          "prepublish": "npm run build # also runs npm run prebuild",    
          "prebuild": "npm run test # also runs npm run pretest",
          "pretest": "npm run lint"
        }
      passing arguments
        ex
          "scripts": {
            "test": "mocha test/",
            "test:xunit": "npm run test -- --reporter xunit"
          }
          npm run test -- x.js
            = mocha test/ x.js
          npm run test:xunit
            = mocha test --reporter xunit
        note: -- prefix extends with custom parameters
      npm config variables
      use script instead of json
        https://github.com/corysimmons/package-script-manager
  Yeoman
    Getting started with Yeoman and generator-webapp-zBt2g9ekiug.mp4
      grunt server
    Yeoman 101 Intro Tutorial-yUFXKhMg5Es.mp4
      yo
        webapp
      yo webapp
      gulp serve
      yo doctor
    Documentation
      Getting Started
        http://yeoman.io/learning/
        generic scaffolding system
        language agnostic
        install
          npm install -g yo
          npm install -g generator-webapp
        basic scaffolding
          yo webapp
          yo webapp --help
          npm home generator-webapp
            go to home page of package
          sub-generators
            additional generators to scaffold smaller parts of a project
            accessed as
              generator:sub-generator
            ex: add a new controller to angular
              yo angular:controller XController
        other yo commands
          yo --help
          yo --generators
          yo doctor
      Writing Your Own Generator
        generator-generator
          to get started with their own generator
        generator: a nodejs module
        setting up
          folder: generator-name
          npm init
            package.json
          npm install --save yeoman-generator
        folder tree 
          yo name -> app generator inside app/
          subgenerators: yo name:subcommand inside subcommand/ 
          "files": ["app", "subcommand"]
        extending generator
          var Generator = require('yeoman-generator')
          module.exports = class extends Generator {}
        adding your own functions
          module.exports = class extends Generator {
            method1() {
              this.log('..')
            }
          }
        running
          create a global module and symlink to a local one
            npm link
          yo name
  Gulp
    Getting Started
      https://github.com/gulpjs/gulp/blob/master/docs/getting-started.md
      install gulp command
        npm install -g gulp-cli
      install gulp in devDependencies
        npm install --save-dev gulp
      create a gulpfile
        gulpfile.js
          var gulp = require('gulp')
          gulp.task('default', function() {
          });
      test it out
        gulp
    API documentation
      https://github.com/gulpjs/gulp/blob/master/docs/API.md
      gulp.src(globs)
        reads globs (files defined in glob syntax) and pipes
        gulp.src('client/templates/*.jade')
          .pipe(jade())
          .pipe(minify())
          .pipe(gulp.dest('build/minified_templates'));
        globs:
          type: String or Array
          Glob or array of globs
          uses glob syntax
            patterns the shell uses: stars
        gulp.src(['client/*.js', '!client/b*.js', 'client/bad.js'])
      gulp.dest(path)
        writes files and reemits all data
        gulp.src('./client/templates/*.jade')
          .pipe(jade())
          .pipe(gulp.dest('./build/templates'))
          .pipe(minify())
          .pipe(gulp.dest('./build/minified_templates'));
      gulp.task(name[,deps][,fn])
        define a task using Orchestrator
        gulp.task('somename', function() {
          // Do stuff
        });
        deps
          type: Array
          tasks to execute before
          gulp.task('mytask', ['array', 'of', 'task', 'names'], function() {
            // Do stuff
          });
        async task support
          can be made async if fn does:
            accept a callback
      gulp.watch(glob [, opts], tasks)
        watch files and do something when a file changes
    node-glob
      https://github.com/isaacs/node-glob
      uses shell pattern
        var glob = require("glob")
        glob("**/*.js", options, function (er, files) {
        })
    Orchestrator
      https://github.com/robrich/orchestrator
      1. Get a reference:
        var Orchestrator = require('orchestrator');
        var orchestrator = new Orchestrator();
      2. Load it up with stuff to do:
        orchestrator.add('thing1', function(){
          // do stuff
        });
        orchestrator.add('thing2', function(){
          // do stuff
        });
      3. Run the tasks:
        orchestrator.start('thing1', 'thing2', function (err) {
          // all done
        });
  yarn
    documentation
      https://github.com/yarnpkg/yarn
      features 
        fast: caches downloads
        reliable: works exactly same way on any system
        secure: checksum
        offline mode
        deterministic: dependencies work same exactly way
      installation
        npm install -g yarn
      usage
        yarn init
          start a new project
        yarn add package@[version]
          add dependency
        yarn upgrade package
        yarn remove package
        yarn install
          install all dependencies
  How JavaScript bundlers work
    https://medium.com/@gimenete/how-javascript-bundlers-work-1fc0d0caf2da
    what is js bundler?
      bundles your code and dependencies in one js file
    popular ones: browserify, webpack
    why?
      there was no require, or import before
      how to import then?
        through global variables
        put lots of script tags
          what about dependency order?
            <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
            <script src="/js/foo.js"></script>
            <script src="/js/bar.js"></script>
    alternatives
      nodejs: own modules system
        require() function
      commonjs: exports() 
      how to do it in frontend?
        <script>
        var $ = require('jquery')
        var foo = require('./js/foo')
      but: require() is synchronous
        so we need to put everything in one file to have available in memory
        bad for performance
  chrome
    30 Chrome DevTools Tips-UURZFzk92bU.mp4
      chrome canary
        experimental dev version
      chrome://flags
        devtools
        > settings > experiments
      elements
        change tags live
        element > right > scroll into view
        element > h: show/hide
        where does the border style come from?
          elements > css > computed
          click border > click arrow
        which js action is triggered?
          page element > inspect > element > right > break on > attributes modifications
          /Users/mertnuhoglu/Dropbox/public/img/ss-150.png
        debug hover state
          element > styles > :hov > hover
          /Users/mertnuhoglu/Dropbox/public/img/ss-151.png
        color palette
          styles > color > click
          shift click: changes color encoding format
          option
            preloaded color palettes
      sources
        > pretty print 
        > dom breakpoints
      network
        disable cache: always keep selected
        capture screenshots 
          you see what comes in which order
          /Users/mertnuhoglu/Dropbox/public/img/ss-152.png
        device simulation
          2017-03-07_14-29-40.jpg
        network throttling
          farklı hızlarda nasıl yüklenyior
        right click column headings
          sort by domain/cookies ...
      sources
        js editor
        left pane: top level domains of sources
          changes live
        to make changes persistent
          drag drop finder folder 
          file > right > map to network source
          edit in any editor
        what is being repainted as you scroll, move?

# tools: module, test, css, chrome

  Modernizr
    https://en.wikipedia.org/wiki/Modernizr
      checks whether user's browser implements a css/html5 feature
      ex
        elem = document.getElementById('result');
        if ( Modernizr.websockets ) {
          elem.innerHTML = 'Your browser supports WebSockets.';
                            alert("Your browser supports WebSockets");
        } else {
          elem.innerHTML ='Your browser does not support WebSockets.' ;
        }
      ex: css
        .wsno,
        .wsyes { display: none; }
        /* Modernizr will add one of the following classes to the HTML element based on
                       whether or not WebSockets is supported by the user's browser. */
        .no-websockets .wsno,
        .websockets .wsyes { display: block; }
  RequireJS, AMD, CommonJS
    http://stackoverflow.com/questions/16521471/relation-between-commonjs-amd-and-requirejs
      RequireJS implements AMD API
      CommonJS: uses exports object
        ex
          // someModule.js
          exports.doSomething = function() { return "foo"; };
          //otherModule.js
          var someModule = require('someModule'); // in the vein of node    
          exports.doSomethingElse = function() { return someModule.doSomething() + "bar"; };
        nodejs is an implementation of commonjs
        not designed for browsers
      AMD
        more suited for browser
        supports asynchronous loading
      Browserify
        let you use CommonJS in browser
  npm semver: semantic versioner for npm
    https://docs.npmjs.com/misc/semver
    usage
      npm install semver
    operators
      < <= > >= =
    ex
      >=1.2.7 <1.3.0
      1.2.7 || >=1.2.9 <2.0.0
    prerelease tags
  browsersync dev server
    documentation
      https://www.browsersync.io
      aynı eylemleri birden çok browserda yapmayı sağlar
      hot deploy değişen html, js kodları için
    next
  chokidar cli: file watch
    https://github.com/kimmobrunfeldt/chokidar-cli
  rollup.js
    documentation
      https://rollupjs.org
      similar to browserify, webpack
      creates a bundles
      files written in es6 module format
      faster and smaller bundling
        tree-shaking
          only the code we need is included in bundle
  browserify
    https://github.com/substack/browserify-handbook
      nedir?
        npm normalde backend, js ise frontend için.
        fakat aslında npm ile yapılan her şey frontendde çalışabilir
        ancak bunun için js kodlarını yükleme mekanizmasını değiştirmek gerekiyor
        browserify bunu sağlıyor
      require
      node packaged modules
        require
          require() for loading code
          let module1 = require("some_file.js")
          module1.f()
        exports
          form default:
            module.exports = function (a) {..}
            # use it in another place
            var foo = require("./foo.js")
            foo(5)
          form with name:
            exports.beep = function (a) {..}
            module.exports.beep = function (a) {..}
            # use
            foo.beep(5)
        bundling for browser
          in node running:
            node file.js
          in browserify:
            browserify file.js > bundle.js
          bundle.js contains all js that file.js needs to work
          put it into <script> tag just before </body>
              <script src="bundle.js">
            </body>
      watchify
        watchify instead of browserify
          to write bundle at each change
        npm run watch
        {
          "build": "browserify browser.js -o static/bundle.js",
          "watch": "watchify browser.js -o static/bundle.js --debug --verbose",
        }
          

# angular js

  angularjs fundamentals - egghead
    01-egghead-angularjs-building-an-angular-app-eggly-introduction.mp4
    02-egghead-angularjs-building-an-angular-app-bootstrapping.mp4
      01
        <html ng-app>
          <div ng-init="hello='world'">
            <h1>{{hello}}</h1>
      02
        <input type="text" ng-model="hello">
        --> {{hello}}
    03-egghead-angularjs-building-an-angular-app-controllers.mp4
      module
        to group functionality
      01
        <html ng-app="Eggly">
          # looks for module called Eggly
        <script src="app/eggly-app.start.js">
        # eggly-app.start.js
          angular.module('Eggly', [ # dependencies 
          ])
          .controller('MainCtrl', function($scope) {
            $scope.hello = 'world';
          })
          ;
        <body ng-controller="MainCtrl">
          <h1>{{hello}}</h1>
      02
        # create categories, bookmarks data in Eggly module MainCtrl controller
          .controller('MainCtrl', function($scope) {
            $scope.categories = [
              {"id": 0, "name": "Development"},
              {"id": 1, "name": "Design"},
            ];
            $scope.bookmarks = [ id, title, url, category... ]
          })
        # index.html
          <ul ..>
            <li ng-repeat="c in categories">
              <a href="#">{{c.name}}
            <div ng-repeat="b in bookmarks">
              <a href="{{b.url}}">{{b.title}}</a>
    04-egghead-angularjs-building-an-angular-app-filters.mp4
      01
        # eggly-app.start.js
          $scope.currentCategory = null;
          function setCurrentCategory(c) {
            $scope.currentCategory = c;
          }
          $scope.setCurrentCategory = setCurrentCategory;
        # index.html
          <li ng-repeat="c in categories">
            <a href="#" ng-click="setCurrentCategory(c)>{{c.name}}
          <div ng-repeat="b in bookmarks | filter:{category:currentCategory.name}">
            <a href="{{b.url}}">{{b.title}}</a>
      02
        # index.html
          <a ng-click="setCurrentCategory(null)"> # logo
      03
        # eggly-app
          function isCurrentCategory(c) {
            return $scoppe.currentCategory !== null && category.name === $scope.currentCategory.name
          }
          $scope.isCurrentCategory = isCurrentCategory
        # index.html
          <li ng-repeat="c in categories" ng-class="{'active':isCurrentCategory(c)}">
    05-egghead-angularjs-building-an-angular-app-simple-states.mp4
      01
        # eggly
          function startCreating, cancelCreating, startEditing, cancelEditing, shouldShowCreating, shouldShowEditing
          /Users/mertnuhoglu/Dropbox/public/img/ss-136.png
        # index.html
          <div ng-repeat="b in bookmarks | filter:{category:currentCategory.name}">
            <button ng-click="startEditing();">
            <a href="{{b.url}}">{{b.title}}</a>
          <div ng-if="shouldShowCreating()" ng-click="startCreating();>..
          <div ng-if="shouldShowEditing()">..
    06-egghead-angularjs-building-an-angular-app-add-a-bookmark-with-ng-submit-and-ng-model.mp4
      adding a form
      01
        # index.html
          <form ng-show="isCreating" ng-submit="createBookmark(newBookmark)"
          # newBookmark comes from form elements
          <input ng-model="newBookmark.title">
          <input ng-model="newBookmark.url">
          <button ng-click="cancelCreating()">
        # eggly.js
          function resetCreateForm() {
            $scope.newBookmark = { title: '', url: '', category: $scope.currentCategory };
          }
          function createBookmark(b) {
            b.id = $scope.bookmarks.length;
            $scope.bookmarks.push(bookmark);
            resetCreateForm();
          }
          $scope.createBookmark = createBookmark
  Creating AngularJS project in WebStorm-nebg6X1El2g.mp4
  Using WebStorm for Building Angular Apps-upgjCMHGpwo.mp4
    Webstorm configuration
      hide all buttons
        View > Toolbar ...
      find action command #+A
      stretch to left right #+ok
        first select a window
      Prefs > Editor > Editor Tabs > Limit = 1
      Recent Files  #e
      Recently edited files #+e
      Hide all windows #+F12
      Prefs > Fonts > Enable font ligatures
        => --> ⇒ 
      Terminal  !F12
      Plugin > AceJump
        hızlı bir şekilde bir yere zıplamak
        #; ^;
  angularjs architecture - egghead
    01-egghead-angularjs-angularjs-architecture-series-introduction.mp4
    02
  next
    https://medium.com/google-developer-experts/angular-introduction-to-reactive-extensions-rxjs-a86a7430a61f#.gy5er28ih
    https://docs.angularjs.org/tutorial
    https://docs.angularjs.org/tutorial/step_00
    https://www.w3schools.com/angular/
    https://thinkster.io/a-better-way-to-learn-angularjs
    https://www.quora.com/What-is-the-best-way-to-learn-AngularJS-2
    http://www.ng-newsletter.com/posts/how-to-learn-angular.html
        
  @mine-angularjs
    ng-app ng-init ng-model
      01
        <html ng-app>
          <div ng-init="hello='world'">
            <h1>{{hello}}</h1>
      02
        <input type="text" ng-model="hello">
        --> {{hello}}
    

## bootstrap twitter bootstrap id=g_10186

  bootstrap twitter bootstrap <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10186>
  official documentation 3.3
    structures of documentations
      Getting Started
        https://getbootstrap.com/docs/3.3/getting-started/
        Download
          Bootstrap CDN
          Install with npm
        What's included
          Precompile Bootstrap
          Compiling CSS and JS
        Basic template
          start with basic html template
        Examples
          Using the framework
            Starter template
            Bootstrap theme
              more visual
            Grids
              examples of grid layouts
            Jumbotron
              with navbar and basic grid columns
            Narrow jumbotron
          Navbars in action
            Navbar
              basic template
            Static top navbar
            Fixed navbar
          Custom components
            Cover
              one-page template
            Carousel
            Blog
              two column blog layout
            Dashboard
              admin dashboard
              fixed sidebar and navbar
            Sign-in page
            Justified nav
            Sticky footer
              fotter at bottom
            Sticky footer with navbar
      CSS
        https://getbootstrap.com/docs/3.3/css/
        Overview
          HTML5 doctype
            <!DOCTYPE html>
            <html lang="en">
          Mobile first
          Typography and links
            basic global display params
            background-color: #fff;
            ...
          Containers
            bs requires a containing element to wrap contents and grid system
            opt
              <div class="container">
                # fixed width 
              <div class="container-fluid">
                # spans entire width
        Grid system
          what is
            12 columns
            predefined classes
            mixins for more semantic layouts
          Introduction
            .row within .container
            content within columns
            predefined classes: .col-xs-4
            gutters: gaps between column content via `padding`
          Grid options
            small devices: .col-xs-
            small devices tablets: .col-sm-
            medium devices: .col-md-
            large: .col-lg-
        Typography
          Headings
          Body copy
          Inline text elements
            Marked text
              for highlighting
              <mark>
          Alignment classes
            .text-left
          Transformation classes
            text capitalization
            .text-lowercase
        Tables
          Basic example
            add .table to <table>
          Striped rows
            .table-striped 
          Bordered table
            .table-bordered
          Hover rows
            .table-hover
          Responsive tables
            .table-responsive
              scroll horizontally on phones
        Forms
          Basic Examples
            width: 100%; by default
            wrap in .form-group
            ex:
              <form>
                <div class="form-group">
                  <label for="exampleInputEmail1">Email address</label>
                  <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                </div>
          Inline Form 
            .form-inline
            /Users/mertnuhoglu/Dropbox/public/img/ss-277.png
          Horizontal form
            like rows
            ex
              <form class="form-horizontal">
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                  <div class="col-sm-10">
                    <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                  </div>
          Supported controls
            Inputs
              text, password, datetime, datetime-local, number, email, ...
            Selects
              .form-control
              <select multiple class="form-control">
                <option>1<...>
          Static control
            static text next to a form label
            /Users/mertnuhoglu/Dropbox/public/img/ss-278.png
            ex
              <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                  <p class="form-control-static">email@example.com</p>
                </div>
          Disabled state
            disabled attribute
            <input ... disabled>
        Buttons
          Button tags
            <a> <button> <input> elements
            button types: link button input submit
            <a class="btn btn-default" href="#" role="button">Link</a>
            <button class="btn btn-default" type="submit">Button</button>
            <input class="btn btn-default" type="button" value="Input">
            <input class="btn btn-default" type="submit" value="Submit">
          Options
            styled buttons
            classes: btn-default btn-primary btn-success ...
          Sizes
            .btn-lg .btn-sm .btn-xs
          Active state
          Disabled state
        Images
          Responsive images
            .img-responsive
          Image shapes
            .img-rounded .img-circle .img-thumbnail
        Helper classes
          Contextual colors
            /Users/mertnuhoglu/Dropbox/public/img/ss-279.png
          Contextual backgrounds
            /Users/mertnuhoglu/Dropbox/public/img/ss-280.png
          Close icon
            /Users/mertnuhoglu/Dropbox/public/img/ss-281.png
        Responsive utilities
          Print classes
          Test cases
        Using Less
      Components
        https://getbootstrap.com/docs/3.3/components/
        Glyphicons
        Dropdowns
          for displaying lists of links
          .dropdown
        Button groups
          Basic example
            <div class="btn-group" ...>
            /Users/mertnuhoglu/Dropbox/public/img/ss-282.png
          Button toolbar
            multiple .btn-group elements grouped
            .btn-toolbar
          Sizing
            .btn-group-lg
          Nesting
            for dropdown menus mixed with normal buttons
          Vertical variation
            .btn-group-vertical
        Button dropdowns
          use buttons to trigger dropdown
        Input groups
          /Users/mertnuhoglu/Dropbox/public/img/ss-283.png
        Navs
          Tabs
            <ul class="nav nav-tabs">
            /Users/mertnuhoglu/Dropbox/public/img/ss-284.png
          Pills
            .nav-pills instead of .nav-tabs
        Navbar
          Default navbar
            as navigation headers
            /Users/mertnuhoglu/Dropbox/public/img/ss-285.png
            ex:
              <nav class="navbar">
                <div ..
                  <div class="navbar-header">
                    <button .. class="navbar-toggle"...
        Breadcrumbs
        Pagination
          Deafult pagination
        Labels
          <span class="label"
        Badges
          /Users/mertnuhoglu/Dropbox/public/img/ss-286.png
          <span class="badge">42
        Jumbotron
          key content on page
          <div class="jumbotron"
        Page header
          .page-header
        Thumbnails
          to display grids of images, text
            <a class="thumbnail"
        Alerts
        Progress bars
          image + alongside text
          /Users/mertnuhoglu/Dropbox/public/img/ss-287.png
        List group
          .list-group
          /Users/mertnuhoglu/Dropbox/public/img/ss-288.png
        Panels
          to put element in a box
          Basic example
            .panel
            /Users/mertnuhoglu/Dropbox/public/img/ss-289.png
          With tables
            /Users/mertnuhoglu/Dropbox/public/img/ss-290.png
        Wells
          .well
          Default well
            gives inset effect
            /Users/mertnuhoglu/Dropbox/public/img/ss-291.png
      Javascript
        https://getbootstrap.com/docs/3.3/javascript/
        Overview
          Individual or compiled
            bootstrap plugins: individual *.js files
            or all: bootstrap.js
          Data attributes
            use bs plugins without js
            or use js
          Programmatic API
            use bs plugins through js api
            ex: $('.btn.danger').button('toggle').addClass('fat')
            methods 
              chainable
                returns collection acted upon
              accept optional options object
          No conflict
            name collisions with other UI frameworks
          Events
            ex: 
              show: triggered at the start of event
              shown: triggered on completion of an action
            all events are namespaced
            ex: stop execution of action before it starts
              $('#myModal').on('show.bs.modal', function (e) {
                if (!data) return e.preventDefault() // stops modal from being shown
              })
          Version numbers
            $.fn.tooltip.Constructor.VERSION // => "3.3.7"
          When JavaScript is disabled
            how to gracefully fail when js is disabled
        Transitions
          transition.js
        Modal
          what is
            dialog prompts
          Examples
            static examples
            sections: header, body, set of actions in footer
            .modal
            .modal-header
            ex
              <div class="modal fade" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title">Modal title</h4>
                    </div>
          Sizes
            .modal-lg
          Remove animation
            .fade: appears with animation
          Using the grid system
            nest .row within .modal-body
            ex
              <div class="modal-body">
                <div class="row">
                  <div class="col-md-4">.col-md-4</div>
          Varying modal content based on trigger button
            çok sayıda buton var, aynı modal dialog'u tetikler
            hangi butondan tıklandığı bilgisi data-* attributuna konulur
            ex:
              <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Open modal for @mdo</button>
            ex: js koduyla data-* bilgisi çekilir:
              $('#exampleModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget) // Button that triggered the modal
                var recipient = button.data('whatever') // Extract info from data-* attributes
            Usage
              via data attributes
                use on controller elements like button
                data-toggle="modal"
                data-target="#foo" 
                  to target a specific modal
                ex:
                  <button type="button" data-toggle="modal" data-target="#myModal">Launch modal</button>
              via js
                $('#myModal').modal(options)
          Options
          Methods
            .modal(options)
            .modal('toggle')
            .modal('show')
              manually show modal
          Events
        Dropdown
          what is
            add dropdown menus to anything
              within a navbar
              within pills
          Examples
            via data attributes
              data-toggle="dropdown"
              data-target="#"
                use data-target instead of href="#"
              ex
                <div class="dropdown">
                  <button id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            via js
              $('.dropdown-toggle').dropdown()
          Usage
          Methods
            $().dropdown('toggle')
          Events
        Scrollspy
          what is
            scroll ederken bulunulan konuma göre nav target'ını highlight eder
          Examples
          Usage
            requires 
              nav
              position: relative;
            via data attributes
              data-spy="scroll"
                to element that you spy on
              data-target="#navbar-example"
              ex:
                <body data-spy="scroll" data-target="#navbar-example">
                  ...
                  <div id="navbar-example">
                    <ul class="nav nav-tabs" role="tablist">
            via js
              $('body').scrollspy({ target: '#navbar-example' })
          Methods
          Events
        Tab
          what is
            tab.js
            panes of local content
          Examples
            via markup
              data-toggle="tab"
            ex
              <div>
                <ul class="nav nav-tabs" role="tablist">
                  <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
                  <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
                </ul>
                <div class="tab-content">
                  <div role="tabpanel" class="tab-pane active" id="home">...</div>
                  <div role="tabpanel" class="tab-pane" id="profile">...</div>
          Usage
          Methods
          Events
        Tooltip
          what is
            tooltip.js
            hover over a link to see tooltips
          Examples
          Usage
            markup
              data-toggle="tooltip"
              title="content of tip"
          Methods
            .tooltip('toggle')
          Events
        Popover
          what is
            popver.js
            add small overlays of content
            konuşma balonu gibi
          Examples
          Usage
            markup
              data-toggle="popver"
              title="title of it"
              data-content="content of it"
          Methods
          Events
        Alert
          what is
            alert.js
            alert messages
          Examples
          Usage
            markup
              data-dismiss="alert"
              ex:
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
          Methods
            $().alert()
          Events
        Button
          what is
            button.js
          Examples
            single toggle button
            checkbox/radio
              ex:
                <div class="btn-group" data-toggle="buttons">
                  <label class="btn btn-primary active">
                    <input type="checkbox" autocomplete="off" checked> Checkbox 1 (pre-checked)
          Usage
          Methods
          Events
        Collapse
          what is
            collapse.js
            collapse/expand a pane
          Examples
            accordion
              data-parent="#accordion"
          Usage
            markup
              data-toggle="collapse"
              data-target=""
                what to collapse
          Methods
          Events
        Carousel
          what is
            carousel.js
            slideshow
          Examples
          Usage
            markup
              data-ride="carousel"
              data-slide
            ex
              <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                  <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                  <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                ...
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
          Methods
          Events
        Affix
          what is
          Examples
          Usage
          Methods
          Events
  version 4
    Bootstrap 4: What’s New
      https://medium.com/wdstack/bootstrap-4-whats-new-visual-guide-c84dd81d8387
      Panels are now Cards
        .panel .well -> .card
      fonts are bigger
      bs4 grid
        XL
        LG
        MD
        SM
        XS
    What changed in Bootstrap 4.0 ?
      https://hackernoon.com/what-changed-in-bootstrap-4-0-ca3cbbf4f62f
      less to sass
      flexbox
      font: rem sizing
        <html> tag is reference size
        h1 { font-size: 3 rem; } /* 3 times <html> font size */
    What's New in Bootstrap 4
      https://scotch.io/bar-talk/whats-new-in-bootstrap-4

# ES 6

    ES6 tips and tricks to make your code cleaner, shorter, and easier to read!
      https://medium.freecodecamp.org/make-your-code-cleaner-shorter-and-easier-to-read-es6-tips-and-tricks-afd4ce25977c
      let declaration
      const
      problem with block scoping functions
        problem: inside if statement
      spread operator ...
        feature: it creates a new array or object
          let a = [1,2,3];
          let b = [ ...a ];
          let c = a;
      default parameters
      destructuring
        let {a,c} = {a:1, b:2, c:3};
        assign to a new variable name
          let {x: vehicle} = {x: 'car'};
          let {x: {name: driver}} = {x: {name: 'john'}};
        assigning a value to multiple variables
          let {x: first, x: second} = {x:4};
      object literals and concise parameters
        omit key if it is the same as variable name
          let a = 4, b = 7;
          let concise = {a,b};
          console.log(concise) // {a: 4, b: 7}
      dynamic property names
        let city = 'london_';
        let a = { [city+'population']: 350 };
        a[ city + 'county' ]
      arrow functions
        .map
        .forEach
        .sort
      for ... of loops
        for ... in loops over key/index
        for ... of loops over val
          skips: `let val = a[idx]`
      number literals
        29
        035 // octal
        0o35 // octal
        0x1d // hexa
        0b1111 // binary

# es modules

    ECMAScript modules in browsers id=g_10192
      ECMAScript modules in browsers <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10192>
      https://jakearchibald.com/2017/es-modules-in-browsers/
      ex: <url:/Users/mertnuhoglu/projects/study/js/ecmascript_modules.Rmd#tn=## v01: Basic Example 01>
    ES6 Modules in Browsers
      https://salomvary.com/es6-modules-in-browsers.html
      essentials
        index.html
          <script type="module" src="app.js"></script>
        app.js
          import lib from './lib.js'
          lib.doStuff()
        must: static web server (due to CORS error from file:// urls)
        if authentication enabled, <script crossorigin="use-credentials">
      Using External Dependencies
        how to use external libraries that have older syntax?
        most of them cannot be used with new syntax
        you still need Webpack
        libraries can be used directly from CDN or local node_modules
          but you need to write whole path including node_modules
        ex:
          // Some authors already publish in browser friendly ES6 format.
          import Vue from 'https://cdn.jsdelivr.net/npm/vue@2.5.13/dist/vue.esm.browser.js'
          // Lodash in ES6 format is published under a different name. When
          // using npm, the whole node_modules folder has to be exposed by the
          // static web server.
          import camelCase from './node_modules/lodash-es/camelCase.js'
          // Other libraries are authored in ES6 module format but use bare
          // module paths which does not work in browsers. CDNs like Unpkg can
          // help by expanding the modules.
          import scale from 'https://unpkg.com/d3-scale@1.0.7/index.js?module'
          // Sadly there is no module expansion when installed with npm, this
          // will NOT work.
          import scale from './node_modules/d3-scale/index.js'
    Moving Past RequireJS
      https://benmccormick.org/2015/05/28/moving-past-requirejs/
      What is RequireJS
        module loaders
        3 alternatives
          AMD Async Module Definition
          CommonJS
          ES Modules
      Problems with AMD
        requirejs uses AMD modules
        AMD had benefits over CommonJS previously
          no more 
          less clear syntax
        Syntax comparison
          ex: AMD
            define(['file1', 'file2'], function(Class1, Class2)) {
              let obj = new Class1(),
                obj2 = new Class2();
              return obj.foo(obj2);
            }
          ex: CommonJS
            let Class1 = require('file1'),
              Class2 = require('file2')
              obj = new Class1(),
              obj2 = new Class2();
            module.exports = obj.foo(obj2);
          ex: es6
            import Class1 from 'file1'
            import Class2 from 'file2'
            let obj = new Class1(),
              obj2 = new Class2();
            export default obj.foo(obj2);
          syntax benefits:
            less boilerplate: no need to wrap code in an outer function
              similar to server side languages
            AMD dependencies: 
              1. array of strings
              2. parameters to callback function
        Network Effects
          CommonJS is built into nodejs
            unit testing libraries use it
            very easy to work on node platform
          some node modules work only with CommonJS
          most libraries support AMD through a "Universal" module format
      Alternatives to RequireJS
        3 options on module loading scene:
          Browserify
          Webpack
          RequireJS
        Browserify: module loader on top of node
          uses CommonJS
        Webpack: attempt to unify landscape
          supports AMD, CommonJS, ES6
          handles js, css, other assets, preprocessors
        RequireJS: suffers in comparison to both of them
    CommonJS vs AMD vs RequireJS vs ES6 Modules
      https://medium.com/computed-comparisons/commonjs-vs-amd-vs-requirejs-vs-es6-modules-2e814b114a0b
      before modules: Revealing Module Pattern
        var revealingModule = (function () {
          var privateVar = "Ben Thomas";
          function setNameFn( strName ) {
              privateVar = strName;
          }
          return {
                  setName: setNameFn,
              };
          })();
        revealingModule.setName( "Paul Adams" );
      costs of it:
        no async loading of modules
        cannot import modules programmatically
      CommonJS
        new keywords: require, exports
        ex:
          var obj = require('module_name')
          exports = function() {
            return ...;
          }
      NodeJS
        ex
          var obj = require('module_name')
          modules.exports = function() {
            return ...;
          }
        path relative to node_modules directory
        circular dependencies supported
        con: one file per module
        con: browsers cannot use them without transpiling
          Browserify, Webpack
      AMD and RequireJS
        CommonJS wasn't suited for browsers
        modules loaded in non-blocking manner
        ex:
          <script data-main="scripts/main" src="scripts/require.js">
        data-main: defines initialization
      ES6 modules
        ex:
          main.js
            import {square, diag} from 'lib';
          lib.js
            export function square(x) { return x * x; }
        import statement is not dynamic
          makes static analyzers build tree of dependencies

## webpack

    Using ES6 Modules with Webpack
      http://www.zsoltnagy.eu/using-es6-modules-with-webpack/
      index.html
        <ul class="js-top-transactions">
        </ul>
        <script src="myaccount.dist.js"></script>
      webpack
        npm install -g webpack
        npm install babel-loader
        webpack.config.js
    Webpack Getting Started Tutorial
      https://webpack.js.org/guides/getting-started/
      basic setup
        cli
          mkdir demo
          npm init -y
          npm install --save-dev webpack
        src/index.js
          function component() {
            var element = document.createElement('div');
            element.innerHTML = _.join(['Hello','webpack'], ' ');
            return element;
          }
          document.body.appendChild(component());
        index.html
          <!doctype html>
          <html>
            <head>
              <title>Getting Started</title>
              <script src="https://unpkg.com/lodash@4.16.6"></script>
            </head>
            <body>
              <script src="./src/index.js"></script>
            </body>
          </html>
        implicit dependency:
          <script src="https://unpkg.com/lodash@4.16.6"></script>
      creating a bundle
        cli
          mkdir dist
          npm install --save lodash
        src/index.js
          import _ from 'lodash';
          function component() {
            var element = document.createElement('div');
            element.innerHTML = _.join(['Hello','webpack'], ' ');
            return element;
          }
          document.body.appendChild(component());
        index.html
          <!doctype html>
          <html>
            <head>
              <title>Getting Started</title>
            </head>
            <body>
              <script src="bundle.js"></script>
            </body>
          </html>
        cli
          npx webpack src/index.js --output dist/bundle.js
      how it works
        Webpack transpiles `import` and `export` statements
          other code is not touched
      Using a Configuration
        if more complex setup is needed, use configuration file
        webpack.config.js
          const path = require('path');
          module.exports = {
            entry: './src/index.js',
            output: {
              filename: 'bundle.js',
              path: path.resolve(__dirname, 'dist')
            }
          }
        cli
          npx webpack --config webpack.config.js
      NPM Scripts
        shortcut:
        package.json
          "scripts": {
            "build": "webpack"
          }
        cli
          npm run build
        instead of npx webpack
    Webpack: Asset Management
      https://webpack.js.org/guides/asset-management/
      setup
      Loading CSS
        webpack.config.js
          module: {
            rules: [
              {
                test: /\.css$/,
                use: [
                  'style-loader',
                  'css-loader'
                ]
              }
            ]
          }
        src/style.css
          .hello {
            color: red;
          }
        src.index.js
          import _ from 'lodash';
          import './style.css';
          function component() {
            var element = document.createElement('div');
            element.innerHTML = _.join(['Hello','webpack'], ' ');
            element.classList.add('hello');
            return element;
          }
          document.body.appendChild(component());
        cli
          npm run build
      Loading Images
        cli
          npm install --save-dev file-loader
        webpack.config.js
          module: {
            rules: [
              {
                test: /\.(png|svg|jpg|gif)$/,
                use: [
                  'file-loader'
                ]
              }
            ]
          }
        src/index.js
          import Icon from './icon.png';
          var myIcon = new Image();
          myIcon.src = Icon;
          element.appendChild(myIcon);
        src/style.css
          .hello {
            background: url('./icon.png');
          }
      Loading Data
        cli
          npm install --save-dev csv-loader xml-loader
        webpack.config.js
          module: {
            rules: [
              {
                test: /\.(csv|tsv)$/,
                use: [
                  'csv-loader'
                ]
              },
              {
                test: /\.(xml)$/,
                use: [
                  'xml-loader'
                ]
              }
            ]
          }
        src/index.js
          import Data from './data.xml';
      Global Assets
        file structuring
          /components
            /my-component
              index.jsx
              index.css
              icon.svg

### Webpack: When To Use And Why id=g_10193

Webpack: When To Use And Why <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10193>

https://blog.andrewray.me/webpack-when-to-use-and-why/

#### What is Webpack?

Webpack is 

- a build tool: bundles all files in a dependency graph
- lets you use require() to point to local files
- replaces the file references with URLs in final bundle

#### History of Dependency Graph

1. Early days: implicit dependencies

        <script src="jquery.min.js"></script>

    Con: too slow because of several HTTP requests
    Con: no modular isolation and namespace

2. Build script to concatenate and minify scripts

    ``` 
    // build-script.js
    var scripts = [  
        'jquery.min.js',
        'jquery.some.plugin.js',
        'main.js'
    ].concat().uglify().writeTo('bundle.js');

    // Everything our app needs!
    <script src="bundle.js"></script>  
    ``` 

    Con: relied on the order of concatenated files
    Con: still has global variables

3. CommonJS or ES6 modules

    ``` 
    // version.js
    module.exports = {version: 1.0};

    // app.js
    var config = require('./version.js');
    ``` 

    Con: Browser doesn't support `require()`. We use a build tool like
    browserify.

#### What Does Webpack Do?

Use `require()` on non-js files:

    <img src={ require('../../assets/logo.png') } />  

Webpack configuration:

    loaders: [
      {test: /.png$/, loader: "file-loader"}
    ]

Webpack replaces `require()` with a URL string. It puts `logo.png` into some
local folder such as `dist/`. 

Note that: The final (bundled) js code doesn't have `require('logo.png')` instead it has URL of `logo.png`. 

#### What About Browserify, Grunt, Gulp ...?

Grunt and Gulp don't have dependency graph. Webpack puts static assets and source code in a dependency graph.

Browserify transforms `require()` calls into calls that work in the browser.
It has dependency graph but only for source code. 

So Webpack replaces those three tools. 

#### The Good

Static assets in a dependency graph has benefits:

- Dead asset elimination. Great especially for CSS rules.

- Easier code splitting. Each js file has a specific CSS file that reduces
file sizes a lot.

- You control how assets are processed. Ex: You can base64 encode small files
directly into js.
 
- Stable production deploys. No image missing.

- Hot page reloading. True CSS management. CDN cache busting.

#### The Bad

- Single maintainer

- Mini language in a string: `require("!style!css/bootstrap.less")`

#### Dev Server

"dev server" is a small express app.

#### Stop Programming with Globals

Traditional front end programming relies on global variables. CSS rules exist
in a global namespace. 

Stop being a human compiler. Use a dependency graph.

### What's new in webpack 2 id=g_10194

What's new in webpack 2 <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10194>

https://gist.github.com/sokra/27b24881210b56bbaff7

- Supports ES6 Modules: no need to transform to commonjs

        import { currentPage, readPage } from "./book";

        currentPage === 0;
        readPage();
        currentPage === 1;
        // book.js
        export var currentPage = 0;

        export function readPage() {
          currentPage++;
        }

        export default "This is a book";

- Mixing ES6 With AMD and CommonJS

        // CommonJS consuming ES6 Module
        var book = require("./book");
        
        book.currentPage;
        book.readPage();
        book.default === "This is a book";
        // ES6 Module consuming CommonJS
        import fs from "fs"; // module.exports map to default
        import { readFileSync } from "fs"; // named exports are read from returned object+
        
        typeof fs.readFileSync === "function";
        typeof readFileSync === "function";

### Getting Started with webpack

https://blog.envylabs.com/getting-started-with-webpack-2-ed2b86c68783

Webpack has evolved into a manager for all front end code

Earlier: Gulp, Grunt: One Task runner for HTML, another for CSS etc.

Webpack: gets all HTML, CSS into JS and outputs them all separately but
integrated.

Introducing kyt — Our Web App Configuration Toolkit
  https://open.blogs.nytimes.com/2016/09/13/introducing-kyt-our-web-app-configuration-toolkit/
  Lots of build tools. 
    Con: extensive configuration.
  Common requirements: 
    transpiler, server build, client build, test, style and
    script linting, combining several scripts together
    Matrix of dependencies
  Configuration hell => boilerplates => boilerplate fatigue
    unused code
    brittle and time consuming
  kyt: escape from configuration hell
  how it works
    cli
      dev: runs dev server with live reloading
      build + start: runs code 
      test + lint
      proto: prototyping
  starter-kyt: choose what matters
    Pro: benefits of boilerplates
    Pro: minimizing number of new tools
  kyt is different
Hyperapp + Parcel = 😎
  https://blog.daftcode.pl/hyperapp-parcel-71823bd93f1c
  hyperapp: tiny frontend framework
  parcel: asset bundler
  cons of react + webpack
    webpack: hard to configure
    react: too big
  hyperapp
    1 kb
    tiny react
    uses virtual dom
    state management: inspired by elm
  parcel
    huge speed increase
    all work out of the box
    ex: linking a .sass file, 
      it installed automatically node-sass dependency 
      and transformed file to css
  how to use it?
    ref
      ~/projects/study/js/ex/study_hyperparcel/package.json
    install
      mkdir hyperparcel
      cd $_
      npm init -y
      npm i hyperapp parcel-bundler babel-plugin-transform-react-jsx babel-preset-env
    index.html
      <html>
        <body>
          <script src="./index.js"></script>
        </body>
      </html>
    index.js
      console.log('hello parcel')
    package.json
      "start": "parcel index.html",
      "build": "parcel build index.html --public-url ./"
    run
      npm start
       
Everything You Need To Know About Parcel
  https://medium.freecodecamp.org/all-you-need-to-know-about-parcel-dbe151b70082
  why parcel?
    simplicity: zero configuration
      development server built in with hot module replacement
      fast bundle times
    out of the box support: 
      js css html, file assets
      code splitting
      css preprocessors
      caching
    friendly error logs
  installation
    npm install parcel-bundler --save-dev
    mkdir parcel01 && cd $_ && npm init -y
    touch index.html && touch index.js
    index.html
      <script src="index.js">
    index.js
      document.write("hello")
    package.json
      "start": "parcel index.html"
    npm run start
    http://localhost:1234
  scss
    npm i node-sass && touch styles.scss
    index.js
      import './styles.scss'
    package.json
      "build": "parcel build index.js"
    npm run build
  run specific build path
    parcel build index.js -d build/output

### ParcelJs Official Documentation id=g_10195

ParcelJs Official Documentation <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10195>

https://parceljs.org/getting_started.html

#### Getting Started

    npm install -g parcel-bundler
    npm init -y

Entry point: an html/js file

    parcel index.html

    open http://localhost:1234

    parcel -p <port_number>

#### Assets

##### Javascript

    const dep = require('./path/to/dep')
    import dep from './path/to/dep'

css:

    import './test.css'
    import classNames from './test.css'
    import imageUrl from './test.png'

to inline a file use fs.readFileSync

    import fs from 'fs'
    const string = fs.readFileSync(__dirname + '/test.txt', 'utf8')
    const buffer = fs.readFileSync(__dirname + '/test.png')

##### Css Assets

css files can contain dependencies referenced by `@import` (inlined) or `url()` (rewritten to their output filenames)

    @import './other.css'
    .test {
      background: url('./images/img.png')
    }

scss works the same way:

    npm install node-sass

    import './custom.scss'

##### HTML Files

URLs are extracted and compiled

    <img src="./images/header.png">
    <a href="./other.html">..
    <script src="./index.js">
    
#### Transforms

Built in transformers: Babel, PostCSS, PostHTML

Parcel runs these transform when it finds their configuration files (.babelrc, .postcssrc)

#### Code Splitting

Split code into separate bundles loaded on demand.

Controlled by use of dynamic `import()` that returns a Promise.

    // pages/about.js
    export function render() {..}

    import('./pages/about').then(function (page) {
      page.render()
    })

#### Hot Module Replacement HMR

Two methods:

    module.hot.accept
    module.hot.dispose

#### Production

    parcel build entry.js

Options

    parcel build entry.js --out-dir build/output
      -d
      output directory
    parcel build entry.js --public-url ./
      public url to serve on

#### Recipes

#### API

#### Plugins

Plugins are very simple

Modules that export a single function

Input: Bundler object

    module.exports = function (bundler) {
      bundler.addAssetType('ext', require.resolve('./MyAsset'))
    }

## hyperscript 

### Hyperscript Helpers

https://github.com/ohanhi/hyperscript-helpers

    h('div')
    --->>>
    div()

    h('section#main', mainContents)
    --->>>
    section('#main', mainContents)

hyperscript-helpers:

1. easy to use with ramda, because they are functions
2. you get error if you mistype
3. consistent syntax all times

ex: list of menu items of `{ title: String, id: Number }`

    function attrs(id) {
      return { draggable: "true", "data-id": id };
    }
    ul("#best-menu', items.map( item =>
      li('#item-'+item.id, attrs((item.id), item.title))
    )")
    // JSX
    <ul id="bestest-menu">
      {items.map( item =>
        <li id={"item-"+item.id} {...attrs(item.id)}>{item.title}</li>
      )}
    </ul>

#### How to use
  
    npm install hyperscript-helpers

    const h = require('hyperscript');
    const { div, span, h1 } = require('hyperscript-helpers')(h);

    h1({ 'data-id': 'headline-6.1.2' }, 'Structural Weaknesses').outerHTML
    // '<h1 data-id="headline-6.1.2">Structural Weaknesses</h1>'
    div('#with-proper-id.wrapper', [ h1('Heading'), span('Spanner') ]).outerHTML
    // '<div class="wrapper" id="with-proper-id"><h1>Heading</h1><span>Spanner</span></div>'

`Span`, `Var` have uppercase first letter.

### API

    tagName(selector)
    tagName(attrs)
    tagName(children)
    tagName(attrs, children)
    tagName(selector, children)
    tagName(selector, attrs, children)

`selector`: starts with "." or "#"
`attrs`: object of attributes

Multiple classes:

    button({className: "btn btn-default"}); // by space!
    button(".btn.btn-default");             // by dot!

# nodejs id=g_10126

  nodejs <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10126>
  expressjs
    multiple template engines
      http://expressjs.com/en/api.html
      var engines = require('consolidate');
      app.engine('haml', engines.haml);
      app.engine('html', engines.hogan);
    expressjs examples
      Parsing Forms with Multiple Submit Buttons in Node.js with Express 4
        http://shiya.io/parsing-forms-with-multiple-submit-buttons-in-node-js-with-express-4/
        body-parser module
        index.ejs: two submit buttons
          <form action="/login" method="post">
              <div class="form-group">
                  <label>To Do:</label>
                  <input type="text" class="form-control" name="todo">
              </div>
              <button type="submit" class="btn btn-primary btn-lg" formaction="/top">Add to Top</button>
              <button type="submit" class="btn btn-primary btn-lg" formaction="/bottom">Add to Bottom</button>
          </form>
        index.js
          note: formaction="/top" attributes
            it links to app.post() methods
          code
            // parse html forms
            app.use(bodyParser.urlencoded({ extended : false }));
            // render the ejs page
            app.get('/', function (req, res) {
              res.render('index.ejs');
            });
            // when Add to Top button is clicked
            app.post('/top', function (req, res) {
              console.log(req.body.todo + " is added to top of the list.");
              res.redirect('/');
            });
      Build a javascript todo app with express, jade and mongodb
        ref
          https://coderwall.com/p/4gzjqw/build-a-javascript-todo-app-with-express-jade-and-mongodb
          https://github.com/jasonshark/express-todo
        server.js
          middleware
            var app = express();
            app.set('views', path.join(__dirname, 'views'));
            app.set('view engine', 'jade');
            //app.use(favicon(__dirname + '/public/favicon.ico'));
            app.use(logger('dev'));
            app.use(bodyParser.json());
            app.use(bodyParser.urlencoded());
            app.use(cookieParser());
            app.use(express.static(path.join(__dirname, 'public')));
          routes
            // Routes
            var main = require('./routes/main');
            var todo = require('./routes/todo');
            var todoRouter = express.Router();
            app.use('/todos', todoRouter);
            app.get('/', main.index);
            todoRouter.get('/', todo.all);
            todoRouter.post('/create', todo.create);
            todoRouter.post('/destroy/:id', todo.destroy);
            todoRouter.post('/edit/:id', todo.edit);
        routes/todo.js
          module.exports = {
              all: function(req, res){
                  res.send('All todos')
              },
              viewOne: function(req, res){
                  console.log('Viewing ' + req.params.id);
              },
        pages
          app.get('/', main.index);
          routes/main.js
            module.exports = {
              index: function(req, res) {
                res.render('main', { title: 'Express Todo' });
          views/main.jade
            block content
              h1= title
              p Welcome to #{title}
              a(href='/todos').btn.btn-success.btn-lg View all todos
        @mine: şu kod nasıl rx ile yapılır?
          ex
            all: function(req, res){
                Todo.find({}, function(err, todos){
                    if(err) res.send(err);
                    res.json(todos);
          opt
            gleb'e bak
          opt1
            all: (req, res) => { all_.onNext( { req: req, res: res } ) }
            all_
              .subscribe(
                Todo.find(..)
        refactor
          server.js
            var env = process.env.NODE_ENV || 'development';
            var envConfig = require('./config/env')[env];
            require('./config/config')(app, envConfig);
            require('./config/database')(envConfig)
              config/database.js
                module.exports = function(envConfig){
                  mongoose.connect(envConfig.database, function(){
        form fields 
          todo.js
            create: function(req, res){
                var todoContent = req.body.content;
    Hackathon Starter
      https://github.com/sahat/hackathon-starter
    expressjs guide
      https://expressjs.com/en/guide/
      routing
        https://expressjs.com/en/guide/routing.html
        GET and POST methods
          app.get('/', function (req, res) {
            res.send('GET request to the homepage')
          }
        route parameters
          ex
            Route path: /users/:userId/books/:bookId
            Request URL: http://localhost:3000/users/34/books/8989
            req.params: { "userId": "34", "bookId": "8989" }
        app.route()
          reuse route path for multiple verbs
          ex
            app.route('/book')
              .get(function (req, res) {
                res.send('Get a random book')
              })
              .post(function (req, res) {
                res.send('Add a book')
              })
        express.Router
          has its own middleware
          moduler route handlers
          ex
            var router = express.Router()
            router.use(function timeLog (req, res, next) {
              console.log('Time: ', Date.now())
              next()
            })
            // define the home page route
            router.get('/', function (req, res) {
              res.send('Birds home page')
            })
        route handlers
          multiple callback functions per request
            ex: impose preconditions on a route
            call next() to pass to next callback
          ex
            app.get('/example/b', function (req, res, next) {
              console.log('the response will be sent by the next function ...')
              next()
            }, function (req, res) {
              res.send('Hello from B!')
            })
      Serving static files in Express
        app.use('/static', express.static(path.join(__dirname, 'public')))
        Now, you can load the files that are in the public directory from the /static path prefix.
        http://localhost:3000/static/images/kitten.jpg
        /static: mount path
      express application generator
        npm install express-generator -g
        express -h
        express --view=pug myapp
        run
          DEBUG=myapp:* npm start
      Writing middleware for use in Express apps
        mw: functions that access to req and res and next
          next: pass control to next step in cycle
        ex
          middleware = function(req, res, next) {
            next()
          }
          app.get('/', middleware)
        myLogger
          var myLogger = function (req, res, next) {
            console.log('LOGGED')
            next()
          }
          app.use(myLogger)
          app.get('/', (req,res) => {..})
        requestTime
          var requestTime = function (req, res, next) {
            req.requestTime = Date.now()
            next()
          }
        configurable middleware
          export a function
            that accepts an options object
          ex
            module.exports = function(options) {
              return function(req, res, next) {
                // Implement the middleware function based on the options object
                next()
              }
            }
            var mw = require('./my-middleware.js')
            app.use(mw({ option1: '1', option2: '2' }))
      Using middleware
        application level
          opt
            app.use()
              optional: mount path
            app.METHOD()
          ex
            app.use(function (req, res, next) {..}
          ex: with mount path
            app.use('/user/:id', function (req, res, next) {..}
          ex: route and its handler
            app.get('/user/:id', function (req, res, next) {
              res.send('USER')
            })
          ex: next('route') pass to next get() function
            app.get('/user/:id', function (req, res, next) {
              // if the user ID is 0, skip to the next route
              if (req.params.id === '0') next('route')
              // otherwise pass the control to the next middleware function in this stack
              else next()
            }, function (req, res, next) {
              // render a regular page
              res.render('regular')
            })
            // handler for the /user/:id path, which renders a special page
            app.get('/user/:id', function (req, res, next) {
              res.render('special')
            })
        Router level
          ex
            var router = express.Router()
            // a middleware function with no mount path. This code is executed for every request to the router
            router.use(function (req, res, next) {
              console.log('Time:', Date.now())
              next()
            })
            // mount the router on the app
            app.use('/', router)
          next('router') pass control out of router (to the app)
        built-in middleware
          static
            code
              express.static(root, [options])
              var options = {
                dotfiles: 'ignore',
                setHeaders: function (res, path, stat) {
                  res.set('x-timestamp', Date.now())
                }
              }
              app.use(express.static('public', options))
            multi
              app.use(express.static('public'))
              app.use(express.static('uploads'))
      3rd party middleware
        body parser
          parses request body
            makes available:
              req.body
          not for multipart bodies
          ex
            // create application/x-www-form-urlencoded parser
            var urlencodedParser = bodyParser.urlencoded({ extended: false })
            // POST /login gets urlencoded bodies
            app.post('/login', urlencodedParser, function (req, res) {
              if (!req.body) return res.sendStatus(400)
              res.send('welcome, ' + req.body.username)
            })
    file upload - nodejs id=g_10125
      file upload - nodejs <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10125>
      Form Fields and Upload At the Same Time using Formidable
        http://stackoverflow.com/questions/26996333/can-i-use-body-parser-and-formidable-at-the-same-time
        code
          app.post('/upload', function(req, res){
              var form = new formidable.IncomingForm();
              form.uploadDir = __dirname + "/data";
              form.parse(req, function(err, fields, files) {
                  //fields is an object containing all your fields, do waht ever you want with them from here
                  //file is an object containing properties of your uploaded file
                res.send(util.inspect({fields: fields, files: files}));
                console.log('file uploaded : ' + files.upload.path + '/' + files.upload.name);
                console.log('Fields : ' + fields.adName);//you can access all your fields
              });
          });
        form
          app.get('/', function (req, res) {
              res.send(     
              '<form action="/upload" enctype="multipart/form-data" method="post">'+
              '<input type="text" name="adName" placeholder="adName"><br>'+
              '<input type="file" name="upload" multiple="multiple"><br>'+
              '<input type="submit" value="Upload">'+
              '</form>'
            );
          });
      Do stuff with multiple files when uploading them using node-formidable with Express
        http://stackoverflow.com/questions/10124099/do-stuff-with-multiple-files-when-uploading-them-using-node-formidable-with-expr
          code
            app.use(express.bodyParser({ uploadDir:__dirname + '/public/uploads' }));
            app.post('/upload', function(req, res){
                var form = new formidable.IncomingForm(),
                files = [],
                fields = [];
                form.on('field', function(field, value) {
                    fields.push([field, value]);
                })
                form.on('file', function(field, file) {
                    console.log(file.name);
                    files.push([field, file]);
                })
                form.on('end', function() {
                    console.log('done');
                    res.redirect('/forms');
                });
                form.parse(req);
            });
      Simple File Upload with Express.js and Formidable in Node.js
        ref
          http://shiya.io/simple-file-upload-with-express-js-and-formidable-in-node-js/
        app.js
          var express = require('express');
          var formidable = require('formidable');
          var app = express();
          app.get('/', function (req, res){
              res.sendFile(__dirname + '/index.html');
          });
          app.post('/', function (req, res){
              var form = new formidable.IncomingForm();
              form.parse(req);
              form.on('fileBegin', function (name, file){
                  file.path = __dirname + '/uploads/' + file.name;
              });
              form.on('file', function (name, file){
                  console.log('Uploaded ' + file.name);
              });
              res.sendFile(__dirname + '/index.html');
          });
          app.listen(3000);
        index.html
          <form action="/" enctype="multipart/form-data" method="post">
              <input type="file" name="upload" multiple>
              <input type="submit" value="Upload">
          </form>
      Building a File Uploader with NodeJs
        https://coligo.io/building-ajax-file-uploader-with-node/
        ref
          https://github.com/coligo-io/file-uploader
          /Users/mertnuhoglu/projects/itr/kentgida/nodeapp/file-uploader
          /Users/mertnuhoglu/projects/itr/kentgida/nodeapp/upload01
        views/index.html
          <input id="upload-input" type="file" name="uploads[]" multiple="multiple"></br>
            hide it
              #upload-input {
                display: none;
            replace with
              <div class="progress-bar" role="progressbar"></div>
            functionally too
              $('.upload-btn').on('click', function (){
                  $('#upload-input').click();
        file uploading logic
          listen to file input for a change input
            $('#upload-input').on('change', function(){
          get selected files
            var files = $(this).get(0).files;
            if (files.length > 0){
          populate a FormData object
            it is: a set of key/value pairs
              representing form fields and values
            then send it with ajax to server
            code
              var formData = new FormData();
              for (var i = 0; i < files.length; i++) {
                var file = files[i];
                formData.append('uploads[]', file, file.name);
          ajax request that posts data to server
            code
              $.ajax({
                url: '/upload',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(data){ console.log('upload successful!\n' + data); },
            processData: false,
              don't convert to string
            contentType: false,
              don't add Content-Type header
          update progress bar
            code
              xhr: function() {
                var xhr = new XMLHttpRequest();
                xhr.upload.addEventListener('progress', function(evt) {
                  if (evt.lengthComputable) {
                    var percentComplete = evt.loaded / evt.total;
                    percentComplete = parseInt(percentComplete * 100);
                    $('.progress-bar').text(percentComplete + '%');
                    $('.progress-bar').width(percentComplete + '%');
                    if (percentComplete === 100) {
                      $('.progress-bar').html('Done');
                    }
                  }
                }, false);
                return xhr;
        backend: processing upload
          app.js
            formidable
              parse incoming form data
            fs
              rename files
          routing home page
            app.get('/', function(req, res){
              res.sendFile(path.join(__dirname, 'views/index.html'));
          routing upload/ service
            app.post('/upload', function(req, res){
      multer readme
        https://github.com/expressjs/multer
        middleware for multipart/form-data
          only multipart forms
        multer adds two properties to: request
          body
          file or files
        body: values of text fields of form
        file/files: files uploaded
        ex: basic usage
          x
          var express = require('express')
          var multer  = require('multer')
          var upload = multer({ dest: 'uploads/' })
          var app = express()
          app.post('/profile', upload.single('avatar'), function (req, res, next) {
            // req.file is the `avatar` file
            // req.body will hold the text fields, if there were any
          })
          app.post('/photos/upload', upload.array('photos', 12), function (req, res, next) {
            // req.files is array of `photos` files
            // req.body will contain the text fields, if there were any
          })
          var cpUpload = upload.fields([{ name: 'avatar', maxCount: 1 }, { name: 'gallery', maxCount: 8 }])
          app.post('/cool-profile', cpUpload, function (req, res, next) {
            // req.files is an object (String -> Array) where fieldname is the key, and the value is array of files
            //
            // e.g.
            //  req.files['avatar'][0] -> File
            //  req.files['gallery'] -> Array
            //
            // req.body will contain the text fields, if there were any
          })
        if text-only form, then:
          var upload = multer()
          app.post('/profile', upload.array(), function (req, res, next) {
            // req.body contains the text fields
          })
      formidable readme
        https://github.com/felixge/node-formidable
        parse() to get data
        if cb is provided, fields, files are collected
          form.parse(req, function(err, fields, files) {
            // ...
          });
        field event
          form.on('field', function(name, value) {
          });
        file event
          form.on('file', function(name, file) {
          });
      other opt
        koa.js examples: file upload
          https://github.com/koajs/examples
          ref
            /Users/mertnuhoglu/codes/js/koa-examples/upload
            ~/codes/js/koa-examples/upload/app.js
        Koa.js - File Uploading
          https://www.tutorialspoint.com/koajs/koajs_file_uploading.htm
          ref
          code
            file_upload.pug
              form(action="/upload" method="POST" enctype="multipart/form-data")
                  div
                      input(type="text" name="name" placeholder="Name")
                  div
                      input(type="file" name="image")
                  div
                      input(type="submit")
            app.js
              _.get('/files', renderForm);
              _.post('/upload', handleForm);
              function * renderForm(){
                  this.render('file_upload');
              }
              function *handleForm(){
                  console.log("Files: ", this.request.body.files);
                  console.log("Fields: ", this.request.body.fields);
                  this.body = "Received your data!"; //This is where the parsed request is stored
              }
        File uploads using Node.js
          https://codeforgeek.com/2014/11/file-uploads-using-node-js/
          multer
            var upload = multer({ storage : storage},{limits : {fieldNameSize : 10}}).single('userPhoto');
        File uploads using Node.js: once again
      next
        FormData nerede tanımlanmış?
        doc:
          formData.append('uploads[]', file, file.name);
    alternatives to expressjs
      koa
        kaio
          ref
            https://github.com/enten/kaio
            /Users/mertnuhoglu/codes/js/kaio-app
          app.js
          run
            KO_PORT=1333 DEBUG=* node --harmony app.js
            $ curl http://localhost:1333/api/
            Hello world!
            $ curl http://localhost:1333/api/books
            [{"title":"The Fellowship of the Ring","author":"J. R. R. Tolkien","publication":"1954-07-29"},{"title":"The Two Towers","author":"J. R. R. Tolkien","publication":"1954-11-11"},{"title":"The Return of the King","author":"J. R. R. Tolkien","publication":"1955-10-20"}]
            $ curl http://localhost:1333/api/books/The%2520Two%2520Towers
            {"title":"The Two Towers","author":"J. R. R. Tolkien","publication":"1954-11-11"}
          api doc
            https://cdn.rawgit.com/enten/kaio/master/docs/kaio/0.5.4/Kaio.html#bind
        Getting started with Koa.js
          ref
            https://medium.com/@adrianmacneil/getting-started-with-koa-js-52d8852fa49d
          first there was express
            ex
              app.get('/', (req, res) => { res.send('hello') })
            this lead to callback hell
              app.get(.., (..) => {
                Session.findById(sid, (..) => {
                  User.findById(.., (..) => {..}
            solution: to use Promises
              app.get(.., (..) => {
                Session.findById(sid).then( (..) => { 
                  return User.findById(uid)
                }).then((user) => {
                  res.send(..)
            still mess: boilerplate code, catching errors, passing them to next
            koa cleans mess
              using new async/await
              app.get(.., async (ctx) => {
                session = await Session.findById(sid)
                user = await User.findById(uid)
                ctx.body = `${user.name}`
        koa resources
          https://github.com/koajs/koa/blob/master/docs/guide.md
          https://github.com/alexmingoia/koa-router
          http://koajs.com
          https://bramanti.me/working-with-koa-js/
          https://code.tutsplus.com/tutorials/introduction-to-generators-koajs-part-1--cms-21615
          https://cdn.rawgit.com/enten/kaio/master/docs/kaio/0.5.4/Kaio.html#bind
          https://github.com/enten/kaio
          https://github.com/koajs/koa/wiki
    Error: Can't set headers after they are sent.
      http://stackoverflow.com/questions/7042340/error-cant-set-headers-after-they-are-sent-to-the-client
        ans1
          res object is subclass of Nodejs http.ServerResponse
          you can call 
            res.setHeader
            until: res.writeHead(status)
          after writeHead
            you can only call res.write(data) and res.end(data)
          error means
            you are in body or finished state
            some function set header or statusCode
          case1
            you called res.redirect()
            it caused response to become Finished
            then code threw an error
          case2
            problem
              res.send(util.inspect({fields: fields, files: files}));
              res.redirect("http://localhost:5050/progress/")
            solution
              // remove res.send
              res.redirect("http://localhost:5050/progress/")
  node and rx
    Node server with Rx and Cycle.js
      ref
        https://glebbahmutov.com/blog/node-server-with-rx-and-cycle/
        https://github.com/bahmutov/node-rx-cycle
        /Users/mertnuhoglu/codes/js/node-rx-cycle
      00 node hello world
        <url:file:///~/codes/js/node-rx-cycle/src/00-node-server.js>
        run
          $ node src/00-node-server.js 
          Server running at http://127.0.0.1:1337/
          $ curl 127.0.0.1:1337
          Hello World
        problems
          1. callbacks
            solution: Promises, reactive programming
          2. i/o mixed in code
            solution: separate io from pure functions
      reactive server
        replace callbacks with one reactive stream
        ref
          <url:file:///~/codes/js/node-rx-cycle/src/01-rx.js>
        code logic
          requests_.onNext({ req: req, res: res });
            { req: req, res: res } is event
              passed to onNext(evt) 
          requests_
            .subscribe(
              sendHello,
              console.error,
              () => console.log('stream is done')
            )
          onNext = sendHello
          function sendHello(e) {
            console.log('sending hello');
            e.res.writeHead(200, { 'Content-Type': 'text/plain' });
            e.res.end('Hello World\n');
          e.res accesses response
        run
          $ node src/01-rx.js 
          Server running at http://127.0.0.1:1337/
          $ curl 127.0.0.1:1337
          $ curl 127.0.0.1:1337/hi
    Bacon.js + Node.js + MongoDB: Functional Reactive Programming on the Server
      http://blog.carbonfive.com/2014/09/23/bacon-js-node-js-mongodb-functional-reactive-programming-on-the-server/
    Rx: onNext
      ref
        https://github.com/Reactive-Extensions/RxJS/blob/master/doc/api/core/observer.md#rxobserverprototypeonnextvalue
      corresponds to Iterator.next()
        Iterator.next() pulls next value
        Subject.onNext(v) = Observer.onNext(v)
      code
        var observer = Rx.Observer.create(
            function (x) {
                console.log('Next: ' + x)
            },
            function (err) {
                console.log('Error: ' + err);
            },
            function () {
                console.log('Completed');
            }
        );
        observer.onNext(42);
        // => Next: 42
    Reactive Programming - RxJS vs EventEmitter in Node.js
      http://stackoverflow.com/questions/25338930/reactive-programming-rxjs-vs-eventemitter-in-node-js
      Rx streams and EventEmitter are similar
        both implement Observer pattern
      ex - EventEmitter
        eventEmitter.on('response', function(res) {
          setTimeout(function(){..}, 2000)
      ex - rx
        response_.delay(2000).subscribe(function(res) {
          ..
  Send HTTP Requests To a Server with Node.js
    http://shiya.io/send-http-requests-to-a-server-with-node-js/
    http.request(options, callback)
    ex: post request
      POST /authentication/v1/authenticate HTTP/1.1
      Host: developer.api.autodesk.com
      Content-Type: application/x-www-form-urlencoded
      client_id=my_client_id&client_secret=my_client_secret&grant_type=client_credentials
    ex: post
      var options = {
          host: "developer.api.autodesk.com",
          path: "/oss/v1/buckets",
          method: "POST",
          headers: {
              "Content-Type": "application/json"
              "Authorization": "Bearer token"
          }
      };
      var req = http.request(options, function (res) {
          var responseString = "";
          res.on("data", function (data) {
              responseString += data;
              // save all the data from response
          });
          res.on("end", function () {
              console.log(responseString); 
              // print to console when response ends
          });
      });
      req.write(); // sending the request

# Testing JS id=g_10611

	Testing JS <url:file:///~/gdrive/mynotes/content/articles/articles_js.md#r=g_10611>
		Picking snapshot library id=g_10613
			Picking snapshot library <url:file:///~/gdrive/mynotes/content/articles/articles_js.md#r=g_10613>
			https://glebbahmutov.com/blog/picking-snapshot-library/
		Jest 14.0: React Tree Snapshot Testing id=g_10612
			Jest 14.0: React Tree Snapshot Testing <url:file:///~/gdrive/mynotes/content/articles/articles_js.md#r=g_10612>
			https://jestjs.io/blog/2016/07/27/jest-14.html
			intro
				jest: zero-configuration testing
				ex
					test('Link renders correctly', () => {
						const tree = renderer
							.create(<Link page="http://www.facebook.com">Facebook</Link>)
							.toJSON();
						expect(tree).toMatchSnapshot();
					});
				jest creates a snapshot file:
					<a
						className="normal"
						href="http://www.facebook.com"
						onMouseEnter={[Function bound _onMouseEnter]}
						onMouseLeave={[Function bound _onMouseLeave]}>
						Facebook
					</a>
				if they don't match
					either: update snapshots with `jest -u`
					or: a bug
				it shows exactly what is not matching:
			why snapshot testing
				no browser needed
				debugging
				fast iteration

# unclassified

	Ramdajs  id=g_10190
		Ramdajs  <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10190>
		moved to <url:file:///~/projects/study/js/study_ramda.Rmd>
  Use EJS to Template Your Node Application
    https://scotch.io/tutorials/use-ejs-to-template-your-node-application
    ex
      <body class="container">
          <header>
              <% include ../partials/header %>
          </header>
  https://code.lengstorf.com/learn-rollup-js/
  good blogs
    https://webapplog.com/tag/node-js/
  next - articles_js id=g_10124
    next - articles_js <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10124>
    ref
      next - js egzersiz <url:file:///~/Dropbox/mynotes/stuff.otl#r=g_10123>
    https://medium.freecodecamp.com/5-javascript-bad-parts-that-are-fixed-in-es6-c7c45d44fd81#.ht5ypbzbh
    https://medium.com/@rajaraodv/is-class-in-es6-the-new-bad-part-6c4e6fe1ee65#.8hcaar39k
  Wes Bos - Modern workflow and tooling for frontend developers-CiMGKZpnHQE.mp4
    build tools 
    browserify
    browsersync
    sourcemaps
  Wes Bos - Start Using ES6 Today-493p5FSFHz8.mp4
    string templating
      const markup = `
        <div>
          <h2>${beer.name}</h2>
          ${renderKeywords(beer.keywords)}
        </div>
      `
      funcion renderKeywords(kw) {
        return (
          `<ul>
            ${kw.map(key => `li>${key}</li>`).join('')}
          </ul>`);
      }
    enhanced object literals
      const dog = {
        first: first,
        last: last
      -->
        first, last,
      method definition 
        var modal = {
          create: function(sel) {..
        -->
          create(sel) { ...
    Set
    destructuring
      create 3 variables in 1 shot
        const {first, second, last} = person
      rename while you destructure
        const { twitter: tweet, facebook:fb } = wes.links.social
      with arrays too
        const [name, id] = details
    onst details = ['wes', 123]
  What is Node.js Exactly - a beginners introduction to Nodejs-pU9Q6oiQNd0.mp4
    difference bw nodejs and browser console
      no window, no document object
      global process object
      var a = 1;
      global.a
        in node
      window.a
        in browser
    modules
      define
        module.exports.a = a;
      use
        var m2 = require('./folder1/module2')
        m2.a
      define
        module.exports = function() {..}
      use 
        var m2 = require('./folder1/module2')
        m2()
    npm
      npm init
      npm install
  Azat Mardan - You Don't Know Node.js - JSConf Iceland 2016-NLtL-EEclRc.mp4
    non-blocking
      event loop: non-blocking io
      single thread is better
      it is possible to write blocking code
        fs.readFileSync(..)
        doSth()
        -->
        fs.readFile(..) {
          doSth()
        }
    one language everywhere
      learn quicker
    deeply nested callbacks problem
      solution: events
      observer pattern
      ex
        var events = require("events")
        var emitter = new events.EventEmitter()
      listen
        emitter.on("knock", function() {..})
        emitter.on("knock", function() {..})
        emitter.emit("knock")
      we can have multiple observers
      ex: inheriting from EventEmitter
        job.js
          var util = require("util")
          var Job = function Job() {
            ..
            this.process = function() {
              ..
              job.emit("done", {completedOn: new Date()})
            }
          }
          util.inherits(Job, require("events").EventEmitter)
          module.exports = Job
        weekly.js
          var Job = require("./job.js")
          var job = new Job()
          job.on("done", funciton(details) {
            console.log("job completed", details.completedOn)
          }
          job.process()
    how to handle big data?
      streams
        inherit from Event Emitter
      ex
        http request
        stdin
        file reads
  What the... JavaScript-2pL28CcEijU.mp4
    author: YouDontKnowJS.com
    wtf: intentionally inconsistent, incoherent, unreasonable code
  Bootstrap
    Why Bootstrap Admin Templates suck?
      https://medium.com/@lukaszholeczek/jack-of-all-trades-master-of-none-5ea53ef8a1f
      why they aren't good enough?
        1. no direct contact between seller and user
          tons of unwanted features
        2. most bootstrap components are useless
  David Blurton - Full-stack JavaScript development with Docker - JSConf Iceland 2016-zcSbOl8DYXM.mp4

## ReactiveConf 2016 - Thomas Roch - Past and future of client-side routing-hblXdstrAg0.mp4

Routing is not a solved problem. 

Today is all about components.

"Is MVC Dead?" 

Components can be compared to functions: data -> f(data) -> data

Components are data-driven.

Ex: React component

    function LoggedUser({user}) {
      return user
        ? <div> Hello {user.name} </div>
      ...

We compose components as trees of components.

But when it comes to routing, we do:

    function App({route}) {
      if (route === "dashboard") 
        return <Dashboard/>
      if (route === "profile') 
        return <Profile/>
    }

Why do we treat routing differently?

Because we have been influenced by server side rendering:

Ex:

    django
      router.register('users', UserViewSet)
    express
      app.get('/users', function(req, res) {
        res.send('Users List')
      }

request -> response

This translates into:

route -> action

This type of routing is stateless routing.

Routing on server side is basically pattern matching.

We transposed the same model to stateful browsers.

Ex:

    React-router
      <Route path="users" component={Users}>

Routers became responsible for rendering components. 

Thus they are also responsible for transitioning between them. 

What about code splitting?

Routers are made more complex by being tied to a component library or framework.

Also, in a sense routing has been mystified.

They are enabler of SPA. But when we choose our view library, we will look to router. Mostly, router is embedded into it. 

What about view/state separation?

router5: github.com/router5/router5

npm install router5

``` js
import createRouter from 'router5'
const routes = [
  { name: 'home', path: '/' },
  { name: 'schedule', path: '/schedule/:day' }
]
const router = createRouter(routes)
router.start('/', (err, state) => alert(state.name))
``` 

Move `alert` into an observer/listener:

``` js
import createRouter from 'router5'
import listenersPlugin from 'router5/plugins/listeners'
const routes = [
  { name: 'home', path: '/' },
  { name: 'schedule', path: '/schedule/:day' }
]
const router = createRouter(routes)
  .usePlugin(listenersPlugin())
router.addListener((state) => {
  const text = `Navigated to ${state.name} (${state.path})
  alert(text)
}
router.start('/')
``` 

Now use `navigate()` to go to a specific state.

`browserPlugin` updates browser's url when we navigate to some new state.

``` js
import createRouter from 'router5'
import listenersPlugin from 'router5/plugins/listeners'
import browserPlugin from 'router5/plugins/browser'
const routes = [
  { name: 'home', path: '/' },
  { name: 'schedule', path: '/schedule/:day' }
]
const router = createRouter(routes)
  .usePlugin(browserPlugin())
  .usePlugin(listenersPlugin())
router.addListener((state) => {
  const text = `Navigated to ${state.name} (${state.path})
  alert(text)
}
router.start('/')
router.navigate('schedule', {day: 'wednesday'})
``` 

Now use router with components instead of alert box:

``` bash
npm install react-router5
``` 

`App.js`

``` js
import React from 'react'
export default function App(props) {
  return <h1>Hello</h1>
}
``` 

`index.js`

``` js
import createRouter from 'router5'
import listenersPlugin from 'router5/plugins/listeners'
import browserPlugin from 'router5/plugins/browser'
import App from './App'
import React from 'react'
import ReactDOM from 'react-dom'
const routes = [
  { name: 'home', path: '/' },
  { name: 'schedule', path: '/schedule/:day' }
]
const router = createRouter(routes)
  .usePlugin(browserPlugin())
  .usePlugin(listenersPlugin())
router.start('/')
ReactDOM.render(<App/>, document.getElementById('root'))
``` 

Next, our component needs to access router instance. We add `RouterProvider`

``` js
import createRouter from 'router5'
import listenersPlugin from 'router5/plugins/listeners'
import browserPlugin from 'router5/plugins/browser'
import App from './App'
import React from 'react'
import ReactDOM from 'react-dom'
import {RouterProvider} from 'react-router5'
const routes = [
  { name: 'home', path: '/' },
  { name: 'schedule', path: '/schedule/:day' }
]
const router = createRouter(routes)
  .usePlugin(browserPlugin())
  .usePlugin(listenersPlugin())
router.start('/')
ReactDOM.render(
  <RouterProvider router={router}>
    <App/>
  </RouterProvider>, 
  document.getElementById('root')
)
``` 

`App.js`

``` js
import React from 'react'
import {Link} from 'react-router5'
function AppMenu() {
  return (
    <nav>
      <Link routeName='home'>Home</Link>
      <Link routeName='schedule' routeParams={{day:'wednesday'}}>Wednesday</Link>
    </nav>
  )
}
export default function App(props) {
  return (
    <div>
      <AppMenu/>
      <h1>Hello</h1>
    </div>
  )
}
``` 

When we click 'Home' or 'Wednesday' the url changes. 

But we want to change the component (or view) in addition to url change.

`App.js`

``` js
import React from 'react'
import {Link, withRoute} from 'react-router5'
function AppMenu() {
  return (
    <nav>
      <Link routeName='home'>Home</Link>
      <Link routeName='schedule' routeParams={{day:'wednesday'}}>Wednesday</Link>
    </nav>
  )
}
function AppMain(props) {
  const {route} = props
  if (!route) {
    return <h1>Not found</h1>
  } 
  if (route.name === 'home') {
    return <h1>Hello </h1>
  } 
  if (route.name === 'schedule') {
    return <h1>Schedule for {route.params.day} </h1>
  } 
}
const AppMainWithRoute = withRoute(AppMain)
export default function App(props) {
  return (
    <div>
      <AppMenu/>
      <AppMainWithRoute />
    </div>
  )
}
``` 

Data flow:

    router -> browserListener -> browser url
    router -> listener -> AppMain 
    App 
      AppMain
      AppMenu
        Link -> router
        Link -> router
      

Integration with redux:

Instead of component listener to update AppMain directly, let redux store do it:

    router -> reduxListener -> store -> AppMain

It works with Observables too

``` js
import createRouter from 'router5'
import createObservables from 'rxjs-router5'
import routes from './routers'
const router = createRouter(routes) 
const {
  route$,
  routeNode, 
  transitionError$,
  transitionRoute$
} = createObservables(router)
``` 

Why Routing state/view separation?

- Easier to integrate
- Leverage existing component layers
- Code splitting
- Simpler universal architecture
- Native

## cycle-router5

https://www.npmjs.com/package/cycle-router5

``` js
import {makeRouterDriver} from 'cycle-router5';
 
function intent(sources) {
  return {
    clickStart$: sources.dom.get('.start-button', 'click'),
    routeChange$: sources.router.addListener()
  };
}
 
function model(actions) {
  return actions.clickStart$
    .startWith(null)
    .map(ev => {
      return {
        started: !!ev
      };
    });
}
 
function view(model$) {
  return model$.map(model => {
    return h('p', [
      model.started ? 'Router starting now.' : 'Router not started yet.',
      h('br'),
      h('button', { className: 'start-button' }, 'Start Router')
      h('br')
    ]);
  });
}
 
function routing(intent) {
  return intent.clickStart$
    .map(ev => 'start'); // could also be ['start'] or ['start', arg1, ...] 
}
 
function main(sources) {
  var intent = intent(sources);
  return {
    DOM: view(model(intent)),
    router: routing(intent)
  };
}
 
var routes = [
  { name: 'home', path: '/' }
];
 
var routerOptions = {
  disableClickHandler: false // obviously you could omit this if false 
  // other router5 constructor options go here 
};
 
var [sources, sinks] = Cycle.run(main, {
  DOM: makeDOMDriver('#app'),
  router: makeRouterDriver(routes, routerOptions)
});
``` 

### Integrating React and Datatables — not as hard as advertised

https://medium.com/@zbzzn/integrating-react-and-datatables-not-as-hard-as-advertised-f3364f395dfa

Both Datatables and React manage DOM.

``` js
class Table extends Component { 
    componentDidMount() {
        $(this.refs.main).DataTable({
           dom: '<"data-table-wrapper"t>',
           data: this.props.names,
           columns,
           ordering: false
        });
    }  
    componentWillUnmount(){
       $('.data-table-wrapper')
       .find('table')
       .DataTable()
       .destroy(true);
    }
    shouldComponentUpdate() {
        return false;
    }
    render() {
        return (
            <div>
                <table ref="main" />
            </div>);
    }
}
``` 

Points:

- React doesn't know that there is more DOM inside `<table>`

- `shouldComponentUpdate` always returns `false`. This ensures that no re-rendering happens.

- Table initialization happens only once when component is mounted. Because Datatables handles its internal DOM manipulation. 




