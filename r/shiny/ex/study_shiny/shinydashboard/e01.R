library(shiny)
library(shinydashboard)

ui <- dashboardPage(
  dashboardHeader(),
  dashboardSidebar(),
  dashboardBody(
    tags$style(type = "text/css", "#map {height: calc(100vh - 80px) !important;}"),
		textOutput("body")
  )
)

server <- function(input, output) {
	output$body = renderText({"Body"})
}

runApp(shinyApp(ui, server), host="0.0.0.0",port=5050)


