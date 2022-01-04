fun! Telescope03()
  lua for k in pairs(package.loaded) do if k:match("^telescope%-03") then package.loaded[k] = nil end end
  lua require("telescope-03").buffer_list({})
endfun

augroup Telescope03
  autocmd!
augroup END

