library(shiny)
outRow = shiny::fluidRow(
	shiny::column( width = 12,
		titlePanel("Navlist panel example"),
		navlistPanel(
			"Header",
			tabPanel("First",
				h3("This is the first panel")
			),
			tabPanel("Second",
				h3("This is the second panel")
			)
		)
	)
)
ui = fluidPage(outRow)
server = function(input, output) {}
app = shinyApp(ui = ui, server = server)
runApp(app)
