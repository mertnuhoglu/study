
# 20230328-Neovim-LazyVim-Kurulum id=g14157

TODO: merhaba

[(637) Zero to IDE with LazyVim - YouTube](https://www.youtube.com/watch?v=N93cTbtLCIM&t=298s)

```
git clone https://github.com/LazyVim/starter ~/.config/nvimconfigs/lazyvim
nvswap.sh ~/.config/nvimconfigs/lazyvim
```

Edit `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/init.lua`

Kendi ayarları: `/Users/mertnuhoglu/.local/share/nvim/lazy/LazyVim/lua/lazyvim/init.lua`

Default options: `~/.local/share/nvim/lazy/LazyVim/lua/lazyvim/config/options.lua`

Default keybindings: `~/.local/share/nvim/lazy/LazyVim/lua/lazyvim/config/keymaps.lua`

Default plugins: `~/.local/share/nvim/lazy/LazyVim/lua/lazyvim/plugins/editor.lua`

[⌨️ Keymaps - LazyVim](https://www.lazyvim.org/keymaps)

Kısayollar

	| !SPC  | incremental selection    |
	| ]]    | next reference           |
	| Scr   | LSP Code Rename          |
	| Sca   | LSP Code Action          |
	| Sfb   | search buffers           |
	| [b    | prev buffer              |
	| ]b    | next buffer              |
	| S     |                          | vertical split |
	| S-    | horizontal split         |
	| ^hjkl | window navigation        |
	| Sst   | search todo              |
	| Ssk   | search keymaps           |
	| Sul   | ui linenumbers toggle    |
	| Ssr   | Global replace (spectre) |
	| Sgg   | git                      |
	| Sqs   | Restore session          |

Custom options: `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/config/options.lua`

Custom keybindings: `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/config/keymaps.lua`

Custom plugins example: `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/plugins/gruvbox.lua`

Custom plugins all: `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/plugins/plugins.lua`

Mevcut plugin ayarlarını değiştirme: `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/plugins/alpha.lua`

Belli bir kısayolla aktifleşen plugin: `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/plugins/file-browser.lua`

Disable plugin: `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/plugins/disabled.lua`

Kendi özel eklentim: `~/prj/private_dotfiles/vim/my-vim-lazyvim/plugin/my-vim-lazyvim.vim`

ftplugin ayarları:

```
ln -s ~/projects/private_dotfiles/.vim/after ~/prj/private_dotfiles/.config/nvimconfigs/lazyvim 
ln -s ~/projects/private_dotfiles/.vim/ftdetect ~/prj/private_dotfiles/.config/nvimconfigs/lazyvim 
ln -s ~/projects/private_dotfiles/.vim/indent ~/prj/private_dotfiles/.config/nvimconfigs/lazyvim 
ln -s ~/projects/private_dotfiles/.vim/ftplugin ~/prj/private_dotfiles/.config/nvimconfigs/lazyvim 
```

Treesitter options (custom): `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/plugins/treesitter.lua`

