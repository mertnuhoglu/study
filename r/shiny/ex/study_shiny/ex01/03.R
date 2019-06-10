library(leaflet)

icon.fa <- makeAwesomeIcon(text = "1")


# Marker + Label
leaflet() %>% addTiles() %>%
  addAwesomeMarkers(
    lng = -118.456554, lat = 34.078039,
    icon = icon.fa)

