tags:: vim

# 20230404-Vim--Spell-check--imla-denetimi id=g14176

[Neovim: How to setup the spell checker - YouTube](https://www.youtube.com/watch?v=KoL-2WTlr04)

	| z= | düzeltme |
	| ]s | next spell warning |
	| [s | prev spell warning |
	| zg | add to spell dictionary  |

- `:spellrepall`: Bir imla düzeltmesini tüm benzer örnekler için tekrarla

- `~/.config/lvim/spell/en.utf-8.add`: Yeni eklenen kelimelerin veritabanı

- rfr: Generating a spell file: <vimhelp:spell-mkspell>

- `g13901` türü kelimeleri spell check'ten hariç tutmak:

[How to exclude capitalized words from spell checking in Vim? - Stack Overflow](https://stackoverflow.com/questions/18196399/how-to-exclude-capitalized-words-from-spell-checking-in-vim/18196613#18196613)

```
:syn match myExCapitalWords +\<[A-Z]\w*\>+ contains=@NoSpell
```

