
# 20230323-VsCode-Snippet-Ekleme-Vim-Icine id=g14080

pprv: [[20230321-Neovim--Setup-AutoComplete-and-Snippets]] <url:file:///~/prj/study/logseq-study/pages/20230321-Neovim--Setup-AutoComplete-and-Snippets.md#r=g14059>

## a01: VsCode snippet

https://github.com/L3MON4D3/LuaSnip/blob/b5a72f1fbde545be101fcd10b70bcd51ea4367de/Examples/snippets.lua#L501

```lua
require("luasnip.loaders.from_vscode").load({ paths = { "~/prj/private_dotfiles/mysnippets" } }) 
```

Edit `/Users/mertnuhoglu/projects/private_dotfiles/mysnippets/package.json`

Bu satırı buraya ekle: `paths for vscode snippets <url:file:///~/prj/private_dotfiles/.config/nvim/lua/mert/completions.lua#r=g14081>`

Artık `~/prj/private_dotfiles/mysnippets` içine koyduğun vscode snippetları kullanılabilir.

pnxt: [[20230323-Lua-Kullanarak-Dinamik-Snippet-Ekleme-Vim-Icinde]] <url:file:///~/projects/study/logseq-study/pages/20230323-Lua-Kullanarak-Dinamik-Snippet-Ekleme-Vim-Icinde.md#r=g14087>

