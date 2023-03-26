
# 20230324-Neovim-which-key--insert-mode-icin-menu-ve-kisayol-girmek id=g14103

insert modda which-key menülerini kullanmak için iki yerde ayar yapmak gerekiyor.

1. Ayar:

`wk.setup` table içinde `triggers` property için: tetikleyici kısayolu koyalım. Insert mode için `$` olsun tetikleyici.

```
  triggers = {"<leader>", "'", "z", "g", "<c-w>", '"', "z=", "$"}, -- or specify a list manually
```

2. Ayar:

Insert mod için kısayolları tanımlayalım. Dikkat `$` içinde tekrar `$` ile tetiklenerek menüleri aktifleştirelim. Böylece bir kere `$` basılırsa, bu tuş yazı olarak yazılabilir. İki kere ardışık `$` basılırsa, o zaman menü aktifleşir.

```
local mappings_insert =  {
  ['$'] = {
    name = "mappings",
    ['$'] = window_map,
  },
}
wk.register(mappings_insert, { mode = "i"})
```


