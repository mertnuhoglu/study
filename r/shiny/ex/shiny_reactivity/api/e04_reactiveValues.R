# [Shiny - Create an object for storing reactive values — reactiveValues](https://shiny.rstudio.com/reference/shiny/1.6.0/reactiveValues.html)

library(shiny)

# Create the object with no values
values <- reactiveValues()

# Assign values to 'a' and 'b'
values$a <- 3
values[['b']] <- 4

if (FALSE) {
# From within a reactive context, you can access values with:
values$a
values[['a']]
}

# If not in a reactive context (e.g., at the console), you can use isolate()
# to retrieve the value:
isolate(values$a)
# 3
isolate(values[['a']])
# 3

# Set values upon creation
values <- reactiveValues(a = 1, b = 2)
isolate(values$a)
# 1


