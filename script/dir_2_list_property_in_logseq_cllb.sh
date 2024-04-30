#!/bin/sh

# Title: Directory to List of Property (in logseq-cllb dir)
# Date: 20240220
# [[dir_2_list_property_in_logseq_cllb.sh]]
#
# prn: [[dir_2_list_property.sh]]
#
# Usage:
#   dir_2_list_property_in_logseq_cllb.sh
#

DIR=$HOME/prj/collabryio/cldocs/cllogseq
REPO=cllb
DATE=$(date +%Y%m%d)
BASENAME=$DATE-Property-List-$REPO
FILENAME=$BASENAME.md
OUTPUT="$DIR/pages/$FILENAME"

sh ~/prj/study/script/dir_2_list_property.sh $DIR $REPO >"$OUTPUT"

echo "$OUTPUT" | pbcopy
echo "$OUTPUT"
