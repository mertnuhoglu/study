function! ConvertMarksTxt2MarksTsv()
	" Convert `^M` into new line
	%s//\r/g
	" delete number lines such as 24
	g/^\d\+$/d
	" delete html tags
	%s/<\/\?\w\+>//g
	" wrap text lines inside double quotes
	" note that a text line may start with -.'":;, symbols
	%s/"/'/g
	v/^\d\d:\d\d:\d\d/ s/\(^\([-()# .'":;,]\|\k\).*\n\)\+/"\0"/
	" put ### at the start of each scene
	g/^"$/ s/\n/###/g
	" replace all newlines 
	%s/\n/@@@/g
	" put each scene into a new line
	%s/###/\r/g
	" delete the last @@@
	%s/@@@"$/"/
	" delete all intermediate time digits
	%s#--> \([0-9:,]\+@@@[0-9:,]\+ --> \)\+#--> #g
	%s/@@@"/\t"/
	%s/ --> /\t/
	g/^@@@$/d
	%s/@@@/ <br> /g
	%s/([A-Z]\+)//g
	%s#\(\d\d\),\(\d\d\d\)#\1.\2#g
endfunction
command! ConvertMarksTxt2MarksTsv call ConvertMarksTxt2MarksTsv()

:ConvertMarksTxt2MarksTsv
:wq

