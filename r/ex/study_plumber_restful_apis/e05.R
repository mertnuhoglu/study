# e05.R

library(plumber)
pr <- plumber::plumb("e04.R")
stat = PlumberStatic$new("./files")
pr$mount("/files", stat)
pr$routes
