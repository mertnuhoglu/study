tags:: study, vim/telescope

# 20230511-Telescope-Kisaltma-ve-Tagleri-Arama-Fonksiyonu id=g14292

## a01: neoscopes eklentisi

rfr: `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/plugins/neoscopes.lua`

En pratik yöntem bu.

rfr: `local file_map = { -- SPC f <url:file:///~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/config/which-key.lua#r=g14293>`

rfr: `neoscopes: farklı projelerde ve dosyalarda telescope ile arama yapma <url:file:///~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/config/keymaps.lua#r=g14294>`

## a02: search_dirs parametresi

SPC sK

```
Telescope live_grep search_dirs={"app/","lib/"}
~/prj/myrepo/logseq-myrepo/pages/kslt.md
Telescope live_grep search_dirs={"~/prj/myrepo/logseq-myrepo/pages"}
Telescope live_grep search_dirs={"~/prj/myrepo/logseq-myrepo/pages/kslt.md", "~/gdrive/logbook/vivaldi_bookmark_nicknames_20230511.txt"}
-- error
Telescope live_grep search_dirs={"~/gdrive/logbook/vivaldi_bookmark_nicknames_20230511.txt"}
```



