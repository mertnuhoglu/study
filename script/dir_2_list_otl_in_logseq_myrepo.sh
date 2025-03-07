#!/bin/sh

# Title: Directory to List of otl (in logseq-myrepo dir)
# Date: 20240121
# [[dir_2_list_otl_in_logseq_myrepo.sh]]
#
# prn: [[dir_2_list_otl.sh]]
#
# Usage:
#   dir_2_list_otl_in_logseq_myrepo.sh
#

DIR=$HOME/prj/myrepo/logseq-myrepo
DATE=$(date +%Y%m%d)
FILENAME=$DATE-otl-list-myr.md
OUTPUT="$DIR/pages/$FILENAME"

sh ~/prj/study/script/dir_2_list_otl.sh $DIR >"$OUTPUT"

gsed -i "1 i tags:: myrepo, f/ndx\\
date:: $DATE\\
.\\
- # $DATE-otl-list-myr\\
.\\
  - prn: [[ndx/otl-list-myr]]" "$OUTPUT"

printf "$FILENAME" | pbcopy
echo "$OUTPUT" 
