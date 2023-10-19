tags:: study

# 20231014-Call-a-Lua-Function-from-within-Vimscript id=g15013

rfr: script: navigating files <url:file:///~/prj/vim_repos/vim-infoman/plugin/vim-infoman.vim#r=g15014>

```vim
": ex01: Call lua function from within vim {{{

lua << EOF
function _G.find_files_20231014_01()
	local scopes = require("neoscopes")
	require("telescope.builtin").find_files({
		search_dirs = scopes.get_current_dirs(),
		search_file = "20231014-rtc-Yatirim101-Videolari.md"
	 })
	return 0
end
EOF

function! GotoFileInBracket2023101401() 
	normal! "fyi]
  let f01 = @f
  let f02 = substitute(f01, '[\[\]]', '', 'g')
  let f03 = substitute(f02, '.*', '\1.md', '')
	call v:lua.find_files_20231014_01()
  return "test"
endfunction
command! GotoFileInBracket2023101401 call GotoFileInBracket2023101401()
" Bu çalışıyor. 
" Ancak vim'den lua'ya argüman göndermem lazım. 

lua << EOF
function _G.find_files_20231014_02(filename)
	local scopes = require("neoscopes")
	require("telescope.builtin").find_files({
		search_dirs = scopes.get_current_dirs(),
		search_file = filename
	})
	return 0
end
EOF

function! GotoFileInBracket20231014b() 
	normal! "fyi]
  let f01 = @f
  let f02 = substitute(f01, '[\[\]]', '', 'g')
  let f03 = substitute(f02, '.*', '\1.md', '')
	call v:lua.find_files_20231014_02("20231014-rtc-Yatirim101-Videolari.md")
  return "test"
endfunction
command! GotoFileInBracket20231014b call GotoFileInBracket20231014b()
" Bu da çalışıyor.

function! GotoFileInBracket20231014c() 
	" [[20231014-rtc-Yatirim101-Videolari]]
	normal! "fyi]
  let f01 = @f
  let f02 = substitute(f01, '[\[\]]', '', 'g')
  let f03 = substitute(f02, '.*', '\0.md', '')
	echo f03
	call v:lua.find_files_20231014_02(f03)
  return f03
endfunction
command! GotoFileInBracket20231014c call GotoFileInBracket20231014c()
" Bu da çalışıyor.

": }}}


```

