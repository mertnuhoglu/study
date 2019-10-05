library(shiny)

ui = fluidPage(
	textInput("a", ""),
	textInput("z", ""),
	textOutput("b")
)
server = function(input, output) {
	re = reactive({paste(input$a, input$z)})
	output$b = renderText({re()})
}
app = shinyApp(ui = ui, server = server)
runApp(app)
