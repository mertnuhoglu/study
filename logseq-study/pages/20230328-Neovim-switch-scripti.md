
# 20230328-Neovim-switch-scripti id=g14154

Source: [(3) LunarVim vs NVChad : neovim](https://www.reddit.com/r/neovim/comments/r2l5ui/comment/hmkitic/?utm_source=reddit&utm_medium=web2x&context=3)

Edit `~/prj/stuff/bash/nvswap.sh`

```bash
#!/bin/bash
rm -rf "$HOME/.config/nvim"
ln -s "$(pwd)/"$1 "$HOME/.config/nvim"
```

I have a dir called nvimconfigs in my .config dir.

Usage:

```bash
nvswap.sh <some config dir> 
# it will symlink that config to .config/nvim.
```

Example:

```
nvswap.sh "$HOME/.config/nvimconfigs/nvchad" 
```


