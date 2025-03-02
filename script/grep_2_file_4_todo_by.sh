#!/bin/sh

# Title: Write grep `TODO` outputs to files #prg/bash #f/myst
# Date: 20240528
#
# rfr: doc: Shell: Write grep outputs to files `prg/bash f/myst` `TODO` || ((5630288b-d73c-4773-a963-fea700a97da9))
#
# Usage:
#
#    ~/prj/study/script/grep_2_file_4_todo_by.sh "#p/beyza"

source ~/.zshenv
DATE=$(date +%Y%m%d)

if [ -z "$1" ]; then
	PERSON=""
	FILENAME=$DATE-grep-todo-by-all.txt
else
	PERSON="$1"
	PERSON_NAME=$(echo "${PERSON}" | sed -nE 's/.*\/([^/]+)$/\1/p')
	FILENAME="$DATE-grep-todo-by-${PERSON_NAME}.txt"
fi

DEST_DIR="${DIR_CLLOGSEQ}"
OUTPUT="${DIR_CLLOGSEQ}/out/${FILENAME}"
cd "${DEST_DIR}"

rg --vimgrep "\bTODO\b.*${PERSON}" | sort --reverse | rg -v "pages\/ndx\w+.md" | rg "(pages|journals)\/2024.*.md" | rg -v "pages\/.*-Tag-List-.*\.md" >"${OUTPUT}"
# echo "${DIR_CLLOGSEQ}/out/${FILENAME}"
printf "${DIR_CLLOGSEQ}/out/${FILENAME}" 
