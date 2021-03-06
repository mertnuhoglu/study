library(shiny)

ui = fluidPage(
	textInput("a", ""),
	textOutput("b")
)
server = function(input, output) {
	output$b = renderText({input$a})
}
app = shinyApp(ui = ui, server = server)
runApp(app)
