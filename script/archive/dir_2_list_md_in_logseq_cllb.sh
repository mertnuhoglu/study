#!/bin/sh

# Title: Directory to List of Markdown Files (in logseq cllb dir)
# Date: 20240121
# [[dir_2_list_md_in_logseq_cllb.sh]]
#
# Usage:
#   dir_2_list_md_in_logseq_cllb.sh
# dir_2_list_md_in_logseq_cllb

DIR=$HOME/prj/collabryio/cldocs/cllogseq
DATE=$(date +%Y%m%d)
FILENAME=Document-List-cllb.md
FILENAME2=$DATE-$FILENAME
OUTPUT="$DIR/pages/Document-List-cllb.md"
OUTPUT2="$DIR/pages/.out/$FILENAME2"

sh ~/prj/study/script/dir_2_list_md.sh $DIR >"$OUTPUT"

gsed -i "1 i tags:: cllb, f/ndx\\
date:: $DATE\\
.\\
- # $DATE-Document-List-cllb\\
.\\" "$OUTPUT"
cp "$OUTPUT" "$OUTPUT2"

printf "$FILENAME" | pbcopy
echo "$OUTPUT"

