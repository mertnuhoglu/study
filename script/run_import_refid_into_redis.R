#!/usr/bin/env Rscript

# Title: Read refid-to-path.tsv and put refid-to-path pairs into redis
# Date: 20241012
# [[run_import_refid_into_redis.R]]
#
# spcs: Index: rg: refid-to-fpath `f/myst prg/bash f/spcs` || ((373df2bd-6466-4458-86f5-563ed3864531))
#

# [[rutils.R]]
library(rutils)
# import_refid_into_redis <- function() { || ((d47d61b5-fc3b-4135-b02f-c0708ca0b5a2))
rutils::import_refid_into_redis()

