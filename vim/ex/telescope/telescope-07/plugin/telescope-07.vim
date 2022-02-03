fun! Telescope07()
  lua print("Telescope07:")
  lua for k in pairs(package.loaded) do if k:match("^telescope%-07") then package.loaded[k] = nil end end
  " lua for k in pairs(package.loaded) do if k:match("telescope") then print(k) end end
  lua require("telescope-07").color_list()
endfun

augroup Telescope07
  autocmd!
augroup END

