
## lazy evaluation

  Non-standard evaluation lazyeval
    https://cran.r-project.org/web/packages/lazyeval/vignettes/lazyeval.html
    common use cases
      1. labelling
        plot(grid, sin(grid), type = "l")
      2. non-standard scoping
        df <- data.frame(x = c(1, 5, 4, 2, 3), y = c(2, 1, 5, 4, 3))
        with(df, mean(x))
      3. metaprogramming
  fun standardizing non standard evaluation.html
    http://www.carlboettiger.info/2015/02/06/fun-standardizing-non-standard-evaluation.html
    opt1: default
      x <- filter(all_taxa, Family == 'Scaridae')
    opt2: se version using formula
      .dots <- list(~Family == 'Scaridae')
      x1 <- filter_(all_taxa, .dots=.dots)
    opt3: treat arguments as variables (values of the factor on which we filter)
      family <- 'Scaridae'
      .dots <- list(~Family == family)
      x2 <- filter_(all_taxa, .dots=.dots)
    opt4: let both key and value to vary
      library(lazyeval)
      family <- 'Scaridae'
      field <- 'Family'
      .dots <- list(interp(~y == x, 
        .values = list(y = as.name(field), x = family))
      )
      x3 <- filter_(all_taxa, .dots=.dots)
    opt5: encapsulate this into a function
      query <- list(Family = 'Scaridae', SpecCode = 5537)
      dots <- lapply(names(query), function(level){
          value <- query[[level]]
          interp(~y == x, 
            .values = list(y = as.name(level), x = value)) 
      })
      x3 <-  filter_(all_taxa, .dots = dots) 

## dplyr

  Non-standard evaluation dplyr
    https://cran.r-project.org/web/packages/dplyr/vignettes/nse.html
    two versions of each function
      nse
        group_by
      se  
        group_by_
        inputs: "quoted"
      ex
        # NSE version:
        summarise(mtcars, mean(mpg))
        #>   mean(mpg)
        #> 1  20.09062
        # SE versions:
        summarise_(mtcars, ~mean(mpg))
        #>   mean(mpg)
        #> 1  20.09062
        summarise_(mtcars, quote(mean(mpg)))
        #>   mean(mpg)
        #> 1  20.09062
        summarise_(mtcars, "mean(mpg)")
        #>   mean(mpg)
        #> 1  20.09062
    how to quote inputs?
      formula
        ~mean(mpg)
      quote
        quote(mean(mpg))
      string
        "mean(mpg)"
      best:
        formula
          captures both
            expression to evaluate
            environment in which to evaluate
    setting variable names
      pass a list of quoted objects to .dots argument
        n <- 10
        dots <- list(~mean(mpg), ~n)
        summarise_(mtcars, .dots = dots)
        #>   mean(mpg)  n
        #> 1  20.09062 10
        summarise_(mtcars, .dots = setNames(dots, c("mean", "count")))
        #>       mean count
        #> 1 20.09062    10
    interp()
      ex
        library(lazyeval)
        # Interp works with formulas, quoted calls and strings (but formulas are best)
        interp(~ x + y, x = 10)
        #> ~10 + y
        interp(quote(x + y), x = 10)
        #> 10 + y
        interp("x + y", x = 10)
        #> [1] "10 + y"
        # Use as.name if you have a character string that gives a variable name
        interp(~ mean(var), var = as.name("mpg"))
        #> ~mean(mpg)
        # or supply the quoted name directly
        interp(~ mean(var), var = quote(mpg))
        #> ~mean(mpg)
      modifying functions by interp 
        interp(~ f(a, b), f = quote(mean))
        #> ~mean(a, b)
        interp(~ f(a, b), f = as.name("+"))
        #> ~a + b
        interp(~ f(a, b), f = quote(`if`))
        #> ~if (a) b
      values by interp  
        interp(~ x + y, .values = list(x = 10))
        #> ~10 + y

## rmarkdown

### bookdown docs id=g_10029

  ref
    bookdown docs <url:file:///~/Dropbox/mynotes/content/articles/articles_r.md#r=g_10029>
    https://bookdown.org/yihui/bookdown/get-started.html
    Publishing Web Site and Blog <url:file:///~/Dropbox/mynotes/general/processes.md#r=g_10033>

#### Preface

  why read this book
    benefits
      latex/word
        cannot turn back to another tool
        worrying too much about appearance
      interactions with shiny/html widgets
      feedback from reviewers through github
      collaborating through github
    missing features of markdown
      automatic numbering of figures
      cross references
      fine control of appearance
  structure
    ch01 02: basics
    ch03 04: fine tuning
    cl05
    ch06: publishing
  conventions
    no prompt (> +) for R code
    ## commented text output
    code: typewriter font
    function names: followed by parantheses
    double-colon :: object in a package
  get started
    install.packages("bookdown")
    examples
      https://github.com/yihui/bookdown-minimal
  usage
    each chapter = one .Rmd
    start with chapter title: # Title
    merges files by alphabetical order of filenames
      index.Rmd: first file if exists
    override behavior: _bookdown.yml
      rmd_files: order of files
        by outputs:
          rmd_files
            html: [..]
            latex: ["01.Rmd", "02.Rmd"]
    render output
      opt1:
        bookdown::render_book("..", "bookdown::gitbook")
      opt2: current chapter
        Rstudio > knit
      opt3: complete book
        Rstudio > Build > Build Book
    output format settings:
      site: "bookdown::bookdown_site"
      output: 
        bookdown::gitbook:
          lib_dir: "book_assets"

### RMarkdown Docs id=g_10030

  http://rmarkdown.rstudio.com/
  meta
    RMarkdown Docs <url:file:///~/Dropbox/mynotes/content/articles/articles_r.md#r=g_10030>
    codes
      /Users/mertnuhoglu/codes/rr/rmarkdown/
  How it works
    RStudio notebook interface
      1-example.Rmd
      code > Run chunk #+Enter
    Rendering Output
      opt1
        library(rmarkdown)
        render("1-example.Rmd")
      opt2
        RStudio > Knit #+K
  Code Chunks
    adding new code chunk
      opt1
        ```{r}
        ```
      opt2
        RStudio > Add Chunk #!i
    chunk options
      knitr options
        ```{..}
        ``{r message = FALSE, warning = FALSE}
      include = F
        don't print code and results
      echo = F
        don't print code only (to embed figures)
      message = F
        don't print messages generated by code
      warning = F
        don't print warnings
      fig.cap = ".."
        add caption
      error=TRUE
        suppress errors continue
    Global options 
      knitr::opts_chunk$set
    caching
      to improve performance
  inline code
    insert code into text
    `r `
  Code languages
    ex
      ```{bash}
      ls *.Rmd
      ```   
  Parameters
    opt1: put params into header
      params:
        data: "hawaii"
    opt2: params to render()
      render("5-parameters.Rmd", params = list(data = "aleutians"))
    opt3: RStudio > Knit with Parameters
    use them in code
      data(list = params$data)
  Tables
    opt1: knitr::kable
      library(knitr)
      kable(mtcars[1:5, ], caption = "A knitr kable.")
  Output formats
    opt1: render
      render("1-example.Rmd", output_format = "word_document")
    opt2: Rstudio > Knit to pdf/html/word
    available formats
      html_notebook
    html_notebook
      creating a notebook
        opt1: RStudio > New File > R Notebook
        opt2: header
          output: html_notebook
      by default: all Rmd files are treated as notebook
        inline results in Rstudio
        options > chunk output in inline 
        options > chunk output in console 
      run inline chunks #+enter
      saving automatically updates preview
      nb.html file
        saving notebook .Rmd -> creates .nb.html file
      version control
        check in both: .Rmd and .nb.html
    pdf_document
      requires Tex
      table of contents
        header
          pdf_document:
            toc: true
            toc_depth: 2
            number_sections: true
      fiture options
        fig_width fig_height
        fig_crop
        fig_caption
        dev
      data frame printing
        df_print: kable
    presentation
      outputs
        revealjs
          header
            output:
              revealjs::revealjs_presentation:
                theme: league
        ioslides
          header
            output:  ioslides_presentation
      title slide
        # title
      new slide
        ## title
      bullets
        -
      incremental bullets
        >- Viridis

## r shiny

  study code
    /Users/mertnuhoglu/projects/study/study_evammoa/study_shiny
      /Users/mertnuhoglu/projects/study/study_evammoa/study_shiny.R
      /Users/mertnuhoglu/projects/study/study_evammoa/shiny01/server.R
      /Users/mertnuhoglu/projects/study/study_evammoa/study_shiny/app.R

### shiny tutorial _shiny

  https://shiny.rstudio.com/tutorial/lesson1/
  examples in shiny
    shiny/inst/examples/
    library(shiny)
    runExample("01_hello")
  structure of a shiny app
    two components
      user interface script
        ui.R
      server script
        server.R
  ex
    /Users/mertnuhoglu/projects/study/r/shiny/lesson01/server.R
  run app
    runApp("my_app")
      my_app/ is the directory to look
    runApp("shiny/lesson01")
  ui.R
    sidebarLayout(
      sidebarPanel( sliderInput(..) )
      mainPanel( plotOutput("myPlot") )
  server.R
    output$myPlot = renderPlot( {
      x = ..
    } )
  ui user interface
    html tags
      h1(..), p
      mainPanel( h1(..), p(..) )
    control widgets
      actionButton
      SliderInput
    layout
      fluidRow( column(3, ..), .. )
  output
    output - ui.R
      htmlOutput
      plotOutput
      textOutput
    render - server.R
      renderText
      renderPlot
    code
      textOutput("id") # ui.R
      output$id = renderText(..) # server.R
    get user data
      input$var # server.R
      selectInput("var", ... ) # ui.R
  reactive
    data = reactive({ .. }) # server.R
    output$plot = renderPlot({ chartSeries(data(), .. }) # server.R
    server.R
  sharing
    opt1: server.R and ui.R
      from local
        runApp("myapp")
      from url
        runUrl(..)
        runGitHub( .. )
        runGist(..)
    opt2: web page
      shinyapps.io
      shiny server
  ref
    http://shiny.rstudio.com/reference/shiny/latest/ 
  articles
    http://blog.rstudio.org/2015/06/24/dt-an-r-interface-to-the-datatables-library/
      show datatables using js on front end
    http://blog.rstudio.org/2015/06/24/leaflet-interactive-web-maps-with-r/
      leaflet: interactive web maps

### shiny reference doc

doc
  reactive functions
    observe
      create a reactive observer
      args
        x: an expression
      value
        observer reference class object
          suspend()
          resume()
      comparison to reactive expression
        like
          reads reactive values
          calls reactive expressions
          reexecutes when dependencies change
        unlike
          no result
          can't be used as input to other reactive expressions
          execution strategy
            reactive: use lazy evaluation
              called by someone else
            observers: eager evaluation
      use
        for their side effects
    isolate
      create a non-reactive scope for an expression
      arg
        expr
      executes expr
        cannot cause reactive scope to be reevaluated 
      reading a reactive value
        causes
          relationship: between caller - reactive value
            change in value => caller to re-execute
            same for getting expr value
      difference of isolate
        read reactive value
        without that relationship
    reactiveValues
      create an object for storing reactive values
      similar to list
      with reactive capabilities
        calling expr
          takes a reactive dependency on that value
        notifies any reactive fun
      values taken from it: reactive
      itself: not
    reactivePoll
      args
        intervalMillis: wait to poll
        session
        checkFunc
        valueFunc
      check fun: cheap
      value fun: actual data
    reactive
      create a reactive expression
      args
        x: expr
      value
        function wrapped in "reactive"
      reactive expr
        result changes over time
    observeEvent and eventReactive
      calculated values: reactive expressions
      side-effect causing actions: observers
      more imperative, event handling style
      observeEvent
        perform an action in response to an event
      eventReactive
        create a calculated value that only updates in response to an event
      difference
        rv can be used in reactive functions like render*
    invalidateLater
      args
        millis
      use
        place in observer or reactive expression
        that object will be invalidated after the interval has passed
        reexecutes again
      alt
        reactiveTimer
          less safe
    reactiveFileReader
      args
        intervalMillis
        filePath
        readFunc
      invalidates when file changes
    showReactLog
      use
        options(shiny.reactlog=TRUE);
    makeReactiveBinding
      args
        symbol
        env
      turns a normal variable into rv
  examples
    renderPlot( callback )
      implicit observable (reactive value)
    reactive( inputExp )
      returns reactive values
    isolate
      don't invalidate reactive expression
      render( isolate(var) )
    observeEvent( rv, callback )
      isolated callback
      returns: observer object
    observe( callback )
      implicit callback
    eventReactive( rv, callback )
      returns: reactive expression object
    reactiveValues( elem1 = .. , elem2 = ... )
      returns: list of reactive values
  
### How to understand reactivity in R

  http://shiny.rstudio.com/articles/understanding-reactivity.html
  2500
  illusion of push
    information in R only travels by pull
  what is reactivity
    1. R expresions update themselves, if you ask
    2. nothing needs to happen instantly
    3. the app must do as little as possible
    4. think carrier pigeons, not electricity
  R expresions update themselves, if you ask
  nothing needs to happen instantly
    how quickly to rerun an expression after a change?
      if instantly => then it feels like you caused it
        illusion of reactivity
  the app must do as little as possible
    a system of alerts
      to make server know
        when an expression becomes out of date
    server runs expressions that are out of date
      flush
  think carrier pigeons, not electricity
    two special object classes
      reactivevalues
      observers
      ex
        input$a: reactive values object
        print(input$a): observer
    observer uses a reactive value
      registers a reactive context
        contains an expression to run
          callback
            is a command to re-run observer
    data structure
      observer: o1
        print(input$a)
      reactive value: rv1
        input$a = 50
      context: c1
        callback: cb1
          o1
      processing
        input$a changes
          rv1 is a list
            value changed by settor function: $<- or [[<-
            rv has own settor method
              sends callbacks in server
              callback is stored in context
        server saves callbacks in queue
          queue: list of observers that are out of date
          next flush: runs each callback
            callback: runs each out of date observer
      registration of callback
        observer stores a context in reactive value
          context contains callback
        reactive value receives multiple contexts
        reactive value changes
          releases all contexts it has
            called: invalidating the contexts
            callback placed in server's queue to be run on next flush
        callback of context:
          an R command 
          reexecutes the observer that created the context
            observer updates itself with new value of reactive values object
  class associations
    reactive value -> context -> callback -> expression -> reactive value

### How-to-start-shiny-complete.mp4

  How-to-start-shiny-complete-131218530.mp4
  fluidPage(...)
    generates html component
    can run on console
  basic shiny app
    ui = fluidPage()
    server = function(input, output) {}
    shinyApp(ui = ui, server = server)
  *Output functions
    their content are built in server side
    reactive
  *Input functions
    their content built in client side
    reactive
  server side
    output$hist = # code
      output is list
    render*() functions
      build the output using them
      build reactive output
      arg:
        code block that builds the object
    3 rules
      1. save output to output$
      2. build output with render*()
      3. access input with input$
  what is reactivity
    input$x -> expression -> output$y
    input$x -> run(this)
    input$x -> Update button -> output$y
  reactive toolkit
    logic
      reactive values notify the functions that use them
      object created by reactive functions respond
    render functions
      build reactive output 
      renderPlot( { hist(rnorm(input$num)) } )
        {..} 
          object will respond to every reactive value in the code
          code used to build object
    reactive()
      input$num -> 
        date <- reactive({ rnorm(input$num) }) ->
          output$hist = renderPlot({ hist(data()) })
          output$stats = renderPrint({ summary(data()) })
      reactive expressions cache their values
        return most recent value
        unless it has become invalidated
    isolate(): prevent reactions
      output$hist = renderPlot({
        hist(rnorm(input$num),
        main = isolate({input$title}))
      })
      input$title does not invalidate observer
    observeEvent(): trigger code
      ex: action button
        observeEvent( input$clicks, { print(input$clicks) } )
          respond to input$clicks
            explicit rv
          callback: {print(input$clicks)}
      returns
        observer object
    observe(): trigger code
      rv implicit like others
      ex:
        observe({ print(input$clicks) })
    eventReactive(): delay reactions
      update after clicking button
      ex
        data = eventReactive(input$go, { rnorm(input$num) })
      returns
        reactive expression object
    reactiveValues(): manage state
      creates a list of reactive values
      ex
        rv = reactiveValues(data = rnorm(100))
        observeEvent(input$norm, { rv$data = rnorm(100) })
        observeEvent(input$unif, { rv$data = runif(100) })
        output$hist = renderPlot({ hist(rv$data) })
    summary
      input$x -> expression -> output$y
        reactiveValues() -> reactive() -> render()
      input$x -> run(this)
        .. -> observeEvent(), observe()
      input$x -> Update button -> output$y
        .. -> eventReactive()
### shinySignals

  what is socketConnection
    http://blog.corynissen.com/2013/05/using-r-to-communicate-via-socket.html
      communicate two applications
    what is socket
      http://stackoverflow.com/questions/152457/what-is-the-difference-between-a-port-and-a-socket
        summary
          tcp socket
            an endpoint instance
          port
            virtualisation identifier defining a service endpoint
          tcp socket
            enpoint of a connection
          concurrent connections to a service endpoint
          only one listener socket for a given address/port combination
        exposition
          socket
            seems self-explanatory
            imagery
              endpoint
              into which you plug a network cable
            but not right exactly
          port
            a point of ingress or egress
          combination: IP address + port
            = endpoint or socket
            origin: RFC793
          TCP connection
            two endpoints/sockets
            endpoint: network address + port identifier
              not address/port
          purpose of ports
            differentiate multiple endpoints on a given network address
            say: port: virtualised endpoint
              multiple concurrent connections on a single network interface
          misapprehension: socket is connection
          TcpConnection
            two arguments:
              LocalEndpoint
              RemoteEndpoint
          socket is not ip address + port
            TCP demultiplexes 
    what is network segment
      https://en.wikipedia.org/wiki/Network_segment
      Physical Network Segmentation - Eli
        https://www.youtube.com/watch?v=cLNCYg5RorY
        Demarc
          = demarc point = demarcation point
          what is it?
            actual place
            for authority transfers
            from one party to another
          ex
            ISP: responsible for network until the building
            inside building: your responsibility
          testing connection
            at demarc point
              if you get signal: it is your home problem
              if you don't gen signal: is ISP problem
          inside building
            from building's box to floor's box
          
## tibble

  https://github.com/hadley/tibble
    creating tibbles
      as_tibble(iris)
      tibble(x = 1:5, y = 2)
      as_data_frame(..)
      data_frame(..)
    creating row by row
      tribble( ~x, ~y,
        1, 3,
        5, 2
      )
    comparison to data.frame
      never 
        converts strings into factors
        changes names of variables
        creates row.names()
      accessing nonexisting variables
        gives warning
      no drop = FALSE
        [ returns tibble
        [[ returns vector
    https://blog.rstudio.org/2016/03/24/tibble-1-0-0/

## purr

### purr 0.1.0

  http://blog.rstudio.org/2015/09/29/purrr-0-1-0/
  purr is for vectors
    dplyr for dataframes
  ex1: 
    split mtcars by cylinders
    fit linear model to each piece
    summarize
    extract R^2
    code
      mtcars %>%
        split(.$cyl) %>%
        map(~lm(mpg ~ wt, data = .)) %>%
        map(summary) %>%
        map_dbl("r.squared")
    grammar
      first arg: data vector
      second arg: .f: what to do with each piece
        such as
          function: summary()
          formula: converted to anonymous function
            ~ lm(mpg ~ wt, data = .)
            ===
            function(x) lm(mpg ~ wt, data = x)
          string/number: to extract components
            "r.squared"
            ===
            function(x) x[[r.squared]]
    map function variations
      input
      map(): take a vector return a list
        mp_lgl, map_int, map_dbl, map_chr
          take vector, return atomic vector
        flatmap: return arbitrary length vectors
      map_if(): only applies .f where .p is true
        ex
          iris %>% map_if(is.factor, as.character)
        map_at: works with integer vector of element positions
      map2(): take a pair of lists, iterate them in parallel
        ex
          map2(1:3, 2:4, c)
          map2(1:3, 2:4, ~ .x * (.y -1))
        map3: for triple lists
        map_n: in general
      invoke(): take a list of functions, call each one with supplied args
        invoke_logl, invoke_int, invoke_dbl, invoke_chr
        ex
          list(m1 = mean, m2 = median) %>%
            invoke_dbl(rcauchy(100))
      walk(): take vector, call function on piece, return original input
        useful for side effect functions
  purr and dplyr
    ex
      random_group = function(n, probs) {
        probs = probs / sum(probs)
        g = findInterval(seq(0, 1, length = n), c(0, cumsum(probs)), rightmost.closed = T)
        names(probs)[sample(g)]
      partition = function(df, n, probs) {
        n %>%
          replicate(split(df, random_group(nrow(df), probs)), F) %>%
          zip_n() %>%
          as_data_frame()
      }
      cv = mtcars %>%
        partition(100, c(training = 0.8, test = 0.2)) %>%
        mutate(
          model = map(training, ~ lm(mpg ~ wt, data = .)), 
          pred = map2(model, test, predict), 
          diff = map2(pred, test %>% map("mpg"), msd) %>% flatten()
        )
      cv
      mean(cv$diff)
  other functions
    zip_n(): turn a list of lists "inside-out"
    keep() and discard()
      compact()
    lift()
    cross2()
    negate, compose, partial
    is_*
    wrappers
      replicate -> rerun
      Reduce -> reduce
  design philosophy
    expressivenes of FP language
      anonymous functions too verbose in R
        predicate functions
          ~ .x + 1
        chains of transformations functions
          . %>% f() %>% g()
      R: weakly typed
        we can implement zip_n()
        but map2 is useful
          to separate which args are vectorised over
        functions designed to be output type-stable
          st. you can rely on output being as you expect
      R has named args
        instead of detect() and detectLast()
        we use named args
      instead of currying we use ... to pass extrac args
      instead of point free style, use pipe
  ## proto An R Package for Prototype Programming
    https://cran.r-project.org/web/packages/proto/vignettes/proto.pdf
  class proto
    addProto <- proto(x = rnorm(5), add = function(.) sum(.$x))
    addProto$add()
    addProto$x

### proto - documentation

  https://github.com/hadley/proto
  code
    # new object with variable a and method addtwice
    oo <- proto(a = 1, addtwice = function(., x) .$a <- .$a + 2*x)
    oo$addtwice(3)  # add twice 3 to 1
    oo$ls()         # "a" "addtwice"
    oo$a            # 7
    # create child object overriding a
    ooc <- oo$proto(a = 10)
    ooc$addtwice(1) # inherit addtwice from parent oo
    ooc$ls()        # "a"
    ooc$a           # 12 - addtwice from oo used "a" in ooc!

## caret

  http://www.edii.uclm.es/~useR-2013/Tutorials/kuhn/user_caret_2up.pdf
    Formula interface
      conventions for specifying models
        formula interface
          outcome ~ var1 + var2 + ...
        non-formula (matrix) interface
      formula interface features
        in-line specification: log(acres)
        converts factors into dummy variables
      non-formula (matrix) interface
        modelFunction(x = housePredictors, y = price)
        dummy vars should be created prior
    building and predicting models
      3 steps
        1. create model
          fit = knn(trainingData, outcome, k = 5)
        2. assess model
          print, plot, summary
        3. predict outcomes
          predict(fit, newSamples)
      single interface for different modeling packages
        predict(obj, type = "response")
        predict(obj, type = "posterior")
        obj class of
          lda, glm, gbm ...
    caret
      unified interface to 147 models
      model tuning using resampling
      helper funcs
      parallel processing
    blog
      http://appliedpredictivemodeling.com

## ggvis

  http://www.r-bloggers.com/goodbye-static-graphs-hello-shiny-ggvis-rmarkdown-vs-js-solutions/
    workflow
      setup shiny server
      ggvis > vega > d3
  http://blog.rstudio.org/2014/06/23/introducing-ggvis/
    grammar of graphics like ggplot
  http://ggvis.rstudio.com/interactivity.html
    <url:file:///Users/mertnuhoglu/Dropbox/mynotes/articles_datascience.R#tn=study_ggvis_interactivity = function() {>
    tutorial
    basic controls
      code
        mtcars %>%
          ggvis(~wt, ~mpg) %>%
          layer_smooths(span = input_slider(0.5, 1, value = 1)) 
      input controls  
        input_slider
        input_checkbox
        input_numeric
        input_text
        ...
      common arguments of input controls
        label
        id
        map
      usage of map
        input_slider(-5, 5, label = "lambda", map = function(x) 10 ^ x)
        maps input to 10^x
    assign an input to a variable
      slider = input_slider(10, 1000)
      mtcars %>% ggvis(..) %>%
        layer_points(... size := slider)
    shiny apps
      how to use in shiny apps
        server.R
          mtcars %>% ggvis(..) %>%
            bind_shiny("ggvis", "ggvis_ui")
        ui.R
          mainPanel(
            uiOutput("ggvis_ui"),
            ggvisOutput("ggvis")
          )
  http://vega.github.io/vega/
    vega: visualization grammar
      declarative format for visualization
        describe visualization in Json format
        generate views
  https://github.com/vega/vega/wiki/Vega-and-D3
    vega and d3
    not a replacement for d3
      d3: low level
        visualization kernel rather than a toolkit
      vega: leverages d3 
    vega motivations
      fast and customizable design
      more reusable and shareable
      programmatic generation
      performance
  http://ggvis.rstudio.com/layers.html
    <url:file:///Users/mertnuhoglu/Dropbox/mynotes/articles_datascience.R#tn=study_ggvis_layers = function() {>
    layers
      2 types
        simple
          correspond to vega marks
          represent geometric primitives 
            point, line, rectangle
        compound
          combine data transformations
    marks
      fundamental building block
      represent a type of geometric object
        ~ geom in ggplot
      not used directly usually
        used in their layer wrappers
      5 vega marks
        ~ corresponding ggplot
        mark_area ~ geom_ribbon
        mark_path ~ geom_path
        mark_rect ~ geom_rect
        mark_point ~ geom_point
        mark_text ~ geom_text
      2 optional args
        props or props()
        data
    adding a layer to a plot
      mtcars %>% ggvis() %>% layer_points(...)
        ... x = ~wt, y = ~mpg, stroke := "red"
    mark details
      layer_rects
        width, height
      layer_paths
        df = data.frame(x = c(1,1,2,2), y = c(..))
        df %>% ggvis(~x, ~y) %>% layer_paths()
      layer_ribbons
    grouping
      ggvis(..) %>% group_by(cyl) %>% layer_paths() 
    compound layers
      first data transformation then simple layers
      layer_histogram() = transform_bin() + mark_rect()
       
## ggplot2

### ggplot2-version-of-figures-in-“25-recipes-for-getting-started-with-r”

  http://www.r-bloggers.com/ggplot2-version-of-figures-in-“25-recipes-for-getting-started-with-r”/
  scatter plot
    ggplot(cars, aes(speed, dist)) +
      geom_point()
  bar chart
    heights = ddply(airquality, .(Month), mean)
    heights$Month = as.character(heights$Month)
    p1 = ggplot(heights, aes(x=Month, weight=Temp) +
      geom_bar()
  histogram
    data(Cars93, package="MASS")
    p = ggplot(Cars93, aes(MPG.city)) 
    p + geom_histogram()
    p + geom_histogram(
      binwidth = diff( range(Cars93$MPG.city) ) / 5
    )
  linear regression residuals
    m = lm( Sepal.Length ~ Sepal.Width, data = iris )
    r = residuals(m)
    yh = predict(m)
    d = data.frame(x = yh, y = r)
    p = ggplot(d, aes(x = yh, y = r)) 
    p + geom_point()
    p + geom_point() +
      geom_hline(yintercept = 0)
    p + geom_point() +
      geom_hline(yintercept = 0) +
      geom_smooth()
      
### ggplot-and-concepts-whats-right-and-whats-wrong

  http://www.harlan.harris.name/2010/03/ggplot-and-concepts-whats-right-and-whats-wrong/
  intro
    Leland Wilkinson
    3 representations
      data
      grammatical mapping: data -> graph
      graph
    ggplot: 4 representations
      data: data.frame object
      R syntax
      underlying ggplot object
      graph
    ggplot object
      hierarchical structure
      "+" operator
        adds a layer to object
          not always
      qplot object
        conceptual problem
        data not in data.frame format
        like slang (shortcuts)
      learn 
        ggplot() grammar
        hierarchical representations

### a-simple-introduction-to-the-graphing-philosophy-of-ggplot2

  http://www.r-bloggers.com/a-simple-introduction-to-the-graphing-philosophy-of-ggplot2/
  basic elements
    geometries
      geom_
      geometric shapes that represent data
    aesthetics
      aes()
      aesthetics of geometric and statistical objects
    scales
      scale_
      maps between data and aesthetic dimensions
    putting together
      data
        + geometry
        + aesthetic mappings to plot coordinates (position, color, size ....)
        + scaling of ranges
  ex
    some.data = data.frame(
      timer = 1:12,
      countdown = 12:1,
      category = factor(letters[1:3]))
    some.plot = ggplot(data = some.data,
      aes(x = timer, y = countdown)) +
      geom_point(aes(color = category)) +
      scale_x_continuous(limits = c(0, 15)) +
      scale_color_brewer(palette = "Dark2") +
      coord_fixed(ratio = 1)
    some.plot
  advanced parts
    statistical transformations
      stat_
      statistical summaries of data 
        quantiles, fitted curves, sums 
    coordinate systems
      coord_
      mapping coordinates into plane of data rectangle
    facets
      facet_
      arrangement of data into grid of plots
        latticing
    visual themes
      theme
      visual defaults

### A Layered Grammar of Graphics - Hadley Wickham

  http://vita.had.co.nz/papers/layered-grammar.pdf
  http://vita.had.co.nz/papers/layered-grammar.html


_me refcard
  observable = reactiveValue
  observer = listener = callback 
  js:
    input$a$addListener( function( observable ) {
      observable...
    }
  R:
    renderPlot( { input$a.. } )
  
# R_Cookbook.pdf

## ch11 Linear Regression and ANOVA

### Introduction

  simple linear regression
    y_i = b_0 + b_1 x_i + e_i
  goal: fit the model
  multiple variables
    predictors
    response = target = dependent
  questions
    is model significant
      check F
    are coefficients significant
      check coef's t and p 
      check their confidence intervals
    is model useful?
      check R^2
    does model fit well
      plot residuals
      check regression diagnostics
    does data satisfy assumptions
  Anova
    regression:
      creates a model
    anove:
      evaluates this model
    one way anova
      simplest
      do populations have different means?
      if distribution
        normal => oneway.test function
        other (non parametric) => kruskal.test function
    model comparison
      add/delete predictor
        did it improve model?
      anova() function
        compares two models
    Anova table
      anova() function
      constructs anova table
    ref
      Practical Regression and Anova using R, Julian Faraway
        https://cran.r-project.org/doc/contrib/Faraway-PRA.pdf
  Performing Simple Linear Regression
    solution
      lm(y ~ x)
  Performing Multiple Linear Regression
    solution
      lm(y ~ u + v + w)
        +: seperation symbol
        lm(y ~ u + v + w, data = df)
  Getting Regression Statistics
    solution
      m = lm(y ~ u + v + w)
      functions to extract stats
        anova(m)
          anova table
        coefficients(m)
          coef(m)
        confint(m)
          confidence intervals
        deviance(m)
          residual sum of squares
          SSE
        effects(m)
          orthogonal effects
        fitted(m)
          fitted y 
        residuals(m)
          resid(m)
        summary(m)
        vcov(m)
          var-covar matrix
      summary
        Adjusted R-squared
        F-statistic
      anova(m)
  Undestanding the regression Summary
    problem
      confusing summary(m)
    residual statistics
      median: -0.47
        OLS algorithm: mean zero
        if median negative => skew's direction
          deviation from normality
        if normal =>
          1Q = 2Q in magnitude
    coefficients
      estimate 
      how likely is true coefficient is zero? check t and p
        p: Pr(>|t|)
          p small => good
            it is not likely to be insignificant
    residual standard error
      residual standard error: 1.625 on 26 degrees of freedom
    R^2 (coef of determination)
      bigger => better
      fraction of variance of y that is explained
      use adjusted R^2
        accounts for number of variables
    F statistic
      is model significant?
      significant 
        if any coef is nonzero
      p-value: 0.003
        prorability that model is insignificant
  Performing Linear Regression without an Intercept
    problem
      force intercept to be zore
    solution
      lm(y ~ x + 0)
  Performing Linear Regression with Interaction Terms
    solution
      lm(y ~ u*v)
      corresponding model
        y_i = ... b_3 u_i v_i ...
    problem
      not every possible interaction
      u:v:w
        just product term of 
          u_i v_i w_i
        not other combinations
    syntax for other
      (u+v)^2
        all first order interactions
      (u+v)^3
      x*(u+v)
  Selecting the Best Regression Variables
      
# coursera data science specialization

## Practical Machine Learning

  self
    <url:file:///Users/mertnuhoglu/Dropbox/mynotes/articles_datascience.otl#tn=## Practical Machine Learning>

### caret package

  <url:file:///Users/mertnuhoglu/Dropbox/mynotes/articles_datascience.R#tn=study_caret = function() {>
  modelFit = train(type ~., data = training, method = "glm")
    ~.
      use all other variables except type

# ggplot2_Elegant_Graphics_for_Data_Analysis-Hadley_Wickham.pdf id=art_0002

## ch01 Introduction

  what is the grammar of graphics?
    wilkinson (2005)
    what is a statistical graphics
    grammar telss
      a statistical graphic is
        mapping
          from data
          to aesthetic attributes (color, shape)
          of geometric objects (points, lines)
      plot 
        contains
          statistical transformations of data (binning, summarising)
        drawn
          on scaled coordinate system
            maps
              data coordinates to
              plane of graphic
        scale
          maps
            data to
            values in aesthetic space (color, size)
      faceting
        used to generate
          same plot
          for different subsets of data

## ch02 qplot

  basic
    set.seed(1410)
    dsmall = diamonds[sample(nrow(diamonds), 100), ]
    qplot(carat, price, data = diamonds)
    qplot(log(carat), log(price), data = diamonds)
    qplot(carat, x*y*z, data = diamonds)
  aesthetic
    qplot(carat, price, data = dsmall, color = color)
    qplot(carat, price, data = dsmall, shape = cut)
    qplot(carat, price, data = dsmall, shape = cut, color = color)
    qplot(carat, price, data = diamonds, alpha = I(1/10))
      transparency
  plot geoms
    not only scatterplots
      any kind of plot by varying geom
    geom = ""
      point   scatterplot
      smooth  fits a smoother
      boxplot
      path draws lines
      line

### Understanding data.table Rolling Joins

  https://r-norberg.blogspot.com/2016/06/understanding-datatable-rolling-joins.html
  https://news.ycombinator.com/item?id=8795778
  data.table's `DT[i, j, by]` is quite consistent actually and is comparable to SQL's - i = where, j = select | update and by = group by.
    require(data.table)  
    DT = data.table(x=c(3:7), y=1:5, z=c(1,2,1,1,2))
    DT[x >= 5, mean(y), by=z]        ## calculates mean of y while grouped by z on 
                                     ## rows where x >= 5
    DT[x >= 5, y := cumsum(y), by=z] ## updates y in-place with it's cumulative sum 
                                     ## while grouped by z on rows where x >= 5

### dplyr unnest

  http://bioinfoblog.it/2015/02/the-most-useful-r-command-unnest-from-tidyr/comment-page-1/
    ex
      d1
        k v
        k1  v1,v2
      ->
      d2
        k v
        k1  v1
        k1  v2
      d1 %>%
        mutate( v = str_split(v, ",") ) %>%
        unnest(v)

## Hierarchical Queries

  https://en.wikipedia.org/wiki/Hierarchical_and_recursive_queries_in_SQL
  CONNECT BY
    https://cran.r-project.org/web/packages/data.tree/vignettes/applications.html
      data.tree
    https://cran.r-project.org/web/packages/data.tree/vignettes/data.tree.html

### data.tree

  vignette data.tree
    https://cran.r-project.org/web/packages/data.tree/vignettes/data.tree.html
    generating data.tree
      opt1: using Node api
        acme <- Node$new("Acme Inc.")
          accounting <- acme$AddChild("Accounting")
      opt2: dataframe using pathString
        df$pathString <- paste("world", GNI2014$continent, GNI2014$country, sep = "/")
        population <- as.Node(df)
      opt3: from file: yaml, csv, newick, csv (network format), json
        osList <- yaml.load_file("data/study_data_tree/data01.yaml")
        osNode <- as.Node(osList)
    node methods 
      entry point: root Node
      3 types of methods
        oo style actives (properties)
          Node$isRoot
        oo style methods
          Node$Prune(pruneFun)
        classical R methods
          Clone(node)
          rationales
            performance
            documentation
              ?Prune -> Node$Prune
      actives (properties)
        look like fields
          dynamically evaluated
        documentation
          ?Node
        ex
          population$isRoot
          ## [1] TRUE
          population$height
          ## [1] 3
      oo style methods
        Get
          sum(population$Get("population", filterFun = isLeaf))
            gets "population" of Nodes it traverses
        Prune
          filters
          has side-effect on original object
          population$Prune(pruneFun = function(x) !x$isLeaf || x$population > 1000000)
      traditional r methods
        Clone
          pop = Clone(acme)
          as.data.frame(acme)
          ToDataFrameNetwork(acme)
    tree navigation (climbing tree)
      by path
        acme$IT$Outsource
      by position
        acme$children[[1]]$name
      by fields
        single level
          acme$Climb(position = 1, name ="New Software")$path
          ==
          acme$Accounting$`New Software`
        multiple levels
          acme$Climb(position = c(1, 2))$path
    Custom fields
      ex
        software$cost = 100
      as function
        birds$species = function(self) sum(sapply(self$children, function(x) x$species))
        print(birds, "species")
    plotting
      networkD3
        library(networkD3)
        acmeNetwork <- ToDataFrameNetwork(acme, "name")
        simpleNetwork(acmeNetwork[-3], fontSize = 12)
    tree conversion
      dataframe
        as.data.frame(acme)
        ToDataFrameTree(acme)
        ToDataFrameTree(acme, "level", "cost")
        ToDataFrameNetwork(acme, "cost")
        ToDataFrameTypeCol(acme, 'cost')
      lists
        as.list(acme)
        ToListExplicit(acme$IT, unname = FALSE, nameName = "id", childrenName = "dependencies")

## Unclassified

  GOTO 2016 • Interoperability between R and other Languages • John D. Cook-gMNX1Zb7p58.mp4
    most general: Rcpp
    options
      HaskellR
      Jupyter
      R Markdown
      Beaker
  jade rjade
    https://www.opencpu.org/posts/jade-release-0-1/
      library(rjade)
      text <- readLines(system.file("examples/test.jade", package = "rjade"))
      tpl <- jade_compile(text, pretty = TRUE)
    call template
      tpl()
      tpl(youAreUsingJade = TRUE)
  v8 intro
    https://cran.r-project.org/web/packages/V8/vignettes/v8_intro.html
    ex: calling js from R
      library(V8)
      ct <- new_context()
      ct$eval('console.log("Bla bla")')
      # Bla bla
      ct$eval('console.warn("Heads up!")')
      # Warning: Heads up!
      ct$eval('console.error("Oh noes!")')
      # Error: Oh noes!
    interactive console
      ct$console()
      console.log("bar")
    ex
      # Evaluate some code
      ct$eval("var foo = 123")
      ct$eval("var bar = 456")
      ct$eval("foo + bar")
    ex
      # Create some JSON
      cat(ct$eval("JSON.stringify({x:Math.random()})"))
      {"x":0.9552653292194009}
    loading js libraries
      ct$source(system.file("js/underscore.js", package="V8"))
      ct$source("https://cdnjs.cloudflare.com/ajax/libs/crossfilter/1.3.11/crossfilter.min.js")
    data interchange:
      happens via json
      ct$assign("mydata", mtcars)
      ct$get("mydata")
    Using NPM packages in V8
      https://cran.r-project.org/web/packages/V8/vignettes/npm.html
      use browserify then use npm package
      ex
        shell
          npm install js-beautify
          echo "global.beautify = require('js-beautify');" > in.js
          browserify in.js -o bundle.js
        r
          ct <- v8()
          ct$source("~/Desktop/bundle.js")
          test <- "(function(x,y){x = x || 1; y = y || 1; return y * x;})(4, 9)"
          pretty_test <- ct$call("beautify.js_beautify", test, list(indent_size = 2))
          cat(pretty_test)
  
## Bookdown Manual

    https://bookdown.org/yihui/blogdown/get-started.html
    ch01: Get Started
      1.1 Installation
        install.packages("blogdown")
        library(blogdown)
        blogdown::update_hugo()
      1.2 A quick example
        cmd
          blogdown::new_site()
        LiveReload
      1.3 RStudio IDE
      1.4 Global Options
        Option name Default Meaning
          servr.daemon  FALSE Whether to use a daemonized server
          blogdown.author   The default author of new posts
          blogdown.ext  .md Default extension of new posts
          blogdown.subdir post  A subdirectory under content/
          blogdown.yaml.empty TRUE  Preserve empty fields in YAML?
        use ?Rprofile
    ch02: Hugo
      2.2 Configuration
        https://bookdown.org/yihui/blogdown/configuration.html
        2.2.1 TOML Syntax
          ex:
            key = value
            title = "My Site"
            relativeURLs = true
          ex: bracket = table
            [social]
              github = "..."
          ex: double brackets = array of tables
            [[menu.main]]
              name = "Blog"
              url = "/blog/"
            [[menu.main]]
              name = "About"
              url = "/about/"
        2.2.2 Options
      2.3 Content
        content/ directory
          structure of directory is arbitrary
        2.3.1 YAML metadata
          important fields:
            draft: true
            publishdate: <date>
              possible to set future date
            weight: 10
              order of pages when sorting them
            slug: <string>
              for permanent URLs
        2.3.2 Body
        2.3.3 Shortcode
          hugo shortcodes
          ex: tweet embedding
            {{< tweet <id> >}}
            ->
            blogdown::shortcode('tweet', '<id>')
      2.4 Themes
      2.5 Templates
        theme consists of: templates + web assets
        2.5.1 A minimal example
      2.7 Static files
        all files under static/ are copied to public/



