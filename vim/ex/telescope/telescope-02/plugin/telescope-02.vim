fun! Telescope02()
  lua for k in pairs(package.loaded) do if k:match("^telescope%-02") then package.loaded[k] = nil end end
  lua require("telescope-02").color_list()
endfun

augroup Telescope02
  autocmd!
augroup END

