tags:: study, tll/sed

# 20230914-sed-Delete-lines-between-matches id=g14687

[regex - Using sed to delete all lines between two matching patterns - Stack Overflow](https://stackoverflow.com/questions/6287755/using-sed-to-delete-all-lines-between-two-matching-patterns)

a01:

```
sed '/^#/,/^\$/{/^#/!{/^\$/!d}}' file.txt
```

> Explanation:
> /^#/,/^\$/ will match all the text between lines starting with # to lines starting with $. ^ is used for start of line character. $ is a special character so needs to be escaped.
> /^#/! means do following if start of line is not #
> /^$/! means do following if start of line is not $
> d means delete

## Problem:

Input:

```
---
id: "20230313-rdb_data"
aliases:
  - "20230313-rdb_data"
tags: []
---

# 20230313-rdb_data id=g13955

```

Output:

```

# 20230313-rdb_data id=g13955

```

```
sed '/^\-\-\-$/,/^\-\-\-$/d' file.txt
```

Dosyaları önden ayıkla, sadece replacement yapacağın dosyaları sed programına gönder:

Test et:

```
gsed -i -e "/^\-\-\-$/,/^\-\-\-$/{d}" 20230308-mtng-Zelimhan-Sourcing-Sureci.md
```

Bu çalıştı.

Şunlar çalışmadı:

```
sed -i -e "/^\-\-\-$/,/^\-\-\-$/{d}" 20230313-sync_rules.md
match="^\-\-\-$"
gsed -i -e "/$match/,/$match/{d}" 20230308-mtng-Zelimhan-Sourcing-Sureci.md
```

Final script:

```
rg -l "^---$" | gxargs -n1 -d '\n' -I {} gsed -i -e "/^\-\-\-$/,/^\-\-\-$/d" {}
```

rfr: [[20230914-sed--Replacement-in-specific-lines-only]]

## Replace tags

Sadece ilk satırda metin replacement yapalım:

```
rg -l "^---$" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s///" {}
```

Bu şablon komutu temel alalım. Şimdi match ve replace keywordlerini koyalım.

```bash
MATCH="analiz"
REPLACE="prd\\/analysis"
rg "tags::.*${MATCH}" 
#> 20230413-Beyza-Analiz-GG.md
#> 1:tags:: analiz
#> 
#> 20230322-mtng-Zelimhan-PMS.md
#> 1:tags:: prj/pms, analiz
```

Bu dosyalarda replacement yapacağız.

Tek bir dosyada test edelim.

```bash
gsed -i -e "1s/${MATCH}/${REPLACE}/" 20230413-Beyza-Analiz-GG.md
```

Bütün dosyalarda test edelim:

```
MATCH="analiz"
REPLACE="prd\\/analysis"
rg -l "tags::.*${MATCH}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s/$MATCH/$REPLACE/" {}
rg -l "^---$" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s///" {}
rg -l "^---$" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s///" {}
```

## Debug 01

```
match="analiz"
replace="prd\\/analysis"
gsed -i -e "1s/${match}/${replace}/" 20230413-Beyza-Analiz-GG.md
# gsed: -e expression #1, char 0: no previous regular expression
```

Hata verdi.

```
gsed -i -e "1s/analiz/prd\/analysis/" 20230413-Beyza-Analiz-GG.md
```

Bu şekilde çalıştı.

```
match="analiz"
replace="prd\\/analysis"
echo "1s/${match}/${replace}/"
#> 1s//prd\/analysis/
```

Neden `${match}` değişkeninin değeri yok oluyor?

Başka bir değişken ismi kullanalım.

```
MATCH="analiz"
REPLACE="prd\\/analysis"
echo "1s/${MATCH}/${REPLACE}/"
```

```
gsed -i -e "1s/${MATCH}/${REPLACE}/" 20230413-Beyza-Analiz-GG.md
```

Böyle çalıştı.

## Debug 02

```
rg -l "tags::.*${MATCH}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s/$MATCH/$REPLACE/" {}
# regex parse error:
#     tags::.*\
#             ^
# error: incomplete escape sequence, reached end of pattern prematurely
```

Tek başına rg komutunu verelim:

```
rg -l "tags::.*${MATCH}" 
# regex parse error:
#     tags::.*\
#             ^
# error: incomplete escape sequence, reached end of pattern prematurely
```

Yine aynı hatayı veriyor.

```
echo "1s/${MATCH}/${REPLACE}/"
#> 1s/\/prd\/analysis/
```

Yine `${MATCH}` değişkeninin içi boşalmış. Nasıl oluyor bu?

a01: Tüm kullanımların hemen öncesinde değişkenleri yeniden set edelim.

```
MATCH="analiz"
REPLACE="prd\\/analysis"
rg -l "tags::.*${MATCH}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s/$MATCH/$REPLACE/" {}
```

Bu şekilde çalıştı.

