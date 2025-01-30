#!/bin/sh

# Title: Write grep `gtd` outputs to files #prg/bash #f/myst
# Date: 20240605
#
#
# Usage:
#

source ~/.zshenv

PERSON=$1
DEST_DIR="${DIR_MYREPO_LOGSEQ}"
~/prj/study/script/grep_2_file_4_gtd_by_person.sh "${PERSON}" "${DEST_DIR}"
