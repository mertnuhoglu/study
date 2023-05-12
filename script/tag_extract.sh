#!/bin/sh

# Title: Extract tags from all notes id=g14267
# Date: 20230504

DAY=$(date +%Y%m%d)
cd $HOME/projects/myrepo/logseq-myrepo
rg '#\w+' > tags_myrepo_$DAY.txt
cd $HOME/projects/study/logseq-study
rg '#\w+' > tags_study_$DAY.txt
cd $HOME/gdrive/grsm/opal/docs-grsm
rg '#\w+' > tags_grsm_$DAY.txt
mv $HOME/projects/myrepo/logseq-myrepo/tags_myrepo_$DAY.txt $HOME/projects/myrepo/scrap/out
mv $HOME/projects/study/logseq-study/tags_study_$DAY.txt $HOME/projects/myrepo/scrap/out
mv $HOME/gdrive/grsm/opal/docs-grsm/tags_grsm_$DAY.txt $HOME/projects/myrepo/scrap/out
cd $HOME/projects/myrepo/scrap/out
cat tags_myrepo_$DAY.txt tags_study_$DAY.txt tags_grsm_$DAY.txt > tags_$DAY.txt

echo "$HOME/projects/myrepo/scrap/out/tags_$DAY.txt"

