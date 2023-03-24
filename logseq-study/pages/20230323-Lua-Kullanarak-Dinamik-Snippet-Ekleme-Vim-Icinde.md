
# 20230323-Lua-Kullanarak-Dinamik-Snippet-Ekleme-Vim-Icinde id=g14087

pprv: [[20230323-VsCode-Snippet-Ekleme-Vim-Icine]] <url:file:///~/prj/study/logseq-study/pages/20230323-VsCode-Snippet-Ekleme-Vim-Icine.md#r=g14080>

## a02: Lua snippet

VsCode snippetları statik metindir. Lua snippetları ise, lua'nın dinamikliğiyle ayarlayabildiğin snippetlardır.

Örnek: `~/prj/private_dotfiles/.config/nvim/lua/mert/luasnip.lua`

Burada `add_snippets` fonksiyonuyla lua snippetları ekleniyor.

Orjinal kaynak kodu: https://github.com/L3MON4D3/LuaSnip/blob/master/Examples/snippets.lua

	rfr: `/Users/mertnuhoglu/.local/share/nvim/site/pack/packer/start/LuaSnip/Examples/snippets.lua`

Not: Eski dokümanlarda `ls.snippets` ile snippet tanımlanıyordu. Artık o çalışmıyor.

Örnek lua snippetları: `snippets are added via ls.add_snippets(filetype, snippets[, opts]), where <url:file:///~/.local/share/nvim/site/pack/packer/start/LuaSnip/Examples/snippets.lua#r=g14088>`

pnxt: [[20230323-Neovim-Autocomplete-Kisayollari]] <url:file:///~/prj/study/logseq-study/pages/20230323-Neovim-Autocomplete-Kisayollari.md#r=g14092>



