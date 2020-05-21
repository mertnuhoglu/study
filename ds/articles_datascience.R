library("lubridate")
library("stringr")
library("pryr")
library("data.table")
library("pipeR"); library("plyr"); library("dplyr")
library("tidyr")
library("magrittr")
library("rlist")
library("XML")
library("xml2"); library("rvest")
library("reshape2")
library("gtools")
library("rjson")
library("compare")
library("readr")
library("ggvis")

is.blank <- function(x, false.triggers=FALSE){
    if(is.function(x)) return(FALSE) # Some of the tests below trigger
                                     # warnings when used on functions
	 if (is.list(x)) x = unlist(x)
    return(
        is.null(x) ||                # Actually this line is unnecessary since
        length(x) == 0 ||            # length(NULL) = 0, but I like to be clear
        all(is.na(x)) ||
        all(x=="") ||
        (false.triggers && all(!x))
    )
}
grepm = function(x, pattern, ...) {
	grep(pattern, x, ...)
}
grepv = partial(grepm, value = T)
greplm = function(x, pattern, ...) {
	grepl(pattern, x, ...)
}
vgrep = function(x, patterns, ...) {
	x %>% 
		grepm( patterns %>% paste(collapse="|"), ...) %>%
		unique
}
vgrepv = partial(vgrep, value = T)
subm = function(x, pattern, replacement, ...) {
	sub(pattern, replacement, x, ...)
}
gsubm = function(x, pattern, replacement, ...) {
	gsub(pattern, replacement, x, ...)
}

study_ggvis_interactivity = function() {
	mtcars %>%
		ggvis(~wt, ~mpg) %>%
		layer_smooths(span = input_slider(0.5, 1, value = 1)) %>%
		layer_points(size := input_slider(100, 1000, value = 100))			 
	mtcars %>%
		ggvis(~cyl, ~hp) %>%
		layer_smooths(span = input_slider(0.5, 1, value = 1)) %>%
		layer_points(size := input_slider(100, 1000, value = 100))			 
	mtcars %>% ggvis(x = ~wt) %>%
		layer_densities(
			adjust = input_slider(.1, 2, value = 1, step = .1, label = "Bandwidth adjustment"),
			kernel = input_select(
				c("Gaussian" = "gaussian",
					"Epanechnikov" = "epanechnikov",
					"Rectangular" = "rectangular",
					"Triangular" = "triangular",
					"Biweight" = "biweight",
					"Cosine" = "cosine",
					"Optcosine" = "optcosine"),
				label = "Kernel")
		)
	slider <- input_slider(10, 1000)
	mtcars %>% ggvis(~wt, ~mpg) %>%
		layer_points(size := slider) %>% 
		layer_points(fill := "red", opacity := 0.5, size := slider)

}

study_ggvis_layers = function() {
	mtcars %>% ggvis(~wt, ~mpg) %>%
		layer_points() %>%
		group_by(cyl) %>%
		layer_paths()
}

study_caret = function() {
	# coursera Practical machine Learning
	# <url:/Users/mertnuhoglu/Dropbox/mynotes/articles_datascience.otl#tn=## Practical Machine Learning>
	install.packages("caret", dependencies = c("Depends", "Suggests"))
	library(caret); library(kernlab); data(spam)
	inTrain <- createDataPartition(y=spam$type,
																p=0.75, list=FALSE)
	# > inTrain %>% head
	#      Resample1
	# [1,]         1
	# [2,]         2
	# [3,]         3
	# [4,]         4
	# [5,]         8
	# [6,]         9
	# > inTrain %>% class
	# [1] "matrix"
	training <- spam[inTrain,]
	testing <- spam[-inTrain,]
	dim(training)
	set.seed(32343)
	modelFit = train(type ~ ., data = training, method = "glm")
	modelFit
	modelFit$finalModel
	predictions = predict(modelFit, newdata = testing)
	predictions
	confusionMatrix(predictions, testing$type)
}

study_caret2 = function() {
	# caret vignette
	# https://cran.r-project.org/web/packages/caret/vignettes/caret.pdf
}

study_caret3 = function() {
	# user_caret_2up.pdf
	# http://www.edii.uclm.es/~useR-2013/Tutorials/kuhn/user_caret_2up.pdf
}
