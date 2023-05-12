tags:: study, vim, tpc/spell-check, mypst

# 20230427-spell-checking---Toggle-spellcheck-globally---Vi-and-Vim-Stack-Exchange id=g14261

rfr: [spell checking - Toggle spellcheck globally - Vi and Vim Stack Exchange](https://vi.stackexchange.com/questions/42023/toggle-spellcheck-globally)

It is possible to `set nospell` in `.vimrc` to disable it globally. But it doesn't [override filetype plugins][1].

Is it possible to toggle between `spell` and `nospell` globally so that I can at least disable it once for all open buffers?


  [1]: https://stackoverflow.com/questions/54169337/set-nospell-in-vimrc-has-no-effect

Answer:

```vim
:bufdo set spell
```

