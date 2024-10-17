tags:: study, my/script, prg/rlang, f/blg

# 20230924-Script--Apply-Data-To-Template 
	- id=g14760

rfr: parent: [[20230916-Script-Generation-from-CSV-Files]]

rfr: use-case: 

	[[20230926-Etiket-Duzeltme]] <url:file:///~/prj/myrepo/logseq-myrepo/pages/20230926-Etiket-Duzeltme.md#r=g14786>
	[[20230925-Etiket-Tasnifi]] <url:file:///~/prj/study/logseq-study/pages/20230925-Etiket-Tasnifi.md#r=g14778>

rfr: R-script: [[apply_data_to_template.R]]

rfr: shell-script: [[apply_data_to_template]]

```
# Usage:
# apply_data_to_template d01.tsv template01.txt > out.txt
apply_data_to_template ~/prj/study/logseq-study/pages/ex/20230916-Script-Generation-from-CSV-Files/d01.tsv ~/prj/study/logseq-study/pages/ex/20230916-Script-Generation-from-CSV-Files/template01.txt
```

İki tane girdin var:

- Şablon metin
- Bu şablon metindeki placeholderlara uygulanacak veriyi içeren bir tablo

Çıktı:

- Girdi tablodaki her bir satırdaki veriler, şablon metine uygulanmıştır. 

Örnek:

Şablon:

```
Merhaba {person}. {city} çok güzel.
```

Girdi veri tablosu:

```
person	city
Hakan	İstanbul
Orhan	Ankara
```

Çıktı:

```
Merhaba Hakan. İstanbul çok güzel.
Merhaba Orhan. Ankara çok güzel.
```

