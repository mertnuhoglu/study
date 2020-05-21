  <url:file:///~/Dropbox/mynotes/content/articles/articles_fp_reactive.md>

# Reactive Programming

## cyclejs

## Observables - Cycle.js

  http://cycle.js.org/observables.html#reactive-programming
  observables
    indicates observerer
    actually:
      observable = iterable of iterator + subject of observer pattern
    definition
      lazy event streams
      which emit events (0..n)
      maybe finite or infinite
  reactive programming
    ex: module foo and bar
      module:
        an object of an oop class or
        any mechanism of encapsulating state
      assume: foo -> bar
        foo affects state inside bar
        ex:
          when foo does a network request, increment a counter in bar
        alt1:
          class foo 
            function onRequest() {
              bar.increment()
            }
          foo owns the relationship
            we say: arrow lives at arrow tail ie. foo
            foo: proactive
            bar: passive
          passive module is unaware of the arrow which affects it
        alt2:
          invert the ownership of arrow
          class bar
            foo.addOnRequestListener(() => {
              self.increment();
            }
          bar: reactive
            responsible for managing its own state by reacting to external events
          foo: listenable
            unaware of existence originating from its network request event
        what is benefit of alt2:
          inversion of control
            we can hide bar's increment() as private
          if we want to discover how bar's counter works
    duals: reactive vs. passive
      foo -> bar
      |                         | passive     | reactive    |
      | how does bar work?      | find usages | look inside |
      | which modules affected? | look inside | find usages |
    notes
      MVC
        controller = foo
        view = bar
        foo { bar.increment() }
          view passive
        foo.subscribe( () => { bar.increment } )
          view reactive
      IMV: IM
        intent = foo
        model = bar
          model passive
          model reactive
    paradigms
      passive/proactive: imperative
      reactive/listenable: reactive
    design principles
      self-responsible modules
        modules don't change external state
        leads to separation of concerns
      inversion of control
        bar is responsible for itself
    note
      how to differentiate bar from foo?
        bar
          state modified
          object: affected module
          target of effect
          observer in observer pattern
        foo
          subject (özne)
          source of effect
          subject in observer pattern
      mappings
        | object  | target | observer | affected | state modified |
        | subject | source | subject  | affects  | calls client   |
      duality
        | understand        | module | active    | reactive  |
        |-------------------|--------|-----------|-----------|
        | how bar works?    | bar    | find uses | look in   |
        | whom foo affects? | foo    | look in   | find uses |
      programming models
        |      | subject    | object   |
        | alt1 | active     | passive  |
        | alt2 | passive    | active   |
        | alt1 | proactive  | passive  |
        | alt2 | listenable | reactive |
  rxjs observables
    why rxjs
    Observable contract: (OnNext)* (OnCompleted|OnError){0,1}
    can be listened to
      obs.subscribe(
        function handleNextEvent(event) {..},
        function handleError(error) {..}, // optional
        function handleCompleted() {..} // optional
      );
    very useful when you transform them with pure functions
      creating new observables on top
      ex: 
        in: observable of click events
        out: observable of double-click events
      code
        doubleClick = clickObservable
          .buffer(() => clickObservable.debounce(250))
          .map(arr => arr.length)
          .filter(x => x === 2);
      succinctness is power
  observables in cyclejs
    types
      senses
      actuators
    simplest case
      computer: pixels on screen
      human: mouse + keyboard events
      computer: observes user inputs
      human: screen state
      both: observable
      computer's output: screen observable
      human's output: input observable
    computer() function
      takes human's output as its input
    human() function
      takes computer's output as its input
    code
      function computer(userEventsObs) {
        return userEventsObs
          .map(event => ..)
          .filter(somePredicate)
          .flatMap(transformToPixels)
    human() function
      external world
      conceptually exists
      in practice:
        we need to use driver functions to reach external world
    drivers
      adapters to external world
      represents
        one aspect of external effects
      ex
        DOM driver
          in: screen observable
          out: mouse/keyboard events' observable
          in between: 
            produces "write" side effects
              to render elements on DOM
            catches "read" side effects
              to detect user interaction
          notes
            human -read> Dom driver -write> computer
      joining both parts
        main(): computer function
        driver function
        code
          y = domDriver(x)
          x = main(y)
        circular dependency
          if "=" means assignment
            x = g(f(x))
          cycle.js solves the problem
      how cyclejs solves circular dependency?
        Cycle.run(main, drivers)
        function main(sources)
        notes
          code
            k: keyboard events
            s: screen output
            k = domDriver(s)
            s = main(k)
            s = main(domDriver(s))
          functions
            human -k> dom driver -k> computer -s> human

## articles

  Ben Lesh _ RxJS 5 In Modern Web Applications-D7ImfM7_mcs.mp4
  Cool RxJS Tricks - Seth House-hkVq7u94Vzw.mp4
  Get Reactive with RxJS -  Venkat Subramaniam-Sj9E_10K4Pw.mp4
  Learn How To Use RxJS 5.5 Beta 2 - Special Edition with Ben Lesh-aHYlsjaj1RY.mp4
  ReactiveConf 2016 - André Staltz - Visualizing the data flow with Cycle.js-3a98OPJWFPY.mp4
  Refactoring to RxJS 5.5 Beta 2 in Production, RxJS Docs & Microstates-s7gK__dC-_g.mp4
  RxJS - The Good Parts - Christopher Gosselin & Daniel Figueiredo Caetano-TszoFCFydiM.mp4
  RxJS 5   Thinking Reactively _ Ben Lesh-3LKMwkuK0ZE.mp4
  RxJS In-Depth – Ben Lesh-KOOT7BArVHQ.mp4
  RxJS Observables Crash Course-ei7FsoXKPl0.mp4
  RxJS Quick Start with Practical Examples-2LCo926NFLI.mp4Everything is a Stream - Rob Wormald-UHI0AzD_WfY.mp4
    redux: reduces all the states and new action, to new state
  RxJS 5   Thinking Reactively _ Ben Lesh-3LKMwkuK0ZE.mp4
    ex: when mouse is down, start listening mouse moves until mouse is up
      const targget = document.querySelect('#target')
      const targetMouseDown$ = Observable.fromEvent(target, 'mousedown')
      const targetMouseMove$ = Observable.fromEvent(target, 'mousemove')
      const targetMouseUp$ = Observable.fromEvent(target, 'mouseup')
      const dragDrop$ = targetMouseDown$.switchMap(
        () => docMouseMove$.takeUntil(docMouseUp$)
      )
    all variables in your system change over time,
      and are observable
      find where variables are used
        var c = a + b
        doSomething(c)
        -->
        c$.subscribe(doSomething)
      how to make c a stream?
        const c$ = a$.combineLatest(b$, (a, b) => a + b)
      how to make a$, b$?
    rxjs is confusing!
      stop worrying with operators
      comfortable with promises?
        promise.then(
          successFn, 
          errorFn
        )
        observable.subscribe(
          nextFn,
          errorFn,
          completeFn
        )
  Streams - FunFunFunction #13-UD2dZw9iHCc.mp4
    highland examples
      http://highlandjs.org
    /Users/mertnuhoglu/Dropbox/public/img/ss-140.png
    https://baconjs.github.io
      var up   = $('#up').asEventStream('click');
      var down = $('#down').asEventStream('click');
      var counter =
        // map up to 1, down to -1
        up.map(1).merge(down.map(-1))
        // accumulate sum
          .scan(0, function(x,y) { return x + y });
      // assign observable value to jQuery property text
      counter.assign($('#counter'), 'text');
  The introduction to Reactive Programming you've been missing
    https://gist.github.com/staltz/868e7e9bc2a7b8c1f754
  dead-simple-rxjs-todo-list
    http://blog.edanschwartz.com/2015/09/18/dead-simple-rxjs-todo-list/
    example
      Rx.Observable.
        // create a stream of button click events
        fromEvent($('button'), 'click').
        // map the click-event stream to a stream of button values
        map(evt => parseInt(evt.target.innerText)).
        // filter to only even-numbered buttons
        filter(val => val % 2 === 0).
        // Write values to DOM
        forEach(val => $('ul').append(`<li>You clicked ${val}</li>`));
    explain
      event handler for a button click event
      code looks like
        data processing
        rather than event-handling
      think about RxJS observables as
        asynchronous arrays
  andre-staltz-people-are-often-scared-of-learning-something-new
    https://medium.com/@ReactiveConf/andre-staltz-people-are-often-scared-of-learning-something-new-3efe2b4ea1b4
    why cycle.js?
      React:
        implementation by: OOP
        ideas: FP
      cycle: both FP
  shiny-server node q
    https://github.com/rstudio/shiny-server/blob/master/node_modules/q/README.md
    explain
      function
        cannot return a value
        return a promise instead
      promise
        return value or exception
      used as
        proxy for remote object
      ex
        step1(function (..) {
          step2(.. { ..
        =>
        Q.fcall(promise1)
        .then(promise2)
      callback aproach
        inversion of control
        don't call me, i'll call you
      Pyramid of Doom _me
        what does this mean?
          buttonOn( function(..) {
            xOn( function(..) {
              yOn( function(.. ) {
              ...
        dependencies between events
          make a deeply nested design
        dependencies determine
          the structure of control flow
        separate dependencies from control flow 
      promise
        uninvert the inversion
        seperate
          input arguments 
          from control flow arguments
    tutorial
      then method
        promises have then method
        aim
          get return value
          or get exception
      ex
        promiseMeSomething()
          .then(function (value) {
          }, function (reason) {
          });
      promiseMeSomething
        returns a promise
      that promise will get fulfilled later with
        first function (fulfillment handler)
      if pMS gets rejected
        second function will be called
      resolution of a promise is 
        asynchronous always
        that is
          fulfillment or rejection handler
          will always be called
          in next turn of event loop
            ie. process.nextTick in Node
        thus
          "then" will always return
          before either handler
    Propagation
      ex
        var outputPromise = getInputPromise()
          .then(function (input) {
          }, function (reason) {
          });
      "then" returns a promise
        assigned to outputPromise
          return value: 
            either handler
  understanding-the-functional-revolution
    http://blog.reactandbethankful.com/posts/2015/09/15/understanding-the-functional-revolution/
    are functions always functional?
      conditions for functional 
        don't mutate arguments
        return a meaningful result
        don't have side effects
    functional programming at the application level
      FP at language level
        classic map, reduce examples
      application has a new dimension:
        time
      asynchronicity
        difference 
          between calling a function a()
            now
            or later
          none
            but assure 
              functions are called at right time
          when is right time?
            when arguments are available
      ways to deal with asynchronicity
        callbacks
          not elegant
          need control flow library
          don't return meaningful value
        use fp
          promises
          streams
    Promises
      what is it?
        object wrapping a future result
        ref
  how-and-why-to-return-functions-in-r
    http://www.win-vector.com/blog/2015/04/how-and-why-to-return-functions-in-r/
  what-is-webassembly-the-dawn-of-a-new-era
    https://medium.com/javascript-scene/what-is-webassembly-the-dawn-of-a-new-era-61256ec5a8f6
    low level programming
      AST
      compiles to javascript
      no complex feature: object, gc, 
    missing
      write mostly in high level
      but drop to assembly language once in a while
    language diversity
      js is not slow 
  javascript-training-sucks
    https://medium.com/javascript-scene/javascript-training-sucks-284b53666245
    summary
      everybody knows a little js
      almost nobodly really understands it
    we all depend on js
      js rules the web
        web eats the software
      two pillars of js
        most important features of js
          prototypal oo
          functional programming
    myth of 10x developer
      if you hire average dev without senior developers available
        slow down productivity
          lasting effects
          technical debt
            long after the average dev moves on
        avg dev: closes tickets quickly
        10x dev is not 10x better than average
          just competent enough to avoid technical debt
    interview
      regular js interviews
        idioms, syntax, quirks
        what is hosting?, "this" context building, setTimeout
      now: two questions
        example of prototypal inheritance?
          pros and cons of different forms of inheritance
            vitally important
        example use cases of closures?
    two pillars of javascript
      prototypal oo
      functional programming
      why?
        impossible to do async programming
          without understanding
            closures
            negative impact of side effects
      traditional teaching
        classes, syntax
        need
          concatenating objects, callback, higher order functions, Array.prototype.map()
          book: Programming JS Applications
  Data Binding in Reactive Programming and Firebase
    http://schd.ws/hosted_files/droidconnyc2015/f3/Data%20Binding%20in%202015-%20Reactive%20Programming%20and%20Firebase.pdf
    summary
      Observables: event producers
      Subscribers: event consumers
      Operators: event transformers
        Filter
        Map
        Reduce
    code
      Observable.create(new Observable.OnSubscribe() {
        call(Subscriber sub) {
          sub.onNext(..)
        }
      })
      .filter(..)
      .subscribe(..)
      what happens
        stream -> transform -> subscribe
    Firebase: Observable
      real time data synchronization
      code
        Firebase ref = new Firebase("http...")
        ref.addChildEventListener(new ChildEventListener() {
          onChildAdded(DataSnapshot ds, String prevKey) {..}
          onCancelled(..)
        ref.setValue(pojo)
      expl
        view listens model
        model listens firebase
        view setValue firebase
  closures fp articles
    http://www.win-vector.com/blog/2015/03/using-closures-as-objects-in-r/
    http://blog.reactandbethankful.com/posts/2015/09/15/understanding-the-functional-revolution/
    What if the user was a function? Andre Staltz
      https://www.youtube.com/watch?v=1zj7M1LnJV4
      insights
        uis are cycles
        uis are functions
        uis are async
        uis are asymmetric
        user is a function
      mvc
        view seen by user
        user uses controller
        controller manipulates view
        controller manipulates model
        model updates view
      IMV or functions
        intent
        model
        view
        these are just functions
      cyclejs ex
        Cycle.applyToDOM('#app', function computer(interactions) {
            .map(ev => ev.target.value);
          var name$ = changeName$.startWith('');
          var screen$ = name$.map( name =>
            <div>
              <h1> Hello {name}</h1>
          );
          return screen$;
        }
      model view intent
        intent
          function intent(interactions) {
            return interactions.get('.field', 'input')
              .map(ev => ev.target.value);
          }
        model
          function model(changeName$) {
            return Rx.Observable.just('').merge(changeName$);
          }
        view
          function view(name$) {
            return name$.map( name =>
              <div>
                <h1> Hello {name}</h1>
            );
          }
        intent -> model -> view -> user -> intent
  Effectful Haskell: IO, Monads, Functors
    http://slpopejoy.github.io/posts/Effectful01.html
    effectful programming
      pure functions
        referentially transparent
    effectful encompasses
      1. actual side-effects (IO)
      2. stuff that seems like side-effects (state, writer, etc)
      3. context that persist over function calls (reader, state, etc.)
      4. non-local control flow (maybe, either)
  All evidence points to OOP being bad
    http://blog.pivotal.io/labs/labs/all-evidence-points-to-oop-being-bullshit
    paul graham
      oop makes it easy to build up by accretion
      structured way to write spaghetti code
    difference to procedural
      abstractions to improve
        code sharing, security
      but still procedural
    state
      joe armstrong
        you want banana
        you get a gorilla holding banana with entire jungle
      state is your enemy
        oop -> mutability + non-determinism + complexity
    nouns and verbs
      mostly verbs masquerading as nouns
        strategies, factories, commands
      software's primary concern: verbs
      oop programs with low coupling
        hundreds of tiny objects
        sacrificing readability for changeability
    inheritance vs. composition
      dijkstra
        oop is an exceptionally bad idea which could only have originated in california
      inheritance
        advocates of oo discourage it
        forces taxonomy in advance
        structure is resistant to change
  Monads demystified
    http://blog.reverberate.org/2015/08/monads-demystified.html
    monads are design pattern
      analogy: operator overloading in c++
        monads are about side effects
        == operator overloading is about matrix multiplication
    monad abstraction: bind ("and then")
      what is basic abstraction of +=
        add y to x
      basic abstraction of monad:
        bind
        written
          x >>= f
        means
          and then f
        lets you
          keep adding functions to a chain of operations
        ex
          monadic value: M1
          M1 bind() f1(val) -> M 
          -> M2 bind() fi(val) -> M
          -> M3
  Reactive Programming for Java Programmers-fz31sbwOYq8.mp4
    project
      /Users/mertnuhoglu/projects/study/study_evammoa/src/study/rx/Sample.java
    ex01
      Observable<StockInfo> feed = StockServer.getFeed(symbols);
      feed.subscribe(System.out::println);
    ex02
      feed.subscribe(new Observer<StockInfo>() {
        onCompleted() {..}
        onError() {..}
        onNext() {..}
      }
    essence
      observable.subscribe( action / observer )
    streams _me
      stream is a function
        -----x-----x-----> : feed = stream
             |     |
             v     v
        -----x-----x-----> : observer
        subs.onNext() 
        here feed is a function
      reactive values in R
        reactive value = stream = observable
        x events are value updates
          rv <- 5
          rv <- 7
        -----x-----x-----> : reactive value
             |     |
             v     v
        -----x-----x-----> : observer
        observer actions are
          observeEvent( this_expression )
    ex03
      feed.skipWhile(stockInfo -> stockInfo.value < 1000)
  Functional Reactive Programming with RxJava-Dk8cR1Kxj0Y.mp4
    why
      network latency
        multiple network calls collapsed into 1
        problems
          nested conditions
          how to make them concurrently
    first solution
      getData(Callback<T> c)
      Future<T> getData()
      Future<List<Future<T>>> getData()
    Rx solution
      Iterable -> Observable
      pull -> push
      T next() -> onNext(T)
      throws Exception -> onError(Exception)
      returns -> onCompleted()
    ex - Iterable
      getData()
        .skip(10)
        .take(5)
        .map({ s -> ... })
        .forEach( { println ... })
    ex - Observable
      getData()
        .skip(10)
        .take(5)
        .map({ s -> ... })
        .subscribe( { println ... })
    alternatives
      sync - single
        T getData()
          String s = getData(args)
          if (s == x) ...
      sync - multiple
        Iterable<T> getData()
          Iterable<String> values = getData(args)
          for (String s: values)
            if (s == x) ..
      async - single
        Future<T> getData()
          Future<String> s = getData(args)
          if (s.get().equals(x))
        limits
          blocking: s.get()
        guava:
          Futures.addCallback(s, 
            new FutureCalback {
              onSuccess(s)
          better
        CompletableFuture
          better
          CompletableFuture<String> s = getData(args)
          s.thenApply((v) -> {
            if (v.equals(x)) ..
        Akka's Future
          great
            Future<String> s = getData(args)
            s.map({ s ->
              if (s.equals(x))
          except that it is singular
      async - multiple
        like Akka
          Observable<String> s = getData(args)
          s.map({ s ->
            if (s.equals(x))
    Blocking API -> Observable API
      Blocking API
        class VideoService {
          def VideoBookmark getBookmark(..)
          ..
      Observable API
        def Observable<VideoBookmark> getBookmark(..)
    nonopinianeted where the concurrency comes from
      calling Thread -> Observable -> callback thread 
      calling and callback threads are independent of each other
      api implementation chooses
        whether blocking
        what resources it uses
    create
      Observable<T> create(Func1<Observer<T>, Subscription> func)
      marble diagram
        create { onNext; onNext; onCompleted }
  RxJS Book
    http://xgrommx.github.io/rx-book/
    Introduction
      composing async and event based programs
        using observable sequences
          and query operators
      data sequences
        ex: stream from data, web service, request, notification, user input
        represented as observable sequence
      works with
        sync multiple values
          iterable objects: array, set, map, object
        single value async computations
          promise
        sync single values
          object
      similarity with higher order functions to an array
        Iterable
          getData()
            .filter(..)
            .map(..)
            .forEach(..)
        Observable
          getData()
            .filter(..)
            .map(..)
            .forEach(..)
    Why RxJS
      what about promises
        for solving async operations 
          such as querying a service with XMLHttpRequest
          where 
            one value
            and then completion
      Observables unify
        promises, callback
        evented data
          dom input, web workers, web sockets
      enables: rich composition
        what is it?
          ex:
            makes sure not to flood the service with calls for every key stroke
  Functional Programming in Javascript
    http://reactivex.io/learnrx/
    intro
      5 simple functions enough:
        map
        filter
        concatAll
        reduce
        zip
    working with arrays
      for loop
        for (i = 0; i < names.length; i++)
          console.log(names[i])
      forEach
        names.forEach(function(name) 
          console.log(name)
      mapping
        projecting arrays
          code
            JSON.stringify([1,2,3].filter(function(x) { return x > 2;})) === "[3]"
            newReleases.forEach(function(video) {
              videoAndTitlePairs.push({id:video.id, title: video.title});
            });
          common operations in projection:
            1. traverse source array
            2. add each projected value to a new array
        implement map
          abstract projection operations
          code
            Array.prototype.map = function(projectionFun) {
              var results = [];
              this.forEach(function(item) {
                results.push( projectionFun(item) );
              });
              return results;
            };
            JSON.stringify([1,2,3].map(function(x) { return x + 1; })) === '[2,3,4]'
          use map() for videos
            newReleases = videoAndTitlePairs.map( function(elem) { return {id:elem.id, title: elem.title} } )
      filtering arrays
        use forEach
          var results = [];
          newReleases.filter( function(video) {
            if (video.rating == 5.0) results.push( video );
          }
        common operations
          1. traverse array
          2. add objects that pass the test
        implement filter
          code
            Array.prototype.filter = function(predicate) {
              var results = [];
              this.forEach(function(item) {
                if (predicate(item)) results.push( item );
              });
            }
            JSON.stringify([1,2,3].filter(function(x) { return x > 2})) === "[3]"
        query data by chaining method calls
          chain filter and map
            code
              newReleases.filter( function(x) { return x == 5.0 })
                .map( function(x) return {id: x.id, title: x.title} );
      querying trees
        what to do
          instead of querying flat arrays, query trees
          first we need to flatten them in order to apply filter and map
        code
          var movieLists = [
            {
              name: ..
              videos: [
                { 
                  id: ..
                }
              ]
            }
          ]
          results = []
          movieLists.forEach( function(list) {
            list.videos.forEach( function(video) {
              results.push( {id: video.id} ) ...
        representation of structure _me
          opt1
            List
              MovieList
                name
                List
                  Video
                    id
                    title
          opt2
            List -> MovieList[name] -> videos: List -> Video[id, title]
          opt3
            List<MovieList> 
            MovieList{ name, videos:List<Video> }
            Video{ it, title }
        implement concatAll
          what
            iterates over each sub-array
            collects results in a flat array
          code
            Array.prototype.concatAll = function() {
              var results = [];
              this.forEach(function(subArray) {
                results.push.apply( results, subArray )
            JSON.stringify([ [1,2,3], [4,5,6], [7,8,9] ].concatAll()) === "[1,2,3,4,5,6,7,8,9]"
        use map and concatAll to flatten movieLists
          code
            movieLists = [
              { 
                name
                videos: [
                  { 
                    id
            movieLists.map( function(list) { 
              return list.videos.map(function(video) {
                return video.id;
              });
            }).
            concatAll()
        get id, title, and 150x200 box art for every video
          code
            movieLists = [
              { name
                videos: [
                  { id
                    title
                    boxarts: [
                      { width: 150
                        url
            movieLists.map( function(list) {
              list.videos.map( function(video) {
                return video.boxarts
                  .filter( function(boxart) {
                    return boxart.width === 150
                  })
                  .map( function(boxart) {
                    return {id: video.id, title: video.title, boxarts: boxart.url} 
                  });
              })
              .concatAll();
            })
            .concatAll();
        implement concatMap
          what
            we usually chain map and concatAll 
            common: map followed by concatAll
          code
            Array.prototype.concatMap = function(projection) {
              return this.
                map(function(item) {
                  return projection(item)
                })
                .concatAll();
            })
        use concatMap
          code
            return movieLists.concatMap(function(movieList) {
              return movieList.videos.concatMap(function(video) {
                return video.boxarts.
                  filter(function(boxart) {
                    return boxart.width === 150;
                  }).
                  map(function(boxart) {
                    return {id: video.id, title: video.title, boxart: boxart.url};
                  });
                });
              });
      reducing arrays
        what
          operation on more than one item in array at the same time
          ex: max integer in array
            needs to compare items in array
            reduction
              select an item as max
              compare that to every other item
              each time we find a larger number
                replace larger value
          reduction in general
            size comparision encapsulated in a closure
            write a function to handle array traversal
        use forEach to find largest box art
          code
            var boxarts = [
              { width:.., url : .. }
            boxarts.forEach( function(boxart) {
              currentSize = boxart.width * boxart.height;
              if (currentSize > maxSize) {
                largestBoxart = boxart;
                maxSize = currentSize;
              }
            });
            return largestBoxart;
        impelment reduce
          code
            Array.prototype.reduce = function(combiner, initialValue) {
              var counter, accumulatedValue;
              if (this.length === 0) {
                return this;
              } else {
                if (arguments.length === 1) {
                  counter = 1;
                  accumulatedValue = this[0];
                } else if (arguments.length >= 2) {
                  counter = 0;
                  accumulatedValue = initialValue;
                }
              }
              while (counter < this.length) {
                accumulatedValue = combiner(accumulatedValue, this[counter])
                counter++;
              }
              return [aacumulatedValue];
            }
            [1,2,3].reduce(function(accumulatedValue, currentValue) { return accumulatedValue + currentValue; }); === [6];
            [1,2,3].reduce(function(accumulatedValue, currentValue) { return accumulatedValue + currentValue; }, 10); === [16];
        retrieve largest rating
          code
            var ratings = [2,3,1,4,5];
            ratings.reduce( function(acc, cur) { 
              if (acc >= cur) {
                return acc;
              } else {
                return cur;
              }
            });
        retrieve url of largest boxart
          code
            var boxarts = [ { width:.., url:.. 
            boxarts.reduce( function(acc, curr) {
              if (acc.width * acc.height > cur.width * cur.height) {
                return acc;
              } else {
                return curr;
              }
            }).map( function(boxart) {
              return boxart.url;
            });
        reducing with an initial value
          code
            var videos = [ { id:.. , title:.. 
            // output
            // [ { 'id': 'title', ... 
            videos.reduce( function(accumulatedMap, video) {
              var copy = Object.create(accumulatedMap);
              copy[ video.id ] = video.title;
              return copy;
            }, {} );
        retrieve id, title, smallest box art url for every video
          code
            var movieLists = [ 
              { name:
                videos: [
                  { id:
                    boxarts: [
                      { width, url
            movieLists.
            boxarts.reduce( function(acc, curr) {
              if (acc.width * acc.height < cur.width * cur.height) { return acc; } else { return curr; }
            }).map( function(boxart) {
              return boxart.url;
            });
            
      zipping arrays
        what
          combining two arrays
        combine videos and bookmarks
          code
            videos [ { id, title }, .. ]
            bookmarks [ { id, time }, .. ]
            result = []
            for (..)
              result.push( {videoId: videos[i].id, bookmarkId: bookmarks[i].id} )
        implement zip
          code
            Array.zip = function(left, right, combiner) {
              var counter, 
                results = [];
              for (counter = 0; counter < Math.min(left.length, right.length); counter++) {
                results.push(combiner(left[counter], right[counter]));
              }
              return results;
            };
        combine videos and bookmarks using zip
          code
            Array.zip(videos, bookmarks, function(xs, ys) {
              return {videoId: xs.id, bookmarkId: ys.id};
            });
        retrieve each video's id, title, middle interesting moment time, smallest boxart url
          code
            mL = [ 
              { name, 
                videos: [ 
                  { id, title, 
                    boxarts: [ { width, url } ], 
                    interesting: [ {type, time} ] } ] } ]
          opt1
            boxarts = ml.concatMap( a ->
              a.videos.concatMap( v ->
                v.boxarts.reduce( acc, cur ->
                  return ifelse( cur < acc, cur, acc )
                ).map( b ->
                  return { b.url, v.id, v.title }
                ) ) )
            interesting = ml.concatMap( a ->
              a.videos.concatMap( v ->
                v.interesting.filter( i ->
                  return i.type === "Middle"
                ).map( i ->
                  return { i.time, v.id }
                ) ) )
            Array.zip( boxarts, interesting, fun(xs, ys) {
              return { xs.id, xs.title, xs.url, ys.time }
            })
          opt2
            boxarts = ml.concatMap( a ->
              a.videos.concatMap( v ->
                return Array.zip( 
                  v.boxarts.reduce( acc, cur ->
                    return ifelse( cur < acc, cur, acc )
                  ),
                  v.interesting.filter( i ->
                    return i.type === "Middle"
                  ), fun(xs, ys) {
                    return { v.id, v.title, xs.url, ys.time }
                  })
  Collection Pipelines - Martin Fowler
    http://martinfowler.com/articles/collection-pipeline/
    first encounters
      ruby
        some_articles
        .select{|a| a.tags.include?(:nosql)}
        .sort_by{|a| a.words}
        .take(3)
      forming a collection pipeline
        as a method chain
      clojure
        (->> (articles)
          (filter #(some #{:nosql} (:tags %)))
          (take 3))   
    defining collection pipeline
      special case of 
        Pipes and Filters
        composing higher-order functions
      in unix
        collection: text file
        items: lines of file
      in oop
        collection: collection class
        items: objects
      in fp
        collection: collection
        item: genric collection types
      sql
        collections: constrained to be relations
        similar to comprehensions
    exploring more pipelines and operations
      getting total word counts (map and reduce)
        ex
          [1, 2, 3].map{|i| i * i} # => [1, 4, 9]
        ex
          some_articles
          .map{|a| a.words}
          .reduce {|acc, w| acc + w}
        opt2
          some_articles
            .map(&:words)
            .reduce(:+)
      getting number of articles of each type (group-by)
        hashmaps =
          lists of key-value pairs
        ex
          some_articles
            .group_by {|a| a.type}
            .map {|key, value| [key, value.size ]}
            .to_h 
      getting number of articles for each tag
        data structure
          - title: NoDBA
            words: 561
            tags: [nosql, people, orm]
            type: :bliki 
        invert many-to-many relationship
          code 
            some_articles
              .map {|a| a.tags.map{|tag| [tag, a]}}    
              .flatten 1
            =
            some_articles
              .flat_map {|a| a.tags.map{|tag| [tag, a]}}
          group by tag
            .group_by {|pair| pair.first}
            .map {|k,pairs| [k, pairs.map {|p| p.last}]}      
          representation of data structure _me
            articles:List -> Article -> tags:List -> tag
            articles.map
              a -> 
                a.tags.map
                  tag ->
            alist:map === List
              object === Object/argument
                object.alist.map === List
                  object === Object/argument
        count words
          .map {|k,v| [k, {articles: v.size, words: v.map(&:words).reduce(:+)}]}
        clojure
          (->> (articles)
            (mapcat #(map (fn [tag] [tag %]) (:tags %)))
            (map (fn [[k v]] [k (map last v)]))
            (map (fn [[k v]] {k {:articles (count v), :words (reduce + (map :words v))}}))
            (into {})) 
        abstract inversion
          .invert_index_by {|a| a.tags}
  Building An Application from Scratch using RxJava and Java 8
    http://blog.xebia.in/2015/09/01/day1-building-an-application-from-scratch-using-rxjava-and-java8/
    project
      /Users/mertnuhoglu/projects/study/java/rxjava/
    working with rxjava
      ex01
        Observable<String> tweets = Observable.just("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!");
        tweets.subscribe(tweet -> System.out.println(tweet));
      just
        factory method
      subscribe
        Observer object
          onNext, onError, onCompleted
        this: onNext
        more succinct
          tweets.subscribe(System.out::println);
    sequence diagram _m
      code - websequencediagrams.com
        Subscriber->Observable:subscribe
        Observable->OnSubscribe:run
        OnSubscribe->Scanner:nextLine *
        Scanner->Subscriber:onNext(line)
        Subscriber->Action1:run(line)
        Action1->System.out:println(line)
      /Users/mertnuhoglu/Dropbox/public/img/m0001.png
      code - sequence.jar
        Subscriber.start {
          Observable.subscribe {
            OnSubscribe.run {
              Scanner.nextLine* {
                Subscriber.onNext(line) {
                  Action1.run(line) {
                    System_out.println(line); }}}}}}
    Observable factory methods
      Observable.from
        ex
          List<String> tweets = Arrays.asList("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!");
          Observable<String> obs = Observable.from(tweets);
          obs.subscribe(System.out::println);
        arg: Iterable
        just: wrapper around from
      Observable.create
        base factory method
        arg: anything
    Multiple subscribers
    Cold and Hot Observable
      cold
        emit events when someone subscribes
        create, from, just
      hot
        emit independent of whether someone has subscribed
        publish
      code
        Observable.interval(1, TimeUnit.SECONDS).publish()
    Transformations on Observable
      transformation operators
        map, flatMap, scan
        map
          code
            tweets.map(tweet -> tweet.length())
              .forEach(System.out::println);
        flatMap
          code
            tweets.flatMap(tweet -> Observable.<String>from(tweet.split("")))
                    .forEach(System.out::println);
        filter
          tweets.filter(tweet -> tweet.startsWith("RxJava"))
      aggregation operators
        count
          tweets.count().forEach(..)
        reduce
          tweets.map(tweet -> tweet.length()).reduce(0, (acc, el) -> acc + el).forEach(..)
  10 Interview Questions Every JavaScript Developer Should Know
    https://medium.com/javascript-scene/10-interview-questions-every-javascript-developer-should-know-6fa6bdf5ad95#.cnfblh4iv
      class is not harmless sugar
        it is virus that infects everything
        joe armstrong - erlang
          gorilla banana problem "coders at work"
          you wanted a banana but what you got was a gorilla holding the banana and the entire jungle
        favor object composition over class inheritance
    it starts with people
      best way to evaluate: pair programming exercise
        sample project: pull tweets from twitter api and display them on timeline
    can you name two programming paradigms important for js developers
      js supports oop with prototypal inheritance
      functional programming
    what is functional programming
      pure functions 
      avoid side effects
      simple function composition
      functions as arguments
      higher order functions
    difference between classical inheritance and prototypal inheritance
      instance inherit from other objects
      instantiated via factory functions
      composed from many different objects  classes create tight coupling
      prototypes: functional inheritance, object composition
    pros and cons of fp vs. oop
      oop pros
        easy to understand
      oop cons
        shared state
        accessed at random with non-deterministic order
      fp pros
        avoid shared state
        point-free style = tacit programming = data pipeline
        declarative = denotational style
      fp cons
        reduce readability due to abstractness
    when to use classical inheritance
      never
      favor object composition
    when to use prototypal inheritance
      types
        delegation (prototype chain)
        concatenative = mixins = Object.assign()
        functional = function to create a closure for private state
    favor composition
      code reuse 
        by assembling smaller units of functionaility
    what are two-way data binding and one-way data flow
      two way
        ui fields bound to model data dynamically
        st. ui field change -> model data changes 
        vice versa
        can cause side effects hard to follow
        angular: two way
      one way
        model is single source of truth
        deterministic
        react: canonical example 
        cycle.js: uni-directional data flow
    monolithic vs. microservice architecture
      mono
        app as a cohesive unit of code
        pros
          easy to handle cross-cutting concerns (security, logging ...)
        cons
          difficult to isolate services
          harder to understand
      micro
        app made up of lots of smaller apps
        pros
          decoupled services: easier to recompose
          performance
        cons
          cross-cutting concerns 
            overhead of separate modules or
            new service layer to get traffic through
    asynch programming
      synch
        code executed sequentially
      asynch
        engine runs in event loop
        no operation blocks execution
        ex: ui
    
  LangNextDay02ErikMeijer-LangNextDay02ErikMeijer.mp4
    https://channel9.msdn.com/Events/Lang-NEXT/Lang-NEXT-2014/Keynote-Duality
    ambigous definitions
      automatically propagate change
      reactive is dead long live composing side effects
        state change = side effect
      architect: talks about code, but does not write it
    Lesli Lamport
      culture of software that impeded use of specification
      wonderful way of describing things precisely: mathematics
    effects
      4 fundamental effects
        sync-one: T
          function returns one result synchronously
          we are usually silent about effect
          haskell: IO<T>
        sync-many: Enumerable[T]
        async-one: Future[T]
          reactive/async programming
          because there is latency, fetch something from disk
        async-many: Observable[T]
          async data stream
    getters
      () => A
        gets nothing
        returns A
        if it was a real function
          it would return always same value
        this is a side effecting function
          thus it can return different values
      <: subtype
      covariant
        A <: B
          if A is a subtype of B
        () => A <: () => B
          getter of A is a subtype of B
        ex: vending machines
          A: coca cola
          B: soft drink
            coca cola is a subtype of soft drink
          () => B: vending machine of soft drink
          () => A <: () => B
            coca cola vending machine is a subtype of soft drink vending machine
    Functor
      map: ( A => B ) 
        => ( () => A ) => () => B
      takes: a function
      value: getter -> getter
        lifts that function over your structure
      given a function A=>B, return a function that takes getter of A => getter of B
      trick: how do you define this function?
        map f a = ()=>f(a())
          f: A=>B
          a: ()=>A
          a(): A
          f(A): B
        getter is an infinite list
      _me
        normally, map takes list and returns list
        but getters are lists too in their essence
        so input: ()=>A which is a getter (list)
        output: ()=>B which is another getter (list)
        so function signature: 
          map (A=>B) => ( ()=>A ) => ()=>B
        read as
          take a function
          then take a list (getter)
          return a list (getter)
    Side Effects
      ()=>Try[A]
        returns A but may throw Error
      ()=>Try[Option[A]]
        if no Exception, then either terminates normally or returns A
        tasteful combination of side effects
        dsl is bad because
          creates combinations of side-effects without good separation
    Self application: getter getter
      ()=>
        (()=>
          Try[Option[A]]
        )
      what is it?
        trait Enumerable[+T] {
          def getEnumerator(): Enumerator[T]
        }
        trait Enumerator[+T]{
          def next(): Boolean
          def current: T
        }
      _me
        Enumerator is a getter
          current:T
            ()=>T
        Enumerable returns a getter
          ()=>( ()=>T )
        thus Enumerable is a getter getter
      why java iterable iterator is bad?
        Iterator: hasNext, next
          next has side effect:
            it may return Exception
            then you can call current multiple times
      what is + sign?
        this is covariant 
          scala: 
            + covariant
            - contravariant
        getter is covariant
        getter of getter is covariant
    Lifting
      trait Enumerable[+T] {
        def getEnumerator(): Enumerator[T]
        def lift(f:Enumerator[T]=>Enumerator[S]):
          val that = this: Enumerable[S] = {
            new Enumerable[S]{
              def getEnumerator() = f(that.GetEnumerator())
            }
        }
      }
    Functor
      def map: (A=>B)
        => Enumerable[A]=>Enumerable[B]
      map f as = as.lift(_.map f)
    summary
      getters are simple functions
        made them more precise by looking at types
        they are functors
        i can lift functor of getter to a getter getter. and it is still functor
    Reverse all those 
      applying duality
    setters
      A=>()
        lazy consumer
        if it were haskell, then ignores the argument
        thus there is implicit IO()
          it is a side effecting function
    Contravariant
      A <: B
      B=>() <: A=>()
        reversed B and A
      a setter is a garbage can
      trick: 
        A <: B
          recyclable stuff: A
          as a subtype of trash
        if you ask for a garbage can for recyclables
    cofunctor
  Introduction to Reactive Programming - Andre Staltz - egghead
    event streams: similar to array
      var source = Rx.Observable.interval(400).take(9)
        .map(i => ['1', 'foo'][i]);
      var result = source;
      result.subscribe(x => console.log(x));
    ex: summing up
      var result = source
        .map(x => parseInt(x))
        .filter(x => !isNaN(x))
        .reduce((x,y) => x + y);
    ex: dom button. detect double clicks
      var button = document.querySelector('.button');
      var label = document.querySelector('h4');
      // take stream
      var clickStream = Rx.Observable.fromEvent(button, 'click');
      var doubleClickStream = clickStream
        .buffer(() => clickStream.throttle(250))
        .map(arr => arr.length)
        .filter(len => len === 2);
      doubleClickStream.subscribe(event => {
        labe.textContent = 'double click';
      });
      doubleClickStream
        .throttle(1000)
        .subscribe(suggestion => {
          label.textContent = '-';
        });
    why to use event streams?
      it allows you to specify the dynamic behavior of a value completely at the time of declaration
      ex:
        var streamA = Rx.Observable.of(3, 4);
        var streamB = streamA.map(a => 10 * a);
        streamB.subscribe(b => console.log(b));
    ex: github users suggestion ui
      var requestStream = Rx.Observable.just('https://api.github.com/users');
      requestStream.subscribe(requestUrl => {
        jQuery.getJSON(requestUrl)
          .done(response => {
            console.log(response);
          });
        });
      -->
      requestStream.subscribe(requestUrl => {
        var responseStream = Rx.Observable.fromPromise(jQuery.getJSON(requestUrl));
        responseStream.
        responseStream.subscribe(response => {
          console.log(response);
        });
      });
      -->
      var responseStream = requestStream
        .flatMap( requestUrl =>
          Rx.Observable.fromPromise(jQuery.getJSON(requestUrl))
        );
      responseStream.subscribe(response => {
        console.log(response);
      });
      --> render data on dom
  Some problems with React/Redux
    time facts
      2 h for cyclejs on react native dev
      7 h for react js on react native dev
    framework/tool criteria
      paradigm
        good signal to noise ratio
          each line 
            contributes to delivering features
            is semantic
            reads like a specifcation
              closer to "what i want the program to do"
            less manual wiring (noise and verbose)
      ecosystem
      features
    cyclejs
      better paradigm
    react/redux concepts required to learn is longer
      action types, action creator functions, store, middleware, adhoc effects, provider, containers
    ex:
      componentDidMount in react
        to dispatch actions in order to get app to start an operation
      vs.
      startWith() rxjs operator
    ex: jsx
      better: to use js functions instead of jsx for markup
      code
        // JSX
        <ul id="bestest-menu">
          {items.map( item =>
            <li className=".item" {...attrs(item.id)}>{item.title}</li>
          )}
        </ul>
        // hyperscript-helpers
        ul('#bestest-menu', items.map( item =>
          li('.item', attrs(item.id), item.title))
        );
      jsx
        more verbose
          <, > add no value
          {, } are noise
    functional but not really
      imperative traces
        oop, classes, callbacks, this
        dispatch, setState
        no clear interface for side effects
      cyclejs
        all read effects are inputs to main()
        all write effects are output of main()
      elm

## next

  https://github.com/staltz/fp-js-workshop
  https://github.com/staltz/rxjs-training 
  https://github.com/staltz/cyclejs-examples
  https://github.com/cyclejs-community/awesome-cyclejs
