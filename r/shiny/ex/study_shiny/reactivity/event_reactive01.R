library(shiny)

ui = fluidPage(
	textInput("a", ""),
	textOutput("b"),
	actionButton("go", "")
)
server = function(input, output) {
	re = eventReactive(input$go, {input$a})
	output$b = renderText({re()})
}
app = shinyApp(ui = ui, server = server)
runApp(app)
