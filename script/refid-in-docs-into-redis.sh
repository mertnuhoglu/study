#!/bin/sh

# Title: Grep all docs for refid and make vimgrep output
# Date: 20241012
# [[doc-refid-to-vimgrep.sh]]
#
# spcs: Index: rg: refid-to-fpath `f/myst prg/bash f/spcs` || ((373df2bd-6466-4458-86f5-563ed3864531))
#
# Usage:
#   refid-in-docs-into-redis.sh
#
# Output: [[vimgrep-refid.txt]]

source ~/.zshenv

echo $(date +"%Y%m%d-%H%M%S") started: refid-in-docs-into-redis.sh
/Users/mertnuhoglu/prj/study/script/doc-refid-to-vimgrep.sh
/Users/mertnuhoglu/prj/study/script/vimgrep-to-refid-path-tsv.sh
/Users/mertnuhoglu/prj/study/script/run_import_refid_into_redis.R
echo $(date +"%Y%m%d-%H%M%S") completed: refid-in-docs-into-redis.sh

