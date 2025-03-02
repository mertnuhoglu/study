#!/bin/sh

# [[grep_2_file_4_gtd_by_person.sh]] #prg/bash #f/myst #org/kms
#   id:: 9fa6e502-9543-443e-9a6a-6f5242a071b5
#
# Title: Write grep `gtd` outputs to files #prg/bash #f/myst
# Date: 20240605
#
#
# Usage:
#   grep_2_file_4_gtd_by_person.sh <regex-query>
#
# rfr: vim-mapping:
#   local edit_shell_last = { -- SPC ichl || ((83e82028-b255-433b-9eb2-caea9e573a16))
#   local shell_grep = {  -- SPC icg || ((53a8da67-00eb-481a-bbd2-245a1608c727))
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
	PERSON_NAME=$(echo "${PERSON}" | sed -nE 's/#([^/]+)\/([^/]+)$/\1-\2/p')
	# #p/huseyin -> p-huseyin
	FILENAME="$DATE-grep-gtd-by-${PERSON_NAME}.txt"
fi

OUTPUT="${DEST_DIR}/out/${FILENAME}"
# echo $OUTPUT
mkdir -p "${DEST_DIR}/out"
cd "${DEST_DIR}"

GTD='\bg\/(gtd|gnd)\b'
Q1="${GTD}.*${PERSON}"
Q2="${PERSON}.*${GTD}"
QRY="(${Q1})|(${Q2})"
# echo "OUTPUT: " "${OUTPUT}"
rg --vimgrep "${QRY}" >"${OUTPUT}"
rg --vimgrep "\bTODO\b.*${PERSON}" >>"${OUTPUT}"
cat "${OUTPUT}" | sort --reverse | rg -v "pages\/ndx\w+.md" | rg "(pages|journals)\/2024.*.md" | rg -v "pages\/.*-Tag-List.*\.md" | sponge "${OUTPUT}"
echo "${OUTPUT}"
