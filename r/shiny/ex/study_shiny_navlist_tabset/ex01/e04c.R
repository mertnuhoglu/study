library(shiny)
ui = fluidPage(uiOutput("body"))
server = function(input, output) {
	output$body = shiny::renderUI({
		h3("First panel")
		#tabsetPanel("Header" , tabPanel("Master", h3("First panel")))
		h3("Second header")
	})
}
app = shinyApp(ui = ui, server = server)
runApp(app)
