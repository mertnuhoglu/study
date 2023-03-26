
# 20230325-Luasnip-expand-or-jump-kisayol-mantigi id=g14111

- İki tane alternatif kısayol var

  - a01: <c-y> luasnip expand or jump <url:file:///~/prj/private_dotfiles/.config/nvim/lua/mert/completions.lua#r=g14112>

  - a02: ['<C-t>'] = cmp.mapping.confirm({ select = true }),  -- autocomplete first if none selected <url:file:///~/prj/private_dotfiles/.config/nvim/lua/mert/completions.lua#r=g14113>

Örneğin şöyle bir snippet tanımladın:

  ls.parser.parse_snippet("expand", "-- this is expanded") -- luasnip expand example <url:file:///~/prj/private_dotfiles/.config/nvim/lua/mert/snippets/e01.lua#r=g14114>

Bunu kullanmak için:

```
expand@
```

İmleç `@` konumundayken `^t` veya `^y` ile expand edersin. Tab tuşlarıyla da jump edersin.

- Tab tuşları ya IntelliJ gibi davranır (autocomplete de yapar), ya da sadece zıplama için kullanılır:

  - a01: Super <Tab> IntelliJ-like https://github.com/hrsh7th/nvim-cmp/wiki/Example-mappings#intellij-like-mapping <url:file:///~/prj/private_dotfiles/.config/nvim/lua/mert/completions.lua#r=g14117>

  - a02: Super <Tab> jump or expand https://github.com/hrsh7th/nvim-cmp/wiki/Example-mappings#luasnip <url:file:///~/prj/private_dotfiles/.config/nvim/lua/mert/completions.lua#r=g14116>

Alternatif olarak `RET` ile de expand edebilirsin:

  <CR> Select menu item https://github.com/hrsh7th/nvim-cmp/wiki/Example-mappings#safely-select-entries-with-cr <url:file:///~/prj/private_dotfiles/.config/nvim/lua/mert/completions.lua#r=g14118>


