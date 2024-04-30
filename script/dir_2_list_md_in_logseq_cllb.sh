#!/bin/sh

# Title: Directory to List of Markdown Files (in logseq cllb dir)
# Date: 20240121
# [[dir_2_list_md_in_logseq_cllb.sh]]
#
# Usage:
#   dir_2_list_md_in_logseq_cllb.sh
#

DIR=$HOME/prj/collabryio/cldocs/cllogseq
DATE=$(date +%Y%m%d)
FILENAME=$DATE-Document-List-cllb.md
OUTPUT="$DIR/pages/$FILENAME"

sh ~/prj/study/script/dir_2_list_md.sh $DIR >"$OUTPUT"

gsed -i "1 i tags:: cllb, f/ndx\\
date:: $DATE\\
.\\
- # $DATE-Document-List-cllb\\
.
  - prn: [[ndx/Document-List-cllb]]" "$OUTPUT"

echo "$FILENAME" | pbcopy
echo "$OUTPUT"
