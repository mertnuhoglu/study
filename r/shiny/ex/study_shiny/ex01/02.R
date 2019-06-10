library(leaflet)

icon.fa <- makeAwesomeIcon(icon = "bicycle", markerColor = "red", library = "vehicles",
                           iconColor = "black")


# Marker + Label
leaflet() %>% addTiles() %>%
  addAwesomeMarkers(
    lng = -118.456554, lat = 34.078039,
    label = "This is a label",
    icon = icon.fa)

