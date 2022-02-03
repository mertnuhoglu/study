fun! Telescope04()
  lua for k in pairs(package.loaded) do if k:match("^telescope%-04") then package.loaded[k] = nil end end
  lua require("telescope-04").color_list()
endfun

augroup Telescope04
  autocmd!
augroup END

