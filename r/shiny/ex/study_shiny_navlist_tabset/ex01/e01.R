library(shiny)
ui = fluidPage(
  titlePanel("Navlist panel example"),
  navlistPanel(
    "Header",
    tabPanel("First",
      h3("This is the first panel")
    ),
    tabPanel("Second",
      h3("This is the second panel")
    )
  )
)
server = function(input, output) {}
app = shinyApp(ui = ui, server = server)
runApp(app)
