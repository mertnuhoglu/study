tags:: vim

# 20230326-vim--lualine-ile-son-iki-klasoru-nasil-gosteririz id=g14133

[[20230326-vim-expand-directory-path-islemleri]]

```vim
:echo expand("%:p:h:h:t")  " path > head (parent) > head (parent) > tail
=> logseq-study
```

rfr: function parent_directory_name() --  <url:file:///~/prj/private_dotfiles/.config/nvim/lua/statusline.lua#r=g14137>
