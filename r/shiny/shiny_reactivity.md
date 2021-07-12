---
title: "Study Shiny Reactivity"
date: 2021-06-12T20:28:02+03:00 
draft: true
description: ""
tags:
categories: r, shiny
type: post
url:
author: "Mert Nuhoglu"
output: html_document
blog: mertnuhoglu.com

---

ref: [Shiny - Reactivity - An overview](https://shiny.rstudio.com/articles/reactivity-overview.html)

# Article: Reactivity - An overview

[Shiny - Reactivity - An overview](https://shiny.rstudio.com/articles/reactivity-overview.html)

## Reactive sources and endpoints

### example:

ref: `~/projects/study/r/shiny/ex/shiny_reactivity/art01/e01.R`

```r
  output$distPlot <- renderPlot({
    dist <- rnorm(input$obs)
    hist(dist)
  })
```

		| input$obs       | reactive source   |
		| output$distPlot | reactive endpoint |

## Reactive conductors

### example:

ref: `~/projects/study/r/shiny/ex/shiny_reactivity/art01/e02.R`

no reactive conductor

ref: `~/projects/study/r/shiny/ex/shiny_reactivity/art01/e02b.R`

with reactive conductor

```clj
  currentFib         <- reactive({ fib(as.numeric(input$n)) })
   
  output$nthValue    <- renderText({ currentFib() })
```

    | input$n         | reactive source    |
    | currentFib      | reactive conductor |
    | output$nthValue | reactive endpoint  |

## Implementations

    | role         | implementation | used for                |
    | r. source    | r. value       | return value            |
    | r. conductor | r. expression  | access and return value |
    | r. endpoint  | r. observer    | for side-effect         |

    | r. value    | input object                         | ReactiveValues object | like a list |
    | r. observer | wrapper of fct returned by renderX() | Observers object      |

Differences bw reactive expressions and observers

    | observers   | respond to reactive flush events             |
    | expressions | needs an observer as a descendant to execute |
    | expressions | return values                                |
    | expresions  | eager evaluation                             |
    | observers   | lazy evaluation                              |

# Article: Stop reactions with isolate()

[Shiny - Stop reactions with isolate()](https://shiny.rstudio.com/articles/isolation.html)

Isolation: avoid dependency

Observer, bir reactive kaynağa erişir, ancak ona bağımlı olmaz. Yani kaynak değişse de, gözlemci hemen ona tepki vermez.

## example:

ref: `~/projects/study/r/shiny/ex/shiny_reactivity/art01/e03.R`

```clj
  output$distPlot <- renderPlot({
    input$goButton

    dist <- isolate(rnorm(input$obs))
```

ref: `~/projects/study/r/shiny/ex/shiny_reactivity/art01/e03b.R`

```clj
    if (input$goButton == 0)
      return()
```

Böylece uygulama ilk açıldığında da plot oluşmaz.

ref: `~/projects/study/r/shiny/ex/shiny_reactivity/art01/e02b.R`

```clj
		isolate({ fib(as.numeric(input$n)) })
```

`isolate` ile reaktif expressionları da sarabilirsin.

# Article: How to understand reactivity in R

[Shiny - How to understand reactivity in R](https://shiny.rstudio.com/articles/understanding-reactivity.html)

    | class          | similar to   |
    | ReactiveValues | R values     |
    | Observers      | R epressions |

Observer creates a reactive context with the reactive value. 

Context contains an expression to be run if the value ever changes

		[ReactiveContext] - [Expression] - [Observer]
		[ReactiveContext] n-n [ReactiveValue]

Value değiştiğinde, Expression Observer'ı yeniden çalıştırır.

# API

    | reactive()       | create r. expression | reads r. expressions | returns r. expressions             |
    | observe()        | create r. observer   | reads r. expressions | no return value (for side-effects) |
    | eventReactive()  | create r.expression  |                      | returns r. expressions             |
    | observeEvent()   | create r.observer    |                      | no return value (for site-effects) |
    | reactiveValues() | list of r. values    |

Note: 

    | r.expression | for calculated values |
    | observer     | for side-effects      |

Event: r. expression/value triggering other calculations. Ex: clicking an `actionButton()`

They need more imperative style. `observe()` ve `isolate()` ile de yapılabilir, ama çok aşikar olmaz. Bunlar daha uygun: `observeEvent` and `eventReactive`. Bunlar `observe` ve `isolate` fonksiyonlarını sarar.

		| observeEvent() | perform a side-effect in response to an event | similar to observe() |
		| eventReactive() | calculate a value in response to an event | similar to reactive() |

Codes:


# Examples

## Form Field Examples id=g12299

### ex01: textOutput and verbatimTextOutput with renderPrint and renderText

ref: `~/projects/study/r/shiny/ex/shiny_reactivity/form_fields/e01.R`

`textOutput` fields

```clj
      verbatimTextOutput("print"),
      verbatimTextOutput("text"),
  ...
	output$print = renderPrint({ input$obs })
	output$text = renderText({ input$obs })
```

### ex02: reactiveValues

ref: `~/projects/study/r/shiny/ex/shiny_reactivity/form_fields/e02.R`

```clj
	state <- shiny::reactiveValues(
		obs3 = 0,
		obs4 = 0
	)
	observeEvent(input$obs, {
		state$obs3 <- input$obs
	})
	observe({
		state$obs4 <- input$obs
	})
```

