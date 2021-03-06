# [Shiny - Create a reactive observer — observe](https://shiny.rstudio.com/reference/shiny/1.6.0/observe.html)

library(shiny)

values <- reactiveValues(A=1)

obsB <- observe({
  print(values$A + 1)
})
# 2

# Can use quoted expressions
obsC <- observe(quote({ print(values$A + 2) }), quoted = TRUE)
# 3

# To store expressions for later conversion to observe, use quote()
expr_q <- quote({ print(values$A + 3) })
obsD <- observe(expr_q, quoted = TRUE)
# 4

# In a normal Shiny app, the web client will trigger flush events. If you
# are at the console, you can force a flush with flushReact()
shiny:::flushReact()

