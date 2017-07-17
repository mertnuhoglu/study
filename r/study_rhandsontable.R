 library(rhandsontable)

est_rhandsontable01 = function() {
	# https://github.com/jrowen/rhandsontable
	DF = data.frame(int = 1:10,
									numeric = rnorm(10),
									logical = TRUE,
									character = LETTERS[1:10],
									fact = factor(letters[1:10]),
									date = seq(from = Sys.Date(), by = "days", length.out = 10),
									stringsAsFactors = FALSE)

	# add a sparkline chart
	DF$chart = sapply(1:10, function(x) jsonlite::toJSON(list(values=rnorm(10))))

	rhandsontable(DF, rowHeaders = NULL) %>%
		hot_col("chart", renderer = htmlwidgets::JS("renderSparkline"))
}
