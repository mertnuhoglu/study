#!/bin/sh

# Title: Directory to List of Tag (in logseq-myrepo dir)
# Date: 20240121
# [[dir_2_list_tag_in_logseq_myrepo.sh]]
#
# prn: [[dir_2_list_tag.sh]]
#
# Usage:
#   dir_2_list_tag_in_logseq_myrepo.sh
#

DIR=${DIR_MYREPO_LOGSEQ}
REPO=myr
DATE=$(date +%Y%m%d)
BASENAME=$DATE-Tag-List-$REPO
FILENAME=$BASENAME.md
OUTPUT="$DIR/pages/$FILENAME"

sh ~/prj/study/script/dir_2_list_tag.sh $DIR $REPO >"$OUTPUT"

printf "$OUTPUT" | pbcopy
echo "$OUTPUT" 
