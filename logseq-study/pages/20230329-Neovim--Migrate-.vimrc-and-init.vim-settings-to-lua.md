tags:: vim/lua

# 20230329-Neovim--Migrate-.vimrc-and-init.vim-settings-to-lua id=g14158

[From init.vim to init.lua - a crash course](https://www.notonlycode.org/neovim-lua-config/)

## a01: Wrap inside vim.cmd 

```vim
vim.cmd([[
set notimeout
set encoding=utf-8
]])
```

## a02: Reuse existing .vimrc file

[Nvchad: Is it possible to reuse existing .vimrc file? - Vi and Vim Stack Exchange](https://vi.stackexchange.com/questions/41698/nvchad-is-it-possible-to-reuse-existing-vimrc-file)

Edit `~/prj/private_dotfiles/.config/nvimconfigs/nvchad/lua/custom/vimrc.lua`

```lua
vim.cmd('source ~/.vimrc')
```

```
echo stdpath("config")
```

20230329: vimrc.lua dosyasına yukarıdaki ifadeyi ekledim, ama bir faydası olmadı. `init.lua` dosyasına ekleyince çalıştı.

rfr: `~/prj/private_dotfiles/.config/nvimconfigs/nvchad/lua/custom/init.lua`


