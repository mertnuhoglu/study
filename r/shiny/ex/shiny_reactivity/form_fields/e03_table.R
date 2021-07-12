library(shiny)

ui <- fluidPage(
  sidebarLayout(
    sidebarPanel(
    ),

    mainPanel(
			tableOutput('table01'),
			dataTableOutput('table02'),
			DT::DTOutput('table03'),
    )
  )
)

server <- function(input, output) {
	output$table01 <- renderTable(iris[1:5, ])
	output$table02 <- renderDataTable(iris[1:5, ])
	output$table03 <- DT::renderDT(iris[1:5, ], options = list(scrollX = TRUE))
}

shinyApp(ui, server)


