---
title: "Study ctags"
date: 2021-05-23T11:56:33+03:00 
draft: true
description: ""
tags:
categories: bash, 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css

---

# Article: Use vim with ctags 

[Use vim with ctags | what-why-how](https://kulkarniamit.github.io/whatwhyhow/howto/use-vim-ctags.html)

Check `~/.ctags`

Run `ctags`

Update index files automatically:

```vim
autocmd BufWritePost *.c,*.h silent! !ctags . &
```

