#!/bin/sh

# Title: Extract tags from all notes id=g14267
# Date: 20230504
# rfr: [[20230326-Script--Logseq-Repolarindaki-Tagleri-Cikartma]] <url:file:///~/prj/study/logseq-study/pages/20230326-Script--Logseq-Repolarindaki-Tagleri-Cikartma.md#r=g14146>

DAY=$(date +%Y%m%d)
cd $HOME/prj/myrepo/logseq-myrepo

# myr
#
# match: inline #sometag
rg -g "!ndx-kslt.md" "#\w+" >tags_myr_$DAY.txt
rg --ignore-file ~/prj/study/script/.tag_extract_ignore "#\w+" >tags_std_$DAY.txt
# match:
# tags:: myrepo, tfk/motivasyon
rg "tags::" >>tags_myr_$DAY.txt

# std
cd $HOME/prj/study/logseq-study
rg "#\w+" >tags_std_$DAY.txt
rg "tags::" >>tags_std_$DAY.txt

# cllb
cd $HOME/prj/collabryio/cldocs/cllogseq
rg "#\w+" >tags_cllb_$DAY.txt
rg "tags::" >>tags_cllb_$DAY.txt

mv $HOME/prj/myrepo/logseq-myrepo/tags_myr_$DAY.txt $HOME/prj/myrepo/scrap/out
mv $HOME/prj/study/logseq-study/tags_std_$DAY.txt $HOME/prj/myrepo/scrap/out
mv $HOME/prj/collabryio/cldocs/cllogseq/tags_cllb_$DAY.txt $HOME/prj/myrepo/scrap/out

cd $HOME/prj/myrepo/scrap/out
cat tags_myr_$DAY.txt tags_std_$DAY.txt tags_cllb_$DAY.txt >tags_all_$DAY.txt

nvim -c "LogseqExtractTags" -c "wq" tags_cllb_$DAY.txt
cp -f tags_cllb_$DAY.txt tags_cllb.txt
cp -f tags_cllb_$DAY.txt $HOME/prj/collabryio/cldocs/cllogseq/pages/$DAY-Tag-List-cllb.md
cp -f tags_cllb_$DAY.txt $HOME/prj/collabryio/cldocs/cllogseq/pages/Tag-List-out-cllb.md

nvim -c "LogseqExtractTags" -c "wq" tags_myr_$DAY.txt
cp -f tags_myr_$DAY.txt tags_myr.txt
cp -f tags_myr_$DAY.txt $HOME/prj/myrepo/logseq-myrepo/pages/$DAY-Tag-List-myr.md
cp -f tags_myr_$DAY.txt $HOME/prj/myrepo/logseq-myrepo/pages/Tag-List-out-myr.md

nvim -c "LogseqExtractTags" -c "wq" tags_std_$DAY.txt
cp -f tags_std_$DAY.txt tags_std.txt
cp -f tags_std_$DAY.txt $HOME/prj/study/logseq-study/pages/$DAY-Tag-List-std.md
cp -f tags_std_$DAY.txt $HOME/prj/study/logseq-study/pages/Tag-List-out-std.md

printf "$HOME/prj/myrepo/scrap/out/tags_all_$DAY.txt" | pbcopy
