  <url:file:///~/Dropbox/mynotes/content/articles/articles_java.md>

### Java 7 Features

    http://radar.oreilly.com/2011/09/java7-features.html
    diamond operator
      before
        Map<String, List<Trade>> trades = new TreeMap<String, List<Trade>>()
      now
        Map<String, List<Trade>> trades = new TreeMap<>()
    strings in switch statements
      switch(status) {
        caseNew:...
    automatic resource management
      before
        try {
          fos = newFileOutputStream(..)
          ..
        } catch(IOException e) {
          e...
        } finally{
          try{
            fos.close()
          } catch(IOException e) {
          }
        }
      now
        try(fos = newFileOutputStream(..)) {
          ..
        } catch(IOException e) {..}
        meta
          implement AutoCloseable
    numeric literals
      int thousand = 1_000
    improved exception handling
      now
        catch(ExceptionOne | ExceptionTwo e) {..}
    new file system API
      working with path
        Path path = Paths.get("/../")
        path.getParent()
        path.getFileName()
        Files.delete(path)
      file change notifications
        FileSystems.getDefault().newWatchService()
    iterating over a list
      http://stackoverflow.com/questions/18410035/ways-to-iterate-over-a-list-in-java
      opt1: basic for loop
          // Not recommended (see below)!
          for (int i = 0; i < list.size(); i++) {
                  E element = list.get(i);
                  // 1 - can call methods of element
                  // 2 - can use i to make index-based calls to methods of list
          }
      opt2: enhanced for loop
          for (E element : list) {
              // 1 - can call methods of element
              // ...
          }
      opt3: Iterator
          for (Iterator<E> iter = list.iterator(); iter.hasNext(); ) {
              E elem = iter.next();
              // 1 - can call methods of element
              // 2 - can use iter.remove()
          }
      opt4: ListIterator
          for (ListIterator<E> iter = list.listIterator(); iter.hasNext(); ) {
              E elem = iter.next();
              // 3 - can iter.add()
              // 4 - can iter.set(..) to replace
          }
      opt5: Functional Java
          list.map({E e => e++ }
      opt6: JDK8 style
          list.stream().forEach(e -> f(e));
    building a list
      opt01
          List list = new ArrayList();
      opt02: with generics
          List<T> list = new ArrayList<T>();
      opt03: initialized
          List<String> messages = Arrays.asList("Hello", "World!");
      opt04: immutable list
          Collections.unmodifiableList(..)
      opt05: empty immutable list
      opt06: List of chars
      opt07: from string array
          String[] m = new String[] { "elem1", "elem2" };
          List list = Arrays.asList(m);

### refcardz core java

    keywords
        abstract
            abstract class 
            abstract void
        boolean
        break
        case switch
        try catch
        char
        continue
        enum
        extends
        final
        finally
        float
        for
        if
        import
        instanceof
        interface
        int
        long
        new
        null
        package
        private
        protected
        public
        return
        static
        super
        switch
        throw
        throws
    common tasks
        List<T> s = new ArrayList<T>()
        for (String i: s) 
        Iterator<T> iter = s.iterator()
            while (iter.hasNext())
                String m = iter.next()
        items
            s.add("Hello")
            s.addAll(coll)
            s.get(i)
            s.set(i, "hello")
            s.insert(i, "hello")
            s.remove(i)
        String[] arr = new String[s.size()]
            s.toArray(arr)
        List<String> list = Arrays.asList(arr)
        list.sort()
    map
        Map<String, Person> map = new LinkedHashMap<String, Person>()
        for (Map.Entry<String, Person> entry : map.entrySet()) 
            String key = entry.getKey()
            Person value entry.getValue()
        Person value = map.get(str)
        map.put(key, value)
    printf
        System.out.printf("%4d", quantity)
        flags
            +
            0
            -   left justify
    MessageFormat
        String msg = MessageFormat.format("A {0} caused {1, date, long}", "hurricane", new GregorianCalendar(2009, 0, 15).getTime())
    regex
        common
            String[] words = str.split("\\s+")
            Pattern pattern = Pattern.compile("[0-9]+")
            Matcher matcher = pattern.matcher(str)
            replacement
                String result = matcher.replaceAll("#")
                while (matcher.find()) 
                    process(str.substring(matcher.start(), matcher.end()))
                for (int i = 1; i <= matcher.groupCount(); i++)
                    process(matcher.group(i))
    type conversions
        double d = Double.parseDouble(str)
        Double d = Double.valueOf(str)
        d.doubleValue()
        new Double(str)
        String.valueOf(dbl)
    general
        aString.equals("Max")
        Element[] arr = new Element[] { new Element(1), new Element(2) }
        access rules
                                    class       package subclass world
            public
            protected
            no modifier
            private
        StringBuilder sb = new StringBuilder()
            sb.append("")
    io
        csv reader
            opt1: while loop
                CSVReader reader = new CSVReader(new FileReader("data_user0.csv"));
                String [] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                        // nextLine[] is an array of values from the line
                        System.out.println(nextLine[0] + " " + nextLine[1] + " etc...");
                }
            opt2: read at once
                CSVReader reader = new CSVReader(new FileReader("data_user0.csv"));
                List myEntries = reader.readAll();
                System.out.println("myEntries.get(0) = " + myEntries.get(0));
        convert inputstream to string
            String s = IOUtils.toString(inputStream, encoding)
        read file into a string
            opt1: util/idiom
                static String readFile(String path, Charset encoding) throws IOException {
                    byte[] encoded = Files.readAllBytes(Paths.get(path));
                    return new String(encoded, encoding);
                }
            opt2: read lines 
                List<String> lines = Files.readAllLines(Paths.get(path), encoding);
            opt3: Scanner and "beginning of file" flag
                String text = new Scanner(new File(path), "UTF-8").useDelimiter("\\A").next()
            opt4: java8 join
                String text = String.join("\n", Files.readAllLines(Paths.get(path))
            opt5: java8 stream api
                String result = Files.lines(Paths.get(path))
                    .parallel()
                    .filter(line -> line.length() > 2)
                    .map(String::trim)
                    .collect(Collectors.joining())
        encoding
            StandardCharsets.UTF_8
    map
        iterate
            opt1: Iterator
                Iterator it = map.entrySet().iterator()
                while (it.asNext())
                    Map.Entry pair = (Map.Entry) it.next()
                    pair.getKey()
                    pair.getValue()
            opt2: keySet
                for (String key: map.keySet())
                for (Object value: map.values())
                for (Map.Entry<String, Object> entry: map.entrySet())
    filtering collections
        List<Person> beerDrinkers = persons.stream()
            .filter(p -> p.getAge() > 16).collect(Collectors.toList());
    arrays
        print array
            opt1: simple array
                System.out.println(Arrays.toString(arr))
            opt2: nested array
                String[][] deep = new String[][] {{"John", "Mary"}, {..}}
                System.out.println(Arrays.deepToString(arr))
            opt3: java8
                Arrays.asList(arr).stream().forEach(s -> System.out.println(s))
            opt3.2: java8
                Arrays.stream(arr).forEach(System.out::println)
        contains
            Arrays.asList(arr).contains(value)

### Java 8 Tutorial

    Java Streams Preview vs .Net High-Order Programming with LINQ id=g_10141
      Java Streams Preview vs .Net High-Order Programming with LINQ <url:file:///~/Dropbox/mynotes/content/articles/articles_java.md#r=g_10141>
      http://web.archive.org/web/20160316155815/http://blog.informatech.cr/2013/03/24/java-streams-preview-vs-net-linq/
      ex01: filtering
        String[] names = {"Sam","Pamela", "Dave", "Pascal", "Erik"};
        List<String> filteredNames = stream(names)
                   .filter(c -> c.contains("am"))
                   .collect(toList());
      ex02: filtering with array indexes
        String[] names = {"Sam","Pamela", "Dave", "Pascal", "Erik"};
        List<String> nameList;
        Stream<Integer> indices = intRange(1, names.length).boxed();
        nameList = zip(indices, stream(names), SimpleEntry::new)
              .filter(e -> e.getValue().length() <= e.getKey())
              .map(Entry::getValue)
              .collect(toList());
      ex03: projection with mapping
        nameList1.stream()
           .map(c -> "Hello! " + c)
           .forEach(System.out::println);
      ex04: projection by flattening
        Map<String, List<String>> map = new LinkedHashMap<>();
        map.put("UK", asList("Bermingham","Bradford","Liverpool"));
        map.put("USA", asList("NYC","New Jersey","Boston","Buffalo"));
        FlatMapper<Entry<String, List<String>>,String> flattener;
        flattener = (entry,consumer) -> { entry.getValue().forEach(consumer); };
        List<String> cities = map.entrySet()
               .stream()
               .flatMap( flattener )
               .collect(toList());
      ex04: partitioning: taking an arbitrary number of items
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13 };
        List<Integer> firstFour;
        firstFour = stream(numbers).limit(4)
          .boxed()
          .collect(toList());
      ex05: partitioning: take while
        String[] names  = { "Sam","Samuel","Dave","Pascal","Erik","Sid" };
        List<String> found;
        found = stream(names).collect(partitioningBy( c -> c.startsWith("S")))
          .get(true);
      ex06: skipping items
        String[] vipNames = { "Sam", "Samuel", "Samu", "Remo", "Arnold","Terry" };
        List<String> skippedList;
        skippedList = stream(vipNames).substream(3).collect(toList());
      ex07: ordering elements
        String[] friends = { "Sam", "Pamela", "Dave", "Anders", "Erik" };
        friends = stream(friends).sorted().toArray(String[]::new);
      ex08: order by length
        String[] friends = { "Sam", "Pamela", "Dave", "Anders", "Erik" };
        friends = stream(friends)
          .sorted(comparing((ToIntFunction<String>)String::length))
          .toArray(String[]::new);
      ex09: order by multiple criteria
        String[] fruits = {"grape", "passionfruit", "banana","apple", "orange", "raspberry","mango", "blueberry" };
        Comparator<String> comparator = comparing((Function<String,Integer>)String::length, Integer::compare)
          .thenComparing((Comparator<String>)String::compareTo);
        fruits = stream(fruits).sorted(comparator)
          .toArray(String[]::new);
      ex10: grouping 
        String[] names = {"Sam", "Samuel", "Samu", "Ravi", "Ratna",  "Barsha"};
        Map<Integer,List<String>> groups;
        groups = stream(names).collect(groupingBy(String::length));
      ex11: set: filter distinct elements
        String[] songIds = {"Song#1", "Song#2", "Song#2", "Song#2", "Song#3", "Song#1"};
        stream(songIds).distinct();
      ex12: union two sets
        List<String> friends1 = asList("Anders","David","James","Jeff","Joe","Erik");
        List<String> friends2 = asList("Erik","David","Derik");
        Stream<String> allMyFriends = concat(friends1.stream(),
                                             friends2.stream()).distinct();
      ex13: element: first 
        String[] otherFriends = {"Sam", "Danny", "Jeff", "Erik", "Anders","Derik"};
        Optional<String> found = stream(otherFriends).findFirst();
        Optional<String> maybe = stream(otherFriends).filter(c -> c.length() == 5)
          .findFirst();
        if(maybe.isPresent()) {
           //do something with found data
        }
      ex14: range: generate
        IntStream multiplesOfEleven = intRange(1,100).filter(n -> n % 11 == 0);
      ex15: all
        String[] persons = {"Sam", "Danny", "Jeff", "Erik", "Anders","Derik"};
        boolean x = stream(persons).allMatch(c -> c.length() == 5);
      ex16: any
        String[] persons = {"Sam", "Danny", "Jeff", "Erik", "Anders","Derik"};
        boolean x = stream(persons).anyMatch(c -> c.length() == 5);
      ex17: zip
        String[] salutations = {"Mr.", "Mrs.", "Ms", "Master"};
        String[] firstNames = {"Samuel", "Jenny", "Joyace", "Sam"};
        String lastName = "McEnzie";
        zip(
            stream(salutations),
            stream(firstNames),
            (sal,first) -> sal + " " +first)
        .forEach(c -> { System.out.println(c + " " + lastName); });
    http://winterbe.com/posts/2014/03/16/java-8-tutorial/
      Extension Methods: default implementation in interface
        interface X {
          default void f() {...}
        }
      Lambda expressions
        opt1
          Collections.sort(names, (String a, String b) -> { return b.compareTo(a); });
        before:
          Collections.sort(names, new Comparator<String>() {
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
          });
        opt2
          Collections.sort(names, (a,b) -> b.compareTo(a));
      Functional Interfaces
        @FunctionalInterface
        interface Converter<F, T> {
          T convert(F from);
        }
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
      Method and Constructor References
        static method references
          Converter<String, Integer> converter = Integer::valueOf;
        reference object methods
          class Something {
            String startsWith(String s) {..}
          }
          Something s = new Something()
          Converter<String, Integer> converter = something::startsWith;
        reference Constructor methods
          Person(String f, String l) {..}
          interface PersonFactory<P extends Person> {
            P create(String f, String l);
          }
          PersonFactory<Person> pf = Person::new
      Lambda Scopes
        can access
          final variables
          instance fields
          static variables
        Accessing local variables
          final int num = 1;
          Converter<Integer, String> sc = (from) -> String.valueOf(from + num);
          sc.convert(2)
        Accessing fields and static variables
        Default interface methods cannot be accessed
          Formula formula = (a) -> sqrt(a*100)
            error
      Built-in functional interfaces
        Predicates
          value: boolean
          one arg
          code
            Predicate<String> p = (s) -> s.length() > 0;
            p.test("foo"); // t
            p.negate().test("foo"); // f
            Predicate<Boolean> nonNull = Objects::nonNull;
        Functions
          one arg
          value: anything
          code
            Function<String, Integer> toInteger = Integer::valueOf;
            Function<String, String> backToString = toInteger.andThen(String::valueOf);
            backToString.apply("123");
        Suppliers
          no arg
          result: given generic type
          code
            Supplier<Person> ps = Person::new;
            ps.get(); // new Person
        Consumers
          one arg
          code
            Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
            greeter.accept(new Person("Luke", "Skywalker"));
        Comparators
          Comparator<Person> c = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
          Person p1 = new Person(..)
          Person p2 = new Person(..)
          c.compare(p1, p2);
          c.reversed().compare(p1, p2);
        Optionals
          to prevent NPE
          return Optional instead of null
          code
            Optional<String> opt = Optional.of("bam");
            opt.isPresent();
            opt.get();
            opt.orElse("fallback");
            opt.ifPresent((s) -> System.out.println(s.charAt(0)));
      Streams
        code
          List<String> c = new ArrayList<>();
          c.add("..")
          c.add("..")
        Filter
          c
            .stream()
            .filter((s) -> s.startsWith("a"))
            .forEach(System.out::println)
      refcard
        lambda
          Collections.sort(names, (a,b) -> b.compareTo(a))
            instead of
              new Comparator<String>() {
                public int compare(String a, String b) {..} }
        functional interface
          interface Converter<..> { T convert(..) }
          Converter<String, Integer> converter = (from) -> Integer.valueOf(from)
        method references
          Integer::valueOf
          object::startsWith
          Person::new
        built-in
          predicate
            Predicate<String> p = (s) -> s.length() > 0
            p.negate().test("foo")
          function
            Function<String, Integer> toInteger = Integer::valueOf;
            Function<String, String> backToString = toInteger.andThen(String::valueOf);
            backToString.apply("123");
          suppliers
            Supplier<Person> ps = Person::new
            ps.get()
          Consumer
            greeter.accept(..)
          Comparator
            c = (p1, p2) -> p1.firstName.compareTo(p2.firstName)
            c.compare(p1, p2)
          Optional
            Optional.of("bam")
              .isPresent()
              .get()
              .orElse("fallback")
              .ifPresent((s) -> System.out.println(s.charAt(0)))
        streams
          array.stream()
            .filter((s) -> s.startsWith("a"))
            .forEach(System.out::println)

### IntelliJ

    keyboard shortcuts
      ref
        https://www.jetbrains.com/help/idea/2016.2/keyboard-shortcuts-you-cannot-miss.html
        https://www.jetbrains.com/help/idea/2016.2/keyboard-shortcuts-and-mouse-reference.html
      mostly used
        available intention actions !enter
        switch between views  !f1
        switch tool/editor  ^tab
        show navigation #up
        live template #j
        surround with live template #!j

### rxjava

    code examples
      ref
        /Users/mertnuhoglu/projects/study/java/rxjava
      steps
        what are basic concepts
          Action
          Subscription
      concepts
        Observable
          emits data items
        Subscriber
          does some action on data items emitted
        relationship between them
          Observable invokes onNext() of subscriber
            for each item emitted by Observable
            onCompleted() after all
    ways to create observable
      Observable.just
      Observable.from
      Observable.create
    subscribe
      obs.subscribe(function)
      obs.subscribe(arg -> do(arg))
    new observable
      Observable.just("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!")
    subscribe
      tweets.subscribe(tweet -> System.out.println(tweet))
    from
      tweets = Arrays.asList(..)
      obs = Observable.from(tweets)
    subscribe
      public static Observable<Integer> naturalNumbers(int n) {
        return Observable.create(subscriber -> {
            IntStream.rangeClosed(1, n).forEach(number -> subscriber.onNext(number));
            subscriber.onCompleted();
        });
      }
      Observable<Integer> obs1 = naturalNumbers(10);
      obs1.subscribe(System.out::println)
    filter
      tweets.filter(tweet -> tweet.startsWith("RxJava"))
    action on elements
      .forEach(System.out::println)
    flatMap
      tweets.flatMap(tweet -> Observable.<String>from(tweet.split("")))

#### RxJava API

    ref
      https://github.com/ReactiveX/RxJava/wiki/Creating-Observables
    creating observables
      just()
        convert objects into observable
      from
        convert Iterable, Future, Array
      repeat
        emit items repeatedly
      repeatWhen
        repeat depending on emissions of a second observable
      create
        from scratch by means of a function
      defer
        create when a subscriber subscribes
      range
        emit range of integers
      interval
        emit integers spaced by a time interval
      timer
        emit single item after a delay
      empty
        emit nothing and complete
      error
        emit nothing and signal an error
      never
        emit nothing
    filtering operations
      filter
      takeLast
        emit last n items
      last
        emit last item
      lastOrDefault
      takeLastBuffer
      skip
        ignore first n items
      skipLast
        ignore last n items
      take
        emit only first n items
      first + takeFirst
      firstOrDefault
      elementAt
        emit item n
      elementAtOrDefault
      sample + throttleLast
        emit most recent withing periodic time intervals
      throttleFirst
      throttleWithTimeout + debounce
      timeout
      distinct
        suppress duplicate items
      ofType
      ignoreElements
    string
      byLine
        split on line-endings
      decode
        emit byte arrays
      encode
      from
        convert Reader stream
      join
        concat all strings
      split
      stringConcat
    transforming
      map
        apply a function to each item
      flatMap, concatMap, flatMapIterable
        transform items of an Observable into Observables, then flatten
      switchMap
      scan
        apply a function to each item, emit each successive value
      groupBy
        divide an observable into a set of observables, organized by key
      buffer
        gather items into bundles periodically, emit the bundles
      window
        subdivide items into observable windows, emit windows
      cast
    combining
      startWith
        emit first some specific items
      merge
        combine multiple observables
      mergeDelayError
      zip
        combine emitted items from 2+ observables
      and, then, when, (joins)
        combine items by means of Pattern and Plan intermediaries
      combineLatest
        combine latest item by 2 observables
      join, groupJoin
        combine when one item falls within a window of duration
      switchOnNext
    async
      start
        emit return value of a function
      toAsync, asyncAction, asyncFunc
        convert a function or Action into an observable
      startFuture
      deferFuture
      forEachFuture
      fromAction
      fromCallable
      fromRunnable
      runAsync
    conditional operators
      defaultIfEmpty
      doWhile
      ifThen
      skipUntil
      switchCase
      takeUntil
        emit from 1st obs until 2nd obs emits an item
      takeWhile
      whileDo
    Connectable
    error handling
      onErrorResumeNext
        emit items if encounters an error
      onErrorReturn
        emit a particular item if error
      onExceptionResumeNext
      retry
      retryWhen
    mathematical and aggregate
      averageInteger
      averageLong
      averageFloat
      max
      maxBy
      min
      sumInteger
      concat
      count
      reduce
        apply a function to each emitted item and emit only final accumulated value
      collect
      toList
      toMap
      toMultiMap
    next
      check examples of rxjava code
      check your twitter stars
      ask a few questions on sof
    @mine Differences between RxJava and Collections.stream
      My guess is that probably these are the differences:
      1. Pull based vs. push based
      Collection <Publisher>
      Consumer <Subscriber>
      In Collection.stream: probably, the consumer pulls the next event (item).
      In RxJava, the publisher pushes the next event (item).
      2. Sync vs. async
      3. Finite vs. potentially infinite stream
      4. Clearer API
      Ex:
         obs.filter(var -> var.getTitle().equals(variableTitle))
      vs.
         .filter(v -> v.getTitle().equals(variableTitle)).findFirst().orElse(new Variable(null));

# Unclassified 

    maven
      mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
    deployment of java apps id=g_10118
      deployment of java apps <url:file:///~/Dropbox/mynotes/content/articles/articles_java.md#r=g_10118>
      ref
        reverse proxy <url:file:///~/Dropbox/mynotes/content/articles/articles_code.md#r=g_10117>
      How do people deploy SpringBoot apps in production?
        https://www.reddit.com/r/java/comments/45x294/how_do_people_deploy_springboot_apps_in_production/
          opt1: fat jar
            fat jar: everything inside (spring boot)
            built in server included
            unprivileged port 
              opt: command line switch
                -server.port=80
              opt: application.properties
            fronting with nginx
              opt: reverse proxy
                safer
              opt: iptables redirects
                if good in jvm security
            init script that runs on server startup
              java -jar springboot.jar
      Spring Boot and Nginx integration
        http://stackoverflow.com/questions/37057772/spring-boot-and-nginx-integration
          using docker tomcat + docker nginx
      How do I run a spring boot executable jar in a Production environment?
        http://stackoverflow.com/questions/22886083/how-do-i-run-a-spring-boot-executable-jar-in-a-production-environment
        build executable jar
          maven
            <plugin>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-maven-plugin</artifactId>
              <configuration>
                  <executable>true</executable>
              </configuration>
            </plugin>
          mvn clean install
        init.d
          ln -s /var/yourapp/yourapp.jar /etc/init.d/yourapp
          init.d <url:file:///~/Dropbox/mynotes/content/articles/articles_code.md#r=g_10119>
        start app like
          /etc/init.d/yourapp start|stop|restart
        maybe
          chmod +x /etc/init.d/yourapp
      55. Installing Spring Boot applications
        http://docs.spring.io/spring-boot/docs/1.3.0.BUILD-SNAPSHOT/reference/htmlsingle/#deployment-service
        unix services
          opt1: init.d service
            logs: /var/log/<app>.log
            create symlink 
              sudo ln -s /var/myapp/myapp.jar /etc/init.d/myapp
            automatically start:
              update-rc.d myapp defaults <priority>
          opt2: systemd service
            add in:
              /etc/systemd/system/myapp.service


