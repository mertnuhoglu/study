# e01.R

#' Echo the parameter that was sent in
#' @param msg The message to echo back.
#' @get /echo
function(msg=""){
  list(msg = paste0("The message is: '", msg, "'"))
}

#' Plot out data from the iris dataset
#' @param spec If provided, filter the data to only this species (e.g. 'setosa')
#' @get /plot
#' @png
function(spec){
  myData <- iris
  title <- "All Species"

  # Filter if the species was specified
  if (!missing(spec)){
    title <- paste0("Only the '", spec, "' Species")
    myData <- subset(iris, Species == spec)
  }

  plot(myData$Sepal.Length, myData$Petal.Length,
       main=title, xlab="Sepal Length", ylab="Petal Length")
}

#' Increment the parameter by one
#' @param num The number to increment
#' @post /add1
function(num=0){
  list(y = as.numeric(num) + 1)
}

#' Increment the parameter by one
#' @param num The number to increment
#' @post /add2
add2 = function(num=0){
  list(y = as.numeric(num) + 2)
}

#' Increment the parameter by one
#' @param num The number to increment
#' @param x The number to add
#' @post /add3
add3 = function(num=0, x=0){
  list(y = as.numeric(num) + as.numeric(x))
}

