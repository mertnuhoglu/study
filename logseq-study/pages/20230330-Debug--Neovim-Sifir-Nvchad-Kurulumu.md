tags:: vim/nvchad

# 20230330-Debug--Neovim-Sifir-Nvchad-Kurulumu id=g14162

rfr: `~/prj/stuff/bash/nvswap.sh`

Sıfır nvchad kurulumu

```
git clone https://github.com/NvChad/NvChad ~/.config/nvimconfigs/clean_nvchad --depth 1
nvswap.sh "$HOME/.config/nvimconfigs/clean_nvchad" 
```

## Error: lazy

Error message: `:checkhealth`

```
lazy: require("lazy.health").check()
========================================================================
  - ERROR: Failed to run healthcheck for "lazy" plugin. Exception:
    function health#check, line 20
    Vim(eval):E5108: Error executing lua ...glu/.local/share/nvim/lazy/lazy.nvim/lua/lazy/health.lua:50: attempt to index local 'spec' (a nil value)
    stack traceback:
    ...glu/.local/share/nvim/lazy/lazy.nvim/lua/lazy/health.lua:50: in function 'check'
    [string "luaeval()"]:1: in main chunk

man: require("man.health").check()
========================================================================
  - OK: plugin/man.vim not in $VIMRUNTIME
  - OK: autoload/man.vim not in $VIMRUNTIME

```

### Fix: a01: tüm lazy pluginlerini sil

```
rm -rf ~/.local/share/nvim/lazy
```

