library(shiny)
library(shinydashboard)
library(shinyWidgets)
library(dplyr)
library(lubridate)
library(glue)
library(rio)

ui <- dashboardPage(
  shinydashboard::dashboardHeader(title = "App Title"
		, shiny::tags$li(class = "dropdown", style = "padding: 8px;"
		)
		, shiny::tags$li(class = "dropdown", 
			shiny::tags$a(shiny::icon("map-marker-alt"), 
			href = "https://example.com",
			title = "my title")
		)
	)
  , shinydashboard::dashboardSidebar(collapsed = FALSE
		, shiny::uiOutput("sidebar")
	)
  , shinydashboard::dashboardBody(
    tags$style(type = "text/css", "#map {height: calc(100vh - 480px) !important;}")
    , shiny::uiOutput("body")
  )
)

bolgeler <- readxl::read_excel("data.xlsx", sheet = "bolgeler")

make_map <- function() {
	color_palette <- c("cyan", "yellow", "magenta", "green", "orange", "purple", "darkblue")

	map <- leaflet() %>% addTiles() %>%
		setView(lat=41.0082, lng=28.9784,zoom=10) 

	ids <- bolgeler$bolge_id %>% unique
	for(i in ids) {
		d0 <- dplyr::filter(bolgeler, bolge_id == i)
		map <- addPolygons(map, data = d0, lng = ~lng, lat = ~lat
											 , layerId = ~bolge_id
											 , weight = 1
											 , smoothFactor = 0.5
											 , opacity = 0.05, fillOpacity = 0.01
											 , fillColor = color_palette[i %% length(color_palette)]
											 )
	}
	map
}

server <- function(input, output, session) {

	state <- shiny::reactiveValues(
		map = make_map()
		, ui_state = "focus"
	)

	output$sidebar = renderUI({
		shiny::fluidRow(
			shiny::column( width = 12
				, shiny::actionButton("addObservation", "Gözlem Ekle")
				, shiny::actionButton("focus", "Odakla")
			)
		)
	})
	output$body = shiny::renderUI({
    shiny::fluidRow(
      shiny::column( width = 12
				, leaflet::leafletOutput("map")
      )
    )
	})

  output$map = renderLeaflet({ state$map })
	shiny::observeEvent(input$addObservation, { 
		state$ui_state = "addObservation"
	})
	shiny::observeEvent(input$focus, { 
		state$ui_state = "focus"
	})

  focus_grup <- function(bolge_id, lat, lng) {
    selected_grup <- bolgeler[bolgeler$bolge_id == bolge_id,]
    leafletProxy("map") %>% 
      fitBounds(min(selected_grup$lng), min(selected_grup$lat), max(selected_grup$lng), max(selected_grup$lat))
  }

  observe({
    event <- input$map_shape_click
		print(event)
    if (is.null(event))
      return()

		isolate({
			if (state$ui_state == "focus") {
				focus_grup(event$id, event$lat, event$lng)
			} else {
				state$observation_id_next = state$observation_id_next + 1
				leafletProxy("map") %>% 
					addMarkers( layerId = state$observation_id_next, lng = event$lng, lat = event$lat ) 
				state$observations = state$observations %>%
					tibble::add_row(observation_id = state$observation_id_next, lng = event$lng, lat = event$lat
													, flower = state$flower 
													, observation_date = state$observation_date
													, is_observed = state$is_observed
													, is_blossom = state$is_blossom
													, comment = state$comment
													)
				rio::export(state$observations, "egar_observations.xlsx")
			}
		})
  })
}

shinyApp(ui, server)


