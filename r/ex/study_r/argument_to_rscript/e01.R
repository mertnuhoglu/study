# Rscript e01.R some_arg

args <- commandArgs(trailingOnly = TRUE)
print(args)
print(sprintf("args: %s", args[1]))
file_name = args[1]
