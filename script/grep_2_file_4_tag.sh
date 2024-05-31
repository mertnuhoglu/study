#!/bin/sh

# Title: Write grep `TODO` outputs to files #prg/bash #f/myst
# Date: 20240528
#
# rfr: doc: Shell: Write grep outputs to files `prg/bash f/myst` `TODO` || ((5630288b-d73c-4773-a963-fea700a97da9))
#
# Usage:
#
#    ~/prj/study/script/grep_2_file_4_tag.sh "g/gnd"

DATE=$(date +%Y%m%d)

if [ -z "$1" ]; then
	TAG=""
	FILENAME=$DATE-grep-tag.txt
else
	TAG="$1"
	TAG_NAME=$(echo "${TAG}" | sed -nE 's/.*\/([^/]+)$/\1/p')
	FILENAME="$DATE-grep-tag-${TAG_NAME}.txt"
fi

DEST_DIR="${DIR_MYREPO_LOGSEQ}"
OUTPUT="${DEST_DIR}/out/${FILENAME}"
cd "${DEST_DIR}"

rg --vimgrep "\b${TAG}\b" >"${OUTPUT}"
sort --reverse --output="${OUTPUT}" "${OUTPUT}"
echo "${TAG}"
# echo "rg --vimgrep \bTODO\b.*${TAG} >\"${OUTPUT}\""
echo "${OUTPUT}"
echo "${OUTPUT}" | pbcopy
