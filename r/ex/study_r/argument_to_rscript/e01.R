# Rscript e01.R some_arg

# 
# ❯ Rscript e01.R arg01 arg02
# [1] "arg01" "arg02"
# [1] "args: arg01"
#

args <- commandArgs(trailingOnly = TRUE)
print(args)
print(sprintf("args: %s", args[1]))
file_name = args[1]
