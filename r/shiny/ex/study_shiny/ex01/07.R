library(dplyr)
library(leaflet)

icon.fa <- makeAwesomeIcon(icon = "flag", markerColor = "red", library = "fa", iconColor = "black")

leaflet() %>% addTiles() %>%
  addAwesomeMarkers( layerId = "1", lng = -118.456554, lat = 34.078039, label = "orange", icon = icon.fa) %>%
  addAwesomeMarkers( layerId = "2", lng = -118.556554, lat = 34.078039, label = "red" , icon = icon.fa) %>%
  addAwesomeMarkers( layerId = "3", lng = -118.556554, lat = 34.178039, label = "blue", icon = icon.fa) %>%
  addAwesomeMarkers( layerId = "4", lng = -118.456554, lat = 34.178039, label = "green", icon = icon.fa) %>%
	removeMarker( layerId = "2" )

