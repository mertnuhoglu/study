# code from: 007-widgets in [rstudio/shiny-examples](https://github.com/rstudio/shiny-examples)

library(shiny)

ui <- fluidPage(
  sidebarLayout(
    sidebarPanel(
      numericInput("obs", "Numeric:", 10),
      textInput("obsText", "Text:", 10),
      textAreaInput("obsTextArea", "Text Area:", 10),
			checkboxInput("obsCheck", "checkbox", FALSE),
			dateInput("obsDate", "date", FALSE),
			selectInput("obsSelect", "select",
									c("Long Value 01" = "v01",
										"Long Value 02" = "v02",
										"Long Value 03" = "v03")),


    ),

    mainPanel(
      verbatimTextOutput("print"),
      verbatimTextOutput("text"),
      textOutput("print2"),
      textOutput("text2"),
      verbatimTextOutput("obsText2"),
      verbatimTextOutput("obsTextArea2"),
      verbatimTextOutput("obsCheck2"),
      verbatimTextOutput("obsDate2"),
      verbatimTextOutput("obsSelect2"),

    )
  )
)

server <- function(input, output) {

	output$print = renderPrint({ input$obs })
	output$text = renderText({ input$obs })
	output$print2 = renderPrint({ input$obs })
	output$text2 = renderText({ input$obs })

	output$obsText2 = renderPrint({ input$obsText })
	output$obsTextArea2 = renderPrint({ input$obsTextArea })
	output$obsCheck2 = renderPrint({ input$obsCheck })
	output$obsDate2 = renderPrint({ input$obsDate })
	output$obsSelect2 = renderPrint({ input$obsSelect })

}

shinyApp(ui, server)

