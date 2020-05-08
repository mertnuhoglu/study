library(shiny)
ui = fluidPage(
  titlePanel("sidebarLayout example"),
  sidebarLayout(
		sidebarPanel(
			h3("Sidebar")
		),
		mainPanel(
			tabsetPanel(
				type = "tabs", 
			  tabPanel("First", h3("first tab")),
			  tabPanel("Second", h3("second tab"))
			)
		)
  )
)
server = function(input, output) {}
app = shinyApp(ui = ui, server = server)
runApp(app)

