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
FILENAME=otl-list-myr.md
FILENAME2=$DATE-$FILENAME
OUTPUT="$DIR/pages/otl-list-myr.md"
OUTPUT2="$DIR/pages/.out/$FILENAME2"

sh ~/prj/study/script/dir_2_list_otl.sh $DIR >"$OUTPUT"

gsed -i "1 i tags:: myrepo, f/ndx\\
date:: $DATE\\
.\\
- # $DATE-otl-list-myr\\
.\\
  - prn: [[ndx/otl-list-myr]]" "$OUTPUT"
cp "$OUTPUT" "$OUTPUT2"

printf "$FILENAME" | pbcopy
echo "$OUTPUT" 
