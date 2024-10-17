#!/bin/sh

# Title: Directory to List of Property (in logseq-myrepo dir)
# Date: 20240220
# [[dir_2_list_property.sh]]
#
# prn: [[dir_2_list_property.sh]]
#
# Usage:
#   dir_2_list_property.sh <dir> > <output>
#
# Output: [[Property-List]]

# DIR=/Users/mertnuhoglu/projects/myrepo/logseq-myrepo
DIR="$1"
REPO="$2"
DATE=$(date +%Y%m%d)
BASENAME=$DATE-Property-List-$REPO
FILENAME=$BASENAME.md
OUTPUT="$DIR/pages/$FILENAME"

cd "${DIR}"

# ignore files that already includes previously extracted tags such as: Property-List.md
rg --ignore-file ~/prj/study/script/.tag_extract_ignore "^\s*\w+:: " >$BASENAME.txt

mv $DIR/$BASENAME.txt $HOME/prj/myrepo/scrap/out

cd $HOME/prj/myrepo/scrap/out

nvim -c "LogseqExtractProperty" -c "wq" $BASENAME.txt
cp -f $BASENAME.txt property_$REPO.txt
cp -f $BASENAME.txt $DIR/pages/$FILENAME
cp -f $BASENAME.txt $DIR/pages/Property-List-out-$REPO.md

gsed -i "1 i tags:: $REPO, f/ndx\\
date:: $DATE\\
.\\
- # $BASENAME\\
." "$OUTPUT"

printf "$OUTPUT" | pbcopy
echo "$OUTPUT" 
