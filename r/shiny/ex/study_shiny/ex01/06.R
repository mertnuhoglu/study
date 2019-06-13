library(dplyr)
library(leaflet)

leaflet() %>% addTiles() %>%
  addMarkers( layerId = "1", lng = -118.456554, lat = 34.078039, label = "orange") %>%
  addMarkers( layerId = "2", lng = -118.556554, lat = 34.078039, label = "red" ) %>%
  addMarkers( layerId = "3", lng = -118.556554, lat = 34.178039, label = "blue") %>%
  addMarkers( layerId = "4", lng = -118.456554, lat = 34.178039, label = "green") %>%
	removeMarker( layerId = "2" )
