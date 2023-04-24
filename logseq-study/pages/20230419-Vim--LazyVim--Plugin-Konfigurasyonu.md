tags:: study, mypst, vim/lazyvim

# 20230419-Vim--LazyVim--Plugin-Konfigurasyonu id=g14236

[Is this right? To override default options of a plugin, I need to set `opts` · LazyVim/LazyVim · Discussion #626](https://github.com/LazyVim/LazyVim/discussions/626)

I am confused between `opts` and `options`. 

This one is correct, right?

```lua
return {
  {
    "projekt0n/github-nvim-theme",
    opts = {
      transparent = false,
  ...
```

This one is wrong, right?

```lua
return {
  {
    "projekt0n/github-nvim-theme",
    opts = {
      options = {
        transparent = false,
  ...
```

---

## Answer:

You could either try the first one which is correct or you could even try the config option of lazy.nvim and setup your plugin like you're used to already. For example:

```lua
return {
  {
    "projekt0n/github-nvim-theme",
    config = function()
      require('github-theme').setup({
				options = {
					transparent = false,
 		 ...
  },
}
```


