fun! Telescope06()
  lua for k in pairs(package.loaded) do if k:match("^telescope%-06") then package.loaded[k] = nil end end
  lua require("telescope-06").color_list()
endfun

augroup Telescope06
  autocmd!
augroup END

