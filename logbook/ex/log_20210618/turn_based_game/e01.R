library(shiny)
library(shinydashboard)

game_state = readxl::read_excel("game_data.xlsx", sheet = "game_state")

prepare_datastructure = function() {
	game_state = tibble::tibble(turn = 0)
	write.xlsx( game_state, "game_data.xlsx", sheetName = "game_state", append = T )
}

ui <- dashboardPage(
  shinydashboard::dashboardHeader(title = "Endemik Gözlem Analiz Rehberi"
		, shiny::tags$li(class = "dropdown", style = "padding: 8px;"
		)
		, shiny::tags$li(class = "dropdown", 
			shiny::tags$a(shiny::icon("map-marker-alt"), 
			href = "https://endemik-bitkiler.com",
			title = "endemik-bitkiler")
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

server <- function(input, output) {

	state <- shiny::reactiveValues(
		game_state = game_state
		, turn = max(game_state$turn)
	)

	output$sidebar = renderUI({
		shiny::fluidRow(
			shiny::column( width = 12
				, shiny::actionButton("button", "Turn")
			)
		)
	})
	output$body = shiny::renderUI({
    shiny::fluidRow(
      shiny::column( width = 12
					, shiny::dataTableOutput('game_state')
				)
      )
    )
	})
	output$game_state <- renderDataTable(game_state)

	observeEvent(input$button, {
		state$game_state = tibble::add_row(state$game_state, turn = (state$turn + 1) )
	})
}

runApp(shinyApp(ui, server), host="0.0.0.0",port=5050)

