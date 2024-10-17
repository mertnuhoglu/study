tags:: study, prg/rlang, org/pcm/atm

- # 20230916-Script-Generation-from-CSV-Files id=g14709
  
  rfr: tags-replacements /Users/mertnuhoglu/projects/study/logseq-study/pages/ex/20230916-Script-Generation-from-CSV-Files/tags_replacements.tsv
- ## Özet
  type:: block01
- rfr: [[20230924-Script--Apply-Data-To-Template]]
- ## Detay
  
  Şu çıktıyı oluşturmak istiyorum:
  
  ```sh
  MATCH="analiz"
  REPLACE="prd\\/analysis"
  rg -l "tags::.*${MATCH}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s/$MATCH/$REPLACE/" {}
  ```
  
  Ancak bu komutları, yüzlerce farklı `MATCH` ve `REPLACE` değeri için oluşturmak istiyorum.
  
  Bu `MATCH` ve `REPLACE` değerlerini bir CSV dosyasında tutuyorum:
  
  rfr: `~/prj/study/logseq-study/pages/ex/20230916-Script-Generation-from-CSV-Files/d01.tsv`
  
  ```tsv
  match	replace
  analiz	prd\\/analysis
  "analysis"	"prd\\/analysis"
  ```
  
  Baştaki çıktıyı bir şablon dosyası içine koyuyorum:
  
  rfr: `~/prj/study/logseq-study/pages/ex/20230916-Script-Generation-from-CSV-Files/template01.txt`
  
  ```sh
  MATCH="{match}"
  REPLACE="{replace}"
  rg -l "tags::.*${{MATCH}}" | gxargs -n1 -d '\n' -I {{}} gsed -i -e "1s/${{MATCH}}/${{REPLACE}}/" {{}}
  ```
  
  Bu şablonu scriptle okuyorum, tsv dosyasındaki her bir satırdaki atributları bu şablondaki değişkenlere uyguluyorum (interpolate).
  
  Sonuçta istediğim çıktıyı üretiyorum:
  
  rfr: `~/prj/study/logseq-study/pages/ex/20230916-Script-Generation-from-CSV-Files/out01.txt`
  
  ```sh
  MATCH="analiz"
  REPLACE="prd\\/analysis"
  rg -l "tags::.*${MATCH}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s/${MATCH}/${REPLACE}/" {}
  
  MATCH="analysis"
  REPLACE="prd\\/analysis"
  rg -l "tags::.*${MATCH}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s/${MATCH}/${REPLACE}/" {}
  ```
  
  Script burada:
  
  rfr: `~/prj/study/logseq-study/pages/ex/20230916-Script-Generation-from-CSV-Files/e01.R`
  
  ```r
  library(dplyr)
  library(readr)
  
  d01 <- readr::read_tsv("d01.tsv")
  template <- readr::read_file("template01.txt")
  
  d02 <- d01 |>
  mutate(cmd = glue::glue(template))
  
  lines <- paste0(d02$cmd, collapse = "\n")
  readr::write_file(lines, file = "out01.txt")
  ```
- ## Parametrik Script id=g14751
  
  Yukarıdaki scriptin girdi ve çıktıları sabit.
  
  Girdi ve çıktılarını parametrik hale getirelim. 
  
  rfr: `~/prj/study/r/ex/study_r/argument_to_rscript/e02.R`
  
  ```
  # Usage:
  Rscript e02.R d01.tsv template01.txt > out02.txt
  ```
