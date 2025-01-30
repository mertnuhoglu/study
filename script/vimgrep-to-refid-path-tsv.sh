#!/bin/sh

# Title: vimgrep output to refid-to-path tsv
# Date: 20241012
# [[vimgrep-to-refid-path-tsv.sh]]
#
# spcs: Index: rg: refid-to-fpath `f/myst prg/bash f/spcs` || ((373df2bd-6466-4458-86f5-563ed3864531))
#
# Usage:
#   vimgrep-to-refid-path-tsv.sh
#
# Input: [[vimgrep-refid.txt]]
# Output: [[refid-to-path.tsv]]

# ~/prj/study/script/tag_extract.sh
source ~/.zshenv
DIR="/Users/mertnuhoglu/prj/myrepo/scrap/out/refid-to-path"
INPUT="${DIR}/vimgrep-refid.txt"
DATE=$(date +%Y%m%d)
BASETITLE=refid-to-path
OUTPUT="$DIR/$BASETITLE.tsv"
TSTITLE=$DATE-$BASETITLE
TSOUTPUT="$DIR/$FILENAME.tsv"

cat "${INPUT}" | sed -E 's/^([^:]+):([0-9]+):[0-9]+:.*id:: ([^ ]+)$/\3\t\1\t\2/' >"${OUTPUT}"
cp "${OUTPUT}" "${TSOUTPUT}"

printf "${TSOUTPUT}" 
printf "${OUTPUT}" 
echo "${OUTPUT}" 
