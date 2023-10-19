tags:: study, vim/lazyvim, error

# 20230911-Error--LazyVim--Failed-to-load-plugins.obsidian id=g14642

Configuration of `obsidian.nvim` plugin:

rfr: `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/plugins/obsidian.lua`

```lua
return {
  "epwalsh/obsidian.nvim",
  lazy = false,
  -- event = { "BufReadPre /Users/mertnuhoglu/gdrive/grsm/opal/docs-grsm/pages/**.md" },
  event = { "BufReadPre /Users/mertnuhoglu/gdrive/grsm/opal/docs-grsm/pages/**.md" },
  -- If you want to use the home shortcut '~' here you need to call 'vim.fn.expand':
  -- event = { "BufReadPre " .. vim.fn.expand "~" .. "/my-vault/**.md" },
  dependencies = {
    -- Required.
    "nvim-lua/plenary.nvim",

    -- see below for full list of optional dependencies 👇
  },
  opts = {
    dir = "~/gdrive/grsm/opal/docs-grsm/pages",  -- no need to call 'vim.fn.expand' here
    -- dir = {"~/gdrive/grsm/opal/docs-grsm/pages", "~/prj/myrepo/logseq-myrepo/pages"},
		mappings = {
			["gf"] = require("obsidian.mapping").gf_passthrough(),
		},
  },
}
```

Error message:

	Failed to load `plugins.obsidian`
	/Users/mertnuhoglu/.config/nvim/lua/plugins/obsidian.lua:19: module 'obsidian.mapping' not found:
	^Ino field package.preload['obsidian.mapping']
	cache_loader: module obsidian.mapping not found
	cache_loader_lib: module obsidian.mapping not found
	^Ino file './obsidian/mapping.lua'
	^Ino file '/usr/local/share/luajit-2.1.0-beta3/obsidian/mapping.lua'
	^Ino file '/usr/local/share/lua/5.1/obsidian/mapping.lua'
	^Ino file '/usr/local/share/lua/5.1/obsidian/mapping/init.lua'
	^Ino file './obsidian/mapping.so'
	^Ino file '/usr/local/lib/lua/5.1/obsidian/mapping.so'
	^Ino file '/usr/local/lib/lua/5.1/loadall.so'
	^Ino file './obsidian.so'
	^Ino file '/usr/local/lib/lua/5.1/obsidian.so'
	^Ino file '/usr/local/lib/lua/5.1/loadall.so'
	stacktrace:
		- ~/.config/nvim/lua/plugins/obsidian.lua:19
		- ~/.config/nvim/lua/config/lazy.lua:9
	Press ENTER or type command to continue
		- ~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/init.lua:3

## Solution:

Moved these lines into `~/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/config/keymaps.lua`

```
		mappings = {
			["gf"] = require("obsidian.mapping").gf_passthrough(),
		},
```

