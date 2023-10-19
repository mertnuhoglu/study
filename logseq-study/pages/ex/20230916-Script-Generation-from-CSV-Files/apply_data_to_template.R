suppressMessages(library(dplyr))
suppressMessages(library(readr))

# e01.R scriptini generic hale getirir.
# template.txt ve input.tsv parametriktir.
# rfr: std: [[20230916-Script-Generation-from-CSV-Files]] <url:file:///~/prj/study/logseq-study/pages/20230916-Script-Generation-from-CSV-Files.md#r=g14709>

#
# Usage:
# Rscript e02.R d01.tsv template01.txt > out02.txt
#

args <- commandArgs(trailingOnly = TRUE)
data <- args[1]
template <- args[2]

# silence col type messages: [r - Suppress All Messages/Warnings with Readr read_csv function - Stack Overflow](https://stackoverflow.com/questions/55687401/suppress-all-messages-warnings-with-readr-read-csv-function)
d01 <- readr::read_tsv(data, show_col_types = FALSE)
template <- readr::read_file(template)
	
d02 <- d01 |>
	mutate(cmd = glue::glue(template))
	
lines <- paste0(d02$cmd, collapse = "\n")
cat(lines)
# readr::write_file(lines, file = "out02.txt")

