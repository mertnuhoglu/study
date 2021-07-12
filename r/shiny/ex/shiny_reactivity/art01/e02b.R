# [Shiny - Reactivity - An overview](https://shiny.rstudio.com/articles/reactivity-overview.html)

library(shiny)

ui <- fluidPage(
  
  sidebarLayout(
    sidebarPanel(
      sliderInput("n", 
                  "Number", 
                  min = 1, 
                  max = 20, 
                  value = 10)
    ),
    
    mainPanel(
      textOutput("nthValue")
      , textOutput("nthValueInv")
    )
  )
)

fib <- function(n) ifelse(n<3, 1, fib(n-1)+fib(n-2))

server <- function(input, output) {
  currentFib         <- reactive({ fib(as.numeric(input$n)) })
   
  output$nthValue    <- renderText({ currentFib() })
  output$nthValueInv <- renderText({ 1 / currentFib() })
}

shinyApp(ui = ui, server = server)
     

