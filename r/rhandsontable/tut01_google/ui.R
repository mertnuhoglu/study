library(rhandsontable)
library(shiny)

# run 

shinyUI(fluidPage(
	
	titlePanel("Edit and save a table"),
	sidebarLayout(
		sidebarPanel(
			helpText("Shiny app based on an example given in the rhandsontable package.", 
							 "Right-click on the table to delete/insert rows.", 
							 "Double-click on a cell to edit"),
			
			wellPanel(
				h3("Table options"),
				radioButtons("useType", "Use Data Types", c("TRUE", "FALSE"))
			),
			br(), 
			
			wellPanel(
				h3("Save table"), 
				div(class='row', 
						div(class="col-sm-6", 
								actionButton("save", "Save")),
						div(class="col-sm-6",
								radioButtons("fileType", "File type", c("ASCII", "RDS")))
				)
			)
			
		),
		
		mainPanel(
			wellPanel(
				uiOutput("message", inline=TRUE)
			),
			
			actionButton("cancel", "Cancel last action"),
			br(), br(), 
			
			rHandsontableOutput("hot"),
			br(),
			
			wellPanel(
				h3("Add a column"),
				div(class='row', 
						div(class="col-sm-5", 
								uiOutput("ui_newcolname"),
								actionButton("addcolumn", "Add")),
						div(class="col-sm-4", 
								radioButtons("newcolumntype", "Type", c("integer", "double", "character"))),
						div(class="col-sm-3")
				)
			)
			
		)
	)
))

