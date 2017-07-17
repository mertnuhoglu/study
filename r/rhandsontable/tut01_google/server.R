library(rhandsontable)
library(shiny)
library(googlesheets)

DF <- data.frame(Value = 1:10, Status = TRUE, Name = LETTERS[1:10], Date = seq(from = Sys.Date(), by = "days", length.out = 10), stringsAsFactors = FALSE) 
outputDir <- "Apps/ithalattakip"
outdir = outputDir

shinyServer(function(input, output) {
	
	values <- reactiveValues()
	
	## Handsontable
	observe({
		if (!is.null(input$hot)) {
			values[["previous"]] <- isolate(values[["DF"]])
			DF = hot_to_r(input$hot)
		} else {
			if (is.null(values[["DF"]]))
				DF <- DF
			else
				DF <- values[["DF"]]
		}
		values[["DF"]] <- DF
	})
	
	output$hot <- renderRHandsontable({
		DF <- values[["DF"]]
		if (!is.null(DF))
			rhandsontable(DF, useTypes = as.logical(input$useType), stretchH = "all")
	})
	
	## Save 
	observeEvent(input$save, {
		finalDF <- isolate(values[["DF"]])
		fileName <- sprintf("%s_%s.csv", as.integer(Sys.time()), digest::digest(data))
		# Write the data to a temporary file locally
		filePath <- file.path(tempdir(), fileName)
		write.csv(data, filePath, row.names = FALSE, quote = TRUE)
		# Upload the file to Dropbox
		drop_upload(filePath, dest = outputDir)
	}
	)
	
	## Cancel last action    
	observeEvent(input$cancel, {
		if(!is.null(isolate(values[["previous"]]))) values[["DF"]] <- isolate(values[["previous"]])
	})
	
	## Add column
	output$ui_newcolname <- renderUI({
		textInput("newcolumnname", "Name", sprintf("newcol%s", 1+ncol(values[["DF"]])))
	})
	observeEvent(input$addcolumn, {
		DF <- isolate(values[["DF"]])
		values[["previous"]] <- DF
		newcolumn <- eval(parse(text=sprintf('%s(nrow(DF))', isolate(input$newcolumntype))))
		values[["DF"]] <- setNames(cbind(DF, newcolumn, stringsAsFactors=FALSE), c(names(DF), isolate(input$newcolumnname)))
	})
	
	## Message
	output$message <- renderUI({
		if(input$save==0){
			helpText(sprintf("This table will be saved in folder \"%s\" once you press the Save button.", outdir))
		}else{
			outfile <- ifelse(isolate(input$fileType)=="ASCII", "table.txt", "table.rds")
			fun <- ifelse(isolate(input$fileType)=="ASCII", "dget", "readRDS")
			list(helpText(sprintf("File saved: \"%s\".", file.path(outdir, outfile))),
					 helpText(sprintf("Type %s(\"%s\") to get it.", fun, outfile)))
		}
	})
	
})

