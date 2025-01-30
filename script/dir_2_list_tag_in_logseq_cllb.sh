#!/bin/sh

# Title: Directory to List of Tag (in logseq-cllb dir)
# Date: 20240121
# [[dir_2_list_tag_in_logseq_cllb.sh]]
#
# prn: [[dir_2_list_tag.sh]]
#
# Usage:
#   dir_2_list_tag_in_logseq_cllb.sh
#

DIR=$HOME/prj/collabryio/cldocs/cllogseq
REPO=cllb
DATE=$(date +%Y%m%d)
BASENAME=$DATE-Tag-List-$REPO
FILENAME=$BASENAME.md
OUTPUT="$DIR/pages/$FILENAME"

sh ~/prj/study/script/dir_2_list_tag.sh $DIR $REPO >"$OUTPUT"

printf "$OUTPUT" 
echo "$OUTPUT" 
