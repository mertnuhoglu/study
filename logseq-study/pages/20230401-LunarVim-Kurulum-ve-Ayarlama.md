
# 20230401-LunarVim-Kurulum-ve-Ayarlama id=g14170

## Video: LunarVim - Config Structure - chris@machine

[LunarVim - Config Structure - YouTube](https://www.youtube.com/watch?v=aFguUic3v3g)

Edit `/Users/mertnuhoglu/prj/private_dotfiles/.config/lvim/config.lua`

Bu dosya başlangıç dosyası. Lunarvim başlar başlamaz bu dosyayı yükler.

Kendi özel konfigürasyonların için alt dosyalar oluştur.

```
cd ~/.config/lvim
mkdir -p lua/user
cd $_
touch options.lua
```

## Plugin Kurma

rfr: `lvim.plugins <url:file:///~/prj/private_dotfiles/.config/lvim/config.lua#r=g14172>`

Packer sync etmek için: `LvimReload`

Ancak plugin sildiysen sync etmek için: `LvimSyncCorePlugins`

## ftplugin ayarları

```
ln -s ~/prj/private_dotfiles/.vim/after ~/.config/lvim/after
ln -s ~/prj/private_dotfiles/.vim/ftdetect ~/.config/lvim/ftdetect
ln -s ~/prj/private_dotfiles/.vim/ftplugin ~/.config/lvim/ftplugin
ln -s ~/prj/private_dotfiles/.vim/indent ~/.config/lvim/indent
```

## lua ayarları

## which-key ayarları

rfr: `~/prj/private_dotfiles/.config/lvim/lua/user/which-key.lua`

### Hata: Explorer kısayolu

`~/.local/share/lunarvim/lvim/lua/lvim/core/which-key.lua` içinde `SPC e` kısayolunu normal şekilde iptal edemedim. Ancak kodu komentleyince, iptal oldu.

```
  -- lvim.builtin.which_key.mappings["e"] = { "<cmd>NvimTreeToggle<CR>", "Explorer" }
```


