#!/bin/sh
# dir_2_list_tag.nu

# Date: 20240121
# [[dir_2_list_tag.sh]]
#
# pnxt: [[dir_2_list_tag.nu]]
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
OUTPUT_DATED="$DIR/out/$FILENAME"
OUTPUT="$DIR/pages/Tag-List-out-$REPO.txt"

cd "${DIR}"

echo step01

# rg -g "!ndx-kslt.md" "#\w+" >$BASENAME.txt
# ignore files that already includes previously extracted tags such as: Tag-List.md
rg --ignore-file ~/prj/study/script/.tag_extract_ignore "#\w+" >$BASENAME.txt

echo step02

rg --ignore-file ~/prj/study/script/.tag_extract_ignore "tags::" >>$BASENAME.txt
mv $DIR/$BASENAME.txt $HOME/prj/myrepo/scrap/out

echo step03

cd $HOME/prj/myrepo/scrap/out

# use ~/.config/nushell/modules/mert_utils.nu logseq-extract-tags-run
# logseq-extract-tags-run $BASENAME.txt | save $BASENAME-01.txt
# nvim -c "LogseqExtractTags" -c "wq" $BASENAME.txt
echo step04

cp -f $BASENAME.txt tags/tags_$REPO.txt
cp -f $BASENAME.txt $OUTPUT_DATED
cp -f $BASENAME.txt $OUTPUT

echo step05

gsed -i "1 i tags:: $REPO, f/ndx\\
date:: $DATE\\
.\\
- # $BASENAME\\
.\\
  - prn: [[ndx/Tag-List-myr]]\\
  - prn: [[ndx/Tag-List-cllb]]\\
" "$OUTPUT_DATED"

echo step06

printf "$OUTPUT" | pbcopy
echo "$OUTPUT" 
