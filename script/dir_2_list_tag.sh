#!/bin/sh

# Title: Directory to List of Tags [[dir_2_list_tag.sh]] #myst #f/script
#   id:: d1238b17-5cf1-4e80-bf8b-46d51c477d27
# Date: 20240121
# [[dir_2_list_tag.sh]]
#
# prt:
# [[dir_2_list_tag_in_logseq_myrepo.sh]]
# [[dir_2_list_tag_in_logseq_cllb.sh]]
#
# Usage:
#   dir_2_list_tag.sh <dir> > <output>
# rfr: which-key: local dir_2_list_tag = { -- SPC hd || ((2aec545b-0bc2-4920-a06b-533df10802fa))
#
# Output: [[Tag-List]]

# ~/prj/study/script/tag_extract.sh
DIR="$1"
REPO="$2"
DATE=$(date +%Y%m%d)
BASENAME=$DATE-Tag-List-$REPO
FILENAME=$BASENAME.md
OUTPUT="$DIR/pages/$FILENAME"

cd "${DIR}"

# rg -g "!ndx-kslt.md" "#\w+" >$BASENAME.txt
# ignore files that already includes previously extracted tags such as: Tag-List.md
rg --ignore-file ~/prj/study/script/.tag_extract_ignore "#\w+" >$BASENAME.txt

rg "tags::" >>$BASENAME.txt
mv $DIR/$BASENAME.txt $HOME/prj/myrepo/scrap/out

cd $HOME/prj/myrepo/scrap/out

nvim -c "LogseqExtractTags" -c "wq" $BASENAME.txt
cp -f $BASENAME.txt tags_$REPO.txt
cp -f $BASENAME.txt $DIR/pages/$FILENAME
cp -f $BASENAME.txt $DIR/pages/Tag-List-out-$REPO.md

gsed -i "1 i tags:: $REPO, f/ndx\\
date:: $DATE\\
.\\
- # $BASENAME\\
.
  - prn: [[ndx/Tag-List-myr]]
  - prn: [[ndx/Tag-List-cllb]]
" "$OUTPUT"

echo "$OUTPUT" | pbcopy
echo "$OUTPUT" 
