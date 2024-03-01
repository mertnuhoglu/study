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

DIR=$HOME/prj/collabry/cldocs/cllogseq
DATE=$(date +%Y%m%d)
FILENAME=$DATE-otl-list-cllb.md
OUTPUT="$DIR/pages/$FILENAME"

sh ~/prj/study/script/dir_2_list_otl.sh $DIR >"$OUTPUT"

gsed -i "1 i tags:: cllb, f/ndx\\
date:: $DATE\\
.\\
- # $DATE-otl-list-cllb\\
." "$OUTPUT"

echo "$FILENAME" | pbcopy
echo "$OUTPUT" 
