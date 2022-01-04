fun! Plugin01()
  lua for k in pairs(package.loaded) do if k:match("^plugin%-01") then package.loaded[k] = nil end end
  lua require("plugin-01").printWindowSize()
endfun

augroup Plugin01
  autocmd!
augroup END
