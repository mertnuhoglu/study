#!/usr/bin/env Rscript

# Title: Print yesterday's date 
# Date: 20240531
# [[echo_yesterday.R]]
#
#

yesterday <- format(Sys.Date() - 1, "%Y%m%d")
cat(yesterday)

