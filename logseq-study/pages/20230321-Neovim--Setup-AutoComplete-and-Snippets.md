tags:: vim/lsp

# 20230321-Neovim--Setup-AutoComplete-and-Snippets id=g14059

pprv: [[20230321-Neovim-LSP-Setup-and-Configuration]] <url:file:///~/prj/study/logseq-study/pages/20230321-Neovim-LSP-Setup-and-Configuration.md#r=g14063>

rfr: cmp.setup({ -- autocomplete mappings <url:file:///~/prj/private_dotfiles/.config/nvim/lua/mert/completions.lua#r=g14079>

## Sıfırdan Neovim Konfigürasyonu id=g14061

[(563) How to Configure Neovim to make it Amazing -- complete tutorial - YouTube](https://www.youtube.com/watch?v=J9yqSdvAKXY)

[Code with Neovim AutoComplete and Snippets! - YouTube](https://www.youtube.com/watch?v=h4g0m0Iwmys)

Edit `~/prj/private_dotfiles/.config/nvim/lua/plugins.lua`

```
	use 'hrsh7th/nvim-cmp'
	use 'hrsh7th/cmp-nvim-lua'
```

Edit `~/prj/private_dotfiles/.config/nvim/lua/mert/completions.lua`

## Snippet engine configuration

rfr: snippet <url:file:///~/prj/private_dotfiles/.config/nvim/lua/mert/completions.lua#r=g14074>

Eklenti: `luasnip`

## VsCode Snippets

VsCode'un snippetlarını kullanmak için iki eklenti gerekiyor:

Eklenti: 
'saadparwaiz1/cmp_luasnip'
'rafamadriz/friendly-snippets'

pnxt: [[20230323-VsCode-Snippet-Ekleme-Vim-Icine]] <url:file:///~/projects/study/logseq-study/pages/20230323-VsCode-Snippet-Ekleme-Vim-Icine.md#r=g14080>

