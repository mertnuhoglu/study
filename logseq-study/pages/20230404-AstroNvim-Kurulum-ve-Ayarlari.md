
# 20230404-AstroNvim-Kurulum-ve-Ayarlari id=g14181

## Installation

[Getting Started | AstroNvim](https://astronvim.com/)

```
mkdir -p ~/.local/share/nvim.bak
mv ~/.local/share/nvim/lazy ~/.local/share/nvim.bak/lazy
mv ~/.local/state/nvim ~/.local/state/nvim.bak
mv ~/.cache/nvim ~/.cache/nvim.bak
```

```
cd /Users/mertnuhoglu/.config/nvimconfigs
git clone --depth 1 https://github.com/AstroNvim/AstroNvim
nvswap.sh "$HOME/.config/nvimconfigs/AstroNvim" 
nvim
```

Konfigürasyon reposu:

```
cd /Users/mertnuhoglu/projects/private_dotfiles/.vim/myconfig/
git clone https://github.com/AstroNvim/user_example astronvim
~/.config/nvim/lua/user
ln -s ~/projects/private_dotfiles/.vim/myconfig/astronvim ~/.config/nvimconfigs/AstroNvim/lua/user
```

## Video: Neovim With AstroNvim - Cretezy

[Neovim With AstroNvim | Your New Advanced Development Editor - YouTube](https://www.youtube.com/watch?v=GEHPiZ10gOk)


