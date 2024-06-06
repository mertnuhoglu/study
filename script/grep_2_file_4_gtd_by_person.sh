#!/bin/sh

# Title: Write grep `gtd` outputs to files #prg/bash #f/myst
# Date: 20240605
#
#
# Usage:
#

DEST_DIR=$2

DATE=$(date +%Y%m%d)

if [ -z "$1" ]; then
	PERSON=""
	FILENAME=$DATE-grep-gtd-by-all.txt
else
	PERSON="$1"
	# PERSON_NAME=$(echo "${PERSON}" | sed -nE 's/.*\/([^/]+)$/\1/p')
	# #p/huseyin -> huseyin
	PERSON_NAME=$(echo "${PERSON}" | sed -nE 's/#?(.*)\/?([^/]+)$/\1-\2/p')
	# #p/huseyin -> p-huseyin
	FILENAME="$DATE-grep-gtd-by-${PERSON_NAME}.txt"
fi

OUTPUT="${DEST_DIR}/out/${FILENAME}"
mkdir -p "${DEST_DIR}/out"
cd "${DEST_DIR}"

GTD='\bg\/(gtd|gnd)\b'
Q1="${GTD}.*${PERSON}"
Q2="${PERSON}.*${GTD}"
QRY="(${Q1})|(${Q2})"
rg --vimgrep "${QRY}"  | sort --reverse | rg -v "pages\/ndx\w+.md" | rg "(pages|journals)\/2024.*.md" | rg -v "pages\/.*-Tag-List.*\.md" > "${OUTPUT}"
echo "${DEST_DIR}/out/${FILENAME}"
echo "${DEST_DIR}/out/${FILENAME}" | pbcopy
