#!/bin/sh
# dir_2_list_tag_in_logseq_myrepo.nu

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

sh ~/prj/study/script/dir_2_list_tag.sh $DIR $REPO >> /Users/mertnuhoglu/tmp/cron_hourly.log 2>&1
# sh /Users/mertnuhoglu/prj/study/script/dir_2_list_tag.sh /Users/mertnuhoglu/prj/myrepo/logseq-myrepo myrm >"$OUTPUT"

# printf "$OUTPUT" | pbcopy
# echo "2 $OUTPUT" 
