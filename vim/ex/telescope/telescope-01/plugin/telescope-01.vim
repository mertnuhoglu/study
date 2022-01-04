fun! Telescope01()
  lua for k in pairs(package.loaded) do if k:match("^telescope%-01") then package.loaded[k] = nil end end
  lua require("telescope-01").color_list()
endfun

augroup Telescope01
  autocmd!
augroup END

