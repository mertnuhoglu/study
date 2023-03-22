tags:: vim/lsp

# 20230321-Neovim--Setup-AutoComplete-and-Snippets id=g14059

[Code like a GOD with Neovim AutoComplete and Snippets! - YouTube](https://www.youtube.com/watch?v=h4g0m0Iwmys)

Edit `~/prj/private_dotfiles/.config/nvim/lua/plugins.lua`

```
	use 'hrsh7th/nvim-cmp'
	use 'hrsh7th/cmp-nvim-lua'
```

Edit `~/prj/private_dotfiles/.config/nvim/lua/lsp_auto_complete.lua`

rfr: nvim-cmp setup <url:file:///~/prj/private_dotfiles/.config/nvim/lua/lsp_auto_complete.lua#r=g14060>

```
local cmp = require 'cmp'
cmp.setup {
  mapping = {
    ['<CR>'] = cmp.mapping.confirm { select = true, },
  },
  sources = {
    { name = 'nvim_lsp' },
    { name = 'luasnip' },
    { name = 'buffer' },
    { name = 'path' },
    { name = 'calc' },
  },
	formatting = {
    format = lspkind.cmp_format({
      with_text = true, -- do not show text alongside icons
      maxwidth = 50, -- prevent the popup from showing more than provided characters (e.g 50 will not show more than 50 characters)
    })
  }
}
```

## Sıfırdan Neovim Konfigürasyonu id=g14061

[(563) How to Configure Neovim to make it Amazing -- complete tutorial - YouTube](https://www.youtube.com/watch?v=J9yqSdvAKXY)
