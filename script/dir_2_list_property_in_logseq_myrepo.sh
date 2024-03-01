#!/bin/sh

# Title: Directory to List of Property (in logseq-myrepo dir)
# Date: 20240220
# [[dir_2_list_property_in_logseq_myrepo.sh]]
#
# prn: [[dir_2_list_property.sh]]
#
# Usage:
#   dir_2_list_property_in_logseq_myrepo.sh
#

DIR=$HOME/prj/myrepo/logseq-myrepo
REPO=myr
DATE=$(date +%Y%m%d)
BASENAME=$DATE-Property-List-$REPO
FILENAME=$BASENAME.md
OUTPUT="$DIR/pages/$FILENAME"

sh ~/prj/study/script/dir_2_list_property.sh $DIR $REPO >"$OUTPUT"

echo "$OUTPUT" | pbcopy
echo "$OUTPUT" 
