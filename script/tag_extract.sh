#!/bin/sh

# Title: Extract tags from all notes id=g14267
# Date: 20230504
# rfr: [[20230326-Script--Logseq-Repolarindaki-Tagleri-Cikartma]] <url:file:///~/prj/study/logseq-study/pages/20230326-Script--Logseq-Repolarindaki-Tagleri-Cikartma.md#r=g14146>

DAY=$(date +%Y%m%d)
cd $HOME/projects/myrepo/logseq-myrepo
# match:
# #sometag
rg -g "!ndx-kslt.md" "#\w+" >tags_myrepo_$DAY.txt
rg --ignore-file ~/prj/study/script/.tag_extract_ignore "#\w+" >tags_myrepo_$DAY.txt
# match:
# tags:: myrepo, tfk/motivasyon
rg "tags::" >>tags_myrepo_$DAY.txt
cd $HOME/projects/study/logseq-study
rg "#\w+" >tags_study_$DAY.txt
rg "tags::" >>tags_study_$DAY.txt
cd $HOME/gdrive/grsm/opal/docs-grsm
rg "#\w+" >tags_grsm_$DAY.txt
rg "tags::" >>tags_grsm_$DAY.txt
mv $HOME/projects/myrepo/logseq-myrepo/tags_myrepo_$DAY.txt $HOME/projects/myrepo/scrap/out
mv $HOME/projects/study/logseq-study/tags_study_$DAY.txt $HOME/projects/myrepo/scrap/out
mv $HOME/gdrive/grsm/opal/docs-grsm/tags_grsm_$DAY.txt $HOME/projects/myrepo/scrap/out
cd $HOME/projects/myrepo/scrap/out
cat tags_myrepo_$DAY.txt tags_study_$DAY.txt tags_grsm_$DAY.txt >tags_$DAY.txt

nvim -c "LogseqExtractTags" -c "wq" tags_grsm_$DAY.txt
cp -f tags_grsm_$DAY.txt tags_grsm.txt
cp -f tags_grsm_$DAY.txt $HOME/gdrive/grsm/opal/docs-grsm/pages/$DAY-Tag-List.md
cp -f tags_grsm_$DAY.txt $HOME/gdrive/grsm/opal/docs-grsm/pages/Tag-List-out.md

echo "$HOME/projects/myrepo/scrap/out/tags_$DAY.txt"
