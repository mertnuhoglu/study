library(shiny)
ui = fluidPage(uiOutput("body"))
server = function(input, output) {
	output$body = shiny::renderUI({
		tagList(
		  shiny::tabsetPanel("Header" , tabPanel("Master", h3("First panel")))
		)
	})
}
app = shinyApp(ui = ui, server = server)
runApp(app)
