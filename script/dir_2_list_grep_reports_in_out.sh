#!/bin/sh

# Title: Directory to List all Grep Outputs in out directory
# Date: 20250204
# [[dir_2_list_grep_reports_in_out.sh]]
#
# Usage:
#   dir_2_list_grep_reports_in_out.sh
#
# Lists all files with names such as:
#   20250204-grep-todo-by-all.txt
#   20250204-grep-gtd-by-all.txt
# Searches the following directories:
#   /Users/mertnuhoglu/prj/myrepo/logseq-myrepo/out
#   /Users/mertnuhoglu/prj/collabryio/cldocs/cllogseq/out

BASE_DIR=$HOME/prj/collabryio/cldocs/cllogseq
DIR=$BASE_DIR/out
DATE=$(date +%Y%m%d)
FILENAME=$DATE-document-list-out-cllb.otl
OUTPUT="$BASE_DIR/out/$FILENAME"

sh ~/prj/study/script/dir_2_list_all.sh $DIR >"$OUTPUT"

echo "$OUTPUT"

BASE_DIR=$HOME/prj/myrepo/logseq-myrepo
DIR=$BASE_DIR/out
DATE=$(date +%Y%m%d)
FILENAME=$DATE-document-list-out-myr.otl
OUTPUT="$BASE_DIR/out/$FILENAME"

sh ~/prj/study/script/dir_2_list_all.sh $DIR >"$OUTPUT"

echo "$OUTPUT"

# /Users/mertnuhoglu/prj/collabryio/cldocs/cllogseq/out/20250204-document-list-out-cllb.otl
# /Users/mertnuhoglu/prj/myrepo/logseq-myrepo/out/20250205-document-list-out-myr.otl

