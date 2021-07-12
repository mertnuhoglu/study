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

server <- function(input, output, session) {

	state <- shiny::reactiveValues(
	)

	output$body = shiny::renderUI({
    shiny::fluidRow(
      shiny::column( width = 12
				, textInput("obs", "Text:", 10)
      )
    )
	})
	output$sidebar = renderUI({
		shiny::fluidRow(
			shiny::column( width = 12
				, textOutput("obs2")
			)
		)
	})
	output$obs2 = renderText({ input$obs })

}

shinyApp(ui, server)


