library(shiny)
ui = fluidPage(uiOutput("body"))
server = function(input, output) {
	output$body = shiny::renderUI({
    shiny::fluidRow(
      shiny::column( width = 12
				, h3("First panel")
			)
		)
    shiny::fluidRow(
      shiny::column( width = 12
				, h3("Second header")
			)
		)
	})
}
app = shinyApp(ui = ui, server = server)
runApp(app)
