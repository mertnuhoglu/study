tags:: mypst, vim/lazyvim, error

# 20230409-Neovim--Error--LazyVim--require-which-key--gives-error id=g14232

I use Neovim with LazyVim configuration setup.

I want to set the mappings in `which-key`. So I put the following code inside `~/.config/nvim/lua/config/which-key.lua`:

```
local wk = require("which-key")
```

This gives the following error: 

```
/Users/mertnuhoglu/.config/nvim/lua/config/which-key.lua:1174: module 'which-key' not found:
	no field package.preload['which-key']
cache_loader: module which-key not found
```

LazyVim installs and setups `which-key.nvim` by default using the following code inside `~/.local/share/nvim/lazy/LazyVim/lua/lazyvim/plugins/editor.lua`:

```
  {
    "folke/which-key.nvim",
    event = "VeryLazy",
		...
  }
```

I thought maybe the error above is due to this plugin being loaded lazily. So, I did this:

```
    -- event = "VeryLazy",
		lazy = false,
```

Still, Neovim gives the same error as above. 

LazyVim's documentation suggests to use the following style to customize keymaps as in https://github.com/LazyVim/LazyVim/blob/main/lua/lazyvim/config/keymaps.lua

```
map("n", "<leader>fn", "<cmd>enew<cr>", { desc = "New File" })
...
```

But this style is much more verbose and difficult to manage than lua tables. Therefore, I prefer to use lua tables as it is commonly used in which-key.
