library(shiny)
ui = fluidPage(
  title = 'Examples of DataTables',
	tabsetPanel(
		id = 'dataset',
		tabPanel('tab1', h3("Panel1")),
		tabPanel('tab2', h3("Panel2"))
	)
)
server = function(input, output) {
}
app = shinyApp(ui = ui, server = server)
runApp(app)
