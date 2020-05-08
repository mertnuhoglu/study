library(shiny)
ui = fluidPage(
  titlePanel("nested tabsetPanel"),
	tabsetPanel(
		type = "tabs", 
		tabPanel("First", 
		  tabsetPanel(
				type = "tabs", 
				tabPanel("1.1", h3("1.1 tab")),
				tabPanel("1.2", h3("1.2 tab"))
		  )
		),
		tabPanel("Second", 
		  tabsetPanel(
				type = "tabs", 
				tabPanel("2.1", h3("2.1 tab")),
				tabPanel("2.2", h3("2.2 tab"))
		  )
		)
	)
)
server = function(input, output) {}
app = shinyApp(ui = ui, server = server)
runApp(app)

