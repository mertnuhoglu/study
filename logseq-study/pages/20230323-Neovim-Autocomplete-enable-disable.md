
# 20230323-Neovim-Autocomplete-enable-disable id=g14093

rfr: `" disable autocompletion based on file types <url:file:///~/.vim/bundle/my-vim-custom/plugin/my-vim-custom.vim#r=g12612>`

```vim
autocmd BufNewFile,BufRead *.md lua require('cmp').setup.buffer { enabled = false }
autocmd BufNewFile,BufRead *.md lua require('cmp').setup.buffer { enabled = true }
```

rfr: `autocomplete settings<url:file:///~/prj/vim_repos/my-vim-custom/plugin/my-vim-custom.vim#r=g14094>`

Özel komut tanımladım: `:ToggleAutoComplete`

Mevcut bufferda autocomplete özelliğini açıp kapatır.

Kısayolu: `SPC Ta` 

	local tab_toggle_menu = { -- <url:file:///~/prj/private_dotfiles/.config/nvim/lua/mert/which-key.lua#r=g12937>

