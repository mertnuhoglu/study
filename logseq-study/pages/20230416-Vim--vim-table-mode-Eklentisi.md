tags:: study, vim

# 20230416-Vim--vim-table-mode-Eklentisi id=g14219

| name  | address           | phone      |
|-------|-------------------|------------|
| mert  | idealtepe maltepe | 30-3003920 |
| orhan | erenköy kadıköy   | 49-9943949 |

Kısayollar:

| kısayol | komut             | açıklama              |
|---------|-------------------|-----------------------|
| NA      | :TableModeToggle  | aktifleştirme         |
| NA      | :TableModeRealign | hizalama              |
| NA      | :Tableize         | csv satırlar -> tablo |
| Stdd    |                   | delete row            |
| Stdc    |                   | delete column         |
| tiC     |                   | insert column left    |
| tic     |                   | insert column right   |
| ts      |                   | sort table            |
| [\      |                   | prev cell             |
| ]\      |                   | next cell             |
| St?     |                   | echo cell address     |
| V: Sttt |                   | Tableize              |
| V: SttT |                   | Tableize/..           |

- csv example:

```
col1,col2
101,ali
102,istanbul
```

-> `:Tableize`

| col1 | col2     |
|------|----------|
| 101  | ali      |
| 102  | istanbul |

- tsv example:

```
col1;col2
101;ali
102;istanbul
```

-> `:Tableize/;`

| col1 | col2     |
|------|----------|
| 101  | ali      |
| 102  | istanbul |

