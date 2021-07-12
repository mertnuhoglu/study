# [Shiny - Create a reactive expression — reactive](https://shiny.rstudio.com/reference/shiny/1.6.0/reactive.html)

library(shiny)

values <- reactiveValues(A=1)

reactiveB <- reactive({
  values$A + 1
})

# Can use quoted expressions
reactiveC <- reactive(quote({ values$A + 2 }), quoted = TRUE)

# To store expressions for later conversion to reactive, use quote()
expr_q <- quote({ values$A + 3 })
reactiveD <- reactive(expr_q, quoted = TRUE)

# View the values from the R console with isolate()
isolate(reactiveB())
# 2
isolate(reactiveC())
# 3
isolate(reactiveD())
# 4


