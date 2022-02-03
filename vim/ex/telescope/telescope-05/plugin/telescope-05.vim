fun! Telescope05()
  lua for k in pairs(package.loaded) do if k:match("^telescope%-05") then package.loaded[k] = nil end end
  lua require("telescope-05").color_list()
endfun

augroup Telescope05
  autocmd!
augroup END

