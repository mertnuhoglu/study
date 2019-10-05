library(shiny)

ui = fluidPage(
	textInput("a", "")
)
server = function(input, output) {
	rv = reactiveValues()
	rv$number = 5
}
app = shinyApp(ui = ui, server = server)
runApp(app)

