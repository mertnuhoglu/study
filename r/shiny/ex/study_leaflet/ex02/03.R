library(shiny)

ui <- fluidPage(
  leafletOutput("map1")
)

make_map = function() {
	leaflet() %>% addCircleMarkers(
		lng = runif(10),
		lat = runif(10),
		layerId = paste0("marker", 1:10))
}

server <- function(input, output, session) {
	state = reactiveValues(
		map = make_map()
	)

  output$map1 = renderLeaflet(state$map)

  observeEvent(input$map1_marker_click, {
    leafletProxy("map1", session) %>%
      removeMarker(input$map1_marker_click$id)
  })
}

runApp(shinyApp(ui, server), launch.browser = T)
