# [Shiny - Reactivity - An overview](https://shiny.rstudio.com/articles/reactivity-overview.html)

library(shiny)

ui <- fluidPage(
  
  titlePanel("Hello Shiny!"),
  
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
   
  output$nthValue    <- renderText({ fib(as.numeric(input$n)) })
  output$nthValueInv <- renderText({ 1 / fib(as.numeric(input$n)) })

}

shinyApp(ui = ui, server = server)
     

