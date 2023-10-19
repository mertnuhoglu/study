tags:: study
date:: 20231018

# 20231018-Vimscript-Grep-Function id=g15043

- a01: Dosya içinde grep

```vim
function FileContainsPattern(pattern, filePath)
	" Source: Bard
	let pattern = a:pattern
	let filePath = a:filePath
  let matches = Grep(pattern, [filePath])
  return matches > 0
endfunction

function Rcb20231018()
	let filePath = "/path/to/file.txt"
	" let pattern = "hello"
	let pattern = "file"

	if FileContainsPattern(pattern, filePath)
		echo "The pattern '" . pattern . "' was found in the file '" . filePath . "'."
	else
		echo "The pattern '" . pattern . "' was not found in the file '" . filePath . "'."
	endif	
endfunction
```

Bu yukarıdaki fonksiyon, bir dosyanın içinde grep yapıyor.

- a02: String içinde grep

```
function GrepInString(pattern, string)
	let pattern = a:pattern
	let string = a:string
  let matches = match(string, pattern)
  return matches > 0
endfunction

function Rcb20231018_02()
	let string = "This is a sample text."
	let pattern = "sample"

	if GrepInString(pattern, string)
		echo "The pattern '" . pattern . "' was found in the string '" . string . "'."
	else
		echo "The pattern '" . pattern . "' was not found in the string '" . string . "'."
	endif
endfunction
```

