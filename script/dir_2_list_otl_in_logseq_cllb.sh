#!/bin/sh

# Title: Directory to List of otl (in logseq-cllb dir)
# Date: 20240121
# [[dir_2_list_otl_in_logseq_cllb.sh]]
#
# prn: [[dir_2_list_otl.sh]]
#
# Usage:
#   dir_2_list_otl_in_logseq_cllb.sh
#

DIR=$HOME/prj/collabryio/cldocs/cllogseq
DATE=$(date +%Y%m%d)
FILENAME=otl-list-myr.md
FILENAME2=$DATE-$FILENAME
OUTPUT="$DIR/pages/otl-list-myr.md"
OUTPUT2="$DIR/pages/.out/$FILENAME2"

sh ~/prj/study/script/dir_2_list_otl.sh $DIR >"$OUTPUT"

gsed -i "1 i tags:: cllb, f/ndx\\
date:: $DATE\\
.\\
- # $DATE-otl-list-cllb\\
.\\
  - prn: [[ndx/otl-list-myr]]" "$OUTPUT"
cp "$OUTPUT" "$OUTPUT2"

printf "$FILENAME" | pbcopy
echo "$OUTPUT" 
