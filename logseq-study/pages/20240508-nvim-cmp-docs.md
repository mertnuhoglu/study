tags:: study
date:: 20240508

# 20240508-nvim-cmp-docs
.
- [[f/ndx]]
	- rfr: <vimhelp:nvim-cmp>
	- rfr: https://github.com/hrsh7th/nvim-cmp/wiki
.
# f/pnt
.
- Mappings:
	- ^x^k: Default keyword
	- ^n: Default word
- `completeopt`
	- rfr: <vimhelp:completeopt>
	- `completeopt = "menu,menuone,noinsert"`
		- menu: use popup menu 
		- menuone: use popup menu even if only one match
		- noinsert: don't insert until the user selects
	- `cmp.config.sources`
		- `nvim_lsp`
		- `path`
		- `buffer`
- FAQ: <vimhelp:cmp-faq>
	- Disable auto-completion
	  `autocomplete = false,`
	- Change trigger key
		`["<M-Space>"] = cmp.mapping.complete(),`
	- Configure for a specific file type:
	  `cmp.setup.filetype({ 'markdown', 'help' }, {`
- Vim: Omni completion
	- rfr: [Omni completion | Vim Tips Wiki | Fandom](https://vim.fandom.com/wiki/Omni_completion)
	- `^x^o` or `^x^k`
- vim-fzf-dictionary

- error: tab ile sonlandırma çalışmıyor #f/log
  id:: 2aa90934-59ad-4f07-99e1-f45f9fe7508f
	- E5108: Error executing lua ...ocal/share/nvim/lazy/nvim-cmp/lua/cmp/config/default.lua:35: snippet engine is not configured.
	- ilk başta düzgün çalışıyor, 5-10 saniye sonra replace ediyor satırı
	- cmp.ConfirmBehavior.Replace
	- error: E5108: Error executing lua ...ocal/share/nvim/lazy/nvim-cmp/lua/cmp/config/default.lua:35: snippet engine is not configured.
		- stack traceback:
			- [C]: in function 'error'
		- ...ocal/share/nvim/lazy/nvim-cmp/lua/cmp/config/default.lua:35:
		- a01: snippet engine configuration
		- fix: https://github.com/hrsh7th/nvim-cmp/issues/373#issuecomment-946392918
			- rfr: snippet configuration is required `vim/nvim-cmp` || ((a596f22f-c250-4014-af68-af91dd69c5a6))

- Use all opened buffers but not larger than 1 Mb:
	- https://www.reddit.com/r/neovim/comments/16o22w0/comment/k1j8g62/
	
- Omni-completion menu mappings:

This is Vim's built-in omni-completion:

rfr: " Completion Popup menu mappings || ((25a5bdec-d2fb-4f54-8329-f330fbe5ab5f))

rfr: <vimhelp:popupmenu-completion>

rfr: <vimhelp: ins-completion>

Default lazyVim mappings: `~/.local/share/nvim/lazy/LazyVim/lua/lazyvim/config/keymaps.lua`

```vim
v  <M-j>       * :m '>+1<CR>gv=gv
                 Move Down
	Last set from Luan  <M-j>       * <Cmd>m .+1<CR>==
                 Move Down
```

- Dictionary words using nvim-cmp

Define all keywords in a file. Make nvim-cmp import the keywords in the file to use for auto-complete.

```lua
        {
            name = 'look',
            keyword_length = 2,
            option = {
                convert_case = true,
                loud = true,
                -- dict = '/usr/share/dict/words'
								dict = '/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/journals/ex/taglist.txt',
            }
        },
```

The keywords file must only include keywords.

- nvim-cmp videos:

[(4356) nvim-cmp #4: custom mapping like supertab - YouTube](https://www.youtube.com/watch?v=C2lNgfrDzt0)
