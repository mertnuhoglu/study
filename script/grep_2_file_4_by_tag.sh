#!/bin/sh

# [[grep_2_file_4_by_tag.sh]] #prg/bash #f/myst #org/kms
#   id:: ae76beaa-e478-48c7-93fc-224e6ac435aa
#
# Title: Write grep `tag` outputs to files #prg/bash #f/myst
# Date: 20240607
#
#
# Usage:
#   grep_2_file_4_by_tag.sh "${DIR_MYREPO_LOGSEQ}" "#f/isfkr"

source ~/.zshenv

DEST_DIR=$1

DATE=$(date +%Y%m%d)

if [ -z "$2" ]; then
	TAG=""
	FILENAME=$DATE-grep-tag-by-${TAG}.txt
else
	TAG="$2"
	TAG_NAME=$(echo "${TAG}" | sed -nE 's/#([^/]+)\/([^/]+)$/\1-\2/p')
	# #p/huseyin -> p-huseyin
	FILENAME="$DATE-grep-tag-by-${TAG_NAME}.txt"
fi

OUTPUT="${DEST_DIR}/out/${FILENAME}"
# echo $OUTPUT
mkdir -p "${DEST_DIR}/out"
cd "${DEST_DIR}"

# GTD='\bg\/(gtd|gnd)\b'
# Note: `\b` ile `\/` birlikte olunca düzgün çalışmıyor:
# ripgrep: Word boundary doesn't match when forward slash is escaped `f/stfl f/qst prg/bash` || ((bf0e4946-1f37-47a5-8dc8-72e45396aa8e))
QRY="${TAG}"
rg --vimgrep "${QRY}" >"${OUTPUT}"

cat "${OUTPUT}" | sort --reverse | rg -v "pages\/ndx\w+.md" | rg "(pages|journals)\/2024.*.md" | rg -v "pages\/.*-Tag-List.*\.md" | sponge "${OUTPUT}"
echo "${OUTPUT}"
