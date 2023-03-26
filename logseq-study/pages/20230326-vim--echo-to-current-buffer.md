tags:: vim

# 20230326-vim--echo-to-current-buffer id=g14134

[Dump the output of internal vim command into buffer - Vi and Vim Stack Exchange](https://vi.stackexchange.com/questions/8378/dump-the-output-of-internal-vim-command-into-buffer)

## a01: `:redir`

## a02: `:enew|pu=execute('scriptnames')`

```vim
:h :redir
:h :silent
:h :scriptnames
:h :enew
:h :put
:h execute()
```

```vim
echo execute('echon "foo"')
=> foo
pu=execute('echon "foo"')
# error
put ='path'
=>path
put =echo 'path'
# Error: Undefined variable echo
put =execute('echo "path"')
# Error: Missing quotes for echo
```

```
function! Test01()  
  return "hello"
endfunction
execute('echo Test01()')
=>hello
```

Bir değer dönen fonksiyonu çağırırsan, bunun çıktısını put ile buffera yazdırabilirsin:

```
put=execute('echo Test01()') 
=>hello
```

## a03: Redir fonksiyonu ile scratch buffera çıktı almak

[Redirect the output of a Vim or external command into a scratch buffer](https://gist.github.com/romainl/eae0a260ab9c135390c30cd370c20cd7)

rfr: function! Redir(cmd) " <url:file:///~/prj/vim_repos/my-vim-custom/plugin/my-vim-custom.vim#r=g14135>

```
:Redir hi
# yeni scratch buffer içine koyar tüm çıktıyı
:Redir echo "hello"
:Redir echo expand("%:.:h")
=> logseq-study/pages
```

