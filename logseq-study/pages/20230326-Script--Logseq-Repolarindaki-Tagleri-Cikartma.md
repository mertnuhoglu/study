
# 20230326-Script--Logseq-Repolarindaki-Tagleri-Cikartma id=g14146

```
cd /Users/mertnuhoglu/projects/myrepo/logseq-myrepo
rg '#\w+' > tags_myrepo.txt
cd /Users/mertnuhoglu/projects/study/logseq-study
rg '#\w+' > tags_study.txt
cd /Users/mertnuhoglu/gdrive/grsm/opal/docs-grsm
rg '#\w+' > tags_grsm.txt
mv /Users/mertnuhoglu/projects/myrepo/logseq-myrepo/tags_myrepo.txt /Users/mertnuhoglu/projects/myrepo/scrap/out
mv /Users/mertnuhoglu/projects/study/logseq-study/tags_study.txt /Users/mertnuhoglu/projects/myrepo/scrap/out
mv /Users/mertnuhoglu/gdrive/grsm/opal/docs-grsm/tags_grsm.txt /Users/mertnuhoglu/projects/myrepo/scrap/out
cd /Users/mertnuhoglu/projects/myrepo/scrap/out
cat tags_myrepo.txt tags_study.txt tags_grsm.txt > tags.txt
```

Output: `/Users/mertnuhoglu/projects/myrepo/scrap/out/tags.txt`

`:LogseqExtractTags` ile etiketleri çıkart.

rfr: `function! LogseqExtractTags() " <url:file:///~/prj/vim_repos/my-vim-custom/plugin/my-vim-custom.vim#r=g14147>`

Hata ayıklama:

rfr: [[kslt]] <url:file:///~/prj/myrepo/logseq-myrepo/pages/kslt.md#r=g14145>
