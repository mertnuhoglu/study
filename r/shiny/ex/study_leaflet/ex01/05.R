library(leaflet)

ic0 <- makeIcon(iconWidth = 16)
ic1 <- makeAwesomeIcon(text = 3)
ic2 <- makeIcon(iconWidth = 32)


# Marker + Label
leaflet() %>% addTiles() %>%
  addAwesomeMarkers( lng = -118.456554, lat = 34.078039, icon = ic0) %>%
  addMarkers( lng = -118.556554, lat = 34.078039, icon = ic0) %>%
  addMarkers( lng = -118.556554, lat = 34.178039, icon = ic1) %>%
  addMarkers( lng = -118.456554, lat = 34.178039, icon = ic2) 

