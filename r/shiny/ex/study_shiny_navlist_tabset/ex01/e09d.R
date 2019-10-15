library(shiny)
runApp(list(
  ui = pageWithSidebar(
    headerPanel('Dynamic Tabs'),
    sidebarPanel(
      numericInput("nTabs", 'No. of Tabs', 5)
    ),
    mainPanel(
      uiOutput('mytabs')  
    )
  ),
  server = function(input, output, session){
    output$mytabs = renderUI({
			tabsetPanel("Header", list(tabPanel("Master")))
    })
  }
))

