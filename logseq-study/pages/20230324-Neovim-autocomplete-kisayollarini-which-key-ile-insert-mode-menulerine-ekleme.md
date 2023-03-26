
# 20230324-Neovim-autocomplete-kisayollarini-which-key-ile-insert-mode-menulerine-ekleme id=g14109

Edit `~/prj/private_dotfiles/.config/nvim/lua/mert/which-key.lua`

Insert modda which-key menülerini başlatmak için: [[20230324-Neovim-which-key--insert-mode-icin-menu-ve-kisayol-girmek]]

Örnek: `$$y` ile `expand` komutu çağrılır.

```lua
local insert_map = {
	t = {'<cmd>lua require("cmp").mapping.confirm({ select = true })<CR>', '💠 autocomplete'},
	y = {"<cmd>lua require('luasnip').expand()<CR>", '💠 expand'},
}
local mappings_insert =  { 
  ['$'] = {
    name = "mappings",
    ['$'] = insert_map,
  },
}
```

rfr: [[20230324-Neovim-autocomplete-kisayollarini-which-key-ile-insert-mode-menulerine-ekleme]] <url:file:///~/prj/study/logseq-study/pages/20230324-Neovim-autocomplete-kisayollarini-which-key-ile-insert-mode-menulerine-ekleme.md#r=g14109>

