library(shiny)

ui <- shiny::fluidRow(title = 'Minimal app',
               shiny::numericInput("num_input", "Please insert a number n:", 0),
               shiny::textOutput('text_out')
               )

server <- function(input, output, session) {
  result <- shiny::reactive(input$num_input^2)
  output$text_out <- shiny::renderText(
    paste("The square of the number n is: n² =", result())
    )
}

shiny::shinyApp(ui, server)

