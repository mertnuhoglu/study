# [Shiny - Stop reactions with isolate()](https://shiny.rstudio.com/articles/isolation.html)

library(shiny)

ui <- pageWithSidebar(
  headerPanel("Click the button"),
  sidebarPanel(
		sliderInput("n", 
								"Number", 
								min = 1, 
								max = 20, 
								value = 10)
    , actionButton("goButton", "Go!")
  ),
  mainPanel(
		textOutput("nthValue")
		, textOutput("nthValueInv")
  )
)

fib <- function(n) ifelse(n<3, 1, fib(n-1)+fib(n-2))

server <- function(input, output) {
  currentFib         <- reactive({ fib(as.numeric(input$n)) })
   
  output$nthValue    <- renderText({ 
		if (input$goButton == 0)
			return()
    input$goButton

		isolate({ fib(as.numeric(input$n)) })
	})

  output$nthValueInv <- renderText({ 1 / currentFib() })
}

shinyApp(ui = ui, server = server)
