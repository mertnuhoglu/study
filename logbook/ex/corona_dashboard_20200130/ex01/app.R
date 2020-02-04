library(shiny)

ui = fluidPage(
	numericInput(inputId = "n", "Sample Size", value = 25),
	plotOutput(outputId = "hist")
)
server = function(input, output) {
	output$hist = renderPlot({
		hist(rnorm(input$n))
	})
}
app = shinyApp(ui = ui, server = server)
runApp(app)
