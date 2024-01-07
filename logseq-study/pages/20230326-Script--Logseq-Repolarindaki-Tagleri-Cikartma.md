tags:: study, logseq, my/script, kms

# 20230326-Script--Logseq-Repolarindaki-Tagleri-Cikartma 

rfr: Title: Extract tags from all notes <url:file:///~/prj/study/script/tag_extract.sh#r=g14267>

- ## a02: grsm özel çıktı:

```sh
sh ~/prj/study/script/tag_extract.sh
```

Output: [[Tag-List-out]]

Put into (manually): [[Tag-List]]

- ## a01: Genel çıktı:

```sh
sh ~/prj/study/script/tag_extract.sh
cd ~/prj/myrepo/scrap/out/
ls tags_grsm*
# grsm etiketleri:
#> ~/prj/myrepo/scrap/out/tags_grsm_20230912.txt
# tüm etiketler:
#> ~/projects/myrepo/scrap/out/tags_20230607.txt
#> ~/projects/myrepo/scrap/out/tags_20231105.txt
```

Output: `/Users/mertnuhoglu/projects/myrepo/scrap/out/tags_20231105.txt`

`:LogseqExtractTags` ile etiketleri çıkart.

rfr: `function! LogseqExtractTags() " <url:file:///~/prj/private_dotfiles/vim/my-vim-custom2/plugin/my-vim-custom2.vim#r=g14147>`

Sonuçları buraya aktar:

rfr: [[kslt]] <url:file:///~/prj/myrepo/logseq-myrepo/pages/kslt.md#r=g14145>

