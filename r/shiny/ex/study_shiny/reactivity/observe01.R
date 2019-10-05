library(shiny)

ui = fluidPage(
	textInput("a", ""),
	textOutput("b"),
	actionButton("go", "")
)
server = function(input, output) {
	observeEvent(input$go, {
		print(input$a)
	})
}
app = shinyApp(ui = ui, server = server)
runApp(app)
