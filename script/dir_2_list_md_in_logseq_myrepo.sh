#!/bin/sh

# Title: Directory to List of Markdown Files (in logseq-myrepo dir)
# Date: 20240121
# [[dir_2_list_md_in_logseq_myrepo.sh]]
#
# prn: [[dir_2_list_md.sh]]
#
# Usage:
#   dir_2_list_md_in_logseq_myrepo.sh
#

DIR=$HOME/prj/myrepo/logseq-myrepo
DATE=$(date +%Y%m%d)
FILENAME=$DATE-Document-List-myr.md
OUTPUT="$DIR/pages/$FILENAME"

sh ~/prj/study/script/dir_2_list_md.sh $DIR >"$OUTPUT"

gsed -i "1 i tags:: myrepo, f/ndx\\
date:: $DATE\\
.\\
- # $DATE-Document-List-myr\\
.
  - prn: [[ndx/Document-List-myr]]" "$OUTPUT"

echo "$FILENAME" | pbcopy
echo "$OUTPUT" 
