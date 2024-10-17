#!/bin/sh

# [[grep-in-files-R.sh]] #prg/bash #f/myst #org/kms #prg/rlang
#   id:: 2756c0c0-2fba-4212-82b8-6e9f68181d39
#
# Title:
# Date: 20240906
#
#
# Usage:
#   grep-in-files-R.sh "\btidyverse\b"

QRY=$1
R_FILES="/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/pages/ex/20240109-Log/20240109-out_R_files02.txt"

grep-in.sh "${QRY}" "${R_FILES}"
