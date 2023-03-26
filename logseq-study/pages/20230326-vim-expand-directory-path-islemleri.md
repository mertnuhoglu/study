
# 20230326-vim-expand-directory-path-islemleri id=g14136

```lua
local function directory_name() 
  return "💼" .. vim.api.nvim_eval('expand("%:h:t")')
end
```

`expand` help: <url:vimhelp:expand()>

		Modifiers:
			:p		expand to full path
			:h		head (last path component removed)
			:t		tail (last path component only)
			:r		root (one extension removed)
			:e		extension only

Örnek:

```vimscript
:echo expand("%:h:t")
=> pages
:echo expand("%:h:t:t")
=> pages
:echo expand("%:p:h:t")
=> pages
:echo expand('%:t')
=> 20230326-vim--lualine-ile-son-iki-klasoru-nasil-gosteririz.md
```

`help filename-modifiers`: <url:vimhelp:filename-modifiers>

```vim
:echo expand("%:p")    " absolute path
=> /Users/mertnuhoglu/prj/study/logseq-study/pages/20230326-vim--lualine-ile-son-iki-klasoru-nasil-gosteririz.md
:echo expand("%:p:h")  " absolute path dirname
=> /Users/mertnuhoglu/prj/study/logseq-study/pages
:echo expand("%:p:h:h")" absolute path dirname dirname
=> /Users/mertnuhoglu/prj/study/logseq-study
:echo expand("%:.")    " relative path
=> logseq-study/pages/20230326-vim--lualine-ile-son-iki-klasoru-nasil-gosteririz.md
:echo expand("%:.:h")  " relative path dirname
=> logseq-study/pages
:echo expand("%:.:h:h")" relative path dirname dirname
=> logseq-study
:echo expand("<sfile>:p")  " absolute path to [this] vimscript
:echo expand("%:p:h:h:t")  " path > head (parent) > head (parent) > tail
=> logseq-study
```

