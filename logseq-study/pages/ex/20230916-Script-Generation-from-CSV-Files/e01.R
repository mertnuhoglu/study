library(dplyr)
library(readr)

d01 <- readr::read_tsv("d01.tsv")
template <- readr::read_file("template01.txt")
	
d02 <- d01 |>
	mutate(cmd = glue::glue(template))
	
lines <- paste0(d02$cmd, collapse = "\n")
readr::write_file(lines, file = "out01.txt")
