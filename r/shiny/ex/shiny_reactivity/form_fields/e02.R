# code from: 007-widgets in [rstudio/shiny-examples](https://github.com/rstudio/shiny-examples)

library(shiny)

ui <- fluidPage(
  sidebarLayout(
    sidebarPanel(
      numericInput("obs", "Numeric:", 10),
    ),

    mainPanel(
      verbatimTextOutput("obs2"),
      verbatimTextOutput("obs3"),
      verbatimTextOutput("obs4"),
    )
  )
)

server <- function(input, output) {
	state <- shiny::reactiveValues(
		obs3 = 0,
		obs4 = 0
	)
	observeEvent(input$obs, {
		state$obs3 <- input$obs
	})
	observe({ state$obs4 <- input$obs })

	output$obs2 = renderPrint({ input$obs })
	output$obs3 = renderPrint({ state$obs3 })
	output$obs4 = renderPrint({ state$obs4 })
}

shinyApp(ui, server)

