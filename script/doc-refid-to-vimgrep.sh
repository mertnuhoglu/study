#!/bin/sh

# Title: Grep all docs for refid and make vimgrep output
# Date: 20241012
# [[doc-refid-to-vimgrep.sh]]
#
# spcs: Index: rg: refid-to-fpath `f/myst prg/bash f/spcs` || ((373df2bd-6466-4458-86f5-563ed3864531))
#
# Usage:
#   doc-refid-to-vimgrep.sh
#
# Output: [[vimgrep-refid.txt]]

# ~/prj/study/script/tag_extract.sh
DIR="/Users/mertnuhoglu/prj/myrepo/scrap/out/refid-to-path"
DATE=$(date +%Y%m%d)
BASETITLE=vimgrep-refid
OUTPUT="$DIR/$BASETITLE.txt"
TSTITLE=$DATE-$BASETITLE
TSOUTPUT="$DIR/$FILENAME.txt"

SEARCH_DIR="/Users/mertnuhoglu/projects"
cd "${SEARCH_DIR}"

rg --no-messages --vimgrep '^[ -"#;/\t]+id:: [^ ]+$' "${SEARCH_DIR}" >"${OUTPUT}"
cp "${OUTPUT}" "${TSOUTPUT}"

echo "Completed: doc-refid-to-vimgrep.sh"
printf "${TSOUTPUT}" | pbcopy
printf "${OUTPUT}" | pbcopy
echo "${OUTPUT}" 
