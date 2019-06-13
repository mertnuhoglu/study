library(shiny)
library(shinydashboard)
library(leaflet)

ui <- dashboardPage(
  dashboardHeader(),
  dashboardSidebar(),
  dashboardBody(
    tags$style(type = "text/css", "#map {height: calc(100vh - 80px) !important;}"),
    leafletOutput("map")
  )
)

server <- function(input, output) {
  output$map <- renderLeaflet({
    leaflet() %>% addTiles() %>% setView(29, 41, 11)
  })
}

runApp(shinyApp(ui, server), launch.browser = TRUE)


