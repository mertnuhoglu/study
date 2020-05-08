library(shiny)
runApp(list(
  ui = fluidPage(
    headerPanel('Dynamic Tabs'),
    mainPanel(
      uiOutput('mytabs')  
    )
  ),
  server = function(input, output, session){
    output$mytabs = renderUI({
			tabsetPanel(id = "tabset01", tabPanel("Master"))
    })
  }
))

