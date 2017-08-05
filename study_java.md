  <url:file:///~/Dropbox (Personal)/projects/study/study/study_java.md>

# Study Java

    joinery DataFrame
      not: error: NA satırlarını atlıyor
        880	586	34	"AKDENIZ CAM BUSAN 3.OZEL ORGANIZE SANAYI BUSAN 3.OZEL ORGANIZE SANAYI KONYA"	"KONYA"	"SB"	NA	NA	0
        DataFrame, içinde "NA" olan satırları okuyamıyor, atlıyor. 
        çözüm: NA satırları boş geç
      select a column
        opt0        
          DataFrame<Object> df = DataFrame.readCsv("resources/miktar.csv")
            .retain("Miktar");
          Long[] adet0 = df.col("Miktar").toArray(new Long[0]);
          long[] adet = ArrayUtils.toPrimitive(adet0);
        opt1
          Object[] df =  DataFrame.readCsv("dentas.csv")
            .retain("IsYeri")
            .col("IsYeri")
            .toArray();
          Long[] df2 = Arrays.copyOf(df, df.length, Long[].class);
          Stream.of(df2).limit(3).forEach(System.out::println);
      filter rows     
        DataFrame<Object> df2 = df
          .select(vals -> (Long) vals.get(0) == facility);
      length of rows
        df.length()
    array
      take n elements - opt
        Stream.of(df).limit(3).forEach(System.out::println); 
        Arrays.stream(df).limit(3).forEach(System.out::println);
      convert Long[] to long[]
        long[] adet = ArrayUtils.toPrimitive(arrayLong);
      convert List<Object> to Long[]
        Long[] adet0 = myList.toArray(new Long[0]);
      convert Object[] to Long[]
        Long[] df2 = Arrays.copyOf(df, df.length, Long[].class);
        Stream.of(df2).limit(3).forEach(System.out::println);
      convert int[] to double[]
        int[] ints = {23, 31, 11, 9};
        double[] doubles = Arrays.stream(ints).asDoubleStream().toArray();
      convert Object[] to double[]
        Object[] as = {Long.valueOf(12), Long.valueOf(11)};
        long[] bs = ArrayUtils.toPrimitive( Arrays.copyOf(as, as.length, Long[].class) );
        double[] cs = Arrays.stream(bs).asDoubleStream().toArray();
    date      
      date to string
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyyMMdd");
        inFormat.format(new Date());
      string to date
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyyMMdd");
        inFormat.parse("20171130");
      pattern syntax
        http://tutorials.jenkov.com/java-internationalization/simpledateformat.html#pattern-syntax
        G Era designator (before christ, after christ)
        y Year (e.g. 12 or 2012). Use either yy or yyyy.
        M Month in year. Number of M's determine length of format (e.g. MM, MMM or MMMMM)
        d Day in month. Number of d's determine length of format (e.g. d or dd)
        h Hour of day, 1-12 (AM / PM) (normally hh)
        H Hour of day, 0-23 (normally HH)
        m Minute in hour, 0-59 (normally mm)
        s Second in minute, 0-59 (normally ss)
        S Millisecond in second, 0-999 (normally SSS)
        E Day in week (e.g Monday, Tuesday etc.)
        D Day in year (1-366)
        F Day of week in month (e.g. 1st Thursday of December)
        w Week in year (1-53)
        W Week in month (0-5)
        a AM / PM marker
        k Hour in day (1-24, unlike HH's 0-23)
        K Hour in day, AM / PM (0-11)
        z Time Zone
        ' Escape for text delimiter
        ' Single quote
      ex: DateFormat
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        // 2017-07-26T09:29Z
      ex: Java 8
        thisMoment = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmX")
                                      .withZone(ZoneOffset.UTC)
                                      .format(Instant.now());
        // 2017-07-26T09:32Z
      ex: LocalDateTime
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME; 
        LocalDateTime planDtTm;
        String date_str = "2011-12-03T10:15:30";
        planDtTm = LocalDateTime.parse(date_str, dtf);
        // 2011-12-03T10:15:30
    process
      call terminal command        
        String inputDir = "../data/test_data/";
        String[] arguments = new String[]{"Rscript", "src/main/r/excel_to_csv.R", inputDir};
        Process p = new ProcessBuilder(arguments).start();
      working directory
        pb.directory("/home");
      capture console output
        StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(), System.out::println);
        StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), System.out::println);
        new Thread(outputGobbler).start();
        new Thread(errorGobbler).start();
        p.waitFor();      
        class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumeInputLine;
        public StreamGobbler(InputStream inputStream, Consumer<String> consumeInputLine) {
            this.inputStream = inputStream;
            this.consumeInputLine = consumeInputLine;
        }
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumeInputLine);
        }
      }
    functions
      default parameter values
        String inputDir = args.length > 0 ? args[0] : "../data/test_data/";
        String yuzdeKazanc = args.length > 1 ? args[1] : "90";
        opt2
          void foo(String a, Integer... b) {
              Integer b1 = b.length > 0 ? b[0] : 0;
              Integer b2 = b.length > 1 ? b[1] : 0;
    gradle
      ref
        https://spring.io/guides/gs/gradle/
        /Users/mertnuhoglu/codes/java/gradle_01
      build.gradle
      gradle tasks
      gradle build
        output:
          build/libs/x.jar
      build file
        jar {..}
        dependencies { .. }
        repositories {..}
      gradle wrapper
        # use instead of gradle build
        gradle wrapper --gradle-version 2.13
          # new files: gradlew, gradle/wrapper/wrapper.jar
        # add it to source repo
        ./gradlew build
      make code runnable
        apply plugin: 'application'
        mainClassName = 'hello.HelloWorld'
      run app 
        ./gradlew run
      build jar/app
        gradle build
      run spring boot
        gradle bootRun
      gradle home
        https://stackoverflow.com/questions/18495474/how-to-define-gradles-home-in-idea
        task getHomeDir << {
            println gradle.gradleHomeDir
        }
        gradle getHomeDir
    File
      mkdir -p
        new File("out/dir/in").mkdirs();
    errors:
      IntelliJ'de Spring Boot app çalıştırınca, maven'dan sonra: Whitelabel Error Page çıkıyor
        sebep:
          maven içindeki hotswap pluginleri bir şekilde path'leri bozuyor olmalı
        çözüm:
          maven > clean
          sonra intellij > run
    mongo
      options for using mongo in java
        mongo java driver: uses java objects
        jongo: uses json 
        comparison
          shell
            db.friends.find({age: {$gt: 18}})
          java driver
            friends.find(new BasicDBObject("age",new BasicDBObject("$gt",18)))
          jongo
            friends.find("{age: {$gt: 18}}").as(Friend.class)
      mongo java driver
    spring
      spring rest
        tutorial: getting started
          https://spring.io/guides/gs/rest-service/
          goal
            request
              http://localhost:8080/greeting
            response
              {"id":1,"content":"Hello, World!"}
            request 02
              http://localhost:8080/greeting?name=User
            response 02
              {"id":1,"content":"Hello, User!"}
          structure
            class Application
              @SpringBootApplication
            class GreetingController
              @RestController
              @RequestMapping("/greeting")
              Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
                return new Greeting(counter.incrementAndGet(), String.format(template, name));
              public class Greeting {
                  private final long id;
                  private final String content;
          pom.xml
            <parent>
                <groupId>org.springframework.boot</groupId>
            <dependency>
                <groupId>org.springframework.boot</groupId>
            <dependency>
                <groupId>com.jayway.jsonpath</groupId>
    intellij
      create exception breakpoint
        https://www.jetbrains.com/help/idea/creating-exception-breakpoints.html
        Run > View Breakpoints > + > Java Exception Breakpoint > Choose Exception Class: .NullPointerException


