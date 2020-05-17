---
title: "process anki: extract sentences from tatoeba database"
date: 2019-05-09T13:06:23+03:00 
draft: true
description: ""
tags:
categories: anki
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com

---

# process anki: extract sentences from tatoeba database

## ex01: with repeatable data

Sample files for:

`~/Downloads/anki/tatoeba_sentences/sentence_ids_eng.tsv`
`~/Downloads/anki/tatoeba_sentences/sentence_pairs.tsv`

Check `~/projects/study/code/anki/ex/process_anki_tatoeba_extract_sentences/e01/sentence_pairs_ex.tsv`

Check `~/projects/study/code/anki/ex/process_anki_tatoeba_extract_sentences/e01/sentence_ids_eng_ex.tsv`

input file: `~/projects/study/code/anki/ex/process_anki_tatoeba_extract_sentences/e01/example_en.tsv`

script: `~/projects/anki_english/scripts/tatoeba_example_en_2_sentence_id.R`

``` 
cd ~/projects/study/code/anki/ex/process_anki_tatoeba_extract_sentences/e01
Rscript ~/projects/anki_english/scripts/tatoeba_example_en_2_sentence_id.R sentence_ex_20200516.tsv sentence_ids_eng_ex.tsv sentence_pairs_ex.tsv example_en.tsv
``` 

output: `~/projects/study/code/anki/ex/process_anki_tatoeba_extract_sentences/e01/gen/sentence_ex_20200516.tsv`

## complete process script:

script: `~/projects/anki_english/scripts/anki_process_tatoeba_sentences`

ex with reproducible datasets:

``` 
tags="sentence_ex_20200516"
sentence_ids_eng="sentence_ids_eng_ex.tsv"
example_en="example_en.tsv"
~/projects/anki_english/scripts/anki_process_tatoeba_sentences "${tags}" "${sentence_ids_eng}" "${example_en}"
``` 

ex with actual datasets:

``` 
cd ~/projects/myrepo/logbook/ozgur_emin/english/sentence_20200516
tags="sentence_20200516"
sentence_ids_eng="~/Downloads/anki/tatoeba_sentences/sentence_ids_eng.tsv"
example_en="~/projects/myrepo/logbook/ozgur_emin/english/sentence_20200516/example_en.tsv"
~/projects/anki_english/scripts/anki_process_tatoeba_sentences "${tags}" "${sentence_ids_eng}" "${example_en}"
``` 


