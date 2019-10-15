library(shiny)
ui = fluidPage(uiOutput("body"))
server = function(input, output) {
	output$body = shiny::renderUI({
    shiny::fluidRow(
      shiny::column( width = 12
				, h3("First panel")
				, h3("Second panel")
			)
		)
	})
}
app = shinyApp(ui = ui, server = server)
runApp(app)
